<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>选择类型</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="dataModelPanel" class="easyui-layout" fit="true">
		<div data-options="region:'center',border:false"style="position: relative; overflow-x: hidden;">
			<div id="sysDictPanel"></div>
		</div>
		<div data-options="region:'south',border:false" style="height:47px;">
			<div class="footer">
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn"	onclick="sysDict.select()"  class="no_ico"><span>选择</span></a>
				</div>			
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var sysDict = {
		panel : 'sysDictPanel',
		//查询
		query : function() {
	        $('#'+sysDict.panel).datagrid({
	            queryParams: {
	                'searchString': $('#searchString').val()
	            },
	            pageNumber: 1
	        });
	    },
		//获取数据
		select : function() {
			var sysDictSelections = $("#sysDictPanel").datagrid("getSelections");
			var parentObject = parent.Comm.getObjectCache();
			parentObject.setDictList(sysDictSelections, '${param.index}');
			parent.Comm.dialogClose('${param.dialogId}');
	    }
	};
	$(document).ready(function () { 
		$('#'+sysDict.panel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            fitColumns: true,
            collapsible:true,
            url:'${webroot}/sysDict/f_json/findList.shtml?dictTypeCode=${sysDict.dictTypeCode}&checkeds=${param.checkeds}',   
            remoteSort: false,
            singleSelect: false,
            columns:[
            	[
	              	{field:'ck',checkbox:true,width:20},
                 	{field:'dictCode',title:'字典编码',sortable:true,width:80},
                    {field:'dictName',title:'字典名称',sortable:true,width:170},  
                    {field:'extParam1',title:'扩展参数1',sortable:true,width:100},
                    {field:'extParam2',title:'扩展参数2',sortable:true,width:80}
                ]
            ],
            pagination:false,
            rownumbers:false
        });
	});
	</script>
</body>
</html>