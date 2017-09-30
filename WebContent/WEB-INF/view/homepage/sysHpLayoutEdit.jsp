<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<form id="editFormHpLayout" method="post">
	<input type="hidden" name="id" value="${sysHpLayout.id}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>布局名称：</td>
				<td><input type="text" name="layoutName"
					value="${sysHpLayout.layoutName}" class="easyui-validatebox text" required="true" style="width:90%;"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>布局编码：</td>
				<td><input type="text" name="layoutCode"
					value="${sysHpLayout.layoutCode}" class="easyui-validatebox text" required="true" style="width:90%;"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>布局URL：</td>
				<td><input type="text" name="layoutUrl"
					value="${sysHpLayout.layoutUrl}" class="easyui-validatebox text" required="true" style="width:90%;"></td>
			</tr>
			<tr>
				<td class="t_title" ><span class="red">*</span>展示图片：</td>
				<td><input type="text" name="imgUrl"
					value="${sysHpLayout.imgUrl}" class="easyui-validatebox text" required="true" style="width:90%;"></td>
			</tr>
			<tr>
				<td class="t_title">布局描述：</td>
				<td>
					<textarea name="remark" class="easyui-validatebox" style="width:90%;">${sysHpLayout.remark}</textarea>
				</td>
			</tr>
		</tbody>
	</table>
			
	<div class="footer dialog_footer"><input type="button"
		class="btn_save" id="changeFormSubmitBtn"
		onclick="$('#editFormHpLayout').submit()" value="保存"></div>

</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			
			Comm.form({
				id : 'editFormHpLayout',
				url : '${webroot}/sysHpLayout/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						hpLayout.query();
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