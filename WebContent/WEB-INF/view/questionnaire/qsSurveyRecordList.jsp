<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>问卷答题详细</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="wjdcSurveyPanel"></div>
		<div id="tb" class="m_search">
		    <input type="button" class="btn_return" value="返回" onclick="location.href='${param.back}'"/>
		</div>
		
		<script type="text/javascript">
			var wjdcSurvey = {
				panel : 'wjdcSurveyPanel',
				//录入问卷调查
			    inputWj : function(id) {
					Comm.dialog({
						url: '${webroot}/qsSurveyRecord/f_view/toedit.shtml?success=parent.wjdcSurvey.query()&id=' + id,
					    type: 'iframe',
					    title:'编辑问卷调查',
					    width: 750,
					    height: 420
					});
			    },
			  	//查询
				query : function() {
				    $('#'+wjdcSurvey.panel).datagrid({
				        pageNumber: 1
				    });
				}
			};
			$(document).ready(function () {
				$('#'+wjdcSurvey.panel).datagrid({
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                fit: true,
	                collapsible:true,
	                url:'${webroot}/qsSurveyRecord/f_json/pageQuery.shtml?qid=${param.qid}',
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
		                    {field:'title',title:'问卷标题',sortable:true,width:250,formatter:function(value, r) {
		                    	return ['<a href="javascript:wjdcSurvey.inputWj(\'',r.rid,'\');">',r.title,'</a>'].join('');
		                    }},
		                    {field:'surveyTime',title:'调查时间',sortable:true,width:120},
		                    {field:'depNoName',title:'调查科室',sortable:true,width:120},
		                    {field:'patientName',title:'调查对象',sortable:true,width:90,formatter:function(value, r) {
		                    	if((r.patientName===null || r.patientName==='')
		                    			&& (r.patientPhone===null || r.patientPhone==='')) return '匿名';
		                    	return r.patientName;
		                    }},
		                    {field:'dtNum',title:'答题/总题数',sortable:true,align:'center',width:100,formatter:function(value, r) {
		                    	return [r.dtNum,'&nbsp;/&nbsp;',r.tmCount].join('');
		                    }},
		                    {field:'dtScore',title:'问卷评分',sortable:true,align:'center',width:80}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>
