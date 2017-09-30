<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>居民死因报告卡</title>
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
	<form id="sybk" name="sybk">
		<div style="margin: 60px 5%;margin-top:10px; width: 90%;">
			<center><h1>居民死因报告卡</h1></center>
			<div style="width: 100%;">		
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">死者基本信息</span>
						<input type="hidden" name="masterid" id="masterid" value="${syxx.masterid}"/>
						<input type="hidden" name="cardid" id="cardid" value="${syxx.cardid}" />
						<input type="hidden" name="doctorid" id="doctorid" 
						<c:choose>
							<c:when test="${!empty syxx}">
								value="${syxx.doctorid}"
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
							<c:when test="${!empty syxx}">
								value="${syxx.doctorname}"
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
							<c:when test="${!empty syxx}">
								value="${syxx.officeid}"
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
							<c:when test="${!empty syxx}">
								value="${syxx.officename}"
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
						<input type="hidden" name="isemptycard" id="isemptycard" 
						<c:choose>
							<c:when test="${!empty syxx}">
								value="${syxx.isemptycard}" 
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
						<c:if test="${isEmptyCard eq 'Y' || syxx.isemptycard==1}">
						<tr>
							<td class="rightTextAlign" style="color:#3e9c06;">注：</td>
							<td colspan="5"><div style="color:#3e9c06;">门诊号/住院号/病例号可填身份证号</div></td>
						</tr>
						</c:if>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>病例号：</td>
							<td>
								<input type="text" name="patientId" id="patientId" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if> class="easyui-validatebox" required="true"
								<c:choose>
									<c:when test="${!empty syxx}">
										value="${syxx.patientId}"
									</c:when>
									<c:when test="${!empty BRXX}">
										value="${BRXX.patientId}"
									</c:when>
								</c:choose> />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>门诊/住院唯一号：</td>
							<td style="width: 300px;">
								<input type="text" id="uniqId" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if> class="easyui-validatebox" required="true"
								<c:choose>
									<c:when test="${patientType eq 'zy' }">
										name="zyid" value="${!empty syxx.zyid?syxx.zyid:BRXX.zyid}"
									</c:when>
									<c:when test="${patientType eq 'mz' }">
										name="mzid" value="${!empty syxx.mzid?syxx.mzid:BRXX.mzid}"
									</c:when>
								</c:choose> />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>门诊/住院次数：</td>
							<td>
								<input type="text" name="visitId" id="visitId" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if> class="easyui-validatebox" required="true" onkeyup="this.value=this.value.replace(/\D/g,'')" 
								<c:choose>
									<c:when test="${!empty syxx}">
										value="${syxx.visitId}"
									</c:when>
									<c:when test="${!empty BRXX}">
										value="${BRXX.visitId}"
									</c:when>
								</c:choose> />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>死者姓名：</td>
							<td>
								<input type="text" name="patientName" id="patientName" class="easyui-validatebox" required="true"
								<c:choose>
									<c:when test="${!empty syxx}">
										value="${syxx.patientName}"
									</c:when>
									<c:when test="${!empty BRXX}">
										value="${BRXX.patientName}"
									</c:when>
								</c:choose>	 />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>性别：</td>
							<td>
								<label style="padding-right: 10px;">
									<input type="radio" name="sexid" id="nan" value="1" textvalue="男" class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=sexid]\', \'性别\']'" onclick="$('#sexname').val($(':radio[name=sexid]:checked').attr('textvalue'));unLockWomanStatus();"
									<c:choose>
										<c:when test="${syxx.sexid==1}">
											checked="checked"
										</c:when>
										<c:when test="${BRXX.sex eq '男'}">
											checked="checked"
										</c:when>
									</c:choose> />男
								</label>
								<label style="padding-right: 10px;">
									<input type="radio" name="sexid" id="nv" value="2" textvalue="女" onclick="$('#sexname').val($(':radio[name=sexid]:checked').attr('textvalue'));unLockWomanStatus();" 
									<c:choose>
										<c:when test="${syxx.sexid==2}">
											checked="checked"
										</c:when>
										<c:when test="${BRXX.sex eq '女'}">
											checked="checked"
										</c:when>
									</c:choose> />女
							   	</label>
								<input type="hidden" name="sexname" id="sexname" value="${syxx.sexname}" />
							</td>
							<td class="rightTextAlign">女性情况：</td>
							<td>
								<select id="womantypeid" name="womantypeid" class="easyui-combobox" data-options='onSelect:function(r){$("#womantypename").val($("#womantypeid").combobox("getText"))}'>
									<option></option>
									<c:forEach items="${NXQK}" var="ws">
										<option value="${ws.dictCode }" <c:if test="${syxx.womantypeid eq ws.dictCode}">selected="selected"</c:if> >${ws.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="womantypename" id="womantypename" value="${syxx.womantypename }" />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>出生日期：</td>
							<td>
								<input style="width: 130px;" type="text" name="birtyday" id="birthday" class="Wdate text easyui-validatebox" required="true" onclick='WdatePicker({dateFmt:"yyyy-MM-dd",maxDate:"<fmt:formatDate value="${now}"/>"})'  onblur="$('#age').val(ages($(this).val()));$('#ageunit').val('岁');$('#age').validatebox();"
									<c:choose>
										<c:when test="${!empty syxx}">
											value='<fmt:formatDate value="${syxx.birtyday}" pattern="yyyy-MM-dd" />'
										</c:when>
										<c:otherwise>
											value='<fmt:formatDate value="${(patientType eq 'zy'?BRXX.birthDate:BRXX.birthday)}" pattern="yyyy-MM-dd" />'
										</c:otherwise>
									</c:choose>
								/>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>年龄：</td>
							<td>
								<input type="text" name="age" id="age" class="easyui-validatebox" required="true" style="width:66px;" onkeyup="this.value=this.value.replace(/\D/g,'')"
								<c:choose>
									<c:when test="${!empty syxx}">
										value="${syxx.age}"
									</c:when>
									<c:otherwise>
										value='${BRXX.age}'
									</c:otherwise>
								</c:choose> />
								<select style="width:60px;" name="ageunit" id="ageunit" class="easyui-combobox">
									<option value="岁" 
									<c:choose>
										<c:when test="${!empty syxx && syxx.ageunit eq '岁'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '岁'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose> >岁</option>
									<option value="月" 
									<c:choose>
										<c:when test="${!empty syxx && syxx.ageunit eq '月'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '月'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose>	>月</option>
									<option value="天" 
									<c:choose>
										<c:when test="${!empty syxx && syxx.ageunit eq '天'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '天'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose> >天</option>
								</select>
							</td>
							<td class="rightTextAlign">国家或地区：</td>
							<td><input type="text" name="area" id="area" value="${syxx.area}"/></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>证件类型：</td>
							<td>
								<select id="idtype" name="idtype"  class="easyui-combobox" data-options='required:true,onSelect:function(r){$("#idtypevalue").val($("#idtype").combobox("getText"));$("#id").validatebox();}'>
									<c:forEach items="${ZJLX}" var="ct">
										<option value="${ct.dictCode }" <c:if test="${syxx.idtype eq ct.dictCode}">selected="selected"</c:if> >${ct.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="idtypevalue" name="idtypevalue" value="${syxx.idtypevalue}"/>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>有效证件号：</td>
							<td>
								<input type="text" name="id" id="id" style="float:left;" class="easyui-validatebox" data-options="required:true,validType:'idcared'" 
								<c:choose>
									<c:when test="${!empty syxx}">
										value="${syxx.id}"
									</c:when>
									<c:otherwise>
										value='${(patientType eq 'zy'?BRXX.idCard:BRXX.idnumber)}'
									</c:otherwise>
								</c:choose> />
								<!-- <input type="button" class="butt" value="提取性别和生日" onclick="getInfoByID();"/> -->
								<a href="javascript:;" class="tqxx" title="提取性别和生日" onclick="getInfoByID();"></a>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>民族：</td>
							<td>
								<select id="nationid" name="nationid" class="easyui-combobox" data-options="required:true,onSelect:function(r){$('#nationname').val($('#nationid').combobox('getText'))}" >
									<option></option>
									<c:forEach items="${NATION}" var="nt">
										<option value="${nt.dictCode }" <c:if test="${syxx.nationid eq nt.dictCode}">selected="selected"</c:if> >${nt.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="nationname" id="nationname" value="${syxx.nationname}"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>个人身份：</td>
							<td>
								<select id="professionid" name="professionid" class="easyui-combobox" data-options="required:true,onSelect:function(r){$('#professionname').val($('#professionid').combobox('getText'))}" >
									<option></option>
									<c:forEach items="${GRSF}" var="pi">
										<option value="${pi.dictCode }" <c:if test="${syxx.professionid eq pi.dictCode}">selected="selected"</c:if> >${pi.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="professionname" id="professionname" value="${syxx.professionname}"/>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>文化程度：</td>
							<td>
								<select id="educationid" name="educationid" class="easyui-combobox" data-options="required:true,onSelect:function(r){$('#educationname').val($('#educationid').combobox('getText'))}" >
									<option></option>
									<c:forEach items="${EDUCATION}" var="ec">
										<option value="${ec.dictCode }" <c:if test="${syxx.educationid eq ec.dictCode}">selected="selected"</c:if> >${ec.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="educationname" name="educationname" value="${syxx.educationname}"/>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>婚姻状况：</td>
							<td>
								<select id="marriageid" name="marriageid" class="easyui-combobox" data-options="required:true,onSelect:function(r){$('#marriagevalue').val($('#marriageid').combobox('getText'))}" >
									<option></option>
									<c:forEach items="${HYZK}" var="ms">
										<option value="${ms.dictCode }" <c:if test="${syxx.marriageid eq ms.dictCode}">selected="selected"</c:if> >${ms.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="marriagevalue" name="marriagevalue" value="${syxx.marriagevalue}"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">生前工作单位：</td>
							<td><input type="text" id="workplace" name="workplace" value="${syxx.workplace}" /></td>
							<td class="rightTextAlign"><span class="red">*</span>死亡日期：</td>
							<td>
								<input style="width: 130px;" type="text" name="deaddate" id="deaddate" class="Wdate text easyui-validatebox" required="true" value='<fmt:formatDate value="${syxx.deaddate}" type="both"/>' onclick='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm",maxDate:"<fmt:formatDate value="${now}" type="both"/>"})' /></td>
							<td class="rightTextAlign"><span class="red">*</span>死亡地点：</td>
							<td>
								<select id="deadzoneid" name="deadzoneid" class="easyui-combobox" data-options="required:true,onSelect:function(r){$('#deadzonevalue').val($('#deadzoneid').combobox('getText'))}" >
									<option></option>
									<c:forEach items="${SWDD}" var="dp">
										<option value="${dp.dictCode }" <c:if test="${syxx.deadzoneid eq dp.dictCode}">selected="selected"</c:if> >${dp.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="deadzonevalue" id="deadzonevalue" value="${syxx.deadzonevalue}"/>
							</td>
						</tr>
						<tr class="SNPlace"  style="display: none;">
							<td class="rightTextAlign">死亡报卡流水号：</td>
							<td><input type="text" name="serialnumber" id="serialnumber" value="${syxx.serialnumber}" /></td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">户籍地址信息</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>类型：</td>
							<td>
								<select id="regaddrtypeid" name="regaddrtypeid" class="easyui-combobox" data-options="required:true,onSelect:function(r){regAddress()}" >
									<c:forEach items="${patientBelong}" var="pb">
										<option value="${pb.dictCode}" <c:if test="${syxx.regaddrtypeid==pb.dictCode}">selected="selected"</c:if> >${pb.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="regaddrtypename" name="regaddrtypename" value="${syxx.regaddrtypename}"/>
								<select id="regSheng" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>
								<select id="regShi" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>
								<select id="regXian" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>
								<select id="regXiang" class="easyui-combobox" data-options="required:true">
									<option></option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>详细户籍地：</td>
							<td>
								<input type="text" name="registrationaddr" id="registrationaddr" style="width:90%;" class="easyui-validatebox" required="true"/>
								<input type="hidden" name="registrationaddrcode" id="registrationaddrcode" value="${syxx.registrationaddrcode }"/>
								<input type="hidden" name="registrationaddrcodevalue" id="registrationaddrcodevalue" value="${syxx.registrationaddrcodevalue}"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">现住地址信息</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>类型：</td>
							<td>
								<select id="nowaddrtypeid" name="nowaddrtypeid" class="easyui-combobox" data-options="required:true,onSelect:function(r){nowAddress()}" >
									<c:forEach items="${patientBelong}" var="pb">
										<option value="${pb.dictCode}" <c:if test="${mz.areatypeid==pb.dictCode}">selected="selected"</c:if> >${pb.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="nowaddrtypename" id="nowaddrtypename" value="${syxx.nowaddrtypename }"/>
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
							<td class="rightTextAlign"><span class="red">*</span>详细现住地：</td>
							<td>
								<input type="text" name="nowaddr" id="nowaddr" style="width:90%;" class="easyui-validatebox" required="true"/>
								<input type="hidden" name="nowaddrcode" id="nowaddrcode" value="${syxx.nowaddrcode }"/>
								<input type="hidden" name="nowaddrcodevalue" id="nowaddrcodevalue" value="${syxx.nowaddrcodevalue}"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">家属相关信息</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign" style="width:140px;"><c:if test="${isFIN==1}"><span class="red">*</span></c:if>姓名：</td>
							<td><input type="text" name="folkname" id="folkname" value="${syxx.folkname}" <c:if test="${isFIN==1}">class="easyui-validatebox" required="true" </c:if> /></td>
							<td class="rightTextAlign" style="width:105px;"><c:if test="${isFIN==1}"><span class="red">*</span></c:if>联系电话：</td>
							<td><input type="text" name="folktel" id="folktel" value="${syxx.folktel}" <c:if test="${isFIN==1}">class="easyui-validatebox" required="true" </c:if> /></td>
							<td class="rightTextAlign"><c:if test="${isFIN==1}"><span class="red">*</span></c:if>联系地址：</td>
							<td><input type="text" name="folkaddr" id="folkaddr" value="${syxx.folkaddr}" style="width:250px;" <c:if test="${isFIN==1}">class="easyui-validatebox" required="true" </c:if> /></td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">致死的主要疾病诊断(请填写具体的病名,勿填症状体征)</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign" style="width:200px;"><span class="red">*</span>(a)直接导致死亡的疾病或情况：</td>
							<td style="width:205px;"><input type="text" name="acause" id="acause" style="width:200px;" value="${syxx.acause}" class="easyui-validatebox" required="true" /></td>
							<td class="rightTextAlign">ICD10</td>
							<td style="width:142px;"><input type="text" name="aicd10" id="aicd10" value="${syxx.aicd10}" class="easyui-validatebox" required="true" onclick="showICDdialog('aicd10','acause');"/></td>
							<td class="rightTextAlign" style="width:110px;">发病至死亡间隔</td>
							<td style="width:70px;"><input type="text" name="ascopetime" id="ascopetime" style="width:60px;" value="${syxx.ascopetime}" onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="this.value=this.value.replace(/\D/g,'')" class="easyui-validatebox" required="true"/></td>
							<td class="rightTextAlign" style="width:60px;">单位</td>
							<td>
								<select id="ascopetimeunit" name="ascopetimeunit" style="width:60px;" class="easyui-combobox" data-options="required:true">
									<option value="小时" <c:if test="${syxx.ascopetimeunit eq '小时'}">selected="selected"</c:if>>小时</option>
									<option value="天" <c:if test="${syxx.ascopetimeunit eq '天'}">selected="selected"</c:if>>天</option>
									<option value="月" <c:if test="${syxx.ascopetimeunit eq '月'}">selected="selected"</c:if>>月</option>
									<option value="年" <c:if test="${syxx.ascopetimeunit eq '年'}">selected="selected"</c:if>>年</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">(b)引起(a)的疾病或情况：</td>
							<td><input type="text" name="bcause" id="bcause" style="width:200px;" value="${syxx.bcause}" /></td>
							<td class="rightTextAlign">ICD10</td>
							<td><input type="text" name="bicd10" id="bicd10" value="${syxx.bicd10}" onclick="showICDdialog('bicd10','bcause');"/></td>
							<td class="rightTextAlign">发病至死亡间隔</td>
							<td style="width:70px;"><input type="text" name="bscopetime" id="bscopetime" style="width:60px;" value="${syxx.bscopetime}" onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="this.value=this.value.replace(/\D/g,'')"/></td>
							<td class="rightTextAlign">单位</td>
							<td>
								<select id="bscopetimeunit" name="bscopetimeunit" style="width:60px;" class="easyui-combobox">
									<option value="小时" <c:if test="${syxx.bscopetimeunit eq '小时'}">selected="selected"</c:if> >小时</option>
									<option value="天" <c:if test="${syxx.bscopetimeunit eq '天'}">selected="selected"</c:if>>天</option>
									<option value="月" <c:if test="${syxx.bscopetimeunit eq '月'}">selected="selected"</c:if>>月</option>
									<option value="年" <c:if test="${syxx.bscopetimeunit eq '年'}">selected="selected"</c:if>>年</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">(c)引起(b)的疾病或情况：</td>
							<td><input type="text" name="ccause" id="ccause" style="width:200px;" value="${syxx.ccause }"/></td>
							<td class="rightTextAlign">ICD10</td>
							<td><input type="text" name="cicd10" id="cicd10" value="${syxx.cicd10}" onclick="showICDdialog('cicd10','ccause');"/></td>
							<td class="rightTextAlign">发病至死亡间隔</td>
							<td><input type="text" name="cscopetime" id="cscopetime" style="width:60px;" value="${syxx.cscopetime}" onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="this.value=this.value.replace(/\D/g,'')"/></td>
							<td class="rightTextAlign">单位</td>
							<td>
								<select style="width:60px;" id="cscopetimeunit" name="cscopetimeunit" class="easyui-combobox">
									<option value="小时" <c:if test="${syxx.cscopetimeunit eq '小时'}">selected="selected"</c:if> >小时</option>
									<option value="天" <c:if test="${syxx.cscopetimeunit eq '天'}">selected="selected"</c:if>>天</option>
									<option value="月" <c:if test="${syxx.cscopetimeunit eq '月'}">selected="selected"</c:if>>月</option>
									<option value="年" <c:if test="${syxx.cscopetimeunit eq '年'}">selected="selected"</c:if>>年</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">(d)引起(c)的疾病或情况：</td>
							<td><input type="text" name="dcause" id="dcause" style="width:200px;" value="${syxx.dcause}"/></td>
							<td class="rightTextAlign">ICD10</td>
							<td><input type="text" name="dicd10" id="dicd10" value="${syxx.dicd10 }" onclick="showICDdialog('dicd10','dcause');"/></td>
							<td class="rightTextAlign">发病至死亡间隔</td>
							<td><input type="text" name="dscopetime" id="dscopetime" style="width:60px;" value="${syxx.dscopetime }" onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="this.value=this.value.replace(/\D/g,'')"/></td>
							<td class="rightTextAlign">单位</td>
							<td>
								<select style="width:60px;" id="dscopetimeunit" name="dscopetimeunit" class="easyui-combobox">
									<option value="小时" <c:if test="${syxx.dscopetimeunit eq '小时'}">selected="selected"</c:if> >小时</option>
									<option value="天" <c:if test="${syxx.dscopetimeunit eq '天'}">selected="selected"</c:if>>天</option>
									<option value="月" <c:if test="${syxx.dscopetimeunit eq '月'}">selected="selected"</c:if>>月</option>
									<option value="年" <c:if test="${syxx.dscopetimeunit eq '年'}">selected="selected"</c:if>>年</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">其它疾病诊断：</td>
							<td><input type="text" name="otherCause" id="otherCause" style="width:200px;" value="${syxx.otherCause}"/></td>
							<td class="rightTextAlign">ICD10</td>
							<td><input type="text" name="otherIcd10" id="otherIcd10" value="${syxx.otherIcd10}" onclick="showICDdialog('otherIcd10','otherCause');"/></td>
							<td></td><td></td><td></td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">死亡调查记录</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign" style="width: 200px;">死者生前病史及症状体症：</td>
							<td colspan="5"><input type="text" name="livingHistory" id="livingHistory" style="width:90%;" value="${syxx.livingHistory}"/></td>
						</tr>
						<tr>
							<td class="rightTextAlign">调查者姓名：</td>
							<td><input type="text" name="surveyName" id="surveyName" value="${syxx.surveyName}"/></td>
							<td class="rightTextAlign">与死者关系：</td>
							<td><input type="text" name="relationDead" id="relationDead" value="${syxx.relationDead}"/></td>
							<td class="rightTextAlign">联系电话：</td>
							<td><input type="text" name="contactphone" id="contactphone" value="${syxx.contactphone}"/></td>
						</tr>
						<tr>
							<td class="rightTextAlign">联系地址或工作单位：</td>
							<td colspan="5"><input type="text" name="contactaddr" id="contactaddr" style="width:90%;" value="${syxx.contactaddr}"/></td>
						</tr>
						<tr>
							<td class="rightTextAlign">死因推断：</td>
							<td><input type="text" name="verbalAutopsy" id="verbalAutopsy" value="${syxx.verbalAutopsy}"/></td>
							<td class="rightTextAlign">调查日期：</td>
							<td><input style="width:130px;" type="text" name="dsurvey" id="dsurvey" class="Wdate text" value='<fmt:formatDate value="${syxx.dsurvey}" type="both"/>' onclick='WdatePicker({dateFmt:"yyyy-MM-dd",maxDate:"<fmt:formatDate value="${now}"/>"})'/></td>
							<td></td><td></td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">根本死因</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign" style="width:200px;"><span class="red">*</span>最高诊断单位：</td>
							<td style="width:205px;">
								<select id="dorglevelid" name="dorglevelid" style="width:212px;" class="easyui-combobox" data-options="required:true,onSelect:function(r){$('#dorglevelvalue').val($('#dorglevelid').combobox('getText'))}">
									<option></option>
									<c:forEach items="${ZGZDDW}" var="mhdd">
										<option value="${mhdd.dictCode}" <c:if test="${syxx.dorglevelid==mhdd.dictCode}">selected="selected"</c:if> >${mhdd.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="dorglevelvalue" name="dorglevelvalue" value="${syxx.dorglevelvalue}"/>
							</td>
							<td class="rightTextAlign" style="width:150px;"><span class="red">*</span>最高诊断依据：</td>
							<td style="width:142px;">
								<select id="diagnoseby" name="diagnoseby" class="easyui-combobox" data-options="required:true,onSelect:function(r){$('#diagnosebyvalue').val($('#diagnoseby').combobox('getText'))}">
									<option></option>
									<c:forEach items="${ZGZDYJ}" var="mhdg">
										<option value="${mhdg.dictCode}" <c:if test="${syxx.diagnoseby==mhdg.dictCode}">selected="selected"</c:if> >${mhdg.dictName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="diagnosebyvalue" name="diagnosebyvalue" value="${syxx.diagnosebyvalue}"/>
							</td>
							<td></td><td></td>
						</tr>
						<tr>
							<td colspan="6"><div id="cannotfillBD" style="width:89%;height:33px;position: absolute;display: none;"></div></td>
						</tr>
						<tr>
							<td class="rightTextAlign">根本死因：</td>
							<td><input type="text" name="basecause" id="basecause" style="width:200px;" value="${syxx.basecause}" /></td>
							<td class="rightTextAlign">ICD10：</td>
							<td><input type="text" name="bsicd10" id="bsicd10" value="${syxx.bsicd10}"  onclick="showICDdialog('bsicd10','basecause');"/></td>
							<td colspan="2"><span id="bsnote" style="color:red;display: none;">(临床医生不能填写根本死因)</span></td>
						</tr>
						<tr>
							<td class="rightTextAlign">备注：</td>
							<td colspan="5"><input type="text" name="notes" id="notes" style="width:90%;" value="${syxx.notes}"/></td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">填报机构信息</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>填报科室：</td>
							<td>
								<input class="easyui-combobox" name="reportdeptid" id="reportdeptid" style="width: 142px;" />
								<input type="hidden" name="reportdeptname" id="reportdeptname" value="${syxx.reportdeptname}"/>
							</td>
							<td class="rightTextAlign"><span class="red">*</span>填报医生：</td>
							<td>
								<input class="easyui-combobox" name="reportdoctorid" id="reportdoctorid" style="width: 142px;" />
								<input type="hidden" name="reportdoctorname" id="reportdoctorname" value="${syxx.reportdoctorname}"/>
							</td>
							<td class="fillDateTime rightTextAlign" style="display: none;" ><span class="red">*</span>填报时间：</td>
							<td class="fillDateTime" style="display: none;">
								<input type="text" name="filldate" id="filldate" class="Wdate text" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" type="both"/>'})" 
								<c:choose>
									<c:when test="${!empty syxx}">
										value='<fmt:formatDate value="${syxx.filldate}" type="both" />'
									</c:when>
									<c:otherwise>
										value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" />'
									</c:otherwise>
								</c:choose>	
									 />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="footer">
			<%@ include file="/WEB-INF/view/cards/includepages/bkOpts.jsp"%>
		</div>
	</form>
	<div id="chooseICD" >
		<div id="distool">
			<input id="icdKey" type="text" style="width: 100%;height:28px;" class="easyui-textbox" data-options="prompt:'输入ICD编号/ICD名称/助记码检索'" />
		</div>
		<table id="icdTable" style="height:400px;"></table>
	</div>
	<script type="text/javascript">
	$(function(){
		<c:if test="${acType eq 'hospital'}">
			$(".fillDateTime").show();
			<c:if test="${!empty syxx.flag && syxx.flag!=0}">
				$("#NoEdit").css("height",$("#sybk").height());
			</c:if>
			<c:if test="${ isGDSN eq '1' }">
				$("#serialnumber").attr("readonly","readonly");
				$(".SNPlace").show();
			</c:if>
			<c:if test="${ isGDSN eq '2' }">
				$(".SNPlace").show();
			</c:if>
		</c:if>
		<c:if test="${acType eq 'doctor'}">
			<c:if test="${!empty syxx && syxx.flag!=2}">
				$("#NoEdit").css("height",$("#sybk").height());
			</c:if>
			<c:if test="${canfillBD == 0}">
				$("#cannotfillBD").show();
				/* $("#basecauseast").hide();
				$("#bsicd10ast").hide(); */
				$("#bsnote").show();
				//移除必填限制
				/* $("#basecause").validatebox('remove');  
				$("#bsicd10").validatebox('remove');   */
			</c:if>
		</c:if>
		unLockWomanStatus();
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
					if(""=="${syxx.reportdeptid}"){
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
						$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${syxx.reportdeptid}");
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
			<c:when test="${!empty syxx}">
				value:"${syxx.reportdoctorid}",
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
					if(!"${syxx.reportdoctorid}"){
						<c:choose>
							<c:when test="${acType eq 'hospital'}">
								$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${user.username}");
							</c:when>
							<c:otherwise>
								$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${user.docNo}");
							</c:otherwise>
						</c:choose>
						//$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${user.docNo}");
					}else{
						$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${syxx.reportdoctorid}");
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
		
		$("input",$("#icdKey").next("span")).keyup(function(){
	    	timedCount();
	    });
		
	 	//更新地址信息
		regAddress();
		
		//更新地址信息
		nowAddress();
	   
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
		
	   //更新地址
	   <c:choose>
		   	<c:when test="${!empty syxx.registrationaddr}">
			   setTimeout("$('#registrationaddr').val('${syxx.registrationaddr }')",1000);
			   setTimeout("$('#nowaddr').val('${syxx.nowaddr }')",1000);
		   	</c:when>
		   	<c:when test="${patientType eq 'zy'}">
		   		<c:if test="${!empty BRXX.address}">
		   			setTimeout("$('#nowaddr').val('${BRXX.address }')",1000);
		   		</c:if>
		   	</c:when>
		   	<c:when test="${patientType eq 'mz'}">
			   	<c:if test="${!empty BRXX.registeraddr}">
			   		setTimeout("$('#registrationaddr').val('${BRXX.registeraddr }')",1000);
				    setTimeout("$('#nowaddr').val('${BRXX.presentaddr }')",1200);
		   		</c:if>
		   	</c:when>
	   </c:choose>
	   
	   <c:if test="${empty syxx}">
	   		getInfoByID();
		</c:if>
		
		//数据初始化
		$('#sexname').val($(':radio[name=sexid]:checked').attr('textvalue'));
		$('#womantypename').val($('#womantypeid').combobox('getText'));
	   	$("#idtypevalue").val($("#idtype").combobox('getText'));
	   	$('#nationname').val($('#nationid').combobox('getText'));
	   	$('#professionname').val($('#professionid').combobox('getText'));
	   	$('#educationname').val($('#educationid').combobox('getText'));
	   	$('#marriagevalue').val($('#marriageid').combobox('getText'));
	   	$('#deadzonevalue').val($('#deadzoneid').combobox('getText'));
	   	$('#regaddrtypename').val($('#regaddrtypeid').combobox('getText'));
	   	$('#nowaddrtypename').val($('#nowaddrtypeid').combobox('getText'));
	   	$('#dorglevelvalue').val($('#dorglevelid').combobox('getText'));
	   	$('#diagnosebyvalue').val($('#diagnoseby').combobox('getText'));
	   	
	   	//$("#sexname").val($("#sexid option:selected").text());
	   //是否被编辑
	   var edited = 0;
	   
	});
	function unLockWomanStatus(){
		//性别
		var sexid = $(":radio[name='sexid']:checked").val();
		if(sexid=="2"){
			$("#womantypeid").removeAttr("disabled");
		}else{
			$("#womantypeid").combobox({value:0,disabled:true});
			$("#womantypename").val("");
		}
	}
	function showICDdialog(id,name){
		$('#icdTable').datagrid({
			url:"${webroot}/cdc/f_json/chooseICD.shtml",
			fitColumns:true,
			height:410,
			singleSelect:true,
			columns:[[
				{field:'icdCode',width:80,title:'ICD编号'},
				{field:'icdName',width:150,title:'ICD名称'},
				{field:'spCode',width:80,title:'首拼码'},
				{field:'wbCode',width:80,title:'五笔码'}
			]],
			onLoadSuccess: function (data) {},
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
	     		'searchString': (tv=="输入ICD编号/ICD名称/助记码检索"?"":tv)
	        },
	        method:"post",
	        onLoadSuccess: function (data) {}
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
	function getInfoByID(){
		var idtype = $("#idtype").combobox("getValue");
		var idcard = $("#id").val();
		if(idtype=='01' && idcard.length==18){
			//前17位是否是纯数字
			if(isNaN(idcard.substring(0,17))){
				$.messager.show({ title: '提示', msg: '提取信息失败：身份证号码不合法。' });
				return false;
			}
			//性别   奇数为男，偶数为女
			if(idcard.substring(16,17)%2==0){
				$("#nv").attr("checked","checked").trigger("click");
			}else{
				$("#nan").attr("checked","checked").trigger("click");
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
	 	//国标编码
		$("#nowaddrcode").val($("#xiang").combobox("getValue"));
		//国标地址
		$("#nowaddrcodevalue").val($("#sheng").combobox("getText")+$("#shi").combobox("getText")+$("#xian").combobox("getText")+$("#xiang").combobox("getText"));
		//详细地址
		$("#nowaddr").val($("#sheng").combobox("getText")+$("#shi").combobox("getText")+$("#xian").combobox("getText")+$("#xiang").combobox("getText"));
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
		var val=$("#nowaddrtypeid").combobox("getValue");
		$("#nowaddrtypename").val($("#nowaddrtypeid").combobox("getText"));
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
	 	//国标编码
		$("#registrationaddrcode").val($("#regXiang").combobox("getValue"));
		//国标地址
		$("#registrationaddrcodevalue").val($("#regSheng").combobox("getText")+$("#regShi").combobox("getText")+$("#regXian").combobox("getText")+$("#regXiang").combobox("getText"));
		//详细地址
		$("#registrationaddr").val($("#regSheng").combobox("getText")+$("#regShi").combobox("getText")+$("#regXian").combobox("getText")+$("#regXiang").combobox("getText"));
		$("#regSheng").validatebox();
		$("#regShi").validatebox();
		$("#regXian").validatebox();
		$("#regXiang").validatebox();
		$("#registrationaddr").validatebox();
	}
	function regAddress(){
		$("#regSheng").combobox({value:"",disabled:false});
		$("#regShi").combobox({value:"",disabled:false});
		$("#regXian").combobox({value:"",disabled:false});
		$("#regXiang").combobox({value:"",disabled:false});
		var val=$("#regaddrtypeid").combobox("getValue");
		$("#regaddrtypename").val($("#regaddrtypeid").combobox("getText"));
		if(val==1){
			$("#regSheng").combobox({value:"",disabled:true});
			$("#regShi").combobox({value:"",disabled:true});
			$("#regXian").combobox({value:"",disabled:true});
		}
		if(val==2){
			$("#regSheng").combobox({value:"",disabled:true});
			$("#regShi").combobox({value:"",disabled:true});
		}
		if(val==3){
			$("#regSheng").combobox({value:"",disabled:true});
		}
		if(val==4){
		}
		if(val>=5){
			$("#regSheng").combobox({value:"",disabled:true});
			$("#regShi").combobox({value:"",disabled:true});
			$("#regXian").combobox({value:"",disabled:true});
			$("#regXiang").combobox({value:"",disabled:true});
		}
		get_reg_sheng();
		/* $("#regSheng").validatebox();
		$("#regShi").validatebox();
		$("#regXian").validatebox();
		$("#regXiang").validatebox(); */
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
			if($("#ageunit").combobox('getValue')=='岁' && $("#age").val()>120){
				$.messager.show({ title: '提示', msg: '年龄最大不能超过120岁。' });
				return false;
			}
		}
		/* var s= $(":radio[name='sexid']:checked").val();
		var w= $("#womantypeid option:selected").val();
		if(s==2 && !w){
			$.messager.show({ title: '提示', msg: '当性别为女时，请填写女性情况。' });
			return false;
		} */
		
		<c:if test="${isFIN==1}">
		if(!$("#folkname").val()){
			$.messager.show({ title: '提示', msg: '请填写家属姓名。' });
			return false;
		}
		if(!$("#folkname").val()){
			$.messager.show({ title: '提示', msg: '请填写家属联系电话。' });
			return false;
		}	
		if(!$("#folkname").val()){
			$.messager.show({ title: '提示', msg: '请填写家属联系地址。' });
			return false;
		}
		</c:if>
		
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
		var result = $("#sybk").form('validate'); 
		if(result){
			if(checkForm()){
				$.messager.confirm('提示', "确认保存？", function (r) {
					if(r){
						$.ajax({
							url:"${webroot}/cdc/f_json/saveSYCard.shtml",
							data:$("#sybk").serialize(),
							type:"POST",
							success:function(data){
								data = eval("("+data+")");
								if(data.result=='success'){
									$.messager.show({ title: '提示', msg: '保存成功！' });
									<c:if test="${acType eq 'hospital'}">
									back();
									</c:if>
									<c:if test="${acType eq 'doctor'}">
									back(data.extraValue);
									</c:if>
								}else{
									/* if(!data.extraValue){
										$.messager.show({ title: '提示', msg: '您的身份信息过期请刷新后重试！' });
									}else{ */
										$.messager.show({ title: '提示', msg: '保存失败！错误信息：'+data.extraValue });
									/* } */
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
			var st = parent.menuInfo.getCurSelectTabTitle();
			if(parent.frames[0].document.all.query){
				$(parent.frames[0].document.all.query).trigger('click');
			}
			if(r){
				parent.menuInfo.closeCurSelectTab();
			}else if(i){
				var PO = parent.menuInfo.getCurSelectTabPanelOpts();
				var purl = $(PO.content).attr("src");
				//去除自带的webroot名
				var c = purl.replace("/","").indexOf("/");
				purl = purl.substring(c+1);
				var url = purl+"&masterid="+i;
				parent.menuInfo.clickMenu("死因上报记录查看",url,true);
				parent.menuInfo.closeMenu(st);
				//$("#NoEdit").css("height",$("#sybk").height());
				//$("#upload-main").hide();
			}else{
				//刷新当前tab页
				parent.menuInfo.refreshMenu(parent.menuInfo.getCurSelectTabTitle());
			}
		});
	}
	
	function audit(bktype,msid){
		//触发保存，验证表单数据是否符合规范
		var result = $("#sybk").form('validate'); 
		if(result){
			if(checkForm()){
				$.messager.confirm("提示", "确认审核该报卡？", function (r) {
					if(r){
						$.ajax({
								url:"${webroot}/cdc/f_json/saveSYCard.shtml",
								data:$("#sybk").serialize(),
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