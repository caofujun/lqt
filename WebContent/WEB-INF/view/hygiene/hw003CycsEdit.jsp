<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormhw003Cycs" method="post">
	<input type="hidden" name="action" value="${action}" />
	<input type="hidden" name="flag" value="${hw003Cycs.flag}" />
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 90px;">场所编号：</td>
				<td>
					<input type="text" name="placeId" style="width:150px;" value="${hw003Cycs.placeId}" class="easyui-validatebox text" required="true" validType="number" ${action eq 'edit' ? 'readonly="true"' : '' } />
				</td>
			</tr>
			<tr>
				<td class="t_title" style="width: 90px;">场所名称：</td>
				<td>
					<input type="text" name="placeName" style="width: 150px;" value="<c:out value="${hw003Cycs.placeName}" />" class="easyui-validatebox text" required="true" onblur="$('#id_spCode').val($(this).pinyinFirstLower().toUpperCase());" />
				</td>
			</tr>
			<tr>
				<td class="t_title">首拼码：</td>
				<td>
					<input type="text" id="id_spCode" name="spCode" style="width: 150px;" class="easyui-validatebox text" required="true" value="${hw003Cycs.spCode}" />
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormhw003Cycs').submit()"  class="no_ico"><span>确认</span></a>
			</div>
			<div class="n_btn_grey">
					<a href="javascript:;" onclick="parent.Comm.dialogClose('${param.dialogId}')"  class="no_ico"><span>取消</span></a>
			</div>
		</div>			
	</div>
</form>
<script>
$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'editFormhw003Cycs',
			url : '${webroot}/hw003Cycs/f_json/save.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					//刷新父页面数据
					var parentObject = parent.Comm.getObjectCache();
					if ('edit' == '${action}') {
						parentObject.updateRow('${hw003Cycs.placeId}');
					} else {
						parentObject.query();
					}
					parent.Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	}, 100);
});
</script>