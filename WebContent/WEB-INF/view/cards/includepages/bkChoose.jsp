<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<div id="cdcbkChoose">
	<table id="BKS"></table>
	
	<div id="cardsPlace" style="padding:0px;" class="bk_up" style='<c:if test="${isShowCards eq 'hide'}">display:none;</c:if>'>
		<input type="hidden" id="curZyid" value="${curZyid}"/>
		<input type="hidden" id="curMzid" value="${curMzid}"/>
		<div class="bk_up_class">
			<span id="toLeft" style="position: absolute;display: block;width:20px;height:50px;text-align: center;border-left:1px solid #ddd;border-right:1px solid #ddd;padding-top:25px;background-color: #fff;z-index: 10;cursor: not-allowed;" onclick="Right();">&lt;</span>
			<span id="toRight" style="position: absolute;right:0px;display: block;width:20px;height:50px;text-align: center;border-left:1px solid #ddd;border-right:1px solid #ddd;padding-top:25px;background-color: #fff;z-index: 10;cursor: pointer;" onclick="Left();">&gt;</span>
			<ul class="allcardsLogo" style="margin-left: 13px;padding:3px 5px;width:${fn:length(AllBK)*100}px;position: relative;left:0px;">
				<c:forEach items="${AllBK}" var="abk">
					<c:if test="${fn:contains(cdcScope,abk.dictCode) or fn:contains(cdcScope,'all')}">
						<li>
							<div class="bk_img2" style="padding-top: 5px;padding-bottom: 5px;">
								<a onclick="chooseBk('${abk.dictCode}');" style="" ><img src="${webroot}/resources/images/${abk.dictCode}.png" style="display: block;margin-left: 35px;" onerror="nofindlogo();"/>${abk.dictName}</a>
							</div>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
//parent是否存在
var isParentExist = true;
$(function(){
	
	try{
		if(parent && parent.menuInfo){
			isParentExist = true;
		}else{
			isParentExist = false;
		}
	}catch (e) {
		isParentExist = false;
	}
	
	var zyid="${curZyid}";
	var mzid="${curMzid}";
	$('#BKS').datagrid({
		url:'${webroot}/cdc/f_json/bkChoose.shtml',
		queryParams: {
     		'id': (!zyid||zyid=="null"?mzid:zyid),
     		'cardType':(!zyid?'mz':'zy')
        },
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
				return "<a class='ico_view' href=\"javascript:detail('"+row['cardType']+"','"+row['zyid']+"','"+row['mzid']+"','"+row['masterid']+"');\"></a>";
			}}
		]],
		onLoadSuccess:function(){
			if(!zyid){
				//隐藏zyid
				$("#BKS").datagrid('hideColumn', "zyid");
			}else{
				$("#BKS").datagrid('hideColumn', "mzid");
			}
		}
	});
});
	function nofindlogo(){
		var img=event.srcElement;
		img.src="${webroot}/resources/images/forbidden.png";
		img.onerror=null;
	}
	/* function queryData(zyid,mzid){
		if(window["console"]){
			console.log("开始查询数据:"+new Date().getMinutes()+"  --  "+new Date().getSeconds()+"  --  "+new Date().getMilliseconds());
		}
		var act_url="${webroot}/cdc/f_json/bkChoose.shtml";
		$('#BKS').datagrid({
	        url: act_url,
	        queryParams: {
	     		'id': (!zyid||zyid=="null"?mzid:zyid),
	     		'cardType':(!zyid?'mz':'zy')
	        },
	        onLoadSuccess: function (data) {
	        	if(window["console"]){
	        		console.log("数据查询成功:"+new Date().getMinutes()+"  --  "+new Date().getSeconds()+"  --  "+new Date().getMilliseconds());
	        	}
	        }
	    });
	} */
/* 	function detail(cardtype,zyid,mzid,msid){
		if(msid){
			if(cardtype=='死因报卡'){
				parent.menuInfo.clickMenu('死因上报记录查看','/cdc/f_view/deathReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			}else if(cardtype=='法定传染病报卡'){
				parent.menuInfo.clickMenu('传染病上报记录查看','/cdc/f_view/reportCardZY.shtml?zyid='+zyid+'&masterid='+msid+'&justLook=Y',true);
			}else if(cardtype=='食源监测报卡'){
				parent.menuInfo.clickMenu('食源监测上报记录查看','/cdc/f_view/fsmReport.shtml?zyid='+zyid+'&masterid='+msid+'&justLook=Y',true);
			}else if(cardtype=='肿瘤病例报卡'){
				parent.menuInfo.clickMenu('肿瘤病例上报记录查看','/cdc/f_view/tumourReport.shtml?zyid='+zyid+'&masterid='+msid+'&justLook=Y',true);
			}else if(cardtype=='食源异常报卡'){
				parent.menuInfo.clickMenu('食源异常上报记录查看','/cdc/f_view/fsaReport.shtml?zyid='+zyid+'&masterid='+msid+'&justLook=Y',true);
			}else if(cardtype=='心脑血管报卡'){
				parent.menuInfo.clickMenu('心脑血管事件上报记录查看','/cdc/f_view/hcvReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
			}
			Comm.dialogClose('${param.dialogId}');
		}else{
			$.messager.show({ title: '提示', msg: '查看详情失败：必要参数获取失败！' });
		}
	} */

	function detail(cardtype,zyid,mzid,msid){
		zyid = (!zyid||zyid=='null')?"":zyid;
		mzid = (!mzid||mzid=='null')?"":mzid;
		if(msid){
			if(cardtype=='死因报卡'){
				if(isParentExist){
					parent.menuInfo.clickMenu('死因报卡详情','/cdc/f_view/deathReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
				}else{
					window.parent.location.href = '${webroot}/cdc/f_view/deathReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
				}
			}else if(cardtype=='法定传染病报卡'){
				if(!zyid || zyid=='null'){
					if(isParentExist){
						parent.menuInfo.clickMenu('传染病报卡详情','/cdc/f_view/reportCardMZ.shtml?mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
					}else{
						window.parent.location.href = '${webroot}/cdc/f_view/reportCardMZ.shtml?mzid='+mzid+'&masterid='+msid+'&justLook=Y';
					}
				}else{
					if(isParentExist){
						parent.menuInfo.clickMenu('传染病报卡详情','/cdc/f_view/reportCardZY.shtml?zyid='+zyid+'&masterid='+msid+'&justLook=Y',true);
					}else{
						window.parent.location.href = '${webroot}/cdc/f_view/reportCardZY.shtml?zyid='+zyid+'&masterid='+msid+'&justLook=Y';
					}	
				}
			}else if(cardtype=='食源监测报卡'){
				if(isParentExist){
					parent.menuInfo.clickMenu('食源监测报卡详情','/cdc/f_view/fsmReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
				}else{
					window.parent.location.href = '${webroot}/cdc/f_view/fsmReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
				}	
			}else if(cardtype=='肿瘤病例报卡'){
				if(isParentExist){
					parent.menuInfo.clickMenu('肿瘤病例报卡详情','/cdc/f_view/tumourReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
				}else{
					window.parent.location.href = '${webroot}/cdc/f_view/tumourReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
				}	
			}else if(cardtype=='食源异常报卡'){
				if(isParentExist){
					parent.menuInfo.clickMenu('食源异常报卡详情','/cdc/f_view/fsaReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
				}else{
					window.parent.location.href = '${webroot}/cdc/f_view/fsaReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
				}	
			}else if(cardtype=='心脑血管报卡'){
				if(isParentExist){
					parent.menuInfo.clickMenu('心脑血管报卡详情','/cdc/f_view/hcvReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
				}else{
					window.parent.location.href = '${webroot}/cdc/f_view/hcvReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
				}	
			}else if(cardtype=='高温中暑报卡'){
				if(isParentExist){
					parent.menuInfo.clickMenu('高温中暑报卡详情','/cdc/f_view/sunstrokeReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
				}else{
					window.parent.location.href = '${webroot}/cdc/f_view/sunstrokeReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
				}	
			}else if(cardtype=='农药中毒报卡'){
				if(isParentExist){
					parent.menuInfo.clickMenu('农药中毒报卡详情','/cdc/f_view/nyzdReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y',true);
				}else{
					window.parent.location.href = '${webroot}/cdc/f_view/nyzdReport.shtml?zyid='+zyid+'&mzid='+mzid+'&masterid='+msid+'&justLook=Y';
				}
			}
			parent.Comm.dialogClose('${param.dialogId}');
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
				if(isParentExist){
					parent.menuInfo.clickMenu('传染病报卡上报','/cdc/f_view/reportCardMZ.shtml?mzid='+mzid,true);
				}else{
					window.parent.location.href ='${webroot}/cdc/f_view/reportCardMZ.shtml?mzid='+mzid;
				}
			}else{
				if(isParentExist){
					parent.menuInfo.clickMenu('传染病报卡上报','/cdc/f_view/reportCardZY.shtml?zyid='+zyid,true);
				}else{
					window.parent.location.href ='${webroot}/cdc/f_view/reportCardZY.shtml?zyid='+zyid;
				}
			}
		}else if(rc == "syjcbk"){
			if(isParentExist){
				parent.parent.menuInfo.clickMenu('食源监测报卡上报','/cdc/f_view/fsmReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
			}else{
				window.parent.location.href ='${webroot}/cdc/f_view/fsmReport.shtml?zyid='+zyid+'&mzid='+mzid;
			}
		}else if(rc == "sybk"){
			if(isParentExist){
				parent.parent.menuInfo.clickMenu('居民死因报卡上报','/cdc/f_view/deathReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
			}else{
				window.parent.location.href ='${webroot}/cdc/f_view/deathReport.shtml?zyid='+zyid+'&mzid='+mzid;
			}
		}else if(rc == "zlbk"){
			if(isParentExist){	
				parent.parent.menuInfo.clickMenu('肿瘤病例报卡上报','/cdc/f_view/tumourReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
			}else{
				window.parent.location.href ='${webroot}/cdc/f_view/tumourReport.shtml?zyid='+zyid+'&mzid='+mzid;
			}
		}else if(rc == "syycbk"){
			if(isParentExist){	
				parent.parent.menuInfo.clickMenu('食源异常报卡上报','/cdc/f_view/fsaReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
			}else{
				window.parent.location.href ='${webroot}/cdc/f_view/fsaReport.shtml?zyid='+zyid+'&mzid='+mzid;
			}
		}else if(rc == "xnxgbk"){
			if(isParentExist){	
				parent.parent.menuInfo.clickMenu('心脑血管事件报卡上报','/cdc/f_view/hcvReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
			}else{
				window.parent.location.href ='${webroot}/cdc/f_view/hcvReport.shtml?zyid='+zyid+'&mzid='+mzid;
			}
		}else if(rc == "gwzsbk"){
			if(isParentExist){	
				parent.parent.menuInfo.clickMenu('高温中暑病例报卡上报','/cdc/f_view/sunstrokeReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
			}else{
				window.parent.location.href ='${webroot}/cdc/f_view/sunstrokeReport.shtml?zyid='+zyid+'&mzid='+mzid;
			}	
		}else if(rc=="nyzdbk"){
			if(isParentExist){
				parent.menuInfo.clickMenu('农药中毒报卡上报','/cdc/f_view/nyzdReport.shtml?zyid='+zyid+'&mzid='+mzid,true);
			}else{
				window.parent.location.href = '${webroot}/cdc/f_view/nyzdReport.shtml?zyid='+zyid+'&mzid='+mzid;
			}
		}
		parent.Comm.dialogClose('${param.dialogId}');
	}
	function Left(){
		var allWidth = $(".allcardsLogo").width();   //logo全宽
		//var width = $("#cardsPlace").width()-$("#toLeft").width()-$("#toRight").width();   //当前容器宽度-左右图标宽度=可显示宽度
		var width = 800;
		var maxOffset = allWidth-width;  //最大偏移量
		var Offset = parseInt($(".allcardsLogo").css("left").replace("px",""));	//偏移量 去除px
		var surplusOffset = maxOffset-Math.abs(Offset);
		var singleWidth = $(".bk_img2").width();
		if(maxOffset<=0){
			//logo的宽还没有显示的宽宽，就不用滑动
			//左划禁用
			$("#toRight").css("cursor","not-allowed");
			//右划禁用
			$("#toLeft").css("cursor","pointer");
		}else if(surplusOffset-width>singleWidth){ //左移后还有未显示的,直接翻一整页
			$(".allcardsLogo").animate({left:Offset-width});  //left为负数实现左划
		}else{
			//不够下一页了，直接移到最后
			$(".allcardsLogo").animate({left:-(maxOffset)});  //left为负数实现左划
			//左划禁用
			$("#toRight").css("cursor","not-allowed");
			//右划禁用
			$("#toLeft").css("cursor","pointer");
		}
	}
	function Right(){
		var allWidth = $(".allcardsLogo").width();   //logo全宽
		//var width = $("#cardsPlace").width()-$("#toLeft").width()-$("#toRight").width();   //当前容器宽度-左右图标宽度=可显示宽度
		var width = 800;
		var maxOffset = allWidth-width;  //最大偏移量
		var Offset = parseInt($(".allcardsLogo").css("left").replace("px",""));	//偏移量 去除px
		var surplusOffset = Math.abs(Offset)-width;
		var singleWidth = $(".bk_img2").width();
		if(Offset==0){
			//右划禁用
			$("#toLeft").css("cursor","not-allowed");
			//右划启用
			$("#toRight").css("cursor","pointer");
		}else if(Math.abs(Offset)-width>singleWidth){ //左移后还有未显示的,直接翻一整页
			$(".allcardsLogo").animate({left:(Offset+width)});  //left为正数实现右划
		}else{
			//不够下一页了，直接移到最后
			$(".allcardsLogo").animate({left:0});  //left为正数实现右划
			//右划禁用
			$("#toLeft").css("cursor","not-allowed");
			//右划启用
			$("#toRight").css("cursor","pointer");
		}
	}
</script>