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
	<form id="id_delInfectCases" method="post">
		<input type="hidden" name="zyid" value="${param.zyid}"/>
		<input type="hidden" name="relid" value="${param.relid}"/>
		<div class="pj_title" style="margin:5px 10px;width:550px;">请勾选需要删除的诊断并说明删除感染病例的原因(未勾选默认删除整个报卡)：</div>
		<table class="table mb60" cellpadding="0" cellspacing="0">
			<tbody>
			<tr>
				<td class="t_title" style="width:80px;">删除的诊断：</td>
				<td>
					<ul class="table_checkbox_li">
						<c:forEach items="${bk002List}" var="bk002Grzd">
							<li>
								<input id="id_infect_${bk002Grzd.relid}" type="checkbox" name="relids" value="${bk002Grzd.relid}"/>  
								<label for="id_infect_${bk002Grzd.relid}">${bk002Grzd.infectDiagnName}</label>
							</li>
						</c:forEach>
					</ul>	
				</td>
			</tr>
			<tr>
				<td class="t_title">删除的原因：</td>
				<td><textarea id="id_delReason" name="delReason" style="width: 95%;height: 100px;"></textarea></td>
			</tr>
			</tbody>
		</table>	
		
		
		<div class="footer">
			<input type="button" class="btn_save" id="changeFormSubmitBtn" onclick="$('#id_delInfectCases').submit();" value="保存">
			<input type="button" class="btn_return" data-options="iconCls:'icon-cancel'" onclick="parent.Comm.dialogClose('${param.dialogId}')" value="关闭" />
		</div>
	</form>
<script>
$(document).ready(function() {
		
	window.setTimeout(function() {
		Comm.form({
			id : 'id_delInfectCases',
			url : '${webroot}/gr002YsgrMx/f_json/delDiagnosis.shtml',
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