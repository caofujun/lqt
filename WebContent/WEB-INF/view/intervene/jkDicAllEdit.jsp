<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%-- <%@ include file="/WEB-INF/view/core/include.jsp"%> --%>

<form id="editFormjkDicAll" method="post">
	<input type="hidden" name="orderCode" value="${jkDicAll.orderCode}" /> 
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title"><span class="red">*</span>监测项目分类：</td>
				<td>
				<select name="classCode" id="classCode" onChange="chooseClassCode()">					
					<c:forEach items="${jkDicAllList}" var="dicAll">
						<option value="${dicAll.classCode}" <c:if test="${jkDicAll.classCode eq dicAll.classCode}">selected="selected"</c:if>>${dicAll.className}</option>
					</c:forEach>
				</select>
				<input type="hidden" id="className" name="className" value="${jkDicAll.className}">
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>监测项目名称：</td>
				<td>
					<input type="text" class="text" id="orderName" name="orderName" value="${jkDicAll.orderName}" style="width:152px;">
					<input type="hidden" class="text" id="pyCode" name="pyCode" value="${jkDicAll.pyCode}">
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>监测项目来源：</td>
				<td ><nis:select id="source" name="source" dictcode="zb_source" value="${jkDicAll.source}" cssCls="easyui-validatebox" exp="required=\"true\""/></td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn"	onclick="$('#editFormjkDicAll').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
function chooseClassCode(){
	$("#className").val($("#classCode").find("option:selected").text());
}
$(document).ready(
	function() {
		window.setTimeout(function() {
			$('#orderName').live('blur',function() {
				$("#pyCode").val($(this).pinyinFirstLower().toUpperCase());});
			Comm.form({
				id : 'editFormjkDicAll',
				url : '${webroot}/jkDicAll/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({title : '提示',msg : '操作成功！'});
 						//var parentObject = parent.Comm.getObjectCache();
 						jkDicAll2.query2();
						Comm.dialogClose('${param.dialogId}');
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
		}, 100);
	});
</script>