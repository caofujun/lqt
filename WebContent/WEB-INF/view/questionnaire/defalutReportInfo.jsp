<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   	<title>问卷调查报表</title>
    <%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div class="report_cont">
	<c:choose>
		<c:when test="${fn:length(reportList) <= 0 }">
			无调查记录....................
		</c:when>
		<c:otherwise>
			<c:forEach  items="${reportList}" var="reportTopic" varStatus="tstatus">
				<div class="report_cont_li">
					<div class="report_cont_title">${tstatus.count}、${reportTopic.title}<span class="blue ml10">[${reportTopic.ttypeName}]</span></div>
					<div class="report_t">
						<table  cellspacing="0" cellpadding="0" border="1" width="100%">
						<c:choose>
							<c:when test="${reportTopic.ttype == '3' }">
								<tr>
									<th width="20%">序号</th>
									<th width="80%">答案文本</th>
								</tr>
								<c:forEach  items="${reportTopic.qsReportOptionList}" var="reportOption" varStatus="ostatus">
									<tr>
										<td>${ostatus.count}</td>
										<td>${reportOption.optName}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<th width="30%">选项</th>
									<th width="10%">小计</th>
									<th width="60%">比例</th>
								</tr>
								<c:forEach  items="${reportTopic.qsReportOptionList}" var="reportOption" varStatus="ostatus">
									<tr>
										<td>${reportOption.optName}</td>
										<td>${reportOption.opCount}</td>
										<td>
											<div class="easyui-progressbar" data-options="value:${reportOption.opScale}" style="width:350px; margin:5px 10px;"></div>
										</td>
									</tr>
								</c:forEach>
								<tr>
									<th>本次有效填写人数</th>
									<th>${reportTopic.tidCount}</th>
									<th></th>
								</tr>
							</c:otherwise>
						</c:choose>
						</table>
						<c:if test="${reportTopic.ttype == '3' }">
							<div class="Record_sum">总共<span class="blue">${reportTopic.tidCount}</span>条记录</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</div>
	<c:if test="${fn:length(reportList) <0 }">
		<div class="footer_button"><input type="button" class="btn_oranger" onclick="parent.report.downReport();" value="下载调查报告（Word）" /></div>
	</c:if>
</body>
<script type="text/javascript" >
var reportInfo = {
		
};

$(document).ready(function(){
	
});
</script>
</html>
