<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%-- 被  “疑似病例搜索”、“预警病例查询”、“目标监测-患者信息查询” 调用 --%>
<script type="text/javascript">
function nofindlogo(){
	var img=event.srcElement;
	img.src="${webroot}/resources/images/forbidden.png";
	img.onerror=null;
}
</script>
<div id="cdcbkChoose" style="display: none;">
	<table id="BKS" style="width:820px;height:300px;"></table>
	
	<div id="cardsPlace" class="bk_up" style="display: none;">
		<input type="hidden" id="curZyid" />
		<input type="hidden" id="curMzid" />
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
<script type="text/javascript">
$(function(){
	InitGrid();
})
function crbbk(pname,zyid,mzid){
	$("#cdcbkChoose").dialog({
		title: "病例上报 - 病人:"+pname+"("+(!zyid?mzid:zyid)+")	 - 已报卡列表",
		width: 850,
		height: 430,
	    closed: true,
	    cache: false,
	    modal: true,
	    onClose:function(){
	    	$("#reportCard").val("0");
	    	setTimeout("$('#BKS').datagrid('loadData', { total: 0, rows: [] })",200);
	    },
	    onOpen:function(){
	    	$("#cdcbkChoose").show();
	    	$("#reportCard").show();
	    	//
	    	queryData(zyid,mzid);
	    	//setTimeout("InitGrid('"+zyid+"','"+mzid+"')",20);
	    	//setTimeout("queryData('"+zyid+"','"+mzid+"')",50);
	    }
	});
	//居中判断
	var top = document.body.scrollTop;
	var mtop = parseInt(($(window).height()-425)/2);
	if(mtop>0){
		top += mtop;
	}
	$('#cdcbkChoose').window('open').window('resize',{top: top});
	$("#curMzid").val(mzid);
	$("#curZyid").val(zyid);
	/* setTimeout(InitGrid(),100);
	setTimeout(queryData(zyid),200); */
	//parent.menuInfo.clickMenu('传染病上报','/cdc/f_view/reportCardZY.shtml?zyid='+zyid,true);
}

/* 在调用页面中被初始化，打开快些 */
function InitGrid(){
	$('#BKS').datagrid({
		url:'',
		fitColumns:true,
		singleSelect:true,
		height:300,
		columns:[[
			{field:'masterid',title:'唯一编号',hidden:true},
			{field:'cardStates',title:'状态',width:50,formatter:function(value,row,index){
				if(value=="未审核"){
					return "<div class='yellow'>"+value+"</div>";
				}else if(value=="已审核"){
					return "<div class='red'>"+value+"</div>";
				}else if(value=="已退卡"){
					return "<div class='blue'>"+value+"</div>";
				}else if(value=="已删卡"){
					return "<div class='gray'>"+value+"</div>";
				}
			}},
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
			{field:'ops',title:'操作',formatter:function(value,row,index){
				return "<a class='ico_search' href=\"javascript:detail('"+row['cardType']+"','"+row['zyid']+"','"+row['mzid']+"','"+row['masterid']+"');\"></a>";
			}}
		]]
	});
}

function queryData(zyid,mzid){
	var act_url="${webroot}/cdc/f_json/bkChoose.shtml";
	$('#BKS').datagrid({
        url: act_url,
        queryParams: {
     		'id': (!zyid||zyid=="null"?mzid:zyid),
     		'cardType':(!zyid?'mz':'zy')
        },
        onLoadSuccess: function (data) {}
    });
}

function detail(cardtype,zyid,mzid,msid){
	zyid = (!zyid||zyid=='null')?"":zyid;
	mzid = (!mzid||mzid=='null')?"":mzid;
	if(msid){
		if(cardtype=='死因报卡'){
			parent.menuInfo.clickMenu('死因上报记录查看','/cdc/f_view/deathReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			$("#cdcbkChoose").dialog("close");
		}else if(cardtype=='法定传染病报卡'){
			if(!zyid || zyid=='null'){
				parent.menuInfo.clickMenu('传染病上报记录查看','/cdc/f_view/reportCardMZ.shtml?mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			}else{
				parent.menuInfo.clickMenu('传染病上报记录查看','/cdc/f_view/reportCardZY.shtml?zyid='+zyid+'&masterid='+msid+'&justLook=Y',true);
			}
			$("#cdcbkChoose").dialog("close");
		}else if(cardtype=='食源监测报卡'){
			parent.menuInfo.clickMenu('食源监测上报记录查看','/cdc/f_view/fsmReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			$("#cdcbkChoose").dialog("close");
		}else if(cardtype=='肿瘤病例报卡'){
			parent.menuInfo.clickMenu('肿瘤病例上报记录查看','/cdc/f_view/tumourReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			$("#cdcbkChoose").dialog("close");
		}else if(cardtype=='食源异常报卡'){
			parent.menuInfo.clickMenu('食源异常上报记录查看','/cdc/f_view/fsaReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			$("#cdcbkChoose").dialog("close");
		}else if(cardtype=='心脑血管报卡'){
			parent.menuInfo.clickMenu('心脑血管事件上报记录查看','/cdc/f_view/hcvReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			$("#cdcbkChoose").dialog("close");
		}else if(cardtype=='农药中毒报卡'){
			parent.menuInfo.clickMenu('农药中毒上报记录查看','/cdc/f_view/nyzdReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			$("#cdcbkChoose").dialog("close");
		}
	}else{
		$.messager.show({ title: '提示', msg: '查看详情失败：必要参数获取失败！' });
	}
}

function chooseBk(rc){
	//var rc = $("#reportCard option:selected").val();
	var zyid = $("#curZyid").val();
	var mzid = $("#curMzid").val();
	if(rc == "crbbk"){
		if(!zyid || zyid=='null'){
			parent.menuInfo.clickMenu('传染病报卡上报','/cdc/f_view/reportCardMZ.shtml?mzid='+mzid,true);
		}else{
			parent.menuInfo.clickMenu('传染病报卡上报','/cdc/f_view/reportCardZY.shtml?zyid='+zyid,true);
		}
	}else if(rc == "syjcbk"){
		parent.parent.menuInfo.clickMenu('食源监测报卡上报','/cdc/f_view/fsmReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
	}else if(rc == "sybk"){
		parent.parent.menuInfo.clickMenu('居民死因报卡上报','/cdc/f_view/deathReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
	}else if(rc == "zlbk"){
		parent.parent.menuInfo.clickMenu('肿瘤病例报卡上报','/cdc/f_view/tumourReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
	}else if(rc == "syycbk"){
		parent.parent.menuInfo.clickMenu('食源异常报卡上报','/cdc/f_view/fsaReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
	}else if(rc == "xnxgbk"){
		parent.parent.menuInfo.clickMenu('心脑血管事件报卡上报','/cdc/f_view/hcvReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
	}else if(rc == "nyzdbk"){
		parent.menuInfo.clickMenu('农药中毒报卡上报','/cdc/f_view/nyzdReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
	}
	$("#cdcbkChoose").dialog("close");
}

function emptyCard(){
	var curCT = $("#emptyCard option:selected").val();
	if(curCT=='crbbk_empty'){
		parent.menuInfo.clickMenu('传染病报卡上报','/cdc/f_view/reportCardMZ.shtml?type=empty',true);
	}else if(curCT=='sybk_empty'){
		parent.menuInfo.clickMenu('死因报卡上报','/cdc/f_view/deathReport.shtml?type=empty',true);
	}else if(curCT=='syjcbk_empty'){
		parent.menuInfo.clickMenu('食源监测报卡上报','/cdc/f_view/fsmReport.shtml?type=empty',true);
	}else if(curCT=='zlbk_empty'){
		parent.menuInfo.clickMenu('肿瘤病例报卡上报','/cdc/f_view/tumourReport.shtml?type=empty',true);
	}else if(curCT=='syycbk_empty'){
		parent.menuInfo.clickMenu('食源异常报卡上报','/cdc/f_view/fsaReport.shtml?type=empty',true);
	}else if(curCT=="xnxgbk_empty"){
		parent.parent.menuInfo.clickMenu('心脑血管事件报卡上报','/cdc/f_view/hcvReport.shtml?type=empty',true);
	}
	$("#emptyCard option[value='']").attr("selected","selected");
}

</script>