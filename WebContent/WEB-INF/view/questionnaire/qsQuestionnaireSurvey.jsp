<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>问卷录入</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="wjdcQuestionnairePanel"></div>
		
		<script type="text/javascript">
			$(document).ready(function () {
				$('#wjdcQuestionnairePanel').datagrid({
	                nowrap: true,
	                autoRowHeight: true,
	                striped: true,
	                fitColumns: false,
	                fit: true,
	                collapsible:true,
	                url:'${webroot}/qsQuestionnaire/f_json/findCanEnter.shtml?type=${type}',
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
							{field:'title',title:'问卷',sortable:true,width:180},
		                    {field:'startTime',title:'发布日期',sortable:true,width:100},
		                    {field:'endTime',title:'有效时间',sortable:true,width:100,formatter:function(value, r) {
		                    	if(r.status == 0) return '未发布';
		                    	if(r.isExpire == 1) return '已过期';
		                    	//判断是否过期
		                    	return (r.endTime!=null&&r.endTime!=''?r.endTime:'长期有效');
		                    }},
		                    {field:'answerCount',title:'答卷人数',sortable:true,align:'center',width:80,formatter:function(value, r) {
		                    	return ['<a class="count" href="${webroot}/qsSurveyRecord/f_view/index.shtml?qid=',r.qid,'&back=',location.href,'">',r.answerCount,'</a>'].join('');
		                    }},
		                    {field:'code',title:'操作',width:60,align:'center',sortable:true,formatter:function(value, r) {
	                        	var _cont = [];
	                        	if('${type}' === 'input')
	                        		_cont.push('<a class="ico_editor" title="现场问卷录入" href="javascript:openSurvey(',r.qid,',\'',r.title,'\')"></a>');
	                        	else if('${type}' === 'phone')
	                        		_cont.push('<a class="ico_editor" title="电话问卷录入" href="javascript:openSurvey(',r.qid,',\'',r.title,'\')"></a>');
	                        	else if('${type}' === 'internet')
	                        		_cont.push('<a class="ico_editor" title="网络问卷录入" href="javascript:openSurvey(',r.qid,',\'',r.title,'\')"></a>');
	                        	if (r.status == 1) {
	                        		_cont.push('<a href="javascript:share(',r.qid,',',r.status,');" class="ico_tag" title="分享"></a>');
	                        	}
		                    	return _cont.join('');
		                    }}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb',
	            });
			});
			
			function openSurvey(qid, title) {
				Comm.dialog({
		        	url:'${webroot}/qsSurveyRecord/f_view/toedit.shtml?type=${type}&qid='+qid,
		    		type: 'iframe',
		            title: title,
		    		width: 750,
		    		height: 420
		        });
			}
			//分享
		    function share(quesId, publish){
		    	if(publish==0){
		    		$.messager.show({ title : '提示', msg : '请先公开问卷！' });
		    		return;
		    	}
		    	var isHttps=getRootPath_web();
		    	if(isHttps.indexOf("https")>-1){
		    	parent.menuInfo.clickMenu('分享问卷','/qsQuestionnaire/f_view/questShare.shtml?qId='+quesId+"&isHttps=yes",true);
		    	}else{
			    parent.menuInfo.clickMenu('分享问卷','/qsQuestionnaire/f_view/questShare.shtml?qId='+quesId+"&isHttps=no",true);
		    	}
		    }
		</script>
	</body>
</html>
