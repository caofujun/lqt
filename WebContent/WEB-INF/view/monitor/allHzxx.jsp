<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${!empty errorMsg}">
	<div class="error closable">
		<p>${errorMsg}</p>
	</div>
</c:if>
<c:if test="${!empty tipMsg}">
	<div class="success closable">
		<p>${tipMsg}</p>
	</div>
</c:if>
	<c:forEach items="${HZXX}" var="hzxx">
		<div class="card">
			<div class="title" >
				<label>
					<c:choose >
						<c:when test="${hzxx.sex=='男'}">
							<span class="icon-man-two"></span>
						</c:when>
						<c:otherwise>
							<span class="icon-woman-two"></span>
						</c:otherwise>
					</c:choose>
					${hzxx.patientName}
				</label>
				<label><a>&nbsp;  ${hzxx.bedNo}床   &nbsp;&nbsp; ${hzxx.sex}   &nbsp;&nbsp;  ${hzxx.age}${hzxx.ageUnit}  </a></label>
			</div>
			<div class="info" style="padding: 5px;">
				<table>
					<tr>
						<td class="item"><span>${patientZyTitle}：<a href="javascript:void(0);" onclick="patientInfo('${hzxx.zyid}')">${hzxx.zyid}</a></span></td>
					</tr>
					<tr>
						<td class="item"><span>入院日期：<fmt:formatDate value="${hzxx.inHospAt}" type="both"/></span></td>
					</tr>
					<tr>
						<td class="item"><span>感染部位：${null == hzxx.diagnosisName?"无":hzxx.diagnosisName}</span></td>
					</tr>
					<c:if test="${null != hzxx.fhyj && hzxx.fhyj != 0}">
						<tr>
							<td class="item">预警感染：<a class="yj_tip"><img width="20px" height="20px" src="${webroot}/resources/images/jd.png" alt="预警感染" style="vertical-align: top;"/>&nbsp;${hzxx.fhyj}</a></td>
						</tr>
					</c:if>
				</table>
			</div>
			<div id="butt_place" class="opts">
				<nis:auth menuNo="F0101">
					<div class="butts" style="float: left">
						<a class="" onclick="bk('${hzxx.zyid}')" style="cursor: pointer;">院感上报</a>
					</div>
				</nis:auth>
				<nis:auth menuNo="F0102">
					<div class="butts split" style="float: left">
						<a class="" onclick="crbbk('${hzxx.patientName}','${hzxx.zyid}')" style="cursor: pointer;">传染病上报</a>
					</div>
				</nis:auth>
				<div class="butts split" style="float: left">
					<a class="" onclick="" style="cursor: pointer;">干预</a>
				</div>
				<!-- <div class="divs" style="padding-right: 30px;margin-right:30px;border-right: 1px solid #cbcbcb">
					<a class="" style="cursor: pointer;">风险评估</a>
				</div> -->
				<div class="clear"></div>
			</div>
		</div>
	</c:forEach>

<div id="cdcbkChoose">
	<table id="BKS" ></table>
	<div style="text-align: right;padding: 10px;">
		<input type="hidden" id="curZyid" />
		<select id="reportCard" onchange="chooseBk();" style="display: none;">
			<option value="0">请选择上报卡...</option>
			<c:forEach items="${AllBK}" var="abk">
				<option value="${abk.dictCode}">${abk.dictName}</option>
			</c:forEach>
		</select>
	</div>
</div>
<script>
$(function(){
});
$(".closable").each(function(){
	$(this).dblclick(function(){
		$(this).hide();
	});
});
function patientInfo(zyid){
	parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
}
function bk(zyid){
	parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid,true);
}
function crbbk(pname,zyid){
	$("#cdcbkChoose").dialog({
		title: "病例上报 - 病人:"+pname+"("+zyid+")	 - 已报卡列表",
	    width: 850,
	    height: 405,
	    closed: true,
	    cache: false,
	    modal: true,
	    onClose:function(){
	    	$("#reportCard").val("0");
	    },
	    onOpen:function(){
	    	$("#reportCard").show();
	    	InitGrid(zyid);
	    }
	});
	//居中判断
	var top = document.body.scrollTop;
	var mtop = parseInt(($(window).height()-405)/2);
	if(mtop>0){
		top += mtop;
	}
	$('#cdcbkChoose').window('open').window('resize',{top: top});
	$("#curZyid").val(zyid);
	/* setTimeout(InitGrid(),100);
	setTimeout(queryData(zyid),200); */
	//parent.menuInfo.clickMenu('传染病上报','/cdc/f_view/reportCardZY.shtml?zyid='+zyid,true);
}
//宽度计算
var count = $("#butt_place").find(".butts").length;
$(".butts").css("width",parseInt(100/count)+"%");

function InitGrid(zyid){
	$('#BKS').datagrid({
		url:'${webroot}/cdc/f_json/bkChoose.shtml',
		queryParams: {
     		'id': zyid
        },
		fitColumns:true,
		singleSelect:true,
		height:300,
		columns:[[
			{field:'masterid',title:'唯一编号',hidden:true},
			{field:'cardStates',title:'状态'},
			{field:'cardType',title:'报卡类别'},
			{field:'diseaseName',title:'上报疾病'},
			{field:'mzid',title:'门诊号'},
			{field:'zyid',title:'${patientZyTitle}'},
			{field:'patientName',title:'患者姓名'},
			{field:'sexname',title:'性别'},
			{field:'ageunit',title:'年龄'},
			{field:'reportdoctorname',title:'上报医生'},
			{field:'reportdeptname',title:'上报科室'},
			{field:'filldate',title:'上报日期'},
			{field:'ops',title:'操作',formatter:function(value,row,index){
				return "<a class='ico_search' title='查看' href=\"javascript:detail('"+row['cardType']+"','"+row['zyid']+"','"+row['mzid']+"','"+row['masterid']+"');\"></a>";
				//return "<a class='ico_search' title='查看' href=\"javascript:detail('"+row['zyid']+"','"+row['masterid']+"');\"></a>";
			}}
		]]
	});
}
function queryData(id){
	var act_url="${webroot}/cdc/f_json/bkChoose.shtml";
	$('#BKS').datagrid({
        url: act_url,
        queryParams: {
     		'id': id
        },
        onLoadSuccess: function (data) {}
    });
}
function chooseBk(){
	var rc = $("#reportCard option:selected").val();
	var zyid = $("#curZyid").val();
	if(rc == "crbbk"){
		parent.menuInfo.clickMenu('传染病报卡上报','/cdc/f_view/reportCardZY.shtml?zyid='+zyid,true);
	}else if(rc == "syjcbk"){
		parent.menuInfo.clickMenu('食源监测报卡上报','/cdc/f_view/fsmReport.shtml?zyid='+zyid,true);
	}else if(rc == "sybk"){
		parent.menuInfo.clickMenu('居民死因报卡上报','/cdc/f_view/deathReport.shtml?zyid='+zyid,true);
	}
	$("#cdcbkChoose").dialog("close");
}
function detail(cardtype,zyid,mzid,msid){
	if(msid){
		if(cardtype=='死因报卡'){
			parent.menuInfo.clickMenu('死因上报记录查看','/cdc/f_view/deathReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			$("#cdcbkChoose").dialog("close");
		}else if(cardtype=='法定传染病报卡'){
			parent.menuInfo.clickMenu('传染病上报记录查看','/cdc/f_view/reportCardZY.shtml?zyid='+zyid+'&masterid='+msid+'&justLook=Y',true);
			$("#cdcbkChoose").dialog("close");
		}else if(cardtype=='食源监测报卡'){
			parent.menuInfo.clickMenu('食源监测上报记录查看','/cdc/f_view/fsmReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			$("#cdcbkChoose").dialog("close");
		}
	}else{
		$.messager.show({ title: '提示', msg: '查看详情失败：必要参数获取失败！' });
	}
}
</script>