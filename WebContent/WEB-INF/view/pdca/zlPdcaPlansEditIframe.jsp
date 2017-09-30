<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>pdca</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body style="padding:0px;" >
<iframe  width="100%" id="iframepage${puid}" height="100%" scrolling="no" marginheight="0" frameborder="0"  src="${webroot}/zlPdcaPlans/f_view/toedit.shtml?puid=${puid}&pzId=${pzId}"></iframe>
<script type="text/javascript" language="javascript">
 function iFrameHeight() {
     var ifm= document.getElementById("iframepage${puid}");
     var subWeb = document.frames ? document.frames["iframepage${puid}"].document :ifm.contentDocument;
     if(ifm != null && subWeb != null) {
     	ifm.height = subWeb.body.scrollHeight;
     	parent.iFrameHeight();
     }
 }
</script> 

</body>
</html>