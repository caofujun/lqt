<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>居民肿瘤病例报告卡</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css${version}" />
<style type="text/css">
	.mainTable{width: 100%; margin:10px 0px;}
	.mainTable td{padding: 2px; color:#333;}
	.piece{border: 1px solid #ddd;padding: 5px;padding-bottom:20px; width: initial;margin: 0px;}
	.pieceTitle{padding: 3px;border-left: 1px solid #ddd;border-right: 1px solid #ddd;}
	.success{text-align: center; font-size: 16px; border: 1px solid #0fbb0f; background-color: rgb(233, 251, 221);
    border-radius: 5px; padding: 10px; margin: 5px 10px; color: #3e9c06;}
    .tabs-header{border-top-width:0px;border-left-width:0px;}
    .tabs-panels{border-left-width:0px;border-bottom-width:0px;}
    input[type='text']{width: 130px;}
</style>
<script type="text/javascript" src="${webroot}/resources/js/idcard.check.js"></script>
</head>
<body style="width: 100%;">
<div id="NoEdit" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
	<c:if test="${!empty errMsg}">
		<div class="errTip">${errMsg}</div>
	</c:if>
	<c:if test="${!empty tipMsg}">
		<div class="success">${tipMsg}</div>
	</c:if>
	<form id="zlbk">
		<div style="margin: 60px 5%;margin-top:10px; width: 90%;">
			<center><h1>居民肿瘤病例报告卡</h1></center>
			<div style="width: 100%;">		
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">患者基本信息</span>
						<input type="hidden" name="masterid" id="masterid" value="${zlxx.masterid}" />
						<input type="hidden" name="isemptycard" id="isemptycard" 
						<c:choose>
							<c:when test="${!empty zlxx}">
								value="${zlxx.isemptycard}" 
							</c:when>
							<c:when test="${isEmptyCard eq 'Y'}">
								value="1"
							</c:when>
							<c:otherwise>
								value="0" 
							</c:otherwise>
						</c:choose> />
						<input type="hidden" id="flag" name="flag" value="${zlxx.flag}" />
						<input type="hidden" id="validpersonid" name="validpersonid" value="${zlxx.validpersonid}"/>
						<input type="hidden" id="validpersonname" name="validpersonname" value="${zlxx.validpersonname}"/>
						<input type="hidden" id="validdt" name="validdt" value='<fmt:formatDate value="${zlxx.validdt}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
					</div>
					<table class="mainTable" >
						<c:if test="${isEmptyCard eq 'Y' || syxx.isemptycard==1}">
						<tr>
							<td class="rightTextAlign" style="color:#3e9c06;">注：</td>
							<td colspan="5"><div style="color:#3e9c06;">门诊号/住院号/病例号可填身份证号</div></td>
						</tr>
						</c:if>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>报卡类别：</td>
							<td style="width:22%;">
								<label style="margin-right:20px;">
									<input type="radio" name="cardType" value="发病卡" <c:if test="${zlxx.cardType eq '发病卡'}"> checked="checked" </c:if> />发病卡</label>
								<label>
									<input type="radio" name="cardType" value="死亡卡" <c:if test="${zlxx.cardType eq '死亡卡'}"> checked="checked" </c:if> class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=cardType]\', \'报卡类别\']'" />死亡卡</label>
							</td>
							<td class="rightTextAlign">编号：</td>
							<td style="width: 250px;">
								<input type="text" name="cardid" id="cardid" value="${zlxx.cardid}"/>
							</td>
							<td></td><td></td>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>ICD-10编码：</td>
							<td>
								<input type="text" id="icd10Id" name="icd10Id" class="easyui-validatebox" required="true" onclick="showICDdialog('icd10Id','icd10Name');" value="${zlxx.icd10Id}" readonly="readonly"/>
								<input type="hidden" id="icd10Name" name="icd10Name" value="${zlxx.icd10Name}"/>
							</td>
							<td class="rightTextAlign">ICD-0-3编码：</td>
							<td>
								<input type="text" id="icd0Id" name="icd0Id" onclick="showICD0dialog('icd0Id','icd0Name');" value="${zlxx.icd0Id}" readonly="readonly"/>
								<input type="hidden" id="icd0Name" name="icd0Name" value="${zlxx.icd0Name}"/>
							</td>
							<td></td><td></td>
						</tr>
						<tr>
							<td class="rightTextAlign">门诊号：</td>
							<td>
								<input type="text" id="mzid" name="mzid"  <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if>
								<c:choose>
									<c:when test="${patientType eq 'mz' }">
										value="${!empty zlxx.mzid?zlxx.mzid:BRXX.mzid}" 
										<c:if test="${empty isEmptyCard}">
											class="easyui-validatebox" required="true"
										</c:if>
									</c:when>
								</c:choose>	/>
							</td>
							<td class="rightTextAlign">住院号：</td>
							<td>
								<input type="text" id="zyid" name="zyid" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if>
								<c:choose>
									<c:when test="${patientType eq 'zy' }">
										value="${!empty zlxx.zyid?zlxx.zyid:BRXX.zyid}" 
										<c:if test="${empty isEmptyCard}">
											class="easyui-validatebox" required="true"
										</c:if>
									</c:when>
								</c:choose> />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>患者姓名</td>
							<td>
								<input type="text" id="patientName" name="patientName" class="easyui-validatebox" required="true" value="<c:if test="${! empty BRXX}">${BRXX.patientName}</c:if><c:if test="${! empty zlxx}">${zlxx.patientName}</c:if>"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>身份证号：</td>
							<td>
								<input type="text" name="idNo" id="idNo" style="float:left;" class="easyui-validatebox" data-options="required:true,validType:'idcared[1]'" 
								<c:choose>
									<c:when test="${!empty zlxx}">
										value="${zlxx.idNo}"
									</c:when>
									<c:otherwise>
										value='${(patientType eq 'zy'?BRXX.idCard:BRXX.idnumber)}'
									</c:otherwise>
								</c:choose> />
								<!-- <input type="button" class="butt" value="提取性别和生日" onclick="getInfoByID();"/> -->
								<a href="javascript:;" class="tqxx" title="提取性别和生日" onclick="getInfoByID();"></a>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>患者性别：</td>
							<td>
								<label style="padding-right: 10px;">
									<input type="radio" name="sex" id="nan" value="男"
									<c:choose>
										<c:when test="${zlxx.sex eq '男'}">
											checked="checked"
										</c:when>
										<c:when test="${BRXX.sex eq '男'}">
											checked="checked"
										</c:when>
									</c:choose> class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=sex]\', \'性别\']'"/>男</label>
								<label style="padding-right: 10px;">
									<input type="radio" name="sex" id="nv" value="女" 
									<c:choose>
										<c:when test="${zlxx.sex eq '女'}">
											checked="checked"
										</c:when>
										<c:when test="${BRXX.sex eq '女'}">
											checked="checked"
										</c:when>
									</c:choose> />女</label>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>出生日期：</td>
							<td><input type="text" style="width: 130px;" name="birthday" id="birthday" onchange="$('#age').val(ages($(this).val()));$('#ageunit').val('岁')" class="Wdate text easyui-validatebox" required="true" onclick='WdatePicker({dateFmt:"yyyy-MM-dd",maxDate:"<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"})'
							<c:choose>
								<c:when test="${!empty zlxx}">
									value='<fmt:formatDate value="${zlxx.birthday}" pattern="yyyy-MM-dd" />'
								</c:when>
								<c:otherwise>
									value='<fmt:formatDate value="${(patientType eq 'zy'?BRXX.birthDate:BRXX.birthday)}" pattern="yyyy-MM-dd" />'
								</c:otherwise>
							</c:choose>
							/></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>实足年龄：</td>
							<td>
								<input type="text" name="age" id="age" class="easyui-validatebox" required="true" style="width:66px;" onkeyup="this.value=this.value.replace(/\D/g,'')"
									<c:choose>
										<c:when test="${!empty zlxx}">
											value="${zlxx.age}"
										</c:when>
										<c:otherwise>
											value='${BRXX.age}'
										</c:otherwise>
									</c:choose> />
								<select style="width:60px;" name="ageUnit" id="ageunit" class="easyui-combobox">
									<option value="岁" 
									<c:choose>
										<c:when test="${!empty zlxx && zlxx.ageUnit eq '岁'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '岁'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose> >岁</option>
									<option value="月" 
									<c:choose>
										<c:when test="${!empty zlxx && zlxx.ageUnit eq '月'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '月'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose>	>月</option>
									<option value="天" 
									<c:choose>
										<c:when test="${!empty zlxx && zlxx.ageUnit eq '天'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '天'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose> >天</option>
								</select>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>联系电话：</td>
							<td>
								<input type="text" name="tel" id="tel" class="easyui-validatebox" data-options="required:true"
								<c:choose>
									<c:when test="${!empty zlxx}">
										value="${zlxx.tel}"
									</c:when>
									<c:otherwise>
										value="${BRXX.tel}"
									</c:otherwise>
								</c:choose> />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>联系人：</td>
							<td>
								<input type="text" name="kinsfolkTel" id="kinsfolkTel" class="easyui-validatebox" data-options="required:true"
								<c:choose>
									<c:when test="${!empty zlxx}">
										value="${zlxx.kinsfolkTel}"
									</c:when> 
								</c:choose> />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>婚姻状况：</td>
							<td colspan="3">
								<c:forEach items="${marital}" var="mar" >
									<label style="margin-right: 20px;"><input type="radio" name="marriage" value="${mar.dictCode}" class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=marriage]\', \'婚姻状况\']'" <c:if test="${zlxx.marriage eq mar.dictCode}"> checked="checked" </c:if> />${mar.dictName}</label>
								</c:forEach>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>民族：</td>
							<td>
								<select style="width: 142px;" id="nation"  name="nation" class="easyui-combobox" data-options="required:true">
									<option></option>
									<c:forEach items="${nation}" var="nation">
										<option value="${nation.dictName }" <c:if test="${zlxx.nation==nation.dictName}">selected="selected"</c:if> >${nation.dictName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>文化程度：</td>
							<td colspan="3">
								<c:forEach items="${education}" var="edu" >
									<label style="margin-right: 20px;"><input type="radio" name="education" class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=education]\', \'文化程度\']'" value="${edu.dictCode}" <c:if test="${zlxx.education eq edu.dictCode}"> checked="checked" </c:if> />${edu.dictName}</label>
								</c:forEach>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>职业：</td>
							<td>
								<select id="profession" name="profession" style="width:200px;" class="easyui-combobox" data-options="required:true">
									<option></option>
									<c:forEach items="${profession}" var="prof" >
										<option value="${prof.dictName}" <c:if test="${zlxx.profession==prof.dictName}">selected="selected"</c:if> >${prof.dictName}</option>
									</c:forEach>
								</select>(具体到工种)
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">工作单位：</td>
							<td colspan="5"><input type="text" style="width:88%" id="unit" name="unit" value="${zlxx.unit}"/> </td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>户口地址：</td>
							<td colspan="5">
								<select style="width: 12%;" id="regsheng" class="easyui-combobox" data-options="required:true" ><option></option></select>省
								<select style="width: 12%;" id="regshi" class="easyui-combobox" data-options="required:true" ><option></option></select>市
								<select style="width: 12%;" id="regxian" class="easyui-combobox" data-options="required:true" ><option></option></select>县（区）
								<select style="width: 12%;" id="regxiang" class="easyui-combobox" data-options="required:true" ><option></option></select>乡（街道）
								<input type="text" style="width: 12%;" name="registeraddrVillage" id="registeraddrVillage" value="${zlxx.registeraddrVillage}" onblur="publicfullAddress('reg','regxiang');"/>村
								<input type="text" style="width: 12%;" name="registeraddrGroup" value="${zlxx.registeraddrGroup}" id="registeraddrGroup" onblur="publicfullAddress('reg','regxiang');"/>组
								
								<input type="hidden" id="registerareacode" name="registerareacode" value="${zlxx.registerareacode}"/><!-- 编码 -->
								<input type="hidden" id="registerareaaddr" name="registerareaaddr" value="${zlxx.registerareaaddr}"/><!-- 完整的住址 -->
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="5">
								<input type="text" id="registerfulladdr" name="registerfulladdr" style="width:88%;"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>现住址：</td>
							<td colspan="5">
								<select style="width: 12%;" name="province" id="sheng" class="easyui-combobox" data-options="required:true" ><option></option></select>省
								<select style="width: 12%;" name="city" id="shi" class="easyui-combobox" data-options="required:true" ><option></option></select>市
								<select style="width: 12%;" name="country" id="xian" class="easyui-combobox" data-options="required:true" ><option></option></select>县（区）
								<select style="width: 12%;" id="xiang" class="easyui-combobox" data-options="required:true" ><option></option></select>乡（街道）
								<input type="text" style="width: 12%;" name="nowaddrVillage" id="nowaddrVillage" value="${zlxx.nowaddrVillage}" onblur="publicfullAddress('now','xiang');"/>村
								<input type="text" style="width: 12%;" name="nowaddrGroup" value="${zlxx.nowaddrGroup}" id="nowaddrGroup" onblur="publicfullAddress('now','xiang');"/>组
								
								<input type="hidden" id="nowaddrareacode" name="nowaddrareacode" value="${zlxx.nowaddrareacode}"/><!-- 编码 -->
								<input type="hidden" id="nowaddr" name="nowaddr" value="${zlxx.nowaddr}"/><!-- 完整的住址 -->
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="5">
								<input type="text" id="nowfulladdr" name="nowfulladdr" style="width:88%;"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>入院日期：</td>
							<td>
								<input type="text" name="inHospitalDt" id="inHospitalDt" readonly="readonly" class="Wdate text easyui-validatebox" data-options="required:true" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" 
									<c:choose>
										<c:when test="${!empty zlxx}">
											value='<fmt:formatDate value="${zlxx.inHospitalDt}" pattern="yyyy-MM-dd HH:mm:ss" />'
										</c:when>
										<c:when test="${patientType eq 'zy' }">
											value='<fmt:formatDate value="${BRXX.inHospAt}" pattern="yyyy-MM-dd HH:mm:ss" />'
										</c:when>
									</c:choose>
								/> 
							</td>
							<td class="rightTextAlign">发病（就诊）日期：</td>
							<td>
								<input type="text" name="startDt" id="startDt" readonly="readonly" class="Wdate text" style="width: 130px;" value='<fmt:formatDate value="${zlxx.startDt}" pattern="yyyy-MM-dd HH:mm:ss"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value='${now}' pattern="yyyy-MM-dd HH:mm:ss"/>'})" /> 
							</td>
							<td class="rightTextAlign">治疗方法：</td>
							<td>
								<input type="text" name="cureWay" id="cureWay" value="${zlxx.cureWay}"/> 
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">治疗医院：</td>
							<td><input type="text" name="cureHospital" id="cureHospital" value="${zlxx.cureHospital }"/></td>
							<td class="rightTextAlign">手术日期：</td>
							<td>
								<input type="text" name="operationDt" id="operationDt" readonly="readonly" class="Wdate text" style="width: 130px;" value='<fmt:formatDate value="${zlxx.operationDt}" pattern="yyyy-MM-dd HH:mm:ss"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" type="both"/>'})" />
							</td>
							<td class="rightTextAlign">是否病检：</td>
							<td>
								<label style="margin-right: 20px;"><input type="radio" name="diseaseCheck" value="是" <c:if test="${zlxx.diseaseCheck eq '是'}"> checked="checked" </c:if>/>是</label>
								<label><input type="radio" name="diseaseCheck" value="否" <c:if test="${zlxx.diseaseCheck eq '否'}"> checked="checked" </c:if>/>否</label>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">诊断部位及亚部位：</td>
							<td colspan="3">
								<input type="text" id="diagnosisPart" name="diagnosisPart" style="width:82%;" value="${zlxx.diagnosisPart}"/>
							</td>
							<td></td><td></td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">更正诊断报告栏</span>
					</div>
					<table class="mainTable" >
						<tr>
							<td class="rightTextAlign" style="width:144px;">原诊断：</td>
							<td colspan="3">
								<input type="text" style="width:100%" id="oldDiagnose" name="oldDiagnose" value="${zlxx.oldDiagnose}"/>
							</td>
							<td class="rightTextAlign">原诊断日期：</td>
							<td>
								<input type="text" name="oldDiagnosedDate" id="oldDiagnosedDate" readonly="readonly" class="Wdate text" style="width: 130px;" value='<fmt:formatDate value="${zlxx.oldDiagnosedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" />
							</td>
						</tr>
					</table>
				</div>  
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">诊断信息</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign" style="width:144px;"><span class="red">*</span>诊断：</td>
							<td colspan="3">
								<input type="text" style="width:100%" id="diagnose" name="diagnose" class="easyui-validatebox" required="true" value="${zlxx.diagnose }" />
							</td>
							<td></td><td></td>
						</tr>
						<tr>
							<td class="rightTextAlign">病理学类型：</td>
							<td style="width:22%;">
								<input type="text" name="pathologyName" id="pathologyName" value="${zlxx.pathologyName}"/>
							</td>
							<td class="rightTextAlign" style="width:100px;">病理号：</td>
							<td style="width:22%;">
								<input type="text" name="pathologyId" id="pathologyId" readonly="readonly" onclick="showICD0dialog('pathologyId','pathologyName');" value="${zlxx.pathologyId}"/>
							</td>
							<td class="rightTextAlign" style="width:110px;">确诊时期别：</td>
							<td>
								T<input type="text" id="timeT" name="timeT" style="width:50px;" value="${zlxx.timeT}"/>N<input type="text" id="timeN" name="timeN" style="width:50px;" value="${zlxx.timeN}"/>M<input type="text" id="timeM" name="timeM" style="width:50px;" value="${zlxx.timeM}"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>临床分期：</td>
							<td colspan="3">
								<label style="margin-right:20px;"><input type="radio" name="clinicalStages" value="0-I期" <c:if test="${zlxx.clinicalStages eq '0-I期'}">checked="checked"</c:if> class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=clinicalStages]\', \'临床分期\']'" />0-I期</label>
								<label style="margin-right:20px;"><input type="radio" name="clinicalStages" value="II期" <c:if test="${zlxx.clinicalStages eq 'II期'}">checked="checked"</c:if> />II期</label>
								<label style="margin-right:20px;"><input type="radio" name="clinicalStages" value="III期" <c:if test="${zlxx.clinicalStages eq 'III期'}">checked="checked"</c:if> />III期</label>
								<label style="margin-right:20px;"><input type="radio" name="clinicalStages" value="IV期" <c:if test="${zlxx.clinicalStages eq 'IV期'}">checked="checked"</c:if> />IV期</label>
								<label style="margin-right:20px;"><input type="radio" name="clinicalStages" value="无法判定" <c:if test="${zlxx.clinicalStages eq '无法判定'}">checked="checked"</c:if> />无法判定</label>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>首次诊断日期：</td>
							<td>
								<input type="text" name="diagnosedDate" id="diagnosedDate" readonly="readonly" class="Wdate text easyui-validatebox" required="true" style="width: 130px;" value='<fmt:formatDate value="${zlxx.diagnosedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">诊断单位：</td>
							<td colspan="3">
								<input type="text" id="diagnosedUnit" style="width:100%" name="diagnosedUnit" value="${zlxx.diagnosedUnit}"/>
							</td>
							<td></td><td></td>
						</tr>
						<tr>
							<td class="rightTextAlign">死亡日期：</td>
							<td>
								<input type="text" name="deathdate" id="deathdate" readonly="readonly" class="Wdate text " style="width: 130px;" value='<fmt:formatDate value="${zlxx.deathdate}" pattern="yyyy-MM-dd HH:mm:ss"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" />
							</td>
							<td class="rightTextAlign">死亡原因：</td>
							<td>
								<input type="text" id="deathCauses" name="deathCauses" value="${zlxx.deathCauses}"/>
							</td>
							<td class="rightTextAlign">死亡地点：</td>
							<td>
								<label style="margin-right:20px;"><input type="radio" value="医院" name="deathAddr" <c:if test="${zlxx.deathAddr eq '医院'}"> checked="checked"</c:if> />医院</label>
								<label style="margin-right:20px;"><input type="radio" value="家中" name="deathAddr" <c:if test="${zlxx.deathAddr eq '家中'}"> checked="checked"</c:if> />家中</label>
								<label style="margin-right:20px;"><input type="radio" value="不详" name="deathAddr" <c:if test="${zlxx.deathAddr eq '不详'}"> checked="checked"</c:if> />不详</label>
							</td>
						</tr>
					</table>
				</div>                                    
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">诊断依据<span class="red">*</span></span>
						<input type="hidden" id="diagnosticBasis" name="diagnosticBasis" value="${zlxx.diagnosticBasis}"/>
					</div>
					<table class="mainTable" >
						<tr>
							<td style="width:18%;"></td>
							<td style="width:15%;">
								<label><input type="checkbox" name="zdyj" value="临床" />临床</label>
							</td>
							<td style="width:15%;">
								<label><input type="checkbox" name="zdyj" value="内窥镜"/>内窥镜</label>
							</td>
							<td style="width:15%;">	
								<label><input type="checkbox" name="zdyj" value="免疫"/>免疫</label>
							</td>
							<td style="width:15%;">	
								<label><input type="checkbox" name="zdyj" value="病理（原发）"/>病理（原发）</label>
							</td>
							<td ></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<label><input type="checkbox" name="zdyj" value="CT"/>CT</label>
							</td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="手术"/>手术</label>
							</td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="细胞学"/>细胞学</label>
							</td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="尸检（有病理）"/>尸检（有病理）</label>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="X线"/>X线</label>
							</td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="尸检（无病理）"/>尸检（无病理）</label>
							</td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="血片"/>血片</label>
							</td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="不详"/>不详</label>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="超声波"/>超声波</label>
							</td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="生化"/>生化</label>
							</td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="病理（继发）"/>病理（继发）</label>
							</td>
							<td>	
								<label><input type="checkbox" name="zdyj" value="死亡补发病"/>死亡补发病</label>
							</td>
							<td></td>
						</tr>
					</table>
				</div> 
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">填报机构信息</span>
					</div>
					<table class="mainTable" >
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>报告单位：</td>
							<td>
								<input type="text" id="reportdeptid" name="reportdeptid" class="easyui-combobox" value="${zlxx.reportdeptid}"/>
								<input type="hidden" name="reportdeptname" id="reportdeptname" value="${zlxx.reportdeptname }"/>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>报告医师：</td>
							<td>
								<input type="text" id="reportdoctorid" name="reportdoctorid" class="easyui-combobox" value="${zlxx.reportdoctorid }"/>
								<input type="hidden" name="reportdoctorname" id="reportdoctorname" value="${zlxx.reportdoctorname}"/>
							</td>
							<td class="fillDateTime rightTextAlign" style="display: none;"><span class="red">*</span>报告日期：</td>
							<td style="display: none;" class="fillDateTime syjcfilldate">
								<input type="text" name="reportdt" id="filldate" readonly="readonly" class="Wdate text <c:if test="${acType eq 'hospital'}">easyui-validatebox</c:if>" <c:if test="${acType eq 'hospital'}">required="true"</c:if> style="width: 130px;" 
								<c:choose>
									<c:when test="${!empty zlxx}">
										value='<fmt:formatDate value="${zlxx.reportdt}" pattern="yyyy-MM-dd HH:mm:ss"/>' 
									</c:when>
									<c:otherwise>
										value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>' 
									</c:otherwise>
								</c:choose>
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" /> 
							</td>
						</tr>
					</table>
				</div> 
			</div>
<%-- 			<div class="footer">
			<div  class="footer_btn" style="display: none;">			
				<div class="n_btn_grey">
					<a href="javascript:;" class="no_ico"><span>稍后再报</span></a>
				</div>
			</div>
			<div class="footer_btn">
				<c:if test="${acType eq 'hospital'}">
					<c:if test="${zlxx.flag==0}">
						<div class="n_btn_blue" id="upload-main">
							<a href="javascript:saveForm();" id="upload" class="no_ico"><span>上报</span></a>
						</div>
					</c:if>
				</c:if>
				<c:if test="${acType eq 'doctor'}">
					<c:if test="${empty zlxx || zlxx.flag==2}">
						<div class="n_btn_blue"  id="upload-main">
							<a href="javascript:saveForm();" id="upload" class="no_ico"><span>上报</span></a>
						</div>
					</c:if>
				</c:if>
				<div class="n_btn_grey">
					<a href="javascript:back();" class="no_ico"><span>取消</span></a>
				</div>
			</div>
		</div> --%>
		<div class="footer">
			<%@ include file="/WEB-INF/view/cards/includepages/bkOpts.jsp"%>
		</div>
		</div>
	</form>
	<div id="chooseICD">
		<div id="distool">
			<input id="icdKey" type="text" style="width: 100%;height:28px;" class="easyui-textbox" data-options="prompt:'输入ICD编号/ICD名称/助记码检索'" />
		</div>
		<table id="icdTable" style="height:400px;"></table>
	</div>
	<div id="chooseICD0">
		<div id="distool0">
			<input id="icd0Key" type="text" style="width: 100%;height:28px;" class="easyui-textbox" data-options="prompt:'输入ICD编号/ICD名称/助记码检索'" />
		</div>
		<table id="icd0Table" style="height:400px;"></table>
	</div>
<script type="text/javascript">
$(function(){
	<c:if test="${acType eq 'hospital'}">
		$(".fillDateTime").show();
		<c:if test="${!empty zlxx.flag && zlxx.flag!=0}">
			$("#NoEdit").css("height",$("#zlbk").height());
		</c:if>
	</c:if>
	<c:if test="${acType eq 'doctor'}">
		<c:if test="${!empty zlxx && zlxx.flag!=2}">
		$("#NoEdit").css("height",$("#zlbk").height());
		</c:if>
	</c:if>
	
	fillData();
	
	//ICD选择框
	$("#chooseICD").dialog({
		title: "请选择ICD10 <span style='font-size:12px;font-weight:normal;color:#e25050;'>(双击确定选择)</span>",
		width: 510,
	    height: 498,
		closed: true,
	    cache: false,
	    modal: true,
	    onClose:function(){
			$("#icdKey").textbox("setValue","");
		}
	});
	//ICD0选择框
	$("#chooseICD0").dialog({
		title: "请选择ICD0 <span style='font-size:12px;font-weight:normal;color:#e25050;'>(双击确定选择)</span>",
		width: 510,
	    height: 498,
		closed: true,
	    cache: false,
	    modal: true,
	    onClose:function(){
			$("#icd0Key").textbox("setValue","");
		}
	});
	//自动搜索
	$("input",$("#icdKey").next("span")).keyup(function(){
    	timedCount();
    });
	//自动搜索
	$("input",$("#icd0Key").next("span")).keyup(function(){
    	timedCount0();
    });
	
	//地址联动
	/* $("#sheng").change(function () { 
		get_public_shi("now","sheng","shi","xian","xiang",null);
    });
    $("#shi").change(function () { 
    	get_public_xian("now","sheng","shi","xian","xiang",null);	
    });
    $("#xian").change(function () { 
    	get_public_xiang("now","sheng","shi","xian","xiang",null);
    	publicfullAddress("now","xiang");
    });
    $("#xiang").change(function () { 
    	publicfullAddress("now","xiang");
    }); */
    $("#sheng").combobox({
		onSelect : function(r){
			get_public_shi("now","sheng","shi","xian","xiang",null);
		}
	});
	$("#shi").combobox({
		onSelect : function(r){
			get_public_xian("now","sheng","shi","xian","xiang",null);	
		}
	});
	$("#xian").combobox({
		onSelect : function(r){
			get_public_xiang("now","sheng","shi","xian","xiang",null);
	    	publicfullAddress("now","xiang");
		}
	});
	$("#xiang").combobox({
		onSelect : function(r){
			publicfullAddress("now","xiang");
		}
	});

    //户籍地址
	/* $("#regsheng").change(function () { 
		get_public_shi("reg","regsheng","regshi","regxian","regxiang",null);
    });
    $("#regshi").change(function () { 
    	get_public_xian("reg","regsheng","regshi","regxian","regxiang",null);
    });
    $("#regxian").change(function () { 
    	get_public_xiang("reg","regsheng","regshi","regxian","regxiang",null);
    });
    $("#regxiang").change(function () { 
    	publicfullAddress("reg","regxiang");
    }); */
    $("#regsheng").combobox({
		onSelect : function(r){
			get_public_shi("reg","regsheng","regshi","regxian","regxiang",null);
		}
	});
	$("#regshi").combobox({
		onSelect : function(r){
			get_public_xian("reg","regsheng","regshi","regxian","regxiang",null);
		}
	});
	$("#regxian").combobox({
		onSelect : function(r){
			get_public_xiang("reg","regsheng","regshi","regxian","regxiang",null);
		}
	});
	$("#regxiang").combobox({
		onSelect : function(r){
			publicfullAddress("reg","regxiang");
		}
	});
    
	
	get_public_sheng("now","sheng","shi","xian","xiang",null);
	get_public_sheng("reg","regsheng","regshi","regxian","regxiang",null);

	//
    $("[name='zdyj']:checkbox").click(function(){
    	get_zdyj_val();
    });
   
   //重新验证
   setTimeout("$('.easyui-validatebox').validatebox()",700);
 	//上报科室
	Csm.combogrid.dep({
		//【必传】控件名称
		id: 'reportdeptid',
		//【可选参数】不传默认区session的医院ID
		hospId: '',
		//【可选参数】不传默认区所有监控科室
		//dataType: 'zy',
		onClickRow:function(index,row){
			$("#reportdeptname").val(row['deptName']);
		},
		onLoadSuccess:function(data){
			if(data.total>0){
				if(""=="${zlxx.reportdeptid}"){
					//默认选中第一行
					<c:choose>
						<c:when test="${RDFD eq '1'}">
							$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${user.depNo}");
						</c:when>
						<c:when test="${patientType eq 'mz'}">
							$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${BRXX.deptId}");
						</c:when>
						<c:otherwise>
							$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${BRXX.deptCode}");
						</c:otherwise>
					</c:choose>
					//$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${user.depNo}");
				}else{
					$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${zlxx.reportdeptid}");
				}
				//有选中的就设置值
				var r = $('#reportdeptid').combogrid('grid').datagrid('getSelected');
				if(r){
					$('#reportdeptid').combogrid("setValue",r['deptId']);
					$("#reportdeptname").val(r['deptName']);
				}
			}else{
				$("#reportdeptname").val("");
			}
		}
	});
 	//上报医生
	Csm.combogrid.doctor({
		//【必传】控件名称
		id: 'reportdoctorid',
		//【可选参数】不传默认区session的医院ID
		hospId: '',
		//【可选参数】不传默认区所有监控科室
		onClickRow:function(index,row){
			$("#reportdoctorname").val(row['employeeName']);
		},
		<c:choose>
			<c:when test="${!empty zlxx}">
				value:"${zlxx.reportdoctorid}",
			</c:when>
			<c:when test="${acType eq 'hospital'}">
				value:"${user.username}",
			</c:when>
			<c:otherwise>
				value:"${user.docNo}",
			</c:otherwise>
		</c:choose>
		onLoadSuccess:function(data){
			if(data.total>0){
				//默认选中第一行
				if(!"${zlxx.reportdoctorid}"){
					$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${user.docNo}");
				}else{
					$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${zlxx.reportdoctorid}");
				}
				var r = $('#reportdoctorid').combogrid('grid').datagrid('getSelected');
				if(r){
					$('#reportdoctorid').combogrid("setValue",r['employeeId']);
					$("#reportdoctorname").val(r['employeeName']);
				}
			}else{
				$("#reportdoctorname").val("");
			}
		}
	});
 	
	<c:choose>
	   	<c:when test="${!empty zlxx}">
	   		setTimeout("$('#nowfulladdr').val('${zlxx.nowfulladdr}')",1000);
	   		setTimeout("$('#registerfulladdr').val('${zlxx.registerfulladdr}')",1000);
	   	</c:when>
	   	<c:when test="${patientType eq 'zy'}">
	   		<c:if test="${!empty BRXX.address}">
		   		setTimeout("$('#nowfulladdr').val('${BRXX.address}')",1000);
	   		</c:if>
	   	</c:when>
	   	<c:when test="${patientType eq 'mz'}">
		   	<c:if test="${!empty BRXX.presentaddr}">
		   		setTimeout("$('#nowfulladdr').val('${BRXX.presentaddr}')",1000);
	   		</c:if>
	   		<c:if test="${!empty BRXX.registeraddr}">
		   		setTimeout("$('#registerfulladdr').val('${BRXX.registeraddr}')",1000);
	   		</c:if>
	   	</c:when>
	</c:choose>
 	
	<c:if test="${empty zlxx}">
		getInfoByID();
	</c:if>
	
});

function get_public_sheng(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getSheng.shtml";
	var eatSheng = "${nowSheng}";
	var buySheng = "${regSheng}";
	$.getJSON(url,function (data) {
		$("#"+sheng).html('');
		var appendstr="";
		$.each(data, function (i, item) {  
  			if(type=="now"){
  				if(eatSheng){
	       			if(item.areacode==eatSheng){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}else{
	       			if(item.areacode=="${(HASheng)}"){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}
  			}else if(type=="reg"){
  				if(buySheng){
	       			if(item.areacode==buySheng){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}else{
	       			if(item.areacode=="${(HASheng)}"){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}
  			}
   		});
		$("#"+sheng).append(appendstr);
		if(type=="now"){
				if(eatSheng){
					$("#"+sheng).combobox({'value':eatSheng});
				}else{
					$("#"+sheng).combobox({'value':"${(HASheng)}"});
				}
		}else if(type=="reg"){
				if(buySheng){
					$("#"+sheng).combobox({'value':buySheng});
				}else{
					$("#"+sheng).combobox({'value':"${(HASheng)}"});
				}
		}
		get_public_shi(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_shi(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+sheng).combobox("getValue");
	var eatShi = "${nowShi}";
	var buyShi = "${regShi}";
	$.getJSON(url,function (data) {
		$("#"+shi).html('');
		var appendstr="";
		$.each(data, function (i, item) {   
			if(type=="now"){
  				if(eatShi){
	       			if(item.areacode==eatShi){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}else{
	       			if(item.areacode=="${(HAShi)}"){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}
  			}else if(type=="reg"){
  				if(buyShi){
	       			if(item.areacode==buyShi){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}else{
	       			if(item.areacode=="${(HAShi)}"){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}
  			}
   		});
		$("#"+shi).append(appendstr);
		if(type=="now"){
			if(eatShi){
				$("#"+shi).eq(0).combobox({'value':eatShi});
			}else{
				$("#"+shi).eq(0).combobox({'value':"${(HAShi)}"});
			}
		}else if(type=="reg"){
			if(buyShi){
				$("#"+shi).eq(0).combobox({'value':buyShi});
			}else{
				$("#"+shi).eq(0).combobox({'value':"${(HAShi)}"});
			}
		}
		get_public_xian(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_xian(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+shi).combobox("getValue");
	var eatXian = "${nowXian}";
	var buyXian = "${regXian}";
	$.getJSON(url,function (data) {
		$("#"+xian).html('');
		var appendstr="";
		$.each(data, function (i, item) {    
			if(type=="now"){
  				if(eatXian){
	       			if(item.areacode==eatXian){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}else{
	       			if(item.areacode=="${(HAXian)}"){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}
  			}else if(type=="reg"){
  				if(buyXian){
	       			if(item.areacode==buyXian){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}else{
	       			if(item.areacode=="${(HAXian)}"){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}
  			}
   		});
		$("#"+xian).append(appendstr);
		if(type=="now"){
			if(eatXian){
				$("#"+xian).combobox({'value':eatXian});
			}else{
				$("#"+xian).combobox({'value':"${(HAXian)}"});
			}
		}else if(type=="reg"){
			if(buyXian){
				$("#"+xian).combobox({'value':buyXian});
			}else{
				$("#"+xian).combobox({'value':"${(HAXian)}"});
			}
		}
		get_public_xiang(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_xiang(type,sheng,shi,xian,xiang,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+xian).combobox("getValue");
	var nowXiang = "${nowXiang}";
	var regXiang = "${regXiang}";
	$.getJSON(url,function (data) {
		$("#"+xiang).html('');
		var appendstr="";
		$.each(data, function (i, item) {    
			if(type=="now"){
  				if(nowXiang){
	       			if(item.areacode==nowXiang){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}else{
	       			if(item.areacode=="${(HAXiang)}"){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}
  			}else if(type=="reg"){
  				if(regXiang){
	       			if(item.areacode==regXiang){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}else{
	       			if(item.areacode=="${(HAXiang)}"){
	    		   		appendstr += "<option value='"+item.areacode+"'  selected='selected'>"+item.areacodevalue+"</option>";
	       			}else{
	       				appendstr += "<option value='"+item.areacode+"'>"+item.areacodevalue+"</option>";
	       			}
				}
  			}
   		});
		$("#"+xiang).append(appendstr);
		if(type=="now"){
			if(nowXiang){
				$("#"+xiang).combobox({'value':nowXiang});
			}else{
				$("#"+xiang).combobox({'value':"${(HAXiang)}"});
			}
		}else if(type=="reg"){
			if(regXiang){
				$("#"+xiang).combobox({'value':regXiang});
			}else{
				$("#"+xiang).combobox({'value':"${(HAXiang)}"});
			}
		}
		publicfullAddress(type,xiang);
	});
}
function publicfullAddress(type,xiang){
	var code = $("#"+xiang).combobox("getValue");
	if("now"==type){
		$("#nowaddrareacode").val(code);
		$("#nowaddr").val($("#sheng").combobox("getText")+$("#shi").combobox("getText")+$("#xian").combobox("getText")+$("#xiang").combobox("getText"));
		$("#nowfulladdr").val($("#sheng").combobox("getText")+$("#shi").combobox("getText")+$("#xian").combobox("getText")+$("#xiang").combobox("getText")+$("#nowaddrVillage").val()+$("#nowaddrGroup").val());
	}else{
		$("#registerareacode").val(code);
		$("#registerareaaddr").val($("#regsheng").combobox("getText")+$("#regshi").combobox("getText")+$("#regxian").combobox("getText")+$("#regxiang").combobox("getText"));
		$("#registerfulladdr").val($("#regsheng").combobox("getText")+$("#regshi").combobox("getText")+$("#regxian").combobox("getText")+$("#regxiang").combobox("getText")+$("#registeraddrVillage").val()+$("#registeraddrGroup").val());
	}
}
function showICDdialog(id,name){
	$('#icdTable').datagrid({
		url:"${webroot}/cdc/f_json/chooseICD.shtml",
		queryParams: {
        	'icdId':$("#icd10Id").val()
        },
		fitColumns:true,
		height:410,
		singleSelect:true,
		columns:[[
			{field:'icdCode',width:80,title:'ICD编号'},
			{field:'icdName',width:150,title:'ICD名称'},
			{field:'spCode',width:80,title:'首拼码'},
			{field:'wbCode',width:80,title:'五笔码'}
		]],
		onLoadSuccess: function (data) {
			if(!$("#icd10Id").val()){}else{
				$('#icdTable').datagrid("selectRow",0);
			}
		},
		onDblClickRow:function(index,row){
			$("#"+id).val(row['icdCode']);
			$("#"+name).val(row['icdName']);
			//重新调用表单验证
			$("#"+id).validatebox();
			$("#"+name).validatebox();
			$("#chooseICD").dialog("close");
			//$("input",$("#diseaseKey").next("span")).val("");
		},
		pagination:true
	});
	//居中判断
	var top = document.documentElement.scrollTop||document.body.scrollTop;
	var mtop = parseInt(($(window).height()-410)/2);
	if(mtop>0){
		top += mtop;
	}
	$('#chooseICD').window('open').window('resize',{top: top});
	queryICD();
}
function showICD0dialog(id,name){
	$('#icd0Table').datagrid({
		url:"${webroot}/cdc/f_json/chooseICD0.shtml",
		queryParams: {
        	'pathologyno':$("#icd0Id").val()
        },
		fitColumns:true,
		height:410,
		singleSelect:true,
		columns:[[
			{field:'pathologyno',width:80,title:'ICD0编号'},
			{field:'pathologyname',width:150,title:'ICD0名称'},
			{field:'pycode',width:80,title:'首拼码'},
			{field:'wbCode',width:80,title:'五笔码'}
		]],
		onLoadSuccess: function (data) {
			if(!$("#icd0Id").val()){}else{
				$('#icd0Table').datagrid("selectRow",0);
			}
		},
		onDblClickRow:function(index,row){
			$("#"+id).val(row['pathologyno']);
			$("#"+name).val(row['pathologyname']);
			//重新调用表单验证
			$("#"+id).validatebox();
			$("#"+name).validatebox();
			$("#chooseICD0").dialog("close");
			//$("input",$("#diseaseKey").next("span")).val("");
		},
		pagination:true
	});
	//居中判断
	var top = document.documentElement.scrollTop||document.body.scrollTop;
	var mtop = parseInt(($(window).height()-410)/2);
	if(mtop>0){
		top += mtop;
	}
	$('#chooseICD0').window('open').window('resize',{top: top});
	queryICD0();
}
function queryICD(){
	var act_url="${webroot}/cdc/f_json/chooseICD.shtml";
	var tv = $("input",$("#icdKey").next("span")).val();
	$('#icdTable').datagrid({
        url: act_url,
        queryParams: {
        	'icdId':$("#icd10Id").val(),
     		'searchString': (tv=="输入ICD编号/ICD名称/助记码检索"?"":tv)
        },
        method:"post",
        onLoadSuccess: function (data) {
        	if(!$("#icd10Id").val() || tv!="输入ICD编号/ICD名称/助记码检索"){}else{
				$('#icdTable').datagrid("selectRow",0);
			}
        }
    });
}
function queryICD0(){
	var act_url="${webroot}/cdc/f_json/chooseICD0.shtml";
	var tv = $("input",$("#icd0Key").next("span")).val();
	$('#icd0Table').datagrid({
        url: act_url,
        queryParams: {
        	'pathologyno':$("#icd0Id").val(),
     		'searchString': (tv=="输入ICD编号/ICD名称/助记码检索"?"":tv)
        },
        method:"post",
        onLoadSuccess: function (data) {
        	if(!$("#icd0Id").val() || tv!="输入ICD编号/ICD名称/助记码检索"){}else{
				$('#icd0Table').datagrid("selectRow",0);
			}
        }
    });
}
var t;
function timedCount() {
	if (t) {
	clearTimeout(t);
	}
	t = setTimeout(submitEvent, 1000);
}
function submitEvent() {
	queryICD();
}
var g;
function timedCount0() {
	if (g) {
	clearTimeout(g);
	}
	g = setTimeout(submitEvent0, 1000);
}
function submitEvent0() {
	queryICD0();
}
function getInfoByID(){
	var idcard = $("#idNo").val();
	if(idcard.length==18){
		//前17位是否是纯数字
		if(isNaN(idcard.substring(0,17))){
			$.messager.show({ title: '提示', msg: '提取信息失败：身份证号码不合法。' });
			return false;
		}
		//性别   奇数为男，偶数为女
		if(idcard.substring(16,17)%2==0){
			$("#nv").attr("checked","checked");
		}else{
			$("#nan").attr("checked","checked");
		}
		//身份证上生日
		var idbir=idcard.substring(6,10)+"-"+idcard.substring(10,12)+"-"+idcard.substring(12,14);
		$("#birthday").val(idbir);
		$("#age").val(ages(idbir));
		$.messager.show({ title: '提示', msg: '从身份证提取性别和生日信息成功！' });
	}else{
		$.messager.show({ title: '提示', msg: '提取信息失败：身份证号码为空或不是身份证号码。' });
	}
	
}
function ages(str){   
      var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
      if(r==null){
    	$.messager.show({ title: '提示', msg: '提取信息失败：无法获取生日日期' });
        return; 
      }   
      var returnAge=0;
      var strBirthdayArr=str.split("-");
      var birthYear = strBirthdayArr[0];
      var birthMonth = strBirthdayArr[1];
      var birthDay = strBirthdayArr[2];
      d = new Date();
      var nowYear = d.getFullYear();
      var nowMonth = d.getMonth() + 1;
      var nowDay = d.getDate();
      if(nowYear == birthYear){
    	return 0;
      }else{
		var ageDiff = nowYear - birthYear ; //年之差
		if(ageDiff > 0){	
   			if(nowMonth == birthMonth){
   				var dayDiff = nowDay - birthDay;//日之差
   				if(dayDiff < 0){return (ageDiff-1);
   				}else{return ageDiff;}
   			}else{
   				var monthDiff = nowMonth - birthMonth;//月之差
   				if(monthDiff < 0){return (ageDiff-1);
  					}else{return (ageDiff);}
   			}
		}
      }
}
function checkForm(){
	if($("#age").val()){
		if($("#ageunit option:selected").val()=='岁' && $("#age").val()>120){
			$.messager.show({ title: '提示', msg: '年龄最大不能超过120岁。' });
			return false;
		}
	}
	if($("[name=zdyj]:checked").length<=0){
		$.messager.show({ title: '提示', msg: '请勾选诊断依据。'});
		return false;
	}
	if(!$("#reportdoctorid").combogrid("getValue") || !$("#reportdoctorname").val()){
		$.messager.show({ title: '提示', msg: '请从下拉列表中选择上报医生。'});
		return false;
	}
	if(!$("#reportdeptid").combogrid("getValue") || !$("#reportdeptname").val()){
		$.messager.show({ title: '提示', msg: '请从下拉列表中选择上报单位。'});
		return false;
	}
	return true;
}
function report(){
	var result = $("#zlbk").form('validate'); 
	var info = "确认保存？";
	<c:if test="${acType eq 'hospital'}">
		<c:choose>
			<c:when test="${empty zlxx.flag}">
				info="确认保存？";
			</c:when>
			<c:otherwise>
				info="确认修改？";
			</c:otherwise>
		</c:choose>
	</c:if>
	<c:if test="${!empty isEmptyCard}">
	if(!$("#zyid").val() && !$("#mzid").val()){
		$.messager.show({ title: '提示', msg: '门诊编号或住院编号至少填写一栏。' });
		return false;
	}else</c:if> if(result){
		if(checkForm()){
			$.messager.confirm('提示', info, function (r) {
				if(r){
					$.ajax({
						url:"${webroot}/cdc/f_json/saveTumourCard.shtml",
						data:$("#zlbk").serialize(),
						type:"POST",
						success:function(data){
							data = eval("("+data+")");
							if(data.result=='success'){
								$.messager.show({ title: '提示', msg: '保存成功！' });
								<c:if test="${acType eq 'hospital'}">
								back();
								</c:if>
								<c:if test="${acType eq 'doctor'}">
								back("noEdit");
								</c:if>
							}else{
								$.messager.show({ title: '提示', msg: '保存失败！错误信息：'+data.extraValue });
							}
						},
						error:function(){
							alert("抱歉，操作失败！");
						}
					});
				}
			});
		}
	 }
}
function back(i){
	$.messager.confirm("提示", "确认离开此页面？", function (r) {
		
		//当其他方式打开此页面时：接口
		if(!parent.menuInfo){
			history.go(-1);
		}
		
		//整个页面刷新，若有查询条件的话就会丢失
		//parent.menuInfo.refreshMenu("患者列表");
		//只重新查询下datagrid
		if(parent.frames[0].document.all.query){
			$(parent.frames[0].document.all.query).trigger('click');
		}
		if(r){
			parent.menuInfo.closeCurSelectTab();
		}else if(i=="noEdit"){
			$("#NoEdit").css("height",$("#zlbk").height());
			$("#upload-main").hide();
		}else{
			//刷新当前tab页
			parent.menuInfo.refreshMenu(parent.menuInfo.getCurSelectTabTitle());
		}
	});
}

function fillData(){
	
	var body="${zlxx.diagnosticBasis}";
	var bos=document.getElementsByName("zdyj");
	if(!body){}else{
		var bodys=body.split("|");
		for(var i=0;i<bodys.length;i++){
			for(var j=0;j<bos.length;j++){
				if(bodys[i]==bos[j].value){
					bos[j].checked=true;
					break;
				}
			}
		}
	}
}
//获取诊断依据 
function get_zdyj_val(){
	var bodyval="";
	var arr=$(":checkbox[name='zdyj']:checked");
	for(var i=0;i<arr.length;i++){
		bodyval+=$(arr[i]).val();		
		if(i<arr.length-1){
			bodyval+="|";
		}
	}
	$("#diagnosticBasis").val(bodyval);
}

function audit(bktype,msid){
	//触发保存，验证表单数据是否符合规范
	var result = $("#syycbk").form('validate'); 
	if(result){
		if(checkForm()){
			$.messager.confirm("提示", "确认审核该报卡？", function (r) {
				if(r){
					$.ajax({
							url:"${webroot}/cdc/f_json/saveTumourCard.shtml",
							data:$("#zlbk").serialize(),
							type:"POST",
							success:function(data){
								data = eval("("+data+")");
								if(data.result=='success'){
									var url="${webroot}/cdc/f_json/auditCards.shtml";
										$.ajax({
											url:url,
											data:{
												'bktype':bktype,
												'masterid':msid
											},
											type:'POST',
											success:function(data){
												data = eval("("+data+")");
												if(data.result=="success"){
													$.messager.show({ title: '提示', msg: data.msg });
													var tt = parent.menuInfo.getCurSelectTabTitle();
													setTimeout("parent.menuInfo.refreshMenu('"+tt+"')",1000);
												}else{
													$.messager.show({ title: '提示', msg: data.msg });
												}
											},error:function(){
												$.messager.alert("抱歉，操作失败！");
											}
										});
							}else{
									$.messager.show({ title: '提示', msg: '审核时保存失败！错误信息：'+data.extraValue });
							}
						},
						error:function(){
							alert("抱歉，操作失败！");
						}
					});
				}
			});
		}
	}
}
</script>
</body>
</html>