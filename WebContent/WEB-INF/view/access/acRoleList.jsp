<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="acRolePanel"></div>
	<div id="tb" class="m_search">
		<input type="text" id="acRoleName" class="auto-tip" data-tip="请输入角色名称" /> 
		<div class="n_btn_blue">
			<a href="javascript:;" onclick="acRole.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>		
		<div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="acRole.edit(undefined,'新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
			var acRole = {
				panel:'acRolePanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+acRole.panel).datagrid({
			            queryParams: {
			                name: $('#acRoleName').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			    	Comm.dialogGlobal({
			        	url:"${webroot}/acRole/f_view/toedit.shtml?ownership=${param.ownership}&id=" + id,
			            title: title,
			            width:500
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该角色?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/acRole/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id, ownership: '${param.ownership}' },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
								    		acRole.query();
			                                $.messager.show({ title: '提示', msg: '删除成功！' });
								    	} else if(json.result === 'error') {
								    		$.messager.show({ title: '提示', msg: '系统异常！' });
								    	} else {
								    		$.messager.show({ title: '提示', msg: json.msg });
								    	}
									}
			            	});
			        	}
			    	});
			    },
			    //分配权限
			    grant : function(id) {
			    	Comm.dialogGlobal({
			        	url:"${webroot}/acRole/f_view/toauth.shtml?ownership=${param.ownership}&id=" + id,
			            title: '分配菜单权限',
			            width:600,
			            height: 400,
			            type: 'iframe'
			        });
			    },
			    //分配报表权限
			    grantReport : function(id) {
			    	Comm.dialogGlobal({
			        	url:"${webroot}/acMenu/f_view/toReportAuth.shtml?ownership=${param.ownership}&roleId=" + id,
			            title: '报表功能菜单授权',
			            method:'get',
			            width:500,
			            height: 550,
			            type: 'iframe'
			        });
			    }
			};

			$(document).ready(function () { 
				$('#'+acRole.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/acRole/f_json/pageQuery.shtml?ownership=${param.ownership}',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'name',title:'角色名称',sortable:true,width:150},
		                    {field:'roleScope',title:'角色管理类型',sortable:true,width:150},  
		                    {field:'remark',title:'角色说明',sortable:true,width:500},
		                    {field:'_operate',title:'操作',width:110,
								formatter:function(value,r){
									return ['<a href="javascript:acRole.edit(\'',r.roleId,'\',\'修改\');" class="ico_editor" title="修改"></a>',
									'<a href="javascript:acRole.grant(\'',r.roleId,'\');" id="roleAssign" class="ico_setup" title="分配权限"></a>',
									'<a href="javascript:acRole.grantReport(\'',r.roleId,'\');" id="roleAssign" class="ico_reportSet" title="分配报表权限"></a>',
									'<a href="javascript:acRole.del(\'',r.roleId,'\');" class="ico_del" title="删除"></a>'].join('');
								}
							}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb'
	            });
			});
		</script>
</body>
</html>