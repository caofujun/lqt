<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormICD10" method="post">
	<input type="hidden" name="icdId" value="${icd10.icdId}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 140px;"><span class="red">*</span>ICD10编号：</td>
				<td><input type="text"  id="icdCode" name="icdCode" style="width: 250px;"
					value="<c:out value="${icd10.icdCode}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>ICD10名称：</td>
				<td><input type="text"  id="icdName" name="icdName" style="width: 250px;"
					value="<c:out value="${icd10.icdName}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 140px;">拼音码：</td>
				<td>
					<input type="text"  id="spCode" name="spCode" style="width: 250px;" value="<c:out value="${icd10.spCode}" />" class="easyui-validatebox text" required="true">
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;"  id="changeFormSubmitBtn" onclick="$('#editFormICD10').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>				
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			$('#icdName').live('blur',function() {
				var icdName = $(this).pinyinFirstLower().toUpperCase();
				if(icdName.length > 10){
					icdName = icdName.substring(0,10);
				}
				$("#spCode").val(icdName);
			});
			Comm.form({
				id : 'editFormICD10',
				url : '${webroot}/icd10/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
						menuInfo.clickMenu('ICD-10管理','/icd10/f_view/index.shtml',true,'A0402');
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