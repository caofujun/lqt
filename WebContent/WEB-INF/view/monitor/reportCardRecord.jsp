<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<%-- 接口调用报卡选择界面 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>所有报卡情况</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body> 
<c:if test="${!empty errMsg}">
	<div class="errTip">${errMsg}</div>
</c:if>
<c:if test="${!empty tipMsg}">
	<div class="errTip">${tipMsg}</div>
</c:if>
<div id="cdcbkChoose">
	<table id="BKS" ></table>
	<div style="text-align: right;padding: 10px;">
		<input type="hidden" id="curId" 
			<c:choose>
				<c:when test="${!empty ZYBRXX}">
					value="${ZYBRXX.zyid}"
				</c:when>
				<c:when test="${!empty MZBRXX}">
					value="${MZBRXX.mzid}" 
				</c:when>
			</c:choose>
		/>
		<input type="hidden" id="patientType"
			<c:choose>
				<c:when test="${!empty ZYBRXX}">
					value="zy"
				</c:when>
				<c:when test="${!empty MZBRXX}">
					value="mz" 
				</c:when>
			</c:choose>
		/>
	<%-- 	<select id="reportCard" onchange="chooseBk();" >
			<option value="0">请选择上报卡...</option>
			<c:forEach items="${AllBK}" var="abk">
				<option value="${abk.dictCode}">${abk.dictName}</option>
			</c:forEach>
		</select> 
	--%>
	</div> 
	
	<div class="bk_up" >
		<input type="hidden" id="curZyid" />
		<div class="bk_up_class">
			<ul>
				<c:forEach items="${AllBK}" var="abk">
					<c:if test="${fn:contains(cdcScope,abk.dictCode) or fn:contains(cdcScope,'all')}">
						<li>
							<div class="bk_img2" style="padding-top: 5px;padding-bottom: 5px;">
								<a onclick="chooseBk('${abk.dictCode}');" style="" <%-- style="background:url('${webroot}/resources/images/${abk.dictCode}.png') top no-repeat;" --%> ><img src="${webroot}/resources/images/${abk.dictCode}.png" style="display: block;margin-left: 35px;" onerror="nofindlogo();"/>${abk.dictName}</a>
							</div>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
	
</div>
<script>
$(function(){
	InitGrid();
});
function InitGrid(){
	var id=$("#curId").val();
	var cardType = $("#cardType").val();
	var patientType = $("#patientType").val();
/* 	var url  = "${webroot}/cdc/f_view/toChooseCard.shtml?ownership=${param.ownership}";
	if($("#patientType")=="zy"){
		url  += "&zyid="+id;
	}else{
		url  += "&mzid="+id;
	} */
	$('#BKS').datagrid({
		title:"所有上报卡记录",
		fitColumns:true,
		singleSelect:true,
		url:'${webroot}/cdc/f_json/bkChoose.shtml',
		queryParams: {
     		'id': id,
     		'cardType':patientType
        },
		height:350,
		columns:[[
			{field:'masterid',title:'唯一编号',hidden:true},
			{field:'cardStates',title:'状态',width:50},
			{field:'cardType',title:'报卡类别',width:95},
			{field:'diseaseName',title:'上报疾病',width:75},
			{field:'mzid',title:'门诊号',width:95},
			{field:'zyid',title:'${patientZyTitle}',width:95},
			{field:'patientName',title:'患者姓名',width:65},
			{field:'sexname',title:'性别',width:35},
			{field:'ageunit',title:'年龄',width:36},
			{field:'reportdoctorname',title:'上报医生',width:85},
			{field:'reportdeptname',title:'上报科室',width:105},
			{field:'filldate',title:'上报日期',width:130},
			{field:'ops',title:'操作',width:35,formatter:function(value,row,index){
				return "<a class='ico_search' href=\"javascript:detail('"+row['cardType']+"','"+row['mzid']+"','"+row['zyid']+"','"+row['masterid']+"');\"></a>";
			}}
		]]
	});
}

function detail(cardtype,mzid,zyid,msid){
	if(msid){
		if(cardtype=='死因报卡'){
			location.href = '${webroot}/cdc/f_view/deathReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
		}else if(cardtype=='法定传染病报卡'){
			if(null!=mzid && ""!=mzid && "null"!=mzid){
				location.href = '${webroot}/cdc/f_view/reportCardMZ.shtml?mzid='+zyid+'&masterid='+msid;
			}else if(null!=zyid && ""!=zyid && "null"!=zyid){
				location.href = '${webroot}/cdc/f_view/reportCardZY.shtml?zyid='+zyid+'&masterid='+msid;
			}
		}else if(cardtype=='食源监测报卡'){
			location.href = '${webroot}/cdc/f_view/fsmReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
		}else if(cardtype=='肿瘤病例报卡'){
			location.href = '${webroot}/cdc/f_view/tumourReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
		}else if(cardtype=='食源异常报卡'){
			location.href = '${webroot}/cdc/f_view/fsaReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
		}else if(cardtype=='心脑血管报卡'){
			location.href = '${webroot}/cdc/f_view/hcvReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
		}else if(cardtype=='高温中暑报卡'){
			location.href = '${webroot}/cdc/f_view/sunstrokeReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
		}else if(cardtype=='农药中毒报卡'){
			location.href = '${webroot}/cdc/f_view/nyzdReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
		}
	}else{
		alert('查看详情失败：必要参数（masterid）获取失败！');
	}
}

function chooseBk(rc){
	//var rc = $("#reportCard option:selected").val();
	var pt = $("#patientType").val();
	var cid = $("#curId").val();
	var zid = "";
	var mid = "";
	if(pt=='zy'){
		zid=cid;
	}else{
		mid=cid;
	}
	if(rc == "crbbk"){
		if(pt=="zy"){
			location.href = "${webroot}/cdc/f_view/reportCardZY.shtml?zyid="+zid;
		}else if(pt=="mz"){
			location.href = "${webroot}/cdc/f_view/reportCardMZ.shtml?mzid="+mid;
		}
	}else if(rc == "syjcbk"){
		location.href = '${webroot}/cdc/f_view/fsmReport.shtml?zyid='+zid+'&mzid='+mid;
	}else if(rc == "sybk"){
		location.href = '${webroot}/cdc/f_view/deathReport.shtml?zyid='+zid+'&mzid='+mid;
	}else if(rc == "zlbk"){
		location.href = '${webroot}/cdc/f_view/tumourReport.shtml?zyid='+zid+'&mzid='+mid;
	}else if(rc == "syycbk"){
		location.href = '${webroot}/cdc/f_view/fsaReport.shtml?zyid='+zid+'&mzid='+mid;
	}else if(rc == "xnxgbk"){
		location.href = '${webroot}/cdc/f_view/hcvReport.shtml?zyid='+zid+'&mzid='+mid;
	}else if(rc == "gwzsbk"){
		location.href = '${webroot}/cdc/f_view/sunstrokeReport.shtml?zyid='+zid+'&mzid='+mid;
	}else if(rc == "nyzdbk"){
		location.href = '${webroot}/cdc/f_view/nyzdReport.shtml?zyid='+zid+'&mzid='+mid;
	}
}
function nofindlogo(){
	var img=event.srcElement;
	img.src="${webroot}/resources/images/forbidden.png";
	img.onerror=null;
}
</script>
</body>
</html>