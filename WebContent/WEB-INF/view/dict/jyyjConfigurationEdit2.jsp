<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editJyyj2" method="post">
	<input type="hidden" name="id" value="${zg033Jcxxpp.id }" /> 
	<input type="hidden" name="sjId" value="${sjId }" >
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title"><span class="red">*</span>检出项目匹配字段：</td>
				<td>
					<nis:select id="itemField" name="itemField" value="${zg033Jcxxpp.itemField }" dictcode="check_item_field" cssCls="easyui-combobox" exp="style=\"width: 152px;\" data-options=\"editable:false\""/>
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>匹配符：</td>
				<td>
					<nis:select id="itemMatch" name="itemMatch" value="${zg033Jcxxpp.itemMatch }" dictcode="match_character" cssCls="easyui-combobox" exp="style=\"width: 152px;\" data-options=\"editable:false\""/>
				</td>
			</tr>
			<tr>
				<td class="t_title" style="border-bottom:thin dashed gray;" >
					<span class="red">*</span>匹配值：</td>
				<td style="border-bottom:thin dashed gray;" >
					<input type="text" id="itemMatchValue" name="itemMatchValue" class="easyui-validatebox" required="true" value="${zg033Jcxxpp.itemMatchValue }" style="width:140px;">
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>检出值字段：</td>
				<td>
					<nis:select id="valueField" name="valueField" value="${zg033Jcxxpp.valueField }" dictcode="check_value_field" cssCls="easyui-combobox" exp="style=\"width: 152px;\" data-options=\"editable:false\""/>
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>匹配符：</td>
				<td>
					<nis:select id="valueMatch" name="valueMatch" value="${zg033Jcxxpp.valueMatch }" dictcode="match_character" cssCls="easyui-combobox" exp="style=\"width: 152px;\" data-options=\"editable:false\""/>
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>匹配值：</td>
				<td>
					<input type="text" id="valueMatchValue" name="valueMatchValue" class="easyui-validatebox" required="true" value="${zg033Jcxxpp.valueMatchValue }" style="width:140px;">
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="editJyyjBtn" onclick="$('#editJyyj2').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>		
		</div>	
	</div>
</form>
<script type="text/javascript">
$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'editJyyj2',
			url : '${webroot}/zg033Jcxxpp/f_json/save.shtml',
			subbtn : 'editJyyjBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					jyyj.query();
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