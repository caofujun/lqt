<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>网站地图</title>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
</head>

<body>	
	<div class="siteMap">
		<ul>
			<c:forEach items="${menuList}" var="menu" varStatus="st">
			<li class="siteMap_Class <c:if test="${st.index+1 eq fn:length(menuList)}">last_li</c:if>">
				<div class="siteMap_menu <c:if test="${menu.joinReport!=null and menu.joinReport!=''}">report_bg</c:if>">
					<div class="siteMap_menu_n">
						<span class="siteMap_menu_n_num">${st.index+1}</span>
						<span class="siteMap_menu_n_text">${menu.menuName}</span>
						<span class="siteMap_menu_n_r"></span>
					</div>
					<ul class="siteMap_menu_li">
					 <c:forEach items="${menu.children}" var="acMenu">	
						<li>
							<ul class="siteMap_menu_sub_li">
								<c:forEach items="${acMenu.children}" var="mu" varStatus="status">	
								<li <c:if test="${status.index+1 eq fn:length(acMenu.children)}"> class="li_last"</c:if>><a href="javascript:parent.menuInfo.clickMenu('${mu.menuName}','${mu.destUrl}',true)">${mu.menuName}</a></li>
								</c:forEach>
							</ul>
						</li>
					</c:forEach> 
					</ul>
					<div class="clear"></div>
				</div>
				<c:if test="${menu.joinReport!=null and menu.joinReport!=''}">
				 <div class="siteMap_report">
					<div class="siteMap_report_n">
						<span class="siteMap_report_n_text">监测报表</span>
					</div>
					 <ul class="siteMap_menu_li">
					 <c:forEach items="${menu.reportList}" var="report">						
						<li>
							<ul class="siteMap_menu_sub_li">
								 <c:forEach items="${report.children}" var="child" varStatus="status">
								<li <c:if test="${status.index+1 eq fn:length(report.children)}"> class="li_last"</c:if>><a href="javascript:parent.menuInfo.clickMenu('${child.menuName}','${child.destUrl}',true)" >${child.menuName}</a></li>
								</c:forEach> 
							</ul>
						</li>					
					</c:forEach>
					</ul> 
					<div class="clear"></div>
				</div> 
				</c:if>
			</li>
			</c:forEach>
		</ul>
	</div>	
</body>
</html>
    