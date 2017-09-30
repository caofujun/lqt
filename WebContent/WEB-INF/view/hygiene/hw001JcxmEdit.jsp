<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormhw001Jcxm" method="post">
	<input type="hidden" name="action" value="${action}" />
	<table class="table" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 90px;">所属项目：</td>
				<td>
					<input id="id_pclassId" name="pclassId" style="width:150px;" />
				</td>
				<td class="t_title" style="width: 90px;">项目编码：</td>
				<td>
					<input type="text" id="id_classId" name="classId" style="width: 150px;" value="<c:out value="${hw001Jcxm.classId}" />" class="easyui-validatebox text" required="true" validType="number" ${action eq 'edit' ? 'readonly="true"' : '' } />
				</td>
			</tr>
			<tr>
				<td class="t_title">项目名称：</td>
				<td>
					<input type="text" id="className" name="className" style="width: 150px;" value="<c:out value="${hw001Jcxm.className}" />" class="easyui-validatebox text" required="true" onblur="$('#id_spCode').val($(this).pinyinFirstLower().toUpperCase());" />
				</td>
				<td class="t_title">类型：</td>
				<td>
					<select name="type" style="width: 150px;" required="true" data-options="editable:false,required:true"  class="easyui-combobox">
						<option value="">-请选择-</option>
						<option value="1" ${hw001Jcxm.type eq '1' ? 'selected="selected"' : ''} >环境卫生学监测</option>
						<option value="2" ${hw001Jcxm.type eq '2' ? 'selected="selected"' : ''} >一次性物品监测</option>
						<option value="3" ${hw001Jcxm.type eq '3' ? 'selected="selected"' : ''} >污水监测</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_title">首拼码：</td>
				<td colspan="3">
					<input type="text" name="spCode" id="id_spCode" style="width: 150px;" class="easyui-validatebox text" value="${hw001Jcxm.spCode}" />
				</td>
			</tr>
			<tr>
				<td class="t_title">检测材料：</td>
				<td colspan="3">
					<textarea name="material" style="width: 95%; height: 50px;" class="easyui-validatebox"><c:out value="${hw001Jcxm.material}" /></textarea>
				</td>
			</tr>
			<tr>
				<td class="t_title">采样面积：</td>
				<td  colspan="3">
					<textarea name="takeArea" style="width: 95%; height: 50px;" class="easyui-validatebox"><c:out value="${hw001Jcxm.takeArea}" /></textarea>
				</td>
			</tr>
			<tr>
				<td class="t_title">采样方法：</td>
				<td  colspan="3">
					<textarea name="takeMode" style="width: 95%; height: 50px;" class="easyui-validatebox"><c:out value="${hw001Jcxm.takeMode}" /></textarea>
				</td>
			</tr>
			<tr>
				<td class="t_title">检测方法：</td>
				<td  colspan="3">
					<textarea name="testMode" style="width: 95%; height: 50px;" class="easyui-validatebox"><c:out value="${hw001Jcxm.testMode}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormhw001Jcxm').submit()" class="no_ico"><span>确认</span></a>
			</div>
			<div class="n_btn_grey">
					<a href="javascript:;" onclick="parent.Comm.dialogClose('${param.dialogId}')"  class="no_ico"><span>取消</span></a>
			</div>
		</div>	
	</div>
</form>
<script>
$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'editFormhw001Jcxm',
			url : '${webroot}/hw001Jcxm/f_json/save.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					//刷新父页面数据
					var parentObject = parent.Comm.getObjectCache();
					if ('edit' == '${action}') {
						parentObject.updateRow('${hw001Jcxm.classId}');
					} else {
						parentObject.query();
					}
					parent.Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	}, 100);
	
	//所属项目
	$('#id_pclassId').combogrid({
		url:'${webroot}/hw001Jcxm/f_json/queryList.shtml',
		method:'get',
		value: '${hw001Jcxm.pclassId}',
		panelHeight:300,
		panelWidth:320,
		idField:'classId',
        textField:'className',
        readonly:('edit' == '${action}' ? true : false),
        columns:[
        	[
	            {field:'classId',title:'场所编号',sortable:true,width:70},  
	            {field:'className',title:'场所名称',sortable:true,width:160},
	            {field:'spCode',title:'首拼码',sortable:true,width:60}
            ]
        ],
        onClickRow : function(index,row){
           	$('#id_classId').val(row.classId + '01');
        },
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_pclassId');
		}
	});
});

</script>