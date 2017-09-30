<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormXn004Trny" method="post">
	<input type="hidden" name="pathogenId2" value="${xn004Trny.pathogenId }" />
	<input type="hidden" name="drugId2" value="${xn004Trny.drugId }" />
	<input type="hidden" name="isAdd" value="${isAdd }" />
		<table class="table" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td class="t_title"><span class="red">*</span>标准病原体：</td>
					<td>
					<div class="select_del">
						<input type="text" id="id_pathogenId" name="pathogenId" required="true" />
						<input type="hidden" id="id_pathogenName" name="pathogenName" >
						<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_pathogenId').combo('clear');"></a>
					</div>
					</td>
				</tr>
				<tr>
					<td class="t_title"><span class="red">*</span>标准抗菌药物：</td>
					<td>
						<div class="select_del">
						<input type="text" id="id_drugId" name="drugId" required="true" />
						<input type="hidden" id="id_drugName" name="drugName" >
						<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_drugId').combo('clear');"></a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
</form>
<div class="footer dialog_footer">		
	<div class="footer_btn">
		<div class="n_btn_blue">
			<a href="javascript:;" id="editFormXn004Trny" onclick="getName();" class="no_ico"><span>确&nbsp;认</span></a>
		</div>			
	</div>	
</div>
<script>
function getName(){
	 var pName = $('#id_pathogenId').combobox('getText');
	 $("#id_pathogenName").val(pName);
	 var dName = $('#id_drugId').combobox('getText');
	 $("#id_drugName").val(dName);
	 //alert(pName + dName);
	 $('#editFormXn004Trny').submit();
};
$(document).ready(function () {
	window.setTimeout(function(){
		Comm.form({
			id: 'editFormXn004Trny',
			url: '${webroot}/xn004Trny/f_json/save.shtml',
			subbtn: 'changeFormXn004TrnySubmitBtn',
			success : function(json) {
				if(json.result==='success') {
		    		$.messager.show({ title: '提示', msg: '操作成功！' });
		    		xn004Trny.query();
		    		Comm.dialogClose('${param.dialogId}');
		    	} else if(json.result === 'error') {
					$.messager.show({ title : '提示', msg : json.msg });
				} else {
					$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
		Csm.combogrid.byt({
			id: 'id_pathogenId',
			value:'${xn004Trny.pathogenId}'
		});
		Csm.combogrid.kjyw({
			id: 'id_drugId',
			value:'${xn004Trny.drugId}'
		});
	},100);
});
</script>