<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
	<form id="editFormusMenu" method="post">
		<input type="hidden" name="menuId" value="${acMenu.menuId}"/>
		<input type="hidden" name="unitId" value="${acMenu.unitId}"/>
		<input type="hidden" name="ownership" value="${param.ownership}"/>
		<table class="table mb60" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td class="t_title" style="width:85px;"><span class="red">*</span>名称：</td>
					<td><input type="text" name="menuName"  value="<c:out value="${acMenu.menuName}" />" class="easyui-validatebox text" required="true"/></td>
					<td class="t_title" style="width:85px;">上级菜单：</td>
					<td>
						<select id="selectLoad" name="parentMenuNo"  style="width:140px;"></select>
					</td>
				</tr>
				<tr>
					<td class="t_title"><span class="red">*</span>编号：</td>
					<td><input type="text" id="menuNo" name="menuNo" value="<c:out value="${acMenu.menuNo}" />" class="easyui-validatebox text" required="true" /></td>
					<td class="t_title">显示顺序：</td>
					<td><input type="text" name="showOrder" value="<c:out value="${acMenu.showOrder}" />" class="easyui-numberbox text"></td>
				</tr>
				<tr>
					<td class="t_title">类型：</td>
					<td>
						<nis:radio dictcode="menu_type" name="menuType" defvalue="0" value="${acMenu.menuType }"/>
					</td>
					<td class="t_title">是否启用：</td>
					<td>
						<nis:radio dictcode="boolean" name="isvalid" defvalue="1" value="${acMenu.isvalid }"/>
					</td>
				</tr>
				<tr>
					<td class="t_title">说明：</td>
					<td>
						<input type="text" name="remark" style="" value="<c:out value="${acMenu.remark}" />" class="easyui-validatebox text">
					</td>
					<td class="t_title">是否是报表：</td>
					<td>
						<nis:radio dictcode="boolean" name="isreport" defvalue="0" value="${acMenu.isreport }"/>
					</td>
				</tr>
				<tr>
					<td class="t_title">地址：</td>
					<td colspan="3"><textarea name="destUrl" style="width:434px; height:60px;" class="easyui-validatebox"><c:out value="${acMenu.destUrl}" /></textarea></td>
				</tr>				
			</tbody>
		</table>
		<div class="footer dialog_footer">
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormusMenu').submit()" class="no_ico"><span>确&nbsp;认</span></a>
				</div>			
			</div>	
		</div>
	</form>
	<script>
	$(document).ready(function () { 
		var menuid = "${acMenu.menuId}";
		var ownership = "${param.ownership}";
		var selectLoadValue="";
		if(menuid==""){
			selectLoadValue="${param.parentMenuNo}";
		}else{
			selectLoadValue="${acMenu.parentMenuNo}";
		}
		Csm.select.load({
			id: 'selectLoad',
			url: webroot + '/acMenu/f_json/getAllPatentMenu.shtml?ownership=' + ownership,
			data: {},
			headerKey: '',
			headerValue: '--请选择 --',
			value: selectLoadValue,
			kcode: 'menuNo',
			kvalue: 'menuName',
			pid: 'parentMenuNo'
		});
		window.setTimeout(function(){
			if(menuid!=""){
				$("#menuNo").attr("readonly","readonly");
			}
			Comm.form({
				id: 'editFormusMenu',
				url: '${webroot}/acMenu/f_json/save.shtml',
				subbtn: 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						var parentObject = Comm.getObjectCache();
						parentObject.query();
						Comm.dialogClose('${param.dialogId}');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : '操作失败！' });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				},
				msg : '只能创建到三级菜单！',
				check : function(){
					var selectValue = $("#selectLoad").val();
					var checkResult = false;
					$.ajax({
                        url: '${webroot}/acMenu/f_json/check.shtml',
                        type: 'post',
                        data: {'menuNo':selectValue,'ownership':ownership},
                        dataType: 'json',
                        async : false,
                        success : function(json) {
							if(json.result==='success') {
								checkResult = true;
					    	} else if(json.result === 'error') {
					    		checkResult = false;
					    	} else {
					    		checkResult = false;
					    	}
						}
            		});
					return checkResult;
				}
			});
		},100);
	});
	</script>