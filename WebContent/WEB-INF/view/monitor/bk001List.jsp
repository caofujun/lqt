<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>首页</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script src="${webroot}/resources/js/emptyCards.js"></script>
<style>
	.select{
    	color: #FF5D25 !important;
		font-weight: bold;
    	text-decoration: underline !important;
	}
</style>
</head>
<body>
<div id="bk001SbkPanel" style="width:100%;"></div>
<div id="tb" class="m_search search_list">
	<div class="m_search_tabs cur" id="zy" onclick="bk001Sbk.chooseHospType(1,'zy')">在院</div>
	<div class="m_search_tabs" id="cy" onclick="bk001Sbk.chooseHospType(2,'cy')">出院</div>
	<div id="" class="m_search_first">
		<span id="searchDate" style="display:none; float:left; margin-right:5px;">
			<input type="text" id="startDate" value="${startDate}"  class="Wdate text" onclick="WdatePicker()"  style="width:85px;"/>~
			<input type="text" id="endDate" value="${endDate}" class="Wdate text"  onclick="WdatePicker()"  style="width:85px;"/>
		</span>
		<div class="search_input">
			<input type="text" class="auto-tip input_tip" style="width:150px" data-tip="姓名/${patientZyTitle}" id="searchString" />
		</div>
		<div class="n_btn_blue">
			<a href="javascript:;" id="query" onclick="updateTo=true;$('.select').removeClass('select');bk001Sbk.query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			<a href="javascript:;" style="position:absolute;top:-100px;" class="toTrigger" onclick="bk001Sbk.query();"></a>
		</div>
	</div>
	
	<c:if test="${fn:contains(systemScope,'cdc')}">
	<div class="btn_r">
		<div class="n_btn_blue">
			<a href="javascript:void(0);" onclick="showDialog();"><span>实时提取患者信息</span></a>
		</div>
		<select id="emptyCard" onclick="EC.emptySelect();">
			<option value="">选择空卡上报...</option>
			<c:forEach items="${AllBK}" var="abk">
				<c:if test="${fn:contains(cdcScope,abk.dictCode) or fn:contains(cdcScope,'all')}">
					<option value="${abk.dictCode}_empty">${abk.dictName}</option>
				</c:if>
			</c:forEach>
		</select> 
	</div>
	</c:if> 
</div>
 <!-- style="background-color: #ffffff; padding:5px 10px;position: fixed;bottom: 0px;z-index: 99;width: 100%;border-top: 1px solid #efefef;"  -->
<div class="m_search search_list" style="padding:10px">
	<div class="m_search_center" style="margin-left:10px;">
		<span class="m_search_center_title">本页合计：</span>
		<div class="m_search_center_a">
			<a href="javascript:void(0);" id="patientRange" style="text-align: center;width:120px;" onclick="updateTo=true;dnlx='1';$('.select').removeClass('select');bk001Sbk.setPatientType(this);" value="1" title="设置全科患者或我的患者">本科患者&nbsp;<span id="allCount"></span>&nbsp;人</a>
			<a href="javascript:void(0);" id="ygyj" name="ygyj" tvalue="1" onclick="selectMe(this);updateTo=false;bk001Sbk.query();">院感(预警)&nbsp;<span id="ygyjCount"></span>&nbsp;人</a>
			<a href="javascript:void(0);" id="ygqr" name="ygqr" tvalue="1" onclick="selectMe(this);updateTo=false;bk001Sbk.query();">院感(已确认)&nbsp;<span id="ygqrCount"></span>&nbsp;人</a>
			<c:if test="${iszd==0}">
				<a href="javascript:void(0);" id="mdro" name="mdro" tvalue="" onclick="selectMe(this);updateTo=false;bk001Sbk.query();">多耐菌&nbsp;<span id="MDRoCount"></span>&nbsp;人</a>
			</c:if>
			<a href="javascript:void(0);" id="zd" name="zd" tvalue="" onclick="selectMe(this);updateTo=false;bk001Sbk.query();">重点菌&nbsp;<span id="zdCount"></span>&nbsp;人</a>
			<c:if test="${ISDB==1}">
				<a href="javascript:void(0);" id="dbhz" name="dbhz" tvalue="1" onclick="selectMe(this);updateTo=false;bk001Sbk.query();">传染病待报&nbsp;<span id="dbhzCount"></span>&nbsp;人</a>
			</c:if>
		</div> 
	</div>
</div>

<div id="getPatientById" style="padding: 5px;">
	<div id="patientInfo">
		<div style="margin-bottom: 5px;">
			<div style="width: 80px;text-align: right;display: inline-block;">
				<span>患者类型：</span>
			</div>
			<select id="patientType">
				<option value="zy">住院</option>
			</select>
		</div>
		<div style="margin-bottom: 5px;">
			<div style="width: 80px;text-align: right;display: inline-block;">
				<span>患者关键字：</span>
			</div>
			<input type="text" class="auto-tip input_tip" style="width:150px" data-tip="姓名/住院号" id="patient"/>
		</div>
		<div>
			<div style="width: 80px;text-align: right;display: inline-block;">
				<span>入院日期：</span>
			</div>
			<input type="text" class="Wdate" style="width:100px" id="startTime" value="${startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>~<input type="text" class="Wdate" style="width:100px" id="endTime" value="${endTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
			<div class="n_btn_blue" style="float:right;margin-right: 10px;">
				<a href="javascript:;" onclick="showProgressBar();"><i class="icon iconfont">&#xe688;</i><span>提取</span></a>
			</div>
		</div>
	</div>
	<div id="progressBar" style="display: none;">
		<div style="margin: 0px 5px;height: 32px;">
			<span style="" id="msg"></span>
			<div class="n_btn_blue" style="float:right;" id="cancelBut">
				<a href="javascript:;" onclick="cencelbar();"><i class="icon iconfont">&#xe688;</i><span>取消</span></a>
			</div>
		</div>
		<div style="margin: 10px 5px 0px 5px;border: 1px solid #ddd;height: 15px;" id="allbarwidth">
			<div id="nowprogress" style="width:0;background-color: #5eb31a;height: 15px;"></div>
		</div>
	</div>
</div>
<script>
var isInHosp='1';
function cgPg(zyid){
	parent.parent.menuInfo.clickMenu('插管评估','/gm004Jcmx/f_view/cgPgList.shtml?zyid='+zyid,true);
}
var dnlx="1";
var bk001Sbk = {
		panel : 'bk001SbkPanel',
		//查询
		query : function() {
			//$(".select").removeClass("select");
			//清空记录
			yjcount = 0;
			qrcount = 0;
			 mdroCountAll = 0;
			 mdroCountY = 0;
			 zdCountAll = 0;
			 zdCountY = 0;
			dbcount = 0;
			if(updateTo){
				$("#ygyjCount").html('');
	        	$("#ygqrCount").html('');
	        	$("#MDRoCount").html('');
	        	$("#zdCount").html('');
	        	$("#dbhzCount").html('');
			}
			autoTip.clear();
	        $('#'+bk001Sbk.panel).datagrid({
	            queryParams: {
	            	'startDate': $('#startDate').val(),
	                'endDate': $('#endDate').val(),
	                'isInHosp':isInHosp,
	                'searchString': $('#searchString').val(),
	                'patientType': $(".cur").attr("tvalue"),
	                'patientRange': $("#patientRange").val(),
	                'ygyj': $("#ygyj").hasClass("select")?$("#ygyj").attr("tvalue"):"",
	                'ygqr': $("#ygqr").hasClass("select")?$("#ygqr").attr("tvalue"):"",
	                'dnlx': $("#mdro").hasClass("select")?$("#mdro").attr("tvalue"):"1",
	                'ydnlx': $("#zd").hasClass("select")?$("#zd").attr("tvalue"):"1",		
               		'dbhz': $("#dbhz").hasClass("select")?$("#dbhz").attr("tvalue"):""
	            }
	        });
	    },

	    chooseHospType:function(inHosp,id){
	    	isInHosp = inHosp;
	    	if(id=='zy'){
		    	$('#searchDate').hide();
	    	}else{
		    	$('#searchDate').show();
	    	}
	    	$('#zy').attr("class","m_search_tabs");
	    	$('#cy').attr("class","m_search_tabs");
	    	$('#mz').attr("class","m_search_tabs");
	    	$('#'+id).attr("class","m_search_tabs cur");
	    	bk001Sbk.query();
	    },
	    showDetail:function(zyid){
	    	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
	    },
	  //院感社感切换
    	setPatientType : function(obj) {
    		var value = $(obj).attr('value');
    		if (value == '1') {
    			$(obj).html('我的患者&nbsp;<span id="allCount"></span>&nbsp;人');
    			$(obj).attr('value', 2);
    		} else {
    			$(obj).html('本科患者&nbsp;<span id="allCount"></span>&nbsp;人');
    			$(obj).attr('value', 1);
    		}
    		bk001Sbk.query();
    	}
	};
var yjcount = 0;
var qrcount = 0;
var mdroCountAll = 0;
var mdroCountY = 0;
var zdCountAll = 0;
var zdCountY = 0;
var cyjCount = 0;
var dbcount = 0;
var updateTo = true;
$(document).ready(function () {
	$("#cdcbkChoose").show();
	autoTip.clear();
	$('#'+bk001Sbk.panel).datagrid({
        fit: false,
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        fitColumns: true,
        collapsible:true,
        height:$(window).height()-50,
        border:true,
        url:'${webroot}/bk001Sbk/f_json/pageQuery.shtml',
        queryParams: {
        	'startDate': $('#startDate').val(),
            'endDate': $('#endDate').val(),
            'isInHosp':isInHosp,
            'searchString': $('#searchString').val(),
            'patientType': $(".cur").attr("tvalue"),
            'patientRange': $("#patientRange").val(),
            'ygyj': $("#ygyj").hasClass("select")?$("#ygyj").attr("tvalue"):"",
	        'ygqr': $("#ygqr").hasClass("select")?$("#ygqr").attr("tvalue"):"",
	        'ydnlx': $("#zd").hasClass("select")?$("#zd").attr("tvalue"):"1",
	        'dnlx': $("#mdro").hasClass("select")?$("#mdro").attr("tvalue"):"1"
        },
        remoteSort: false,
        singleSelect: true,
        rownumbers:true,
        onBeforeSortColumn:beforeSort,
        columns:[
        	[
                {field:'yj',title:'预警',sortable:true,width:25,
                	formatter:function(value,r){
                		var yj = '';
                		if(r.fhyj!=null){
                			yj = yj + '<a href="javascript:openYj(\''+r.zyid+'\');" class="frame_a"><span class="frame_blue">院</span><span class="blue">'+r.fhyj+'</span></a>';
                			yjcount++;
                		
                		}
                	<c:if test="${EWLOGO==1}">
                		if(r.cyjCount!=null && r.cyjCount!=0){
                			yj = yj + '<a href="javascript:openCRBYJ(\''+r.zyid+'\',\''+r.inHospAt+'\');" class="frame_a"><span class="frame_green">传</span><span class="green">'+r.cyjCount+'</span></a>';
                			cyjCount++;
                		}
                	</c:if>
                		return [yj].join('');
                	}
                },
                {field:'ju',title:'菌',sortable:true,width:25,
                	formatter:function(value,r){
                		var yj = '';
                		
                		if(r.mdroYCount!=null && r.mdroYCount!=0){             		
                			mdroCountY++;
                		}
                		if(r.mdroCount!=null && r.mdroCount!=0){
                			yj = yj + '<a href="javascript:openMDRo(\''+r.patientName+'\',\''+r.zyid+'\',\''+r.bedNo+'\',\''+r.mdroCount+'\',\'0\');" title="多耐菌,总数：'+r.mdroCount+'  未处理：'+r.mdroYCount+'" class="frame_a"><span class="frame_green2">多</span><span class="">'+r.mdroYCount+'/'+r.mdroCount+'</span></a>';
                			mdroCountAll++;
                		}
                		if(r.zdYCount!=null && r.zdYCount!=0){             		
                			zdCountY++;
                		}
                		if(r.zdCount!=null && r.zdCount!=0){
                			yj = yj + '<a href="javascript:openMDRo(\''+r.patientName+'\',\''+r.zyid+'\',\''+r.bedNo+'\',\''+r.zdCount+'\',\'1\');" title="重点菌,总数：'+r.zdCount+'  未处理：'+r.zdYCount+'" class="frame_a"><span class="frame_orange2">重</span><span class="">'+r.zdYCount+'/'+r.zdCount+'</span></a>';
                			zdCountAll++;
                		}
                	
                		return [yj].join('');
                	}
                },
                {field:'bk',title:'报卡',sortable:true,width:18,
                	formatter:function(value,r){
                		var bk = '';
                		var crb = r.crbCount+r.syCount+r.jcCount+r.tmCount+r.ycCount+r.xnCount+r.zsCount+r.nyCount;
                		if(r.bkCount!=0){
                			bk = bk + '<a href="javascript:openBk(\''+r.zyid+'\');" class="frame_a"><span class="frame_blue">感</span><span class="blue">'+r.bkCount+'</span></a>';
                			qrcount++;
                		}
                		if(crb!=0){
                			bk = bk + '<a href="javascript:crbbk(\''+r.patientName+'\',\''+r.zyid+'\');" class="frame_a"><span class="frame_green">公</span><span class="green">'+crb+'</span></a>';
                		}
                		
                		return [bk].join('');
					}
                },
                /*{field:'patientId',title:'病案号',sortable:true,width:20},*/
                {field:'zyid',title:'${patientZyTitle}',sortable:true,width:20,
					formatter:function(value,r){
						return ['<a href="javascript:bk001Sbk.showDetail(\'',r.zyid,'\',\'\');">',r.${patientZyValue},'</a>'].join('');
					}
				},
                {field:'patientName',title:'患者',sortable:true,width:10},
                {field:'bedNo',title:'床号',sortable:true,width:10},
                {field:'deptName',title:'当前所在科',sortable:true,width:22},
                {field:'inHospAt',title:'入院时间',sortable:true,width:22},
                {field:'diagnosisName',title:'入院诊断',sortable:true,width:28},
                {field:'outAt',title:'出院时间',sortable:true,width:22},
                {field:'zg',title:'转归',sortable:true,width:12},
                {field:'yqxCount',title:'操作',sortable:true,width:20,
                	formatter:function(value,r){
                		var tmp = '';
                		if('${systemScope}'.indexOf('nis') >= 0){
                			tmp += '<a href="javascript:bk(\''+r.zyid+'\');" class="ico_up_blue" title="院感上报"></a>';
						}
                		if('${systemScope}'.indexOf('cdc') >= 0){
	               			tmp += '<a href="javascript:crbbk(\''+r.patientName+'\',\''+r.zyid+'\',\'\');" class="ico_up_green" title="公卫卡上报"></a>';
						}
                		if('${cgpg}' == '0'){
                			tmp += '<a href="javascript:cgPg(\''+r.zyid+'\');" class="ico_check" title="评估插管"></a>';
                		}
                		tmp += '<a href="javascript:sendMessage(\''+r.zyid+'\');" class="ico_mail" title="消息"></a>';
                		<c:if test="${ISDB==1}">
	                		if(r.isdb==1){
	                			tmp +='<a href="javascript:removeDbRecord(\''+r.zyid+'\',\'ZY\');" class="ico_fork"  title="移除传染病待报"></a>';
	                			dbcount++;
	                		}
                		</c:if>
						return [tmp].join('');
					}
                }
            ]
        ],
        toolbar:'#tb',
        onLoadSuccess:function(data){
        	//获取所有预警条数
        	if(updateTo){
        		$("#allCount").html($('#'+bk001Sbk.panel).datagrid("getRows").length);
	        	$("#ygyjCount").html(yjcount);
	        	$("#ygqrCount").html(qrcount);
	        	$("#MDRoCount").html(mdroCountAll);
	        	$("#zdCount").html(zdCountAll);
	        	$("#dbhzCount").html(dbcount);
        	}else if($("#ygyj").hasClass("select")){
        		//如果选中预警或确认，就单独更新数目
        		$("#ygyjCount").html($('#'+bk001Sbk.panel).datagrid("getRows").length);
        	}else if($("#ygqr").hasClass("select")){
        		//如果选中预警或确认，就单独更新数目
        		$("#ygqrCount").html($('#'+bk001Sbk.panel).datagrid("getRows").length);
        	}else if($("#mdro").hasClass("select")){
        		//如果选中预警或确认，就单独更新数目
        		$("#mdroCount").html($('#'+bk001Sbk.panel).datagrid("getRows").length);
        	}else if($("#dbhz").hasClass("select")){
        		$("#dbhzCount").html($('#'+bk001Sbk.panel).datagrid("getRows").length);
        	}
        }
    });
	
	$("#getPatientById").dialog({
		title: "从HIS提取患者信息",
	    width: 455,
	    height: 160,
	    closed: true,
	    cache: false,
	    modal: true,
	    onClose:function(){
	    	if(interval){
	    		clearInterval(interval);
	    	}
	    	$("#patient").val("");
	    	hideProgressBar();
	    	//$("#msg").html("");
	    }
	});
	
});
function beforeSort(){
	updateTo = false;
}

/* function emptyCard(){
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
		parent.menuInfo.clickMenu('心脑血管事件报卡上报','/cdc/f_view/hcvReport.shtml?type=empty',true);
	}else if(curCT=="gwzsbk_empty"){
		parent.menuInfo.clickMenu('高温中暑病例报卡上报','/cdc/f_view/sunstrokeReport.shtml?type=empty',true);
	}
	$("#emptyCard option[value='']").attr("selected","selected");
}
 */
function sendMessage(zyid){
	Comm.dialogGlobal({
    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid="+zyid+"&msgType=1",
        title: "干预会话",
        width:870,
        height:555,
        type:"iframe"
    });
}

function openYj(zyid){
	Comm.dialog({
    	url:"${webroot}/gr002YsgrMx/f_view/toWarningPatient.shtml?zyid="+zyid+"&type=wait&infectTypeId=1&diagType=1",
        title: "预警",
        width:870,
        height:555,
        type:"iframe",
        parent:this
    });
}
function openMDRo(pname,zyid,bedNo,mdroCount,zhong){
	var titles="";
	if(bedNo=='null'){
		bedNo="";
		//titles=pname+"&nbsp;&nbsp;&nbsp;&nbsp;"+ "<font size='2'>"+zyid+"</font>"+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<span id='bedNum' style=display:none>"+"床号:"+bedNo+"</span>"+ "&nbsp;&nbsp;&nbsp;&nbsp;"+ "<font color='red' size='4'>"+mdroCount+"</font>"+"&nbsp;"+"株多重耐药菌未处理";
		titles=pname+"&nbsp;&nbsp;&nbsp;&nbsp;"+ "<font size='2'>"+zyid+"</font>"+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<span id='bedNum' style=display:none>"+"床号:"+bedNo+"</span>"+ "&nbsp;&nbsp;&nbsp;&nbsp;";
	}else{
		//titles=pname+"&nbsp;&nbsp;&nbsp;&nbsp;"+ "<font size='2'>"+zyid+"</font>"+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<span id='bedNum' >"+"床号:"+bedNo+"</span>"+ "&nbsp;&nbsp;&nbsp;&nbsp;"+ "<font color='red' size='4'>"+mdroCount+"</font>"+"株多重耐药菌未处理";
		titles=pname+"&nbsp;&nbsp;&nbsp;&nbsp;"+ "<font size='2'>"+zyid+"</font>"+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<span id='bedNum' >"+"床号:"+bedNo+"</span>"+ "&nbsp;&nbsp;&nbsp;&nbsp;";
	}
	
	Comm.dialog({
    	url:"${webroot}/xn011Dclymx/f_view/toWarningPatientMDRo.shtml?zyid="+zyid+"&bedNo="+bedNo+"&zhong="+zhong,
        title: titles,
        width:870,
        height:555,
        type:"iframe"
    });
}
function openCRBYJ(zyid,yjdate){
	parent.menuInfo.clickMenu('预警病例查询','/cdc/f_view/suspectedCase.shtml?patientType=ZY&patientName='+zyid+'&queryStartDate='+yjdate.substring(0,10)+'&dateType=2&deptType=4&',true);
}
function openBk(zyid){
	Comm.dialog({
    	url:"${webroot}/bk001Sbk/f_view/findBkListByZyid.shtml?zyid="+zyid,
        title: "报卡",
        width:870,
        height:555,
        type:"iframe"
    });
}

function bk(zyid){
	parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid,true);
}

function crbbk(pname,zyid,mzid){
	Comm.dialogGlobal({
    	url:"${webroot}/cdc/f_view/toChooseCard.shtml?zyid="+zyid+"&mzid="+mzid+"&ownership=${param.ownership}",
    	title: "病例上报 - 病人:"+pname+"("+(!zyid?mzid:zyid)+")	 - 已报卡列表",
        width:850,
        type:"iframe",
        height:430
    });
}
function selectMe(ele){
	if($(ele).hasClass("select")){
		$(".select").removeClass("select");
	}else{
		$(".select").removeClass("select");
		$(ele).addClass("select");
	}
}
function showDialog(){
	//居中判断
	var top = document.body.scrollTop;
	var mtop = parseInt(($(window).height()-130)/2);
	if(mtop>0){
		top += mtop;
	}
	$("#getPatientById").window('open').window('resize',{top: top});
}
function showProgressBar(){
	var pv = ($("#patient").val()=="姓名/住院号"?"":$("#patient").val());
	var stdate = $("#startTime").val();
	var etdate = $("#endTime").val();
	if(!pv){
		$.messager.show({ title: '提示', msg: '请填写患者关键字！' });
		$("#patient").focus();
	}else if(!stdate){
		$.messager.show({ title: '提示', msg: '请填写入院日期范围！' });
		$("#startTime").focus();
	}else if(!etdate){
		$.messager.show({ title: '提示', msg: '请填写入院日期范围！' });
		$("#endTime").focus();
	}else{
		$("#patientInfo").slideUp(500,function(){
			$("#progressBar").slideDown(500,function(){
				updateProgressBar();
			});
		});
	}
}
function hideProgressBar(){
	$("#progressBar").slideUp(500,function(){
		$("#patientInfo").slideDown(500,function(){
			initProgressBar();
		});
	});
}
var interval;
function updateProgressBar(){
	$("#msg").html("获取数据时间长短取决于网络快慢和数据大小，请耐心等待！");
	interval = setInterval("normalbar()",1500);
	$.ajax({
		url:"${webroot}/cdc/f_json/getZYPatientFromHIS.shtml",
		type:"POST",
		data:{
			'searchKey':$("#patient").val(),
			'startTime':$("#startTime").val(),
			'endTime':$("#endTime").val()
		},
		success:function(data){
			data = eval("("+data+")");
			if(data.result=='success'){
				successbar(data.msg);
			}else{
				errorbar(data.msg);
			}
		},error:function(data, status, e){
			alert("抱歉，操作出错！");
		}
	});
}
function initProgressBar(){
	$("#msg").css("color","#000").html("");
	$("#cancelBut").show();
	$("#allbarwidth").css("border-color","#ddd").show();
	$('#nowprogress').css("background-color","#5eb31a").width("0");
}
function normalbar(){
	var aw = $("#allbarwidth").width();
	var nw = $('#nowprogress').width();
	//距离差 (aw-nw)*0.01+nw
	var newwidth = (aw-nw)*(Math.floor((Math.random()*10))/100)+nw;
	$('#nowprogress').width(newwidth);
	/* if(count>10){
		//successbar("共获取到3条数据！窗口将在3秒后关闭！XXXXXXXXXXXXXXXXXXX");
		errorbar("程序执行出错！请联系管理人员！");
	} */
}
function errorbar(errorMsg){
	clearInterval(interval);
	$("#cancelBut").hide();
	$('#nowprogress').css("background-color","#c30000");
	$('#allbarwidth').css("border-color","#b90000");
	$('#msg').css("color","#ec4040").html("抱歉！数据获取失败！错误信息："+errorMsg);
}
function successbar(successMsg){
	clearInterval(interval);
	$('#nowprogress').width("100%");
	$('#msg').css("color","#5eb31a").html((!successMsg?"":successMsg)+"  窗口将在3秒后关闭！");
	$("#cancelBut").hide();
	$("#searchString").val($("#patient").val());
	//if(!successMsg){
	//	$("#searchString").val($("#patient").val());
	//}
	//$("#allbarwidth").hide();
	setTimeout("closeDialog()",3000);
	isMzProgrem = '1';
	bk001Sbk.query();
	setTimeout("isMzProgrem='0'",1000);
}
function cencelbar(){
	clearInterval(interval);
	$("#cancelBut").hide();
	$('#nowprogress').css("background-color","#b3b3b3");
	$('#allbarwidth').css("border-color","#b3b3b3");
	$('#msg').css("color","#b3b3b3").html("用户取消操作！");
	setTimeout("hideProgressBar()",1500);
}
function closeDialog(){
	$("#getPatientById").dialog("close");
}
function removeDbRecord(zyid,patientType){
	if(zyid && patientType){
		if(confirm("确定移除待报记录吗？")){
			$.ajax({
				url:"${webroot}/cdc/f_json/removeTodoList.shtml",
				data:{
					'mzzyid':zyid,
					'patientType':patientType
				},
				success:function(data){
					data = eval("("+data+")");
					if(data.result=="success"){
						$.messager.show({ title: '提示', msg: '移除待报记录成功！' });
						bk001Sbk.query();
					}else{
						$.messager.show({ title: '提示', msg:data.msg });
					}
				},error:function(){
					alert("抱歉，移除失败！");
				}
			});
		}
	}
}
</script>
</body>
</html>