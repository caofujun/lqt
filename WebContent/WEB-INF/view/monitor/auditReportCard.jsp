<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>删除感染诊断</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<form id="id_auditReport" method="post">
		<input type="hidden" name="refid" value="${param.relid}"/>
		<div class="Explain">请勾选需要做审核操作的诊断，审核不通过需要填写原因：</div>
		<table class="table mb60" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
			<thead>
			<tr>
				<th style="width:30px"></th><th style="width:100px">审核状态：</th>
				<th style="width:300px"><label><input type="radio" name="authStatus" onclick="audit.showReason(1)" checked="checked" value="1" /> 通过 </label> &nbsp;&nbsp;&nbsp;&nbsp;<label><input type="radio" onclick="audit.showReason(2)" name="authStatus" value="2"/> 不通过</label></th>
			</tr>
			</thead>
			<thead>
				<tr>
					<th></th><th>诊断</th><th>原因</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${bk002List}" var="bk002Grzd">
				<tr style="height:36px;">
					<td><input id="id_${bk002Grzd.relid}" type="checkbox" name="relids" value="${bk002Grzd.relid}" checked="checked"/></td>
					<td><span id="id_name_${bk002Grzd.relid}"><c:out value="${bk002Grzd.infectDiagnName}" /></span></td>
					<td><span id="reason_${bk002Grzd.relid}" style="display:none;"><input type="text" id="id_reason_${bk002Grzd.relid}" name="reason_${bk002Grzd.relid}" style="width:280px" /></span></td>
			</c:forEach>
			<!-- <tr>
				<td>
				
					<input id="id_auditPass" type="radio" name="authStatus" value="1" onclick="audit.pass();" checked="checked"/>  
					<label for="id_auditPass">通过</label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="id_refundCard" type="radio" name="authStatus" value="2" onclick="audit.refund();"/>  
					<label for="id_refundCard">退卡</label>	
				</td>
			</tr>
			<tr id="id_refundTitle" style="display: none;"><td>退卡原因：</td></tr>
			<tr id="id_refund" style="display: none;">
				<td><textarea id="id_cardReturnCause" name="cardReturnCause" style="width: 95%;height: 100px;"></textarea></td>
			</tr> -->
			</tbody>
		</table>
		<div class="footer">
			<div class="footer_btn">
				<div class="n_btn_blue">
						<a href="javascript:;" id="changeFormSubmitBtn" onclick="audit.pass();" class="no_ico"><span>审&nbsp;核</span></a>
				</div>
				<!-- <div class="n_btn_grey">
						<a href="javascript:;" onclick="audit.refund();" class="no_ico"><span>不通过</span></a>
				</div> -->
			</div>	
		
		</div>
	</form>
<script>
/* $.extend($.fn.validatebox.defaults.rules, {
    isOrNotToFill : {
        validator: function(value, param){
        	var isChecked = $('#id_' + param[0]).is(':checked');
        	if (isChecked && $.trim(value).length <= 0) {
        		return false;
        	} else {
        		return true;
        	}
        },
        message: 'Field do not match.'
    }
}); */

var audit = {
	showReason:function(value){
		$("input:checkbox[name='relids']:checked").each(function(){
			var relid = $(this).val();
			if(value==1){
				$('#reason_'+relid).hide()
			}else if(value==2){
				$('#reason_'+relid).show();
			}
		});
	},
	//通过
	pass : function() {
		if (this.submitValidate()) {
			//$('#id_audit_state').val('1');
			var authStatus = $('input[name="authStatus"]:checked ').val();
			if(authStatus==2&& this.refundValidate()){
				$('#id_auditReport').submit();
			}else if(authStatus==1){
				$('#id_auditReport').submit();
			}
			//
		}
	},
	//退卡
	refund : function() {
		if (this.submitValidate() && this.refundValidate()) {
			$('#id_audit_state').val('2');
			$('#id_auditReport').submit();
		}
	},
	//退卡验证
	refundValidate : function() {
		var reState = true;
		$("input:checkbox[name='relids']:checked").each(function(){ 
			var relid = $(this).val(),
				reason = $('#id_reason_' + relid).combogrid('getValue'), 
				name = $('#id_name_' + relid).text();
			if ($.trim(reason).length <= 0) {
				reState = false;
				parent.$.messager.show({ title: '提示', msg: '请填写诊断【' + name + '】不通过的原因！' });
			}
		});
		return reState;
	},
	//提交验证
	submitValidate : function() {
		var reState = true;
		var checks = $("input:checkbox[name='relids']:checked");
		if (!checks || checks.length == 0) {
			parent.$.messager.show({ title: '提示', msg: '请勾选要审核的诊断！' });
			reState = false;
		}
		return reState;
	}
}

$(document).ready(function() {
	//退卡原因
	<c:forEach items="${bk002List}" var="bk002Grzd">
		Csm.combogrid.sysDicName({
			id: 'id_reason_${bk002Grzd.relid}',
			value: '',
			mode:'',
			dictTypeCode : 'card_audit_reason',
			onClickRow : function(index,row){
				//alert(row.dictName);
				//$('#id_reason_${bk002Grzd.relid}').val(row.dictName);
			}
		});
	</c:forEach>
	
	window.setTimeout(function() {
		Comm.form({
			id : 'id_auditReport',
			url : '${webroot}/bk001Sbk/f_json/auditReportCard.shtml',
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