<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>选择手术</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div class="easyui-tabs" data-options="fit:true,plain:true">
		<div title="患者手术记录" style="overflow:hidden" style="padding:10px" data-options="href:'${webroot}/st005Ssxxb/f_view/toSelectList.shtml?patientId=${param.patientId}&dialogId=${param.dialogId}&operAt=${param.operAt}'">
        </div>
        <div title="手术字典" style="overflow:hidden" style="padding:10px" data-options="href:'${webroot}/icd9/f_view/toSelectList.shtml?patientId=${param.patientId}&dialogId=${param.dialogId}&operAt=${param.operAt}'" >
        </div>
        <div title="手工填写" style="overflow:hidden" style="padding:10px">
        	
        </div>
	</div>
</body>
</html>