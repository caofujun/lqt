<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<form id="editFormUsRole" method="post">
	<input type="hidden" name="roleId" value="${acRole.roleId}"/>
	<input type="hidden" name="ownership" value="${param.ownership}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0"  >
		<tbody>
			<tr>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>角色名称：</td>
				<td class="t_cont"><input type="text" style="width:326px;" name="name" value="<c:out value="${acRole.name}" />" class="easyui-validatebox" required="true"></td>
			</tr>
			<tr>
				<td class="t_title">角色管理类型：</td>
				<td class="t_cont">
				<nis:select dictcode="role_type" cssCls="easyui-validatebox easyui-combobox" name="roleScope" headerKey="" headerValue="请选择"  value="${acRole.roleScope}"/>
				</td>
			</tr>
			<tr>
				<td class="t_title">角色说明：</td>
				<td class="t_cont"><textarea name="remark"
						style="width:320px; height: 60px;" class="easyui-validatebox"><c:out value="${acRole.remark}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormUsRole').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormUsRole',
				url : '${webroot}/acRole/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
// 						acRole.query();
						menuInfo.clickMenu('用户角色管理','/acRole/f_view/index.shtml',true,'system_ac_role');
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