<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<title>报表关联</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form method="post" id="reportJoinForm">
	<div id="dataModelPanel" class="easyui-layout" style="width: 100%; height: 100%;">
		<div id="button_div" class="footer" border="false">			
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#reportJoinForm').submit()" class="no_ico"><span>确&nbsp;认</span></a>
				</div>			
			</div>	
		</div>
		<div data-options="region:'center',border:false,footer:'#button_div'" style="overflow: hidden;">
			
				<input type="hidden" name="menuNo" value="${menuNo}" />
				<div id="usMenuPanel">
					<c:if test="${isHavegrant == 0}">您目前没有可授权的功能</c:if>
				</div>
			
		</div>
	</div>
</form>
<script type="text/javascript">
var reportJoin = {
		menuNos : {},
		//所有菜单编码
		getMenu : function() {
			//var _menuNos = {};
			$('input[name=fungrants]').each(function(i, obj) {
				//_menuNos[$(obj).attr('data-no')] = $(obj).attr('data-pNo');
				reportJoin.menuNos[$(obj).attr('data-no')] = $(obj).attr('data-pNo');
			});
			/* $.each(_menuNos, function(key, value) {
				var _val = getMenuNo(key, _menuNos);
				reportJoin.menuNos[key] = _val;
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
		fit: true,
           nowrap: true,
           autoRowHeight: true,
           striped: true,
           fitColumns: true,
           collapsible:true,
           url:'${webroot}/acMenu/f_json/reportAuth.shtml?menuNo=${menuNo}',
           remoteSort: false,
           singleSelect: true,
           border:false,
           columns:[
           	[
               {field:'menuName',title:'报表名称',sortable:true,width:200},
               {field:'menuTypeName',title:'类型',sortable:true,width:80,align:'center'},
               {field:'acGrant',title:'关联',sortable:true,width:80,align:'center',formatter:function(value,rec){
               	var _cont = [];
               	_cont.push('<input type="checkbox" class="checkbox_list" name="havegrants" ',(rec.isHavegrant==='1'?'checked':''),'  value="',rec.menuNo,'"/>');
               	return _cont.join('');
               }}
               ]
           ],
           pagination:false,
           rownumbers:true
       });
	Comm.form({
		id : 'reportJoinForm',
		url : '${webroot}/acMenu/f_json/saveJoinReport.shtml',
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
