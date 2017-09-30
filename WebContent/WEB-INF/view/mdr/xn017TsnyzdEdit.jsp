<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormXn017Tsnyzd" method="post">
	<input type="hidden" name="pathogenId2" value="${xn017Tsnyzd.pathogenId }" />
	<input type="hidden" name="pathogenName2" value="${xn017Tsnyzd.pathogenName }" />
	<input type="hidden" name="drugId" value="${xn017Tsnyzd.drugId }" />
	<input type="hidden" name="drugName" value="${xn017Tsnyzd.drugName }" />
	<input type="hidden" name="drugId2" value="${xn017Tsnyzd.drugId }" />
	<input type="hidden" name="drugName2" value="${xn017Tsnyzd.drugName }" />
	<input type="hidden" name="specDescribe" value="${specDescribe }" />
	<input type="hidden" name="flag" value="${xn017Tsnyzd.flag }" />
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title" >重点监测菌描述：</td>
				<td><c:out value="${dictName }"/></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>对应菌名称：</td>
				<td>
				<div class="select_del">
					<input type="text" id="id_pathogenId" name="pathogenId" required="true" />
					<input type="hidden" id="id_pathogenName" name="pathogenName" >
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_pathogenId').combo('clear');"></a>
				</div>
				</td>
			</tr>
		</tbody>
	</table>
</form>	
<div class="footer dialog_footer">		
	<div class="footer_btn">
		<div class="n_btn_blue">
			<a href="javascript:;" id="editFormXn017Tsnyzd" onclick="getName();" class="no_ico"><span>确&nbsp;认</span></a>
		</div>			
	</div>	
</div>
<script>
function getName(){
	 var pName = $('#id_pathogenId').combobox('getText');
	 $("#id_pathogenName").val(pName);
	 $('#editFormXn017Tsnyzd').submit();
};
$(document).ready(function () { 
	window.setTimeout(function(){
		Comm.form({
			id: 'editFormXn017Tsnyzd',
			url: '${webroot}/xn017Tsnyzd/f_json/save.shtml',
			subbtn: 'changeFormXn017TsnyzdSubmitBtn',
			success : function(json) {
				if(json.result==='success') {
		    		$.messager.show({ title: '提示', msg: '操作成功！' });
		    		xn017Tsnyzd.query();
		    		Comm.dialogClose('${param.dialogId}');
		    	} else if(json.result === 'error') {
					$.messager.show({ title : '提示', msg : json.msg });
				} else {
					$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
		Csm.combogrid.byt({
			//【必传】控件名称
			id: 'id_pathogenId',
			value:'${xn017Tsnyzd.pathogenId}',
			isByt : '1'
		});
	},100);
});
</script>