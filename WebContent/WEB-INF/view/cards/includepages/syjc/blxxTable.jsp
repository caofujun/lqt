<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<table class="stand_table" style="width:100%;" id="chosenBLXX">
	<thead>
		<tr>
			<th>操作</th>
			<th>序号</th>
			<th><font color="red">*</font>食品名称</th>
			<th><font color="red">*</font>食品分类</th>
			<th><font color="red">*</font>加工或包装方式</th>
			<th>食品品牌</th>
			<th>生产厂家</th>
			<th>购买地点</th>
			<th>进食场所</th>
			<th><font color="red">*</font>进食场所类型</th>
			<th><font color="red">*</font>进食时间(年月日时)</th>
			<th><font color="red">*</font>进食人数</th>
			<th><font color="red">*</font>其他人是否发病</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${AllBLXX}" var="ab" varStatus="vs">
		<tr rownum="${ab.subid}">
			<td>
				<a class='ico_check' style="margin: 3px;" title="修改" onclick="modifyRow('chosenBLXX','${ab.subid}')"></a>
				<a class='ico_del' style="margin: 3px;" title="删除" onclick="removeRow(this,'blxx')"></a>
			</td>
			<td>
				<span class="orderno">${vs.index+1}</span>
				<input type='hidden' name='orderno' value='${vs.index}' />
			</td>
			<td><span class="foodname">${ab.foodname}</span><input type="hidden" class="foodname_hide" name="ctgBk005Blxx[${vs.index}].foodname" value="${ab.foodname}"/></td>
			<td><span class="foodclass">${ab.foodclass}</span><input type="hidden" class="foodclass_hide" name="ctgBk005Blxx[${vs.index}].foodclass" value="${ab.foodclass}"/></td>
			<td><span class="packingway">${ab.packingway}</span><input type="hidden" class="packingway_hide" name="ctgBk005Blxx[${vs.index}].packingway" value="${ab.packingway}"/></td>
			<td><span class="foodbrand">${ab.foodbrand}</span><input type="hidden" class="foodbrand_hide" name="ctgBk005Blxx[${vs.index}].foodbrand" value="${ab.foodbrand}"/></td>
			<td><span class="manufacturer">${ab.manufacturer}</span><input type="hidden" class="manufacturer_hide" name="ctgBk005Blxx[${vs.index}].manufacturer" value="${ab.manufacturer}"/></td>
			<td>
				<span class="purchaseplace">${ab.purchaseplace}</span>
				<input type="hidden" class="purchaseplace_hide" name="ctgBk005Blxx[${vs.index}].purchaseplace" value="${ab.purchaseplace}"/>
				<input type='hidden' class='purcplacecode_hide' name='ctgBk005Blxx[${vs.index}].purcplacecode' value='${ab.purcplacecode}'/>
			</td>
			<td>
				<span class="eatingplaces">${ab.eatingplaces}</span>
				<input type="hidden" class="eatingplaces_hide" name="ctgBk005Blxx[${vs.index}].eatingplaces" value="${ab.eatingplaces}"/>
				<input type='hidden' class='eatplacecode_hide' name='ctgBk005Blxx[${vs.index}].eatplacecode' value='${ab.eatplacecode}'/>
			</td>
			<td><span class="placetype">${ab.placetype}</span><input type="hidden" class="placetype_hide" name="ctgBk005Blxx[${vs.index}].placetype" value="${ab.placetype}"/></td>
			<td><span class="eatingtime"><fmt:formatDate value="${ab.eatingtime}" pattern="yyyy-MM-dd HH" />时</span><input type="hidden" class="eatingtime_hide" name="ctgBk005Blxx[${vs.index}].eatingtime" value="<fmt:formatDate value="${ab.eatingtime}" type="both" />"/></td>
			<td><span class="numberofeating">${ab.numberofeating}</span><input type="hidden" class="numberofeating_hide" name="ctgBk005Blxx[${vs.index}].numberofeating" value="${ab.numberofeating}"/></td>
			<td><span class="otherpeople">${ab.otherpeople}</span><input type="hidden" class="otherpeople_hide" name="ctgBk005Blxx[${vs.index}].otherpeople" value="${ab.otherpeople}"/></td>
		</tr>
	</c:forEach>
	</tbody>
</table>