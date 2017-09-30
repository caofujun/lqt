<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<title>蓝蜻蜓医院感染实时监控平台</title>
<jsp:include page="/WEB-INF/view/core/res.jsp"></jsp:include>
</head>
<body>
<div class="pwd_wrap">
  <jsp:include page="/WEB-INF/view/core/header.jsp">
  	<jsp:param name="type" value="login"/>
  </jsp:include>
  <div class='pwd_main padd_0 <c:choose><c:when test="${systemScope=='cdc7.0'}">crb_bg</c:when><c:otherwise>login_bg</c:otherwise></c:choose>'>
  <div class="login_banner w960 center">
    <div class="login_box">
		<div class="hd">欢迎登录</div>
		<div class="field username-field">
			<label for="TPL_username_1" class="iconfont"></label>
			<input type="text" class="login-text J_UserName easyui-validatebox" require="true" id="username" name="username" value="" maxlength="32" tabindex="1" placeholder="请输入帐号">
			<span class="nickx" href="javascript:void(0)" style="display: block;" onclick="$('#username').val('')"></span>				
		</div>
		<div class="field pwd-field">
			<label class="iconfont"></label>
			<input type="password" class="login-text easyui-validatebox" require="true" id="password" name="password" value="" maxlength="1000" tabindex="2" placeholder="请输入登录密码">
		</div>
		<div class="login_error"><span class="red" style="display:none;" id="msgPanel">账号或密码错误，请重新输入</span></div>
		<div id="kaptchaPanel" class="field validation-field" style="display:none;">
			<label class="iconfont"></label>
			<input type="text" id="kaptcha" class="login-text" style="width:125px;" placeholder="请输入验证码" /><img src="../../../kaptcha.jpg" id="kaptchaImage"/>			
		</div>
		<div class="login_error"><span class="red" style="display:none;" id="vmsgPanel">验证码输入错误，请重新输入</span></div>
		<%-- <div class="auto">
			<span class="auto-login">
				<!-- <input type="checkbox"><label>下次自动登录</label> -->				
			</span>
			<span class="forget-pw">
			还没有账号？
			<a href="${webroot}/user/view/unitRegister.shtml" target="_blank" >立即注册</a>
			<span class="blue">|</span>
			<a href="${webroot}/user/view/findPwd.shtml" target="_blank" >忘记密码</a>			
			</span>
		</div> --%>
		<div class="submit">
			<button type="submit" class="J_Submit" tabindex="5" id="loginBtn">登　录</button>
		</div>		
		<!--  <div class="entries">
			还没有账号？ 
			<a href="${webroot}/user/view/unitRegister.shtml" target="_blank" >立即注册</a>		
		</div> -->
	</div>   
  </div>
  </div>
  
  <jsp:include page="/WEB-INF/view/core/footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
var _btn = null;
var _login_error_num = 0;
//验证码
function kaptcha(){
	$('#kaptchaImage').click(function () { 
        $(this).attr('src', 'kaptcha.jpg?' + Math.floor(Math.random()*100) ); 
    });
}

/**
 * 显示验证码面板
 */
function showKaptchaPanel(){
	if(_login_error_num >= 3){
		$("#kaptchaPanel").show();
	}
	return;
}

//登录之前验证
function kaptchaVerification(){
	//登录错误次数小于3 直接登录
	if(_login_error_num < 3){
		login();
		return;
	}
	var _vmsgPanel = $('#vmsgPanel').css('display', 'block').empty();
	$('#msgPanel').css('display', 'block').empty();
	$.ajax({
		url : '${webroot}/user/json/kaptcha.shtml',
		dataType: 'json',
		data: {kaptcha:$('#kaptcha').val()},
		type: 'post',
		cache: false,
		error: function() {
			_vmsgPanel.append('验证码验证失败！');
			_btn.removeProp('disabled');
			_btn.html('登　录');
		},
		success: function(json) {
			if(json.result === 'success') {
				//登录
				login();
			} else if(json.result === 'error') {
				_vmsgPanel.append(json.msg);
				_btn.removeProp('disabled');
				_btn.html('登　录');
			} else {
				_vmsgPanel.append('验证码验证失败！');
				_btn.removeProp('disabled');
				_btn.html('登　录');
			}
		}
	});
}

//登录
function login(){
	var _msgPanel = $('#msgPanel').css('display', 'block').empty();
	var _username = $('#username').val();
	var _password = $('#password').val();
	var _kaptcha = $('#kaptcha').val();
	$.ajax({
		url : '${webroot}/user/json/login.shtml',
		data: {username:_username,password:_password,kaptcha:_kaptcha},
		dataType: 'json',
		type: 'post',
		cache: false,
		error: function() {
			 Showbo.Msg.alert('登录失败');
			_btn.removeProp('disabled');
			_btn.html('登　录');
		},
		success: function(json) {
			_btn.removeProp('disabled');
			_btn.html('登　录');
			if(json.result === 'success') {
				if(json.data.dataScope === 9) {
					_msgPanel.append('您是运营人员，请前往运营后台登录！');
				} else if (json.data.isNeedUpdPwd === '1') {
					location = '${webroot}/acAccount/f_view/toUpdatePasswd.shtml';
				} else if(json.data.roleCur.roleType=='clinical'){
					location="${webroot}/user/f_view/main_admin.shtml";
				}else {
					location = '${webroot}/user/f_view/main.shtml';
				}
			} else if (json.result === 'repeat') {
				Showbo.Msg.show({buttons:{yes:'确认'},msg:'由于'+json.msg+'，即将跳转到修改页面!',title:'提示',ctimer:5,
					fn:function () {
						location = '${webroot}/user/view/repeat.shtml?loginUser='+$.base64.encode(json.extraValue,'utf-8');
					},
					tfn : function () {
						location = '${webroot}/user/view/repeat.shtml?loginUser='+$.base64.encode(json.extraValue,'utf-8');
					}
				});
			} else if(json.result === 'error') {
				if(json.expandValue){
					_login_error_num = parseInt(json.expandValue);
				}else{
					_login_error_num++;
				}
				/* showKaptchaPanel();
				if(_login_error_num >= 3){
					$('#kaptchaImage').click();
				} */
				_msgPanel.append(json.msg);
			}else if(json.result === 'error_p'){
				_msgPanel.append(json.msg);
			} else {
				_login_error_num ++;
				/* showKaptchaPanel();
				if(_login_error_num >= 3){
					$('#kaptchaImage').click();
				} */
				_msgPanel.append(json.msg);
			}
		}
	});
}

$(function() {
    if(window.parent != window) {
        window.parent.location.href = window.location.href; 
    }
    kaptcha();
    $('#username').focus();
    $('#username').keydown(function (e) {
        if (e.which == 13) $('#password').focus();
    });
    $('#password').keydown(function (e) {
        var key = e.which;
        if (key == 13) $('#loginBtn').click();
    });
	$('#loginBtn').click(function() {
		//临时加的
		var un = $('#username').val();
		var pw = $('#password').val();
		if(un && pw){
			_btn = $('#loginBtn');
			_btn.prop('disabled', true);
			_btn.html('登 录 中 ...');
			login();
			return;
		}else{
			if(!un){
				$('#username').focus();
			}else if(!pw){
				$('#password').focus();
			}
		}
		
	});
});
</script>
</body>
</html>