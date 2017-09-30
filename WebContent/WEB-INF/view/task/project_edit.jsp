<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="editFormtaskProject" method="post">
		<input type="hidden" name="id" value="${taskProject.id}" />
		<input type="hidden" name="signstring" value="{}"> 
		<table class="table mb60" id="taskProjectList" cellpadding="0" cellspacing="0">
		<tbody>
		<tr>
			<td class="t_title" width="120"><span class="red">*</span>名称：</td>
			<td><input type="text" name="name" style="width:150px"
				value="<c:out value="${taskProject.name}" />" class="easyui-validatebox"
				required="true"></td>
		</tr>
		<tr>
			<td class="t_title"><span class="red">*</span>加密方式：</td>
			<td><nis:select id="sign" name="sign" headerKey="" headerValue="请选择加密方式" dictcode="project_sign" value="${taskProject.sign}" cssCls="easyui-validatebox" exp="required=\"true\""/></td>
		</tr>
		<tr>
			<td class="t_title">描叙：</td>
			<td><input type="text" name="remark" style="width:360px"
				value="<c:out value="${taskProject.remark}" />" class="easyui-validatebox"
				></td>
		</tr>
		</tbody>
	</table>
	<div class="footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormtaskProject').submit()" class="no_ico"><span>确认</span></a>
			</div>			
		</div>
	</div>
</form>
<script>
$(document).ready(
	function() {
		window.setTimeout(function() {
			Comm.form({
					id : 'editFormtaskProject',
					subbtn : 'changeFormSubmitBtn',
					url : '${webroot}/taskProject/f_json/save.shtml',
					success : function(json) {
						if (json.result === 'success') {
							parent.$.messager.show({title : '提示',msg : '操作成功！'});
							var parentObject = parent.Comm.getObjectCache();
							parentObject.query();
							parent.Comm.dialogClose('${param.dialogId}');
						} else if (json.result === 'error') {
							$.messager.show({title : '提示',msg : '操作失败！'});
						} else {
							$.messager.show({title : '提示',msg : json.msg});
						}
					}});
			}, 100);
	});
</script>