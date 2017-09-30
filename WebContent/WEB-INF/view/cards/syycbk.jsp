<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>疑似食源性异常病例</title>
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
	<form id="syycbk">
		<div style="margin: 60px 5%;margin-top:10px; width: 90%;" >
			<center><h1>疑似食源性异常病例/异常健康事件报告卡</h1></center>
			<div style="width: 100%;">
				<div id="NoEdit1" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
				<div id="P1" class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">患者基本信息</span>
						<input type="hidden" id="allbody_val" name="symptoms" value="${syycxx.symptoms}"/>
						<input type="hidden" id="allskin_val" name="skins" value="${syycxx.skins}"/>
						<input type="hidden" id="allrespiratory_val" name="respiratorys" value="${syycxx.respiratorys}"/>
						<input type="hidden" id="allcardiovascular_val" name="cardiovasculars" value="${syycxx.cardiovasculars}"/>
						<input type="hidden" id="allurinary_val" name="urinarys" value="${syycxx.urinarys}"/>
						<input type="hidden" id="alldigestive_val" name="digestives" value="${syycxx.digestives}"/>
						<input type="hidden" id="allnervous_val" name="nervous" value="${syycxx.nervous}"/>
						<input type="hidden" name="masterid" id="masterid" value="${syycxx.masterid}" />
						<input type="hidden" name="cardid" id="cardid" value="${syycxx.cardid}" />
						<input type="hidden" name="patientId" id="patientId" value="${BRXX.patientId}" />
						<input type="hidden" name="isemptycard" id="isemptycard" 
						<c:choose>
							<c:when test="${! empty syycxx}">
								value="${syycxx.isemptycard}" 
							</c:when>
							<c:when test="${isEmptyCard eq 'Y'}"> 
								value="1" 
							</c:when>
							<c:otherwise>
								value="0" 
							</c:otherwise>
						</c:choose> />
						<input type="hidden" name="flag" id="flag" value="${syycxx.flag}"/>
					</div>
					<table class="mainTable">
						<c:if test="${isEmptyCard eq 'Y' || syycxx.isemptycard==1}">
						<tr>
							<td class="rightTextAlign" style="color:#3e9c06;">注：</td>
							<td colspan="5"><div style="color:#3e9c06;">门诊号/住院号/病例号可填身份证号</div></td>
						</tr>
						</c:if>
						<tr>
							<td class="rightTextAlign">门诊号/住院号：</td>
							<td style="width:25%;">
								<input type="text" id="uniqId" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if> class="easyui-validatebox" required="true"
								<c:choose>
									<c:when test="${patientType eq 'zy' }">
										name="zyid" value="${!empty syycxx.zyid?syycxx.zyid:BRXX.zyid}"
									</c:when>
									<c:when test="${patientType eq 'mz' }">
										name="mzid" value="${!empty syycxx.mzid?syycxx.mzid:BRXX.mzid}"
									</c:when>
								</c:choose> />
							</td>
							<td class="rightTextAlign">门诊/住院次数：</td>
							<td style="width:25%;">
								<input type="text" name="visitId" id="visitId" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if> class="easyui-validatebox" required="true" onkeyup="this.value=this.value.replace(/\D/g,'')" 
								<c:choose>
									<c:when test="${!empty syycxx}">
										value="${syycxx.visitId}"
									</c:when>
									<c:when test="${!empty BRXX}">
										value="${BRXX.visitId}"
									</c:when>
								</c:choose> />
							</td>
							<td></td><td></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>患者姓名：</td>
							<td>
								<input type="text" name="patientName" id="patientName" class="easyui-validatebox" required="true"
								<c:choose>
									<c:when test="${!empty syycxx}">
										value="${syycxx.patientName}"
									</c:when>
									<c:when test="${!empty BRXX}">
										value="${BRXX.patientName}"
									</c:when>
								</c:choose>	 />
							</td>
							<td class="rightTextAlign">证件类型：</td>
							<td>
								<select id="idType" name="idType"  class="easyui-validatebox" required="true" onchange="$('#idTypeName').val($('option:selected',this).text());$('#id').validatebox();">
									<c:forEach items="${cardType}" var="ct">
										<option value="${ct.dictCode }" <c:if test="${syycxx.idType eq ct.dictCode}">selected="selected"</c:if> >${ct.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="idTypeName" name="idTypeName" value="${syycxx.idTypeName}"/>
							</td>
							<td class="rightTextAlign">证件号码：</td>
							<td>
								<input type="text" name="id" id="id" style="float:left;" class="easyui-validatebox" data-options="required:true,validType:'idcared'" 
								<c:choose>
									<c:when test="${!empty syycxx}">
										value="${syycxx.id}"
									</c:when>
									<c:otherwise>
										value='${(patientType eq 'zy'?BRXX.idCard:BRXX.idnumber)}'
									</c:otherwise>
								</c:choose> />
								<!-- <input type="button" class="butt" value="提取性别和生日" onclick="getInfoByID();"/> -->
								<a href="javascript:;" class="tqxx" title="提取性别和生日" onclick="getInfoByID();"></a>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>患者性别：</td>
							<td>
								<label style="padding-right: 10px;">
									<input type="radio" name="sex" id="nan" value="男"
									<c:choose>
										<c:when test="${syycxx.sex eq '男'}">
											checked="checked"
										</c:when>
										<c:when test="${BRXX.sex eq '男'}">
											checked="checked"
										</c:when>
									</c:choose> class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=sex]\', \'性别\']'"/>男</label>
								<label style="padding-right: 10px;">
									<input type="radio" name="sex" id="nv" value="女" 
									<c:choose>
										<c:when test="${syycxx.sex eq '女'}">
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
								<c:when test="${!empty syycxx}">
									value='<fmt:formatDate value="${syycxx.birthday}" pattern="yyyy-MM-dd" />'
								</c:when>
								<c:otherwise>
									value='<fmt:formatDate value="${(patientType eq 'zy'?BRXX.birthDate:BRXX.birthday)}" pattern="yyyy-MM-dd" />'
								</c:otherwise>
							</c:choose> /></td>
							<td class="rightTextAlign"><span class="red">*</span>实足年龄：</td>
							<td>
								<input type="text" name="age" id="age" class="easyui-validatebox" required="true" style="width:66px;" onkeyup="this.value=this.value.replace(/\D/g,'')"
									<c:choose>
										<c:when test="${!empty syycxx}">
											value="${syycxx.age}"
										</c:when>
										<c:otherwise>
											value='${BRXX.age}'
										</c:otherwise>
									</c:choose> />
								<select style="width:60px;" name="ageUnit" id="ageunit">
									<option value="岁" 
									<c:choose>
										<c:when test="${!empty syycxx && syycxx.ageUnit eq '岁'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '岁'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose> >岁</option>
									<option value="月" 
									<c:choose>
										<c:when test="${!empty syycxx && syycxx.ageUnit eq '月'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '月'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose>	>月</option>
									<option value="天" 
									<c:choose>
										<c:when test="${!empty syycxx && syycxx.ageUnit eq '天'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '天'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose> >天</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>现住址：</td>
							<td colspan="5">
								<select style="width: 12%;" name="province" id="sheng" class="easyui-validatebox" required="true" ><option></option></select>省
								<select style="width: 12%;" name="city" id="shi" class="easyui-validatebox" required="true" ><option></option></select>市
								<select style="width: 12%;" name="country" id="xian" class="easyui-validatebox" required="true" ><option></option></select>县（区）
								<select style="width: 12%;" id="xiang" class="easyui-validatebox" required="true" ><option></option></select>乡（街道）
								
								<input type="hidden" id="nowaddrareacode" name="addrcode" value="${syycxx.addrcode}"/><!-- 编码 -->
								<input type="hidden" id="nowaddr" name="addr" value="${syycxx.addr}"/><!-- 完整的住址 -->
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>户口地址：</td>
							<td colspan="5">
								<select style="width: 12%;" id="regsheng" class="easyui-validatebox" required="true" ><option></option></select>省
								<select style="width: 12%;" id="regshi" class="easyui-validatebox" required="true" ><option></option></select>市
								<select style="width: 12%;" id="regxian" class="easyui-validatebox" required="true" ><option></option></select>县（区）
								<select style="width: 12%;" id="regxiang" class="easyui-validatebox" required="true" ><option></option></select>乡（街道）
								
								<input type="hidden" id="registerareacode" name="registerareacode" value="${syycxx.registerareacode}"/><!-- 编码 -->
								<input type="hidden" id="registerareaaddr" name="registeraddr" value="${syycxx.registeraddr}"/><!-- 完整的住址 -->
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">固定电话：</td>
							<td>
								<input type="text" name="telephone" id="tel"  
								<c:choose>
									<c:when test="${!empty syycxx}">
										value="${syycxx.telephone}"
									</c:when> 
									
								</c:choose> />
							</td>
							<td class="rightTextAlign">移动电话：</td>
							<td>
								<input type="text" name="mobilePhone" id="phone"  
								<c:choose>
									<c:when test="${!empty syycxx}">
										value="${syycxx.mobilePhone}"
									</c:when> 
									<c:otherwise>
										value="${BRXX.tel}"
									</c:otherwise>
								</c:choose> />
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>患者发病时间：</td>
							<td>
								<input type="text" name="startDate" id="startDate" readonly="readonly" class="Wdate text easyui-validatebox" required="true" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" 
									<c:choose>
										<c:when test="${!empty syycxx}">
											value='<fmt:formatDate value="${syycxx.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>'
										</c:when>
									</c:choose>
								/> 
							</td>
							<td class="rightTextAlign"><span class="red">*</span>患者就诊时间：</td>
							<td>
								<input type="text" name="diagnoseDate" id="diagnoseDate" readonly="readonly" class="Wdate text easyui-validatebox" required="true" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" 
									<c:choose>
										<c:when test="${!empty syycxx}">
											value='<fmt:formatDate value="${syycxx.diagnoseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>'
										</c:when>
									</c:choose>
								/> 
							</td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text"><span class="red">*</span>主要症状</span>
					</div>
					<div id="NoEdit2" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;margin-top:30px;"></div>
					<div id="P2">
					<div id="zzytzTabs" class="easyui-tabs" style="height: 170px;">
						<div title="全身症状及体征" style="padding:5px 20px;">
							<table>
								<tr style="line-height: 25px;">
									<td style="width:200px;">
										<label><input type="checkbox" name="body1" class="ckb" id="showfeverdegree" value="发热" onclick="get_body_val();"/>&nbsp;<span>发热</span></label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="body1" class="ckb" value="寒战" onclick="get_body_val();"/>&nbsp;<span>寒战</span></label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="body1" class="ckb" value="乏力" onclick="get_body_val();"/>&nbsp;<span>乏力</span></label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="body1" class="ckb" value="脱水" onclick="get_body_val();"/>&nbsp;<span>脱水</span></label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="body1" class="ckb" value="浮肿" onclick="get_body_val();"/>&nbsp;<span>浮肿</span></label>
									</td>
								</tr>
								<tr style="line-height: 25px;">
									<td>
										<label><input type="checkbox" name="body1" class="ckb" value="发绀" onclick="get_body_val();"/>&nbsp;<span>发绀</span></label>
									</td>
									<td>
										<label><input type="checkbox" name="body1" class="ckb" value="面色潮红" onclick="get_body_val();"/>&nbsp;<span>面色潮红</span></label>
									</td>
									<td> 
										<label><input type="checkbox" name="body1" class="ckb" value="面色苍白" onclick="get_body_val();"/>&nbsp;<span>面色苍白</span></label>
									</td>
									<td colspan="2">
										<label><input type="checkbox" name="body1" id="showbody" class="ckb" value="其他" onclick="get_body_val();"/>&nbsp;<span>其他</span></label>
										<label><input type="text" id="bodyelse" name="symptomsOther" class="field text" style="display: none;width:150px;" value="${syycxx.symptomsOther}" /></label>
									</td>
								</tr>
							</table>
						</div>
						<div title="消化系统" style="padding:5px 20px;">
							<table class="pageTable">
								<tr>
									<td style="width:200px;">
										<label><input type="checkbox" name="dige1" class="ckb" value="恶心" onclick="get_dige_val();"/>&nbsp;恶心</label>
									</td>
									<td style="width:200px;">
										<label><input id="xhot" type="checkbox" name="dige1" class="ckb" value="呕吐" onclick="get_dige_val();"/>&nbsp;呕吐</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="dige1" class="ckb" value="腹痛" onclick="get_dige_val();"/>&nbsp;腹痛</label>
									</td>
									<td style="width:200px;">
										<label>
											<input id="isfx" type="checkbox" name="dige1" class="ckb" value="腹泻" onclick="get_dige_val();"/>&nbsp;腹泻&nbsp;
										</label>
											<span id="fxxg" style="display: none;">
												<select id="fxxz" name="digestiveFxxz" style="width: 100px;" >
													<option></option>
													<c:forEach items="${FXXZ}" var="fx">
														<option value="${fx.dictName}" <c:if test="${!empty syjcxx && (syjcxx.digestiveFxxz==fx.dictName)}"> selected="selected"</c:if> >${fx.dictName}</option>
													</c:forEach>
												</select>
												<%-- <input type="text" id="fxxzother" name="syjc.DIGESTIVE_FX_OTHER" value="${syjcxx.DIGESTIVE_FX_OTHER}" style="width: 120px;display: none;"/> --%>
												<input id="fxcount" type="text" name="digestiveFx" value="${syjcxx.digestiveFx}" style="width: 40px;" onkeyup="this.value=this.value.replace(/\D/g,'')" />次/天
											</span>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="dige1" class="ckb" value="便秘" onclick="get_dige_val();"/>&nbsp;便秘</label>
									</td>
								</tr>
								<tr>
									<td>
										<label><input type="checkbox" name="dige1" class="ckb" value="里急后重" onclick="get_dige_val();"/>&nbsp;里急后重</label>
									</td>
									<td colspan="4">
										<label><input type="checkbox" name="dige1" id="showdige" class="ckb" value="其他" onclick="get_dige_val();"/>&nbsp;其他</label>
										<label><input type="text" id="digeelse" name="digestiveOther" class="field text" style="display: none;"  value="${syycxx.digestiveOther}"/></label>
									</td>
								</tr>
							</table>
						</div>
						<div title="呼吸系统" style="padding:5px 20px;">
							<table class="pageTable">
								<tr>
									<td style="width:200px;">
										<label><input type="checkbox" name="resp1" class="ckb" value="呼吸短促" onclick="get_resp_val();"/>&nbsp;呼吸短促</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="resp1" class="ckb" value="咯血" onclick="get_resp_val();"/>&nbsp;咯血</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="resp1" class="ckb" value="呼吸困难" onclick="get_resp_val();"/>&nbsp;呼吸困难</label>
									</td>
									<td colspan="2" style="width:400px;">
										<label><input type="checkbox" name="resp1" id="showresp" class="ckb" value="其他" onclick="get_resp_val();"/>&nbsp;其他</label>
										<label><input type="text" id="respelse" name="respiratoryOther" class="field text" style="display: none;" value="${syycxx.respiratoryOther}"/></label>
									</td>
								</tr>
							</table>
						</div>
						<div title="心脏血管系统" style="padding:5px 20px;">
							<table class="pageTable">
								<tr>
									<td style="width:200px;">
										<label><input type="checkbox" name="card1" class="ckb" value="胸闷" onclick="get_card_val();"/>&nbsp;胸闷</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="card1" class="ckb" value="胸痛" onclick="get_card_val();"/>&nbsp;胸痛</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="card1" class="ckb" value="心悸" onclick="get_card_val();"/>&nbsp;心悸</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="card1" class="ckb" value="气短" onclick="get_card_val();"/>&nbsp;气短</label>
									</td>
									<td>
										<label><input type="checkbox" name="card1" id="showcard" class="ckb" value="其他" onclick="get_card_val();"/>&nbsp;其他</label>
										<label><input type="text" id="cardelse" name="cardiovascularOther" class="field text" style="display: none;" value="${syycxx.cardiovascularOther}"/></label>
									</td>
								</tr>
							</table>
						</div>
						<div title="泌尿系统" style="padding:5px 20px;">
							<table class="pageTable">
								<tr>
									<td style="width:200px;">
										<label><input type="checkbox" name="urin1" class="ckb" value="尿量减少" onclick="get_urin_val();"/>&nbsp;尿量减少</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="urin1" class="ckb" value="背部/肾区疼痛" onclick="get_urin_val();"/>&nbsp;背部/肾区疼痛</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="urin1" class="ckb" value="尿中带血" onclick="get_urin_val();"/>&nbsp;尿中带血</label>
									</td>
									<td style="width:400px;">
										<label><input type="checkbox" name="urin1" id="showurin" class="ckb" value="其他" onclick="get_urin_val();"/>&nbsp;其他</label>
										<label><input type="text" id="urinelse" name="urinaryOther" class="field text" style="display: none;" value="${syycxx.urinaryOther}"/></label>
									</td>
								</tr>
							</table>
						</div>
						<div title="神经系统" style="padding:5px 20px;">
							<table class="pageTable">
								<tr>
									<td style="width:200px;">
										<label><input type="checkbox" name="nerv1" class="ckb" value="头痛" onclick="get_nerv_val();"/>&nbsp;头痛</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="nerv1" class="ckb" value="眩晕" onclick="get_nerv_val();"/>&nbsp;眩晕</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="nerv1" class="ckb" value="昏迷" onclick="get_nerv_val();"/>&nbsp;昏迷</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="nerv1" class="ckb" value="抽搐" onclick="get_nerv_val();"/>&nbsp;抽搐</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="nerv1" class="ckb" value="惊厥" onclick="get_nerv_val();"/>&nbsp;惊厥</label>
									</td>
								</tr>
								<tr>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="谵妄" onclick="get_nerv_val();"/>&nbsp;谵妄</label>
									</td>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="瘫痪" onclick="get_nerv_val();"/>&nbsp;瘫痪</label>
									</td>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="言语困难" onclick="get_nerv_val();"/>&nbsp;言语困难</label>
									</td>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="吞咽困难" onclick="get_nerv_val();"/>&nbsp;吞咽困难</label>
									</td>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="感觉异常" onclick="get_nerv_val();"/>&nbsp;感觉异常</label>
									</td>
								</tr>
								<tr>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="复视" onclick="get_nerv_val();"/>&nbsp;复视</label>
									</td>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="视力模糊" onclick="get_nerv_val();"/>&nbsp;视力模糊</label>
									</td>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="眼睑下垂" onclick="get_nerv_val();"/>&nbsp;眼睑下垂</label>
									</td>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="肢体麻木" onclick="get_nerv_val();"/>&nbsp;肢体麻木</label>
									</td>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="末梢感觉障碍" onclick="get_nerv_val();"/>&nbsp;末梢感觉障碍</label>
									</td>
								</tr>
								<tr>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="针刺感" onclick="get_nerv_val();"/>&nbsp;针刺感</label>
									</td>
									<td>
										<label><input type="checkbox" name="nerv1" class="ckb" value="精神失常" onclick="get_nerv_val();"/>&nbsp;精神失常</label>
									</td>
									<td colspan="3">
										<label><input type="checkbox" name="nerv1" id="shownerv" class="ckb" value="其他" onclick="get_nerv_val();"/>&nbsp;其他</label>
										<label><input type="text" id="nervelse" name="nervouOther" class="field text" style="display: none;"  value="${syycxx.nervouOther}"/></label>
									</td>
								</tr>
							</table>
						</div>
						<div title="皮肤和皮下组织" style="padding:5px 20px;">
							<table class="pageTable">
								<tr>
									<td style="width:200px;">
										<label><input type="checkbox" name="skin1" class="ckb" value="瘙痒" onclick="get_skin_val();"/>&nbsp;瘙痒</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="skin1" class="ckb" value="烧灼感"onclick="get_skin_val();"/>&nbsp;烧灼感</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="skin1" class="ckb" value="皮疹"onclick="get_skin_val();"/>&nbsp;皮疹</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="skin1" class="ckb" value="出血点"onclick="get_skin_val();"/>&nbsp;出血点</label>
									</td>
									<td style="width:200px;">
										<label><input type="checkbox" name="skin1" class="ckb" value="黄疸"onclick="get_skin_val();"/>&nbsp;黄疸</label>
									</td>
								</tr>
								<tr>
									<td colspan="5">
										<label><input type="checkbox" name="skin1" id="showskin" class="ckb" value="其他" onclick="get_skin_val();"/>&nbsp;其他</label>
										<label><input type="text" id="skinelse" name="skinOther" class="field text" style="display: none;" value="${syycxx.skinOther}"/></label>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<table class="mainTable">
						<tr>
							<td style="width:107px;"></td>
							<td style="width:100px;"></td>
							<td></td>
							<td></td>
							<td></td>
							<td style="width:5%;"></td>
						</tr>
						<tr>
							<td class="rightTextAlign">其它症状：</td>
							<td colspan="4">
								<input type="text" style="width:100%;" id="otherSymptom" name="otherSymptom" value="${syycxx.otherSymptom}"/>
							</td>
						</tr>
						<tr><td colspan="6"></td></tr>
					</table>
					</div>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">检查结果</span>
					</div>
					<div id="NoEdit3" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
					<div id="P3" style="padding:5px 20px; ">
					<table class="mainTable">
						<tr>
							<td style="width:107px;"></td>
							<td style="width:100px;"></td>
							<td></td>
							<td></td>
							<td></td>
							<td style="width:5%;"></td>
						</tr>
						<tr>
							<td class="rightTextAlign">主要体征：</td>
							<td colspan="4">
								<input type="text" id="sign" name="sign" style="width:100%;" value="${syycxx.sign}"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="rightTextAlign">实验室检查结果：</td>
							<td colspan="4">
								<input type="text" id="labResult" name="labResult" style="width:100%;" value="${syycxx.labResult }"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="rightTextAlign" colspan="2">辅助检查结果（B超、CT或核磁等）：</td>
							<td colspan="3">
								<input type="text" id="assistResult" name="assistResult" style="width:100%;" value="${syycxx.assistResult }"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>主要诊断：</td>
							<td colspan="4">
								<input type="text" id="mainDiagnosis" name="mainDiagnosis" class="easyui-validatebox" required="true" style="width:100%;" value="${syycxx.mainDiagnosis }"/>
							</td>
							<td></td>
						</tr>
						<tr><td colspan="6"></td></tr>
					</table>
					</div>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text"><span class="red">*</span>可疑原因（可多选）</span>
						<input type="hidden" id="pathogenesis" name="pathogenesis" value="${syycxx.pathogenesis}"/>
					</div>
					<div id="NoEdit4" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
					<div id="P4" style="padding:5px 20px; ">
					<table class="pageTable">
						<tr>
							<td style="width:200px;padding-left: 20px;"><label><input type="checkbox" id="showfood" name="kyyy" value="与食品有关" onclick="get_kyyy_val();if($(this).is(':checked')){$('#foods').removeAttr('disabled');}else{$('#foods').attr('disabled','disabled').val('');}" />与食品有关</label></td>
							<td style="width:200px;padding-left: 20px;"><label><input type="checkbox" name="kyyy" value="与饮用水有关" onclick="get_kyyy_val();"/>与饮用水有关</label></td>
							<td style="width:200px;padding-left: 20px;"><label><input type="checkbox" name="kyyy" value="与环境污染有关" onclick="get_kyyy_val();"/>与环境污染有关</label></td>
							<td style="width:200px;padding-left: 20px;"><label><input type="checkbox" name="kyyy" value="职业" onclick="get_kyyy_val();"/>职业</label></td>
							<td style="width:200px;padding-left: 20px;"><label><input type="checkbox" name="kyyy" value="辐射" onclick="get_kyyy_val();"/>辐射</label></td>
						</tr>
						<tr style="padding-left: 10px;">
							<td style="padding-left: 20px;"><label><input type="checkbox" name="kyyy" value="不详" />不详</label></td>
							<td style="padding-left: 20px;"><label><input type="checkbox" name="kyyy" value="其它" />其它</label></td>
						</tr>
					</table>
					<table class="mainTable">
						<tr>
							<td style="width:107px;"></td>
							<td style="width:140px;"></td>
							<td></td>
							<td></td>
							<td></td>
							<td style="width:5%;"></td>
						</tr>
						<tr>
							<td colspan="2" class="rightTextAlign">如果疑似与食品有关，请列出可疑食品名称：</td>
							<td colspan="3">
								<input type="text" id="foods" name="foods" style="width:100%;" disabled="disabled" value="${syycxx.foods}"/>
							</td>
							<td></td>
						</tr>
						<tr><td colspan="6"></td></tr>
					</table>
					</div>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text"><span class="red">*</span>上报原因（报告病例需要与某种可疑食品有关）</span>
						<input type="hidden" id="reportReason" name="reportReason" value="${syycxx.reportReason}"/>
					</div>
					<div id="NoEdit5" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
					<div id="P5" style="padding:5px 20px; ">
					<table class="mainTable">
						<tr style="line-height: 25px;">
							<td style="padding-left: 20px;"><label><input type="checkbox" name="sbyy" value="疾病的临床表现及流行病学特征，用现有的专业知识及临床经验无法合理解释；" onclick="get_sbyy_val();"/>疾病的临床表现及流行病学特征，用现有的专业知识及临床经验无法合理解释；</label></td>
						</tr>
						<tr style="line-height: 25px;">
							<td style="padding-left: 20px;"><label><input type="checkbox" name="sbyy" value="病情/健康损害严重，临床少见并且无法合理解释；" onclick="get_sbyy_val();"/>病情/健康损害严重，临床少见并且无法合理解释；</label></td>
						</tr>
						<tr style="line-height: 25px;">
							<td style="padding-left: 20px;"><label><input type="checkbox" name="sbyy" value="同一医疗机构接诊类似的病人数异常增多，超出既往水平且不能合理解释；" onclick="get_sbyy_val();"/>同一医疗机构接诊类似的病人数异常增多，超出既往水平且不能合理解释；</label></td>
						</tr>
						<tr style="line-height: 25px;">	
							<td style="padding-left: 20px;"><label><input type="checkbox" name="sbyy" value="存在上述一个或数个特征，疑似与进食某种食品有关。" onclick="get_sbyy_val();"/>存在上述一个或数个特征，疑似与进食某种食品有关。</label></td>
						</tr>
					</table>
					</div>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">填报信息</span>
					</div>
					<div id="NoEdit6" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
					<div id="P6" style="padding:5px 20px; ">
					<table class="mainTable">
						<td class="rightTextAlign">医疗机构名称：</td>
						<td>
							<input type="text" id="reportdeptid" name="reportdeptid" class="easyui-combobox" value="${syycxx.reportdeptid}"/>
							<input type="hidden" name="reportdeptname" id="reportdeptname" value="${syycxx.reportdeptname }"/>
						</td>
						<td class="rightTextAlign">填报人：</td>
						<td>
							<input type="text" id="reportdoctorid" name="reportdoctorid" class="easyui-combobox" value="${syycxx.reportdoctorid }"/>
							<input type="hidden" name="reportdoctorname" id="reportdoctorname" value="${syycxx.reportdoctorname}"/>
						</td>
						<td class="fillDateTime rightTextAlign" style="display: none;"><span class="red">*</span>填报日期：</td>
						<td style="display: none;" class="fillDateTime syjcfilldate">
							<input type="text" name="reportdt" id="filldate" readonly="readonly" class="Wdate text <c:if test="${acType eq 'hospital'}">easyui-validatebox</c:if>" <c:if test="${acType eq 'hospital'}">required="true"</c:if> style="width: 130px;" 
							<c:choose>
								<c:when test="${!empty syycxx}">
									value='<fmt:formatDate value="${syycxx.reportdt}" pattern="yyyy-MM-dd HH:mm:ss"/>' 
								</c:when>
								<c:otherwise>
									value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>' 
								</c:otherwise>
							</c:choose>
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" /> 
						</td>
					</table>
					</div>
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
					<c:if test="${syycxx.flag==0}">
						<div class="n_btn_blue" id="upload-main">
							<a href="javascript:saveForm();" id="upload" class="no_ico"><span>上报</span></a>
						</div>
					</c:if>
				</c:if>
				<c:if test="${acType eq 'doctor'}">
					<c:if test="${empty syycxx || syycxx.flag==2}">
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
<script type="text/javascript">
$(function(){
	<c:if test="${acType eq 'hospital'}">
		$(".fillDateTime").show();
		<c:if test="${!empty syycxx.flag && syycxx.flag!=0}">
			$("#NoEdit1").css("height",$("#P1").height());
			$("#NoEdit2").css("height",$("#P2").height());
			$("#NoEdit3").css("height",$("#P3").height());
			$("#NoEdit4").css("height",$("#P4").height());
			$("#NoEdit5").css("height",$("#P5").height());
			$("#NoEdit6").css("height",$("#P6").height());
		</c:if>
	</c:if>
	<c:if test="${acType eq 'doctor'}">
		<c:if test="${!empty syycxx && syycxx.flag!=2}">
		$("#NoEdit1").css("height",$("#P1").height());
		$("#NoEdit2").css("height",$("#P2").height());
		$("#NoEdit3").css("height",$("#P3").height());
		$("#NoEdit4").css("height",$("#P4").height());
		$("#NoEdit5").css("height",$("#P5").height());
		$("#NoEdit6").css("height",$("#P6").height());
		</c:if>
	</c:if>
	<c:if test="${acType eq 'hospital'}">
	$(".fillDateTime").show();
	</c:if>
	
	fillData();
	
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
				if(""=="${syycxx.reportdeptid}"){
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
					$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${syycxx.reportdeptid}");
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
			<c:when test="${!empty syycxx}">
				value:"${syycxx.reportdoctorid}",
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
				if(!"${syycxx.reportdoctorid}"){
					$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${user.docNo}");
				}else{
					$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${syycxx.reportdoctorid}");
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
 	
	//地址联动
	$("#sheng").change(function () { 
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
    });

    //户籍地址
	$("#regsheng").change(function () { 
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
    });
	
	get_public_sheng("now","sheng","shi","xian","xiang",null);
	get_public_sheng("reg","regsheng","regshi","regxian","regxiang",null);

	//数据初始化
    $("#idTypeName").val($("#idType option:selected").text());
	
 	//重新验证
  	setTimeout("$('.easyui-validatebox').validatebox()",700);
 	
  	<c:if test="${empty syycxx}">
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
		get_public_shi(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_shi(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+sheng+" option:selected").val();
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
		get_public_xian(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_xian(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+shi+" option:selected").val();
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
		get_public_xiang(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_xiang(type,sheng,shi,xian,xiang,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+xian+" option:selected").val();
	var nowXiang = "${nowXiang}";
	var regXiang = "${regXian}";
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
		publicfullAddress(type,xiang);
	});
}

function publicfullAddress(type,xiang){
	var code = $("#"+xiang+" option:selected").val();
	if("now"==type){
		$("#nowaddrareacode").val(code);
		$("#nowaddr").val($("#sheng option:selected").text()+$("#shi option:selected").text()+$("#xian option:selected").text()+$("#xiang option:selected").text());
	}else{
		$("#registerareacode").val(code);
		$("#registerareaaddr").val($("#regsheng option:selected").text()+$("#regshi option:selected").text()+$("#regxian option:selected").text()+$("#regxiang option:selected").text());
	}
}
function getInfoByID(){
	var idcard = $("#id").val();
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
	if(!$("#startDate").val() && !$("#diagnoseDate").val()){}else{
		var sD = new Date($("#startDate").val().replace("-", "/").replace("-", "/"));
		var dD = new Date($("#diagnoseDate").val().replace("-", "/").replace("-", "/"));
		if(dD<sD){
			$.messager.show({ title: '提示', msg: '就诊时间不能在发病时间之前！' });
			throw "X";
		}
	}
	//主要体征必填验证
	if(!$("#allbody_val").val() && !$("#allskin_val").val() && !$("#allrespiratory_val").val() && !$("#allcardiovascular_val").val() && !$("#allurinary_val").val() && !$("#alldigestive_val").val() && !$("#allnervous_val").val()){
		$.messager.show({ title: '提示', msg: '请填写主要症状。'});
		return false;
	}
	if($("#showbody").is(":checked") && !$("#bodyelse").val()){
		$.messager.show({ title: '提示', msg: '请填写全身症状及体征的其他项。'});
		return false;
	}
	if($("#showdige").is(":checked") && !$("#digeelse").val()){
		$.messager.show({ title: '提示', msg: '请填写消化系统的其他项。'});
		return false;
	}
	if($("#showresp").is(":checked") && !$("#respelse").val()){
		$.messager.show({ title: '提示', msg: '请填写呼吸系统的其他项。'});
		return false;
	}
	if($("#showcard").is(":checked") && !$("#cardelse").val()){
		$.messager.show({ title: '提示', msg: '请填写心脏血管系统的其他项。'});
		return false;
	}
	if($("#showurin").is(":checked") && !$("#urinelse").val()){
		$.messager.show({ title: '提示', msg: '请填写泌尿系统的其他项。'});
		return false;
	}
	if($("#shownerv").is(":checked") && !$("#nervelse").val()){
		$.messager.show({ title: '提示', msg: '请填写神经系统的其他项。'});
		return false;
	}
	if($("#showskin").is(":checked") && !$("#skinelse").val()){
		$.messager.show({ title: '提示', msg: '请填写皮肤和皮下组织的其他项。'});
		return false;
	}
	if($("#showfood").is(":checked") && !$("#foods").val()){
		$.messager.show({ title: '提示', msg: '请列出可疑食品名称。'});
		return false;
	}
	if(!$("#pathogenesis").val()){
		$.messager.show({ title: '提示', msg: '请勾选可疑原因。'});
		return false;
	}
	if(!$("#reportReason").val()){
		$.messager.show({ title: '提示', msg: '请勾选上报原因。'});
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
	var result = $("#syycbk").form('validate'); 
	var info = "确认保存？";
	<c:if test="${acType eq 'hospital'}">
		info="确认修改？";
	</c:if>
	if(result){
		if(checkForm()){
			$.messager.confirm('提示', info, function (r) {
				if(r){
					$.ajax({
						url:"${webroot}/cdc/f_json/saveSyycCard.shtml",
						data:$("#syycbk").serialize(),
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
			$("#NoEdit1").css("height",$("#crbbk").height());
			$("#upload-main").hide();
		}else{
			//刷新当前tab页
			parent.menuInfo.refreshMenu(parent.menuInfo.getCurSelectTabTitle());
		}
	});
}
//获取全身所有症状 
function get_body_val(){
	var bodyval="";
	var arr=$(":checkbox[name='body1']:checked");
	for(var i=0;i<arr.length;i++){
		bodyval+=$(arr[i]).val();		
		if(i<arr.length-1){
			bodyval+="|";
		}
	}
	$("#allbody_val").val(bodyval);
	if(bodyval.indexOf("其他")>-1){
		$("#bodyelse").show();
	}else{
		$("#bodyelse").val("");
		$("#bodyelse").hide();
	}
	
}
//获取皮肤/皮下组织所有症状 
function get_skin_val(){
	var arr=$(":checkbox[name='skin1']:checked");
	var skinval="";
	for(i=0;i<arr.length;i++){
		skinval+=$(arr[i]).val();
		if(i<arr.length-1){
			skinval+="|";
		}
	}
	$("#allskin_val").val(skinval);
	if(skinval.indexOf("其他")>-1){
		$("#skinelse").show();
	}else{
		$("#skinelse").val("");
		$("#skinelse").hide();
	}
}
//获取呼吸系统所有症状
function get_resp_val(){
	var arr=$(":checkbox[name='resp1']:checked");
	var respval="";
	for(i=0;i<arr.length;i++){
		respval+=$(arr[i]).val();
		if(i<arr.length-1){
			respval+="|";
		}
	}
	$("#allrespiratory_val").val(respval);
	if(respval.indexOf("其他")>-1){
		$("#respelse").show();
	}else{
		$("#respelse").hide();
		$("#respelse").val("");
	}
}
//获取心血管系统所有症状
function get_card_val(){
	var arr=$(":checkbox[name='card1']:checked");
	var cardval="";
	for(i=0;i<arr.length;i++){
		cardval+=$(arr[i]).val();
		if(i<arr.length-1){
			cardval+="|";
		}
	}
	$("#allcardiovascular_val").val(cardval);
	if(cardval.indexOf("其他")>-1){
		$("#cardelse").show();
	}else{
		$("#cardelse").hide();
		$("#cardelse").val("");
	}
}
//获取泌尿系统所有症状 
function get_urin_val(){
	var arr=$(":checkbox[name='urin1']:checked");
	var urinval="";
	for(i=0;i<arr.length;i++){
		urinval+=$(arr[i]).val();	
		if(i<arr.length-1){
			urinval+="|";
		}
	}
	$("#allurinary_val").val(urinval);
	if(urinval.indexOf("其他")>-1){
		$("#urinelse").show();
	}else{
		$("#urinelse").hide();
		$("#urinelse").val("");
	}
}
//获取消化系统症状 所有
function get_dige_val(){
	var arr=$(":checkbox[name='dige1']:checked");
	var digeval="";
	for(i=0;i<arr.length;i++){
		digeval+=$(arr[i]).val();
		if(i<arr.length-1){
			digeval+="|";
		}
	}
	$("#alldigestive_val").val(digeval);
	if(digeval.indexOf("其他")>-1){
		$("#digeelse").show();
	}else{
		$("#digeelse").hide();
		$("#digeelse").val("");
	}
}
//获取所有神经系统所有症状 
function get_nerv_val(){
	var arr=$(":checkbox[name='nerv1']:checked");
	var nervval="";
	for(i=0;i<arr.length;i++){
		nervval+=$(arr[i]).val();
		if(i<arr.length-1){
			nervval+="|";
		}
	}
	$("#allnervous_val").val(nervval);
	if(nervval.indexOf("其他")>-1){
		$("#nervelse").show();
	}else{
		$("#nervelse").hide();
		$("#nervelse").val("");
	}
}
//获取所有神经系统所有症状 
function get_kyyy_val(){
	var arr=$(":checkbox[name='kyyy']:checked");
	var nervval="";
	for(i=0;i<arr.length;i++){
		nervval+=$(arr[i]).val();
		if(i<arr.length-1){
			nervval+="|";
		}
	}
	$("#pathogenesis").val(nervval);
}
//获取所有神经系统所有症状 
function get_sbyy_val(){
	var arr=$(":checkbox[name='sbyy']:checked");
	var nervval="";
	for(i=0;i<arr.length;i++){
		nervval+=$(arr[i]).val();
		if(i<arr.length-1){
			nervval+="|";
		}
	}
	$("#reportReason").val(nervval);
}

function fillData(){
	var body="${syycxx.symptoms}";
	var bos=document.getElementsByName("body1");
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
	
	var skin="${syycxx.skins}";
	var ski=document.getElementsByName("skin1");
	if(!skin){}else{
		var skins=skin.split("|");
		for(var i=0;i<skins.length;i++){
			for(var j=0;j<ski.length;j++){
				if(skins[i]==ski[j].value){
					ski[j].checked=true;
					break;
				}
			}
		}
	}
	
	var resp="${syycxx.respiratorys}";
	var res=document.getElementsByName("resp1");
	if(!resp){}else{
		var resps=resp.split("|");
		for(var i=0;i<resps.length;i++){
			for(var j=0;j<res.length;j++){
				if(resps[i]==res[j].value){
					res[j].checked=true;
					break;
				}
			}
		}
	}
	
	var card="${syycxx.cardiovasculars}";
	var car=document.getElementsByName("card1");
	if(!card){}else{
		var cards=card.split("|");
		for(var i=0;i<cards.length;i++){
			for(var j=0;j<car.length;j++){
				if(cards[i]==car[j].value){
					car[j].checked=true;
					break;
				}
			}
		}
	}
	
	var urin="${syycxx.urinarys}";
	var uri=document.getElementsByName("urin1");
	if(!urin){}else{
		var urins=urin.split("|");
		for(var i=0;i<urins.length;i++){
			for(var j=0;j<uri.length;j++){
				if(urins[i]==uri[j].value){
					uri[j].checked=true;
					break;
				}
			}
		}
	}
	
	var dige="${syycxx.digestives}";
	var dig=document.getElementsByName("dige1");
	if(!dige){}else{
		var diges=dige.split("|");
		for(var i=0;i<diges.length;i++){
			for(var j=0;j<dig.length;j++){
				if(diges[i]==dig[j].value){
					dig[j].checked=true;
					break;
				}
			}
		}
	}
	
	var nerv="${syycxx.nervous}";
	var ner=document.getElementsByName("nerv1");
	if(!nerv){}else{
		var nervs=nerv.split("|");
		for(var i=0;i<nervs.length;i++){
			for(var j=0;j<ner.length;j++){
				if(nervs[i]==ner[j].value){
					ner[j].checked=true;
					break;
				}
			}
		}
	}
	
	get_body_val();
	get_skin_val();
	get_resp_val();
	get_card_val();
	get_urin_val();
	get_dige_val();
	get_nerv_val();
	
	var kyyyv="${syycxx.pathogenesis}";
	var kyv=document.getElementsByName("kyyy");
	if(!kyyyv){}else{
		var kyarr=kyyyv.split("|");
		for(var i=0;i<kyarr.length;i++){
			for(var j=0;j<kyv.length;j++){
				if(kyarr[i]==kyv[j].value){
					kyv[j].checked=true;
					break;
				}
			}
		}
	}
	
	if($("#showfood").is(':checked')){
		$('#foods').removeAttr('disabled');
	}else{
		$('#foods').attr('disabled','disabled').val('');
	}
	
	var rr="${syycxx.reportReason}";
	var rrckbs=document.getElementsByName("sbyy");
	if(!rr){}else{
		var rrs=rr.split("|");
		for(var i=0;i<rr.length;i++){
			for(var j=0;j<rrckbs.length;j++){
				if(rrs[i]==rrckbs[j].value){
					rrckbs[j].checked=true;
					break;
				}
			}
		}
	}
}

function audit(bktype,msid){
	//触发保存，验证表单数据是否符合规范
	var result = $("#syycbk").form('validate'); 
	if(result){
		if(checkForm()){
			$.messager.confirm("提示", "确认审核该报卡？", function (r) {
				if(r){
					$.ajax({
							url:"${webroot}/cdc/f_json/saveSyycCard.shtml",
							data:$("#syycbk").serialize(),
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


