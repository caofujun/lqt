<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<style>
	body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;overflow: hidden;}
</style>
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<head>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
</head>
<body>
<div class="easyui-layout" style="width: 100%;height: 100%;">
	<div data-options="region:'west',border:false,title:''" style="width:270px; border-right-width: 1px;">
	<form id="searchform${id}" method="post" action="${reportUrl}${reportFile}.cpt&__bypagesize__=${__bypagesize__}" target="reportFrame${id}">
	<input type="hidden" id="unitId" name="unitId" value="${unitId}" />
	<input type="hidden" id="userType" name="userType" value="${account.userType}" />
	<input type="hidden" id="doctorId" name="doctorId" value="${account.doctorId}" />
	<input type="hidden" id="scopeInfo" name="scopeInfo" value="${account.scopeInfo}" />
	<input type="hidden" id="dataScope" name="dataScope" value="${account.dataScope}" />
	<input type="hidden" id="urlPrefix" name="urlPrefix" value="${nisUrl}" />
	<input type="hidden" id="deptType" name="deptType" value="1" />
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<!--tr>
				<td class="t_title">患者维度：</td>
				<td>
					<select style="width:150px" id="dateType" class="easyui-combobox"><option value="s">出院患者人数</option></select>
				</td>
			</tr -->
			<tr>
				<td class="t_title">开始时间：</td>
				<td>
					<input type="text" id="startDate" name="startDate" value="${startDate}" style="width:138px"  class="Wdate text" onclick="WdatePicker()"  />
				</td>
			</tr>
			<tr>
				<td class="t_title">结束时间：</td>
				<td>
					<input type="text" id="endDate" name="endDate" value="${endDate}" style="width:138px" class="Wdate text" onclick="WdatePicker()" />
				</td>
			</tr>
			<tr style="display: none;">
				<td class="t_title">给药途径：</td>
				<td>
					<div class="select_del">
						<select id="gytj" name="gytj" class="easyui-combobox" style="width:150px" >
							<option></option>
							<option value="1">口服</option>
							<option value="2">肌肉注射</option>
							<option value="3">静脉滴注</option>
							<option value="4">静脉注射</option>
						</select>
						<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#gytj').combo('clear');"></a>
					</div>
				</td>
			</tr>
			<tr>
				<td class="t_title">用药目的：</td>
				<td>
					<select id="yymd" name="usePerpose" class="easyui-combobox" style="width:150px" >
						<option value="1">治疗</option>
						<option value="2">预防</option>
						<option value="3">治疗+预防</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_title">药品类别：</td>
				<td>
					<div class="select_del">
						<select id="yplb" name="drugLine" class="easyui-combobox" style="width:150px" >
							<option></option>
							<option value="1">非限制类</option>
							<option value="2">限制类</option>
							<option value="3">特殊类</option>
						</select>
						<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#yplb').combo('clear');"></a>
					</div>
				</td>
			</tr>
			<tr>
				<td class="t_title">送检类别：</td>
				<td>
					<select id="sjlb" name="state" class="easyui-combobox">
						<option value="1">病原学送检</option>
						<option value="2">血培养送检</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:query${id}();"><i class="icon iconfont">&#xe61e;</i><span>统计</span></a>
			</div>
		</div>
	</div>	
	</form>
	</div>
	<div data-options="region:'center',border:false" >
		<iframe width="100%" height="99%" id="reportFrame${id}" name="reportFrame${id}" allowtransparency scrolling="no"  frameborder="0"></iframe>
	</div>
		<script type="text/javascript">
		function query${id}() {
			saveDate${id}();
			$('#searchform${id}').attr("action","${reportUrl}${reportFile}.cpt&__bypagesize__=${__bypagesize__}");
			$('#searchform${id}').submit();
	    }
	    
		function saveDate${id}(){
			var startDate =  $('#startDate').val();
			var endDate = $('#endDate').val();
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
		}
		
			
			$(document).ready(function () {				
				window.setTimeout(function() {
					$('#searchform${id}').submit();
				}, 200);
			});
		</script>
</div>
</body>
</html>