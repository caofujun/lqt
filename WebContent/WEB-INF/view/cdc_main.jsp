<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蓝蜻蜓传染病监测及数据直报系统</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css${version}" />
<style type="text/css">
/*覆盖原easyui dialog边框样式-慎用*/
.window{background:none;background-color: #208eed;}
.panel-title{color: #fff;font-size: 16px;}
.pname{color:#cc6600;font-weight: bold;font-size: 20px;width: 100px;display: inline-block;}
.sbtn:HOVER {background-color: #bedeff;}
</style>
</head>
<body class="easyui-layout">
	<div style="padding: 5px;">
		<div style="float:left;font-size: large;color:#adadad;">
			<a style="padding: 3px 7px;font-weight: bold;cursor: pointer;" name="mz" class="curM" onclick="move(this)">门诊患者</a>
			<a style="padding: 3px 7px;font-weight: bold;cursor: pointer;" name="zy" onclick="move(this)">住院患者</a>
			<a style="padding: 3px 7px;font-weight: bold;cursor: pointer;" name="db"onclick="move(this)">待报患者<span>(5)</span></a>
			<div class="move-bg"></div>
		</div>
		<div style="float:right;">
			<select id="emptyCard">
				<option>空卡上报</option>
				<option>法定传染病（ing...）</option>
				<option>死因（待开发）</option>
			</select>
			<input type="button" class="butt" onclick="extractPatientInfo();" value="实时提取患者信息" />
			<input type="text" class="auto-tip input_tip" id="searchString" name="searchString" data-tip="${patientZyTitle}/姓名/门诊号" />
			<img class="sbtn" src="${webroot}/resources/images/search-icon.png" style="vertical-align: middle;padding: 1px 2px;margin-left: -32px;margin-right: 6px;cursor: pointer;width: 24px;height: 24px;border-radius: 4px;" /> 
			<input type="button" id="moreCondition" class="butt" onclick="if($(this).hasClass('cdtOn')){$(this).removeClass('cdtOn');}else{$(this).addClass('cdtOn');};showMoreCDT();" value="更多条件" />
		</div>
		<div style="clear:both;"></div>
	</div>
	<div class="cdtBox">
		<div id="mzMC">
			<form id="mzForm">
				<label>起止就诊日期：<input type="text" id="queryStartDate" name="queryStartDate" class="Wdate text" value="${queryStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />~<input type="text" id="queryEndDate" value="${queryEndDate}" name="queryEndDate" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></label>
				<label class="CDTSpace">科室：<input class="easyui-combobox" name="deptId" id="deptIds" style="width: 200px;" data-options="url:'',method:'get',valueField:'id',textField:'text',multiple:true,panelHeight:'auto'"></label>
				<label class="CDTSpace"><input type="checkbox" name="NRM" value="1"/>有未读干预消息</label>
				<label class="CDTSpace"><input type="button" class="butt searchButt" onclick="query('mz');" value="查询" /></label>
			</form>
		</div>
		<div id="zyMC">
			<form id="zyForm">
				患者状态：<label ><input type="checkbox" id="inhosp" name="inhosp" value="1" />在院 </label><label class="CDTSpace"><input type="checkbox" id="outhosp" name="outhosp" value="1" />出院 </label>
				<span class="CDTSpace">日期类型：<label ><input type="radio" name="queryDateType" value="1" />入院日期</label><label class="CDTSpace"><input type="radio" name="queryDateType" value="2" />出院日期 </label></span>
				<label class="CDTSpace">起止日期：<input type="text" id="queryStartDate" name="queryStartDate" class="Wdate text" value="${queryStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />~<input type="text" id="queryEndDate" value="${queryEndDate}" name="queryEndDate" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></label>
				<label class="CDTSpace">科室：<input class="easyui-combobox" name="deptId" id="deptIds" style="width: 200px;" data-options="url:'',method:'get',valueField:'id',textField:'text',multiple:true,panelHeight:'auto'"></label>
				<label class="CDTSpace"><input type="checkbox" name="NRM" value="1"/>有未读干预消息</label>
				<label class="CDTSpace"><input type="button" class="butt searchButt" onclick="query('zy');" value="查询" /></label>
			</form>
		</div>
		<div id="dbMC">
			<form id="dbForm">
				患者类型：<label ><input type="checkbox" id="mzbr" name="mzbr" value="1" />门诊 </label><label class="CDTSpace"><input type="checkbox" id="zybr" name="zybr" value="1" />住院</label>
				<label class="CDTSpace2"><input type="checkbox" name="NRM" id="NRM" value="1"/>有未读干预消息</label>
				<label class="CDTSpace"><input type="button" class="butt searchButt" onclick="query('db');" value="查询" /></label>
			</form>
		</div>
	</div>
	<div id="pageStage">
		
	</div>
	<div id="extract" class="easyui-dialog" title="从HIS提取患者信息" style="width:450px;height:140px;" data-options="resizable:true,modal:true,closed:true">
		<div style="text-align: center;padding-top: 30px;">
			<label>患者关键字：<input type="text" name="extractKey" class="auto-tip input_tip" id="extractKey" data-tip="姓名/门诊号/${patientZyTitle}"/></label>
			<label class="CDTSpace"><input type="button" id="extractButt" class="butt searchButt" value="提取" /></label>
		</div>
	</div>
</body>
<script type="text/javascript">
$(".move-bg").width($(".curM")[0].offsetWidth);
$(".move-bg").animate({left:getPos($(".curM")[0])},200);
$(function(){
	loadDate();
});
function move(ele){
	$(".curM").removeClass("curM");
	$(ele).addClass("curM");
	var o = getPos($(".curM")[0]);
	$(".move-bg").width($(".curM").width()+14);
	$(".move-bg").animate({left:o},200);
	loadDate();
	showMoreCDT();
}
function getPos(ele){
	 var p=ele.offsetParent;
	 var left=ele.offsetLeft;
	 while(p){
	  if(window.navigator.userAgent.indexOf("MSIE 8")>-1){
	   left+=p.offsetLeft;
	   }else{
	   left+=p.offsetLeft+p.clientLeft; 
	    }
	    p=p.offsetParent;
	  //left+=p.offsetLeft;
	//  p=p.offsetParent;
	  }
	/*  var obj={};
	 obj.x=left; */
	 return left;
}
function showMoreCDT(){
	$(".cdtBox div").each(function(){
		$(this).hide();
	});
	var ct = $(".curM")[0].name;
	if($("#moreCondition").hasClass("cdtOn")){
		$("#"+ct+"MC").show();
		$(".cdtBox").show();
	}else{
		$("#"+ct+"MC").hide();
		$(".cdtBox").hide();
	}
	$("#"+ct+"MC").find(".searchButt").trigger("click");
}
function loadDate(){
	$("#pageStage").load("${webroot}/cdc/f_view/pages.shtml?ct="+$(".curM")[0].name);
}
function extractPatientInfo(){
	$("#extract").dialog("open");
}
function query(id){
	$("#pageStage").load("${webroot}/cdc/f_view/"+id+"page.shtml",$("#"+id+"Form").serialize());
}
</script>
</html>