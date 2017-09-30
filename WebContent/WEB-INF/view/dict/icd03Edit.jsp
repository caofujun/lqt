<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormICD0" method="post">
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 140px;"><span class="red">*</span>ICD0编号：</td>
				<td><input type="text"  id="pathologyno" name="pathologyno" style="width: 250px;" 
					value="<c:out value="${ICD0.pathologyno}" />" <c:choose><c:when test="${!empty ICD0}">readonly= "readonly" </c:when><c:otherwise> onblur="isNoExsit(this.value)" </c:otherwise></c:choose> class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>ICD0名称：</td>
				<td><input type="text"  id="pathologyname" name="pathologyname" style="width: 250px;"
					value="<c:out value="${ICD0.pathologyname}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title" style="width: 140px;">拼音码：</td>
				<td>
					<input type="text"  id="spCode" name="pycode" style="width: 250px;" value="<c:out value="${ICD0.pycode}" />" class="easyui-validatebox text" required="true">
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;"  id="changeFormSubmitBtn" onclick="$('#editFormICD0').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>				
	</div>
</form>
<script>
	$(document).ready(function() {
		window.setTimeout(function() {
			$('#pathologyname').live('blur',function() {
				var icdName = $(this).pinyinFirstLower().toUpperCase();
				if(icdName.length > 10){
					icdName = icdName.substring(0,10);
				}
				$("#spCode").val(icdName);
			});
			Comm.form({
				id : 'editFormICD0',
				url : '${webroot}/icd03/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
						menuInfo.clickMenu('ICD-0-3字典','/icd03/f_view/index.shtml',true);
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
	});
	
    function isNoExsit(id){
    	if(id){
	    	$.ajax({
	    		url:"${webroot}/icd03/f_json/isNoExist.shtml",
	    		data: { pathologyno: id },
	    		type:"POST",
	    		dataType: 'json',
	    		success:function(json){
	    			if(json.result == 'error') {
			    		$.messager.show({ title: '提示', msg: json.msg });
			    		$("#changeFormSubmitBtn").parent().hide();
			    	}else{
			    		$("#changeFormSubmitBtn").parent().show();
			    	}
	    		},error:function(){
	    			$.messager.show({ title: '提示', msg: "抱歉，验证编号是否重复失败！" });
	    		}
	    	});
    	}
    }
</script>