<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
<div class="f_download">
		<div class="download_ie"><a href="${IEflashPlayerUrl}" target="_blank">Flash下载（IE）</a></div>
		<div class="download_Chrome"><a href="${ChromeflashPlayerUrl}" target="_blank">Flash下载（Chrome）</a></div>
</div>
