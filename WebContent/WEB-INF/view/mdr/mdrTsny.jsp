<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="xn017TsnyzdPanel"></div>
<script type="text/javascript">
	var xn017Tsnyzd = {
		panel : 'xn017TsnyzdPanel'
	}
	$(document).ready(function () {
		$('#'+xn017Tsnyzd.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: false,
	        striped: true,
	        fitColumns: true,
	        collapsible:true,
		    url:'${webroot}/xn017Tsnyzd/f_json/pageQuery.shtml?specDescribe=${specDescribe}',
            remoteSort: false,
            singleSelect: true,
            border:false,
		    columns:[
		    	[
		         {field:'pathogenName',title:'菌名',sortable:true,width:120},
		         {field:'drugName',title:'药物名',sortable:true,width:120},
		         {field:'showTestresult',title:'药敏结果定性',sortable:true,width:120}
		     	]
		    ],
            pagination:true,
            rownumbers:true,
		});
	});
</script>
</body>
</html>
