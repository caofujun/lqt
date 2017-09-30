<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>首页布局管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div class="home_component">
		<ul id="layoutPanel" style="border:0px;">
		</ul>
	</div>
		
		<script type="text/javascript">
			var hpLayout = {
				//查询
				query : function() {
					autoTip.clear();
					$.ajax({
                        url: '${webroot}/sysHpLayout/f_json/find.shtml',
                        type: 'post',
                        data: { 'searchString': $('#searchString').val() },
                        dataType: 'json',
                        success : function(json) {
							if(json.length > 0) {
								var _panel = $('#layoutPanel').empty();
								$.each(json, function(i, obj) {
									_panel.append(['<li class="component_preview">',
									        				'<div class="cap"><span class="cap_w">',obj.layoutName,'</span><div class="fr"><input type="button" class="ico_modify" title="修改" onclick="hpLayout.edit(\'',obj.id,'\',\'修改布局\')"/>',
									        				'<input type="button" class="ico_delete" title="删除" onclick="hpLayout.del(\'',obj.id,'\')"/>',
									        				'<input type="button" class="ico_disable" title="',(obj.layoutStatus==='disable'?'启用':'禁用'),'" onclick="hpLayout.status(\'',obj.id,'\', \'',obj.layoutStatus,'\')"/></div></div>',
									        				'<div class="con"><img src="',webroot,obj.imgUrl,'" width="100%" /></div>',
									        			'</li>'].join(''));
								});
								_panel.append('<li class="component_preview"><div class="cap"><div class="fr"></div></div>'
										+'<div class="con"><a href="javascript:hpLayout.edit(undefined,\'新增组件\')"><img src="'+webroot+'/resources/images_org/home/img_add.jpg" width="100%" /></a></div>'
									+'</li><div class="clear"></div>');
					    	} else {
					    		$.messager.show({ title: '提示', msg: '暂无记录' });
					    	}
						}
            		});
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialog({
			        	url:"${webroot}/sysHpLayout/f_view/toedit.shtml?id=" + id,
			            title: title,
			            width:600
			        });
			    },
			    //禁用
			    status:function(id, status) {
			    	status = (status==='disable'?'enable':'disable');
			    	var _typeName;
			    	if(status==='disable') {
			    		_typeName = '禁用';
			    	} else {
			    		_typeName = '启用';
			    	}
			    	$.messager.confirm('提示', '确认要 ['+_typeName+'] 该布局?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sysHpLayout/f_json/status.shtml',
			                        type: 'post',
			                        data: { id: id, status: status },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											hpLayout.query();
			                                $.messager.show({ title: '提示', msg: '操作成功！' });
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
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该参数?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sysHpLayout/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											hpLayout.query();
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
			    }
			};
			$(document).ready(function () {
				hpLayout.query();
				/* $('#'+hpLayout.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                collapsible:true,
	                url:'${webroot}/sysHpLayout/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
		                    {field:'id',title:'id',sortable:true,width:120},
		                    {field:'layoutCode',title:'layoutCode',sortable:true,width:120},
		                    {field:'layoutName',title:'layoutName',sortable:true,width:120},
		                    {field:'layoutUrl',title:'layoutUrl',sortable:true,width:120},
		                    {field:'layoutStatus',title:'layoutStatus',sortable:true,width:120},
		                    {field:'imgUrl',title:'imgUrl',sortable:true,width:120},
		                    {field:'remark',title:'remark',sortable:true,width:120},
		                    {field:'_operate',title:'操作',width:80,
								formatter:function(value,r){
									return ['<a href="javascript:hpLayout.edit(',r.id,',\'修改\');" class="ico_editor" title="修改"></a>',
									'<a href="javascript:hpLayout.del(',r.id,');" class="ico_del" title="删除"></a>'].join('');
								}
							}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb'
	            }); */
			});
		</script>
	</body>
</html>
