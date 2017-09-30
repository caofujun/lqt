<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中国居民心脑血管事件报卡</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css${version}" />
<style type="text/css">
	.mainTable{width: 100%;}
	.mainTable td{padding: 2px;}
	.piece{border: 1px solid #ddd;padding: 5px;padding-bottom:20px; width: initial;margin: 0px;}
	.pieceTitle{padding: 3px;border-left: 1px solid #ddd;border-right: 1px solid #ddd;}
	.success{text-align: left; font-size: 14px; border: 1px solid #0fbb0f; background-color: rgb(233, 251, 221);
    border-radius: 5px; padding: 5px; margin: 5px 10px; color: #3e9c06;}
	input[type='text']{width: 130px;}
	input[type='number']{height: 22px;border: 1px solid #e0e0e0;}
	select{width:142px;}
	.pageTable tr{line-height: 25px;}
	.pageTable tr td{width:200px;}
	.rightTextAlign{width:130px;}
	.forRadioOrCkb{margin-right:20px; }
	/* 死亡转归情况部分 */
	/* .result_death_part{display: none;} */
</style>

<script type="text/javascript" src="${webroot}/resources/js/idcard.check.js?${now}"></script>
</head>
<body style="width: 100%;">
	<c:if test="${!empty errMsg}">
		<div class="errTip">${errMsg}</div>
	</c:if>
	<c:if test="${!empty tipMsg}">
		<div class="success">${tipMsg}</div>
	</c:if>
	<div id="NoEdit" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
	<form id="xnxgbk">
		<div style="margin: 60px 5%;margin-top:10px; width: 90%;" >
			<center><h1>中国居民心脑血管事件报卡</h1></center>
			<div style="width: 100%;">
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">患者基本信息</span>
						<input type="hidden" name="patientId" id="patientId" <c:if test="${empty isEmptyCard}">class="easyui-validatebox" required="true"</c:if>
							<c:choose>
								<c:when test="${!empty ccvd}">
									value="${ccvd.patientId}"
								</c:when>
								<c:when test="${!empty BRXX}">
									value="${BRXX.patientId}"
								</c:when>
							</c:choose> />
						<input type="hidden" name="visitId" id="visitId" <c:if test="${empty isEmptyCard}">class="easyui-validatebox" required="true"  </c:if>
							<c:choose>
								<c:when test="${!empty ccvd}">
									value="${ccvd.visitId}"
								</c:when>
								<c:when test="${!empty BRXX}">
									value="${BRXX.visitId}"
								</c:when>
							</c:choose> />
						<input type="hidden" id="cardId" name="cardId" value="${ccvd.cardId}"/>
						<input type="hidden" id="masterid" name="masterid" value="${ccvd.masterid}"/>
						<input type="hidden" id="flag" name="flag" value="${ccvd.flag}"/>
						<input type="hidden" name="isemptycard" id="isemptycard" 
						<c:choose>
							<c:when test="${!empty ccvd}">
								value="${ccvd.isemptycard}" 
							</c:when>
							<c:when test="${isEmptyCard eq 'Y'}">
								value="1"
							</c:when>
							<c:otherwise>
								value="0" 
							</c:otherwise>
						</c:choose> />
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign">门诊号：</td>
							<td>
								<input type="text" id="mzid" name="mzid" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if>
								<c:choose>
									<c:when test="${patientType eq 'mz' }">
										value="${!empty ccvd.mzid?ccvd.mzid:BRXX.mzid}" 
										class="easyui-validatebox" required="true"
										<%-- <c:if test="${empty isEmptyCard}">
											class="easyui-validatebox" required="true"
										</c:if> --%>
									</c:when>
								</c:choose>	/>
							</td>
							<td class="rightTextAlign">住院号：</td>
							<td>
								<input type="text" id="zyid" name="zyid" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if>
 								<c:choose>
									<c:when test="${patientType eq 'zy' }">
										value="${!empty ccvd.zyid?ccvd.zyid:BRXX.zyid}" 
										<c:if test="${empty isEmptyCard}">
											class="easyui-validatebox" required="true"
										</c:if>
									</c:when>
								</c:choose> />
 							</td>
							<td class="rightTextAlign"><span class="red">*</span>姓名：</td>
							<td><input type="text" id="patientName" name="patientName" class="easyui-validatebox" required="true" value="<c:if test="${! empty BRXX}">${BRXX.patientName}</c:if><c:if test="${! empty ccvd}">${ccvd.patientName}</c:if>"/></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>身份证号：</td>
							<td>
								<input type="text" id="idno" name="idno" class="easyui-validatebox" data-options="required:true,validType:'idcared[1]'" 
								<c:choose>
									<c:when test="${!empty ccvd}">
										value="${ccvd.idno}"
									</c:when>
									<c:otherwise>
										value='${(patientType eq 'zy'?BRXX.idCard:BRXX.idnumber)}'
									</c:otherwise>
								</c:choose> />
								<a href="javascript:;" class="tqxx" style="vertical-align: middle;float:none;" title="提取性别和生日" onclick="getInfoByID();"></a>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>性别：</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" id="nan" name="sex" value="男" 
									<c:choose>
										<c:when test="${ccvd.sex eq '男'}">
											checked="checked"
										</c:when>
										<c:when test="${BRXX.sex eq '男'}">
											checked="checked"
										</c:when>
									</c:choose> class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=sex]\', \'性别\']'"/>男</label>
								<label><input type="radio" id="nv" name="sex" value="女"
									<c:choose>
										<c:when test="${ccvd.sex eq '女'}">
											checked="checked"
										</c:when>
										<c:when test="${BRXX.sex eq '女'}">
											checked="checked"
										</c:when>
									</c:choose> />女</label>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>出生日期：</td>
							<td>
								<input type="text" id="birthday" 
									<c:choose>
										<c:when test="${!empty ccvd}">
											value='<fmt:formatDate value="${ccvd.birthday}" pattern="yyyy-MM-dd" />'
										</c:when>
										<c:otherwise>
											value='<fmt:formatDate value="${(patientType eq 'zy'?BRXX.birthDate:BRXX.birthday)}" pattern="yyyy-MM-dd" />'
										</c:otherwise>
									</c:choose> name="birthday" style="width:130px;" class="easyui-validatebox Wdate" required="true" onclick='WdatePicker({dateFmt:"yyyy-MM-dd",maxDate:"<fmt:formatDate value="${now}"/>"})' onblur="$('#age').val(ages($(this).val()));$('#ageunit').val('岁')"/>
								<input type="hidden" id="age" name="age" <c:choose>
										<c:when test="${!empty ccvd}">
											value="${ccvd.age}"
										</c:when>
										<c:otherwise>
											value='${BRXX.age}'
										</c:otherwise>
									</c:choose> />
								<input type="hidden" id="ageunit" name="ageunit" value="岁">
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>民族：</td>
							<td>
								<select id="nation" name="nation" class="easyui-combobox" data-options="required:true">
									<option></option>
									<c:forEach items="${NATION}" var="nt">
										<option value="${nt.dictName }" 
										<c:choose>
											<c:when test="${!empty ccvd && ccvd.nation eq nt.dictName}">selected="selected"</c:when>
											<c:when test="${BRXX.nation eq nt.dictName}">selected="selected"</c:when>
										</c:choose>
										 >${nt.dictName}</option>
									</c:forEach>
								</select>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>职业：</td>
							<td><input type="text" id="profession" name="profession" class="easyui-validatebox" required="true" value="${ccvd.profession}"/></td>
							<td class="rightTextAlign">工作单位：</td>
							<td><input type="text" id="unit" name="unit" value="${ccvd.unit}"/></td>
						</tr>
						<tr>
							<td class="rightTextAlign">婚姻状况：</td>
							<td colspan="5">
								<label class="forRadioOrCkb"><input type="radio" name="marriage" value="未婚" <c:if test="${!empty ccvd && ccvd.marriage eq '未婚'}">checked="checked"</c:if> />未婚</label>
								<label class="forRadioOrCkb"><input type="radio" name="marriage" value="已婚" <c:if test="${!empty ccvd && ccvd.marriage eq '已婚'}">checked="checked"</c:if> />已婚</label>
								<label class="forRadioOrCkb"><input type="radio" name="marriage" value="同居" <c:if test="${!empty ccvd && ccvd.marriage eq '同居'}">checked="checked"</c:if> />同居</label>
								<label class="forRadioOrCkb"><input type="radio" name="marriage" value="离婚" <c:if test="${!empty ccvd && ccvd.marriage eq '离婚'}">checked="checked"</c:if> />离婚</label>
								<label class="forRadioOrCkb"><input type="radio" name="marriage" value="丧偶" <c:if test="${!empty ccvd && ccvd.marriage eq '丧偶'}">checked="checked"</c:if> />丧偶</label>
								<label class="forRadioOrCkb"><input type="radio" name="marriage" value="不详" <c:if test="${!empty ccvd && ccvd.marriage eq '不详'}">checked="checked"</c:if> />不详</label>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">文化程度：</td>
							<td colspan="5">
								<label class="forRadioOrCkb"><input type="radio" name="education" <c:if test="${!empty ccvd && ccvd.education eq '文盲/半文盲'}">checked="checked"</c:if> value="文盲/半文盲"/>文盲/半文盲</label>
								<label class="forRadioOrCkb"><input type="radio" name="education" <c:if test="${!empty ccvd && ccvd.education eq '小学'}">checked="checked"</c:if> value="小学"/>小学</label>
								<label class="forRadioOrCkb"><input type="radio" name="education" <c:if test="${!empty ccvd && ccvd.education eq '初中'}">checked="checked"</c:if> value="初中"/>初中</label>
								<label class="forRadioOrCkb"><input type="radio" name="education" <c:if test="${!empty ccvd && ccvd.education eq '高中/中专/技校'}">checked="checked"</c:if> value="高中/中专/技校"/>高中/中专/技校</label>
								<label class="forRadioOrCkb"><input type="radio" name="education" <c:if test="${!empty ccvd && ccvd.education eq '大专'}">checked="checked"</c:if> value="大专"/>大专</label>
								<label class="forRadioOrCkb"><input type="radio" name="education" <c:if test="${!empty ccvd && ccvd.education eq '本科'}">checked="checked"</c:if> value="本科"/>本科</label>
								<label class="forRadioOrCkb"><input type="radio" name="education" <c:if test="${!empty ccvd && ccvd.education eq '研究生'}">checked="checked"</c:if> value="研究生"/>研究生</label>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>联系人：</td>
							<td><input type="text" id="contacts" name="contacts" class="easyui-validatebox" required="true" value="${ccvd.contacts}"/></td>
							<td class="rightTextAlign"><span class="red">*</span>联系电话：</td>
							<td><input type="text" id="tel" name="tel" class="easyui-validatebox" required="true" 
							<c:choose>
								<c:when test="${!empty ccvd}">
									value="${ccvd.tel}"
								</c:when>
								<c:otherwise>
									value="${BRXX.tel}"
								</c:otherwise>
							</c:choose>
							/></td>
							<td></td><td></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>户籍地址：</td>
							<td colspan="5">
								<select id="regSheng" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>省 &nbsp;
								<select id="regShi" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>市 &nbsp;
								<select id="regXian" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>县 &nbsp;
								<select id="regXiang" name="registerareacode" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>街道(乡) &nbsp;
								<input type="text" id="registeraddrVillage" onchange="fullRegAddress();" name="registeraddrVillage" value="${ccvd.registeraddrVillage }" />居委会(村) &nbsp;
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="5">
								<input type="text" id="registeraddrGroup" onchange="fullRegAddress();" name="registeraddrGroup" value="${ccvd.registeraddrGroup}" />号
								<input type="hidden" id="registerareaaddr" name="registerareaaddr" value="${ccvd.registerareaaddr}" />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>户籍地址详情：</td>
							<td colspan="5">
								<input type="text" id="registeraddrDetail" name="registeraddrDetail" style="width:90%;" 
								<c:choose>
									<c:when test="${!empty ccvd}">
										value="${ccvd.registeraddrDetail}"
									</c:when>
									<c:when test="${patientType eq 'zy'}">
										value="${BRXX.address}"
									</c:when>
									<c:otherwise>
										value="${BRXX.registeraddr}"
									</c:otherwise>
								</c:choose>
								 />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>现住地址：</td>
							<td colspan="5">
								<select id="sheng" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>省 &nbsp;
								<select id="shi" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>市 &nbsp;
								<select id="xian" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>县 &nbsp;
								<select id="xiang" name="nowaddrareacode" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>街道(乡) &nbsp;
								<input type="text" id="nowaddrVillage" name="nowaddrVillage" onchange="fullAddress();" value="${ccvd.nowaddrVillage}" />居委会(村) &nbsp;
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="5">
								<input type="text" id="nowaddrGroup" onchange="fullAddress();" name="nowaddrGroup" value="${ccvd.nowaddrGroup}"/>号
								<input type="hidden" id="nowaddr" name="nowaddr" value="${ccvd.nowaddr}" />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>现住地址详情：</td>
							<td colspan="5">
								<input type="text" id="nowaddrDetail" name="nowaddrDetail" style="width:90%;" 
								<c:choose>
									<c:when test="${!empty ccvd}">
										value="${ccvd.nowaddrDetail}"
									</c:when>
									<c:when test="${patientType eq 'zy'}">
										value="${BRXX.address}"
									</c:when>
									<c:otherwise>
										value="${BRXX.registeraddr}"
									</c:otherwise>
								</c:choose>
								 />
							</td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">疾病信息</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>ICD编码：</td>
							<td colspan="5"><input type="text" id="icdcode" name="icdcode" class="easyui-validatebox" required="true" onclick="showICDdialog('icdcode','icdcodeName');" value="${ccvd.icdcode}" readonly="readonly"/></td>
						</tr>
						<tr>
							<td class="rightTextAlign" style="vertical-align: top;"><span class="red">*</span>疾病诊断：</td>
							<td colspan="5">
								<table>
									<tr>
										<td>脑卒中：</td>
										<td><label class="forRadioOrCkb"><input type="radio" name="diagnosisList" <c:if test="${!empty ccvd && ccvd.diagnosisList eq '蛛网膜下腔出血'}">checked="checked"</c:if> value="蛛网膜下腔出血" class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=diagnosisList]\', \'疾病诊断\']'"/>蛛网膜下腔出血</label></td>
										<td><label class="forRadioOrCkb"><input type="radio" name="diagnosisList" <c:if test="${!empty ccvd && ccvd.diagnosisList eq '脑出血'}">checked="checked"</c:if> value="脑出血"/>脑出血</label></td>
										<td><label class="forRadioOrCkb"><input type="radio" name="diagnosisList" <c:if test="${!empty ccvd && ccvd.diagnosisList eq '脑梗死'}">checked="checked"</c:if> value="脑梗死"/>脑梗死</label></td>
										<td><label class="forRadioOrCkb"><input type="radio" name="diagnosisList" <c:if test="${!empty ccvd && ccvd.diagnosisList eq '未分类脑卒中'}">checked="checked"</c:if> value="未分类脑卒中"/>未分类脑卒中</label></td>
									</tr>
									<tr>
										<td>冠心病：</td>
										<td><label class="forRadioOrCkb"><input type="radio" name="diagnosisList" <c:if test="${!empty ccvd && ccvd.diagnosisList eq '急性心肌梗死'}">checked="checked"</c:if> value="急性心肌梗死"/>急性心肌梗死</label></td>
										<td colspan="2"><label class="forRadioOrCkb"><input type="radio" name="diagnosisList" <c:if test="${!empty ccvd && ccvd.diagnosisList eq '随后性心肌梗死'}">checked="checked"</c:if> value="随后性心肌梗死"/>随后性心肌梗死</label></td>
										<td><label class="forRadioOrCkb"><input type="radio" name="diagnosisList" <c:if test="${!empty ccvd && ccvd.diagnosisList eq '心源性猝死'}">checked="checked"</c:if> value="心源性猝死"/>心源性猝死</label></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>诊断依据(可多选)：</td>
							<td colspan="5">
								<label class="forRadioOrCkb"><input type="checkbox" name="zdyj" value="心电图"/>心电图</label>
								<label class="forRadioOrCkb"><input type="checkbox" name="zdyj" value="血管造影"/>血管造影</label>
								<label class="forRadioOrCkb"><input type="checkbox" name="zdyj" value="CT"/>CT</label>
								<label class="forRadioOrCkb"><input type="checkbox" name="zdyj" value="磁共振"/>磁共振</label>
								<label class="forRadioOrCkb"><input type="checkbox" name="zdyj" value="体格检查"/>体格检查</label>
								<label class="forRadioOrCkb"><input type="checkbox" name="zdyj" value="超声检查"/>超声检查</label>	
								<label class="forRadioOrCkb"><input type="checkbox" name="zdyj" value="实验室检查"/>实验室检查</label>
								<input type="hidden" id="diagnosisGist" name="diagnosisGist" value="${ccvd.diagnosisGist}"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>发病时间：</td>
							<td style="width:150px;"><input type="text" id="startDt" name="startDt" class="easyui-validatebox Wdate" value='<fmt:formatDate value="${ccvd.startDt }" pattern="yyyy-MM-dd HH:mm:ss"/>' required="true" style="width: 130px;" onclick='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:ss",maxDate:"<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"})' /></td>
							<td></td>
							<td class="rightTextAlign"><span class="red">*</span>确诊时间：</td>
							<td><input type="text" id="diagnosisDt" name="diagnosisDt" class="easyui-validatebox Wdate" value='<fmt:formatDate value="${ccvd.diagnosisDt }" pattern="yyyy-MM-dd HH:mm:ss"/>' required="true" style="width: 130px;" onclick='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:ss",maxDate:"<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"})' /></td>
							<td></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>是否首次发病：</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="onsetTime" value="1" <c:if test="${!empty ccvd && ccvd.onsetTime == '1'}">checked="checked"</c:if> class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=onsetTime]\', \'是否首次发病\']'"/>是</label>
								<label><input type="radio" name="onsetTime" value="" <c:if test="${!empty ccvd && empty ccvd.onsetTime}">checked="checked"</c:if>/>否</label>
								<%-- <input type="text" id="onsetTime" name="onsetTime" value="${ccvd.onsetTime }" class="easyui-validatebox" required="true" onkeyup="this.value=this.value.replace(/\D/g,'')"/> --%>
							</td>
							<td></td>
							<td class="rightTextAlign">首次确诊日期：</td>
							<td colspan="3"><input type="text" id="firstDiagnosisDt" name="firstDiagnosisDt" value='<fmt:formatDate value="${ccvd.firstDiagnosisDt }" pattern="yyyy-MM-dd HH:mm:ss"/>' class="Wdate" style="width: 130px;" onclick='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:ss",maxDate:"<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"})' />（仅当发病次数大于1时填写）</td>
							<td></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>最高诊断单位：</td>
							<td  style="width:150px;">
								<label class="forRadioOrCkb"><input  type="radio" name="highestUnit" <c:if test="${!empty ccvd && ccvd.highestUnit eq '省级医院' }">checked="checked"</c:if> value="省级医院" class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=highestUnit]\', \'最高诊断单位\']'"/>省级医院</label>
							</td>
							<td style="width:150px;">
								<label class="forRadioOrCkb"><input type="radio" name="highestUnit" <c:if test="${!empty ccvd && ccvd.highestUnit eq '市级医院' }">checked="checked"</c:if> value="市级医院"/>市级医院</label>
							</td>
							<td style="width:150px;">
								<label class="forRadioOrCkb"><input type="radio" name="highestUnit" <c:if test="${!empty ccvd && ccvd.highestUnit eq '县级医院' }">checked="checked"</c:if> value="县级医院"/>县级医院</label>
							</td>
							<td style="width:150px;">
								<label class="forRadioOrCkb"><input type="radio" name="highestUnit" <c:if test="${!empty ccvd && ccvd.highestUnit eq '乡镇级级医院' }">checked="checked"</c:if> value="乡镇级级医院"/>乡镇级级医院</label>
							</td>
							<td style="width:150px;">
								<label class="forRadioOrCkb"><input type="radio" name="highestUnit" <c:if test="${!empty ccvd && ccvd.highestUnit eq '其他' }">checked="checked"</c:if> value="其他"/>其他</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="highestUnit" <c:if test="${!empty ccvd && ccvd.highestUnit eq '不详' }">checked="checked"</c:if> value="不详"/>不详</label>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>转归：</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="theresult" <c:if test="${!empty ccvd && ccvd.theresult eq '治愈' }">checked="checked"</c:if> value="治愈" onclick="checkIsDead();" class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=theresult]\', \'转归情况\']'"/>治愈</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="theresult" <c:if test="${!empty ccvd && ccvd.theresult eq '好转' }">checked="checked"</c:if> value="好转" onclick="checkIsDead();"/>好转</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="theresult" <c:if test="${!empty ccvd && ccvd.theresult eq '恶化' }">checked="checked"</c:if> value="恶化" onclick="checkIsDead();" />恶化</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="theresult" <c:if test="${!empty ccvd && ccvd.theresult eq '死亡' }">checked="checked"</c:if> value="死亡" onclick="checkIsDead();"/>死亡</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="theresult" <c:if test="${!empty ccvd && ccvd.theresult eq '其他' }">checked="checked"</c:if> value="其他" onclick="checkIsDead();"/>其他</label>
							</td>
						</tr>
						<tr class="result_death_part"> 
							<td></td>
							<td colspan="5" style="font-weight: bolder;">(仅当<font color="red">转归</font>为“<font color="red">死亡</font>”时填写以下内容)</td>
						</tr>
						<tr class="result_death_part">
							<td class="rightTextAlign">死亡地点：</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadzone" <c:if test="${!empty ccvd && ccvd.deadzone eq '医院' }">checked="checked"</c:if> value="医院"/>医院</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadzone" <c:if test="${!empty ccvd && ccvd.deadzone eq '家中' }">checked="checked"</c:if> value="家中"/>家中</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadzone" <c:if test="${!empty ccvd && ccvd.deadzone eq '送治途中' }">checked="checked"</c:if> value="送治途中"/>送治途中</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadzone" <c:if test="${!empty ccvd && ccvd.deadzone eq '其他' }">checked="checked"</c:if> value="其他"/>其他</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadzone" <c:if test="${!empty ccvd && ccvd.deadzone eq '不详' }">checked="checked"</c:if> value="不详"/>不详</label>
							</td>
						</tr>
						<tr class="result_death_part">
							<td class="rightTextAlign">死亡原因：</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadReason" <c:if test="${!empty ccvd && ccvd.deadReason eq '心肌梗死' }">checked="checked"</c:if> value="心肌梗死"/>心肌梗死</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadReason" <c:if test="${!empty ccvd && ccvd.deadReason eq '心脏性猝死' }">checked="checked"</c:if> value="心脏性猝死"/>心脏性猝死</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadReason" <c:if test="${!empty ccvd && ccvd.deadReason eq '蛛网膜下腔出血' }">checked="checked"</c:if> value="蛛网膜下腔出血"/>蛛网膜下腔出血</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadReason" <c:if test="${!empty ccvd && ccvd.deadReason eq '脑出血' }">checked="checked"</c:if> value="脑出血"/>脑出血</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadReason" <c:if test="${!empty ccvd && ccvd.deadReason eq '脑梗死' }">checked="checked"</c:if> value="脑梗死"/>脑梗死</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadReason" <c:if test="${!empty ccvd && ccvd.deadReason eq '未分类脑卒中' }">checked="checked"</c:if> value="未分类脑卒中"/>未分类脑卒中</label>
							</td>
						</tr>
						<tr class="result_death_part">
							<td></td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadReason" <c:if test="${!empty ccvd && ccvd.deadReason eq '其他疾病' }">checked="checked"</c:if> value="其他疾病"/>其他疾病</label>
							</td>
							<td>
								<label class="forRadioOrCkb"><input type="radio" name="deadReason" <c:if test="${!empty ccvd && ccvd.deadReason eq '不详' }">checked="checked"</c:if> value="不详"/>不详</label>
							</td>
						</tr>
						<tr class="result_death_part">
							<td class="rightTextAlign">死亡时间：</td>
							<td><input type="text" id="deathDt" name="deathDt" value='<fmt:formatDate value="${ccvd.deathDt}" pattern="yyyy-MM-dd HH:mm:ss"/>' class="Wdate" style="width: 130px;" onclick='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:ss",maxDate:"<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"})'/></td>
							<td class="rightTextAlign">死亡报告医师：</td>
							<td>
								<input class="easyui-combobox" name="deadReportName" id="deadReportName" style="width: 142px;" />
							</td>
							<td></td><td></td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">报告信息</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>报卡单位：</td>
							<td>
								<input class="easyui-combobox" name="reportdeptid" id="reportdeptid" style="width: 142px;" />
								<input type="hidden" name="reportdeptname" id="reportdeptname" value="${ccvd.reportdeptname}"/>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>报卡医师：</td>
							<td>
								<input class="easyui-combobox" name="reportdoctorid" id="reportdoctorid" style="width: 142px;" />
								<input type="hidden" name="reportdoctorname" id="reportdoctorname" value="${ccvd.reportdoctorname}"/>
							</td>
							<td class="fillDateTime rightTextAlign" style="display: none;"><span class="red">*</span>报告日期：</td>
							<td class="fillDateTime" style="display: none;"><input type="text" id="reportdt" name="reportdt" 
							<c:choose>
								<c:when test="${!empty ccvd}">
									value='<fmt:formatDate value="${ccvd.reportdt}" pattern="yyyy-MM-dd HH:mm:ss" />' 
								</c:when>
								<c:otherwise>
									value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" />' 
								</c:otherwise>
							</c:choose>
							class="Wdate" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})"/></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="footer">
			<%@ include file="/WEB-INF/view/cards/includepages/bkOpts.jsp"%>
		</div>
	</form>
	<div id="chooseICD">
		<div id="distool">
			<input id="icdKey" type="text" style="width: 100%;height:28px;" class="easyui-textbox" data-options="prompt:'输入ICD编号/ICD名称/助记码检索'" />
		</div>
		<table id="icdTable" style="height:400px;"></table>
	</div>
	<script type="text/javascript">
		$(function(){
			<c:if test="${acType eq 'hospital'}">
				$(".fillDateTime").show();
				<c:if test="${!empty ccvd && ccvd.flag!=0}">
					$("#NoEdit").css("height",$("#xnxgbk").height());
				</c:if>
			</c:if>
			<c:if test="${acType eq 'doctor'}">
				<c:if test="${!empty ccvd && ccvd.flag!=2}">
					$("#NoEdit").css("height",$("#xnxgbk").height());
				</c:if>
			</c:if>
			
			fillData();
			
			//地址联动
			/* $("#sheng").change(function () { 
		    	get_shi();
		    });
		    $("#shi").change(function () { 
		    	get_xian(); 
		    });
		    $("#xian").change(function () { 
		    	get_xiang();
		    });
		   	$("#xiang").change(function (){
			   	fullAddress();
			}); */
			$("#sheng").combobox({
				onSelect : function(r){
			    	get_shi();
				}
			});
			$("#shi").combobox({
				onSelect : function(r){
					get_xian(); 
				}
			});
			$("#xian").combobox({
				onSelect : function(r){
					get_xiang();
				}
			});
			$("#xiang").combobox({
				onSelect : function(r){
					fullAddress();
				}
			});
			
		 //户籍地址联动
			/* $("#regSheng").change(function () { 
		    	get_reg_shi();
		    });
		    $("#regShi").change(function () { 
		    	get_reg_xian(); 
		    });
		    $("#regXian").change(function () { 
		    	get_reg_xiang();
		    });
		    $("#regXiang").change(function (){
			   	fullRegAddress();
			}); */
			$("#regSheng").combobox({
				onSelect : function(r){
					get_reg_shi();
				}
			});
			$("#regShi").combobox({
				onSelect : function(r){
					get_reg_xian(); 
				}
			});
			$("#regXian").combobox({
				onSelect : function(r){
					get_reg_xiang();
				}
			});
			$("#regXiang").combobox({
				onSelect : function(r){
					fullRegAddress();
				}
			});
		    
		    //
		    $("[name='zdyj']:checkbox").click(function(){
		    	get_zdyj_val();
		    });
		  //上报科室
			Csm.combogrid.dep({
				//【必传】控件名称
				id: 'reportdeptid',
				//【可选参数】不传默认区session的医院ID
				hospId: '',
				//【可选参数】不传默认区所有监控科室
				<%-- <c:choose>
					<c:when test="${!empty isEmptyCard || syxx.isemptycard eq '1'}">
					</c:when>
					<c:when test="${patientType eq 'zy'}">
						dataType: 'zy',
					</c:when>
					<c:when test="${patientType eq 'mz'}">
						dataType: 'mz',
					</c:when>
				</c:choose> --%>
				onClickRow:function(index,row){
					$("#reportdeptname").val(row['deptName']);
				},
				/* onBeforeLoad:function(param){
					var c = $("#reportdeptname").val()
					if(!c){
						var defaultv = "${user.depNo}";
						var dept = "${syxx.reportdeptid}";
						if(dept){
							param.q=dept;
						}else{
							param.q=defaultv;
						}
					}
				}, */
				onLoadSuccess:function(data){
					if(data.total>0){
						if(""=="${ccvd.reportdeptid}"){
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
							$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${ccvd.reportdeptid}");
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
			//死亡报告医生
			Csm.combogrid.doctor({
				//【必传】控件名称
				id: 'deadReportName',
				//【可选参数】不传默认区session的医院ID
				hospId: '',
				//【可选参数】不传默认区所有监控科室
				onClickRow:function(index,row){
					$("#deadReportName").combobox("setValue",row['employeeName']);
				},
			<c:choose>
				<c:when test="${!empty ccvd}">
					value:"${ccvd.deadReportName}",
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
						if(!"${ccvd.deadReportName}"){
							$('#deadReportName').combogrid('grid').datagrid('selectRecord',"${user.realname}");
						}else{
							$('#deadReportName').combogrid('grid').datagrid('selectRecord',"${ccvd.deadReportName}");
						}
						var r = $('#deadReportName').combogrid('grid').datagrid('getSelected');
						if(r){
							$("#deadReportName").combogrid("setValue",r['employeeName']);
						}
					}else{
						$("#deadReportName").val("");
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
				<c:when test="${!empty ccvd}">
					value:"${ccvd.reportdoctorid}",
				</c:when>
				<c:when test="${acType eq 'hospital'}">
					value:"${user.username}",
				</c:when>
				<c:otherwise>
					value:"${user.docNo}",
				</c:otherwise>
			</c:choose>
				/* onBeforeLoad:function(param){
					var c = $("#reportdoctorname").val()
					if(!c){
						var defaultv = "${user.doctorId}";
						var doctor = "${syxx.reportdoctorid}";
						if(doctor){
							param.q=doctor;
						}else{
							param.q=defaultv;
						}
					}
				}, */
				onLoadSuccess:function(data){
					if(data.total>0){
						//默认选中第一行
						if(!"${ccvd.reportdoctorid}"){
							$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${user.docNo}");
						}else{
							$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${ccvd.reportdoctorid}");
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
			//自动搜索
			$("input",$("#icdKey").next("span")).keyup(function(){
		    	timedCount();
		    });
			
			<c:if test="${empty ccvd}">
				getInfoByID();
			</c:if>
		});
		function getInfoByID(){
			var idcard = $("#idno").val();
			if(null!=idcard && idcard.length==18){
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
		function get_sheng(){
			var url = "${webroot}/cdc/f_json/getSheng.shtml";
			var curSheng = "${curSheng}";
			$.getJSON(url,function (data) {
				$("#sheng").html('');
				var appendstr="";
				$.each(data, function (i, item) {   
					if(curSheng){
		       			if(item.areacode==curSheng){
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
	       		});
				$("#sheng").append(appendstr);
				if(curSheng){
					$("#sheng").combobox({'value':curSheng});
				}else{
					$("#sheng").combobox({'value':"${(HASheng)}"});
				}
				get_shi();
			});
		}
		function get_shi(){
			var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#sheng").combobox("getValue");
			var curShi = "${curShi}";
			$.getJSON(url,function (data) {
				$("#shi").html('');
				var appendstr="";
				$.each(data, function (i, item) {   
					if(curShi){
		       			if(item.areacode==curShi){
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
	       		});
				$("#shi").append(appendstr);
				if(curShi){
					$("#shi").combobox({'value':curShi});
				}else{
					$("#shi").combobox({'value':"${(HAShi)}"});
				}
				get_xian();
			});
		}
		function get_xian(){
			var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#shi").combobox("getValue");
			var curXian = "${curXian}";
			$.getJSON(url,function (data) {
				$("#xian").html('');
				var appendstr="";
				$.each(data, function (i, item) {    
					if(curXian){
						if(item.areacode==curXian){
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
	       		});
				$("#xian").append(appendstr);
				if(curXian){
					$("#xian").combobox({'value':curXian});
				}else{
					$("#xian").combobox({'value':"${(HAXian)}"});
				}
				get_xiang();
			});
		}
		function get_xiang(){
			var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#xian").combobox("getValue");
			var curXiang = "${curXiang}";
			$.getJSON(url,function (data) {
				$("#xiang").html('');
				var appendstr="";
				$.each(data, function (i, item) {   
					if(curXiang){
						if(item.areacode==curXiang){
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
					
	       		});
				$("#xiang").append(appendstr);
				if(curXiang){
					$("#xiang").combobox({'value':curXiang});
				}else{
					$("#xiang").combobox({'value':"${(HAXiang)}"});
				}
				fullAddress();
			});
		}
		function fullAddress(){
			$("#sheng").validatebox();
			$("#shi").validatebox();
			$("#xian").validatebox();
			$("#xiang").validatebox();
			//addrcodevalue
			$("#nowaddr").val($("#sheng").combobox("getText")+$("#shi").combobox("getText")+$("#xian").combobox("getText")+$("#xiang").combobox("getText"));
			$("#nowaddrDetail").val($("#sheng").combobox("getText")+$("#shi").combobox("getText")+$("#xian").combobox("getText")+$("#xiang").combobox("getText")+$("#nowaddrVillage").val()+$("#nowaddrGroup").val());
		}
		function get_reg_sheng(){
			var url = "${webroot}/cdc/f_json/getSheng.shtml";
			var curRegSheng = "${curRegSheng}";
			$.getJSON(url,function (data) {
				$("#regSheng").html('');
				var appendstr="";
				$.each(data, function (i, item) {   
					if(curRegSheng){
		       			if(item.areacode==curRegSheng){
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
	       		});
				$("#regSheng").append(appendstr);
				if(curRegSheng){
					$("#regSheng").combobox({'value':curRegSheng});
				}else{
					$("#regSheng").combobox({'value':"${(HASheng)}"});
				}
				get_reg_shi();
			});
		}
		function get_reg_shi(){
			var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#regSheng option:selected").val();
			var curRegShi = "${curRegShi}";
			$.getJSON(url,function (data) {
				$("#regShi").html('');
				var appendstr="";
				$.each(data, function (i, item) {   
					if(curRegShi){
		       			if(item.areacode==curRegShi){
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
	       		});
				$("#regShi").append(appendstr);
				if(curRegShi){
					$("#regShi").combobox({'value':curRegShi});
				}else{
					$("#regShi").combobox({'value':"${(HAShi)}"});
				}
				get_reg_xian();
			});
		}
		function get_reg_xian(){
			var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#regShi option:selected").val();
			var curRegXian = "${curRegXian}";
			$.getJSON(url,function (data) {
				$("#regXian").html('');
				var appendstr="";
				$.each(data, function (i, item) {    
					if(curRegXian){
						if(item.areacode==curRegXian){
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
	       		});
				$("#regXian").append(appendstr);
				if(curRegXian){
					$("#regXian").combobox({'value':curRegXian});
				}else{
					$("#regXian").combobox({'value':"${(HAXian)}"});
				}
				get_reg_xiang();
			});
		}
		function get_reg_xiang(){
			var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#regXian option:selected").val();
			var curRegXiang = "${curRegXiang}";
			$.getJSON(url,function (data) {
				$("#regXiang").html('');
				var appendstr="";
				$.each(data, function (i, item) {   
					if(curRegXiang){
						if(item.areacode==curRegXiang){
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
					
	       		});
				$("#regXiang").append(appendstr);
				if(curRegXiang){
					$("#regXiang").combobox({'value':curRegXiang});
				}else{
					$("#regXiang").combobox({'value':"${(HAXiang)}"});
				}
				fullRegAddress();
			});
		}
		function fullRegAddress(){
			$("#regSheng").validatebox();
			$("#regShi").validatebox();
			$("#regXian").validatebox();
			$("#regXiang").validatebox();
			//
			$("#registerareaaddr").val($("#regSheng").combobox("getText")+$("#regShi").combobox("getText")+$("#regXian").combobox("getText")+$("#regXiang").combobox("getText"));
			$("#registeraddrDetail").val($("#regSheng").combobox("getText")+$("#regShi").combobox("getText")+$("#regXian").combobox("getText")+$("#regXiang").combobox("getText")+$("#registeraddrVillage").val()+$("#registeraddrGroup").val());
		}
		function checkIsDead(){
			if($(":radio[name='theresult']:checked").val()=="死亡"){
				$(".result_death_part").show();
			}else{
				$(".result_death_part").hide();
			}
		}
		function showICDdialog(id,name){
			$('#icdTable').datagrid({
				url:"${webroot}/cdc/f_json/chooseICD.shtml",
				queryParams: {
		        	'icdId':$("#icdcode").val()
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
					if(!$("#icdcode").val()){}else{
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
		function queryICD(){
			var act_url="${webroot}/cdc/f_json/chooseICD.shtml";
			var tv = $("input",$("#icdKey").next("span")).val();
			$('#icdTable').datagrid({
		        url: act_url,
		        queryParams: {
		        	'icdId':$("#icdcode").val(),
		     		'searchString': (tv=="输入ICD编号/ICD名称/助记码检索"?"":tv)
		        },
		        method:"post",
		        onLoadSuccess: function (data) {
		        	if(!$("#icdcode").val() || tv!="输入ICD编号/ICD名称/助记码检索"){}else{
						$('#icdTable').datagrid("selectRow",0);
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
		function checkForm(){
			if(!$("#diagnosisGist").val()){
				$.messager.show({ title: '提示', msg: '请选择诊断依据！' });
        		return false;
			}
			var fbt = new Date($("#startDt").val().replace("-", "/").replace("-", "/"));
    		var jst = new Date($("#diagnosisDt").val().replace("-", "/").replace("-", "/"));
        	if(fbt>jst){
        		$.messager.show({ title: '提示', msg: '发病时间不能晚于确诊时间！' });
        		return false;
        	}
			if($(":radio[name='theresult']:checked").val()=="死亡"){
				if(!$(":radio[name='deadzone']:checked").val()){
					$.messager.show({ title: '提示', msg: '请选择死亡地点。'});
					return false;
				}
				if(!$(":radio[name='deadReason']:checked").val()){
					$.messager.show({ title: '提示', msg: '请选择死亡原因。'});
					return false;
				}
				if(!$("#deathDt").val()){
					$.messager.show({ title: '提示', msg: '请选择死亡时间。'});
					return false;
				}
				if(!$("#deadReportName").combobox('getValue')){
					$.messager.show({ title: '提示', msg: '请选择死亡报告医师。'});
					return false;
				}
			}
			if(!$("#reportdoctorid").combogrid("getValue") || !$("#reportdoctorname").val()){
				$.messager.show({ title: '提示', msg: '请从下拉列表中选择填报医生。'});
				return false;
			}
			if(!$("#reportdeptid").combogrid("getValue") || !$("#reportdeptname").val()){
				$.messager.show({ title: '提示', msg: '请从下拉列表中选择填报科室。'});
				return false;
			}
			return true;
		}
		function report(){
			var result = $("#xnxgbk").form('validate'); 
			if(result){
				if(checkForm()){
					$.messager.confirm('提示', "确认保存？", function (r) {
						if(r){
							$.ajax({
								url:"${webroot}/cdc/f_json/saveXNXGCard.shtml",
								data:$("#xnxgbk").serialize(),
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
					$("#NoEdit").css("height",$("#xnxgbk").height());
					$("#upload-main").hide();
				}else{
					//刷新当前tab页
					parent.menuInfo.refreshMenu(parent.menuInfo.getCurSelectTabTitle());
				}
			});
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
			$("#diagnosisGist").val(bodyval);
		}
		function fillData(){
			
			get_sheng();
			get_reg_sheng();
			checkIsDead();
			
			<c:choose>
			   	<c:when test="${!empty ccvd}">
			   		setTimeout("$('#registeraddrDetail').val('${ccvd.registeraddrDetail}')",1200);
			   		setTimeout("$('#nowaddrDetail').val('${ccvd.nowaddrDetail}')",1200);
			   	</c:when>
			   	<c:when test="${patientType eq 'zy'}">
			   		<c:if test="${!empty BRXX.address}">
				   		setTimeout("$('#registeraddrDetail').val('${BRXX.address}')",1200);
				   		setTimeout("$('#nowaddrDetail').val('${BRXX.address}')",1200);
			   		</c:if>
			   	</c:when>
			   	<c:when test="${patientType eq 'mz'}">
				   	<c:if test="${!empty BRXX.registeraddr}">
					   	setTimeout("$('#registeraddrDetail').val('${BRXX.registeraddr}')",1200);
					</c:if>
					<c:if test="${!empty BRXX.presentaddr}">
					   	setTimeout("$('#nowaddrDetail').val('${BRXX.presentaddr}')",1200);
			   		</c:if>
			   	</c:when>
	   		</c:choose>
			
			var body="${ccvd.diagnosisGist}";
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
		
		function audit(bktype,msid){
			//触发保存，验证表单数据是否符合规范
			var result = $("#xnxgbk").form('validate'); 
			if(result){
				if(checkForm()){
					$.messager.confirm("提示", "确认审核该报卡？", function (r) {
						if(r){
							$.ajax({
									url:"${webroot}/cdc/f_json/saveXNXGCard.shtml",
									data:$("#xnxgbk").serialize(),
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