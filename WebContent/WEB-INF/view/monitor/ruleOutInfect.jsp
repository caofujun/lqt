<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>删除感染诊断</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<form id="id_ruleOutInfect" method="post">
		<input type="hidden" name="regId" value="${param.regId}"/>
		<div style="margin:5px auto;width:92%;text-align: center; line-height: 35px; font-size: 14px; color: #666; font-weight: bold; border-bottom:1px solid #ccc;">排除的预警是无法恢复，请确认后操作</div>
		<table class="table mb60" cellpadding="0" cellspacing="0">
			<tbody>
			<tr>
				<td>
					<ul class="table_checkbox_li">
						<input id="id_un_infect" type="radio" name="excludeType" value="2"/>  
						<label for="id_un_infect">非感染</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="id_un_confirm" type="radio" name="excludeType" value="3"/>  
						<label for="id_un_confirm">不确定</label>
					</ul>	
				</td>
			</tr> 
			<tr>
				<td><textarea id="id_remark" name="remark" style="width:300px;height: 80px;"></textarea></td>
			</tr>
			</tbody>
		</table>	
		<div class="footer">			
			<div class="footer_btn">
				<div class="n_btn_blue">
						<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#id_ruleOutInfect').submit();" class="no_ico"><span>保存</span></a>
				</div>
				<div class="n_btn_grey">
						<a href="javascript:;" onclick="parent.Comm.dialogClose('${param.dialogId}')" class="no_ico"><span>关闭</span></a>
				</div>
			</div>
		</div>
	</form>
<script>
/* function submitValidate() {
	var checks = $('#id_ruleOutInfect').find('input:radio[name="excludeType"]:checked');
	if (!checks || checks.length == 0) {
		parent.$.messager.show({ title: '提示', msg: '请勾选排除原因！' });
		return false;
	}
	$('#id_ruleOutInfect').submit();
} */

$(document).ready(function() {
	/* $('#id_ruleOutInfect').find('input:radio[name="excludeType"]').on("click", function(){
		switch($(this).val()) {
			case '0' : $('#id_remark').val('定植'); break;
			case '1' : $('#id_remark').val('社区感染'); break;
			case '2' : $('#id_remark').val('非感染'); break;
			case '3' : $('#id_remark').val('不确定'); break;
		}
	}); */
	
	window.setTimeout(function() {
		Comm.form({
			id : 'id_ruleOutInfect',
			url : '${webroot}/gr002YsgrMx/f_json/ruleOutInfection.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title: '提示', msg: '操作成功！' });
					var parentObject = parent.Comm.getObjectCache();
					parentObject.query();
					parent.Comm.dialogClose('${param.dialogId}');
				} else if (json.result === 'error') {
					parent.$.messager.show({title : '提示',msg : '操作失败！'});
				} else {
					parent.$.messager.show({title : '提示',msg : json.msg});
				}
			}});
	}, 100);
});
</script>
</body>
</html>