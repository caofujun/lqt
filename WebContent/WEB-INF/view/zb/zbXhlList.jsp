<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>网络直报</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
	<body>	
		<div id="tb" class="m_search" style="display: none;">
			<form id="zbform" method="post" action="${xhlZbURL}">
			用户名：<input type="text" id='userName' name="userName" class="easyui-validatebox" value=""/>
			密码：<input type="text" id='password' name="password" class="easyui-validatebox" value=""/>
			<input type="hidden" id="channel" name="channel" value="${channel}"/>
			<input type="hidden" id="dicOffice" name="dicOffice" value=""/>
			<input type="hidden" id="patientMain" name="patientMain" value=""/>
			<input type="hidden" id="infectInfo" name="infectInfo" value=""/>
			<input type="hidden" id="pathoInfo" name="pathoInfo" value=""/>
			<input type="hidden" id="antibInfo" name="antibInfo" value=""/>
			</form>			
			<span>调查日期:</span>
				<select id="surveyDate" onchange="zbXhl.query()">
					<c:forEach items="${xl001BrxxList}" var="xl001Brxx">
						<option value="<fmt:formatDate value="${xl001Brxx.votedate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />"><fmt:formatDate value="${xl001Brxx.votedate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></option>
					</c:forEach>
				</select>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:void(0);" id="id_network_report_btn" onclick="zbXhl.reportXhl();"><i class="icon iconfont">&#xe602;</i><span>网络直报</span></a>
				</div>
				<span id="loading" style="display: none;"><img src="${webroot}/resources/load/images/loading.gif" style="margin-bottom:-3px;"/> 数据提交中...</span>
			</div>
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="zbXhl.exportHtml();"><i class="icon iconfont">&#xe628;</i><span>导出直报文件</span></a>
				</div>
			</div>
		</div>
		<div id="zbXhlPanel"></div>
		<div id="waiting"></div>
<script type="text/javascript">
var zbXhl = {
	panel : 'zbXhlPanel',
	reportCount : 0,
	query : function() {
		$('#'+zbXhl.panel).datagrid({
            url:'${webroot}/zbRecord/f_json/xhlPageQuery.shtml',
            queryParams: {
            	'startDate':$('#surveyDate').val(),
            	'endDate':$('#surveyDate').val()+' 23:59:59'
            }
		});
	},
	exportHtml :function(){
		//数据验证
		var startDate = $('#surveyDate').val();
		var endDate = $('#surveyDate').val()+' 23:59:59';
		$.ajax({
	        url: '${webroot}/zbRecord/f_view/exportZbValidate.shtml',
            type: 'post',
            data: { startDate: startDate, endDate: endDate },
            dataType: 'json',
            success : function(json) {
            	if (json.result=='success') {
            		window.location.href = '${webroot}/zbRecord/f_view/exportZbHtml.shtml?startDate=' + startDate + '&endDate=' + endDate;
            	} else {
            		$.messager.alert({title : '数据异常',msg : json.msg});
            	}
            }
		});
		
	},
	reportXhl: function(){
		if($('#userName').val()==''||$('#password').val()==''){
			 $.messager.alert("直报失败", "用户名和密码不能为空");  
		}else{
			$.ajax({
		        url: '${webroot}/zbRecord/f_json/findXhl.shtml',
	            type: 'post',
	            data: { startDate: $('#surveyDate').val(), endDate:$('#surveyDate').val()+' 23:59:59' },
	            dataType: 'json',
	            beforeSend: function() {
	            	$('#loading').show();
					$('#id_network_report_btn').hide();
	            },
	            success : function(json) {
	            	if(json.result=='success'){
	            		var data = json.data;
	            		$('#dicOffice').val(data[0]);
	            		$('#patientMain').val(data[1]);
	            		$('#infectInfo').val(data[2]);
	            		$('#pathoInfo').val(data[3]);
	            		$('#antibInfo').val(data[4]);
	            		$('#zbform').submit();
	            		$.messager.progress({
							text : '正在提交中....',
							interval : 200
						});
	            	}else if(json.result=='error'){
	            		 $.messager.alert("直报失败", json.msg);  
	            	}
	            },
	            complete: function() {
	            	$('#loading').hide();
					$('#id_network_report_btn').show();
	            }
			});
		}
		
	}
};
$(document).ready(function () {
	$('#' + zbXhl.panel).datagrid({
		fit: true,
        autoRowHeight: true,
        striped: true,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/zbRecord/f_json/xhlPageQuery.shtml',
        queryParams: {
        	'startDate':$('#surveyDate').val(),
        	'endDate':$('#surveyDate').val()+' 23:59:59'
        },
        columns:[ 
	       	[
	            {field:'itemName',title:'横断面数据名称',sortable:true,width:200},
	            {field:'unReportAmount',title:'总数',sortable:true,width:80,align:'center'}      	
	        ]
        ],
        rownumbers:true,
        toolbar:'#tb'
	});
});
</script>
	</body>
</html>