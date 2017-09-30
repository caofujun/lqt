<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="mainTable">
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign" width='146px'><span class="red">*</span>原因：</td>
		<td colspan="5">
			<label style="margin-right:20px; "><input type="radio" name="fjh.referralReason" value="1"  <c:if test="${fjh.referralReason=='1'}">checked="checked"</c:if>/>可疑肺结核症状</label>
			<label style="margin-right:20px; "><input type="radio" name="fjh.referralReason" value="2"  <c:if test="${fjh.referralReason=='2'}">checked="checked"</c:if>/>肺结核或疑似患者</label>
			<label><input type="radio" name="fjh.referralReason" value="3"   <c:if test="${fjh.referralReason=='3'}">checked="checked"</c:if> />结核性胸膜炎或疑似患者</label>
		</td>
	</tr>
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign">出院患者出院日期：</td>
		<td>
			<input type="text" style="width: 140px;" name="fjh.outAt" value='<fmt:formatDate value="${fjh.outAt}" pattern="yyyy-MM-dd HH:mm:ss"/>'
			  class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
		</td>
		<td style="line-height: 25px;" class="rightTextAlign">单位联系电话：</td>
		<td>
			<input type="text" name="fjh.unitTel" value="${fjh.unitTel}" />
		</td>
	</tr>
	<tr>
		<td style="line-height: 25px;" class="rightTextAlign"><span class="red">*</span>转诊/推荐单位：</td>
		<td>
			<input type="text" name="fjh.referencesUnit" id="zzdw" value="${fjh.referencesUnit}" required="true" class="easyui-validatebox" />
		</td>
		<td style="line-height: 25px;" class="rightTextAlign"><span class="red">*</span>转诊/推荐人：</td>
		<td>
			<input type="text" name="fjh.referencesPerson" value="${fjh.referencesPerson}" required="true" class="easyui-validatebox" />
		</td>
		<td style="line-height: 25px;" class="rightTextAlign"><span class="red">*</span>转诊/推荐日期：</td>
		<td>
			<input type="text" class="Wdate text fbdate easyui-validatebox" style="width: 140px;" name="fjh.referencesDt" data-options='required:true' value='<fmt:formatDate value="${fjh.referencesDt}" pattern="yyyy-MM-dd HH:mm:ss"/>'   class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" required="true" class="easyui-validatebox" />
		</td>
	</tr>
</table>
<script>
	$(function(){
	});
</script>