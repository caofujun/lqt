<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<title></title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div id="patient_frame_tabs" class="easyui-tabs" data-options="fit:true,plain:true" style="border-width: 0px;">
	       <div title="药敏抗菌药物"  style="overflow:hidden"   data-options="href:'${webroot}/antibiosisDrug/f_view/antibiosisDrugList.shtml'"></div>
		   <div title="预警抗菌药物"  style="overflow:hidden"  data-options="href:'${webroot}/antibiosisDrug/f_view/yjDrugList.shtml'"></div>
	 </div>
</div>
<script type="text/javascript">

$(document).ready(function () {
	 function refresh(index) {
		$('#patient_frame_tabs').tabs('select', index);
		var currTab = $('#patient_frame_tabs').tabs('getTab', index);
		var url = currTab.attr('href');
		currTab.panel('refresh', url);
	} 
	
	
 
});
</script>
</body>
</html>