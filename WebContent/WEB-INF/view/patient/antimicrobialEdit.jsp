<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="antimicrobialEdit" method="post">
	<input type="hidden" name="st004Id" value="<c:out value="${st004Yzxxb.id}" />" />
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title" style="width: 150px;"><span class="red">*</span>药物编号：</td>
				<td>
					<input type="text" id="id_orderId" name="drugId" value="<c:out value="${st004Yzxxb.orderId}" />" class="easyui-validatebox" required="true" style="width: 200px;" />
				</td>
			</tr>
			<tr>
				<td class="t_title" ><span class="red">*</span>药物名称：</td>
				<td>
					<input type="text" name="drugName" value="<c:out value="${st004Yzxxb.orderName}" />" class="easyui-validatebox" required="true" style="width: 200px;" />
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>药物分类：</td>
				<td>
					<input id="id_antimicrobial" name="classId" style="width:200px" />
					<input type="hidden" id="id_className" name="className" />
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
					<a href="javascript:;" class="no_ico" id="changeFormSubmitBtn" onclick="$('#antimicrobialEdit').submit();"><span>保存</span></a>
			</div>
			<div class="n_btn_grey">
					<a href="javascript:;" class="no_ico" onclick="parent.Comm.dialogClose('${param.dialogId}')"><span>关闭</span></a>
			</div>
		</div>	
	</div>
</form>
<script>
$(document).ready(function() {
	//药物分类
	$('#id_antimicrobial').combogrid({
        panelWidth:240,
        url: '${webroot}/trocheKind/f_view/query.shtml',
        idField:'thocheKindId',
        textField:'thocheKindName',
        mode:'remote',
        fitColumns:true,
        columns:[[
            {field:'thocheKindId',title:'分类编号',width:60},
            {field:'thocheKindName',title:'分类名称',width:130}
        ]],
        onClickRow : function (index, row){
        	$('#id_className').val(row.thocheKindName);
        }
    });
		
	window.setTimeout(function() {
		Comm.form({
			id : 'antimicrobialEdit',
			url : '${webroot}/st004Yzxxb/f_json/setAntimicrobial.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title: '提示', msg: '操作成功！' });
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