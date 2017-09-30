<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<table class="stand_table" style="width:100%;" id="chosenCYXX">
	<thead>
		<tr>
			<th style="width:50px;">操作</th>
			<th style="width:50px;">序号</th>
			<th><font color="red">*</font>标本类型</th>
			<th>标本编号</th>
			<th><font color="red">*</font>标本数量</th>
			<th><font color="red">*</font>单位</th>
			<th><font color="red">*</font>采样日期</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${AllCYXX}" var="ac" varStatus="acvs">
		<%-- <tr rowno="${ac.subid}">
			<td>
				<a class='ico_del' style="margin: 3px;" title="删除" onclick="removeRow('chosenCYXX','${ac.subid}')"></a>
			</td>
			<td><span class="no">${acvs.index+1}</span></td>
			<td><span class="specimentype">${ac.specimentype}</span><input type="hidden" class="specimentype_hide" name="ctgBk005Cyxx[${acvs.index}].specimentype" value="${ac.specimentype}"/></td>
			<td><span class="specimennumber">${ac.specimennumber}</span><input type="hidden" class="specimennumber_hide" name="ctgBk005Cyxx[${acvs.index}].specimennumber" value="${ac.specimennumber}"/></td>
			<td><span class="specimencount">${ac.specimencount}</span><input type="hidden" class="specimencount_hide" name="ctgBk005Cyxx[${acvs.index}].specimencount" value="${ac.specimencount}"/></td>
			<td><span class="numberofunits">${ac.numberofunits}</span><input type="hidden" class="numberofunits_hide" name="ctgBk005Cyxx[${acvs.index}].numberofunits" value="${ac.numberofunits}"/></td>
			<td><span class="samplingdate"><fmt:formatDate value="${ac.samplingdate}" type="both"/></span>
				<input type="hidden" class="samplingdate_hide" name="ctgBk005Cyxx[${acvs.index}].samplingdate" value='<fmt:formatDate value="${ac.samplingdate}" type="both"/>' /></td>
			<td><span class="note">${ac.note}</span><input type="hidden" class="note_hide" name="ctgBk005Cyxx[${acvs.index}].note" value="${ac.note}"/></td>
		</tr> --%>
		<tr rowNum="${ac.subid}">
			<td>
				<a class='ico_del' style='margin: 3px;' title='删除' onclick="removeRow(this,'cyxx')"></a>
			</td>
			<td>
				<span class='index' name='no'>${acvs.index+1}</span>
				<input type='hidden' name='xb' value='${acvs.index+1}' />
			</td>
			<td>
				<select class='bblx easyui-validatebox' name='ctgBk005Cyxx[${acvs.index}].specimentype' required='true'>
					<c:forEach items="${BBLX}" var="bbld">
						<option value='${bbld.dictName}' <c:if test="${ac.specimentype eq bbld.dictName}">selected="selected"</c:if> >${bbld.dictName}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<input type='text' class='bbbh' name='ctgBk005Cyxx[${acvs.index}].specimennumber' value="${ac.specimennumber}" />
			</td>
			<td>
				<input type='text' class='bbsl easyui-validatebox' name='ctgBk005Cyxx[${acvs.index}].specimencount' style='width:60px;' required='true' value="${ac.specimencount}" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
			</td>
			<td>
				<select class='bbdw easyui-validatebox' required='true' style='width:70px;' name='ctgBk005Cyxx[${acvs.index}].numberofunits'>
					<option></option>
					<option value='g' <c:if test="${ac.numberofunits eq 'g'}">selected="selected"</c:if> >g</option>
					<option value='ml' <c:if test="${ac.numberofunits eq 'ml'}">selected="selected"</c:if> >ml</option>
					<option value='份' <c:if test="${ac.numberofunits eq '份'}">selected="selected"</c:if> >份</option>
				</select>
			</td>
			<td>
				<input type='text' class='cyrq Wdate text easyui-validatebox' required='true' name='ctgBk005Cyxx[${acvs.index}].samplingdate' value='<fmt:formatDate value="${ac.samplingdate}" />' onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'<fmt:formatDate value='${now}'/>'})" />
			</td>
			<td>
				<input type='text' class='bbbz' name='ctgBk005Cyxx[${acvs.index}].note' value="${ac.note}"/>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>