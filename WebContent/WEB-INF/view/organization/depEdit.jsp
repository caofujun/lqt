<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormDep" method="post">
	<input type="hidden" name="id" value="${dep.id}"/>
	<input type="hidden" id="deptTypeName" name="deptTypeName" value="${dep.deptTypeName}"/>
	<input type="hidden" id="baseInfect" name="baseInfect" value="${dep.baseInfect}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width:35%;"><span class="red">*</span>科室编号：</td>
				<td><input type="text"  id="code" name="deptId" 
					value="<c:out value="${dep.deptId}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>科室名称：</td>
				<td><input type="text"  id="code" name="deptName" 
					value="<c:out value="${dep.deptName}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>院区：</td>
				<td>
					<input id="id_hospId" name="hospId" style="width:150px;"/>
				</td>
			</tr>
			<tr>
				<td class="t_title">科室类别：</td>
				<td>				
					<nis:select name="deptTypeId" id="deptTypeId" value="${dep.deptTypeId}"  exp="onchange='onchangeDeptTypeId(this.value)'" dictcode="dept_type" cssCls="easyui-validatebox" />				
				</td>
			</tr>
			<tr> 
				<td class="t_title">科室分类：</td>
				<td>
					<nis:select name="deptClassify" id="deptClassify" headerKey="" headerValue="请选择"  value="${dep.deptClassify}"   dictcode="dept_classify"  />				
				</td>	
			</tr>
			<tr> 
				<td class="t_title">对应标准科室：</td>
				<td>
					<select name="standDeptId" id="standDeptId" style="width:152px;" ></select>（影响现患率直报）
				</td>	
			</tr>
<!-- 			<tr> -->
<!-- 				<td class="t_title">现患率基准数：</td> -->
<!-- 				<td><input type="text"  id="baseInfect" name="baseInfect"  -->
<%-- 					value="<c:out value="${dep.baseInfect}" />" class="easyui-validatebox text"></td> --%>
<!-- 			</tr> -->
			<tr>
				<td class="t_title">科室电话：</td>
				<td><input type="text"  id="tel" name="tel" 
					value="<c:out value="${dep.tel}" />" class="easyui-validatebox text"></td>
			</tr>
			<tr>
				<td class="t_title">排序号：</td>
				<td><input type="text"  id="showOrder" name="showOrder" 
					value="<c:out value="${dep.showOrder}" />" class="easyui-validatebox text"></td>
			</tr>
			<tr>
				<td style="text-align: right;color: #333;vertical-align: text-top;font-weight: bold;">属性设置：</td>
				<td>
				    <label style="display: inline-block;width: 110px;"><input type="checkbox" <c:if test="${dep.ifcaseoffice=='1'}">checked</c:if> id="ifcaseoffice" name="ifcaseoffice" value="1"/>病例监测科室</label>
				    <label style="display: inline-block;width: 110px;"><input type="checkbox" <c:if test="${dep.ifenvoffice=='1'}">checked</c:if> id="ifenvoffice" name="ifenvoffice" value="1"/>环境监测科室</label>
				 </td>
			</tr>
			<tr>
				<td></td>
				<td>
				    <label style="display: inline-block;width: 110px;"><input type="checkbox" <c:if test="${dep.ificu=='1'}">checked</c:if> id="ificu" name="ificu" value="1"/>ICU科室</label>
				    <label style="display: inline-block;width: 110px;"><input type="checkbox" <c:if test="${dep.ifchildoffice=='1'}">checked</c:if> id="ifchildoffice" name="ifchildoffice" value="1"/>新生儿科室</label>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
				    <label style="display: inline-block;width: 110px;"><input type="checkbox" <c:if test="${dep.ifoperoffice=='1'}">checked</c:if> id="ifoperoffice" name="ifoperoffice" value="1"/>手术科室</label>
<%-- 				    <label><input type="checkbox" <c:if test="${dep.ifhospdept=='1'}">checked</c:if> id="ifhospdept" name="ifhospdept" value="1"/>住院科室</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <label><input type="checkbox" <c:if test="${dep.ifmzoffice=='1'}">checked</c:if> id="ifmzoffice" name="ifmzoffice" value="1"/>门诊科室</label><br> --%>
				    <label style="display: inline-block;width: 110px;"><input type="checkbox" <c:if test="${dep.ifbedicu=='1'}">checked</c:if> id="ifbedicu" name="ifbedicu" value="1"/>ICU床位检测科室</label>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
				    <label style="display: inline-block;width: 110px;"><input type="checkbox" <c:if test="${dep.ifmzoffice=='1'}">checked</c:if> id="ifmzoffice" name="ifmzoffice" value="1"/>手卫生调查科室</label>
				    <label style="display: inline-block;width: 110px;"><input type="checkbox" <c:if test="${dep.flag=='1'}">checked</c:if> id="flag" name="flag" value="1"/>是否有效</label>
<%-- 				<label><input type="checkbox" <c:if test="${dep.hosinfectBaseper=='1'}">checked</c:if> id="hosinfectBaseper" name="hosinfectBaseper" value="1"/>医院感染基准科室</label>&nbsp;&nbsp;
				    <label><input type="checkbox" <c:if test="${dep.outhosinfectBaseper=='1'}">checked</c:if> id="outhosinfectBaseper" name="outhosinfectBaseper" value="1"/>社区感染基准科室</label>&nbsp;&nbsp;&nbsp;&nbsp; --%>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn"onclick="$('#editFormDep').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
	$(document).ready(function() {		
		window.setTimeout(function() {
			Csm.select.load({
				id : 'standDeptId',
				url : webroot+ '/standDept/f_view/queryWithCdt.shtml',
				data : {'flag':1},
				headerKey : '',
				headerValue : '-- 请选择 --',
				value : '${dep.standDeptId}',
				kcode : 'deptId',
				kvalue : 'deptName',
				pid : 'standDeptId'
				});
			Comm.form({
				id : 'editFormDep',
				url : '${webroot}/dep/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						var parentObject = Comm.getObjectCache();
						parentObject.query();
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
						//menuInfo.clickMenu('科室信息','/dep/f_view/index.shtml',true,'A0102');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
			
			Csm.comboBox.unit({
				//【必传】控件名称
				id: 'id_hospId',
				value: '${dep.hospId}',
				required:true,
				flag: '1',
				callback: '0'
			});

		}, 100);
	});
	
	/*
	 *查询数据
	 */
	function onchangeDeptTypeId(deptTypeId){
		$('#deptTypeName').val($("#deptTypeId").find("option:selected").text());
	}
</script>