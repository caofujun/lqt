<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormSysParam" method="post">
	<input type="hidden" name="id" value="${sysParam.id}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>参数名称：</td>
				<td>
					<%-- <select name="paramName"  class="easyui-validatebox" required="true" id="kvName" onchange="showKey()">
						<option value="">--请选择--</option>
						<c:forEach items="${kvList}" var="kv">
							<option value="${kv.value}" title="${kv.key}" <c:if test="${kv.key==sysParam.paramCode}">selected="selected"</c:if>>${kv.value}</option>
						</c:forEach>
					</select> --%>
					<input id="paramId"  style="width:420px"/>
					<input name="paramName" id="paramName" type="hidden" value="${sysParam.paramName}">
				</td>
			</tr>
			<tr>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>参数编码：</td>
				<td><input type="text"  id="code" name="paramCode" style="width: 400px;"
					value="<c:out value="${sysParam.paramCode}" />" class="easyui-validatebox text" required="true" readonly="readonly"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>参数值：</td>
				<td><input type="text" name="paramValue" style="width: 400px;"
					value="<c:out value="${sysParam.paramValue}" />" class="easyui-validatebox text" required="true">
				</td>
			</tr>
			<tr>
				<td class="t_title">参数类型：</td>
				<td>
					<nis:select name="paramType" id="paramType" value="${sysParam.paramType}" dictcode="param_type"  cssCls="easyui-validatebox" exp="required=\"true\" "/>				
				</td>
			</tr>
			<tr id="scope_Level" style="display:none">
				<td class="t_title">应用对象：</td>
				<td>
					<nis:select name="scopeLevel" id="dataLevel" value="${sysParam.scopeLevel}" dictcode="use_scope"  cssCls="easyui-validatebox" exp="required=\"true\" onChange=\"showLevel(this.value)\""/>				
				</td>
			</tr>
			<tr id="yh" style="display:none;">
					<td class="t_title">用户：</td>
					<td colspan="3">
						<input type="text" id="account" class="text" name="userName"  value="${sysParam.userName}"/>
					</td>
			</tr>
			<tr id="yy" style="display:none;">
					<td class="t_title">医院：</td>
					<td  colspan="3">
						<input type="text" id="unit1_2" class="text" name="unitId"  value="${sysParam.unitId}"/>
					</td>
			</tr>
			<tr id="ks" style="display:none;">
					<td class="t_title">科室：</td>
					<td colspan="3">
						<input type="text" id="unit2_2" class="text" name="unitId" value="${sysParam.unitId}"/>
						<input type="text" id="dep2_2" class="text" name="depNo"   value="${sysParam.depNo}"/>
					</td>
			</tr>
			<tr>
				<td class="t_title">参数描述：</td>
				<td  colspan="3"><textarea name="remark"
						style="width: 90%; height: 60px;" class="easyui-validatebox"><c:out value="${sysParam.remark}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormSysParam').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>
	</div>
</form>
<script>
	function showKey(key){
		$("#code").val($('#paramId').combobox('getValue'));
		$("#paramName").val($('#paramId').combobox('getText'));
	}
	function showLevel(value){
		if(value==6){
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
		}else if(value==0){
			$("#yh").show();
			$("#ks").hide();
			$("#yy").hide();
			$("#unit2_2").attr("disabled","disabled");
			$("#unit1_2").attr("disabled","disabled");
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
			$("#paramId").combobox({
				delay: 1000,   
				panelWidth:420,
				mode: 'remote',
				url: '${webroot}/sysParam/json/queryList.shtml',
				valueField: 'key',
			    textField: 'value',
			    value:"${sysParam.paramCode}",
			   
			    onSelect : function(){
			    	showKey();
				},
			});
			Comm.form({
				id : 'editFormSysParam',
				url : '${webroot}/sysParam/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
// 						sysParam.query();
						Comm.dialogClose('${param.dialogId}');
						menuInfo.clickMenu('参数管理','/sysParam/f_view/index.shtml',true,'sys_bd_param');
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