<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="handHygieneEdit" method="post">
	<table class="table" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 90px;">填报日期：</td>
				<td>
					<input type="hidden" name="id" value="${sw001Swsyp.id}" />
					<input type="text" name="reportDate" style="width: 120px;" value="<fmt:formatDate value="${sw001Swsyp.reportDate}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox" onclick="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<td class="t_title">填报人：</td>
				<td>
					<input type="hidden" id="id_reportUserName" name="reportUserName" value="${sw001Swsyp.reportUserName}" />
					<div class="select_del"><input type="text" id="id_reportUserId" name="reportUserId" style="width: 120px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_reportUserId').combo('clear');"></a></div>
				</td>
			</tr>
			<tr>
				<td class="t_title">规格：</td>
				<td>
					<input type="text" name="specification" style="width:120px;" value="${sw001Swsyp.specification}" class="easyui-validatebox text" required="true" validType="number" />&nbsp;<c:out value="${sw001Swsyp.specificaUnit}" />
				</td>
			</tr>
			<tr>
				<td class="t_title">上月剩余量：</td>
				<td>
					<input type="text" name="lastMonthRemain" style="width: 120px;" value="${sw001Swsyp.lastMonthRemain}" class="easyui-validatebox text" required="true" validType="number" />&nbsp;<c:out value="${sw001Swsyp.inventoryUnit}" />
				</td>
			</tr>
			<tr>
				<td class="t_title">本月领用：</td>
				<td>
					<input type="text" name="thisMonthGet" style="width: 120px;" value="${sw001Swsyp.thisMonthGet}" class="easyui-validatebox text" required="true" validType="number" />&nbsp;<c:out value="${sw001Swsyp.usedUnit}" />
				</td>
			</tr>
			<tr>
				<td class="t_title">本月库存量：</td>
				<td>
					<input type="text" name="thisMonthInventory" style="width: 120px;" value="${sw001Swsyp.thisMonthInventory}" class="easyui-validatebox text" required="true" validType="number" />&nbsp;<c:out value="${sw001Swsyp.inventoryUnit}" />
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#handHygieneEdit').submit();" class="no_ico"><span>确认</span></a>
			</div>
			<div class="n_btn_grey">
					<a href="javascript:;" onclick="parent.Comm.dialogClose('${param.dialogId}')" class="no_ico"><span>取消</span></a>
			</div>
		</div>		
	</div>
</form>
<script type="text/javascript">
$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'handHygieneEdit',
			url : '${webroot}/sw001Swsyp/f_json/save.shtml',
			subbtn : 'changeFormSubmitBtn',
			onLoading : function () {
				$.messager.progress({
					text : '正在提交中....',
					interval : 200
				});
				$('#changeFormSubmitBtn').hide();
			},
			success : function(json) {
				$.messager.progress('close');
				$('#changeFormSubmitBtn').show();
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
	
	//填报人
	$('#id_reportUserId').combogrid({
		delay: 1000,
	    mode: 'remote',
	    loadMsg : '正在查询中...',
	    value: '${sw001Swsyp.reportUserId}',
	    required:true,
        idField:'employeeId',
        panelWidth: 200,
        panelHeight: 210,
        textField:'employeeName',
		url: '${webroot}/doctor/json/queryToSelect.shtml?page=1&size=200&defValue=${sw001Swsyp.reportUserId}',
        columns:[
        	[
             {field:'employeeId',title:'职工编号',sortable:true,width:100},  
             {field:'employeeName',title:'职工名称',sortable:true,width:80}
            ]
        ],
		onClickRow : function(index,row){
			$('#id_reportUserName').val(row.employeeName);
		},
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_reportUserId');
		}
	});
});
</script>