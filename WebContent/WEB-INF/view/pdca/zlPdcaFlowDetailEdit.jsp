<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<form id="editFormZlPdcaFlowDetail" method="post">
	<input type="hidden" name="fuid" value="${zlPdcaFlowDetail.fuid}" /> 
	<input type="hidden" name="fdSubid" value="${zlPdcaFlowDetail.fdSubid}" />
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title"><span class="red">*</span>过程编号：</td>
				<td><input type="text" id="code" style="width:152px;" name="processNo"
					value="<c:out value="${zlPdcaFlowDetail.processNo}" />" class="easyui-validatebox"
					required="true"></td>
				<td class="t_title"><span class="red">*</span>过程名称：</td>
				<td><input type="text" name="processName"
					value="<c:out value="${zlPdcaFlowDetail.processName}" />" class="easyui-validatebox"
					required="true"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>状态：</td>
				<td>
				<nis:select id="flag" name="flag" dictcode="pdca_status" value="${zlPdcaFlowDetail.flag}" cssCls="easyui-validatebox" exp="required=\"true\""/>
				</td>
				<td class="t_title"><span class="red">*</span>排序：</td>
				<td>
				<input type="text" name="xh"
					value="<c:out value="${zlPdcaFlowDetail.xh}" />" class="easyui-validatebox"
					required="true">
				</td>
			</tr>
			<tr>
				<td class="t_title">过程内容：</td>
				<td colspan="3">
				<textarea name="processContent" id="processContent"
				style="width: 95%; height: 60px;" class="easyui-validatebox"><c:out value="${zlPdcaFlowDetail.processContent}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormZlPdcaFlowDetail').submit()" class="no_ico"><span>确&nbsp;认</span></a>
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
				id : 'editFormZlPdcaFlowDetail',
				url : '${webroot}/zlPdcaFlowDetail/f_json/save.shtml',
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