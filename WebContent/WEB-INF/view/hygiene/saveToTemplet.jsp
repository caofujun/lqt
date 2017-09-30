<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
		<form id="templetAdd" method="post">
<div class="easyui-layout" data-options="fit:true" style="width:820px;height:450px;">
	<div data-options="region:'north',border:false" style="overflow: hidden;border-bottom-width: 1px;">

			<input type="hidden" name="djId" value="${hw101Jcdj.djId}">
			<div class="m_search datagrid-toolbar">
				<div class="div_row">
					&nbsp;&nbsp;
					<label><input type="radio" id="id_isCurrDept" name="isCurrDept" value="1" checked="checked" onchange="templet.templetQuery();" />&nbsp;当前科室</label>&nbsp;&nbsp;&nbsp;
					<label><input type="radio" name="isCurrDept" value="0" onchange="templet.templetQuery();" />&nbsp;全院</label>
					<span class="row_title" style="width: 70px;"><b>填报科室：</b></span>
					<input type="text" id="id_deptId" name="deptId" style="width:133px;" />
					<span class="row_title" style="width: 70px;"><b>模版名称：</b></span>
					<input type="text" id="id_templetName" name="templetName" style="width:133px;" required="true" class="easyui-validatebox"/>
				</div>
			</div>

	</div>
	<div data-options="region:'west',border:false" style="width:230px;border-right-width: 1px;">
		<div id="templetPanel"></div>
	</div>
	<div data-options="region:'center',border:false">
		<div id="samplePanel"></div>
	</div>
</div>
</form>
<div class="footer dialog_footer">
	<input type="button" class="btn_save" id="changeFormSubmitBtn" onclick="templet.formSubmit();" value="保存">
	<input type="button" class="btn_return" data-options="iconCls:'icon-cancel'" onclick="parent.Comm.dialogClose('${param.dialogId}')" value="关闭" />
</div>
<script>
var templet = {
	templetPanel : 'templetPanel',
	samplePanel : 'samplePanel',
	currDeptId : '${hw101Jcdj.deptId}',
	templetQuery : function() {
		if ($('#id_isCurrDept').is(':checked')) {
			$('#id_deptId').combo('readonly', true);
			$('#id_deptId').combogrid('setValue', this.currDeptId);
		} else {
			$('#id_deptId').combo('readonly', false);
		}
		$('#' + templet.templetPanel).datagrid({
            url: '${webroot}/hw201Jcdmb/f_json/findTempletList.shtml',
            queryParams: {
            	isCurrDept: $('#id_isCurrDept:checked').val()
            }
        });
	},
	//查询监测项目列表
	sampleQuery : function(deptId, templetName) {
		$('#' + templet.samplePanel).datagrid({
            url: '${webroot}/hw201Jcdmb/f_json/findSampleList.shtml',
            queryParams: {
            	deptId: deptId,
            	templetName: templetName
            }
        });
	},
	//表单提交处理
	formSubmit : function() {
		if ($('#templetAdd').form('validate')) {
			$.ajax({
	            url: '${webroot}/hw201Jcdmb/f_json/findByTempletName.shtml',
	            type: 'post',
	            data: { deptId: $('#id_deptId').combogrid('getValue'),  templetName: $('#id_templetName').val() },
	            dataType: 'json',
	            success : function(json) {
	            	if (json.result === 'success') {
	            		if (parseInt(json.data) > 0) {
	            			$.messager.confirm('提示', '模版已存在，确认覆盖?', function (r) {
	            	        	if (r) {
	            	        		$('#templetAdd').submit();
	            	        	}
	            	    	});
	            		} else {
	            			$('#templetAdd').submit();
	            		}
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}
			});
		}
	}
};

$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'templetAdd',
			url : '${webroot}/hw201Jcdmb/f_json/saveToTemplet.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title: '提示', msg: '操作成功！' });
					parent.Comm.dialogClose('${param.dialogId}');
				} else if (json.result === 'error') {
					parent.$.messager.show({title : '提示',msg : '操作失败！'});
				} else {
					parent.$.messager.show({title : '提示',msg : json.msg});
				}
			}});
	}, 100);
	
	//报告单模版列表
	$('#' + templet.templetPanel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: false,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/hw201Jcdmb/f_json/findTempletList.shtml',
        queryParams: {
        	isCurrDept: '1'
		},
        columns:[ 
	       	[
	            {field:'templetName',title:'模版名',sortable:true,width:180}
	        ]
        ],
        rownumbers:true,
        groupField:'deptId',
        view: groupview,
        collapseGroup: true,
        groupFormatter:function(value, rows) {
            return  '<span>' + rows[0].deptName + '</span>';
        },
        groupStyler: function(value,rows){
    		return 'background-color:#EEF6FC;height:28px;font-size:15px;padding-top:3px;font-weight:normal;'; 
    	},
        onLoadSuccess : function (data) {
        	if (data.rows.length > 0) {
            	$('#' + templet.templetPanel).datagrid('selectRow', 0);
            	$('#' + templet.templetPanel).datagrid('collapseGroup');
            	$('#' + templet.templetPanel).datagrid('expandGroup', 0);
        	} else {
        		$('#' + templet.samplePanel).datagrid('loadData',{total:0,rows:[]});
        	}
        },
        onSelect : function(index,row){
        	templet.sampleQuery(row.deptId, row.templetName);
        	$('#id_templetName').val(row.templetName);
		}
	});
	
	//模版监测项目列表
	$('#' + templet.samplePanel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: false,
        remoteSort: false,
        singleSelect: true,
        border:false,
        columns:[ 
	       	[
				{field:'className',title:'监测项目',sortable:true,width:90},
				{field:'placeName',title:'采样场所',sortable:true,width:80},
				{field:'takeByName',title:'采样人',sortable:true,width:70},
				{field:'sampleName',title:'采样标本',sortable:true,width:110},
	            {field:'posName',title:'采样点数',sortable:true,width:55},
	            {field:'takeModeName',title:'采样方法',sortable:true,width:70},
	            {field:'takeTypeName',title:'监测类型',sortable:true,width:90}
	        ]
        ],
        rownumbers:true,
        onLoadSuccess : function (data) {
        	if (data.rows.length > 0) {
            	$('#' + templet.samplePanel).datagrid('selectRow', 0);
        	}
        }
	});
	
	//填报科室
	$('#id_deptId').combogrid({
		delay: 1000,    
	    mode: 'remote',
	    loadMsg : '正在查询中...',
	    value: '${hw101Jcdj.deptId}',
	    required: true,
	    readonly: true,
        idField:'deptId',
        textField:'deptName',
        panelWidth: 260,
        panelHeight: 300,
		url: '${webroot}/hw009Kssq/f_json/queryList.shtml',
		queryParams: {
			defValue: '${hw101Jcdj.deptId}',
			page: 1,
			size: 200
		},
        columns:[
        	[
             {field:'deptId',title:'科室编号',sortable:true,width:80},  
             {field:'deptName',title:'科室名称',sortable:true,width:160},
            ]
        ],
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_deptId');
		}
	});
});
</script>