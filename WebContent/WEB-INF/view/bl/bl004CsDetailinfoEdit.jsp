<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormBl004CsDetailinfo" method="post">
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 140px;"><span class="red">*</span>项目编号：</td>
				<td><input type="text"  id="csdId" name="csdId" style="width: 138px;" <c:if test="${bl004CsDetailinfo.csdId!=null and bl004CsDetailinfo.csdId!=''}"> readonly="true"</c:if>
					value="<c:out value="${bl004CsDetailinfo.csdId}" />" class="easyui-validatebox text" required="true"></td>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>项目名称：</td>
				<td><input type="text"  id="csdName" name="csdName" style="width: 138px;"
					value="<c:out value="${bl004CsDetailinfo.csdName}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;">抗生素分类：</td>
				<td>
					<nis:select name="csmId" id="csmId" value="${bl004CsDetailinfo.csmId}"  exp="style='width: 150px;'" dictcode="bl_item_type" cssCls="easyui-combobox"/>				
				</td>
				<td class="t_title" style="width: 140px;">拼音码：</td>
				<td>
					<input type="text"  id="spCode" name="spCode" style="width: 138px;" value="<c:out value="${bl004CsDetailinfo.spCode}" />" class="easyui-validatebox text" required="true">
				</td>
			</tr>
			<tr>
				<td class="t_title">属性设置：</td>
				<td colspan="3">
				    <label><input type="checkbox" <c:if test="${bl004CsDetailinfo.flag=='1' || empty bl004CsDetailinfo.flag }">checked</c:if> id="flag" name="flag" value="1"/>是否启用</label>
				</td>
			</tr>
			<tr>
				<td class="t_title">备注：</td>
				<td class="t_cont" colspan="3"><textarea id="bz" name="bz"
						style="width: 90%; height: 60px;" class="easyui-validatebox"><c:out value="${bl004CsDetailinfo.bz}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormBl004CsDetailinfo').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			$('#csdName').live('blur',function() {
				var csdName = $(this).pinyinFirstLower().toUpperCase();
				if(csdName.length > 10){
					csdName = csdName.substring(0,10);
				}
				$("#spCode").val(csdName);
			});
			Comm.form({
				id : 'editFormBl004CsDetailinfo',
				url : '${webroot}/bl004CsDetailinfo/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						var parentObject = Comm.getObjectCache();
						parentObject.query();
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
						//menuInfo.clickMenu('职业暴露项目维护','/bl004CsDetailinfo/f_view/index.shtml',true,'A0307');
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