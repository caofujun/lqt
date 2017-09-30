<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加手术</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout">
<form id="editFormOperation" method="post">
	<input type="hidden" name="zyid" value="${st003Cryxxb.zyid}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 140px;"><span class="red">*</span>手术名称：</td>
				<td><input type="text" id="operName" name="operName" class="easyui-validatebox" style="width:150px;"/></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>手术医生:</td>
				<td>
					<input type="hidden" id="id_opedocName" name="opedocName"/>
					<input type="text" id="id_opedocId" style="width: 160px;" name="opedocId" />
				</td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>手术日期：</td>
				<td><input type="text" class="Wdate text easyui-validatebox" id="operAt" name="operAt" style="width: 150px;" onclick="WdatePicker()" /></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>手术开始时间:</td>
				<td><input type="text" class="Wdate text easyui-validatebox" id="operAtStart" name="operAtStart" style="width: 150px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" /></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>手术结束时间:</td>
				<td><input type="text" class="Wdate text easyui-validatebox" onblur="opration.getDiffMinute()" id="operAtEnd" name="operAtEnd" style="width: 150px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" /></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>持续时间:</td>
				<td><input type="text" style="width: 150px;" id="operLengTime" class="easyui-validatebox" name="operLengTime" />(分钟)</td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>切口等级:</td>
				<td><nis:select dictcode="incision_type" cssCls="easyui-combobox" name="incisionGrade" headerKey="" headerValue="" exp="style=\"width: 160px;\" required=\"true\"" /></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>ASA评分:</td>
				<td>
	            	<select name="asa" class="easyui-combobox" style="width: 160px;">
						<option value=""></option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;"  id="changeFormSubmitBtn" onclick="$('#editFormOperation').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>				
	</div>
</form>
	<script type="text/javascript">
	var opration = {
		panel : 'oprationPanel',
		//处理手术时间相减
		 getDiffMinute : function(){
			var operAtStart = $("#operAtStart").val();
			var operAtEnd = $("#operAtEnd").val();
			if(operAtStart!=''&&operAtEnd!=''){
				var reg=new RegExp("-","g"); //创建正则RegExp对象    
				var date1 = operAtStart.replace(reg,"/") ; 
				var date2 = operAtEnd.replace(reg,"/") ; 
				var date1 = new Date(date1) ; 
				var date2 = new Date(date2); 
				var s1 = date1.getTime(); 
				var s2 = date2.getTime();
				$("#operLengTime").val(parseInt((s2-s1)/60000));
			}
		},
		//获取数据
		addSimple : function() {
			document.location.href = "${webroot}/st005Ssxxb/f_view/addSimple.shtml?patientId=${param.patientId}&zyid=${param.zyid}&operAt=${param.operAt}";
	    }
	};
	$(document).ready(function () { 		
		//手术医生
		Csm.combogrid.doctor({
			id: 'id_opedocId',
			//【可选参数】1:回调，0:不回调，不传默认回调
			callback: '0',
			onClickRow : function(index,row){
				$('#id_opedocName').val(row.employeeName);
			}
		});
		
		
		Comm.form({
			id : 'editFormOperation',
			url : '${webroot}/st005Ssxxb/f_view/operationAdd.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					var parentObject = parent.Comm.getObjectCache();
					parentObject.setNewOprationList(json.data.id,json.data.relid,json.data.operName,json.data.incisionGrade);
					parent.Comm.dialogClose('${param.dialogId}');					
				} else if(json.result === 'error') {
					$.messager.show({ title : '提示', msg : json.msg });
				} else {
					$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	});
	</script>
</body>
</html>