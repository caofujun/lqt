<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormNyUnanalyzeBbDict" method="post" >
	<input type="hidden" name="flag" value="${flag }"/>
	<input type="hidden" name="noDictName2" value="${nyUnanalyzeBbDict.noDictName }" />
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title"><span class="red">*</span>检出结果：</td>
				<td>
					<input type="text" id="id_noDictName" name="noDictName" value="${nyUnanalyzeBbDict.noDictName }" data-options="required:true" class="easyui-validatebox"/>
				</td>
			</tr>
		</tbody>
	</table>
</form>
<div class="footer dialog_footer">		
	<div class="footer_btn">
		<div class="n_btn_blue">
			<a href="javascript:;" id="editFormNyUnanalyzeBbDict" onclick="submitSave();" class="no_ico"><span>确&nbsp;认</span></a>
		</div>			
	</div>	
</div>
<script>
function submitSave(){
	$('#editFormNyUnanalyzeBbDict').submit();
};
$(document).ready(function () { 
	window.setTimeout(function(){
		Comm.form({
			id: 'editFormNyUnanalyzeBbDict',
			url: '${webroot}/nyUnanalyzeBbDict/f_json/save.shtml',
			subbtn: 'changeFormNyUnanalyzeBbDictSubmitBtn',
			success : function(json) {
				if(json.result==='success') {
		    		$.messager.show({ title: '提示', msg: '操作成功！' });
		    		nyUnanalyzeBbDict.query();
		    		Comm.dialogClose('${param.dialogId}');
		    	} else if(json.result === 'error') {
					$.messager.show({ title : '提示', msg : json.msg });
				} else {
					$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	},100);
});
</script>
