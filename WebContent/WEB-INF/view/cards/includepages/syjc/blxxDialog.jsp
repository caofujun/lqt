<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<form id="blxxForm">
<input type="hidden" id="rowid" <c:if test="${!empty BLXX}">value="${BLXX.subid}"</c:if> />
<table style="margin-top:5px;margin-bottom: 5px;">
	<tr style="line-height: 28px;">
		<td class="rightTextAlign" style="width:110px;"><span class="red">*</span>食品名称：</td>
		<td style="width:145px;"><input type="text" id="foodname" class="easyui-validatebox" required="true" <c:if test="${!empty BLXX}">value="${BLXX.foodname}"</c:if> /></td>
		<td style="width:120px;" class="rightTextAlign" ><span class="red">*</span>食品分类：</td>
		<td style="width:145px;">
			<select id="foodclass" class="easyui-combobox" data-options="required:true">
				<option></option>
			<c:forEach items="${FOODCLASSIFY}" var="fc">
				<option value="${fc.dictName}" <c:if test="${BLXX.foodclass eq fc.dictName}">selected="selected"</c:if> >${fc.dictName }</option>
			</c:forEach>
			</select>
		</td>
		<td class="rightTextAlign" style="width: 120px;"><span class="red">*</span>加工或包装方式：</td>
		<td style="width: 145px;">
			<select id="packingway" class="easyui-combobox" data-options="required:true">
				<option></option>
			<c:forEach items="${PACKAGEWAY}" var="pw">
				<option value="${pw.dictName}" <c:if test="${BLXX.packingway eq pw.dictName}">selected="selected"</c:if> >${pw.dictName}</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr style="line-height: 28px;">
		<td class="rightTextAlign">食品品牌：</td>
		<td><input type="text" id="foodbrand" <c:if test="${!empty BLXX}">value="${BLXX.foodbrand}"</c:if> /></td>
		<td class="rightTextAlign">生产厂家：</td>
		<td colspan="2"><input type="text" id="manufacturer" <c:if test="${!empty BLXX}">value="${BLXX.manufacturer}"</c:if>/></td>
		<td></td>
	</tr>
	<tr style="line-height: 28px;">
		<td class="rightTextAlign"><span class="red">*</span>进食场所类型：</td>
		<td>
			<select id="placetype" class="easyui-combobox" data-options="required:true">
				<option></option>
			<c:forEach items="${EATPLACE}" var="ep">
				<option value="${ep.dictName}" <c:if test="${BLXX.placetype eq ep.dictName}">selected="selected"</c:if> >${ep.dictName}</option>
			</c:forEach>
			</select>
		</td>
		<td class="rightTextAlign"><span class="red">*</span>进食时间：</td>
		<td><input type="text" id="eatingtime" class="Wdate text easyui-validatebox" required="true" <c:if test="${!empty BLXX}">value='<fmt:formatDate value="${BLXX.eatingtime}" type="both"/>' </c:if> style="width:130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'<fmt:formatDate value="${now}" type="both"/>'})"/></td>
		<td></td><td></td>
	</tr>
	<tr style="line-height: 28px;">
		<td class="rightTextAlign">进食场所：</td>
		<td colspan="5">
			<select id="epsheng" class="easyui-combobox" data-options="onSelect:function(r){get_public_shi('eat','epsheng','epshi','epxian','eatingplaces','eatingplacecode');}"><option></option></select>
			<select id="epshi" class="easyui-combobox" data-options="onSelect:function(r){get_public_xian('eat','epsheng','epshi','epxian','eatingplaces','eatingplacecode');}"><option></option></select>
			<select id="epxian" class="easyui-combobox" data-options="onSelect:function(r){getFullPublicAddress('epsheng','epshi','epxian','eatingplaces','eatingplacecode');}"><option></option></select>
			<input type="hidden" id="eatingplacecode" <c:if test="${!empty BLXX}">value="${BLXX.eatplacecode}"</c:if> />
		</td>
	</tr>
	<tr style="line-height: 28px;">
		<td></td>
		<td colspan="5">
			<input type="text" style="width:90%" id="eatingplaces" />
		</td>
	</tr>
	<tr style="line-height: 28px;">
		<td class="rightTextAlign">购买地点：</td>
		<td colspan="5">
			<select id="bpsheng" class="easyui-combobox" data-options="onSelect:function(r){get_public_shi('buy','bpsheng','bpshi','bpxian','purchaseplace','purchaseplacecode');}"><option></option></select>
			<select id="bpshi" class="easyui-combobox" data-options="onSelect:function(r){get_public_xian('buy','bpsheng','bpshi','bpxian','purchaseplace','purchaseplacecode');}"><option></option></select>
			<select id="bpxian" class="easyui-combobox" data-options="onSelect:function(r){getFullPublicAddress('bpsheng','bpshi','bpxian','purchaseplace','purchaseplacecode');}"><option></option></select>
			<input type="hidden" id="purchaseplacecode" <c:if test="${!empty BLXX}">value="${BLXX.purcplacecode}"</c:if>/>
		</td>
	</tr>
	<tr style="line-height: 28px;">
		<td></td>
		<td colspan="5">
			<input type="text" style="width:90%" id="purchaseplace" />
		</td>
	</tr>
	<tr style="line-height: 28px;">
		<td class="rightTextAlign"><span class="red">*</span>进食人数：</td>
		<td><input type="text" id="numberofeating" style="width:50px" class="easyui-validatebox" required="true" onkeyup="this.value=this.value.replace(/\D/g,'')" <c:if test="${!empty BLXX}">value="${BLXX.numberofeating}"</c:if> /> </td>
		<td class="rightTextAlign"><span class="red">*</span>其他人是否发病：</td>
		<td>
			<label style="margin-right: 20px;"><input type="radio" name="isOtherSick" value="是" <c:if test="${!empty BLXX && BLXX.otherpeople eq '是'}">checked="checked"</c:if> class="easyui-validatebox" data-options="validType:'requireRadio[\'input[name=isOtherSick]\', \'是或否或未知\']'"/>是</label>
			<label style="margin-right: 20px;"><input type="radio" name="isOtherSick" value="否" <c:if test="${!empty BLXX && BLXX.otherpeople eq '否'}">checked="checked"</c:if> />否</label>
			<label><input type="radio" name="isOtherSick" value="未知" <c:if test="${!empty BLXX && BLXX.otherpeople eq '未知'}">checked="checked"</c:if> />未知</label>
		</td>
	</tr>
</table>
</form>
<script>
$(function(){
	
	$.parser.parse($("#blxxForm"));
	
	get_public_sheng("eat","epsheng","epshi","epxian","eatingplaces",'eatingplacecode');
	get_public_sheng("buy","bpsheng","bpshi","bpxian","purchaseplace",'purchaseplacecode');

	<c:if test="${!empty BLXX}">
		setTimeout("$('#purchaseplace').val('${BLXX.purchaseplace}')",1000);
		setTimeout("$('#eatingplaces').val('${BLXX.eatingplaces}')",1000);
	</c:if>
	
});
function get_public_sheng(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getSheng.shtml";
	var eatSheng = "${eatSheng}";
	var buySheng = "${buySheng}";
	$.getJSON(url,function (data) {
		$("#"+sheng).html('');
		var appendstr="";
		$.each(data, function (i, item) {  
  			if(type=="eat"){
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
  			}else if(type=="buy"){
  				if(buySheng){
	       			if(item.areacode==buySheng){
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
		if(type=="eat"){
				if(eatSheng){
					$("#"+sheng).combobox({'value':eatSheng});
				}else{
					$("#"+sheng).combobox({'value':"${(HASheng)}"});
				}
		}else if(type=="buy"){
				if(buySheng){
					$("#"+sheng).combobox({'value':buySheng});
				}else{
					$("#"+sheng).combobox({'value':"${(HASheng)}"});
				}
		}
		get_public_shi(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_shi(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+sheng).combobox("getValue");
	var eatShi = "${eatShi}";
	var buyShi = "${buyShi}";
	$.getJSON(url,function (data) {
		$("#"+shi).html('');
		var appendstr="";
		$.each(data, function (i, item) {   
			if(type=="eat"){
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
  			}else if(type=="buy"){
  				if(buyShi){
	       			if(item.areacode==buyShi){
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
		if(type=="eat"){
			if(eatShi){
				$("#"+shi).combobox({'value':eatShi});
			}else{
				$("#"+shi).combobox({'value':"${(HAShi)}"});
			}
		}else if(type=="buy"){
				if(buyShi){
					$("#"+shi).combobox({'value':buyShi});
				}else{
					$("#"+shi).combobox({'value':"${(HAShi)}"});
				}
		}
		get_public_xian(type,sheng,shi,xian,addr,codeplace);
	});
}
function get_public_xian(type,sheng,shi,xian,addr,codeplace){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#"+shi).combobox("getValue");
	var eatXian = "${eatXian}";
	var buyXian = "${buyXian}";
	$.getJSON(url,function (data) {
		$("#"+xian).html('');
		var appendstr="";
		$.each(data, function (i, item) {    
			if(type=="eat"){
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
  			}else if(type=="buy"){
  				if(buyXian){
	       			if(item.areacode==buyXian){
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
		if(type=="eat"){
			if(eatXian){
				$("#"+xian).combobox({'value':eatXian});
			}else{
				$("#"+xian).combobox({'value':"${(HAXian)}"});
			}
		}else if(type=="buy"){
			if(buyXian){
				$("#"+xian).combobox({'value':buyXian});
			}else{
				$("#"+xian).combobox({'value':"${(HAXian)}"});
			}
		}
		getFullPublicAddress(sheng,shi,xian,addr,codeplace);
	});
}
function getFullPublicAddress(sheng,shi,xian,addr,codeplace){
	$("#"+codeplace).val($("#"+xian).combobox("getValue"));
	$("#"+addr).val($("#"+sheng).combobox("getText")+$("#"+shi).combobox("getText")+$("#"+xian).combobox("getText"));
}
</script>
<!-- <div style="position: relative;bottom: -12px;text-align: center;padding-top:15px;padding-bottom:15px;background-color:#ddd;">
	<input type="button" class="butt" value="确定" style="margin-right: 20px;width:70px;"/><input type="button" class="butt" value="取消" style="width:70px;"/>
</div> -->