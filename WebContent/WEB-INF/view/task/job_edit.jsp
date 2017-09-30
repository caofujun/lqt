<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="editFormtaskJob" method="post">
		<input type="hidden" name="id" value="${taskJob.id}" /> 
		<input type="hidden" name="projectid" value="${param.projectid}">
		<input type="hidden" name="status" value="${taskJob.status}"> 
		
		<table class="table mb60" id="taskJobList" cellpadding="0" cellspacing="0">
		<tbody>
		<tr>
			<td class="t_title" width="120"><span class="red">*</span>名称：</td>
			<td><input type="text" name="name" style="width:300px"
				value="<c:out value="${taskJob.name}" />" class="easyui-validatebox"
				required="true"></td>
		</tr>
		<tr>
			<td class="t_title"><span class="red">*</span>描叙：</td>
			<td><input type="text" name="remark" style="width:300px"
				value="<c:out value="${taskJob.remark}" />" class="easyui-validatebox"></td>
		</tr>
		<tr>
			<td class="t_title"><span class="red">*</span>调用链接：</td>
			<td><input type="text" name="link" style="width:300px"
				value="<c:out value="${taskJob.link}" />" class="easyui-validatebox"
				required="true"></td>
		</tr>
		<tr>
			<td class="t_title"><span class="red">*</span>任务规则：</td>
			<td><input type="text" id="cron" name="cron" style="width:300px"
				value="<c:out value="${taskJob.cron}" />" class="easyui-validatebox" required="true">
				<a href="javascript:void(0)" onclick="jobEdit.setCron('任务规则工具');" class="icon_r" title="设置重点关注项目"><i class="icon iconfont">&#xe601;</i></a>
			</td>
		</tr>
		</tbody>
	</table>
	<div class="footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormtaskJob').submit()" class="no_ico"><span>确认</span></a>
			</div>			
		</div>
	</div>
</form>
<script>
var jobEdit = {
    //设置重点关注项
    setCron:function(title) {
    	Comm.dialogGlobal({
        	url:"${webroot}/taskJob/f_view/CronGenerator.shtml",
        	title: title,
            width:600,
            height:560,
            type:'iframe',
            parent:this
        });
    },
    cron : function(cron){
    	$('#cron').val(cron);
    }
};

$(document).ready(
	function() {
		window.setTimeout(function() {
			Comm.form({
					id : 'editFormtaskJob',
					subbtn : 'changeFormSubmitBtn',
					url : '${webroot}/taskJob/f_json/save.shtml',
					success : function(json) {
						if (json.result === 'success') {
							parent.$.messager.show({title : '提示',msg : '操作成功！'});
							var parentObject = parent.Comm.getObjectCache();
							parentObject.query();
							parent.Comm.dialogClose('${param.dialogId}');
						} else if (json.result === 'error') {
							$.messager.show({title : '提示',msg : '操作失败！'});
						} else {
							$.messager.show({title : '提示',msg : json.msg});
						}
					}});
			}, 100);
	});
</script>