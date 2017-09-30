<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormxn014Liskjyw" method="post">
	<input type="hidden" name="drugid" value="${xn014Liskjyw.drugid}">
	<input type="hidden" name="drugname" value="${xn014Liskjyw.drugname}">
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">检验系统药敏药物：</td>
				<td>
					${xn014Liskjyw.drugid} ${xn014Liskjyw.drugname}
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>标准药敏药物：</td>
				<td>
					<div class="select_del"><input id="kjyw" name="counterDrugid" style="width:200px"/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#kjyw').combo('clear');"></a></div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;"  id="changeFormSubmitBtn" onclick="$('#editFormxn014Liskjyw').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
$(document).ready(
	function() {
		window.setTimeout(function() {
			Csm.combogrid.kjyw({
				//【必传】控件名称
				id: 'kjyw',
				value:'${xn014Liskjyw.counterDrugid}'
			});
			Comm.form({
				id : 'editFormxn014Liskjyw',
				url : '${webroot}/xn014Liskjyw/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({title : '提示',msg : '操作成功！'});
						xn014Liskjyw.query();
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