<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormzg027LisbbPp" method="post">
	<input type="hidden" name="bbppId" value="${zg027LisbbPp.bbppId}">
	<input type="hidden" name="itemCode" value="${zg027LisbbPp.itemCode}">
	<input type="hidden" name="itemName" value="${zg027LisbbPp.itemName}">
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">检验系统标本：</td>
				<td>
					${zg027LisbbPp.itemCode} ${zg027LisbbPp.itemName}
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>标准标本：</td>
				<td>
					<div class="select_del"><input id="bbDict" name="bbid" style="width:200px"/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#bbDict').combo('clear');"></a></div>
					<input type="hidden" id="bbmc" name="bbmc"/>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormzg027LisbbPp').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>			
	</div>
</form>
<script>
$(document).ready(
	function() {
		window.setTimeout(function() {
			Csm.combogrid.bbDict({
				//【必传】控件名称
				id: 'bbDict',
				value:'${zg027LisbbPp.bbid}',
				onSelect: function(){
					$('#bbmc').val($('#bbDict').combobox('getText'));
				}
			});
			Comm.form({
				id : 'editFormzg027LisbbPp',
				url : '${webroot}/zg027LisbbPp/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({title : '提示',msg : '操作成功！'});
						zg027LisbbPp.query();
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