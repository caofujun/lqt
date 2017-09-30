<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%--   <div class="easyui-tabs" style="height:350px">
<c:forEach items="${zlPdcaPlansList}" var="zlPdcaPlans">
	<div title="${zlPdcaPlans.planName}" style="padding:10px;"  >
		<iframe  width="100%"  height="290px" marginheight="0" frameborder="0"  src="${webroot}/zlPdcaPlans/f_view/toedit.shtml?puid=${zlPdcaPlans.puid}&pzId=${fxPatientZb.pzId}"></iframe>
	</div>
</c:forEach>
</div>  --%> 

<div id="patient_frame_tabs">  
     <c:forEach items="${zlPdcaPlansList}" var="zlPdcaPlans">
      <div title="${zlPdcaPlans.planName}" style="overflow:hidden" style="padding:10px" data-options="href:'${webroot}/zlPdcaPlans/f_view/toeditIframe.shtml?puid=${zlPdcaPlans.puid}&pzId=${fxPatientZb.pzId}'">
      </div>
      </c:forEach>
      <c:forEach items="${surveyRecordList}" var="surveyRecord">
       <div title="${surveyRecord.title}" style="overflow:hidden" style="padding:10px" data-options="href:'${webroot}/qsSurveyRecord/f_view/toeditIframe.shtml?id=${surveyRecord.rid}'">
      </div>
      </c:forEach>
</div>
<script type="text/javascript">
$(document).ready(function () {
	//监听右键事件，创建右键菜单
    $('#patient_frame_tabs').tabs({
    	onSelect:function(title,index){
    		//parent.iFrameHeight();
    	}
    });
});
</script> 