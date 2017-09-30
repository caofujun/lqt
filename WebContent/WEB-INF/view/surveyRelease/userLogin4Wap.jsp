<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,user-scalable=no;"/>
   	<title>用户登录</title>
    <%@ include file="/WEB-INF/view/core/include.jsp"%>
    <script type="text/javascript" src="${webroot}/resources/zclip/jquery.zclip.js${version}"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery-migrate-1.1.0.js"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery.jqprint-0.3.js"></script>
</head>
<% String flag = (String)session.getAttribute("flag")==null?"":(String)session.getAttribute("flag");
 
String username = "";
String password = "";
try{ 
    Cookie[] cookies=request.getCookies(); 
    if(cookies!=null){ 
    for(int i=0;i<cookies.length;i++){ 
        if(cookies[i].getName().equals("cookie_user")){ 
        String value =  cookies[i].getValue();
        if(value!=null&&!"".equals(value)){
        	username=cookies[i].getValue().split("%-%")[0]; 
            if(cookies[i].getValue().split("%-%")[1]!=null && !cookies[i].getValue().split("%-%")[1].equals("null")){
            	password=cookies[i].getValue().split("%-%")[1]; 
            }
                     
           }
           } 
       request.setAttribute("username",username); 
     	System.out.println("___________"+username);
       request.setAttribute("passward",password); 
   } 
   } 
}catch(Exception e){ 
   e.printStackTrace(); 
} 
%> 
<body  class="qs_wap_body">
	<div class="user_login">
		<table>
			<tr><td>CSM用户名：</td></tr>
			<tr><td><input type="text" id='username' value="<%=username %>"/></td></tr>
			<tr><td>登录密码：</td></tr>
			<tr><td><input type="password" id='password' value="<%=password %>"/></td></tr>
			<tr><td><div class="login_error"><span class="red" style="display:none;" id="msgPanel">账号或密码错误，请重新输入</span></div></td></tr>
			<tr><td><input type="checkbox" id="check"/>保存用户登录信息</td></tr>
			<tr><td><a href="#" class="grade_grey"  onclick="login()">确&nbsp;&nbsp;定</a></td></tr>
		</table>		   
	</div>	
	
	<script type="text/javascript">
	function login(){
		var _password=$("#password").val();
		var _username=$("#username").val();
		var flag=0;  
		if(document.getElementById("check").checked){
			flag=1;
		}
		$.ajax({
             url: '${webroot}/surveyRelease/json/login.shtml',
             type: 'post',
             data: { 'password': _password,'username':_username,'flag':flag},
             dataType: 'json',
             success : function(json) {
	             	if (json.result === 'success') {
	             		$('#msgPanel').css('display', 'none');
	             		location = '${webroot}/surveyRelease/f_view/selectQnaire4Wap.shtml';	
					}else{
	             		$('#msgPanel').css('display', 'block');
					}
			 }
        });
	}
	
	</script>
</body>
</html>
