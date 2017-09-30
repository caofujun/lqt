<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormhw004Cybb" method="post">
	<input type="hidden" name="action" value="${action}" />
	<input type="hidden" name="flag" value="${hw004Cybb.flag}" />
	<table class="table" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 90px;">所属项目：</td>
				<td>
					<div class="select_del"><input id="id_classId" name="classId" style="width:150px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_classId').combo('clear');"></a></div>
				</td>
				<td class="t_title" style="width: 90px;">标本ID：</td>
				<td>
					<input type="text" name="sampleId" style="width: 150px;" readonly="readonly" value="<c:out value="${hw004Cybb.sampleId}" />" class="easyui-validatebox text" required="true" validType="number" ${action eq 'edit' ? 'readonly="true"' : '' } />
				</td>
			</tr>
			<tr>
				<td class="t_title">标本名称：</td>
				<td>
					<input type="text" name="sampleName" style="width: 150px;" value="<c:out value="${hw004Cybb.sampleName}" />" class="easyui-validatebox text" required="true" onblur="$('#id_spCode').val($(this).pinyinFirstLower().toUpperCase());" />
				</td>
				<td class="t_title">首拼码：</td>
				<td>
					<input type="text" id="id_spCode" name="spCode" style="width: 150px;" class="easyui-validatebox text" required="true" value="${hw004Cybb.spCode}" />
				</td>
			</tr>
			<tr>
				<td class="t_title">备注：</td>
				<td  colspan="3">
					<textarea name="memo" style="width: 95%; height: 150px;" class="easyui-validatebox"><c:out value="${hw004Cybb.memo}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormhw004Cybb').submit()"  class="no_ico"><span>确认</span></a>
			</div>
			<div class="n_btn_grey">
					<a href="javascript:;"  onclick="parent.Comm.dialogClose('${param.dialogId}')"  class="no_ico"><span>取消</span></a>
			</div>
		</div>	
	</div>
</form>
<script>
$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'editFormhw004Cybb',
			url : '${webroot}/hw004Cybb/f_json/save.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					//刷新父页面数据
					var parentObject = parent.Comm.getObjectCache();
					parentObject.query();
					parent.Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	}, 100);
	
	//所属项目
	$('#id_classId').combogrid({
		url:'${webroot}/hw001Jcxm/f_json/queryList.shtml?pclassId=0',
		method:'get',
		required: true,
		value: '${hw004Cybb.classId}',
		panelHeight:260,
		panelWidth:320,
		idField:'classId',
        textField:'className',
        columns:[
        	[
	            {field:'classId',title:'场所编号',sortable:true,width:70},  
	            {field:'className',title:'场所名称',sortable:true,width:160},
	            {field:'spCode',title:'首拼码',sortable:true,width:60}
            ]
        ],
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_classId');
		}
	});
});
</script>