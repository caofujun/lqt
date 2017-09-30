<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormYygrzd" method="post">
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>感染诊断编码：</td>
				<td><input type="text"  id="infectCode" name="infectCode" style="width: 250px;"
					value="<c:out value="${yygrzd.infectCode}" />" class="easyui-validatebox text" required="true" <c:if test="${yygrzd.infectCode!=null and yygrzd.infectCode!=''}"> readonly="true"</c:if>></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>感染诊断名称：</td>
				<td><input type="text"  id="infectName" name="infectName" style="width: 250px;"
					value="<c:out value="${yygrzd.infectName}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 140px;"><span class="red">*</span>上级编号：</td>
				<td><input type="text"  id="pInfectCode" name="pInfectCode" style="width: 250px;"
					value="<c:out value="${yygrzd.pInfectCode}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>排序号：</td>
				<td><input type="text"  id="orderIndex" name="orderIndex" style="width: 250px;"
					value="<c:out value="${yygrzd.orderIndex}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;"  id="changeFormSubmitBtn" onclick="$('#editFormYygrzd').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormYygrzd',
				url : '${webroot}/yygrzd/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
						menuInfo.clickMenu('医院感染诊断管理','/yygrzd/f_view/index.shtml',true,'A0304');
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