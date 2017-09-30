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
</head>
<body>
<div id="tb" class="m_search">
	<!-- <div class="m_search_tabs cur" id="mz">门诊</div> -->
	<div id="" class="m_search_first">
		<span>患者类型:</span>
		<select id="id_patientType" class="easyui-combobox" data-options="editable:false,value:'${patientType}'" style="width: 85px;">
			<option value="ZY">住院</option>
			<option value="MZ">门诊</option>
		</select>
		<span>就诊时间:</span>
		<span id="searchDate">
			<input type="text" id="startDate" value="${endTime}"  class="Wdate text" onclick="WdatePicker()"  style="width:85px;"/>~
			<input type="text" id="endDate" value="${endTime}" class="Wdate text"  onclick="WdatePicker()"  style="width:85px;"/>
		</span>
		<input type="text" class="auto-tip input_tip" style="width:150px" data-tip="姓名/门诊号" id="searchString" />	
		<!-- <input type="checkbox" id="chargeFlag" name="chargeFlag" value="1" onclick="bk001Sbk.query();">分管患者</input>
		&nbsp;&nbsp; -->
		<div class="n_btn_blue">
			<a href="javascript:;" class="toTrigger" onclick="bk001Sbk.query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
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
<div id="bk001SbkPanel"></div>

<div id="getPatientById" style="padding: 5px;">
	<div id="patientInfo">
		<div style="margin-bottom: 5px;">
			<div style="width: 80px;text-align: right;display: inline-block;">
				<span>患者类型：</span>
			</div>
			<select id="patientType">
				<option value="mz">门诊</option>
			</select>
		</div>
		<div style="margin-bottom: 5px;">
			<div style="width: 80px;text-align: right;display: inline-block;">
				<span>患者关键字：</span>
			</div>
			<input type="text" class="auto-tip input_tip" style="width:150px" data-tip="姓名/门诊号" id="patient"/>
		</div>
		<div>
			<div style="width: 80px;text-align: right;display: inline-block;">
				<span>诊断日期：</span>
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
var isMzProgrem = '0';
var bk001Sbk = {
		panel : 'bk001SbkPanel',
		//查询
		query : function() {
			//alert($('input[name="chargeFlag"]:checked').val());
			autoTip.clear();
	        $('#'+bk001Sbk.panel).datagrid({
	            queryParams: {
	            	'chargeFlag': $('input[name="chargeFlag"]:checked').val(),
	            	'queryStartDate': $('#startDate').val(),
	                'queryEndDate': $('#endDate').val(),
	                'searchString': $('#searchString').val(),
	                'patientRange': $("#patientRange").val(),
	                'patientType':'${patientType}',
	                'searchOpt': isMzProgrem
	            }
	        });
	    },
	    showDetail:function(mzid){
	    	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecordsMz.shtml?mzid='+mzid+"&tab=1",true);
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
$(document).ready(function () {
	//
	$("#cdcbkChoose").show();
	autoTip.clear();
	$('#'+bk001Sbk.panel).datagrid({
		fit: true,
		nowrap: true,
		autoRowHeight: true,
		striped: true,		
        url:'${webroot}/bk001Sbk/f_json/pageQueryMz.shtml',
        remoteSort: false,
	    singleSelect: true,
		fitColumns: true,
	    border:false,
        queryParams: {
        	'chargeFlag': $('input[name="chargeFlag"]:checked').val(),
        	'queryStartDate': $('#startDate').val(),
            'queryEndDate': $('#endDate').val(),
            'searchString': $('#searchString').val(),
            'patientRange': $("#patientRange").val(),
            'patientType':'${patientType}'
        },
        remoteSort: false,
        singleSelect: true,
        rownumbers:true,
        columns:[
        	[
                {field:'yj',title:'预警',sortable:true,width:22,
                	formatter:function(value,r){
                		var yj = '';
                		<c:if test="${EWLOGO==1}">
	                		if(r.cyjCount!=null && r.cyjCount!=0){
	                			yj = yj + '<a href="javascript:openCRBYJ(\''+r.zyid+'\',\''+r.inHospAt+'\');" class="frame_a"><span class="frame_green">传</span><span class="green">'+r.cyjCount+'</span></a>';
	                			cyjCount++;
	                		}
                		</c:if>
                		return [yj].join('');
                	}
                }, 
                {field:'bk',title:'报卡',sortable:true,width:22,
                	formatter:function(value,r){
                		var bk = '';
                		var crb = r.crbCount+r.syCount+r.jcCount+r.tmCount+r.ycCount+r.xnCount+r.zsCount+r.nyCount;
                		if(crb!=0){
                			bk = bk + '<a href="javascript:crbbk(\''+r.patientName+'\',\'\',\''+r.mzid+'\');" class="frame_a"><span class="frame_green">公</span><span class="green">'+crb+'</span></a>';
                		}
                		return [bk].join('');
					}
                },
                {field:'patientId',title:'${patientZyTitle}',sortable:true,width:20,
					formatter:function(value,r){
						return ['',r.${patientZyValue},''].join('');
					}
				},
                {field:'mzid',title:'门诊号',sortable:true,width:20,
                	formatter:function(value,r){
						return ['<a href="javascript:bk001Sbk.showDetail(\'',r.mzid,'\',\'\');">',r.mzid,'</a>'].join('');
					}	
                },
				{field:'visitId',title:'就诊次数',sortable:true,width:20},
				{field:'isreturnvisit',title:'复诊',sortable:true,width:20,formatter:function(value,r){
					if(value=="1"){
						return "是";
					}else{
						return "否";
					}
				}},
                {field:'patientName',title:'患者',sortable:true,width:10},
                {field:'startDt',title:'发病日期',sortable:true,width:22},
                {field:'diagnosisDt',title:'就诊时间',sortable:true,width:25},
                {field:'diagnosisName',title:'诊断名称',sortable:true,width:28},
                {field:'deptName',title:'就诊科室',sortable:true,width:22},
                {field:'doctorName',title:'就诊医生',sortable:true,width:22},
                {field:'yqxCount',title:'操作',sortable:true,width:16,
                	formatter:function(value,r){
                		var tmp = '';
                		if('${systemScope}'.indexOf('cdc') >= 0){
                			tmp += '<a href="javascript:crbbk(\''+r.patientName+'\',\'\',\''+r.mzid+'\');" class="ico_up_green" title="公卫卡上报"></a>';
						}
                		tmp += '<a href="javascript:sendMessage(\''+r.mzid+'\');" class="ico_mail" title="消息"></a>';
                		<c:if test="${ISDB==1}">
                		if(r.isdb==1){
                			tmp +='<a href="javascript:removeDbRecord(\''+r.mzid+'\',\'MZ\');" class="ico_fork"  title="移除传染病待报"></a>';
                		}
            		</c:if>
                		return [tmp].join('');
					}
                }
            ]
        ],
        pagination:true,
        rownumbers:true,
        toolbar:'#tb',
        pageSize:30,
        pageList:[30,50,100,200]
    });
	
	
	$('#BKS').datagrid({
		url:'',
		fitColumns:true,
		singleSelect:true,
		height:300,
		columns:[[
			{field:'masterid',title:'唯一编号',hidden:true},
			{field:'cardStates',title:'状态',width:50},
			{field:'cardType',title:'报卡类别',width:95},
			{field:'diseaseName',title:'上报疾病',width:75},
			{field:'mzid',title:'门诊号',width:95},
			{field:'zyid',title:'${patientZyTitle}',hidden:true},
			{field:'patientName',title:'患者姓名',width:65},
			{field:'sexname',title:'性别',width:35},
			{field:'ageunit',title:'年龄',width:36},
			{field:'reportdoctorname',title:'上报医生',width:85},
			{field:'reportdeptname',title:'上报科室',width:105},
			{field:'filldate',title:'上报日期',width:130},
			{field:'ops',title:'操作',width:35,formatter:function(value,row,index){
				return "<a class='ico_search' href=\"javascript:detail('"+row['cardType']+"','"+row['zyid']+"','"+row['mzid']+"','"+row['masterid']+"');\"></a>";
			}}
		]],
		onLoadSuccess:function(){
			if(window["console"]){
        		console.log("表格初始化成功:"+new Date().getMinutes()+"  --  "+new Date().getSeconds()+"  --  "+new Date().getMilliseconds());
        	}
		}
	
	});
	
	//宽度计算
	var count = $("#butt_place").find(".butts").length;
	$(".butts").css("width",parseInt(99/count)+"%");
	
	$("#cardsPlace").show();
	
	$("#id_patientType").combobox({
		onChange: function (nv,ov) {
			if(nv=="MZ"){
				parent.parent.menuInfo.clickMenu('患者信息查询','/st003Cryxxb/f_view/patientMzList.shtml?patientType=MZ',true);
			}else{
				parent.parent.menuInfo.clickMenu('患者信息查询','/st003Cryxxb/f_view/toList.shtml?patientType=ZY',true);
			}
		}
	});
	
});

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
		parent.menuInfo.clickMenu('高温中暑报卡上报','/cdc/f_view/sunstrokeReport.shtml?type=empty',true);
	}
	$("#emptyCard option[value='']").attr("selected","selected");
} */

function crbbk(pname,zyid,mzid){
	Comm.dialogGlobal({
    	url:"${webroot}/cdc/f_view/toChooseCard.shtml?zyid="+zyid+"&mzid="+mzid+"&ownership=${param.ownership}",
    	title: "病例上报 - 病人:"+pname+"("+(!zyid?mzid:zyid)+")	 - 已报卡列表",
        width:850,
        type:"iframe",
        height:430
    });
}

function sendMessage(mzid){
	Comm.dialogGlobal({
    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?mzid="+mzid+"&msgType=1",
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
        type:"iframe"
    });
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

function showDialog(){
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
	//居中判断
	var top = document.body.scrollTop;
	var mtop = parseInt(($(window).height()-130)/2);
	if(mtop>0){
		top += mtop;
	}
	$("#getPatientById").window('open').window('resize',{top: top});
}
function showProgressBar(){
	var pv = ($("#patient").val()=="姓名/门诊号"?"":$("#patient").val());
	var stdate = $("#startTime").val();
	var etdate = $("#endTime").val();
	if(!pv){
		$.messager.show({ title: '提示', msg: '请填写患者关键字！' });
		$("#patient").focus();
	}else if(!stdate){
		$.messager.show({ title: '提示', msg: '请填写诊断日期范围！' });
		$("#startTime").focus();
	}else if(!etdate){
		$.messager.show({ title: '提示', msg: '请填写诊断日期范围！' });
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
		url:"${webroot}/cdc/f_json/getPatientFromHIS.shtml",
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
			errorbar("操作出错！");
			//alert("抱歉，操作出错！");
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