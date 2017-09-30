<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${webroot}/resources/js/nyTellActive-1.0.1.js"></script>
<script type="text/javascript">
var nyTeleConfig = {enableRecord:${saveRecord},unitId:user.unitId,userId:user.userId,url:voiceUploadHttpUrl,privateKey:'${uploadPrivateKey}'};
var iframeId="";
//电话事件
var nyTellEvent = {
	//电话控件校验
	tellVerify : {
		//控件有效性
		ocxValid : function(){
			if(JYTopTele.tellControl.valid){
				$.messager.alert('提示', '插件正常工作');
				return;
			}
			$.messager.alert('提示', '插件异常');
		}
	},
	//取得电话控件
	gettellControl : function(){
		return document.getElementById('nyTellDll');
	},
	//页面注册事件
	addEvent : function(obj, name, func){
		if (window.addEventListener) {
			//非ie浏览器
			obj.addEventListener(name, func, false); 
		} else {
			//ie浏览器
			obj.attachEvent("on"+name, func);
		};
	},
	//初始化所有电话事件
	initAllTellEvent : function(){
		//设备连接事件
		nyTellEvent.addEvent(nyTellEvent.gettellControl(),'OnDeviceConnect',nyTellEvent.OnDeviceConnectFunc);
		//设备断开事件
		nyTellEvent.addEvent(nyTellEvent.gettellControl(),'OnDeviceDisconnect',nyTellEvent.OnDeviceDisconnectFunc);
		//电话呼出
		nyTellEvent.addEvent(nyTellEvent.gettellControl(),'OnCallOut',nyTellEvent.OnCallOutFunc);
		//电话来电
		nyTellEvent.addEvent(nyTellEvent.gettellControl(),'OnCallIn',nyTellEvent.OnCallInFunc);
		//电话挂起
		nyTellEvent.addEvent(nyTellEvent.gettellControl(),'OnHangUp',nyTellEvent.OnHangUpFunc);
	},
	//设备连接处理
	OnDeviceConnectFunc : function(number){
		//$.messager.show({ title: '提示', msg: '设备连接事件：'+number});
	},
	//设备断开处理
	OnDeviceDisconnectFunc : function(number){
		//$.messager.show({ title: '提示', msg: '设备断开事件：'+number});
	},
	//电话呼出处理
	OnCallOutFunc : function(teleNo){
		//alert('呼叫事件：'+teleNo);
	},
	//电话来电处理
	OnCallInFunc : function(teleNo){
		//电话处理
		$.ajax({
			 type: "POST",
			 url: "${webroot}/callIn/f_json/panduan.shtml",
			 data:{'telPhone':teleNo},
			 dataType: 'json',
			 success: function(data){
				 if(data.result === 'success') {
					 parent.menuInfo.clickMenu('客户来电处理','/foFollowCase/f_view/toDetailNew.shtml?pid=' + data.data.pid + '&tellType=answer',true);
				 } else if (data.result === 'success_multi') {
					 parent.menuInfo.clickMenu('来电信息页','/callIn/f_view/callInList.shtml?telPhone=' + teleNo,true);
				 } else if(data.result === 'success_not'){
					 var dialogObj = Comm.dialog({
					        url: '${webroot}/callIn/f_view/index.shtml?telPhone='+teleNo,
					        title: '客户来电处理',
					        type: 'iframe',
					        modal:'false',//打开父窗口操作
					        width: 320,
					        height:170
					 });
					 iframeId=dialogObj.iframeId;
				 }		
			}
		});
	},
	//挂起事件
	OnHangUpFunc : function(teleNo,callType,startTime,endTime,duration){
		var cachObject = Comm.getObjectCache();
		var optionDiv = cachObject.getCallDiv();
		//是否为新患者咨询
		var tellAdd = optionDiv.attr('tellAdd');
		/*if(tellAdd == 'answer' ){
			//内部业务处理
			nyTellEvent.customHongUp(teleNo,callType,startTime,endTime,duration);
			return;
		}*/
		nyTellEvent.commonHongUp(teleNo,callType,startTime,endTime,duration,cachObject);
	},
	//个性化电话挂起处理
	customHongUp : function(teleNo,callType,startTime,endTime,duration){
		if (teleNo.length == 13) {
			teleNo = teleNo.substring(2, teleNo.length); 
		} else if (teleNo.length == 12) {
			teleNo = teleNo.substring(1, teleNo.length); 
		}
		if (callType == "CallOut") {
			callType = "2";
		} else {
			callType = "1";
		}
		$('#'+iframeId)[0].contentWindow.callPhone.enableDiv(teleNo,callType,startTime,endTime,duration);
	},
	//通用挂起处理
	commonHongUp : function(teleNo,callType,startTime,endTime,duration,cachObject){
		//电话按钮变为拨号
		var optionDiv = cachObject.getCallDiv();
		optionDiv.attr("tellType","tell");
		optionDiv.val("拨号");
		optionDiv.removeClass("red");
		//启用上一页下一页
		cachObject.enableDiv();
		//不上传录音
		if(!JYTopTele.enableRecord) {
			return;
		}
		//判断录音时间长度
		if (duration == 0 || startTime == endTime || duration < 5) {
			return;
		}
		try {
			//上传电话录音文件
			var fileUrl = NYTellOptions.uploadCallFile();
			if(fileUrl == ""){
				return;
			}
			if (teleNo.length == 13) {
				teleNo = teleNo.substring(2, teleNo.length); 
			} else if (teleNo.length == 12) {
				teleNo = teleNo.substring(1, teleNo.length); 
			}
			if (callType == "CallOut") {
				callType = "2";
			} else {
				callType = "1";
			}
			//记录电话记录
			var recordData = {'startTime': startTime, 'endTime': endTime, 'duration': duration, 'fileName': fileUrl, 
					'callType': callType, 'phone': teleNo,'patientId':Comm.phone.patientId,'visitName':Comm.phone.patientName};
			//保存电话记录
			NYTellOptions.saveCallRecord(recordData);
		} catch (e) {
			$.messager.progress('close');
		}
	}
}; 

/** 初始化电话控件. */
function inittellControl(){
	//电话控件
	JYTopTele = new NYTele({tellControl: document.getElementById('nyTellDll') });
	nyTellEvent.initAllTellEvent();
}

/*  function initTell(){
	$('#tellDll').append('<object id="nyTellDll" type="application/x-nyteleactivex" style="width:0;height:0;" ><param name="install-url" value="${pluginDownAddrr}" /> </object>');
} */ 

</script>
<!-- 电话控件 param name="install-url" value="${pluginDownAddrr}" <param name="onload" value="inittellControl" /> -->
<object id="nyTellDll" type="application/x-nyteleactivex" style="width:0;height:0;" >
<param name="install-url" value="${pluginDownAddrr}" /> 
</object> 
<!-- <div id="tellDll"></div> -->
<script type="text/javascript">
<!-- setTimeout("inittellControl()",5000);-->
</script>
