<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<head>
</head>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
<body>
	<c:forEach items="${reportExplainList}" var="reportExplain">
	<div class="report_shows">	
		<div class="report_show_box">
			<div class="report_show_box_title">${reportExplain.formulaTitle}</div>
			<div class="report_show_box_text">
				${reportExplain.reportFormula}
			</div>		
		</div>
		<div class="report_show_box">
			<div class="report_show_box_title">统计规则</div>
			<div class="report_show_box_text">
				${reportExplain.reportRule}
			</div>		
		</div>
		<div class="report_show_box">
			<div class="report_show_box_title">说明</div>
			<div class="report_show_box_text">
				<p>${reportExplain.reportDesc}</p>
			</div>		
		</div>
	</div>
	</c:forEach>
</body>
</html>