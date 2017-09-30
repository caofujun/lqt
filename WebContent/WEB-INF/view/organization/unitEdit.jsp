<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormUnit" method="post">
	<input type="hidden" name="id" value="${unit.unitId}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>医院编号：</td>
				<td><input type="text"  id="code" name="unitId" 
					value="<c:out value="${unit.unitId}" />" class="easyui-validatebox" required="true"></td>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>医院名称：</td>
				<td><input type="text"  id="code" name="hospName" 
					value="<c:out value="${unit.hospName}" />" class="easyui-validatebox" required="true"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>医院类型：</td>
				<td>
					<nis:select name="hospType" id="hospType"  value="${unit.hospType}" dictcode="hosp_type"  cssCls="easyui-validatebox easyui-combobox" exp="required=\"true\""/>				
				</td>
				<td class="t_title"><span class="red">*</span>医院等级：</td>
				<td>
					<nis:select name="hospLevel" id="hospLevel" value="${unit.hospLevel}" dictcode="hosp_level"  cssCls="easyui-validatebox easyui-combobox" exp="required=\"true\""/>			
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>医院性质：</td>
				<td>
					<nis:select name="hospNature" id="hospNature" value="${unit.hospNature}" dictcode="hosp_nature"  cssCls="easyui-validatebox easyui-combobox" exp="required=\"true\""/>				
					
				</td>
				<td class="t_title">床位数：</td>
				<td><input type="text" name="beds" 
					value="<c:out value="${unit.beds}" />" class="easyui-validatebox text" required="true">
				</td>
			</tr>
			<tr>
				<td class="t_title">是否分院：</td>
				<td >
				    <nis:radio name="ifbranch" dictcode="boolean" defvalue="1" value="${unit.ifbranch}"/>
				</td>
				<td class="t_title">启用状态：</td>
				<td >
				    <nis:radio name="flag" dictcode="enable_status" defvalue="1" value="${unit.flag}"/>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn"	onclick="$('#editFormUnit').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>
	</div>	
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormUnit',
				url : '${webroot}/unit/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
						menuInfo.clickMenu('医院信息','/unit/f_view/index.shtml',true,'A0101');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
	});
</script>