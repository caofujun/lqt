<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<title>患者转归情况</title>
</head>
<body>
<form id="editFormOutcome" method="post">
	<input type="hidden" name="relid" value="${bk002Grzd.relid}"/>
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>感染转归:</td>
				<td>
					<nis:select dictcode="lapse_to" cssCls="easyui-combobox" id="jbzg" name="jbzg" value="${bk002Grzd.jbzg}" headerKey="" headerValue="-请选择-" exp="style=\"width: 160px;\" " />
				</td>
			</tr>
			<tr>
				<td class="t_title" style="width: 120px;"><span class="red">*</span>转归日期:</td>
				<td><input type="text" id="jbzgDate" name="jbzgDate" value='<fmt:formatDate value="${bk002Grzd.jbzgDate}" pattern="yyyy-MM-dd" />' onclick="WdatePicker();" class="Wdate" style="width:150px;" /></td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;"  id="changeFormSubmitBtn" onclick="$('#editFormOutcome').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>				
	</div>
</form>
	<script type="text/javascript">
		$(document).ready(function () {		
			Comm.form({
				id : 'editFormOutcome',
				url : '${webroot}/bk002Grzd/f_json/outcomne/update.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						var parentObject = parent.Comm.getObjectCache();
						parentObject.refreshOutcome($('#jbzg').combobox("getText"),$('#jbzgDate').val());
						parent.Comm.dialogClose('${param.dialogId}');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		});
	</script>
</body>
</html>