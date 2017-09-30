<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormSysDict" method="post">
	<input type="hidden" name="id" value="${sysDict.id}" /> 
	<input type="hidden" name="dictTypeCode" value="${sysDict.dictTypeCode}" /> 
	<input type="hidden" name="dictStatus" value="${sysDict.dictStatus}" />
	<table class="table mb60" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title" style="width: 85px;">级别：</td>
				<td>
					<select id="id_scopeLevel" name="scopeLevel" class="easyui-combobox" style="width: 150px;" onchange="changeScopeLevel();">
						<option value="">平台</option>
						<!-- <option value="6">医院</option> -->
						<option value="3" ${sysDict.scopeLevel == 3 ? 'selected="selected"' : ''}>科室</option>
					</select>
				</td>
				<td class="t_title" style="width: 85px;" id="id_depNo_th"><c:out value="${sysDict.scopeLevel == 3 ? '科室：' : ''}" /></td>
				<td>
					<span id="id_depNo_td" style="display: none;">
						<input id="id_depNo" name="depNo" disabled="disabled" style="width: 150px;" />
					</span>
				</td>
			</tr>
			<tr>
				<%-- <td class="t_title" style="width: 85px;">应用对象：</td>
				<td><input type="text" id="unit1_3" 
						<c:if test="${sysDict.id!=null and sysDict.scopeLevel==6}"> value="${sysDict.unitId}" </c:if>
						style="width: 150px;" class="text" name="scopeLevel" />
				</td> --%>
				<td class="t_title" style="width: 85px;">上级字典：</td>
				<td>					
					<select id="parentCode" name="parentCode" ></select>						
				</td>
				<td class="t_title"></td><td></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>字典名称：</td>
				<td><input type="text" id="code" name="dictName"
					value="<c:out value="${sysDict.dictName}" />" class="easyui-validatebox text"
					required="true"></td>
				<td class="t_title"><span class="red">*</span>字典编码：</td>
				<td><input type="text" name="dictCode"
					value="<c:out value="${sysDict.dictCode}" />" class="easyui-validatebox text"
					required="true"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>排序号：</td>
				<td><input type="text" name="sequenceNumber"
					value="<c:out value="${sysDict.sequenceNumber}" />" class="easyui-validatebox text"
					required="true"></td>
				<td class="t_title">拼音码：</td>
				<td><input type="text" class="text" name="pinyin"
					value="<c:out value="${sysDict.pinyin}" />" id="topinyin"></td>
			</tr>
			<tr>
				<td class="t_title">扩展参数1：</td>
				<td><input type="text" class="text" name="extParam1"
					value="<c:out value="${sysDict.extParam1}" />"></td>
				<td class="t_title">扩展参数2：</td>
				<td><input type="text" class="text" name="extParam2"
					value="<c:out value="${sysDict.extParam2}" />"></td>
			</tr>
			<tr>
				<td class="t_title">参数描述：</td>
				<td colspan="3"><textarea name="remark"
						style="width: 95%; height: 60px;" class="easyui-validatebox"><c:out value="${sysDict.remark}" /></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormSysDict').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>		
	</div>
</form>
<script>
	$(document).ready(
		function() {
			window.setTimeout(function() {
				Csm.text.unit({id:'unitIdPanel', value:'${sysDict.unitId}',type: 'html'});
				$('#code').live('blur',function() {
				$("#topinyin").val($(this).pinyinFirstLower().toUpperCase());});
				Csm.select.load({
					id : 'parentCode',
					url : webroot+ '/sysDict/f_view/getParent.shtml?code=${sysDict.dictTypeCode}',
					data : {},
					headerKey : '',
					headerValue : '-- 请选择 --',
					value : '${sysDict.parentCode}',
					kcode : 'dictCode',
					kvalue : 'dictName',
					pid : 'parentCode'
					});
				var placeholderInfo = '平台';
				<c:if test="${sysDict.id!=null and sysDict.scopeLevel==9 }">
					placeholderInfo = '${sysDict.scopelevelName}';
				</c:if>
				/* $('#unit1_3').select2Remote({
					//这里填写空选项时显示的文字
					placeholder : placeholderInfo,
					//远程加载的url
					url : Csm.url.unitQuery,
					//初始化url【参考方法写法：/unit/json/get】
					initUrl : Csm.url.unitGet
				}); */
					Comm.form({
						id : 'editFormSysDict',
						url : '${webroot}/sysDict/f_json/save.shtml',
						subbtn : 'changeFormSubmitBtn',
						success : function(json) {
							if (json.result === 'success') {
								$.messager.show({title : '提示',msg : '操作成功！'});
								var parentObject = Comm.getObjectCache();
								parentObject.query();
								Comm.dialogClose('${param.dialogId}');
							} else if (json.result === 'error') {
								$.messager.show({title : '提示',msg : '操作失败！'});
							} else {
								$.messager.show({title : '提示',msg : json.msg});
							}
						}});
					
					//科室
					Csm.combogrid.dep({
						id: 'id_depNo',
						required:true,
				        onHidePanel : function() {
				        	Csm.valueValite.combogrid('id_depNo');
						}
					});
					
					if ('${sysDict.scopeLevel}' == '3') {
						$('#id_depNo').combogrid('setValue', '${sysDict.depNo}');
						$('#id_depNo_td').css('display','');
						$('#id_depNo').combogrid('enable');
					}
					
				}, 100);
		});
	
	function changeScopeLevel() {
		if ($('#id_scopeLevel').combogrid('getValue') == 3) {
			$('#id_depNo_th').html('科室：');
			$('#id_depNo_td').css('display','');
			$('#id_depNo').combogrid('enable');
		} else {
			$('#id_depNo_th').html('');
			$('#id_depNo_td').css('display','none');
			$('#id_depNo').combogrid('disable');
			$('#id_depNo').next().find(".textbox-text").removeClass("validatebox-invalid");
		}
	}
</script>