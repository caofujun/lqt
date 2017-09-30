<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>风险分析</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body style="padding:5px;">
		<div class="m_search">			
			<input type="radio" name="dgsType" value="0" checked="checked"/><label>在院</label>
			<input type="radio" name="dgsType" value="1"/><label>出院</label>			
			<span class="pro_text ml20">日期:</span>
			 	<input type="text"  id="startDate" value="${startDate}"  class="Wdate text" onclick="WdatePicker()"  style="width:85px;"/>~
				<input type="text"  id="endDate" value="${endDate}" class="Wdate text"  onclick="WdatePicker()"  style="width:85px;"/>
			 	
			<div class="n_btn_blue">
				<a href="javascript:;"  onclick="fxPatientIndex.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">				
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="fxPatientIndex.exportFile()"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
				</div>
			</div>					
		</div>
		
			<div style="height:300px; margin:5px;">				
					<div id="fxPatientIndexPanel"></div>					
				
				<!-- <td>
					<div class="Risk_infor">
						<div class="Risk_infor_t">其他相关指标：</div>
						<ul class="Risk_infor_main">
							<li>1、患者风险因素类别趋势图<a href="#">查看</a></li>
							<li>2、患者风险因素与感染病例关联图<a href="#">查看</a></li>
							<li>3、患者风险因素管理过程明细表<a href="#">查看</a></li>
							<li>4、患者风险因素类别趋势图<a href="#">查看</a></li>
							<li>5、患者风险因素与感染病例关联图<a href="#">查看</a></li>
							<li>6、患者风险因素管理过程明细表<a href="#">查看</a></li>
						</ul>
						<div class="Risk_infor_b"><a href="#">查看更多</a></div>
					</div>				
				</td> -->
			</div>
			<div style="text-align:center; margin-top:20px;">				
				<div class="btn-group" style="padding:10px 0px;">		
					<button type="button" id="dayButton" class="btn btn-default cur" onClick="fxPatientIndex.chooseDateType('day')">日</button>
					<button type="button" id="monthButton" class="btn btn-default" onClick="fxPatientIndex.chooseDateType('month')">月</button>
				</div>
				<div id="report" style="width: 100%;height:300px; padding:20px 0px;"></div>				
			</div>
		<jsp:include page="/WEB-INF/view/core/report.jsp"></jsp:include>
		<div id="pop">
			<iframe style="display: none;" id="download"></iframe>
			<div id="loading" style="margin-top: 35px;margin-left: 80px; display: none;">
				<img src="${webroot}/resources/images/loading.gif"/>
				<span>下载数据加载中...</span>
			</div>
		</div>
		<script type="text/javascript">
			var fxPatientIndex = {
				panel : 'fxPatientIndexPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+fxPatientIndex.panel).datagrid({
			            queryParams: {
			            	'startDate': $('#startDate').val(),
			                'endDate': $('#endDate').val(),
			                'dgsType':$('input[name="dgsType"]:checked ').val()
			            }
			        });
					fxPatientIndex.report('','day');
			    },
			    exportFile:function(){
			    	$('#pop').window({
			    		  title:'风险分析导出',   
					      width:250,   
					      height:150,   
					      modal:true  
				 	 });
				 	 
				 	$('#loading').show();
				 	$('#download').attr('src','${webroot}/fxPatient/f_json/exportDeptFx.shtml?startDate='+$('#startDate').val()+'&endDate='+$('#endDate').val()+'&dgsType='+$('input[name="dgsType"]:checked ').val());
				 	 window.setTimeout(function(){
				 	 	$('#loading').hide();
				 	 	$('#pop').window('close');
				 	 },5000);
			    },
			    chooseDateType : function(dateType){
			    	$('#dayButton').attr("class","btn btn-default");
			    	$('#monthButton').attr("class","btn btn-default");
			    	$("#"+dateType+"Button").attr("class","btn btn-default cur");
			    	fxPatientIndex.report('',dateType);
			    },
			  	//报表1
				report : function(deptCode,dateType){
					var startTime=$('#startDate').val();
					var endTime=$('#endDate').val();
					var dgsType=$('input[name="dgsType"]:checked ').val();			
					var options = {
							panelId : 'report',
							url : webroot+'/fxPatient/f_json/report.shtml',
							data : {queryStartDate : startTime,queryEndDate : endTime,deptCode:deptCode,dgsType:dgsType,dateType:dateType}
					};
					_report.loadByUrlAndQueryParamsToChart(options, function (data) {
						var seriesData = [];
						var seriesData2 = [];
						var xAxisData = [];
						var titleData="";
						$.each(data,function(index,value){
							seriesData.push(value.COUNT);
							seriesData2.push(value.COUNT2);
							xAxisData.push(value.DATE);
							if(value.DATE.length==7){
								if(index==0){
									titleData = value.DATE;
								}else if(index==11){
									titleData = titleData+"-"+value.DATE;
								}
							}else if(value.DATE.length==5){
								if(index==0){
									titleData = value.DATE;
								}else if(index==14){
									titleData = titleData+"-"+value.DATE;
								}
							}
						});
						titleData = titleData+"在院人数与高风险人数时序图";
						/**柱状图**/
						_report.removeChart(options.panelId);
						report_t = echarts.init(document.getElementById(options.panelId));
						var option  = {
							title:{text:titleData},
							legend: {data:['在院人数','高风险人数'],y:'bottom'},
						    tooltip : {},
						    toolbox:{show : true,feature : {saveAsImage : {
				                show : true,
				                title : '保存为图片',
				                type : 'jpeg',
				                lang : ['点击本地保存'] 
				            }}},
						    xAxis: [{data : xAxisData, axisLabel:{interval:0 },splitLine:{show: false}}],
						    yAxis : [{name:'在院人数',type:'value',splitLine:{show: false}},{name:'高风险人数',type:'value',splitLine:{show: false}}],
						    series : [{name:'在院人数',type : 'line',data : seriesData2,itemStyle:{normal:{color:'green'}}},{name:'高风险人数' ,type : 'line',data : seriesData,yAxisIndex: 1,itemStyle:{normal:{color:'red'}}}]
						};
						report_t.setOption(option);
						_report.addChart(options.panelId,report_t);	
					},function(){});
				},				
				//查看
				show : function(deptCode,gyStatus,pgStatus,cxStatus) {
					parent.menuInfo.clickMenu('患者风险详情','/fxPatient/f_view/fxPatientList.shtml?deptCode='+deptCode+"&startDate="+$('#startDate').val()+"&endDate="+ $('#endDate').val()+"&dgsType="+$('input[name="dgsType"]:checked ').val()+"&gyStatus="+gyStatus+"&pgStatus="+pgStatus+"&cxStatus="+cxStatus,true);
		    	}
			   
			};
			$(document).ready(function () {
				fxPatientIndex.report('','day');
				$('#'+fxPatientIndex.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	    	        border:true,
	                url:'${webroot}/fxPatient/f_json/FxPatientIndex.shtml',
	                queryParams: {
		            	'startDate': $('#startDate').val(),
		                'endDate': $('#endDate').val(),
		                'dgsType':$('input[name="dgsType"]:checked ').val()
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
		                    {field:'deptName',title:'科室名称',sortable:true,width:22},
		                    {field:'fxCount',title:'高风险人数',sortable:true,width:15,
		                    	formatter:function(value,r){
									return ['<a href="javascript:fxPatientIndex.show(\'',r.deptCode,'\',\'\');">',r.fxCount,'</a>'].join('');
								}	
		                    },
		                    {field:'ygyCount',title:'已干预人数',sortable:true,width:15,
		                    	formatter:function(value,r){
									return ['<a href="javascript:fxPatientIndex.show(\'',r.deptCode,'\',\'1,2\');">',r.ygyCount,'</a>'].join('');
								}	
		                    },
		                    {field:'wgyCount',title:'未干预人数',sortable:true,width:15,
		                    	formatter:function(value,r){
									return ['<a href="javascript:fxPatientIndex.show(\'',r.deptCode,'\',\'0\');">',r.wgyCount,'</a>'].join('');
								}	
		                    }, 
		                    {field:'ypgCount',title:'已评估',sortable:true,width:12,
		                    	formatter:function(value,r){
									return ['<a href="javascript:fxPatientIndex.show(\'',r.deptCode,'\',\'\',\'1\');">',r.ypgCount,'</a>'].join('');
								}	
		                    },
		                    {field:'wpgCount',title:'未评估',sortable:true,width:12,
		                    	formatter:function(value,r){
									return ['<a href="javascript:fxPatientIndex.show(\'',r.deptCode,'\',\'\',\'0\');">',r.wpgCount,'</a>'].join('');
								}	
		                    },
		                    {field:'wzxCount',title:'措施未执行',sortable:true,width:15,
		                    	formatter:function(value,r){
									return ['<a href="javascript:fxPatientIndex.show(\'',r.deptCode,'\',\'\',\'\',\'0\');">',r.wzxCount,'</a>'].join('');
								}	
		                    },
		                    {field:'yqxCount',title:'措施执行有缺陷',sortable:true,width:20,
		                    	formatter:function(value,r){
									return ['<a href="javascript:fxPatientIndex.show(\'',r.deptCode,'\',\'\',\'\',\'1\');">',r.yqxCount,'</a>'].join('');
								}	
		                    }
		                ]
	                ]
	            });
			});
		</script>
	</body>
</html>
