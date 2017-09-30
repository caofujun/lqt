<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="hygieneAdd" method="post">
<div class="easyui-layout" data-options="fit:true" style="width: 830px; height: 440px;">
	<div data-options="region:'north',border:false" style="overflow: hidden;border-bottom-width: 1px;">
		
			<input type="hidden" name="type" value="${param.type}" />
			<input type="hidden" id="id_templetName" name="templetName" />
			<input type="hidden" id="id_templetDeptId" name="templetDeptId" />
			<div class="m_search datagrid-toolbar">
				<div class="div_row h_set">
					<span>报告单号:</span>
					<input type="text" id="id_djId" name="djId" style="width:120px;" value="${hw101Jcdj.djId}" required="true" class="easyui-validatebox" readonly="readonly"/>
					<span class="ml5">填报科室:</span>
					<input type="hidden" name="deptName" id="id_deptName" />
					<input type="text" id="id_deptId" name="deptId" style="width:120px;" />
					<span class="ml5">填报人:</span>
			    	<input type="text" id="id_reportBy" name="reportBy" style="width: 120px;" />
			    	<span class="ml5">填报日期:</span>
			    	<input type="text" id="id_reportAt" name="reportAt" class="Wdate easyui-validatebox" value="<fmt:formatDate value="${hw101Jcdj.reportAt}" pattern="yyyy-MM-dd" />" onclick="WdatePicker()" required="true"/>
				</div>
			</div>
	</div>
	<div data-options="region:'west',border:false" style="width:270px;border-right-width: 1px;">
		<div id="tb_templetList" style="height: 30px;">			
			<div style="padding-right: 12px;padding-top: 5px;display: inline-block;">
				<label><input type="radio" id="id_isCurrDept" name="isCurrDept" value="1" checked="checked" onchange="templet.templetQuery();" />&nbsp;当前科室</label>&nbsp;&nbsp;&nbsp;
				<label><input type="radio" name="isCurrDept" value="0" onchange="templet.templetQuery();" />&nbsp;全院</label>
			</div>
			<div style="float:right;margin:1px 10px;"><a class="ico_no" href="javascript:void(0)" onclick="templet.delTemplet();" title="删除"><i class="icon iconfont fax">&#xe62b;</i></a></div>
		</div>
		<div id="templetPanel"></div>
	</div>
	<div data-options="region:'center',border:false">
		<div id="tb_sampleList" style="height: 30px;">
			<div  style="float:right;margin:1px 10px;"> <a class="ico_no" href="javascript:void(0)" onclick="templet.delSample();" title="删除"><i class="icon iconfont fax">&#xe62b;</i></a></div>
		</div>
		<div id="samplePanel"></div>
	</div>
</div>
</form>
<div class="footer dialog_footer">	
	<div class="footer_btn">
		<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="templet.formSubmit();" class="no_ico"><span>确认</span></a>
		</div>
		<div class="n_btn_grey">
				<a href="javascript:;" onclick="parent.Comm.dialogClose('${param.dialogId}')"  class="no_ico"><span>取消</span></a>
		</div>
	</div>
</div>
<script>
var templet = {
	templetPanel : 'templetPanel',
	samplePanel : 'samplePanel',
	templetQuery : function() {
		$('#' + templet.templetPanel).datagrid({
            url: '${webroot}/hw201Jcdmb/f_json/findTempletList.shtml',
            queryParams: {
            	isCurrDept: $('#id_isCurrDept:checked').val(),
            	type: '${type}'
            }
        });
	},
	//查询监测项目列表
	sampleQuery : function(deptId, templetName) {
		$('#' + templet.samplePanel).datagrid({
            url: '${webroot}/hw201Jcdmb/f_json/findSampleList.shtml',
            queryParams: {
            	type: '${type}',
            	djDeptId: deptId,
            	templetName: templetName
            }
        });
	},
	//删除模版
	delTemplet : function() {
		var selection = $('#' + templet.templetPanel).datagrid('getSelected');
		if (selection) {
			$.messager.confirm('提示', '确认删除当前模版全部内容?', function (r) {
	        	if (r) {
	        		//拿到右侧模版id
	        		var rightRows = $('#' + templet.samplePanel).datagrid('getRows');
	        		var templetIdStr = '';
	        		var rows;
	        		for (var i = 0; i < rightRows.length; i ++) {
	        			rows = rightRows[i];
	        			templetIdStr += rows.templetId + ',';
	        		}
					$.ajax({
			            url: '${webroot}/hw201Jcdmb/f_json/delTemplet.shtml',
			            type: 'post',
			            data: { templetIdStr: templetIdStr },
			            dataType: 'json',
			            success : function(json) {
			            	if (json.result === 'success') {
			            		templet.templetQuery();
							} else {
								parent.$.messager.show({title : '提示',msg : json.msg});
							}
						}
					});
				}
	    	});
		} else {
			parent.$.messager.show({title : '提示',msg : '请选择需要删除的记录！'});
		}

	},
	//删除模版下的监测项目
	delSample : function() {
		var selection = $('#' + templet.samplePanel).datagrid('getSelected');
		var rowsSize = $('#' + templet.samplePanel).datagrid('getRows').length;
		if (selection) {
			$.messager.confirm('提示', '确认删除当前监测项目?', function (r) {
	        	if (r) {
					$.ajax({
			            url: '${webroot}/hw201Jcdmb/f_json/delTemplet.shtml',
			            type: 'post',
			            data: { templetIdStr: selection.templetId },
			            dataType: 'json',
			            success : function(json) {
			            	if (json.result === 'success') {
			            		if (rowsSize > 1) {
			            			//var leftSelect = $('#' + templet.templetPanel).datagrid('getSelected');
			            			//templet.sampleQuery(leftSelect.deptId, leftSelect.templetName);
			            			//删除当前行
			            			var index = $('#' + templet.samplePanel).datagrid('getRowIndex', selection);
			            			$('#' + templet.samplePanel).datagrid('deleteRow', index);
			            			$('#' + templet.samplePanel).datagrid('selectRow', 0);
			            		} else {
			            			templet.templetQuery();
			            		}
							} else {
								parent.$.messager.show({title : '提示',msg : json.msg});
							}
						}
					});
				}
	    	});
		} else {
			parent.$.messager.show({title : '提示',msg : '请选择需要删除的记录！'});
		}
	},
	//表单提交处理
	formSubmit : function() {
		var select = $('#' + templet.templetPanel).datagrid('getSelected');
		$.messager.confirm('提示', '确认根据【' + select.deptName + '：' + select.templetName + '】模版来创建申请单?', function (r) {
        	if (r) {
        		$('#hygieneAdd').submit();
        	}
    	});
	}
};

$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'hygieneAdd',
			url : '${webroot}/hw201Jcdmb/f_json/addByTemplet.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title: '提示', msg: '操作成功！' });
					//刷新父页面列表数据
					var parentObject = parent.Comm.getObjectCache();
					parentObject.query(json.data);
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
        	isCurrDept: '1',
        	type: '${type}'
		},
        columns:[ 
	       	[
				{field:'deptName',title:'科室',sortable:true,width:115},
	            {field:'templetName',title:'模版名',sortable:true,width:100}
	        ]
        ],
        rownumbers:true,
        toolbar:'#tb_templetList',
        onLoadSuccess : function (data) {
        	if (data.rows.length > 0) {
            	$('#' + templet.templetPanel).datagrid('selectRow', 0);
        	} else {
        		$('#' + templet.samplePanel).datagrid('loadData',{total:0,rows:[]});
        	}
        },
        onSelect : function(index,row){
        	templet.sampleQuery(row.deptId, row.templetName);
        	$('#id_templetName').val(row.templetName);
        	$('#id_templetDeptId').val(row.deptId);
        	$('#id_deptId').combogrid('setValue', row.deptId);
        	$('#id_deptName').val(row.deptName);
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
        toolbar:'#tb_sampleList',
        onLoadSuccess : function (data) {
        	if (data.rows.length > 0) {
            	$('#' + templet.samplePanel).datagrid('selectRow', 0);
        	}
        }
	});
	
	//填报人
	$('#id_reportBy').combogrid({
		delay: 1000,
	    value: '${hw101Jcdj.reportBy}',
	    required: true,
        panelWidth: 240,
        panelHeight: 300,
        idField:'employeeId',
        textField:'employeeName',
        url: '${webroot}/hw010Zzry/f_json/queryList.shtml',
		columns:[
        	[
	            {field:'employeeId',title:'职工编号',sortable:true,width:100},
	            {field:'employeeName',title:'职工姓名',sortable:true,width:115}
            ]
        ],
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_reportBy');
		}
	});
	
	//填报科室
	$('#id_deptId').combogrid({
		delay: 1000,    
	    mode: 'remote',
	    loadMsg : '正在查询中...',
	    value: '${hw101Jcdj.deptId}',
	    required: true,
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
		onLoadSuccess : function() {
			$('#id_deptName').val($('#id_deptId').combogrid('getText'));
		},
		onClickRow : function(index,row){
			$('#id_deptName').val(row.deptName);
		},
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_deptId');
		}
	});
});
</script>