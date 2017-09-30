<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fun" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改密码</title>
<jsp:include page="/WEB-INF/view/core/res.jsp"></jsp:include>
 
</head>

<body>
<div class="pwd_wrap">
  <!-- 头部导航条 -->
  <jsp:include page="/WEB-INF/view/core/header.jsp"></jsp:include>
  <div class="pwd_main">
	<div class="pwd_content w960 center">
		<div class="pwd_tab clearfix"> <span class="pwd_tab_a fs16 fw pl16 " id="findPwdA">修改密码（首次登录修改密码）</span> </div>
		<div class="reg_content">
			<div class="cont_left">
				<div class="reset_password">
				    <div class="table_pwd" style="display: none; text-align: center;" id="id_jump" width="100%" cellpadding="0" cellspacing="0">
				        <span style="color:red">密码修改成功！</span>页面将在1秒后跳转...
				    </div>
				    <input type="hidden" id="id_userName" value="${userName}"/>
					<table class="block_center" id="id_password" width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<th class="form-key gray_4" width="100">旧密码</th>
							<td  class="form-value" width="165"><input name="oldPassword" class="form-input text" type="password" id="id_oldPassword" onblur="oldPwdVail();"  placeholder="请输入旧密码"></td>
							<td class="form-prompt gray_4"><span class="correct"></span><span style="display:inline-block;" id="sp_oldPassword"></span></td>
						</tr>
						<tr>
							<th class="form-key gray_4">新密码</th>
							<td class="form-value"><input name="newPassword" class="form-input text" type="password" id="id_newPassword" maxlength="20" onblur="newPwdVail();" placeholder="请输入新密码"></td>
							<td class="form-prompt gray_4"><span class="correct"></span><span  style="display:inline-block;" id="sp_newPassword"></span></td>
						</tr>
						<!-- <tr>
							<th></th>
							<td><div class="pw_weak"><span class="pw_bar">弱</span><span>中</span><span>强</span></div></td>
							<td></td>			
						</tr> -->
						<tr>
							<th class="form-key gray_4">再次确认</th>
							<td class="form-value"><input name="affirmPassword" class="form-input text" type="password" id="id_affirmPassword" maxlength="20" onblur="affPwdVail();"></td>
							<td class="form-prompt gray_4"><span class="correct"></span><span  style="display:inline-block;" id="sp_affirmPassword"></span></td>
						</tr>
						<tr>
							<th></th>
							<td class="form-value">
							    <input class="btn_submit" id="id_submit" type="button" onclick="submitPwd();" value="提交修改">
							    <span id="loading" style="display: none;"><img src="${webroot}/resources/load/images/loading.gif" style="margin-bottom:-3px;"/> 数据提交中...</span>
							</td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>	  
				<div class="cont_right">
					<div class="prompt_title">特别提示:</div>
					<ul class="prompt_li">
						<li>• 新用户第一次登录必须修改密码</li>
						<li>• 请勿使用太过简单的密码</li>
					</ul>
					<div  class="prompt_cont icon_doubt">
						<b>如果您有疑问？</b>
						<p>请拨打全国免费客服热线</p>
						<div class="orange_0 fs16">400-11-91160</div>
					</div>
					<div class="prompt_cont icon_prompt">
						<p class="span_m">如果您已使用就医160网站排班，可点击<a href="${webroot}/index.jsp">登录</a></p>
					</div>
				</div>
		    </div>
		</div>  
	</div>
	<!-- 底部 -->
	<jsp:include page="/WEB-INF/view/core/footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
function oldPwdVail() {
	var oldPwd = $('#id_oldPassword').val();
	if (oldPwd.length <= 0) {
		$('#sp_oldPassword').html('请输入旧密码');
		$('#sp_oldPassword').removeClass('correct');
		$('#sp_oldPassword').addClass('error');
		return false;
	} else {
		$('#sp_oldPassword').html('');
		$('#sp_oldPassword').removeClass('error');
		$('#sp_oldPassword').addClass('correct');
		return true;
	}
}

function newPwdVail() {
	var oldPwd = $('#id_oldPassword').val();
	var newPwd = $('#id_newPassword').val();
	var affPwd = $('#id_affirmPassword').val();
	var userName = $('#id_userName').val();
	if (affPwd.length > 0) {
		if (newPwd !== affPwd) {
			$('#sp_affirmPassword').html('与第一次输入的密码不一致');
			$('#sp_affirmPassword').removeClass('correct');
			$('#sp_affirmPassword').addClass('error');
		} else {
			$('#sp_affirmPassword').html('');
			$('#sp_affirmPassword').removeClass('error');
			$('#sp_affirmPassword').addClass('correct');
		}
	}
	if (newPwd.length <= 0) {
		$('#sp_newPassword').html('请输入新密码');
		$('#sp_newPassword').removeClass('correct');
		$('#sp_newPassword').addClass('error');
		return false;
	} else if (oldPwd === newPwd) {
		$('#sp_newPassword').html('为保证您的安全，新密码必须与旧密码不同');
		$('#sp_newPassword').removeClass('correct');
		$('#sp_newPassword').addClass('error');
		return false;
	} else if (userName === newPwd) {
		$('#sp_newPassword').html('新密码不能和用户名相同');
		$('#sp_newPassword').removeClass('correct');
		$('#sp_newPassword').addClass('error');
		return false;
    }else if ((newPwd.length < 6) || (newPwd.length > 20)) {
		$('#sp_newPassword').html('密码应由6-20 个字符组成');
		$('#sp_newPassword').removeClass('correct');
		$('#sp_newPassword').addClass('error');
		return false;
	} else {
		$('#sp_newPassword').html('');
		$('#sp_newPassword').removeClass('error');
		$('#sp_newPassword').addClass('correct');
		return true;
	}
}

function affPwdVail() {
	var newPwd = $('#id_newPassword').val();
	var affPwd = $('#id_affirmPassword').val();
	if (affPwd.length <= 0) {
		$('#sp_affirmPassword').html('请输入确认密码');
		$('#sp_affirmPassword').removeClass('correct');
		$('#sp_affirmPassword').addClass('error');
		return false;
	} else if (newPwd !== affPwd) {
		$('#sp_affirmPassword').html('与第一次输入的密码不一致');
		$('#sp_affirmPassword').removeClass('correct');
		$('#sp_affirmPassword').addClass('error');
		return false;
	} else {
		$('#sp_affirmPassword').html('');
		$('#sp_affirmPassword').removeClass('error');
		$('#sp_affirmPassword').addClass('correct');
		return true;
	}
}

function jump(count) { 
	$('#id_jump').show();
	$('#id_password').hide();
    window.setTimeout(function(){ 
        count--; 
        if(count > 0) { 
            //$('#num').html(count); 
            jump(count); 
        } else { 
            location.href="${webroot}/user/f_view/main.shtml"; 
        } 
    }, 1000); 
} 

//登录
function submitPwd(){
	if (oldPwdVail() && newPwdVail() && affPwdVail()) {
		var oldPwd = $('#id_oldPassword').val();
		var newPwd = $('#id_newPassword').val();
		var affPwd = $('#id_affirmPassword').val();
		$('#loading').show();
		$('#id_submit').hide();
		$.ajax({
			url : '${webroot}/acAccount/f_json/updatePasswd.shtml',
			data: {oldPassword:oldPwd,newPassword:newPwd,affirmPassword:affPwd},
			dataType: 'json',
			type: 'post',
			cache: false,
			error: function() {
				alert('修改失败');
				$('#id_submit').show();
				$('#loading').hide();
			},
			success: function(json) {
				$('#id_submit').show();
				$('#loading').hide();
				if(json.result === 'success') {
					jump(1); 
				} else if (json.result === 'info_error'){
					$('#sp_oldPassword').html(json.msg);
					$('#sp_oldPassword').removeClass('correct');
					$('#sp_oldPassword').addClass('error');
				} else {
					alert(json.msg);
				}
			}
		});
	}
}
</script>
</body>
</html>
