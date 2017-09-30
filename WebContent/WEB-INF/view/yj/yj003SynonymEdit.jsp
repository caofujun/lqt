<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormyj003Synonym" method="post">
	<input type="hidden" name="id" value="${yj003Synonym.id}">
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title"><span class="red">*</span>检验项目名称：</td>
				<td>
					<input type="text" name="synonyms" value="${yj003Synonym.synonyms}" class="easyui-validatebox" required="true"/>
				</td>
			</tr>
			<tr>
				<td class="t_title">标准：</td>
				<td>
					<div class="select_del"><input id="standard" name="standardId" style="width:152px"/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#standard').combo('clear');"></a></div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormyj003Synonym').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
$(document).ready(
	function() {
		window.setTimeout(function() {
			Csm.combogrid.standard({
				//【必传】控件名称
				id: 'standard',
				value:'${yj003Synonym.standardId}'
			});
			Comm.form({
				id : 'editFormyj003Synonym',
				url : '${webroot}/yj003Synonym/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({title : '提示',msg : '操作成功！'});
						yj003Synonym.query();
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