<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="updFormPasswd" method="post">
    <input type="hidden" id="id_userName" value="${userName}"/>
	<input type="hidden" name="sourceType" value="${param.sourceType}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title" style="width: 100px;"><span class="red">*</span>旧密码：</td>
				<td class="t_cont">
				    <input type="password" class="text" id="id_oldPassword" name="oldPassword" class="easyui-validatebox" style="width: 200px;"
				        required="true"/>
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>新密码：</td>
				<td class="t_cont">
				    <input type="password" class="text" id="id_newPassword" name="newPassword" class="easyui-validatebox" style="width: 200px;" required="true"
				        validType="extis['newPassword']"/></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>再次确认：</td>
				<td class="t_cont">
				    <input type="password" class="text" id="id_affirmPassword" name="affirmPassword" class="easyui-validatebox" style="width: 200px;" required="true"
				        validType="extis['affirmPassword']"/></td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="updPasswdFormSubmitBtn" onclick="passwdSubmit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>
			<span id="loading" style="display: none;"><img src="${webroot}/resources/load/images/loading.gif" style="margin-bottom:-3px;"/> 数据提交中...</span>		
		</div>	    
	</div>
</form>
<script>
function passwdSubmit(){
	$('#updFormPasswd').submit();
}

$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'updFormPasswd',
			url : '${webroot}/acAccount/f_json/updatePasswd.shtml',
			subbtn : 'updPasswdFormSubmitBtn',
			onLoading : function () {
				$('#loading').show();
				$('#updPasswdFormSubmitBtn').hide();
			},
			success : function(json) {
				$('#loading').hide();
				$('#updPasswdFormSubmitBtn').show();
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					parent.Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				} else {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});

		$.extend($.fn.validatebox.defaults.rules, {
			extis: {
				validator:function(value,param){
					var _bool=true;
					var oldPwd = $('#id_oldPassword').val();
					var newPwd = $('#id_newPassword').val();
					var affPwd = $('#id_affirmPassword').val();
					var userName = $('#id_userName').val();
					if(value!=''){
						if(_bool && param[0]=="affirmPassword"){
							if (newPwd !== affPwd) {
								_bool=false;
								$.fn.validatebox.defaults.rules.extis.message ="与第一次输入的密码不一致！";
							}
						}
						if(_bool && param[0]=="newPassword"){
							if((/^[A-Za-z0-9]*?$/.test(value)==false) || (value.length < 6) || (value.length > 20)){
								_bool=false;
								$.fn.validatebox.defaults.rules.extis.message ="请填入6-20位数字或者字母！";
							} else if (oldPwd === newPwd) {
								_bool=false;
								$.fn.validatebox.defaults.rules.extis.message ="为保证您的安全，新密码必须与旧密码不同";
							} 
						}
					}
					return _bool;
				},
				message:''
			}
		});
	}, 100);
});
</script>