<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>咳嗽腹泻</title>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body>
<form id="editFormAntibiosisDrug" method="post">
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" ><span class="red">*</span>登记日期：</td>
				<td><input type="text" id="creationdate" name="creationdate" value='<fmt:formatDate value="${gm003Ybsj.creationdate}" pattern="yyyy-MM-dd"/>'  required="true" class="Wdate easyui-validatebox text" style="width: 138px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
				
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>科室：</td>
				<td><div class="select_del"><input id="deptId" name="deptId" class="easyui-validatebox" value="${gm003Ybsj.deptId}" style="width: 138px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a></div>	</td>
			</tr>
			<tr>
				<td class="t_title">医务人员咳嗽：</td>
				<td>
					<input type="text"  id="ywryksCount" name="ywryksCount" value="${gm003Ybsj.ywryksCount}" style="width: 138px;" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
				</td>
			</tr>
			<tr>
				<td class="t_title">医务人员腹泻：</td>
				<td>
					<input type="text"  name="ywryfxCount" id="ywryfxCount" value="${gm003Ybsj.ywryfxCount}" style="width: 138px;"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" >				
				</td>
			</tr>
			<tr>
				<td class="t_title">病患咳嗽：</td>
				<td>
					<input type="text"  name="bhksCount" id="bhksCount" value="${gm003Ybsj.bhksCount}" style="width: 138px;"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" >			
				</td>
			</tr>
			<tr>
				<td class="t_title">病患腹泻：</td>
				<td colspan="3">
					<input type="text"  name="bhfxCount" id="bhfxCount" style="width: 138px;" value="${gm003Ybsj.bhfxCount}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" >
				</td>
			</tr>
			<tr>
				<td class="t_title">医务人员发热：</td>
				<td>
					<input type="text"  name="ywryfrCount" id="ywryfrCount" value="${gm003Ybsj.ywryfrCount}" style="width: 138px;"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" >			
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="gm003kfjc.validate()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>		
	</div>
</form>
<script>
var gm003kfjc = {
		keyPress : function () {    
		     var keyCode = event.keyCode;    
		    alert(keyCode);
		     if ((keyCode >= 48 && keyCode <= 57))    
		    {    
		         event.returnValue = true;    
		     } else {    
		    	 $.messager.show({ title : '提示', msg : '输入值只能为数字！' });
		         event.returnValue = false;    
		    }    
		 },  
		validate : function (){
			var ywryksCount = $("#ywryksCount").val();
			var ywryfxCount = $("#ywryfxCount").val();
			var ywryfrCount = $("#ywryfrCount").val();
			var bhksCount = $("#bhksCount").val();
			var bhfxCount = $("#bhfxCount").val();
			if($.trim(ywryksCount) !="" || $.trim(ywryfxCount) != "" || $.trim(bhksCount) != "" ||  $.trim(bhfxCount) != "" ||  $.trim(ywryfrCount) != "" ){
				$('#editFormAntibiosisDrug').submit();
			}else{
				$.messager.show({ title : '提示', msg : '医务人员咳嗽、医务人员腹泻、病患咳嗽、病患腹泻，至少填写一个！' });
			}
		}
};
	$(document).ready(function() {
		window.setTimeout(function() {
			Csm.combogrid.dep({
				//【必传】控件名称
				id: 'deptId',
				ifenvoffice: '1',
				value : '${deptId}',
				required:true,
			});
			Comm.form({
				id : 'editFormAntibiosisDrug',
				url : '${webroot}/gm003Ybsj/f_json/kfjcSave.shtml',
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
</body>
</html>