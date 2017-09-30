<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>上报日志</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
	<body>
	<div id="logList"></div>
<!-- 		<table width="96%" align="center" > -->
<%-- 		<c:forEach items="${zbLogList}" var="zbRecord"> --%>
<!-- 			<tr> -->
<!-- <!-- 			<td align="right" width="80" height="30">概要：</td> --> -->
<!-- 			<td colspan="2"> -->
<%-- 			直报时间 : <fmt:formatDate value="${zbRecord.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />  --%>
<%-- <%-- 			&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${zbRecord.reportStatus == '0'}"><span style="color:red">失败</span></c:if><c:if test="${zbRecord.reportStatus == '1'}"><span style="color:green">成功</span></c:if>   --%> --%>
<%-- 			&nbsp;&nbsp;&nbsp;&nbsp;${zbRecord.logContent}&nbsp;&nbsp;&nbsp;&nbsp;上报人 :&nbsp; ${zbRecord.userName}   --%>
<!-- 			</td> -->
<!-- <!-- 			<tr> --> -->
<%-- <%-- 			<c:if test="${zbRecord.reportStatus == '0'}"> --%> --%>
<!-- <!-- 				<tr> --> -->
<!-- <!-- 					<td align="right" valign="top">失败原因：</td> --> -->
<%-- <%-- 					<td align="left" valign="top">${zbRecord.reportResult}</td> --%> --%>
<!-- <!-- 				<tr> --> -->
<%-- <%-- 			</c:if> --%> --%>
<!-- <!-- 			<tr> --> -->
<!-- 			<td colspan="2"><hr></td><tr> -->
<%-- 		</c:forEach> --%>
<!-- 		</table> -->
<script type="text/javascript">
$(document).ready(function () {
	$('#logList').datagrid({
		fit: true,
        autoRowHeight: true,
        striped: true,
        fitColumns : true,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/zbRecord/f_json/findLogList.shtml?itemCode=${itemCode}',
        columns:[ 
	       	[
	            {field:'createTime',title:'直报时间 ',sortable:true,width:120},
	            {field:'logContent',title:'日志内容',sortable:true,width:200},
	           	{field:'userName',title:'上报人',sortable:true,width:80},
	        ]
        ],
        rownumbers:true,
        toolbar:'#tb'
	});
});
</script>
	</body>
</html>