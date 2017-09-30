<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>维护手术信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout" >
	<div  data-options="region:'center',border:false" style="padding:10px; border-bottom-width:1px;">
		<form id="id_form" method="post">
		<input type="hidden" id="id" name="id" value="${st005Ssxxb.id}" />
		<input type="hidden" name="zyid" value="${st005Ssxxb.zyid}" />
		<input type="hidden" name="relid" value="${st005Ssxxb.relid}" />	
	    <div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">患者信息</span>
			</div>			
			<table class="info_table">
			    <tbody>  
			        <tr>
			            <th>${patientZyTitle}:</th>
			            <td>
			            	<c:out value="${patientZyValue == 'patientId'?st005Ssxxb.patientId:st005Ssxxb.zyid}" />
			            </td>
			            <th>姓名:</th>
			            <td>
			            	<input type="text" style="width: 88px;" name="patientName" value="${st005Ssxxb.patientName}" />
			            </td>
			            <th>性别:</th>
			            <td>
			            	<nis:radio name="sex" dictcode="sex" value="${st005Ssxxb.sex}" defvalue="男"/>
			            </td>
			            <th>年龄:</th>
			            <td>
			            	<input type="text" style="width: 38px;" name="age" value="${st005Ssxxb.age}" />
							<nis:select name="ageUnit" cssCls="easyui-combobox" dictcode="age_unit" value="${st005Ssxxb.ageUnit}" exp="style=\"width: 45px;\"" />
			            </td>
			            <th>床号:</th>
			            <td>
			            	<input type="text" style="width: 88px;" name="bedNo" value="${st005Ssxxb.bedNo}" />
			            </td>
			        </tr>
			        <tr>
			        	<th>科室:</th>
			            <td>
			            	<input type="hidden" id="id_deptName" name="deptName" value="${st005Ssxxb.deptName}" />
							<input type="text" id="id_dept" style="width: 130px;" class="easyui-validatebox" name="deptId" />
			            </td>
			            <th>入院时间:</th>
			            <td>
			            	<input type="text" class="Wdate text" name="inHospAt" value="<fmt:formatDate value="${st005Ssxxb.inHospAt}" pattern="yyyy-MM-dd" />" style="width: 88px;" onclick="WdatePicker()" />
			            </td>
			            <th>出院时间:</th>
			            <td>
			            	<input type="text" class="Wdate text" name="outAt" value="<fmt:formatDate value="${st005Ssxxb.outAt}" pattern="yyyy-MM-dd" />" style="width: 88px;" onclick="WdatePicker()" />
			            </td>
			            <th>卡片状态:</th>
			            <td>
							<%-- <nis:select id="id_status" name="status" cssCls="easyui-combobox" dictcode="opera_status" value="${st005Ssxxb.status}" exp="style=\"width: 100px;\" data-options=\"readonly:'true'\"" /> --%>
							<c:out value="${st005Ssxxb.statusName}" />
							<input type="hidden" id="id_status" name="status" value="${st005Ssxxb.status}" />
			            </td>
			            <td></td><td></td>
			        </tr>
			 	</tbody>         
			</table>		    
		</div>
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">患者术前情况</span>
			</div>			
			<table class="info_table">
			    <tbody>  
			        <tr>
			            <th>入院至手术(天数):</th>
			            <td>
			            	<input type="text" style="width: 88px;" name="inopeDays" value="${st005Ssxxb.inopeDays}" />
			            </td>
			            <th>血糖水平(mmol/L):</th>
			            <td>
			            	<input type="text" style="width: 88px;" name="bloodSugarLevel" value="${st005Ssxxb.bloodSugarLevel}" />
			            </td>
			            <th>严重基础疾病:</th>
			            <td>
			            	<nis:radio name="yzjcjb" dictcode="yes_or_no" value="${st005Ssxxb.yzjcjb}"/>
			            </td>
			            <th>营养情况:</th>
			            <td>
							<nis:select name="nutritionCondition" cssCls="easyui-combobox" dictcode="nutrition_condition"  value="${st005Ssxxb.nutritionCondition}" headerKey="" headerValue="-请选择-" exp="style=\"width: 100px;\"" />
			            </td>
			        </tr>
			        <tr>
			        	<th>外周白细胞数(*10^9):</th>
			            <td>
							<input type="text" style="width: 88px;" name="wzbxbjs" value="${st005Ssxxb.wzbxbjs}" />
			            </td>
			            <th>BMI(kg/m2):</th>
			            <td>
			            	<input type="text" style="width: 88px;" name="bmi" value="${st005Ssxxb.bmi}" />
			            </td>
			            <th>体型:</th>
			            <td>
							<nis:select name="typeBuild" dictcode="type_build" cssCls="easyui-combobox" value="${st005Ssxxb.typeBuild}" headerKey="" headerValue="-请选择-" exp="style=\"width: 100px;\"" />
			            </td>
			            <th>疾病转归:</th>
			            <td>
							<nis:select name="lapseTo" dictcode="lapse_to" cssCls="easyui-combobox" value="${st005Ssxxb.lapseTo}" headerKey="" headerValue="-请选择-" exp="style=\"width: 100px;\"" />
			            </td>
			        </tr>
			        <tr>
			        	<th>联系地址:</th>
			        	<td colspan="3">
			        		<input type="text" style="width: 300px;" name="patientAddress" value="${st005Ssxxb.patientAddress}" />
			        	</td>
			        	<th>联系电话:</th>
			        	<td>
			        		<input type="text" style="width: 88px;" name="tel" value="${st005Ssxxb.tel}" />
			        	</td>
			        	<th>调查日期:</th>
			        	<td>
			        		<input type="text" class="Wdate text" name="monitorDate" value="<fmt:formatDate value="${st005Ssxxb.monitorDate}" pattern="yyyy-MM-dd" />" style="width: 88px;" onclick="WdatePicker()" />
			        	</td>
			        </tr>
			 	</tbody>         
			</table>
		</div>
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">患者手术情况</span>
			</div>			
			<table class="info_table">
			    <tbody>  
			        <tr>
			            <th><span class="red">*</span>手术名称:</th>
			            <td>
			            	<input type="text" id="id_operName" name="operName" class="easyui-validatebox" style="width:150px;" <c:if test="${fn:contains(inputRequired,'operName')}">required="true"</c:if> value="${st005Ssxxb.operName}"/>
			            	<!-- <input type="text" id="id_operId" style="width: 100px;" class="easyui-validatebox"  name="operId" /> -->
			            </td>
			            <th><span class="red">*</span>手术日期:</th>
			            <td>
			            	<input type="text" class="Wdate text easyui-validatebox" <c:if test="${fn:contains(inputRequired,'operDate')}">required="true"</c:if> name="operAt" value="<fmt:formatDate value="${st005Ssxxb.operAt}" pattern="yyyy-MM-dd" />" style="width: 88px;" onclick="WdatePicker()" />
			            </td>
			            <th><span class="red">*</span>手术开始时间:</th>
			            <td>
			            	<input type="text" class="easyui-validatebox" id="operAtStart"  name="operAtStart" <c:if test="${fn:contains(inputRequired,'operAtStart')}">required="true"</c:if> value="<fmt:formatDate value="${st005Ssxxb.operAtStart}" pattern="yyyy-MM-dd HH:mm" />" style="width: 102px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
			            </td>
			            <th><span class="red">*</span>手术结束时间:</th>
			            <td>
			            	<input type="text" onblur="surgeryInfo.getDiffMinute()" id="operAtEnd" class="easyui-validatebox"  name="operAtEnd" <c:if test="${fn:contains(inputRequired,'operAtEnd')}">required="true"</c:if> value="<fmt:formatDate value="${st005Ssxxb.operAtEnd}" pattern="yyyy-MM-dd HH:mm" />" style="width: 102px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
			            </td>
			            <th><span class="red">*</span>持续时间(分钟):</th>
			            <td>
			            	<input type="text" style="width: 88px;" id="operLengTime" class="easyui-validatebox" <c:if test="${fn:contains(inputRequired,'operLengTime')}">required="true"</c:if> name="operLengTime" value="${st005Ssxxb.operLengTime}" />
			            </td>
			        </tr>
			        <tr>
			        	<th><span class="red">*</span>手术医生:</th>
			            <td>
			            	<input type="hidden" id="id_opedocName" name="opedocName" value="${st005Ssxxb.opedocName}"/>
							<input type="text" id="id_opedocId" style="width: 100px;" name="opedocId" />
			            </td>
			            <th><span class="red">*</span>切口等级:</th>
			            <td>
							<nis:select dictcode="incision_type" cssCls="easyui-combobox" name="incisionGrade" value="${st005Ssxxb.incisionGrade}" headerKey="" headerValue="" exp="style=\"width: 100px;\" required=\"true\"" />
			            </td>
			            <th><span class="red">*</span>ASA评分:</th>
			            <td>
			            	<select name="asa" class="easyui-combobox" style="width: 100px;" <c:if test="${fn:contains(inputRequired,'asa')}">required="true"</c:if>>
								<option value=""></option>
								<option value="1" ${st005Ssxxb.asa eq 1 ? 'selected="selected"' : ''}>1</option>
								<option value="2" ${st005Ssxxb.asa eq 2 ? 'selected="selected"' : ''}>2</option>
								<option value="3" ${st005Ssxxb.asa eq 3 ? 'selected="selected"' : ''}>3</option>
								<option value="4" ${st005Ssxxb.asa eq 4 ? 'selected="selected"' : ''}>4</option>
								<option value="5" ${st005Ssxxb.asa eq 5 ? 'selected="selected"' : ''}>5</option>
								<option value="6" ${st005Ssxxb.asa eq 6 ? 'selected="selected"' : ''}>6</option>
							</select>
			            </td>	
			            <th>NNIS评分:</th>
			            <td>
			            	<c:out value="${st005Ssxxb.nnis}" />
			            	<%-- <input type="text" style="width: 60px;" name="nnis" value="${st005Ssxxb.nnis}" readonly="readonly" /> --%>
			            	<span class="ico_help"></span>
			            </td>
			            <th>手术操作类别:</th>
			            <td>
			            	<input type="hidden" id="id_partkindname" name="partkindname" value="${st005Ssxxb.partkindname}"/>
			            	<input type="text" id="id_opepartkindid" style="width: 100px;" name="opepartkindid" value="${st005Ssxxb.opepartkindid}" />
			            </td>		          
			        </tr>
			        <tr>
			            <th>重点监测手术:</th>
			            <td>
			            	<input type="hidden" id="id_impOpeName" name="impOpeName" value="${st005Ssxxb.impOpeName}"/>
			            	<input type="text" id="id_impOpeId" style="width: 100px;" name="impOpeId" />
			            </td>
			        	<th>术前白细胞数:</th>
			            <td>
							<input type="text" style="width:88px;" name="sqbxbs" value="${st005Ssxxb.sqbxbs}" />
			            </td>
			            <th>手术医生职称:</th>
			            <td>
			            	<select class="easyui-combobox" style="width: 100px;" name="ssyszc">
								<option value="">-请选择-</option>
								<option value="初级" ${st005Ssxxb.ssyszc eq '初级' ? 'selected="selected"' : ''}>初级</option>
								<option value="中级" ${st005Ssxxb.ssyszc eq '中级' ? 'selected="selected"' : ''}>中级</option>
								<option value="正高" ${st005Ssxxb.ssyszc eq '正高' ? 'selected="selected"' : ''}>正高</option>
								<option value="副高" ${st005Ssxxb.ssyszc eq '副高' ? 'selected="selected"' : ''}>副高</option>
							</select>
			            </td>
			            <th>备皮:</th>
			            <td>
			            	<%-- <input id="id_skinPrepare_yes" type="radio" value="1" name="skinPrepare" ${st005Ssxxb.skinPrepare eq 1 ? 'checked="checked"' : ''} /><label for="id_skinPrepare_yes">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="id_skinPrepare_no" type="radio" value="0" name="skinPrepare" ${(st005Ssxxb.skinPrepare eq 0 || empty st005Ssxxb.skinPrepare) ? 'checked="checked"' : ''} /><label for="id_skinPrepare_no">&nbsp;否</label> --%>
			            	<nis:radio name="skinPrepare" dictcode="yes_or_no" value="${st005Ssxxb.skinPrepare}"/>
			            </td>
			            <th>备皮方法:</th>
			            <td>
							<nis:select dictcode="skin_method" cssCls="easyui-combobox" name="skinMethod" value="${st005Ssxxb.skinMethod}" headerKey="" headerValue="-请选择-" exp="style=\"width: 100px;\"" />
			            </td>			     
			        </tr>
			        <tr>
			        	<th>术前备皮时间:</th>
			            <td>
							<nis:select dictcode="pre_skintime" cssCls="easyui-combobox" name="preSkintime" value="${st005Ssxxb.preSkintime}" headerKey="" headerValue="-请选择-" exp="style=\"width: 100px;\"" />
			            </td>
			        	<th>术中失血量(ml):</th>
			            <td>
							<input type="text" style="width: 88px;" name="bloodOut" value="${st005Ssxxb.bloodOut}" />
			            </td>
			            <th>术中输血量(ml):</th>
			            <td>
			            	<input type="text" style="width: 88px;" name="bloodIn" value="${st005Ssxxb.bloodIn}" />
			            </td>
			            <th>麻醉医师:</th>
			            <td>
			            	<input type="hidden" id="id_anesDrName" name="anesDrName" value="${st005Ssxxb.anesDrName}"/>
			            	<input type="text" id="id_anesDrId" style="width: 100px;" name="anesDrId" />
			            </td>
			            <th>麻醉类型:</th>
			            <td>
							<nis:select dictcode="narc_kind"   cssCls="easyui-combobox" name="narcKind" value="${st005Ssxxb.narcKind}" headerKey="" headerValue="" exp="style=\"width: 100px;\" " />
			            </td>
			            
			        </tr>
			        <tr>
			        	<th>手术室:</th>
			            <td>
			            	<input type="text" style="width: 88px;" name="operRoom" value="${st005Ssxxb.operRoom}" />
			            </td>
			        	<th>急诊手术:</th>
			            <td>
			            	<nis:radio name="urgentOpe" dictcode="yes_or_no" value="${st005Ssxxb.urgentOpe}"/>
			            </td>
			            <th>连台:</th>
			            <td>
			            	<nis:radio name="continuousOpe" dictcode="yes_or_no" value="${st005Ssxxb.continuousOpe}"/>
			            </td>
			            <th>是否接台:</th>
			            <td>
			            	<nis:radio name="isjt" dictcode="yes_or_no" value="${st005Ssxxb.isjt}"/>
			            </td>
			            <th>巡回护士:</th>
			            <td>
			            	<input type="hidden" id="id_circuitNurse" name="circuitNurse" value="${st005Ssxxb.circuitNurse}"/>
			            	<input type="text" id="id_circuitNurseId" style="width: 100px;" />
			            </td>
			           
			        </tr>
			        <tr>		       
			        	<th>洗手护士:</th>
			            <td>
			            	<input type="hidden" id="id_scrubNurse" name="scrubNurse" value="${st005Ssxxb.scrubNurse}"/>
			            	<input type="text" id="id_scrubNurseId" style="width: 100px;" />
			            </td>
			        	<th>使用内镜:</th>
			            <td>
			            	<nis:radio name="glassOpe" dictcode="yes_or_no" value="${st005Ssxxb.glassOpe}"/>
			            </td>
			            <th>术中使用引流管:</th>
			            <td>
			            	<nis:radio name="sfsszylg" dictcode="yes_or_no" value="${st005Ssxxb.sfsszylg}"/>
			            </td>
			            <th>出院使用引流管:</th>
			            <td>
			            	<nis:radio name="cysfylg" dictcode="yes_or_no" value="${st005Ssxxb.cysfylg}"/>
			            </td>
			            <th>植入物:</th>
			            <td>
			            	<nis:radio name="replant" dictcode="yes_or_no" value="${st005Ssxxb.replant}"/>
			            </td>
			           
			        </tr>
			        <tr>
			        	 <th>手术愈合情况:</th>
			            <td>
							<nis:select dictcode="healing_of_surgery" cssCls="easyui-combobox" name="heal" value="${st005Ssxxb.heal}" headerKey="" headerValue="-请选择-" exp="style=\"width: 100px;\"" />
			            </td>
			        	<th>是否多种操作:</th>
			        	<td>
			        		<nis:radio name="isdzcz" dictcode="yes_or_no" value="${st005Ssxxb.isdzcz}"/>
			        	</td>
			        	
			            <th>手术部位分类:</th>
			            <td>
			            	<nis:select name="operBw"  cssCls="easyui-combobox" dictcode="opera_bw" headerKey="" headerValue="-部位分类-" value="${st005Ssxxb.operBw}" exp="style=\"width: 120px;\"" />
			            </td>
			        	<th>备注:</th>
			        	<td colspan="3">
			        		<input type="text" style="width: 300px;" name="memo" value="${st005Ssxxb.memo}" />
			        	</td>
			        </tr>
			 	</tbody>         
			</table>
		</div>
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">术前用药情况</span>
			</div>
			<div class="m_search">				
				<nis:select dictcode="unite_medication"  cssCls="easyui-combobox" name="preLhyy" value="${st005Ssxxb.preLhyy}" headerKey="" headerValue="-请选择联合用药-"  />				
				<nis:select dictcode="is_correct_medication"  cssCls="easyui-combobox" name="preZqyy" value="${st005Ssxxb.preZqyy}" headerKey="" headerValue="-请选择是否正确用药-"  />				
				<span class="ml10">用药天数:</span><input type="text"  name="preYyts"  value="${st005Ssxxb.preYyts}" style="width: 30px; margin:0px 5px;" />天
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="surgeryInfo.addDrug('添加术前用药',1);"  class="no_ico"><span>添加术前用药</span></a>
				</div>
			</div>
			<div class="byt_table" style="margin-top:0px;">
				<div id="id_pre-operation"></div>
			</div>
		</div>
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">术中用药情况</span>
			</div>
			<div class="m_search">						
				<nis:select dictcode="unite_medication"  cssCls="easyui-combobox" name="wsqLhyy" value="${st005Ssxxb.wsqLhyy}" headerKey="" headerValue="-请选择联合用药-"  />				
				<nis:select dictcode="is_correct_medication"  cssCls="easyui-combobox" name="wsqZqyy" value="${st005Ssxxb.wsqZqyy}" headerKey="" headerValue="-请选择是否正确用药-" />	
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="surgeryInfo.addDrug('维护术中用药',2);"  class="no_ico"><span>添加术中用药</span></a>
				</div>
			</div>	
			<div class="byt_table" style="margin-top:0px;">	
				<!-- <div id="id_perioperative22" style="display:none;"></div>			 -->
				<div id="id_perioperative"></div>
			</div>
		</div>
		<div class="card_cont mb60">
			<div class="card_cont_h">
				<span class="card_c_h_text">术后用药情况</span>
			</div>
			<div class="m_search">				
				<nis:select dictcode="unite_medication"  cssCls="easyui-combobox" name="afterLhyy" value="${st005Ssxxb.afterLhyy}" headerKey="" headerValue="-请选择联合用药-"  />
				<nis:select dictcode="is_correct_medication"  cssCls="easyui-combobox" name="afterZqyy" value="${st005Ssxxb.afterZqyy}" headerKey="" headerValue="-请选择是否正确用药-"  />
				<span class="ml10">用药天数:</span><input type="text" name="afterYyts" value="${st005Ssxxb.afterYyts}" style="width: 30px; margin:0px 5px;"/>天
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="surgeryInfo.addDrug('添加术后用药',3);"  class="no_ico"><span>添加术后用药</span></a>
				</div>
			</div>
			<div class="byt_table" style="margin-top:0px;">
				<div id="id_postoperation"></div>
			</div>
		</div>			
		</form>
	</div>
	<div  data-options="region:'south',border:false">
		<div style="padding:10px 0px; text-align:center;">	
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="$('#id_form').submit();"  class="no_ico"><span>保存</span></a>
				</div>
				<div class="n_btn_grey">
					<c:choose>
						<c:when test="${st005Ssxxb.status eq 4}">
							<a href="javascript:;" onclick="surgeryInfo.formSubmit(0);"><i class="icon iconfont">&#xe687;</i><span>取消归档</span></a>
						</c:when>
						<c:otherwise>
							<a href="javascript:;" onclick="surgeryInfo.formSubmit(4);"><i class="icon iconfont">&#xe687;</i><span>归档</span></a>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="surgeryInfo.printReportInfo();" ><i class="icon iconfont">&#xe604;</i><span>打印</span></a>
				</div>
			</div>	
		</div>
	</div>
	
<script type="text/javascript">
	var surgeryInfo = {
		operAt : '<fmt:formatDate value="${st005Ssxxb.operAt}" pattern="yyyy-MM-dd" />',
		dateSectionsBefore : '${dateSectionsBefore}',
		dateSectionsAfter : '${dateSectionsAfter}',
		yymd : '${yymd}',
		szyzjyyewzj : '${szyzjyyewzj}',
		dateSectMap : {},
		//处理用药情况
		del : function(operTypeId, relid) {
	    	$.messager.confirm('提示', '确认删除该手术用药?', function (r) {
	        	if (r) {
	            	$.ajax({
                        url: '${webroot}/gr016SsbwKjyw/f_json/delete.shtml',
                        type: 'post',
                        data: { relid: relid },
                        dataType: 'json',
                        success : function(json) {
							if(json.result==='success') {
								surgeryInfo.query(operTypeId);
                                $.messager.show({ title: '提示', msg: '删除成功！' });
					    	} else if(json.result === 'error') {
					    		$.messager.show({ title: '提示', msg: '系统异常！' });
					    	} else {
					    		$.messager.show({ title: '提示', msg: json.msg });
					    	}
						}
	            	});
	        	}
	    	});
		},
		//处理用药情况
		setDrugSituate : function(operTypeId, id,  obj) {
			var isselect = 0;
			if ($(obj).attr("checked")) {
				isselect = 1;
			}
			$('#id_drugSituate_' + operTypeId + '_' + id).val(isselect);
		},
		//处理手术时间相减
		 getDiffMinute : function(){
			var operAtStart = $("#operAtStart").val();
			var operAtEnd = $("#operAtEnd").val();
			if(operAtStart!=''&&operAtEnd!=''){
				var reg=new RegExp("-","g"); //创建正则RegExp对象    
				var date1 = operAtStart.replace(reg,"/") ; 
				var date2 = operAtEnd.replace(reg,"/") ; 
				var date1 = new Date(date1) ; 
				var date2 = new Date(date2); 
				var s1 = date1.getTime(); 
				var s2 = date2.getTime();
				$("#operLengTime").val(parseInt((s2-s1)/60000));
			}
		}, 
		//处理时间段选择
		setDateSectionBefore : function(value, index, nameList){
			var selectStr = '<select name="' + nameList + '[' + index +'].dateSection' + '" style="width: 110px;"><option value="">请选择</option> ';
			for (var i=0; i < surgeryInfo.dateSectionsBefore.length; i++) {
				var dateSection = surgeryInfo.dateSectionsBefore[i];
				selectStr += '<option value="' + dateSection.value + '" ' + (dateSection.value == value ? 'selected="selected"' : '') + '>' + dateSection.text + '</option>';
			}
			selectStr += '</select>';
			return selectStr;
		},
		//处理时间段选择
		setDateSectionAfter : function(value, index, nameList){
			var selectStr = '<select name="' + nameList + '[' + index +'].dateSection' + '" style="width: 110px;"><option value="">请选择</option>';
			for (var i=0; i < surgeryInfo.dateSectionsAfter.length; i++) {
				var dateSection = surgeryInfo.dateSectionsAfter[i];
				selectStr += '<option value="' + dateSection.value + '" ' + (dateSection.value == value ? 'selected="selected"' : '') + '>' + dateSection.text + '</option>';
			}
			selectStr += '</select>';
			return selectStr;
		},
		//用药目的选择
		setPreYymdList : function(value, index, nameList){
			var selectStr = '<select name="' + nameList + '[' + index +'].preYymd' + '" style="width: 110px;"><option value="">请选择</option> ';
			for (var i=0; i < surgeryInfo.yymd.length; i++) {
				var preYymd = surgeryInfo.yymd[i];
				selectStr += '<option value="' + preYymd.value + '" ' + (preYymd.value == value ? 'selected="selected"' : '') + '>' + preYymd.text + '</option>';
			}
			selectStr += '</select>';
			return selectStr;
		},
		//是否术中追加用药
		setSzyzjyyewzjList : function(value, index, nameList){
			var selectStr = '<select name="' + nameList + '[' + index +'].szyzjyyewzj' + '" style="width: 110px;"><option value="">请选择</option> ';
			for (var i=0; i < surgeryInfo.szyzjyyewzj.length; i++) {
				var tmpSzyzjyyewzj = surgeryInfo.szyzjyyewzj[i];
				selectStr += '<option value="' + tmpSzyzjyyewzj.value + '" ' + (tmpSzyzjyyewzj.value == value ? 'selected="selected"' : '') + '>' + tmpSzyzjyyewzj.text + '</option>';
			}
			selectStr += '</select>';
			return selectStr;
		},
		//转换时间段数据为map
		transToMap : function() {
			surgeryInfo.dateSectionsBefore = jQuery.parseJSON(surgeryInfo.dateSectionsBefore);
			for (var i=0; i < surgeryInfo.dateSectionsBefore.length; i++) {
				var dateSection = surgeryInfo.dateSectionsBefore[i];
				//this.dateSectMap[dateSection.value] = dateSection.text;
			}
			//this.dateSectMap[null] = ' ';
			
			surgeryInfo.dateSectionsAfter = jQuery.parseJSON(surgeryInfo.dateSectionsAfter);
			for (var i=0; i < surgeryInfo.dateSectionsAfter.length; i++) {
				var dateSection = surgeryInfo.dateSectionsAfter[i];
				//this.dateSectMap[dateSection.value] = dateSection.text;
			}
			//this.dateSectMap[null] = ' ';
			
			surgeryInfo.yymd = jQuery.parseJSON(surgeryInfo.yymd);
			for (var i=0; i < surgeryInfo.yymd.length; i++) {
				var preYymd = surgeryInfo.yymd[i];
				//this.dateSectMap[dateSection.value] = dateSection.text;
			}
			//this.dateSectMap[null] = ' ';
			
			surgeryInfo.szyzjyyewzj = jQuery.parseJSON(surgeryInfo.szyzjyyewzj);
			for (var i=0; i < surgeryInfo.szyzjyyewzj.length; i++) {
				var tmpSzyzjyyewzj = surgeryInfo.szyzjyyewzj[i];
				//this.dateSectMap[dateSection.value] = dateSection.text;
			}
			//this.dateSectMap[null] = ' ';
		},
		//归档提交处理
		formSubmit : function(status) {
			/* $('#id_status').combobox('setValue', status); */
			$('#id_status').val(status);
			$('#id_form').submit();
		},
		//刷新原来的列表页面
		refreshList : function() {
			try {
				var ifr = parent.document.getElementById('${param.fromTabBodyId}');
				var win = ifr.window || ifr.contentWindow;
				win.surgery.query();
			} catch (e) {
				//console.error(e);
			}
		},
		//新增手术用药
		addDrug : function(title,operTypeId){
	        Comm.dialogGlobal({
	        	url:"${webroot}/st004Yzxxb/f_view/toAddDrug.shtml?zyid=${st005Ssxxb.zyid}&refid=${st005Ssxxb.relid}&operTypeId="+ operTypeId+"",
	            title: title,
	            width:950,
	            height:550,
	            type:'iframe',
	            parent:this
	        });
		},
		//查询
		query : function(operTypeId) {
			var panelId = "id_pre-operation";
			if(operTypeId == "1"){
				panelId = "id_pre-operation";
			}else if(operTypeId == "2"){
				panelId = "id_perioperative";
			}else if(operTypeId == "3"){
				panelId = "id_postoperation";
			}
	        $('#'+panelId).datagrid({
	            queryParams: {
	            	'operTypeId':operTypeId,
	            	'isselect':'1',
	            	'refid':'${st005Ssxxb.relid}',
	            	'zyid':'${st005Ssxxb.zyid}',
	            	'operAt':surgeryInfo.operAt
	            }
	        });
	    },
		//打印报卡信息
		printReportInfo : function() {
       		var url = reportUrl+'nis7/SS_SSGRXQ.cpt&__bypagesize__=false&id=' + $('#id').val();
       		window.open(url);
       		//parent.menuInfo.clickMenu(decodeURI(decodeURI('报卡打印')), url, true, '', null);
		}
	};
	
	var perioperative = {
		id : 'id_perioperative',
		editIndex : undefined,
		endEditing : function(){
	        if (perioperative.editIndex == undefined){return true;}
	        if ($('#' + perioperative.id).datagrid('validateRow', perioperative.editIndex)){
	            $('#' + perioperative.id).datagrid('endEdit', perioperative.editIndex);
	            perioperative.editIndex = undefined;
	            return true;
	        } else {
	            return false;
	        }
	    },
	    onClickCell : function(index, field){
	        if (perioperative.editIndex != index){
	            if (perioperative.endEditing()){
	                $('#' + perioperative.id).datagrid('selectRow', index)
	                        .datagrid('beginEdit', index);
	                var ed = $('#' + perioperative.id).datagrid('getEditor', {index:index,field:field});
	                if (ed){
	                    ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
	                }
	                perioperative.editIndex = index;
	            } else {
	                setTimeout(function(){
	                    $('#' + perioperative.id).datagrid('selectRow', perioperative.editIndex);
	                },0);
	            }
	        }
	    },
	    onEndEdit : function(index, row) {
	        var ed = $(this).datagrid('getEditor', {
	            index: index,
	            field: 'dateSection'
	        });
	        var text = $(ed.target).combobox('getText');
	        var value = $(ed.target).combobox('getValue');
	        row.text = '<input type="hidden" name="dateSection" value="' + value + '" />' + text;
	    }
	};
	
	$(document).ready(function () {
		
		surgeryInfo.transToMap();
		
		window.setTimeout(function(){
			Comm.form({
				id: 'id_form',
				url: '${webroot}/gr016SsbwKjyw/f_json/update.shtml',
				subbtn: 'btn_save',
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
                		surgeryInfo.refreshList();
                		parent.menuInfo.closeMenu('手术信息');
    				} else {
    					$.messager.show({title : '提示',msg : json.msg});
    				}
				}
			});
		},100);
		
		//屏蔽BUG
		$('#testBug').datagrid({});
		
		//术中用药情况
		$('#id_perioperative').datagrid({});
		$('#id_perioperative').datagrid({
	        fit: false,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url:'${webroot}/st004Yzxxb/f_json/findDrugSituation.shtml',
	        queryParams: {
            	'operTypeId':'2',
            	'isselect':'1',
            	'refid':'${st005Ssxxb.relid}',
            	'zyid':'${st005Ssxxb.zyid}',
            	'operAt':surgeryInfo.operAt
            },
	        remoteSort: false,
	        singleSelect: true,
	        border:true,
	        /* onClickCell: perioperative.onClickCell,
	        onEndEdit: perioperative.onEndEdit, */
	        columns:[ 
		       	[
					{field:'orderTypeName',title:'医嘱类型',sortable:true,align:'center',width:70,
						formatter:function(value,rec,index){
							return ['<input type="hidden" name="gr16List2[' + index + '].yzId" value="' + rec.id + '"/>' + 
							        '<input type="hidden" name="gr16List2[' + index + '].relid" value="' + rec.relid + '"/>' + 
							        '<input type="hidden" id="id_drugSituate_2_' + rec.id + '" name="gr16List2[' + index + '].isselect" value="' + (rec.isselect == null ? 0 : rec.isselect) + '"/>' + 
							        rec.orderTypeName].join('');
					    }
					},
		            {field:'orderName',title:'医嘱名称',sortable:true,width:140},
		            {field:'orderAt',title:'开嘱时间',sortable:true,align:'center',width:125},
		            {field:'stopAt',title:'停嘱时间',sortable:true,align:'center',width:125},
		            {field:'dosage',title:'剂量',sortable:true,align:'center',width:50},
		            {field:'sypc',title:'频次',sortable:true,align:'center',width:50},
		            {field:'adminRouteName',title:'给药路径',sortable:true,align:'center',width:80},
		            {field:'preYymd',title:'用药目的',width:120,
		            	formatter:function(value,row,index){
                            return surgeryInfo.setPreYymdList(value, index, 'gr16List2');
                        }
		            },
		            {field:'szyzjyyewzj',title:'术中追加用药',width:120,
		            	formatter:function(value,row,index){
                            return surgeryInfo.setSzyzjyyewzjList(value, index, 'gr16List2');
                        }
		            },
                    {field:'_operate',title:'操作',width:40,
						formatter : function(value,row){
							return ['<a href="javascript:surgeryInfo.del(2,\'',row.relid,'\');" class="ico_del" title="删除"></a>'].join('');
						}
                    }
		           /*  {field:'dateSection',title:'时间段',width:120,
		            	formatter:function(value,row,index){
		            		return surgeryInfo.setDateSection(value, index, 'gr16List2');
                        }
		            	 formatter:function(value,row){
                            return '<input type="hidden" name="dateSection" value="' + value + '" />' + surgeryInfo.dateSectMap[value];
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'value',
                                textField:'text',
                                data:surgeryInfo.dateSections,
                                editable: false
                            }
                        } 
		            }*/
		        ]
	        ],
	        rownumbers:true
	    });
		
		//术前用药情况
		$('#id_pre-operation').datagrid({
	        fit: false,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url:'${webroot}/st004Yzxxb/f_json/findDrugSituation.shtml',
	        queryParams: {
            	'operTypeId':'1',
            	'isselect':'1',
            	'refid':'${st005Ssxxb.relid}',
            	'zyid':'${st005Ssxxb.zyid}',
            	'operAt':surgeryInfo.operAt
            },
	        remoteSort: false,
	        singleSelect: true,
	        //rownumbers:true,
	        border: true,
	        columns:[ 
		       	[
					{field:'orderTypeName',title:'医嘱类型',sortable:true,align:'center',width:70,
						formatter:function(value,rec,index){
							return ['<input type="hidden" name="gr16List1[' + index + '].yzId" value="' + rec.id + '"/>' + 
							        '<input type="hidden" name="gr16List1[' + index + '].relid" value="' + rec.relid + '"/>' + 
							        '<input type="hidden" id="id_drugSituate_1_' + rec.id + '" name="gr16List1[' + index + '].isselect" value="' + (rec.isselect == null ? 0 : rec.isselect) + '"/>' + 
							        rec.orderTypeName].join('');
					    }
					},
		            {field:'orderName',title:'医嘱名称',sortable:true,width:140},
		            {field:'orderAt',title:'开嘱时间',sortable:true,align:'center',width:125},
		            {field:'stopAt',title:'停嘱时间',sortable:true,align:'center',width:125},
		            {field:'dosage',title:'剂量',sortable:true,align:'center',width:50},
		            {field:'sypc',title:'频次',sortable:true,align:'center',width:50},
		            {field:'adminRouteName',title:'给药路径',sortable:true,align:'center',width:80},
		            {field:'preYymd',title:'用药目的',width:120,
		            	formatter:function(value,row,index){
                            return surgeryInfo.setPreYymdList(value, index, 'gr16List1');
                        }
		            },
		            {field:'dateSection',title:'时间段',width:120,
		            	formatter:function(value,row,index){
                            return surgeryInfo.setDateSectionBefore(value, index, 'gr16List1');
                        }
		            },
                    {field:'_operate',title:'操作',width:40,
						formatter : function(value,row){
							return ['<a href="javascript:surgeryInfo.del(1,\'',row.relid,'\');" class="ico_del" title="删除"></a>'].join('');
						}
                    }
		        ]
	        ],
	        rownumbers:true
	    });
		
		
		
		//术后用药情况
		$('#id_postoperation').datagrid({
	        fit: false,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url:'${webroot}/st004Yzxxb/f_json/findDrugSituation.shtml',
	        queryParams: {
            	'operTypeId':'3',
            	'isselect':'1',
            	'refid':'${st005Ssxxb.relid}',
            	'zyid':'${st005Ssxxb.zyid}',
            	'operAt':surgeryInfo.operAt
            },
	        remoteSort: false,
	        singleSelect: true,
	        border:true,
	        columns:[ 
		       	[
					{field:'orderTypeName',title:'医嘱类型',sortable:true,align:'center',width:70,
						formatter:function(value,rec,index){
							return ['<input type="hidden" name="gr16List3[' + index + '].yzId" value="' + rec.id + '"/>' + 
							        '<input type="hidden" name="gr16List3[' + index + '].relid" value="' + rec.relid + '"/>' + 
							        '<input type="hidden" id="id_drugSituate_3_' + rec.id + '" name="gr16List3[' + index + '].isselect" value="' + (rec.isselect == null ? 0 : rec.isselect) + '"/>' + 
							        rec.orderTypeName].join('');
					    }
					},
		            {field:'orderName',title:'医嘱名称',sortable:true,width:140},
		            {field:'orderAt',title:'开嘱时间',sortable:true,align:'center',width:125},
		            {field:'stopAt',title:'停嘱时间',sortable:true,align:'center',width:125},
		            {field:'dosage',title:'剂量',sortable:true,align:'center',width:50},
		            {field:'sypc',title:'频次',sortable:true,align:'center',width:50},
		            {field:'adminRouteName',title:'给药路径',sortable:true,align:'center',width:80},
		            {field:'preYymd',title:'用药目的',width:120,
		            	formatter:function(value,row,index){
                            return surgeryInfo.setPreYymdList(value, index, 'gr16List3');
                        }
		            },
		            {field:'dateSection',title:'时间段',width:120,
		            	formatter:function(value,row,index){
		            		return surgeryInfo.setDateSectionAfter(value, index, 'gr16List3');
                        }
		            },
                    {field:'_operate',title:'操作',width:40,
						formatter : function(value,row){
							return ['<a href="javascript:surgeryInfo.del(3,\'',row.relid,'\');" class="ico_del" title="删除"></a>'].join('');
						}
                    }
		        ]
	        ],
	        rownumbers:true	        
	    });
		
		//科室
		Csm.combogrid.dep({
			id: 'id_dept',
			value: '${st005Ssxxb.deptId}',
			ifcaseoffice: '1',
			//【可选参数】1:回调，0:不回调，不传默认回调
			callback: '0',
			required:true,
			onClickRow : function(index,row){
				$('#id_deptName').val(row.deptName);
			}
		});
		
		//手术
		Csm.combogrid.icd9({
			id: 'id_operId',
			value: '${st005Ssxxb.operId}',
			//【可选参数】1:回调，0:不回调，不传默认回调
			callback: '0',
			required:true,
			onClickRow : function(index,row){
				$('#id_operName').val(row.operaCnname);
			}
		});
		
		//手术操作类别
		
		
		//手术医生
		Csm.combogrid.doctor({
			id: 'id_opedocId',
			value: '${st005Ssxxb.opedocId}',
			//【可选参数】1:回调，0:不回调，不传默认回调
			callback: '0',
			<c:if test="${fn:contains(inputRequired,'opedocName')}">required:true,</c:if>
			onClickRow : function(index,row){
				$('#id_opedocName').val(row.employeeName);
			}
		});
		
		//重点监测手术
		Csm.combogrid.sysDic({
			id: 'id_impOpeId',
			value: '${st005Ssxxb.impOpeId}',
			mode:'',
			dictTypeCode : 'operation_point',
			onClickRow : function(index,row){
				$('#id_impOpeName').val(row.dictName);
			}
		});
		
		//麻醉医师
		Csm.combogrid.doctor({
			id: 'id_anesDrId',
			value: '${st005Ssxxb.anesDrId}',
			//【可选参数】1:回调，0:不回调，不传默认回调
			callback: '0',
			onClickRow : function(index,row){
				$('#id_impOpeName').val(row.employeeName);
			}
		});
		
		//巡回护士
		Csm.combogrid.doctor({
			id: 'id_circuitNurseId',
			value: '${st005Ssxxb.circuitNurse}',
			idField: 'employeeName',
			cclass: '护士',
			//【可选参数】1:回调，0:不回调，不传默认回调
			callback: '0',
			onClickRow : function(index,row){
				$('#id_circuitNurse').val(row.employeeName);
			}
		});
		
		//洗手护士
		Csm.combogrid.doctor({
			id: 'id_scrubNurseId',
			value: '${st005Ssxxb.scrubNurse}',
			idField: 'employeeName',
			cclass: '护士',
			//【可选参数】1:回调，0:不回调，不传默认回调
			callback: '0',
			onClickRow : function(index,row){
				$('#id_scrubNurse').val(row.employeeName);
			}
		});
		
		//手术操作分类
		Csm.combogrid.sysDic({
			id: 'id_opepartkindid',
			value: '${st005Ssxxb.opepartkindid}',
			//required:true,
			dictTypeCode : 'icd9_opekind',
			onClickRow : function(index,row){
				$('#id_partkindname').val(row.dictName);
			}
		});
	});
	
	/**显示NNIS评分规则**/
	$(function(){
        $('.ico_help').tooltip({
            position: 'top',
            content: '<div style="width:450px; padding:10px;"><p style="text-indent:24px;">手术风险分为四级。具体计算方法是将手术切口清洁程度、麻醉分级和手术持续时间的分值相加，总分0分为NNIS-0级，1分为NNIS-1级、2分为NNIS-2级，3分为NNIS-3级。</p><p class="mt10">分值分配：</p><table class="tableCsm"><tr><td>分值</td><td>手术切口</td><td>麻醉分级</td><td>手术持续时间</td></tr><tr><td>0分</td><td>Ⅰ类切口、Ⅱ类切口</td><td>P1、P2</td><td>未超出75%分位</td></tr><tr><td>1分</td><td>Ⅲ类切口、Ⅳ类切口</td><td>P3、P4、P5</td><td>超出75%分位</td></tr></table></div>',
            onShow: function(){
                $(this).tooltip('tip').css({
                    backgroundColor: '#fafafa',
                    borderColor: '#ccc'
                });
            }
        });
	 });
</script>
</body>
</html>