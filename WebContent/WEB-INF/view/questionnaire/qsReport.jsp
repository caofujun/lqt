<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   	<title>问卷调查报表</title>
   			<script type="text/javascript" src="${webroot}/resources/js/report_js.js${version}" charset="utf-8"></script>
    <%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>

<body  style="margin:5px 2%;" >
	<input type="hidden" id="tid" name="tid" />
	<div class="m_search" id="datePickers">
		<!-- <span class="pro_text">报表类型：</span> -->		
		<select id="reportType" name="reportType" onchange="reportTypeChange()" >
			<option value="month">月报</option>
			<option value="quarter">季报</option>
			<option value="year">年报</option>
			<option value="date">自定义时间</option>
		</select>			
		<!-- <span class="pro_text">报表时间：</span> -->
		<input type="text" style="display: none;" class="Wdate text" id="time_dateStart"    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-{%d}'})" />		
		<input type="text" style="display: none;" class="Wdate text" id="time_dateEnd"      onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-{%d}'})"  />		
		<input type="text" style="display: inline-block; " class="Wdate text" id="time_month"        onclick="WdatePicker({dateFmt:'yyyy年MM月',maxDate:'%y-{%M}'})"  />		
		<input type="text" style="display: none; " class="Wdate text" id="time_quarter"      onclick="WdatePicker({dateFmt:'yyyy年M季度', isQuarter:true, isShowOK:false,disabledDates:['....-0[5-9]-..','....-1[0-2]-..'], startDate:'%y-01-01' })" />		
		<input type="text" style="display: none; " class="Wdate text" id="time_year"         onclick="WdatePicker({dateFmt:'yyyy年',maxDate:'{%y}'})"  />		
		<input id="_qsList" name="qsList" />
		<input type="hidden" id="_depNo" value="347"/>	
		<input type="hidden" id="unit4_1" value="${account.unitId }"/>		    
		<input type="text" class="text" id="dep" style="width: 150px;value="${actionMap.depNo}"/>
		<div class="n_btn_blue">
			<a href="javascript:;" onclick="report.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>
		<div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:report.viewQs();"><i class="icon iconfont">&#xe687;</i><span>查看评估表</span></a>
			</div>			
		</div>		
	</div>

	<div class="report_tab">
		<div class="report_tab_li">
			<a class="_qs_report_tab cur" id="defaultReport" tag="1" href="javascript:report.changeReport(1);">默认报告</a>
			<!-- <a class="_qs_report_tab " id="classicReport" tag="2" href="javascript:report.changeReport(2);">分类统计</a> -->
		</div>
		<div class="class_query" id="_calss_ui" style="display: none;">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
				<tr>
					<td width="80">选择条件：</td>
					<td>
						<span class="standard_select">
							<span class="select_shelter">
								<!-- 问卷题目信息 -->
								<input id="_qsTopic" name="qsTopic" />
							</span>
						</span>
					</td>
				</tr>
				<tr>
					<td>选择分类：</td>
					<td id="_options">
						
					</td>
				</tr>
				<tr><td colspan="2"><b id="_countInfo" style="display: none;">该分类统计的有效答卷：<span class="red" id="_true_qs"></span>份；此问卷总共答卷：<span class="red" id="_all_qs"></span>份</b></td><tr>
			</table>
		</div>	
	</div>
	<!-- tab项切换 -->
	<div style="height:100%">
		<iframe id="tagReportIframe" src="" scrolling="no" width="100%" marginheight="0" frameborder="0" onload="AutoIframe()"></iframe>
	</div>
<!-- <div class="footer_button" id="downLoad"><input type="button" class="btn_oranger" onclick="exportWord()" value="下载调查报告（Word）"/></div> -->
</body>
<script type="text/javascript" >
//报表对象
var report = {
		//报表查询
		query : function(){
			var qId = $("input[name='qsList']").val();
			var depNo=$('#dep').val();	
			if(qId == null || qId == '' ){
				$.messager.show({ title: '提示', msg: '请先选择问卷!' });
				return;
			}
			var selectOb = $('.report_tab_li .cur');
			var tag = selectOb.attr('tag');
			
			//defaultSearch();
			
			switch(tag)
			{
				case '1':
				  //默认报表
				  report.defaultReport();
				  break;
				case '2':
				  //分类统计
				  report.classifyReport();
				  break;
			};
		},
		//切换报表
		changeReport : function(type){
			var qId = $("input[name='qsList']").val();
			var depNo=$('#dep').val();	
			if(qId == null || qId == '' ){
				$.messager.show({ title: '提示', msg: '请先选择问卷!' });
				return;
			}
			//全部去掉选中
			$('._qs_report_tab').removeClass('cur');
			switch(type)
			{
				case 1:
				  //默认报表
				  $('#defaultReport').addClass('cur');
				  report.defaultReport();
				  break;
				case 2:
				  //分类统计
				  $('#classicReport').addClass('cur');
				  report.classifyReport();
				  break;
			};
		},
		//默认报表
		defaultReport : function(){
			//隐藏分类信息
			$('#_calss_ui').hide();
			$('#_countInfo').hide();
			var unit=$('#unit4_1').val();
			var qId = $("input[name='qsList']").val();
			var startTime ="";
			var endTimeOrTime ="";
			var options=$("#reportType option:selected");
			var depName=$('#select2-chosen-1').text()=='请输入科室名称' ? '全院':$('#select2-chosen-1').text();
			depNo=$("#dep").val();
			if(options.val()=='month'){
				endTimeOrTime=$("#time_month").val().replace("年","-");
				endTimeOrTime=endTimeOrTime.replace("月","");
				preTitle=$("#time_month").val();
			}else if(options.val()=='quarter'){
				endTimeOrTime=$("#time_quarter").val().replace("年","-");
				endTimeOrTime=endTimeOrTime.replace("季度","")
				preTitle=$("#time_quarter").val();
			}else if(options.val()=='year'){
				endTimeOrTime=$("#time_year").val().replace("年","");
				preTitle=$("#time_year").val();
			}else if(options.val()=='date'){
				startTime=$("#time_dateStart").val();
				endTimeOrTime=$("#time_dateEnd").val();
				preTitle=$("#time_dateStart").val()+"至"+$("#time_dateEnd").val();
			}
			//页面跳转
			url="${reportUrl}"+"/frameset?__report="+'report/depQsStatisticsReport.rptdesign'+"&__navigationbar=false&__toolbar=false"+"&unitId="+unit+"&qid="+qId+'&begTime='+startTime+'&endTime='+endTimeOrTime+'&depNo='+depNo+'&dateType='+options.val()+"&depName="+depName+'&preTitle=（'+preTitle+"）";;
			$("#tagReportIframe").attr("src",url);
			var ifobj=document.getElementById("tagReportIframe");
			if(ifobj.height <1500){
				ifobj.height=1500
			}else{
				ifobj.height=ifobj.contentWindow.document.body.scrollHeight;
			}
		    
		},
		//全题报表
		defaultReport : function(){
			//隐藏分类信息
			$('#_calss_ui').hide();
			$('#_countInfo').hide();
			$('#downLoad').hide();
			var qId = $("input[name='qsList']").val();
			var depNo=$('#dep').val();	
			var startTime ="";
			var endTimeOrTime ="";
			var options=$("#reportType option:selected");
			if(options.val()=='month'){
				endTimeOrTime=$("#time_month").val().replace("年","-");
				endTimeOrTime=endTimeOrTime.replace("月","");
			}else if(options.val()=='quarter'){
				endTimeOrTime=$("#time_quarter").val().replace("年","-");
				endTimeOrTime=endTimeOrTime.replace("季度","")
			}else if(options.val()=='year'){
				endTimeOrTime=$("#time_year").val().replace("年","");
			}else if(options.val()=='date'){
				startTime=$("#time_dateStart").val();
				endTimeOrTime=$("#time_dateEnd").val();
			}
			//页面跳转
			url="${webroot}/qsSurveyResult/f_view/defalutReportInfo.shtml?qId="+qId+'&queryStartDate='+startTime+'&queryEndDate='+endTimeOrTime+"&dateType="+options.val();
			$("#tagReportIframe").attr("src",url);
		},
		//分类统计
		classifyReport : function(){
			//加载统计信息
			$("#tagReportIframe").attr("src","");
			//显示题目信息
			report.topicListCombobox();
			$('#downLoad').hide();
		},
		//分类统计查询
		classifyReportQuery : function(optId){
			var qId = $("input[name='qsList']").val();
			if(optId == null || optId == '' ){
				$.messager.show({ title: '提示', msg: '请先选择分类!' });
				return;
			}
			var unit=$('#unit4_1').val();
			if(unit==null){
				unit='';
			}else{
				unit=$('#unit4_1').val();
			}
			//加载统计信息
			$('#_countInfo').show();
			report.classCountInfo(optId);
			//当前选项选中opTag
			var optionObject = $('.opTag');
			optionObject.removeClass('cur');
			$('#_option_'+optId).addClass('cur');
			var startTime ="";
			var endTimeOrTime ="";
			var options=$("#reportType option:selected");
			if(options.val()=='month'){
				endTimeOrTime=$("#time_month").val().replace("年","-");
				endTimeOrTime=endTimeOrTime.replace("月","");
			}else if(options.val()=='quarter'){
				endTimeOrTime=$("#time_quarter").val().replace("年","-");
				endTimeOrTime=endTimeOrTime.replace("季度","")
			}else if(options.val()=='year'){
				endTimeOrTime=$("#time_year").val().replace("年","");
			}else if(options.val()=='date'){
				startTime=$("#time_dateStart").val();
				endTimeOrTime=$("#time_dateEnd").val();
			}
			var depNo=$('#dep').val();
			//页面跳转
			url="${webroot}/qsSurveyResult/f_view/classifyReportInfo.shtml?qId="+qId+'&optId='+optId+'&queryStartDate='+startTime+'&queryEndDate='+endTimeOrTime+'&depNo='+depNo+"&dateType="+options.val();
			$("#tagReportIframe").attr("src",url);
		},
		//查看问卷
		viewQs : function(){
			var qId = $("input[name='qsList']").val();
			if(qId == null || qId == '' ){
				$.messager.show({ title: '提示', msg: '请先选择问卷!' });
				return;
			}
			window.open("${webroot}/qsQuestionnaire/f_view/viewQs.shtml?qId="+qId,"_blank");
		},
		//下载报表
		downReport : function(){
			$.messager.show({ title: '提示', msg: '待研发,导出和打印一起研发!' });
		},
		//题目下拉框
		topicListCombobox : function(){
			var qId = $("input[name='qsList']").val();
			//显示分类信息
			$('#_calss_ui').show();
		},
		//分类统计信息
		classCountInfo : function(optId){
			var qId = $("input[name='qsList']").val();
			$.ajax({
                url: '${webroot}/qsSurveyResult/f_json/classifyReportCount.shtml',
                type: 'post',
                data: { 'qId': qId,'optId':optId },
                dataType: 'json',
                success : function(json) {
                	if (json.result === 'success') {
                       $('#_true_qs').text(json.data.trueQsCount);
                       $('#_all_qs').text(json.data.allQsCount);
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
            });
		}
};

function exportWord(){
	var unit=$('#unit4_1').val();
	var qId = $("input[name='qsList']").val();
	var startTime ="";
	var endTimeOrTime ="";
	var options=$("#reportType option:selected");
	var depName=$('#select2-chosen-1').text()=='请输入科室名称' ? '全院':$('#select2-chosen-1').text();
	depNo=$("#dep").val();
	if(options.val()=='month'){
		endTimeOrTime=$("#time_month").val().replace("年","-");
		endTimeOrTime=endTimeOrTime.replace("月","");
		preTitle=$("#time_month").val();
	}else if(options.val()=='quarter'){
		endTimeOrTime=$("#time_quarter").val().replace("年","-");
		endTimeOrTime=endTimeOrTime.replace("季度","")
		preTitle=$("#time_quarter").val();
	}else if(options.val()=='year'){
		endTimeOrTime=$("#time_year").val().replace("年","");
		preTitle=$("#time_year").val();
	}else if(options.val()=='date'){
		startTime=$("#time_dateStart").val();
		endTimeOrTime=$("#time_dateEnd").val();
		preTitle=$("#time_dateStart").val()+"至"+$("#time_dateEnd").val();
	}
	var _url = '${reportUrl}/output?__report=report/depQsStatisticsReport.rptdesign&__dpi=120&__format=doc&__asattachment=true&__overwrite=false&__navigationbar=false'+"&unitId="+unit+"&qid="+qId+'&begTime='+startTime+'&endTime='+endTimeOrTime+'&depNo='+depNo+'&dateType='+options.val()+'&preTitle=（'+preTitle+"）&depName="+depName;
	__export(_url);
}
function reportTypeChange(){
	var options=$("#reportType option:selected");	
	var datePickers = document.getElementById('datePickers');
	  var inputs=datePickers.getElementsByTagName('input');
	    for(var i = 0; i < inputs.length; i++){
	        if(inputs[i].id.indexOf("time_")>-1){
	        	if(inputs[i].id.indexOf(options.val())>-1){
	        	  $("#"+inputs[i].id).css('display','inline-block');
	        	}else{
	        	  $("#"+inputs[i].id).css('display','none');
	        	}
	        }
	    }
	}
function setDefaultTime(){
	var myDate = new Date();
	var nowMonth=myDate.getMonth()+1;
	var nowYear=myDate.getFullYear();//获得当前年月
	var nowDay=myDate.getDate();
	if(nowMonth<10){
		nowMonth="0"+nowMonth;
	}
	if(nowDay<10){
		nowDay="0"+nowDay;
	}
	var startTime ="";
	var endTimeOrTime =nowYear+"年"+nowMonth+"月";
	
	$("#time_month").val(endTimeOrTime);//给时间选择器设置默认时间
	$("#time_year").val(nowYear+"年");//给时间选择器设置默认时间
	$("#time_dateStart").val(nowYear+"-"+nowMonth+"-"+nowDay);//给时间选择器设置默认时间
	$("#time_dateEnd").val(nowYear+"-"+nowMonth+"-"+nowDay);//给时间选择器设置默认时间
	var quarter_temp="";
	if(0<nowMonth<=3){
		quarter_temp="1季度";
	}else if(3<nowMonth<=6){
		quarter_temp="2季度";
	}else if(6<nowMonth<=9){
		quarter_temp="3季度";
	}else if(9<nowMonth<=12){
		quarter_temp="4季度";
	}
	$("#time_quarter").val(nowYear+"年"+quarter_temp);//给时间选择器设置默认时间
}	
$(document).ready(function(){
	getLastAction();
	setDefaultTime();
	
	reportTypeChange();//设定默认日期类型

	var _qid = '${param.qid}';
	var defalultValue = encodeURIComponent(encodeURIComponent("--请选择问卷--"));
	//问卷信息
	$('#_qsList').combobox({
	    url:'${webroot}/qsQuestionnaire/f_json/queryQsEasyUi.shtml?defalultValue='+defalultValue,
	    valueField:'key',
	    textField:'value',
	    onSelect : function(record){
	    	if(record == null){
	    		return;
	    	};
	    	$('#_qsTopic').combobox('reload','${webroot}/qsTopic/f_json/findTopicEasyUi.shtml?qid='+record.key);
	    },
	    onLoadSuccess: function() {
	    	//设置选中问卷
	    	if(_qid != '') {
	    		$('#_qsList').combobox('setValue', _qid);
	    		setTimeout(function() {
		    		$('#_qsTopic').combobox('reload','${webroot}/qsTopic/f_json/findTopicEasyUi.shtml?qid='+_qid);
		    		//加载统计
		    		report.query();
	    		});
	    	}
	    }
	});
	//题目信息
	$('#_qsTopic').combobox({
	    url:'${webroot}/qsTopic/f_json/findTopicEasyUi.shtml',
	    valueField:'key',
	    textField:'value',
	    onSelect : function(record){
	    	//显示题目信息 题目id#选项id@选项名字&选项id@选项名字
	    	if(record == null){
	    		return;
	    	}
	    	var keyString = record.key;
	    	var tid = keyString.substring(0,keyString.indexOf('#'));
	    	var options = keyString.substring(keyString.indexOf('#')+1);
	    	var optionArray = options.split("&");
	    	var _html = "";
	    	for(i=0;i<optionArray.length ;i++){
	    		var optString = optionArray[i];
	    		var optId = optString.substring(0,optString.indexOf('@'));
	    		var optName = optString.substring(optString.indexOf('@')+1); 
	    		_html += '<a href="javascript:report.classifyReportQuery('+optId+')" id="_option_'+optId+'" class="class_a opTag">'+optName+'</a>';
	    	}
	    	$('#_options').empty();
	    	$('#_options').html(_html);
	    },
	    formatter : function(row) {
	    	//如果被选中，用于第一次
	    	if(row.selected){
	    		var keyString = row.key; 
	    		var tid = keyString.substring(0,keyString.indexOf('#'));
		    	var options = keyString.substring(keyString.indexOf('#')+1);
		    	var optionArray = options.split("&");
		    	var _html = "";
		    	for(i=0;i<optionArray.length ;i++){
		    		var optString = optionArray[i];
		    		var optId = optString.substring(0,optString.indexOf('@'));
		    		var optName = optString.substring(optString.indexOf('@')+1); 
		    		_html += '<a href="javascript:report.classifyReportQuery('+optId+')" id="_option_'+optId+'" class="class_a opTag">'+optName+'</a>';
		    	}
		    	$('#_options').empty();
		    	$('#_options').html(_html);
	    	}
	    	return row.value;
	    }
	});
});
	Csm.select.dep({
		id: 'dep',
		unitId: 'unit4_1',
		depNo:'_depNo'
	});//加载科室
	
function AutoIframe()
{
    if(document.readyState!='complete')
    {
        setTimeout( function(){AutoIframe();},25 );
        return;
    }
    else
    {
      var ifobj=document.getElementById("tagReportIframe");
     ifobj.height= ifobj.contentWindow.document.body.scrollHeight;
    }
}
/* 取出上次操作的筛选条件 */
function getLastAction(){
	$("#reportType option[value='${actionMap.reportType}']").attr("selected",true);
	reportTypeChange();
}
 /* 将当前条件存入数据库 */
	/* function defaultSearch(){
		 $.ajax({
			 url:'${webroot}/sysAction/f_json/report_saveOrUpdate.shtml',
			 type:'post',
			 data:{reportName:"report_wjtx",reportType:$("#reportType").val(),depNo:$("#dep").val(),depName:$('#select2-chosen-1').text()=='请输入科室名称' ? '全院':$('#select2-chosen-1').text()},
			 dataType:'json',
			 error:function (){
			 },
			 success:function(){
			 }
		 });
	} */
</script>
</html>
