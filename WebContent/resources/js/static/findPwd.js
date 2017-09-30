/**********************
 * 就医160医院加盟
 * @author seven
 * @version 1.0
 * @updated 2013-08-15
 *********************/
$(function () {
	//生成地区的下拉菜单
	var hospitalName = $("#_hospitalNamev");
	var pwd = $("#_pwdv_");
	var repwd = $("#_repwdv_");
	var tel = $("#_telv");
	var _pwd = $("#_pwd_");
	var _loginUser = $("#_loginUser_");
	var _nloginUser = $("#_nloginUser_");
	
	//验证信息
	var _unit_name = $('input[name="unit_name"]');
	var _icard_ =$('input[name="icard"]');
	var _truename_ = $('#_truename_');
	var _phone    = $('input[name="phone"]');
	var _email    = $('#_email');
	var findPwForm=$('#findPwForm');
	var _pwdSubmitBtn=$("#_pwdSubmitBtn");
	
	hospitalName.length && hospitalName.on('blur',function(){
		_validate(hospitalName);
	});
	
	pwd.length && pwd.on('blur',function(){
		_validate(pwd);
	});
	
	repwd.length && repwd.on('blur',function(){
		_validate(repwd);
	});
	
	tel.length && tel.on('blur',function(){
		_validate(tel);
	});
	
	_unit_name.length && _unit_name.on('blur',function(){
		_validate(_unit_name);
	});
	
	_icard_.length && _icard_.on('blur',function(){
		_validate(_icard_);
	});
	
	_truename_.length && _truename_.on('blur',function(){
		_validate(_truename_);
	});

	_phone.length && _phone.on('blur',function(){
		_validate(_phone);
	});
	
	_email.length && _email.on('blur',function(){
		_validate(_email);
	});
	
	_pwd.length && _pwd.on('blur',function(){
		_validate(_pwd);
	});
	
	_loginUser.length && _loginUser.on('blur',function(){
		_validate(_loginUser);
	});
	
	_nloginUser.length && _nloginUser.on('blur',function(){
		_validate(_nloginUser);
	});
	
	
});


