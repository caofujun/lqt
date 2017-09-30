<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="editFormZlPdcaFlow" method="post">
	<input type="hidden" name="fuid" value="${zlPdcaFlow.fuid}" /> 
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
<%-- 				<td class="t_title"><span class="red">*</span>模板编号：</td>
				<td><input type="text" id="code" name="flowNo"
					value="<c:out value="${zlPdcaFlow.flowNo}" />" class="easyui-validatebox"
					required="true"></td> --%>
				<td class="t_title"><span class="red">*</span>模板名称：</td>
				<td><input type="text" name="flowName" style="width:152px;"
					value="<c:out value="${zlPdcaFlow.flowName}" />" class="easyui-validatebox"
					required="true"></td>
			</tr>
			<tr>				
				<td class="t_title"><span class="red">*</span>模板状态：</td>
				<td ><nis:select id="status" name="status" dictcode="pdca_status" value="${zlPdcaFlow.status}" cssCls="easyui-validatebox" exp="required=\"true\""/></td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;"  id="changeFormSubmitBtn" onclick="$('#editFormZlPdcaFlow').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>
	</div>
</form>
<script>
$(document).ready(
	function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormZlPdcaFlow',
				url : '${webroot}/zlPdcaFlow/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '操作成功！'});
						var parentObject = parent.Comm.getObjectCache();
						parentObject.query();
						parent.Comm.dialogClose('${param.dialogId}');
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
		}, 100);
	});
</script>