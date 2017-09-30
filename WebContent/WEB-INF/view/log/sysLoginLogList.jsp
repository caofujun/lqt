<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>操作日志管理</title>
		<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="sysLoginLogPanel"></div>
		<div id="tb" class="m_search">
			操作时间：
			<input type="text" class="Wdate text" id="queryStartDate" onclick="WdatePicker()"/>
			~ <input type="text" class="Wdate text" id="queryEndDate" onclick="WdatePicker()"/>
			<input type="text" class="auto-tip text input_tip" id="username" data-tip="请输入操作用户"/>
			<input type="button" class="btn_search" name="search" onclick="sysLoginLog.query()" value="查询" />
		</div>
		
		<script type="text/javascript">
			var sysLoginLog = {
				panel : 'sysLoginLogPanel',
				//查询
				query : function() {
					autoTip.clear();
					var _startDate = $('#queryStartDate').val();
					if(_startDate != '') {
						_startDate = _startDate + ' 00:00:00';
					}
					var _endDate = $('#queryEndDate').val();
					if(_endDate != '') {
						_endDate = _endDate + ' 23:59:59';
					}
			        $('#'+sysLoginLog.panel).datagrid({
			            queryParams: {
			                'username': $('#username').val(),
			                'queryStartDate': _startDate,
			                'queryEndDate': _endDate
			            },
			            pageNumber: 1
			        });
			    },
			    //查看
			    look:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialog({
			        	url:"${webroot}/sysLoginLog/f_view/look.shtml?id=" + id,
			            title: title,
			            width:720
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该日志?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sysLoginLog/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											sysLoginLog.query();
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
			    grant : function(username, title) {
			    	Comm.dialog({
			        	url: '${webroot}/acAccount/f_view/toedit.shtml?sourceType=look&username=' + username,
			        	title: title,
			            width:650,
			            height:450
			        });
			    }
			};
			$(document).ready(function () { 
				$('#'+sysLoginLog.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                collapsible:true,
	                url:'${webroot}/sysLoginLog/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
		                    {field:'id',title:'ID',sortable:true,width:100},  
		                    {field:'username',title:'登录用户',sortable:true,width:130},
		                    {field:'content',title:'状态',sortable:true,width:130},
		                    {field:'ip',title:'登录IP',sortable:true,width:100},
		                    {field:'operateTime',title:'登录时间',sortable:true,width:150},
		                    {field:'_operate',title:'操作',width:90,
								formatter:function(value,r){
									return ['<a href="javascript:sysLoginLog.look(',r.id,',\'查看\');" class="ico_view" title="查看"></a>',
									        '<a href="javascript:sysLoginLog.grant(\'',r.username,'\',\'查看权限\');" class="ico_rule" title="查看权限"></a>',
									'<a href="javascript:sysLoginLog.del(',r.id,');" class="ico_del" title="删除"></a>'].join('');
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