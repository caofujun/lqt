<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>SQL日志查看</title>
		<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="sqlLogPanel"></div>
		<div id="tb_sqlLog" class="m_search h_set">				
			<div class="btn_r">	
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="sqlLog.query();"><i class="icon iconfont">&#xe633;</i><span>刷新</span></a>
				</div>			
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="sqlLog.clearRecord();"><i class="icon nisfont">&#xe640;</i><span>清除</span></a>
				</div>				
			</div>
		</div>
		<script type="text/javascript">
			var sqlLog = {
				panel : 'sqlLogPanel',
				
			    //查询
			    query : function() {
			    	$('#' + sqlLog.panel).datagrid({
			            url: '${webroot}/sysLog/f_json/pageQuery.shtml?logType=sql',
			            pageNumber : 1
			        });
			    },
			    
			    //清除记录
			    clearRecord : function() {
			    	$.ajax({
		                url: '${webroot}/sysLog/f_json/delSqlLog.shtml',
		                type: 'get',
		                dataType: 'json',
		                success : function(json) {
		                	if (json.result === 'success') {
		                		parent.$.messager.show({ title: '提示', msg: '清除成功！' });
		                		sqlLog.query();
		                	} else {
		                		parent.$.messager.show({ title: '提示', msg: '清除失败！' });
		                	}
						}
		    		});
			    }
			};
			$(document).ready(function () { 
				$('#'+sqlLog.panel).datagrid({
	                fit: true,
	                nowrap: false,
	                autoRowHeight: true,
	                striped: true,
	                fitColumns: false,
	                url:'${webroot}/sysLog/f_json/pageQuery.shtml?logType=sql',
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[ 
		                    {field:'createTime',title:'时间',sortable:true,align:'center',width:90},
		                    {field:'logFun',title:'业务',sortable:true,align:'center',width:90},
		                    {field:'logContent',title:'SQL',sortable:true ${param.type eq 'dialog' ? ',width:570' : ''}}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                pageSize:30,
	                toolbar:'#tb_sqlLog'
	            });
			});
		</script>
	</body>
</html>