<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>出入科记录</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="inAndOutPanel"></div>
<script type="text/javascript">
	$(function(){
		$('#inAndOutPanel').datagrid({
	       fit: true,
	       nowrap: true,
	       autoRowHeight: true,
	       striped: true,
	       fitColumns: true,
	       url:'${webroot}/st012Zkjl/f_json/findInAndOutDepList.shtml?zyid=' + '${param.zyid}',   
	       remoteSort: false,
	       singleSelect: true,	       
	       border:false,
	       columns:[ 
		       	[
		            {field:'inDate',title:'入科日期',sortable:true,width:140,align:'center'},
		            {field:'inDeptId',title:'科室编号',sortable:true,width:80},
		            {field:'inDeptName',title:'科室名称',sortable:true,width:140},
		            {field:'outDate',title:'出科日期',sortable:true,width:140,align:'center'},
		            {field:'outDeptId',title:'转向科室编号',sortable:true,width:100},
		            {field:'outDeptName',title:'转向科室名称',sortable:true,width:120},
		            {field:'bedNo',title:'转科后床位号',sortable:true,width:100},
		            {field:'inDeptDays',title:'在科天数',sortable:true,width:60}
		        ]
	       ],
	       rownumbers:true
	   });
	});
</script>
</body>
</html>
