<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<table class="mainTable">
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign" width='100px'><span class="red">*</span>户籍类型：</td>
		<td>
			<label><input type="radio" name="fjh.register_type" value="本地" />本地</label>
		</td>
		<td>
			<label><input type="radio" name="fjh.register_type" value="外地" />外地</label>
		</td>
		<td style="line-height: 25px;" class="rightTextAlign"><span class="red">*</span>患者来源：</td>
		<td colspan="2">
			<select id="patientSource" name="patientSource" >
				<option></option>
				<c:forEach items="${patientSourceList}" var="psl">
					<option value="${psl.dictCode}" <c:if test="${FJHFK.patientSource==psl.dictCode}">selected="selected"</c:if> >${psl.dictName}</option>
				</c:forEach>
			</select>
			<input type="text" id="patientSourceOther" name="patientSourceOther" style="display: none;" value="${FJHFK.patientSourceOther}" />
		</td>
	</tr>
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign" width='100px'><span class="red">*</span>症状：</td>
		<td>
			<label><input type="checkbox" value="咳嗽咳痰" id="" name="" />咳嗽咳痰</label>
		</td>
	</tr>
</table>
<script>
	$(function(){
	});
</script>