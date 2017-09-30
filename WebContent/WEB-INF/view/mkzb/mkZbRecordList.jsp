<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>网络直报</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
	<body>
		<div id="form1" style="display: none;">	
<!-- 			<form id="zbform" method="post"> -->
			<form id="zbform" method="post" action="${zbURL}">
			<input type="hidden" id="channel" name="channel" value="${channel}"/>
			<input type="hidden" id="inputData" name="inputData" value=""/>
			</form>
		</div>
		<div id="tb" class="m_search" style="display: none;">			
			<span>上报期限:</span>	
			<select id="reportStartYear" class="easyui-combobox" style="width: 60px;" data-options="editable:false,onChange:function(newValue,oldValue){zbRecord.query();}">
				<c:forEach items="${years}" var="year">
					<option value="${year}">${year}</option>
				</c:forEach>
			</select>
			<select id="reportStartMonth" class="easyui-combobox" style="width: 40px;" data-options="editable:false,onChange:function(newValue,oldValue){zbRecord.query();}">
				<c:forEach items="${months}" var="month">
					<option value="${month}" <c:if test="${month == 1}">selected</c:if>>${month}</option>
				</c:forEach>
			</select>
			 ~ 
			<select id="reportEndYear" class="easyui-combobox" style="width: 60px;" data-options="editable:false,onChange:function(newValue,oldValue){zbRecord.query();}">
				<c:forEach items="${years}" var="year">
					<option value="${year}">${year}</option>
				</c:forEach>
			</select>
			<select id="reportEndMonth" class="easyui-combobox" style="width: 40px;" data-options="editable:false,onChange:function(newValue,oldValue){zbRecord.query();}">
				<c:forEach items="${months}" var="month">
					<option value="${month}" <c:if test="${month == curMonth-1}">selected</c:if>>${month}</option>
				</c:forEach>
			</select>
			<div class="btn_r">
<!-- 				<span style="color:red">上报前请确保本机能连接互联网！</span> -->
<!-- 				<div class="n_btn_grey"> -->
<!-- 					<a href="javascript:void(0);" onclick="zbRecord.testConnection();"><span>测试是否连接</span></a> -->
<!-- 				</div> -->
				<div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="zbRecord.report();"><i class="icon iconfont">&#xe602;</i><span>网络直报</span></a>
				</div>
			</div>
		</div>
		<div id="waiting">
		<span style="color:red;font-weight：bold;" id="paitentCount"></span>
		</div>
		<div id="zbRecordPanel"></div>
		
		</div>
<script type="text/javascript">
var zbRecord = {
	panel : 'zbRecordPanel',
	count : 0,
	query : function() {
		$('#'+zbRecord.panel).datagrid({
            url:'${webroot}/mkZbRecord/f_json/pageQuery.shtml',
            queryParams: {
            	'reportStartYear':$('#reportStartYear').combobox('getValue'),
            	'reportStartMonth':$('#reportStartMonth').combobox('getValue'),
            	'reportEndYear':$('#reportEndYear').combobox('getValue'),
            	'reportEndMonth':$('#reportEndMonth').combobox('getValue'),
            }
		});
	},

    report:function() {
    	$.messager.confirm('提示', '是否确认上报?', function (r) {
        	if (!r) {
        		return ;
        	}
        	if(zbRecord.count == 0){
        		$.messager.show({ title: '提示', msg: '直报患者人数不能为空！' });
        		return ;
        	}
	        $.messager.progress({ // 显示进度条  
	       		title:"院感直报",  
    	   		text:"正在处理,请耐心等待...",  
    	    	interval:100  
	    	});
           	var reportStartYear = $('#reportStartYear').combobox('getValue');
           	var reportStartMonth = $('#reportStartMonth').combobox('getValue');
           	var reportEndYear = $('#reportEndYear').combobox('getValue');
           	var reportEndMonth = $('#reportEndMonth').combobox('getValue');
           	zbRecord.reportData(reportStartYear,reportStartMonth,reportEndYear,reportEndMonth);
    	});
    },
    //获取患者信息,并且生成患者信息文件夹
    reportData:function(reportStartYear,reportStartMonth,reportEndYear,reportEndMonth) {
    		$.ajax({
		        url: '${webroot}/mkZbRecord/f_json/findReportDataByItem.shtml',
	            type: 'post',
	            data: {reportStartYear: reportStartYear, reportStartMonth: reportStartMonth, reportEndYear: reportEndYear, reportEndMonth: reportEndMonth},
	            dataType: 'json',
	            success : function(json) {
	            	if(json.result=="success"){
	            		zbRecord.zbResult(json.data,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth);
	            	}else{
	            		$.messager.progress('close');
	            		$.messager.alert('提示','直报数据异常！！','error');  
	            	}
				},  
			    error : function(XMLHttpRequest, textStatus, errorThrown) {
			    	$.messager.progress('close');
					$.messager.alert('提示','直报数据异常！！','error');  
			    }
    		 });
    },
    zbResult : function(reportResult,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth){
    	$.ajax({
	        url: '${webroot}/mkZbRecord/f_json/zbResult.shtml',
            type: 'post',
            dataType: "JSON",
            data: { reportResult: reportResult,
            	reportStartYear : reportStartYear,
            	reportStartMonth : reportStartMonth,
            	reportEndYear : reportEndYear,
            	reportEndMonth : reportEndMonth,	
            },
            success : function(json) {
            	if(json.result=="success"){
            		$.messager.show({ title: '提示', msg: '直报文件生成成功！' });
    	        	$.messager.progress('close');
    	        	var fileUrl=json.data;
    	        	window.location.href="${webroot}/mkZbRecord/s_view/downLoadFile.shtml?fileUrl=" + encodeURI(encodeURI(fileUrl))+"";
    	        	zbRecord.query();
            	}else{
            		$.messager.progress('close');
            		$.messager.alert('提示','回写上报结果异常！！','error');  
            	}
	        	
			},  
		    error : function(XMLHttpRequest, textStatus, errorThrown) {
		    	zbRecord.itemCodesTemp = [];
             	$.messager.progress('close');
				$.messager.alert('提示','回写上报结果异常！！','error');  
		    }  
    });
    }
};
$(document).ready(function () {
	$('#' + zbRecord.panel).datagrid({
		fit: true,
        autoRowHeight: true,
        striped: true,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/mkZbRecord/f_json/pageQuery.shtml',
        columns:[ 
	       	[
	            {field:'itemName',title:'监测项目',sortable:true,width:200},
	            {field:'unReportAmount',title:'待报',sortable:true,width:80,align:'center',},
	           	{field:'reportAmount',title:'已报',sortable:true,width:80,align:'center'},
	        ]
        ],
        onLoadSuccess: function(data) {
			var rows = $('#' + zbRecord.panel).datagrid('getRows');
			zbRecord.count = rows[0]['unReportAmount']; 
        },
        rownumbers:true,
        toolbar:'#tb'
	});
});
</script>
	</body>
</html>