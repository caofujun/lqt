<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editFormAcAcount" method="post" enctype="multipart/form-data">
	<input type="hidden" id="userId" name="userId" value="${acAccount.userId}"/>
	<input type="hidden" id="ownership" name="ownership" value="${param.ownership}"/>
	<input type="hidden" name="unitId" id="unit" value="${acAccount.unitId}"/>
	<input type="hidden" name="acType" value="${acAccount.acType}"/>
	<input type="hidden" name="sourceType" value="${param.sourceType}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title"  style="width: 100px;"><span class="red">*</span>帐号：</td>
				<td>
					<input type="text" id="username" name="username"  class="easyui-validatebox checkExtis" value="${acAccount.username}" required="true" validType="extis['username']" ${empty acAccount.userId ? 'onblur="setPasswd();"' : ''} />
				    <%-- <input type="text" class="text" id="username" name="username"  value="${acAccount.username}" class="easyui-validatebox checkExtis" required="true" validType="extis['username']" ${empty acAccount.userId ? 'onblur="setPasswd();"' : ''} /> --%>
				</td>
				<c:choose>
				<c:when test="${empty acAccount.userId}">
				<td class="t_title" style="width: 100px;">密码：</td>
				<td>
				    <input type="text" id="id_passwd" name="passwd" required="true" class="easyui-validatebox" value="" />
				</td>
				</c:when>
				<c:when test="${dataScope == 6 or dataScope == 9 }">
				<td class="t_title" style="width: 100px;">密码：</td>
				<td>
				    <input type="text" class="text" id="id_passwd"  name="passwd" value="" />
				</td>	
				</c:when>
				<c:otherwise>
				<td class="t_title" style="width: 100px;"></td><td></td>
				</c:otherwise>
				</c:choose>
				<%-- <td class="t_cont">初始密码为<span class="red">${deflutPwd}</span></td> --%>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>姓名：</td>
				<td><input type="text" name="realname"  class="easyui-validatebox" value="<c:out value="${acAccount.realname}" />" required="true"/></td>
				<td class="t_title"><span class="red">*</span>角色：</td>
				<td>
					<c:choose>
					<c:when test="${param.sourceType=='my'}">
						<input type="hidden" name="roleIds" value="${roleIds}"/>
						<select name="roleIds" class="easyui-validatebox easyui-combobox" disabled="disabled" required="true">
							<c:forEach items="${roles}" var="role">
								<option value="${role.roleId}" <c:if test="${role.roleId eq roleIds}">selected="selected"</c:if>>${role.name}</option>
							</c:forEach>
						</select>
					</c:when>
					<c:otherwise>
					<select name="roleIds" required="true" class="easyui-validatebox easyui-combobox">
						<c:forEach items="${roles}" var="role">
							<option value="${role.roleId}" <c:if test="${role.roleId eq roleIds}">selected="selected"</c:if>>${role.name}</option>
						</c:forEach>
					</select>
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="t_title">手机号：</td>
				<td><input type="text" id="id_mobilenum" name="mobilenum" maxlength="11" class="easyui-validatebox checkExtis" value="${acAccount.mobilenum}" validType="extis['mobilenum']"/></td>
				<td class="t_title">邮箱：</td>
				<td><input type="text" id="id_email" name="email" class="easyui-validatebox checkExtis" value="${acAccount.email}" validType="extis['email']"/></td>
			</tr>
			<tr>
				<td class="t_title">
					<div class="oneDep">所属科室：</div>
				</td>
				<td colspan=3>
					<div class="select_del">
					<input type="hidden" id="dataScope" name="dataScope" value="6"/>
					<input type="hidden" id="scopeInfo" name="scopeInfo" value="${acAccount.scopeInfo}"/>
					<input type="text" name="depNo" id="dep" style="width:350px;" value="${acAccount.depNo}"/>
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#dep').combo('clear');"></a>
					</div>
				</td>
			</tr>
			<tr<c:if test="${param.sourceType=='my'}"> style="display: none;"</c:if>>				
				<td class="t_title oneDoc">关联医生：</td>
				<td class="oneDoc" colspan="3"><input type="text" class="text" name="docNo" id="doc" value="${acAccount.docNo}"/></td>
			</tr>
			<tr<c:if test="${param.sourceType=='my'}"> style="display: none;"</c:if>>
				<td class="t_title">激活账号：</td>
				<td >
				    <nis:radio name="isvalid" dictcode="boolean" defvalue="1" value="${acAccount.isvalid}"/>
				</td>
				<td class="t_title">有效日期</td>
				<td>
				    <input name="validDate" value="<fmt:formatDate value="${acAccount.validDate}" pattern="yyyy-MM-dd HH:mm"/>" style="width:140px" class="Wdate text easyui-validatebox" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" required="true"  type="text" />
				</td>
			</tr>
			<c:if test="${not empty acAccount.userId}">
			<tr>
				<td class="t_title">头像：</td>
				<td colspan="3">
				    <input type="file" class="text" name="image" style="width:200px;" class="easyui-validatebox" />
				    <span style="color: green;">格式为：gif,png,jpg,bmp,jpeg</span>
			    </td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
		<c:choose>
		<c:when test="${param.sourceType=='look'}">			
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="Comm.dialogClose('${param.dialogId}')"  class="no_ico"><span>取&nbsp;消</span></a>
			</div>
		</c:when>
		<c:otherwise>			
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="beforeSubmit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>
		</c:otherwise>
		</c:choose>
		<span id="loading" style="display: none;"><img src="${webroot}/resources/load/images/loading.gif" style="margin-bottom:-3px;"/> 数据提交中...</span>
		</div>
	</div>
</form>
<script>
$.extend($.fn.validatebox.defaults.rules, {
	extis: {
		validator:function(value,param){
			var _bool = true;
			var userId = '${acAccount.userId}';
			if(value!=''){
				if(param[0]=="mobilenum"){
					if(/^(13|14|15|18)\d{9}$/.test(value)==false){
						_bool = false;
						$.fn.validatebox.defaults.rules.extis.message ="手机号码格式不正确！";
					} else {
						$.ajax({
	                        url: '${webroot}/acAccount/f_json/checkExtis.shtml',
	                        type: 'post',
	                        data: { userId : userId, mobilenum : $("#id_mobilenum").val() },
	                        dataType: 'json',
	                        async:false,
	                        success : function(json) {
								if(json.result === 'error') {
									_bool = false;
									$.fn.validatebox.defaults.rules.extis.message ="已被使用!";
						    	}
							}
	            		});
					}
				}
				if(param[0]=="email"){
					if(/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9_]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(value)==false){
						_bool = false;
						$.fn.validatebox.defaults.rules.extis.message ="邮箱格式不正确！";
					} else {
						$.ajax({
	                        url: '${webroot}/acAccount/f_json/checkExtis.shtml',
	                        type: 'post',
	                        data: { userId : userId, email : $("#id_email").val() },
	                        dataType: 'json',
	                        async:false,
	                        success : function(json) {
								if(json.result === 'error') {
									_bool = false;
									$.fn.validatebox.defaults.rules.extis.message ="已被使用!";
						    	}
							}
	            		});
					}
				}
				if(param[0]=="username"){
					if(/^[A-Za-z0-9]*?$/.test(value)==false){
						_bool = false;
						$.fn.validatebox.defaults.rules.extis.message ="请填入数字或者字母！";
					} else {
						/* $.ajax({
	                        url: '${webroot}/acAccount/f_json/checkExtis.shtml',
	                        type: 'post',
	                        data: { userId : userId, username : $("#username").val() },
	                        dataType: 'json',
	                        async:false,
	                        success : function(json) {
								if(json.result === 'error') {
									_bool = false;
									$.fn.validatebox.defaults.rules.extis.message ="已被使用!";
						    	}
							}
	            		}); */
					}
				}
			}
			return _bool;
		},
		message:''
	}
});

	function beforeSubmit(){
		if($(".oneDep").is(":visible")){
			/* var value =$("#dep").val();
			if(value==''){
				$.messager.show({ title : '提示', msg : '请选择科室！' });
				return false;
			} */
		}
		if($(".manyDep").is(":visible")){
			/* var value =$("#moreDep").val();
			if(value==''){
				$.messager.show({ title : '提示', msg : '请选择多科室！' });
				return false;
			} */
		}
		if($(".oneDoc").is(":visible")){
			/* var value =$("#doc").val();
			if(value==''){
				$.messager.show({ title : '提示', msg : '请选择医生！' });
				return false;
			} */
		}
		$('#editFormAcAcount').submit();
	}
	
	function setPasswd() {
		$('#id_passwd').val($('#username').val());
	}
	
	$(document).ready(function() {
		var ownership = $("#ownership").val();
		if(ownership=="platform"){
			$("#dataScope option[value!=9]").remove();
		}else{
			$("#dataScope option[value='9']").remove();
		}
		
		var userId = $("#userId").val();
		if(userId!=""){
			$("#username").attr("readonly","readonly");
		}
		
		window.setTimeout(function() {
			Csm.combogrid.dep({
				//【必传】控件名称
				id: 'dep',
				//【可选参数】下拉列表的默认value，不传则没有默认值；
				value: '${acAccount.depNo}',
				//【可选参数】不传默认区session的医院ID
				hospId: '${acAccount.unitId}',
				//【可选参数】1：回调,回调方法setDep()，0：不回调，不传默认回调
				callback: '0',
			});
			Comm.form({
				id : 'editFormAcAcount',
				url : '${webroot}/acAccount/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				onLoading : function () {
					$('#loading').show();
					$('#changeFormSubmitBtn').hide();
				},
				success : function(json) {
					$('#loading').hide();
					$('#changeFormSubmitBtn').show();
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						Comm.dialogClose('${param.dialogId}');
						if ('${param.sourceType}' === 'my') {
							if (typeof(json.data)!="undefined" && json.data) {
								$('#id_userPhoto').attr("src", json.data);
							}
						} else {
// 							acAccount.query();
							menuInfo.clickMenu('用户管理','/acAccount/f_view/index.shtml',true,'system_ac_user');
						}
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
			
			
			var scopeInfo='${acAccount.scopeInfo}';
			var arry=[];
			if(scopeInfo!=''){
				arry=scopeInfo.split(',');
			}
			Csm.select.depMultiple({
				id: 'moreDep',
				unitId: $("#unit").val(),
				values: arry
			});
			
			/*$("#dataScope").val("${acAccount.dataScope}");
			$("#dataScope").change(function(){
				var value=$(this).val();
				if(value=='6' || value=='9'){
					$(".manyDep").hide(); $("#moreDep").attr('disabled','disabled');
					$(".oneDep").hide(); $("#dep").attr('disabled','disabled');
					$(".oneDoc").hide(); $("#doc").attr('disabled','disabled');
				}else if(value=='4'){
					$(".manyDep").show(); $("#moreDep").removeAttr('disabled');
					$(".oneDep").hide();  $("#dep").attr('disabled','disabled');
					$(".oneDoc").hide();  $("#doc").attr('disabled','disabled');
				}else if(value=='3'){
					$(".manyDep").hide(); $("#moreDep").attr('disabled','disabled');
					$(".oneDep").show();  $("#dep").removeAttr('disabled');
					$(".oneDoc").hide();  $("#doc").attr('disabled','disabled');
				}else if(value=='0'){
					$(".manyDep").hide(); $("#moreDep").attr('disabled','disabled');
					$(".oneDep").show();  $("#dep").removeAttr('disabled');
					$(".oneDoc").show();  $("#doc").removeAttr('disabled');
				}
			});
			$("#dataScope").change();*/
		}, 100);
	});
</script>