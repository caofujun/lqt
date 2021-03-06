<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<jsp:include page="/WEB-INF/view/core/report.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>暴发预警趋势分析</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout">
	<div class="easyui-layout" style="width: 100%;height: 100%;">
		<div data-options="region:'west',border:false,title:'趋势阀值参数'" style="width:200px;">
			<form id="form_trend" method="post">
				<div class="search_tj mb60">
					<div class="search_tj_li">
						<span>选择日期：</span>
						<input class="Wdate" type="text" id="queryStartDate_trend" name="startDate" value="${startDate}" style="width:88px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
					<c:forEach items="${configList}" var="config" varStatus="status">
					<fieldset>
						<legend><c:out value="${config.typeName}"></c:out></legend>
						<input type="hidden" name="configList[${status.index}].typeId" value="${config.typeId}" />	
						<input type="text" style="width: 30px;" name="configList[${status.index}].absoluteDays" value="${config.absoluteDays}" class="easyui-validatebox text" required="true" validType="number" />
						<span>天内出现</span>
						<input type="text" style="width: 30px;" name="configList[${status.index}].absoluteAppear" value="${config.absoluteAppear}" class="easyui-validatebox text" required="true" validType="number" />
						<span>例及以上</span>		
					</fieldset>
					</c:forEach>
				</div>
			</form>
			<div class="footer dialog_footer">	
				<div class="footer_btn">
					<div class="n_btn_blue">
						<a href="javascript:void(0);" id="submitBtn_trend" onclick="$('#form_trend').submit();" ><i class="icon iconfont">&#xe688;</i><span>查询</span></a>
					</div>
					<div class="n_btn_grey">
						<a href="javascript:void(0);" onclick="outbreakTrend.exportExcel();"><i class="icon iconfont"></i><span>导出</span></a>
					</div>
				</div>
			</div>
		</div>
        <div data-options="region:'center',border:false">
        	<div class="easyui-layout" data-options="fit:true">
	        	<div data-options="region:'center',border:false" style="border-left-width: 1px;">
					<div id="outbreakTrendPanel"></div>
				</div>
				<div data-options="region:'south'" style="height:270px;border-width: 1 0 0 1;">
					<div id="report_trend" style="width: 100%;height:250px; padding:8px 0px;"></div>
				</div>
			</div>
        </div>
		
	</div>
<script type="text/javascript">
	var outbreakTrend = {
		panel : 'outbreakTrendPanel',
		showFields : {},
		myChart : {},
		id : '', 
		deptId : '',
		dialogOpen : false,
		
		//导出
		exportExcel : function() {
			var startDate = $('#queryStartDate_trend').val();
			window.location.href = '${webroot}/by007Show/f_json/exportBreakCount.shtml?dataType=trend&startDate=' + startDate;
		},
		
		//图标点击事件
		eConsole : function(param) {
			 if (typeof param.seriesIndex == 'undefined') {      
		     	 return false;      
		     } else if (param.name == '最大值' || param.name == '最小值' || param.name == '平均值') {
		    	 return false;
		     }
		     if (param.type == 'click') {
		    	 if (!outbreakTrend.dialogOpen) {
		    		 outbreakTrend.dialogOpen = true;
		    		 $.ajax({
		                 url: '${webroot}/by007Show/f_json/getByShowId.shtml',
		                 type: 'post',
		                 data: { id: outbreakTrend.id },
		                 dataType: 'json',
		                 success : function(json) {
		                	 var url = '';
		                	 var title = '';
		                	 if ('0607003' == json.absoluteDetailName) {
		                		 title = '血培养人数';
		                		 url = webroot + '/by007Show/f_view/toBloodCultureList.shtml?dataType=trend&id=' + outbreakTrend.id + '&deptId=' + outbreakTrend.deptId + '&moniDate=' + param.name;
		                	 } else if ('pr_stat_by0007_detail' == json.absoluteDetailName) {
		                		 title = '相同耐药谱';
		                		 url = webroot + '/by007Show/f_view/toDrugResistantList.shtml?dataType=trend&id=' + outbreakTrend.id + '&deptId=' + outbreakTrend.deptId + '&moniDate=' + param.name;
		                	 } else if ('0607001' == json.absoluteDetailName) {
		                		 title = '病人列表';
		                		 url = webroot + '/by007Show/f_view/toPatientList.shtml?dataType=trend&id=' + outbreakTrend.id + '&deptId=' + outbreakTrend.deptId + '&moniDate=' + param.name;
		                	 }
		                	 if (url.length > 0) {
		                		 Comm.dialogGlobal({
		     			         	 url: url,
		     			             title: title,
		     			             width:960,
		     			             height:500,
		     			             type:"iframe",
		     			             parent:this,
		     			             closeFn:function(){outbreakTrend.dialogOpen = false; return true;}
		     			         });
		                	 }
		                 }
		     		});
		    	 }
		     } 
		},
		
		//趋势报表
		trendReport : function(id, deptId, deptName, title){
			if (deptId == "undefined") {
				deptId = '';
			}
			outbreakTrend.id = id;
			outbreakTrend.deptId = deptId;
			var startDate = $('#queryStartDate_trend').val();
			var options = {
					panelId : 'report_trend',
					url : webroot+'/by007Show/f_json/findTrendGraphData.shtml',
					data : {endDate : startDate, id : id, deptId : deptId }
			};
			_report.loadByUrlAndQueryParamsToChart(options, function (data) {
				var xAxisData = [];
				var seriesData = [];
				var seriesData1 = [];
				var titleData = data.startDate + ' 至 ' + startDate + ' ' + deptName + ' ' + title + ' 趋势图';
				$.each(data.list,function(index,value){
					xAxisData.push(value.moniDate);
					seriesData.push(value.cnt);
					seriesData1.push(value.line);
				});
				/**柱状图**/
				try {
					if (outbreakTrend.myChart && outbreakTrend.myChart.dispose) {
						outbreakTrend.myChart.dispose(); 
						outbreakTrend.myChart = null;
						_report.removeChart(options.panelId);
					}
				} catch (e) {
					if(window["console"]){
						console.error(e.name + ": " + e.message);
					}
				}
				$('#'+options.panelId).empty();
				outbreakTrend.myChart = echarts.init(document.getElementById(options.panelId));
				var legendData = new Array();
				var yjSeries = new Object();
				if (deptId != '') {
					legendData.push('预警阀值');
					yjSeries.name = '预警阀值';
					yjSeries.type = 'line';
					yjSeries.symbol = 'emptyCircle';
					yjSeries.data = seriesData1;
				}
				legendData.push(data.absolutePeople);
				
				var option  = {
					title:{text : titleData, textStyle : { fontSize: 16, fontWeight: 'bold' } },
					legend: {data : legendData, y : 'bottom'},
				    tooltip : {},
				    toolbox:{show : true,feature : {saveAsImage : {
		                show : true,
		                title : '保存为图片',
		                type : 'jpeg',
		                lang : ['点击本地保存'] 
		            }}},

				    xAxis: [{ type:'category', splitNumber: data.list.length, boundaryGap:false, data:xAxisData ,
			            markLine : {
			                data : [
			                    { type:'value', name: '平均值', value: 38}
			                ]
			            }}],
				    yAxis: [{ type:'value', splitNumber: 6}],
				    series:[
				        {name:data.absolutePeople, showAllSymbol: true, type:'line', data:seriesData,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            }},
			            yjSeries
				    ]
				};
				outbreakTrend.myChart.setOption(option);
				var ecConfig = echarts.config;    
				outbreakTrend.myChart.on(ecConfig.EVENT.CLICK, outbreakTrend.eConsole);
				_report.addChart(options.panelId,outbreakTrend.myChart);	
			},function(){});
		}
	};

	$(document).ready(function () {
		
		window.setTimeout(function() {
			Comm.form({
				id : 'form_trend',
				url : '${webroot}/by007Show/f_json/findTrendFieldsAndWarn.shtml',
				subbtn : 'submitBtn_trend',
				success : function(json) {
					if (json.result === 'success') {
						var startDate = $('#queryStartDate_trend').val();
						var columns = new Array();
						var cols = new Array();
						var colData = new Object();
						colData.field='DEPT_NAME';
						colData.title='科室';
						colData.width=30;
						cols.push(colData);
						if (json.data) {
		            		outbreakTrend.showFields = json.data.showFields;
		            		var warmList = json.data.warmList;
		            		var warms = new Array();
		            		if (warmList && warmList.length > 0) {
		            			for (var i = 0; i < warmList.length; i ++) {
		            				var warm = warmList[i];
		            				warms.push(warm.ID + '##' + warm.DEPT_ID);
		            			}
		            		}
			            	//动态生成表头开始
			            	if (outbreakTrend.showFields && outbreakTrend.showFields.length > 0) {
			            		$.each(outbreakTrend.showFields,function(){
			            			colData = new Object();
			            			colData.field = this.id;
			        				colData.title = this.name;
			        				colData.align = 'center';
			        				colData.width=10;
			        				colData.styler =  function(value,row,index){
			        					if ($.inArray(this.field + '##' + row.DEPT_ID, warms) > 0) {
			        						return 'background-color:#FF8040;color:blue;text-decoration: underline;';
			        					} else {
			        						return 'color:blue;text-decoration: underline;';
			        					}
			        				};
			        				colData.formatter =  function(value,row,index){
			        					return '<a href="javascript:void(0);" onclick="outbreakTrend.trendReport(\'' + this.field + '\',\'' + row.DEPT_ID + '\',\'' + row.DEPT_NAME + '\',\'' + this.title + '\');" >' + value + '</a>';
			        				};
			        				cols.push(colData);
			            		});
			            	}
			            	columns.push(cols);
			        		//动态生成表头结束
			            	$('#' + outbreakTrend.panel).datagrid({
			    	            url: '${webroot}/by007Show/f_json/findTrendAnalysis.shtml',
			    	            queryParams: {
			    	            	'startDate' : startDate
			    	            },
			    	            columns: columns,
			    		        onLoadSuccess : function(data) {
			    		        	outbreakTrend.trendReport(outbreakTrend.showFields[0].id, '', '', '全院');
			    		        }
			    	        });
		            	}
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}
			});
			$('#form_trend').submit();
		}, 100);
		
		$('#' + outbreakTrend.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        rownumbers:true
	    });
		
		//outbreakTrend.query();
	});
</script>
</body>
</html>
