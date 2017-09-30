<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<jsp:include page="/WEB-INF/view/core/report.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>患者档案</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
<style type="text/css">
.tabs-panels {
	border-width: 0;
}
</style>
</head>
<body>
	<div id="patient_rightTabMenu" class="easyui-menu" style="width: 100px;display: none;">
	    <div name="refresh" data-options="iconCls:'icon-reload'">刷新</div>
	</div>
	
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
		<div data-options="region:'north'" style="overflow: hidden;border-width: 0px;" >
			<div class="h_title" style="margin:6px 5px;">
				<h2 style="display:inline-block; margin-right:10px;">
				<c:out value="${st020ClinicPatients.patientName}" /></h2>
				<span>门诊号：<c:out value="${st020ClinicPatients.mzid}" /></span>
				<span style="margin-left:15px;"><c:out value="${st020ClinicPatients.sex}" /></span>
				<span style="margin-left:15px;"><c:out value="${st020ClinicPatients.age}" /><c:out value="${st020ClinicPatients.ageUnit}" /></span>
				<!-- span style="margin-left:20px;">床位：<c:out value="${st003Cryxxb.bedNo}" /></span-->
				<span style="margin-left:15px;">就诊日期：<fmt:formatDate value="${st020ClinicPatients.diagnosisDt}" pattern="yyyy-MM-dd HH:mm" /></span>
				<span style="margin-left:15px;">门诊诊断：<c:out value="${st020ClinicPatients.diagnosisName}" /></span>				 
			</div>						
		</div>
		<%-- <c:if test="${follow=='1'}">
		<span id="guanzhuPatient" class="guanzhu" title="取消关注" onclick="foPatient()"></span>
		</c:if>
		<c:if test="${follow!='1'}">
		<span id="guanzhuPatient" class="guanzhu_gray" onclick="foPatient()"></span>
		</c:if> --%>
		<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
		    <div id="patient_frame_tabs" data-options="fit:true,plain:true">
		        <%-- <div title="患者信息" data-options="href:'${webroot}/st003Cryxxb/f_view/detail.shtml?zyid=${st003Cryxxb.zyid}'" style="padding:10px"></div> --%>
		        <div title="患者信息" style="padding:10px;" data-options="href:'${webroot}/st003Cryxxb/f_view/mzDetail.shtml?mzid=${st020ClinicPatients.mzid}'">
		            <%-- <iframe scrolling="yes" frameborder="0"  src="${webroot}/st003Cryxxb/f_view/detail.shtml?zyid=${st003Cryxxb.zyid}" style="width:100%;height:100%;"></iframe> --%>
		        </div>
		        <div title="检验信息" style="overflow:hidden" data-options="href:'${webroot}/st009Sjbb/f_view/toCheckInfoList.shtml?mzid=${st020ClinicPatients.mzid}&id=${param.sjId}'" ${'4' eq param.tab ? "selected" : ""}>
		        </div>
				<c:if test="${mzbc=='1'}">
				<div title="病程分析" style="overflow:hidden" data-options="href:'${webroot}/st008Bcjl/f_view/toDisAnalysisList.shtml?mzid=${st020ClinicPatients.mzid}&id=${param.sjId}&dataDate=${param.dataDate}'" ${'7' eq param.tab ? "selected" : ""}>
		        </div>
				</c:if>      
		        <div title="影像结果" style="overflow:hidden" data-options="href:'${webroot}/st014Pacs/f_view/toImageResultsList.shtml?mzid=${st020ClinicPatients.mzid}&id=${param.sjId}'" ${'8' eq param.tab ? "selected" : ""}>
		        </div>
		        <div title="干预情况" style="overflow:hidden" data-options="href:'${webroot}/nyMessageDetail/f_view/index.shtml?mzid=${st020ClinicPatients.mzid}'" ${'13' eq param.tab ? "selected" : ""}>
		        </div>
		    </div>
	    </div>
    </div>
<script type="text/javascript">
//风险因素
function riskFactors(zyid) {
	parent.menuInfo.clickMenu('风险详情','/fxPatient/f_view/detail.shtml?zyId='+zyid+"&tab=2",true);
}
//关注患者
function foPatient(){
	if($("#guanzhuPatient").attr("title")=='取消关注'){
		$.ajax({
            url: '${webroot}/foPatient/f_json/delete.shtml',
            type: 'post',
            data: { patientId:'${st003Cryxxb.patientId}'},
            dataType: 'json',
            success : function(json) {
            	if(json.result=='success'){
            		$("#guanzhuPatient").attr("title","关注");
            	/*	$("#followPatient").attr("class","img_satr_gray"); */
            		$("#guanzhuPatient").attr("class","guanzhu_gray");
            	}
            }
		});
	}else{
		$.ajax({
            url: '${webroot}/foPatient/f_json/save.shtml',
            type: 'post',
            data: { patientId:'${st003Cryxxb.patientId}'},
            dataType: 'json',
            success : function(json) {
            	if(json.result=='success'){
            		$("#guanzhuPatient").attr("title","取消关注");
            	/* $("#followPatient").attr("class","img_satr"); */
            		$("#guanzhuPatient").attr("class","guanzhu");
            	}
            }
		});
	}
}

$(document).ready(function () {
	function refresh(index) {
		$('#patient_frame_tabs').tabs('select', index);
		var currTab = $('#patient_frame_tabs').tabs('getTab', index);
		var url = currTab.attr('href');
		currTab.panel('refresh', url);
	}
	
	//监听右键事件，创建右键菜单
    $('#patient_frame_tabs').tabs({
        onContextMenu:function(e, title, index){
   		    e.preventDefault();
            $('#patient_rightTabMenu').menu('show', {
                left: e.pageX,
                top: e.pageY
            }).data('tabTitle', index);
        }
    });
	
    //右键菜单click
    $('#patient_rightTabMenu').menu({
        onClick : function (item) {
            var clickType = item.name;
        	var clickIndex = $(this).data('tabTitle');
            switch(clickType) {
            	case 'refresh':
            		refresh(clickIndex);
        		break;
            }
        }
    });
});
</script>
</body>
</html>