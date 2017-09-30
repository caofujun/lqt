<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>食源性疾病病例监测上报卡</title>
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
	<form id="syjcbk">
		<div style="margin: 60px 5%;margin-top:10px; width: 90%;" >
			<center><h1>食源性疾病病例监测信息表</h1></center>
			<div style="width: 100%;">
				<div id="NoEdit1" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
				<div id="P1" class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">病例基本信息</span>
						<input type="hidden" id="allbody_val" name="symptoms" value="${syjcxx.symptoms}"/>
						<input type="hidden" id="allskin_val" name="skins" value="${syjcxx.skins}"/>
						<input type="hidden" id="allrespiratory_val" name="respiratorys" value="${syjcxx.respiratorys}"/>
						<input type="hidden" id="allcardiovascular_val" name="cardiovasculars" value="${syjcxx.cardiovasculars}"/>
						<input type="hidden" id="allurinary_val" name="urinarys" value="${syjcxx.urinarys}"/>
						<input type="hidden" id="alldigestive_val" name="digestives" value="${syjcxx.digestives}"/>
						<input type="hidden" id="allnervous_val" name="nervous" value="${syjcxx.nervous}"/>
						<input type="hidden" id="allfirstD_val" name="initdiagnosis" value="${syjcxx.initdiagnosis}"/>
						<input type="hidden" name="masterid" id="masterid" value="${syjcxx.masterid}" />
						<input type="hidden" name="cardid" id="cardid" value="${syjcxx.cardid}" />
						<input type="hidden" name="isemptycard" id="isemptycard" 
						<c:choose>
							<c:when test="${! empty syjcxx}">
								value="${syjcxx.isemptycard}" 
							</c:when>
							<c:when test="${isEmptyCard eq 'Y'}"> 
								value="1" 
							</c:when>
							<c:otherwise>
								value="0" 
							</c:otherwise>
						</c:choose> />
						<input type="hidden" name="flag" id="flag" value="${syjcxx.flag}"/>
						<input type="hidden" name="patientId" id="patientId" 
						<c:choose>
							<c:when test="${!empty syjcxx}">
								value="${syjcxx.patientId}"
							</c:when>
							<c:otherwise>
								value="${BRXX.patientId}"
							</c:otherwise>
						</c:choose>	 />
						<input type="hidden" name="visitId" id="visitId" 
						<c:choose>
							<c:when test="${!empty syjcxx}">
								value="${syjcxx.visitId}"
							</c:when>
							<c:otherwise>
								value="${BRXX.visitId}"
							</c:otherwise>
						</c:choose>
						/>
						<input type="hidden" name="doctorid" id="doctorid" 
						<c:choose>
							<c:when test="${!empty syjcxx}">
								value="${syjcxx.doctorid}"
							</c:when>
							<c:otherwise>
								<c:if test="${patientType eq 'zy'}">
									value="${empty BRXX.chargeDrId?user.docNo:BRXX.chargeDrId}"
								</c:if>
								<c:if test="${patientType eq 'mz'}">
									value="${empty BRXX.doctorId?user.docNo:BRXX.doctorId}"
								</c:if>
							</c:otherwise>
						</c:choose> />
						<input type="hidden" name="doctorname" id="doctorname" 
						<c:choose>
							<c:when test="${!empty syjcxx}">
								value="${syjcxx.doctorname}"
							</c:when>
							<c:otherwise>
								<c:if test="${patientType eq 'zy'}">
									value="${empty BRXX.chargeDrName?user.realname:BRXX.chargeDrName}"
								</c:if>
								<c:if test="${patientType eq 'mz'}">
									value="${empty BRXX.doctorName?user.realname:BRXX.doctorName}"
								</c:if>
							</c:otherwise>
						</c:choose> />
						<input type="hidden" name="officeid" id="officeid" 
						<c:choose>
							<c:when test="${!empty syjcxx}">
								value="${syjcxx.deptid}"
							</c:when>
							<c:otherwise>
								<c:if test="${patientType eq 'zy'}">
									value="${empty BRXX.deptCode?user.depNo:BRXX.deptCode}"
								</c:if>
								<c:if test="${patientType eq 'mz'}">
									value="${empty BRXX.deptId?user.realname:BRXX.deptId}"
								</c:if>
							</c:otherwise>
						</c:choose> />
						<input type="hidden" name="officename" id="officename" 
						<c:choose>
							<c:when test="${!empty syjcxx}">
								value="${syjcxx.deptname}"
							</c:when>
							<c:otherwise>
								<c:if test="${patientType eq 'zy'}">
									value="${BRXX.deptName}"
								</c:if>
								<c:if test="${patientType eq 'mz'}">
									value="${empty BRXX.deptName?'':BRXX.deptName}"
								</c:if>
							</c:otherwise>
						</c:choose> />
					</div>
					<table class="mainTable">
						<c:if test="${isEmptyCard eq 'Y' || syjcxx.isemptycard==1}">
						<tr>
							<td class="rightTextAlign" style="color:#3e9c06;">注：</td>
							<td colspan="5"><div style="color:#3e9c06;">门诊号/住院号/病例号可填身份证号</div></td>
						</tr>
						</c:if>
						<tr>
							<td class="rightTextAlign">门诊号：</td>
							<td style="width:310px;"><input type="text" name="mzid" id="mzid" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if>
							<c:choose>
								<c:when test="${!empty syjcxx}">
									value="${syjcxx.mzid}"
								</c:when>
								<c:when test="${patientType eq 'mz' }">
									value="${BRXX.mzid}"
								</c:when>
							</c:choose> />
							</td>
							<td class="rightTextAlign">是否复诊：</td>
							<td>
								<label style="padding-right: 10px;"><input type="radio" name="isreturnvisit" value="1" 
								<c:choose>
									<c:when test="${patientType eq 'mz'}">
										<c:choose>
											<c:when test="${syjcxx.isreturnvisit==1}">
												checked="checked"
											</c:when>
											<c:when test="${BRXX.isreturnvisit==1 }">
												checked="checked"
											</c:when>
										</c:choose>
									</c:when>
									<c:when test="${patientType eq 'zy'}">
										<c:choose>
											<c:when test="${syjcxx.isreturnvisit==1}">
												checked="checked"
											</c:when>
										</c:choose>
									</c:when>
								</c:choose>
							 /> 是</label>
								<label style="padding-right: 10px;">
								<input type="radio" name="isreturnvisit" value="0" class="easyui-validatebox"
								<c:choose>
									<c:when test="${patientType eq 'mz'}">
										<c:choose>
											<c:when test="${syjcxx.isreturnvisit==0}">
												checked="checked"
											</c:when>
											<c:when test="${BRXX.isreturnvisit==0 }">
												checked="checked"
											</c:when>
										</c:choose>
									</c:when>
									<c:when test="${patientType eq 'zy'}">
										<c:choose>
											<c:when test="${syjcxx.isreturnvisit==0}">
												checked="checked"
											</c:when>
											<c:otherwise>
												checked="checked"
											</c:otherwise>
										</c:choose>
									</c:when>
								</c:choose>
								/>否</label>
							</td>
							<td class="rightTextAlign">是否住院：</td>
							<td>
								<label style="padding-right: 10px;"><input type="radio" name="isinhospital" value="是" 
								<c:choose>
									<c:when test="${syjcxx.isinhospital eq '是'}">
										checked="checked"
									</c:when>
									<c:when test="${patientType eq 'zy' }">
										checked="checked"
									</c:when>
								</c:choose>
								/>是</label>
								<label style="padding-right: 10px;"><input type="radio" name="isinhospital" value="否" 
								<c:choose>
									<c:when test="${syjcxx.isinhospital eq '否'}">
										checked="checked"
									</c:when>
									<c:when test="${patientType eq 'mz' }">
										checked="checked"
									</c:when>
								</c:choose>
								/>否</label>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">${patientZyTitle}：</td>
							<td><input type="text" name="zyid" id="zyid" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if>
							<c:choose>
								<c:when test="${!empty syjcxx}">
									value="${syjcxx.zyid}"
								</c:when>
								<c:when test="${patientType eq 'zy' }">
									value="${BRXX.zyid}"
								</c:when>
							</c:choose> />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>患者姓名：</td>
							<td>
								<input type="text" name="patientName" id="patientName"  class="easyui-validatebox" required="true"
								<c:choose>
									<c:when test="${!empty syjcxx}">
										value="${syjcxx.patientName}"
									</c:when>
									<c:otherwise>
										value="${BRXX.patientName}"
									</c:otherwise>
								</c:choose> />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>患者性别：</td>
							<td>
								<label style="padding-right: 10px;">
									<input type="radio" name="sexid" id="nan" value="1" textvalue="男" onclick="$('#sexname').val($(':radio[name=sexid]:checked').attr('textvalue'));"
									<c:choose>
										<c:when test="${syjcxx.sexid==1}">
											checked="checked"
										</c:when>
										<c:when test="${BRXX.sex eq '男'}">
											checked="checked"
										</c:when>
									</c:choose> class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=sexid]\', \'性别\']'"/>男</label>
								<label style="padding-right: 10px;">
									<input type="radio" name="sexid" id="nv" value="2" textvalue="女" onclick="$('#sexname').val($(':radio[name=sexid]:checked').attr('textvalue'));"
									<c:choose>
										<c:when test="${syjcxx.sexid==2}">
											checked="checked"
										</c:when>
										<c:when test="${BRXX.sex eq '女'}">
											checked="checked"
										</c:when>
									</c:choose> />女</label>
									<input type="hidden" name="sexname" id="sexname" 
									<c:choose>
										<c:when test="${!empty syxx}">
											value="${syxx.sexname}"
										</c:when>
										<c:otherwise>
											value="${BRXX.sex}"
										</c:otherwise>
									</c:choose>
									/>
							</td>
							</tr>
						<tr>
							<td class="rightTextAlign">监护人姓名：</td>
							<td><input type="text" name="parentName" id="parentName" value="${syjcxx.parentName}"/></td>
							<td class="rightTextAlign"><span class="red">*</span>出生日期：</td>
							<td><input type="text" style="width: 130px;" name="birthday" id="birthday" onchange="$('#age').val(ages($(this).val()));$('ageUnit').val('岁')" class="Wdate text easyui-validatebox" required="true" onclick='WdatePicker({dateFmt:"yyyy-MM-dd",maxDate:"<fmt:formatDate value="${now}"/>"})'
							<c:choose>
								<c:when test="${!empty syjcxx}">
									value='<fmt:formatDate value="${syjcxx.birthday}" pattern="yyyy-MM-dd" />'
								</c:when>
								<c:otherwise>
									value='<fmt:formatDate value="${(patientType eq 'zy'?BRXX.birthDate:BRXX.birthday)}" pattern="yyyy-MM-dd" />'
								</c:otherwise>
							</c:choose>
							/></td>
							<td class="rightTextAlign"><span class="red">*</span>年龄：</td>
							<td>
								<input type="text" name="age" id="age" class="easyui-validatebox" required="true" style="width:66px;" onkeyup="this.value=this.value.replace(/\D/g,'')"
									<c:choose>
										<c:when test="${!empty syjcxx}">
											value="${syjcxx.age}"
										</c:when>
										<c:otherwise>
											value='${BRXX.age}'
										</c:otherwise>
									</c:choose> />
									<select style="width:60px;" name="ageUnit" id="ageUnit" class="easyui-combobox">
										<option value="岁" 
										<c:choose>
											<c:when test="${!empty syjcxx && syjcxx.ageUnit eq '岁'}">
												selected="selected"
											</c:when>
											<c:otherwise>
												${(BRXX.ageUnit eq '岁'?"selected='selected'":"")}
											</c:otherwise>
										</c:choose> >岁</option>
										<option value="月" 
										<c:choose>
											<c:when test="${!empty syjcxx && syjcxx.ageUnit eq '月'}">
												selected="selected"
											</c:when>
											<c:otherwise>
												${(BRXX.ageUnit eq '月'?"selected='selected'":"")}
											</c:otherwise>
										</c:choose>	>月</option>
										<option value="天" 
										<c:choose>
											<c:when test="${!empty syjcxx && syjcxx.ageUnit eq '天'}">
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
							<td class="rightTextAlign">身份证号：</td>
							<td>
								<input type="text" name="id" id="id" style="float:left;"  class="easyui-validatebox" required="true"  data-options="validType:'idcared[01]'" 
								<c:choose>
									<c:when test="${!empty syjcxx}">
										value="${syjcxx.id}"
									</c:when>
									<c:otherwise>
										value='${(patientType eq 'zy'?BRXX.idCard:BRXX.idnumber)}'
									</c:otherwise>
								</c:choose> />
								<!-- <input type="button" class="butt" value="提取性别和生日" onclick="getInfoByID();"/> -->
								<a href="javascript:;" class="tqxx" title="提取性别和生日" onclick="getInfoByID();"></a>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>患者职业：</td>
							<td>
								<select name="profession" id="profession" class="easyui-combobox" data-options="required:true" >
									<option></option>
									<c:forEach items="${groupClassify}" var="gc">
										<option value="${gc.dictName}" <c:if test="${syjcxx.profession eq gc.dictName}">selected="selected"</c:if> >${gc.dictName}</option>
									</c:forEach>
								</select>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>联系电话：</td>
							<td><input type="text" name="telp" id="telp" class="easyui-validatebox" required="true" 
							<c:choose>
								<c:when test="${!empty syjcxx}">
									value="${syjcxx.telp}"
								</c:when>
								<c:otherwise>
									value="${BRXX.tel}"
								</c:otherwise>
							</c:choose> />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">单位：</td>
							<td colspan="5"><input type="text" name="unit" id="unit" style="width: 90%" value="${syjcxx.unit}"/></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>患者属于：</td>
							<td colspan="5" style="line-height: 25px;">
								<c:forEach items="${patientBelong}" var="pb">
									<label style="padding-right: 10px;"><input type="radio" name="areatypeId" value="${pb.dictCode }" textvalue="${pb.dictName}" <c:if test="${(!empty syjcxx && syjcxx.areatypeId eq pb.dictCode) || pb.dictCode eq '1'}">checked="checked"</c:if> onclick="nowAddress();" />${pb.dictName}</label>
								</c:forEach>
								<input type="hidden" id="areatypeName" name="areatypeName" value="${syxx.areatypeName}"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>现住地址：</td>
							<td colspan="5">
								<select id="sheng" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>
								<select id="shi" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>
								<select id="xian" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>
								<select id="xiang" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="5">
								<input type="text" name="addr" id="addr" style="width: 90%;" value="${syjcxx.addr}"/>
								<input type="hidden" name="addrcode" id="addrcode" value="${syjcxx.addrcode }"/>
								<input type="hidden" name="addrcodevalue" id="addrcodevalue" value="${syjcxx.addrcodevalue }"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>发病时间：</td>
							<td><input type="text" name="startDate" id="startDate" readonly="readonly" value='<fmt:formatDate value="${syjcxx.startDate}" pattern="yyyy-MM-dd HH:mm:ss" />' style="width: 130px;" class="Wdate text easyui-validatebox" required="true" onclick='WdatePicker({dateFmt:"yyyy-MM-dd HH:00:00",maxDate:"<fmt:formatDate value="${now}"/>"})'/></td>
							<td class="rightTextAlign"><span class="red">*</span>就诊时间：</td>
							<td><input type="text" name="diagnoseDate" id="diagnoseDate" readonly="readonly" value='<fmt:formatDate value="${syjcxx.diagnoseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>' style="width: 130px;" class="Wdate text easyui-validatebox" required="true" onclick='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:00",maxDate:"<fmt:formatDate value="${now}"/>"})'/></td>
							<td class="rightTextAlign">死亡时间：</td>
							<td><input type="text" name="deaddate" id="deaddate" readonly="readonly" value='<fmt:formatDate value="${syjcxx.deaddate}" pattern="yyyy-MM-dd HH:mm:ss"/>' style="width: 130px;" class="Wdate text" onclick='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:00",maxDate:"<fmt:formatDate value="${now}"/>"})'/></td>
						</tr>
					</table>
				</div>
			<div class="card_cont">
				<div class="card_cont_h" >
					<span class="card_c_h_text">主要症状与体征</span>
				</div>
				<div id="NoEdit2" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;margin-top:30px;"></div>
				<div id="zzytzTabs" class="easyui-tabs" style="height: 170px;">
					<div title="全身症状及体征" style="padding:5px 20px;">
						<table>
							<tr style="line-height: 25px;">
								<td style="width:200px;">
									<label><input type="checkbox" name="body1" class="ckb" id="showfeverdegree" value="发热" onclick="get_body_val();"/>&nbsp;<span>发热</span>&nbsp;<span style="display: none;" id="frxg"><input type="text" id="feverdegree" name="symptomsFr" class="easyui-validatebox" style="width:50px;" value="${syjcxx.symptomsFr}" onblur="checkTmp();" />°C</span></label>
								</td>
								<td style="width:200px;">
									<label><input type="checkbox" name="body1" class="ckb" value="面色潮红" onclick="get_body_val();"/>&nbsp;<span>面色潮红</span></label>
								</td>
								<td style="width:200px;"> 
									<label><input type="checkbox" name="body1" class="ckb" value="面色苍白" onclick="get_body_val();"/>&nbsp;<span>面色苍白</span></label>
								</td>
								<td style="width:200px;">
									<label><input type="checkbox" name="body1" class="ckb" value="发绀" onclick="get_body_val();"/>&nbsp;<span>发绀</span></label>
								</td>
								<td style="width:200px;">
									<label><input type="checkbox" name="body1" class="ckb" value="脱水" onclick="get_body_val();"/>&nbsp;<span>脱水</span></label>
								</td>
							</tr>
							<tr  style="line-height: 25px;">
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="口渴" onclick="get_body_val();"/>&nbsp;<span>口渴</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="浮肿" onclick="get_body_val();"/>&nbsp;<span>浮肿</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="体重下降" onclick="get_body_val();"/>&nbsp;<span>体重下降</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="寒战" onclick="get_body_val();"/>&nbsp;<span>寒战</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="乏力" onclick="get_body_val();"/>&nbsp;<span>乏力</span></label>
								</td>
							</tr>
							<tr style="line-height: 25px;">
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="贫血" onclick="get_body_val();"/>&nbsp;<span>贫血</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="肿胀" onclick="get_body_val();"/>&nbsp;<span>肿胀</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="失眠" onclick="get_body_val();"/>&nbsp;<span>失眠</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="畏光" onclick="get_body_val();"/>&nbsp;<span>畏光</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="口有糊味" onclick="get_body_val();"/>&nbsp;<span>口有糊味</span></label>
								</td>
							</tr>
							<tr style="line-height: 25px;">
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="金属味" onclick="get_body_val();"/>&nbsp;<span>金属味</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="肥皂/咸味" onclick="get_body_val();"/>&nbsp;<span>肥皂/咸味</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="唾液过多" onclick="get_body_val();"/>&nbsp;<span>唾液过多</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="足/腕下垂" onclick="get_body_val();"/>&nbsp;<span>足/腕下垂</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="色素沉着" onclick="get_body_val();"/>&nbsp;<span>色素沉着</span></label>
								</td>
							</tr>
							<tr style="line-height: 25px;">
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="脱皮" onclick="get_body_val();"/>&nbsp;<span>脱皮</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="body1" class="ckb" value="指甲出现白带" onclick="get_body_val();"/>&nbsp;<span>指甲出现白带</span></label>
								</td>
								<td colspan="3">
									<label><input type="checkbox" name="body1" id="showbody" class="ckb" value="其他" onclick="get_body_val();"/>&nbsp;<span>其他</span></label>
									<label><input type="text" id="bodyelse" name="symptomsOther" class="field text" style="display: none;width:150px;" value="${syjcxx.symptomsOther}" /></label>
								</td>
							</tr>
						</table>
					</div>
					<div title="消化系统<span style='color:red'>*</span>" style="padding:5px 20px;">
						<table class="pageTable">
							<tr>
								<td>
									<label><input type="checkbox" name="dige1" class="ckb" value="恶心" onclick="get_dige_val();"/>&nbsp;恶心</label>
								</td>
								<td>
									<label><input id="xhot" type="checkbox" name="dige1" class="ckb" value="呕吐" onclick="get_dige_val();"/>&nbsp;呕吐&nbsp;<span id="otxg" style="display: none;"><input id="otcount" type="text" name="digestiveOt" value="${syjcxx.digestiveOt}" style="width:50px;" onkeyup="this.value=this.value.replace(/\D/g,'')" />&nbsp;次/天</span></label>
								</td>
								<td>
									<label><input type="checkbox" name="dige1" class="ckb" value="腹痛" onclick="get_dige_val();"/>&nbsp;腹痛</label>
								</td>
								<td colspan="2"  style="width:400px;">
									<label>
										<input id="isfx" type="checkbox" name="dige1" class="ckb" value="腹泻" onclick="get_dige_val();"/>&nbsp;腹泻&nbsp;
									</label>
										<span id="fxxg" style="display: none;">
											<select id="fxxz" name="digestiveFxxz" style="width: 100px;" class="easyui-combobox" >
												<option></option>
												<c:forEach items="${FXXZ}" var="fx">
													<option value="${fx.dictName}" <c:if test="${!empty syjcxx && (syjcxx.digestiveFxxz==fx.dictName)}"> selected="selected"</c:if> >${fx.dictName}</option>
												</c:forEach>
											</select>
											<%-- <input type="text" id="fxxzother" name="syjc.DIGESTIVE_FX_OTHER" value="${syjcxx.DIGESTIVE_FX_OTHER}" style="width: 120px;display: none;"/> --%>
											<input id="fxcount" type="text" name="digestiveFx" value="${syjcxx.digestiveFx}" style="width: 40px;" onkeyup="this.value=this.value.replace(/\D/g,'')" />次/天
										</span>
								</td>
							</tr>
							<tr>
								<td>
									<label><input type="checkbox" name="dige1" class="ckb" value="便秘" onclick="get_dige_val();"/>&nbsp;便秘</label>
								</td>
								<td>
									<label><input type="checkbox" name="dige1" class="ckb" value="里急后重" onclick="get_dige_val();"/>&nbsp;里急后重</label>
								</td>
								<td colspan="3">
									<label><input type="checkbox" name="dige1" id="showdige" class="ckb" value="其他" onclick="get_dige_val();"/>&nbsp;其他</label>
									<label><input type="text" id="digeelse" name="digestiveOther" class="field text" style="display: none;"  value="${syjcxx.digestiveOther}"/></label>
								</td>
							</tr>
						</table>
					</div>
					<div title="呼吸系统" style="padding:5px 20px;">
						<table class="pageTable">
							<tr>
								<td>
									<label><input type="checkbox" name="resp1" class="ckb" value="呼吸短促" onclick="get_resp_val();"/>&nbsp;呼吸短促</label>
								</td>
								<td>
									<label><input type="checkbox" name="resp1" class="ckb" value="咯血" onclick="get_resp_val();"/>&nbsp;咯血</label>
								</td>
								<td>
									<label><input type="checkbox" name="resp1" class="ckb" value="呼吸困难" onclick="get_resp_val();"/>&nbsp;呼吸困难</label>
								</td>
								<td colspan="2" style="width:400px;">
									<label><input type="checkbox" name="resp1" id="showresp" class="ckb" value="其他" onclick="get_resp_val();"/>&nbsp;其他</label>
									<label><input type="text" id="respelse" name="respiratoryOther" class="field text" style="display: none;" value="${syjcxx.respiratoryOther}"/></label>
								</td>
							</tr>
						</table>
					</div>
					<div title="心脏血管系统" style="padding:5px 20px;">
						<table class="pageTable">
							<tr>
								<td>
									<label><input type="checkbox" name="card1" class="ckb" value="胸闷" onclick="get_card_val();"/>&nbsp;胸闷</label>
								</td>
								<td>
									<label><input type="checkbox" name="card1" class="ckb" value="胸痛" onclick="get_card_val();"/>&nbsp;胸痛</label>
								</td>
								<td>
									<label><input type="checkbox" name="card1" class="ckb" value="心悸" onclick="get_card_val();"/>&nbsp;心悸</label>
								</td>
								<td>
									<label><input type="checkbox" name="card1" class="ckb" value="气短" onclick="get_card_val();"/>&nbsp;气短</label>
								</td>
								<td style="width:400px;">
									<label><input type="checkbox" name="card1" id="showcard" class="ckb" value="其他" onclick="get_card_val();"/>&nbsp;其他</label>
									<label><input type="text" id="cardelse" name="cardiovascularOther" class="field text" style="display: none;" value="${syjcxx.cardiovascularOther}"/></label>
								</td>
							</tr>
						</table>
					</div>
					<div title="泌尿系统" style="padding:5px 20px;">
						<table class="pageTable">
							<tr>
								<td>
									<label><input type="checkbox" name="urin1" class="ckb" value="尿量减少" onclick="get_urin_val();"/>&nbsp;尿量减少</label>
								</td>
								<td>
									<label><input type="checkbox" name="urin1" class="ckb" value="背部/肾区疼痛" onclick="get_urin_val();"/>&nbsp;背部/肾区疼痛</label>
								</td>
								<td>
									<label><input type="checkbox" name="urin1" class="ckb" value="尿中带血" onclick="get_urin_val();"/>&nbsp;尿中带血</label>
								</td>
								<td>
									<label><input type="checkbox" name="urin1" class="ckb" value="肾结石" onclick="get_urin_val();"/>&nbsp;肾结石</label>
								</td>
								<td style="width:400px;">
									<label><input type="checkbox" name="urin1" id="showurin" class="ckb" value="其他" onclick="get_urin_val();"/>&nbsp;其他</label>
									<label><input type="text" id="urinelse" name="urinaryOther" class="field text" style="display: none;" value="${syjcxx.urinaryOther}"/></label>
								</td>
							</tr>
						</table>
					</div>
					<div title="神经系统" style="padding:5px 20px;">
						<table class="pageTable">
							<tr>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="头痛" onclick="get_nerv_val();"/>&nbsp;头痛</label>
								</td>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="昏迷" onclick="get_nerv_val();"/>&nbsp;昏迷</label>
								</td>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="惊厥" onclick="get_nerv_val();"/>&nbsp;惊厥</label>
								</td>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="谵妄" onclick="get_nerv_val();"/>&nbsp;谵妄</label>
								</td>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="瘫痪" onclick="get_nerv_val();"/>&nbsp;瘫痪</label>
								</td>
							</tr>
							<tr>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="言语困难" onclick="get_nerv_val();"/>&nbsp;言语困难</label>
								</td>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="吞咽困难" onclick="get_nerv_val();"/>&nbsp;吞咽困难</label>
								</td>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="感觉异常" onclick="get_nerv_val();"/>&nbsp;感觉异常</label>
								</td>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="精神失常" onclick="get_nerv_val();"/>&nbsp;精神失常</label>
								</td>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="眩晕" onclick="get_nerv_val();"/>&nbsp;眩晕</label>
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
									<label>
										<input id="istkyc" type="checkbox" name="nerv1" class="ckb" value="瞳孔异常" onclick="get_nerv_val();"/>&nbsp;瞳孔异常&nbsp;
									</label>
									<span id="tkycxg" style="display: none;">
										<select id="tkyc" name="syjc.NERVOU_YC" style="width: 100px;" class="easyui-combobox">
											<option></option>
											<c:forEach items="${TKYC}" var="tk">
												<option value="${tk.dictCode}" <c:if test="!empty syjcxx && (syjcxx.NERVOU_YC==tk.DICNAME)"> selected="selected"</c:if> >${tk.dictName}</option>
											</c:forEach>
										</select>
									</span>
								</td>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="针刺感" onclick="get_nerv_val();"/>&nbsp;针刺感</label>
								</td>
								<td>
									<label><input type="checkbox" name="nerv1" class="ckb" value="抽搐" onclick="get_nerv_val();"/>&nbsp;抽搐</label>
								</td>
								<td colspan="2">
									<label><input type="checkbox" name="nerv1" id="shownerv" class="ckb" value="其他" onclick="get_nerv_val();"/>&nbsp;其他</label>
									<label><input type="text" id="nervelse" name="nervouOther" class="field text" style="display: none;"  value="${syjcxx.nervouOther}"/></label>
								</td>
							</tr>
						</table>
					</div>
					<div title="皮肤和皮下组织" style="padding:5px 20px;">
						<table class="pageTable">
							<tr>
								<td >
									<label><input type="checkbox" name="skin1" class="ckb" value="瘙痒" onclick="get_skin_val();"/>&nbsp;瘙痒</label>
								</td>
								<td>
									<label><input type="checkbox" name="skin1" class="ckb" value="烧灼感"onclick="get_skin_val();"/>&nbsp;烧灼感</label>
								</td>
								<td>
									<label><input type="checkbox" name="skin1" class="ckb" value="皮疹"onclick="get_skin_val();"/>&nbsp;皮疹</label>
								</td>
								<td>
									<label><input type="checkbox" name="skin1" class="ckb" value="出血点"onclick="get_skin_val();"/>&nbsp;出血点</label>
								</td>
								<td>
									<label><input type="checkbox" name="skin1" class="ckb" value="黄疸"onclick="get_skin_val();"/>&nbsp;黄疸</label>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<label><input type="checkbox" name="skin1" id="showskin" class="ckb" value="其他" onclick="get_skin_val();"/>&nbsp;其他</label>
									<label><input type="text" id="skinelse" name="skinOther" class="field text" style="display: none;" value="${syjcxx.skinOther}"/></label>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="card_cont">
				<div class="card_cont_h" >
					<span class="card_c_h_text">初步诊断</span>
				</div>
				<div id="NoEdit3" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
				<div id="P3" style="padding:5px 20px; ">
				<table class="pageTable" title="初步诊断">
					<tr>
						<td >
							<label><input type="checkbox" name="fd" class="fd" value="急性胃肠炎" onclick="get_firstD_val();"/>&nbsp;急性胃肠炎</label>
						</td>
						<td >
							<label><input type="checkbox" name="fd" class="fd" value="感染性腹泻" onclick="get_firstD_val();"/>&nbsp;感染性腹泻</label>
						</td>
						<td >
							<label><input type="checkbox" name="fd" class="fd" value="毒蘑菇中毒" onclick="get_firstD_val();"/>&nbsp;毒蘑菇中毒</label>
						</td>
						<td >
							<label><input type="checkbox" name="fd" class="fd" value="菜豆中毒" onclick="get_firstD_val();"/>&nbsp;菜豆中毒</label>
						</td>
						<td >
							<label><input type="checkbox" name="fd" class="fd" value="河豚中毒" onclick="get_firstD_val();"/>&nbsp;河豚中毒</label>
						</td>
					</tr>
					<tr>
						<td>
							<label><input type="checkbox" name="fd" class="fd" value="肉毒中毒" onclick="get_firstD_val();"/>&nbsp;肉毒中毒</label>
						</td>
						<td>
							<label><input type="checkbox" name="fd" class="fd" value="亚硝酸盐中毒" onclick="get_firstD_val();"/>&nbsp;亚硝酸盐中毒</label>
						</td>
						<td>
							<label><input type="checkbox" name="fd" class="fd" value="贝类毒素中毒" onclick="get_firstD_val();"/>&nbsp;贝类毒素中毒</label>
						</td>
						<td colspan="2">
							<label><input type="checkbox" name="fd" id="showfd" class="fd" value="其他" onclick="get_firstD_val();"/>&nbsp;其他</label>
							<label><input type="text" id="fdelse" name="initdiagnosisOther" class="field text" style="display: none;" value="${syjcxx.initdiagnosisOther}"/></label>
						</td>
					</tr>
				</table>
				</div>
			</div>
			<div class="card_cont">
				<div class="card_cont_h" >
					<span class="card_c_h_text">就诊前是否使用抗生素</span>
				</div>
				<div id="NoEdit4" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
				<div id="P4" style="padding:5px 20px; ">
					<table class="pageTable" title="就诊前是否使用抗生素" >
						<tr>
							<td>
								<label style="padding-right: 10px;"><input type="radio" name="isusedantibiotic" value="是" class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=isusedantibiotic]\', \'就诊前是否使用抗生素\']'" <c:if test="${syjcxx.isusedantibiotic eq '是'}">checked="checked"</c:if>/>是</label>
								<label style="padding-right: 10px;"><input type="radio" name="isusedantibiotic" value="否" class="easyui-validatebox" <c:if test="${syjcxx.isusedantibiotic eq '否'}">checked="checked"</c:if>/>否</label>
							</td>
							<td id="antiName" style="display: none;width:600px;">
								抗生素名称：<input type="text" style="width:320px;" id="antibiotic" name="antibiotic" value="${syjcxx.antibiotic}" /><span style="color:red;">(多个抗生素请用 “;”号隔开)</span>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="card_cont">
				<div class="card_cont_h" >
					<span class="card_c_h_text">既往病史</span>	
				</div>
				<div id="NoEdit5" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
				<div id="P5" style="padding:5px 20px; ">
				<table class="pageTable">
					<tr>
						<td >
							<label><input type="checkbox" name="illHistory" value="无" id="noill" onclick="disableOther('noill','illHistory');get_illHistory_val();"/>&nbsp;无</label>
						</td>
						<td >
							<label><input type="checkbox" name="illHistory" class="illHistory" value="一般消化道炎症" onclick="get_illHistory_val();"/>&nbsp;一般消化道炎症</label>
						</td>
						<td >
							<label><input type="checkbox" name="illHistory" class="illHistory" value="克罗恩病" onclick="get_illHistory_val();"/>&nbsp;克罗恩病</label>
						</td>
						<td >
							<label><input type="checkbox" name="illHistory" class="illHistory" value="消化道溃疡" onclick="get_illHistory_val();"/>&nbsp;消化道溃疡</label>
						</td>
						<td >
							<label><input type="checkbox" name="illHistory" class="illHistory" value="消化道肿瘤" onclick="get_illHistory_val();"/>&nbsp;消化道肿瘤</label>
						</td>
					</tr>
					<tr>
						<td>
							<label><input type="checkbox" name="illHistory" class="illHistory" value="肠易激综合征" onclick="get_illHistory_val();"/>&nbsp;肠易激综合征</label>
						</td>
						<td>
							<label><input type="checkbox" name="illHistory" class="illHistory" value="脑膜炎、脑肿瘤等" onclick="get_illHistory_val();"/>&nbsp;脑膜炎、脑肿瘤等</label>
						</td>
						<td colspan="3">
							<label><input type="checkbox" name="illHistory" id="showpho" class="illHistory" value="其他" onclick="get_illHistory_val();"/>&nbsp;其他</label>
							<label><input type="text" id="pho" name="previoushistoryOther" class="field text" style="display: none;" value="${syjcxx.previoushistoryOther}"/></label>
							<input type="hidden" name="previoushistory" id="previoushistory_val" value="${syjcxx.previoushistory}"/>
						</td>
					</tr>
				</table>
				</div>
			</div>
			<div id="NoEdit6" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
			<div id="P6" class="card_cont">
				<div class="card_cont_h" >
					<span class="card_c_h_text">暴露信息</span>
					<div class="card_c_h_btn">
						<a class="btn_icon" href="javascript:void(0)" onclick="addBLXX()" title="添加暴露信息"><i class="icon iconfont">&#xe665;</i></a>
					</div>
				</div>
				<div title="暴露信息" style="padding: 5px;">
					<jsp:include page="/WEB-INF/view/cards/includepages/syjc/blxxTable.jsp"></jsp:include>
				</div>
			</div>
			<div id="NoEdit7" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
			<div id="P7" class="card_cont">
				<div class="card_cont_h" >
					<span class="card_c_h_text">标本采集</span>
					<div class="card_c_h_btn">
						<a class="btn_icon" href="javascript:void(0)" onclick="addCYXX()" title="添加暴露信息"><i class="icon iconfont">&#xe665;</i></a>
					</div>
				</div>
				<div title="标本采集" style="padding: 5px;">
					<jsp:include page="/WEB-INF/view/cards/includepages/syjc/cjbbTable.jsp"></jsp:include>
				</div>
			</div>
			<div class="card_cont">
				<div class="card_cont_h" >
					<span class="card_c_h_text">填报机构信息</span>
				</div>
				<div id="NoEdit8" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
				<table id="P8" class="mainTable">
					<tr>
						<td class="rightTextAlign"><span class="red">*</span>填报科室：</td>
						<td>
							<input class="easyui-combobox" name="reportdeptid" id="reportdeptid" style="width: 142px;" value="${syjcxx.reportdeptid}"/>
							<input type="hidden" name="reportdeptname" id="reportdeptname" value="${syjcxx.reportdeptname}"/>
						</td>
						<td class="rightTextAlign"><span class="red">*</span>填报医生：</td>
						<td>
							<input class="easyui-combobox" name="reportdoctorid" id="reportdoctorid" style="width: 142px;" value="${syjcxx.reportdoctorid}"/>
							<input type="hidden" name="reportdoctorname" id="reportdoctorname" value="${syjcxx.reportdoctorname}"/>
						</td>
						<td class="fillDateTime rightTextAlign" style="display: none;" class="syjcfilldate"><span class="red">*</span>填报时间：</td>
						<td style="display: none;" class="fillDateTime syjcfilldate">
							<input type="text" name="reportdt" id="filldate" readonly="readonly" class="Wdate text" style="width: 130px;" 
							<c:choose>
								<c:when test="${!empty syjcxx}">
									 value='<fmt:formatDate value="${syjcxx.reportdt}" pattern="yyyy-MM-dd HH:mm:ss"/>'
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
		<div class="footer">
			<%@ include file="/WEB-INF/view/cards/includepages/bkOpts.jsp"%>
		</div>
	</div>
		
	</form>
	<div id="blxx_dialog" style="overflow:hidden;"></div>
	<script type="text/javascript">
	$(function(){
		
		//$("#zzytzTabs").tabs();
		
		<c:if test="${acType eq 'hospital'}">
			$(".fillDateTime").show();
			<c:if test="${!empty syjcxx.flag && syjcxx.flag!=0}">
				$("#NoEdit1").css("height",$("#P1").height());
				$("#NoEdit2").css("height",$("#zzytzTabs").height());
				$("#NoEdit3").css("height",$("#P3").height());
				$("#NoEdit4").css("height",$("#P4").height());
				$("#NoEdit5").css("height",$("#P5").height());
				$("#NoEdit6").css("height",$("#P6").height());
				$("#NoEdit7").css("height",$("#P7").height());
				$("#NoEdit8").css("height",$("#P8").height());
			</c:if>
		</c:if>
		<c:if test="${acType eq 'doctor'}">
			<c:if test="${!empty syjcxx && syjcxx.flag!=2}">
			$("#NoEdit1").css("height",$("#P1").height());
			$("#NoEdit2").css("height",$("#zzytzTabs").height());
			$("#NoEdit3").css("height",$("#P3").height());
			$("#NoEdit4").css("height",$("#P4").height());
			$("#NoEdit5").css("height",$("#P5").height());
			$("#NoEdit6").css("height",$("#P6").height());
			$("#NoEdit7").css("height",$("#P7").height());
			$("#NoEdit8").css("height",$("#P8").height());
			</c:if>
		</c:if>
		<c:if test="${acType eq 'hospital'}">
		$(".fillDateTime").show();
		</c:if>
		nowAddress();
		//疾病选择框
		$("#blxx_dialog").dialog({
			title: "暴露信息",
			width: 830,
			closed: true,
		    cache: false,
		    modal: true,
		    onClose:function(){},
		    onOpen:function(){
		    	/* $("#blxx_dialog").load("${webroot}/cdc/f_view/getBlxxTable.shtml",function(){
		    		$(".easyui-validatebox").validatebox();
		    	}); */
		    },
		    buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function () {
                	var result = $("#blxxForm").form('validate'); 
                	if(result){
                		var fbt = new Date($("#startDate").val().replace("-", "/").replace("-", "/"));
                		var jst = new Date($("#eatingtime").val().replace("-", "/").replace("-", "/"));
	                	if(jst>fbt){
	                		$.messager.show({ title: '提示', msg: '进食时间不能晚于发病日期！' });
	                		return;
	                	}
	                	//判断字符长度
	                	var alen = getLen($("#eatingplaces").val());
			            if(alen>100){
			            	$.messager.show({ title: '提示', msg: '进食地点文字长度超出范围(50个中文)！' });
	                		return;
			            }
			            var blen = getLen($("#purchaseplace").val());
			            if(blen>100){
			            	$.messager.show({ title: '提示', msg: '购买地点文字长度超出范围(50个中文)！' });
	                		return;
			            }
	                	var c = $("#chosenBLXX tbody tr").length;		//序号
                		var guid = guidG();
                		//先判断rowId是不是为空，不为空说明是在修改，然后就删除
                		var frid = $("#rowid").val();
                		if($("#rowid").val()){
                			var currow = $("#chosenBLXX tbody").find("tr[rowNum='"+$("#rowid").val()+"']").eq(0);
            				currow.remove();
                		}
                		//list下标
                		var xbArr = new Array();
                		$("#chosenBLXX input[name='orderno']").each(function(){
                			xbArr.push($(this).val());
                		})
                        xbArr.sort();
                		//console.log(xbArr);
                		var newxb = "";
                		if(xbArr.length>0){
                			newxb = (parseInt(xbArr[xbArr.length-1])+1);
                		}else{
                			newxb = 0;
                		}
                		
                		
	                    var blxxHtml = "<tr rownum='"+(!frid?guid:frid)+"'>"+
	                    "<td>"+
	                    "<a class='ico_check' style='margin: 3px;' title='修改' onclick=\"modifyRow('chosenBLXX','"+(!frid?guid:frid)+"')\"></a>"+
	                    "<a class='ico_del' style='margin: 3px;' title='删除' onclick=\"removeRow(this,'blxx')\"></a></td>"+
	                    "<td><span class='orderno'>"+(c+1)+"</span><input type='hidden' class='orderno_hide' name='orderno' value='"+newxb+"'/></td>"+
	                    "<td><span class='foodname'>"+$("#foodname").val()+"</span><input type='hidden' class='foodname_hide' name='ctgBk005Blxx["+newxb+"].foodname' value='"+$("#foodname").val()+"'/></td>"+
	        			"<td><span class='foodclass'>"+$("#foodclass").combobox("getValue")+"</span><input type='hidden' class='foodclass_hide' name='ctgBk005Blxx["+newxb+"].foodclass' value='"+$("#foodclass").combobox("getValue")+"'/></td>"+
	        			"<td><span class='packingway'>"+$("#packingway").combobox("getValue")+"</span><input type='hidden' class='packingway_hide' name='ctgBk005Blxx["+newxb+"].packingway' value='"+$("#packingway").combobox("getValue")+"'/></td>"+
	        			"<td><span class='foodbrand'>"+$("#foodbrand").val()+"</span><input type='hidden' class='foodbrand_hide' name='ctgBk005Blxx["+newxb+"].foodbrand' value='"+$("#foodbrand").val()+"'/></td>"+
	        			"<td><span class='manufacturer'>"+$("#manufacturer").val()+"</span><input type='hidden' class='manufacturer_hide' name='ctgBk005Blxx["+newxb+"].manufacturer' value='"+$("#manufacturer").val()+"'/></td>"+
	        			"<td>"+
	        			"<span class='purchaseplace'>"+$("#purchaseplace").val()+"</span><input type='hidden' class='purchaseplace_hide' name='ctgBk005Blxx["+newxb+"].purchaseplace' value='"+$("#purchaseplace").val()+"'/>"+
	        			"<input type='hidden' class='purcplacecode_hide' name='ctgBk005Blxx["+newxb+"].purcplacecode' value='"+$("#purchaseplacecode").val()+"'/>"+
	        			"</td>"+
	        			"<td>"+
	        			"<span class='eatingplaces'>"+$("#eatingplaces").val()+"</span><input type='hidden' class='eatingplaces_hide' name='ctgBk005Blxx["+newxb+"].eatingplaces' value='"+$("#eatingplaces").val()+"'/>"+
	        			"<input type='hidden' class='eatplacecode_hide' name='ctgBk005Blxx["+newxb+"].eatplacecode' value='"+$("#eatingplacecode").val()+"'/>"+
	        			"</td>"+
	        			"<td><span class='placetype'>"+$("#placetype").combobox("getValue")+"</span><input type='hidden' class='placetype_hide' name='ctgBk005Blxx["+newxb+"].placetype' value='"+$("#placetype").combobox("getValue")+"'/></td>"+
	        			"<td><span class='eatingtime'>"+$("#eatingtime").val().substring(0,13)+"时</span><input type='hidden' class='eatingtime_hide' name='ctgBk005Blxx["+newxb+"].eatingtime' value='"+$("#eatingtime").val()+"'/></td>"+
	        			"<td><span class='numberofeating'>"+$("#numberofeating").val()+"</span><input type='hidden' class='numberofeating_hide' name='ctgBk005Blxx["+newxb+"].numberofeating' value='"+$("#numberofeating").val()+"'/></td>"+
	        			"<td><span class='otherpeople'>"+$(":radio[name='isOtherSick']:checked").val()+"</span><input type='hidden' class='otherpeople_hide' name='ctgBk005Blxx["+newxb+"].otherpeople' value='"+$(":radio[name='isOtherSick']:checked").val()+"'/></td>"+
	                    "</tr>";
	                	$("#chosenBLXX tbody").append(blxxHtml);
	                	updateIndex("chosenBLXX","orderno");
	                	$("#blxx_dialog").dialog('close');
                	}
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#blxx_dialog').dialog('close');
                }
            }]
		});
		//上报科室
		Csm.combogrid.dep({
			//【必传】控件名称
			id: 'reportdeptid',
			//【可选参数】不传默认区session的医院ID
			hospId: '',
			//【可选参数】不传默认区所有监控科室
			//dataType: 'mz',
			onClickRow:function(index,row){
				$("#reportdeptname").val(row['deptName']);
			},
			onLoadSuccess:function(data){
				if(data.total>0){
					if(""=="${syjcxx.reportdeptid}"){
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
						$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${syjcxx.reportdeptid}");
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
			onSelect:function(index,row){
				$("#reportdoctorname").val(row['employeeName']);
			},
		<c:choose>
			<c:when test="${!empty syjcxx}">
				value:"${syjcxx.reportdoctorid}",
			</c:when>
			<c:when test="${acType eq 'hospital'}">
				value:"${user.username}",
			</c:when>
			<c:otherwise>
				value:"${user.docNo}",
			</c:otherwise>
		</c:choose>
			/* onBeforeLoad:function(param){
				if(!param.q){
					var defaultv = "${user.doctorId}";
					var doctor = "${syjcxx.reportdoctorid}";
					if(doctor){
						param.q=doctor;
					}else{
						param.q=defaultv;
					}
				}
			}, */
			onLoadSuccess:function(data){
				if(data){
					
					var defaultv = "${user.docNo}";
					var doctor = "${syjcxx.reportdoctorid}";
					if(doctor){
						$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',doctor);
					}else{
						$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',defaultv);
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
		
		$(":radio[name='isusedantibiotic']").click(function(){
			if($(this).val()=="是"){
				$("#antiName").show();
			}else{
				$("#antiName").hide();
			}
		});
		
		$(":radio[name='isinhospital']").click(function(){
			if($("#isemptycard").val()!="0"){
				if($(this).val()=="是"){
					$("#mzid").addClass("easyui-validatebox").attr("required"," true");
					$("#mzid").validatebox();
					$("#zyid").removeAttr("disabled");
					$("#zyid").addClass("easyui-validatebox").attr("required"," true");
					$("#zyid").validatebox();
				}else{
					$('#zyid').validatebox('remove'); 
					$("#zyid").val("");
					$("#zyid").attr("disabled","disabled");
					$("#mzid").removeAttr("disabled");
					$("#mzid").addClass("easyui-validatebox").attr("required"," true");
					$("#mzid").validatebox();
				}
			}else{
				if($(this).val()=="是"){
					$("#zyid").removeAttr("readonly");
					$("#mzid").attr("readonly","readonly");
					$("#zyid").addClass("easyui-validatebox").attr("required"," true");
					$("#zyid").validatebox();
					$('#mzid').validatebox('remove'); 
				}else{
					$("#mzid").removeAttr("readonly");
					$("#zyid").attr("readonly","readonly");
					$("#mzid").addClass("easyui-validatebox").attr("required"," true");
					$("#mzid").validatebox();
					$('#zyid').validatebox('remove'); 
				}
			}
		});
		
		//数据初始化
		$('#sexname').val($(':radio[name=sexid]:checked').attr('textvalue'));
		
		<c:choose>
		   	<c:when test="${!empty syjcxx.addr}">
		   		setTimeout("$('#addr').val('${syjcxx.addr}')",1200);
		   	</c:when>
		   	<c:when test="${patientType eq 'zy'}">
		   		<c:if test="${!empty BRXX.address}">
			   		setTimeout("$('#addr').val('${BRXX.address}')",1200);
		   		</c:if>
		   	</c:when>
		   	<c:when test="${patientType eq 'mz'}">
			   	<c:if test="${!empty BRXX.presentaddr}">
			   	setTimeout("$('#addr').val('${BRXX.presentaddr}')",1200);
		   		</c:if>
		   	</c:when>
	   </c:choose>
		
		fillData();
		
		/* //地址联动
		$("#sheng").change(function () { 
	    	get_shi();
	    	sheng=$('#sheng option:selected').html();

	    });
	    $("#shi").change(function () { 
	    	get_xian(); 
	    	shi=$('#shi option:selected').html();
		
	    });
	    $("#xian").change(function () { 
	    	get_xiang();
	    	xian=$('#xian option:selected').html(); 
		
	    });
	   $("#xiang").change(function (){
		   xiang=$('#xiang option:selected').html();
		   fullAddress();
		}); */
		$("#sheng").combobox({
			onSelect : function(r){
		    	get_shi();
		    	sheng=$('#sheng').combobox("getText");
			}
		});
		$("#shi").combobox({
			onSelect : function(r){
				get_xian(); 
				shi=$('#shi').combobox("getText");
			}
		});
		$("#xian").combobox({
			onSelect : function(r){
				get_xiang();
		    	xian=$('#xian').combobox("getText");
			}
		});
		$("#xiang").combobox({
			onSelect : function(r){
				xiang=$('#xiang').combobox("getText");
			   fulladdress();
			}
		});
		
		
	   <c:if test="${empty syjcxx}">
		getInfoByID();
		</c:if>
	   
	})
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
		//判断包含发热
		if(bodyval.indexOf("发热")>-1){
			$("#frxg").show();
		}else{
			$("#feverdegree").val("");
			$("#frxg").hide();
		}
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
		//判断包含腹泻
		if(digeval.indexOf("呕吐")>-1){
			$("#otxg").show();
		}else{
			$("#otcount").val("");
			$("#otxg").hide();
		}
		if(digeval.indexOf("腹泻")>-1){
			$("#fxxg").show();
		}else{
			$("#fxxz").combobox("setValue","");
			$("#fxcount").val("");
			$("#fxxg").hide();
		}
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
		if(nervval.indexOf("瞳孔异常")>-1){
			$("#tkycxg").show();
		}else{
			$("#tkyc").val("");
			$("#tkycxg").hide();
		}
		if(nervval.indexOf("其他")>-1){
			$("#nervelse").show();
		}else{
			$("#nervelse").hide();
			$("#nervelse").val("");
		}
	}
	// 
	function get_firstD_val(){
		var arr=$(":checkbox[name='fd']:checked");
		var fdval="";
		for(i=0;i<arr.length;i++){
			fdval+= $(arr[i]).val();
			if(i<arr.length-1){
				fdval+="|";
			}
		}
		$("#allfirstD_val").val(fdval);
		if(fdval.indexOf("其他")>-1){
			$("#fdelse").show();
		}else{
			$("#fdelse").hide();
			$("#fdelse").val("");
		}
	}
	//既往病史 
	function get_illHistory_val(){
		var arr=$(":checkbox[name='illHistory']:checked");
		var ihval="";
		for(i=0;i<arr.length;i++){
			ihval+=$(arr[i]).val();	
			if(i<arr.length-1){
				ihval+="|";
			}
		}
		$("#previoushistory_val").val(ihval);
		if(ihval.indexOf("其他")>-1){
			$("#pho").show();
		}else{
			$("#pho").hide();
			$("#pho").val("");
		}
	}
	function addBLXX(){
		//居中判断
		var top = document.documentElement.scrollTop || document.body.scrollTop;
		var mtop = parseInt(($(window).height()-300)/2);
		if(mtop>0){
			top += mtop;
		}
		$('#blxx_dialog').window('open').window('resize',{top: top});
		$("#blxx_dialog").load("${webroot}/cdc/f_view/getBlxxTable.shtml",function(){
    		$(".easyui-validatebox").validatebox();
    	});
	}
	function addCYXX(){
		var index = $("#chosenCYXX tbody tr").length;
		var xbArr = new Array();
		$("input[name='xb']").each(function(){
			xbArr.push($(this).val());
		})
		//list下标
        xbArr.sort();
		var newxb = "";
		if(xbArr.length>0){
			newxb = (parseInt(xbArr[xbArr.length-1])+1);
		}else{
			newxb = 0;
		}
		var html = "<tr rowNum='"+guidG()+"'>"+
		"<td><a class='ico_del' style='margin: 3px;' title='删除' onclick=\"removeRow(this,'cyxx')\"></a></td>"+
		"<td><span class='index' name=''>"+(index+1)+"</span><input type='hidden' name='xb' value='"+newxb+"' /></td>"+
		"<td><select class='bblx easyui-combobox' name='ctgBk005Cyxx["+newxb+"].specimentype' data-options='required:true'>"+
		<c:forEach items="${BBLX}" var="bbld">
			"<option value='${bbld.dictName}'>${bbld.dictName}</option>"+
		</c:forEach>
		"</select></td>"+
		"<td><input type='text' class='bbbh' name='ctgBk005Cyxx["+newxb+"].specimennumber' /></td>"+
		"<td><input type='text' class='bbsl easyui-validatebox' name='ctgBk005Cyxx["+newxb+"].specimencount' style='width:60px;' required='true' onkeyup=\"this.value=this.value.replace(/\D/g,'')\"/></td>"+
		"<td><select class='bbdw easyui-combobox' data-options='required:true' style='width:70px;' name='ctgBk005Cyxx["+newxb+"].numberofunits'><option></option><option value='g'>g</option><option value='ml'>ml</option><option value='份'>份</option></select></td>"+
		"<td><input type='text' class='cyrq Wdate text easyui-validatebox' required='true' name='ctgBk005Cyxx["+newxb+"].samplingdate' value='<fmt:formatDate value='${now}'/>' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'<fmt:formatDate value='${now}'/>'})\" /></td>"+
		"<td><input type='text' class='bbbz' name='ctgBk005Cyxx["+newxb+"].note'/></td>"+
		"</tr>";
		$("#chosenCYXX").append(html);
		updateIndex("chosenCYXX","index");
		//
		$.parser.parse($("#chosenCYXX tbody tr[rowNum='"+(index+1)+"']"));
		//
		$(".easyui-validatebox").validatebox();
	}
	function removeRow(ele,type){
		$.messager.confirm("提示","确认移除本行？",function(r){
			if(r){
				var currow = $(ele).parent().parent();
				currow.remove();
				if(type=="blxx"){
					updateIndex("chosenBLXX","orderno");
				}else{
					updateIndex("chosenCYXX","index");
				}
			}
		});
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
		var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#sheng option:selected").val();
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
		var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#shi option:selected").val();
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
		var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#xian option:selected").val();
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
	 	//国标编码
		$("#addrcode").val($("#xiang").combobox("getValue"));
		//国标地址
		$("#addrcodevalue").val($("#sheng").combobox("getText")+$("#shi").combobox("getText")+$("#xian").combobox("getText")+$("#xiang").combobox("getText"));
		//详细地址
		$("#addr").val($("#sheng").combobox("getText")+$("#shi").combobox("getText")+$("#xian").combobox("getText")+$("#xiang").combobox("getText"));
		$("#sheng").validatebox();
		$("#shi").validatebox();
		$("#xian").validatebox();
		$("#xiang").validatebox();
		$("#nowaddr").validatebox();
	}
	function nowAddress(){
		$("#sheng").combobox({value:"",disabled:false});
		$("#shi").combobox({value:"",disabled:false}); 
		$("#xian").combobox({value:"",disabled:false});
		$("#xiang").combobox({value:"",disabled:false});
		var val=$(":radio[name='areatypeId']:checked").val();
		$("#areatypeName").val($(":radio[name='areatypeId']:checked").attr("textvalue"));
		get_sheng();
		if(val==1){
			$("#sheng").combobox({value:"",disabled:true});
			$("#shi").combobox({value:"",disabled:true});
			$("#xian").combobox({value:"",disabled:true});
		}
		if(val==2){
			$("#sheng").combobox({value:"",disabled:true});
			$("#shi").combobox({value:"",disabled:true});
		}
		if(val==3){
			$("#sheng").combobox({value:"",disabled:true});
		}
		if(val==4){
		}
		if(val>=5){
			$("#sheng").combobox({value:"",disabled:true});
			$("#shi").combobox({value:"",disabled:true});
			$("#xian").combobox({value:"",disabled:true});
			$("#xiang").combobox({value:"",disabled:true});
		}
		$("#sheng").validatebox();
		$("#shi").validatebox();
		$("#xian").validatebox();
		$("#xiang").validatebox();
	}
	function report(){
		var result = $("#syjcbk").form('validate'); 
		if(result){
			if(checkForm()){
				$.messager.confirm('提示', "确认保存？", function (r) {
					if(r){
						$.ajax({
							url:"${webroot}/cdc/f_json/saveSYJCCard.shtml",
							data:$("#syjcbk").serialize(),
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
	function checkForm(){
		if(!$(":radio[name=isreturnvisit]:checked").val()){
			$.messager.show({ title: '提示', msg: '请选择是否复诊。' });
			return false;
		}
		if($("#age").val()){
			if($("#ageUnit").combobox("getValue")=='岁' && $("#age").val()>120){
				$.messager.show({ title: '提示', msg: '年龄最大不能超过120岁。' });
				return false;
			}
		}
		//验证家长姓名
		if($("#age").val() && $("#age").val()<=${NCMA}){
			if(!$("#parentName").val()){
				$.messager.show({ title: '提示', msg: '年龄小于或等于${NCMA}岁的患者必须填写家长姓名。' });
				return false;
			}
		}
		var startDate = new Date($("#startDate").val().replace("-", "/").replace("-", "/"));
		var diagnoseDate = new Date($("#diagnoseDate").val().replace("-", "/").replace("-", "/"));
		if(startDate>diagnoseDate){
			$.messager.show({ title: '提示', msg: '发病时间不能大于就诊时间。'});
			return false;
		}
		var deadv = $("#deaddate").val();
		if(deadv){
			var deaddate = new Date($("#deaddate").val().replace("-", "/").replace("-", "/"));
			if(deaddate<startDate){
				$.messager.show({ title: '提示', msg: '死亡时间不能小于发病时间。'});
				return false;
			}
		}
		//主要体征必填验证
		if(!$("#allbody_val").val() && !$("#allskin_val").val() && !$("#allrespiratory_val").val() && !$("#allcardiovascular_val").val() && !$("#allurinary_val").val() && !$("#alldigestive_val").val() && !$("#allnervous_val").val()){
			$.messager.show({ title: '提示', msg: '请填写主要症状与体征。'});
			return false;
		}
		if(!$("#alldigestive_val").val()){
			$.messager.show({ title: '提示', msg: '请填写主要症状与体征-呼吸系统。'});
			$("#zzytzTabs").tabs("select",1);
			return false;
		}
		//发热度数
		if($("#showfeverdegree").is(":checked") && !$("#feverdegree").val()){
			$.messager.show({ title: '提示', msg: '请填写全身症状及体征的发热度数。'});
			$("#zzytzTabs").tabs("select",0);
			return false;
		}
		if($("#showfeverdegree").is(":checked")){
			var temperature = $("#feverdegree").val();
			var reg=   /^([1-9]{1,2})(\.\d{1})?$/;
			if(reg.test(temperature)){}else{
				$.messager.show({ title: '提示', msg: '体温格式不正确（两位整数、一位小数）' });
				$("#zzytzTabs").tabs("select",0);
				return false; 
			}
		}
		
		if($("#showbody").is(":checked") && !$("#bodyelse").val()){
			$.messager.show({ title: '提示', msg: '请填写全身症状及体征的其他项。'});
			$("#zzytzTabs").tabs("select",0);
			return false;
		}
		if($("#xhot").is(":checked") && !$("#otcount").val()){
			$.messager.show({ title: '提示', msg: '请填写消化系统的呕吐次数。'});
			$("#zzytzTabs").tabs("select",1);
			return false;
		}
		if($("#isfx").is(":checked") && !$("#fxxz").combobox("getValue")){
			$.messager.show({ title: '提示', msg: '请填写消化系统的腹泻性状。'});
			$("#zzytzTabs").tabs("select",1);
			return false;
		}
		if($("#isfx").is(":checked") && !$("#fxcount").val()){
			$.messager.show({ title: '提示', msg: '请填写消化系统的腹泻次数。'});
			$("#zzytzTabs").tabs("select",1);
			return false;
		}
		if($("#showdige").is(":checked") && !$("#digeelse").val()){
			$.messager.show({ title: '提示', msg: '请填写消化系统的其他项。'});
			$("#zzytzTabs").tabs("select",1);
			return false;
		}
		if($("#showresp").is(":checked") && !$("#respelse").val()){
			$.messager.show({ title: '提示', msg: '请填写呼吸系统的其他项。'});
			$("#zzytzTabs").tabs("select",2);
			return false;
		}
		if($("#showcard").is(":checked") && !$("#cardelse").val()){
			$.messager.show({ title: '提示', msg: '请填写心脏血管系统的其他项。'});
			$("#zzytzTabs").tabs("select",3);
			return false;
		}
		if($("#showurin").is(":checked") && !$("#urinelse").val()){
			$.messager.show({ title: '提示', msg: '请填写泌尿系统的其他项。'});
			$("#zzytzTabs").tabs("select",4);
			return false;
		}
		if($("#istkyc").is(":checked") && !$("#tkyc option:selected").val()){
			$.messager.show({ title: '提示', msg: '请填写神经系统的瞳孔异常项。'});
			$("#zzytzTabs").tabs("select",5);
			return false;
		}
		if($("#shownerv").is(":checked") && !$("#nervelse").val()){
			$.messager.show({ title: '提示', msg: '请填写神经系统的其他项。'});
			$("#zzytzTabs").tabs("select",5);
			return false;
		}
		if($("#showskin").is(":checked") && !$("#skinelse").val()){
			$.messager.show({ title: '提示', msg: '请填写皮肤和皮下组织的其他项。'});
			$("#zzytzTabs").tabs("select",6);
			return false;
		}
		//初步诊断
		if(!$("#allfirstD_val").val()){
			$.messager.show({ title: '提示', msg: '请填写初步诊断。'});
			return false;
		}
		if(!$("#fdelse").is(":hidden") && !$("#fdelse").val()){
			$.messager.show({ title: '提示', msg: '请填写初步诊断的其他项。'});
			return false;
		}
		//就诊前是否使用抗生素
		if(!$("#antibiotic").is(":hidden") && !$("#antibiotic").val()){
			$.messager.show({ title: '提示', msg: '请填写就诊前使用抗生素名称。'});
			return false;
		}
		
		//既往病史
		if(!$("#previoushistory_val").val()){
			$.messager.show({ title: '提示', msg: '请填写既往病史。'});
			return false;
		}
		if(!$("#pho").is(":hidden") && !$("#pho").val()){
			$.messager.show({ title: '提示', msg: '请填写既往病史的其他项。'});
			return false;
		}
		//暴露信息
		if($("#chosenBLXX tbody tr").length<1){
			$.messager.show({ title: '提示', msg: '请填写暴露信息。'});
			return false;
		}else{
			try{
				var stt = new Date($("#startDate").val().replace("-", "/").replace("-", "/"));
				$("#chosenBLXX tbody tr").each(function(index){
					var etht = new Date($(this).find("input[class='eatingtime_hide']").eq(0).val().replace("-", "/").replace("-", "/"));
					if(etht>stt){
						throw "暴露信息表格中，第"+(index+1)+"条记录中，进食时间在发病时间之后，请修改！";
					}
				});
			}catch(e){
				$.messager.show({ title: '提示', msg: e});
				return false;
			}
		}
		//上报信息
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
	function fillData(){
		
		var c = $(":radio[name='isusedantibiotic']:checked").val();
		if(c=="是"){
			$("#antiName").show();
		}
		
		var t = $(":radio[name='isinhospital']:checked").val();
		if($("#isemptycard").val()!="0"){
			if(t=="是"){
				$("#mzid").addClass("easyui-validatebox").attr("required"," true");
				$("#mzid").validatebox();
				$("#zyid").removeAttr("disabled");
				$("#zyid").addClass("easyui-validatebox").attr("required"," true");
				$("#zyid").validatebox();
			}else{
				$('#zyid').validatebox('remove'); 
				$("#zyid").val("");
				$("#zyid").attr("disabled","disabled");
				$("#mzid").removeAttr("disabled");
				$("#mzid").addClass("easyui-validatebox").attr("required"," true");
				$("#mzid").validatebox();
			}
		}else{
			if(t=="是"){
				$("#zyid").removeAttr("readonly");
				$("#mzid").attr("readonly","readonly");
				$("#zyid").addClass("easyui-validatebox").attr("required"," true");
				$("#zyid").validatebox();
				$('#mzid').validatebox('remove'); 
			}else{
				$("#mzid").removeAttr("readonly");
				$("#zyid").attr("readonly","readonly");
				$("#mzid").addClass("easyui-validatebox").attr("required"," true");
				$("#mzid").validatebox();
				$('#zyid').validatebox('remove'); 
			}
		}
		
		var body="${syjcxx.symptoms}";
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
		
		var skin="${syjcxx.skins}";
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
		
		var resp="${syjcxx.respiratorys}";
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
		
		var card="${syjcxx.cardiovasculars}";
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
		
		var urin="${syjcxx.urinarys}";
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
		
		var dige="${syjcxx.digestives}";
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
		
		var nerv="${syjcxx.nervous}";
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
		
		//初步诊断
		var initDv="${syjcxx.initdiagnosis}";
		var initD=document.getElementsByName("fd");
		if(!initDv){}else{
			var initDvs=initDv.split("|");
			for(var i=0;i<initDvs.length;i++){
				for(var j=0;j<initD.length;j++){
					if(initDvs[i]==initD[j].value){
						initD[j].checked=true;
						break;
					}
				}
			}
		}
		
		var pvhisv="${syjcxx.previoushistory}";
		var pvhis=document.getElementsByName("illHistory");
		if(!pvhisv){}else{
			var pvhisvs=pvhisv.split("|");
			for(var i=0;i<pvhisvs.length;i++){
				for(var j=0;j<pvhis.length;j++){
					if(pvhisvs[i]==pvhis[j].value){
						pvhis[j].checked=true;
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
		get_firstD_val();
		get_illHistory_val();
		
		disableOther("noill","illHistory");
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
				$("#NoEdit1").css("height",$("#P1").height());
				$("#NoEdit2").css("height",$("#zzytzTabs").height());
				$("#NoEdit3").css("height",$("#P3").height());
				$("#NoEdit4").css("height",$("#P4").height());
				$("#NoEdit5").css("height",$("#P5").height());
				$("#NoEdit6").css("height",$("#P6").height());
				$("#NoEdit7").css("height",$("#P7").height());
				$("#NoEdit8").css("height",$("#P8").height());
				$("#upload-main").hide();
			}else{
				//刷新当前tab页
				parent.menuInfo.refreshMenu(parent.menuInfo.getCurSelectTabTitle());
			}
		});
	}
	function getInfoByID(){
		var idcard = $("#id").val();
		if(idcard && isIdCardNo(idcard)){
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
				$("#age").validatebox();$("#birthday").validatebox();
				$.messager.show({ title: '提示', msg: '从身份证提取性别和生日信息成功！' });
			}
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
	function disableOther(id,className){
		if($("#"+id).is(":checked")){
			$("."+className).removeAttr("checked");
			$("."+className).attr("disabled","disabled");
		}else{
			$("."+className).removeAttr("disabled");
		}
	}
	function modifyRow(tableId,rowId){
		var tr = $("#"+tableId+" tbody").find("tr[rowNum='"+rowId+"']").eq(0);
		var tds = $(tr).find("td");
		//居中判断
		var top = document.documentElement.scrollTop||document.body.scrollTop;
		var mtop = parseInt(($(window).height()-300)/2);
		if(mtop>0){
			top += mtop;
		}
		$('#blxx_dialog').window('open').window('resize',{top: top});
		$("#blxx_dialog").load("${webroot}/cdc/f_view/getBlxxTable.shtml",{
			'subid':rowId,
			'foodname':$(tds[2]).find(".foodname").eq(0).text(),
			'foodclass':$(tds[3]).find(".foodclass").eq(0).text(),
			'packingway':$(tds[4]).find(".packingway").eq(0).text(),
			'foodbrand':$(tds[5]).find(".foodbrand").eq(0).text(),
			'manufacturer':$(tds[6]).find(".manufacturer").eq(0).text(),
			'purchaseplace':$(tds[7]).find(".purchaseplace").eq(0).text(),
			'purcplacecode':$(tds[7]).find(".purcplacecode_hide").eq(0).val(),
			'eatingplaces':$(tds[8]).find(".eatingplaces").eq(0).text(),
			'eatplacecode':$(tds[8]).find(".eatplacecode_hide").eq(0).val(),
			'placetype':$(tds[9]).find(".placetype").eq(0).text(),
			'eatingtime':$(tds[10]).find(".eatingtime_hide").eq(0).val(),
			'numberofeating':$(tds[11]).find(".numberofeating").eq(0).text(),
			'otherpeople':$(tds[12]).find(".otherpeople").eq(0).text()
		},function(){
    		$(".easyui-validatebox").validatebox();
    	});
	}
	function checkTmp(){
		var temperature = $("#feverdegree").val();
		var reg=   /^([1-9]{1,2})(\.\d{1})?$/;
		if(reg.test(temperature)){}else{
			$.messager.show({ title: '提示', msg: '体温格式不正确（两位整数、一位小数）' });
		}
	}
	function getLen(val){
		var alen = 0;
        for (var i = 0; i < val.length; i++) {
           var length = val.charCodeAt(i);
           if(length>=0&&length<=128){
                alen += 1;
            }else{
                alen += 2;
            }
        }
        return alen;
	}
	
	function audit(bktype,msid){
		//触发保存，验证表单数据是否符合规范
		var result = $("#syjcbk").form('validate'); 
		if(result){
			if(checkForm()){
				$.messager.confirm("提示", "确认审核该报卡？", function (r) {
					if(r){
						$.ajax({
							    url:"${webroot}/cdc/f_json/saveSYJCCard.shtml",
							    data:$("#syjcbk").serialize(),
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
	
	function updateIndex(tableId,orderClass){
		$("#"+tableId+" tbody tr").each(function(index){
			$(this).find("."+orderClass).eq(0).text(index+1);
		})
	}
	
	function guidG() {
		  function S4() {
		    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
		  }
		  return (S4()+S4()+""+S4()+""+S4()+""+S4()+""+S4()+S4()+S4());
		}
	</script>
</body>
</html>