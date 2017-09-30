<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>报告卡信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/monitor/reportCards.js?${version}"></script>
<script type="text/javascript">
var queryType = '${param.isSeparatePage}';
if (!queryType || queryType.length > 0) {
	reportCards.queryType = queryType;
} else {
	reportCards.queryType = '1';
}
var ticket = '${param.ticket}';
reportCards.ticket=ticket;
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="margin-right:20px; border-bottom-width:1px;">
		<div class="patient_infor" style="min-width:760px;margin: 6px 10px;">
			<table class="info_table">
				<tbody>  
			        <tr>
			            <th>姓名：</th>
			            <td style="min-width: 60px;">
			            	<c:out value="${st003Cryxxb.patientName}" />(<c:out value="${st003Cryxxb.sex}" />,<c:out value="${st003Cryxxb.age}" /><c:out value="${st003Cryxxb.ageUnit}" />)
			            </td>
			            <th>${patientZyTitle}：</th>
			            <td nowrap="nowrap">
			            	<a href="javascript:void(0);" class="underline" onclick="reportCards.patientInfo('${st003Cryxxb.zyid}');"><c:out value="${patientZyValue == 'patientId'?st003Cryxxb.patientId:st003Cryxxb.zyid}" /></a>
			            </td>
			            <th>入院日期：</th>
			            <td nowrap="nowrap">
			            	<fmt:formatDate value="${st003Cryxxb.inHospAt}" pattern="yyyy-MM-dd HH:mm" />
			            </td>
			            <th>入院诊断：</th>
			            <td style="min-width: 60px;">
			            	<c:out value="${st003Cryxxb.ryzd}" />
			            </td>
			        </tr>
			        <tr>			        	
			            <th>主管医生：</th>
			            <td>
		            		<input id="id_chargeDrId_${gr002YsgrMx.regId}" style="width:120px" value="${st003Cryxxb.chargeDrName}"/>
			            </td>
			            <th>疾病名称：</th>
			            <td>
							<input id="id_jbzd_${gr002YsgrMx.regId}" style="width:130px" />
			            </td>
			            <th>报告科室：</th>
			            <td>
			            	<input id="id_reportDeptId_${gr002YsgrMx.regId}" style="width:120px" value="${bk001Sbk.reportDeptId}"/>
			            </td>
			        	<th>报告人：</th>
			            <td>
			            	${bk001Sbk.reportDrName}
			            	<%-- <input id="id_reportDrId_${gr002YsgrMx.regId}" style="width:120px" value="${bk001Sbk.reportDrId}"/> --%>
			            </td>
			        </tr>
			        <c:if test="${!empty st003Cryxxb.outAt}">
			        <tr>
			        	<th>出院日期：</th>
			        	<td>
			        		<fmt:formatDate value="${st003Cryxxb.outAt}" pattern="yyyy-MM-dd HH:mm" />
			        	</td>
			        </tr>
			        </c:if>
			    </tbody>
			</table>
		</div>
		<form id="id_form_${gr002YsgrMx.regId}" method="post">
		<input type="hidden" name="regId" value="${gr002YsgrMx.regId}"/>
		<input type="hidden" id="id" name="id" />
		<input type="hidden" id="preYymd" name="preYymd" />
		<input type="hidden" name="zyid" value="${gr002YsgrMx.zyid}"/>
		<input type="hidden" id="id_chargeDrIdf_${gr002YsgrMx.regId}" name="chargeDrId" value="${st003Cryxxb.chargeDrId}" />
		<input type="hidden" id="id_reportDrIdf_${gr002YsgrMx.regId}" name="reportDrId" value="${bk001Sbk.reportDrId}" />
		<input type="hidden" id="id_reportDrNamef_${gr002YsgrMx.regId}" name="reportDrName" value="${bk001Sbk.reportDrName}" />
		<input type="hidden" id="id_reportDeptIdf_${gr002YsgrMx.regId}" name="reportDeptId" value="${bk001Sbk.reportDeptId}" />
		<input type="hidden" id="id_reportDeptNamef_${gr002YsgrMx.regId}" name="reportDeptName" value="${bk001Sbk.reportDeptName}" />
		<input type="hidden" id="id_jbzdf_${gr002YsgrMx.regId}" name="jbzd" />
		<input type="hidden" id="id_jbzdCodef_${gr002YsgrMx.regId}" name="jbzdCode" />
	    <div style="margin:6px 10px 0px 10px;">	
		<input type="hidden" id="id_action_${relid}" value="${action}"/>
		<input type="hidden" name="isAuth" value="${param.isAuth}"/>
		<input type="hidden" id="id_infectName_${relid}" value="${gr002YsgrMx.infectName}"/>
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">诊断信息</span>							
			</div>	
			<table class="info_table">
			<tbody>  
		        <tr>
		            <th>感染类型：</th>
		            <td>
		            	<nis:select  id="infectType" dictcode="infect_type" cssCls="easyui-combobox easyui-validatebox" name="infectType" value="${gr002YsgrMx.infectTypeId}" exp="style=\"width: 85px;\" data-options=\"editable:false,onSelect:function(r){IsUploadBefore();}\" required=\"true\"" />
		            	<%-- <select id="infectType" style="width: 85px;" class="easyui-combobox" name="infectType" data-options="editable:false,onSelect:function(r){IsUploadBefore();}">
							<option value="1" ${gr002YsgrMx.infectTypeId == 1 ? 'selected="selected"' : ''} >医院感染</option>
							<option value="2" ${gr002YsgrMx.infectTypeId == 2 ? 'selected="selected"' : ''} >社区感染</option>
						</select> --%>
		            </td>
		            <th>感染日期：</th>
		            <td>
		            	<input type="text" id="id_infectDate_${relid}" name="infectDate" value="<fmt:formatDate value="${gr002YsgrMx.startAt}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox text" style="width:80px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'id_confDate_${relid}\')}'})" />
		            </td>
		            <th>感染诊断：</th>
		            <td colspan="3" class="reportPlace">
		            	<input type="hidden" id="id_infectDiagnName_${relid}" value="${gr002YsgrMx.infectName}" name="infectDiagnName"/>
		            	<input type="text" id="id_infectDiagnId_${relid}" name="infectDiagnId" style="width:152px"/>
		            </td>
		        </tr>
		        <tr>
		        	<th>上报类型：</th>
		            <td>
		            	<select style="width: 85px;" class="easyui-combobox" name="bkType" data-options="editable:false">
							<option value="0" >正常</option>
							<option value="1" >迟报</option>
							<option value="2" >漏报</option>
						</select>
		            </td>
		            <th>确诊日期：</th>
		            <td>
		            	<input type="text" id="id_confDate_${relid}" name="confirmDt" value="<fmt:formatDate value="${gr002YsgrMx.confDate}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox text" style="width:80px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'id_infectDate_${relid}\')}'})" />
		            </td>
		            <th>感染科室：</th>
		            <td>
		            	<input type="hidden" id="id_infectDeptName_${relid}" name="infectDeptName" value="${gr002YsgrMx.infectDeptName}" />
						<input id="id_infectDept_${relid}" name="infectDeptId" class="easyui-validatebox"  style="width: 152px;" />
		            </td>
		            <th>相关插管：</th>
		            <td>
		            	<nis:select dictcode="intubate_type" id="id_relation_${relid}" cssCls="easyui-combobox" name="relation" headerKey="" headerValue="-请选择-" exp="style=\"width: 105px;\" data-options=\"editable:false\"" />
		            </td>
		        </tr>
		        <tr>
		            <th>感染转归：</th>
		            <td>
		            	<nis:select dictcode="lapse_to" cssCls="easyui-combobox" name="jbzg" headerKey="" headerValue="-请选择-" exp="style=\"width: 85px;\" data-options=\"editable:false\"" />
		            </td>
		            <th>转归日期：</th>
		            <td>
		            	<input type="text" name="jbzgDate" required="true" class="Wdate" style="width:80px;" onclick="WdatePicker();" value="<fmt:formatDate value="${st003Cryxxb.outAt}" pattern="yyyy-MM-dd" />" />
		            </td>
            		<th>手术名称：</th>
		            <td>
		            	<input type="hidden" id="id_opration_id_${relid}" name="operId" value="${param.operId}" />
						<input type="hidden" id="id_opration_relid_${relid}" name="opeRelid" value="${param.relid}" />
						<div class="text_del">
						<c:if test="${ISOPEN != 1}">
							<input type="text" id="id_opration_name_${relid}" name="opeName" value="${operName}" readonly="readonly" style="width: 140px;" ${canSelOprat ? '' : 'disabled="disabled"'} onclick="reportCards.selOpration('${relid}','${st003Cryxxb.zyid}','${st003Cryxxb.patientId}');"/>
						</c:if>
						<c:if test="${ISOPEN == 1}">
							<input type="text" id="id_opration_name_${relid}" name="opeName" value="${operName}" readonly="readonly" style="width: 140px;"  onclick="reportCards.selOpration('${relid}','${st003Cryxxb.zyid}','${st003Cryxxb.patientId}');"/>
						</c:if>
							<a href="javascript:void(0)" class="select_img_del" title="清除" onclick="reportCards.delOpration('${relid}');"></a>
						</div>
		            </td>
            		<th>切口类型：</th>
		            <td>
		            <c:if test='${ISOPEN != 1}'>
		            	<nis:select id="id_memo_${relid}" dictcode="incision_type" cssCls="easyui-combobox" name="memo" headerKey="" headerValue="-请选择-" value="${incisionGrade}" exp="style=\"width: 105px;\" ${canSelOprat ? '' : 'disabled=\"disabled\"'} data-options=\"editable:false\"" />
		            </c:if>
		            <c:if test='${ISOPEN == 1}'>
		            	<nis:select id="id_memo_${relid}" dictcode="incision_type" cssCls="easyui-combobox" name="memo" headerKey="" headerValue="-请选择-" value="${incisionGrade}" exp="style=\"width: 105px;\"  data-options=\"editable:false\"" />
		            </c:if>
		            </td>
		        </tr>
		    </tbody>
			</table>
		</div>	
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">易感因素</span>
				<div class="card_c_h_btn">
					<a href="javascript:void(0)" onclick="reportCards.addEasyFactors('${relid}', '${param.index}');" class="btn_icon" title="添加"><i class="icon iconfont fax">&#xe665;</i></a>
				</div>
			</div>											
			<div class="gr_label" id="id_ygys_${relid}">				
			</div>
		</div>
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">致病菌</span>
				<span class="red" id="tips" style="text-align: center;"></span>
			</div>
			<div class="byt_table" id="id_samples_table_${relid}">
				<div id="id_samples_${relid}"></div>
			</div>
		</div>
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">抗菌药物使用</span>
			</div>
			<div class="byt_table">
				<div id="id_kjywsy_${relid}"></div>
			</div>
		</div>
		<c:if test="${ISCGSY != 0}">
			<div class="card_cont">
				<div class="card_cont_h">
					<span class="card_c_h_text">侵入性操作</span>
				</div>
				<div class="byt_table">
					<div id="id_cgsy_${relid}"></div>
				</div>
			</div>
		</c:if>
		<!-- 历史报卡明细 -->
		<div id="id_lsbkmx">
		</div>
    </div>
	    </form>
	</div>
	<div data-options="region:'south',border:false" style="height:46px;">
		<div class="footer dialog_footer">			
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:void(0);" id="id_submit" onclick="hygiene.validate();" class="no_ico"><span>上报</span></a>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
var hygiene = {
	isZd : "0",
	isYgys : '${isYgys}',
	isIncision : '${isIncision}',
	pInfectCode : '',
	validate : function (){
		$('#id_jbzdf_${gr002YsgrMx.regId}').val($('#id_jbzd_${gr002YsgrMx.regId}').combogrid('getText'));
		if(hygiene.isZd=="1"){
			if('${isSb}'==1){
				$.messager.show({title : '提示',msg : '此感染之前已报过！不允许再次上报！'});
				return false;
			}
        }

		//北大深圳医院需求处理start
		var infectCode = $('#id_infectDiagnId_${relid}').combotree('getValue');
		if ('${hospName}' === '北大深圳医院' && $.inArray(infectCode, ['PNU4', 'CAUTI', 'CLBSI']) != -1) {
			var relation = $('#id_relation_${relid}').combotree('getValue');
			if (relation.length == 0) {
				$.messager.show({title : '提示',msg : '请选择相关插管！'});
				return false;
			}
		}
		//北大深圳医院需求处理end
		if (hygiene.isYgys === '1' && $('#id_ygys_${relid}').children('span').length == 0 && $('#infectType').val()==1) {
			$.messager.show({title : '提示',msg : '请选择易感因素！'});
			return false;
		}
		//北大深圳医院需求处理end
		if(hygiene.isIncision === '1' && '${ISOPEN}'!= 1 && $('#id_memo_${relid}').combobox('getValue')=='' && !$('#id_opration_name_${relid}').attr("disabled")){
			 $.messager.show({title : '提示',msg : '手术类感染切口必填！'});
			 return false;
		}
		//判断致病菌和抗菌药物是否勾选
		var tips = '';
		if($("#id_samples_${relid}").datagrid('getRows').length > 0){
			if ($('#id_samples_table_${relid}').find('.checkbox_list:checked').length == 0) {
				tips += '致病菌';
			}
		}
		
		if('${KJYWTS}' == 0){
			if ($('#id_kjywsy_${relid}').datagrid('getRows').length > 0) {
				if ($('#id_kjywsy_${relid}').datagrid('getChecked').length == 0) {
					if (tips.length > 0) {
						tips += '、抗菌药物';
					} else {
						tips += '抗菌药物';
					}
				}
			}
		}
		if (tips.length > 0) {
			$.messager.confirm('提示', '<font color="red">' + tips +  '未勾选，</font>确认继续上报?', function (r) {
	        	if (r) {
	        		if(hygiene.pInfectCode=='SSI'){
	        			if(!$("#id_opration_name_${relid}").val()){
	        				if("${NBISR}"==1){
	        					$.messager.alert('提示', '该感染诊断属于<font color="red">手术部位感染 </font>，未选择手术，无法上报！');
	        				}else{
			        			$.messager.confirm('提示', '该感染诊断属于<font color="red">手术部位感染 </font>，未选择手术，确认继续上报?', function (r) {
			        	        	if (r) {
			        					$('#id_form_${gr002YsgrMx.regId}').submit();
			        	        	}
			        	    	});
	        				}
	        			}else{
	        				$('#id_form_${gr002YsgrMx.regId}').submit();
	        			}
	        		}else{
	 					$('#id_form_${gr002YsgrMx.regId}').submit();
	        		}
	        	}
	    	});
		} else {
			if(hygiene.pInfectCode=='SSI'){
				if(!$("#id_opration_name_${relid}").val()){
					if("${NBISR}"==1){
						$.messager.alert('提示', '该感染诊断属于<font color="red">手术部位感染 </font>，未选择手术，无法上报！');
					}else{
	        			$.messager.confirm('提示', '该感染诊断属于<font color="red">手术部位感染 </font>，未选择手术，确认继续上报?', function (r) {
	        	        	if (r) {
	        					$('#id_form_${gr002YsgrMx.regId}').submit();
	        	        	}
	        	    	});
					}
				}else{
    				$('#id_form_${gr002YsgrMx.regId}').submit();
    			}
    		}else{
 				$('#id_form_${gr002YsgrMx.regId}').submit();
    		}
		}
		
	},
	showMdr:function(testOrderNo,pathogenSn){
		 Comm.dialogGlobal({
	        	url:"${webroot}/xn011Dclymx/f_view/mdrDetail.shtml?testOrderNo="+testOrderNo+"&pathogenSn="+pathogenSn,
	            title: "多耐详情",
	            width:500,
	            height:360,
	            type:"iframe",
	            parent:this
	        });
   },
};
	$(document).ready(function () {
		window.setTimeout(function(){
			Comm.form({
				id: 'id_form_${gr002YsgrMx.regId}',
				url: '${webroot}/bk001Sbk/f_json/addReportCard.shtml',
				subbtn: 'id_submit',
				onLoading : function () {
					$.messager.progress({
						text : '正在提交中....',
						interval : 200
					});
				},
				success : function(json) {
					if (json.result === 'success') {
						if('${testOrderNo}'!=''){
							$.ajax({
			                    url: '${webroot}/xn011Dclymx/f_json/update.shtml',
			                    type: 'post',
			                    data: { 
			                    	zyid: '${gr002YsgrMx.zyid}',
			                    	normItemId:'${param.normItemId}',
			                    	normOrderno:'${param.normOrderno}',
			                    	testOrderNo:'${testOrderNo}',
			                    	resProp:'${param.resprop}',
			                    	orderno:'${param.orderno}',
			                    	surveyDeptId:'${param.surveyDeptId}',
			                    	infectTypeId:'${gr002YsgrMx.infectTypeId}',
			                    	audited:1
			                    },
			                    dataType: 'json',
			                    success : function(data) {	
									alert("操作成功！");
									//$.messager.show({ title: '提示', msg: '操作成功！' });
			                		reportCards.toEdit(json.data);         
								}
			        		}); 
						}else{
							$.messager.progress('close');
	    					parent.$.messager.show({title : '提示',msg :'上报成功！'});
							//$.messager.show({ title: '提示', msg: '上报成功！' });
	                		reportCards.toEdit(json.data);
						}
    				} else {
    					$.messager.progress('close');
						alert(json.msg);
    					//parent.$.messager.show({title : '提示',msg : json.msg});
    				}
				}
			});
		},100);
		
		//主管医生
		Csm.combogrid.doctor({
			id: 'id_chargeDrId_${gr002YsgrMx.regId}',
			value: '${st003Cryxxb.chargeDrId}',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
			required:true,
	        onHidePanel : function() {
	        	Csm.valueValite.combogrid('id_chargeDrId_${gr002YsgrMx.regId}');
			},
			onClickRow : function(index, row){
				$('#id_chargeDrIdf_${gr002YsgrMx.regId}').val(row.employeeId);
			}
		});
		
		//报告人
		/* Csm.combogrid.doctor({
			id: 'id_reportDrId_${gr002YsgrMx.regId}',
			value: '${bk001Sbk.reportDrId}',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
			required:true,
	        onHidePanel : function() {
	        	Csm.valueValite.combogrid('id_reportDrId_${gr002YsgrMx.regId}');
			},
			onClickRow : function(index, row){
				$('#id_reportDrIdf_${gr002YsgrMx.regId}').val(row.employeeId);
				$('#id_reportDrNamef_${gr002YsgrMx.regId}').val(row.employeeName);
			}
		}); */
		
		//报告科室
		Csm.combogrid.dep({
			id: 'id_reportDeptId_${gr002YsgrMx.regId}',
			value: '${bk001Sbk.reportDeptId}',
			ifcaseoffice: '1',
			required:true,
			onClickRow : function(index,row){
				$('#id_reportDeptIdf_${gr002YsgrMx.regId}').val(row.deptId);
				$('#id_reportDeptNamef_${gr002YsgrMx.regId}').val(row.deptName);
			}
		});
		
		//疾病
		Csm.combogrid.icd10({
			id: 'id_jbzd_${gr002YsgrMx.regId}',
			value: '${bk001Sbk.jbzdCode}',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
	        onHidePanel : function() {
	        	//Csm.valueValite.combogrid('id_jbzd_${gr002YsgrMx.regId}');
			},
			onClickRow : function(index, row){
				$('#id_jbzdf_${gr002YsgrMx.regId}').val(row.icdName);
				$('#id_jbzdCodef_${gr002YsgrMx.regId}').val(row.icdCode);
			}
		});
		
		//感染科室
		Csm.combogrid.dep({
			id: 'id_infectDept_${relid}',
			value: '${gr002YsgrMx.infectDeptId}',
			ifcaseoffice: '1',
			required:true,
			onClickRow : function(index,row){
				$('#id_infectDeptName_${relid}').val(row.deptName);
			}
		});
		
		//感染诊断
		$('#id_infectDiagnId_${relid}').combotree({
			url:'${webroot}/yygrzd/f_json/findYygrzdList.shtml?flag=1&infectCode=${bk002Grzd.infectDiagnId}',
			method:'get',
			required:true,
			panelHeight:300,
			panelWidth:250,
			value:'${gr002YsgrMx.infectCode}',
			onBeforeSelect: function(node) {
	            if (!$(this).tree('isLeaf', node.target)) {
	                return false;
	            }
	        },
	        onClick: function(node) {
	        	if (!$(this).tree('isLeaf', node.target)) {
	        		$(this).tree('toggle',node.target);
	                $('#id_infectDiagnId_${relid}').combo('showPanel');
	            } else {
	            	hygiene.pInfectCode=node.pInfectCode;
	            	$.ajax({
	                    url: '${webroot}/bk002Grzd/f_json/canSelOprat.shtml',
	                    type: 'post',
	                    data: { infectCode: node.id },
	                    dataType: 'json',
	                    success : function(json) {
	                    	if('${ISOPEN}' == 0){
		                    	if (parseInt(json) > 0) {
		                    		$('#id_opration_name_${relid}').attr("disabled",false);
		                    		$('#id_memo_${relid}').combobox("enable");
		                    	}else if('POP'== node.id){
		                    		$('#id_opration_name_${relid}').attr("disabled",false);
		                    		$('#id_memo_${relid}').combobox("enable");
		                    	}else {
		                    		$('#id_opration_id_${relid}').val('');
		                    		$('#id_opration_relid_${relid}').val('');
		                    		$('#id_opration_name_${relid}').val('');
		                    		$('#id_opration_name_${relid}').attr("disabled",true);
		                    		$('#id_memo_${relid}').combobox("clear");
		                    		$('#id_memo_${relid}').combobox("disable");
		                    	}
	                    	}else{
	                    		$('#id_opration_name_${relid}').attr("disabled",false);
	                    		$('#id_memo_${relid}').combobox("enable");
	                    	}
	    				}
	        		});
	            	$('#id_infectDiagnName_${relid}').val(node.text);
	            	
	            	//验证是否已上报
	            	IsUploadBefore();         	
	            	//北大深圳医院需求处理start
	            	//PNU4-呼吸机相关性肺炎（VAP）   CAUTI-导尿管相关尿路感染（CAUTI）  CLBSI-中心静脉导管相关血流感染（CLBSI）
	            	if ('PNU4' === node.id) {
	            		$('#id_relation_${relid}').combobox("enable");
	            		$('#id_relation_${relid}').combobox('setValue', '03');
	            		//$('#id_relation_${relid}').validatebox({ required: true });
	            	} else if ('CAUTI' === node.id) {
	            		$('#id_relation_${relid}').combobox("enable");
	            		$('#id_relation_${relid}').combobox('setValue', '02');
	            		//$('#id_relation_${relid}').validatebox({ required: true });
	            	} else if ('CLBSI' === node.id) {
	            		$('#id_relation_${relid}').combobox("enable");
	            		$('#id_relation_${relid}').combobox('setValue', '01');
	            		//$('#id_relation_${relid}').validatebox({ required: true });
	            	} else {
	            		if('${hospName}' === '北大深圳医院'){
	            			$('#id_relation_${relid}').combobox('setValue', '');
	            			$('#id_relation_${relid}').combobox("disable");	
	            		}
	            		//$('#id_relation_${relid}').validatebox({ required: false });
	            		//$('#id_relation_${relid}').removeClass("validatebox-invalid");
	            	}
	            	//北大深圳医院需求处理end
	            }
	        }
		});
		
		$('#id_samples_${relid}').datagrid({
            fit: false,
            nowrap: true,
            autoRowHeight: true,
            striped: true,
            url:'${webroot}/st009Sjbb/f_json/findSjbbForInfectList.shtml?refid=${bk002Grzd.relid}&zyid=${gr002YsgrMx.zyid}&infectPartId=${gr002YsgrMx.infectCode}&testOrderNo=${testOrderNo}',
            remoteSort: false,
            singleSelect: true,
            columns:[
            	[
					{field:'testOrderNos',align:'center',width:25,
						formatter:function(value,rec){
							return ['<input class="checkbox_list" name="testOrderNos" title="'+rec.pathoName+'" data="' + rec.testOrderNo + '" type="checkbox" ' + (rec.refid != null ? 'checked="checked"' : '') + ' value="' + rec.testOrderNo + '" onclick="pathogens.checkTestOrderNo(this, \'${relid}\',\'' + rec.testOrderNo + '\',\'' + rec.pathoName + '\');" />'].join('');
					    }
					},
                 	{field:'submiAt',title:'送检时间',sortable:true,width:80},
                    {field:'itemName',title:'标本名称',sortable:true,width:100},  
                    {field:'testDate',title:'检出时间',sortable:true,width:80},
                    {field:'pathoName',title:'检出细菌',sortable:true,width:120},
                    {field:'resPropName',title:'耐药情况',sortable:true,width:70,
                    	formatter:function(value,r){
							return ['<a href="javascript:hygiene.showMdr(\''+r.testOrderNo+'\',\''+r.pathogenSn+'\');">'+r.resPropName+'</a>'].join('');
						}
                    },
                    {field:'specDescribes',title:'特殊耐药',sortable:true,width:80},
                    {field:'testOrderNo',title:'检验单号',sortable:true,width:170}
                ]
            ],
            pagination:false,
            rownumbers:false
        });
		
		$('#id_kjywsy_${relid}').datagrid({
		    fit: false,
		    nowrap: true,
		    autoRowHeight: true,
		    striped: true,
		    fitColumns: true,
		    height:'200px',
		    url:'${webroot}/gr016BkKjyw/f_json/queryAad.shtml',
		    remoteSort: false,
		    singleSelect: true,
		    border: true,
            queryParams: {
// 		    	refid:'${refid}',
		    	zyid : '${gr002YsgrMx.zyid}' 
            },
		    columns:[ 
			    [
				{field:'id',align:'center',width:25,
					formatter:function(value,rec){
						return ['<input class="checkbox_list" name="yzIds" value="'+rec.id+'" title="'+rec.id+'" data="' + rec.id + '" type="checkbox" ' + '  />'
						].join('');
				    }
				},
			    {field:'orderName',title:'抗菌药物',sortable:true ,
					formatter:function(value,rec,index){
						return ['<input type="hidden" name="gr16List[' + index + '].yzId" value="' + rec.id + '"/>' + 
						        '<input type="hidden" id="id_drugSituate_2_' + rec.yzId + '" name="gr16List[' + index + 	                                         '].isselect" value="' + (rec.isselect == null ? 0 : rec.isselect) + '"/>' + 
						        rec.orderName].join('');
					}
				},
			    {field:'orderAt',title:'开嘱时间',sortable:true,align:'center',width:110},
			    {field:'stopAt',title:'停嘱时间',sortable:true,align:'center',width:110},
			    {field:'days',title:'用药天数',sortable:true,align:'center',width:70},
			    {field:'adminRouteName',title:'给药路径',sortable:true,align:'center',width:70},
			    {field:'dosage',title:'剂量',sortable:true,align:'center',width:70},
			    {field:'sypc',title:'频次',sortable:true,align:'center',width:70},
			    {field:'preYymd',title:'用药目的',width:120,align:'center',
				    formatter:function(value,row,index){
	                    return reportCards.setPreYymdList(value, index, 'gr16List','${yymd}');
				    }},
// 			    {field:'_operate',title:'操作',width:40,
// 						formatter : function(value,row){
// 							return ['<a href="javascript:reportCards.del(\'',row.refid+ '\', \'' +row.relid,'\');" class="ico_del" title="删除"></a>'].join('');
// 						}
//               	}
			    ]
		    ],		    
// 		    rownumbers:true
	    });
		$('#id_cgsy_${relid}').datagrid({
			 fit: false,
	         nowrap: true,
	         autoRowHeight: true,
	         striped: true,
		    url:'${webroot}/st004BkCgxx/f_json/querycgsy.shtml',
		    remoteSort: false,
		    fitColumns: true,
	        singleSelect: true,
	        pagination:false,
	        rownumbers:false,
	        height:'200px',
	        queryParams: {
		    	zyid : '${gr002YsgrMx.zyid}' 
	        },
		    columns:[ 
			    [
				{field:'id',align:'center',width:25,
					formatter:function(value,rec){
						return ['<input class="checkbox_list" name="yzIdList" value="'+rec.id+'" title="'+rec.id+'" data="' + rec.id + '" type="checkbox" ' + '  />'
						].join('');
				    }
				},
			    {field:'orderName',title:'侵入性操作名称',sortable:true, width:200},
			    {field:'orderAt',title:'开嘱时间',sortable:true,align:'center', width:200},
			    {field:'stopAt',title:'停嘱时间',sortable:true,align:'center', width:200}
			    ]
		    ]		    
	    });
		// 历史报卡明细
		$.ajax({
			url : '${webroot}/bk001Sbk/f_json/getReportInfectListByZyid.shtml',
			type : 'post',
			data : { zyid: '${gr002YsgrMx.zyid}'},
			dataType : 'json',
			success : function(json){
				var htmlStr = '',
					rows = '',
					tempStr1 = '',
					tempStr2 = '',
					isHave = false;
				for(var i = 0; i < json.length; i++){
					isHave = true;
					var bk001Sbk = json[i];
					tempStr1 = '<td class="omit" title="' + bk001Sbk.infectTypeName + '">' + bk001Sbk.infectTypeName + '</td><td class="omit" title="' + bk001Sbk.startAt.substring(0,10) + '">' + bk001Sbk.startAt.substring(0,10) + '</td><td class="omit" title="' + bk001Sbk.infectDeptName + '">' + bk001Sbk.infectDeptName + '</td><td class="omit" title="'+ bk001Sbk.infectName + '">'+ bk001Sbk.infectName + '</td><td class="omit" title="'+ bk001Sbk.reportDrName + '">'+ bk001Sbk.reportDrName + '</td><td class="omit" title="'+ bk001Sbk.reportDeptName + '">'+ bk001Sbk.reportDeptName + '</td>';
					if(bk001Sbk.ygysCount == 0){
						tempStr2 += '<td></td>';
					}else{
						tempStr2 += '<td class="omit" title="' + bk001Sbk.ygysArr + '" >'+ bk001Sbk.ygysArr + '</td>';
					}
					if(bk001Sbk.opeName == ''){
						tempStr2 += '<td></td>';
					}else{
						tempStr2 += '<td class="omit" title="' + bk001Sbk.opeName + '" >'+ bk001Sbk.opeName +'</td>';
					}
					if(bk001Sbk.zbjCount == 0){
						tempStr2 += '<td></td>';
					}else{
						tempStr2 += '<td class="omit" title="' + bk001Sbk.zbjArr + '" >' + bk001Sbk.zbjArr + '</td>';
					}
					rows += '<tr>' + tempStr1 + tempStr2 +'</tr>';
					tempStr1 = '';
					tempStr2 = '';
				}
				htmlStr = '<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">历史报卡明细</span></div><div class="byt_table"><table class="stand_table" style="width: 100%; table-layout: fixed;"><thead><tr><th>感染类型</th><th>感染日期</th><th>感染科室</th><th>感染诊断</th><th>上报医生</th><th>上报科室</th><th>易感因素</th><th>感染手术名称</th><th>致病菌</th></tr></thead><tbody>' + rows + '</tbody></table></div></div>';
				if(isHave){
					$('#id_lsbkmx').append(htmlStr);
				}else{
					$('#id_lsbkmx').append('<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">历史报卡明细</span></div><div class="byt_table">无</div></div>');
				}
			}
		});
	});
	
	
	
	
	function IsUploadBefore(){
		var nodeId = $('#id_infectDiagnId_${relid}').combotree("getValue");
		var zyid = "${st003Cryxxb.zyid}";
		var ift = $("#infectType").combobox("getValue");
		if(nodeId && zyid && ift){
			$.ajax({
				url:"${webroot}/bk001Sbk/f_json/isReportBefore.shtml",
				data:{
					"idi":nodeId,
					"zyid":zyid,
					"ift":ift
				},
				success:function(data){
					data = eval("("+data+")");
					if(data.count>0){
						$("#tip").remove();
						hygiene.isZd="1";
						$(".reportPlace").append("<span id='tip' style='color:#ef6b3a'>！此感染之前已报过 &nbsp;&nbsp;<span style='cursor: pointer;' onclick=\"checkDetail('"+data.bk2relid+"');\">查看</span></span>");
					}else if(data.count==0){
						$("#tip").remove();
						hygiene.isZd="0";
					}else if(data.count<0){
						alert("验证‘感染诊断是否已上报’失败！");
					}
				},error:function(){
					alert("抱歉！");
				}
			});
		}
	}
	function checkDetail(bk2Relid){
		if(parent.menuInfo){
			parent.menuInfo.clickMenu('报卡详情查看','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&action=edit&bk2Relid='+bk2Relid,true);
		}else{
			window.location.href = '${webroot}/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&action=edit&bk2Relid='+bk2Relid;
		}
	}

</script>
</body>
</html>