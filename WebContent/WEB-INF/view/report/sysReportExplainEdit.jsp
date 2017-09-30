<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %> 
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<style type="text/css">
	.tabs-header {
		border-width: 0 0 0 1px;
	}
	.tabs-panels {
		border-right-width: 0;
	}
</style>
</head>
<body style="overflow: hidden;">
<form id="editFormSysReportExplain" method="post">
	<input type="hidden" name="reportId" value="${reportExplain.reportId}"/>
			<table class="table mb60">
				<tbody>
					<tr>
						<td class="t_title" style="width: 110px;">
							报表名称：
						</td>
						<td class="t_cont">
							<select  id="reportType"></select>
							<select id="reportName" name="reportName" class="easyui-combobox"></select>
							<%-- <input type="text" name="reportName" style="width:340px;" value="${reportExplain.reportName}" class="easyui-validatebox" required="true">
							<span style="color:red">*</span> --%>
						</td>
					</tr>
					<tr>
						<td class="t_title">
							公式名称：
						</td>
						<td class="t_cont">
							<input type="text" name="formulaTitle" style="width:340px;" value="${reportExplain.formulaTitle}" class="easyui-validatebox" required="true">
							<span style="color:red">*</span>
						</td>
					</tr>
					<tr>
						<td class="t_title">
							报表公式：
						</td>
						<td class="t_cont">
							<input type="text" name="reportFormula" style="width:340px;" value="${reportExplain.reportFormula}" class="easyui-validatebox" required="true">
							<span style="color:red">*</span>
						</td>
					</tr>
					<tr>
						<td class="t_title">
							统计规则：
						</td>
						<td class="t_cont">
							<textarea name="reportRule" id="content" cols="60" rows="5" style="width:99%;height: 60px;" >
							${reportExplain.reportRule}
							</textarea>
						</td>
					</tr>
					<tr>
						<td class="t_title">
							说明：
						</td>
						<td class="t_cont">
							<input type="text" name="reportDesc" style="width:340px;" value="${reportExplain.reportDesc}" >
						</td>
					</tr>
					<tr>
						<td class="t_title">
							排序号：
						</td>
						<td class="t_cont">
							<input type="text" name="seq" style="width:340px;" value="${reportExplain.seq}" >
						</td>
					</tr>			
				</tbody>
			</table>
		<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSysReportExplainSubmitBtn" onclick="$('#editFormSysReportExplain').submit()" class="no_ico"><span>发&nbsp;布</span></a>	
			</div>			
		</div>				
	</div>
</form>
	<script>
	var editor=null;
	var reportTypeSelect = '${reportExplain.reportType}';
	$(document).ready(function () {
		$('#reportType').combobox({
			url:'${webroot}/sysDict/f_json/findTop.shtml?dictTypeCode=report_type',
		    valueField:'dictCode',
		    textField:'dictName',
			headerKey: '',
			value:'${reportExplain.reportType}',
			headerValue: '请选择',
			onSelect : function(record){
				$('#reportName').combobox({
					url:'${webroot}/sysDict/f_json/getByParentDictCode.shtml?dictTypeCode=report_type&dictCode='+record.dictCode,
				    valueField:'dictCode',
				    textField:'dictName',
					headerKey: '',
					headerValue: '请选择',
					value:'${reportExplain.reportName}'
				})
			}
		});
		if(reportTypeSelect!=''){
			$('#reportName').combobox({
				url:'${webroot}/sysDict/f_json/getByParentDictCode.shtml?dictTypeCode=report_type&dictCode='+reportTypeSelect,
			    valueField:'dictCode',
			    textField:'dictName',
				headerKey: '',
				headerValue: '请选择',
				value:'${reportExplain.reportName}'
			})
		}
		window.editor = KindEditor.create('#content', {
			uploadJson   : '${webroot}/nyMessageTheme/f_json/upload.shtml',
			allowFileManager : false,
	   	    allowImageManager : true,
	        allowImageUpload : true, 
			items:[
	       	'preview','justifyleft','justifycenter', 'justifyright',
	      		'justifyfull','clearhtml', 'quickformat', 'selectall', '|', 'fullscreen',
	      		'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'image',
	     			'italic', 'underline', 'strikethrough'
			],
			autoHeightMode : true,
			afterBlur: function(){this.sync();}
		});
		window.setTimeout(function(){
			Comm.form({
				id: 'editFormSysReportExplain',
				url: '${webroot}/reportExplain/f_json/save.shtml',
				subbtn: 'changeFormSysReportExplainSubmitBtn',
				success : function(json) {
					if(json.result=='success') {
						var parentObject = parent.Comm.getObjectCache();
						parentObject.query();
						parent.$.messager.show({ title: '提示', msg: '操作成功！' });
						parent.Comm.dialogClose('${param.dialogId}');
			    	}
			    	else $.messager.show({ title: '提示', msg: '操作失败！' });
				}
			});
		},100);
	});
	</script>
</body>
</html>