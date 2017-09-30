<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>消毒灭菌录入</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
<form id="hygieneEdit" method="post">
	<input type="hidden" id="id_action" name="action" value="${action}" />
	<input type="hidden" name="type" value="${type}" />
	<div class="easyui-layout" style="width: 960px; height: 545px;">
		<div id="id_hygiene" data-options="region:'west',border:false" style="width:470px;overflow: hidden;border-right-width: 1px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',border:false" style="overflow: hidden;height: 110px;">
					<table class="table smallTable" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="t_title">报告单号：</td>
								<td>
									<input type="text" id="id_djId" name="djId" style="width:120px;" value="${hw101Jcdj.djId}" required="true" class="easyui-validatebox" readonly="readonly"/>
								</td>
								<td class="t_title">科室：</td>
								<td>
									<input type="hidden" name="deptName" id="id_deptName" />
									<input type="text" id="id_deptId" name="deptId" style="width:132px;" />
								</td>
							</tr>
							<tr>
								<td class="t_title">填报人：</td>
								<td>
									<input type="text" id="id_reportBy" name="reportBy" style="width: 132px;" onchange="hygiene.leftChange();"/>
								</td>
								<td class="t_title">填报日期：</td>
								<td>
									<input type="text" id="id_reportAt" name="reportAt" style="width:120px;" class="Wdate text easyui-validatebox" value="<fmt:formatDate value="${hw101Jcdj.reportAt}" pattern="yyyy-MM-dd" />" onclick="WdatePicker()" required="true" onchange="hygiene.leftChange();" />
								</td>
							</tr>
							<tr>
								<td class="t_title">负责人：</td>
								<td>
									<input type="text" id="id_createBy" name="createBy" style="width: 132px;" />
								</td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div data-options="region:'center',border:false" style="border-top-width: 1px;">
					<div id="tb_sampling" style="padding-right: 10px; text-align:right;">
						<input type="button" id="id_add" class="button" value="新建" onclick="hygiene.addSampleData();" />&nbsp;&nbsp;
						<input type="button" id="id_del" class="button" value="删除" onclick="hygiene.delSampleDataConf();" />&nbsp;&nbsp;
						<input type="button" id="id_copy_add" class="button" value="复制并新增" onclick="hygiene.copyAndAdd();" />
					</div>
					<div id="id_sampling_list"></div>
				</div>
			</div>
		</div>
		<div id="id_sampling" data-options="region:'center',border:false,footer:'#button_div'" style="overflow: hidden;">
			<div class="easyui-panel" data-options="border:false" style="overflow: hidden;">
				<input type="hidden" id="id_hw102Id" name="id" class="input_clear" value="<c:out value="${hw102Jcdmx.id}" />" />
				<input type="hidden" id="id_reportId" name="reportId" class="input_clear" value="<c:out value="${hw102Jcdmx.reportId}" />" />
				<table class="table smallTable" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td class="t_title">监测项目：</td>
							<td colspan="3">
								<input type="hidden" id="id_className" name="className" class="input_clear" value="${hw102Jcdmx.className}"/>
								<input id="id_classId" name="classId" style="width:200px;" /> 
								<c:if test="${'edit' eq action}">、
								&nbsp;&nbsp;<input type="button" id="id_entry_sample" class="button" value="录入" onclick="hygiene.entrySampleAdd();" />
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="t_title">采样场所：</td>
							<td>
								<input type="hidden" id="id_placeName" name="placeName" class="input_clear" value="${hw102Jcdmx.placeName}"/>
								<input type="text" id="id_placeId" name="placeId" style="width: 133px;" />
							</td>
							<td class="t_title">采样标本：</td>
							<td>
								<input type="hidden" id="id_sampleName" name="sampleName" class="input_clear" value="${hw102Jcdmx.sampleName}"/>
								<input type="text" id="id_sampleId" name="sampleId" style="width: 133px;" />
							</td>
						</tr>
						<tr>
							<td class="t_title">采样方法：</td>
							<td>
								<input type="hidden" id="id_takeModeName" name="takeModeName" class="input_clear" value="${hw102Jcdmx.takeModeName}"/>
								<input type="text" id="id_takeModeId" name="takeModeId" style="width: 133px;" />
							</td>
							<td class="t_title">采样点数：</td>
							<td>
								<input type="hidden" id="id_posName" name="posName" class="input_clear" value="${hw102Jcdmx.posName}"/>
								<input type="text" id="id_posId" name="posId" style="width: 133px;" />
							</td>
						</tr>
						<tr>
							<td class="t_title">采样人员：</td>
							<td>
								<input type="hidden" id="id_takeByName" name="takeByName" class="input_clear" value="${hw102Jcdmx.takeByName}"/>
								<input type="text" id="id_takeBy" name="takeBy" style="width: 133px;" />
							</td>
							<td class="t_title">采样日期：</td>
							<td>
								<input type="text" id="id_takeAt" name="takeAt" style="width: 133px;" class="Wdate text" value="<fmt:formatDate value="${hw102Jcdmx.takeAt}" pattern="yyyy-MM-dd" />" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							</td>
						</tr>
						<tr>
							<td class="t_title">采样备注：</td>
							<td colspan="3">
								<input type="text" id="id_cyMeno"  name="cyMeno" class="input_clear" style="width: 340px;" value="${hw102Jcdmx.cyMeno}"/>
							</td>
						</tr>
						<tr>
							<td class="t_title">监测类型：</td>
							<td>
								<input type="hidden" id="id_takeTypeName" name="takeTypeName" class="input_clear" value="${hw102Jcdmx.takeTypeName}"/>
								<nis:select id="id_takeType" name="takeType" value="${hw102Jcdmx.takeType}" cssCls="easyui-combobox" dictcode="sampe_monitor_type" headerKey="" headerValue="-请选择-" exp="data-options=\"editable:false\" style=\"width: 133px;\" onChange=\"$('#id_takeTypeName').val($('#id_takeType').find('option:selected').text());\"" />
							</td>
							<td class="t_title">是否复查：</td>
							<td>
								<label><input type="radio" id="id_recheck_True" value="True" name="recheck" ${hw102Jcdmx.recheck eq 'True' ? 'checked="checked"' : ''} />&nbsp;是</label>&nbsp;&nbsp;&nbsp;
								<label><input type="radio" id="id_recheck_False" value="False" name="recheck" ${(hw102Jcdmx.recheck eq 'False'  || empty hw102Jcdmx.recheck) ? 'checked="checked"' : ''} />&nbsp;否</label>
							</td>
						</tr>
						<tr>
							<td class="t_title">检验人员：</td>
							<td>
								<input type="hidden" id="id_testByName" name="testByName" class="input_clear" value="${hw102Jcdmx.testByName}"/>
								<input type="text" id="id_testBy" name="testBy" style="width: 133px;" />
							</td>
							<td class="t_title">检验日期：</td>
							<td>
								<input type="text" id="id_testAt" name="testAt" style="width: 133px;" class="Wdate text input_clear" value="<fmt:formatDate value="${hw102Jcdmx.testAt}" pattern="yyyy-MM-dd" />" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="easyui-panel" id="id_monitoringsample_div" style="height: 150px;border-width: 1px 0 1px 0;">
				<input type="hidden" id="id_hw103Action" name="hw103Action">
				<div id="id_monitoringsample"></div>
			</div>
			<div class="easyui-panel" data-options="border:false" style="overflow: hidden;">
				<input type="hidden" name="st004Id" value="<c:out value="${st004Yzxxb.id}" />" />
				<table class="table smallTable" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td class="t_title">检出细菌：</td>
							<td>
								<input type="text" id="id_checkOutBacteria" name="checkOutBacteria"  style="width: 133px;" />
							</td>
							<td class="t_title">菌落数：</td>
							<td>
								<input type="text" id="id_resultPathoNum" name="resultPathoNum" class="input_clear" style="width: 110px; height:24px;" />
							</td>
						</tr>
						<tr>
							<td class="t_title">
							<span id="id_resultFlagSpanTile">是否合格：<span>
							</td>
							<td>
							<span id="id_resultFlagSpan">
								<select id="id_resultFlag"  class="easyui-combobox" name="resultFlag" style="width:133px;">
								    <option value='0'>合格</option>
								    <option value='1'>不合格</option>
								</select>	
							</span>
							</td>
						</tr>
						<tr>
							<td class="t_title">结果备注：</td>
							<td colspan="3">
								<textarea id="id_memo" name="memo" class="input_clear" style="width: 340px;height: 28px;"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="button_div" style="overflow: hidden; height: 30px;padding-top:6px;">				
				<div style="margin-left:30px;">
					<div style="display:inline-block; margin-right:80px;"><label><input type="checkbox" id="id_add_again" />&nbsp;继续新增</label></div>
	 					<!-- <div class="btn_blue"> -->
						<input type="button" class="btn_blue_save" id="changeFormSubmitBtn" onclick="hygiene.validate();" value="保存">
<!-- 						<a href="javascript:;"id="changeFormSubmitBtn" onclick = "hygiene.validate();" class="no_ico"><span>保存</span></a> -->
					<!-- </div> -->	
					<div class="n_btn_grey">
						<a href="javascript:;" onclick="parent.Comm.dialogClose('${param.dialogId}')"  class="no_ico"><span>关闭</span></a>
					</div>
				</div>	
			</div>
		</div>
	</div>
	<%-- <div class="footer">
		<input type="button" class="btn_save" id="changeFormSubmitBtn" onclick="$('#monitorProjectEdit').submit();" value="保存">
		<input type="button" class="btn_return" data-options="iconCls:'icon-cancel'" onclick="parent.Comm.dialogClose('${param.dialogId}')" value="关闭" />
	</div> --%>
</form>
<script type="text/javascript">
var hygiene = {
	isInputResults : "enable",
	pclassId : '',
	classId : '',
	action : '${action}',
	hw102Id : '',
	isAudit : '${isAudit}',
	canEdit : 1,
	//刷新父页面列表数据
	parentQuery : function() {
		//刷新父页面列表数据
		var parentObject = parent.Comm.getObjectCache();
		parentObject.query('${hw101Jcdj.djId}');
		
	},
	
	validate : function (){
		$('#changeFormSubmitBtn').attr('disabled',true);
		if(hygiene.isInputResults == "enable"){
			var count = $('#id_sampling_list').datagrid('getRows').length;
			for(var i=0;i<=count-1;i++){
				var index = i;
				var value = $('#id_resultCriterion_' + index).val();
				var criter = $('#id_samp_criter_' + index).val();
				if ($.trim(value).length > 0) {
					if(criter && !/.*[\u4e00-\u9fa5]+.*$/.test(criter)){
						if (isNaN(value)){
							parent.$.messager.show({title : '提示',msg : '监测结果必须为数字！'});
							$('#changeFormSubmitBtn').attr('disabled',false);
							return false;   
						}
					}
				}
			}
		}
		var checkOutBacteria = $('#id_checkOutBacteria').combobox('getValues');
		if(checkOutBacteria!=""&&checkOutBacteria!=null){
			if(!$('#id_resultPathoNum').val()){
				parent.$.messager.show({title : '提示',msg : '菌落数不能为空！'});
				$('#changeFormSubmitBtn').attr('disabled',false);
				return false;
			}else{
				if(isNaN($('#id_resultPathoNum').val())){
					parent.$.messager.show({title : '提示',msg : '菌落数输入不合法！'});
					$('#changeFormSubmitBtn').attr('disabled',false);
					  return false;
				}
			}
		}
		$('#hygieneEdit').submit();
	},
	getNotNullStr : function(str) {
		if (str == null) {
			return '';
		} else {
			return str;
		}
	},
	//左边数据填写处理
	leftChange : function() {
		if ($.trim($('#id_djId').val()).length != 0 && $.trim($('#id_deptId').combogrid('getText')).length != 0 && $.trim($('#id_reportBy').combogrid('getText')).length != 0 
				&& $.trim($('#id_reportAt').val()).length != 0 && $.trim($('#id_createBy').combogrid('getText')).length != 0) {
			this.setRightEdit('editable');
		} else {
			this.setRightEdit('disabled');
		}
	},
	//设置右边数据编辑状态
	setRightEdit: function(state) {
		if ('editable' == state) {
			$("#id_sampling input:text").attr("disabled",false);
			$("#id_sampling select").attr("disabled",false);
			$("#id_sampling textarea").attr("disabled",false);
			$("#id_sampling .combo-f").combobox("enable");
			if ($('#id_hw102Id').val().length > 0) {
				$('#id_classId').combotree('disable');
			}
			var checkOutBacteria = $('#id_checkOutBacteria').combobox('getValues');
       		if(checkOutBacteria!=""){
       			$('#id_resultFlag').combobox({disabled:false});
       			$('#id_resultPathoNum').attr("disabled",false);
       		}else{
       			$('#id_resultFlag').combobox({disabled:true});
       			document.getElementById("id_resultFlagSpanTile").style.display='none';
       			document.getElementById("id_resultFlagSpan").style.display='none';
       			$('#id_resultPathoNum').attr("disabled",true);
       		}
		} else {
			$("#id_sampling input:text").attr("disabled",true);
			$("#id_sampling select").attr("disabled",true);
			$("#id_sampling textarea").attr("disabled",true);
			$("#id_sampling .combo-f").combobox("disable");
		}
	},
	//设置左边数据编辑状态
	setLeftEdit: function(state) {
		if ('editable' == state) {
			$("#id_hygiene input:text").attr("disabled",false);
			$("#id_hygiene .combo-f").combobox("enable");
			$('#changeFormSubmitBtn').attr("disabled",false);
			$('#id_copy_add').attr("disabled",false);
			$('#id_add').attr("disabled",false);
			$('#id_del').attr("disabled",false);
		} else {
			$("#id_hygiene input:text").attr("disabled",true);
			$("#id_hygiene .combo-f").combobox("disable");
			$('#changeFormSubmitBtn').attr("disabled",true);
			$('#id_copy_add').attr("disabled",true);
			$('#id_add').attr("disabled",true);
			$('#id_del').attr("disabled",true);
		}
	},
	//渲染采样标本数据
	setSampleData : function(classId, value) {
		$('#id_sampleId').combogrid({
			url:'${webroot}/hw004Cybb/f_json/queryList.shtml?flag=1&classId=' + classId
			//value:value
		});
		setTimeout("$('#id_sampleId').combogrid('setValue', '"+value+"')",400);
	},
	//渲染采样方法数据
	setTakeMode : function(classId, value) {
		$('#id_takeModeId').combobox({
			url: '${webroot}/hw005Cyff/f_json/query.shtml?classId=' + classId,
			value: value
		});
	},
	//渲染采样点数数据
	setPosData : function(classId, value) {
		$('#id_posId').combobox({
			url: '${webroot}/hw006Cyds/f_json/query.shtml?classId=' + classId,
			value: value
		});
	},
	//渲染采样人员数据
	setTakeByData : function(djDeptId) {
		$('#id_takeBy').combogrid({
			url: '${webroot}/doctor/json/queryList.shtml?page=1&size=2000',
			queryParams: {
				djDeptId: djDeptId
			}
		});
	},
	//渲染监测样本数据
	setMonitorSample : function(classId,posId) {
		if ('edit' == hygiene.action) {
			hygiene.setSampleEdit(classId,posId);
		} else {
			hygiene.setSampleAdd(classId,posId);
		}
	},
	//渲染监测样本数据(修改)
	setSampleEdit : function(classId) {
		$('#id_monitoringsample').datagrid({
			url: '${webroot}/hw103Jcdjg/f_json/findListByHw102Id.shtml',
			queryParams: {
				hw102Id: classId
			},
			columns:[ 
		       	[
					{field:'resultId',title:'样本编号',sortable:true,width:60,
						formatter:function(value,row,index){
							return ['<input type="hidden" name="hw103List[' + index + '].resultId" value="' + row.resultId + '" />' +
							        '<input type="hidden" name="hw103List[' + index + '].itemId" value="' + row.itemId + '" />' + 
							        '<input type="hidden" name="hw103List[' + index + '].classId" value="' + row.classId + '" />' + row.resultId + ''].join('');
						}
					},
		            {field:'itemName',title:'监测指标',sortable:true,width:120,
						formatter:function(value,row,index){
							return ['<input type="hidden" name="hw103List[' + index + '].itemName" value="' + row.itemName + '" />' + row.itemName + ''].join('');
						}
					},
		            {field:'resultCriterion',title:'监测结果',width:80,
		            	formatter:function(value,row,index){
		            		return ['<input type="text" id="id_resultCriterion_' + index + '" name="hw103List[' + index + '].result" value="' + hygiene.getNotNullStr(row.result) + 
			            			'" onblur="hygiene.judgeIsQualified(this, ' + index + ');" style="width: 70px;" ' + (hygiene.canEdit == 0 ? 'disabled="disabled"' : '')  + '/>'].join('');
						}
		            },
		            {field:'condition',title:'执行标准',width:120,
		            	formatter:function(value,row,index){
		            		return ['<input type="hidden" id="id_samp_condition_' + index + '" name="hw103List[' + index + '].resultCondition" value="' + hygiene.getNotNullStr(row.resultCondition) + '" />' +
		            		        '<input type="hidden" id="id_samp_criter_' + index + '" name="hw103List[' + index + '].resultCriterion" value="' + row.resultCriterion + '" />' +
							        '<input type="hidden" id="id_samp_unit_' + index + '" name="hw103List[' + index + '].resultUnit" value="' + hygiene.getNotNullStr(row.resultUnit) + '" />'+ 
							        '<span id="id_samp_span_' + index + '">' + hygiene.getNotNullStr(row.resultCondition) + row.resultCriterion + hygiene.getNotNullStr(row.resultUnit) + '</span>'].join('');
						}
		            },
		            {field:'flag',title:'是否合格',sortable:true,align:'center',width:55,
		            	formatter:function(value,row,index){
		            		var reStr = '<input type="hidden" id="id_resultFlag_' + index + '" name="hw103List[' + index + '].resultFlag" value="' + row.resultFlag + '" />';
		            		if (row.resultFlag == 0) {
		            			reStr += '<span id="id_flag_' + index + '" style="color: green;font-size:16px;">√</span>';
		            		} else if (row.resultFlag == 1) {
		            			reStr += '<span id="id_flag_' + index + '" style="color: red;font-size:20px;">×</span>';
		            		} else {
		            			reStr += '<span id="id_flag_' + index + '"></span>';
		            		}
							return [reStr].join('');
						},
						styler: function(value,row,index){
							return 'font-weight:900;';
						}
		            }
		        ]
	        ]
		});
	},
	//渲染监测样本数据(添加)
	setSampleAdd : function(classId,posId,callback) {
		$('#id_monitoringsample').datagrid({
			url: '${webroot}/hw002Jsbz/f_json/findListByClassId.shtml',
			queryParams: {
				classId: classId,
				posId : posId
			},
			columns:[ 
		       	[
					{field:'itemId',title:'样本编号',sortable:true,width:60,
						formatter:function(value,row,index){
							return ['<input type="hidden" name="hw103List[' + index + '].resultId" value="' + (index + 1) + '" />' + 
							        '<input type="hidden" name="hw103List[' + index + '].itemId" value="' + row.itemId + '" />' + 
							        '<input type="hidden" name="hw103List[' + index + '].classId" value="' + row.classId + '" />' + (index + 1) + ''].join('');
						}
					},
		            {field:'itemName',title:'监测指标',sortable:true,width:120,
						formatter:function(value,row,index){
							return ['<input type="hidden" name="hw103List[' + index + '].itemName" value="' + row.itemName + '" />' + row.itemName + ''].join('');
						}
					},
		            {field:'criterion',title:'监测结果',width:80,
		            	formatter:function(value,row,index){
		            		if($("#id_testAt").attr("disabled")){
		            			return ['<input type="text" id="id_resultCriterion_' + index + '" disabled="disabled" name="hw103List[' + index + '].result"  onblur="hygiene.judgeIsQualified(this, ' + index + ');" style="width: 40px;" />'].join('');
		            		}else{
		            			return ['<input type="text" id="id_resultCriterion_' + index + '" name="hw103List[' + index + '].result"  onblur="hygiene.judgeIsQualified(this, ' + index + ');" style="width: 40px;" />'].join('');

		            		}
						}
		            },
		            {field:'condition',title:'执行标准',width:120,
		            	formatter:function(value,row,index){
		            		var unit = new Array();
		            		if (row.unit) {
		            			unit = row.unit.split('|');
		            		}
		            		if (unit.length > 1) {
		            			var criterion = row.criterion.split('|');
		            			return ['<input type="hidden" id="id_samp_condition_' + index + '" name="hw103List[' + index + '].resultCondition" value="' + hygiene.getNotNullStr(row.condition) + '" />' +
		            			        '<input type="hidden" id="id_samp_criter_' + index + '" name="hw103List[' + index + '].resultCriterion" value="' + criterion[0] + '" />' +
								        '<input type="hidden" id="id_samp_unit_' + index + '" name="hw103List[' + index + '].resultUnit" value="' + unit[0] + '" />'+ 
								        '<span id="id_samp_span_' + index + '">' + hygiene.getNotNullStr(row.condition) + criterion[0] + unit[0] + '</span>'].join('');
		            		} else {
		            			return ['<input type="hidden" id="id_samp_condition_' + index + '" name="hw103List[' + index + '].resultCondition" value="' + hygiene.getNotNullStr(row.condition) + '" />' +
		            			        '<input type="hidden" id="id_samp_criter_' + index + '" name="hw103List[' + index + '].resultCriterion" value="' + row.criterion + '" />' +
								        '<input type="hidden" id="id_samp_unit_' + index + '" name="hw103List[' + index + '].resultUnit" value="' + hygiene.getNotNullStr(row.unit) + '" />'+ 
								        (hygiene.getNotNullStr(row.condition) + row.criterion + hygiene.getNotNullStr(row.unit))].join('');
		            		}
						}
		            },
		            {field:'flag',title:'是否合格',sortable:true,align:'center',width:55,
		            	formatter:function(value,row,index){
		            		var reStr = '<input type="hidden" id="id_resultFlag_' + index + '" name="hw103List[' + index + '].resultFlag" value="' + hygiene.getNotNullStr(row.resultFlag) + '" />';
		            		if (row.resultFlag == 0) {
		            			reStr += '<span id="id_flag_' + index + '" style="color: green;font-size:16px;">√</span>';
		            		} else if (row.resultFlag == 1) {
		            			reStr += '<span id="id_flag_' + index + '" style="color: red;font-size:20px;">×</span>';
		            		} else {
		            			reStr += '<span id="id_flag_' + index + '"></span>';
		            		}
							return [reStr].join('');
						},
						styler: function(value,row,index){
							return 'font-weight:900;';
						}
		            }
		        ]
	        ],
	        onLoadSuccess : function (data) {
	        	for(var i = 0; i < data.rows.length; i++) {
	    			var index = $('#id_monitoringsample').datagrid('getRowIndex', data.rows[i]);
	    			//判断是否符合标准
	    			hygiene.judgeIsQualified($('#id_resultCriterion_' + index), index);
	    		}
	        }
		});
	},
	//录入监测样本数据(添加)
	entrySampleAdd : function() {
		var rows = $('#id_monitoringsample').datagrid('getRows');
		var classId = $('#id_classId').combotree('getValue');
		var posId = $('#id_posId').combotree('getValue');
		if ((rows == null || rows.length == 0) && classId) {
    		//重新加载需要添加的数据
    		$('#id_hw103Action').val('add');
    		hygiene.setSampleAdd(classId,posId);
    	}
	},
	//设置监测指标的单位和标准
	setCriterionAndUnit : function(num) {
		var rows = $('#id_monitoringsample').datagrid('getRows');
		for(var i = 0; i < rows.length; i++) {
			var index = $('#id_monitoringsample').datagrid('getRowIndex', rows[i]);
			var unit = rows[i].unit.split('|');
			if (unit.length > 1 && unit.length >= (num - 1)) {
				var criterion = rows[i].criterion.split('|');
				$('#id_samp_criter_' + index).val(criterion[num - 1]);
				$('#id_samp_unit_' + index).val(unit[num - 1]);
				$('#id_samp_span_' + index).text(rows[i].condition + criterion[num - 1] + unit[num - 1]);		
			}
			//判断是否符合标准
			this.judgeIsQualified($('#id_resultCriterion_' + index), index);
		}
	},
	//判断是否合格
	judgeIsQualified : function(obj, index) {
		var condition = $('#id_samp_condition_' + index).val();
		var flag = false;
		var value = $(obj).val();
		if ($.trim(value).length > 0) {
			var criter = $('#id_samp_criter_' + index).val();
			if ($.trim(condition).length > 0) {
				if ("≤" == condition && parseFloat(value) <= parseFloat(criter)) {
					flag = true;
				} else if ("≥" == condition && parseFloat(value) >= parseFloat(criter)) {
					flag = true;
				} else if ("<" == condition && parseFloat(value) < parseFloat(criter)) {
					flag = true;
				} else if (">" == condition && parseFloat(value) > parseFloat(criter)) {
					flag = true;
				} else if ("=" == condition && parseFloat(value) == parseFloat(criter)) {
					flag = true;
				}
			} else if (value.substr(0, criter.length) === criter) {
				flag = true;
			}
			if (!flag) {
				//不合格处理
				$('#id_flag_' + index).text('×');
				$('#id_flag_' + index).css({"color":"red", "font-size":"20px"});
				$('#id_resultFlag_' + index).val(1);
			} else if (flag) {
				$('#id_flag_' + index).text('√');
				$('#id_flag_' + index).css({"color":"green", "font-size":"16px"});
				$('#id_resultFlag_' + index).val(0);
			} else {
				$('#id_flag_' + index).text('');
				$('#id_resultFlag_' + index).val(-1);
			}
		} else {
			$('#id_flag_' + index).text('');
			$('#id_resultFlag_' + index).val(-1);
		}
	},
	//修改采样数据
	editSampleData : function(id) {
		hygiene.hw102Id=id;
		$.ajax({
            url: '${webroot}/hw101Jcdj/f_json/getHw102Jcdmx.shtml',
            type: 'post',
            data: { id: id,djDeptId:$('#id_deptId').combogrid('getValue')},
            dataType: 'json',
            success : function(json) {
            	$('#id_classId').combotree({
    				url:'${webroot}/hw001Jcxm/f_json/queryTree.shtml',
    				method:'get',
    				queryParams: {
    					'isSee' : '1',
    					'flag' : '1',
    					'type': '${type}',
    		        	'djDeptId': $('#id_deptId').combogrid('getValue')
    		        }
    			});
            	hygiene.action='edit';
            	hygiene.pclassId='';
        		hygiene.classId='';
            	$('#id_hw102Id').val(json.id);
            	$('#id_reportId').val(json.reportId);
            	var classId = json.classId.substr(0, 2);
           		hygiene.setSampleData(classId, json.sampleId);
           		hygiene.setTakeMode(classId, json.takeModeId);
           		hygiene.setPosData(classId, json.posId);
           		hygiene.pclassId = classId;
            	$('#id_className').val(json.className);
            	$('#id_classId').combotree('disable');
            	$('#id_classId').combotree('setValue', json.classId);
            	$('#id_placeName').val(json.placeName);
            	if (json.placeId != null) {
            		//采样场所
            		$('#id_placeId').combogrid({
            	        url: '${webroot}/hw003Cycs/f_json/queryList.shtml?page=1&size=100',
            	        queryParams: {
            	        	defValue: json.placeId
            	        }
            		});
            		setTimeout("$('#id_placeId').combogrid('setValue', '"+json.placeId+"')",400);
            	}
            	$('#id_sampleName').val(json.sampleName);
            	//$('#id_sampleId').combogrid('setValue', json.sampleId);
            	$('#id_takeModeName').val(json.takeModeName);
            	//$('#id_takeModeId').combobox('setValue', json.takeModeId);
            	$('#id_posName').val(json.posName);
            	//$('#id_posId').combobox('setValue', json.posId);
            	if (json.takeBy != null) {
            		$('#id_takeBy').combogrid('setValue', json.takeBy);
            		$('#id_takeByName').val($('#id_takeBy').combogrid('getText'));
            	}
            	$('#id_takeAt').val(json.takeAt);
            	$('#id_cyMeno').val(json.cyMeno);
            	$('#id_takeTypeName').val(json.takeTypeName);
            	$('#id_takeType').combobox('setValue', json.takeType);
            	hygiene.judgeReportPermissions(classId, function(i){
            		if (i > 0) {
                		hygiene.setIsInputReport('enable');
            		} else {
            			hygiene.setIsInputReport('disabled');
                	}
            	});
            	hygiene.classId = json.classId;
            	hygiene.setMonitorSample(json.id,json.posId);
           		hygiene.judgeResultsPermissions(classId, function(i){
               		if (i > 0) {
                   		hygiene.setIsInputResults('enable');
               		} else {
               			hygiene.setIsInputResults('disabled');
                   	}
               		$('#id_recheck_' + json.recheck).attr('checked', 'checked');
               		if (json.testBy != null) {
                   		$('#id_testBy').combogrid('setValue', json.testBy);
                   		$('#id_testByName').val($('#id_testBy').combogrid('getText'));
                   	}
               		$('#id_testAt').val(json.testAt);
               		$('#id_resultPathoNum').val(json.resultPathoNum);
               		if (json.checkOutBacteria != null) {
                   		$('#id_checkOutBacteria').combobox('setValues', json.checkOutBacteria);
                   		var checkOutBacteria = $('#id_checkOutBacteria').combobox('getValues');
                   		if(checkOutBacteria!=""){
                   			$('#id_resultFlag').combobox({disabled:false});
                   			$('#id_resultPathoNum').attr("disabled",false);
                   		}else{
                   			$('#id_resultFlag').combobox({disabled:true});
                   			document.getElementById("id_resultFlagSpanTile").style.display='none';
                   			document.getElementById("id_resultFlagSpan").style.display='none';
                   			$('#id_resultPathoNum').attr("disabled",true);
                   		}
                   	}
               		if(json.resultFlag != null){
               			$('#id_resultFlag').combobox('setValue', json.resultFlag);
               		}
               		$('#id_memo').val(json.memo);
               	});
            	if (hygiene.isAudit == '1' && json.status == 1) {
            		hygiene.canEdit = 0;
            		hygiene.setRightEdit('disabled');
            		hygiene.setLeftEdit('disabled');
            	} else {
            		hygiene.canEdit = 1;
            		hygiene.setRightEdit('editable');
            		hygiene.setLeftEdit('editable');
            	}
            	$('#id_hw103Action').val('edit');
			}
		});
	},
	//添加采样数据
	addSampleData : function() {
		hygiene.setIsInputReport('enable');
		this.setIsInputResults('enable');
		$('#id_classId').combotree({
			url:'${webroot}/hw001Jcxm/f_json/queryTree.shtml',
			method:'get',
			queryParams: {
				'isAdd' : '1',
				'flag' : '1',
				'type': '${type}',
	        	'djDeptId': $('#id_deptId').combogrid('getValue')
	        }
		});
		hygiene.hw102Id='';
		hygiene.action='add';
		hygiene.pclassId='';
		hygiene.classId='';
		$('#id_sampling_list').datagrid('clearSelections');
		$('#id_monitoringsample').datagrid('loadData',{total:0,rows:[]});
		$('#id_sampling .input_clear').val('');
		$('#id_recheck_False').attr('checked', 'checked');
		$('#id_classId').combotree('enable');
		$('#id_classId').combotree("clear");
		$('#id_placeId').combogrid('clear');
    	$('#id_sampleId').combogrid('clear');
    	$('#id_takeModeId').combobox('clear');
    	$('#id_posId').combobox('clear');
    	//$('#id_takeBy').combogrid('clear');
    	//$("#id_takeType").combobox("clear");
   		$('#id_testBy').combogrid('clear');
   		$('#id_checkOutBacteria').combobox('clear');
	},
	//删除采样数据(确认)
	delSampleDataConf : function() {
		var selections = $('#id_sampling_list').datagrid('getChecked');
		if (selections.length == 0) {
			parent.$.messager.show({title : '提示',msg : '请选择要删除的数据！'});
		} else {
			var rows = $('#id_monitoringsample').datagrid('getRows');
			if (rows && rows.length > 0) {
				$.messager.confirm('提示', '该项目<font color="red"><b>已出结果</b></font>，确认删除?', function (r) {
		        	if (r) {
		        		hygiene.delSampleData(selections);
		        	}
				});
			} else {
				$.messager.confirm('提示', '确认删除?', function (r) {
		        	if (r) {
		        		hygiene.delSampleData(selections);
		        	}
		    	});
			}
		}
	},
	//删除采样数据
	delSampleData : function(selections) {
		var id = selections[0].id;
		var row = selections[0];
		var djDeptId = $('#id_deptId').combogrid('getValue');
		$.ajax({
            url: '${webroot}/hw102Jcdmx/f_json/delSampleInfo.shtml',
            type: 'post',
            data: { id: id, djDeptId: djDeptId },
            dataType: 'json',
            success : function(json) {
            	if (json.result === 'success') {
            		//页面上面删除
            		/* for (var i = 0; i < rows.length; i++) {    
        	            var index = $('#id_sampling_list').datagrid('getRowIndex',rows[i]);
        	            $('#id_sampling_list').datagrid('deleteRow',index); 
        	        } */
            		var index = $('#id_sampling_list').datagrid('getRowIndex', row);
       	            $('#id_sampling_list').datagrid('deleteRow', index);
       	            hygiene.addSampleData();
       	          	//刷新父页面列表数据
       	          	hygiene.parentQuery();
				} else {
					parent.$.messager.show({title : '提示',msg : json.msg});
				}
			}
		});
	},
	//复制并新增处理
	copyAndAdd : function() {
		var selections = $('#id_sampling_list').datagrid('getChecked');
		if (selections.length == 0) {
			parent.$.messager.show({title : '提示',msg : '请选择要复制并新增的数据！'});
		} else {
			var id = selections[0].id;
			var row = selections[0];
			var djDeptId = $('#id_deptId').combogrid('getValue');
			$.ajax({
	            url: '${webroot}/hw102Jcdmx/f_json/copySampleInfo.shtml',
	            type: 'post',
	            data: { id: id, djDeptId: djDeptId },
	            dataType: 'json',
	            success : function(json) {
	            	if (json.result === 'success') {
	            		//页面上面添加
	            		$('#id_sampling_list').datagrid('appendRow',{
							id: json.data,
				            placeName: row.placeName,
				            sampleName: row.sampleName,
				            takeByName: row.takeByName,
				            takeAt: row.takeAt,
				            resultFlagName: row.resultFlagName
						});
	            		//刷新父页面列表数据
	            		hygiene.parentQuery();
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}
			});
		}
	},
	//表单提交成功后处理
	formSubmitEnd : function(data) {		
		var datas = data.split('##');
		if ('edit' != hygiene.action) {
			//添加数据到左侧列表
			
			/* $('#id_sampling_list').datagrid("reload"); */
			
			$('#id_sampling_list').datagrid('appendRow',{
				id: datas[0],
	            placeName: $('#id_placeName').val(),
	            sampleName: $('#id_sampleName').val(),
	            takeByName: $('#id_takeBy').combogrid('getText'),
	            takeAt: $('#id_takeAt').val(),
	            resultFlagName: datas[1]
			}); 
			//选中
			if (!$('#id_add_again').is(':checked')) {
				var index = $('#id_sampling_list').datagrid('getRows').length;
				$('#id_sampling_list').datagrid('selectRow', index - 1);
			}
			$('#id_action').val('edit');
			
		} else {
			//刷新左侧列表数据
			var row = $('#id_sampling_list').datagrid('getSelected');
			var index = $('#id_sampling_list').datagrid('getRowIndex', row);
 			$('#id_sampling_list').datagrid('updateRow',{
				index: index,
				row: {
					id: datas[0],
		            placeName: $('#id_placeName').val(),
		            sampleName: $('#id_sampleName').val(),
		            takeByName: $('#id_takeBy').combogrid('getText'),
		            takeAt: $('#id_takeAt').val(),
		            resultFlagName: datas[1]
				}
			}); 
		}
		$('#changeFormSubmitBtn').attr("disabled",false);
		
		if ($('#id_add_again').is(':checked')) {
			hygiene.addSampleData();
		}
	},
	//判断是否有结果录入权限
	judgeResultsPermissions : function(classId, callback) {
		var djDeptId = $('#id_deptId').combogrid('getValue');
		$.ajax({
            url: '${webroot}/hw008Xmsq/f_json/judgeResultsPermissions.shtml',
            type: 'post',
            data: { djDeptId: djDeptId, classId: classId },
            dataType: 'json',
            success : function(json) {
            	callback(parseInt(json));
			}
		});
	},
	//录入结果权限控制
	setIsInputResults : function(state) {
		isInputResults = state;
		if ('enable' == state) {
			//$("input:radio[name='recheck']").attr("disabled",false);
			$("#id_testBy").combogrid("enable");
			$("#id_testAt").attr("disabled",false);
			//$('#id_resultPathoNum').attr("disabled",false);
			$('#id_checkOutBacteria').combobox("enable");
			$('#id_memo').attr("disabled",false);
			$("#id_monitoringsample_div input:text").attr("disabled", false);
			$('#id_entry_sample').show();
		} else {
			//$("input:radio[name='recheck']").attr("disabled",true);
			$("#id_testBy").combogrid("clear");
			$("#id_testBy").combogrid("disable");
			$('#id_testBy').next().find(".textbox-text").removeClass("validatebox-invalid");
			$("#id_testAt").val('');
			$("#id_testAt").attr("disabled",true);
			$("#id_resultPathoNum").val('');
			//$('#id_resultPathoNum').attr("disabled",true);
			$('#id_checkOutBacteria').combobox("clear");
			$('#id_checkOutBacteria').combobox("disable");
			$("#id_memo").val('');
			$('#id_memo').attr("disabled",true);
			$("#id_monitoringsample_div input:text").attr("disabled", true);
			$('#id_entry_sample').hide();
		}
	},
	//判断是否有上报权限
	judgeReportPermissions : function(classId, callback) {
		var djDeptId = $('#id_deptId').combogrid('getValue');
		$.ajax({
            url: '${webroot}/hw008Xmsq/f_json/judgeReportPermissions.shtml',
            type: 'post',
            data: { djDeptId: djDeptId, classId: classId },
            dataType: 'json',
            success : function(json) {
            	callback(parseInt(json));
			}
		});
	},
	//上报权限控制
	setIsInputReport : function(state) {
		if ('enable' == state) {
			$("#id_placeId").combogrid("enable");
			$("#id_sampleId").combogrid("enable");
			$("#id_takeModeId").combobox("enable");
			$("#id_posId").combobox("enable");
			$("#id_takeBy").combogrid("enable");
			$("#id_takeType").combobox("enable");
			$("#id_takeAt").attr("disabled",false);
		} else {
			$("#id_placeId").combogrid("disable");
			$("#id_sampleId").combogrid("disable");
			$("#id_takeModeId").combobox("disable");
			$("#id_posId").combobox("disable");
			$("#id_takeBy").combogrid("disable");
			$("#id_takeType").combobox("disable");
			$("#id_takeAt").attr("disabled",true);
		}
	}
};

$(document).ready(function() {
// 	hygiene.setRightEdit('disabled');
	window.setTimeout(function() {
		if('${action}'!='edit'){
			$('#id_resultFlag').combobox({disabled:true});
			document.getElementById("id_resultFlagSpanTile").style.display='none';
			document.getElementById("id_resultFlagSpan").style.display='none';
			$('#id_resultPathoNum').attr("disabled",true);
		}
		$('#hygieneEdit').form({
			url : '${webroot}/hw101Jcdj/f_json/saveHygiene.shtml',
			onSubmit: function () {
                 var b = $("#hygieneEdit").form('validate');
                 if (!b) {
                	 $('#changeFormSubmitBtn').attr("disabled",false);
                 }
                 return b;
            },
            success:function(data){
            	var json = eval('('+data+')');
            	if (json != undefined && json.result === 'success') {
					parent.$.messager.show({ title: '提示', msg: '操作成功！' });
					//刷新父页面列表数据
					hygiene.parentQuery();
					hygiene.formSubmitEnd(json.data);
				} else if (json != undefined && json.result === 'error') {
					//刷新父页面列表数据
					hygiene.parentQuery();
					parent.$.messager.show({title : '提示',msg : '操作失败！'});
				} else {
					parent.$.messager.show({title : '提示',msg : json});
					//刷新父页面列表数据
					hygiene.parentQuery();
				}
            }
		});
		
	}, 500);
	
	//科室
	$('#id_deptId').combogrid({
		delay: 1000,    
	    mode: 'remote',
	    loadMsg : '正在查询中...',
	    value: '${hw101Jcdj.deptId}',
	    required: true,
        idField:'deptId',
        textField:'deptName',
        panelWidth: 260,
        panelHeight: 300,
		url: '${webroot}/hw009Kssq/f_json/queryList.shtml',
		queryParams: {
			defValue: '${hw101Jcdj.deptId}',
			page: 1,
			size: 200
		},
        columns:[
        	[
             {field:'deptId',title:'科室编号',sortable:true,width:80},  
             {field:'deptName',title:'科室名称',sortable:true,width:160},
            ]
        ],
		onLoadSuccess : function() {
			/* $('#id_deptId').next().find(".textbox-text").blur(function(){
				hygiene.leftChange();
			}); */
			$('#id_deptName').val($('#id_deptId').combogrid('getText'));
		},
		onClickRow : function(index,row){
			$('#id_deptName').val(row.deptName);
			//hygiene.leftChange();
			//hygiene.setTakeByData(row.deptId);
			$('#id_monitoringsample').datagrid('loadData',{total:0,rows:[]});
			$('#id_classId').combotree('clear');
			$('#id_classId').combotree({
				url:'${webroot}/hw001Jcxm/f_json/queryTree.shtml',
				method:'get',
				queryParams: {
					'isAdd' : '1',
					'flag' : '1',
					'type': '${type}',
		        	'djDeptId': $('#id_deptId').combogrid('getValue')
		        }
			});
	    	$('#id_sampleId').combogrid('clear');
	    	$('#id_takeModeId').combobox('clear');
	    	$('#id_posId').combobox('clear');
		},
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_deptId');
        	hygiene.leftChange();
		}
	});
	
	hygiene.setTakeByData('${hw101Jcdj.deptId}');
	
	//样本监测结果
	$('#id_monitoringsample').datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: false,
        remoteSort: false,
        singleSelect: true,
        rownumbers:true,
        border: false,
        url : '',
        columns:[ 
	       	[
				{field:'itemId',title:'样本编号',sortable:true,width:60},
	            {field:'itemName',title:'监测指标',sortable:true,width:120},
	            {field:'criterion',title:'监测结果',width:80},
	            {field:'condition',title:'执行标准',width:120},
	            {field:'flag',title:'是否合格',sortable:true,align:'center',width:55}
	        ]
        ]
	});
	
	//监测项目
	$('#id_classId').combotree({
		url:'${webroot}/hw001Jcxm/f_json/queryTree.shtml',
		method:'get',
		queryParams: {
			'isAdd' : '1',
			'flag' : '1',
			'type': '${type}',
        	'djDeptId': $('#id_deptId').combogrid('getValue')
        },
		value: '${hw102Jcdmx.classId}',
		required:true,
		editable:true, 
		panelHeight:300,
		onBeforeSelect: function(node) {
            if (!$(this).tree('isLeaf', node.target)) {
                return false;
            }
        },
       /*  onSelect: function (item) {  
            var parent = item;  
            var tree = $('#id_classId').combotree('tree');  
            var path = new Array();  
            do {  
                  path.unshift(parent.text);  
                  var parent = tree.tree('getParent', parent.target);  
            } while (parent);  
            var pathStr = '';  
            for (var i = 0; i < path.length; i++) {     
                  if (i < path.length - 1) {
                	 pathStr += path[i];  
                     pathStr += ' - ';  
              }  
            }  
            $('#fm_AETypePath').text(pathStr);  
        },   */
        onClick: function(node) {
            if (!$(this).tree('isLeaf', node.target)) {
            	$(this).tree('toggle',node.target);
                $('#id_classId').combo('showPanel');
            } else {
            	var classId = node.id.substr(0, 2);
            	if (hygiene.pclassId != classId) {
            		hygiene.setSampleData(classId, '${hw102Jcdmx.sampleId}');
            		hygiene.setTakeMode(classId, '${hw102Jcdmx.takeModeId}');
            		hygiene.setPosData(classId, '${hw102Jcdmx.posId}');
            		hygiene.pclassId = classId;
            	}
            	if (hygiene.classId != node.id) {
            		//判断是否有结果录入权限
            		hygiene.judgeResultsPermissions(classId, function(i){
	            		if (i > 0) {
	                		hygiene.setIsInputResults('enable');
	                		//hygiene.setMonitorSample(node.id);
	            		} else {
	            			hygiene.setIsInputResults('disabled');
	            			$('#id_monitoringsample').datagrid('loadData',{total:0,rows:[]});
	                	}
            		});
            		hygiene.classId = node.id;
            	}
            	$('#id_className').val(node.text);
            }
        }
	});
	
	//采样场所
	$('#id_placeId').combogrid({
		delay: 1000,
	    required:true,
	    mode: 'remote',
        idField:'placeId',
        textField:'placeName',
        panelWidth: 230,
        panelHeight:260,
        url: '${webroot}/hw003Cycs/f_json/queryList.shtml?page=1&size=1000',
        queryParams: {
        	defValue: '${hw102Jcdmx.placeId}'
        },
        columns:[
        	[
	            {field:'placeId',title:'场所编号',sortable:true,align:'center',width:70},  
	            {field:'placeName',title:'场所名称',sortable:true,width:160}
            ]
        ],
        onSelect : function(index,row){
			$('#id_placeName').val(row.placeName);
		},
		onClickRow : function(index,row){
			$('#id_placeName').val(row.placeName);
		},
        onHidePanel : function() {
        	//Csm.valueValite.combogrid('id_placeId');
        	//赋值
        	var select = $('#id_placeId').combogrid('grid').datagrid('getSelected');
			if (!select) {
	        	$('#id_placeName').val($('#id_placeId').combogrid("getValue"));
			}
		}
	});
	
	//采样标本
	$('#id_sampleId').combogrid({
		delay: 1000,
	    required:true,
	    mode: 'remote',
        idField:'sampleId',
        textField:'sampleName',
        panelWidth: 250,
        panelHeight:260,
        columns:[
        	[
	            {field:'sampleId',title:'标本编号',sortable:true,align:'center',width:70},  
	            {field:'sampleName',title:'标本名称',sortable:true,width:160}
            ]
        ],
        onSelect : function(index,row){
        	$('#id_sampleName').val(row.sampleName);
        },
		onClickRow : function(index,row){
			$('#id_sampleName').val(row.sampleName);
		},
        onHidePanel : function() {
        	/* Csm.valueValite.combogrid('id_sampleId'); */
        	//赋值
        	var select = $('#id_sampleId').combogrid('grid').datagrid('getSelected');
			if (!select) {
	        	$('#id_sampleName').val($('#id_sampleId').combogrid("getValue"));
			}
		}
	});
	
	//采样方法
	$('#id_takeModeId').combobox({
		editable: false,
		//required:true,
	    valueField:'takeModeId',
	    textField:'takeModeName',
	    onSelect: function(record) {
	    	$('#id_takeModeName').val(record.takeModeName);
	    	hygiene.setCriterionAndUnit(record.rownum);
	    }
	});
	
	//采样点数
	$('#id_posId').combobox({
		editable: false,
		required:true,
	    valueField:'posId',
	    textField:'posName',
	    onSelect: function(record) {
	    	$('#id_posName').val(record.posName);
	    	var classId = $('#id_classId').combotree('getValue');
	    	hygiene.setSampleAdd(classId,record.posId);
	    	//hygiene.setMonitorSample(classId,record.posId);
	    }
	});
	
	//采样人员
	$('#id_takeBy').combogrid({
		delay: 1000,
	    value: '${hw102Jcdmx.takeBy}',
	    required: true,
        panelWidth: 240,
        panelHeight: 240,
        idField:'employeeId',
        textField:'employeeName',
		columns:[
        	[
	            {field:'employeeId',title:'职工编号',sortable:true,width:100},
	            {field:'employeeName',title:'职工姓名',sortable:true,width:115}
            ]
        ],
        onSelect : function(index,row) {
        	$('#id_takeByName').val(row.employeeName);
        },
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_takeBy');
		}
	});
	
	//检验人员
	$('#id_testBy').combogrid({
		delay: 1000,    
	    mode: 'remote',
	    loadMsg : '正在查询中...',
	    value: '${hw102Jcdmx.testBy}',
	    //required:true,
        idField:'employeeId',
        panelWidth: 260,
        panelHeight: 300,
        textField:'employeeName',
		url: '${webroot}/hw010Zzry/f_json/queryList.shtml?page=1&size=200&defValue=${hw102Jcdmx.testBy}',
        columns:[
        	[
             {field:'employeeId',title:'职工编号',sortable:true,width:80},  
             {field:'employeeName',title:'职工名称',sortable:true,width:150}
            ]
        ],
        onSelect : function(index,row) {
        	$('#id_testByName').val(row.employeeName);
        },
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_testBy');
		}
	});
	
	//检出细菌
	$('#id_checkOutBacteria').combobox({
	    //required:true,
	    multiple:true,
	    valueField:'pathoId',
	    textField:'pathoName',
	    url: '${webroot}/hw007Xjlb/f_json/query.shtml',
	    onSelect : function(){
	    	$('#id_resultFlag').combobox({disabled:false});
	    	$('#id_resultPathoNum').attr("disabled",false);
	    	document.getElementById("id_resultFlagSpanTile").style.display='block';
   			document.getElementById("id_resultFlagSpan").style.display='block';
	    	$('#id_resultFlag').combobox('setValue', '1');
	    },
		onUnselect : function(){
			var checkOutBacteria = $('#id_checkOutBacteria').combobox('getValues');
			if(checkOutBacteria==""||checkOutBacteria==null){
	    		$('#id_resultFlag').combobox({disabled:true});
	    		$('#id_resultPathoNum').val("");
	    		document.getElementById("id_resultFlagSpanTile").style.display='none';
	   			document.getElementById("id_resultFlagSpan").style.display='none';
	    		$('#id_resultPathoNum').attr("disabled",true);
			}
	    }
	});

	//采样记录列表
	$('#id_sampling_list').datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: false,
        remoteSort: false,
        singleSelect: true,
        border: false,
        //checkOnSelect: false,
        //selectOnCheck: false,
        url: '${webroot}/hw101Jcdj/f_json/findHw102JcdmxByDjId.shtml',
        queryParams: {
        	type: '${type}',
        	djDeptId: $('#id_deptId').combogrid('getValue'),
        	djId: '${hw101Jcdj.djId}'
		},
        columns:[ 
	       	[
				{field:'ck',checkbox:true,width:20},
				{field:'placeName',title:'采样场所',sortable:true,width:90},
	            {field:'sampleName',title:'采样标本',sortable:true,width:110},
	            {field:'takeByName',title:'采样人',sortable:true,width:60},
	            {field:'takeAt',title:'采样日期',sortable:true,align:'center',width:80},
	            {field:'resultFlagName',title:'结果',sortable:true,width:50},
	        ]
        ],
        rownumbers:true,
        toolbar:'#tb_sampling',
        onSelect : function(index,row){
			hygiene.editSampleData(row.id);
		},
        onLoadSuccess : function (data) {
        	if (data.rows.length > 0) {
            	$('#id_sampling_list').datagrid('selectRow', 0);
            	//hygiene.editSampleData(data.rows[0].id);
        	} else {
        		hygiene.action='add';
        	}
        }
	});
	
	//填报人
	$('#id_reportBy').combogrid({
		delay: 1000,
	    value: '${hw101Jcdj.reportBy}',
	    required: true,
        panelWidth: 240,
        panelHeight: 300,
        idField:'employeeId',
        textField:'employeeName',
        url: '${webroot}/hw010Zzry/f_json/queryList.shtml',
		columns:[
        	[
	            {field:'employeeId',title:'职工编号',sortable:true,width:100},
	            {field:'employeeName',title:'职工姓名',sortable:true,width:115}
            ]
        ],
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_reportBy');
        	hygiene.leftChange();
		}
	});
	
	//负责人
	$('#id_createBy').combogrid({
		delay: 1000,
	    value: '${hw101Jcdj.createBy}',
	    required: true,
        panelWidth: 240,
        panelHeight: 270,
        idField:'employeeId',
        textField:'employeeName',
        url: '${webroot}/hw010Zzry/f_json/queryList.shtml',
		columns:[
        	[
	            {field:'employeeId',title:'职工编号',sortable:true,width:100},
	            {field:'employeeName',title:'职工姓名',sortable:true,width:115}
            ]
        ],
        onHidePanel : function() {
        	Csm.valueValite.combogrid('id_createBy');
        	hygiene.leftChange();
		}
	});
// 	var checkOutBacteria = $('#id_checkOutBacteria').combobox('getValues');
// 	if(checkOutBacteria==""||checkOutBacteria==null){
// 		$('#id_resultFlag').combobox({disabled:true});
// 		$('#id_resultPathoNum').attr("disabled",true);
// 	}
});
</script>
</body>
</html>