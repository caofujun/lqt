<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,user-scalable=no;"/>
	   	<title>问卷提交-手机</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
        <script type="text/javascript" src="${webroot}/resources/js/functions/wjdc.js${version}"></script>
	</head>
	<body  class="qs_wap_body" >
  <div class="jqContent">
	<div class="surveyhead" style="border:0px;">
		<h1 style="position:relative;">
			<span>${title}</span>			
		</h1>		
		<div class="clear"></div>
		<div class="surveycontent"> 								
			<p style="width:90%; font-size:20px; margin:70px auto; text-align:center; padding-bottom:30px; border-bottom:1px solid #ccc;">您的答卷已提交，<br/>感谢您的参与！</p>			
		</div>    
	</div>
	<div class="Mobile_footer_button">深圳市宁远科技股份有限公司  就医160</div>
  </div>
</body>
</html>