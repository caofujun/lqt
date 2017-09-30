<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="mainTable">
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign" width="200px"><c:if test="${YGBT==1}"><span class="red">*</span></c:if>HBsAg阳性时间：</td>
		<td width="200px">
			<label><input type="radio" name="yg.hbsag" value="1" <c:if test="${YGFK.hbsag=='1'}">checked="checked"</c:if> />&gt;6个月</label>
		</td>
		<td width="200px">
			<label><input type="radio" name="yg.hbsag" value="2" <c:if test="${YGFK.hbsag=='2'}">checked="checked"</c:if> />6个月内由阴性转为阳性</label>
		</td>
		<td width="200px">
			<label><input type="radio" name="yg.hbsag" value="3" <c:if test="${YGFK.hbsag=='3'}">checked="checked"</c:if> />既往未检测或结果不详</label>
		</td>
	</tr>
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign"><c:if test="${YGBT==1}"><span class="red">*</span></c:if>首次出现乙肝症状和体征时间：</td>
		<td colspan="3">
			<input type="text" style="width: 60px;" name="yg.firstYear" id="firstYear" value="${YGFK.firstYear}" onkeyup="value=value.replace(/[^\d]/g,'')" />年<input type="text" style="width: 60px;" name="yg.firstMonth" id="firstMonth" value="${YGFK.firstMonth}" onkeyup="value=value.replace(/[^\d]/g,'')" />月
			<label><input type="checkbox" value="1" name="yg.firstUnknown" id="firstUnknown" <c:if test="${YGFK.firstUnknown == 1}"> checked="checked"</c:if> onclick="isUnknown(this);" />不详</label>
		</td>
	</tr>
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign"><c:if test="${YGBT==1}"><span class="red">*</span></c:if>本次ALT：</td>
		<td colspan="3">
			<input type="text" style="width: 130px;" name="yg.alt" id="alt" value="${YGFK.alt}" />U/L
		</td>
	</tr>
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign"><c:if test="${YGBT==1}"><span class="red">*</span></c:if>抗-HBc IgM 1:1000检测结果：</td>
		<td>
			<label><input type="radio" value="阳性" name="yg.hbc" <c:if test="${YGFK.hbc eq '阳性'}">checked="checked"</c:if> />阳性</label>
		</td>
		<td>
			<label><input type="radio" value="阴性" name="yg.hbc" <c:if test="${YGFK.hbc eq '阴性'}">checked="checked"</c:if>/>阴性</label>
		</td>
		<td>
			<label><input type="radio" value="未测" name="yg.hbc" <c:if test="${YGFK.hbc eq '未测'}">checked="checked"</c:if>/>未测</label>
		</td>
	</tr>
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign"><c:if test="${YGBT==1}"><span class="red">*</span></c:if>肝穿检测结果：</td>
		<td>
			<label><input type="radio" value="急性病变" name="yg.liverPuncture" <c:if test="${YGFK.liverPuncture eq '急性病变'}">checked="checked"</c:if> />急性病变</label>
		</td>
		<td>
			<label><input type="radio" value="慢性病变" name="yg.liverPuncture" <c:if test="${YGFK.liverPuncture eq '慢性病变'}">checked="checked"</c:if> />慢性病变</label>
		</td>
		<td>
			<label><input type="radio" value="未测" name="yg.liverPuncture" <c:if test="${YGFK.liverPuncture eq '未测'}">checked="checked"</c:if> />未测</label>
		</td>
	</tr>
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign"><c:if test="${YGBT==1}"><span class="red">*</span></c:if>恢复期血清HBsAg阴转,抗HBs阳转：</td>
		<td>
			<label><input type="radio" value="是" name="yg.decubation" <c:if test="${YGFK.decubation eq '是'}">checked="checked"</c:if> />是</label>
		</td>
		<td>
			<label><input type="radio" value="否" name="yg.decubation" <c:if test="${YGFK.decubation eq '否'}">checked="checked"</c:if>/>否</label>
		</td>
		<td>
			<label><input type="radio" value="未测" name="yg.decubation" <c:if test="${YGFK.decubation eq '未测'}">checked="checked"</c:if>/>未测</label>
		</td>
	</tr>
</table>
<script>
	function isUnknown(ele){
		if($(ele).is(":checked")){
			//禁用年月
			$("#firstYear").val("").attr("readonly","readonly");
			$("#firstMonth").val("").attr("readonly","readonly");
		}else{
			$("#firstYear").removeAttr("readonly");
			$("#firstMonth").removeAttr("readonly");
		}
	}
</script>