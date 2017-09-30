<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<div class="hd_nav ">
    <div class="center w960 clearfix">
      <div class="fl common-logo"> <a href="${webroot}/index.jsp" title="感控蓝蜻蜓" style="display:inline-block"> <img src='<c:choose><c:when test="${systemScope=='cdc7'}">${webroot}/resources/images/logo_lqt_crb.png</c:when><c:otherwise>${webroot}/resources/images/logo_lqt.png</c:otherwise></c:choose>' alt="感控蓝蜻蜓"  /> </a> </div>
     <%--  <div class="fr common-nav" id="h_nav">
        <h5 class="nav-title nav-chosen" id="h_home"><a href="${webroot}/index.jsp" >首页</a></h5>
        <h5 class="nav-title" id="h_customer"><a href="${webroot}/customer.jsp" >客户案例</a></h5>
        <h5 class="nav-title" id="h_help"><a href="${webroot}/help.jsp" >帮助中心</a></h5>
        <h5 class="nav-title<c:if test="${param.type=='login'}"> nav-chosen</c:if>" id="h_login"><a href="${webroot}/login.jsp" >登录</a></h5>
        <h5 class="nav-title" id="h_unitRegister"><a href="${webroot}/user/view/unitRegister.shtml" >注册</a></h5>
      </div> --%>
      <script type="text/javascript">
      $(function(){
    	  $("#h_nav h5").click(function(){
    		  $("#h_nav h5").removeClass("nav-chosen");
    		  $(this).addClass("nav-chosen");
    	  });
      });
      </script>
    </div>
  </div>