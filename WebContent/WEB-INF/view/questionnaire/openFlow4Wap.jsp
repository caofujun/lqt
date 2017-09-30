<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,user-scalable=no;"/>
   	<title>${questInfo.title}</title>
    <%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body  class="qs_wap_body" style="margin:0 auto;" >
		<input type="hidden" name="fid" value="${fid}"/>
		<input type="hidden" name="emergency" value="${emergency}"/>
		<input type="hidden" name="guide" value="${guide}"/>
		<input type="hidden" name="triage" value="${triage}"/>
		<input type="hidden" name="zh_satisfaction" value="${zh_satisfaction}"/>
		<input type="hidden" name="nowStep" id='nowStep' value="${nowStep}"/>
	<div class="jumpPage">
		<div class="jumpPageContent" id="topic1" >	
			<div class="jumpPage_title">
				<h3 id="title">${title}</h3>			
			</div>
			<div class="jumpPage_options" >				
				<ul>
					<li><a href="${answer1url}">${answer1}</a></li>
					<li><a href="${answer2url}">${answer2}</a></li>
				</ul>								
			</div>
		</div>		
	</div>
</body>
</html>
