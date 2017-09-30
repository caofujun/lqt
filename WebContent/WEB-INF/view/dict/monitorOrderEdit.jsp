<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormMonitorOrder" method="post">
	<input type="hidden" name="orderCode" value="${monitorOrder.orderCode}"/>
	<input type="hidden" id="className" name="className" value="${monitorOrder.className}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 120px;">监控项目类型：</td>
				<td>
					<nis:select name="classCode" id="classCode" value="${monitorOrder.classCode}"  exp="onchange='onchangeClassCode(this.value)' style='width: 312px;'" dictcode="monitor_type" cssCls="easyui-validatebox easyui-combobox"/>				
				</td>
			</tr>
			<tr>
				<td class="t_title" style="width: 100px;"><span class="red">*</span>监控项目名称：</td>
				<td><input type="text"  id="orderName" name="orderName" style="width: 300px;"
					value="<c:out value="${monitorOrder.orderName}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 100px;">拼音码：</td>
				<td>
					<input type="text"  id="spCode" name="spCode" style="width: 300px;" value="<c:out value="${monitorOrder.spCode}" />" class="easyui-validatebox text" required="true">
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormMonitorOrder').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			$('#orderName').live('blur',function() {
			$("#spCode").val($(this).pinyinFirstLower().toUpperCase());});
			$('#className').val($("#classCode").find("option:selected").text());
			Comm.form({
				id : 'editFormMonitorOrder',
				url : '${webroot}/monitorOrder/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
						menuInfo.clickMenu('不猛管理','/monitorOrder/f_view/index.shtml',true,'A0102');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
	});
	
	/*
	 *查询数据
	 */
	function onchangeClassCode(classCode){
		$('#className').val($("#classCode").find("option:selected").text());
	}
</script>