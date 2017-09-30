<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>患者指标诊断详情</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div class="patient_infor">		
		<span>风险名称：${qsSurveyRecord.zbName}</span>
	</div>
<div id="patient_frame_tabs" style="height:450px">  
       <div title="${qsSurveyRecord.title}" style="overflow:hidden" style="padding:10px" data-options="href:'${webroot}/qsSurveyRecord/f_view/toeditIframe.shtml?id=${qsSurveyRecord.rid}'">
      </div>
</div>
<script type="text/javascript">
$(document).ready(function () {
	//监听右键事件，创建右键菜单
    $('#patient_frame_tabs').tabs({});

});
</script> 
<script type="text/javascript">
var fxPatient = {
	query : function() {
		location.reload();
    }
}
</script>
</body>
</html>