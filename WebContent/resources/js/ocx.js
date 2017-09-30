//电话
function hangUpTele() {
	window.top.JYTopTele.ocxControl.HangUp();
}
var NYTele = function (options) {
	$.extend(this, ocxTeleConfig, {
		ocxControl: null,
		workPath: 'D:/nycsm/'
	}, options || {});
	
	if (!this.ocxControl) {
		return alert('控件连接不正常，请检查');
	}
	
	// 属性赋值
	this.connected = false;
	this.callNo = '';
	this.isMute = 0;
	this.isHold = 0;
	//this.hangType = '1';
	
	// 控件初始化
	try {
		this.ocxControl.InitTeleDevice();
		this.ocxControl.OutsideLineNo = this.outSideLineNo;
		//是否启用录音
		this.ocxControl.EnableRecord = this.enableRecord;
		this.ocxControl.UserId = JYUser.userId;
		this.ocxControl.UserName = JYUser.userName;
		this.ocxControl.UnitId = JYUser.unitId;
		this.connected = this.ocxControl.DeviceConnected;
		
		this.onHangUp = null;
		this.onCallOut = null;
		this.onCallIn = null;
		this.ocxInstalled = true;
	} catch (e) {
		this.ocxInstalled = false;
	}
};
NYTele.prototype.getOcxVersion = function () {
	return this.ocxControl.Version;
};
NYTele.prototype.callType = function () {
	return this.ocxControl.CallType;
};
NYTele.prototype.isOffHook = function () {
	if (this.ocxControl && this.connected) {
		return this.ocxControl.IsOffHook;
	} else {
		return false;
	}
};
NYTele.prototype.callOut = function (teleNo, extNo, bizCode) {
	this.ocxControl.bizCode = bizCode;
	if (!this.connected) {
		//初始化链接
		this.ocxControl.InitTeleDevice();
		this.ocxControl.OutsideLineNo = this.outSideLineNo;
		this.connected = this.ocxControl.DeviceConnected;
	}
	if (this.connected && this.ocxControl.DeviceConnected) {
		//链接成功，开始呼叫
		this.callNo = teleNo;
		//alert('teleNo='+teleNo+'||this.outSideLineNo='+this.outSideLineNo+'||extNo='+extNo);
		this.ocxControl.CallOut(teleNo, this.outSideLineNo, extNo);
	} else {
		this.connected = false;
		alert('设备连接不正常，请检查电话与电脑的链接。');
		return false;
	}
};

NYTele.prototype.hangUp = function (force) {
	if (force && force == '2') {
		alert("hangUp2");
		this.ocxControl.Answer();//模拟接听，然后挂断
		window.setTimeout(hangUpTele, 1000);
		//this.hangType = '2';
	} else {
		//alert("hangUp1");
		//挂断
		this.ocxControl.HangUp();
	}
	/*if (typeof this.onHangUp == 'function') {
		this.onHangUp();
	}*/
};
NYTele.prototype.answer = function () {
	this.ocxControl.Answer();
};
NYTele.prototype.hold = function (on) {
	this.ocxControl.Hold(on);
};
NYTele.prototype.mute = function (on) {
	this.ocxControl.Mute(on);
};
NYTele.prototype.bell = function (on) {
	this.ocxControl.Bell(on);
};
NYTele.prototype.login = function () {
	if(!JYTopTele.isLogin && window.attachEvent) {
		this.ocxControl.LoginYuyue();
		JYTopTele.isLogin = true;
	}
};
NYTele.prototype.getRecordFileName = function () {
	return this.ocxControl.LocalRecordFileName;
};
//保存录音
NYTele.prototype.saveCallRecord = function () {
	//不上传录音
	if(Comm.phone.uploadRecord === 0) {
		return;
	}
	$.messager.progress({
		text : '文件上传中，请稍等....',
		interval : 200
	});
	// 此时必须使得全部不可用，在处理完本业务逻辑后，才能再次发起或者接电话
	var startTime = this.ocxControl.StartTime;
	var endTime = this.ocxControl.EndTime;
	var duration = this.ocxControl.Duration;
	var fileName = this.ocxControl.FtpRecordFileName;
	var callType = this.ocxControl.CallType;
	var bizCode = this.ocxControl.bizCode;
	var telNo = this.callNo;
	
	var self = this;
	if (duration == 0 || startTime == endTime || duration < 5) {return 0;}
	if (fileName == '') {return 0;}
	var _result = self.uploadRecordFile();
	//如果返回空
	if (!_result){
		$.messager.progress('close');
		return ;
	} 
	_result = $.parseJSON(_result);
	//写上传文件成功的代码
	//alert(['上传录音startTime:',data.startTime,',endTime:',endTime,',duration:',duration,',fileName:',fileName,',callType:',callType,',telNo:',telNo,',bizCode:',bizCode].join(''));
	if (_result.status == 1) {
		var data = {'startTime': startTime, 'endTime': endTime, 'duration': duration, 'fileName': _result.fileUrl, 
				'callType': callType, 'phone': telNo,'patientId':Comm.phone.patientId,'visitName':Comm.phone.patientName};
		$.ajax({
	        url: webroot+'/foTelrecord/f_json/save.shtml',
	        type: 'post',
	        data: data,
	        dataType: 'json',
	        success : function(data) {
	        	$.messager.progress('close');
				if(data.result === 'success') {
	                $.messager.show({ title: '提示', msg: '呼叫完成，录音已上传！' });
		    	}
		    	else $.messager.show({ title: '提示', msg: '上传录音失败！' });
			}
	    });
	} else {
		$.messager.progress('close');
		_result.msg = '';
		if (_result.status == 5) _result.msg = '上传异常';
		if (_result.status == 4) _result.msg = '超时请求';
		if (_result.status == 3) _result.msg = '参数缺失或者异常';
		if (_result.status == 2) _result.msg = '权限不够,校验有误';
		$.messager.show({ title: '提示', msg: _result.msg});
	}
	/*if (telNo) {
		$.post('?c=tele&a=saveRecord', data, function (rsp) {
			if (rsp.code > 0 ) {
				JYCsm.messager.succ(rsp.msg);
				// ftp文件上传
				self.uploadRecordFile();
			} else if (rsp.code < 0){
				JYCsm.messager.error(rsp.msg);
			} else {
			}
		}, 'json');
	}*/
};
NYTele.prototype.uploadRecordFile = function () {
	//alert(this.ocxControl.LocalRecordFileName);
	if(Comm.phone.uploadRecord != 0) {
		try {
			this.ocxControl.HTTPPut(this.ocxControl.LocalRecordFileName,voiceUploadHttpUrl);
			//this.ocxControl.HTTPPut('D:\\nycsm\\records\\20141025143812_1_015273015030_0.wav',voiceUploadHttpUrl);
			return this.ocxControl.PostState;
			//JYCsm.messager.succ('文件上传成功');
		} catch (e) {
			return 5;
		}
	}
};


// ftp
var NYFtp = function (options) {
	/*$.extend(this,
	{host: '127.0.0.1',
	port: 21,
	userName: 'csm',
	password: 'csm',
	passive: 1,
	dir: 'csmftp',
	ocxControl: null,
	workPath: 'D:/nycsm/'}, options||{});*/
	$.extend(this, ocxFtpConfig, {ocxControl: null, workPath: 'D:/nycsm/'}, options||{});
	
	if (!this.ocxControl) {
		return alert('控件连接不正常，请检查');
	}
	
	// 控件初始化
	this.ocxControl.FtpHost = this.host;
	this.ocxControl.FtpPort = this.port;
	this.ocxControl.FtpPassive = this.passive;
	this.ocxControl.FtpUserName = this.userName;
	this.ocxControl.FtpPassword = this.password;
	this.ocxControl.FtpDir = this.dir;
	this.ocxControl.WorkPath = this.workPath;
};
NYFtp.prototype.put = function (localFileName) {
	this.ocxControl.FtpPut(localFileName);
};
NYFtp.prototype.putDir = function (dir) {
	this.ocxControl.FtpPutDir(dir);
};


// checkOcxFunction
$(function () {
	$('.check-ocxctrl').click(function (event) {
		if (!JYCsm.ocxInstalled()) {
			window.top.art.dialog({title: '温馨提示', content:'该功能需要浏览器控件支持，请下载安装', 
				lock: true,
				okVal: '下载',
				ok: function () {
					window.location = '/tool/nycsm.zip';
				}, cancel: function () {}});
			event.preventDefault();
			return false;
		}
	});
});

function checkOcxCtrl() {
	if (!JYCsm.ocxInstalled()) {
		//window.top.art.dialog({title: '温馨提示', content:'该功能需要浏览器控件支持，请下载安装',
		Comm.phone.download();
		return false;
	} else {
		return true;
	}
}

function createOptionHtml(arr, nameField, valField) {
	var html = '';
	for (var el in arr) {
		html += '<option value="'+el[valField]+'">'+el[nameField]+'</option>';
	}
	return html;
}

var NYUser = function () {
		this.userId = '';
		this.userNo = '';
		this.userName = '';
		this.unitId = '';
	};
var NYPatient = function () {
		this.id = 0;
		this.name = '';
		this.phone = '';
};

var NYCsm = function (){
	this.messager = new NYMessager();
	this.form = new NYForm();
};

NYCsm.prototype.ocxInstalled = function () {
	try {
		if (window.top.JYTopTele.ocxInstalled) {
			return true;
		} else {
			throw new Error('电话控件没有安装');
		}
		//var ocxControl = new ActiveXObject('NYTeleActiveX.NYTeleActiveFormX');
	} catch (e) {
		return false;
	}
	return true;
};

NYCsm.prototype.checkOcx = function () {
	try {
		if (window.top.JYTopTele.ocxInstalled) {
			return true;
		} else {
			throw new Error('电话控件没有安装');
		}
		//var ocxControl = new ActiveXObject('NYTeleActiveX.NYTeleActiveFormX');
	} catch (e) {
		window.top.art.dialog({title: '温馨提示', content:'您没有安装浏览器控件，如需使用电话和预约诊疗功能，请下载安装', 
			lock: true,
			okVal: '下载',
			ok: function () {
				window.location = '/tool/nycsm.zip';
			}, cancel: function () {}});
	}
	// 版本检测更新
};
// 消息提示框
var NYMessager = function (options) {
	this.settings = $.extend({
		title: '温馨提示'
	}, options);
};



// ajaxForm提交
var NYForm = function () {
	this.settings = {
		formSelector: '#ajax-form',
		dlgId: '',
		action: null,
		dataType: 'json',
		beforeSubmit: null,
		callback: null
	};
};


var JYCsm = new NYCsm();