<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>感染病例工作量统计</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css?x=${version}" />
<script type="text/javascript" src="${webroot}/resources/js/monitor/jquery.printarea.js"></script>
<style type="text/css">
	.panel-body{border-width:0px;}
</style>
</head>
<body class="easyui-layout">

	<div data-options="region:'west',border:false,title:'查询条件'" style="width:240px;">
		<div id="mainTB">
			<form id="statisticsForm"> 
			<table class="table_cx mb60" cellpadding="0" cellspacing="0" style="width: 100%;">
				<tbody>
					<tr>
						<td class="t_title">时间选择：</td>
						<td>
							 <select name="dateType" id="dateType" style="width:120px;" class="easyui-combobox">
								<option value="1">处理时间</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="t_title">开始时间：</td>
						<td>
							<input type="text" id="queryStartDate" name="queryStartDate" class="Wdate text" style="width:108px;" value="${queryStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</td>
					</tr>					
					<tr>
						<td class="t_title">结束时间：</td>
						<td>
							<input type="text" id="queryEndDate" name="queryEndDate" class="Wdate text" style="width:108px;"  value="${queryEndDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="footer dialog_footer">	
				<div class="footer_btn">
					<div class="n_btn_blue">
						<a href="javascript:;" id="toTrigger" class="toTrigger" onclick="workload.query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
					</div>
				</div>
			</div>
			</form>	
		</div>
	</div>	
	<div id="tbdiv" data-options="region:'center',border:false" style="border-left-width:1px; overflow: hidden;">		
		<div id="toolBar" class="m_search"> 
			<div class="m_search_last">					
				<div class="btn_r">						
					<div class="n_btn_grey">
						<a href="javascript:void(0);" onclick="workload.ExportXls();"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
					</div>														
				</div>		
			</div>
			<div class="m_search_last" style="display: none;">					
				<div class="btn_r">						
					<div class="n_btn_grey">
						<a href="javascript:void(0);" onclick="workload.PrintTable();"><i class="icon iconfont">&#xe604;</i><span>打印</span></a>
					</div>														
				</div>		
			</div>
		</div>
		<div style="border-top: 1px solid #ddd;height: inherit;overflow: auto;">
			<div id="tablePlace" style="margin-bottom: 50px;"></div>
		</div>
	</div>
	
	<script type="text/javascript">
		function saveDate(){
			var startDate =  $('#queryStartDate').val();
			var endDate = $('#queryEndDate').val();
			if(startDate == ''){
				alert("请选择统计开始时间！");
				return false;
			}
			if(endDate == ''){
				alert("请选择统计结束时间！");
				return false;
			}
			var d1 = new Date(startDate.replace(/\-/g, "\/"));  
			 var d2 = new Date(endDate.replace(/\-/g, "\/"));  
			 if(startDate!=""&&endDate!=""&&d2<d1){  
				  alert("结束时间不能小于开始时间！");  
				  return false;  
			 }
			if(startDate != "" && endDate != ""){
				$.ajax({
			     	type: "post",
			          url: "${webroot}/acAccountConfig/f_json/save.shtml",
			          data: {configKey:"reportStartDate",configValue:startDate}
			     });
				$.ajax({
			     	type: "post",
			          url: "${webroot}/acAccountConfig/f_json/save.shtml",
			          data: {configKey:"reportEndDate",configValue:endDate}
			     });
			}
			return true;
		}
		var workload = {
			query : function(){
				if(!saveDate()){
					return;
				}
				$("#tablePlace").load("${webroot}/gr002YsgrMx/f_view/workloadStatisticsTable.shtml",{queryStartDate:$("#queryStartDate").val(),queryEndDate:$("#queryEndDate").val()});
			},
			ExportXls : function(){
				var form=$("<form>");//定义一个form表单
				form.attr("style","display:none");
				form.attr("target","");
				form.attr("method","post");
				form.attr("action","${webroot}/gr002YsgrMx/f_view/exportWST.shtml");
				var input1=$("<input>");
				input1.attr("type","hidden");
				input1.attr("name","queryStartDate");
				input1.attr("value",$("#queryStartDate").val());
				var input2=$("<input>");
				input2.attr("type","hidden");
				input2.attr("name","queryEndDate");
				input2.attr("value",$("#queryEndDate").val());
				$("body").append(form);//将表单放置在web中
				form.append(input1);
				form.append(input2);
				form.submit();//表单提交 
			},
			PrintTable : function(){
				$("#tablePlace").printArea();
			}
		}
		
		window.setTimeout(function() {
			workload.query();
		}, 200);
	</script>
</body>
</html>