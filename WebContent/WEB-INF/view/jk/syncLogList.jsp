<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>数据同步错误日志</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/zeroclipboard-2.2.0/ZeroClipboard.js"></script>
	<style>
		.easyui-combobox{width:75px;}
		.table_cx .easyui-combobox{width:150px;}
	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="border-left-width: 1px;">
		<div id="jkLogPanel"></div>
	</div>	
<script type="text/javascript">
	var jkLogSync = {
		panel : 'jkLogPanel'
	};

	$(document).ready(function () { 
		$('#'+jkLogSync.panel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            fitColumns: true,
            collapsible:true,
            url:'${webroot}/jk/f_json/logPageQuery.shtml?createTime=${param.syncTime}',   
            remoteSort: false,
            singleSelect: true,
            border:false,
            columns:[
            	[
                    {field:'msgContent',title:'错误内容',sortable:true,width:800}
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
