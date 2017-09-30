<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormGm004Jcmx" method="post">
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">开始时间：</td>
				<td>
					<input type="text" name="startDate" style="width:108px"  class="Wdate text" onclick="WdatePicker()" >
				</td>
			</tr>
			<tr>
				<td class="t_title">结束时间：</td>
				<td>
					<input type="text" name="endDate" style="width:108px"  class="Wdate text" onclick="WdatePicker()" >
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormGm004Jcmx').submit()" class="no_ico"><span>计&nbsp;算</span></a>
			</div>			
		</div>			
	</div>
</form>
<script>
$(document).ready(
	function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormGm004Jcmx',
				url : '${webroot}/gm004Jcmx/f_json/judgeJcmx.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '执行中！'});
						parent.Comm.dialogClose('${param.dialogId}');
					} else if (json.result === 'error') {
						$.messager.show({title : '提示',msg : '执行失败！'});
					} else {
						$.messager.show({title : '提示',msg : json.msg});
					}
				}});
		}, 100);
	});
</script>