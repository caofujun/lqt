<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="mdrOptsDetailPanel" class="easyui-panel" data-options="border:false" style="width:300px;height:auto;">
			<table class="list_table">
				<thead>
					<tr >
						<th width="100px">简要</th>
						<th>操作人</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty settingInfo}">
						<tr>
							<td style="text-align: center;">
								<c:choose>
									<c:when test="${!empty settingInfo.infectTypeName}">设为${settingInfo.infectTypeName}</c:when>
									<c:when test="${settingInfo.infectTypeId==1}">设为院感</c:when>
									<c:when test="${settingInfo.infectTypeId==2}">设为社感</c:when>
									<c:when test="${settingInfo.infectTypeId==3}">设为定植</c:when>
									<c:when test="${settingInfo.infectTypeId==4}">设为污染</c:when>
								</c:choose>
							</td>
							<td style="text-align: center;"><fmt:formatDate value="${settingInfo.changeDt}" pattern="yyyy-MM-dd" />  | ${settingInfo.changeUserid}</td>
						</tr>
					</c:if>
					<c:if test="${!empty reportInfo}">
						<tr>
							<td style="text-align: center;">院感报卡</td>
							<td style="text-align: center;"><fmt:formatDate value="${reportInfo.reportAt}" pattern="yyyy-MM-dd" /> | ${reportInfo.reportDrName}</td>
						</tr>
					</c:if>
				</tbody>
				
			</table>
		</div>
	</body>
</html>
