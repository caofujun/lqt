<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>预警人数列表</title>
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
<script>
var isInHosp='1';
function cgPg(zyid){
	location.href = '${webroot}/gm004Jcmx/f_view/cgPgList.shtml?zyid='+zyid;
	//parent.parent.menuInfo.clickMenu('插管评估','/gm004Jcmx/f_view/cgPgList.shtml?zyid='+zyid,true);
}
var bk001Sbk = {
		panel : 'bk001SbkPanel',
		//查询
		query : function() {
	        $('#'+bk001Sbk.panel).datagrid({
	            queryParams: {
	            	'employeeId':'${employeeId}',
	            	'deptId':'${deptId}',
	                'isInHosp':1
	            }
	        });
	    },
	    showDetail:function(zyid){
	    	if(parent && parent.parent.menuInfo){
		    	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
	    	}else{
	    		window.parent.location.href = '${webroot}/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+'&tab=2';;
	    	}
	    }
	};

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
        url:'${webroot}/bk001Sbk/f_view/ewList.shtml',
        queryParams: {
        	'employeeId':'${employeeId}',
        	'deptId':'${deptId}',
            'isInHosp':1
        },
        remoteSort: false,
        singleSelect: true,
        rownumbers:true,
        columns:[
        	[
                {field:'yj',title:'预警',sortable:true,width:25,
                	formatter:function(value,r){
                		var yj = '';
                		if(r.fhyj!=null){
                			yj = yj + '<a href="javascript:openYj(\''+r.zyid+'\');" class="frame_a"><span class="frame_blue">院</span><span class="blue">'+r.fhyj+'</span></a>';
                		}
                		return [yj].join('');
                	}
                },
                {field:'bk',title:'报卡',sortable:true,width:18,
                	formatter:function(value,r){
                		var bk = '';
                		var crb = r.crbCount+r.syCount+r.jcCount+r.tmCount+r.ycCount+r.xnCount+r.zsCount;
                		if(r.bkCount!=0){
                			bk = bk + '<a href="javascript:openBk(\''+r.zyid+'\');" class="frame_a"><span class="frame_blue">感</span><span class="blue">'+r.bkCount+'</span></a>';
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
                {field:'yqxCount',title:'操作',sortable:true,width:18,
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
						return [tmp].join('');
					}
                }
            ]
        ],
        toolbar:'#tb',
        onLoadSuccess:function(data){
        	
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
function openMDRo(pname,zyid,bedNo,mdroCount){
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
    	url:"${webroot}/xn011Dclymx/f_view/toWarningPatientMDRo.shtml?zyid="+zyid+"&bedNo="+bedNo,
        title: titles,
        width:870,
        height:555,
        type:"iframe",
        parent:this
        
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
	location.href = "${webroot}/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid="+zyid;
	//parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid,true);
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
</script>
</body>
</html>