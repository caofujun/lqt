<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>患者综合视图</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body style="padding:0px;" >
<c:set var="src" value="${param.src}${'?a=a'}" />
<c:forEach items="${formData}" var="paramMap">
	<c:set var="src">${src}&${paramMap.key}=${paramMap.value}</c:set>
</c:forEach>
<iframe id="patienView" src="${webroot}${src}" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
</body>
</html>