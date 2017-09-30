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
	<c:if test="${st003Cryxxb==null}">
		找不到该患者的档案信息
	</c:if>
	<c:if test="${st003Cryxxb!=null}">
	<div id="patient_rightTabMenu" class="easyui-menu" style="width: 100px;display: none;">
	    <div name="refresh" data-options="iconCls:'icon-reload'">刷新 </div>
	</div>
	
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
		<div data-options="region:'north'" style="overflow: hidden;border-width: 0px;" >
			<div class="h_title" style="margin:6px 5px;">
				<h2 style="display:inline-block; margin-right:10px;">
				<%-- <c:if test="${follow=='1'}"><a href="Javascript:foPatient()" id="followPatient" title="取消关注" class="img_satr"></a></c:if>
				<c:if test="${follow!='1'}"><a href="Javascript:foPatient()" id="followPatient" title="关注" class="img_satr_gray"></a></c:if> --%>
				<c:out value="${st003Cryxxb.patientName}" /></h2>
				<span>${patientZyTitle}：<c:out value="${patientZyValue == 'patientId'?st003Cryxxb.patientId:st003Cryxxb.zyid}" /></span>
				<span style="margin-left:15px;"><c:out value="${st003Cryxxb.sex}" /></span>
				<span style="margin-left:15px;"><c:out value="${st003Cryxxb.age}" /><c:out value="${st003Cryxxb.ageUnit}" /></span>
				<span style="margin-left:20px;">床位：<c:out value="${st003Cryxxb.bedNo}" /></span>
				<span style="margin-left:15px;">入院日期：<fmt:formatDate value="${st003Cryxxb.inHospAt}" pattern="yyyy-MM-dd HH:mm" /></span>
				<span style="margin-left:15px;">所在科室：<c:out value="${st003Cryxxb.deptName}" /></span>
				<span style="margin-left:15px;">出院日期：<fmt:formatDate value="${st003Cryxxb.outAt}" pattern="yyyy-MM-dd HH:mm" /></span>
				<span style="margin-left:15px;">入院诊断：<c:out value="${st003Cryxxb.diagnosisName}" /></span>
				<span style="margin-left:15px;"><c:out value="${!empty st003Cryxxb.outAt ? '已出院' : '在院'}" /></span>			
				<%-- <span style="margin-left:15px;">				
					<a href="javascript:void(0)" class="a_icon_c red" onclick="riskFactors('${st003Cryxxb.zyid}');" title="风险因素"><i class="icon iconfont">&#xe663;</i></a> 
				</span>	 --%>	 
			</div>						
		</div>
		<c:if test="${ownership!='clinical'}">
			<c:if test="${follow=='1'}">
			<span id="guanzhuPatient" class="guanzhu" title="在【感染预警】中【关注患者】中可以找到关注患者" onclick="foPatient()"></span>
			</c:if>
			<c:if test="${follow!='1'}">
			<span id="guanzhuPatient" class="guanzhu_gray" title="关注" onclick="foPatient()"></span>
			</c:if>
		</c:if>
		<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
		    <div id="patient_frame_tabs" data-options="fit:true,plain:true">
		        <%-- <div title="患者信息" data-options="href:'${webroot}/st003Cryxxb/f_view/detail.shtml?zyid=${st003Cryxxb.zyid}'" style="padding:10px"></div> --%>
		        <div title="患者信息" style="padding:10px;" data-options="href:'${webroot}/st003Cryxxb/f_view/detail.shtml?zyid=${st003Cryxxb.zyid}'">
		            <%-- <iframe scrolling="yes" frameborder="0"  src="${webroot}/st003Cryxxb/f_view/detail.shtml?zyid=${st003Cryxxb.zyid}" style="width:100%;height:100%;"></iframe> --%>
		        </div>
		        <div title="综合图示" style="overflow:hidden" data-options="href:'${webroot}/st003Cryxxb/f_view/viewframe.shtml?zyid=${st003Cryxxb.zyid}&src=/st003Cryxxb/f_view/view.shtml'" ${'2' eq param.tab ? "selected" : ""}>
		        </div>
		        <div title="重点风险因素" style="overflow:hidden" data-options="href:'${webroot}/st003Cryxxb/f_view/viewframe.shtml?zyid=${st003Cryxxb.zyid}&src=/st003Cryxxb/f_view/toriskFacto.shtml'" ${'15' eq param.tab ? "selected" : ""}>
		            <%-- <iframe scrolling="yes" frameborder="0"  src="${webroot}/st003Cryxxb/f_view/toriskFacto.shtml?zyid=${st003Cryxxb.zyid}" style="width:100%;height:100%;"></iframe> --%>
		        </div>
		        <div title="医嘱信息" style="overflow:hidden" data-options="href:'${webroot}/st003Cryxxb/f_view/viewframe.shtml?zyid=${st003Cryxxb.zyid}&id=${param.sjId}&src=/st004Yzxxb/f_view/toDocAdviceList.shtml'" ${'3' eq param.tab ? "selected" : ""}>
		            <%-- <iframe scrolling="yes" frameborder="0"  src="${webroot}/st004Yzxxb/f_view/toDocAdviceList.shtml?zyid=${st003Cryxxb.zyid}" style="width:100%;height:100%;"></iframe> --%>
		        </div>
		        <div title="检验信息" style="overflow:hidden" data-options="href:'${webroot}/st003Cryxxb/f_view/viewframe.shtml?zyid=${st003Cryxxb.zyid}&id=${param.sjId}&src=/st009Sjbb/f_view/toCheckInfoList.shtml'" ${'4' eq param.tab ? "selected" : ""}>
		        </div>
		        <div title="手术信息" style="overflow:hidden" data-options="href:'${webroot}/st005Ssxxb/f_view/toRecordList.shtml?zyid=${st003Cryxxb.zyid}'" ${'5' eq param.tab ? "selected" : ""}>
		            <%-- <iframe scrolling="yes" frameborder="0" src="${webroot}/st005Ssxxb/f_view/toRecordList.shtml?zyid=${st003Cryxxb.zyid}" style="width:100%;height:100%;"></iframe> --%>
		        </div>
		        <div title="体温信息" style="overflow:auto" data-options="href:'${webroot}/st006Twxx/f_view/toTwxxList.shtml?zyid=${st003Cryxxb.zyid}&dataDate=${param.dataDate}'" ${'6' eq param.tab ? "selected" : ""}>
		        </div>
		        <div title="病程分析" style="overflow:hidden" data-options="href:'${webroot}/st008Bcjl/f_view/toDisAnalysisList.shtml?zyid=${st003Cryxxb.zyid}&id=${param.sjId}&dataDate=${param.dataDate}'" ${'7' eq param.tab ? "selected" : ""}>
		        </div>
		        <div title="影像结果" style="overflow:hidden" data-options="href:'${webroot}/st014Pacs/f_view/toImageResultsList.shtml?zyid=${st003Cryxxb.zyid}&id=${param.sjId}'" ${'8' eq param.tab ? "selected" : ""}>
		        </div>
		        <div title="诊断信息" style="overflow:hidden" data-options="href:'${webroot}/st002Zdxxb/f_view/toDiagnosisList.shtml?zyid=${st003Cryxxb.zyid}'" ${'9' eq param.tab ? "selected" : ""}>
		            <%-- <iframe scrolling="yes" frameborder="0" src="${webroot}/st002Zdxxb/f_view/toDiagnosisList.shtml?zyid=${st003Cryxxb.zyid}" style="width:100%;height:100%;"></iframe> --%>
		        </div>
		        <div title="预警结果" style="overflow:hidden;" data-options="href:'${webroot}/st003Cryxxb/f_view/viewframe.shtml?zyid=${st003Cryxxb.zyid}&diagType=1&src=/gr002YsgrMx/f_view/toWarningPatient.shtml'" ${'10' eq param.tab ? "selected" : ""}>
		        </div>
		        <%-- <div title="感染因素" style="overflow:hidden" data-options="href:'${webroot}/gr018Ysgrys/f_view/toInfectionList.shtml?zyid=${st003Cryxxb.zyid}'" ${'11' eq param.tab ? "selected" : ""}>
		        </div> --%>
		        <div title="出入科记录" style="overflow:hidden" data-options="href:'${webroot}/st012Zkjl/f_view/toInAndOutDepList.shtml?zyid=${st003Cryxxb.zyid}'" ${'12' eq param.tab ? "selected" : ""}>
		            <%-- <iframe scrolling="yes" frameborder="0" src="${webroot}/st012Zkjl/f_view/toInAndOutDepList.shtml?zyid=${st003Cryxxb.zyid}" style="width:100%;height:100%;"></iframe> --%>
		        </div>
		        <div title="干预情况" style="overflow:hidden" data-options="href:'${webroot}/nyMessageDetail/f_view/index.shtml?zyid=${st003Cryxxb.zyid}'" ${'13' eq param.tab ? "selected" : ""}>
		        </div>
		        <div title="病例上报情况" style="overflow:hidden" data-options="href:'${webroot}/gr002YsgrMx/f_view/toCasesReported.shtml?zyid=${st003Cryxxb.zyid}'" ${'14' eq param.tab ? "selected" : ""}>
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
	if($("#guanzhuPatient").attr("title")=='关注'){
		$.ajax({
            url: '${webroot}/foPatient/f_json/save.shtml',
            type: 'post',
            data: { patientId:'${st003Cryxxb.patientId}'},
            dataType: 'json',
            success : function(json) {
            	if(json.result=='success'){
            		$("#guanzhuPatient").attr("title","在【感染预警】中【关注患者】中可以找到关注患者");
            	/* $("#followPatient").attr("class","img_satr"); */
            		$("#guanzhuPatient").attr("class","guanzhu");
            	}
            }
		});
	}else{
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
</c:if>
</body>
</html>