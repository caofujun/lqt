<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>操作日志管理</title>
		<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="sysLogPanel"></div>
		
		<script type="text/javascript">
			var sysLog = {
				panel : 'sysLogPanel',
			    //查看
			    look:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialog({
			        	url:"${webroot}/sysLog/f_view/look.shtml?id=" + id,
			            title: title,
			            width:720
			        });
			    }
			   
			};
			$(document).ready(function () { 
				$('#'+sysLog.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                collapsible:true,
	                url:'${webroot}/sysLog/f_json/findbyBusinessId.shtml?businessId=${param.id}',   
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[ 
		                    {field:'createTime',title:'时间',sortable:true,width:130},
		                    {field:'logFun',title:'错误方法',sortable:true,width:200},
		                    {field:'logContent',title:'错误详情',sortable:true,width:370},
		                    {field:'_oper',title:'查看',sortable:true,width:40,
		                    	formatter:function(value,r){
									return ['<a href="javascript:sysLog.look(\'',r.id,'\');" class="ico_view" title="查看"></a>'].join('');                    
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