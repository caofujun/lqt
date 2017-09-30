<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<div class="easyui-panel" data-options="border:false,footer:'#button_div'" style="padding-right: 5px; overflow: hidden;">
<form id="editFormhw016Role" method="post">
	<input type="hidden" name="action" value="${action}" />
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 120px;">角色编号：</td>
				<td>
					<input type="text" name="roleId" style="width:150px;" value="${hw016Role.roleId}" class="easyui-validatebox text" required="true" validType="number" ${action eq 'edit' ? 'readonly="true"' : '' } />
				</td>
			</tr>
			<tr>
				<td class="t_title">角色名称：</td>
				<td>
					<input type="text" name="roleName" style="width: 150px;" value="${hw016Role.roleName}" class="easyui-validatebox text" required="true" />
				</td>
			</tr>
		</tbody>
	</table>
</form>
</div>
<div id="button_div" class="footer dialog_footer">	
	<div class="footer_btn">
		<div class="n_btn_blue">
			<a href="javascript:;"  id="changeFormSubmitBtn" onclick="$('#editFormhw016Role').submit();" class="no_ico"><span>确认</span></a>
		</div>
		<div class="n_btn_grey">
			<a href="javascript:;" onclick="parent.Comm.dialogClose('${param.dialogId}')" class="no_ico"><span>取消</span></a>
		</div>
	</div>	
</div>
<script type="text/javascript">
$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'editFormhw016Role',
			url : '${webroot}/hw016Role/f_json/save.shtml',
			subbtn : 'changeFormSubmitBtn',
			onLoading : function () {
				$.messager.progress({
					text : '正在提交中....',
					interval : 200
				});
				$('#changeFormSubmitBtn').hide();
			},
			success : function(json) {
				$.messager.progress('close');
				$('#changeFormSubmitBtn').show();
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					//刷新父页面数据
					var parentObject = parent.Comm.getObjectCache();
					parentObject.roleQuery();
					parent.Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	}, 100);
});
</script>