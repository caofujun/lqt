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
	<input type="hidden" id="userType" name="userType" value="${account.userType}" />
	<input type="hidden" id="doctorId" name="doctorId" value="${account.doctorId}" />
	<input type="hidden" id="scopeInfo" name="scopeInfo" value="${account.scopeInfo}" />
	<input type="hidden" id="dataScope" name="dataScope" value="${account.dataScope}" />
	<input type="hidden" id="urlPrefix" name="urlPrefix" value="${nisUrl}" />
	<table class="table_cx h_set" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">日期类型：</td>
				<td>
					<select id="dateType" name="dateType" style="width:150px" class="easyui-combobox" >
						<option value="1">填报日期</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_title">开始时间：</td>
				<td>
					<input type="text" id="startDate${id}" name="startDate" value="${startDate}" style="width:138px"  class="Wdate text" onclick="WdatePicker()"  />
				</td>
			</tr>
			<tr>
				<td class="t_title">结束时间：</td>
				<td>
					<input type="text" id="endDate${id}" name="endDate" value="${endDate}" style="width:138px" class="Wdate text" onclick="WdatePicker()" />
				</td>
			</tr>
			<c:if test="${unitFlag=='1'}">
			<tr>
				<td class="t_title">院区：</td>
				<td>
					<div class="select_del">
					<input id="unitId${id}" name="unitId" style="width:150px;"/>
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#unitId${id}').combo('clear');"></a>
					</div>
				</td>
			</tr>
			</c:if>
			<tr>
				<td class="t_title">科室类别：</td>
				<td>
					<select id="deptType" name="deptType" class="easyui-combobox" style="width:150px;">
						<option value="1">住院</option>
						<option value="2">门诊</option>
						<option value="3">其他</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_title">用品类型：</td>
				<td>
					<nis:select cssCls="easyui-combobox" id="type" name="swsType" exp="style='width: 152px;'" dictcode="swsType" />
				</td>
			</tr>
			<tr>
				<td class="t_title">科室：</td>
				<td>
					<input id="statDept${id}" name="statDept" style="width:150px;"/>
				</td>
			</tr> 
			<tr>
				<td class="t_title" colspan="2" style="text-align: left;">用量较少科室排名前几位：</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="text" id="rownum" name="rownum" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
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
			if(!saveDate${id}()){
				return;
			}
			<c:if test="${roleType=='clinical'}">
				if($('#statDept${id}').val() == ''){
					alert("请选择统计科室！");
					return;
				}
			</c:if>
			$('#searchform${id}').attr("action","${reportUrl}${reportFile}.cpt&__bypagesize__=${__bypagesize__}");
			$('#searchform${id}').submit();
	    }
	    
		function saveDate${id}(){
			var startDate =  $('#startDate${id}').val();
			var endDate = $('#endDate${id}').val();
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

		$(document).ready(function () { 	
			<c:if test="${unitFlag=='1'}">
				Csm.comboBox.unit({
					//【必传】控件名称
					id: 'unitId${id}',
					value: '',
					flag: '1',
					callback: '0'
				});
			</c:if>			

			Csm.combogrid.dep({
				//【必传】控件名称
				id: 'statDept${id}',
				//【可选参数】不传默认区session的医院ID
				hospId: '${doctor.hospId}',
				//【可选参数】不传默认区所有监控科室
				//dataType: '1',
				//iffocus:'${handDepFlag}',
				//临床端默认选择本科室
				<c:if test="${roleType=='clinical'}">
				//【可选参数】1：回调,回调方法setDep()，0：不回调，不传默认回调
				value: '${curDeptId}',				
				</c:if>
				onHidePanel : function() {
		        	Csm.valueValite.combogrid('statDept${id}');
				}
			});
			
			window.setTimeout(function() {
				$('#searchform${id}').submit();
			}, 200);
			
		});
	</script>
</div>
</body>
</html>