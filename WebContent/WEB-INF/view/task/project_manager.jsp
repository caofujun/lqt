<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>定时任务-项目管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="taskProjectPanel"></div>
		<div id="tb" class="m_search">			
		 	<input type="text" class="auto-tip" style="width:150px" data-tip="项目名称" id="searchString"/>		 	
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="taskProject.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="taskProject.edit('','新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<script type="text/javascript">
			var taskProject = {
				panel : 'taskProjectPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+taskProject.panel).datagrid({
			            queryParams: {
			                'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/taskProject/f_view/edit.shtml?id="+id,
			            title: title,
			            width:580,
			            height:320,
			            type:"iframe",
			            parent:this
			        });
			    },
			 
			    //任务管理
			    setup:function(id) {
			    	location.href="${webroot}/taskJob/f_view/manager.shtml?projectid="+id;
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该项目?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/taskProject/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											taskProject.query();
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
				$('#'+taskProject.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/taskProject/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'name',title:'项目名称',sortable:true,width:150},
		                    {field:'remark',title:'描述',sortable:true,width:300},
		                    {field:'_operate',title:'操作',width:100,
								formatter:function(value,r){
										return ['<a href="javascript:taskProject.edit(\'',r.id,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										        '<a href="javascript:taskProject.setup(\'',r.id,'\');" class="ico_setup" title="任务管理"></a>',
										'<a href="javascript:taskProject.del(\'',r.id,'\');" class="ico_del" title="删除"></a>'].join('');
								}
							}
		                ]
	                ],
	                pagination:true,
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>
