<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色授权管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="dataModelPanel" class="easyui-layout" fit="true">
		<c:if test="${isHavegrant == 1}">
		<div class="footer" region="south" split="false" border="false">			
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#acAccountAuthForm').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
				</div>			
			</div>	
		</div>
		</c:if>
		<div region="center" border="false"
			style="position: relative; overflow-x: hidden;">
			<form method="post" id="acAccountAuthForm">
				<input type="hidden" name="userId" value="${acAccount.userId}" />
				<div id="usMenuPanel">
					<c:if test="${isHavegrant == 0}">您目前没有可授权的功能</c:if>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	var acAccountAuth = {
			menuNos : {},
			//所有菜单编码
			getMenu : function() {
				//var _menuNos = {};
				$('input[name=fungrants]').each(function(i, obj) {
					//_menuNos[$(obj).attr('data-no')] = $(obj).attr('data-pNo');
					acAccountAuth.menuNos[$(obj).attr('data-no')] = $(obj).attr('data-pNo');
				});
				/* $.each(_menuNos, function(key, value) {
					var _val = getMenuNo(key, _menuNos);
					acAccountAuth.menuNos[key] = _val;
				});
				function getMenuNo(key, menuNos) {
					var _val = menuNos[key];
					if(_val & _val != null && _val != '') {
						_val += '@#$' + getMenuNo(_val, menuNos);
					} else {
						return _val;
					}
				} */
			},
			//复选框点击事件
			sel : function(_this, name) {
				var _menuNos = {};
				name = 'input[name='+name+']'
				$(name).each(function(i, obj) {
					_menuNos[$(obj).attr('data-no')] = $(obj).attr('data-pno');
				});
				var _bool = ($(_this).attr('checked')?true:false);
				_this = $(_this);
				if(_bool) {
					//选中下面的节点，自动选中它的上级节点
					$(name).each(function(i, first) {
						if(_this.attr('data-pno') == $(first).attr('data-no')) {
							$(first).attr('checked', _bool);
							$(name).each(function(j, second) {
								if($(first).attr('data-pno') == $(second).attr('data-no')) {
									$(second).attr('checked', _bool);
									$(name).each(function(k, third) {
										if($(second).attr('data-pno') == $(third).attr('data-no')) {
											$(third).attr('checked', _bool);
										}
									});
								}
							});
						}
					});
				}
				//取消和选中
				$(name).each(function(i, first) {
					if(_this.attr('data-no') == $(first).attr('data-pno')) {
						$(first).attr('checked', _bool);
						$(name).each(function(j, second) {
							if($(first).attr('data-no') == $(second).attr('data-pno')) {
								$(second).attr('checked', _bool);
								$(name).each(function(k, third) {
									if($(second).attr('data-no') == $(third).attr('data-pno')) {
										$(third).attr('checked', _bool);
									}
								});
							}
						});
					}
				});
			}
	};
	$(document).ready(function() {
		$('#usMenuPanel').datagrid({
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            fitColumns: true,
            collapsible:true,
            url:'${webroot}/acAccount/f_json/findMyMenu.shtml?ownership=${param.ownership}&userId=${acAccount.userId}',
            remoteSort: false,
            singleSelect: true,
            columns:[
            	[
                {field:'menuName',title:'菜单名称',sortable:true,width:200},
                {field:'menuTypeName',title:'类型',sortable:true,width:80},
	            {field:'funGrant',title:'使用权限',sortable:true,width:80,formatter:function(value,rec){
                	var _cont = [];
               		_cont.push('<input type="checkbox" style="margin:6px 0px 0px 15px;" name="fungrants" onclick="acAccountAuth.sel(this,\'fungrants\')" ',(rec.isFungrant==='1'?'checked':''),' data-pno="',rec.parentMenuNo,'" data-no="',rec.menuNo,'" value="',rec.menuId,'"/>');
                	return _cont.join('');
                } },
	            {field:'acGrant',title:'授权权限',sortable:true,width:80,formatter:function(value,rec){
                	var _cont = [];
                	_cont.push('<input type="checkbox" style="margin:6px 0px 0px 15px;" name="havegrants" onclick="acAccountAuth.sel(this,\'havegrants\')" ',(rec.isHavegrant==='1'?'checked':''),' data-pno="',rec.parentMenuNo,'" data-no="',rec.menuNo,'" value="',rec.menuId,'"/>');
                	return _cont.join('');
                } }
                ]
            ],
            pagination:false,
            rownumbers:true
        });
		Comm.form({
			id : 'acAccountAuthForm',
			url : '${webroot}/acAccount/f_json/saveAuth.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					parent.Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : '操作失败！' });
				} else {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	});
	</script>
</body>
</html>