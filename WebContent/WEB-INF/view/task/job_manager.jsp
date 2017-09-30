<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>定时任务-任务管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="taskJobPanel"></div>
		<div id="tb" class="m_search">			
		 	<input type="text" class="auto-tip" style="width:150px" data-tip="任务名称" id="searchString"/>		 	
		 	<div class="n_btn_blue">
				<a href="javascript:;" onclick="taskJob.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="taskJob.back()" ><span>返回上级</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="taskJob.edit('','新增')" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<script type="text/javascript">
			var taskJob = {
				panel : 'taskJobPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+taskJob.panel).datagrid({
			            queryParams: {
			            	'projectid':'${param.projectid}',
			                'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //返回上级
			    back:function(){
			    	location.href='${webroot}/taskProject/f_view/manager.shtml?ownership=hospital&tabBodyId=tab_body_A9905&menuNo=A9905';
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/taskJob/f_view/edit.shtml?id="+id+"&projectid=${param.projectid}",
			            title: title,
			            width:580,
			            height:320,
			            type:"iframe",
			            parent:this
			        });
			    },
			    start:function(id) {
			    	$.messager.confirm('提示', '确认启动?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/taskJob/f_json/updateStatus.shtml',
			                        type: 'post',
			                        data: { id: id ,status:0},
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											taskJob.query();
			                                $.messager.show({ title: '提示', msg: '启动成功,30秒后更新状态！' });
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
			    stop:function(id) {
			    	$.messager.confirm('提示', '确认停止?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/taskJob/f_json/updateStatus.shtml',
			                        type: 'post',
			                        data: { id: id,status:50 },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											taskJob.query();
			                                $.messager.show({ title: '提示', msg: '停止成功！' });
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
			    runTime:function(id,title){		
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/gm004Jcmx/f_view/judgeJcmxList.shtml?id="+id+"&projectid=${param.projectid}",
			            title: title,
			            width:480,
			            height:220,
			            type:"iframe",
			            parent:this
			        });
			    },
			    run:function(id){
			    	$.messager.confirm('提示', '立即执行?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/taskJob/f_json/run.shtml',
			                        type: 'post',
			                        data: { id: id},
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											taskJob.query();
			                                $.messager.show({ title: '提示', msg: '执行成功！' });
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
			    viewLog:function(id,title){
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/taskJobLog/f_view/manager.shtml?id="+id+"&projectid=${param.projectid}",
			            title: title,
			            width:680,
			            height:420,
			            type:"iframe",
			            parent:this
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该任务?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/taskJob/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											taskJob.query();
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
				$('#'+taskJob.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                queryParams: {
		            	'projectid':'${param.projectid}'
		            },
	                url:'${webroot}/taskJob/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'name',title:'任务名称',sortable:true,width:18},
		                    {field:'remark',title:'任务描述',sortable:true,width:32},
		                    {field:'statusname',title:'状态',sortable:true,width:6},
		                    {field:'link',title:'调用链接',sortable:true,width:24},
		                    {field:'cron',title:'规则',sortable:true,width:15},
		                    {field:'_operate',title:'操作',width:15,
								formatter:function(value,r){
										var runTime='';
										if(r.link.indexOf('f_task/jcmx.shtml')>-1){
											runTime='<a href="javascript:taskJob.runTime(\''+r.id+'\',\'根据时间执行\');" class="ico_RegularRun" title="根据时间执行"></a>';
										}
										if(r.status==0){											
											return ['<a href="javascript:taskJob.edit(\'',r.id,'\',\'修改\');" class="ico_editor" title="修改"></a>',	
											        '<a href="javascript:taskJob.stop(\'',r.id,'\',\'停用\');" class="ico_stop" title="停用"></a>',
											        '<a href="javascript:taskJob.run(\'',r.id,'\',\'立即执行\');" class="ico_run" title="立即执行"></a>',
											        runTime,
											        '<a href="javascript:taskJob.viewLog(\'',r.id,'\',\'查看日志\');" class="ico_view" title="查看日志"></a>',
											'<a href="javascript:taskJob.del(\'',r.id,'\');" class="ico_del" title="删除"></a>'].join('');
				
										}else{
											return ['<a href="javascript:taskJob.edit(\'',r.id,'\',\'修改\');" class="ico_editor" title="修改"></a>',	
											        '<a href="javascript:taskJob.start(\'',r.id,'\',\'启动\');" class="ico_deal" title="启动"></a>',
											        '<a href="javascript:taskJob.run(\'',r.id,'\',\'立即执行\');" class="ico_run" title="立即执行"></a>',
											        runTime,
											        '<a href="javascript:taskJob.viewLog(\'',r.id,'\',\'查看日志\');" class="ico_view" title="查看日志"></a>',
											'<a href="javascript:taskJob.del(\'',r.id,'\');" class="ico_del" title="删除"></a>'].join('');
										}
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
