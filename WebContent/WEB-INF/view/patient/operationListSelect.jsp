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
			<div id="oprationPanel"></div>
		</div>
		<div data-options="region:'south'" style="height:45px;">
			<div class="footer">			
				<div class="footer_btn">
					<div class="n_btn_blue">
						<a href="javascript:;"  class="no_ico" id="changeFormSubmitBtn" onclick="opration.select();"><span>选择</span></a>
					</div>			
					<div class="n_btn_grey">
						<a href="javascript:;" onclick="opration.addSimple()"><i class="icon iconfont">&#xe602;</i><span>新增手术</span></a>
					</div>			
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var opration = {
		panel : 'oprationPanel',
		//查询
		query : function() {
	        $('#'+opration.panel).datagrid({
	            queryParams: {
	                'searchString': $('#searchString').val()
	            },
	            pageNumber: 1
	        });
	    },
		//获取数据
		select : function() {
			var oprationSelections = $("#"+opration.panel).datagrid("getSelections");
			var parentObject = parent.Comm.getObjectCache();
			parentObject.setOprationList(oprationSelections);
			parent.Comm.dialogClose('${param.dialogId}');
	    },
		//获取数据
		addSimple : function() {
			document.location.href = "${webroot}/st005Ssxxb/f_view/addSimple.shtml?patientId=${param.patientId}&zyid=${param.zyid}&operAt=${param.operAt}&dialogId=${param.dialogId}";
	    }
	};
	$(document).ready(function () { 
		$('#'+opration.panel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            fitColumns: false,
            collapsible:true,
            url:'${webroot}/st005Ssxxb/f_json/findSelectList.shtml?patientId=${param.patientId}&operAt=${param.operAt}',   
            remoteSort: false,
            singleSelect: true,
            columns:[
            	[
	              	{field:'ck',checkbox:true,width:20},
                 	{field:'operAt',title:'手术时间',sortable:true,width:80},
                    {field:'operName',title:'手术名称',sortable:true,width:170},  
                    {field:'opedocName',title:'手术医生',sortable:true,width:100},
                    {field:'incisionGrade',title:'切口等级',sortable:true,width:80}
                ]
            ],
            pagination:false,
            rownumbers:false
        });
	});
	</script>
</body>
</html>