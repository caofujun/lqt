<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,user-scalable=no;">
<title>微信满意度调查-电话验证</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
    <script type="text/javascript" src="${webroot}/resources/zclip/jquery.zclip.js${version}"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery-migrate-1.1.0.js"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery.jqprint-0.3.js"></script>
</head>

<body  class="qs_wap_body" >
	<div class="user_login">
		<table>
			<tr><td>手机号码：</td></tr>
			<tr><td><input type="text" id="MobilePhone"/></td></tr>
			<tr><td height="30"><span id="errorMessage" class="red"></span></td></tr>			
			<tr><td><a href="#" class="grade_grey" onclick="isphone();">确&nbsp;&nbsp;定</a></td></tr>
		</table>		   
	</div>
<script>	
	function isphone(){
		 var num = document.getElementById('MobilePhone').value;
		 var x = document.getElementById("errorMessage");
		 var partten = /^1[3,5,8]\d{9}$/;
		 var telPartten=/^((\(\d{2,3}\))|(\d{3}\-?))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,8}(\-\d{1,4})?$/;
		 if(partten.test(num)||telPartten.test(num)){
		   x.innerHTML = "";
		   $.ajax({
	             url: '${webroot}/qsFlow/json/getIdByPhone.shtml',
	             type: 'post',
	             data: { 'mobilePhone':num},
	             dataType: 'json',
	             success : function(json) {
		             	if (json.result === 'success') {
		             		location="${webroot}/qsFlow/view/openFlow4Wap/"+json.data+".shtml?fid="+'${fId}';
						}else{
							x.innerHTML = "系统查不到该号码所对应的患者！";
						}
					}
	        });
		 }else{
		   x.innerHTML = "您输入的电话号码有误！";
		 }
    }	
</script>	
</body>
</html>