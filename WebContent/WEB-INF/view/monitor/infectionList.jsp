<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>感染因素</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="infectionPanel"></div>
<script type="text/javascript">
	$(function(){
		$('#infectionPanel').datagrid({
	       fit: true,
	       nowrap: true,
	       autoRowHeight: true,
	       striped: true,
	       fitColumns: false,
	       url:'${webroot}/gr018Ysgrys/f_json/findInfectionList.shtml?zyid=' + '${param.zyid}',   
	       remoteSort: false,
	       singleSelect: true,
	       fitColumns: false,      
	       columns:[ 
		       	[
		            {field:'dataDate',title:'感染日期',sortable:true,width:80},
		            {field:'elementName',title:'感染元素',sortable:true,width:200},
		            {field:'dataForm',title:'数据来源',sortable:true,align:'center',width:70},
		            {field:'originalContent',title:'监测到',sortable:true,width:200},
		            {field:'monitorAt',title:'监测日期',sortable:true,width:140}
		        ]
	       ],
	       rownumbers:true
	   });
	});
</script>
</body>
</html>
