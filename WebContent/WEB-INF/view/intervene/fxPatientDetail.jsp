<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>危险因素详情</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/layer/skin/layer.css?${version}">
<script type="text/javascript" src="${webroot}/resources/layer/layer.js"></script>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css_org/fishBone.css?${version}">
<script type="text/javascript" src="${webroot}/resources/js/fishBone.js?${version}"></script>
<script type="text/javascript" src="${webroot}/resources/js/jquery.SuperSlide.2.1.1.js"></script>
</head>
<body style="padding:5px;" >
	<div class="patient_infor" style="margin: 0px 0px 7px 0px;">
	<table class="info_table">
	<tbody>  
	       <tr>
	           <th><c:if test="${follow=='1'}"><a href="Javascript:foPatient()" id="followPatient" title="取消关注" class="img_satr"></a></c:if><c:if test="${follow!='1'}"><a href="Javascript:foPatient()" id="followPatient" title="关注" class="img_satr_gray"></a></c:if>${patientZyTitle}：</th>
	           <td nowrap="nowrap">
	           		<a href="#" style="font-weight: bold;text-decoration: underline;" onclick="patientRecords('${zyxx.zyid}')">${patientZyValue == 'patientId'?zyxx.patientId:zyxx.zyid}</a>
	           </td>	      
	           <th>姓名：</th>
	           <td nowrap="nowrap">
	           		${haPatient.patientName}
	           </td>
	           <th>性别：</th>
	           <td nowrap="nowrap">
	           	${haPatient.sex}
	           </td>
	       	<th>年龄：</th>
	           <td>
	           	${zyxx.age}${zyxx.ageUnit}
	           </td>
	       </tr>
	       <tr>	      
	           <th>住院天数：</th>
	           <td>
	           	${zyxx.inDays}天
	           </td>
	           <th>入院日期：</th>
	           <td>
	           	<fmt:formatDate value="${zyxx.inHospAt}" pattern="yyyy-MM-dd HH:mm" />
	           </td>
	           <th>出院日期：</th>
	           <td>
	           <fmt:formatDate value="${zyxx.outAt}" pattern="yyyy-MM-dd HH:mm" />
	           </td>
	           <th>管床医生：</th>
	           <td colspan="3">
	           	<a href="#" style="font-weight: bold;text-decoration: underline;" onClick="sendMessage()">${zyxx.chargeDrName}</a>
	            </td>
	       </tr>
	    </tbody>
	</table>
	</div>
	<div class="graphic">
		<span><b class="round red"></b>可干预危险因素</span>
		<span><b class="round green"></b>已干预危险因素</span>
		<span><b class="round gray"></b>不可干预危险因素</span>
		<span><b class="round orange"></b>医院感染</span>
	</div>
<%@ include file="/WEB-INF/view/intervene/timeline_yugu.jsp"%>
<script type="text/javascript">
function patientRecords(zyid){
	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+'&tab=2',true);
}
function sendMessage(){
	Comm.dialogGlobal({
    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid=${zyxx.zyid}&msgType=1",
        title: "干预会话",
        width:870,
        height:555,
        type:"iframe"
    });
}
//关注患者
function foPatient(){
	if($("#followPatient").attr("title")=='取消关注'){
		$.ajax({
            url: '${webroot}/foPatient/f_json/delete.shtml',
            type: 'post',
            data: { patientId:'${zyxx.patientId}'},
            dataType: 'json',
            success : function(json) {
            	if(json.result=='success'){
            		$("#followPatient").attr("title","关注");
            		$("#followPatient").attr("class","img_satr_gray")
            	}
            }
		});
	}else{
		$.ajax({
            url: '${webroot}/foPatient/f_json/save.shtml',
            type: 'post',
            data: { patientId:'${zyxx.patientId}'},
            dataType: 'json',
            success : function(json) {
            	if(json.result=='success'){
            		$("#followPatient").attr("title","取消收藏");
            		$("#followPatient").attr("class","img_satr")
            	}
            }
		});
	}
}
</script>	
</body>
</html>