<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">

var lqtMessage = {
	SetConfig: function (AUserID,AUserName,AURL,AUpdateURL,AGKDeptURL){
		try{
			TestOcx.SetConfig(AUserID,AUserName,AURL,AUpdateURL,AGKDeptURL);
		}catch(e){
			if(imcIsShow==0){
				if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
					 parent.Comm.dialog({title: '温馨提示',
						content:['<div style="padding: 20px;"><p>消息功能需要浏览器控件支持，请下载安装</p>',
						         '<p style="text-align: center;"><input type="button" class="btn" value="下载" onclick="location = \'',nisMsgOcxUrl,'/gklqt_nis.exe\'"></p></div>'].join(''),
						width:300, height:160
					}); 
					}else if (navigator.userAgent.indexOf('Firefox') >= 0){
					    
					}else if (navigator.userAgent.indexOf('Opera') >= 0){
					    
					}else if (navigator.userAgent.indexOf('Chrome') >= 0){
					    
					}else{
						 parent.Comm.dialog({title: '温馨提示',
								content:['<div style="padding: 20px;"><p>消息功能需要浏览器控件支持，请下载安装</p>',
								         '<p style="text-align: center;"><input type="button" class="btn" value="下载" onclick="location = \'',nisMsgOcxUrl,'/gklqt_nis.exe\'"></p></div>'].join(''),
								width:300, height:160
						}); 
					}
			}
		}
    }
}
</script>
<OBJECT  id="TestOcx"  
	  classid="clsid:E94142D9-4680-46F9-8297-E9BB8F74E4FD"
	style="width:0;height:0;"
>
</OBJECT>
<script type="text/javascript">
 function initMessageControl(){
	 lqtMessage.SetConfig(user.username,user.realname,'${nisMsgUrl}{"userId":"'+user.username+'"}','${nisMsgOcxUrl}UpdateList.ini','${nisHttpUrl}/nyMessageTheme/f_view/toedit.shtml?deptId=${nisGkDeptid}&username='+user.username+'&password='+user.password+'&token=${nisMsgToken}')
 }
 setTimeout("initMessageControl()",2000);
</script>