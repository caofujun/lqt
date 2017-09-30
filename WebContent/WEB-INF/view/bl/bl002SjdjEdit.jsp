<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>职业暴露登记表</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<style>
	.ib{display:inline-block; _zoom:1;_display:inline;}
</style>
</head>
<body>
<div class="prevalence">
 <div id="prevalence_edit">
	<div class="body_title"><b>职业暴露登记表</b></div>
	<form id="editFormWtjg01" method="post">
	<div class="title">一、基本情况：<a name="1"class="lemma-anchor maodian"></a></div>
	<div class="t_cont">	
		<input type="hidden" name="blId" value="${bl002Sjdj.blId}"/>
		<input type="hidden" name="type" value="01"/>
		<input type="hidden" name="sjState" value="0"/>
		<table class="info_table">
			<tr>
				<th width="60">事件编号</th><td>${bl002Sjdj.blId}</td>
				<th>员工工号</th><td colspan="3"><input type="text" class="easyui-validatebox" id="djCardid" onBlur="findDoctorById(this.value)" value="${bl002Sjdj.djCardid}" name="djCardid" style="width:140px"/></td>				
			</tr>
			<tr>
				<th>暴露地点</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="enterAdd" name="enterAdd" csmId="002" cssCls="easyui-validatebox" value="${bl002Sjdj.enterAdd}" headerKey="" headerValue="" exp="style='width:150px;' required='true'" />
						</span>
					</span>
				</td>
				<th>暴露科室</th>
				<td><input type="text" id="djDept" name="djDept" style="width: 140px;"  /></td>
				<th>暴露时间</th>
				<td><input type="text" name="enterTime"  id="enterTime" value="<fmt:formatDate value="${bl002Sjdj.enterTime}" pattern="yyyy-MM-dd HH:mm" />"  required="true"  class="Wdate text easyui-validatebox" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  style="width:120px;"/></td>
			</tr>
		</table>
		<table class="info_table">
			<tr>
				<th width="60">姓名</th>
				<td><input type="text" id="djName" value="${bl002Sjdj.djName}"  required="true" class="easyui-validatebox" name="djName" style="width: 100px;" /></td>
				<th>性别</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="djSex" csmId="024" name="djSex" value="${bl002Sjdj.djSex }" cssCls="easyui-validatebox" headerKey="" headerValue="" exp="style='width:112px;' required='true'" />
						</span>
					</span>
				</td>
				<th>年龄(岁)</th>
				<td><input type="text" id="djAge" name="djAge" value="${bl002Sjdj.djAge}" required="true" class="easyui-validatebox" style="width: 100px;"/></td>
				<th>学历</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="djXl" csmId="022" name="djXl" value="${bl002Sjdj.djXl}" cssCls="easyui-validatebox" headerKey="" headerValue="" exp="style='width:112px;' required='true'" />
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<th>岗位</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="djGw" csmId="001" name="djGw" value="${bl002Sjdj.djGw}" cssCls="easyui-validatebox" headerKey="" headerValue="请选择" exp="style='width:112px;' required='true'" />
						</span>
					</span>
				</td>
				<th>职称</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="djZc" csmId="023" name="djZc" value="${bl002Sjdj.djZc}" headerKey="" headerValue="请选择" exp="style='width:112px' required='true'" cssCls="easyui-validatebox" />
						</span>
					</span>
				</td>
				<th>工龄</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="djGl" csmId="209" name="djGl" value="${bl002Sjdj.djGl}" headerKey="" headerValue="" exp="style='width:112px' required='true'" cssCls="easyui-validatebox" />
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<th>手机</th>
				<td><input type="text" id="sjTel" name="sjTel" required="true" class="easyui-validatebox" value="${bl002Sjdj.sjTel}" style="width: 100px;"/></td>
				<th>电话</th>
				<td><input type="text" id="ipTel" name="ipTel" value="${bl002Sjdj.ipTel}" style="width: 100px;"/></td>
			</tr>
			<!-- <tr>
				<td colspan="6" style="border-bottom: 1px solid #ccc;"></td>
			</tr>
			<tr>
				<td colspan="8" style="text-align:center; border-bottom:2px solid #ccc; padding:5px 0px 20px;"><a class="ico_save" href="javascript:void(0)" onclick="$('#editFormBl002jdj').submit()">保存基本资料</a></td>
			</tr> -->
		</table>

		<table class="info_table">
			<tr>
				<td>职业安全培训
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="wtjg1" name="wtjg1" value="${wtjg1}" csmId="021" headerKey="" cssCls="easyui-validatebox" headerValue=""  exp="style='width:56px' required='true' " />
						</span>
					</span>
				</td>
				<td>既往发生职业暴露
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="wtjg5" name="wtjg5" value="${wtjg5}" csmId="021" headerKey="" headerValue="" cssCls="easyui-validatebox" exp="style='width:56px' required='true' " />
						</span>
					</span>
				</td>
				<td>暴露时从事医疗活动
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="wtjg20" name="wtjg20" value="${wtjg20}" csmId="031" headerKey="" cssCls="easyui-validatebox" headerValue="" exp="style='width:112px' required='true' " />
						</span>
					</span>
					<input type="text" id="wtjg21" name="wtjg21" style="display: none;width:70px;" value="${wtjg21}"/>
				</td>
			</tr>
			<tr>
				<td>接种乙肝疫苗 
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="wtjg10" name="wtjg10" value="${wtjg10}" csmId="021" headerKey="" headerValue="" cssCls="easyui-validatebox" exp="style='width:56px' required='true' onclick='necessary(this);' " />
						</span>
					</span>
				</td>
				<td colspan="2">接种乙肝疫苗共 <input type="text" value="${wtjg321}" id="wtjg321" name="wtjg321" style="width: 20px;"/>次，有无过敏
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="wtjg320" name="wtjg320" value="${wtjg320}" csmId="021" headerKey="" headerValue="" exp="style='width:56px'" />
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<td colspan="3">接种乙肝免疫球蛋白注射
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="wtjg325" name="wtjg325" value="${wtjg325}" csmId="021" headerKey="" headerValue="" cssCls="easyui-validatebox" exp="style='width:56px' required='true' onclick='necessary(this);' " />
						</span>
					</span>
				 共
				<input type="text" id="wtjg330" name="wtjg330" value="${wtjg330}" style="width: 20px;"/>次，有无过敏
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="wtjg331" name="wtjg331" value="${wtjg331}" csmId="021" headerKey="" headerValue="" exp="style='width:56px'" />
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<td colspan="3">接种乙肝免疫球蛋白注射的原因
				<input type="text" id="wtjg332" name="wtjg332" value="${wtjg332}" style="width: 200px;"/> ，时间
				<input type="text"  id="wtjg333" name="wtjg333" value="${wtjg333}"  class="Wdate text" onclick="WdatePicker()"  style="width:100px;"/></td>
			</tr>
			<tr>
				<td colspan="3">乙肝表面抗体
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect id="wtjg15" name="wtjg15" value="${wtjg15}" csmId="025" headerKey="" headerValue="" cssCls="easyui-validatebox" exp="style='width:56px' required='true' " />
						</span>
					</span>
			<%-- 	&nbsp;&nbsp;暴露原因
				<input type="text" id="wtjg31" value="${wtjg31}" name="wtjg31" style="width: 300px;" required='true' class='easyui-validatebox'/> --%>
			</tr> 
			<tr style="display: none;">
				<td colspan="3">整改措施
				<input type="text" id="wtjg32" value="${wtjg32}" name="wtjg32" style="width: 500px;"/>
			</tr>
		</table>
		<table class="info_table" style="width:380px;border:solid 1px #ccc;display: none;">
			<tr>
				<td style="align:left">HIV感染或携带</td>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect  name="wtjg300" value="${wtjg300}" csmId="005" headerKey="" headerValue="" exp="style='width:66px'" />
						</span>
					</span>
				</td>
				<td style="align:left">乙肝感染或携带</td>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg310" value="${wtjg310}" csmId="005" headerKey="" headerValue="" exp="style='width:66px'" />
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<td style="align:left">丙肝感染或携带</td>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg305" value="${wtjg305}" csmId="005" headerKey="" headerValue="" exp="style='width:66px'" />
						</span>
					</span>
				</td>
				<td style="align:left">梅毒感染或携带</td>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg315" value="${wtjg315}" csmId="005" headerKey="" headerValue="" exp="style='width:66px'" />
						</span>
					</span>
				</td>
			</tr>
		</table>
		<table class="info_table">
			<tr>
				<td colspan="8" class="prevalence_save"><a class="ico_save" href="javascript:editFormWtjg01submit();" id="editFormWtjg01Button" >保存基本资料</a></td>
			</tr>
		</table>
	</div>
	</form>
	<form id="editFormWtjg02" method="post">
	<div id="noedit2" style="width:100%;display: none;position: absolute;background-color: #ffffff;opacity:0.3;"></div>
	<div class="title">二、暴露方式：<a name="2"class="lemma-anchor maodian"></a></div>
	<input type="hidden" name="blId" value="${bl002Sjdj.blId}"/>
	<div class="t_cont">
		<table class="info_table">
			<tr>
				<td colspan="6">
					<input id="wtjg52" type="checkbox" onclick="showBlMode('接触')" <c:if test="${fn:contains(wtjg34,'接触')}">checked="checked"</c:if> value="接触" name="wtjg34" /><label for="wtjg52">&nbsp;<b>1、接触</b></label>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="jiechu1">
				<th>粘膜</th>
				<td>
					<table class="info_table" style="border:solid 1px #ccc;">
					<tr>
						
						<td><input  type="checkbox" <c:if test="${fn:contains(wtjg52,'口腔')}">checked="checked"</c:if> value="口腔" name="wtjg52" /><label for="wtjg52_2">&nbsp;口腔</label></td>
						<td><input  type="checkbox" <c:if test="${fn:contains(wtjg52,'鼻')}">checked="checked"</c:if> value="鼻" name="wtjg52" /><label for="wtjg52_3">&nbsp;鼻</label></td>
					</tr>
					<tr>
						<td><input  type="checkbox" <c:if test="${fn:contains(wtjg52,'眼')}">checked="checked"</c:if> value="眼" name="wtjg52" /><label for="wtjg52_4">&nbsp;眼</label></td>
						
						<td>
							<input  type="checkbox" <c:if test="${fn:contains(wtjg52,'其他')}">checked="checked"</c:if> value="其他" name="wtjg52" /><label for="wtjg52_6">&nbsp;其他</label>
							<input type="text" name="wtjg52" style="width: 50px;"/>
						</td>
					</tr>
					</table>
				</td>
				<th>接触面积(cm)</th>
				<td><input type="text" value="${wtjg45}" name="wtjg45" style="width: 100px;"/></td>
				<th>接触时间</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg50" value="${wtjg50}" csmId="010" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
			</tr>
			<tr id="jiechu3">
				<th>皮肤</th>
				<td>
					<table class="info_table" style="border:solid 1px #ccc;">
						<tr>
							<td><input  type="checkbox" <c:if test="${fn:contains(wtjg52,'无损的皮肤')}">checked="checked"</c:if> value="无损的皮肤" name="wtjg52" /><label for="wtjg52_1">&nbsp;无损的皮肤</label></td>
							<td><input  type="checkbox" <c:if test="${fn:contains(wtjg52,'受损的皮肤')}">checked="checked"</c:if> value="受损的皮肤" name="wtjg52" /><label for="wtjg52_5">&nbsp;受损的皮肤</label></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="jiechu2">
				<th>接触<br/>物质</th>
				<td>
					<table class="info_table" style="border:solid 1px #ccc;">
					<tr>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg55,'血液')}">checked="checked"</c:if> value="血液" name="wtjg55" /><label for="wtjg55_1">&nbsp;血液</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg55,'脓液')}">checked="checked"</c:if> value="脓液" name="wtjg55" /><label for="wtjg55_2">&nbsp;脓液</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg55,'尿液')}">checked="checked"</c:if> value="尿液" name="wtjg55" /><label for="wtjg55_3">&nbsp;尿液</label></td>
					</tr>
					<tr>
						<%-- <td><input type="checkbox" <c:if test="${fn:contains(wtjg55,'含血体液')}">checked="checked"</c:if> value="含血体液" name="wtjg55" /><label for="wtjg55_4">&nbsp;含血体液</label></td> --%>
						<td colspan="2"><input type="checkbox" <c:if test="${fn:contains(wtjg55,'其他')}">checked="checked"</c:if> value="其他" name="wtjg55" /><label for="wtjg55_5">&nbsp;其他</label>
							<input type="text" name="wtjg55" style="width: 50px;"/></td>
						<td></td>
					</tr>
					</table>
				</td>
				<th>接触量</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg51" value="${wtjg51}" csmId="011" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<th>污染物接触</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg76" value="${wtjg76}" csmId="007" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<%-- <th>皮肤破损</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:select name="wtjg36" value="${wtjg36}" dictcode="boolean" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td> --%>
			</tr>
			</div>
		</table>
	</div>
	<div class="t_cont">
		<table class="info_table">
			<tr>
				<td colspan="6">
					<input id="id_unitInfe" type="checkbox" onclick="showBlMode('针刺伤或锐器割伤')" <c:if test="${fn:contains(wtjg34,'针刺伤或锐器割伤')}">checked="checked"</c:if> value="针刺伤或锐器割伤" name="wtjg34" /><label for="id_unitInfe">&nbsp;<b>2、针刺伤或锐器割伤</b></label>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="zhenchi1">
				<th>器械类型</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg60" value="${wtjg60}" csmId="016" headerKey="" headerValue="" exp='style="width:112px" onchange="showThings(this,\'qxqt\')"' />
						</span>
					</span>
					<input type="text" style="display: none;width:100px;" id="qxqt" name="wtjg61" value="${wtjg61}"/>
					<script>
						if($("select[name='wtjg60'] option:selected").text()=="其他"){
							$("#qxqt").show();
						}
					</script>
				</td>
				<th rowspan="3">关联操作</th>
				<td rowspan="3" colspan="3">
					<table class="info_table" style="border:solid 1px #ccc;" height="100%">
					<tr>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'重新套帽')}">checked="checked"</c:if> value="重新套帽" name="wtjg65" /><label for="wtjg65_1">&nbsp;重新套帽</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'处理针')}">checked="checked"</c:if> value="处理针" name="wtjg65" /><label for="wtjg65_2">&nbsp;处理针</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'收集废物')}">checked="checked"</c:if> value="收集废物" name="wtjg65" /><label for="wtjg65_3">&nbsp;收集废物</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'开安剖')}">checked="checked"</c:if> value="开安剖" name="wtjg65" /><label for="wtjg65_4">&nbsp;开安剖</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'实验室操作')}">checked="checked"</c:if> value="实验室操作" name="wtjg65" /><label for="wtjg65_5">&nbsp;实验室操作</label></td>
					</tr>
					<tr>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'采血')}">checked="checked"</c:if> value="采血" name="wtjg65" /><label for="wtjg65_6">&nbsp;采血</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'注射')}">checked="checked"</c:if> value="注射" name="wtjg65" /><label for="wtjg65_7">&nbsp;注射</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'拔针')}">checked="checked"</c:if> value="拔针" name="wtjg65" /><label for="wtjg65_8">&nbsp;拔针</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'缝合')}">checked="checked"</c:if> value="缝合" name="wtjg65" /><label for="wtjg65_9">&nbsp;缝合</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'清理锐器盒')}">checked="checked"</c:if> value="清理锐器盒" name="wtjg65" /><label for="wtjg65_10">&nbsp;清理锐器盒</label></td>
					</tr>
					<tr>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'清理织物')}">checked="checked"</c:if> value="清理织物" name="wtjg65" /><label for="wtjg65_11">&nbsp;清理织物</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'包扎')}">checked="checked"</c:if> value="包扎" name="wtjg65" /><label for="wtjg65_12">&nbsp;包扎</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'清洁设备')}">checked="checked"</c:if> value="清洁设备" name="wtjg65" /><label for="wtjg65_13">&nbsp;清洁设备</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'剃毛')}">checked="checked"</c:if> value="剃毛" name="wtjg65" /><label for="wtjg65_14">&nbsp;剃毛</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg65,'其他')}">checked="checked"</c:if> value="其他" name="wtjg65" /><label for="wtjg65_15">&nbsp;其他</label>
						<input type="text" name="wtjg65" style="width: 50px;"/>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr id="zhenchi2">
				<th>损伤危险度</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg70" value="${wtjg70}" csmId="029" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
			</tr>
			<tr id="zhenchi3">
				<th>污染物来源</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg75" value="${wtjg75}" csmId="004" headerKey="" headerValue="" exp='style="width:112px" onchange="showThings(this,\'wrwqt\'); "' />
						</span>
					</span>
					<input type="text" style="display: none;width:100px;" id="wrwqt" name="wtjg77" value="${wtjg77}"/>
					<script>
						if($("select[name='wtjg75'] option:selected").text()=="其他"){
							$("#wrwqt").show();
						}
					</script>
				</td>
			</tr>
			<tr id="zhenchi4">
				<th>接触部位</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">
							<!-- <select name="wtjg40" id="wtjg40" style='width:112px'></select> -->
							<nis:blselect name="wtjg40" id="wtjg40"  csmId="012" cssCls="easyui-combobox" headerKey="" headerValue="" exp="style='width:112px' data-options='multiple:true'" />
							<%-- <nis:select name="wtjg40" id="wtjg40" value="${wtjg40}" dictcode="bl_contact_position" cssCls="easyui-combobox" exp="style='width:112px'" />
 --%>						</span>
					</span>
				</td>
				<th>暴露时是<br/>否带手套</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg288" value="${wtjg288}" csmId="288" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<td colspan="6">
					<input id="id_unitInfe" type="checkbox" onclick="showBlMode('其他方式')" <c:if test="${fn:contains(wtjg34,'其他方式')}">checked="checked"</c:if> value="其他方式" name="wtjg34" /><label for="id_unitInfe">&nbsp;<b>3、其他方式</b></label>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="qita">
				<th>致伤方式</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg80" value="${wtjg80}" csmId="018" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<th>破损出血</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg85" value="${wtjg85}" csmId="021" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<td colspan="6" style="border-bottom: 1px solid #ccc;"></td>
			</tr>
		</table>
	</div>
	<div class="title">三、暴露源情况：<a name="3"class="lemma-anchor maodian"></a></div>
	<div class="t_cont">
		<table class="info_table">
			<tr>
				<td colspan="8">
					<input id="formPatient" type="checkbox" onclick="return showBlForm(this)" <c:if test="${fn:contains(bl002Sjdj.sjBlqk,'1')}">checked="checked"</c:if> value="1" name="sjBlqk" /><label for="formPatient">&nbsp;<b>来源于患者</b></label>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="formPatient1">
				<th>${patientZyTitle}</th>
				<td style="width:110px;"><input type="text" name="wtjg115" value="${wtjg115}" style="width: 100px;"/></td>
				<th style="width:60px;">患者姓名</th>
				<td style="width:110px;"><input type="text" name="wtjg110" value="${wtjg110}" style="width: 100px;"/></td>
				<td style="width:60px;">联系电话</td>
				<td style="width:110px;"><input type="text" name="wtjg116" value="${wtjg116}" style="width: 100px;"/></td>
				<th style="width:60px;">科室</th>
				<td><input type="text" name="wtjg120" id="wtjg120" value="${wtjg120}" style="width: 120px;" class="easyui-validatebox"/></td>
			</tr>
			<tr id="formBb1">
				<th>患者感染性<br/>疾病情况：</th>
				<td colspan="5">
					<table class="info_table" style="border:solid 1px #ccc;">
					<tr>
						<td>
						<c:forEach items="${BTD}" var="btd" varStatus="c">
							<div class="ib" style="line-height: 25px;"><label><input type="checkbox" <c:if test="${fn:contains(wtjg132,btd.dictName)}">checked="checked"</c:if> value="${btd.dictName}" name="wtjg132" onclick="showOther(this);showThings(this,'wtjg133');" />&nbsp;${btd.dictName}</label>&nbsp;&nbsp;&nbsp;</div>
						</c:forEach>
						<input type="text" id="wtjg133" name="wtjg133" class="other" style="width: 70px; <c:if test="${!(fn:contains(wtjg132,'其他'))}">display: none;</c:if>" value="${wtjg133}"/>
						</td>
					</tr>
					</table>
				</td>
				<td colspan="2">
					<table class="info_table hiv_positive2" style='<c:if test="${!fn:contains(wtjg132,'HIV阳性患者')}">display: none;</c:if>'>
					<tr>
						<th>标本病毒含量：</th>
						<td><input type="text" name="wtjg96" value="${wtjg96}" style="width: 60px;"/></td>
					</tr>
					<tr>
						<th>CD4细胞计数(个/mL)：</th>
						<td><input type="text" name="wtjg126" value="${wtjg126}" style="width: 60px;"/></td>
					</tr>
					<tr>
						<th>病毒载量(拷贝/mL)：</th>
						<td><input type="text" name="wtjg136" value="${wtjg136}" style="width: 60px;"/></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr id="bl1">
				<td colspan="8">
					<input id="formBb" type="checkbox" onclick="return showBlForm(this)" <c:if test="${fn:contains(bl002Sjdj.sjBlqk,'2')}">checked="checked"</c:if> value="2" name="sjBlqk" /><label for="formBb">&nbsp;<b>来源于实验室标本</b></label>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="formPatient2">
				<th>${patientZyTitle}</th>
				<td style="width:110px;"><input type="text" id="wtjg105" name="wtjg105" value="${wtjg105}" style="width: 100px;"/></td>
				<th style="width:60px;">患者姓名</th>
				<td style="width:110px;"><input type="text" id="wtjg100" name="wtjg100" value="${wtjg100}"  style="width: 100px;"/></td>
				<td style="width:60px;">联系电话</td>
				<td style="width:110px;"><input type="text" name="wtjg106" value="${wtjg106}" style="width: 100px;"/></td>
				<th style="width:60px;">标本类型</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg90" value="${wtjg90}" csmId="004" headerKey="" headerValue="" exp="style='width:120px'" />
						</span>
					</span>
				</td>
			</tr>
			<tr id="formBb2">
				<th>患者感染性<br/>疾病情况：</th>
				<td colspan="5">
					<table class="info_table" style="border:solid 1px #ccc;">
					<%-- <tr>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg130,'无症状')}">checked="checked"</c:if> value="无症状" name="wtjg130" /><label for="wtjg130_1">&nbsp;无症状</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg130,'无症状乙肝携带者')}">checked="checked"</c:if> value="无症状乙肝携带者" name="wtjg130" /><label for="wtjg130_2">&nbsp;无症状乙肝携带者</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg130,'丙肝患者')}">checked="checked"</c:if> value="丙肝患者" name="wtjg130" /><label for="wtjg130_3">&nbsp;丙肝患者</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg130,'HIV感染者')}">checked="checked"</c:if> value="HIV感染者" name="wtjg130" /><label for="wtjg130_5">&nbsp;HIV感染着</label></td>
					</tr>
					<tr>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg130,'乙肝患者')}">checked="checked"</c:if> value="乙肝患者" name="wtjg130" /><label for="wtjg130_6">&nbsp;乙肝患者</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg130,'无症状梅病携带者')}">checked="checked"</c:if> value="无症状梅病携带者" name="wtjg130" /><label for="wtjg130_7">&nbsp;无症状梅病携带者</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg130,'艾滋病患者')}">checked="checked"</c:if> value="艾滋病患者" name="wtjg130" /><label for="wtjg130_8">&nbsp;艾滋病患者</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg130,'HCV携带者')}">checked="checked"</c:if> value="HCV携带者" name="wtjg130" /><label for="wtjg130_9">&nbsp;HCV携带者</label></td>
					</tr>
					<tr>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg130,'梅病患者')}">checked="checked"</c:if> value="梅病患者" name="wtjg130" /><label for="wtjg130_10">&nbsp;梅病患者</label></td>
						<td colspan="3"><input type="checkbox" value="其他" name="wtjg130" /><label for="wtjg130_4">&nbsp;其他</label>
						<input type="text" name="wtjg130" style="width: 50px;"/>
						</td>
					</tr> --%>
					<tr>
						<td>
						<c:forEach items="${BTD}" var="btd" varStatus="c">
							<div class="ib" style="line-height: 25px;"><label><input type="checkbox" <c:if test="${fn:contains(wtjg130,btd.dictName)}">checked="checked"</c:if> value="${btd.dictName}" name="wtjg130" onclick="showOther(this);showThings(this,'wtjg131');" />&nbsp;${btd.dictName}</label>&nbsp;&nbsp;&nbsp;</div>
						</c:forEach>
						<input type="text" id="wtjg131" name="wtjg131" class="other" style="width: 70px; <c:if test="${!(fn:contains(wtjg130,'其他'))}">display: none;</c:if>" value="${wtjg131}"/>
						</td>
					</tr>
					</table>
				</td>
				<td colspan="2">
					<table class="info_table hiv_positive" style='<c:if test="${!fn:contains(wtjg130,'HIV阳性患者')}">display: none;</c:if>'>
					<tr>
						<th>标本病毒含量：</th>
						<td><input type="text" name="wtjg95" value="${wtjg95}" style="width: 60px;"/></td>
					</tr>
					<tr>
						<th>CD4细胞计数(个/mL)：</th>
						<td><input type="text" name="wtjg125" value="${wtjg125}" style="width: 60px;"/></td>
					</tr>
					<tr>
						<th>病毒载量(拷贝/mL)：</th>
						<td><input type="text" name="wtjg135" value="${wtjg135}" style="width: 60px;"/></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr id="bl3">
				<td colspan="8">
					<input id="formBm" type="checkbox" onclick="return showBlForm(this)" <c:if test="${fn:contains(bl002Sjdj.sjBlqk,'3')}">checked="checked"</c:if> value="3" name="sjBlqk" /><label for="formBm">&nbsp;<b>来源不明</b></label>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="formBm1">
				<td>详细描述<br/>事情请过：</td>
				<td colspan="7"><input type="text" value="${bl002Sjdj.sjMemo}" name="sjMemo" style="width: 98%;"/></td>
			</tr>
			
		<%-- 	<tr id="bl2">
				<td colspan="8">
					<input id="formBb3" type="checkbox" onclick="showBlForm()" <c:if test="${fn:contains(bl002Sjdj.sjBlqk,'4')}">checked="checked"</c:if> value="4" name="sjBlqk" /><label for="formBb">&nbsp;<b>来源于实验室标本</b></label>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="bl4">
				<td colspan="8">
					<input id="formBm" type="checkbox" onclick="showBlForm()" <c:if test="${fn:contains(bl002Sjdj.sjBlqk,'3')}">checked="checked"</c:if> value="3" name="sjBlqk" /><label for="formBm">&nbsp;<b>来源不明</b></label>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr> --%>
			<tr>
				<td colspan="8" style="border-bottom: 1px solid #ccc;"></td>
			</tr>
		</table>
	</div>	
	<div class="title">四、暴露后处理情况：<a name="4"class="lemma-anchor maodian"></a></div>
	<input type="hidden" name="type" value="04"/>
	<div class="t_cont">
		<table class="info_table">
			<tr>
				<td colspan="6"><b>皮肤</b></td>
			</tr>
			<tr>
				<th>清水冲洗</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg140" value="${wtjg140}" csmId="021" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<th>冲洗时间(分)</th>
				<td><input type="text" value="${wtjg145}" name="wtjg145" style="width: 100px;"/></td>
				<th>皂液或洗手液清洗</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg150" value="${wtjg150}" csmId="021" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<th>挤出伤口血液</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg155" value="${wtjg155}" csmId="021" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<th>局部消毒</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg160" value="${wtjg160}" csmId="021" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<th>消毒剂名称</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg165" value="${wtjg165}" csmId="026" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<td colspan="6"><b>黏膜</b></td>
			</tr>
			<tr>
				<th>冲洗溶液</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg170" value="${wtjg170}" csmId="026" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<th>冲洗时间(分)</th>
				<td><input type="text" name="wtjg175" value="${wtjg175}" style="width:100px;"/></td>
				<th>暴露者签名</th>
				<td><input type="text" name="blzName" style="width:112px" /></td>
			</tr>
			<tr>
				<td><b>其他</b></td>
				<td colspan="5"><input type="text" name="wtjg180" value="${wtjg180}" style="width: 500px;"/></td>
			</tr>
			<tr>
				<th>事情经过</th><td></td>
				<th style="display:none;">建议处置</th><td></td>
				<th>科级意见</th><td><input type="hidden" name="onlySave" id="onlySave2"/></td>
			</tr>
			<tr>
				<td colspan="3"><textarea  name="pgMs" style="width:95%; height:60px;" class="easyui-validatebox">${bl002Sjdj.pgMs}</textarea></td>
				<%-- <td colspan="2"><textarea  name="pgJy" style="width:95%; height:60px;" class="easyui-validatebox">${bl002Sjdj.pgJy}</textarea></td> --%>
				<td colspan="3"><textarea  name="pgKjyj" style="width:95%; height:60px;" class="easyui-validatebox">${bl002Sjdj.pgKjyj}</textarea></td>
			</tr>
			<tr>
			<c:if test="${qm==1}">
			<tr>
				<th></th><td></td>
				<th></th><td></td>
				<th>签名</th><td>
				<c:choose><c:when test="${bl002Sjdj.kjyjQm eq null}"><a href="javascript:;" onclick="grxjbkQm('kjyj')"><i class="icon iconfont">&#xe658;</i><span>签名</span></a></c:when><c:otherwise><img src="${webroot}${kjyjDoctor.email}"  height="80" onclick="grxjbkQm('kjyj')"/></c:otherwise></c:choose>
<!-- 				<input type="text" id="qmDoctor" name="kjyjQm" style="width:112px" /> -->
				</td>
			</tr>
			</c:if>	
		</table>
		<div style="color:red">(1)&nbsp;&nbsp;一级暴露:&nbsp;暴露源沾染了有损伤的皮肤或者粘膜，量小且时间短</div>
		<div style="color:red">(2)&nbsp;&nbsp;二级暴露:&nbsp;暴露源沾染了有损伤的皮肤或者粘膜，量大且时间较长；或暴露源刺伤或割伤皮肤为轻度的表皮擦伤或针刺伤</div>
		<div style="color:red; margin-bottom:20px;">(3)&nbsp;&nbsp;三级暴露:&nbsp;暴露源刺伤或割伤皮肤为深度伤口或割伤物有明显可见的血液</div>
		<table class="info_table">
		<tr>
			<td colspan="8"  class="prevalence_save"><a class="ico_save" href="javascript:editFormWtjg02submitSave();" id="editFormWtjg02Button">保存</a> <a class="ico_save" href="javascript:editFormWtjg02submit();" id="editFormWtjg02Button">保存处理情况并申请上报</a> <a class="ico_del_1" href="javascript:void(0)" onclick="cancelSave()">取消</a></td>
		</tr>
		</table>
	</div>
	</form>	
	
	<form id="editFormWtjg05" method="post">
	<div class="title">五、职业暴露评估及处置：<a name="5"class="lemma-anchor maodian"></a></div>
	<input type="hidden" name="blId" value="${bl002Sjdj.blId}"/>
	<input type="hidden" name="type" value="05"/>
	<div class="t_cont">
		<table class="info_table">
			<c:choose>
			<c:when test="${jb eq 0}">
			<tr>
				<th width="60">暴露级别</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg185" value="${wtjg185}" csmId="019" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<th>暴露源严重程度</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg190" value="${wtjg190}" csmId="020" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>			
				
			</tr>
			</c:when>
			<c:when test="${jb eq 1}">
			<c:if test="${fn:contains(wtjg132, 'HIV阳性患者')}">
			<tr>
				<th width="60">暴露级别</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg185" value="${wtjg185}" csmId="019" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<th>暴露源严重程度</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg190" value="${wtjg190}" csmId="020" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>			
				
			</tr>
			</c:if>
			</c:when>
			<c:otherwise></c:otherwise>
			</c:choose>
			<tr>
				<th>血液检验项目</th>
				<td colspan="3">
					<table class="info_table" style="border:solid 1px #ccc;">
					<tr>
						<td><input type="checkbox" onClick="showFcTime()" <c:if test="${fn:contains(wtjg195,'HIV')}">checked="checked"</c:if> value="HIV" name="wtjg195" /><label for="wtjg195_1">&nbsp;HIV</label></td>
						<td><input type="checkbox" onClick="showFcTime()" tl="102" <c:if test="${fn:contains(wtjg195,'HCV')}">checked="checked"</c:if> value="HCV" name="wtjg195" /><label for="wtjg195_2">&nbsp;HCV</label></td>
						<td><input type="checkbox" onClick="showFcTime()" <c:if test="${fn:contains(wtjg195,'HBV')}">checked="checked"</c:if> value="HBV" name="wtjg195" /><label for="wtjg195_4">&nbsp;HBV</label></td>
						<%-- <td><input type="checkbox" <c:if test="${fn:contains(wtjg195,'暴露源不明')}">checked="checked"</c:if> value="暴露源不明" name="wtjg195" /><label for="wtjg195_3">&nbsp;暴露源不明</label></td> --%>
					</tr>
					<tr>
						
						<td><input type="checkbox" onClick="showFcTime()" <c:if test="${fn:contains(wtjg195,'梅毒')}">checked="checked"</c:if> value="梅毒" name="wtjg195" /><label for="wtjg195_5">&nbsp;梅毒</label></td>
						<td><input name="wtjg215" onClick="showFcTime()" <c:if test="${fn:contains(wtjg215,'血常规')}">checked="checked"</c:if> type="checkbox" value="血常规"  /><label for="wtjg215_1">&nbsp;血常规</label></td>
						<td><input name="wtjg215" onClick="showFcTime()" <c:if test="${fn:contains(wtjg215,'肝功能')}">checked="checked"</c:if> type="checkbox" value="肝功能"  /><label for="wtjg215_2">&nbsp;肝功能</label></td>
						<%-- <td>
						<input type="checkbox" <c:if test="${fn:contains(wtjg195,'其他')}">checked="checked"</c:if> value="其他" name="wtjg195" /><label for="wtjg195_6">&nbsp;其他</label>
						<input type="text"  name="wtjg195" style="width: 50px;"/>
						</td> --%>
					</tr>
					<tr>				
						<td><input name="wtjg215" <c:if test="${fn:contains(wtjg215,'肾功能')}">checked="checked"</c:if> type="checkbox" value="肾功能" /><label for="wtjg215_3">&nbsp;肾功能</label></td>
						<td><input type="checkbox" <c:if test="${fn:contains(wtjg215,'术前感染性指标')}">checked="checked"</c:if> value="术前感染性指标" name="wtjg215" /><label for="wtjg215_4">&nbsp;术前感染性指标</label></td>
						<td>
						<input type="checkbox"  <c:if test="${fn:contains(wtjg215,'其他')}">checked="checked"</c:if> value="其他" name="wtjg215" /><label for="wtjg215_6">&nbsp;其他</label>
						<input type="text" name="wtjg216" style="width: 50px;" value="${wtjg216}"/>
						</td>
					</tr>
					
					</table>
				</td>
			</tr>
		</table>
		<div class="easyui-layout" style="height:300px;width:100%">
			<div data-options="region:'west',border:false">
				<div id="clinicalObservationPlan"></div>
			</div>
			<div id="jyjgHtml"></div>
		</div>
		<table style="width:99%;">
			<tr>
				<td colspan="6" style="line-height: 20px;">&nbsp;</td>
			</tr>
			<tr>
			<c:if test="${!empty ZJCLYJ}">
				<th style="width:50%">专家处理意见</th><td></td><td></td>
			</c:if>
			<c:if test="${!empty XLZXSYJ}">
				<th>心理咨询师意见</th><td></td><td></td>
			</c:if>
			</tr>
			<tr>
				<c:if test="${!empty ZJCLYJ }">
					<td colspan="3">
						<textarea id="zjclYj"  name="zjclYj" style="width:95%; height:60px;" class="easyui-validatebox" >${bl002Sjdj.zjclYj}</textarea>
					</td>
				</c:if>
				<c:if test="${!empty XLZXSYJ}">
					<td colspan="3">
						<textarea id="xlzxsYj"  name="xlzxsYj"  style="width:95%; height:60px;" class="easyui-validatebox" >${bl002Sjdj.xlzxsYj}</textarea>
					</td>
				</c:if>
			</tr>
			<tr>
				<th style="line-height: 25px;">院感科意见</th><td></td><td></td>
				<th></th><td></td><td></td>
			</tr>
			<tr>
				<td colspan="3">
					<textarea id="ygkYj"  name="ygkYj" style="width:95%; height:60px;" class="easyui-validatebox" >${bl002Sjdj.ygkYj}</textarea>
				</td>
				<td colspan="3"></td>
			</tr>
		</table>
	<c:if test="${roleType!='clinical' or pgcz!='0' or (!empty canFill)}">	
	<table class="info_table">
	<tr>
		<td colspan="8"  class="prevalence_save">
			<a class="ico_save" href="javascript:saveList();" id="editFormWtjg05Button">保存评估及处理</a> 
		</td>
	</tr>
	</table>
	</c:if>
	</div>
	</form>	
	
	<form id="editFormWtjg06" method="post">
	<div class="title">六、暴露后预防性治疗方案：<a name="6"class="lemma-anchor maodian"></a></div>
	<input type="hidden" name="blId" value="${bl002Sjdj.blId}"/>
	<input type="hidden" name="type" value="06"/>
	<div class="t_cont">
		<table class="info_table">
			<tr>
				<td width="80"  style="vertical-align: top; line-height:26px;">1、预防性用药</td>
				<td  style="vertical-align: top">
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg200" value="${wtjg200}" csmId="021" headerKey="" headerValue="" exp="style='width:112px' onChange='showYW(this.value)'" />
						</span>
					</span>
				</td>
				<td colspan="2">
					<table class="info_table" id="ywTab" style="border:solid 1px #ccc;">
					<tr>
						<td></td>
						<td><label>使用药物名称</label></td>
						<td><label>剂量</label></td>
						<td><label>开始用药时间</label></td>
						<td><label>停止用药时间</label></td>
					</tr>
					<tr>
						<td>1</td>
						<td><nis:blselect name="pgYyname1" value="${bl002Sjdj.pgYyname1}" csmId="210" headerKey="" headerValue="" exp="style='width:112px'" /> </td>
						<td><input type="text" value="${bl002Sjdj.pgYyjl1}" name="pgYyjl1" style="width: 40px;"/></td>
						<td><input type="text" value="${bl002Sjdj.pgYystime1}" name="pgYystime1" style="width: 80px;" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
						<td><input type="text" value="${bl002Sjdj.pgYyetime1}" name="pgYyetime1" style="width: 80px;" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
					</tr>
					<tr>
						<td>2</td>
						<td><nis:blselect name="pgYyname2" value="${bl002Sjdj.pgYyname2}" csmId="210" headerKey="" headerValue="" exp="style='width:112px'" /></td>
						<td><input type="text" value="${bl002Sjdj.pgYyjl2}" name="pgYyjl2" style="width: 40px;"/></td>
						<td><input type="text" value="${bl002Sjdj.pgYystime2}" name="pgYystime2" style="width: 80px;" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
						<td><input type="text" value="${bl002Sjdj.pgYyetime2}" name="pgYyetime2" style="width: 80px;" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
					</tr>
					<tr>
						<td>3</td>
						<td><nis:blselect name="pgYyname3" value="${bl002Sjdj.pgYyname3}" csmId="210" headerKey="" headerValue="" exp="style='width:112px'" /></td>
						<td><input type="text" value="${bl002Sjdj.pgYyjl3}" name="pgYyjl3" style="width: 40px;"/></td>
						<td><input type="text" value="${bl002Sjdj.pgYystime3}" name="pgYystime3" style="width: 80px;" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
						<td><input type="text" value="${bl002Sjdj.pgYyetime3}" name="pgYyetime3" style="width: 80px;" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
					</tr>
					<tr>
						<td>4</td>
						<td><nis:blselect name="pgYyname4" value="${bl002Sjdj.pgYyname4}" csmId="210" headerKey="" headerValue="" exp="style='width:112px'" /></td>
						<td><input type="text" value="${bl002Sjdj.pgYyjl4}" name="pgYyjl4" style="width: 40px;"/></td>
						<td><input type="text" value="${bl002Sjdj.pgYystime4}" name="pgYystime4" style="width: 80px;" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
						<td><input type="text" value="${bl002Sjdj.pgYyetime4}" name="pgYyetime4" style="width: 80px;" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
					</tr>
					<tr>
						<td>5</td>
						<td><nis:blselect name="pgYyname5" value="${bl002Sjdj.pgYyname5}" csmId="210" headerKey="" headerValue="" exp="style='width:112px'" /></td>
						<td><input type="text" value="${bl002Sjdj.pgYyjl5}" name="pgYyjl5" style="width: 40px;"/></td>
						<td><input type="text" value="${bl002Sjdj.pgYystime5}" name="pgYystime5" style="width: 80px;" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
						<td><input type="text" value="${bl002Sjdj.pgYyetime5}" name="pgYyetime5" style="width: 80px;" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
					</tr>
					</table>
				</td>
			</tr>
			<c:if test="${qm==1}">
				<c:if test="${(!empty canFill)}">			
				<tr>
					<td></td><td></td><th width="400">感染性疾病科签名：</th><td><c:choose><c:when test="${bl002Sjdj.grxjbkQm eq null}"><a href="javascript:;" onclick="grxjbkQm('grxjbk')"><i class="icon iconfont">&#xe658;</i><span>签名</span></a></c:when><c:otherwise><img src="${webroot}${grxjbkDoctor.email}"  height="80" onclick="grxjbkQm('grxjbk')"/></c:otherwise></c:choose></td>
				</tr>
				</c:if>
			</c:if>	
			<c:if test="${roleType!='clinical' or pgcz=='2' or (!empty canFill)}">
			<tr>
				<td colspan="8"  class="prevalence_save"><a class="ico_save" href="javascript:editFormWtjg06submit();" id="editFormWtjg06Button" >保存并完成预防评估</a> <a class="ico_del_1" href="javascript:void(0)" onclick="cancelSave()">取消</a></td>
			</tr>
		</table>
		<table class="info_table">
			</c:if>
			<c:if test="${qm==1}">
			<%-- <tr>
				<th>院感科意见</th><td></td>
				<th>财务科意见</th><td></td>
			</tr>
			<tr>
				<td colspan="2"><textarea  name="ygkYj" id="ygkYj" style="width:95%; height:60px;" class="easyui-validatebox">${bl002Sjdj.ygkYj}</textarea></td>
				<td colspan="2"><textarea  name="cwkYj" id="cwkYj" style="width:95%; height:60px;" class="easyui-validatebox">${bl002Sjdj.cwkYj}</textarea></td>
			</tr>			
				<tr>
					<th width="200">院感科签名：</th><td><c:choose><c:when test="${bl002Sjdj.ygkQm eq null}"><a href="javascript:;" onclick="grxjbkQm('ygk')"><i class="icon iconfont">&#xe658;</i><span>签名</span></a></c:when><c:otherwise><img src="${webroot}${ygkDoctor.email}"  height="80" onclick="grxjbkQm('ygk')"/></c:otherwise></c:choose></td>
					<th width="200">财务科签名：</th><td><c:choose><c:when test="${bl002Sjdj.cwkQm eq null}"><a href="javascript:;" onclick="grxjbkQm('cwk')"><i class="icon iconfont">&#xe658;</i><span>签名</span></a></c:when><c:otherwise><img src="${webroot}${cwkDoctor.email}" height="80" onclick="grxjbkQm('cwk')"/></c:otherwise></c:choose></td>
				</tr> --%>
			</c:if>
		</table>
	</div>
	</form>	
	
	<form id="editFormWtjg07" method="post">
	<div class="title">七、跟踪随访：<a name="7"class="lemma-anchor maodian"></a></div>
	<input type="hidden" name="blId" value="${bl002Sjdj.blId}"/>
	<input type="hidden" name="type" value="07"/>
	<div class="t_cont">
		<table class="info_table">
			<tr>
				<td height="20">用药后不良反应
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="wtjg205" id="wtjg205" value="${wtjg205}"  csmId="021" headerKey="" headerValue="" exp="style='width:112px' onclick='chooseNo(205,210)'" />
						</span>
					</span>
					&nbsp;&nbsp;&nbsp;&nbsp;具体表现&nbsp;
					<input type="text" id="wtjg210" value="${wtjg210}" name="wtjg210" style="width: 100px;"/>
				</td>
			</tr>
			<tr>
				<td height="20" >
					1、是否在四周内出现急性感染症状
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect value="${wtjg220}" id="wtjg220" name="wtjg220" csmId="021" headerKey="" headerValue="" exp="style='width:112px' onclick='chooseNo(220,225)'" />
						</span>
					</span>
					&nbsp;&nbsp;&nbsp;&nbsp;
					持续时间(天)&nbsp;
					<input type="text" id="wtjg225" value="${wtjg225}" name="wtjg225" style="width: 100px;"/>
				</td>
			</tr>
			<tr>
				<td>
					2、具体症状&nbsp;<input type="text" value="${wtjg230}" name="wtjg230" style="width: 435px;"/>				
					
				</td>
			</tr>
			<tr>
				<td>
					3、随访情况(HIV、HBV、HCV、RPRR血清学检查及结果)
					<input type="hidden" name="onlySave" id="onlySave7"/>
				</td>
			</tr>
		</table>
		<div class="easyui-layout" style="height:300px;width:100%">
			<div data-options="region:'west',border:false">
				<div id="tb" class="m_search">
				检验项目:<select id="jyxmTb"  onChange="clinicalObservation2.query()"><option></option></select> &nbsp;时间点:<select id="timeTb" onChange="clinicalObservation2.query()"><option></option></select>
				</div>
				<div id="clinicalObservationPlan2"></div>
			</div> 
		</div>
		<table class="info_table">
		<tr>
			<td style="width:550px"></td>
		<td><span style="padding-left:15px">签名&nbsp;<input type="text" id="gcName" name="gcName" style="width:150px" /></span>	</td>
		</tr>
		</table>
		<c:if test="${roleType!='clinical'}">
			<table class="info_table">
				<tr>
					<td colspan="8"  class="prevalence_save"><a class="ico_save" id="editFormWtjg07Button" href="javascript:editFormWtjg07submitSave();">保存</a> <a class="ico_save" id="editFormWtjg07Button" href="javascript:editFormWtjg07submit();">保存并完成跟踪随访</a> <a class="ico_del_1" href="javascript:void(0)" onclick="cancelSave()">取消</a></td>
				</tr>
			</table>
		</c:if>
	</div>
	</form>
	
	<form id="editFormWtjg08" method="post">
	<div class="title">八、结论：<a name="8"class="lemma-anchor maodian"></a></div>
	<input type="hidden" name="blId" value="${bl002Sjdj.blId}"/>
	<input type="hidden" name="type" value="08" />
	<div class="t_cont mb60">
		<table class="info_table">
			<tr>
				<th width="60">事件结论</th>
				<td>
					<span class="standard_select">
						<span class="select_shelter">	
							<nis:blselect name="blJl" value="${bl002Sjdj.blJl}" csmId="033" headerKey="" headerValue="" exp="style='width:112px'" />
						</span>
					</span>
				</td>
				<th>感染诊断</th>
				<td><input type="text" name="wtjg240" value="${wtjg240}" style="width: 500px;"/></td>
			</tr>
			<tr>
				<th>结论确认人</th>
				<td colspan="3"><input type="text" id="qjDoctor" name="blJlMen" style="width:112px" /></td>
			</tr>
			<c:if test="${roleType!='clinical'}">
			<tr>
				<td colspan="8"  class="prevalence_save"><a class="ico_save" id="editFormWtjg08Button" href="javascript:editFormWtjg08submit();" >保存结论</a> <a class="ico_del_1" href="javascript:void(0)" onclick="cancelSave()">取消</a></td>
			</tr>
			</c:if>
		</table>
	</div>
	</form>
</div>
<div id="prevalence_note"> 
<div class="side-catalog" style="visibility: visible; top: 10px;">
		<div class="side-bar">
			<em class="circle start"></em>
			<em class="circle end"></em>
		</div>
		<div class="catalog-scroller">
			<dl class="catalog-list">
				<dt class="catalog-title level1 <c:if test="${bl002Sjdj.blStep>=1}">complete</c:if>">
					<em class="pointer"></em>
					<span class="text">
						<a href="#1" class="title-link" nslog-type="1026">基本情况</a>
					</span>
				</dt>
				<dt class="catalog-title level1 <c:if test="${bl002Sjdj.blStep>=2}">complete</c:if>">
					<em class="pointer"></em>
					<span class="text">
						<a href="#2" class="title-link" nslog-type="1026">暴露方式</a>
					</span>
				</dt>
				<dt class="catalog-title level1 <c:if test="${bl002Sjdj.blStep>=3}">complete</c:if>">
					<em class="pointer"></em>
					<span class="text">
						<a href="#3" class="title-link" nslog-type="1026">暴露源情况</a>
					</span>
				</dt>
				<dt class="catalog-title level1 <c:if test="${bl002Sjdj.blStep>=4}">complete</c:if>">
					<em class="pointer"></em>
					<span class="text">
						<a href="#4" class="title-link" nslog-type="1026">暴露后处理情况</a>
					</span>
				</dt>
				<dt class="catalog-title level1 <c:if test="${bl002Sjdj.blStep>=5}">complete</c:if>">
					<em class="pointer"></em>
					<span class="text">
						<a href="#5" class="title-link" nslog-type="1026">职业暴露评估及处置</a>
					</span>
				</dt>
				<dt class="catalog-title level1 <c:if test="${bl002Sjdj.blStep>=6}">complete</c:if>">
					<em class="pointer"></em>
					<span class="text">
						<a href="#6" class="title-link" nslog-type="1026">暴露后预防性治疗方案</a>
					</span>
				</dt>
				<dt class="catalog-title level1 <c:if test="${bl002Sjdj.blStep>=7}">complete</c:if>">
					<em class="pointer"></em>
					<span class="text">
						<a href="#7" class="title-link" nslog-type="1026">跟踪随访</a>
					</span>
				</dt>
				<dt class="catalog-title level1 <c:if test="${bl002Sjdj.blStep>=8}">complete</c:if>">
					<em class="pointer"></em>
					<span class="text">
						<a href="#8" class="title-link" nslog-type="1026">结论</a>
					</span>
				</dt>							
				<a class="arrow" href="javascript:void(0);" ></a>
			</dl>
		</div>		 
	</div>
</div> 
</div>
<!-- <div class="footer">
	<input type="button" class="btn_save" id="changeFormSubmitBtn" onclick="$('#editFormICD9').submit()" value="保存">
</div> -->		
<script type="text/javascript">
	var isClick = true;
	function chooseNo(value1,value2){
		$('#wtjg'+value2).attr("disabled",false);
		if($('#wtjg'+value1).val()=='02'){
			$('#wtjg'+value2).attr("disabled",true);
		}
	}
	function editFormWtjg01submit(){
		 if(isClick){
			 isClick = false;
			$('#editFormWtjg01').submit();
		}
	}
	function editFormWtjg02submit(){
		$('#onlySave2').val(0);
		$('#editFormWtjg02').submit();
	}
	function editFormWtjg02submitSave(){
		$('#onlySave2').val(1);
		$('#editFormWtjg02').submit();
	}
	function editFormWtjg06submit(){
		$('#editFormWtjg06').submit();
	}
	function editFormWtjg07submit(){
		$('#onlySave7').val(0);
		$('#editFormWtjg07').submit();
	}
	function editFormWtjg07submitSave(){
		$('#onlySave7').val(1);
		$('#editFormWtjg07').submit();
	}
	function editFormWtjg08submit(){
		$('#editFormWtjg08').submit();
	}
	function findDoctorById(id){
		$.ajax({
            url: '${webroot}/doctor/json/findByid.shtml',
            type: 'post',
            data: { id:id},
            dataType: 'json',
            success : function(json) {
            	if(json.result=='success'){
            		$('#djName').val(json.data.employeeName);
            		$('#djDept').combogrid('setValue',json.data.deptId);
            	}
            }
		});
	}
	function findPatientById(id){
		$.ajax({
            url: '${webroot}/st003Cryxxb/f_json/findByid.shtml',
            type: 'post',
            data: { id:id},
            dataType: 'json',
            success : function(json) {
            	if(json.result=='success'){
            		$('#wtjg110').val(json.data.patientName);
            		$('#wtjg120').combogrid('setValue',json.data.deptCode);
            	}
            }
		});
	}
	function wancen(jyDh,jyHm,index){
		var jyTime = $("input[name='bl006JyjgList["+index+"].jyTime']").val();
		var jyJg = $("select[name='bl006JyjgList["+index+"].jyJg']").val();
		var sfMemo = $("input[name='bl006JyjgList["+index+"].sfMemo']").val();
		if(jyTime!=''&&jyJg!=''){		
           	$.ajax({
                       url: '${webroot}/bl006Jyjg/f_json/updFlag.shtml',
                       type: 'post',
                       data: { jyDh:jyDh,jyHm:jyHm,blId:'${bl002Sjdj.blId}',jyTime:jyTime,jyJg:jyJg,sfMemo:sfMemo},
                       dataType: 'json',
                       success : function(json) {
						if(json.result==='success') {
							clinicalObservation2.query();
                            $.messager.show({ title: '提示', msg: '成功！' });
				    	} else if(json.result === 'error') {
				    		$.messager.show({ title: '提示', msg: '系统异常！' });
				    	} else {
				    		$.messager.show({ title: '提示', msg: json.msg });
				    	}
					}
           	});    	
		}else{
			$.messager.show({ title: '提示', msg: '检验时间与检验结果不能为空！' });
		}
	}
	function showYW(value){
		if(value==1){
			$("#ywTab").show();
		}else{
			$("#ywTab").hide();
			$("#ywTab select").val("");
			$("#ywTab input").val("");
		}
	}
	function showBlForm(ele){
		var e = e || window.event;
		if(e){
			if(!$(ele).is(":checked")){
				if(confirm("取消勾选将清空本栏内容，是否确认？")){}else{
					return false;
				}
			}
		}
		
		$('input[name="sjBlqk"]').each(function(){ 
			if($(this).is(":checked")){
				var value = $(this).val();
				if(value=='1'){
					$("#formPatient1").show();
					$("#formBb1").show();
				}else if(value=='2'){
					$("#formPatient2").show();
					$("#formBb2").show();
				}
			}else{
				var value = $(this).val();
				if(value=='1'){
					$("#formPatient1").hide();
					$("#formPatient1 input:text").attr("value","");
					$("#formPatient1 select").attr("value","");
					$("#formBb1").hide();
					$("#formBb1 input:checkbox").removeAttr("checked");
					$("#formBb1 .other").val("").hide();
					//
					$("#wtjg120").combogrid("setValue","");
					$("#formBb1 .hiv_positive2 input:text").attr("value","");
					$("#formBb1 .hiv_positive2").hide();
				}else if(value=='2'){
					$("#formPatient2").hide();
					$("#formPatient2 input:text").attr("value","");
					$("#formPatient2 select").attr("value","");
					$("#formBb2").hide();
					$("#formBb2 input:checkbox").removeAttr("checked");
					$("#formBb2 .other").val("").hide();
					$("#formBb2 .hiv_positive input:text").attr("value","");
					$("#formBb2 .hiv_positive").hide();
				}
			}
		});
	
	}
	function showBlMode(val){
	   		$("#jiechu1 input:text").attr("disabled",true);
			$("#jiechu1 input:checkbox").attr("disabled",true);
			$("#jiechu1 select").attr("disabled",true);
			$("#jiechu2 input:text").attr("disabled",true);
			$("#jiechu2 input:checkbox").attr("disabled",true);
			$("#jiechu2 select").attr("disabled",true);
			$("#jiechu3 input:text").attr("disabled",true);
			$("#jiechu3 input:checkbox").attr("disabled",true);
			$("#jiechu3 select").attr("disabled",true);
			$("#jiechu1").hide();
	   		$("#jiechu2").hide();
	   		$("#jiechu3").hide();
	   		$("#zhenchi1 input:text").attr("disabled",true);
			$("#zhenchi1 input:checkbox").attr("disabled",true);
			$("#zhenchi1 select").attr("disabled",true);
			$("#zhenchi2 input:text").attr("disabled",true);
			$("#zhenchi2 input:checkbox").attr("disabled",true);
			$("#zhenchi2 select").attr("disabled",true);
			$("#zhenchi3 input:text").attr("disabled",true);
			$("#zhenchi3 input:checkbox").attr("disabled",true);
			$("#zhenchi3 select").attr("disabled",true);
			$("#zhenchi4 input:text").attr("disabled",true);
			$("#zhenchi4 input:checkbox").attr("disabled",true);
			$("#zhenchi4 select").attr("disabled",true);
			$("#zhenchi1").hide();
		    $("#zhenchi2").hide();
		   	$("#zhenchi3").hide();
		   	$("#zhenchi4").hide();
		   	$("#qita input:text").attr("disabled",true);
			$("#qita input:checkbox").attr("disabled",true);
			$("#qita select").attr("disabled",true);
			$("#qita").hide();
	   	if(val=='接触'){
			$("#jiechu1 input:checkbox").attr("checked",false);
			$("#jiechu1 input:text").attr("value","");
			$("#jiechu1 select").attr("value","");
			$("#jiechu2 input:checkbox").attr("checked",false);
			$("#jiechu2 input:text").attr("value","");
			$("#jiechu2 select").attr("value","");
			$("#jiechu3 input:checkbox").attr("checked",false);
			$("#jiechu3 input:text").attr("value","");
			$("#jiechu3 select").attr("value","");
	   	}else if(val=='针刺伤或锐器割伤'){
			$("#zhenchi1 input:checkbox").attr("checked",false);
			$("#zhenchi1 input:text").attr("value","");
			$("#zhenchi1 select").attr("value","");
			$("#zhenchi2 input:checkbox").attr("checked",false);
			$("#zhenchi2 input:text").attr("value","");
			$("#zhenchi2 select").attr("value","");
			$("#zhenchi3 input:checkbox").attr("checked",false);
			$("#zhenchi3 input:text").attr("value","");
			$("#zhenchi3 select").attr("value","");
			$("#zhenchi4 input:checkbox").attr("checked",false);
			$("#zhenchi4 input:text").attr("value","");
			$("#zhenchi4 select").attr("value","");
			$("#wtjg40").combobox("clear");
	   	}else if(val=='其他方式'){
			$("#qita input:checkbox").attr("checked",false);
			$("#qita input:text").attr("value","");
			$("#qita select").attr("value","");
	   	}
		$('input[name="wtjg34"]:checked').each(function(){    
			var value = $(this).val();
		   	if(value=='接触'){
		   		$("#jiechu1").show();
		   		$("#jiechu2").show();
		   		$("#jiechu3").show();
				$("#jiechu1 input:text").attr("disabled",false);
				$("#jiechu1 input:checkbox").attr("disabled",false);
				$("#jiechu1 select").attr("disabled",false);
				$("#jiechu2 input:text").attr("disabled",false);
				$("#jiechu2 input:checkbox").attr("disabled",false);
				$("#jiechu2 select").attr("disabled",false);
				$("#jiechu3 input:text").attr("disabled",false);
				$("#jiechu3 input:checkbox").attr("disabled",false);
				$("#jiechu3 select").attr("disabled",false);
			}else if(value=='针刺伤或锐器割伤'){
		   		$("#zhenchi1").show();
		   		$("#zhenchi2").show();
		   		$("#zhenchi3").show();
		   		$("#zhenchi4").show();
				$("#zhenchi1 input:text").attr("disabled",false);
				$("#zhenchi1 input:checkbox").attr("disabled",false);
				$("#zhenchi1 select").attr("disabled",false);
				$("#zhenchi2 input:text").attr("disabled",false);
				$("#zhenchi2 input:checkbox").attr("disabled",false);
				$("#zhenchi2 select").attr("disabled",false);
				$("#zhenchi3 input:text").attr("disabled",false);
				$("#zhenchi3 input:checkbox").attr("disabled",false);
				$("#zhenchi3 select").attr("disabled",false);
				$("#zhenchi4 input:text").attr("disabled",false);
				$("#zhenchi4 input:checkbox").attr("disabled",false);
				$("#zhenchi4 select").attr("disabled",false);
			}else if(value=='其他方式'){
				$("#qita").show();
				$("#qita input:text").attr("disabled",false);
				$("#qita input:checkbox").attr("disabled",false);
				$("#qita select").attr("disabled",false);
			}
		});
	}
	function grxjbkQm(qmType){
		var ygkYj = encodeURIComponent(encodeURIComponent($('#ygkYj').val()));
		var cwkYj = encodeURIComponent(encodeURIComponent($('#cwkYj').val()));
		Comm.dialog({
        	url:"${webroot}/bl002Sjdj/f_view/qmDetail.shtml?blId=${bl002Sjdj.blId}&qmType="+qmType+"&ygkYj="+ygkYj+"&cwkYj="+cwkYj,
            title: "签名",
            width:350,
            height:220,
        });
	}
	function cancelSave(){
		$.ajax({
            url: '${webroot}/bl002Sjdj/f_json/cancelSave.shtml',
            type: 'post',
            data: { blId:'${bl002Sjdj.blId}'},
            dataType: 'json',
            success : function(json) {
            	if(json.result=='success'){
            		location.reload();
            	}
            }
		});
	}
	//保存
	function saveList(){
		var curRow = $('#clinicalObservationPlan').datagrid('getSelections');
		if (curRow.length>0) {
			var jyjgHtml='';
			for(var i=0;i<curRow.length;i++){
        		jyjgHtml=jyjgHtml+'<input type="hidden" name="bl004CsDetailinfoList['+i+'].itemName" value="'+curRow[i].jyHm+'">';
        		jyjgHtml=jyjgHtml+'<input type="hidden" name="bl004CsDetailinfoList['+i+'].csdName" value="'+curRow[i].jyDh+'">';
        		//因后来将时间改为可修改，所以需要拿到元素取值
        		//jyjgHtml=jyjgHtml+'<input type="hidden" name="bl004CsDetailinfoList['+i+'].fcDate" value="'+curRow[i].djTime+'">';
        		var c = $(curRow[i].djTime).attr('name');
        		var dTv = $("input[name='"+c+"']").val();
        		jyjgHtml=jyjgHtml+'<input type="hidden" name="bl004CsDetailinfoList['+i+'].fcDate" value="'+dTv+'">';
        		
        		jyjgHtml=jyjgHtml+'<input type="hidden" name="bl004CsDetailinfoList['+i+'].csmId" value="'+curRow[i].csmId+'">';
        		jyjgHtml=jyjgHtml+'<input type="hidden" name="bl004CsDetailinfoList['+i+'].csdId" value="'+curRow[i].csdId+'">';
        	}
			$("#jyjgHtml").html(jyjgHtml);
		}
		$('#editFormWtjg05').submit();
	}
	function showHide(){
		//根据状态屏蔽按钮和输入内容
		if(!sjState){
			//新增登记
			$("#noedit2").css("height",$("#editFormWtjg02").height()+"px").show();
			$("#noedit2").click(function(){$.messager.alert("提示","请先填写完整并保存基础信息！")});
		}
		if(!sjState || sjState=='0'||sjState=='2'){//登记，屏蔽5，6，7，8
			$("#editFormBl002jdj input:text").attr("disabled",false);
			$("#editFormBl002jdj input:checkbox").attr("disabled",false);
			$("#editFormBl002jdj select").attr("disabled",false);
			$("#editFormBl002jdj textarea").attr("disabled",false);
			$("#editFormBl002jdj .ico_save").show();
			$("#editFormBl002jdj .ico_del_1").hide();
			for(var i=1;i<6;i++){
				$("#editFormWtjg0"+i+" input:text").attr("disabled",false);
				$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",false);
				$("#editFormWtjg0"+i+" input:radio").attr("disabled",false);
				$("#editFormWtjg0"+i+" select").attr("disabled",false);
				$("#editFormWtjg0"+i+" textarea").attr("disabled",false);
				$("#editFormWtjg0"+i+" .ico_save").show();
				$("#editFormWtjg0"+i+" .ico_del_1").hide();
			}
			for(var i=5;i<9;i++){
				$("#editFormWtjg0"+i).hide();
				$("#editFormWtjg0"+i+" input:text").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:radio").attr("disabled",true);
				$("#editFormWtjg0"+i+" select").attr("disabled",true);
				$("#editFormWtjg0"+i+" textarea").attr("disabled",true);
				$("#editFormWtjg0"+i+" .ico_save").hide();
				$("#editFormWtjg0"+i+" .ico_del_1").hide();
			}
		}else if(sjState=='1'||sjState=='4'){//已上报屏蔽+
			
			$("#editFormBl002jdj input:text").attr("disabled",true);
			$("#editFormBl002jdj input:checkbox").attr("disabled",true);
			$("#editFormBl002jdj select").attr("disabled",true);
			$("#editFormBl002jdj textarea").attr("disabled",true);
			$("#editFormBl002jdj .ico_save").hide();
			$("#editFormBl002jdj .ico_del_1").hide();
			for(var i=5;i<7;i++){
				$("#editFormWtjg0"+i+" input:text").attr("disabled",false);
				$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",false);
				$("#editFormWtjg0"+i+" input:radio").attr("disabled",false);
				$("#editFormWtjg0"+i+" select").attr("disabled",false);
				$("#editFormWtjg0"+i+" textarea").attr("disabled",false);
				$("#editFormWtjg0"+i+" .ico_save").show();
				$("#editFormWtjg0"+i+" .ico_del_1").hide();
			}
			for(var i=1;i<5;i++){
				$("#editFormWtjg0"+i+" input:text").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:radio").attr("disabled",true);
				$("#editFormWtjg0"+i+" select").attr("disabled",true);
				$("#editFormWtjg0"+i+" textarea").attr("disabled",true);
				$("#editFormWtjg0"+i+" .ico_save").hide();
				$("#editFormWtjg0"+i+" .ico_del_1").show();
			}
			for(var i=7;i<9;i++){
				$("#editFormWtjg0"+i+" input:text").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:radio").attr("disabled",true);
				$("#editFormWtjg0"+i+" select").attr("disabled",true);
				$("#editFormWtjg0"+i+" textarea").attr("disabled",true);
				$("#editFormWtjg0"+i+" .ico_save").hide();
				$("#editFormWtjg0"+i+" .ico_del_1").hide();
			}
		}else if(sjState=='5'||sjState=='6'){
			$("#editFormBl002jdj input:text").attr("disabled",true);
			$("#editFormBl002jdj input:checkbox").attr("disabled",true);
			$("#editFormBl002jdj select").attr("disabled",true);
			$("#editFormBl002jdj textarea").attr("disabled",true);
			$("#editFormBl002jdj .ico_save").hide();
			$("#editFormBl002jdj .ico_del_1").hide();
			for(var i=1;i<7;i++){
				$("#editFormWtjg0"+i+" input:text").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:radio").attr("disabled",true);
				$("#editFormWtjg0"+i+" select").attr("disabled",true);
				$("#editFormWtjg0"+i+" textarea").attr("disabled",true);
				$("#editFormWtjg0"+i+" .ico_save").hide();
				if(i==6){
					$("#editFormWtjg0"+i+" .ico_del_1").show();
				}else{
					$("#editFormWtjg0"+i+" .ico_del_1").hide();
				}
			}
			<c:if test="${roleType!='clinical'}">
			$("#editFormWtjg07 input:text").attr("disabled",false);
			$("#editFormWtjg07 input:checkbox").attr("disabled",false);
			$("#editFormWtjg07 input:radio").attr("disabled",false);
			$("#editFormWtjg07 select").attr("disabled",false);
			$("#editFormWtjg07 textarea").attr("disabled",false);
			</c:if>
			<c:if test="${roleType=='clinical'}">
			$("#editFormWtjg07 input:text").attr("disabled",true);
			$("#editFormWtjg07 input:checkbox").attr("disabled",true);
			$("#editFormWtjg07 input:radio").attr("disabled",true);
			$("#editFormWtjg07 select").attr("disabled",true);
			$("#editFormWtjg07 textarea").attr("disabled",true);
			</c:if>
			$("#editFormWtjg07 .ico_save").show();
			$("#editFormWtjg07 .ico_del_1").hide();
			
			$("#editFormWtjg08 input:text").attr("disabled",true);
			$("#editFormWtjg08 input:checkbox").attr("disabled",true);
			$("#editFormWtjg08 input:radio").attr("disabled",true);
			$("#editFormWtjg08 select").attr("disabled",true);
			$("#editFormWtjg08 textarea").attr("disabled",true);
			$("#editFormWtjg08 .ico_save").hide();
			$("#editFormWtjg08 .ico_del_1").hide();
		}else if(sjState=='7'){
			$("#editFormBl002jdj input:text").attr("disabled",true);
			$("#editFormBl002jdj input:checkbox").attr("disabled",true);
			$("#editFormBl002jdj select").attr("disabled",true);
			$("#editFormBl002jdj textarea").attr("disabled",true);
			$("#editFormBl002jdj .ico_save").hide();
			$("#editFormBl002jdj .ico_del_1").hide();
			for(var i=1;i<8;i++){
				$("#editFormWtjg0"+i+" input:text").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:radio").attr("disabled",true);
				$("#editFormWtjg0"+i+" select").attr("disabled",true);
				$("#editFormWtjg0"+i+" textarea").attr("disabled",true);
				if(i==7){
					$("#editFormWtjg0"+i+" .ico_del_1").show();
				}else{
					$("#editFormWtjg0"+i+" .ico_del_1").hide();
				}
				$("#editFormWtjg0"+i+" .ico_save").hide();
			}
			for(var i=8;i<9;i++){
				$("#editFormWtjg0"+i+" input:text").attr("disabled",false);
				$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",false);
				$("#editFormWtjg0"+i+" input:radio").attr("disabled",false);
				$("#editFormWtjg0"+i+" select").attr("disabled",false);
				$("#editFormWtjg0"+i+" textarea").attr("disabled",false);
				$("#editFormWtjg0"+i+" .ico_save").show();
				$("#editFormWtjg0"+i+" .ico_del_1").hide();
			}
		}else if(sjState=='9'){
			$("#editFormBl002jdj input:text").attr("disabled",true);
			$("#editFormBl002jdj input:checkbox").attr("disabled",true);
			$("#editFormBl002jdj select").attr("disabled",true);
			$("#editFormBl002jdj textarea").attr("disabled",true);
			$("#editFormBl002jdj .ico_save").hide();
			$("#editFormBl002jdj .ico_del_1").hide();
			for(var i=1;i<9;i++){
				$("#editFormWtjg0"+i+" input:text").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",true);
				$("#editFormWtjg0"+i+" input:radio").attr("disabled",true);
				$("#editFormWtjg0"+i+" select").attr("disabled",true);
				$("#editFormWtjg0"+i+" textarea").attr("disabled",true);
				if(i==8){
					$("#editFormWtjg0"+i+" .ico_del_1").show();
				}else{
					$("#editFormWtjg0"+i+" .ico_del_1").hide();
				}
				$("#editFormWtjg0"+i+" .ico_save").hide();
			}
		}
		<c:if test="${roleType=='clinical' and pgcz=='0' and (empty canFill)}">	
		$("#editFormBl002jdj input:text").attr("disabled",true);
		$("#editFormBl002jdj input:checkbox").attr("disabled",true);
		$("#editFormBl002jdj select").attr("disabled",true);
		$("#editFormBl002jdj textarea").attr("disabled",true);
		$("#editFormBl002jdj .ico_save").hide();
		$("#editFormBl002jdj .ico_del_1").hide();
		for(var i=5;i<7;i++){
			$("#editFormWtjg0"+i+" input:text").attr("disabled",true);
			$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",true);
			$("#editFormWtjg0"+i+" input:radio").attr("disabled",true);
			$("#editFormWtjg0"+i+" select").attr("disabled",true);
			$("#editFormWtjg0"+i+" textarea").attr("disabled",true);
			$("#editFormWtjg0"+i+" .ico_save").hide();
			$("#editFormWtjg0"+i+" .ico_del_1").show();
		}
		for(var i=7;i<9;i++){
			$("#editFormWtjg0"+i+" input:text").attr("disabled",true);
			$("#editFormWtjg0"+i+" input:checkbox").attr("disabled",true);
			$("#editFormWtjg0"+i+" input:radio").attr("disabled",true);
			$("#editFormWtjg0"+i+" select").attr("disabled",true);
			$("#editFormWtjg0"+i+" textarea").attr("disabled",true);
			$("#editFormWtjg0"+i+" .ico_save").hide();
			$("#editFormWtjg0"+i+" .ico_del_1").hide();
		}
		</c:if>
	}
	//显示复查时间列表
	function showFcTime(){
		var chk_value="";
		$('input[name="wtjg195"]:checked').each(function(){ 
			$('#jyxmTb').append("<option value='"+$(this).val()+"'>"+$(this).val()+"</option>");
			chk_value = chk_value + $(this).val()+",";
		})
		$('input[name="wtjg215"]:checked').each(function(){
			$('#jyxmTb').append("<option value='"+$(this).val()+"'>"+$(this).val()+"</option>");
			chk_value = chk_value + $(this).val()+",";
		})
		var item = $('#clinicalObservationPlan').datagrid('getRows'); 
		if (item) { 
		for (var i = item.length - 1; i >= 0; i--) { 
				var index = $('#clinicalObservationPlan').datagrid('getRowIndex', item[i]); 
				$('#clinicalObservationPlan').datagrid('deleteRow', index); 
			} 
		}
		if(chk_value!=""){
			//"<input type='text' style='width:140px;' value='"+data.fcDate+"' name='dT"+i+"' <c:if test="${bl002Sjdj.blStep>5}">disabled='disabled'</c:if> class='Wdate' onclick='WdatePicker({dateFmt:\"yyyy-MM-dd HH:mm:ss\"});' />",
			$.ajax({
	            url: '${webroot}/bl004CsDetailinfo/f_json/findBycsmId.shtml',
	            type: 'post',
	            data: {blId:'${bl002Sjdj.blId}', itemNames: chk_value,date:'<fmt:formatDate value="${bl002Sjdj.enterTime}" pattern="yyyy-MM-dd HH:mm" />'},
	            dataType: 'json',
	            success : function(json) {
	            	for(var i=0;i<json.length;i++){
	            		var data=json[i];
	            		$('#clinicalObservationPlan').datagrid('appendRow',{
	    					jyHm: data.itemName,
	    					jyDh: data.csdName,
	    					djTime: "<input type='text' style='width:140px;' value='"+data.fcDate.substring(0,10)+"' name='dT"+i+"' <c:if test="${bl002Sjdj.blStep>5}">disabled='disabled'</c:if> class='Wdate' onclick='WdatePicker({dateFmt:\"yyyy-MM-dd\"});' />",
	    					csmId: data.csmId,
	    					csdId: data.csdId
	    				});
	            		if(data.isChoose=='0'){
		            		$('#clinicalObservationPlan').datagrid('selectRow', i);
	            		}
	            	}
	            	/* var data = $('#clinicalObservationPlan').datagrid('getData');
	            	for(var i=0;i<data.total;i++){
	            		if(data.rows[i].isChoose==0){
	            			$('#clinicalObservationPlan').datagrid('selectRow',i);
	            			$('#timeTb').append("<option value='"+data.rows[i].jyDh+"'>"+data.rows[i].jyDh+"</option>");
	            		}
	            	}      */  		
	            	var rows = $('#clinicalObservationPlan').datagrid('getChecked');
	            	var obj = {} ;
	            	//去个重
	            	for(var c=0;c<rows.length;c++){
	            		var x = rows[c].jyDh;
	            		obj[x] = 1;
	            	}
	            	//清空
	            	$('#timeTb').html("<option></option>");
	            	for(var key in obj){
		            	$('#timeTb').append("<option value='"+key+"'>"+key+"</option>");
	            	}
	            }
			});
		}	
	}
	var clinicalObservation = {
			panel : 'clinicalObservationPlan',
	};
	var clinicalObservation2 = {
			panel : 'clinicalObservationPlan2',
			query : function() {
				$('#'+clinicalObservation2.panel).datagrid({
					queryParams: {
		            	'blId':'${bl002Sjdj.blId}',
		            	'jyDh':$("#timeTb").val(),
		            	'itemName':$("#jyxmTb").val()
		            },
				});
			}
	};
	var registryForm = {
		//医院感染部位序号
		unitPart : 1,
		//医院病原体序号
		unitPathogens : 1,
		//社区感染部位序号
		commPart : 1,
		//社区病原体序号
		commPathogens : 1
	};
	var sjState='${bl002Sjdj.sjState}';
	$(document).ready(function () {
		
		if($("#wtjg20 option:selected").text()=="其它" || $("#wtjg20 option:selected").text()=="其他"){
			$("#wtjg21").show();			
		}
		$("#wtjg20").click(function(){showThings(this,"wtjg21");});
		f=
		
		
		window.setTimeout(function() {
			showFcTime();
			/* $('#wtjg40').combobox({
			    url:'${webroot}/sysDict/f_json/getValue.shtml?dictTypeCode=bl_contact_position',
			    valueField:'dictCode',
			    textField:'dictName',
			    value:'${wtjg40}',
			    multiple:true
			}); */
			$('#wtjg40').combobox("setValues","${wtjg40}");
			showHide();
			showBlMode();
			showBlForm();
			Comm.form({
				id : 'editFormBl002jdj',
				url : '${webroot}/bl002Sjdj/f_json/save.shtml',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '操作成功！'});
						location.href="${webroot}/bl002Sjdj/f_view/toedit.shtml?id="+json.msg;
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
			
			Comm.form({
				id : 'editFormWtjg01',
				subbtn:'editFormWtjg01Button',
				url : '${webroot}/bl002Sjdj/f_json/save.shtml',
				onSubmit: function () {
					  var isValid = $("#editFormWtjg01").form('validate');
                       if (!isValid){
						isClick = true;
                      } 
				},
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '操作成功！'});
						location.href="${webroot}/bl002Sjdj/f_view/toedit.shtml?id="+json.msg;
						isClick = true;
					} else if (json.result === 'error') {
						isClick = true;
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						isClick = true;
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
			
			Comm.form({
				id : 'editFormWtjg02',
				subbtn:'editFormWtjg02Button',
				url : '${webroot}/bl005Wtjg/f_json/save.shtml',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '操作成功！'});
						location.reload();
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
			
			Comm.form({
				id : 'editFormWtjg05',
				subbtn:'editFormWtjg05Button',
				url : '${webroot}/bl005Wtjg/f_json/save.shtml',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '操作成功！'});
						location.reload();
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
			
			Comm.form({
				id : 'editFormWtjg06',
				subbtn:'editFormWtjg06Button',
				url : '${webroot}/bl005Wtjg/f_json/save.shtml',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '操作成功！'});
						location.reload();
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
			
			Comm.form({
				id : 'editFormWtjg07',
				subbtn:'editFormWtjg07Button',
				url : '${webroot}/bl005Wtjg/f_json/save.shtml',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '操作成功！'});
						location.reload();
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
			
			Comm.form({
				id : 'editFormWtjg08',
				subbtn:'editFormWtjg08Button',
				url : '${webroot}/bl005Wtjg/f_json/save.shtml',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '操作成功！'});
						location.reload();
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
			
			
		}, 100);
		//遍历锚点  
		var mds = $(".maodian")  
		var arrMd = [];  
		for(var i = 0, len = mds.length;i<len;i++){  
			arrMd.push($(mds[i]));  
		}  
		   
		function update(){  
		var scrollH = $(window).scrollTop();
		
		for(var i = 0;i<len;i++){  
				var mdHeight = arrMd[i].offset().top;  
				if(mdHeight < scrollH +150){var j = i; navon(j);}  
			}  
		}  
		   
		//高亮导航菜单  
		function navon(id){ 			
			$('.arrow').css({"top": id*46+5});	 
		}  
		
		//绑定滚动事件  
		$(window).bind('scroll',update);  
		
		//科室
		Csm.combogrid.dep({
			id: 'djDept',
			value: '${bl002Sjdj.djDept}',
			required:true,
			flag: '1',
			size:400,
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
			onClickRow : function(index,row){
				//$('#id_infectDeptName').val(row.deptName);
			}
		});
		//科室
		Csm.combogrid.dep({
			id: 'wtjg120',
			value: '${wtjg120}',
			ifcaseoffice: '1',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
			onClickRow : function(index,row){
				//$('#id_infectDeptName').val(row.deptName);
			}
		});
		//主管医生
		Csm.combogrid.doctor({
			id: 'doctor',
			callback: '0'
		});
		//主管医生
		Csm.combogrid.doctor({
			id: 'blzName',
			callback: '0'
		});
		//主管医生
		Csm.combogrid.doctor({
			id: 'gcName',
			callback: '0'
		});
		//科级意见签名
		Csm.combogrid.doctor({
			id: 'qmDoctor',
			callback: '0',
			value: '${bl002Sjdj.kjyjQm}',
		});
		//主管医生
		Csm.combogrid.doctor({
			id: 'qjDoctor',
			callback: '0',
			value: '${bl002Sjdj.blJlMen}',
		});
		
		Csm.combogrid.doctor({
			//【必传】控件名称
			id: 'doctor',
			//【可选参数】下拉列表的默认value，不传则没有默认值；
			value: '${doctor.deptId}',
			//【可选参数】不传默认区session的医院ID
			hospId: '${doctor.hospId}',
			//【可选参数】1：回调,回调方
			//callback: '0',
		});

		$('#'+clinicalObservation.panel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            fitColumns: true,
            collapsible:true,
            remoteSort: false,
            singleSelect: false,
            columns:[
            	[
					{field:'ck',checkbox:true,width:20},
                    {field:'jyHm',title:'检验项目',sortable:true,width:100},
                    {field:'jyDh',title:'时间点',sortable:true,width:100},
                    {field:'djTime',title:'复查时间',sortable:true,width:120/* ,
                    	formatter:function(value,row,index){
                    		var djTime='<input type="text" name="bl006JyjgList['+index+'].djTime" onclick="WdatePicker()" value="'+row.djTime+'"/>';
                    		return djTime;
                    	} */
                    },
                    {field:'csmId',title:'csmId',sortable:true,width:0,hidden:true},
                    {field:'csdId',title:'csdId',sortable:true,width:0,hidden:true}
                ]
            ] /*,
            onLoadSuccess:function(data){
            	 var data = $('#clinicalObservationPlan').datagrid('getData');
            	if(data.total==0){
            		showFcTime();
            	}
            	$('#clinicalObservationPlan').datagrid('selectAll'); 
            } */
        });
		
		$('#'+clinicalObservation2.panel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            fitColumns: true,
            collapsible:true,
            url:'${webroot}/bl006Jyjg/f_json/findBl006Jyjg.shtml',
            queryParams: {
            	'blId':'${bl002Sjdj.blId}',
            	'jyDh':$("#timeTb").val(),
            	'itemName':$("#jyxmTb").val()
            },
            remoteSort: false,
            singleSelect: true,
            columns:[
            	[
                    {field:'jyHm',title:'项目明细',sortable:true,width:100},
                    {field:'jyDh',title:'时间点',sortable:true,width:100},
                    {field:'jyTime',title:'检验时间',sortable:true,width:120,
                    	formatter:function(value,row,index){
                    		var jyTime="";
                    		if(row.jyTimeStr!=null){
                        		jyTime='<input type="text" name="bl006JyjgList['+index+'].jyTime" class="Wdate text" onclick="WdatePicker()" value="'+row.jyTimeStr+'"/>';
                    		}else{
                        		jyTime='<input type="text" name="bl006JyjgList['+index+'].jyTime" class="Wdate text" onclick="WdatePicker()" value=""/>';
                    		}
                    		return jyTime;
                    	}
                    },
                    {field:'jyJg',title:'检验结果',sortable:true,width:100,
                    	formatter:function(value,row,index){
                    		var jyJg='<input type="hidden" name="bl006JyjgList['+index+'].jyHm" value="'+row.jyHm+'"/><input type="hidden" name="bl006JyjgList['+index+'].jyDh" value="'+row.jyDh+'"/>';
                    		jyJg=jyJg + '<select name="bl006JyjgList['+index+'].jyJg"><option value=""></option><option value="阴性"';
							if(row.jyJg=='阴性'){
                    			jyJg=jyJg + 'selected="selected"';
                    		}
							jyJg= jyJg +'>阴性</option><option value="阳性"';
							if(row.jyJg=='阳性'){
                    			jyJg=jyJg + 'selected="selected"';
                    		}
							jyJg= jyJg +'>阳性</option></select>';
                    		return jyJg;
                    	}
                    },
                    {field:'sfMemo',title:'备注',sortable:true,width:120,
                    	formatter:function(value,row,index){
                    		var sfMeno ;
                    		if(row.sfMemo==null){
                    			sfMemo = '';
                    		}else{
                    			sfMemo = row.sfMemo;
                    		}
                    		var jyJg='<input type="text" name="bl006JyjgList['+index+'].sfMemo" value="'+sfMemo+'"/>';                  		
                    		return jyJg;
                    	}	
                    },
                    {field:'_operate',title:'操作',sortable:true,width:50,
                    	formatter:function(value,row,index){
                    		var operate='';
                    		if(row.flag==1){
                    			operate = '<input type="hidden" name="bl006JyjgList['+index+'].flag" value="1"/>已完成';
                    		}else{
                    			operate='<input type="hidden" name="bl006JyjgList['+index+'].flag" value="'+row.flag+'"/><a href="javascript:wancen(\''+row.jyDh+'\',\''+row.jyHm+'\',\''+index+'\',);" class="ico_select" title="完成"></a>';                    		
                    		}
                    		return operate;
                    	}
                    }
                ]
            ],
            toolbar:'#tb',
            onLoadSuccess: function() {
            	showHide();
            }
        });
		
		setTimeout("blank()",1200);
		
	});
	function showOther(ele){
		if($(ele).val()=="HIV阳性患者"){
			if($(ele).is(":checked")){
				if($(ele).attr("name")=="wtjg130"){
					$(".hiv_positive").show();
				}else if($(ele).attr("name")=="wtjg132"){
					$(".hiv_positive2").show();
				}
			}else{
				$(".hiv_positive").hide();
				if($(ele).attr("name")=="wtjg130"){
					$(".hiv_positive").hide();
					//清除隐藏信息
					$("input[name='wtjg95']").val("");
					$("input[name='wtjg125']").val("");
					$("input[name='wtjg135']").val("");
				}else if($(ele).attr("name")=="wtjg132"){
					$(".hiv_positive2").hide();
					//清除隐藏信息
					$("input[name='wtjg96']").val("");
					$("input[name='wtjg126']").val("");
					$("input[name='wtjg136']").val("");
				}
			}
		}
	}
	function showThings(ele,id){
		if($(ele).attr("type")=="checkbox" || $(ele).attr("type")=="radio"){
			if($(ele).val()=="其他" || $(ele).val()=="其它" ){
				if($(ele).is(":checked")){
					$("#"+id).show();
				}else{
					$("#"+id).hide();
					//清除隐藏信息
					$("#"+id).val("");
				}
			}
		}else{
			var text = $("option:selected",ele).text();
			if(text=="其他" || text=="其它" ){
					$("#"+id).show();
			}else{
					$("#"+id).hide();
					//清除隐藏信息
					$("#"+id).val("");
			}
		}
	}
	function necessary(ele){
		if($(ele).attr("name")=="wtjg10"){
			if($("option:selected",ele).text()=="是"){
				$("#wtjg321").attr("disabled",false);
				$("#wtjg320").attr("disabled",false);
				$("#wtjg321").attr("class","easyui-validatebox").attr("required","true");
				$.parser.parse($("#wtjg321").parent());
				$("#wtjg320").attr("class","easyui-validatebox").attr("required","true");
				$.parser.parse($("#wtjg320").parent());
			}else{
				$("#wtjg321").removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox').val("");
				$("#wtjg320").removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox').val("");
				$("#wtjg321").attr("disabled",true);
				$("#wtjg320").attr("disabled",true);
			}
		}else if($(ele).attr("name")=="wtjg325"){
			if($("option:selected",ele).text()=="是"){
				$("#wtjg330").attr("disabled",false);
				$("#wtjg331").attr("disabled",false);
				$("#wtjg332").attr("disabled",false);
				$("#wtjg333").attr("disabled",false);
				$("#wtjg330").attr("class","easyui-validatebox").attr("required","true");
				$.parser.parse($("#wtjg330").parent());
				$("#wtjg331").attr("class","easyui-validatebox").attr("required","true");
				$.parser.parse($("#wtjg331").parent());
				$("#wtjg332").attr("class","easyui-validatebox").attr("required","true");
				$.parser.parse($("#wtjg332").parent());
				$("#wtjg333").attr("class","easyui-validatebox").attr("required","true");
				$.parser.parse($("#wtjg333").parent());
			}else{
				$("#wtjg330").removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox').val("");
				$("#wtjg331").removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox').val("");
				$("#wtjg332").removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox').val("");
				$("#wtjg333").removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox').val("");
				$("#wtjg330").attr("disabled",true);
				$("#wtjg331").attr("disabled",true);
				$("#wtjg332").attr("disabled",true);
				$("#wtjg333").attr("disabled",true);
			}
		}
	}
	function blank(){
		//只对临床医生限制，并且某些状态下是不能填写的
		//console.log("${user.acType}"+"..."+sjState+"..."+"${ZJCLYJ}"+"..."+"${XLZXSYJ}"+"..."+"${user.docNo}");
		if(!"${user.acType}") {
			// (!sjState) || sjState==0 || sjState==2
			//
			$("#zjclYj").attr("disabled","disabled");
			$("#xlzxsYj").attr("disabled","disabled");
			if(sjState<5){
				//转大写匹配
				var z = "${ZJCLYJ}";
				if(z){
					var zs = z.split(",");
					for(var j=0;j<zs.length;j++){
						//console.log(zs[j]+"..."+"${user.docNo}"+"..."+(zs[j]=="${user.docNo}"));
						if(zs[j]=="${user.docNo}"){
							$("#zjclYj").removeAttr("disabled");
						}
					}
				}
				
				var x = "${XLZXSYJ}";
				if(x){
					var xs = x.split(",");
					for(var i=0;i<xs.length;i++){
						//console.log(xs[i]+"..."+"${user.docNo}"+"..."+(xs[i]=="${user.docNo}"));
						if(xs[i]=="${user.docNo}"){
							$("#xlzxsYj").removeAttr("disabled");
						}
					}
				}
			}
		}
	}
</script>
</body>
</html>