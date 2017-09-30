<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
	<c:when test="${empty DBBRS}">
		<div class="emptyListTip">没有数据展示！</div>
	</c:when>
	<c:otherwise>
		<c:forEach items="${DBBRS}" var="db" >
			<div class="card">
				<div class="title" >
					<label>${db.zyid}/${db.bedNo}</label>
				</div>
				<div class="info" style="padding: 5px;">
					<label>
						<c:choose>
							<c:when test="${db.sex=='男'}">
								<img width="45px" height="45px" style="vertical-align: middle;" alt="男" src="${webroot}/resources/images/man-icon.png">
							</c:when>
							<c:otherwise>
								<img alt="女" width="45px" height="45px" style="vertical-align: middle;" src="${webroot}/resources/images/woman-icon.png">
							</c:otherwise>
						</c:choose>
					</label>
					<label class="pname">${db.patientName}</label>
					<label class="ages">${db.age}${db.ageUnit}</label>
					<div>
						<label>科室：${db.deptName}</label>
					</div>
					<div>
						<label>就诊日期：<fmt:formatDate value="${db.diagnosisDt}" type="both"/></label>
					</div>
					<div>
						<label>门诊诊断：${db.diagnosisName}</label>
					</div>
				</div>
				<div class="opts">
					<a id="report" class="optsButt" onclick="">上报</a>
					<a id="gy" class="optsButt" onclick="">干预</a>
					<span id="gy_tip" class="gyTip">12</span>
					<a id="remove" class="optsButt" onclick="">移除</a>
				</div>
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>
