<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>问卷反馈</title>
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
	                url:'${webroot}/qsQuestionnaire/f_json/findCanEnter.shtml',
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
							{field:'title',title:'问卷',sortable:true,width:180},
		                    {field:'startTime',title:'发布日期',sortable:true,width:100},
		                    {field:'endTime',title:'有效时间',sortable:true,width:100,formatter:function(value, r) {
		                    	return (r.endTime!=null&&r.endTime!=''?r.endTime:'长期有效');
		                    }},
		                    {field:'answerCount',title:'答卷人数',sortable:true,align:'center',width:80,formatter:function(value, r) {
		                    	return ['<a class="count" href="${webroot}/qsSurveyRecord/f_view/index.shtml?qid=',r.qid,'&back=',location.href,'">',r.answerCount,'</a>'].join('');
		                    }},
		                    {field:'code',title:'操作',width:60,align:'center',sortable:true,formatter:function(value, r) {
	                        	var _cont = [];	                       
	                        	_cont.push('<a class="ico_editor" title="操作" href="javascript:feedbackShow(',r.qid,')"></a>');	                     
		                    	return _cont.join('');
		                    }}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb',
	            });
			});
			
			//分享
		    function feedbackShow(quesId, publish){		
		    	parent.menuInfo.clickMenu('查看调查结果及反馈','/qsQuestionnaire/f_view/feedbackList.shtml?qid='+quesId,true);
		    }
		</script>
	</body>
</html>
