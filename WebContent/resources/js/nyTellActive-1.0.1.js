
/**
*宁远电话控件
*v@1.0.1
*/
var NYTele = function (options) {
	//电话配置信息加入电话控件中
	$.extend(this, nyTeleConfig, {
		tellControl: null
	}, options || {});
	//判断控件页面对象否正常
	if(!this.tellControl){
		$.messager.alert('提示', '电话控件不存在！');
		return;
	}
	if(typeof(this.tellControl.valid) == "undefined"){
		Comm.phone.newDownload();
		return;
	}
	if (!this.tellControl.valid) {
		$.messager.alert('提示', '电话控件无法正常工作，请检查控件');
		return;
	}
};

//实际电话控件
var JYTopTele = null;

/** 电话控件操作集合. */
var NYTellOptions = {
	//上传电话录音文件
	uploadCallFile : function(){
		//文件准备上传
		$.messager.progress({
			text : '文件上传中，请稍等....',
			interval : 60000
		});
		try{
			//设置用户参数
			NYTellOptions.setUserParams();
			//调用控件上传文件接口
			var result = JYTopTele.tellControl.UploadRecord();
			result = $.parseJSON(result);
			if(result.status==='1'){
				return result.fileUrl;
			}else{
				var _tipMsg = undefined;
				if(result.status == 2) {
					_tipMsg = '权限不够,校验有误';
				} else if(result.status == 3) {
					_tipMsg = '参数缺失或者异常';
				} else if(result.status == 4) {
					_tipMsg = '超时请求';
				} else if(result.status == 5) {
					_tipMsg = '上传异常';
				} else {
					_tipMsg = '上传失败';
				}
				$.messager.show({ title: '提示', msg: "上传文件失败,失败原因:"+_tipMsg+"" });
				$.messager.progress('close');
				return "";
			}
		}catch(e){
			$.messager.show({ title: '提示', msg: "上传文件失败,失败原因:"+e.message});
			$.messager.progress('close');
			return "";
		}
	},
	//保存电话录音记录
	saveCallRecord : function(recordData){
		$.ajax({
	        url: webroot+'/foTelrecord/f_json/save.shtml',
	        type: 'post',
	        data: recordData,
	        dataType: 'json',
	        success : function(data) {
	        	$.messager.progress('close');
				if(data.result === 'success') {
	                $.messager.show({ title: '提示', msg: '呼叫完成，录音已上传！' });
		    	}
		    	else $.messager.show({ title: '提示', msg: '上传录音失败！' });
			},
			error : function(){
				$.messager.progress('close');
				$.messager.show({ title: '提示', msg: '上传录音失败！' });
			}
	    });
	},
	//设置用户参数
	setUserParams : function(){
		//调用控件设置参数接口
		JYTopTele.tellControl.SetUserParams(JYTopTele.unitId,JYTopTele.userId,JYTopTele.url,JYTopTele.privateKey);
	},
	//拨号
	startDial : function(tellNo,tellButtonObject){
		if(!tellNo){
			$.messager.alert('提示', '电话号码不能为空！');
			return;
		}
		if (!JYTopTele.tellControl.valid) {
			$.messager.show({ title: '提示', msg: '电话控件无法正常工作，请检查控件'});
			return;
		}
		//电话按钮变为挂断
		tellButtonObject.attr("tellType","hangup");
		tellButtonObject.val("挂断");
		tellButtonObject.addClass("red");
		//调用控件拨号接口
		var result = JYTopTele.tellControl.StartDial(tellNo,JYTopTele.enableRecord);
		if(!result){
			$.messager.show({ title: '提示', msg: '请检查电话控件是否连接!' });
			tellButtonObject.attr("tellType","tell");
			tellButtonObject.val("拨号");
			var cachObject = Comm.getObjectCache();
			//启用上一页下一页
			cachObject.enableDiv();
			tellButtonObject.removeClass("red");
		}
	},
	//挂机
	hangUpTell : function(tellButtonObject){
		//电话按钮变为拨号
		tellButtonObject.attr("tellType","tell");
		tellButtonObject.val("拨号");
		tellButtonObject.removeClass("red");
		//调用控件挂机或挂断接口try{
		var result = JYTopTele.tellControl.HangUpCtrl();
		if(!result){
			$.messager.show({ title: '提示', msg: '请检查电话控件是否连接!！' });
			tellButtonObject.attr("tellType","hangup");
			tellButtonObject.val("挂断");
			tellButtonObject.addClass("red");
		}
	},
	//接听
	offHookTell : function(tellButtonObject){
		//电话按钮变为挂断
		tellButtonObject.attr("tellType","hangup");
		tellButtonObject.val("挂断");
		tellButtonObject.addClass("red");
		//调用控件摘机或接听接口
		var result = JYTopTele.tellControl.OffHookCtrl();
		if(!result){
			$.messager.show({ title: '提示', msg: '请检查电话控件是否连接!！' });
			tellButtonObject.attr("tellType","answer");
			tellButtonObject.val("接听");
			tellButtonObject.removeClass("red");
		}
	},
	//拨号|接听|挂机操作
	tellHandle : function(tellButtonObject,tellNo){
		//检查空是否需要下载
		if(null == JYTopTele){
			Comm.phone.newDownload();
			return;
		}
		//电话类型
		var tellType = tellButtonObject.attr("tellType");
		switch (tellType){
			//拨号
			case "tell" : NYTellOptions.startDial(tellNo,tellButtonObject);break;
			//挂机
			case "hangup" : NYTellOptions.hangUpTell(tellButtonObject);break;
			//接听
			case "answer" : NYTellOptions.offHookTell(tellButtonObject);break;
			default : $.messager.alert('电话处理存在问题，请联系管理员！');
		};
	}
};