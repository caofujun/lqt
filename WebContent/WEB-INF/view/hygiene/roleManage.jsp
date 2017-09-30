<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="saveAccredit" method="post">
	<input type="hidden" id="id_roleId" name="roleId">
	<div class="easyui-layout" style="width: 825px;height: 410px;">
		<div data-options="region:'west',border:false" style="width:288px;border-right-width: 1px;">
			<div id="tb_roleList" class="m_search">				
				<div class="btn_r">
					<div class="n_btn_grey">
						<a href="javascript:role.editRole('', '新增角色');"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
					</div>				
				</div>
			</div>
			<div id="rolePanel"></div>
		</div>
		<div data-options="region:'center',border:false">
			<div id="tb_accreditList" class="m_search">				
				<div class="btn_r">
					<div class="n_btn_grey">
						<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#saveAccredit').submit();"><i class="icon iconfont">&#xe63d;</i><span>保存更改</span></a>
					</div>				
				</div>
			</div>
			<div id="accreditPanel"></div>
		</div>
		<div data-options="region:'south'" style="height:47px;"></div>
	</div>
</form>
<div class="footer dialog_footer">
	<!-- <input type="button" class="btn_save" id="changeFormSubmitBtn" onclick="role.formSubmit();" value="确认"> -->
	<!-- <input type="button" class="btn_return" data-options="iconCls:'icon-cancel'" onclick="parent.Comm.dialogClose('${param.dialogId}')" value="关闭" /> -->
	<div class="footer_btn">		
		<div class="n_btn_grey">
			<a href="javascript:;"  onclick="parent.Comm.dialogClose('${param.dialogId}')" class="no_ico"><span>取消</span></a>
		</div>
	</div>
</div>
<script type="text/javascript">
var role = {
	rolePanel : 'rolePanel',
	accreditPanel : 'accreditPanel',
	//查询角色列表
	roleQuery : function() {
		$('#' + role.rolePanel).datagrid({
            url: '${webroot}/hw016Role/f_json/findList.shtml'
        });
	},
	//查询角色授权列表
	accreditQuery : function(roleId) {
		$('#' + role.accreditPanel).datagrid({
            url: '${webroot}/hw017RoleRight/f_json/findList.shtml',
            queryParams: {
            	roleId: roleId
            }
        });
	},
	//删除角色
	delRole : function(roleId, roleName) {
		$.messager.confirm('提示', '确认删除角色【' + roleName + '】?', function (r) {
        	if (r) {
				$.ajax({
		            url: '${webroot}/hw016Role/f_json/delete.shtml',
		            type: 'post',
		            data: { roleId: roleId },
		            dataType: 'json',
		            success : function(json) {
		            	if (json.result === 'success') {
		            		role.roleQuery();
						} else {
							parent.$.messager.show({title : '提示',msg : json.msg});
						}
					}
				});
			}
    	});
	},
	//修改角色
	editRole : function(roleId, title) {
		Comm.dialogGlobal({
        	url:"${webroot}/hw016Role/f_view/toedit.shtml?roleId=" + roleId,
            title: title,
            width:450,
            height:220,
            type:"iframe",
            parent:this
        });
	},
	//全选/全不选
	checkAll : function(className) {
		if ($('#id_' + className).val() == 0) {
			$("." + className).prop("checked", true);
			$('#id_' + className).val(1);
		} else {
			$("." + className).prop("checked", false);
			$('#id_' + className).val(0);
		}
	}
};

$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'saveAccredit',
			url : '${webroot}/hw017RoleRight/f_json/save.shtml',
			subbtn : 'changeFormSubmitBtn',
			onLoading : function () {
				$.messager.progress({
					text : '正在提交中....',
					interval : 200
				});
				$('#changeFormSubmitBtn').hide();
			},
			success : function(json) {
				$.messager.progress('close');
				$('#changeFormSubmitBtn').show();
				if (json.result === 'success') {
					parent.$.messager.show({ title: '提示', msg: '操作成功！' });
					//刷新父页面列表数据
					var parentObject = parent.Comm.getObjectCache();
					parentObject.query(json.data);
					parent.Comm.dialogClose('${param.dialogId}');
				} else if (json.result === 'error') {
					parent.$.messager.show({title : '提示',msg : '操作失败！'});
				} else {
					parent.$.messager.show({title : '提示',msg : json.msg});
				}
			}});
	}, 100);
	
	//角色列表
	$('#' + role.rolePanel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: false,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/hw016Role/f_json/findList.shtml',
        columns:[ 
	       	[
				{field:'roleId',title:'角色编号',sortable:true,width:80},
	            {field:'roleName',title:'角色名称',sortable:true,width:100},
	            {field:'_operate',title:'操作',width:55,align:'center',
	           		formatter:function(value,rec){
						return ['<a href="javascript:role.editRole(\'' + rec.roleId + '\',\'修改角色\')" class="ico_editor" title="修改"></a>' + 
						        '<a href="javascript:role.delRole(\'' + rec.roleId + '\', \'' + rec.roleName + '\')" class="ico_del" title="删除"></a>'].join('');
					}
	           	}
	        ]
        ],
        rownumbers:true,
        toolbar:'#tb_roleList',
        onLoadSuccess : function (data) {
        	if (data.rows.length > 0) {
            	$('#' + role.rolePanel).datagrid('selectRow', 0);
        	} else {
        		$('#' + role.accreditPanel).datagrid('loadData',{total:0,rows:[]});
        	}
        },
        onSelect : function(index,row){
        	role.accreditQuery(row.roleId);
        	$('#id_roleId').val(row.roleId);
		}
	});
	
	//角色授权列表
	$('#' + role.accreditPanel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: false,
        remoteSort: false,
        singleSelect: true,
        border:false,
        columns:[ 
	       	[
				{field:'classId',title:'监测项目编号',sortable:true,width:80,rowspan:2,
					formatter:function(value,row,index){
						return ['<input name="hw017List[' + index + '].classId" value="' + row.classId + '" type="hidden" />' + 
						        '<input name="hw017List[' + index + '].roleId" value="' + row.roleId + '" type="hidden" />' + 
						        '<span>' + row.classId + '</span>'].join('');
				    }
				},
				{field:'className',title:'监测项目名称',sortable:true,width:120,rowspan:2},
				{title:'本科室',sortable:true,colspan:3},
				{title:'其它科室',sortable:true,colspan:3}
	        ],[  
	            {title:'<span title="全选" style="cursor:pointer;" onclick="role.checkAll(\'selfView\')">查看<input type="hidden" id="id_selfView" value="0" /></span>',field:'selfView',width:50,align:'center',
					formatter:function(value,row,index){
						return ['<input class="checkbox_list selfView" name="hw017List[' + index + '].selfView" value="1" type="checkbox" ' + (row.selfView == 1 ? 'checked="checked"' : '') + ' />'].join('');
				    }
	            },
	            {title:'<span title="全选" style="cursor:pointer;" onclick="role.checkAll(\'selfAdd\')">上报<input type="hidden" id="id_selfAdd" value="0" /></span>',field:'selfAdd',width:50,align:'center',
					formatter:function(value,row,index){
						return ['<input class="checkbox_list selfAdd" name="hw017List[' + index + '].selfAdd" value="1" type="checkbox" ' + (row.selfAdd == 1 ? 'checked="checked"' : '') + ' />'].join('');
				    }
	            },
	            {title:'<span title="全选" style="cursor:pointer;" onclick="role.checkAll(\'selfResult\')">结果录入<input type="hidden" id="id_selfResult" value="0" /></span>',field:'selfResult',width:50,align:'center',
					formatter:function(value,row,index){
						return ['<input class="checkbox_list selfResult" name="hw017List[' + index + '].selfResult" value="1" type="checkbox" ' + (row.selfResult == 1 ? 'checked="checked"' : '') + ' />'].join('');
				    }
	            },
	            {title:'<span title="全选" style="cursor:pointer;" onclick="role.checkAll(\'otherView\')">查看<input type="hidden" id="id_otherView" value="0" /></span>',field:'otherView',width:50,align:'center',
					formatter:function(value,row,index){
						return ['<input class="checkbox_list otherView" name="hw017List[' + index + '].otherView" value="1" type="checkbox" ' + (row.otherView == 1 ? 'checked="checked"' : '') + ' />'].join('');
				    }
	            },
	            {title:'<span title="全选" style="cursor:pointer;" onclick="role.checkAll(\'otherAdd\')">上报<input type="hidden" id="id_otherAdd" value="0" /></span>',field:'otherAdd',width:50,align:'center',
					formatter:function(value,row,index){
						return ['<input class="checkbox_list otherAdd" name="hw017List[' + index + '].otherAdd" value="1" type="checkbox" ' + (row.otherAdd == 1 ? 'checked="checked"' : '') + ' />'].join('');
				    }
	            },
	            {title:'<span title="全选" style="cursor:pointer;" onclick="role.checkAll(\'otherResult\')">结果录入<input type="hidden" id="id_otherResult" value="0" /></span>',field:'otherResult',width:50,align:'center',
					formatter:function(value,row,index){
						return ['<input class="checkbox_list otherResult" name="hw017List[' + index + '].otherResult" value="1" type="checkbox" ' + (row.otherResult == 1 ? 'checked="checked"' : '') + ' />'].join('');
				    }
	            }
		    ]
        ],
        rownumbers:true,
        toolbar:'#tb_accreditList',
        onLoadSuccess : function (data) {
        	if (data.rows.length > 0) {
            	$('#' + role.accreditPanel).datagrid('selectRow', 0);
        	}
        }
	});
});
</script>