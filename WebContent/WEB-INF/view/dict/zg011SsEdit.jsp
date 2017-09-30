<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormzg011Ss" method="post">
	<input type="hidden" name="operId" value="${zg011Ss.operId}">
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">检验系统手术：</td>
				<td>
					${zg011Ss.operId} ${zg011Ss.operaCnname}
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>重点手术：</td>
				<td>
					<div class="select_del"><input type="text" name="impOpeId" id="id_impOpeId" style="width: 180px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_impOpeId').combo('clear');"></a></div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormzg011Ss').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>			
	</div>
</form>
<script>
$(document).ready(
	function() {
		window.setTimeout(function() {
			//重点监测手术
			Csm.combogrid.sysDic({
				id: 'id_impOpeId',
				value: '',
				mode:'',
				dictTypeCode : 'operation_point'
			});
			
			Comm.form({
				id : 'editFormzg011Ss',
				url : '${webroot}/zg011Ss/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({title : '提示',msg : '操作成功！'});
						zg024ImpOpe.query2();
						zg024ImpOpe.query3();
						Comm.dialogClose('${param.dialogId}');
					} else if (json.result === 'error') {
						$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						$.messager.show({title : '提示',msg : json.msg});
					}
				}});
		}, 100);
		
		setTimeout("$('#id_impOpeId').focus()",1000);
	});
</script>