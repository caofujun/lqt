<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   	<title>问卷发布</title>
    <%@ include file="/WEB-INF/view/core/include.jsp"%>
    <script type="text/javascript" src="${webroot}/resources/zclip/jquery.zclip.js${version}"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery-migrate-1.1.0.js"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery.jqprint-0.3.js"></script>
</head>

<body  style="margin:20px 10px;" >

    <input type="hidden" value="${urlDemo}" id="url"/>
	<div class="quest_Share">		
		<div class="quest_Share_title">二维码扫一扫</div>
		<div class="quest_Share_cont">
			<div class="quest_Share_cont_left">
				<img  id="_qcCode" src="${webroot}/surveyRelease/f_view/loginQcCode.shtml" style="width:230px;height:230px;"/>			
			</div>
			<div class="quest_Share_cont_right">
				<div class="quest_Share_cont_right_title">住院患者问卷调查</div>
				<p>微信扫描左侧二维码，输入账户密码登录到系统，选择问卷后进入患者列表页，选择需要进行问卷调查的住院患者，填写问卷后提交即可！</p>		
			</div>
			<div class="clear"></div>		
		</div>
	</div>
	<div class="quest_Share">
		<div class="quest_Share_title">住院患者问卷调查流程</div>
		<div class="quest_Share_cont">
			<div class="quest_Share_cont_lc"><img src="${webroot}/resources/images_org/img_yemianfabuliuchen.jpg" /></div>
		</div>
	</div>
	
	<script type="text/javascript">
	
	var urlView="";
	
	function view(){
		window.open(urlView,"_blank");
	}
	
	$(document).ready(function(){
		var url = $("#url").val();
		urlView=getRootPath_web()+url;
	});
	
      
</script>
	
	
</body>


</html>
