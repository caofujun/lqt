<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,user-scalable=no;">
<title>过期页面</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
    <script type="text/javascript" src="${webroot}/resources/zclip/jquery.zclip.js${version}"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery-migrate-1.1.0.js"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery.jqprint-0.3.js"></script>
</head>

<body>
	<div class="error_page">
		<img  src="${webroot}/resources/images/error2.jpg" />
		<div class="error_right">
			<p>该问卷已过期，如需使用，请在问卷设置中修改问卷有效期!<span class="note"><b>错误信息：</b>问卷已过期</span></p>
			
			<a href="javascript:window.close()" class="error_button">关闭页面</a>
		</div>
	</div>
</body>
</html>