<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>体温信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>

<div id="tb_twxx" >
	<div class="m_search h_set">
		<div class="m_search_last">
			<input type="hidden" id="id_advice_zyid" value="${param.zyid}"/>
			<span class="pro_text" style="font-size:12px">体温日期：</span>
			<input type="text" name="queryStartDate" id="queryStartDate" value="<fmt:formatDate value="${st006Twxx.queryStartDate}" pattern="yyyy-MM-dd" />"  class="Wdate text" style="width:95px;" onclick="WdatePicker()" />~
	    	<input type="text" name="queryEndDate" id="queryEndDate" value="<fmt:formatDate value="${st006Twxx.queryEndDate}" pattern="yyyy-MM-dd" />"  class="Wdate text" style="width:95px;" onclick="WdatePicker()" />
			<div class="n_btn_blue">
				<a href="javascript:;"  onclick="twxx.query(0)"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>	
			<div class="btn_r">				
				<a href="javascript:twxx.query(-1)" title="第一周" class="easyui-linkbutton"><i class="icon iconfont">&#xe67b;</i></a>
				<a id="id_last" href="javascript:void(0);" onclick="twxx.query(-7);"  title="上一周" ><i class="icon iconfont">&#xe679;</i></a>
				<a id="id_next" href="javascript:void(0);" onclick="twxx.query(7);"  title="下一周" ><i class="icon iconfont">&#xe67a;</i></a>
				<a href="javascript:twxx.query(1)"  title="最后周"  class="easyui-linkbutton"><i class="icon iconfont">&#xe67c;</i></a>
			</div>		
		</div>
		<%-- <table cellpadding="0" cellspacing="0">
			<tr>
				<td style="padding-right: 15px; padding-left: 15px;">
					<input type="hidden" id="id_advice_zyid" value="${param.zyid}"/>
					<span class="pro_text" style="font-size:12px">体温日期：</span>
					<input type="text" name="queryStartDate" id="queryStartDate" value="<fmt:formatDate value="${st006Twxx.queryStartDate}" pattern="yyyy-MM-dd" />"  class="Wdate text" style="width:95px;" onclick="WdatePicker()" />~
			    	<input type="text" name="queryEndDate" id="queryEndDate" value="<fmt:formatDate value="${st006Twxx.queryEndDate}" pattern="yyyy-MM-dd" />"  class="Wdate text" style="width:95px;" onclick="WdatePicker()" />
					<input type="button" class="btn_search" onclick="twxx.query(0)" value="查询" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    			<a href="javascript:twxx.query(-1)" class="easyui-linkbutton" >第一周</a>
	    			<a id="id_last" href="javascript:void(0);" onclick="twxx.query(-7);" >上一周</a>
	    			<a id="id_next" href="javascript:void(0);" onclick="twxx.query(7);" >下一周</a>
	    			<a href="javascript:twxx.query(1)" class="easyui-linkbutton" >最后周</a>
				</td>
			</tr>
		</table> --%>
	</div>
</div>
<div id="report" style="width: 100%;height:400px; padding:20px 0px;">1111</div>

	<jsp:include page="/WEB-INF/view/core/report.jsp"></jsp:include>
<script type="text/javascript">
	var twxx = {
		query : function (q){
			if(q==(-1)){
				 $('#queryStartDate').val(twxx.addDate('<fmt:formatDate value="${st006Twxx.firstDate}" pattern="yyyy-MM-dd" />',0));
				 $('#queryEndDate').val(twxx.addDate('<fmt:formatDate value="${st006Twxx.firstDate}" pattern="yyyy-MM-dd" />',6));
			}else if(q == (-7)){
				 $('#queryEndDate').val(twxx.addDate($('#queryStartDate').val(),-1));
				 $('#queryStartDate').val(twxx.addDate($('#queryStartDate').val(),-7));
			}else if(q==7){
				 $('#queryStartDate').val(twxx.addDate($('#queryEndDate').val(),1));
				 $('#queryEndDate').val(twxx.addDate($('#queryStartDate').val(),6));
			}else if(q==1){
				 $('#queryStartDate').val(twxx.addDate('<fmt:formatDate value="${st006Twxx.endDate}" pattern="yyyy-MM-dd" />',-6));
				 $('#queryEndDate').val(twxx.addDate('<fmt:formatDate value="${st006Twxx.endDate}" pattern="yyyy-MM-dd" />',0));
			}
			twxx.report();
			this.setDisabled();
		},
		//设置上一周和下一周是否可用
		setDisabled : function() {
			var startDate = $('#queryStartDate').val();
			var endDate = $('#queryEndDate').val();
			var start = startDate.split("-");
			var end = endDate.split("-");
			start = new Date(Number(start[0]), Number(start[1]) - 1, Number(start[2]));
			end = new Date(Number(end[0]), Number(end[1]) - 1, Number(end[2]), 23, 59, 59);
			var firstDate = '<fmt:formatDate value="${st006Twxx.firstDate}" pattern="yyyy-MM-dd HH:mm:ss" />';
			firstDate = new Date(Number(firstDate.substring(0,4)), Number(firstDate.substring(5,7)) - 1, Number(firstDate.substring(8,10)), Number(firstDate.substring(11,13)), Number(firstDate.substring(14,16), 59));
			var endDate = '<fmt:formatDate value="${st006Twxx.endDate}" pattern="yyyy-MM-dd HH:mm:ss" />';
			endDate = new Date(Number(endDate.substring(0,4)), Number(endDate.substring(5,7)) - 1, Number(endDate.substring(8,10)), Number(endDate.substring(11,13)), Number(endDate.substring(14,16), 59));
			if (start.getTime() < firstDate.getTime()) {
				$('#id_last').linkbutton('disable');
			} else {
				$('#id_last').linkbutton('enable');
			}
			if (end.getTime() > endDate.getTime()) {
				$('#id_next').linkbutton('disable');
			} else {
				$('#id_next').linkbutton('enable');
			}
		},
		addDate : function (dd ,dadd){
			var strs = dd.split('-');
			var date = new Date(Number(strs[0]), Number(strs[1]) - 1, Number(strs[2]));
			var time = date.getTime();
			time = time + (dadd * 24 * 60 * 60 * 1000);
			return twxx.format(new Date(time), "yyyy-MM-dd");
		},
		format : function (date ,fmt) { //author: meizz 
		    var o = {
		        "M+": date.getMonth() + 1, //月份 
		        "d+": date.getDate(), //日 
		        "h+": date.getHours(), //小时 
		        "m+": date.getMinutes(), //分 
		        "s+": date.getSeconds(), //秒 
		        "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
		        "S": date.getMilliseconds() //毫秒 
		    };
		    if (/(y+)/.test(fmt)) {
		    	fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
		    }
		    for (var k in o){
			    if (new RegExp("(" + k + ")").test(fmt)) {
			    	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			    }
			}
		    return fmt;
		},
	  	//报表1
		report : function(){
			var queryStartDate = $('#queryStartDate').val();
			var queryEndDate = $('#queryEndDate').val()+' 23:59:59';
			var options = {
					panelId : 'report',
					url : webroot+'/st006Twxx/f_json/findTwxxList.shtml?zyid=${param.zyid}',
					data : {queryStartDate:queryStartDate,queryEndDate:queryEndDate}
			};
			_report.loadByUrlAndQueryParamsToChart(options, function (data) {
				var xAxisData = [];
				var seriesData = [];
				var seriesData1 = [];
				var seriesData2 = [];
				var seriesData3 = [];
				var titleData="";
				//alert(data.length);
				$.each(data,function(index,value){
					xAxisData.push(value.showRecordingAt);
					seriesData.push(value.twValues);
					seriesData1.push(value.fr_line);
					seriesData2.push(value.bz_line);
					seriesData3.push(value.jz_line);
				});
				titleData = "";
				/**柱状图**/
				_report.removeChart(options.panelId);
				report_t = echarts.init(document.getElementById(options.panelId));
				var option  = {
					title:{text:titleData},
					legend: {data:['体温','38','37.5','37']},
				    tooltip : {},
				    toolbox:{show : true,feature : {saveAsImage : {
		                show : true,
		                title : '保存为图片',
		                type : 'jpeg',
		                lang : ['点击本地保存'] 
		            }}},

				    xAxis: [{ type:'category', splitNumber: data.length, boundaryGap:false, data:xAxisData ,
			            markLine : {
			                data : [
			                    { type:'value', name: '平均值', value: 38}
			                ]
			            }}],
				    yAxis: [{ type:'value', splitNumber: 7,  min:35, max:42 }],
				    series:[{name:'体温', showAllSymbol: true, type:'line',itemStyle: {normal: {areaStyle: {type: 'default'}}}, data:seriesData,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            }},
				            {name:'38',   type:'line', symbol: 'emptyCircle', data:seriesData1},
				            {name:'37.5', type:'line', data:seriesData2},
				            {name:'37',   type:'line', data:seriesData3}
				    ]
				};
				report_t.setOption(option);
				_report.addChart(options.panelId,report_t);	
			},function(){});
		}
	};

	$(document).ready(function () {
		$('#id_last').linkbutton({});
		$('#id_next').linkbutton({});
		twxx.report();
		twxx.setDisabled();
	});
</script>
</body>
</html>
