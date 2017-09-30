<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<jsp:useBean id="now" class="java.util.Date" />
<input type="hidden" id="xbfkSubid" value="${XBFK.subid}" />
<table id="xbfkTable" class="mainTable" style="margin:5px 0px; ">
	<tr>
		<td class="rightTextAlign"><span class="red">*</span>婚姻状况：</td>
		<td>
			<select style="width: 142px;" name="hiv.marriageid" id="marriageid" class="easyui-combobox" >
				<option></option>
				<c:forEach items="${maritalStatus}" var="ms">
					<option value="${ms.dictCode }" <c:if test="${XBFK.marriageid==ms.dictCode}">selected="selected"</c:if> >${ms.dictName }</option>
				</c:forEach>
			</select>
			<input type="hidden" id="marriagevalue" name="hiv.marriagevalue" value="${XBFK.marriagevalue}"/>
		</td>
		<td class="rightTextAlign"><span class="red">*</span>民族：</td>
		<td>
			<select style="width: 142px;" id="nationid" class="easyui-combobox"  name="hiv.nationid" >
				<option></option>
				<c:forEach items="${nation}" var="nation">
					<option value="${nation.dictCode }" <c:if test="${XBFK.nationid==nation.dictCode}">selected="selected"</c:if> >${nation.dictName }</option>
				</c:forEach>
			</select>
			<input type="hidden" id="nationname" name="hiv.nationname" value="${XBFK.nationname}"/>
		</td>
		<td class="rightTextAlign"><span class="red">*</span>文化程度：</td>
		<td>
			<select style="width: 142px;" id="educationid" class="easyui-combobox" name="hiv.educationid">
				<option></option>
				<c:forEach items="${education}" var="education">
					<option value="${education.dictCode }" <c:if test="${XBFK.educationid==education.dictCode}">selected="selected"</c:if> >${education.dictName }</option>
				</c:forEach>
			</select>
			<input type="hidden" id="educationname" name="hiv.educationname" value="${XBFK.educationname}"/>
		</td>
	</tr>
	<tr>
		<td class="rightTextAlign">户籍所在地：</td>
		<td>
			<select style="width: 142px;" id="regaddrtypeid" class="easyui-combobox"  name="hiv.regaddrtypeid" >
			<c:forEach items="${patientBelong}" var="pb">
				<option value="${pb.dictCode}" <c:if test="${XBFK.regaddrtypeid==pb.dictCode}">selected="selected"</c:if> >${pb.dictName}</option>
			</c:forEach>
			</select>
			<input type="hidden" id="regaddrtypename" name="hiv.regaddrtypename" value="${XBFK.regaddrtypename}"/>
		</td>
	</tr>
	<tr>
		<td class="rightTextAlign">户籍地址：</td>
		<td>
			<select style="width: 142px;" id="regSheng" class="easyui-combobox"><option></option></select>省
		</td>
		<td>&nbsp;</td>
		<td>
			<select style="width: 142px;" id="regShi" class="easyui-combobox"><option></option></select>市
		</td>
		<td>&nbsp;</td>
		<td>
			<select style="width: 142px;" id="regXian" class="easyui-combobox"> <option></option></select>县（区）
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>
			<select style="width: 142px;" id="regXiang" class="easyui-combobox"><option></option></select>乡
		</td>
		<td>&nbsp;</td>
		<td><input type="text" style="width: 130px;" name="hiv.regvillage" id="regvillage" value="${XBFK.regvillage}" onblur="fullRegAddress();" /> 村</td>
		<td>&nbsp;</td>
		<td><input type="text" style="width: 130px;" name="hiv.reghousenumber" value="${XBFK.reghousenumber}" id="regmph" onblur="fullRegAddress();" /> 门牌号</td>
	</tr>
	<tr>
		<td class="rightTextAlign">户籍详细地址：</td>
		<td colspan="5">
			<input type="text" style="width: 93%;" id="regaddr" name="hiv.regaddr" required="true" class="easyui-validatebox"  value="${XBFK.regaddr}"/>
			<input type="hidden" id="regaddrcode" name="hiv.regaddrcode" value="${XBFK.regaddrcode }"/>
			<input type="hidden" id="regaddrcodevalue" name="hiv.regaddrcodevalue" value="${XBFK.regaddrcodevalue }"/>
			<input type="hidden" id="regaddrbk" value="${XBFK.regaddr}"/>
		</td>
	</tr>
	<tr>
		<td style="vertical-align: top;" class="rightTextAlign"><span class="red">*</span>接触史(可多选)：</td>
		<td colspan="5">
			<table>
				<tr>
				<c:forEach items="${jcs}" var="jcs" varStatus="status">
					<td width="120px">
						<label><input type="checkbox" name="jcs" onclick="ischecked(this);" value="${jcs.dictCode}"/>${jcs.dictName}</label>
					</td>
			<c:if test="${(status.index+1)%5==0}">
				</tr><tr>
			</c:if>
				</c:forEach>
				</tr>
			</table>
			<input type="hidden" name="hiv.contacthistoryid" id="contacthistoryid" value="${XBFK.contacthistoryid}" />
			<input type="hidden" name="hiv.contacthistoryname" id="contacthistoryname" value="${XBFK.contacthistoryname}" />
		</td>
	</tr>
	<tr id="zsdp" style="display: none;">
		<td class="rightTextAlign">注射毒品史：</td>
		<td colspan="5">
			在您记忆中有<input type="text" style="width: 60px;" name="hiv.injectcount" id="injectcount" onkeyup="value=value.replace(/[^\d]/g,'')" value="${XBFK.injectcount}"/>人与您共用过注射器
		</td>
	</tr>
	<tr id="fhyx" style="display: none;">
		<td class="rightTextAlign">非婚异性性接触史：</td>
		<td colspan="3">
			在您记忆中有<input type="text" style="width: 60px;" name="hiv.oppositesexcount" id="oppositesexcount" onkeyup="value=value.replace(/[^\d]/g,'')" value="${XBFK.oppositesexcount}"/>人与您有过非婚性行为
		</td>
		<td class="rightTextAlign"><span class="red">*</span>商业性：</td>
		<td>
			<select style="width: 142px;" id="business" name="hiv.business" class="easyui-combobox">
				<option value="是" <c:if test="${XBFK.business=='是'}">selected="selected"</c:if> >是</option>
				<option value="否" <c:if test="${XBFK.business=='否'}">selected="selected"</c:if> >否</option>
			</select>
		</td>
	</tr>
	<tr id="nnxxw" style="display: none;">
		<td class="rightTextAlign"><span class="red">*</span>男男性行为史：</td>
		<td colspan="5">
			在您记忆中有<input type="text" style="width: 60px;"id="urningcount" name="hiv.urningcount" value="${XBFK.urningcount }" onkeyup="value=value.replace(/[^\d]/g,'')"/>人与您有过同性性行为
		</td>
	</tr>
	<tr id="qtjcs" style="display: none;">
		<td class="rightTextAlign"><span class="red">*</span>其他接触史：</td>
		<td colspan="5">
			<input type="text" style="width: 130px;" id="contacthistoryother" name="hiv.contacthistoryother" value="${XBFK.contacthistoryother}"/>
		</td>
	</tr>
	<tr>
		<td class="rightTextAlign"><span class="red">*</span>性病史：</td>
		<td>
			<select style="width: 142px;" name="hiv.stdhistoryid" id="stdhistoryid" class="easyui-combobox"  >
				<option value="2" <c:if test="${XBFK.stdhistoryid=='2' }">selected="selected"</c:if> >无</option>
				<option value="1" <c:if test="${XBFK.stdhistoryid=='1' }">selected="selected"</c:if>>有</option>
				<option value="3" <c:if test="${XBFK.stdhistoryid=='3' }">selected="selected"</c:if>>不详</option>
			</select>
			<input type="hidden" name="hiv.stdhistoryname" id="stdhistoryname" value="${XBFK.stdhistoryname}"/>
		</td>
	</tr>
	<tr>
		<td class="rightTextAlign"><span class="red">*</span>最有可能感染途径：</td>
		<td>
			<select style="width: 142px;" id="infectionid" class="easyui-combobox"  name="hiv.infectionid"  >
				<option></option>
				<c:forEach items="${infectWay}" var="iw">
					<option value="${iw.dictCode}" <c:if test="${XBFK.infectionid==iw.dictCode}">selected="selected"</c:if> >${iw.dictName}</option>
				</c:forEach>
			</select>
			<input type="hidden" id="infectionname" name="hiv.infectionname" value="${XBFK.infectionname}"/>
		</td>
		<td class="rightTextAlign OI"  style="display: none;"><span class="red">*</span>其他最有可能感染途径：</td>
		<td colspan="3" class="OI"  style="display: none;"><input type="text" style="width: 130px;" name="hiv.infectionother" id="infectionother" value="${XBFK.infectionother}"/>（请注明）</td>
	</tr>
	<tr>
		<td class="rightTextAlign"><span class="red">*</span>样本来源：</td>
		<td>
			<select name="hiv.samplesourceid" id="samplesourceid" class="easyui-combobox"  style="width: 142px;" >
				<option></option>
				<c:forEach items="${sampleFrom}" var="sf">
					<option value="${sf.dictCode}" <c:if test="${XBFK.samplesourceid==sf.dictCode}">selected="selected"</c:if> >${sf.dictName}</option>
				</c:forEach>
			</select>
			<input type="hidden" id="samplesourcename" name="hiv.samplesourcename" value="${XBFK.samplesourcename}"/>
		</td>
		<td class="rightTextAlign OSS"  style="display: none;">其他样本来源：</td>
		<td colspan="3" class="OSS"  style="display: none;"><input type="text" style="width: 130px;" name="hiv.samplesourceother" id="samplesourceother" value="${XBFK.samplesourceother}"/>（请注明）</td>
	</tr>
	<tr>
		<td class="rightTextAlign">实验室检测结论：</td>
		<td>
			<select style="width: 142px;" id="labconclusionid" name="hiv.labconclusionid" class="easyui-combobox" >
				<option></option>
			<c:forEach items="${labConclusion}" var="lc">
				<option value="${lc.dictCode}" <c:if test="${XBFK.labconclusionid==lc.dictCode}">selected="selected"</c:if> >${lc.dictName}</option>
			</c:forEach>
			</select>
			<input type="hidden" id="labconclusionname" name="hiv.labconclusionname" value="${XBFK.labconclusionname}"/>
		</td>
		<td class="rightTextAlign">确认(替代策略)&nbsp;&nbsp;<br/>检测阳性日期：</td>
		<td colspan="3">
		<input type="text" class="Wdate text"  name="hiv.affirmdate" id="affirmdate" style="width: 130px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			<c:if test="${!empty XBFK}">
				value='<fmt:formatDate value="${XBFK.affirmdate}" pattern="yyyy-MM-dd HH:mm:ss" />'
			</c:if>
		/></td>
	</tr>
	<tr>
		<td class="rightTextAlign">确认（替代策略）<br/>检测单位：</td>
		<td colspan="5"><input type="text" style="width: 90%" name="hiv.affirmunit" id="affirmunit" value="${XBFK.affirmunit }" /></td>
	</tr>
	<tr>
		<td class="rightTextAlign">艾滋病诊断日期：</td>
		<td><input type="text" class="Wdate text" id="diagnosedt" name="hiv.diagnosedt" style="width: 130px;" value='<fmt:formatDate value="${XBFK.diagnosedt}" pattern="yyyy-MM-dd HH:mm:ss"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
		<td class="rightTextAlign">生殖道沙眼衣原体感染：</td>
		<td colspan="3">
			<select style="width: 142px;" name="hiv.sinfect" id="sinfect" class="easyui-combobox">
				<option></option>
				<option value="确诊病例" <c:if test="${XBFK.sinfect=='确诊病例'}">selected="selected"</c:if> >确诊病例</option>
				<option value="无症状感染" <c:if test="${XBFK.sinfect=='无症状感染'}">selected="selected"</c:if> >无症状感染</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="rightTextAlign">备注：</td>
		<td colspan="5"><input type="text" style="width: 90%" id="subremark" name="hiv.subremark" value="${XBFK.subremark}"/></td>
	</tr>
</table>
<script>
$(function(){
	
	$("#marriageid").combobox({
		required:true,
		onSelect : function(r){
			$("#marriagevalue").val($("#marriageid").combobox("getText"));
		}
	});
	$("#nationid").combobox({
		required:true,
		onSelect : function(r){
			$("#nationname").val($("#nationid").combobox("getText"));
		}
	});
	$("#educationid").combobox({
		required:true,
		onSelect : function(r){
			$('#educationname').val($('#educationid').combobox('getText'));
		}
	});
	$("#regaddrtypeid").combobox({
		required:true,
		onSelect : function(r){
			CRBHIVaddress();
		}
	});
	$("#stdhistoryid").combobox({
		required:true,
		onSelect : function(r){
			$('#stdhistoryname').val($('#stdhistoryid').combobox('getText'));
		}
	});
	$("#infectionid").combobox({
		required:true,
		onSelect : function(r){
			$('#infectionname').val($('#infectionid').combobox('getText'));
			if($('#infectionid').combobox('getValue')=='其他'){
				$('.OI').show();
			}else{
				$('.OI').hide();
			}
		}
	});
	$("#samplesourceid").combobox({
		required:true,
		onSelect : function(r){
			$('#samplesourcename').val($('#samplesourceid').combobox('getText'));
			if($('#samplesourceid').combobox('getText')=='其他'){
				$('.OSS').show();
			}else{
				$('.OSS').hide();
			}
		}
	});
	$("#labconclusionid").combobox({
		required:true,
		onSelect : function(r){
			$('#labconclusionname').val($('#labconclusionid').combobox('getText'));
		}
	});
	$("#sinfect").combobox();
	
	
	//
	$('#stdhistoryname').val($('#stdhistoryid').combobox("getText"));
	$('#marriagevalue').val($('#marriageid').combobox("getText"));
	$('#nationname').val($('#nationid').combobox("getText"));
	$('#educationname').val($('#educationid').combobox("getText"));
	$('#infectionname').val($('#infectionid').combobox("getText"));
	$('#samplesourcename').val($('#samplesourceid').combobox("getText"));
	$('#labconclusionname').val($('#labconclusionid').combobox("getText"));
	
	CRBHIVaddress();
	
	var hivchid="${XBFK.contacthistoryid}";
	var hivch=document.getElementsByName("jcs");
	if(!hivchid){
	}else{
		var hivchids=hivchid.split(",");
		for(var i=0;i<hivchids.length;i++){
			for(var j=0;j<hivch.length;j++){
				if(hivchids[i]==hivch[j].value){
					hivch[j].checked=true;
					$(hivch[j]).trigger("click");
					hivch[j].checked=true;
				}
			}
		}
	}
	
	if($('#infectionid').combobox("getText")=='其他'){$('.OI').show();}else{$('.OI').hide();}
	if($('#samplesourceid').combobox("getText")=='其他'){$('.OSS').show();}else{$('.OSS').hide();}
	
	<c:choose>
		<c:when test="${!empty XBFK}">
			setTimeout('$("#regaddr").val("${XBFK.regaddr}")',1000);
		</c:when>
		<c:when test="${!empty regaddress}">
			setTimeout('$("#regaddr").val("${regaddress}")',1000);
		</c:when>
	</c:choose>

	//地址联动
	/* $("#regSheng").change(function () { 
		get_regShi();
	});
	$("#regShi").change(function () { 
		get_regXian(); 
	});
	$("#regXian").change(function () { 
		get_regXiang();
	});
	$("#regXiang").change(function (){
		fullRegAddress();
	}); */
	
	//
	$("#regSheng").combobox({
		onSelect : function(r){
			get_regShi();
		}
	});
	$("#regShi").combobox({
		onSelect : function(r){
			get_regXian(); 
		}
	});
	$("#regXian").combobox({
		onSelect : function(r){
			get_regXiang();
		}
	});
	$("#regXiang").combobox({
		onSelect : function(r){
			fullRegAddress();
		}
	});
	
});


function CRBHIVaddress(){
	$("#regSheng").combobox({value:"",disabled:false});
	$("#regShi").combobox({value:"",disabled:false});
	$("#regXian").combobox({value:"",disabled:false});
	$("#regXiang").combobox({value:"",disabled:false});
	var val=$("#regaddrtypeid").combobox("getValue");
	$("#regaddrtypename").val($("#regaddrtypeid").combobox("getText"));
	get_regSheng();
	if(val==1){
		$("#regSheng").combobox({value:"",disabled:true});
		$("#regShi").combobox({value:"",disabled:true});
		$("#regXian").combobox({value:"",disabled:true});
	}
	if(val==2){
		$("#regSheng").combobox({value:"",disabled:true});
		$("#regShi").combobox({value:"",disabled:true});
	}
	if(val==3){
		$("#regSheng").combobox({value:"",disabled:true});
	}
	if(val==4){
	}
	if(val>=5){
		$("#regSheng").combobox({value:"",disabled:true});
		$("#regShi").combobox({value:"",disabled:true});
		$("#regXian").combobox({value:"",disabled:true});
		$("#regXiang").combobox({value:"",disabled:true});
	}
}
function get_regSheng(){
	var url = "${webroot}/cdc/f_json/getSheng.shtml";
	var curSheng = "${curRegSheng}";
	$.getJSON(url,function (data) {
		$("#regSheng").html('');
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
		$("#regSheng").append(appendstr);
		if(curSheng){
			$("#regSheng").combobox({'value':curSheng});
		}else{
			$("#regSheng").combobox({'value':"${(HASheng)}"});
		}
		get_regShi();
	});
}
function get_regShi(){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#regSheng").combobox("getValue");
	var curShi = "${curRegShi}";
	$.getJSON(url,function (data) {
		$("#regShi").html('');
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
		$("#regShi").append(appendstr);
		if(curShi){
			$("#regShi").combobox({'value':curShi});
		}else{
			$("#regShi").combobox({'value':"${(HAShi)}"});
		}
		get_regXian();
	});
}
function get_regXian(){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#regShi").combobox("getValue");
	var curXian = "${curRegXian}";
	$.getJSON(url,function (data) {
		$("#regXian").html('');
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
		$("#regXian").append(appendstr);
		if(curXian){
			$("#regXian").combobox({'value':curXian});
		}else{
			$("#regXian").combobox({'value':"${(HAXian)}"});
		}
		get_regXiang();
	});
}
function get_regXiang(){
	var url = "${webroot}/cdc/f_json/getOther.shtml?areacode="+$("#regXian").combobox("getValue");
	var curXiang = "${curRegXiang}";
	$.getJSON(url,function (data) {
		$("#regXiang").html('');
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
		$("#regXiang").append(appendstr);
		if(curXiang){
			$("#regXiang").combobox({'value':curXiang});
		}else{
			$("#regXiang").combobox({'value':"${(HAXiang)}"});
		}
		fullRegAddress();
	});
}
function fullRegAddress(){
 	//国标编码
	$("#regaddrcode").val($("#regXiang").combobox("getValue"));
	//国标地址
	$("#regaddrcodevalue").val($("#regSheng").combobox("getText")+$("#regShi").combobox("getText")+$("#regXian").combobox("getText")+$("#regXiang").combobox("getText"));
	//详细地址
	//var addrbk = $("#regaddrbk").val();
	$("#regaddr").val($("#regSheng").combobox("getText")+$("#regShi").combobox("getText")+$("#regXian").combobox("getText")+$("#regXiang").combobox("getText")+$("#regvillage").val()+$("#regmph").val());
}
function ischecked(ele){
	if($(ele).is(":checked")){
		if($(ele).val()=="01"){
			$("#zsdp").show("fast");
		}else if($(ele).val()=="02"){
			$("#fhyx").show("fast");
		}else if($(ele).val()=="04"){
			$("#nnxxw").show("fast");
		}else if($(ele).val()=="10"){
			$("#qtjcs").show("fast");
		}
	}else{
		if($(ele).val()=="01"){
			$("#zsdp").hide("fast");
		}else if($(ele).val()=="02"){
			$("#fhyx").hide("fast");
		}else if($(ele).val()=="04"){
			$("#nnxxw").hide("fast");
		}else if($(ele).val()=="10"){
			$("#qtjcs").hide("fast");
		}
	}
	getAllJCS();
}
function getAllJCS(){
	var arr=document.getElementsByName("jcs");
	var allval="jcs";
	var jcsval="";
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked){
			allval += ","+arr[i].value;
			jcsval+=$.trim(arr[i].nextSibling.nodeValue)+";";		
		}
	}
	$("#contacthistoryid").val(allval);
	$("#contacthistoryname").val(jcsval);
}
</script>