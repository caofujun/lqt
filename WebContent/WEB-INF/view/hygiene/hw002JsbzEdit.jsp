<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="editFormhw002Jsbz" method="post">
	<input type="hidden" name="action" value="${action}" />
	<input type="hidden" name="flag" value="${hw002Jsbz.flag}" />
	<table class="table" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 90px;">监测项目：</td>
				<td>
					<input id="id_classId" name="classId" style="width:150px;" />
				</td>
				<td class="t_title" style="width: 90px;">类别ID：</td>
				<td>
					<input type="text" name="itemId" style="width: 150px;" value="<c:out value="${hw002Jsbz.itemId}" />" class="easyui-validatebox text" required="true" validType="number" ${action eq 'edit' ? 'readonly="true"' : '' } />
				</td>
			</tr>
			<tr>
				<td class="t_title">类别名称：</td>
				<td>
					<input type="text" name="itemName" id="itemName" style="width: 150px;" value="<c:out value="${hw002Jsbz.itemName}" />" class="easyui-validatebox text" required="true" />
				</td>
				<td class="t_title">条件：</td>
				<td>
					<select name="condition" style="width: 150px;" class="easyui-combobox" data-options="editable:false">
						<option value="">-请选择-</option>
						<option value="=" ${hw002Jsbz.condition eq '=' ? 'selected="selected"' : ''} >=</option>
						<option value="≤" ${hw002Jsbz.condition eq '≤' ? 'selected="selected"' : ''} >≤</option>
						<option value="≥" ${hw002Jsbz.condition eq '≥' ? 'selected="selected"' : ''} >≥</option>
						<option value="<" ${hw002Jsbz.condition eq '<' ? 'selected="selected"' : ''} ><</option>
						<option value=">" ${hw002Jsbz.condition eq '>' ? 'selected="selected"' : ''} >></option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_title">执行标准：</td>
				<td>
					<input type="text" name="criterion" style="width: 150px;" class="easyui-validatebox text" required="true" value="${hw002Jsbz.criterion}" />
				</td>
				<td class="t_title">单位：</td>
				<td>
					<input type="text" name="unit" style="width: 150px;"  value="${hw002Jsbz.unit}" />
				</td>
			</tr>
			<tr>
				<td class="t_title">备注：</td>
				<td  colspan="3">
					<textarea name="memo" style="width: 95%; height: 100px;" class="easyui-validatebox"><c:out value="${hw002Jsbz.memo}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
					<a href="javascript:;"  id="changeFormSubmitBtn" onclick="$('#editFormhw002Jsbz').submit()" class="no_ico"><span>确认</span></a>
			</div>
			<div class="n_btn_grey">
					<a href="javascript:;"  onclick="parent.Comm.dialogClose('${param.dialogId}')"  class="no_ico"><span>取消</span></a>
			</div>
		</div>	
	</div>
</form>
<script>
$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'editFormhw002Jsbz',
			url : '${webroot}/hw002Jsbz/f_json/save.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					//刷新父页面数据
					var parentObject = parent.Comm.getObjectCache();
					if ('edit' == '${action}') {
						parentObject.updateRow('${hw002Jsbz.itemId}');
					} else {
						parentObject.query();
					}
					parent.Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	}, 100);
	
	//所属项目     /*readonly:('edit' == '${action}' ? true : false),*///去除禁用
	$('#id_classId').combogrid({
		url:'${webroot}/hw001Jcxm/f_json/queryList.shtml',
		method:'get',
		required: true,
		value: '${hw002Jsbz.classId}',
		panelHeight:260,
		panelWidth:320,
		idField:'classId',
        textField:'className',
        columns:[
        	[
	            {field:'classId',title:'场所编号',sortable:true,width:70},  
	            {field:'className',title:'场所名称',sortable:true,width:160},
	            {field:'spCode',title:'首拼码',sortable:true,width:60}
            ]
        ],
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_classId');
		},
		onSelect: function(rowIndex, rowData) {
			 $('#itemName').val(rowData.className);
		}
	});
});
</script>