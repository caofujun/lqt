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
	<input type="hidden" id="deptType" name="deptType" value="1" />
	<table class="table_cx h_set" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">时间选择：</td>
				<td>
					<select style="width:150px" id="dateType" name="dateType" class="easyui-combobox">
						<option value="1">送检时间</option>
						<option value="2">检出时间</option>
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
			<tr>
				<td class="t_title">检出菌类：</td>
				<td>
				<select style="width:152px" id="pathoType${id}" name="pathoType" class="easyui-combobox"><option value="">全部</option><option value="z">重点菌</option><option value="f">非重点菌</option></select>
				</td>
			</tr>
			<tr style="display: none;">
				<td class="t_title">感染部位：</td>
				<td>
					<select style="width:150px" id="infectPart" class="easyui-combobox">
						<option value="">不做限制</option>
					</select>
				</td>
			</tr>
			<tr style="display: none;">
				<td class="t_title">革兰氏菌：</td>
				<td>
					<select style="width:150px" id="x" class="easyui-combobox">
						<option value="G-">G-</option>
						<option value="G+">G+</option>
					</select>
				</td>
			</tr>
			<tr style="display: none;">
				<td class="t_title">菌属分类：</td>
				<td>
					<select style="width:150px" id="y" class="easyui-combobox">
						<option value="">不做限制</option>
					</select>
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
			<tr style="line-height: 20px;">
				<td></td>
			</tr>
			<tr style="display: none;">
				<td colspan="2" style="text-align: center;">
					<label><input type="checkbox" name="OI" id="OI" value="1" />&nbsp;&nbsp;只看重点菌&nbsp;&nbsp;&nbsp;&nbsp;</label>
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
		<a class="report_help" href="javascript:;" title="帮助" onclick="help${id}();" style="display: none;"></a>
		<iframe width="100%" height="99%" id="reportFrame${id}" name="reportFrame${id}" allowtransparency scrolling="no" frameborder="0"></iframe>
	</div>
	</div>
	<script type="text/javascript">
		function help${id}(){
			Comm.dialogGlobal({
	        	url:"${webroot}/reportExplain/f_view/index.shtml?reportName=${reportFile}",
	            title: "帮助",
	            type:"iframe",
	            width:800,
	            height:400
	        });
		}
		function query${id}() {
			if(!saveDate${id}()){
				return;
			}
			/* var bbsx = $('#bbsx${id}').combobox('getValues');
			if(bbsx!=''){
				bbsx ="'"+$('#bbsx${id}').combobox('getValues')+"'";
				bbsx = bbsx.replace(/,/g,"','");
			}
			$('#bbsxName${id}').val(bbsx);
			$('#grTypeName${id}').val($('#gr_type${id}').combobox('getValues')) */
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
			window.setTimeout(function() {
				$('#searchform${id}').submit();
			}, 200);
		});
	</script>

</body>
</html>