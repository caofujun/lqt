/**********************
 * 就医160医院注册
 * @author lixuefeng
 * @version 5.0
 * @updated 2014-11-05
 *********************/
$(function () {
	var realnameO = $("#_realname");
	var passwdO = $("#_passwd");
	var repasswordO = $("#_repassword");
	var emailO = $("#_email_");
	var mobilenumO = $("#_mobilenum");
	var provinceO = $("#_province");
	var cityO = $("#_city");
	var areaO = $("#_area");
	var unitTypeO = $("#_unitType");
	var unitLevelO = $("#_unitLevel");
	var icodeO = $("#_icode");
	var unitClassO = $("input[name='unitClass']");
	
	realnameO.length && realnameO.on('blur',function(){
		regValidate(realnameO);
	});
	passwdO.length && passwdO.on('blur',function(){
		regValidate(passwdO);
	});
	repasswordO.length && repasswordO.on('blur',function(){
		regValidate(repasswordO);
	});
	emailO.length && emailO.on('blur',function(){
		regValidate(emailO);
	});
	mobilenumO.length && mobilenumO.on('blur',function(){
		regValidate(mobilenumO);
	});
	provinceO.length && provinceO.on('blur',function(){
		regValidate(provinceO);
	});
	cityO.length && cityO.on('blur',function(){
		regValidate(cityO);
	});
//	areaO.length && areaO.on('blur',function(){
//		regValidate(areaO);
//	});
	unitTypeO.length && unitTypeO.on('blur',function(){
		regValidate(unitTypeO);
	});
	unitLevelO.length && unitLevelO.on('blur',function(){
		regValidate(unitLevelO);
	});
	icodeO.length && icodeO.on('blur',function(){
		regValidate(icodeO);
	});
	
});

function regValidate(o){
		var realnameV = $("#_realname_v span");
		var passwdV = $("#_passwd_v span");
		var repasswordV = $("#_repassword_v span");
		var emailV = $("#_email_v span");
		var mobilenumV = $("#_mobilenum_v span");
		var areaV = $("#_area_v span");
		var unitTypeV = $("#_unitType_v span");
		var icodeV = $("#_icode_v span");
		var unitClassV = $("#_unitClass_v span");
		
		var realnameP = $("#_realname_v");
		var passwdP = $("#_passwd_v");
		var repasswordP = $("#_repassword_v");
		var emailP = $("#_email_v");
		var mobilenumP = $("#_mobilenum_v");
		var areaP = $("#_area_v");
		var unitTypeP = $("#_unitType_v");
		var icodeP = $("#_icode_v");
		var unitClassP = $("#_unitClass_v");
		
		var truenamereg = /^[\u4E00-\u9FA5]{2,6}$|^[A-Za-z]{2,20}$/;
		var passwdreg = /^(?=.*\d)(?=.*[a-zA-Z])[\da-zA-Z\W]{6,20}$/;
		var phonereg = /^0?(13[0-9]|15[012356789]|17[0123456789]||18[0123456789]|14[57])[0-9]{8}$/;
		var emailreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9_]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		var numreg = /^[1-9][0-9][0-9][0-9]$/;//正整数
		
		switch(o.attr('id')){
			// 真实名
			case '_realname':
				realnameV.remove();
				repasswordP.empty();
				if(!o.val()){
					realnameP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>姓名不能为空</span>");
					return false;
				}else if(!truenamereg.test(o.val())){
					realnameP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>请填写真实姓名</span>");
					return false;
				}
				realnameP.append("<span class='correct' style='display:inline-block;'></span>");
				return true;
				break;
			case '_passwd':
				passwdV.remove();
				repasswordP.empty();
				if(!o.val()){
					passwdP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>密码不能为空</span>");
					return false;
				}else if(!passwdreg.test(o.val())){
					passwdP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>密码应由6-20字符和数字组成</span>");
					return false;
				}
				passwdP.append("<span class='correct' style='display:inline-block;'></span>");
				return true;
				break;
			case '_repassword':
				repasswordV.remove();
				repasswordP.empty();
				if(!o.val()){
					repasswordP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>重复密码不能为空</span>");
					return false;
				}else if(!passwdreg.test(o.val())){
					repasswordP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>重复密码应由6-20字符和数字组成</span>");
					return false;
				}else if(o.val() != $('#_passwd').val()){
					repasswordP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>重复密码不相同</span>");
					return false;
				}
				repasswordP.append("<span class='correct' style='display:inline-block;'></span>");
				return true;
				break;
			case '_email_':
				emailV.remove();
				emailP.empty();
				if(!o.val()){
					emailP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>邮箱不能为空</span>");
					return false;
				}else if(!emailreg.test(o.val())){
					emailP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>邮箱格式不正确</span>");
					return false;
				}else if(o.val().length > 50 ){
					emailP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>邮箱长度不能大于50个字符</span>");
					return false;
				}
				var userBool = false;
				$.ajax({
					url : webroot+'/user/json/findAccountByEmail.shtml',
					data: {email:o.val()},
					dataType: 'json',
					type: "POST",
					async:false,
					error: function() {
						alert('验证邮箱数据加载失败');
					},
					success: function(json) {
						if(json.result === 'error') {
							userBool = true;
						}
					}
				});
				emailP.empty();
				if(userBool){
					emailP.append("<span class='correct' style='display:inline-block;'></span>");
				}else{
					emailP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>邮箱已存在</span>");
				}
				return userBool;
				break;
			case '_mobilenum':
				mobilenumV.remove();
				mobilenumP.empty();
				if(!o.val()){
					mobilenumP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>手机号码不能为空</span>");
					return false;
				}else if(!phonereg.test(o.val())){
					mobilenumP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>手机号码格式不正确</span>");
					return false;
				}
				var userBool = false;
				$.ajax({
					url : webroot+'/user/json/findAccountByMobilenum.shtml',
					data: {mobilenum:o.val()},
					dataType: 'json',
					type: "POST",
					async:false,
					error: function() {
						alert('验证手机号码数据加载失败');
					},
					success: function(json) {
						if(json.result === 'error') {
							userBool = true;
						}
					}
				});
				mobilenumP.empty();
				if(userBool){
					mobilenumP.append("<span class='correct' style='display:inline-block;'></span>");
				}else{
					mobilenumP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>手机号码已存在</span>");
				}
				return userBool;
				break;
			case '_province':
				areaV.remove();
				areaP.empty();
				if(!o.val() || "" == o.val()){
					areaP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>省份不能为空</span>");
					return false;
				}
				areaP.append("<span class='correct' style='display:inline-block;'></span>");
				return true;
				break;
			case '_city':
				areaV.remove();
				areaP.empty();
				if(!o.val() || "" == o.val()){
					areaP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>市不能为空</span>");
					return false;
				}
				areaP.append("<span class='correct' style='display:inline-block;'></span>");
				return true;
				break;
			case '_area':
				areaV.remove();
				areaP.empty();
				if(!o.val() || "" == o.val()){
					areaP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>区不能为空</span>");
					return false;
				}
				areaP.append("<span class='correct' style='display:inline-block;'></span>");
				return true;
				break;
			case '_unitType':
				unitTypeV.remove();
				unitTypeP.empty();
				if(!o.val()){
					unitTypeP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>医院性质不能为空</span>");
					return false;
				}
				unitTypeP.append("<span class='correct' style='display:inline-block;'></span>");
				return true;
				break;
			case '_unitLevel':
				unitTypeV.remove();
				unitTypeP.empty();
				if(!o.val()){
					unitTypeP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>医院级别不能为空</span>");
					return false;
				}
				unitTypeP.append("<span class='correct' style='display:inline-block;'></span>");
				return true;
				break;
			case '_icode':
				icodeV.remove();
				icodeP.empty();
				if(!o.val()){
					icodeP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>手机验证码不能为空</span>");
					return false;
				}else if(!numreg.test(o.val())){
					icodeP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>手机验证码格式不正确</span>");
					return false;
				}
				var userBool = false;
				var msg = "手机验证码错误";
				$.ajax({
					url : webroot+'/user/json/regSmsVerify.shtml',
					data: {mobilenum:$("#_mobilenum").val(),randomNum:o.val()},
					dataType: 'json',
					type: "POST",
					async:false,
					error: function() {
						icodeP.empty();
						icodeP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>手机验证码错误</span>");
					},
					success: function(json) {
						if(json.result === 'success') {
							userBool = true;
						}else if(json.result === 'error'){
							msg = json.msg;
						}
					}
				});
				icodeP.empty();
				if(userBool){
					icodeP.append("<span class='correct' style='display:inline-block;'></span>");
				}else{
					icodeP.append("<span class='correct'></span><span class='error' style='display:inline-block;'>"+msg+"</span>");
				}
				return userBool;
				break;
		}
		return false;
	}

/**
 * 取得工程路径
 * @returns
 */
function getRootPath(){
	//获取当前网址，如： http://localhost:8081/*1/*2/*3.jsp
	var curWwwPath=window.document.location.href;  
    //获取主机地址之后的目录，如： *1/*2/*3.jsp
    var pathName=window.document.location.pathname;  
    var pos=curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8081  
    var localhostPaht=curWwwPath.substring(0,pos);  
    //获取带"/"的项目名，如：/*1  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
	return (localhostPaht+projectName);
}


