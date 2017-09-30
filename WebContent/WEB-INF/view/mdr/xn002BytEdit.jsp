<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormAntibiosisDrug" method="post">
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" ><span class="red">*</span>病原体编号：</td>
				<td><input type="text"  id="pathogenId" name="pathogenId" style="width: 138px;" <c:if test="${Xn002Byt.pathogenId!=null and Xn002Byt.pathogenId!=''}"> readonly="true"</c:if>
					value="<c:out value="${Xn002Byt.pathogenId}" />" class="easyui-validatebox text" required="true"></td>
				
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>病原体名称：</td>
				<td><input type="text"  id="pathogenName" name="pathogenName" style="width: 138px;"
					value="<c:out value="${Xn002Byt.pathogenName}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title">首拼码：</td>
				<td>
					<input type="text"  id="spCode" name="spCode" style="width: 138px;" value="<c:out value="${Xn002Byt.spCode}" />" >
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>所属菌属：</td>
				<td>
					<div class="select_del"><input id="jszd" name="bactGenusId" style="width:148px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#jszd').combo('clear');"></a></div>			
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>革兰氏分类：</td>
				<td>
					<div class="select_del"><input id="glsfl" name="rsId" style="width:148px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#glsfl').combo('clear');"></a></div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormAntibiosisDrug').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>		
	</div>
</form>
<script>
	$(document).ready(function() {
			window.setTimeout(function() {
			$('#pathogenName').live('blur',function() {
				var drugName = $(this).pinyinFirstLower().toUpperCase();
				if(drugName.length > 10){
					drugName = drugName.substring(0,10);
				}
				$("#spCode").val(drugName);
			});
			//菌属
			Csm.combogrid.jszd({
				//【必传】控件名称
				id: 'jszd',
				value : "${Xn002Byt.bactGenusId}",
				required:true
			});
			//革兰氏分类
			Csm.combogrid.glsfl({
				id: 'glsfl',
				value : "${Xn002Byt.rsId}",
				required:true
			});
			Comm.form({
				id : 'editFormAntibiosisDrug',
				url : '${webroot}/xn002Byt/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						var parentObject = parent.Comm.getObjectCache();
						Comm.dialogClose('${param.dialogId}');
						parentObject.query();
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
	});
</script>