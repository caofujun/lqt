<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<form id="editFormQm" method="post">
	<input type="hidden" name="blId" value="${param.blId}">
	<input type="hidden" id="qmType" name="qmType" value="${param.qmType}">
	<input type="hidden" name="ygkYj" value="${param.ygkYj}">
	<input type="hidden" name="cwkYj" value="${param.cwkYj}">
	<table class="table mb60" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title" style="width: 80px;"><span class="red">*</span>工号：</td>
				<td ><input type="text" name="username"
					value="" style="width:200px; class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>密码：</td>
				<td ><input type="password" name="password"
					value="" style="width:200px; class="easyui-validatebox text" required="true"></td>
			</tr>
			
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormQm').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>				
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormQm',
				url : '${webroot}/bl002Sjdj/f_json/yzQm.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '签名成功！' });
						if($('#qmType').val()!='kjyj'){
							editFormWtjg06submit();
						}else if($('#qmType').val()=='kjyj'){
							editFormWtjg02submitSave();
						}
						//location.reload();
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : '签名验证失败，用户名密码错误！' });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
	});
</script>