<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormMsgTemplate" method="post">
	<input type="hidden" name="id" value="${msgTemplate.id}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 85px;"><span class="red">*</span>模板标题：</td>
				<td><input type="text"  id="title" name="title" style="width: 250px;"
					value="<c:out value="${msgTemplate.title}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 85px;">模板内容：</td>
				<td class="t_cont" colspan="3"><textarea name="contentStr" id="content"
						style="width: 90%; height: 150px;" class="easyui-validatebox"><c:out value="${msgTemplate.contentStr}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer ">
		<div class="footer_btn">
			<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn"	onclick="$('#editFormMsgTemplate').submit()" class="no_ico"><span>保存</span></a>
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
			Comm.form({
				id : 'editFormMsgTemplate',
				url : '${webroot}/msgTemplate/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({ title : '提示', msg : '操作成功！' });
						var parentObject = parent.Comm.getObjectCache();
						if(typeof(parentObject)!="undefined"){ 
							parentObject.query();
						}
						parent.Comm.dialogClose('${param.dialogId}');
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