<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>定时任务-任务管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="taskJobLogPanel"></div>
		
		<script type="text/javascript">
			var taskJobLog = {
				panel : 'taskJobLogPanel',
				
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/taskJobLog/f_view/look.shtml?id="+id+"",
			            title: title,
			            width:580,
			            height:320,
			            type:"iframe",
			            parent:this
			        });
			    }
			};
			$(document).ready(function () { 
				$('#'+taskJobLog.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                queryParams: {
		            	'jobid':'${param.id}'
		            },
	                url:'${webroot}/taskJobLog/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'id',title:'编号',sortable:true,width:18},
		                    {field:'addtime',title:'调度时间',sortable:true,width:32},
		                    {field:'statusname',title:'状态',sortable:true,width:6},
		                    {field:'_operate',title:'操作',width:15,
								formatter:function(value,r){
											return ['<a href="javascript:taskJobLog.edit(\'',r.id,'\',\'修改\');" class="ico_editor" title="修改"></a>'].join('');				
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
