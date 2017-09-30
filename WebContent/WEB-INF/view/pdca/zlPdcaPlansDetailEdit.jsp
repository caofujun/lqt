<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<form id="editFormZlPdcaPlansDetail" method="post">
		<input type="hidden" name="puid" value="${zlPdcaPlansDetail.puid}" /> 
		<input type="hidden" name="pdSubid" value="${zlPdcaPlansDetail.pdSubid}" /> 
		<table class="table mb60" id="ZlPdcaPlansDetailDetailList" cellpadding="0" cellspacing="0">
		<tbody>
		<input type="hidden" name="stepName" value="${zlPdcaPlansDetail.stepName}"/>
		<tr>
			<td class="t_title" width="100">${zlPdcaPlansDetail.stepName}：</td>
			<td colspan="3"><nis:select name="status" dictcode="pdca_plans_status" value="${zlPdcaPlansDetail.status}" cssCls="easyui-validatebox" exp="required=\"true\""/></td>
		</tr>
		<tr>
			<td colspan="4"><textarea name="stepContent" id="stepContent"
					style="width: 95%; height: 60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlansDetail.stepContent}" /></textarea>
			</td>
		</tr>
		<script>
		KindEditor.ready(function(K) {
	   	 	window.editor = K.create('#stepContent', {
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
				autoHeightMode : true
			});
		});
		</script>
		</tbody>
	</table>
	<div class="footer">
		<input type="button" class="btn_save" id="changeFormSubmitBtn"
			 value="保存">
	</div>
</form>
<script>
$(document).ready(
	function() {
		 $('.btn_save').click(function(){
         	var stepContent = editor.html();
     		$("#stepContent").val(stepContent);	
         	$('#editFormZlPdcaPlansDetail').form('submit',{
					url : '${webroot}/zlPdcaPlansDetail/f_json/save.shtml',
					success : function(json) {
						json = $.parseJSON(json);
						if (json.result === 'success') {
							parent.$.messager.show({title : '提示',msg : '操作成功！'});
							var parentObject = parent.Comm.getObjectCache();
							parentObject.query();
							parent.Comm.dialogClose('${param.dialogId}');
						} else if (json.result === 'error') {
							$.messager.show({title : '提示',msg : '操作失败！'});
						} else {
							$.messager.show({title : '提示',msg : json.msg});
						}
					}});
			});
	});
</script>