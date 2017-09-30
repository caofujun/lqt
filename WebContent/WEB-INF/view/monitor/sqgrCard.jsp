<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>报告卡信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/monitor/reportCards.js?${version}"></script>
</head>
<body>
<form id="id_form_${gr002YsgrMx.regId}" method="post">
<input type="hidden" name="regId" value="${gr002YsgrMx.regId}"/>
<input type="hidden" name="zyid" value="${gr002YsgrMx.zyid}"/>
<input type="hidden" name="infectType" value="2"/>
<input type="hidden" name="infectDiagnId" value="${gr002YsgrMx.infectCode}"/>
<input type="hidden" name="infectDiagnName" value="${gr002YsgrMx.infectName}"/>
<input type="hidden" name="bkType" value="0"/>
<input type="hidden" name="isAuth" value="1"/>
<input type="hidden"  name="chargeDrId" value="${st003Cryxxb.chargeDrId}" />
<input type="hidden"  name="reportDrId" value="${bk001Sbk.reportDrId}" />
<input type="hidden"  name="reportDrName" value="${bk001Sbk.reportDrName}" />
<input type="hidden"  name="reportDeptId" value="${bk001Sbk.reportDeptId}" />
<input type="hidden"  name="reportDeptName" value="${bk001Sbk.reportDeptName}" />
<table class="table mb60" cellpadding="0" cellspacing="0">
<tbody>
<tr>
	<th>感染科室:</th>
	<td>
		<input type="hidden" id="id_infectDeptName_${param.relid}" name="infectDeptName" value="${gr002YsgrMx.infectDeptName}" />
		<input id="id_infectDept_${param.relid}" name="infectDeptId" class="easyui-validatebox"  style="width: 160px;" />
	</td>
</tr>
<tr>
	<th>感染日期:</th>
	<td>
		<input type="text" id="id_infectDate_${param.relid}" name="infectDate" style="width: 150px;" value="<fmt:formatDate value="${gr002YsgrMx.startAt}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox text"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'id_confDate_${relid}\')}'})" />
	</td>
</tr>
<tr>
	<th>确诊日期:</th>
	<td>
		<input type="text" id="id_confDate_${relid}" name="confirmDt" style="width: 150px;" value="<fmt:formatDate value="${gr002YsgrMx.confDate}" pattern="yyyy-MM-dd" />" required="true" class="Wdate easyui-validatebox text"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'id_infectDate_${param.relid}\')}'})" />
	</td>
</tr>
</tbody>
</table>
</form>
<div data-options="region:'south',border:false" style="height:46px;">
		<div class="footer dialog_footer">			
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="id_submit" onclick="reportCards.formSubmit('${gr002YsgrMx.regId}');" class="no_ico"><span>排除医院感染，确认为社区感染</span></a>
				</div>
				<div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="confirm('${gr002YsgrMx.regId}');" class="no_ico"><span>完善详细社区感染报卡</span></a>
				</div>
			</div>
			
		</div>
	</div>
<script type="text/javascript">

function confirm (regId) {
	parent.parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&isAuth=1&infectTypeId=2&confirmDt='+$('#id_confDate_${relid}').val()+'&regId=' + regId,true);
	parent.Comm.dialogClose('${param.dialogId}');	
};
$(document).ready(function () {
	window.setTimeout(function(){
		Comm.form({
			id: 'id_form_${gr002YsgrMx.regId}',
			url: '${webroot}/bk001Sbk/f_json/addReportCard.shtml',
			subbtn: 'id_submit',
			onLoading : function () {
				$.messager.progress({
					text : '正在提交中....',
					interval : 200
				});
			},
			success : function(json) {
				$.messager.progress('close');
				if (json.result === 'success') {
					var parentObject = parent.Comm.getObjectCache();
					parent.$.messager.show({ title: '提示', msg: '操作成功！' });
					parentObject.query();
					parent.Comm.dialogClose('${param.dialogId}');
				} else {
					parent.$.messager.show({title : '提示',msg : json.msg});
				}
			}
		});
	},100);
	//感染科室
	Csm.combogrid.dep({
		id: 'id_infectDept_${param.relid}',
		value: '${gr002YsgrMx.infectDeptId}',
		ifcaseoffice: '1',
		panelHeight:200,
		required:true,
		onClickRow : function(index,row){
			$('#id_infectDeptName_${param.relid}').val(row.deptName);
		}
	});
	
});
</script>
</body>
</html>