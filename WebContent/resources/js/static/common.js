//@dlg_msg
function dlg_msg(o, $dmsg){
	var $_dom = o.closest('tr');
	var $_hidden = $_dom.next('tr').hasClass('is-hidden');
	var $_visible = $_dom.next('tr').hasClass('is-visible');

	if($dmsg){
		if($_dom.next('tr').hasClass('dlg_class_'+o.attr('id'))){
			$_dom.next('tr').find('.form-validation').html($dmsg);
			if($_dom.next('tr').css('display')=='none')$_dom.next('tr').show();
			if($_visible || $_hidden)$_dom.next('tr').removeClass('is-hidden').addClass('is-visible');
		}else{
$dmsg = '\
<tr class="dlg_class_'+o.attr('id')+'">\
	<th></th>\
	<td><div class="form-validation">'+$dmsg+'</div></td>\
</tr>';

			$_item_msg = $($dmsg).hide();
			$_dom.after($_item_msg);
			$_item_msg.fadeIn();
		}
	}else{
		if($_visible || $_hidden){
			$_dom.next('tr').find('.form-validation').html('');
			$_dom.next('tr').addClass('is-hidden').removeClass('is-visible');
		}else{
			if($_dom.next('tr').hasClass('dlg_class_'+o.attr('id')))$_dom.next('tr').hide('fast');
		}
	}
};
//@dialogMsg
function dialogMsg(msg, style,showId)
{
	var showId = showId || $('#_dialogMsg');
	switch(style){
		case 1:
			showId.removeClass().addClass('wrong').show().html(msg);
			break;
		case 2:
			showId.removeClass().addClass('warning').show().html(msg);
			break;
		case 3:
			showId.hide();
			break;
	}
}
//@validate
function _validate(o, arge){
	var arge = arge || {};
	var truenamereg = /^[\u4E00-\u9FA5]{2,6}$|^[A-Za-z]{2,18}$/;
	var passwdreg = /^(?=.*\d)(?=.*[a-zA-Z])[\da-zA-Z\W]{8,20}$/;
	var birthreg = /^\d{4}-\d{1,2}-\d{1,2}$/;
	var phonereg = /^0?(13[0-9]|15[012356789]|18[0123456789]|14[57])[0-9]{8}$/;
	var emailreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9_]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var contactreg = /(^(\d{3,4}-)?\d{7,8})$|((\(\d{3}\))|(\d{3}\-))?1[3-8][0-9]\d{8}?$|15[89]\d{8}$/; //联系方式，包括手机、 固话
	var numreg = /^[0-9]*[1-9][0-9]*$/;//正整数
	switch(o.attr('id')){
		//@浮动登录
	//@找回密码
	case '_findcard':
		if(!o.val()){
			dlg_msg(o, '证件号码不能为空');
		}else if($('#reglink').length>0){
			return false;
		}else{
			return true;
		}
		break;
	//@登录
	case '_username' :
		if(!o.val()){
			dlg_msg(o, '请输入手机号码或电子邮箱');
		}else{
			return true;
		}
		break;
	case '_password':
		if(!o.val()){
			dlg_msg(o,'请输入登录密码');
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	//@注册
	case '_regagree':
		if(!o.prop("checked")){
			dlg_msg(o, "请先阅读并同意注册许可协议");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	//@公共
	case '_phoneormail_':
		if(!o.val()){
			dlg_msg(o, "用户名不能为空");
		}else{
			return true;
		}
		break;
	case '_phone_'://@设置密码
		if(!o.val()){
			dlg_msg(o, "手机号码不能为空");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_card_'://@设置密码
		if(!o.val()){
			dlg_msg(o, "证件号码不能为空");
		}else{
			if($('#_card_type').val()=='01'){
				if(o.val().length!=15 || o.val().length!=18){
					dlg_msg(o, "请输入正确的身份证号码");
					return false;
				}
			}
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_pwd_'://@找回密码/设置密码/注册
		if(o.val() == ''){
			dlg_msg(o, '密码不能为空');
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_hospital_'://@医院名称
		if(o.val() == ''){
			dlg_msg(o, '医院名称不能为空');
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_loginUser_'://@登录账号
		if(o.val() == ''){
			dlg_msg(o, '登录账号为空');
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
		
	case '_nloginUser_'://@新登录账号
		if(o.val() == ''){
			dlg_msg(o, '新登录账号不能为空');
		}else{
			var reg = /.*[\u4e00-\u9fa5]+.*$/;
			if (reg.test(o.val())){
				dlg_msg(o, '账号不能为包含中文');
				return false;
			} 
			var userBool = false;
			$.ajax({
				url : webroot+'/user/json/check.shtml',
				data: {username:o.val()},
				dataType: 'json',
				type: "POST",
				async:false,
				error: function() {
					 Showbo.Msg.alert('验证数据加载失败');
				},
				success: function(json) {
					if(json.result === 'error') {
						userBool = false;
						dlg_msg(o, '账号已存在');
					} else {
						userBool = true;
						dlg_msg(o, '');
					}
				}
			});
			return userBool;
		}
		break;
	case '_kaptcha_'://@验证码
		if(o.val() == ''){
			dlg_msg(o, '验证码不能为空');
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_repwd_'://@找回密码/设置密码/注册
		if(o.val() == ''){
			dlg_msg(o, '请输入重复密码');
		}else if(o.val() != $('#_pwd_').val()){
			dlg_msg(o, '重复密码不相同');
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_checkimgcode_'://@找回密码
		if(o.val()==''){
			dlg_msg(o, "验证码不能为空");
		}else if(o.val().length !=4){
			dlg_msg(o, "请输入有效的校验码，填入右边图片中的文字");
		}else{
			dlg_msg(o,'');
			return true;
		}
		break;
	case '_smscode_'://@发送短信验证码
		if(o.val().length!=4){
			dlg_msg(o, '请输入正确的验证码');
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_jyday_':
	case '_mbirth'://个人中心-出生日期
		var _stat = true;
		var _jytdate = '';
		o = arge.o ? arge.o : o;
		o.parent().children('select').each(function(){
			if($(this).val() == '0'){
				dlg_msg(o, '请选择正确的出生日期');
				_stat = false;
			}else{
				_jytdate = _jytdate + '-' + $(this).val();
			}
		})

		if(_stat){
			var thisbirth = arge.card;
			if(thisbirth.length==18){
				var y = thisbirth.substr(6,4);
				var m = thisbirth.substr(10,2);
				var d = thisbirth.substr(12,2);
				if([y,m,d].join('-')!=_jytdate.substr(1,11)){
					dlg_msg(o, "出生日期与身份证号码不一致");
				}else{
					dlg_msg(o, '');
					return _jytdate.substr(1,11);
				}
			}else{
				dlg_msg(o, '');
				return _jytdate.substr(1,11);
			}
		}
		break;
	case '_truename_'://成员姓名
		if(!o.val()){
			dlg_msg(o, "真实姓名|用户名不能为空");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_icard_'://成员/证件号码
		if(!o.val()){
			dlg_msg(o, "证件号码不能为空");
		}else{
			if(o.prev('select').val()=='01'){
				if(o.val().length==15 || o.val().length==18){
					switch(o.val().length){
						case 15:
							sex = o.val().charAt(14);
							sex = (sex%2)==0?1:0;
							break;
						default:
							sex = o.val().charAt(16);
							sex = (sex%2)==0?1:0;
							var y = o.val().substr(6,4);
							var m = o.val().substr(10,2);
							var d = o.val().substr(12,2);
							$jybirth(y, m, d,{
								jyyear:o.parents('.t_info').find('._jyyear'),
								jymonth:o.parents('.t_info').find('._jymonth'),
								jyday:o.parents('.t_info').find('._jyday')});
					}

					o.parents('.t_info').find('._isex').each(function(){
						if($(this).val() == sex){
							$(this).attr('checked','checked');
						}
					})
				}else{
					dlg_msg(o, "请输入正确的身份证号码");
					return false;
				}
			}
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_email':
		if($("#no_email").attr("checked")){
			return true;
		}else{
			if(!o.val()){
				dlg_msg(o, "邮箱不能为空");
			}else if(!emailreg.test(o.val())){
				dlg_msg(o, "邮箱地址不正确");
			}else{
				dlg_msg(o, '');
				return true;
			}
		}
		break;
	case '_agree':
		if(!o.attr("checked")){
			dlg_msg(o, "请先阅读许可协议");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_unit_name':
		if(!o.val()){
			dlg_msg(o, "医院名称不能空");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_phone':
		if(!o.val()){
			dlg_msg(o, "联系方式不能为空");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_unit_level':
		if(!o.val()){
			dlg_msg(o, "联系方式不能为空");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_outpatient':
		if(!o.val()){
			dlg_msg(o, "日门诊量不能为空");
		}else if(!numreg.test(o.val())){
			dlg_msg(o, "日门诊量为正整数");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_tel':
		if(!o.val()){
			dlg_msg(o, "手机号码不能为空");
		}else if(!phonereg.test(o.val())){
			dlg_msg(o, "手机号码格式不正确");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_hospitalName':
		if(!o.val()){
			dlg_msg(o, "医院名称不能为空");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
	case '_user_post':
		if(!o.val() || !$('#_user_name').val()){
			dlg_msg(o, "请填写您的姓名和职位");
		}else{
			dlg_msg(o, '');
			return true;
		}
		break;
		case '_nyusername' :
			if(!o.val()){
				dialogMsg('请输入手机号码或电子邮箱',1);
			}else{
				var type = 'phone';
				var userBool = true;
				var phonereg = /^((\(\d{3}\))|(\d{3}\-))?1[3-8][0-9]\d{8}?$|15[89]\d{8}$/;
				if(phonereg.test($(o).val())){
					$.ajax({
						url:'/user/checkpwd/type-'+type+'/username-'+$(o).val(),
						type:'post',
						dataType:'json',
						async:false,
						success:(function(de){
							_login_state = 1;
							if(de.code==1){
								userBool = false;
								_login_state = 0;
								dialogMsg("您的账号尚未激活<br /><span class='clr3'>如果您已通过电话或医院现场成功预约过，<br/>请<a href='"+JYUrl("user","activate")+"'>账号激活</a>完成相应步骤，即可登录。</span>", 1);
								$.cookieHelper('setpwd', [type,$(o).val()], {expires: 3600,path: '/', domain: $_ny_domain_});
							}else if(de.code < 0){
								userBool = false;
								_login_state = 0;
								dialogMsg(de.msg,1);
							}else{
								dialogMsg('',3);
							}
						})
					});
				}
				return userBool;
			}
			break;
		case '_nypassword':
			if(!o.val()){
				dialogMsg('请输入登录密码', 1);
			}else{
				dialogMsg('', 3);
				return true;
			}
			break;
		case '_nycheckcode':
			if(o.val().length!=4){
				dialogMsg('请输入有效的校验码，填入右边图片中的文字', 1);
			}else{
				dialogMsg('', 3);
				return true;
			}
			break;
		case '_nynewusername':
			if(!o.val()){
				return false;
			}else{
				return true;
			}
			break;
		case '_nynewpassword':
			if(!o.val()){
				return false;
			}else{
				return true;
			}
			break;
	}

	return false;
}

