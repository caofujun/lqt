<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormxn013Lisbyt" method="post">
	<input type="hidden" name="lisBytid" value="${xn013Lisbyt.lisBytid}">
	<input type="hidden" name="lisBytname" value="${xn013Lisbyt.lisBytname}">
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">检验系统病原体：</td>
				<td>
					${xn013Lisbyt.lisBytid} ${xn013Lisbyt.lisBytname}
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>标准病原体：</td>
				<td>
					<div class="select_del"><input id="byt" name="counterBytid" style="width:200px"/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#byt').combo('clear');"></a></div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormxn013Lisbyt').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>		
	</div>
</form>
<script>
$(document).ready(
	function() {
		window.setTimeout(function() {
			Csm.combogrid.byt({
				//【必传】控件名称
				id: 'byt',
				value:'${xn013Lisbyt.counterBytid}'
			});
			Comm.form({
				id : 'editFormxn013Lisbyt',
				url : '${webroot}/xn013Lisbyt/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({title : '提示',msg : '操作成功！'});
						xn013Lisbyt.query();
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