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
	<c:forEach items="${MZHZXX}" var="hzxx">
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
				<label><a>&nbsp; ${hzxx.sex}   &nbsp;&nbsp;  ${hzxx.age}${hzxx.ageUnit}  </a></label>
			</div>
			<div class="info" style="padding: 5px;">
				<table>
					<tr>
						<td class="item"><span>门诊号：<a href="javascript:void(0);" onclick="patientInfo('${hzxx.mzid}')">${hzxx.mzid}</a></span></td>
					</tr>
					<tr>
						<td class="item"><span>就诊日期：<fmt:formatDate value="${hzxx.diagnosisDt}" type="both"/></span></td>
					</tr>
					<tr>
						<td class="item"><span>门诊诊断：${null == hzxx.diagnosisName?"无":hzxx.diagnosisName}</span></td>
					</tr>
				</table>
			</div>
			<div id="butt_place" class="opts">
<%-- 				<nis:auth menuNo="F0101">
					<div class="butts" style="float: left">
						<a class="" onclick="bk('${hzxx.mzid}')" style="cursor: pointer;">院感上报</a>
					</div>
				</nis:auth>  --%>				
 				<nis:auth menuNo="F0102">
					<div class="butts" style="float: left">
						<a class="" onclick="crbbk('${hzxx.patientName}','${hzxx.mzid}')" style="cursor: pointer;">传染病上报</a>
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
		<input type="hidden" id="curMzid" />
		<select id="reportCard" onchange="chooseBk();" style="display: none;">
			<option value="0">请选择上报卡...</option>
			<option value="crb">法定传染病报卡</option>
			<option value="syjcbk">食源监测报卡</option>
			<option value="sybk">死因报卡</option>
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
function crbbk(pname,mzid){
	$("#cdcbkChoose").dialog({
		title: "病例上报 - 病人:"+pname+"("+mzid+")	 - 已报卡列表",
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
	    	InitGrid(mzid);
	    }
	});
	//居中判断
	var top = document.body.scrollTop;
	var mtop = parseInt(($(window).height()-405)/2);
	if(mtop>0){
		top += mtop;
	}
	$('#cdcbkChoose').window('open').window('resize',{top: top});
	$("#curMzid").val(mzid);
	/* setTimeout(InitGrid(),100);
	setTimeout(queryData(zyid),200); */
	//parent.menuInfo.clickMenu('传染病上报','/cdc/f_view/reportCardZY.shtml?zyid='+zyid,true);
}
//宽度计算
var count = $("#butt_place").find(".butts").length;
$(".butts").css("width",parseInt(99/count)+"%");

function InitGrid(mzid){
	$('#BKS').datagrid({
		url:'${webroot}/cdc/f_json/bkChoose.shtml',
		queryParams: {
     		'id': mzid,
     		'cardType':'mz'
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
				return "<a class='ico_search' href=\"javascript:detail('"+row['mzid']+"','"+row['masterid']+"');\"></a>";
			}}
		]]
	});
}
function queryData(id){
	var act_url="${webroot}/cdc/f_json/bkChoose.shtml";
	$('#BKS').datagrid({
        url: act_url,
        queryParams: {
     		'id': id,
     		'cardType':'mz'
        },
        onLoadSuccess: function (data) {}
    });
}
function chooseBk(){
	var rc = $("#reportCard option:selected").val();
	var mzid = $("#curMzid").val();
	if(rc == "crb"){
		parent.menuInfo.clickMenu('传染病报卡上报','/cdc/f_view/reportCardMZ.shtml?mzid='+mzid,true);
	}else if(rc == "syjcbk"){
		parent.menuInfo.clickMenu('食源监测报卡上报','/cdc/f_view/fsmReport.shtml?mzid='+mzid,true);
	}else if(rc == "sybk"){
		parent.menuInfo.clickMenu('居民死因报卡上报','/cdc/f_view/deathReport.shtml?mzid='+mzid,true);
	}
}
function detail(mzid,msid){
	if(msid){
		parent.menuInfo.clickMenu('传染病上报记录查看','/cdc/f_view/reportCardMZ.shtml?mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
		$("#cdcbkChoose").dialog("close");
	}else{
		$.messager.show({ title: '提示', msg: '查看详情失败：必要参数获取失败！' });
	}
}
</script>