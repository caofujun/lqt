<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormDoctor" method="post">
	<input type="hidden" name="id" value="${doctor.id}"/>
	<input type="hidden" name="unitId" id="unit" value="${user.unitId}"/>
	<input type="hidden" name="hospId" id="hospId" value="${doctor.hospId}"/>
	<input type="hidden" name="authCode" id="authCode" value="${doctor.authCode}"/>
	<input type="hidden" name="state" id="state" value="${doctor.state}"/>	
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>员工编号：</td>
				<td><input type="text"  id="employeeId" name="employeeId" 
					value="<c:out value="${doctor.employeeId}" />" class="easyui-validatebox text" validType="extis['username']" style="width:141px" required="true"></td>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>员工名称：</td>
				<td><input type="text"  id="employeeName" name="employeeName" 
					value="<c:out value="${doctor.employeeName}" />" class="easyui-validatebox text" style="width:141px" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 85px;">所属科室：</td>
				<td>
					<div class="select_del"><input id="dep" name="deptId"  /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#dep').combo('clear');"></a></div>					
				</td>
				<td class="t_title" style="width: 85px;">员工类型：</td>
				<td>
					<nis:select name="cclass" id="cclass" value="${doctor.cclass}" dictcode="doctor_type" headerKey="" headerValue="请选择" cssCls="easyui-validatebox easyui-combobox"/>				
				</td>
			</tr>
			<tr>
				<td class="t_title" style="width: 85px;">联系电话：</td>
				<td><input type="text"  id="phoneNo" name="phoneNo" style="width:141px" validType="extis['mobilenum']"
					value="<c:out value="${doctor.phoneNo}" />" class="easyui-validatebox text"></td>
				<td class="t_title" style="width: 85px;">邮箱地址：</td>
				<td><input type="text"  id="email" name="email" style="width:141px" validType="extis['email']"
					value="<c:out value="${doctor.email}" />" class="easyui-validatebox text"></td>
			</tr>
			<tr>
				<td class="t_title">属性设置：</td>
				<td colspan="3">
				    <label><input type="checkbox" <c:if test="${doctor.operDoc=='1'}">checked</c:if> id="operDoc" name="operDoc" value="1"/>手术医生</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <label><input type="checkbox" <c:if test="${doctor.authMode=='1'}">checked</c:if> id="authMode" name="authMode" value="1"/>科室管理员</label>
				</td>
			</tr>
			<tr>
				<td class="t_title">角色：</td>
				<td>
				   <select name="roleId"  class="easyui-combobox">
				   			<option value="">请选择</option>
						<c:forEach items="${roles}" var="role">
							<option value="${role.roleId}" <c:if test="${role.roleId eq doctor.roleId}">selected="selected"</c:if>>${role.name}</option>
						</c:forEach>
					</select>
				</td>
				<td class="t_title" style="width: 85px;">线别：</td>
				<td>
					<nis:select name="drLinetype" id="drLinetype" value="${doctor.drLinetype}" headerKey="" headerValue="请选择" dictcode="dr_linetype" cssCls="easyui-validatebox easyui-combobox"/>				
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormDoctor').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
$.extend($.fn.validatebox.defaults.rules, {
	extis: {
		validator:function(value,param){
			var _bool = true;
			var userId = '${doctor.employeeId}';
			if(value!=''){
				if(param[0]=="mobilenum"){
					if(/^(13|14|15|18)\d{9}$/.test(value)==false){
						_bool = false;
						$.fn.validatebox.defaults.rules.extis.message ="手机号码格式不正确！";
					} else {
						$.ajax({
	                        url: '${webroot}/acAccount/f_json/checkExtis.shtml',
	                        type: 'post',
	                        data: { userId : userId, mobilenum : $("#phoneNo").val() },
	                        dataType: 'json',
	                        async:false,
	                        success : function(json) {
								if(json.result === 'error') {
									_bool = false;
									$.fn.validatebox.defaults.rules.extis.message ="已被使用!";
						    	}
							}
	            		});
					}
				}
				if(param[0]=="email"){
					if(/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9_]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(value)==false){
						_bool = false;
						$.fn.validatebox.defaults.rules.extis.message ="邮箱格式不正确！";
					} else {
						$.ajax({
	                        url: '${webroot}/acAccount/f_json/checkExtis.shtml',
	                        type: 'post',
	                        data: { userId : userId, email : $("#email").val() },
	                        dataType: 'json',
	                        async:false,
	                        success : function(json) {
								if(json.result === 'error') {
									_bool = false;
									$.fn.validatebox.defaults.rules.extis.message ="已被使用!";
						    	}
							}
	            		});
					}
				}
				if(param[0]=="username"){
					if(/^[A-Za-z0-9]*?$/.test(value)==false){
						_bool = false;
						$.fn.validatebox.defaults.rules.extis.message ="请填入数字或者字母！";
					} else {
						$.ajax({
	                        url: '${webroot}/acAccount/f_json/checkExtis.shtml',
	                        type: 'post',
	                        data: { userId : userId, username : $("#employeeId").val() },
	                        dataType: 'json',
	                        async:false,
	                        success : function(json) {
								if(json.result === 'error') {
									_bool = false;
									$.fn.validatebox.defaults.rules.extis.message ="已被使用!";
						    	}
							}
	            		});
					}
				}
			}
			return _bool;
		},
		message:''
	}
});

	$(document).ready(function() {
		window.setTimeout(function() {
			Csm.combogrid.dep({
				//【必传】控件名称
				id: 'dep',
				//【可选参数】下拉列表的默认value，不传则没有默认值；
				value: '${doctor.deptId}',
				//【可选参数】不传默认区session的医院ID
				hospId: '${doctor.hospId}',
				//【可选参数】1：回调,回调方法setDep()，0：不回调，不传默认回调
				callback: '0',
			});
			
			Comm.form({
				id : 'editFormDoctor',
				url : '${webroot}/doctor/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						var parentObject = Comm.getObjectCache();
						parentObject.query();
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
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