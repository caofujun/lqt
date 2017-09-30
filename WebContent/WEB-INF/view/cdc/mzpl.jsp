<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty MZBRS}">
	<div class="emptyListTip">没有数据展示！</div>
</c:if>
<c:forEach items="${MZBRS}" var="ms" >
	<div class="card">
		<div class="title" >
			<label>${ms.mzid}</label>
		</div>
		<div class="info" style="padding: 5px;">
			<label>
				<c:choose>
					<c:when test="${ms.sex=='男'}">
						<img width="45px" height="45px" style="vertical-align: middle;" alt="男" src="${webroot}/resources/images/man-icon.png">
					</c:when>
					<c:otherwise>
						<img alt="女" width="45px" height="45px" style="vertical-align: middle;" src="${webroot}/resources/images/woman-icon.png">
					</c:otherwise>
				</c:choose>
			</label>
			<label class="pname">${ms.patientName}</label>
			<label class="ages">${ms.age}${ms.ageUnit}</label>
			<div>
				<label>科室：${ms.deptName}</label>
			</div>
			<div>
				<label>就诊日期：<fmt:formatDate value="${ms.diagnosisDt}" type="both"/></label>
			</div>
			<div>
				<label>门诊诊断：${ms.diagnosisName}</label>
			</div>
		</div>
		<div class="opts">
				<a id="report" class="optsButt" href="javascript:parent.menuInfo.clickMenu('传染病上报','/cdc/f_visit/reportCardMZ.shtml?mzid=${ms.mzid}',true)">上报</a>
				<a id="gy" class="optsButt" style="padding-left: 10px;overflow: hidden;" onclick="">干预</a>
				<span id="gy_tip" class="gyTip">12</span>
		</div>
	</div>

</c:forEach>