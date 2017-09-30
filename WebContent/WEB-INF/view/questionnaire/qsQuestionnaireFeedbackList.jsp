<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>问卷录入</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
        <style>
        	.sf_infor td, .sf_infor th { padding: 3px 0; }
        </style>
	</head>
	<body>
		<div id="wjdcQuestionnairePanel"></div>
		<div id="tb" class="m_search">
		    <div class="m_search">
		<table class="sf_infor" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<span class="pro_text">当前问卷：</span>						
					<select id="" name="" onchange="chooseWjdc(this.value)" style="width:130px;">
					<c:forEach items="${qsQuestionnaireList}" var="qs">
						<option value="${qs.qid}" <c:if test="${qs.qid == qsQuestionnaire.qid}">selected="selected"</c:if>>${qs.title}</option>
					</c:forEach>
					</select>
				</td>
				<td>
					<span class="pro_text">调查日期：</span>				
					<input type="text" name="queryStartDate" id="queryStartDate" value="${fn:substring(qsQuestionnaire.createTime,0,10)}" class="Wdate text" onclick="WdatePicker()" style="width:80px;">~
					<input type="text" name="queryEndTime" id="queryEndTime" value="${endTime}" class="Wdate text" onclick="WdatePicker()" style="width:80px;">
				</td>			
			
				<td>
					<span class="pro_text">结果显示条件：</span>
					单题分数<=<input type="text" style="width:10px;" name="dcValue" id="dcValue" value="${qsQuestionnaire.dcValue}" />分
				</td>
				<td><input type="button" class="btn_search" value="查询" onClick="query()"> <input type="button" class="btn" onclick="savefeedbackValue('${qsQuestionnaire.qid}')" value="添加审核"></td>
			</tr>
		</table>
	</div>
	<div class="report_tab" style="margin:5px 0;"><a href="javascript:void(0)" onclick="exportFeedback()" class="tab_button button_excel">下载问卷结果</a></div>
	<h1 style="text-align:center;margin-bottom:5px;font-family: font-family:'Microsoft Yahei';">${qsQuestionnaire.title}（不满意）</h1>
		</div>
		<div id="tips">
		
		</div>
		<div id="pop">
			<iframe style="display: none;" id="download"></iframe>
			<div id="loading" style="margin-top: 35px;margin-left: 80px; display: none;">
				<img src="${webroot}/resources/images/loading.gif"/>
				<span>下载数据加载中...</span>
			</div>
		</div>

		<script type="text/javascript">
			$(document).ready(function () {
				<c:if test="${qsQuestionnaire.dcValue!=null}">
				query();
				</c:if>
				<c:if test="${qsQuestionnaire.dcValue==null}">
				showTips();
				</c:if>
			});
			function showTips(){
				$('#tips').html('<h2 style="text-align:center;margin-bottom:20px;color:red;font-family: font-family:\"Microsoft Yahei\";">请根据单题分数值查询！</h2>');
			}
			function query(){
				$('#wjdcQuestionnairePanel').datagrid({
				  	nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                fit: true,
	                queryParams: {
		                'qid': '${qsQuestionnaire.qid}',
		                'queryStartDate': $('#queryStartDate').val(),
		                'queryEndTime': $('#queryEndTime').val(),
		                'dcValue': $('#dcValue').val()
		    		 },
	                collapsible:true,
	                url:'${webroot}/qsQuestionnaire/f_json/pageQueryFeedback.shtml',
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
							{field:'surveyTime',title:'提交问卷时间',sortable:true,width:140},
							{field:'catName',title:'来源',sortable:true,width:80},
							{field:'ttitle',title:'题目详情',sortable:true,width:200},
							{field:'optName',title:'不满意原因',sortable:true,width:80},
							{field:'patientName',title:'患者姓名',sortable:true,width:80},
							{field:'patientPhone',title:'电话',sortable:true,width:100},
							{field:'inputValue',title:'其他',sortable:true,width:100}							
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb'
	            });
			}
			//选择问卷
			function chooseWjdc(qid){
				location.href="${webroot}/qsQuestionnaire/f_view/feedbackList.shtml?qid="+qid;
			}
			
			function savefeedbackValue(qid) {
				Comm.dialog({
		        	url:'${webroot}/qsQuestionnaire/f_view/qsFeedbackValue.shtml?qid='+qid,
		            title: "请设置审核触发条件",
		    		width: 550,
		    		height: 150
		        });
			}
			
			function exportFeedback(){
				$('#pop').window({
		    		  title:'调查结果及反馈导出',   
				      width:250,   
				      height:150,   
				      modal:true  
			 	 });
			 	 
			 	$('#loading').show();
			 	$('#download').attr('src','${webroot}/qsQuestionnaire/f_json/exportFeedback.shtml?qid=${qsQuestionnaire.qid}&queryStartDate='+$('#queryStartDate').val()+'&queryEndTime='+$('#queryEndTime').val()+'&dcValue='+$('#dcValue').val());
			 	 window.setTimeout(function(){
			 	 	$('#loading').hide();
			 	 	$('#pop').window('close');
			 	 },5000);
			}
			
		</script>
	</body>
</html>
