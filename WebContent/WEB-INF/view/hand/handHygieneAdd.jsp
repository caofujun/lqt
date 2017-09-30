<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="handHygieneAdd" method="post">
<div class="easyui-layout" data-options="fit:true" style="width: 100%;height: 100%">
	<div data-options="region:'north',border:false" style="overflow: hidden;">
		<div class="m_search h_set">
			<div class="div_row" style="border-bottom: 1px solid #ddd;">
				<span>选择科室:</span>
				<input type="hidden" id="id_reportDeptName" name="reportDeptName" />
				<div class="select_del"><input type="text" id="id_reportDeptId" name="reportDeptId" style="width:100px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_reportDeptId').combo('clear');"></a></div>
				<span class="ml5">填报日期:</span>
				<input type="text" name="reportDate" value="<fmt:formatDate value="${sw001Swsyp.reportDate}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox" onclick="WdatePicker()"/>
				<span class="ml5">填报人:</span>
				<input type="hidden" id="id_reportUserName" name="reportUserName" value="${sw001Swsyp.reportUserName}" />
				<div class="select_del"><input type="text" id="id_reportUserId" name="reportUserId" style="width:100px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_reportUserId').combo('clear');"></a></div>
			</div>
		</div>
	</div>
	<div data-options="region:'center'" style="border-width: 0px;">
		<div>
			<table style="width: 100%;font-size: 12px;">
				<c:set var="index" value="0"></c:set>
				<c:if test="${fn:length(disinfeList) > 0}">
				<tr>
					<th width="80">手消毒液</th>
					<td>
						<div class="patient_infor" style="margin:5px;">
							<table class="info_table">
								<tbody>
									<c:forEach items="${disinfeList}" var="sw001Swsyp" varStatus="status"> 
								        <tr>
								            <td class="t_title_r">规格：</td>
								            <td>
								            	<input type="hidden" name="sw001List[${index}].type" value="${sw001Swsyp.type}" />
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].specification" validType="number" required="true" class="easyui-validatebox" value="${sw001Swsyp.specification}" />&nbsp;<c:out value="${sw001Swsyp.specificaUnit}" />
								            </td>
								            <td class="t_title_r">上月剩余量：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].lastMonthRemain" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.inventoryUnit}" />
								            </td>
								            <td class="t_title_r">本月领用：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].thisMonthGet" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.usedUnit}" />
								            </td>
								            <td class="t_title_r">本月库存量：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].thisMonthInventory" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.inventoryUnit}" />
								            </td>
								            <td>
								            <c:choose>
								            	<c:when test="${status.index == 0}">
								            		<a href="javascript:void(0)" style="margin: 0px;" class="ico_add" title="新增" onclick="handAdd.add(this, ${sw001Swsyp.type});" ></a>
								            	</c:when>
								            	<c:otherwise>
								            		<a href="javascript:void(0)" style="margin: 0px;" class="ico_del" title="删除" onclick="handAdd.del(this);" ></a>
								            	</c:otherwise>
								            </c:choose>
								            </td>
								        </tr>
								        <c:set var="index" value="${index+1}"></c:set>
							        </c:forEach>
							    </tbody>
							</table>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${fn:length(ordinaryList) > 0}">
				<tr>
					<th width="80">普通洗手液</th>
					<td>
						<div class="patient_infor" style="margin:5px;">
							<table class="info_table">
								<tbody>
									<c:forEach items="${ordinaryList}" var="sw001Swsyp" varStatus="status"> 
							        	<tr>
								            <td class="t_title_r">规格：</td>
								            <td>
								            	<input type="hidden" name="sw001List[${index}].type" value="${sw001Swsyp.type}" />
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].specification" validType="number" required="true" class="easyui-validatebox" value="${sw001Swsyp.specification}" />&nbsp;<c:out value="${sw001Swsyp.specificaUnit}" />
								            </td>
								            <td class="t_title_r">上月剩余量：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].lastMonthRemain" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.inventoryUnit}" />
								            </td>
								            <td class="t_title_r">本月领用：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].thisMonthGet" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.usedUnit}" />
								            </td>
								            <td class="t_title_r">本月库存量：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].thisMonthInventory" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.inventoryUnit}" />
								            </td>
								            <td>
								            <c:choose>
								            	<c:when test="${status.index == 0}">
								            		<a href="javascript:void(0)" style="margin: 0px;" class="ico_add" title="新增" onclick="handAdd.add(this, ${sw001Swsyp.type});" ></a>
								            	</c:when>
								            	<c:otherwise>
								            		<a href="javascript:void(0)" style="margin: 0px;" class="ico_del" title="删除" onclick="handAdd.del(this);" ></a>
								            	</c:otherwise>
								            </c:choose>
								            </td>
								        </tr>
								        <c:set var="index" value="${index+1}"></c:set>
							        </c:forEach>
							    </tbody>
							</table>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${fn:length(antibacteList) > 0}">
				<tr>
					<th width="80">抗菌洗手液</th>
					<td>
						<div class="patient_infor" style="margin:5px;">
							<table class="info_table">
								<tbody>
									<c:forEach items="${antibacteList}" var="sw001Swsyp" varStatus="status"> 
							        	<tr>
								            <td class="t_title_r">规格：</td>
								            <td>
								            	<input type="hidden" name="sw001List[${index}].type" value="${sw001Swsyp.type}" />
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].specification" validType="number" required="true" class="easyui-validatebox" value="${sw001Swsyp.specification}" />&nbsp;<c:out value="${sw001Swsyp.specificaUnit}" />
								            </td>
								            <td class="t_title_r">上月剩余量：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].lastMonthRemain" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.inventoryUnit}" />
								            </td>
								            <td class="t_title_r">本月领用：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].thisMonthGet" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.usedUnit}" />
								            </td>
								            <td class="t_title_r">本月库存量：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].thisMonthInventory" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.inventoryUnit}" />
								            </td>
								            <td>
								            <c:choose>
								            	<c:when test="${status.index == 0}">
								            		<a href="javascript:void(0)" style="margin: 0px;" class="ico_add" title="新增" onclick="handAdd.add(this, ${sw001Swsyp.type});" ></a>
								            	</c:when>
								            	<c:otherwise>
								            		<a href="javascript:void(0)" style="margin: 0px;" class="ico_del" title="删除" onclick="handAdd.del(this);" ></a>
								            	</c:otherwise>
								            </c:choose>
								            </td>
								        </tr>
								        <c:set var="index" value="${index+1}"></c:set>
							        </c:forEach>
							    </tbody>
							</table>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${fn:length(dryPaperList) > 0}">
				<tr>
					<th width="80">干手纸</th>
					<td>
						<div class="patient_infor" style="margin:5px;">
							<table class="info_table">
								<tbody>
									<c:forEach items="${dryPaperList}" var="sw001Swsyp" varStatus="status"> 
							        	<tr>
								            <td class="t_title_r">规格：</td>
								            <td>
								            	<input type="hidden" name="sw001List[${index}].type" value="${sw001Swsyp.type}" />
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].specification" validType="number" required="true" class="easyui-validatebox" value="${sw001Swsyp.specification}" />&nbsp;<c:out value="${sw001Swsyp.specificaUnit}" />
								            </td>
								            <td class="t_title_r">上月剩余量：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].lastMonthRemain" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.inventoryUnit}" />
								            </td>
								            <td class="t_title_r">本月领用：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].thisMonthGet" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.usedUnit}" />
								            </td>
								            <td class="t_title_r">本月库存量：</td>
								            <td>
								            	<input type="text" style="width: 60px;" name="sw001List[${index}].thisMonthInventory" validType="number" required="true" class="easyui-validatebox" />&nbsp;<c:out value="${sw001Swsyp.inventoryUnit}" />
								            </td>
								            <td>
								            <c:choose>
								            	<c:when test="${status.index == 0}">
								            		<a href="javascript:void(0)" style="margin: 0px;" class="ico_add" title="新增" onclick="handAdd.add(this, ${sw001Swsyp.type});" ></a>
								            	</c:when>
								            	<c:otherwise>
								            		<a href="javascript:void(0)" style="margin: 0px;" class="ico_del" title="删除" onclick="handAdd.del(this);" ></a>
								            	</c:otherwise>
								            </c:choose>
								            </td>
								        </tr>
								        <c:set var="index" value="${index+1}"></c:set>
							        </c:forEach>
							    </tbody>
							</table>
						</div>
					</td>
				</tr>
				</c:if>
			</table>
		</div>
	</div>
	<div data-options="region:'south',border:false" style="height:48px;">
		<div class="footer">			
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#handHygieneAdd').submit();" class="no_ico"><span>确认</span></a>
				</div>
				<div class="n_btn_grey">
					<a href="javascript:;"  onclick="parent.Comm.dialogClose('${param.dialogId}')" class="no_ico"><span>取消</span></a>
				</div>
			</div>	
		</div>
	</div>
</div>
</form>

<script type="text/javascript">
var handAdd = {
	//下标
	index : 0,
	//类型
	typeMap : {},
	//必填验证
	validateRequired : function(id) {
		$('#' + id).validatebox({
		    required: true,
		    validType : 'number'
		});
	},
	//添加
	add : function(obj, type) {
		var object = $(obj).parent().parent().parent();
		var htmlStr = '<tr><td class="t_title_r">规格：</td><td><input type="hidden" name="sw001List[' + handAdd.index + '].type" value="' + type + '" />' + 
				'<input type="text" style="width: 60px;" id="id_specification_' + type +  '_' + handAdd.index + '" name="sw001List[' + handAdd.index + '].specification" />&nbsp;' + handAdd.typeMap[type].specificaUnit + '</td>' + 
				'<td class="t_title_r">上月剩余量：</td><td><input type="text" style="width: 60px;" id="id_lastMonthRemain_' + type +  '_' + handAdd.index + '" name="sw001List[' + handAdd.index + '].lastMonthRemain" />&nbsp;' + handAdd.typeMap[type].inventoryUnit + '</td>' +
				'<td class="t_title_r">本月领用：</td><td><input type="text" style="width: 60px;" id="id_thisMonthGet_' + type +  '_' + handAdd.index + '" name="sw001List[' + handAdd.index + '].thisMonthGet" />&nbsp;' + handAdd.typeMap[type].usedUnit + '</td>' + 
				'<td class="t_title_r">本月库存量：</td><td><input type="text" style="width: 60px;" id="id_thisMonthInventory_' + type +  '_' + handAdd.index + '" name="sw001List[' + handAdd.index + '].thisMonthInventory" />&nbsp;' + handAdd.typeMap[type].inventoryUnit + '</td>' + 
				'<td><a href="javascript:void(0)" style="margin: 0px;" class="ico_del" title="删除" onclick="handAdd.del(this);" ></a></td></tr>';
		object.append(htmlStr);
		this.validateRequired('id_specification_' + type +  '_' + handAdd.index);
		this.validateRequired('id_lastMonthRemain_' + type +  '_' + handAdd.index);
		this.validateRequired('id_thisMonthGet_' + type +  '_' + handAdd.index);
		this.validateRequired('id_thisMonthInventory_' + type +  '_' + handAdd.index);
		handAdd.index ++;
	},
	//删除
	del : function(obj) {
		$(obj).parent().parent().remove();
	}
};

$(document).ready(function() {
	handAdd.index = parseInt('${index}');
	handAdd.typeMap = jQuery.parseJSON('${typeMap}');
	
	window.setTimeout(function() {
		Comm.form({
			id : 'handHygieneAdd',
			url : '${webroot}/sw001Swsyp/f_json/add.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title: '提示', msg: '操作成功！' });
					//刷新父页面数据
					var parentObject = parent.Comm.getObjectCache();
					parentObject.query();
					parent.Comm.dialogClose('${param.dialogId}');
				} else {
					parent.$.messager.show({title : '提示',msg : json.msg});
				}
			}
		});
	}, 100);
	
	//填报科室
	Csm.combogrid.dep({
		id: 'id_reportDeptId',
		flag: '1',
		//【可选参数】下拉列表的默认value，不传则没有默认值；
		//value: '${sw001Swsyp.reportDeptId}',
		required:true,
		onSelect : function(index,row) {
			$('#id_reportDeptName').val(row.deptName);
		},
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_reportDeptId');
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
        panelWidth: 260,
        panelHeight: 300,
        textField:'employeeName',
		url: '${webroot}/doctor/json/queryToSelect.shtml?page=1&size=200&defValue=${sw001Swsyp.reportUserId}',
        columns:[
        	[
             {field:'employeeId',title:'职工编号',sortable:true,width:150},  
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