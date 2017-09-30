<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormSt012KjywDrug" method="post">
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<input type="hidden" name="id" value="${st012Kjyw.id}" >
				<td class="t_title" ><span class="red">*</span>抗菌药物编号：</td>
				<td><input type="text"  id="drugId" name="drugId" style="width: 138px;" <c:if test="${st012Kjyw.id!=null and st012Kjyw.id!=''}"> readonly="true"</c:if>
					value="<c:out value="${st012Kjyw.drugId}" />" class="easyui-validatebox text" required="true"></td>
				<td class="t_title"><span class="red">*</span>抗菌药物名称：</td>
				<td><input type="text"  id="drugName" name="drugName" style="width: 138px;"
					value="<c:out value="${st012Kjyw.drugName}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			
			<tr>
				<td class="t_title">拼音码：</td>
				<td>
					<input type="text"  id="spCode" name="pycode" style="width: 138px;" value="<c:out value="${st012Kjyw.pycode}" />" class="easyui-validatebox text" required="true">
				</td>
				<td class="t_title">级别：</td>
				<td>
				<nis:select dictcode="drug_line" headerKey="" name="drugLine" headerValue="-请选择-" value="${st012Kjyw.drugLine}" exp="drugid='${st012Kjyw.drugLine}' defval='${st012Kjyw.drugLine}' "/>
				</td>
			</tr>
			
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormSt012KjywDrug').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>		
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			$('#drugName').live('blur',function() {
				var drugName = $(this).pinyinFirstLower().toUpperCase();
				if(drugName.length > 10){
					drugName = drugName.substring(0,10);
				}
				$("#spCode").val(drugName);
			});
			Comm.form({
				id : 'editFormSt012KjywDrug',
				url : '${webroot}/st012Kjyw/f_json/saveSt012Kjyw.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						var parentObject = parent.Comm.getObjectCache();
						parentObject.query();
						Comm.dialogClose('${param.dialogId}');
// 						menuInfo.clickMenu('抗菌药物管理','/antibiosisDrug/f_view/index.shtml',true,'A0304');
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