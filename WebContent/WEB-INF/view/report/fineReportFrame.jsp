<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<style>
	body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;overflow: hidden;}
</style>
<head>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
</head>
<body class="easyui-layout">	
	<c:set var="src" value="${reportUrl}${reportFile}.cpt&unitId=${unitId}&userType=${account.userType}&doctorId=${account.doctorId}&scopeInfo=${account.scopeInfo}&dataScope=${account.dataScope}&urlPrefix=${nisUrl}" />
	<c:forEach items="${formData}" var="paramMap">
		<c:set var="src">${src}&${paramMap.key}=${paramMap.value}</c:set>
	</c:forEach>
	<iframe width="100%" id="iframe_box" style="height: 100%;" scrolling="no" frameborder="0" src="${src}"></iframe>
</body>
</html>