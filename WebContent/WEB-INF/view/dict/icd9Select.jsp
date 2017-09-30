<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>选择手术</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout">
	<div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'center'">
			<div id="icd9Panel"></div>
			<div id="tb" class="m_search">
				<input type="text" class="auto-tip" data-tip="ICD9名称/编号" id="searchString"/>
			    <input type="button"  onclick="icd9.query()" class="btn_search" iconCls="icon-search" plain="true" value="查询" />
			</div>
		</div>
		<div data-options="region:'south'" style="height:45px;">
			<div class="footer">
				<input type="button" class="btn_save" id="changeFormSubmitBtn" onclick="icd9.select();" value="选择" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var icd9 = {
		panel : 'icd9Panel',
		//查询
		query : function() {
			autoTip.clear();
	        $('#'+icd9.panel).datagrid({
	            queryParams: {
	                'searchString': $('#searchString').val()
	            },
	            pageNumber: 1
	        });
	    },
		//获取数据
		select : function() {
			var icd9Selections = $("#"+icd9.panel).datagrid("getSelections");
			var parentObject = parent.Comm.getObjectCache();
			parentObject.setICD9List(icd9Selections);
			parent.Comm.dialogClose('${param.dialogId}');
	    }
	};
	$(document).ready(function () { 
		$('#'+icd9.panel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            fitColumns: false,
            collapsible:true,
            url:'${webroot}/icd9/f_json/pageQuery.shtml',   
            remoteSort: false,
            singleSelect: true,
            columns:[
            	[
	              	{field:'ck',checkbox:true,width:20},
                    {field:'operId',title:'手术编码',sortable:true,width:100},
                    {field:'icdId',title:'ICD9编码',sortable:true,width:100},
                    {field:'operaCnname',title:'ICD9名称',sortable:true,width:250},
                    {field:'showOpesysId',title:'ICD9系统分类',sortable:true,width:150},
                    {field:'showOpepartKindid',title:'ICD9操作分类',sortable:true,width:150}
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