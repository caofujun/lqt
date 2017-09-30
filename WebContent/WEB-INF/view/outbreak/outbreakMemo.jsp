<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="editFormBy007Bfjl" method="post">
	<input type="hidden" name="id" value="${by007Bfjl.id}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0"  >
		<tbody>
			<tr>
				<td class="t_cont"><textarea name="memo"
						style="width:320px; height: 100px;" class="easyui-validatebox"><c:out value="${by007Bfjl.memo}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormBy007Bfjl').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormBy007Bfjl',
				url : '${webroot}/by007Bfjl/f_json/memo/set.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						var parentObject = parent.Comm.getObjectCache();
						parentObject.queryDetail();
						parent.Comm.dialogClose('${param.dialogId}');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : '操作失败！' });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
	});
</script>