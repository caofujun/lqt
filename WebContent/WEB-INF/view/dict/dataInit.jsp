<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<title>匹配初始化</title>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css_org/comm.css" />
<link rel="stylesheet" type="text/css" href="${webroot}/resources/easyui-1.4.3/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/DatePicker/skin/WdatePicker.css"/>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/customer/jquery.mCustomScrollbar.css" />
<link href="${webroot}/resources/css_org/layout.css" rel="stylesheet"  type="text/css" />
<link  href="${webroot}/resources/css_org/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${webroot}/resources/js/jquery.min.js"></script>
</head>

<body style="height:100%; margin:0 auto;">
	
	<div class="Init">
	
		<div  class="Init_note">下面将会引导你逐步完成数据初始化的工作，点击卡片进操作界面</div>
		
	 <c:forEach items="${sysDictList}" varStatus="i" var="item" > 
	      
	     <!--  单双行 -->
	      <c:if test="${(i.index+1) % 4==1}">
	        <c:choose>
	             <c:when test="${( (i.index+4)/4) % 2  == 0 }">
                  <div class="Init_Double">
	              </c:when>
            <c:otherwise>
                  <div class="Init_single">
            </c:otherwise>
	        </c:choose>
	      </c:if>
	      
	      
	       <div class="Init_box" onclick="parent.menuInfo.clickMenu('${item.dictName}','${item.extParam2}',true);">		
					<div class="Init_box_title">${item.dictName}<span>${item.remark}</span></div>
					<img  class="Init_box_img" src="${webroot}${item.extParam1}"/>
					<div class="Init_box_b">
						<!-- <div class="Init_box_b_l">完成度<span class="red">10</span>/30</div> -->
						<div class="Init_box_b_r">最后更新:<fmt:formatDate value="${item.updateTime }" pattern="yyyy-MM-dd HH:mm"/></div>				
					</div>	
		    </div>
	     
	      	    	      
	      <!--  判断单双行元素 -->
	      
	      
	          <!-- 去掉最后一个箭头 -->
	      <c:if test="${(i.index+1)<fn:length(sysDictList) }">
	      
	      <c:choose>
	        <c:when test="${i.index+1 % 4 !=0 and (i.index+1) % 4!=0  }">
	          <c:choose>
	            <c:when test="${(((fn:substring(((i.index+1)/4),0,fn:indexOf(((i.index+1)/4), '.')))+1)%2 !=0)}">
	                 
	              <!--   单行箭头  -->            
	               <div class="Init_arrow_r"><span class="Init_arrow_img"></span></div>
	               
	            </c:when>
	            <c:otherwise>
	            
	             <!-- 双行箭头 -->
	            <div class="Init_arrow_l"><span class="Init_arrow_img"></span></div> 
	            
	            </c:otherwise>
	          </c:choose>
	      </c:when>
	      </c:choose>
	      </c:if>
	        
	       <!-- 行尾 --> 
	      <c:if test="${(i.index+1)% 4==0}">
	      <div class="Init_arrow"><div class="Init_arrow_d"><span class="Init_arrow_img"></span></div></div></div>
	      </c:if>
	        
     </c:forEach>  
	</div>
	
	
</body>
</html>