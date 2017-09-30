<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<title>蓝蜻蜓医院感染实时监控平台</title>

</head> 
<body> 
<script type="text/javascript"> 
function callExe() 
{ 
	new ActiveXObject("Wscript.Shell").run("E:/NSHIS/住院医师工作站_质控/dzbl3.exe 9999-248083cAg7Ag1#许昌市中心医院*${param.patient_id}-${param.visit_id} <blll>"); 
}
$(function() {
	callExe();
	parent.menuInfo.closeMenu('原始病程');
});
</script>   
</body>
</html> 