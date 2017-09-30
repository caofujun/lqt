<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<title>角色授权管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form method="post" id="acRoleAuthForm">
		<c:if test="${isHavegrant == 1}">
		<div id="button_div" class="footer" border="false">			
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#acRoleAuthForm').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
				</div>			
			</div>	
		</div>
		</c:if>
		<div style="width:100%;height:300px;overflow: hidden;">
			<input type="hidden" name="roleId" value="${acRole.roleId}" />
			<div id="usMenuPanel">
				<c:if test="${isHavegrant == 0}">您目前没有可授权的功能</c:if>
			</div>	
		</div>		
</form>
<script type="text/javascript">
var acRoleAuth = {
		menuNos : {},
		//所有菜单编码
		getMenu : function() {
			//var _menuNos = {};
			$('input[name=fungrants]').each(function(i, obj) {
				//_menuNos[$(obj).attr('data-no')] = $(obj).attr('data-pNo');
				acRoleAuth.menuNos[$(obj).attr('data-no')] = $(obj).attr('data-pNo');
			});
			/* $.each(_menuNos, function(key, value) {
				var _val = getMenuNo(key, _menuNos);
				acRoleAuth.menuNos[key] = _val;
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
	<c:if test="${isHavegrant == 1}">
	/* $('#usMenuPanel').datagrid({
		fit: true,
           nowrap: true,
           autoRowHeight: true,
           striped: true,
           fitColumns: true,
           collapsible:true,
           url:'${webroot}/acRole/f_json/findMyMenu.shtml?ownership=${param.ownership}&roleId=${acRole.roleId}',
           remoteSort: false,
           singleSelect: false,
           checkOnSelect:false,
           selectOnCheck:false,
           border:false,
           columns:[
           	[
               {field:'menuName',title:'菜单名称',sortable:true,width:200},
               {field:'menuTypeName',title:'类型',sortable:true,width:80,align:'center'},
            {field:'funGrant',title:'使用权限',sortable:true,width:80,align:'center',formatter:function(value,rec){
               	var _cont = [];
              		_cont.push('<input type="checkbox" class="checkbox_list" name="fungrants" onclick="acRoleAuth.sel(this,\'fungrants\')" ',(rec.isFungrant==='1'?'checked':''),' data-pno="',rec.parentMenuNo,'" data-no="',rec.menuNo,'" value="',rec.menuId,'"/>');
               	return _cont.join('');
               } },
            {field:'acGrant',title:'<div style="margin-top:8px;margin-left:8px;float:left;">授权权限</div><input id="cackb" type="checkbox" title="全选" style="width:15px;height:20px;margin:10px 2px;" onclick="ca(this);" />',sortable:true,width:80,align:'center',formatter:function(value,rec){
               	var _cont = [];
               	_cont.push('<input type="checkbox" class="checkbox_list2" id="havegrants" name="havegrants" onclick="unccackb(this);acRoleAuth.sel(this,\'havegrants\');" ',(rec.isHavegrant==='1'?'checked':''),' data-pno="',rec.parentMenuNo,'" data-no="',rec.menuNo,'" value="',rec.menuId,'"/>');
               	return _cont.join('');
               } }
    
               ]
           ],
           pagination:false,
           rownumbers:true
       }); */
       $('#usMenuPanel').treegrid({
    	   url:'${webroot}/acRole/f_json/findMyMenu2.shtml?ownership=${param.ownership}&roleId=${acRole.roleId}',
    	   singleSelect: false,
           checkOnSelect:false,
           selectOnCheck:false,
           checkbox:true,
           fit: true,
           fitColumns: true,
           idField:'menuId',
           treeField:'menuName',
           columns:[[
           		{field:'menuName',title:'菜单名称',sortable:true,width:200},
                {field:'menuTypeName',title:'类型',sortable:true,width:80,align:'center'},
                {field:'funGrant',title:'使用权限',sortable:true,width:80,align:'center',formatter:function(value,rec){
                       	var _cont = [];
                      		_cont.push('<input type="checkbox" class="checkbox_list" name="fungrants" onclick="acRoleAuth.sel(this,\'fungrants\')" ',(rec.isFungrant==='1'?'checked':''),' data-pno="',rec.parentMenuNo,'" data-no="',rec.menuNo,'" value="',rec.menuId,'"/>');
                       	return _cont.join('');
                 }},
                 {field:'acGrant',title:'<div style="margin-top:8px;margin-left:8px;float:left;">授权权限</div><input id="cackb" type="checkbox" title="全选" style="width:15px;height:20px;margin:10px 2px;" onclick="ca(this);" />',sortable:true,width:80,align:'center',formatter:function(value,rec){
                       	var _cont = [];
                       	_cont.push('<input type="checkbox" class="checkbox_list2" id="havegrants" name="havegrants" onclick="unccackb(this);acRoleAuth.sel(this,\'havegrants\');" ',(rec.isHavegrant==='1'?'checked':''),' data-pno="',rec.parentMenuNo,'" data-no="',rec.menuNo,'" value="',rec.menuId,'"/>');
                       	return _cont.join('');
                 }}
             ]],
             onLoadSuccess:function(r,data){
            	 //console.log(data);
             }
       });
	</c:if>
	Comm.form({
		id : 'acRoleAuthForm',
		url : '${webroot}/acRole/f_json/saveAuth.shtml',
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
	function ca(ele){
		if($(ele).is(":checked")){ 
			$(":checkbox[name='havegrants']").each(function(){
				$(".checkbox_list2").attr("checked","checked");
				//加入所有id
				acRoleAuth.havegrants.push($(this).val());
			});
		}else{
			$(":checkbox[name='havegrants']").each(function(){
				$(".checkbox_list2").removeAttr("checked");
				acRoleAuth.delDepIds.push($(this).val());
			});
		}
	};
	function unccackb(ele){
		if($(ele).is(":checked")){}else{
			$("#cackb").removeAttr("checked");
		}
	};
</script>
