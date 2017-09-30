<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>传染病上报卡</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css${version}" />
<script type="text/javascript" src="${webroot}/resources/js/idcard.check.js"></script>
</head>
<body style="width: auto; min-width: 1100px;">
	<div id="NoEdit" title="该报卡状态不允许被编辑" style="position:absolute;width:100%;z-index: 10;background: rgba(255,255,255,0.1);filter:alpha(opacity=30) ; opacity:0.3; background-color:#ffffff;"></div>
	<c:if test="${!empty errMsg}">
		<div class="errTip">${errMsg}</div>
	</c:if>
	<c:if test="${!empty tipMsg}">
		<div class="success">${tipMsg}</div>
	</c:if>
	<form id="crbbk">
		<div style="margin: 60px 5%; margin-top: 10px; width: 90%;">
			<center>
				<h1>中华人民共和国传染病报告卡</h1>
			</center>
			<div style="width: 100%;">
				<div class="card_cont">
					<div class="card_cont_h">
						<span class="card_c_h_text">患者基本信息</span> <input type="hidden"
							name="masterid" id="masterid" value="${mz.masterid}" /> <input
							type="hidden" name="cardid" id="cardid" value="${mz.cardid}" />
					</div>
					<table class="mainTable" title="患者基本信息">
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>报卡类别：</td>
							<td>
								<select style="width: 142px;" id="reporttypeid" name="reporttypeid" class="easyui-combobox">
									<option value="1"
										<c:if test="${mz.reporttypeid=='1'}"> selected="selected"</c:if>>初次报告</option>
									<option value="2"
										<c:if test="${mz.reporttypeid=='2'}"> selected="selected"</c:if>>订正报告</option>
									<option value="3"
										<c:if test="${mz.reporttypeid=='3'}"> selected="selected"</c:if>>死亡报告</option>
								</select>
								<input type="hidden" id="reporttypename" name="reporttypename" value="${mz.reporttypename}" />
							</td>
							<td class="dzbmtd rightTextAlign" style="<c:if test="${mz.reporttypeid!='2'}">display: none;</c:if>"><span class="red">*</span>订正病名：</td>
							<td class="dzbmtd" style="<c:if test="${mz.reporttypeid!='2'}">display: none;</c:if>">
								<input type="text" id="dzbm" name="dzbm" style="width:130px;" value="${mz.dzbm}" />
							</td>
							<td></td><td></td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>病例号：</td>
							<td class="">
								<input type="text" name="patientId" readonly="readonly" required="true" class="easyui-validatebox" style="width: 130px;" value='<c:if test="${! empty BRXX}">${BRXX.patientId}</c:if><c:if test="${! empty mz}">${mz.patientId}</c:if>' />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>门诊号/${patientZyTitle}：</td>
							<td>
								<input type="text" style="width: 130px;" readonly="readonly" name="zyid" required="true" class="easyui-validatebox" value="<c:if test="${! empty BRXX}">${BRXX.zyid}</c:if><c:if test="${! empty mz}">${mz.zyid}</c:if>" />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>住院次数：</td>
							<td>
								<input type="text" style="width: 130px;" readonly="readonly" required="true" class="easyui-validatebox" name="visitId" value="<c:if test="${! empty BRXX}">${BRXX.visitId}</c:if><c:if test="${! empty mz}">${mz.visitId}</c:if>" />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>患者姓名：</td>
							<td>
								<input type="text" style="width: 130px;" readonly="readonly" required="true" class="easyui-validatebox" name="patientName" value="<c:if test="${! empty BRXX}">${BRXX.patientName}</c:if><c:if test="${! empty mz}">${mz.patientName}</c:if>" />
							</td>
							<td class="rightTextAlign">患儿家长姓名：</td>
							<td>
								<input type="text" style="width: 130px;" name="parentName" id="parentName" value="${mz.parentName}" />
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>证件类型：</td>
							<td>
								<select style="width: 142px;" name="idtype" id="idtype" class="easyui-combobox">
									<c:forEach items="${cardType}" var="ct">
										<option value="${ct.dictCode}" <c:if test="${mz.idtype eq ct.dictCode}">selected="selected"</c:if>>${ct.dictName}</option>
									</c:forEach>
								</select> 
								<input type="hidden" name="idtypevalue" id="idtypevalue" value="${mz.idtypevalue}" /></td>
							<td class="rightTextAlign"><span class="red">*</span>有效证件号：</td>
							<td>
								<input type="text" id="idcard" style="width: 130px; float:left;" name="idcard"
								<c:choose> 
									<c:when test="${!empty mz}">value="${mz.idcard}"</c:when> 
									<c:otherwise>value="${BRXX.idCard}"</c:otherwise>
								</c:choose> class="easyui-validatebox" data-options="required:true,validType:'idcared'" />
								 <!-- <input	type="button" class="butt" value="提取性别和生日1" onclick="getInfoByID();" /> -->
								<a href="javascript:;" class="tqxx" title="提取性别和生日" onclick="getInfoByID();"></a>
							</td>
							<td class="rightTextAlign NCNPlace">国家传染病编号：</td>
							<td class="NCNPlace">
								<input type="text" id="nidn" name="nidn" value="${mz.nidn}"/>
							</td>
						</tr>
						<tr>
							<td colspan="6" style="line-height: 25px;">
								（有效证件号不许输入中文；包括：居民身份证号、护照、居民健康卡、社会保障卡、新农合医疗卡。暂无身份证号的
								儿童及残障患者填写监护人有效证件号，特殊信息请在备注中填写）</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>性别：</td>
							<td><select style="width: 142px;" name="sexid" id="sexid" class="easyui-combobox"  >
									<option value="1"
										<c:choose>
											<c:when test="${!empty mz && mz.sexid=='1'}">selected="selected"</c:when>
											<c:when test="${!empty BRXX && BRXX.sex=='男'}">selected="selected"</c:when>
										</c:choose>>男</option>
									<option value="2"
										<c:choose>
											<c:when test="${!empty mz && mz.sexid!='1'}">selected="selected"</c:when>
											<c:when test="${!empty BRXX && BRXX.sex!='男'}">selected="selected"</c:when>
										</c:choose>>女</option>
							</select> 
							<input type="hidden" name="sexname" id="sexname" value="${mz.sexname}" /></td>
							<td class="rightTextAlign"><span class="red">*</span>出生日期：</td>
							<td>
								<input type="text" style="width: 130px;" id="birthday" name="birtyday" readonly="readonly" required="true"
								<c:choose>
									<c:when test="${!empty mz}">value='<fmt:formatDate value="${mz.birtyday}" pattern="yyyy-MM-dd" />'</c:when>
									<c:otherwise>value='<fmt:formatDate value="${BRXX.birthDate}" pattern="yyyy-MM-dd" />'</c:otherwise>
								</c:choose>
								class="Wdate text easyui-validatebox" onchange="$('#age').val(ages($(this).val()));$('#ageunit').val('岁')" onclick='WdatePicker({dateFmt:"yyyy-MM-dd",maxDate:"<fmt:formatDate value="${now}"/>"})' /></td>
							<td class="rightTextAlign"><span class="red">*</span>生日不详填年龄：</td>
							<td><input type="text" style="width: 60px;" id="age" name="age" required="true" class="easyui-validatebox" onkeyup="value=value.replace(/[^\d]/g,'')"
								<c:choose>
								<c:when test="${!empty mz}">
									value="${mz.age}"
								</c:when>
								<c:otherwise>
									value="${BRXX.age}"
								</c:otherwise>
							</c:choose> />
								<select style="width: 60px;" name="ageunit" id="ageunit" class="easyui-combobox">
									<option value="岁"
										<c:choose>
									<c:when test="${!empty BRXX && BRXX.ageUnit=='岁'}">selected="selected"</c:when>
									<c:when test="${!empty mz && mz.ageunit=='岁'}">selected="selected"</c:when>
								</c:choose>>岁</option>
									<option value="月"
										<c:choose>
									<c:when test="${!empty BRXX && BRXX.ageUnit=='月'}">selected="selected"</c:when>
									<c:when test="${!empty mz && mz.ageunit=='月'}">selected="selected"</c:when>
								</c:choose>>月</option>
									<option value="天"
										<c:choose>
									<c:when test="${!empty BRXX && BRXX.ageUnit=='天'}">selected="selected"</c:when>
									<c:when test="${!empty mz && mz.ageunit=='天'}">selected="selected"</c:when>
								</c:choose>>天</option>
							</select></td>
						</tr>
						<tr>
							<td class="rightTextAlign">患者工作单位：</td>
							<td colspan="5">
								<input type="text" id="unit" name="unit" style="width: 93%;" value="${mz.unit}" />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>联系电话：</td>
							<td>
								<input type="text" id="telp" name="telp" style="width: 130px;"
								<c:choose>
									<c:when test="${!empty mz }">value="${mz.telp}"</c:when>
									<c:otherwise>value="${BRXX.tel}"</c:otherwise>
								</c:choose>
								required="true" class="easyui-validatebox" onkeyup="value=value.replace(/[^\d]/g,'')" />
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="rightTextAlign" style="line-height: 26px;"><span class="red">*</span>病人属于：</td>
							<td colspan="5">
							<%-- <select style="width: 142px;" id="belong"
								name="areatypeid" onchange="CRBMaddress();">
									<c:forEach items="${patientBelong}" var="pb">
										<option value="${pb.dictCode}"
											<c:if test="${mz.areatypeid==pb.dictCode}">selected="selected"</c:if>>${pb.dictName}</option>
									</c:forEach>
							</select> --%>
							<c:forEach items="${patientBelong}" var="pb">
								<label style="padding-right:12px;"><input type="radio" name="areatypeid" onclick="CRBMaddress();" value="${pb.dictCode}" 
								<c:choose>
									<c:when test="${mz.areatypeid==pb.dictCode || pb.dictCode eq '1'}">
										checked="checked"
									</c:when>
								</c:choose> textvalue="${pb.dictName}" >${pb.dictName}</label>
							</c:forEach>
							<input type="hidden" name="areatypename" id="areatypename" value="${mz.areatypename}" /></td>
						</tr>
						<tr>
							<td class="rightTextAlign" style="line-height: 26px;"><span class="red">*</span>现住地址国标：</td>
							<td><span id="nowAddrCode"></span></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><select style="width: 142px;" name="province" id="sheng" class="easyui-combobox">
									<option></option>
							</select> 省</td>
							<td>&nbsp;</td>
							<td><select style="width: 142px;" name="city" id="shi" class="easyui-combobox">
									<option></option>
							</select> 市</td>
							<td>&nbsp;</td>
							<td><select style="width: 142px;" name="country" id="xian" class="easyui-combobox">
									<option></option>
							</select> 县（区）</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan="2">
								<select style="width: 142px;" id="xiang" class="easyui-combobox">
									<option></option>
								</select> 乡（镇、街道）
							</td>
							<td>
								<input type="text" style="width: 130px;" name="village" id="cun" value="${mz.village}" onchange="fulladdress();" /> 村
							</td>
							<td>&nbsp;</td>
							<td>
								<input type="text" style="width: 130px;" name="housenumber" value="${mz.housenumber}" id="mph" onchange="fulladdress();" /> 门牌号
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>现住详细地址：</td>
							<td colspan="5">
								<input type="text" style="width: 93%;" id="address_info" name="addr" value="${BRXX.address}" /> 
								<input type="hidden" name="addrcodevalue" id="acodevalue" value="${mz.addrcodevalue}" /> 
								<input type="hidden" name="addrcode" id="acode" value="${mz.addrcode}" />
							</td>
						</tr>
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>人群分类：</td>
							<td>
								<select style="width: 142px;" id="professionid" name="professionid" class="easyui-combobox" >
									<option></option>
									<c:forEach items="${groupClassify}" var="gc">
										<option value="${gc.dictCode}"
											<c:if test="${mz.professionid==gc.dictCode}">selected="selected"</c:if>>${gc.dictName}</option>
									</c:forEach>
								</select>
							</td>
							<td class="rightTextAlign otherClassify" style="display: none;"><span class="red">*</span>其他人群分类：</td>
							<td class="otherClassify" style="display: none;">
								<input type="text" style="width: 130px;" name="professionname" id="professionname" required="true" value="${mz.professionname}" />
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="rightTextAlign">死亡日期：</td>
							<td>
								<input type="text" class="Wdate text" readonly="readonly" id="death_time" name="deaddate" value='<fmt:formatDate value="${mz.deaddate}" type="both"/>' style="width: 130px;"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" type="both"/>'})" />
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<%-- <tr>
						<td class="rightTextAlign">备注：</td>
						<td colspan="5">
							<input type="text" id="note" name="note" style="width: 93%;" value="${mz.note}"/>
						</td>
					</tr> --%>
					</table>
				</div>

				<div class="card_cont">
					<div class="card_cont_h">
						<span class="card_c_h_text">传染病信息</span>
						<div class="card_c_h_btn">
							<a class="btn_icon" href="javascript:void(0)"
								onclick="addDisease()" title="添加传染病记录"><i
								class="icon iconfont">&#xe665;</i></a>
						</div>
					</div>
					<div class="byt_table" title="传染病信息">
						<jsp:include page="/WEB-INF/view/cards/crbtable.jsp"></jsp:include>
					</div>
				</div>

				<div class="card_cont">
					<div class="card_cont_h">
						<span class="card_c_h_text">附卡信息</span>
					</div>
					<div id="fkTabs" class="easyui-tabs" style="width: 100%;"
						title="附卡信息"></div>
				</div>

				<div class="card_cont">
					<div class="card_cont_h">
						<span class="card_c_h_text">报卡信息</span>
					</div>
					<table class="mainTable">
						<tr>
							<td class="rightTextAlign"><span class="red">*</span>上报医生：</td>
							<td>
								<div class="select_del">
									<input class="easyui-combobox" name="reportdoctorid" id="reportdoctorid" value="${mz.reportdoctorid }" style="width: 130px;" />
								</div> 
								<input type="hidden" name="reportdoctorname" id="reportdoctorname" value="${mz.reportdoctorid }" />
							</td>
							<td class="rightTextAlign"><span class="red">*</span>上报科室：</td>
							<td>
								<div class="select_del">
									<input class="easyui-combobox" name="reportdeptid" id="reportdeptid" style="width: 130px;" value="${mz.reportdeptid }" />
								</div> 
								<input type="hidden" name="reportdeptname" id="reportdeptname" value="${mz.reportdeptname }" />
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class="fillDateTime rightTextAlign" style="display: none;"><span class="red">*</span>填卡日期：</td>
							<td class="fillDateTime" style="display: none;">
								<input type="text" name="filldate" id="filldate" class="Wdate text" style="width: 130px;"
								<c:choose>
									<c:when test="${!empty mz}">
										value='<fmt:formatDate value="${mz.filldate}" pattern="yyyy-MM-dd HH:mm:ss" />'
									</c:when>
									<c:otherwise>
										value=''
									</c:otherwise>
								</c:choose>
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" type="both"/>'})" />
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="footer">
			<%@ include file="/WEB-INF/view/cards/includepages/bkOpts.jsp"%>
		</div>
	</form>
	<div id="chooseDisease">
		<div id="distool">
			<input id="diseaseKey" type="text" style="width: 100%; height: 28px;"
				class="easyui-textbox" data-options="prompt:'输入疾病编号/疾病名称/助记码检索'" />
		</div>
		<table id="diseasisTable" style="height: 400px;"></table>
	</div>
	<script type="text/javascript">
	var isXBFKExist = false;
	var isYGFKExist = false;
	var isAFPFKExist = false;
	var isFJHFKExist = false;
	$(function(){
	<c:if test="${acType eq 'hospital'}">
		$(".fillDateTime").show();
		$(".NCNPlace").show();
	</c:if>
	<c:if test="${empty mz}">
		if($("#idcard").val()){
			var result = isIdCardNo($("#idcard").val());
			if(result){
				getInfoByID();
			}else{
				$("#idtype").val("04");
				$("#idcard").validatebox();
			}
		}
	</c:if>
	<c:choose>
		<c:when test="${!empty mz}">
			setTimeout('$("#address_info").val("${mz.addr}")',1000);
		</c:when>
		<c:when test="${!empty BRXX.address}">
			setTimeout('$("#address_info").val("${BRXX.address}")',1000);
		</c:when>
	</c:choose>
		if($("#birthday").val()){
			ages($("#birthday").val());
		}
		//疾病选择框
		$("#chooseDisease").dialog({
			title: "请选择疾病 <span style='font-size:12px;font-weight:normal;color:#e25050;'>(双击确定选择)</span>",
			width: 450,
		    height: 485,
			closed: true,
		    cache: false,
		    modal: true,
		    onClose:function(){
				$("#diseaseKey").textbox("setValue","");
			}
		});
		//更新地址信息
		CRBMaddress();
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
					if(""=="${mz.reportdeptid}"){
						//默认选中第一行
						<c:choose>
							<c:when test="${RDFD eq '1'}">
								$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${user.depNo}");
							</c:when>
							<c:otherwise>
								$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${BRXX.deptCode}");
							</c:otherwise>
						</c:choose>
					}else{
						$('#reportdeptid').combogrid('grid').datagrid('selectRecord',"${mz.reportdeptid}");
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
				<c:when test="${!empty mz}">
					value:"${mz.reportdoctorid}",
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
					if(!"${mz.reportdoctorid}"){
						<c:choose>
							<c:when test="${acType eq 'hospital'}">
								$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${user.username}");
							</c:when>
							<c:otherwise>
								$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${user.docNo}");
							</c:otherwise>
						</c:choose>
					}else{
						$('#reportdoctorid').combogrid('grid').datagrid('selectRecord',"${mz.reportdoctorid}");
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
		   fulladdress();
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
		
		
	   //数据初始化
	   $("#idtypevalue").val($("#idtype").combobox("getText"));
	   $("#reporttypename").val($("#reporttypeid").combobox("getText"));
	   $("#sexname").val($("#sexid").combobox("getText"));
	   
	   if($('#professionid').combobox("getText")=='其他'){$('.otherClassify').show();}else{$('.otherClassify').hide();}
	   
	   <c:if test="${HIVBK eq 'exist'}">
		    isXBFKExist = true;
		    addTab("性病附卡","${webroot}/cdc/f_view/xbfk.shtml");
	    </c:if>
	    <c:if test="${YGBK eq 'exist'}">
		    isYGFKExist = true;
			addTab("乙肝附卡","${webroot}/cdc/f_view/ygfk.shtml");
	    </c:if>
	    <c:if test="${TBBK eq 'exist'}">
		    isFJHFKExist = true;
		    addTab("肺结核转诊/推荐单","${webroot}/cdc/f_view/fjhzzdfk.shtml");
	    </c:if>
	   <%--  <c:if test="${AFPBK eq 'exist'}">
		    isAFPFKExist = true;
			addTab("AFP附卡","${webroot}/cdc/f_view/afpfk.shtml");
	    </c:if> --%>
	    
	    <c:if test="${acType eq 'hospital'}">
			<c:if test="${!empty flag && flag!=0}">
			setTimeout('$("#NoEdit").css("height",$("#crbbk").height());',1000);
			</c:if>
		</c:if>
		<c:if test="${acType eq 'doctor'}">
			<c:if test="${!empty flag && flag!=2}">
			setTimeout('$("#NoEdit").css("height",$("#crbbk").height());',1000);
			</c:if>
		</c:if>
	    
	    
	   $("input",$("#diseaseKey").next("span")).keyup(function(){
	    	timedCount();
	    });
	   
	   //疾病table
	   $(".fillName").trigger("change");
	   
	 	//验证梅毒
		checkRPR();
	   
	    InitDsisGrid();
	    
	    <c:if test="${!empty DL}">
			<c:forEach items="${DL}" var="dl">
			//console.log(1);
				addDiseaseRow('${dl.diseaseid}','${dl.diseasename}');
			</c:forEach>
		</c:if>
	
		$("#reporttypeid").combobox({
			editable:false,
			onSelect : function(r){
				$('#reporttypename').val($("#reporttypeid").combobox("getText"));
				if($("#reporttypeid").combobox("getValue")=='2'){
					$('.dzbmtd').show();
				}else{
					$('.dzbmtd').hide();
					$('#dzbm').val('');
				}
			}
		});
		//
		$("#idtype").combobox({
			editable:false,
			onSelect : function(r){
				$('#idtypevalue').val($("#idtype").combobox("getText"));
				$('#idcard').validatebox();
			}
		});
		//
		$("#sexid").combobox({
			editable:false,
			onSelect : function(r){
				$('#sexname').val($('#sexid').combobox('getText'));
			}
		});
		//
		$("#professionid").combobox({
			editable:false,
			required:true, 
			onSelect : function(r){
				$('#professionname').val($("#professionid").combobox("getText"));
				if($("#professionid").combobox("getText")=='其他'){
					$('.otherClassify').show();
				}else{
					$('.otherClassify').hide();
				}
			}
		});
		
		
	});
	function addDisease(){
		//居中判断
		var top = document.documentElement.scrollTop||document.body.scrollTop;
		var mtop = parseInt(($(window).height()-485)/2);
		if(mtop>0){
			top += mtop;
		}
		$('#chooseDisease').window('open').window('resize',{top: top});
		queryDisease();
	}
	function InitDsisGrid(){
		$('#diseasisTable').datagrid({
			url:"${webroot}/cdc/f_json/chooseDisease.shtml",
			fitColumns:true,
			height:400,
			singleSelect:true,
			columns:[[
				{field:'diseaseid',width:80,title:'疾病编号'},
				{field:'diseasename',width:150,title:'疾病名称'},
				{field:'kindname',width:80,title:'疾病分类'},
				{field:'zjf',width:80,title:'首拼码'}
			]],
			onLoadSuccess: function (data) {},
			onDblClickRow:function(index,row){
				addDiseaseRow(row['diseaseid'],row['diseasename']);
				$("#chooseDisease").dialog("close");
				//$("input",$("#diseaseKey").next("span")).val("");
			}
		});
	}
	var rowcount = <c:if test="${empty AllDisease}">0</c:if><c:if test="${!empty AllDisease}"><c:out value="${fn:length(AllDisease)}"></c:out></c:if>;
	//梅毒相关疾病编号
	var md = "${mdDiseasis}";
	function addDiseaseRow(id,name){
		//判断ID 是否已被添加
		var rownos=$("#chosenDiseasis tbody tr[rowno]");
		if(!rownos && null!=id && ""!=id){}else{
			//遍历是否已选择该疾病
			for(var i=0;i<rownos.length;i++){
				if(id==$(rownos[i]).attr("rowno")){
					$.messager.show({ title: '提示', msg: '添加失败：该疾病已添加过，不能重复添加。' });
					return false;
				}
			}
		}
		//判断附卡是否已开启
		//性病附卡
		var xbfkDids = "${fkDiseasis}";
		if(xbfkDids.indexOf(id)>-1){
			$.messager.show({ title: '提示', msg: '附卡提示：该疾病需要填写性病附卡。' });
			isXBFKExist = true;
			addTab("性病附卡","${webroot}/cdc/f_view/xbfk.shtml");
		}else{
			isXBFKExist = false;
		}
		//乙肝附卡
		if(id=="0302"){
			$.messager.show({ title: '提示', msg: '附卡提示：该疾病需要填写乙肝相关附卡。' });
			isYGFKExist = true;
			addTab("乙肝附卡","${webroot}/cdc/f_view/ygfk.shtml");
		}
		//肺结核ZZD附卡
		var fjhfkDids = "${fjhDiseasis}";
		if(fjhfkDids.indexOf(id)>=0){
			$.messager.show({ title: '提示', msg: '附卡提示：该疾病需要填写肺结核转诊单。' });
			isFJHFKExist = true;
			addTab("肺结核转诊/推荐单","${webroot}/cdc/f_view/fjhzzdfk.shtml");
		}else{
			isFJHFKExist = false;
		}
		/* //AFP附卡
		if(id=="9825"){
			$.messager.show({ title: '提示', msg: '附卡提示：该疾病需要填写AFP相关附卡。' });
			isAFPFKExist = true;
			addTab("AFP附卡","${webroot}/cdc/f_view/afpfk.shtml");
		} */
		//添加表格数据
		var singleRow = "<tr rowno="+id+">"+
							"<td><a class='ico_del' style='margin: 3px;' title='删除' onclick=\"var id=$(this).parent().parent().attr('rowno');removeRow(id)\"></a><a class='ico_info' style='margin: 3px;' title='修改' onclick=\"var nid=$(this).parent().parent().attr('rowno');fixRow(nid);\"></a></td>"+
							"<td><span class='show_diseaseid'>"+id+"</span><input type='hidden' name='diseaseList["+rowcount+"].diseaseid' class='diseaseid' value='"+id+"'/></td>"+
							"<td><span class='show_diseasename'>"+name+"</span><input type='hidden' name='diseaseList["+rowcount+"].diseasename' class='diseasename' value='"+name+"'/></td>"+
							"<td><input type='text' name='diseaseList["+rowcount+"].startdate' readonly='readonly' class='Wdate text fbdate easyui-validatebox' style='width: 130px;' data-options='required:true' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})\" /></td>"+
							"<td><input type='text' name='diseaseList["+rowcount+"].diagnosedate' readonly='readonly' class='Wdate text zddate easyui-validatebox' style='width: 130px;' data-options='required:true' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>'})\" /></td>"+
							"<td><select id='ct1_id_"+id+"' style='width: 120px;' name='diseaseList["+rowcount+"].casetypeid' class='blfl1 easyui-combobox fillName' data-options='required:true,onChange:function(n,o){fillName(\"ct1_id_"+id+"\",\"ct1_name_"+id+"\")}' >"+
								"<option></option>"+	
							<c:forEach items="${blfl}" var="bf">
									"<option value='${bf.dictCode }'>${bf.dictName}</option>"+
								</c:forEach>
							"</select>"+
							"<input type='hidden' id='ct1_name_"+id+"' name='diseaseList["+rowcount+"].casetypename'/>"+
							"</td>"+
							"<td><select id='ct2_id_"+id+"'  style='width: 80px;' name='diseaseList["+rowcount+"].casetypeid2' class='blfl2 easyui-combobox fillName ' data-options='required:true,onChange:function(n,o){fillName(\"ct2_id_"+id+"\",\"ct2_name_"+id+"\")}' >"+
								<c:forEach items="${blfl2}" var="bf2">
									"<option value='${bf2.dictCode }'>${bf2.dictName }</option>"+
								</c:forEach>
							"</select>"+
							"<input type='hidden' id='ct2_name_"+id+"' name='diseaseList["+rowcount+"].casetypename2' />"+
							"</td>"+
							"<td><select style='width: 80px;' class='jchzzz easyui-combobox' ";
							if(isXBFKExist){
								singleRow+=" data-options='required:true' ";
							}
							singleRow+="  id='jchz_"+id+"' name='diseaseList["+rowcount+"].contactflag'>"+
									"<option></option><option value='有'>有</option><option value='无'>无</option><option value='不详'>不详</option>"+
							"</select></td>"+
							"<td><select style='width: 120px;' id='labr_"+id+"' name='diseaseList["+rowcount+"].labresultno' class='niddict fillName easyui-combobox ' data-options='onChange:function(n,o){fillName(\"labr_"+id+"\",\"labr_name_"+id+"\")}' >"+
									"<option></option>"+
								<c:forEach items="${LABR}" var="lr">
									"<option value='${lr.dictCode}'>${lr.dictName}</option>"+
								</c:forEach>
							"</select>"+
							"<input id='labr_name_"+id+"' name='diseaseList["+rowcount+"].labresult' type='hidden' />"+
							"</td>"+
							"<td><input type='text' style='width: 120px;' class='notes' name='diseaseList["+rowcount+"].diseasenotes'/></td>"+
							"<td class='rpr' style='display:none;'><input type='text' style='display:none;width: 70px;' class='rprinput' name='diseaseList["+rowcount+"].rpr' value='${ad.rpr}'/></td>"+
							"<td class='trust' style='display:none;'><input type='text' style='display:none;width: 70px;' class='trustinput' name='diseaseList["+rowcount+"].trust' value='${ad.trust}'/></td>"+
						"</tr>";
		$("#chosenDiseasis tbody").append(singleRow);
		//
		$.parser.parse($("#chosenDiseasis tbody tr[rowno='"+id+"']").eq(0));
		//
		rowcount++;
		//初始化数据
		initCellSelectData(id);
		//添加附卡
		//判断附卡是否已开启
		//性病附卡
		var xbfkDids = "${fkDiseasis}";
		if(xbfkDids.indexOf(id)>-1){
			$.messager.show({ title: '提示', msg: '附卡提示：该疾病需要填写性病附卡。' });
			isXBFKExist = true;
			addTab("性病附卡","${webroot}/cdc/f_view/xbfk.shtml?mzid="+$("#mzid").val());
		}else{
			isXBFKExist = false;
		}
		//乙肝附卡
		if(id=="0302"){
			$.messager.show({ title: '提示', msg: '附卡提示：该疾病需要填写乙肝相关附卡。' });
			isYGFKExist = true;
			addTab("乙肝附卡","${webroot}/cdc/f_view/ygfk.shtml");
		}
		//肺结核ZZD附卡
		var fjhfkDids = "${fjhDiseasis}";
		if(fjhfkDids.indexOf(id)>=0){
			$.messager.show({ title: '提示', msg: '附卡提示：该疾病需要填写肺结核转诊单。' });
			isFJHFKExist = true;
			addTab("肺结核转诊/推荐单","${webroot}/cdc/f_view/fjhzzdfk.shtml");
		}else{
			isFJHFKExist = false;
		}
		//验证梅毒
		checkRPR();
		//初始化相关验证
		$("#chosenDiseasis tbody").find(".easyui-validatebox").validatebox();
		
		setTimeout("$('#fkTabs table').find('.easyui-validatebox').validatebox()",500);
	}
	function checkRPR(){
		//遍历table
		//判断table 中是否还有梅毒，没有就将rpr列隐藏
		var mdDC = 0;
		$("#chosenDiseasis tbody tr").each(function(){
			var code = $(this).attr("rowNo");
			//如果这个是md
			if(md.indexOf(code)>-1){
				mdDC++;
				$(".rpr").show();
				$(".trust").show();
				$(this).find(".rprinput").show();
				$(this).find(".trustinput").show();
			}
		});
		//不存在梅毒相关
		if(mdDC==0){
			$(".rpr").hide();
			$(".trust").hide();
		}
	}
	function fixRow(rowNo){
		//居中判断
		var top = document.documentElement.scrollTop||document.body.scrollTop;
		var mtop = parseInt(($(window).height()-485)/2);
		if(mtop>0){
			top += mtop;
		}
		$('#chooseDisease').window('open').window('resize',{top: top});

		var act_url="${webroot}/cdc/f_json/chooseDisease.shtml";
		var tv = $("input",$("#diseaseKey").next("span")).val();
		$('#diseasisTable').datagrid({
	        url: act_url,
	        queryParams: {
	     		'keyword': (tv=="输入疾病编号/疾病名称/助记码检索"?"":tv)
	        },
	        method:"post",
	        onLoadSuccess: function (data) {},
	        onDblClickRow:function(index,row){
				$("#chooseDisease").dialog("close");
				fixDiseaseRow(rowNo,row['diseaseid'],row['diseasename']);
				//$("input",$("#diseaseKey").next("span")).val("");
			}
	    });
			
	}
	function fixDiseaseRow(orgdiseaseid,newid,newname){
		var rownos=$("#chosenDiseasis tbody tr[rowno]");
		if(!rownos && null!=newid && ""!=newid){}else{
			//遍历是否已选择该疾病
			for(var i=0;i<rownos.length;i++){
				if(newid==$(rownos[i]).attr("rowno")){
					$.messager.show({ title: '提示', msg: '修改失败：该疾病已经存在，不能重复添加。' });
					return;
				}
			}
		}
		
		var delCard = "";
		var tip = "";
		var xbfkDids = "${fkDiseasis}";
		if(xbfkDids.indexOf(orgdiseaseid)>-1){
			tip+="此操作将会移除性病相关附卡信息。";
			delCard="性病附卡";
		}
		//乙肝附卡
		if(orgdiseaseid=="0302"){
			tip+="此操作将会移除乙肝相关附卡信息。";
			delCard="乙肝附卡";
		}
		//肺结核ZZD附卡
		var fjhfkDids = "${fjhDiseasis}";
		if(fjhfkDids.indexOf(orgdiseaseid)>=0){
			tip+="此操作将会移除肺结核相关附卡信息。";
			delCard="肺结核转诊/推荐单";
		}
		/* //AFP附卡
		if(rowNo=="9825"){
			tip+="此操作将会移除AFP相关附卡信息。";
			delCard="AFP附卡";
		} */
		if(delCard=="性病附卡"){
			isXBPFKExist = false;
		}else if(delCard=="乙肝附卡"){
			isYGFKExist = false;
		}else if(delCard=="AFP附卡"){
			isAFPFKExist = false;
		}else if(delCard=="肺结核转诊/推荐单"){
			isFJHFKExist = false;
		}
					
		//如果有附卡，则移除
		if($('#fkTabs').tabs('exists', delCard)){
			$('#fkTabs').tabs('close',delCard);
		}
		
		var needfixrow = $("#chosenDiseasis tbody").find("tr[rowno='"+orgdiseaseid+"']").eq(0);
		$(needfixrow).find(".show_diseaseid").eq(0).html(newid);
		$(needfixrow).find(".diseaseid").eq(0).val(newid);
		$(needfixrow).find(".show_diseasename").eq(0).html(newname);
		$(needfixrow).find(".diseasename").eq(0).val(newname);
		$(needfixrow).attr("rowno",newid);
		
		//判断附卡是否已开启
		//性病附卡
		var xbfkDids = "${fkDiseasis}";
		if(xbfkDids.indexOf(newid)>-1){
			$.messager.show({ title: '提示', msg: '附卡提示：该疾病需要填写性病附卡。' });
			isXBFKExist = true;
			addTab("性病附卡","${webroot}/cdc/f_view/xbfk.shtml");
		}else{
			isXBFKExist = false;
		}
		//乙肝附卡
		if(newid=="0302"){
			$.messager.show({ title: '提示', msg: '附卡提示：该疾病需要填写乙肝相关附卡。' });
			isYGFKExist = true;
			addTab("乙肝附卡","${webroot}/cdc/f_view/ygfk.shtml");
		}
		//肺结核ZZD附卡
		var fjhfkDids = "${fjhDiseasis}";
		if(fjhfkDids.indexOf(newid)>=0){
			$.messager.show({ title: '提示', msg: '附卡提示：该疾病需要填写肺结核转诊单。' });
			isFJHFKExist = true;
			addTab("肺结核转诊/推荐单","${webroot}/cdc/f_view/fjhzzdfk.shtml");
		}else{
			isFJHFKExist = false;
		}
		//验证梅毒
		checkRPR();
		
	}
	function removeRow(rowNo){
		var delCard = "";
		var tip = "确定删除此行？";
		var xbfkDids = "${fkDiseasis}";
		if(xbfkDids.indexOf(rowNo)>-1){
			tip+="此操作将会移除性病相关附卡信息。";
			delCard="性病附卡";
		}
		//肺结核ZZD附卡
		var fjhfkDids = "${fjhDiseasis}";
		if(fjhfkDids.indexOf(rowNo) >= 0){
			tip+="此操作将会移除肺结核相关附卡信息。";
			delCard="肺结核转诊/推荐单";
		}
		//乙肝附卡
		if(rowNo=="0302"){
			tip+="此操作将会移除乙肝相关附卡信息。";
			delCard="乙肝附卡";
		}
		/* //AFP附卡
		if(rowNo=="9825"){
			tip+="此操作将会移除AFP相关附卡信息。";
			delCard="AFP附卡";
		} */
		$.messager.confirm("提示", tip, function (r) {
			if(r){
				var currow = $("#chosenDiseasis tbody").find("tr[rowNo='"+rowNo+"']").eq(0);
				currow.remove();
				if(delCard=="性病附卡"){
					isXBPFKExist = false;
				}else if(delCard=="乙肝附卡"){
					isYGFKExist = false;
				}else if(delCard=="AFP附卡"){
					isAFPFKExist = false;
				}else if(delCard=="肺结核转诊/推荐单"){
					isFJHFKExist = false;
				}
				//如果有附卡，则移除
				if($('#fkTabs').tabs('exists', delCard)){
					var fjhfkDids = "${fjhDiseasis}";
					var list=fjhfkDids.split(",");
					var isExist = false;
					$("#chosenDiseasis tbody tr").each(function(){
						var temp = $(this).attr("rowNo");
						if(fjhfkDids.indexOf(temp)>-1){
							isExist = true;
						}
						
					});
					if(!isExist){
						$('#fkTabs').tabs('close',delCard);
					}
				}
				//验证梅毒
				checkRPR();
			}
		});
	}
	function queryDisease(){
		var act_url="${webroot}/cdc/f_json/chooseDisease.shtml";
		var tv = $("input",$("#diseaseKey").next("span")).val();
		$('#diseasisTable').datagrid({
	        url: act_url,
	        queryParams: {
	     		'keyword': (tv=="输入疾病编号/疾病名称/助记码检索"?"":tv)
	        },
	        method:"post",
	        onLoadSuccess: function (data) {},
	        onDblClickRow:function(index,row){
				addDiseaseRow(row['diseaseid'],row['diseasename']);
				$("#chooseDisease").dialog("close");
				//$("input",$("#diseaseKey").next("span")).val("");
			}
	    });
	}
	function addTab(title, url){
		if ($('#fkTabs').tabs('exists', title)){
			$('#fkTabs').tabs('select', title);
		} else {
			var content = $('<div></div>');
			var msid = $("#masterid").val();
			if(msid){
				url+="?masterid="+msid;
			}
			content.load(url);
			$('#fkTabs').tabs('add',{
				title:title,
				content:content,
				closable:false
			});
			//$.parser.parse(content);
		}
	}
	function CRBMaddress(){
		$("#sheng").combobox({value:"",disabled:false});
		$("#shi").combobox({value:"",disabled:false});
		$("#xian").combobox({value:"",disabled:false});
		$("#xiang").combobox({value:"",disabled:false});
		var val=$(":radio[name='areatypeid']:checked").val();
		$("#areatypename").val($(":radio[name='areatypeid']:checked").attr("textvalue"));
		//var val=$("#belong option:selected").val();
		//$("#areatypename").val($("#belong option:selected").text());
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
			fulladdress();
		});
	}
	function fulladdress(){
	 	//国标编码
		$("#acode").val($("#xiang").combobox("getValue"));
	 	//用于显示的地址
	 	$("#nowAddrCode").html($("#xiang").combobox("getValue"));
		//国标地址
		$("#acodevalue").val($("#sheng").combobox("getText")+$("#shi").combobox("getText")+$("#xian").combobox("getText")+$("#xiang").combobox("getText"));
		//详细地址
		$("#address_info").val($("#sheng").combobox("getText")+$("#shi").combobox("getText")+$("#xian").combobox("getText")+$("#xiang").combobox("getText")+$("#cun").val()+$("#mph").val());
	}
	function getInfoByID(){
		var idtype = $("#idtype").combobox("getValue");
		var idcard = $("#idcard").val();
		if(idtype=='01' && idcard.length==18){
			//前17位是否是纯数字
			if(isNaN(idcard.substring(0,17))){
				$.messager.show({ title: '提示', msg: '提取信息失败：身份证号码不合法。' });
				return false;
			}
			//性别   奇数为男，偶数为女
			if(idcard.substring(16,17)%2==0){
				$("#sexid").combobox("setValue","2");
			}else{
				$("#sexid").combobox("setValue","1");
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
	function report(){
		var result = $("#crbbk").form('validate'); 
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
							url:"${webroot}/cdc/f_json/saveCards.shtml",
							data:$("#crbbk").serialize(),
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
	function fillName(ele,oid){
		var c= $("#"+ele).combobox("getText");
		$("#"+oid).val(c);
	}
	function initCellSelectData(id){
		$("#ct1_name_"+id).val($("#ct1_id_"+id).combobox('getText'));
		$("#ct2_name_"+id).val($("#ct2_id_"+id).combobox('getText'));
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
				$("#NoEdit").css("height",$("#crbbk").height());
				$("#upload-main").hide();
			}
		});
	}
	function checkForm(){
		if(!$(".dzbmtd").is(":hidden")){
			if(!$("#dzbm").val()){
				$.messager.show({ title: '提示', msg: '请填写订正病名！' });
				return false;
			}
		}
		if($("#age").val()){
			if($("#ageunit").combobox("getValue")=='岁' && $("#age").val()>120){
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
		var v = $("#professionid").combobox("getValue");
		if(!$("#unit").val()){
			if(v=='01'){
				$.messager.show({ title: '提示', msg: '患者是幼托儿童，患者工作单位需要填写，请填写患者所在的托幼机构!' });
				return false;
			}else if(v=='03'){
				$.messager.show({ title: '提示', msg: '患者是学生，患者工作单位需要填写，请填写患者所在的学校!' });
				return false;
			}else if(v=='04'){
				$.messager.show({ title: '提示', msg: '患者是教师，患者工作单位需要填写!' });
				return false;
			}else if(v=='09'){
				$.messager.show({ title: '提示', msg: '患者是医务人员，患者工作单位需要填写!' });
				return false;
			}else if(v=='16'){
				$.messager.show({ title: '提示', msg: '患者是工人，患者工作单位需要填写，请填写患者所在的工作单位!' });
				return false;
			}else if(v=='17'){
				$.messager.show({ title: '提示', msg: '患者是民工，患者工作单位需要填写，请填写患者所工作的工地或建筑队!' });
				return false;
			}else if(v=='22'){
				$.messager.show({ title: '提示', msg: '患者是干部职员，患者工作单位需要填写，请填写患者的工作单位!' });
				return false;
			}
		}
		
		if($("#death_time").val()){
			var y = new Date($("#death_time").val().replace("-", "/").replace("-", "/"));
			try{
				$("#chosenDiseasis tbody tr .fbdate").each(function(){
					var x = new Date($(this).val().replace("-", "/").replace("-", "/"));
					if(y<x){
						$.messager.show({ title: '提示', msg: '死亡日期不能在发病日期之前！' });
						throw "X";
					}
				});
			}catch(e){
				return false;
			}
		}
		if($("#birthday").val()){
			var y = new Date($("#birthday").val().replace("-", "/").replace("-", "/"));
			try{
				$("#chosenDiseasis tbody tr .fbdate").each(function(){
					var x = new Date($(this).val().replace("-", "/").replace("-", "/"));
					if(x<y){
						$.messager.show({ title: '提示', msg: '出生日期不能在发病日期之后！' });
						throw "X";
					}
				});
			}catch(e){
				return false;
			}
		}
		if(!$("#professionname").is(":hidden") && !$("#professionname").val()){
			$.messager.show({ title: '提示', msg: '请填写其他人群分类。' });
			return false;
		}
		var addr = $("#address_info").val();
		if(!addr){
			$.messager.show({ title: '提示', msg: '请填写完整地址信息。' });
			return false;
		}
		var len = $("#chosenDiseasis tbody tr").length;
		if(len<=0){
				$.messager.show({ title: '提示', msg: '请先选择疾病信息后再上报。' });
				return false;
		}else{
			//验证日期
			try{
				$("#chosenDiseasis tbody tr").each(function(index){
					if(!$(this).find(".fbdate").val() || !$(this).find(".zddate").val()){
						$.messager.show({ title: '提示', msg: '请填写疾病发病日期和诊断日期。' });
						throw "X";
					}
					var startTime=$(this).find(".fbdate").val();  
				    var diagTime=$(this).find(".zddate").val();  
				    var fbdate=new Date(startTime.replace("-", "/").replace("-", "/"));  
				    var zddate=new Date(diagTime.replace("-", "/").replace("-", "/"));
				    if(zddate<fbdate){  
				    	$.messager.show({ title: "提示", msg: "疾病信息第"+(index+1)+"行，发病日期不能在诊断日期之后。" });
						throw "X";
				    }
				    
			<c:if test="${acType eq 'hospital'}">
				    var reportTime = $("#filldate").val();
				    if(reportTime){
				    	var rpdate = new Date(reportTime.replace("-", "/").replace("-", "/"));  
				    	if(rpdate<zddate){
				    		$.messager.show({ title: "提示", msg: "疾病信息第"+(index+1)+"行，诊断日期不能在上报日期之后。" });
							throw "X";
				    	}
				    }
			</c:if>
					var diseaseId = $(this).find(".diseaseid").val();
					var blfl2 =  $(this).find(".blfl2").eq(0).combobox("getValue");
			<c:if test="${ifcheckBLFL2==1}">
					//验证病例分类2
					if(diseaseId=="3100" ||diseaseId=="0302" ||diseaseId=="0303"){
					  if(blfl2=="0"){
						  $.messager.show({ title: '提示', msg: "疾病信息第"+(index+1)+"行，当疾病选择了乙肝、丙肝或血吸虫病时，病例分类2必须选择急性或慢性!" });
					  	 throw "X";
					  }
				    }
			</c:if>
					/* if(blfl2=="1" || blfl2=="2"){
					   if(diseaseId=="3100" ||diseaseId=="0302" ||diseaseId=="0303"){}else{
						   $.messager.show({ title: '提示', msg: "疾病信息第"+(index+1)+"行，当疾病选择了乙肝、丙肝或血吸虫病时，病例分类2必须选择急性或慢性!" });
						   throw "X";
					   }
				    } */
					//验证选择慢性乙肝或慢性丙肝
					if(diseaseId=="0303" ||diseaseId=="0302" ){
						if(blfl2=="2"){
						   var fbh=new Date(fbdate+180);
						   if(zddate<fbh){
							   $.messager.show({ title: '提示', msg: "疾病信息第"+(index+1)+"行，当疾病为慢性乙肝或慢性丙肝时，诊断日期必须大于等于发病日期6个月" });
							   throw "X";
						   }
						}
					}
					//验证病例分类1
					var blfl1 =  $(this).find(".blfl1").eq(0).combobox("getValue");
					if(blfl1=="5"){
					   if(diseaseId!="0601"){
						   $.messager.show({ title: '提示', msg: "疾病信息第"+(index+1)+"行，当病种是HIV时,病例分类才能选择阳性检测！" });
						   throw "X";
					   }
				    }
					if(diseaseId=="0700" ||diseaseId=="0800"){
					   if(blfl1=="2" ||blfl1=="3"){}else{
						   $.messager.show({ title: '提示', msg: "疾病信息第"+(index+1)+"行，当病种是梅毒、淋病时，病例分类只能为确诊病例和疑似病例！" });
						   throw "X";
					   }
				    }
					if(diseaseId=="9802" || diseaseId=="9803"){
					   if(blfl1=="1" ||blfl1=="2"){}else{
						   $.messager.show({ title: '提示', msg: "疾病信息第"+(index+1)+"行，当病种是尖锐湿疣、生殖器疱疹时，病例分类只能为确诊病例和临床诊断病例！" });
						   throw "X";
					   }
				    }
					if(diseaseId=="9804"){
					   if(blfl1=="2" ||blfl1=="4"){}else{
						   $.messager.show({ title: '提示', msg: "疾病信息第"+(index+1)+"行，当病种是生殖道沙眼衣原体感染时，病例分类只能为确诊病例和病原携带者！" });
						   throw "X";
					   }
				    }
					if(diseaseId=="4000" && blfl1=="2"){
						var niddict = $(this).find(".niddict").eq(0).combobox("getValue");
					   if(!niddict){
						   $.messager.show({ title: '提示', msg: "疾病信息第"+(index+1)+"行，当选择了手足口病，病例分类为确诊病例时，需要填写实验室结果！" });
						   throw "X";
					   }
				    }
					
				<c:if test="${IRPRN==1}">	
					//梅毒验证rpr
					var ri = $(this).find(".rprinput").eq(0);
					var rprv = $(this).find(".rprinput").val();
					if(ri.length>0 && !$(ri).is(":hidden")){
						if(!$(ri).val()){
							$.messager.show({ title: '提示', msg: "疾病信息第"+(index+1)+"行，当选择了梅毒相关疾病时，RPR值必须填写！" });
						    throw "X";
						}
					}
					var ti = $(this).find(".trustinput").eq(0);
					if(ti.length>0 && !$(ti).is(":hidden")){
						if(!$(ti).val()){
							$.messager.show({ title: '提示', msg: "疾病信息第"+(index+1)+"行，当选择了梅毒相关疾病时，TRUST值必须填写！" });
						    throw "X";
						}
					}
				</c:if>
				});
				
			}catch(e){
				return false;
			}
		}
		//判断性病附卡是否存在
		if($("#affirmunit").length>0){
			/* //婚姻状况
			if(!$("#marriageid option:selected").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：婚姻状况。' });
				return false;
			}
			//民族
			if(!$("#nationid option:selected").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：民族。' });
				return false;
			}
			//文化程度
			if(!$("#educationid option:selected").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：文化程度。' });
				return false;
			} */
			//接触史
			var t = $("#contacthistoryid").val();
			if($("#contacthistoryid").length>0 && !t){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：接触史。' });
				return false;
			}
			if(!$("#injectcount").is(":hidden") && !$("#injectcount").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：注射毒品史。'});
				return false;
			}
			if(!$("#oppositesexcount").is(":hidden") && !$("#oppositesexcount").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：非婚异性性接触史。'});
				return false;
			}
			if(!$("#urningcount").is(":hidden") && !$("#urningcount").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：男男性行为史。'});
				return false;
			}
			if(!$("#contacthistoryother").is(":hidden") && !$("#contacthistoryother").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：其他接触史。'});
				return false;
			}
			/* if(!$("#infectionid option:selected").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：最有可能感染途径。'});
				return false;
			} */
			if(!$("#infectionother").is(":hidden") && !$("#infectionother").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：其他最有可能感染途径。'});
				return false;
			}
			/* if(!$("#samplesourceid option:selected").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：样本来源。'});
				return false;
			} */
			if(!$("#samplesourceother").is(":hidden") && !$("#samplesourceother").val()){
				$.messager.show({ title: '提示', msg: '请填写性病附卡：其他样本来源。'});
				return false;
			}
			
		}
		
	<c:if test="${YGBT==1}">
		//判断乙肝附卡是否存在
		if($("#firstYear").length>0){
			if(!$("input[name='yg.hbsag']:checked").val()){
				$.messager.show({ title: '提示', msg: '请填写乙肝附卡：HBsAg阳性时间。'});
				return false;
			}
			if(!$("input[name='yg.firstUnknown']:checked").val()){
				//没勾选，验证年月
				if(!$("#firstYear").val() || !$("#firstMonth").val()){
					$.messager.show({ title: '提示', msg: '请填写乙肝附卡：首次出现乙肝症状和体征时间。'});
					return false;
				}
				if($("#firstYear").val().length!=4 || $("#firstYear").val()>new Date().getFullYear()){
					$.messager.show({ title: '提示', msg: '乙肝附卡：首次出现乙肝症状和体征时间，年份不合法。'});
					return false;
				}
				if($("#firstMonth").val().length>2 || $("#firstMonth").val()>(new Date().getMonth()+1)){
					$.messager.show({ title: '提示', msg: '乙肝附卡：首次出现乙肝症状和体征时间，月份不合法。'});
					return false;
				}
			}
			if(!$("#alt").val()){
				$.messager.show({ title: '提示', msg: '请填写乙肝附卡：本次ALT。'});
				return false;
			}
			if(!$("input[name='yg.hbc']:checked").val()){
				$.messager.show({ title: '提示', msg: '请填写乙肝附卡：抗-HBc IgM 1:1000检测结果。'});
				return false;
			}
			if(!$("input[name='yg.liverPuncture']:checked").val()){
				$.messager.show({ title: '提示', msg: '请填写乙肝附卡：肝穿检测结果。'});
				return false;
			}
			if(!$("input[name='yg.decubation']:checked").val()){
				$.messager.show({ title: '提示', msg: '请填写乙肝附卡：恢复期血清HBsAg阴转,抗HBs阳转。'});
				return false;
			}
		}
	</c:if>	
	//判断乙肝附卡是否存在
	if($("#zzdw").length>0){
		if(!$("input[name='fjh.referralReason']:checked").val()){
			$.messager.show({ title: '提示', msg: '请选择原因。'});
			return false;
		}
	}
		if(!$("#reportdoctorid").combogrid("getValue") || !$("#reportdoctorname").val()){
			$.messager.show({ title: '提示', msg: '请从下拉列表中选择上报医生。'});
			return false;
		}
		if(!$("#reportdeptid").combogrid("getValue") || !$("#reportdeptname").val()){
			$.messager.show({ title: '提示', msg: '请从下拉列表中选择上报科室。'});
			return false;
		}
		return true;
	}
	var t;
	function timedCount() {
		if (t) {
		clearTimeout(t);
		}
		t = setTimeout(submitEvent, 1000);
	}
	function submitEvent() {
		queryDisease();
	}
	
	function audit(bktype,msid){
		//触发保存，验证表单数据是否符合规范
		var result = $("#crbbk").form('validate'); 
		if(result){
			if(checkForm()){
				$.messager.confirm("提示", "确认审核该报卡？", function (r) {
					if(r){
						$.ajax({
								url:"${webroot}/cdc/f_json/saveCards.shtml",
								data:$("#crbbk").serialize(),
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