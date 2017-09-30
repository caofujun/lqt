<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,user-scalable=no;"/>
   	<title>选择患者调查表格</title>
    <%@ include file="/WEB-INF/view/core/include.jsp"%>
    <script type="text/javascript" src="${webroot}/resources/zclip/jquery.zclip.js${version}"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery-migrate-1.1.0.js"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery.jqprint-0.3.js"></script>
</head>
<body  class="qs_wap_body" >
<input type="hidden" value="${unitId}" id='unitId'/>
	<div class="user_main">
		<div class="top">		  
			<div class="header_title">
				<span>科室：</span>
				<select style="width:70%;" id='depSelect' onchange="changeSelect()">
						<c:forEach var="li" items="${depList}">
							<option value="${li.depNo}">${li.depName}</option>
						</c:forEach>
				</select>
			</div>
		</div>
		<div class="clear"></div>
		<div class="dateli">
			<ul  id="patientList">
			</ul>
			<div class="clear"></div>
		</div>	
	
		<div class="patient_Share" style="display:none;" id="patientSharePage" onclick="killPage()">				
			<div class="patient_Share_title" id="shareTitle"></div>
			<p>患者满意度调查</p>
			<img  id="_qcCode" src="" style="width:150px;height:150px;"/>	
			<div class="patient_Share_button"><a href="#" class="grade_grey" id='linkSurvey'>输入调查结果</a></div>							
		</div>
	</div>
   <script type="text/javascript">
	$(document).ready(function(){
		var depNo = $("#depSelect").val();
		$("#_qcCode").src="${webroot}/surveyRelease/f_view/questQcCode.shtml?qid=${qid}&depNo="+depNo;
		changeSelect();
	});
	function changeSelect(){
		
		$.ajax({
            url: '${webroot}/surveyRelease/f_json/getDepPatient.shtml',
            data : {depNo:$("#depSelect").val()},
            type: 'post',
            dataType: 'json',
            success : function(json) {
            	var data = json.data;
            	$("#patientList").html(" ");
            	if (json.result == 'success') {
            		if(data.length<1){
            		$("#patientList").append("<li>本科室无在院患者</li>");
            		return;
            		}
            		for (var i = 0; i < data.length; i++) {
            			var gender =data[i].SEX == '0' ? "男" : "女";
            			var calss= (i%2)==1 ? "class='double'" : " ";
            			var bedno="";
            			if(data[i].BEDNO!=null&& data[i].BEDNO!=''){
            				bedno=data[i].BEDNO+"号"+"床位";
            			}else{
            				bedno="未知床号";
            			}
            			
            			var name = data[i].PATIENTNAME+"（"+gender+"）";
var temp="<li  "+calss+" onclick='getPatientCode(\""+data[i].PID+"\",\""+bedno+"\",\""+name+"\")'><span class='mr10' >"+bedno+"</span><span>"+name+"</span></li>";
					$("#patientList").append(temp);
            		}
            	}
			}
		});
		
	}
	
	function getPatientCode(pid,bedno,name){
		var depNo = $("#depSelect").val();
		$("#_qcCode").attr('src' , "${webroot}/surveyRelease/f_view/questQcCode.shtml?qid=${qid}&depNo="+depNo+"&pid="+pid);
		$("#patientSharePage").css("display","block");
		$("#shareTitle").html(" ");
		$("#shareTitle").append("<span class='mr10'>"+bedno+"</span><span>"+name+"</span>");
		document.getElementById("linkSurvey").href = "${webroot}/qsQuestionnaire/view/openWap.shtml?qId=${encryptQid}"+"&depNo="+depNo+"&pid="+pid; 
	}
	function killPage(){
		$("#patientSharePage").css("display","none");
	}
	


	
	</script>
</body>
</html>
