<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	<form id="diseaseEditForm" method="post">
		<input type="hidden" name="isadd" id="isadd" <c:if test="${empty CSDC }">value="1"</c:if> />
		<table style="width:580px;padding-top:5px;">
			<tr style="line-height:30px;">
				<td style="text-align: right;"><font color="red">*</font>疾病编号：</td>
				<td><input type="text" id="diseaseid" name="diseaseid" class="easyui-validatebox" required="true" readonly="readonly"
				<c:choose>
					<c:when test="${!empty CSDC}">
						value="${CSDC.diseaseid }"
					</c:when>
					<c:otherwise>
						value="${MaxDid}"
					</c:otherwise>
				</c:choose> /></td>
			</tr>
			<tr style="line-height:30px;">
				<td style="text-align: right;"><font color="red">*</font>疾病名称：</td>
				<td><input type="text" id="diseasename" name="diseasename" class="easyui-validatebox" required="true" value="${CSDC.diseasename }" onblur='if($(this).val()){$("#zjf").val($(this).pinyinFirstLower().toUpperCase())};'/></td>
			</tr>
			<tr style="line-height:30px;">
				<td style="text-align: right;">首拼码：</td>
				<td><input type="text" id="zjf" name="zjf" value="${CSDC.zjf }" /></td>
			</tr>
			<tr style="line-height:30px;">
				<td style="text-align: right;"><font color="red">*</font>分类：</td>
				<td>
					<select id="kindid" name="kindid" class="easyui-combobox" data-options="required:true" style="width:152px;" >
						<c:forEach items="${classify}" var="c">
							<option value="${c.kindid}" <c:if test="${CSDC.kindid eq c.kindid }">selected="selected"</c:if> >${c.kindname}</option>
						</c:forEach>
						<%-- <option value="J" <c:if test="${CSDC.kindid eq 'J' }">selected="selected"</c:if> >甲类</option>
						<option value="Y" <c:if test="${CSDC.kindid eq 'Y' }">selected="selected"</c:if> >乙类</option>
						<option value="B" <c:if test="${CSDC.kindid eq 'B' }">selected="selected"</c:if> >丙类</option>
						<option value="Q" <c:if test="${CSDC.kindid eq 'Q' }">selected="selected"</c:if> >其他</option> --%>
					</select>
					<input type="hidden" id="kindname" name="kindname" value="${CSDC.kindname }"/>
				</td>
			</tr>
			<tr style="line-height:30px;display: none;">
				<td style="text-align: right;"><font color="red">*</font>ICD10</td>
				<td><input type="text" id="icd10" name="icd10" <c:choose><c:when test="${!empty CSDC}">value="${CSDC.icd10}"</c:when><c:otherwise>value="NULL"</c:otherwise></c:choose> /></td>
			</tr>
			<tr style="line-height:30px;">
				<td style="text-align: right;">父类：</td>
				<td>
					<select id="parentid" name="parentid" class="easyui-combobox" style="width:152px;" data-options="onSelect:function(r){if(r.value==$('#diseaseid').val()){$('#parentid').combobox('clear');}}">
						<option value="">--请选择--</option>	
						<c:forEach items="${DPs}" var="dps">
							<option value="${dps.diseaseid }" >${dps.diseasename }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr style="line-height:30px;">
				<td style="text-align: right;"><font color="red">*</font>诊断到报告法定间隔（小时）：</td>
				<td><input type="text" id="scopetime" name="scopetime" class="easyui-validatebox" required="true" value="${CSDC.scopetime }" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
			</tr>
			<tr style="line-height:30px;">
				<td style="text-align: right;"><font color="red">*</font>重卡验证周期（月）：</td>
				<td><input type="text" id="repeatCycle" name="repeatCycle" class="easyui-validatebox" required="true" value="${CSDC.repeatCycle }" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>填（0）表示每次发现都需上报</td>
			</tr>
			<tr style="line-height:25px;display: none;">
				<td></td>
				<td style="text-align: left;"><label><input type="checkbox" id="isintestinal" name="isintestinal" value="1" <c:if test="${CSDC.isintestinal==1 }">checked="cehecked"</c:if> />是否肠道传染病</label></td>
			</tr>
			<tr style="line-height:25px;display: none;">
				<td></td>
				<td style="text-align: left;"><label><input type="checkbox" id="isrespiratory" name="isrespiratory" value="1" <c:if test="${CSDC.isrespiratory==1 }">checked="cehecked"</c:if> />是否呼吸道传染病</label></td>
			</tr>
			<tr style="line-height:25px;display: none;">
				<td></td>
				<td style="text-align: left;"><label><input type="checkbox" id="isnatural" name="isnatural" value="1" <c:if test="${CSDC.isnatural==1 }">checked="cehecked"</c:if>/>是否自然疫源及虫媒传染病</label></td>
			</tr>
			<tr style="line-height:25px;display: none;">
				<td></td>
				<td style="text-align: left;"><label><input type="checkbox" id="ishemic" name="ishemic" value="1" <c:if test="${CSDC.ishemic==1 }">checked="cehecked"</c:if> />是否血源传播传染病</label></td>
			</tr>
			<tr style="line-height:25px;display: none;">
				<td></td>
				<td style="text-align: left;"><label><input type="checkbox" id="issexspread" name="issexspread" value="1" <c:if test="${CSDC.issexspread==1 }">checked="cehecked"</c:if> />是否性传播传染病</label></td>
			</tr>
			<tr style="line-height:25px;">
				<td></td>
				<td style="text-align: left;"><label><input type="checkbox" id="sexcard" name="sexcard" value="1" <c:if test="${CSDC.sexcard==1 }">checked="cehecked"</c:if> />是否需要填写性病附卡</label></td>
			</tr>
			<tr style="line-height:25px;">
				<td></td>
				<td style="text-align: left;"><label><input type="checkbox" id="caninput" name="caninput" onclick="clearFatherLevel();" value="1" <c:if test="${empty CSDC || CSDC.caninput==1 }">checked="cehecked"</c:if> />是否允许上报疾病</label></td>
			</tr>
		</table>
		<div class="footer dialog_footer">
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="diseaseEditFormSubmitBtn" onclick="checkForm();" class="no_ico"><span>确&nbsp;认</span></a>
				</div>			
			</div>
		</div>
	</form>
	<script type="text/javascript">
	
	$(document).ready(function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'diseaseEditForm',
				url : '${webroot}/cdc/f_json/saveDisease.shtml',
				subbtn : 'diseaseEditFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({title : '提示',msg : '操作成功！'});
						var parentObject = parent.Comm.getObjectCache();
						parentObject.query();
						Comm.dialogClose('${param.dialogId}');
					} else if (json.result === 'error') {
						$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						$.messager.show({title : '提示',msg : json.msg});
					}
				}
			});
			
			$("#parentid").combobox({
				value:"${CSDC.parentid}"
			});
			
			$("#kindname").val($("#kindid").combobox("getText"));
			
			$("#kindid").combobox({
				onChange:function(nv,ov){
					$("#kindname").val($(this).combobox("getText"));
				}
			});
			
		}, 100);
	});
	function checkForm(){
		if(!$("#diseaseid").val()){
			$.messager.show({ title: '提示', msg: '请填写疾病编号！' });
			return;
		}else if(!$("#diseasename").val()){
			$.messager.show({ title: '提示', msg: '请填写疾病名称！' });
			return;
		}else if(!$("#scopetime").val()){
			$.messager.show({ title: '提示', msg: '请填写诊断到报告法定间隔（小时）！' });
			return;
		}else if(!$("#repeatCycle").val()){
			$.messager.show({ title: '提示', msg: '请填写重卡验证周期（月）！' });
			return;
		}else{
			$('#diseaseEditForm').submit();
		}
	}
	function clearFatherLevel(){
		if(!$("#caninput").is(":checked")){
			$("#parentid").combobox("clear").combobox("disable");
		}else{
			$("#parentid").combobox("enable");
		}
	}
	</script>
