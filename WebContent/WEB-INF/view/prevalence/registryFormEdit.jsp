<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>现患率个案登记表</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/prevalence/registryForm.js"></script>
</head>
<body style="position:relevter;">
	<div class="prevalence mb60">
	<div id="prevalence_edit">
		<form id="id_form" method="post">
		<input type="hidden" name="brid" value="${xl001Brxx.brid}" />
		<input type="hidden" id="id_visitId" name="visitId"/>
		<input type="hidden" name="unitInfectMap" id="id_unit_infect_map" />
		<input type="hidden" name="commInfectMap" id="id_comm_infect_map" />
		<c:if test="${isAdd eq 1}">
			<div>
				<div class="div_row" style="padding-top: 10px;">
					<span style="width: 130px;text-align: right;font-size: 14px;"><b>新增个案：</b></span>
					<span style="width: 180px;text-align: right;font-size: 14px;">${patientZyTitle}/${patientNoTitle}/姓名：</span>
					<span><input type="text" id="id_sel_patient" style="width: 130px;" /></span>
				</div>
			</div>
		</c:if>
		<div class="body_title">医院感染横断面调查个案登记表（状态：<c:out value="${xl001Brxx.stateName}"></c:out>）</div>
		<div class="title">一、一般情况：<a href="javascript:void(0);" class="text-icon icon-help" onclick="registryForm.setHelpContent(1);"></a></div>
		<div>
			<table class="info_table">
				<tr>
					<th>${patientZyTitle}</th><td><input type="text" id="id_zyid" style="width: 110px;" name="zyid" value="${xl001Brxx.zyid}" required="true" class="easyui-validatebox" /></td>
					<th>科室</th>
					<td>
						<input type="hidden" id="id_deptName" name="deptName" value="${xl001Brxx.deptName}" />
						<input type="text" id="id_dept" style="width: 130px;" class="easyui-validatebox" name="deptId" />
					</td>
					<th>床号</th><td><input type="text" id="id_bedNo" style="width: 60px;" name="bedNo" value="${xl001Brxx.bedNo}" /></td>
					<th>${patientNoTitle}</th><td><input type="text" id="id_patientId" style="width: 110px;" name="patientId" value="${xl001Brxx.patientId}" /></td>
				</tr>
				<tr>
					<th>姓名</th><td><input type="text" id="id_patientName" style="width: 110px;" name="patientName" value="${xl001Brxx.patientName}" required="true" class="easyui-validatebox" /></td>
					<th>性别</th>
					<td>
						<nis:radio name="sex" dictcode="sex" value="${xl001Brxx.sex}" defvalue="男"/>
						<%-- <input id="id_man" type="radio" value="男" name="sex" ${(xl001Brxx.sex eq '男' || empty xl001Brxx.sex) ? 'checked="checked"' : ''} /><label for="id_man">&nbsp;男</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_woman" type="radio" value="女" name="sex" ${xl001Brxx.sex eq '女' ? 'checked="checked"' : ''} /><label for="id_woman">&nbsp;女</label> --%>
					</td>
					<th>年龄</th>
					<td colspan="3">
						<input type="text" id="id_age" style="width: 60px;" name="age" value="${xl001Brxx.age}" />
						<span>（&nbsp;<input id="id_annum" type="radio" value="岁" name="ageUnit" ${(xl001Brxx.ageUnit eq '岁' || empty xl001Brxx.ageUnit) ? 'checked="checked"' : ''} /><label for="id_annum">&nbsp;岁</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="id_month" type="radio" value="月" name="ageUnit" ${xl001Brxx.ageUnit eq '月' ? 'checked="checked"' : ''} /><label for="id_month">&nbsp;月</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="id_day" type="radio" value="天" name="ageUnit" ${xl001Brxx.ageUnit eq '天' ? 'checked="checked"' : ''} /><label for="id_day">&nbsp;天</label>&nbsp;）
						</span>
					</td>
				</tr>
				<tr>
					<th>诊断</th>
					<td colspan="7">
						<input type="hidden" id="id_diagnose" name="diagnose" value="${xl001Brxx.diagnose}" />
						<input type="text" id="id_diagnoseId" style="width: 320px;" name="diagnoseId" class="easyui-validatebox" />
					</td>
				</tr>
				<tr>
					<th>手术</th>
					<td>
						<input id="id_isOper_yes" type="radio" value="1" name="isOper" ${xl001Brxx.isOper eq 1 ? 'checked="checked"' : ''} onchange="registryForm.setGradeCanChoose();" /><label for="id_isOper_yes">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_isOper_no" type="radio" value="0" name="isOper" ${(xl001Brxx.isOper eq 0 || empty xl001Brxx.isOper) ? 'checked="checked"' : ''}  onchange="registryForm.setGradeCanChoose();" /><label for="id_isOper_no">&nbsp;否</label>
					</td>
					<%-- <th>切口类型</th>
					<td colspan="5">
						<input id="id_grade_0" type="radio" value="0" name="isGrade" ${xl001Brxx.isGrade1 eq 1 ? 'checked="checked"' : ''} /><label for="id_grade_0">&nbsp;I类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_grade_1" type="radio" value="1" name="isGrade" ${xl001Brxx.isGrade2 eq 1 ? 'checked="checked"' : ''} /><label for="id_grade_1">&nbsp;II类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_grade_2" type="radio" value="2" name="isGrade" ${xl001Brxx.isGrade3 eq 1 ? 'checked="checked"' : ''} /><label for="id_grade_2">&nbsp;III类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_grade_3" type="radio" value="3" name="isGrade" ${xl001Brxx.isGrade4 eq 1 ? 'checked="checked"' : ''} /><label for="id_grade_3">&nbsp;Ⅳ类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						 
						<c:if test="${xhlVersion=='gx' }">
						<input id="id_grade_4" type="radio" value="4" name="isGrade" /><label for="id_grade_4">&nbsp;无切口</label>
						</c:if>
					</td> --%>
				</tr>
				<tr>
					<th>手术一</th>
					<td colspan="3">
						<input type="text" id="id_operName1" name="operName1" value="${xl001Brxx.operName1}" style="width: 220px;" maxlength="32"/>
					</td>
					<th>切口类型一</th>
					<td colspan="3" class="sx_qk">
						<input id="id_grade_0" type="radio" value="0" name="isGrade" ${xl001Brxx.isGrade1 eq 1 ? 'checked="checked"' : ''} /><label for="id_grade_0">&nbsp;I类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_grade_1" type="radio" value="1" name="isGrade" ${xl001Brxx.isGrade2 eq 1 ? 'checked="checked"' : ''} /><label for="id_grade_1">&nbsp;II类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_grade_2" type="radio" value="2" name="isGrade" ${xl001Brxx.isGrade3 eq 1 ? 'checked="checked"' : ''} /><label for="id_grade_2">&nbsp;III类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_grade_3" type="radio" value="3" name="isGrade" ${xl001Brxx.isGrade4 eq 1 ? 'checked="checked"' : ''} /><label for="id_grade_3">&nbsp;Ⅳ类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${xhlVersion=='gx' }">
						<input id="id_grade_4" type="radio" value="4" name="isGrade" /><label for="id_grade_4">&nbsp;无切口</label>
						</c:if>
						<a href="javascript:registryForm.clearRadio('isGrade');" class="ico_del" title="清空选项"></a>
					</td>
				</tr>
				<tr>
					<th>手术二</th>
					<td colspan="3">
						<input type="text" id="id_operName2" name="operName2" value="${xl001Brxx.operName2}" style="width: 220px;" maxlength="32"/>
					</td>
					<th>切口类型二</th>
					<td colspan="3" class="sx_qk">
						<label><input type="radio" value="1" name="gradeType2" ${xl001Brxx.gradeType2 eq 1 ? 'checked="checked"' : ''} />&nbsp;I类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<label><input type="radio" value="2" name="gradeType2" ${xl001Brxx.gradeType2 eq 2 ? 'checked="checked"' : ''} />&nbsp;II类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<label><input type="radio" value="3" name="gradeType2" ${xl001Brxx.gradeType2 eq 3 ? 'checked="checked"' : ''} />&nbsp;III类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<label><input type="radio" value="4" name="gradeType2" ${xl001Brxx.gradeType2 eq 4 ? 'checked="checked"' : ''} />&nbsp;Ⅳ类</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:registryForm.clearRadio('gradeType2');" class="ico_del" title="清空选项"></a>
					</td>
				</tr>
			</table>
		</div>
		<div class="title">二、感染情况（包括医院感染与社区感染）：<a href="javascript:void(0);" class="text-icon icon-help" onclick="registryForm.setHelpContent(2);"></a></div>
		<div>
			<table class="info_table">
				<tr>
					<th>感染</th>
					<td width="160">
						<input id="id_inf_exist" type="radio" value="1" name="isInfect" ${xl001Brxx.isInfect eq 1 ? 'checked="checked"' : ''} onchange="registryForm.disUnitAndCommInfect();" /><label for="id_inf_exist">&nbsp;存在</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_inf_unExist" type="radio" value="0" name="isInfect" ${(xl001Brxx.isInfect eq 0 || empty xl001Brxx.isInfect) ? 'checked="checked"' : ''} onchange="registryForm.disUnitAndCommInfect();" /><label for="id_inf_unExist">&nbsp;不存在</label>
					</td>
					<th width="80">感染分类</th>
					<td>
						<input id="id_unitInfe" type="checkbox" value="1" name="isCa" ${xl001Brxx.isCa eq 1 ? 'checked="checked"' : ''} onchange="registryForm.disUnitAndCommInfect();" /><label for="id_unitInfe">&nbsp;医院感染</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_commInfe" type="checkbox" value="1" name="isHa" ${xl001Brxx.isHa eq 1 ? 'checked="checked"' : ''} onchange="registryForm.disUnitAndCommInfect();" /><label for="id_commInfe">&nbsp;社区感染</label>
					</td>
				</tr>
			</table>
			<div id="unit_infect" ${xl001Brxx.isCa eq 1 ? '' : 'style="display: none;"'}>
				<div class="font_line_through"><span>医院感染</span></div>
				<div id="id_unit_infect">
					<c:forEach items="${untiXl2List}" var="xl002" varStatus="status">
						<fieldset>
							<div class="div_row unit_part" style="margin: 0px 5px;" num="${status.index}">
								<span class="row_title"><b>医院感染部位</b></span><span style="width: 220px;">
									<input type="hidden" name="xl2ListU[${status.index}].grid" value="${xl002.grid}" />
									<input type="hidden" name="xl2ListU[${status.index}].infectName" id="id_unit_part_name_${status.index}" value="${xl002.infectName}" />
									<input type="text" name="xl2ListU[${status.index}].infectCode" style="width: 170px;" id="id_unit_part_${status.index}" class="require"/>
								</span><span><a class="ico_no" href="javascript:void(0)" onclick="registryForm.delInfect(this);" title="删除"><i class="icon iconfont fax">&#xe62b;</i></a></span>
								<span><a class="ico_no" href="javascript:void(0)" onclick="registryForm.addPathogens(this, 'unit');"  title="添加病原体"><i class="icon iconfont">&#xe602;</i></a></span>
							</div>							
							<c:forEach items="${xl002.xl003List}" var="xl003" varStatus="status3">
								<div class="unit_pathogens" pnum="${status.index}" num="${status3.index}" >
									<div class="div_row" style="margin: 0px 5px" onmouseover="registryForm.showDelDrug(this);" onmouseout= "registryForm.hideDelDrug(this);" >
										<span class="row_title"><b>病原体</b></span><span style="width: 220px;">
											<input type="hidden" name="xl2ListU[${status.index}].xl003List[${status3.index}].bytid" value="${xl003.bytid}" />
											<input type="hidden" name="xl2ListU[${status.index}].xl003List[${status3.index}].infectPathoName" id="id_unit_pathogens_name_${status.index}_${status3.index}" value="${xl003.infectPathoName}" />
											<input type="text" name="xl2ListU[${status.index}].xl003List[${status3.index}].infectPathoId" style="width: 170px;" id="id_unit_pathogens_${status.index}_${status3.index}"/>
										</span><span>
											<select style="width: 83px;" name="xl2ListU[${status.index}].xl003List[${status3.index}].pathoresult" class="easyui-combobox " data-options="editable:false">
												<option value=""></option>
												<option value="敏感" ${xl003.pathoresult eq '敏感' ? 'selected="selected"' : ''} >敏感</option>
												<option value="MDR" ${xl003.pathoresult eq 'MDR' ? 'selected="selected"' : ''} >MDR</option>
												<option value="XDR" ${xl003.pathoresult eq 'XDR' ? 'selected="selected"' : ''} >XDR</option>
												<option value="PDR" ${xl003.pathoresult eq 'PDR' ? 'selected="selected"' : ''} >PDR</option>
											</select>
										</span>
										<span><select id="id_unit_sample_${status.index}_${status3.index}" style="width:90px;" name="xl2ListU[${status.index}].xl003List[${status3.index}].sample"></select></span>
										<span class="row_but" style="vertical-align:middle;display: none;"><a href="javascript:void(0)" style="margin: 0px;" class="ico_no" title="删除" onclick="registryForm.delPathogens(this);" ><i class="icon iconfont fax">&#xe62b;</i></a></span>
									</div>
									<c:if test="${fn:length(xl003.xl004List) > 0}">
									<div class="drug_results">
									</c:if>
									<c:forEach items="${xl003.xl004List}" var="xl004" varStatus="status4">
										<c:if test="${status4.index % 2 == 0}">
											<div class="div_row" style="margin: 0px 5px"><span class="row_title">
											<c:if test="${status4.index == 0}">
											<b>药敏结果</b>
											</c:if>
											</span>
										</c:if>
											<span style="width: 110px; text-align: right;"><c:out value="${xl004.drugName}" /></span><span style="width: 90px;">
												<input type="hidden" name="xl2ListU[${status.index}].xl003List[${status3.index}].xl004List[${status4.index}].yjywid" value="${xl004.yjywid}"/>
												<input type="hidden" name="xl2ListU[${status.index}].xl003List[${status3.index}].xl004List[${status4.index}].drugId" value="${xl004.drugId}" />
												<input type="hidden" name="xl2ListU[${status.index}].xl003List[${status3.index}].xl004List[${status4.index}].drugName" value="${xl004.drugName}" />
												<select name="xl2ListU[${status.index}].xl003List[${status3.index}].xl004List[${status4.index}].status" style="width: 83px;" required="true" class="easyui-combobox require" data-options="editable:false">
													<option value=""></option>
													<option value="未做" ${xl004.status eq '未做' ? 'selected="selected"' : ''} >未做</option>
													<option value="耐药" ${xl004.status eq '耐药' ? 'selected="selected"' : ''} >耐药</option>
													<option value="敏感" ${xl004.status eq '敏感' ? 'selected="selected"' : ''} >敏感</option>
													<option value="中介" ${xl004.status eq '中介' ? 'selected="selected"' : ''} >中介</option>
												</select>
											</span>
										<c:if test="${(status4.index % 2 != 0) || status4.index == fn:length(xl003.xl004List)}">
											</div>
										</c:if>
									</c:forEach>
									<c:if test="${fn:length(xl003.xl004List) > 0}">
									</div>
									</c:if>
								</div>
							</c:forEach>
						</fieldset>
					</c:forEach>
				</div>				
				<div class="sf_add"><div class="sf_add_jt"></div><a href="javascript:registryForm.addUnitInfect();">添加医院感染部位</a></div>
			</div>
			<div id="comm_infect" ${xl001Brxx.isHa eq 1 ? '' : 'style="display: none;"'}>
				<div class="font_line_through"><span>社区感染</span></div>
				<div id="id_comm_infect">
					<c:forEach items="${commXl2List}" var="xl002" varStatus="status">
						<fieldset>
							<div class="div_row comm_part" style="margin: 0px 5px;" num="${status.index}">
								<span class="row_title"><b>社区感染部位</b></span><span style="width: 220px;">
									<input type="hidden" name="xl2ListC[${status.index}].grid" value="${xl002.grid}" />
									<input type="hidden" name="xl2ListC[${status.index}].infectName" id="id_comm_part_name_${status.index}" value="${xl002.infectName}" />
									<input type="text" name="xl2ListC[${status.index}].infectCode" style="width: 170px;" id="id_comm_part_${status.index}" class="require"/>
								</span><span><a class="ico_no" href="javascript:void(0)" onclick="registryForm.delInfect(this);" title="删除"><i class="icon iconfont fax">&#xe62b;</i></a></span>
								<span><a class="ico_no" href="javascript:void(0)" onclick="registryForm.addPathogens(this, 'comm');" title="添加病原体"><i class="icon iconfont">&#xe602;</i></a></span>
							</div>							
							<c:forEach items="${xl002.xl003List}" var="xl003" varStatus="status3">
								<div class="comm_pathogens" pnum="${status.index}" num="${status3.index}" >
									<div class="div_row" style="margin: 0px 5px" onmouseover="registryForm.showDelDrug(this);" onmouseout= "registryForm.hideDelDrug(this);">
										<span class="row_title"><b>病原体</b></span><span style="width: 220px;">
											<input type="hidden" name="xl2ListC[${status.index}].xl003List[${status3.index}].bytid" value="${xl003.bytid}" />
											<input type="hidden" name="xl2ListC[${status.index}].xl003List[${status3.index}].infectPathoName" id="id_comm_pathogens_name_${status.index}_${status3.index}" value="${xl003.infectPathoName}" />
											<input type="text" name="xl2ListC[${status.index}].xl003List[${status3.index}].infectPathoId" style="width: 170px;" id="id_comm_pathogens_${status.index}_${status3.index}" />
										</span><span>
											<select style="width: 83px;" name="xl2ListC[${status.index}].xl003List[${status3.index}].pathoresult" class="easyui-combobox " data-options="editable:false">
												<option value=""></option>
												<option value="敏感" ${xl003.pathoresult eq '敏感' ? 'selected="selected"' : ''} >敏感</option>
												<option value="MDR" ${xl003.pathoresult eq 'MDR' ? 'selected="selected"' : ''} >MDR</option>
												<option value="XDR" ${xl003.pathoresult eq 'XDR' ? 'selected="selected"' : ''} >XDR</option>
												<option value="PDR" ${xl003.pathoresult eq 'PDR' ? 'selected="selected"' : ''} >PDR</option>
											</select>
										</span>
										<span><select style="width:90px;"  id="id_comm_sample_${status.index}_${status3.index}" name="xl2ListC[${status.index}].xl003List[${status3.index}].sample"></select></span>
										<span class="row_but" style="vertical-align:middle;display: none;"><a href="javascript:void(0)" style="margin: 0px;" class="ico_no" title="删除" onclick="registryForm.delPathogens(this);" ><i class="icon iconfont fax">&#xe62b;</i></a></span>
									</div>
									<c:if test="${fn:length(xl003.xl004List) > 0}">
									<div class="drug_results">
									</c:if>
									<c:forEach items="${xl003.xl004List}" var="xl004" varStatus="status4">
										<c:if test="${status4.index % 2 == 0}">
											<div class="div_row" style="margin: 0px 5px"><span class="row_title">
											<c:if test="${status4.index == 0}">
											<b>药敏结果</b>
											</c:if>
											</span>
										</c:if>
											<span style="width: 110px; text-align: right;"><c:out value="${xl004.drugName}" /></span><span style="width: 90px;">
												<input type="hidden" name="xl2ListC[${status.index}].xl003List[${status3.index}].xl004List[${status4.index}].yjywid" value="${xl004.yjywid}"/>
												<input type="hidden" name="xl2ListC[${status.index}].xl003List[${status3.index}].xl004List[${status4.index}].drugId" value="${xl004.drugId}" />
												<input type="hidden" name="xl2ListC[${status.index}].xl003List[${status3.index}].xl004List[${status4.index}].drugName" value="${xl004.drugName}" />
												<select name="xl2ListC[${status.index}].xl003List[${status3.index}].xl004List[${status4.index}].status" style="width: 83px;" required="true" class="easyui-combobox require" data-options="editable:false">
													<option value=""></option>
													<option value="未做" ${xl004.status eq '未做' ? 'selected="selected"' : ''} >未做</option>
													<option value="耐药" ${xl004.status eq '耐药' ? 'selected="selected"' : ''} >耐药</option>
													<option value="敏感" ${xl004.status eq '敏感' ? 'selected="selected"' : ''} >敏感</option>
													<option value="中介" ${xl004.status eq '中介' ? 'selected="selected"' : ''} >中介</option>
												</select>
											</span>
										<c:if test="${(status4.index % 2 != 0) || status4.index == fn:length(xl003.xl004List)}">
											</div>
										</c:if>
									</c:forEach>
									<c:if test="${fn:length(xl003.xl004List) > 0}">
									</div>
									</c:if>
								</div>
							</c:forEach>
						</fieldset>
					</c:forEach>
				</div>				
				<div class="sf_add"><div class="sf_add_jt"></div><a href="javascript:registryForm.addCommInfect();">添加社区感染部位</a></div>
			</div>
			<div class="div_row">
				<span><b>手术后肺炎</b></span>
				<span>
					<input id="id_exist" type="radio" value="1" name="pop" ${xl001Brxx.pop eq 1 ? 'checked="checked"' : ''} /><label for="id_exist">&nbsp;存在</label>&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="id_unExist" type="radio" value="0" name="pop" ${(xl001Brxx.pop eq 0 || empty xl001Brxx.pop) ? 'checked="checked"' : ''} /><label for="id_unExist">&nbsp;不存在</label>
				</span>
				<span>（仅指调查时间段内）</span>
			</div>
		</div>
		<br />
		
	<c:choose>
		<c:when test="${xhlVersion=='gx'}">
		<div class="title">三、危险因素(调查近一个月的情况)：<a href="javascript:void(0);" class="text-icon icon-help" onclick="registryForm.setHelpContent(5);"></a></div>
		<div>
			<table class="info_table">
				<tr>
					<th width="140">接受放射治疗</th>
					<td width="180">
						<input id="" type="radio" value="1" name="fszl" ${xl001Brxx.fszl eq 1 ? 'checked="checked"' : ''} /><label for="">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="" type="radio" value="0" name="fszl" ${( xl001Brxx.fszl eq 0  || empty xl001Brxx.fszl )? 'checked="checked"' : ''}/><label for="">&nbsp;否</label>
					</td>
					<th width="140">接受抗肿瘤化学治疗</th>
					<td width="180">
						<input id="" type="radio" value="1" name="hxzl" ${xl001Brxx.hxzl eq 1 ? 'checked="checked"' : ''} /><label for="">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="" type="radio" value="0" name="hxzl" ${( xl001Brxx.hxzl eq 0  || empty xl001Brxx.hxzl )? 'checked="checked"' : ''}/><label for="">&nbsp;否</label>
					</td>
				</tr>
			</table>
		</div>
		<br/>
		<div class="title">四、侵袭性操作相关情况(调查日及之前2天内实施情况)：</div>
		<div>
			<table class="info_table">
				<tr>
					<th width="140">动静脉插管(不包括周围浅静脉置管)</th>
					<td width="180">
						<input id="" type="radio" value="1" name="djmcg" ${xl001Brxx.djmcg eq 1 ? 'checked="checked"' : ''} /><label for="">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="" type="radio" value="0" name="djmcg" ${( xl001Brxx.djmcg eq 0  || empty xl001Brxx.djmcg )? 'checked="checked"' : ''}/><label for="">&nbsp;否</label>
					</td>
					<th width="140">泌尿道插管</th>
					<td width="180">
						<input id="" type="radio" value="1" name="mndcg" ${xl001Brxx.mndcg eq 1 ? 'checked="checked"' : ''}/><label for="">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="" type="radio" value="0" name="mndcg" ${( xl001Brxx.mndcg eq 0  || empty xl001Brxx.mndcg )? 'checked="checked"' : ''} /><label for="">&nbsp;否</label>
					</td>
				</tr>
				<tr>
					<th width="140">使用呼吸机(不包括术中)</th>
					<td colspan="3">
						<input id="id_syhxj_yes" type="radio" value="1" name="syhxj" ${xl001Brxx.syhxj eq 1 ? 'checked="checked"' : ''} onchange="registryForm.setQgCanChoose();"/><label for="">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_syhxj_no" type="radio" value="0" name="syhxj" ${( xl001Brxx.syhxj eq 0  || empty xl001Brxx.syhxj )? 'checked="checked"' : ''} onchange="registryForm.setQgCanChoose();"/><label for="">&nbsp;否</label>
					</td>
				</tr>
				<tr>
					<th width="140">气管插管</th>
					<td width="180">
						<input id="id_qgcg_yes" type="radio" value="1" name="qgcg" ${xl001Brxx.qgcg eq 1 ? 'checked="checked"' : ''} /><label for="">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_qgcg_no" type="radio" value="0" name="qgcg" ${( xl001Brxx.qgcg eq 0 )? 'checked="checked"' : ''} /><label for="">&nbsp;否</label>
					</td>
					<th width="140">气管切开</th>
					<td width="180">
						<input id="id_qgqk_yes" type="radio" value="1" name="qgqk" ${xl001Brxx.qgqk eq 1 ? 'checked="checked"' : ''}/><label for="">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_qgqk_no" type="radio" value="0" name="qgqk" ${( xl001Brxx.qgqk eq 0 )? 'checked="checked"' : ''}/><label for="">&nbsp;否</label>
					</td>
				</tr>
			</table>
		</div>
		<br/>
		</c:when>
		<c:when test="${xhlVersion=='sz'}">
			<div class="title">三、医院感染危险因素：<!-- <a href="javascript:void(0);" class="text-icon icon-help" onclick="registryForm.setHelpContent(5);"></a> --></div>
			<div>
				<table class="info_table">
					<tr>
						<th width="140">泌尿道插管</th>
						<td width="180">
							<label><input id="id_mndcg_yes" type="radio" value="1" name="mndcg" ${xl001Brxx.mndcg eq 1 ? 'checked="checked"' : ''} onchange="registryForm.setYwmndcgCanChoose();" />&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label><input id="id_mndcg_no" type="radio" value="0" name="mndcg" ${( xl001Brxx.mndcg eq 0)? 'checked="checked"' : ''} onchange="registryForm.setYwmndcgCanChoose();" />&nbsp;否</label>
						</td>
						<th width="140">泌尿道感染前48小时内有插管操作</th>
						<td width="180">
							<label><input id="id_ywmndcg_yes" type="radio" value="1" name="ywmndcg" ${xl001Brxx.ywmndcg eq 1 ? 'checked="checked"' : ''}/>&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label><input id="id_ywmndcg_no" type="radio" value="0" name="ywmndcg" ${( xl001Brxx.ywmndcg eq 0 )? 'checked="checked"' : ''}/>&nbsp;否</label>
						</td>
					</tr>
					<tr>
						<th width="140">动静脉插管</th>
						<td width="180">
							<label><input id="id_djmcg_yes" type="radio" value="1" name="djmcg" ${xl001Brxx.djmcg eq 1 ? 'checked="checked"' : ''}  onchange="registryForm.setYwdjmcgCanChoose();" />&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label><input id="id_djmcg_no" type="radio" value="0" name="djmcg" ${( xl001Brxx.djmcg eq 0 )? 'checked="checked"' : ''} onchange="registryForm.setYwdjmcgCanChoose();" />&nbsp;否</label>
						</td>
						<th width="140">血流感染前48小时内有动静脉插管</th>
						<td width="180">
							<label><input id="id_ywdjmcg_yes" type="radio" value="1" name="ywdjmcg" ${xl001Brxx.ywdjmcg eq 1 ? 'checked="checked"' : ''}/>&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label><input id="id_ywdjmcg_no" type="radio" value="0" name="ywdjmcg" ${( xl001Brxx.ywdjmcg eq 0 )? 'checked="checked"' : ''}/>&nbsp;否</label>
						</td>
					</tr>
					<tr>
						<th width="140">气管切开</th>
						<td width="180">
							<label><input id="id_qgqk_yes" type="radio" value="1" name="qgqk" ${xl001Brxx.qgqk eq 1 ? 'checked="checked"' : ''}  onchange="registryForm.setYwqgqqgCanChoose();" />&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label><input id="id_qgqk_no" type="radio" value="0" name="qgqk" ${( xl001Brxx.qgqk eq 0 )? 'checked="checked"' : ''} onchange="registryForm.setYwqgqqgCanChoose();" />&nbsp;否</label>
						</td>
						<th width="140">肺部感染前48小时内有气管切开</th>
						<td width="180">
							<label><input type="radio" value="1" name="ywqgqqg" ${xl001Brxx.ywqgqqg eq 1 ? 'checked="checked"' : ''}/>&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label><input type="radio" value="0" name="ywqgqqg" ${( xl001Brxx.ywqgqqg eq 0 )? 'checked="checked"' : ''}/>&nbsp;否</label>
						</td>
					</tr>
					<tr>
						<th width="140">使用呼吸机</th>
						<td width="180">
							<label><input id="id_syhxj_yes" type="radio" value="1" name="syhxj" ${xl001Brxx.syhxj eq 1 ? 'checked="checked"' : ''}  onchange="registryForm.setYwsyychxjCanChoose();" />&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label><input id="id_syhxj_no" type="radio" value="0" name="syhxj" ${( xl001Brxx.syhxj eq 0 )? 'checked="checked"' : ''} onchange="registryForm.setYwsyychxjCanChoose();" />&nbsp;否</label>
						</td>
						<th width="140">肺部感染前48小时内有使用呼吸机</th>
						<td width="180">
							<label><input type="radio" value="1" name="ywsyychxj" ${xl001Brxx.ywsyychxj eq 1 ? 'checked="checked"' : ''}/>&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label><input type="radio" value="0" name="ywsyychxj" ${( xl001Brxx.ywsyychxj eq 0 )? 'checked="checked"' : ''}/>&nbsp;否</label>
						</td>
					</tr>
					<tr>
						<th width="140">使用软式内镜</th>
						<td width="180">
							<label><input id="id_syrsnj_yes" type="radio" value="1" name="syrsnj" ${xl001Brxx.syrsnj eq 1 ? 'checked="checked"' : ''}  onchange="registryForm.setSyrsnjCanChoose();" />&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label><input id="id_syrsnj_no" type="radio" value="0" name="syrsnj" ${( xl001Brxx.syrsnj eq 0 )? 'checked="checked"' : ''} onchange="registryForm.setSyrsnjCanChoose();" />&nbsp;否</label>
						</td>
						<td colspan="2">
							<label style="padding-left: 10px;"><input type="checkbox" id="wj" name="wj" value="1" ${xl001Brxx.wj eq 1 ? 'checked="checked"' : ''} />胃镜</label>
							<label style="padding-left: 10px;"><input type="checkbox" id="cj" name="cj" value="1" ${xl001Brxx.cj eq 1 ? 'checked="checked"' : ''} />肠镜</label>
							<label style="padding-left: 10px;"><input type="checkbox" id="xzj" name="xzj" value="1" ${xl001Brxx.xzj eq 1 ? 'checked="checked"' : ''} />纤支镜</label>
							<label style="padding-left: 10px;"><input type="checkbox" id="hj" name="hj" value="1" ${xl001Brxx.hj eq 1 ? 'checked="checked"' : ''} />喉镜</label>
							<label style="padding-left: 10px;"><input type="checkbox" id="sezcj" name="sezcj" value="1" ${xl001Brxx.sezcj eq 1 ? 'checked="checked"' : ''} />十二指肠镜</label>
						</td>
					</tr>
					<tr>
						<th width="140">血液透析</th>
						<td width="180">
							<label><input type="radio" value="1" name="xytx" ${xl001Brxx.xytx eq 1 ? 'checked="checked"' : ''} />&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<label><input type="radio" value="0" name="xytx" ${( xl001Brxx.xytx eq 0 )? 'checked="checked"' : ''}/>&nbsp;否</label>
						</td>
						<th width="140">其他侵袭性操作</th>
						<td width="180">
							<input type="text" id="qtqxxcc" name="qtqxxcc" value="${xl001Brxx.qtqxxcc}"  />
						</td>
					</tr>
				</table>
			</div>
		</c:when>
	</c:choose>	
		
		<div class="title">
			<c:choose>
				<c:when test="${xhlVersion=='gx'}">五</c:when>
				<c:when test="${xhlVersion=='sz'}">四</c:when>
				<c:otherwise>三</c:otherwise>
			</c:choose> 
			、抗菌药物使用情况（仅指调查日抗菌药物的使用情况）：<a href="javascript:void(0);" class="text-icon icon-help" onclick="registryForm.setHelpContent(4);"></a></div>
		<div>
			<table class="info_table">
				<tr>
					<th width="140">抗菌药物使用</th>
					<td width="180">
						<input id="id_kj_yes" type="radio" value="1" name="sykjyw" ${xl001Brxx.sykjyw eq 1 ? 'checked="checked"' : ''} onchange="registryForm.setKjywCanChoose();" /><label for="id_kj_yes">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_kj_no" type="radio" value="0" name="sykjyw" ${(xl001Brxx.sykjyw eq 0 || empty xl001Brxx.sykjyw) ? 'checked="checked"' : ''} onchange="registryForm.setKjywCanChoose();" /><label for="id_kj_no">&nbsp;否</label>
					</td>
					<td width="180"></td><td></td>
				</tr>
				<tr>
					<th>抗菌药物名称</th>
					<td>
						<input type="text" style="width: 140px;" id="id_antibiotics_1" name="kjywmc1" value="${xl001Brxx.kjywmc1}"/>
					</td>
					<td>
						<input type="text" style="width: 140px;" id="id_antibiotics_2" name="kjywmc2" value="${xl001Brxx.kjywmc2}"/>
					</td>
					<td>
						<input type="text" style="width: 140px;" id="id_antibiotics_3" name="kjywmc3" value="${xl001Brxx.kjywmc3}"/>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<input type="text" style="width: 140px;" id="id_antibiotics_4" name="kjywmc4" value="${xl001Brxx.kjywmc4}"/>
					</td>
					<td>
						<!-- <input type="text" style="width: 140px;" id="id_antibiotics_5" /> -->
					</td>
					<td>
						<!-- <input type="text" style="width: 140px;" id="id_antibiotics_6" /> -->
					</td>
				</tr>
				<tr>
					<th>用药目的</th>
					<td colspan="3">
						<input id="id_treatment" type="radio" value="0" name="yymd" ${xl001Brxx.yymd eq 0 ? 'checked="checked"' : ''} onchange="registryForm.setZlyyspyCanChoose();" /><label for="id_treatment">&nbsp;治疗用药</label><span style="display:inline-block;width: 40px;"></span>
						<input id="id_prophylactic" type="radio" value="1" name="yymd" ${xl001Brxx.yymd eq 1 ? 'checked="checked"' : ''} onchange="registryForm.setZlyyspyCanChoose();" /><label for="id_prophylactic">&nbsp;预防用药</label><span style="display:inline-block;width: 40px;"></span>
						<input id="id_treat_prophy" type="radio" value="2" name="yymd" ${xl001Brxx.yymd eq 2 ? 'checked="checked"' : ''} onchange="registryForm.setZlyyspyCanChoose();" /><label for="id_treat_prophy">&nbsp;治疗+预防</label>
					</td>
				</tr>
				<tr>
					<th>用药联用</th>
					<td colspan="3">
						<input id="id_one_combinat" type="radio" value="0" name="lhyy" ${xl001Brxx.lhyy eq 0 ? 'checked="checked"' : ''} /><label for="id_one_combinat">&nbsp;一联</label><span style="display:inline-block;width: 40px;"></span>
						<input id="id_two_combinat" type="radio" value="1" name="lhyy" ${xl001Brxx.lhyy eq 1 ? 'checked="checked"' : ''} /><label for="id_two_combinat">&nbsp;二联</label><span style="display:inline-block;width: 40px;"></span>
						<input id="id_three_combinat" type="radio" value="2" name="lhyy" ${xl001Brxx.lhyy eq 2 ? 'checked="checked"' : ''} /><label for="id_three_combinat">&nbsp;三联</label><span style="display:inline-block;width: 40px;"></span>
						<input id="id_four_combinat" type="radio" value="3" name="lhyy" ${xl001Brxx.lhyy eq 3 ? 'checked="checked"' : ''} /><label for="id_four_combinat">&nbsp;四联及以上</label>
					</td>
				</tr>
				<tr>
					<th>治疗用药已送细菌培养</th>
					<td>
						<input id="id_sent_bact_yes" type="radio" value="1" name="zlyyspy" ${xl001Brxx.zlyyspy eq 1 ? 'checked="checked"' : ''} /><label for="id_sent_bact_yes">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_sent_bact_no" type="radio" value="0" name="zlyyspy" ${(xl001Brxx.zlyyspy eq 0 || empty xl001Brxx.zlyyspy) ? 'checked="checked"' : ''} /><label for="id_sent_bact_no">&nbsp;否</label>
					</td>
					<th>送培养时机为抗菌药物使用前</th>
					<td>
						<input id="id_send_before_yes" type="radio" value="1" name="spyshkjyw" ${xl001Brxx.spyshkjyw eq 1 ? 'checked="checked"' : ''} /><label for="id_send_before_yes">&nbsp;是</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_send_before_no" type="radio" value="0" name="spyshkjyw" ${xl001Brxx.spyshkjyw eq 0 ? 'checked="checked"' : ''} /><label for="id_send_before_no">&nbsp;否</label>
					</td>
				</tr>
			</table>
		</div>
	
	<c:if test="${xhlVersion=='gx'}">
		
		<div class="title">六、外科抗菌药物使用情况：</div>
		<div>
			<table class="info_table">
				<tr>
					<th width="140">术前应用抗菌药物</th>
					<td width="180">
						<input id="id_sqyy_yes" type="radio" value="1" name="sqyykjyw" ${xl001Brxx.sqyykjyw eq 1 ? 'checked="checked"' : ''} onchange="registryForm.setJyCanChoose();" /><label for="">&nbsp;是</label><span style="display:inline-block;width: 40px;"></span>
						<input id="id_sqyy_no" type="radio" value="0" name="sqyykjyw" ${(xl001Brxx.sqyykjyw eq 0 || empty xl001Brxx.sqyykjyw) ? 'checked="checked"' : ''} onchange="registryForm.setJyCanChoose();"/><label for="">&nbsp;否</label><span style="display:inline-block;width: 40px;"></span>
					</td>
					<th></th><td></td>
				</tr>
				<tr>
					<th width="140">术前0.5～2小时给予</th>
					<td width="180">
						<input id="id_jy_yes" type="radio" value="1" name="sqjy" ${xl001Brxx.sqjy eq 1 ? 'checked="checked"' : ''} /><label for="">&nbsp;是</label><span style="display:inline-block;width: 40px;"></span>
						<input id="id_jy_no" type="radio" value="0" name="sqjy" ${(xl001Brxx.sqjy eq 0 || empty xl001Brxx.sqjy) ? 'checked="checked"' : ''}/><label for="">&nbsp;否</label><span style="display:inline-block;width: 40px;"></span>
					</td>
					<th></th><td></td>
				</tr>
			</table>
		</div>
		
		</c:if>
		
			
			<div class="div_row" style="float: right;padding-bottom: 15px;padding-top: 15px;">
				<span><b>调查者</b></span>
				<span>
					<input type="hidden" name="votename" value="${xl001Brxx.votename}" id="id_investigators_name" />
					<input type="text" name="voteid" style="width: 100px;" id="id_investigators" />
				</span>
				<span><b>调查日期</b></span>
				<span>
					<input type="text" class="Wdate text" name="votedate"  value="<fmt:formatDate value="${xl001Brxx.votedate}" pattern="yyyy-MM-dd"  />" style="width: 80px;" readonly="readonly" <c:if test="${xl001Brxx.stateName ne '已登记'}">readOnly="readOnly" onclick="WdatePicker()"</c:if>  />
				</span>
			</div>
			<div class="clear"></div>
		</div>
		
		<div class="footer">			
			<div class="footer_btn">
				<div class="n_btn_blue">
						<a href="javascript:;" onclick="registryForm.formSubmit();" class="no_ico"><span>保存</span></a>
				</div>
				<div class="n_btn_grey">
						<a href="javascript:;" onclick="registryForm.cancel();" class="no_ico"><span>取消</span></a>
				</div>
			</div>
		</div>
		</form>
	</div>
	<div id="id_filling_explanation" class="easyui-dialog" title="填写说明" style="width:600px;height:350px;padding:15px 30px;"
						data-options="closed: true, modal:false,shadow:true,buttons:[{ text:'知道了', handler:function(){ $('#id_filling_explanation').dialog('close'); }}]" >
		<div id="id_filling_explanation_div" style="display: none;"></div>		
	</div>
	<div id="prevalence_note" style="height: 88%;">
		<div class="prevalence_note_box" style="display:none">
			<div class="Prevalence_note_title">本科现患调查情况</div>
			<div class="Prevalence_note_cont">
				<table class="note_table">
					<tr>
						<td>本科感染现患率：</td>
						<td><span class="red">85%</span></td>
						<td>偏高</td>
					</tr>
					<tr>
						<td>本科感染现患率：</td>
						<td><span class="red">90%</span></td>
						<td>偏高</td>
					</tr>
					<tr>
						<td>本科实查率：</td>
						<td><span class="red">95%</span></td>
						<td>偏高</td>
					</tr>
					<tr>
						<td>本科实查率：</td>
						<td><span class="red">90%</span></td>
						<td>偏高</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="id_wait_register" style="height: 100%;">
			<div id="tb_wait_register" style="display:none;text-align: center;font-size: 14px;">本科未登记列表</div>
			<div id="id_wait_register_cont">
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var registryForm = {
		isClean : '',	
		//医院感染部位序号
		unitPart : 0,
		//社区感染部位序号
		commPart : 0,
		//部位序号(动态添加通用)
		part : 0,
		//感染部位序号(动态添加通用)
		pathogens : 0,
		//是否是添加
		isAdd : '${isAdd}',
		//添加时查到的诊断名称
		diagnosisName : '',
		//添加医院感染
		addUnitInfect : function() {
			var htmlStr = '<fieldset><div class="div_row unit_part" style="margin: 0px 5px;" num="' + this.unitPart + '"><span class="row_title"><b>医院感染部位</b></span><span style="width: 220px;"><input type="hidden" name="xl2ListU[' + registryForm.unitPart + '].infectName" id="id_unit_part_name_' + registryForm.unitPart + '" /><input type="text" name="xl2ListU[' + registryForm.unitPart + '].infectCode" style="width: 170px;" id="id_unit_part_' + registryForm.unitPart + '" /></span><span><a class="ico_no" href="javascript:void(0)" onclick="registryForm.delInfect(this);" title="删除"><i class="icon iconfont fax">&#xe62b;</i></a></span><span><a class="ico_no" href="javascript:void(0)" onclick="registryForm.addPathogens(this, \'unit\');" title="添加病原体"><i class="icon iconfont">&#xe602;</i></a></span></div><div class="unit_pathogens" pnum="' + registryForm.unitPart + '" num="0"><div class="div_row" style="margin: 0px 5px" onmouseover="registryForm.showDelDrug(this);" onmouseout= "registryForm.hideDelDrug(this);"><span class="row_title"><b>病原体</b></span><span style="width: 220px;"><input type="hidden" name="xl2ListU[' + registryForm.unitPart + '].xl003List[0].infectPathoName" id="id_unit_pathogens_name_' + registryForm.unitPart + '_0" /><input type="text"  name="xl2ListU[' + registryForm.unitPart + '].xl003List[0].infectPathoId" style="width: 170px;" id="id_unit_pathogens_' + registryForm.unitPart + '_0" /></span><span><select style="width: 83px;" id="id_pathoresult_u_' + registryForm.unitPart + '_0" name="xl2ListU[' + registryForm.unitPart + '].xl003List[0].pathoresult"><option value=""></option><option value="敏感">敏感</option><option value="MDR">MDR</option><option value="XDR">XDR</option><option value="PDR">PDR</option></select></span><span>'+
			'<select id="id_unit_sample_' + registryForm.unitPart + '_0" style="width:90px;"  name="xl2ListU[' + registryForm.unitPart + '].xl003List[0].sample">'+
			'</select></span><span class="row_but" style="vertical-align:middle;display: none;"><a href="javascript:void(0)" style="margin: 0px;" class="ico_no" title="删除" onclick="registryForm.delPathogens(this);"><i class="icon iconfont fax">&#xe62b;</i></a></span></div></div></fieldset>';
			$('#id_unit_infect').append(htmlStr);
			//渲染easyui下拉框
			this.renderPart('unit', this.unitPart, '');
			this.renderPathogens('unit', this.unitPart + '_0', '');
			this.renderSample('unit', this.unitPart + '_0', '');
			//必填验证
			//this.validateRequired('id_pathoresult_u_' + this.unitPart + '_0');
			$('#id_pathoresult_u_' + this.unitPart + '_0').combobox({editable : false});
			this.unitPart ++;
		},
		//添加社区感染
		addCommInfect : function() {
			var htmlStr = '<fieldset><div class="div_row comm_part" style="margin: 0px 5px;" num="' + this.commPart + '"><span class="row_title"><b>社区感染部位</b></span><span style="width: 220px;"><input type="hidden" name="xl2ListC[' + registryForm.unitPart + '].infectName" id="id_comm_part_name_' + registryForm.commPart + '" /><input type="text" name="xl2ListC[' + registryForm.commPart + '].infectCode" style="width: 170px;" id="id_comm_part_' + registryForm.commPart + '"/></span><span><a class="ico_no" href="javascript:void(0)" onclick="registryForm.delInfect(this);" title="删除"><i class="icon iconfont fax">&#xe62b;</i></a></span><span><a class="ico_no" href="javascript:void(0)" onclick="registryForm.addPathogens(this, \'comm\');" title="添加病原体"><i class="icon iconfont">&#xe602;</i></a></span></div><div  class="comm_pathogens" pnum="' + registryForm.commPart + '" num="0"><div class="div_row" style="margin: 0px 5px" onmouseover="registryForm.showDelDrug(this);" onmouseout= "registryForm.hideDelDrug(this);"><span class="row_title"><b>病原体</b></span><span style="width: 220px;"><input type="hidden" name="xl2ListC[' + registryForm.commPart + '].xl003List[0].infectPathoName" id="id_comm_pathogens_name_' + registryForm.commPart + '_0" /><input type="text"  name="xl2ListC[' + registryForm.commPart + '].xl003List[0].infectPathoId" style="width: 170px;" id="id_comm_pathogens_' + registryForm.commPart + '_0" /></span><span><select style="width: 83px;"  id="id_pathoresult_c_' + registryForm.commPart + '_0" name="xl2ListC[' + registryForm.commPart + '].xl003List[0].pathoresult" ><option value=""></option><option value="敏感">敏感</option><option value="MDR">MDR</option><option value="XDR">XDR</option><option value="PDR">PDR</option></select></span><span>'+
			'<select id="id_comm_sample_' + registryForm.commPart + '_0" style="width:90px;"  name="xl2ListC[' + registryForm.commPart + '].xl003List[0].sample">'+
			'</select></span><span class="row_but" style="vertical-align:middle;display: none;"><a href="javascript:void(0)" style="margin: 0px;" class="ico_no" title="删除" onclick="registryForm.delPathogens(this);"><i class="icon iconfont fax">&#xe62b;</i></a></span></div></div></fieldset>';
			$('#id_comm_infect').append(htmlStr);
			//渲染easyui下拉框
			this.renderPart('comm', this.commPart, '');
			this.renderPathogens('comm', this.commPart + '_0', '');
			this.renderSample('comm', this.commPart + '_0', '');
			//必填验证
			//this.validateRequired('id_pathoresult_c_' + this.commPart + '_0');
			$('#id_pathoresult_c_' + this.commPart + '_0').combobox({editable : false});
			this.commPart ++;
		},
		//删除感染
		delInfect : function(obj) {
			$(obj).parent().parent().parent().remove();
		},
		//添加病原体
		addPathogens : function(obj, type) {
			var object = $(obj).parent().parent().parent();
			var num = object.children(":last").attr('num');
			var pnum = object.children(":last").attr('pnum');
			if (pnum) {
				this.part = pnum;
				this.pathogens = parseInt(num) + 1;
			} else {
				this.part = $(obj).parent().parent().attr('num');
				this.pathogens = 0;
			}
			var htmlStr = '<div class="' + type + '_pathogens" pnum="' + this.part + '" num="' + this.pathogens + '" ><div class="div_row" style="margin: 0px 5px" onmouseover="registryForm.showDelDrug(this);" onmouseout= "registryForm.hideDelDrug(this);"><span class="row_title"><b>病原体</b></span><span style="width: 220px;"><input type="hidden" name="' + (type == 'unit' ? 'xl2ListU' : 'xl2ListC') + '[' + this.part + '].xl003List[' + this.pathogens + '].infectPathoName" id="id_' + type + '_pathogens_name_' + this.part + '_' + this.pathogens + '" /><input type="text" name="' + (type == 'unit' ? 'xl2ListU' : 'xl2ListC') + '[' + this.part + '].xl003List[' + this.pathogens + '].infectPathoId" style="width: 170px;" id="id_' + type + '_pathogens_' + 
				this.part + '_' + this.pathogens + '" /></span><span><select style="width: 83px;" class="easyui-combobox" id="id_pathoresult_' + (type == 'unit' ? 'u_' : 'c_') + this.part + '_' + this.pathogens + '" name="' + (type == 'unit' ? 'xl2ListU' : 'xl2ListC') + '[' + this.part + '].xl003List[' + this.pathogens + '].pathoresult"  ><option value=""></option><option value="敏感">敏感</option><option value="MDR">MDR</option><option value="XDR">XDR</option><option value="PDR">PDR</option></select></span>'+
				'<span><select id="id_' + type + '_sample_' + this.part + '_' + this.pathogens + '" style="width:90px;"  name="' + (type == 'unit' ? 'xl2ListU' : 'xl2ListC') + '[' + this.part + '].xl003List[' + this.pathogens + '].sample">'+
				'</select></span>'+
				'<span class="row_but" style="vertical-align:middle;display: none;"><a href="javascript:void(0)" style="margin: 0px;" class="ico_no" title="删除" onclick="registryForm.delPathogens(this);"><i class="icon iconfont fax">&#xe62b;</i></a></span></div></div>';

			object.append(htmlStr);
			if (type == 'unit') {
				this.renderPathogens('unit', this.part + '_' + this.pathogens, '');
				this.renderSample('unit', this.part + '_'+this.pathogens, '');
				//必填验证
				//this.validateRequired('id_pathoresult_u_' + this.part + '_' + this.pathogens);
				$('#id_pathoresult_u_' + this.part + '_' + this.pathogens).combobox({editable : false});
			} else {
				this.renderPathogens('comm', this.part + '_' + this.pathogens, '');
				this.renderSample('comm', this.part + '_'+this.pathogens, '');
				//必填验证
				//this.validateRequired('id_pathoresult_c_' + this.part + '_' + this.pathogens);
				$('#id_pathoresult_c_' + this.part + '_' + this.pathogens).combobox({editable : false});
			}
		},
		//删除病原体
		delPathogens : function(obj) {
			$(obj).parent().parent().parent().remove();
		},
		//添加药敏药物
		addDrugAllergy : function(obj) {
			var htmlStr = '<div class="div_row drug" onmouseover="registryForm.showDelDrug(this);" onmouseout= "registryForm.hideDelDrug(this);"><span class="row_title"><b>药敏药物</b></span><span style="width: 220px;"><select style="width: 173px;" class="easyui-combobox"><option value="1">A药物</option><option value="2">B药物</option><option value="3">C药物</option></select></span><span><select style="width: 60px;"><option value="1">耐药</option><option value="2">敏感</option><option value="3">中介</option></select></span><span style="vertical-align:middle;display: none;"><a href="javascript:void(0)" style="margin: 0px;" class="ico_no" title="删除" onclick="registryForm.delDrugAllergy(this);"><i class="icon iconfont fax">&#xe62b;</i></a></span></div>';
			$(obj).parent().parent().parent().append(htmlStr);
		},
		//删除药敏药物
		delDrugAllergy : function(obj) {
			$(obj).parent().parent().remove();
		},
		//显示删除药敏药物
		showDelDrug : function(obj) {
			$(obj).children(":last").show();
		},
		//隐藏删除药敏药物
		hideDelDrug : function(obj) {
			$(obj).children(":last").hide();
		},
		//渲染感染部位
		renderPart : function(type, num, value) {
			/* $('#id_' + type + '_part_' + num).combogrid({
				delay: 1000,
			    value: value,
			    required:true,
			    editable:false,
		        idField:'indiagid',
		        textField:'indiagname',
		        panelWidth: 300,
		        panelHeight: 300,
				url: '${webroot}/xl009DicInfectdiag/f_json/query.shtml?page=1&size=200',
		        columns:[
		        	[
		             {field:'indiagid',title:'部位编号',sortable:true,align:'center',width:90},  
		             {field:'indiagname',title:'部位名称',sortable:true,width:180},
		            ]
		        ],
				onClickRow : function(index,row){
					$('#id_' + type + '_part_name_' + num).val(row.indiagname);
				}
			}); */
			$('#id_' + type + '_part_' + num).combotree({
				editable:false,
				url: '${webroot}/xl009DicInfectdiag/f_json/querytree.shtml',
				required:true,
				method:'get',
				required:true,
				panelHeight:300,
				panelWidth:250,
				value:value,
				onBeforeSelect: function(node) {
		            if (!$(this).tree('isLeaf', node.target)) {
		                return false;
		            }
		        },
		        onClick: function(node) {  
		            if (!$(this).tree('isLeaf', node.target)) {  
		            	$(this).tree('toggle',node.target);
		            	$('#id_' + type + '_part_' + num).combo('showPanel');  
		            } else{
		            	$('#id_' + type + '_part_name_' + num).val(node.text);
		            }
		        },
		        onLoadSuccess:function(node,data){
		            var node = $('#id_' + type + '_part_' + num).combotree('tree').tree('getSelected');            
		            if(node){
	               		$('#id_' + type + '_part_' + num).combotree('tree').tree('expandTo',node.target);//展开选中节点
	               	}
	            }
			}); 
		},
		//渲染病原体
		renderPathogens : function(type, num, value) {
			$('#id_' + type + '_pathogens_' + num).combogrid({
				delay: 1000,
			    value: value,
		        idField:'pathoid',
		        textField:'pathoname',
		        panelWidth: 290,
		        panelHeight: 300,
				url: '${webroot}/xl011DicPatho/f_json/query.shtml?page=1&size=200',
		        columns:[
		        	[
		             {field:'pathoid',title:'病原体编号',sortable:true,align:'center',width:80},  
		             {field:'pathoname',title:'病原体名称',sortable:true,width:180},
		            ]
		        ],
				onClickRow : function(index,row){
					$('#id_' + type + '_pathogens_name_' + num).val(row.pathoname);
					//设置药敏结果
					choosePathogens.setDrugResults('id_' + type + '_pathogens_' + num, type, row.pathoid);
				},
				onHidePanel : function() {
		        	Csm.valueValite.combogrid('id_' + type + '_pathogens_' + num);
				}
			});
		},
		renderSample : function(type,num,value){
			$('#id_' + type + '_sample_' + num).combobox({
			    url:'${webroot}/nyBbDict/f_json/queryList.shtml',
			    panelWidth:120,
			    valueField:'bbid',
			    textField:'bbmc',
			    
			    value:value
			});
		},
		//渲染抗菌药物
		renderAntibiotics : function(num, value) {
			$('#id_antibiotics_' + num).combogrid({
				delay: 1000,
			    value: value,
			    mode: 'remote',
			    loadMsg : '正在查询中...',
		        idField:'drugName',
		        textField:'drugName',
		        panelWidth: 220,
		        panelHeight: 300,
				url: '${webroot}/xl020XhlKjyw/f_json/query.shtml?page=1&size=300',
		        columns:[
		        	[
		             {field:'drugId',title:'编号',sortable:true,align:'center',width:70},  
		             {field:'drugName',title:'名称',sortable:true,width:120},
		            ]
		        ],
				onHidePanel : function() {
		        	Csm.valueValite.combogrid('id_antibiotics_' + num);
				}
			});
		},
		//是否显示医院感染/社区感染
		disUnitAndCommInfect : function() {
			if ($('#id_inf_exist').attr("checked")) {
				$('#id_unitInfe').attr("disabled", false);
				$('#id_commInfe').attr("disabled", false);
				if ($('#id_unitInfe').is(':checked')) {
					/* $("#id_unit_infect .require").each(function(){
						$(this).validatebox({
						    required: true
						});
					}); */
					/* $("#id_unit_infect input:text").each(function(){
						$(this).removeClass("validatebox-invalid");
						$(this).attr("disabled",false);
					}); */
					$("#id_unit_infect .combogrid-f").each(function(){
						var id = $(this).attr("id");
						$('#' + id).combogrid('enable');
					});
					$("#id_unit_infect select").each(function(){
						$(this).attr("disabled",false);
					});
					//$("#id_unit_infect input:text").attr("disabled",false);
					//$("#id_unit_infect select").attr("disabled",false);
					$('#unit_infect').show();
				} else {
					$("#id_unit_infect .combogrid-f").each(function(){
						var id = $(this).attr("id");
						$('#' + id).combogrid('disable');
						$('#' + id).next().find(".textbox-text").removeClass("validatebox-invalid");
					});
					$("#id_unit_infect select").each(function(){
						$(this).removeClass("validatebox-invalid");
						$(this).attr("disabled",true);
					});
					//$("#id_unit_infect input:text").attr("disabled",true);
					//$("#id_unit_infect select").attr("disabled",true);
					$('#unit_infect').hide();
				}
				if ($('#id_commInfe').attr("checked")) {
					$("#id_comm_infect .combogrid-f").each(function(){
						var id = $(this).attr("id");
						$('#' + id).combogrid('enable');
					});
					$("#id_comm_infect select").each(function(){
						$(this).attr("disabled",false);
					});
					$('#comm_infect').show();
				} else {
					$("#id_comm_infect .combogrid-f").each(function(){
						var id = $(this).attr("id");
						$('#' + id).combogrid('disable');
						$('#' + id).next().find(".textbox-text").removeClass("validatebox-invalid");
					});
					$("#id_comm_infect select").each(function(){
						$(this).removeClass("validatebox-invalid");
						$(this).attr("disabled",true);
					});
					$('#comm_infect').hide();
				}
			} else {
				$('#id_unitInfe').attr("disabled", true);
				$('#id_commInfe').attr("disabled", true);
				$("#id_unit_infect .combogrid-f").each(function(){
					$('#' + id).next().find(".textbox-text").removeClass("validatebox-invalid");
					var id = $(this).attr("id");
					$('#' + id).combogrid('disable');
				});
				$("#id_unit_infect select").each(function(){
					$(this).removeClass("validatebox-invalid");
					$(this).attr("disabled",true);
				});
				$('#unit_infect').hide();
				$("#id_comm_infect .combogrid-f").each(function(){
					$('#' + id).next().find(".textbox-text").removeClass("validatebox-invalid");
					var id = $(this).attr("id");
					$('#' + id).combogrid('disable');
				});
				$("#id_comm_infect select").each(function(){
					$(this).removeClass("validatebox-invalid");
					$(this).attr("disabled",true);
				});
				$('#comm_infect').hide();
			}
		},
		//设置切口是否可选
		setGradeCanChoose : function() {
			if ($('#id_isOper_yes').attr("checked")) {
				$("input:radio[name='isGrade']").attr("disabled", false);
				$('#id_operName1').attr("disabled", false);
				$('#id_operName2').attr("disabled", false);
				$("input:radio[name='gradeType2']").attr("disabled", false);
				$("input:radio[name='pop']").attr("disabled", false);
			} else {
				$("input:radio[name='isGrade']").removeAttr("checked");
				$("input:radio[name='isGrade']").attr("disabled", true);
				$('#id_operName1').attr("disabled", true);
				$('#id_operName2').attr("disabled", true);
				$("input:radio[name='gradeType2']").removeAttr("checked");
				$("input:radio[name='gradeType2']").attr("disabled", true);
				$("input:radio[name='pop']").removeAttr("checked");
				$("input:radio[name='pop']").attr("disabled", true);
			}
		},
		//设置泌尿道感染前48小时内有插管操作是否可选
		setYwmndcgCanChoose : function() {
			if ($('#id_mndcg_yes').attr("checked")) {
				$("input:radio[name='ywmndcg']").attr("disabled", false);
			} else {
				$("input:radio[name='ywmndcg']").removeAttr("checked");
				$("input:radio[name='ywmndcg']").attr("disabled", true);
			}
		},
		//设置血流感染前48小时内有动静脉插管是否可选
		setYwdjmcgCanChoose : function() {
			if ($('#id_djmcg_yes').attr("checked")) {
				$("input:radio[name='ywdjmcg']").attr("disabled", false);
			} else {
				$("input:radio[name='ywdjmcg']").removeAttr("checked");
				$("input:radio[name='ywdjmcg']").attr("disabled", true);
			}
		},
		//设置肺部感染前48小时内有气管切开是否可选
		setYwqgqqgCanChoose : function() {
			if ($('#id_qgqk_yes').attr("checked")) {
				$("input:radio[name='ywqgqqg']").attr("disabled", false);
			} else {
				$("input:radio[name='ywqgqqg']").removeAttr("checked");
				$("input:radio[name='ywqgqqg']").attr("disabled", true);
			}
		},
		//设置肺部感染前48小时内有使用呼吸机是否可选
		setYwsyychxjCanChoose : function() {
			if ($('#id_syhxj_yes').attr("checked")) {
				$("input:radio[name='ywsyychxj']").attr("disabled", false);
			} else {
				$("input:radio[name='ywsyychxj']").removeAttr("checked");
				$("input:radio[name='ywsyychxj']").attr("disabled", true);
			}
		},
		//设置使用软式内镜选项是否可选
		setSyrsnjCanChoose : function() {
			if ($('#id_syrsnj_yes').attr("checked")) {
				$("input:checkbox[name='wj']").attr("disabled", false);
				$("input:checkbox[name='cj']").attr("disabled", false);
				$("input:checkbox[name='xzj']").attr("disabled", false);
				$("input:checkbox[name='hj']").attr("disabled", false);
				$("input:checkbox[name='sezcj']").attr("disabled", false);
			} else {
				$("input:checkbox[name='wj']").removeAttr("checked");
				$("input:checkbox[name='wj']").attr("disabled", true);
				$("input:checkbox[name='cj']").removeAttr("checked");
				$("input:checkbox[name='cj']").attr("disabled", true);
				$("input:checkbox[name='xzj']").removeAttr("checked");
				$("input:checkbox[name='xzj']").attr("disabled", true);
				$("input:checkbox[name='hj']").removeAttr("checked");
				$("input:checkbox[name='hj']").attr("disabled", true);
				$("input:checkbox[name='sezcj']").removeAttr("checked");
				$("input:checkbox[name='sezcj']").attr("disabled", true);
			}
		},
		//设置气管是否可选
		setQgCanChoose : function() {
			if ($('#id_syhxj_yes').attr("checked")) {
				$("input:radio[name='qgcg']").attr("disabled", false);
				$("input:radio[name='qgqk']").attr("disabled", false);
			} else {
				$("input:radio[name='qgqk']").attr("disabled", true).removeAttr("checked");
				$("input:radio[name='qgcg']").attr("disabled", true).removeAttr("checked");
			}
		},
		//设置给予是否可选
		setJyCanChoose : function() {
			if ($('#id_sqyy_yes').attr("checked")) {
				$("input:radio[name='sqjy']").attr("disabled", false);
			} else {
				$("input:radio[name='sqjy']").attr("disabled", true).removeAttr("checked");
			}
		},
		//设置抗菌药物是否可选
		setKjywCanChoose : function() {
			var $yymd = $("input[name=yymd]");  
			var $lhyy = $("input[name=lhyy]");  
			var $zlyyspy = $("input[name=zlyyspy]");  
			var $spyshkjyw = $("input[name=spyshkjyw]");  
			
			if ($('#id_kj_yes').attr("checked")) {
				for (var i = 1; i < 5; i++) {
					$('#id_antibiotics_' + i).combobox("enable");
					//$('#id_antibiotics_name_' + i).attr("disabled", false);
				}
				$yymd.attr("disabled", false);
				$lhyy.attr("disabled", false);
				$zlyyspy.attr("disabled", false);
				$spyshkjyw.attr("disabled", false);
			} else {
				for (var i = 1; i < 5; i++) {
					$('#id_antibiotics_' + i).combogrid("setValue","");
					$('#id_antibiotics_' + i).combobox("disable");
					//$('#id_antibiotics_name_' + i).attr("disabled", true);
				}
				$yymd.removeAttr("checked");
				$lhyy.removeAttr("checked"); 
				$zlyyspy.removeAttr("checked"); 
				$spyshkjyw.removeAttr("checked"); 
				$yymd.attr("disabled", true);
				$lhyy.attr("disabled", true); 
				$zlyyspy.attr("disabled", true);
				$spyshkjyw.attr("disabled", true);
			}
		},
		//设置抗菌药物是否可选
		setZlyyspyCanChoose : function() {
			var $zlyyspy = $("input[name=zlyyspy]");  
			
			if ($('#id_prophylactic').attr("checked")) {
				$zlyyspy.attr("disabled", true);
				$zlyyspy.removeAttr("checked"); 
			} else {
				$zlyyspy.attr("disabled", false);
			}
		},
		//提交处理
		formSubmit : function() {
			//获取医院感染和科室感染的信息验证并处理
			//医院感染
			var unitPart = new Array();
			$('.unit_part').each(function(){
				unitPart.push($(this).attr('num'));
   			});
			var unitMap = {};
			for (var i=0; i<unitPart.length; i++) {
				var upart = unitPart[i];
				var unitPathogens = new Array();
				$('.unit_pathogens[pnum="' + upart + '"]').each(function(){
					unitPathogens.push($(this).attr('num'));
	   			});
				unitMap[upart] = unitPathogens;
			}
			$('#id_unit_infect_map').val(JSON.stringify(unitMap));
			//社区感染
			var commPart = new Array();
			$('.comm_part').each(function(){
				commPart.push($(this).attr('num'));
   			});
			var commMap = {};
			for (var i=0; i<commPart.length; i++) {
				var cpart = commPart[i];
				var commPathogens = new Array();
				$('.comm_pathogens[pnum="' + cpart + '"]').each(function(){
					commPathogens.push($(this).attr('num'));
	   			});
				commMap[cpart] = commPathogens;
			}
			$('#id_comm_infect_map').val(JSON.stringify(commMap));
			if (this.isAdd == '1') {
				if (registryForm.diagnosisName && registryForm.diagnosisName == $('#id_diagnoseId').combogrid('getValue')) {
					$.messager.show({title : '提示',msg : '请选择对应的标准诊断！'});
					return false;
				}
			}
			//手术验证
			if ($('#id_isOper_yes').attr("checked")) {
				if ($.trim($('#id_operName1').val()).length == 0 && $.trim($('#id_operName2').val()).length == 0) {
					$.messager.show({title : '提示',msg : '请填写手术！'});
					return false;
				}
				if ($.trim($('#id_operName1').val()).length != 0 && !$("input:radio[name='isGrade']:checked").val()) {
					$.messager.show({title : '提示',msg : '请选择切口类型一！'});
					return false;
				} else if ($.trim($('#id_operName1').val()).length == 0 && $("input:radio[name='isGrade']:checked").val()) {
					$.messager.show({title : '提示',msg : '请填写手术一！'});
					$('#id_operName1').focus();
					return false;
				} else if ($.trim($('#id_operName2').val()).length != 0 && !$("input:radio[name='gradeType2']:checked").val()) {
					$.messager.show({title : '提示',msg : '请选择切口类型二！'});
					return false;
				} else if ($.trim($('#id_operName2').val()).length == 0 && $("input:radio[name='gradeType2']:checked").val()) {
					$.messager.show({title : '提示',msg : '请填写手术二！'});
					$('#id_operName2').focus();
					return false;
				}
			}
			if ($('#id_inf_exist').attr("checked")) {
				if (!$('#id_unitInfe').attr("checked") && !$('#id_commInfe').attr("checked")) {
					$.messager.show({title : '提示',msg : '请选择感染分类！'});
					return false;
				} else if ($('#id_unitInfe').attr("checked") && unitPart.length == 0) {
					$.messager.show({title : '提示',msg : '请填写医院感染！'});
					return false;
				} else if ($('#id_commInfe').attr("checked") && commPart.length == 0) {
					$.messager.show({title : '提示',msg : '请填写社区感染！'});
					return false;
				}
			}
			if(!$("#id_investigators").combogrid("getValue") || !$("#id_investigators_name").val()){
				$.messager.show({ title: '提示', msg: '请从列表中选择调查者。'});
				return false;
			}
			<c:if test="${xhlVersion=='gx'}">
			if ($('#id_syhxj_yes').attr("checked")){
				if (!$('#id_qgcg_yes').attr("checked") && !$('#id_qgcg_no').attr("checked")) {
					$.messager.show({title : '提示',msg : '请选择气管插管！'});
					return false;
				}else if (!$('#id_qgqk_yes').attr("checked") && !$('#id_qgqk_no').attr("checked")) {
					$.messager.show({title : '提示',msg : '请选择气管切开！'});
					return false;
				}
			}
			</c:if>
			if ($('#id_sqyy_yes').attr("checked")) {
				if (!$('#id_jy_yes').attr("checked") && !$('#id_jy_no').attr("checked")) {
					$.messager.show({title : '提示',msg : '请选择术前0.5～2小时给予！'});
					return false;
				}
			}
			if($("input[name='sykjyw']:checked").val() == '1'){
				if ($("#id_antibiotics_1").combogrid('getValue').length == 0 && $("#id_antibiotics_2").combogrid('getValue').length == 0 
						&& $("#id_antibiotics_3").combogrid('getValue').length == 0 && $("#id_antibiotics_4").combogrid('getValue').length == 0) {
					$.messager.show({title : '提示',msg : '请选择抗菌药物！'});
					return false;
				}
				if (!$("input[name='yymd']:checked").val()) {
					$.messager.show({title : '提示',msg : '请选择用药目的！'});
					return false;
				}
				var count = -1;
				if($("#id_antibiotics_1").combogrid('getValue') != ''){
					count++;
				}
				if($("#id_antibiotics_2").combogrid('getValue') != ''){
					count++;
				}
				if($("#id_antibiotics_3").combogrid('getValue') != ''){
					count++;
				}
				if($("#id_antibiotics_4").combogrid('getValue') != ''){
					count++;
				}
				if(!$("input[name='lhyy']:checked").val()){
					$.messager.show({title : '提示',msg : '请选择抗菌药物联数！'});
					return false;
				}
				if(($("input[name='lhyy']:checked").val()) != count){
					$.messager.show({title : '提示',msg : '填写的抗菌药物和选择的抗菌药物联数不一致！'});
					return false;
				}
				if ($('#id_sent_bact_yes').attr("checked") && !$("input[name='spyshkjyw']:checked").val()) {
					$.messager.show({title : '提示',msg : '请选择送培养时机！'});
					return false;
				}
			}			
			$('#id_form').submit();
		},
		validateRequired : function(id) {
			$('#' + id).validatebox({
			    required: true
			});
		},
		//设置帮助内容
		setHelpContent : function(num) {
			var htmlStr = registryHelp.general;
			if (num == 2) {
				htmlStr = registryHelp.infections + registryHelp.bacterialDrug;
			} else if (num == 3) {
				htmlStr = registryHelp.bacterialDrug;
			} else if (num == 4) {
				htmlStr = registryHelp.antimicrobialUse;
			}else if (num == 5) {
				htmlStr = registryHelp.riskFactor;
			}
			$('#id_filling_explanation_div').css('display','');
			$('#id_filling_explanation_div').html(htmlStr);
			$('#id_filling_explanation').dialog('center');
			$('#id_filling_explanation').dialog('open');
		},
		//设置未登记列表
		setWaitRegisterList : function(deptId) {
			$('#id_wait_register_cont').datagrid({
				url: '${webroot}/xl001Brxx/f_json/findWaitRegister.shtml',
	            queryParams: {
	            	'deptId':deptId
	            },
	            pageNumber : 1
            });
		},
		//登记个案
		setRegister : function(brid) {
			parent.menuInfo.clickMenu('个案登记表','/xl001Brxx/f_view/toRegistryFormEdit.shtml?brid=' + brid,true, null, null, '${param.fromTabBodyId}');
		},
		//取消
		cancel : function() {
			$.messager.confirm('提示', '确认取消?', function (r) {
	        	if (r) {
	        		parent.menuInfo.closeMenu('个案登记表');
	        	}
	    	});
		},
		//刷新原来的列表页面
		refreshList : function() {
			try {
				var ifr = parent.document.getElementById('${param.fromTabBodyId}');
				var win = ifr.window || ifr.contentWindow;
				win.caseStudy.query();
			} catch (e) {
				//console.error(e);
			}
		},
		//刷新列表当条记录
		refreshRecord : function() {
			try {
				var ifr = parent.document.getElementById('${param.fromTabBodyId}');
				var win = ifr.window || ifr.contentWindow;
				win.caseStudy.refreshRecord('${xl001Brxx.brid}');
			} catch (e) {
				//console.error(e);
			}
		},
		//清空单选选择
		clearRadio : function(name) {
			$("input:radio[name='" + name + "']").removeAttr("checked");
		}
	};
	
	$(document).ready(function () {
		//是否显示医院感染/社区感染
		registryForm.disUnitAndCommInfect();
		//设置切口是否可选
		registryForm.setGradeCanChoose();
		//设置帮助项
		//registryForm.setHelpContent(1);
		
		window.setTimeout(function(){
			Comm.form({
				id: 'id_form',
				url: '${webroot}/xl001Brxx/f_json/saveRegistryForm.shtml',
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
                		registryForm.refreshRecord(); //IE8不支持
    					//parent.menuInfo.clickMenu('横断面个案调查','/xl001Brxx/f_view/toList.shtml',false);
                		parent.$('#mainTabs').tabs('select', '横断面个案调查');
                		//parent.menuInfo.refreshMenu('横断面个案调查');
                		parent.menuInfo.closeMenu('个案登记表');
                		//registryForm.setRegister(json.data);
    				} else {
    					$.messager.show({title : '提示',msg : json.msg});
    				}
				}
			});
		},100);
		
		//科室
		Csm.combogrid.dep({
			id: 'id_dept',
			value: '${xl001Brxx.deptId}',
			ifcaseoffice: '1',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
			required:true,
			onClickRow : function(index,row){
				$('#id_deptName').val(row.deptName);
				//查询本科室待登记数据
				registryForm.setWaitRegisterList(row.deptId);
			},
			onHidePanel : function() {
	        	Csm.valueValite.combogrid('id_dept');
			}
		});
		
		//诊断
		$('#id_diagnoseId').combogrid({
			delay: 1000,
		    value: '${xl001Brxx.diagnoseId}',
		    required:true,
	        idField:'statid',
	        textField:'statname',
	        panelWidth: 370,
	        panelHeight: 300,
			url: '${webroot}/xl010DicStatkind/f_json/query.shtml?page=1&size=200',
	        columns:[
	        	[
	             {field:'statid',title:'诊断编号',sortable:true,align:'center',width:60},  
	             {field:'statname',title:'诊断名称',sortable:true,width:280},
	            ]
	        ],
			onClickRow : function(index,row){
				$('#id_diagnose').val(row.statname);
			},
			onHidePanel : function() {
	        	Csm.valueValite.combogrid('id_diagnoseId');
			}
		});
		
		//操作人
		$('#id_investigators').combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
			value: '${xl001Brxx.voteid}',
			required:true,
	        idField:'employeeId',
	        panelWidth: 260,
	        panelHeight: 300,
	        textField:'employeeName',
			url: '${webroot}/doctor/json/queryToSelect.shtml?page=1&size=200&defValue=${xl001Brxx.voteid}',
	        columns:[
	        	[
	             {field:'employeeId',title:'职工编号',sortable:true,width:150},  
	             {field:'employeeName',title:'职工名称',sortable:true,width:80}
	            ]
	        ],
			onClickRow : function(index,row){
				$('#id_investigators_name').val(row.employeeName);
			},
			onLoadSuccess : function(data){
				if(data.total>0){
					var r = $('#id_investigators').combogrid('grid').datagrid('getSelected');
					if(r){
						$('#id_investigators').combogrid("setValue",r['employeeId']);
						$("#id_investigators_name").val(r['employeeName']);
					}else{
						$("#id_investigators_name").val("");
					}
				}else{
					$("#id_investigators_name").val("");
				}
			},
			onHidePanel : function() {
	        	Csm.valueValite.combogrid('id_investigators');
			}
		});
		
		//本科未登记列表
		$('#id_wait_register_cont').datagrid({
			fit: true,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			fitColumns: false,
			collapsible:true,   
			remoteSort: false,
			singleSelect: true,
			loadMsg:'处理中...',
			columns:[
				[ 
				     {field:'patientId',title:'${patientNoTitle}',sortable:true,width:88},
				     {field:'patientName',title:'患者姓名',sortable:true,width:60},
				     {field:'_operate',title:'操作',width:30,
    					formatter:function(value,row,index){
    						return ['<a href="javascript:void(0);" class="ico_editor" title="登记" onclick ="registryForm.setRegister(\'' + row.brid + '\')"></a>'].join('');
    					}
    				}
				]
			],
			pagination:true,
	        rownumbers:true,
	        toolbar:'#tb_wait_register',
	        pageSize:14,
	        pageList:[14],
	        onBeforeLoad: function() {
	        	var pager = $('#id_wait_register_cont').datagrid('getPager');
	        	pager.pagination({  
	        		showPageList:false,
	        		showRefresh: false,
	        		afterPageText: '页', 
	        		displayMsg:''
        		});
	        	$('#tb_wait_register').next().height(parseInt($('#id_wait_register').children(":first").children(":first").height()) - 56);
            }
        });
		
		if (registryForm.isAdd == '1') {
			//查询病人信息
			$('#id_sel_patient').combogrid({
				delay: 1000,    
			    mode: 'remote',
			    loadMsg : '正在查询中...',
			    value: '',
		        idField:'zyid',
		        panelWidth: 450,
		        panelHeight: 300,
		        textField:'patientName',
				url: '${webroot}/st003Cryxxb/f_json/query.shtml?page=1&size=20',
		        columns:[
		        	[
		             {field:'zyid',title:'${patientZyTitle}',sortable:true,width:100},  
		             {field:'patientName',title:'姓名',sortable:true,width:60},
		             {field:'deptName',title:'科室',sortable:true,width:100},
		             {field:'inHospAt',title:'入院时间',sortable:true,width:80},
		             {field:'outAt',title:'出院	时间',sortable:true,width:80}
		            ]
		        ],
				onClickRow : function(index,row){
					$('#id_visitId').val(row.visitId);
					$('#id_zyid').val(row.zyid);
					$('#id_dept').combogrid('setValue', row.deptCode);
					$('#id_deptName').val(row.deptName);
					registryForm.setWaitRegisterList(row.deptCode);
					$('#id_bedNo').val(row.bedNo);
					$('#id_patientId').val(row.patientId);
					$('#id_patientName').val(row.patientName);
					if ('男' == row.sex) {
						$('#id_man').attr("checked",'checked');
					} else if ('女' == row.sex) {
						$('#id_woman').attr("checked",'checked');
					}
					$('#id_age').val(row.age);
					if ('岁' == row.ageUnit) {
						$('#id_annum').attr("checked",'checked');
					} else if ('月' == row.ageUnit) {
						$('#id_month').attr("checked",'checked');
					} else if ('天' == row.ageUnit) {
						$('#id_day').attr("checked",'checked');
					}
					//AJAX查询
					$.ajax({
		                url: '${webroot}/gr002YsgrMx/f_json/findAddRegistryForm.shtml',
		                type: 'post',
		                data: { zyid: row.zyid },
		                dataType: 'json',
		                success : function(json) {
		                	if (json.diagnosisName != null) {
		                		$('#id_diagnoseId').combogrid('setValue', json.diagnosisName + '（请选择对应的标准诊断）');
		                		registryForm.diagnosisName = json.diagnosisName + '（请选择对应的标准诊断）';
		                	}
		                	if (json.gradeType != null) {
		                		$('#id_isOper_yes').attr("checked",'checked');
		                		$('#id_grade_' + json.gradeType).attr("checked",'checked');
		                		//设置切口是否可选
		    					registryForm.setGradeCanChoose();
		                	}
						}
		    		});
				}
			});
		} else {
			//查询本科室待登记数据
			registryForm.setWaitRegisterList('${xl001Brxx.deptId}');
		}
				
		//抗菌药物
		registryForm.renderAntibiotics(1, '${xl001Brxx.kjywmc1}');
		registryForm.renderAntibiotics(2, '${xl001Brxx.kjywmc2}');
		registryForm.renderAntibiotics(3, '${xl001Brxx.kjywmc3}');
		registryForm.renderAntibiotics(4, '${xl001Brxx.kjywmc4}');
		//设置抗菌药物是否可选
		registryForm.setKjywCanChoose();
		<c:if test="${xhlVersion=='gx'}">
			//设置是否可选
			registryForm.setQgCanChoose();
		</c:if>
		//
		registryForm.setJyCanChoose();
		//registryForm.renderAntibiotics(5, '');
		//registryForm.renderAntibiotics(6, '');
		
		$(window).scroll(function() { 
			var top = $(window).scrollTop() + 5; 
			//var left= $(window).scrollLeft() + 850; 
			$("#prevalence_note").css({ left: "50%", top: top + "px" });
			$("#id_filling_explanation").panel("move",{top:$(document).scrollTop() + ($(window).height()-350) * 0.5});
		}); 
		
		
		 <c:if test="${xhlVersion=='gx' }">
			//切口选中
			if($('#id_isOper_yes').attr("checked")){
				if(!$(":radio[name='isGrade']:checked").val()){
					//选了手术，但切口为空，就默认选中无切口
					$("#id_grade_4").attr("checked","checked");
				}
			}
		</c:if>
		
		<c:if test="${xhlVersion=='sz'}">
		registryForm.setYwmndcgCanChoose();
		registryForm.setYwdjmcgCanChoose();
		registryForm.setYwqgqqgCanChoose();
		registryForm.setYwsyychxjCanChoose();
		registryForm.setSyrsnjCanChoose();
		</c:if>
	});
</script>
<c:forEach items="${untiXl2List}" var="xl002" varStatus="status">
	<script type="text/javascript">
	$(document).ready(function () {
		//病原体
		registryForm.renderPart('unit', '${status.index}', '${xl002.infectCode}');
		registryForm.unitPart ++;
	});
	</script>
	<c:forEach items="${xl002.xl003List}" var="xl003" varStatus="status3">
		<script type="text/javascript">
		$(document).ready(function () {
			//药敏药物
			registryForm.renderPathogens('unit', '${status.index}_${status3.index}', '${xl003.infectPathoId}');
			registryForm.renderSample('unit', '${status.index}_${status3.index}', '${xl003.sample}');
		});
		</script>
	</c:forEach>
</c:forEach>

<c:forEach items="${commXl2List}" var="xl002" varStatus="status">
	<script type="text/javascript">
	$(document).ready(function () {
		//病原体
		registryForm.renderPart('comm', '${status.index}', '${xl002.infectCode}');
		registryForm.commPart ++;
	});
	</script>
	<c:forEach items="${xl002.xl003List}" var="xl003" varStatus="status3">
		<script type="text/javascript">
		$(document).ready(function () {
			//药敏药物
			registryForm.renderPathogens('comm', '${status.index}_${status3.index}', '${xl003.infectPathoId}');
			registryForm.renderSample('comm', '${status.index}_${status3.index}', '${xl003.sample}');
		});
		</script>
	</c:forEach>
</c:forEach>
</body>
</html>