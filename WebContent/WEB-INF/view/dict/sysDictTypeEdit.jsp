<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<form id="editFormSysDictType" method="post">
	<input type="hidden" name="id" value="${sysDictType.id}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title" style="width: 110px;"><span class="red">*</span>字典类型名称：</td>
				<td ><input type="text" name="dictTypeName"
					value="${sysDictType.dictTypeName}" style="width:340px; class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>字典类型编码：</td>
				<td ><input type="text" name="dictTypeCode"
					value="${sysDictType.dictTypeCode}" style="width:340px; class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title">字典类型描述：</td>
				<td ><textarea name="remark"
						style="width:334px; height: 60px;" class="easyui-validatebox text">${sysDictType.remark}</textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormSysDictType').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>				
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormSysDictType',
				url : '${webroot}/sysDictType/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						var parentObject = Comm.getObjectCache();
						parentObject.query();
						Comm.dialogClose('${param.dialogId}');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : '操作失败！' });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
	});
</script>