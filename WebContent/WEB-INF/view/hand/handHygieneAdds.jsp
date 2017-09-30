<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="handHygieneEdit" method="post">

	<table class="table" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 90px;">用品类型：</td>
				<td>
					<ul id="generic" class="tab_new">
					<c:forEach items="${swsTypeList}" var="swsType" varStatus="st">
					<c:if test="${st.index eq 0}"><input type="hidden" id="type" name="type" value="${swsType.dictCode}"></c:if>
					<li <c:if test="${st.index eq 0}">class="cur"</c:if>><a href="javascript:void(0)" onclick="chooseHandUseType(this,'${swsType.dictCode}')">${swsType.dictName}</a></li>
					</c:forEach>
					</ul>						
				</td>
			</tr>		
			<tr>
				<td class="t_title">填报日期：</td>
				<td>
					<input type="hidden" name="id" value="${sw001Swsyp.id}" />
					<input type="text" name="reportDate" style="width: 220px;" value="<fmt:formatDate value="${sw001Swsyp.reportDate}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox" onclick="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<td class="t_title">填报科室：</td>
				<td>
				<input type="hidden" id="id_reportDeptName" name="reportDeptName" value="${sw001Swsyp.reportDeptName}"/>
					<div class="select_del"><input type="text" id="id_reportDeptId" name="reportDeptId"  style="width:230px;"/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_reportDeptId').combo('clear');"></a></div>
				</td>
			</tr>
			<tr>
				<td class="t_title">填报人：</td>
				<td>
					<input type="hidden" id="id_reportUserName" name="reportUserName" />
					<div class="select_del"><input type="text" id="id_reportUserId" name="reportUserId" style="width: 230px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_reportUserId').combo('clear');"></a></div>
				</td>
			</tr>
			<tr>
			<td colspan="2"><div style="border-bottom:1px dotted #ccc; margin:10px 0px;"></div></td>
			</tr>
			<tr>
				<td class="t_title">规格：</td>
				<td>
					<input type="text" name="specification" id="specification" style="width:220px;" value="${sw001Swsyp.specification}" class="easyui-validatebox text" required="true" validType="number" />&nbsp;<span id="specificaUnit"><c:out value="${sw001Swsyp.specificaUnit}" /></span>
				</td>
			</tr>
			<tr>
				<td class="t_title">上月剩余量：</td>
				<td>
					<input type="text" name="lastMonthRemain" id="lastMonthRemain" style="width: 220px;" value="0" class="easyui-validatebox text" required="true" validType="number" />&nbsp;<span id="inventoryUnit"><c:out value="${sw001Swsyp.inventoryUnit}" /></span>
				</td>
			</tr>
			<tr>
				<td class="t_title">本月领用：</td>
				<td>
					<input type="text" name="thisMonthGet" id="thisMonthGet" style="width: 220px;" value="${sw001Swsyp.thisMonthGet}" class="easyui-validatebox text" required="true" validType="number" />&nbsp;<span id="usedUnit"><c:out value="${sw001Swsyp.usedUnit}" /></span>
				</td>
			</tr>
			<tr>
				<td class="t_title">本月库存量：</td>
				<td>
					<input type="text" name="thisMonthInventory" id="thisMonthInventory" style="width: 220px;" value="0" class="easyui-validatebox text" required="true" validType="number" />&nbsp;<span id="inventoryUnitCur"><c:out value="${sw001Swsyp.inventoryUnit}" /></span>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer">
		<div class="footer_btn">
			<div style="display:inline-block; margin-right:10px;"><label><input type="checkbox" id="id_add_again" />&nbsp;继续新增</label></div>
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
function chooseHandUseType(value,id){
	$.ajax({
    	url: '${webroot}/sw001Swsyp/f_json/chooseHandUseType.shtml',
        type: 'POST',
        data: {     	
        	id:id
		},
        dataType: 'json',
        success : function(json) {
			if(json.result=='success'){
				$('#type').val(id);
				$('#generic').find('li').attr('class','');
				$(value).parent('li').attr('class','cur');
				$('#specificaUnit').html(json.data.specificaUnit);
				$('#inventoryUnit').html(json.data.inventoryUnit);
				$('#usedUnit').html(json.data.usedUnit);
				$('#inventoryUnitCur').html(json.data.inventoryUnit);
			}
		}
    });
}
$(document).ready(function() {
	window.setTimeout(function() {
		//填报科室
		Csm.combogrid.dep({
			id: 'id_reportDeptId',
			flag: '1',
			//【可选参数】下拉列表的默认value，不传则没有默认值；
			value: '${sw001Swsyp.reportDeptId}',
			required:true,
			onSelect : function(index,row) {
				$('#id_reportDeptName').val(row.deptName);
			},
	        onHidePanel : function() {
	        	Csm.valueValite.combogrid('id_reportDeptId');
			} , 
			onLoadSuccess:function(data){
				$('#id_reportDeptName').val($('#id_reportDeptId').combobox('getText'));
			} 
		});
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
			},
			onLoadSuccess:function(data){
				$('#id_reportUserName').val($('#id_reportUserId').combobox('getText'));
			}
		});
		Comm.form({
			id : 'handHygieneEdit',
			url : '${webroot}/sw001Swsyp/f_json/saves.shtml',
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
					//选中
					if ($('#id_add_again').is(':checked')) {
						$('#specification').val('');
						$('#lastMonthRemain').val('0');
						$('#thisMonthGet').val('');
						$('#thisMonthInventory').val('0');
					}else{
						parent.Comm.dialogClose('${param.dialogId}');
					}
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	}, 100);
	
	
});
</script>