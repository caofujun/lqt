<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormICD9" method="post">
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 100px;"><span class="red">*</span>ICD9编号：</td>
				<td><input type="text"  id="icdId" name="icdId" style="width: 138px;"
					value="<c:out value="${icd9.icdId}" />" class="easyui-validatebox text" required="true" <c:if test="${icd9.icdId!=null and icd9.icdId!=''}"> readonly="true"</c:if>></td>
				<td class="t_title" style="width: 100px;"><span class="red">*</span>ICD9名称：</td>
				<td><input type="text"  id="operaCnname" name="operaCnname" style="width: 138px;"
					value="<c:out value="${icd9.operaCnname}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>系统分类：</td>
				<td>
					<nis:select name="opesysId" id="opesysId" value="${icd9.opesysId}"  exp="style='width: 150px;'" dictcode="icd9_type" cssCls="easyui-validatebox easyui-combobox"/>				
				</td>
				<td class="t_title" ><span class="red">*</span>操作分类：</td>
				<td>
					<nis:select name="opepartKindid" id="opepartKindid" value="${icd9.opepartKindid}"  exp="style='width: 150px;'" dictcode="icd9_opekind" cssCls="easyui-validatebox easyui-combobox"/>				
				</td>
			</tr>
			<tr>
				<td class="t_title" ><span class="red">*</span>手术编码：</td>
				<td><input type="text"  id="operId" name="operId" style="width: 138px;"
					value="<c:out value="${icd9.operId}" />" class="easyui-validatebox text" required="true"></td>
				<td class="t_title">属性设置：</td>
				<td>
				    <label><input type="checkbox" <c:if test="${icd9.impOpeId=='1'}">checked</c:if> id="impOpeId" name="impOpeId" value="1"/>重点监测手术</label>
				</td>
			</tr>
			<tr>
				<td class="t_title">备注：</td>
				<td colspan="3"><textarea name="memo" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${icd9.memo}" /></textarea></td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormICD9').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormICD9',
				url : '${webroot}/icd9/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
						menuInfo.clickMenu('ICD-9管理','/icd9/f_view/index.shtml',true,'A0403');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
	});
</script>