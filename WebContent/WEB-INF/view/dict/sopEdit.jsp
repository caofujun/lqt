<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormSop" method="post">
	<input type="hidden" name="id" value="${sop.fileId}"/>
	<input type="hidden" id="pFileName" name="pFileName" value="${sop.pFileName}"/>
	<table class="table" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>SOP编码：</td>
				<td><input type="text"  id="fileId" name="fileId" style="width: 180px;" <c:if test="${sop.fileId!=null and sop.fileId!=''}"> readonly="true"</c:if>
					value="<c:out value="${sop.fileId}" />" class="easyui-validatebox text" required="true"></td>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>SOP名称：</td>
				<td><input type="text"  id="fileTitle" name="fileTitle" style="width: 180px;"
					value="<c:out value="${sop.fileTitle}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 85px;">SOP分类：</td>
				<td>
					<span class="standard_select">
						<span class="select_shelter">
							<nis:select name="pFileId" id="pFileId" value="${sop.pFileId}"  exp="onchange='onchangePFileId(this.value)' style='width: 180px;'" dictcode="sop_type" cssCls="easyui-validatebox"/>				
						</span>
					</span>
				</td>
				<td class="t_title" style="width: 85px;">拼音码：</td>
				<td>
					<input type="text"  id="spCode" name="spCode" style="width: 180px;" value="<c:out value="${sop.spCode}" />" class="easyui-validatebox text" required="true">
				</td>
			</tr>
			<tr>
				<td class="t_title" style="width: 85px;">SOP内容：</td>
				<td class="t_cont" colspan="3"><textarea name="showFileData" id="content"
						style="width: 90%; height: 150px;" class="easyui-validatebox"><c:out value="${sop.showFileData}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer ">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormSop').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
KindEditor.ready(function(K) {
 	window.editor = K.create('#content', {
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
});
});
	$(document).ready(function() {
		window.setTimeout(function() {
			$('#fileTitle').live('blur',function() {
				var spCode = $(this).pinyinFirstLower().toUpperCase();
				if(spCode.length > 10){
					spCode = spCode.substring(0,10);
				}
				$("#spCode").val(spCode);
			});
			$('#pFileName').val($("#pFileId").find("option:selected").text());
			Comm.form({
				id : 'editFormSop',
				url : '${webroot}/sop/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({ title : '提示', msg : '操作成功！' });
						var parentObject = parent.Comm.getObjectCache();
						if(typeof(parentObject)!="undefined"){ 
							parentObject.query();
						}
						parent.Comm.dialogClose('${param.dialogId}');
						menuInfo.clickMenu('SOP管理','/sop/f_view/index.shtml',true,'A0102');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
	});
	
	/*
	 *查询数据
	 */
	function onchangeSoptTypeId(pFileId){
		$('#pFileName').val($("#pFileId").find("option:selected").text());
	}
</script>