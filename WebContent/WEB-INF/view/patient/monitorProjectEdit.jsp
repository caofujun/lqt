<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="monitorProjectEdit" method="post">
	<input type="hidden" name="st004Id" value="<c:out value="${st004Yzxxb.id}" />" />
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title" style="width: 150px;"><span class="red">*</span>医嘱编号：</td>
				<td>
					<input type="text" id="id_orderId" name="orderId" value="<c:out value="${st004Yzxxb.orderId}" />" class="easyui-validatebox" required="true" style="width: 200px;"/>
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>医嘱名称：</td>
				<td>
					<input type="text" name="orderName" value="<c:out value="${st004Yzxxb.orderName}" />" class="easyui-validatebox " required="true" style="width: 200px;" />
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>监测类别：</td>
				<td>
					<nis:select id="id_monitoType" name="classCode" dictcode="monitor_type" headerKey="" headerValue="--监测类型--"  cssCls=" easyui-combobox"  />
					<input type="hidden" id="id_className" name="className" class="easyui-validatebox " required="true" />
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
					<a href="javascript:;" class="no_ico"  id="changeFormSubmitBtn" onclick="$('#monitorProjectEdit').submit();"><span>保存</span></a>
			</div>
			<div class="n_btn_grey">
					<a href="javascript:;" class="no_ico" onclick="parent.Comm.dialogClose('${param.dialogId}')" ><span>关闭</span></a>
			</div>
		</div>		
	</div>
</form>
<script>
function setClassName() {
	var objText = $('#id_monitoType').combobox("getText");
	if (objText == '--监测类型--') {
		$('#id_className').val('');
	} else {
		$('#id_className').val(objText);
	}
}

$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'monitorProjectEdit',
			url : '${webroot}/st004Yzxxb/f_json/setMonitorProject.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title: '提示', msg: '操作成功！' });
					var parentObject = parent.Comm.getObjectCache();
					parentObject.query();
					parent.Comm.dialogClose('${param.dialogId}');
				} else if (json.result === 'error') {
					parent.$.messager.show({title : '提示',msg : '操作失败！'});
				} else {
					parent.$.messager.show({title : '提示',msg : json.msg});
				}
			}});
	}, 100);
	
	$('#id_monitoType').combobox({
		onChange: function (n,o) {
			setClassName();
		}
	});
});

</script>