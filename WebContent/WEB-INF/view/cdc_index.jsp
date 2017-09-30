<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蓝蜻蜓传染病监测首页</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<jsp:include page="/WEB-INF/view/core/report.jsp"></jsp:include>
<script type="text/javascript" src="${webroot}/resources/echarts/theme/macarons.js"></script>
<script type="text/javascript" src="${webroot}/resources/load/jquery.loadmask.min.js"></script>
<script type="text/javascript" src="${webroot}/resources/js/jquery-ui.js"></script>
<link href="${webroot}/resources/css_org/layout.css" rel="stylesheet"  type="text/css" />
<style type="text/css"></style>
</head>
<body style="height:100%; margin:0 auto;">
	<div class="home_main">
		<div class="mod_define datalist_li_all" style="width:59%">
			<h4>未审核报卡</h4>
			<ul class="datalist_li">
				<c:forEach items="${unAuditCards}" var="uac">
					<c:if test="${fn:contains(cdcScope,fn:split(uac.name,'_')[1]) or fn:contains(cdcScope,'all')}">
						<li onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=${fn:split(uac.name,'_')[1]}',true);">
							<h3>${fn:split(uac.name,'_')[0]}</h3>
							<h2>${uac.value}</h2>
						</li>
					</c:if>
				</c:forEach>
			</ul>
			<!-- <div class="clear"></div> -->
		</div>
		<div class="mod_define datalist_li_need" style="width:38%">
			<h4>当天未处理预警病例及重点监测疾病</h4>
			<ul class="datalist_li">
				<c:forEach items="${dayYJBL}" var="dy" varStatus="dyi">
					<li onclick="parent.menuInfo.clickMenu('迟报漏报查询','/cdc/f_view/suspectedCase.shtml',true);">
						<h3>${dy.name}</h3>
						<h2 <c:if test="${dyi.index==0}">class="red"</c:if> >${dy.value}</h2>
					</li>	
					<c:if test="${dyi.index==0}">
						<li class="li_border"></li>
					</c:if>			
				</c:forEach>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="mod_define" style="width: 377px;">
			<h4><a href="javascript:void(0);" onclick="">报卡类别构成</a><span class="ml10">本月</span></h4>
			<div class="Annular_chart" id="id_classes_chart">
			</div>
		</div>
		<div class="mod_define" style="width: 377px;">
			<h4><a href="javascript:void(0);" onclick="">法定传染病疾病分类构成</a><span class="ml10">本月</span></h4>
			<div class="Annular_chart" id="id_diseaseType_chart">
			</div>
		</div>
		<div class="mod_define" style="width: 377px;">
			<h4><a href="javascript:void(0);" onclick="">法定传染病发病地区构成</a><span class="ml10">本月</span></h4>
			<div class="Annular_chart" id="id_area_chart">
			</div>
		</div>
		
		<div class="mod_define mod_cont">
			<h4><a href="javascript:void(0);" onclick="">法定传染病上报趋势图</a><span class="ml10">近7天</span></h4>
			<div class="Trend_chart" id="id_report_chart">
			</div>
		</div>
		<div class="mod_define mod_cont">
			<h4><a href="javascript:void(0);" onclick="">法定传染病本年度环比图</                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 a></h4>
			<div class="Trend_chart" id="id_year_chart">
			</div>
		</div>
	</div>
	<script type="text/javascript">
var pieChart = {
	loadedMap: {},
	urlMap : {
		'id_classes_chart' : '/cdc/f_json/classesDataForChart.shtml', 
		'id_diseaseType_chart' : '/cdc/f_json/diseaseTypeDataForChart.shtml', 
		'id_area_chart' : '/cdc/f_json/areaDataForChart.shtml' 
	},
	report :function(panelId, dataRange, unitName) {
		var myChart = pieChart.loadedMap[panelId];
		if (dataRange == "undefined") {
			dataRange = 'month';
		}
		var options = {
			panelId : panelId,
			url : webroot + pieChart.urlMap[panelId],
			data : {dataRange : dataRange }
		};
		
		_report.loadByUrlAndQueryParamsToChart(options, function (data) {
			/**环形图**/
			try {
				if (myChart && myChart.dispose) {
					myChart.dispose(); 
					myChart = null;
					_report.removeChart(options.panelId);
				}
			} catch (e) {
				if(window["console"]){
					console.error(e.name + ": " + e.message);
				}
			}
			$('#'+options.panelId).css('filter', '');
			$('#'+options.panelId).css('background-color', '');
			$('#'+options.panelId).mask('加载中..');
			myChart = echarts.init(document.getElementById(options.panelId));
			var legendData = new Array();
			$.each(data,function(index,value){
				legendData.push(value.name);
			});
			
			var option  = {
				title : {
			        subtext: '单位：' + unitName,
			        x:'left',
			        y:'bottom'
			    },
				tooltip : {
			        trigger: 'item',
			        formatter: "{b} : {c}</br>({d}%)"
			    },
				legend: {
			        orient : 'vertical',
			        x : 'right',
			        y : 'bottom',
			        data: legendData
			    },
			    series:[
			        {type:'pie', center : ['35%', '50%'], itemStyle : {
		                normal : {
		                	label : {
		                		show : false
		                    },
		                    labelLine : {
		                        show : false
		                    }
		                }
		            }, radius : ['40%', '60%'], data : data}
			    ]
			};
			myChart.setOption(option);
			_report.addChart(options.panelId, myChart);
			pieChart.loadedMap[panelId] = myChart;
		},function(){$('#'+options.panelId).unmask();});
	}
};

var brokenChart = {
	loadedMap: {},
	urlMap : { 
		'id_report_chart' : '/cdc/f_json/reportDataForChart.shtml', 
		'id_year_chart' : '/cdc/f_json/yearDataForChart.shtml' 
	},
	report :function(panelId, unitName, chartType) {
		var myChart = brokenChart.loadedMap[panelId];
		var options = {
			panelId : panelId,
			url : webroot + brokenChart.urlMap[panelId]
		};
		
		_report.loadByUrlAndQueryParamsToChart(options, function (data) {
			/**折线图**/
			try {
				if (myChart && myChart.dispose) {
					myChart.dispose(); 
					myChart = null;
					_report.removeChart(options.panelId);
				}
			} catch (e) {
				if(window["console"]){
					console.error(e.name + ": " + e.message);
				}
			}
			$('#'+options.panelId).css('filter', '');
			$('#'+options.panelId).css('background-color', '');
			$('#'+options.panelId).mask('加载中..');
			myChart = echarts.init(document.getElementById(options.panelId));
			var legendData = data.legendData;
			var xAxisData = data.xAxisData;
			var series = new Array();
			for (var i = 0; i < data.series.length; i ++) {
				var serieData = data.series[i];
				var serie = new Object();
				serie.name = legendData[i];
				serie.type = (!chartType?'line':chartType);
				serie.symbol = 'emptyCircle';
				serie.showAllSymbol = true;
				serie.data = serieData;
				series.push(serie);
			}
			var option  = {
					title : {
				        subtext: '单位：' + unitName,
				        x:'left',
				        y:'bottom'
				    },
					legend: {data : legendData},
					tooltip : {},
				    xAxis: [{ type:'category', splitNumber: xAxisData.length, boundaryGap:true, data : xAxisData}],
				    yAxis: [{ type:'value', splitNumber: 4, axisLabel : { formatter : data.formatter} }],
				    series: series
				};
			myChart.setOption(option);
			_report.addChart(options.panelId, myChart);
			brokenChart.loadedMap[panelId] = myChart;
		},function(){$('#'+options.panelId).unmask();});
	}
};

$(document).ready(function () {
	pieChart.report('id_classes_chart', 'month', '例');
 	pieChart.report('id_diseaseType_chart', 'month', '例');
 	pieChart.report('id_area_chart', 'month', '例');
	
	brokenChart.report('id_report_chart', '例');
	brokenChart.report('id_year_chart', '例', 'bar');
});
</script>
</body>
</html>