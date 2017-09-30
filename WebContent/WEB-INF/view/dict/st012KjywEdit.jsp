<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormst012Kjyw" method="post">
	<input type="hidden" name="id" value="${st012Kjyw.id}">
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">检验系统抗菌药物：</td>
				<td>
					${st012Kjyw.id} ${st012Kjyw.drugName}
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>抗菌药物：</td>
				<td>
					<div class="select_del"><input type="text" name="syDrugId" id="id_syDrugId" style="width: 120px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_impOpeId').combo('clear');"></a></div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormst012Kjyw').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>			
	</div>
</form>
<script>
$(document).ready(
	function() {
		window.setTimeout(function() {
			//标准抗菌药物
			Csm.combogrid.zgKjyw({
				id: 'id_syDrugId'
			});
			Comm.form({
				id : 'editFormst012Kjyw',
				url : '${webroot}/st012Kjyw/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({title : '提示',msg : '操作成功！'});
						zg010Kjyw.query2();
						zg010Kjyw.query3();
						Comm.dialogClose('${param.dialogId}');
					} else if (json.result === 'error') {
						$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						$.messager.show({title : '提示',msg : json.msg});
					}
				}});
		}, 100);
	});
</script>