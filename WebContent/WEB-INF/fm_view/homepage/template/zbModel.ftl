<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>现患率直报</title>
	<style type="text/css">
	.m_search{ text-align: center; padding-top: 50px; overflow:hidden; margin:0px;vertical-align: middle;}
	</style>
</head>
	<body>	
		<div id="tb" class="m_search">
			<form id="zbform" method="post" action="${xhlZbURL}">
			<div style="padding-bottom: 20px; font-size: 24px; font-weight: 600;">现患率直报</div>
			用户名：<input type="text" id='userName' name="userName"/>
			密码：<input type="text" id='password' name="password"/>
			<input type="hidden" id="channel" name="channel" value="${channel}"/>
			<input type="hidden" id="dicOffice" name="dicOffice" value='${dicOffice}'/>
			<input type="hidden" id="patientMain" name="patientMain" value='${patientMain}'/>
			<input type="hidden" id="infectInfo" name="infectInfo" value='${infectInfo}'/>
			<input type="hidden" id="pathoInfo" name="pathoInfo" value='${pathoInfo}'/>
			<input type="hidden" id="antibInfo" name="antibInfo" value='${antibInfo}'/>
			<input type="button" id="id_submit" value="网络直报" onclick="zbXhl.reportXhl();">
			<span id="loading" style="display: none;"> 数据处理中...</span>
			</form>			
		</div>
<script type="text/javascript">
var zbXhl = {
	reportXhl: function(){
		if(document.getElementById('userName').value=='' || document.getElementById('password').value==''){
			alert("直报失败-用户名和密码不能为空");  
		} else {
			document.getElementById('zbform').submit();
			document.getElementById("id_submit").style.display="none";
			document.getElementById("loading").style.display="";
		}
	}
};
</script>
	</body>
</html>