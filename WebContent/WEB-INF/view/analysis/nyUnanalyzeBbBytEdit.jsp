<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormNyUnanalyzeBbByt" method="post">
	<input type="hidden" name="bytid" value="${nyUnanalyzeBbByt.bytid }" />
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">标本：</td>
				<td>
				<div class="select_del">
					<input type="text" id="id_bbDict_edit" name="bbid"/>
					<input type="hidden" id="id_bbmc" name="bbmc" >
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_bbDict').combo('clear');"></a>
				</div>
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>检出菌：</td>
				<td>
					<input type="text" id="bytName" name="bytName" value="${nyUnanalyzeBbByt.bytName }" data-options="required:true" class="easyui-validatebox"/>
				</td>
			</tr>
		</tbody>
	</table>
</form>	
<div class="footer dialog_footer">		
	<div class="footer_btn">
		<div class="n_btn_blue">
			<a href="javascript:;" id="editFormNyUnanalyzeBbByt" onclick="getName();" class="no_ico"><span>确&nbsp;认</span></a>
		</div>			
	</div>	
</div>
<script>
function getName(){
	 var bbmc = $('#id_bbDict_edit').combobox('getText');
	 $("#id_bbmc").val(bbmc);
	 //alert(bbmc);
	 $('#editFormNyUnanalyzeBbByt').submit();
};
$(document).ready(function () {
	window.setTimeout(function(){
		Comm.form({
			id: 'editFormNyUnanalyzeBbByt',
			url: '${webroot}/nyUnanalyzeBbByt/f_json/save.shtml',
			subbtn: 'changeFormNyUnanalyzeBbBytSubmitBtn',
			success : function(json) {
				if(json.result==='success') {
		    		$.messager.show({ title: '提示', msg: '操作成功！' });
		    		nyUnanalyzeBbByt.query();
		    		Comm.dialogClose('${param.dialogId}');
		    	} else if(json.result === 'error') {
					$.messager.show({ title : '提示', msg : json.msg });
				} else {
					$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
		Csm.combogrid.bbDict({
			id: 'id_bbDict_edit',
			value:'${nyUnanalyzeBbByt.bbid}'
		});
	},100);
});
</script>