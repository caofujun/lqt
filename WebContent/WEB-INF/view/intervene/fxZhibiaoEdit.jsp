<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<form id="editFormfxZhibiao" method="post">
	<input type="hidden" name="zbId" value="${fxZhibiao.zbId}" /> 
	<table class="table" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
		<tbody>
			<tr>
				<td class="t_title" width="90"><span class="red">*</span>指标名：</td>
				<td><input type="text" id="code" name="zbName"
					value="<c:out value="${fxZhibiao.zbName}" />" class="easyui-validatebox"
					required="true"></td>
				<td class="t_title" width="110"><span class="red">*</span>指标来源：</td>
				<td>
				<nis:select id="zbType" name="zbType" dictcode="zb_source" value="${fxZhibiao.zbType}" cssCls="easyui-combobox" exp="required=\"true\""/>
				</td>			
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>指标得分：</td>
				<td>
				<input type="text" name="zbScore"
					value="<c:out value="${fxZhibiao.zbScore}" />" class="easyui-validatebox"
					required="true">
				</td>
				<td class="t_title"><span class="red">*</span>是否可干预：</td>
				<td>
				<nis:select id="isGy" name="isGy" dictcode="boolean" value="${fxZhibiao.isGy}" cssCls="easyui-combobox" exp="required=\"true\""/>
				</td>
			</tr>
			<tr>
				<td class="t_title">指标值：</td>
				<td colspan="3"><textarea name="zbValue" style="width:470px; height:85px;"><c:out value="${fxZhibiao.zbValue}" /></textarea></td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn"	onclick="$('#editFormfxZhibiao').submit()" class="no_ico"><span>保存</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
$(document).ready(
	function() {
		/* window.editor = KindEditor.create('#processContent', {
			uploadJson   : '${webroot}/nyMessageTheme/f_json/upload.shtml',
			allowFileManager : false,
	   	    allowImageManager : true,
	        allowImageUpload : true, 
			items:[
	       	'preview','justifyleft','justifycenter', 'justifyright',
	      		'justifyfull','clearhtml', 'quickformat', 'selectall', '|', 'fullscreen',
	      		'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'image',
	     			'italic', 'underline', 'strikethrough'
			],
			autoHeightMode : true,
			afterBlur: function(){this.sync();}
		}); */
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormfxZhibiao',
				url : '${webroot}/fxZhibiao/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '操作成功！'});
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
	});
</script>