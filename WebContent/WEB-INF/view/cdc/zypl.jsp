<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<c:choose>
	<c:when test="${empty ZYBRS}">
		<div class="emptyListTip">没有数据展示！</div>
	</c:when>
	<c:otherwise>
		<c:forEach items="${ZYBRS}" var="zy" >
			<div class="card">
				<div class="title" >
					<label>${zy.zyid}/${zy.bedNo}</label>
				</div>
				<div class="info" style="padding: 5px;">
					<label>
						<c:choose>
							<c:when test="${zy.sex=='男'}">
								<img width="45px" height="45px" style="vertical-align: middle;" alt="男" src="${webroot}/resources/images/man-icon.png">
							</c:when>
							<c:otherwise>
								<img alt="女" width="45px" height="45px" style="vertical-align: middle;" src="${webroot}/resources/images/woman-icon.png">
							</c:otherwise>
						</c:choose>
					</label>
					<label class="pname">${zy.patientName}</label>
					<label class="ages">${zy.age}${zy.ageUnit}</label>
					<div>
						<label>科室：${zy.deptName}</label>
					</div>
					<div>
						<label>就诊日期：<fmt:formatDate value="${zy.diagnosisDt}" type="both"/></label>
					</div>
					<div>
						<label>门诊诊断：${zy.diagnosisName}</label>
					</div>
				</div>
				<div class="opts">
					<a id="report" class="optsButt" onclick="">上报</a>
					<a id="gy" class="optsButt" onclick="">干预</a>
					<div id="gy_tip" class="gyTip"><span>12</span></div>
				</div>
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>