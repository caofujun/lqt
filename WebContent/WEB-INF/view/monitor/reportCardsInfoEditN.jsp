<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>报告卡信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<!-- 以单独页面加载时才启用以下js -->
<c:if test="${param.isSeparatePage eq 1}">
<script type="text/javascript" src="${webroot}/resources/js/monitor/reportCards.js?${version}"></script>
<script type="text/javascript">
var queryType = '${param.isSeparatePage}';
if (!queryType || queryType.length > 0) {
	reportCards.queryType = queryType;
} else {
	reportCards.queryType = '1';
}
</script>
</c:if>
<body class="easyui-layout">
<div class="easyui-layout" style="width:100%;height:100%;">
	<div data-options="region:'center',border:false" style="margin-right:20px; border-bottom-width:1px;">
		<div class="h_title">
			<span style="margin-left:15px;"><b>上报医生：</b><c:out value="${bk001Sbk.reportDrName}" /></span>
			<span style="margin-left:20px;"><b>报告科室：</b><c:out value="${bk001Sbk.reportDeptName}" /></span>
			<span style="margin-left:20px;"><b>报告日期：</b><fmt:formatDate value="${bk001Sbk.reportAt}" pattern="yyyy-MM-dd HH:mm" /></span>
			<c:if test="${bk002Grzd.authStatus ne 0}">
				<span style="margin-left:20px;"><b>审核人：</b><c:out value="${bk002Grzd.authUsername}" />（<fmt:formatDate value="${bk002Grzd.authAt}" pattern="yyyy-MM-dd HH:mm" />）</span>
			</c:if>
			<c:if test="${bk002Grzd.isPrint eq '1'}">
				<span class="mark">已打印</span>
			</c:if>
			<span style="display: inline-block;float: right;">
				<c:if test="${ysbcurl!='0'}">
				<a href="javascript:void(0)" onclick="reportCards.ysbc('${ysbcurl}')"  class="a_icon_c green" title="原始病程" ><i class="icon nisfont">&#xe624;</i></a>
            	</c:if>
            	<c:if test="${canAudit}">
				<a href="javascript:void(0)" class="a_icon_c" onclick="reportCards.toAudit('${bk001Sbk.relid}');" title="审核"><i class="icon iconfont">&#xe607;</i></a>
				</c:if>
				<a href="#" class="a_icon_c red" onclick="reportCards.exitEdit('${bk001Sbk.relid}');" title="退出"><i class="icon iconfont">&#xe62f;</i></a>						
				<c:if test="${canDelete}">
				<a href="javascript:void(0)" class="a_icon_c red" onclick="reportCards.toDelete('${bk001Sbk.relid}', '${bk001Sbk.zyid}');" title="删除"><i class="icon iconfont fax">&#xe62b;</i></a> 
				</c:if>
				<a href="javascript:void(0)" class="a_icon_c blue" onclick="reportCards.printReportInfo('${bk001Sbk.relid}', '${bk001Sbk.zyid}', '${bk001Sbk.patientName}','${bk002Grzd.relid}');" title="打印"><i class="icon iconfont">&#xe604;</i></a>			
			</span>
		</div>
		<div class="patient_infor" style="min-width:760px;margin: 6px 10px;">
			<table class="info_table">
				<tbody>					        
			        <tr>
			        	<th>姓名：</th>
			            <td nowrap="nowrap">
			            	<c:out value="${bk001Sbk.patientName}" />(<c:out value="${bk001Sbk.sex}" />,<c:out value="${bk001Sbk.age}" /><c:out value="${bk001Sbk.ageUnit}" />)
			            </td>
			        	<th>${patientZyTitle}：</th>
			            <td nowrap="nowrap">
			            	<a href="javascript:void(0);" class="underline" onclick="reportCards.patientInfo('${bk001Sbk.zyid}');"><c:out value="${patientZyValue == 'patientId'?bk001Sbk.patientId:bk001Sbk.zyid}" /></a>
			            </td>
			            <th>入院日期：</th>
			            <td nowrap="nowrap">
			            	<fmt:formatDate value="${bk001Sbk.inHospAt}" pattern="yyyy-MM-dd HH:mm" />
			            </td>
			        	<th>入院诊断：</th>
			            <td style="min-width: 60px;">
			            	<c:out value="${bk001Sbk.ryzd}" />
			            </td>
			        </tr>
			        <tr>		        	
			        	<th>主管医生：</th>
			            <td>
		            		<input id="id_chargeDrId_${bk001Sbk.relid}" style="width:120px" />
			            </td>
			            <th>疾病名称：</th>
			            <td>
							<input id="id_jbzd_${bk001Sbk.relid}" style="width:130px" />
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
			        <c:if test="${!empty bk001Sbk.outAt}">
			        <tr>
			        	<th>出院日期：</th>
			        	<td>
			        		<fmt:formatDate value="${bk001Sbk.outAt}" pattern="yyyy-MM-dd HH:mm" />
			        	</td>
			        </tr>
			        </c:if>
			    </tbody>
			</table>
		</div>
		<form id="id_form_${bk001Sbk.relid}" method="post">
		<input type="hidden" name="refid" value="${bk001Sbk.relid}" />
		<input type="hidden" id="id_chargeDrIdf_${bk001Sbk.relid}" name="chargeDrId" value="${bk001Sbk.chargeDrId}" />
		<input type="hidden" id="id_reportDrIdf_${gr002YsgrMx.regId}" name="reportDrId" value="${bk001Sbk.reportDrId}" />
		<input type="hidden" id="id_reportDrNamef_${gr002YsgrMx.regId}" name="reportDrName" value="${bk001Sbk.reportDrName}" />
		<input type="hidden" id="id_reportDeptIdf_${gr002YsgrMx.regId}" name="reportDeptId" value="${bk001Sbk.reportDeptId}" />
		<input type="hidden" id="id_reportDeptNamef_${gr002YsgrMx.regId}" name="reportDeptName" value="${bk001Sbk.reportDeptName}" />
		<input type="hidden" id="id_jbzdf_${bk001Sbk.relid}" name="jbzd" value="${bk001Sbk.jbzd}" />
		<input type="hidden" name="isAuth" value="${param.isAuth}"/>
		<input type="hidden" id="id_jbzdCodef_${bk001Sbk.relid}" name="jbzdCode" value="${bk001Sbk.jbzdCode}" />
		<div id="id_grbw_${bk002Grzd.relid}" style="margin:6px 10px 0px 10px;">	
			<input type="hidden" id="id_action_${bk002Grzd.relid}" name="action" value="${action}"/>
			<input type="hidden" name="relid" value="${bk002Grzd.relid}"/>
			<input type="hidden" id="id_infectName_${bk002Grzd.relid}" value="${bk002Grzd.infectDiagnName}"/>
			<div class="card_cont">
				<div class="card_cont_h">
					<span class="card_c_h_text">诊断信息</span>							
				</div>	
				<table class="info_table">
				<tbody>  
			        <tr>
			            <th>感染类型：</th>
			            <td>
			            	<nis:select id="infectType"  dictcode="infect_type" cssCls="easyui-combobox" name="infectType" value="${bk002Grzd.infectType}" exp="style=\"width: 85px;\" data-options=\"editable:false,readonly:${bk002Grzd.authStatus ne 0 ? true : false},onSelect:function(r){IsUploadBefore();}\"" />
			            	<%-- <select  id="infectType" style="width: 85px;" class="easyui-combobox" name="infectType" data-options="editable:false,readonly:${bk002Grzd.authStatus ne 0 ? false : false},onSelect:function(r){IsUploadBefore();}" >
								<option value="1" ${bk002Grzd.infectType == 1 ? 'selected="selected"' : ''} >医院感染</option>
								<option value="2" ${bk002Grzd.infectType == 2 ? 'selected="selected"' : ''} >社区感染</option>
							</select> --%>
			            </td>
			            <th>感染日期：</th>
			            <td>
			            	<c:choose>
			            		<c:when test="${bk002Grzd.authStatus ne 0}">
			            			<input type="text" id="id_infectDate_${bk002Grzd.relid}" name="infectDate" value="<fmt:formatDate value="${bk002Grzd.infectDate}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox text" style="width:80px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'id_confDate_${bk002Grzd.relid}\')}'})"  />
    							</c:when>
    							<c:otherwise>
    								<input type="text" id="id_infectDate_${bk002Grzd.relid}" name="infectDate" value="<fmt:formatDate value="${bk002Grzd.infectDate}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox text" style="width:80px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'id_confDate_${bk002Grzd.relid}\')}'})" />
    							</c:otherwise>
			            	</c:choose>
			            </td>
			            <th>感染诊断：</th>
			            <td class="reportPlace">
			            	<input type="hidden" id="id_infectDiagnName_${bk002Grzd.relid}" value="${bk002Grzd.infectDiagnName}" name="infectDiagnName"/>
			            	<input type="text" id="id_infectDiagnId_${bk002Grzd.relid}" name="infectDiagnId" style="width:152px"/>
			            </td>
			            <th>审核人：</th>
			            <td>
			            	<c:out value="${bk002Grzd.authUsername}" />
			            </td>
			        </tr>
			        <tr>
			        	<th>上报类型：</th>
			            <td>
			            	<select style="width: 85px;"  class="easyui-combobox" name="bkType" data-options="editable:false">
								<option value="0" ${bk002Grzd.bkType == 0 ? 'selected="selected"' : ''} >正常</option>
								<option value="1" ${bk002Grzd.bkType == 1 ? 'selected="selected"' : ''} >迟报</option>
								<option value="2" ${bk002Grzd.bkType == 2 ? 'selected="selected"' : ''} >漏报</option>
							</select>
			            </td>
			            <th>确诊日期：</th>
			            <td>
			            	<c:choose>
			            		<c:when test="${bk002Grzd.authStatus ne 0}">
			            			<input type="text" id="id_confDate_${bk002Grzd.relid}" name="confirmDt" value="<fmt:formatDate value="${bk002Grzd.confirmDt}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox text" style="width:80px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'id_infectDate_${bk002Grzd.relid}\')}'})"  />
    							</c:when>
    							<c:otherwise>
    								<input type="text" id="id_confDate_${bk002Grzd.relid}" name="confirmDt" value="<fmt:formatDate value="${bk002Grzd.confirmDt}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox text" style="width:80px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'id_infectDate_${bk002Grzd.relid}\')}'})" />
    							</c:otherwise>
			            	</c:choose>
			            </td>
			            <th>感染科室：</th>
			            <td>
			            	<input type="hidden" id="id_infectDeptName_${bk002Grzd.relid}" name="infectDeptName" value="${bk002Grzd.infectDeptName}" />
							<input id="id_infectDept_${bk002Grzd.relid}" name="infectDeptId" class="easyui-validatebox" style="width: 152px;" />
			            </td>
			            <th>相关插管：</th>
			            <td>
			            	<nis:select dictcode="intubate_type" id="id_relation_${bk002Grzd.relid}" cssCls="easyui-combobox" name="relation" value="${bk002Grzd.relationCode}" headerKey="" headerValue="-请选择-" exp="style=\"width: 105px;\" data-options=\"editable:false\"" />
			            </td>
			        </tr>
			        <tr>
			            <th>感染转归：</th>
			            <td>
			            	<nis:select dictcode="lapse_to" cssCls="easyui-combobox" name="jbzg" value="${bk002Grzd.jbzg}" headerKey="" headerValue="-请选择-" exp="style=\"width: 85px;\", data-options=\"editable:false,readonly:${bk002Grzd.authStatus ne 0 ? false : false}\" " />
			            </td>
			            <th>转归日期：</th>
			            <td>
			            	<input type="text" name="jbzgDate" value="<fmt:formatDate value="${bk002Grzd.jbzgDate}" pattern="yyyy-MM-dd" />" ${bk002Grzd.authStatus ne 0 ? 'onclick="WdatePicker();"' : 'onclick="WdatePicker();"'} class="Wdate" style="width:80px;" />
			            </td>
	            		<th>手术名称：</th>
			            <td>
			            	<input type="hidden" id="id_opration_id_${bk002Grzd.relid}" name="operId" value="${bk002Grzd.operId}"/>
							<input type="hidden" id="id_opration_relid_${bk002Grzd.relid}" name="opeRelid" value="${bk002Grzd.opeRelid}"/>
							<div class="text_del">
								<c:if test="${ISOPEN != 1}">
									<input type="text" id="id_opration_name_${bk002Grzd.relid}" name="opeName" value="${bk002Grzd.opeName}" readonly="readonly" style="width: 140px;" ${canSelOprat ? '' : 'disabled="disabled"'} onclick="reportCards.selOpration('${bk002Grzd.relid}','${bk001Sbk.zyid}','${bk001Sbk.patientId}');" />
								</c:if>
								<c:if test="${ISOPEN == 1}">
									<input type="text" id="id_opration_name_${bk002Grzd.relid}" name="opeName" value="${bk002Grzd.opeName}" readonly="readonly" style="width: 140px;" onclick="reportCards.selOpration('${bk002Grzd.relid}','${bk001Sbk.zyid}','${bk001Sbk.patientId}');" />
								</c:if>
								<a href="javascript:void(0)" class="select_img_del" title="清除" onclick="reportCards.delOpration('${bk002Grzd.relid}');"></a>
							</div>
			            </td>
	            		<th>切口类型：</th>
			            <td>
			            	<c:if test='${ISOPEN != 1}'>
			            		<nis:select id="id_memo_${bk002Grzd.relid}" dictcode="incision_type" cssCls="easyui-combobox" name="memo" value="${bk002Grzd.memo}" headerKey="" headerValue="-请选择-" exp="style=\"width: 105px;\" ${canSelOprat ? '' : 'disabled=\"disabled\"'} data-options=\"editable:false\"" />
			            	</c:if>
			            	<c:if test='${ISOPEN == 1}'>
			            		<nis:select id="id_memo_${bk002Grzd.relid}" dictcode="incision_type" cssCls="easyui-combobox" name="memo" value="${bk002Grzd.memo}" headerKey="" headerValue="-请选择-" exp="style=\"width: 105px;\"  data-options=\"editable:false\"" />
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
							<a href="javascript:void(0)" onclick="reportCards.addEasyFactors('${bk002Grzd.relid}', '${param.index}');" class="btn_icon" title="添加"><i class="icon iconfont fax">&#xe665;</i></a>
						</div>
					
				</div>											
				<div class="gr_label" id="id_ygys_${bk002Grzd.relid}">				
					<c:forEach items="${bk003List}" var="bk003Ygys">
						<span id="id_ygys_${bk002Grzd.relid}_${bk003Ygys.factorId}" code="${bk003Ygys.factorId}"><input type="hidden" name="factorIds" value="${bk003Ygys.factorId}" /><c:out value="${bk003Ygys.factorName}" />
							<c:if test="${bk002Grzd.authStatus eq 0}">
							<a href="javascript:void(0)" class="img_del" onclick="reportCards.delEasyFactors('${bk002Grzd.relid}','${bk003Ygys.factorId}');"></a>
							</c:if>
						</span>
					</c:forEach>
				</div>
			</div>
			<div class="card_cont">
				<div class="card_cont_h">
					<span class="card_c_h_text">致病菌</span>
					<span class="red" id="tips" style="text-align: center;"></span>
				</div>
				<div class="byt_table" id="id_samples_table_${bk002Grzd.relid}">
					<div id="id_samples_${bk002Grzd.relid}"></div>
				</div>
			</div>
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">抗菌药物使用</span>
			</div>
			<div class="byt_table">
				<div id="id_kjywsy_${bk002Grzd.relid}"></div>
			</div>
		</div>
		<c:if test="${ISCGSY != 0}">
			<div class="card_cont">
				<div class="card_cont_h">
					<span class="card_c_h_text">侵入性操作</span>
				</div>
				<div class="byt_table">
					<div id="id_cgsy_${bk002Grzd.relid}"></div>
				</div>
			</div>
		</c:if>
		<div id="id_lsbkmx_${bk002Grzd.refid }">
		</div>
	    </div>
	    </form>
	</div>
	<div data-options="region:'south',border:false" style="height:46px;">
		<div class="footer dialog_footer">
			<div class="footer_btn">
				<div class="n_btn_blue">
					<%-- reportCards.formSubmit('${bk001Sbk.relid}'); --%>
					<a href="javascript:void(0);"  id="id_submit_${bk001Sbk.relid}" onclick="hygiene.validate();" class="no_ico"><span>保存</span></a>
				</div>
				<div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="reportCards.exitEdit('${bk001Sbk.relid}');" class="no_ico"><span>返回</span></a>
				</div>
			</div>	
		</div>
	</div>
	</div>
<script type="text/javascript">
var hygiene = {
	isZd : "0",
	jbJz : 0,
	isYgys : '${isYgys}',
	isIncision : '${isIncision}',
	pInfectCode : '',
	validate : function (){
	  $('#id_jbzdf_${bk001Sbk.relid}').val($('#id_jbzd_${bk001Sbk.relid}').combogrid('getText'));
  	  if('${isSb}'==1){
		if(hygiene.isZd=="1"){
			  $.messager.show({title : '提示',msg : '此感染之前已报过！不允许再次上报！'});
      		  return false;
      	  }
        }
  	    //北大深圳医院需求处理start
		var infectCode = $('#id_infectDiagnId_${bk002Grzd.relid}').combotree('getValue');
		if ('${hospName}' === '北大深圳医院' && $.inArray(infectCode, ['PNU4', 'CAUTI', 'CLBSI']) != -1) {
			var relation = $('#id_relation_${bk002Grzd.relid}').combotree('getValue');
			if (relation.length == 0) {
				$.messager.show({title : '提示',msg : '请选择相关插管！'});
				return false;
			}
		}
		//北大深圳医院需求处理end
		if (hygiene.isYgys === '1' && $('#id_ygys_${bk002Grzd.relid}').children('span').length == 0 && $('#infectType').val()==1) {
			$.messager.show({title : '提示',msg : '请选择易感因素！'});
			return false;
		}
		//北大深圳医院需求处理end
		if(hygiene.isIncision === '1' && '${ISOPEN}'!= 1 && $('#id_memo_${bk002Grzd.relid}').combobox('getValue')=='' && !$('#id_opration_name_${bk002Grzd.relid}').attr("disabled")){
			 $.messager.show({title : '提示',msg : '手术类感染切口必填！'});
			 return false;
		}
		//判断致病菌和抗菌药物是否勾选
		var tips = '';
		if($("#id_samples_${bk002Grzd.relid}").datagrid('getRows').length > 0){
			if ($('#id_samples_table_${bk002Grzd.relid}').find('.checkbox_list:checked').length == 0) {
				tips += '致病菌';
			}
		}
		if('${KJYWTS}' == 0){
			if ($('#id_kjywsy_${bk002Grzd.relid}').datagrid('getRows').length == 0) {
				if ($('#id_kjywsy_${bk002Grzd.relid}').datagrid('getChecked').length == 0) {
					if (tips.length > 0) {
						tips += '、抗菌药物';
					} else {
						tips += '抗菌药物';
					}
				}
			}
		}
		if (tips.length > 0) {
			$.messager.confirm('提示', '<font color="red">' + tips +  '未勾选，</font>确认继续？', function (r) {
	        	if (r) {
	        		if(hygiene.pInfectCode=='SSI'){
	        			if(!$("#id_opration_name_${relid}").val()){
	        				if("${NBISR}"==1){
	        					$.messager.alert('提示', '该感染诊断属于<font color="red">手术部位感染 </font>，未选择手术，无法上报！');
	        				}else{
			        			$.messager.confirm('提示', '该感染诊断属于<font color="red">手术部位感染 </font>，未选择手术，确认继续上报?', function (r) {
			        	        	if (r) {
			        	        		$('#id_form_${bk001Sbk.relid}').submit();
			        	        	}
			        	    	});
	        				}
	        			}else{
	        				$('#id_form_${bk001Sbk.relid}').submit();
	        			}
	        		}else{
	        			$('#id_form_${bk001Sbk.relid}').submit();
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
	        	        		$('#id_form_${bk001Sbk.relid}').submit();
	        	        	}
	        	    	});
					}
				}else{
    				$('#id_form_${bk001Sbk.relid}').submit();
    			}
    		}else{
    			$('#id_form_${bk001Sbk.relid}').submit();
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
				id: 'id_form_${bk001Sbk.relid}',
				url: '${webroot}/bk002Grzd/f_json/updGrzdInfo.shtml',
				subbtn: 'id_submit_${bk001Sbk.relid}',
				onLoading : function () {
					$.messager.progress({
						text : '正在提交中....',
						interval : 200
					});
				},
				success : function(json) {
					$.messager.progress('close');
					if (json.result === 'success') {
						parent.$.messager.show({ title: '提示', msg: '操作成功！' });
                		//$('#id_action_${bk002Grzd.relid}').val('edit');
                		//reportCards.exitEdit('${param.refid}');
    				} else {
    					$.messager.show({title : '提示',msg : json.msg});
    				}
				}
			});
		
		$('#id_cgsy_${bk002Grzd.relid}').datagrid({
			 fit: false,
	         nowrap: true,
	         autoRowHeight: true,
	         striped: true,
		    url:'${webroot}/st004BkCgxx/f_json/findcgsyByRefid.shtml',
		    remoteSort: false,
	        singleSelect: true,
	        pagination:false,
	        rownumbers:false,
	        fitColumns: true,
	        height:'200px',
	        queryParams: {
	        	refid:'${refid}',
		    	zyid : '${bk001Sbk.zyid}'
	        },
		    columns:[ 
			    [
				{field:'id',align:'center',width:15,
					formatter:function(value,rec){
						return ['<input class="checkbox_list" name="yzIdList" value = "'+rec.id+'" title="'+rec.id+'" data="' + rec.id + '" type="checkbox" ' + (rec.refid != null ? 'checked="checked"' : '') + '  />'
						].join('');
				    }
				},
			    {field:'orderName',title:'侵入性操作名称',sortable:true ,width:100},
			    {field:'orderAt',title:'开嘱时间',sortable:true,align:'center',width:100},
			    {field:'stopAt',title:'停嘱时间',sortable:true,align:'center',width:100}
			    ]
		    ],		    
	    });
		//主管医生
		Csm.combogrid.doctor({
			id: 'id_chargeDrId_${bk001Sbk.relid}',
			value: '${bk001Sbk.chargeDrId}',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
			required:true,
	        onHidePanel : function() {
	        	Csm.valueValite.combogrid('id_chargeDrId_${bk001Sbk.relid}');
			},
			onClickRow : function(index, row){
				$('#id_chargeDrIdf_${bk001Sbk.relid}').val(row.employeeId);
			}
		});
		
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
			id: 'id_jbzd_${bk001Sbk.relid}',
			value: '${bk001Sbk.jbzdCode}',
			//text: '${bk001Sbk.jbzd}',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
	        onHidePanel : function() {
	        	//Csm.valueValite.combogrid('id_jbzd_${bk001Sbk.relid}');
			},
			onClickRow : function(index, row){
				$('#id_jbzdf_${bk001Sbk.relid}').val(row.icdName);
				$('#id_jbzdCodef_${bk001Sbk.relid}').val(row.icdCode);
			},
			onLoadSuccess :function (){
				if(hygiene.jbJz==0){
					$('#id_jbzd_${bk001Sbk.relid}').combogrid('setText','${bk001Sbk.jbzd}');
					hygiene.jbJz=1;
				}
				
			}
		});
		
		//感染科室
		Csm.combogrid.dep({
			id: 'id_infectDept_${bk002Grzd.relid}',
			value: '${bk002Grzd.infectDeptId}',
			ifcaseoffice: '1',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
			required:true,
			readonly:${bk002Grzd.authStatus ne 0 ? false : false},
			onClickRow : function(index,row){
				$('#id_infectDeptName_${bk002Grzd.relid}').val(row.deptName);
			},
	        onHidePanel : function() {
	        	Csm.valueValite.combogrid('id_infectDept_${bk002Grzd.relid}');
			}
		});
		
		//感染诊断
		$('#id_infectDiagnId_${bk002Grzd.relid}').combotree({
			url:'${webroot}/yygrzd/f_json/findYygrzdList.shtml?flag=1&infectCode=${bk002Grzd.infectDiagnId}',
			method:'get',
			required:true,
			panelHeight:300,
			panelWidth:250,
			readonly:${bk002Grzd.authStatus ne 0 ? false : false},
			value:'${bk002Grzd.infectDiagnId}',
			onBeforeSelect: function(node) {
	            if (!$(this).tree('isLeaf', node.target)) {
	                return false;
	            }
	        },
	        onLoadSuccess : function (node) {
	        	var nodeId = $("#id_infectDiagnId_${bk002Grzd.relid}").combotree('getValue');
	        	if ('PNU4' === nodeId) {
            		$('#id_relation_${bk002Grzd.relid}').combobox("enable");
            		$('#id_relation_${bk002Grzd.relid}').combobox('setValue', '03');
            		//$('#id_relation_${relid}').validatebox({ required: true });
            	} else if ('CAUTI' === nodeId) {
            		$('#id_relation_${bk002Grzd.relid}').combobox("enable");
            		$('#id_relation_${bk002Grzd.relid}').combobox('setValue', '02');
            		//$('#id_relation_${relid}').validatebox({ required: true });
            	} else if ('CLBSI' === nodeId) {
            		$('#id_relation_${bk002Grzd.relid}').combobox("enable");
            		$('#id_relation_${bk002Grzd.relid}').combobox('setValue', '01');
            		//$('#id_relation_${relid}').validatebox({ required: true });
            	} else {
            		if('${hospName}' === '北大深圳医院'){
              			$('#id_relation_${relid}').combobox('setValue', '');
            			$('#id_relation_${bk002Grzd.relid}').combobox("disable");
            		}
            	}
	        },
	        onClick: function(node) {         
	        	if (!$(this).tree('isLeaf', node.target)) {
	        		$(this).tree('toggle',node.target);
	                $('#id_infectDiagnId_${bk002Grzd.relid}').combo('showPanel');
	            } else {
	            	hygiene.pInfectCode=node.pInfectCode;
	            	$('#id_infectDiagnName_${bk002Grzd.relid}').val(node.text);
	            	$.ajax({
	                    url: '${webroot}/bk002Grzd/f_json/canSelOprat.shtml',
	                    type: 'post',
	                    data: { infectCode: node.id },
	                    dataType: 'json',
	                    success : function(json) {
	                    	if('${ISOPEN}' == 0){
		                    	if (parseInt(json) > 0) {
		                    		$('#id_opration_name_${bk002Grzd.relid}').attr("disabled",false);
		                    		$('#id_memo_${bk002Grzd.relid}').combobox("enable");
		                    	}else if('POP'== node.id){
		                    		$('#id_opration_name_${relid}').attr("disabled",false);
		                    		$('#id_memo_${relid}').combobox("enable");
		                    	}else {
		                    		$('#id_opration_id_${bk002Grzd.relid}').val('');
		                    		$('#id_opration_relid_${bk002Grzd.relid}').val('');
		                    		$('#id_opration_name_${bk002Grzd.relid}').val('');
		                    		$('#id_opration_name_${bk002Grzd.relid}').attr("disabled",true);
		                    		$('#id_memo_${bk002Grzd.relid}').combobox("clear");
		                    		$('#id_memo_${bk002Grzd.relid}').combobox("disable");
		                    	}
	                    	}else{
	                    		$('#id_opration_name_${relid}').attr("disabled",false);
	                    		$('#id_memo_${relid}').combobox("enable");
	                    	}
	    				}
	        		});
  					
	            	if (reportCards.queryType.length == 0) {
	            		var currTab = $('#infect_monitor_tab').tabs('getSelected');
		            	$('#infect_monitor_tab').tabs('setTabTitle',{ tab:currTab, title: node.text + '（${bk002Grzd.authStatusName}）' });
	            	}
	            	
	            	//验证是否已上报
	            	IsUploadBefore();
	            	//北大深圳医院需求处理start
	            	//PNU4-呼吸机相关性肺炎（VAP）   CAUTI-导尿管相关尿路感染（CAUTI）  CLBSI-中心静脉导管相关血流感染（CLBSI）
	            	if ('PNU4' === node.id) {
	            		$('#id_relation_${bk002Grzd.relid}').combobox("enable");
	            		$('#id_relation_${bk002Grzd.relid}').combobox('setValue', '03');
	            		//$('#id_relation_${relid}').validatebox({ required: true });
	            	} else if ('CAUTI' === node.id) {
	            		$('#id_relation_${bk002Grzd.relid}').combobox("enable");
	            		$('#id_relation_${bk002Grzd.relid}').combobox('setValue', '02');
	            		//$('#id_relation_${relid}').validatebox({ required: true });
	            	} else if ('CLBSI' === node.id) {
	            		$('#id_relation_${bk002Grzd.relid}').combobox("enable");
	            		$('#id_relation_${bk002Grzd.relid}').combobox('setValue', '01');
	            		//$('#id_relation_${relid}').validatebox({ required: true });
	            	} else {
	            		if('${hospName}' === '北大深圳医院'){
	            			$('#id_relation_${bk002Grzd.relid}').combobox("disable");
	            		}
	            		//$('#id_relation_${relid}').validatebox({ required: false });
	            		//$('#id_relation_${relid}').removeClass("validatebox-invalid");
	            	}
	            }
	        }
		});
		
		$('#id_samples_${bk002Grzd.relid}').datagrid({
            fit: false,
            nowrap: true,
            autoRowHeight: true,
            striped: true,
            url:'${webroot}/st009Sjbb/f_json/findSjbbForInfectList.shtml?refid=${bk002Grzd.relid}&zyid=${bk001Sbk.zyid}&infectPartId=${bk002Grzd.infectDiagnId}',
            remoteSort: false,
            singleSelect: true,
            columns:[
            	[
					{field:'testOrderNos',align:'center',width:25,
						formatter:function(value,rec){
							return ['<input class="checkbox_list" name="testOrderNos" title="'+rec.pathoName+'" data="' + rec.testOrderNo + '" type="checkbox" ' + (rec.refid != null ? 'checked="checked"' : '') + ' value="' + rec.testOrderNo + '" onclick="pathogens.checkTestOrderNo(this, \'${bk002Grzd.relid}\',\'' + rec.testOrderNo + '\',\'' + rec.pathoName + '\');" />'].join('');
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
            rownumbers:false,
        });
	
		$('#id_kjywsy_${bk002Grzd.relid}').datagrid({
		    fit: false,
		    nowrap: true,
		    autoRowHeight: true,
		    striped: true,
		    fitColumns: true,
		    height:'200px',
		    url:'${webroot}/gr016BkKjyw/f_json/query.shtml',
		    remoteSort: false,
		    singleSelect: true,
		    border: true,
		    queryParams: {
		    	refid:'${refid}',
		    	zyid : '${bk001Sbk.zyid}'
            },
		    columns:[ 
			    [
				{field:'id',align:'center',width:25,
					formatter:function(value,rec){
						return ['<input class="checkbox_list" name="yzIds" value = "'+rec.id+'" title="'+rec.id+'" data="' + rec.id + '" type="checkbox" ' + (rec.refid != null ? 'checked="checked"' : '') + '  />'
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
			    ]],
			    onLoadSuccess : function (data) {
		        },
// 		    rownumbers:true
	    });
		},100);
		// 历史报卡明细
		$.ajax({
			url : '${webroot}/bk001Sbk/f_json/getReportInfectListByZyid.shtml',
			type : 'post',
			data : { zyid: '${bk001Sbk.zyid}'},
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
					tempStr1 = '<td class="omit" title="' + bk001Sbk.infectTypeName + '">' + bk001Sbk.infectTypeName + '</td><td class="omit" title="' + (bk001Sbk.startAt == null? "":bk001Sbk.startAt.substring(0,10)) + '">' + (bk001Sbk.startAt == null? "":bk001Sbk.startAt.substring(0,10)) + '</td><td class="omit" title="' + bk001Sbk.infectDeptName + '">' + bk001Sbk.infectDeptName + '</td><td class="omit" title="'+ bk001Sbk.infectName + '">'+ bk001Sbk.infectName + '</td><td class="omit" title="'+ bk001Sbk.reportDrName + '">'+ bk001Sbk.reportDrName + '</td><td class="omit" title="'+ bk001Sbk.reportDeptName + '">'+ bk001Sbk.reportDeptName + '</td>';
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
					$('#id_lsbkmx_${bk002Grzd.refid }').append(htmlStr);
				}else{
					$('#id_lsbkmx_${bk002Grzd.refid }').append('<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">历史报卡明细</span></div><div class="byt_table">无</div></div>');
				}
			}
		});
	});
	function IsUploadBefore(){
		var nodeId = $('#id_infectDiagnId_${bk002Grzd.relid}').combotree("getValue");
		var zyid = "${bk001Sbk.zyid}";
		var ift = $("#infectType").combobox("getValue");
		if(nodeId && zyid){
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
						hygiene.isZd = "1";
						$(".reportPlace").append("<span id='tip' style='color:#ef6b3a'>！此感染之前已报过 &nbsp;&nbsp;<span style='cursor: pointer;' onclick=\"checkDetail('"+data.bk2relid+"');\">查看</span></span>");
					}else if(data.count==0){
						$("#tip").remove();
						hygiene.isZd = "0";
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