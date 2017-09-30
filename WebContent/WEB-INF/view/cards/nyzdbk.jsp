<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>农药中毒报告卡</title>
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
    .ib{display:inline-block; _zoom:1;_display:inline;margin-right: 10px;line-height: 25px;}
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
	<form id="zdxxbk">
		<div style="margin: 60px 5%;margin-top:10px; width: 90%;">
			<center><h1>农药中毒报告卡</h1></center>
			<div style="width: 100%;">		
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">患者基本信息</span>
						<input type="hidden" name="masterid" id="masterid" value="${zdxx.masterid}" />
						<input type="hidden" name="isemptycard" id="isemptycard" 
						<c:choose>
							<c:when test="${!empty zdxx}">
								value="${zdxx.isemptycard}" 
							</c:when>
							<c:when test="${isEmptyCard eq 'Y'}">
								value="1"
							</c:when>
							<c:otherwise>
								value="0" 
							</c:otherwise>
						</c:choose> />
						<input type="hidden" id="flag" name="flag" value="${zdxx.flag}" />
						<input type="hidden" name="patientId" value='<c:if test="${! empty BRXX}">${BRXX.patientId}</c:if><c:if test="${! empty zdxx}">${zdxx.patientId}</c:if>' />
						<input type="hidden" name="visitId" value="<c:if test="${! empty BRXX}">${BRXX.visitId}</c:if><c:if test="${! empty zdxx}">${zdxx.visitId}</c:if>" />
						<input type="hidden" id="validpersonid" name="validpersonid" value="${zdxx.validpersonid}"/>
						<input type="hidden" id="validpersonname" name="validpersonname" value="${zdxx.validpersonname}"/>
						<input type="hidden" id="validdt" name="validdt" value='<fmt:formatDate value="${zdxx.validdt}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
					</div>
					<table class="mainTable" >
						<c:if test="${isEmptyCard eq 'Y' || zdxx.isemptycard==1}">
						<tr>
							<td class="rightTextAlign" style="color:#3e9c06;">注：</td>
							<td colspan="5"><div style="color:#3e9c06;">门诊号/住院号/病例号可填身份证号</div></td>
						</tr>
						</c:if>
						<tr>
							<td class="rightTextAlign" style="width:110px"><span class="red">*</span>门诊号/住院号：</td>
							<td>
								<input type="text" id="mzzyId" <c:if test="${empty isEmptyCard}">readonly="readonly"</c:if> class="easyui-validatebox" required="true"
								<c:choose>
									<c:when test="${patientType eq 'zy' }">
										name="zyid" value="${!empty zdxx.zyid?zdxx.zyid:BRXX.zyid}"
									</c:when>
									<c:when test="${patientType eq 'mz' }">
										name="mzid" value="${!empty zdxx.mzid?zdxx.mzid:BRXX.mzid}"
									</c:when>
								</c:choose> />
							</td>
							
							<td class="rightTextAlign"><span class="red">*</span>姓名：</td>
							<td>
								<input type="text" id="patientName" name="patientName" class="easyui-validatebox" required="true" value="<c:if test="${! empty BRXX}">${BRXX.patientName}</c:if><c:if test="${! empty zdxx}">${zdxx.patientName}</c:if>"/>
							</td>
							
							<td class="rightTextAlign"><span class="red">*</span>身份证号：</td>
							<td>
								<input type="text" name="idNo" id="idNo" style="float:left;" class="easyui-validatebox" data-options="validType:'idcared[1]',required:true" 
								<c:choose>
									<c:when test="${!empty zdxx}">
										value="${zdxx.idNo}"
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
							<td class="rightTextAlign"><span class="red">*</span>性别：</td>
							<td>
								<label style="padding-right: 10px;">
									<input type="radio" name="sex" id="nan" value="男"
									<c:choose>
										<c:when test="${zdxx.sex eq '男'}">
											checked="checked"
										</c:when>
										<c:when test="${BRXX.sex eq '男'}">
											checked="checked"
										</c:when>
									</c:choose> class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=sex]\', \'性别\']'"/>男</label>
								<label style="padding-right: 10px;">
									<input type="radio" name="sex" id="nv" value="女" 
									<c:choose>
										<c:when test="${zdxx.sex eq '女'}">
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
								<c:when test="${!empty zdxx}">
									value='<fmt:formatDate value="${zdxx.birthday}" pattern="yyyy-MM-dd" />'
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
										<c:when test="${!empty zdxx}">
											value="${zdxx.age}"
										</c:when>
										<c:otherwise>
											value='${BRXX.age}'
										</c:otherwise>
									</c:choose> />
								<select style="width:60px;" name="ageUnit" id="ageunit" class="easyui-combobox">
									<option value="岁" 
									<c:choose>
										<c:when test="${!empty zdxx && zdxx.ageUnit eq '岁'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '岁'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose> >岁</option>
									<option value="月" 
									<c:choose>
										<c:when test="${!empty zdxx && zdxx.ageUnit eq '月'}">
											selected="selected"
										</c:when>
										<c:otherwise>
											${(BRXX.ageUnit eq '月'?"selected='selected'":"")}
										</c:otherwise>
									</c:choose>	>月</option>
									<option value="天" 
									<c:choose>
										<c:when test="${!empty zdxx && zdxx.ageUnit eq '天'}">
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
							<td class="rightTextAlign"><span class="red">*</span>联系电话：</td>
							<td>
								<input type="text" name="tel" id="tel" class="easyui-validatebox" required="true" value="${zdxx.tel}"/>
							</td>
							<td class="rightTextAlign">卡片序号：</td>
							<td>
								<input type="text" name="cardId" id="cardId" class="easyui-validatebox" value="${zdxx.cardId}"/>
							</td>
							<td></td><td></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>现住址：</td>
							<td colspan="5">
								<select style="width: 12%;" name="province" id="sheng" class="easyui-combobox" data-options="required:true" ><option></option></select>省
								<select style="width: 12%;" name="city" id="shi" class="easyui-combobox" data-options="required:true" ><option></option></select>市
								<select style="width: 12%;" name="country" id="xian" class="easyui-combobox" data-options="required:true"  ><option></option></select>县（区）
								<select style="width: 12%;" id="xiang" class="easyui-combobox" data-options="required:true"  ><option></option></select>乡（街道）
								<input type="text" style="width: 12%;" name="nowaddrVillage" id="nowaddrVillage" value="${zdxx.nowaddrVillage}" onblur="publicfullAddress('now','xiang');"/>居委会（村）
								<input type="text" style="width: 12%;" name="nowaddrGroup" value="${zdxx.nowaddrGroup}" id="nowaddrGroup" onblur="publicfullAddress('now','xiang');"/>号
								
								<input type="hidden" id="nowaddrareacode" name="nowaddrareacode" value="${zdxx.nowaddrareacode}"/><!-- 编码 -->
								<input type="hidden" id="nowaddr" name="nowaddr" value="${zdxx.nowaddr}"/><!-- 完整的住址 -->
							</td>
						</tr>
						<tr style="display: none;">
							<td></td>
							<td colspan="5">
								<input type="text" id="nowfulladdr" name="nowfulladdr" style="width:88%;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">农药中毒信息</span>
					</div>
					<table class="mainTable" >
						<tr>
							<td class="rightTextAlign" style="width:180px;line-height: 26px;"><span class="red">*</span>中毒数量：</td>
							<td colspan="3">
								<%-- <nis:radio dictcode="pp_poisoningNum" name="poisoningNum" value="${zdxx.poisoningNum}"/> --%>
								<c:forEach items="${poisoningNum}" var="pn">
									<div class="ib"><label style="width:150px;display:block;"><input type="radio" name="poisoningNum" <c:if test="${zdxx.poisoningNum eq pn.dictName}">checked="checked"</c:if> value="${pn.dictName}" class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=poisoningNum]\', \'中毒数量\']'" />${pn.dictName}</label></div>
								</c:forEach>
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class="rightTextAlign" style="line-height: 26px;"><span class="red">*</span>具体中毒农药名称：</td>
							<td colspan="5">
								<input type="text" name="pesticideList" id="pesticideList" style="width:250px;" value="${zdxx.pesticideList}" class="easyui-validatebox" required="true"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign">中毒农药种类：</td>
							<td colspan="5" style="line-height: 26px;">
							<%-- 	<nis:select dictcode="pp_pesticideKind" id="pesticideKind" name="pesticideKind" cssCls="" headerKey="" headerValue="" exp=''></nis:select> --%>
								<select  id="pesticideKind" name="pesticideKind" class="easyui-combobox" data-options="onChange:function(n,o){if('其他'==n || '其它'==n ){$('#kindOther').show();}else{$('#kindOther').hide();}  }">
									<option></option>
									<c:forEach items="${pesticideKind}" var="pk">
										<option value="${pk.dictName}" <c:if test="${zdxx.pesticideKind eq pk.dictName}">selected="selected"</c:if> >${pk.dictName}</option>
									</c:forEach>
								</select>
								<input type="text" id="kindOther" name="kindOther" style="display:none;" value="${zdxx.kindOther}"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign" style="line-height: 26px;"><span class="red">*</span>中毒原因：</td>
							<td colspan="5">
								<%-- <nis:radio dictcode="pp_poisoningReason" name="poisoningReason" value="${zdxx.poisoningReason }" cssCls="easyui-validatebox" exp="repuired='true'"/> --%>
								<c:forEach items="${poisoningReason}" var="pr">
									<div class="ib"><label style="width:150px;display:block;"><input type="radio" name="poisoningReason" value="${pr.dictName}" <c:if test="${zdxx.poisoningReason eq pr.dictName}">checked="checked"</c:if> class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=poisoningReason]\', \'中毒原因\']'" />${pr.dictName}</label></div>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign" style="line-height: 26px;">职业安全卫生知识的培训：</td>
							<td colspan="5">
								<%-- <nis:radio dictcode="pp_train" name="train"/> --%>
								<c:forEach items="${train}" var="t">
									<div class="ib"><label style="width:150px;display:block;"><input type="radio" name="train" <c:if test="${zdxx.train eq t.dictName}">checked="checked"</c:if> value="${t.dictName}" />${t.dictName}</label></div>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign" style="line-height: 26px;">施药方式：</td>
							<td colspan="5">
								<%-- <nis:radio dictcode="pp_drugWay" name="drugWay" /> --%>
								<c:forEach items="${drugWay}" var="dw">
									<div class="ib"><label style="width:150px;display:block;"><input type="radio" name="drugWay" <c:if test="${zdxx.drugWay eq dw.dictName}">checked="checked"</c:if> value="${dw.dictName}" />${dw.dictName}</label></div>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign" style="line-height: 26px;vertical-align: top;">危险行为：</td>
							<td colspan="5">
								<c:forEach items="${riskBehavior}" var="rb" varStatus="rbindex">
									<div class="ib"><label style="width:150px;display:block;"><input type="checkbox" name="riskBehavior" value="${rb.dictCode}" <c:if test="${fn:contains(zdxx.riskBehavior,rb.dictCode)}">checked="checked"</c:if> />${rb.dictName}</label></div>
									<c:if test="${(rbindex.index+1)%4==0}">
										<br/>
									</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign" style="line-height: 26px;"><span class="red">*</span>转归：</td>
							<td colspan="5">
								<%-- <nis:radio dictcode="lapse_to" name="outcome" exp='onclick="showOther(this,\'outcomeOther\');"'/> --%>
								<c:forEach items="${outcome}" var="oc">
									<div class="ib"><label style="width:70px;display:block;"><input type="radio" name="outcome" value="${oc.dictName}" <c:if test="${zdxx.outcome eq oc.dictName}">checked="checked"</c:if> onclick="showOther(this,'outcomeOther');"  class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=outcome]\', \'转归\']'" />${oc.dictName}</label></div>
								</c:forEach>
								<input type="text" id="outcomeOther" name="outcomeOther" style="width:100px;display:none;" value="${zdxx.outcomeOther}"/>
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>诊断日期：</td>
							<td style="width: 243px;">
								<input type="text" name="diagnoseDt" id="diagnoseDt" readonly="readonly" class="Wdate text easyui-validatebox" required="true" style="width: 130px;" value="<fmt:formatDate value="${zdxx.diagnoseDt}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" />
							</td>
							<td class="rightTextAlign">死亡日期：</td>
							<td style="width: 243px;">
								<input type="text" name="deadDt" id="deadDt" readonly="readonly" class="Wdate text" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" value="<fmt:formatDate value="${zdxx.deadDt }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							</td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>	
				<div class="card_cont">
					<div class="card_cont_h" >
						<span class="card_c_h_text">报告信息</span>
					</div>
					<table class="mainTable" >
						<tr>
							<td class="rightTextAlign">诊断单位：</td>
							<td>
								<input id="diagnoseUnit" name="diagnoseUnit" type="text" value="${zdxx.diagnoseUnit }"/>
							</td>
							<td class="rightTextAlign">单位负责人：</td>
							<td>
								<input id="unitLeader" name="unitLeader" type="text" value="${zdxx.unitLeader}"/>
							</td>
							<td class="rightTextAlign">填报科室：</td>
							<td>
								<input class="easyui-combobox" name="reportdeptid" id="reportdeptid" style="width: 142px;" value="${zdxx.reportdeptid }" />
								<input type="hidden" name="reportdeptname" id="reportdeptname" value="${zdxx.reportdeptname }" />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign" style="width:104px"><span class="red">*</span>填报人：</td>
							<td>
								<div class="select_del">
									<input class="easyui-combobox" name="reportdoctorid" id="reportdoctorid" value="${zdxx.reportdoctorid }" style="width: 142px;" />
								</div> 
								<input type="hidden" name="reportdoctorname" id="reportdoctorname" value="${zdxx.reportdoctorname }" />
							</td>
							<td class="rightTextAlign" style="width:104px">填报人联系电话：</td>
							<td>
								<input type="text" id="reportTel" name="reportTel" value="${zdxx.reportTel}" />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>填报日期：</td>
							<td style="width:243px">
								<input type="text" name="reportDate" id="reportDate" readonly="readonly" disabled="disabled"
								<c:choose>
									<c:when test="${!empty zdxx}">
										value='<fmt:formatDate value="${zdxx.reportdt}" pattern="yyyy-MM-dd HH:mm:ss" />'
									</c:when>
									<c:otherwise>
										value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" />'
									</c:otherwise>
								</c:choose>
								class="Wdate text easyui-validatebox" required="true" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})" />
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
<script type="text/javascript">
$(function(){
	<c:if test="${acType eq 'hospital'}">
		$("#reportDate").removeAttr("disabled");
		<c:if test="${!empty zdxx.flag && zdxx.flag!=0}">
			$("#NoEdit").css("height",$("#zdxxbk").height());
		</c:if>
		//$("#upload").html("<span>修改</span>");
	</c:if>
	<c:if test="${acType eq 'doctor'}">
		<c:if test="${!empty zdxx && zdxx.flag!=2}">
		$("#NoEdit").css("height",$("#zdxxbk").height());
		</c:if>
	</c:if>
	
	fillData();
	
	/* $("#printButt").parent().hide(); //隐藏打印按钮 */
	
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
	
	get_public_sheng("now","sheng","shi","xian","xiang",null);

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
				if(""=="${zdxx.reportdeptid}"){
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
					//默认选中第一行
					//$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${user.depNo}");
				}else{
					$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${zdxx.reportdeptid}");
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
			<c:when test="${!empty zdxx}">
				value:"${zdxx.reportdoctorid}",
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
				if(!"${zdxx.reportdoctorid}"){
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
					$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${zdxx.reportdoctorid}");
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
 	
 	<c:if test="${empty zdxx}">
		getInfoByID();
 	</c:if>
	
});

function get_public_sheng(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getSheng.shtml";
	var eatSheng = "${curSheng}";
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
  			}
   		});
		$("#"+sheng).append(appendstr);
		if(type=="now"){
			if(eatSheng){
				$("#"+sheng).combobox({'value':eatSheng});
			}else{
				$("#"+sheng).combobox({'value':"${(HASheng)}"});
			}
		}
		get_public_shi(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_shi(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+sheng).combobox("getValue");
	var eatShi = "${curShi}";
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
  			}
   		});
		$("#"+shi).append(appendstr);
		if(type=="now"){
			if(eatShi){
				$("#"+shi).eq(0).combobox({'value':eatShi});
			}else{
				$("#"+shi).eq(0).combobox({'value':"${(HAShi)}"});
			}
		}
		get_public_xian(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_xian(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+shi).combobox("getValue");
	var eatXian = "${curXian}";
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
  			}
   		});
		$("#"+xian).append(appendstr);
		if(type=="now"){
			if(eatXian){
				$("#"+xian).combobox({'value':eatXian});
			}else{
				$("#"+xian).combobox({'value':"${(HAXian)}"});
			}
		}
		get_public_xiang(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_xiang(type,sheng,shi,xian,xiang,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+xian).combobox("getValue");
	var nowXiang = "${curXiang}";
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
  			}
   		});
		$("#"+xiang).append(appendstr);
		if(type=="now"){
			if(nowXiang){
				$("#"+xiang).combobox({'value':nowXiang});
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
	}
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
	if(!$("#mzzyId").val()){
		$.messager.show({ title: '提示', msg: '请填写门诊号/住院号。' });
		return false;
	}
	if($("#age").val()){
		if($("#ageunit option:selected").val()=='岁' && $("#age").val()>120){
			$.messager.show({ title: '提示', msg: '年龄最大不能超过120岁。' });
			return false;
		}
	}
	if(!$("#reportdeptid").combogrid("getValue") || !$("#reportdeptname").val()){
		$.messager.show({ title: '提示', msg: '请从下拉列表中选择报告科室。'});
		return false;
	}
	if(!$("#reportDate").val()){
		$.messager.show({ title: '提示', msg: '请选择报告日期。'});
		return false;
	}
	if(!$("#reportdoctorid").combogrid("getValue") || !$("#reportdoctorname").val()){
		$.messager.show({ title: '提示', msg: '请从下拉列表中选择报告人。'});
		return false;
	}
	return true;
}
function report(){
	var result = $("#zdxxbk").form('validate'); 
	var info = "确认保存？";
	<c:if test="${acType eq 'hospital'}">
		<c:choose>
			<c:when test="${empty flag}">
				info="确认保存？";
			</c:when>
			<c:otherwise>
				info="确认修改？";
			</c:otherwise>
		</c:choose>	
	</c:if>
	if(result){
		if(checkForm()){
			$.messager.confirm('提示', info, function (r) {
				if(r){
					$.ajax({
						url:"${webroot}/cdc/f_json/savePesticideCard.shtml",
						data:$("#zdxxbk").serialize(),
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
			$("#NoEdit").css("height",$("#zdxxbk").height());
			$("#upload-main").hide();
		}else{
			//刷新当前tab页
			parent.menuInfo.refreshMenu(parent.menuInfo.getCurSelectTabTitle());
		}
	});
}

function fillData(){
	//中毒农药种类其他
	if($("#pesticideKind option:selected").val()=="其他"){
		$("#kindOther").show();
	}
	if($(":radio[name='outcome']:checked").val()=="其它" || $(":radio[name='outcome']:checked").val()=="其他"){
		$("#outcomeOther").show();
	}
}

function audit(bktype,msid){
	//触发保存，验证表单数据是否符合规范
	var result = $("#zdxxbk").form('validate'); 
	if(result){
		if(checkForm()){
			$.messager.confirm("提示", "确认审核该报卡？", function (r) {
				if(r){
					$.ajax({
							url:"${webroot}/cdc/f_json/savePesticideCard.shtml",
							data:$("#zdxxbk").serialize(),
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
function showOther(ele,otherId){
	if($(ele).is(":checked")){
		if("其他"==$(ele).val() || "其它"==$(ele).val()){
			$("#"+otherId).show();
		}else{
			$("#"+otherId).val("").hide();
		}
		if("死亡"==$(ele).val()){
			$("#deadDt").addClass("easyui-validatebox").attr("required","true");
			$.parser.parse($("#deadDt").parent());
		}else{
			$("#deadDt").removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox').val("");
		}
	}else{
		if("其他"==$("option:selected",ele).text() || "其它"==$("option:selected",ele).text()){
			$("#"+otherId).show();
		}else{
			$("#"+otherId).hide();
		}
	}
}
</script>
</body>
</html>