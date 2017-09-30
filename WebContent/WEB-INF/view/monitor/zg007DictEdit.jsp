<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormZg007Dict" method="post">
	<input type="hidden" name="elementId" value="${zg007Dict.elementId }" >
	<input type="hidden" name="keyid" value="${zg007Dict.keyid }" />
	<input type="hidden" name="itemClass" value="${zg007Dict.itemClass }" >
	<input type="hidden" name="elementType" value="${zg007Dict.elementType }" >
	<input type="hidden" name="qyElement" value="${zg007Dict.qyElement }" >
	<input type="hidden" name="appElement" value="${zg007Dict.appElement }" >
	<input type="hidden" name="bhElement" value="${zg007Dict.bhElement }" >
	<input type="hidden" name="dictType" value="${zg007Dict.dictType }" >
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title" >词名称：</td>
				<td><input type="text" name="itemName" value="${zg007Dict.itemName }" ></td>
			</tr>
		</tbody>
	</table>
<form>
<div class="footer dialog_footer">		
	<div class="footer_btn">
		<div class="n_btn_blue">
			<a href="javascript:;" id="editZg007DictBtn" onclick="$('#editFormZg007Dict').submit()" class="no_ico"><span>确&nbsp;认</span></a>
		</div>			
	</div>	
</div>
<script>
$(document).ready(function () { 
	window.setTimeout(function(){
		Comm.form({
			id: 'editFormZg007Dict',
			url: '${webroot}/zg007Dict/f_json/save.shtml',
			subbtn: 'editZg007DictBtn',
			success : function(json) {
				if(json.result === 'success') {
		    		$.messager.show({ title: '提示', msg: '操作成功！' });
		    		zg007Dict.query2('${zg007Dict.elementId }','${zg007Dict.itemClass }');
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