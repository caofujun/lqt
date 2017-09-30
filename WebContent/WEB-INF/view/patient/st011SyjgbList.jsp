<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<jsp:include page="/WEB-INF/view/core/report.jsp"></jsp:include>
<script type="text/javascript"
	src="${webroot}/resources/echarts/theme/macarons.js"></script>
<script type="text/javascript"
	src="${webroot}/resources/load/jquery.loadmask.min.js"></script>
<script type="text/javascript"
	src="${webroot}/resources/js/jquery-ui.js"></script>
<link href="${webroot}/resources/css_org/layout.css" rel="stylesheet"
	type="text/css" />
	</head>
	<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">	
		<div data-options="region:'west'" style="width:250px;height:100%;border-width: 0px 1px 0px 0px;">
			<div id="syjgbListPanelList"></div>
		</div>	
		<div data-options="split:true,region:'center'" style="border-width: 1px 1px 1px 1px;">
			<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">	
				<div data-options="region:'center'" style="border-width: 0px 0px 1px 0px;">
					<div style="margin:7px 2px 6px 30px">
					送检：<span id="viewSubmiAt">${submiAt}</span>&nbsp;&nbsp;&nbsp;&nbsp;检出：<span id="viewCheckOutAt">${checkOutAt}</span>&nbsp;&nbsp;&nbsp;&nbsp;送检单号：<span id="viewTestOrderNo">${testOrderNo}</span>
					</div>
					<div id="syjgbListPanel">
					</div>
				</div>
				<div data-options="region:'south'" style="height:230px; border-width: 1px 0px 0px 0px;">
						<h4 style="margin:7px 2px 6px 0px;position: absolute; top:0; left:0">
						<span id="qsTitle" class="ml10" ></span>
						</h4>
						<div class="Trend_chart" id="id_incidence_chart"></div>
				</div>
			</div>
		</div>	
	</div>	
	<script type="text/javascript">
	var brokenChart = {
			loadedMap : {},
			urlMap : {   
				'id_incidence_chart' : '/st011Syjgb/f_json/findSt011Syjgbqst.shtml?',
			},
			report : function(panelId, zyid,classCode,antiCode,unitName) {
				if(unitName == null){
					unitName='';
				}
				var myChart = brokenChart.loadedMap[panelId];
				var options = {
					panelId : panelId,
					url : webroot + brokenChart.urlMap[panelId]+"zyid="+zyid+"&classCode="+classCode+"&antiCode="+encodeURI(antiCode).replace("#","%23"),
				};

				_report.loadByUrlAndQueryParamsToChart(options, function(data) {
					/**折线图**/
					try {
						if (myChart && myChart.dispose) {
							myChart.dispose();
							myChart = null;
							_report.removeChart(options.panelId);
						}
					} catch (e) {
						//console.error(e.name + ": " + e.message);
					}
					$('#' + options.panelId).css('filter', '');
					$('#' + options.panelId).css('background-color', '');
					$('#' + options.panelId).mask('加载中..');
					myChart = echarts.init(document
							.getElementById(options.panelId));
					var legendData = data.legendData;
					var xAxisData = data.xAxisData;
					var series = new Array();
					for (var i = 0; i < data.series.length; i++) {
						var serieData = data.series[i];
						var serie = new Object();
						serie.name = legendData[i];
						serie.type = 'line';
						serie.symbol = 'emptyCircle';
						serie.showAllSymbol = true;
						serie.data = serieData;
						series.push(serie);
					}
					var option = {
						title : {
							subtext : unitName==''?'':'单位：' + unitName,
							x : 'left',
							y : 'bottom'
						},
						legend : {
							data : legendData
						},
						tooltip : {},
						xAxis : [ {
							type : 'category',
							splitNumber : xAxisData.length,
							boundaryGap : false,
							data : xAxisData
						} ],
						yAxis : [ {
							type : 'value',
							splitNumber : 4,
							axisLabel : {
								formatter : data.formatter
							}
						} ],
						series : series
					};
					myChart.setOption(option);
					_report.addChart(options.panelId, myChart);
					brokenChart.loadedMap[panelId] = myChart;
				}, function() {
					var ele=window.document .getElementById ("qsTitle"); 
					ele.innerHTML = '';
					$('#' + options.panelId).unmask();
				});
			}
	};
	var mdrDetail = {
		panel : 'syjgbListPanel',
		panel2 : 'syjgbListPanelList',
		panel3 : 'qushiPanel',
		resultHigh : '${resultHigh}'.split(','),
		resultLow : '${resultLow}'.split(','),
		rowIndex : '',
		//查询
		queryResult : function(zyid,testOrderNo) {
			$('#'+mdrDetail.panel).datagrid({
	            fit: true,
	            nowrap: true,
	            autoRowHeight: true,
	            striped: true,
	            fitColumns: true,
	            collapsible:true,
	            url:'${webroot}/st011Syjgb/f_json/findSt011SyjgbList.shtml',
	            queryParams: {
	            	'zyid': zyid,
	            	'testOrderNo':testOrderNo
	            },
	            remoteSort: false,
	            singleSelect: true,
		        columns:[
			       	[
						{field:'antiCode',title:'项目编号',sortable:true,width:100},
			            {field:'antiName',title:'项目名称',sortable:true,width:100},
// 			            {field:'yaominResult',title:'药敏结果 ',sortable:true,width:66},
			            {field:'testResult',title:'定量结果',align:'right',sortable:true,width:56},
			            {field:'unit',title:'单位',align:'left',sortable:true,width:60},
			            {field:'referRange',title:'参考范围',sortable:true,width:60},
			            {field:'remark',title:'定性结果',sortable:true,width:60,
							formatter:function(value,rec){
								var remarkStr = '';
								if (rec.remark) {
									if ($.inArray(rec.remark, mdrDetail.resultHigh) != -1) { remarkStr = '↑';} else if ($.inArray(rec.remark, mdrDetail.resultLow) != -1) {remarkStr = '↓';}
								}
								return [remarkStr].join('');
						    }
						}
			            
			        ]
		        ],
		        onBeforeLoad: function (param) {
		        },
		        onLoadSuccess:function(data){ 
		        	$('#'+mdrDetail.panel).datagrid('selectRow', 0);
		        	var curRow = $('#'+mdrDetail.panel).datagrid('getSelected');
		        	var ele=window.document .getElementById ("qsTitle"); 
		        	ele.innerHTML = curRow.antiName +"结果趋势";
		        	brokenChart.report('id_incidence_chart',curRow.zyid, '${classCode}',curRow.antiCode,curRow.unit);
		       },
		       onClickRow:function(rowIndex, rowData){
		          	var ele=window.document .getElementById ("qsTitle"); 
		        	ele.innerHTML = rowData.antiName +"结果趋势";
		        	brokenChart.report('id_incidence_chart',rowData.zyid, '${classCode}',rowData.antiCode,rowData.unit);
		    	},
		        rownumbers:true,
		        rowStyler:function(rowIndex,rowData){
		        	if (rowData && rowData.remark) {
		        		if ($.inArray(rowData.remark, mdrDetail.resultHigh) != -1) {
		        			return 'color:red;';
		        		} else if ($.inArray(rowData.remark, mdrDetail.resultLow) != -1) {
		        			return 'color:blue;';
		        		}
		        	} 
	            },
//	             toolbar:'#tb_div_' + type + '_' + day
			});
	    },
		//获取数据
		select : function() {
			var oprationSelections = $("#"+mdrDetail.panel).datagrid("getSelections");
			var parentObject = parent.Comm.getObjectCache();
			parentObject.setOprationList(oprationSelections);
			parent.Comm.dialogClose('${param.dialogId}');
	    }
	};
	$(document).ready(function () { 
		$('#'+mdrDetail.panel2).datagrid({
			 fit: true,
	            nowrap: true,
	            autoRowHeight: true,
	            striped: true,
	            fitColumns: true,
	            collapsible:true,
	            url:'${webroot}/st011Syjgb/f_json/findSt011SyjgbListLeft.shtml',
	            queryParams: {
	            	'zyid': '${zyid}',
	            	'testOrderNo':'${testOrderNo}',
	            	'classCode':'${classCode}'
	            },
	            remoteSort: false,
	            singleSelect: true,
		        columns:[
			       	[
						{field:'submiAt',title:'送检时间',sortable:true,width:92,
							formatter:function(value,row,index){
								if(mdrDetail.rowIndex == '' && mdrDetail.rowIndex != '0'){
		    						if(row.submiAt == '${submiAt}'){
		    							mdrDetail.rowIndex=index;
		    						}
								}
	    						return row.submiAt;
	    					}
						},
			            {field:'itemTypeName',title:'送检项目',sortable:true,width:115},
			            {field:'yaominResult',title:'结果异常 ',sortable:true,width:70,
			            	formatter:function(value,row,index){
	    						if(row.isexception > 0 || row.isyang > 0){
	    							return [('<span class="r_red"></span>')].join('');
	    						}else{
	        						return [('')].join('');
	    						}
	    					}	
			            },
			        ]
		        ],
		        onLoadSuccess:function(data){ 
 		        	$('#'+mdrDetail.panel2).datagrid('selectRow', mdrDetail.rowIndex);
		        	var curRow = $('#'+mdrDetail.panel2).datagrid('getSelected');
		        	mdrDetail.queryResult(curRow.zyid,curRow.testOrderNo);
		       },
		       onClickRow:function(rowIndex, rowData){
		    	   mdrDetail.queryResult(rowData.zyid,rowData.testOrderNo);
		           $('#viewTestOrderNo')[0].innerHTML = rowData.testOrderNo;
		           $('#viewCheckOutAt')[0].innerHTML = rowData.checkOutAt;
		           $('#viewSubmiAt')[0].innerHTML = rowData.submiAt;
		      },
		});
		
		});
	</script>
		
	</body>
</html>
