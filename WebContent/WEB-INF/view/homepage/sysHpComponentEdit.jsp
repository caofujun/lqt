<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<form id="editFormComponent" method="post">
	<input type="hidden" name="id" value="${sysHpComponent.id}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>组件名称：</td>
				<td><input type="text" name="componentName"
					value="${sysHpComponent.componentName}" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>组件编码：</td>
				<td><input type="text" name="componentCode"
					value="${sysHpComponent.componentCode}" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>所属业务：</td>
				<td>
					<span class="standard_select">
						<span class="select_shelter">
							<nis:select name="codeBusiness" dictcode="business_scope" dictParentCode="0" headerKey="" headerValue="请选择业务域" value="${sysHpComponent.codeBusiness}" cssCls="easyui-validatebox" exp=" required=\"true\""/>
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>组件URL：</td>
				<td><input type="text" name="componentUrl"
					value="${sysHpComponent.componentUrl}" class="easyui-validatebox text" required="true" style="width:90%;"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>展示图片：</td>
				<td ><input type="text" name="imgUrl"
					value="${sysHpComponent.imgUrl}" class="easyui-validatebox text" required="true" style="width:90%;"></td>
			</tr>
			<tr>
				<td class="t_title">应用对象：</td>
				<td>
					<span class="standard_select">
						<span class="select_shelter">
							<nis:select name="scopeLevel" id="dataLevel" headerKey="" headerValue="所有用户" value="${sysHpComponent.scopeLevel}" dictcode="data_scope" cssCls="easyui-validatebox" exp="onChange=\"showLevel(this.value)\""/>
						</span>
					</span>
				</td>
			</tr>
			<%-- <tr id="yh" style="display:none;">
					<td class="t_title">用户：</td>
					<td class="t_cont" colspan="3">
						<input type="text" id="account" name="userName"  style="width:150px;" value="${sysHpComponent.userName}"/>

					</td>
			</tr> --%>
			<tr id="yy" style="display:none;">
					<td class="t_title">医院：</td>
					<td>
						<input type="text" id="unit1_2" class="text" name="unitId"  value="${sysHpComponent.unitId}"/>
					</td>
			</tr>
			<tr id="ks" style="display:none;">
					<td class="t_title">科室：</td>
					<td>
						<input type="text" id="unit2_2" class="text" name="unitId"  value="${sysHpComponent.unitId}"/>
						<input type="text" id="dep2_2" class="text" name="depNo"    value="${sysHpComponent.depNo}"/>
					</td>
			</tr>
			<tr>
				<td class="t_title">菜单权限：</td>
				<td>
					<span class="standard_select">
						<span class="select_shelter">
							<select id="menuCode" name="menuCode" ></select>
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<td class="t_title">组件描述：</td>
				<td>
					<textarea name="remark" class="easyui-validatebox">${sysHpComponent.remark}</textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer"><input type="button"
					class="btn_save" id="changeFormSubmitBtn"
					onclick="$('#editFormComponent').submit()" value="保存"></div>
</form>
<script>
function showLevel(value){
	if(value=='') {
		$("#ks").hide();
		$("#yh").hide();
		$("#yy").hide();
	}else if(value==6){
		$("#ks").hide();
		$("#yh").hide();
		$("#unit2_2").attr("disabled","disabled");
		$("#unit1_2").removeAttr("disabled");
		$("#yy").show();
	}else if(value==3){
		$("#ks").show();
		$("#unit2_2").removeAttr("disabled");
		$("#unit1_2").attr("disabled","disabled");
		$("#yh").hide();
		$("#yy").hide();
	/* }else if(value==0){
		$("#yh").show();
		$("#ks").hide();
		$("#yy").hide();
		$("#unit2_2").attr("disabled","disabled");
		$("#unit1_2").attr("disabled","disabled"); */
	}else if(value==9){
		$("#yh").hide();
		$("#ks").hide();
		$("#yy").hide();
		$("#unit2_2").attr("disabled","disabled");
		$("#unit1_2").attr("disabled","disabled");
	}
}
	$(document).ready(function() {
		window.setTimeout(function() {
			$('#dataLevel option[value="0"]').remove();
			$('#dataLevel option[value="4"]').remove();
			showLevel($("#dataLevel").val());
			$('#account').select2Remote({
				//这里填写空选项时显示的文字
				placeholder: '请输入用户名称',
				//远程加载的url
				url: "${webroot}/acAccount/json/query.shtml",
				//初始化url
				initUrl: "${webroot}/acAccount/json/get.shtml"
			});
			Csm.select.unit({
				id: 'unit1_2'
			});
			Csm.select.unit({
				id: 'unit2_2',
				depId: 'dep2_2'
			});
			Comm.form({
				id : 'editFormComponent',
				url : '${webroot}/sysHpComponent/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						component.query();
						Comm.dialogClose('${param.dialogId}');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : '操作失败！' });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
		
		Csm.select.load({
			id: 'menuCode',
			url: webroot + '/acMenu/f_json/getAllMenu.shtml',
			data: {unitId:0,ownership:'hospital'},
			headerKey: '',
			headerValue: '-- 请选择 --',
			value: '${sysHpComponent.menuCode}',
			kcode: 'menuNo',
			kvalue: 'menuName',
			pid: 'parentMenuNo'
		});
	});
</script>