<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<table class="stand_table" style="width:100%;" id="chosenDiseasis">
	<thead>
		<tr>
			<th>操作</th>
			<th>疾病编号</th>
			<th>疾病名称</th>
			<th><span class="red">*</span>发病日期</th>
			<th><span class="red">*</span>诊断日期</th>
			<th><span class="red">*</span>病例分类</th>
			<th>病例分类2</th>
			<th>密切接触者有无相同症状</th>
			<th>实验室结果</th>
			<th>备注</th>
			<th class="rpr" style="display: none;">RPR</th>
			<th class="trust" style="display: none;">TRUST</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${AllDisease}" var="ad" varStatus="vs">
	  <tr rowno="${ad.diseaseid}">
		<td>
			<input class="diseaseSubid" type="hidden" value="${ad.subid}"/>
			<a class='ico_del' style="margin: 3px;" title="删除" onclick="var id=$(this).parent().parent().attr('rowno');removeRow(id);"></a>
			<a class='ico_info' style="margin: 3px;" title="修改" onclick="var nid=$(this).parent().parent().attr('rowno');fixRow(nid);"></a>
		</td>
		<td><span class="show_diseaseid">${ad.diseaseid}</span><input type="hidden" name="diseaseList[${vs.index}].diseaseid" class="diseaseid" value="${ad.diseaseid}"/></td>
		<td><span class="show_diseasename">${ad.diseasename}</span><input type="hidden" name="diseaseList[${vs.index}].diseasename" class="diseasename" value="${ad.diseasename}"/></td>
		<td><input type="text" class="Wdate text fbdate easyui-validatebox" data-options='required:true' readonly='readonly' name="diseaseList[${vs.index}].startdate" style="width: 130px;" value='<fmt:formatDate value="${ad.startdate}" pattern="yyyy-MM-dd HH:mm:ss"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}"  pattern="yyyy-MM-dd HH:mm:ss" />'})" /></td>
		<td><input type="text" class="Wdate text zddate easyui-validatebox" data-options='required:true' readonly='readonly' name="diseaseList[${vs.index}].diagnosedate" style="width: 130px;" value='<fmt:formatDate value="${ad.diagnosedate}" pattern="yyyy-MM-dd HH:mm:ss"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" />'})" /></td>
		<td>
			<select id='ct1_id_${ad.diseaseid}' style="width: 120px;" name="diseaseList[${vs.index}].casetypeid" class="blfl1 easyui-combobox fillName"  data-options='required:true,onChange:function(n,o){fillName("ct1_id_${ad.diseaseid}","ct1_name_${ad.diseaseid}");}'>
				<option></option>
				<c:forEach items="${blfl}" var="bf">
					<option value="${bf.dictCode }" <c:if test="${ad.casetypeid==bf.dictCode}">selected="selected"</c:if> >${bf.dictName }</option>
				</c:forEach>
			</select>
			<input type="hidden" id='ct1_name_${ad.diseaseid}' name="diseaseList[${vs.index}].casetypename" value="${ad.casetypename}" />
		</td>
		<td>
			<select id='ct2_id_${ad.diseaseid}' style="width: 80px;" name="diseaseList[${vs.index}].casetypeid2" class="blfl2 easyui-combobox fillName" data-options='required:true,onChange:function(n,o){fillName("ct2_id_${ad.diseaseid}","ct2_name_${ad.diseaseid}");}'>
				<c:forEach items="${blfl2}" var="bf2">
					<option value="${bf2.dictCode }" <c:if test="${ad.casetypeid2==bf2.dictCode}">selected="selected"</c:if>>${bf2.dictName }</option>
				</c:forEach>
			</select>
			<input type="hidden" id='ct2_name_${ad.diseaseid}' name="diseaseList[${vs.index}].casetypename2" value="${ad.casetypename2}" />
		</td>
		<td>
			<select style="width: 80px;" class="jchzzz easyui-combobox"  name="diseaseList[${vs.index}].contactflag">
				<option></option>
				<option value="有" <c:if test="${ad.contactflag eq '有'}">selected="seleced"</c:if> >有</option>
				<option value="无" <c:if test="${ad.contactflag eq '无'}">selected="seleced"</c:if> >无</option>
				<option value="不详" <c:if test="${ad.contactflag eq '不详'}">selected="seleced"</c:if> >不详</option>
			</select>
		</td>
		<td>
			<select id='labr_${ad.diseaseid}' style="width: 120px;" name="diseaseList[${vs.index}].labresultno" class="niddict fillName easyui-combobox" data-options='onChange:function(n,o){fillName("labr_${ad.diseaseid}","labr_name_${ad.diseaseid}");}'>
				<option></option>
				<c:forEach items="${LABR}" var="lr">
					<option value="${lr.dictCode}" <c:if test="${ad.labresultno==lr.dictCode}">selected="seleced"</c:if> >${lr.dictName}</option>
				</c:forEach>
			</select>
			<input id="labr_name_${ad.diseaseid}" name="diseaseList[${vs.index}].labresult" type="hidden" value="${ad.labresult }"/>
		</td>
		<td><input type="text" style="width: 120px;" class="notes" name="diseaseList[${vs.index}].diseasenotes" value="${ad.diseasenotes}"/></td>
		<td class="rpr" style="display:none;"><input type="text" style="display:none;width:70px;" class="rprinput" name="diseaseList[${vs.index}].rpr" value="${ad.rpr}"/></td>
		<td class="trust" style="display:none;"><input type="text" style="display:none;width:70px;" class="trustinput" name="diseaseList[${vs.index}].trust" value="${ad.trust}"/></td>
	</tr>
	</c:forEach>
	</tbody>
</table>