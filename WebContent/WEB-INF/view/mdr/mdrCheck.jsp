<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="checkMdrForm" method="post">
			<input type="hidden" name="dt" value="<fmt:formatDate value="${viewMdr.dt}" pattern="yyyy-MM-dd" />" />
			<input type="hidden" name="orderno" value="${viewMdr.orderno }" />
			<input type="hidden" name="surveyDeptId" value="${viewMdr.surveyDeptId }" />

	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
		<tr>
			<td>
				<label><input name="resProp" type="radio" value="0" <c:if test="${viewMdr.resPropName eq '敏感'}">checked="checked"</c:if> />敏感</label> 
			</td>
			<td>
				<label><input name="resProp" type="radio" value="1" <c:if test="${viewMdr.resPropName eq 'MDR'}">checked="checked"</c:if> />MDR</label> 
			</td>
			<td>
				<label><input name="resProp" type="radio" value="2" <c:if test="${viewMdr.resPropName eq 'XDR'}">checked="checked"</c:if> />XDR</label> 
			</td>
			<td>
				<label><input name="resProp" type="radio" value="3" <c:if test="${viewMdr.resPropName eq 'PDR'}">checked="checked"</c:if> />PDR</label> 
			</td>
		</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#checkMdrForm').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>	
			<div class="n_btn_grey" >
				<a href="javascript:;" onclick="parent.Comm.dialogClose('${param.dialogId}')"  class="no_ico"><span>关闭</span></a>
			</div>		
		</div>		
	</div>
</form>
<script type="text/javascript">
$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'checkMdrForm',

			url : '${webroot}/xn011Dclymx/f_json/updateMdr.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					$.messager.show({ title : '提示', msg : '操作成功！' });
					var parentObject = parent.Comm.getObjectCache();
					parentObject.query();
					Comm.dialogClose('${param.dialogId}');
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