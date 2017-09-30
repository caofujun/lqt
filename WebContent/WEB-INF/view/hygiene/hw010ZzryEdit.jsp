<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<div class="easyui-panel" data-options="border:false,fit:true,footer:'#button_div'" style="padding-right: 5px; overflow: hidden;">
<form id="editFormhw010Zzry" method="post">
	<input type="hidden" name="action" value="${action}" />
	<table class="table mb60 mt20" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 120px;">专职人员：</td>
				<td>
					<input type="hidden" id="id_source" name="source" value="${hw010Zzry.source}">
					<input type="hidden" id="id_employeeName" name="employeeName" value="${hw010Zzry.employeeName}">
					<div class="select_del"><input type="text" id="id_employeeId" name="employeeId" style="width:150px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_employeeId').combo('clear');"></a></div>
				</td>
			</tr>
			<!-- <tr>
				<td class="t_title">所属科室：</td>
				<td>
					<div class="select_del"><input type="text" id="id_edit_deptId" name="deptId" style="width: 150px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_edit_deptId').combo('clear');"></a></div>
				</td>
			</tr> -->
		</tbody>
	</table>
</form>
</div>
<div id="button_div" class="footer dialog_footer" style="height:35px;border-width: 1px 0 0 0;">
	<!-- <input type="button" class="button" id="changeFormSubmitBtn" onclick="$('#editFormhw010Zzry').submit();" value=" 保存 ">&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" class="button" data-options="iconCls:'icon-cancel'" style="background-color: #E5E5E5;color: #333;" onclick="$('#id_edit').dialog('close');" value=" 关闭 " /> -->
	<div class="footer_btn">
		<div class="n_btn_blue">
			<a href="javascript:;" id="changeFormSubmitBtn" onclick="checkSubmit();" class="no_ico"><span>确认</span></a>
		</div>
		<div class="n_btn_grey">
			<a href="javascript:;" onclick="$('#id_edit').dialog('close');"  class="no_ico"><span>取消</span></a>
		</div>
	</div>
</div>
<script type="text/javascript">
function checkSubmit(){
	var employeeName = $('#id_employeeName').val();
	if(employeeName!=''){
		$('#editFormhw010Zzry').submit();
	}else{
		parent.$.messager.show({ title : '提示', msg : '请选择专职人员！' });
	}
}
$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'editFormhw010Zzry',
			url : '${webroot}/hw010Zzry/f_json/save.shtml',
			subbtn : 'changeFormSubmitBtn',
			onLoading : function () {
				$.messager.progress({
					text : '正在提交中....',
					interval : 200,
					width: 200,
			        border:false
				});
				$('#changeFormSubmitBtn').hide();
			},
			success : function(json) {
				$.messager.progress('close');
				$('#changeFormSubmitBtn').show();
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					//刷新父页面数据
					hw010Zzry.query();
					$('#id_edit').dialog('close');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	}, 100);
	
	//专职人员
	$('#id_employeeId').combogrid({
		delay: 1000,    
	    mode: 'remote',
	    loadMsg : '正在查询中...',
	    value: '${hw010Zzry.employeeId}',
	    required:true,
        idField:'employeeId',
        panelWidth: 320,
        panelHeight: 230,
        textField:'employeeName',
        readonly:('edit' == '${action}' ? true : false),
		url: '${webroot}/doctor/json/queryToSelect1.shtml?page=1&size=200&defValue=${xl001Brxx.voteid}',
        columns:[
        	[
            	{field:'employeeId',title:'职工编号',sortable:true,width:80},  
            	{field:'employeeName',title:'职工名称',sortable:true,width:70},
            	{field:'sourceName',title:'来源',sortable:true,width:50},
            	{field:'deptName',title:'所属科室',sortable:true,width:100}
            ]
        ],
		onClickRow : function(index,row){
			$('#id_employeeName').val(row.employeeName);
			$('#id_source').val(row.source);
		},
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_employeeId');
		}
	});
	
	//所属科室
	Csm.combogrid.dep({
		id: 'id_edit_deptId',
		required:true,
        panelHeight: 230,
		value: '${hw010Zzry.deptId}',
		ifenvoffice: '1',
		//【可选参数】1：回调，0：不回调，不传默认回调
		callback: '0',
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_edit_deptId');
		}
	});
});
</script>