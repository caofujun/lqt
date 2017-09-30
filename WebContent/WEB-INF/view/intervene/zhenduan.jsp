<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>患者指标医嘱详情</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div class="patient_infor">
	<div class="patient_infor_button mt5 mr5"><a href="#" onclick="fxPatient.openPdca()" class="ico_no">PDCA</a><a href="#" class="ico_no" onclick="fxPatient.SendMessage()">干预会话</a><a href="#" onclick="fxPatient.openQuestion('${fxPatientZb.qid}')" class="ico_no">风险评估表</a></div>
		<table class="info_table">
		<tbody>
		<tr>
		<th>疾病类型：</th>
        <td nowrap="nowrap">
        	${zyxx.zyid}
        </td>
        <th>诊断来源：</th>
        <td style="min-width: 60px;">
        	${haPatient.patientId}
        </td>
        <th>诊断日期：</th>
        <td style="min-width: 60px;">
        	<fmt:formatDate value="${st002Zdxxb.diagnosisDate}" pattern="yyyy-MM-dd HH:mm:ss" />
        </td>
        <th>疾病名称：</th>
        <td nowrap="nowrap">
        	${st002Zdxxb.diagnosisName}
        </td>
		</tr>
		<tr>
		<th>诊断医生：</th>
        <td nowrap="nowrap">
        	${haPatient.sex}
        </td>
        <th>诊断科室：</th>
        <td style="min-width: 60px;">
        	
        </td>
		</tr>
		<tr>
           <th>PDCA：</th>
           <td nowrap="nowrap" colspan="3">
           		<c:forEach items="${zlPdcaPlansList}" var="pdca">
           		<a href="javascript:fxPatient.editPdca('${pdca.planName}','${pdca.puid}')">${pdca.planName}</a>,
           		</c:forEach>
           </td>
           <th>评估表：</th>
           <td style="min-width: 60px;">
           		<c:forEach items="${surveyRecordList}" var="surveyRecord">
           		<a href="javascript:fxPatient.editQuestion('${surveyRecord.title}','${surveyRecord.rid}')">${surveyRecord.title}</a>,
           		</c:forEach>
           </td>           
       </tr>         		
		</tbody>
		</table>		
	</div>
<script type="text/javascript">
var fxPatient = {
	query : function() {
		location.reload();
    },
    openPdca :function(){
		Comm.dialogGlobal({
	      	url:"${webroot}/zlPdcaPlans/f_view/toedit.shtml?pzId=${fxPatientZb.pzId}",
	          title: "引入PDCA",
	          width:780,
	          height:500,
	          type:"iframe",
	          parent:this
	      });
	},
	editPdca : function(title,id){
		parent.parent.parent.menuInfo.clickMenu(title,'/zlPdcaPlans/f_view/toedit.shtml?puid='+id,true);
	},
 	SendMessage :function(){
		Comm.dialogGlobal({
	    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid=${fxPatientZb.zyId}&msgType=1&pzId=${fxPatientZb.pzId}",
	        title: "干预会话",
	        width:870,
	        height:555,
	        type:"iframe"
	    });
	},
	editQuestion : function(title,rid){
		parent.parent.parent.menuInfo.clickMenu(title,'/qsSurveyRecord/f_view/toedit.shtml?id='+rid,true);
	},
	openQuestion : function(qid){
		if(qid!=''){
			var patientName = Comm.Str.encode('<c:out value="${cryxxb.patientName}" />');
			Comm.dialogGlobal({
				url:'${webroot}/qsSurveyRecord/f_view/toedit.shtml?patientId=${fxPatientZb.patientId}&qid='+qid+'&patientName='+patientName+'&depNo=${cryxxb.deptCode}&visitId=${fxPatientZb.pzId}&zyid=${fxPatientZb.zyId}',
		   		type: 'iframe',
		        title: "评估表",
		   		width: 750,
		   		height: 420,
		   	 	parent:this
			});	
		}
	}
}
</script>
</body>
</html>
