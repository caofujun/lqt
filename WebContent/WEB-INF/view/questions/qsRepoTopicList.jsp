<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>题库管理</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
	    <input type="hidden" id="id_username" value="${username}"/>
		<div id="infoPanel"></div>
		<div id="tb" class="m_search">
			<table>
				<tr>
				<td>
				<input type="text" id="questionTitle" class="auto-tip" data-tip="请输入题目标题"/>
			    <input type="button" class="btn_search" onclick="info.query()" value="查询"/>
				<input type="button" class="btn_add" value="新增" onclick="info.edit(undefined,'新增')"/>
				</td>
				</tr>
			</table>
		</div>
		
		<script type="text/javascript">
			var info = {
				panel : 'infoPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+info.panel).datagrid({
			            queryParams: {
			                'title': $('#questionTitle').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialog({
			        	url:'${webroot}/qsRepoTopic/f_view/toedit.shtml?id=' + id,
			    		type: 'iframe',
			            title: title,
			    		width: 600,
			    		height: 420
			        });
			    },
			    //删除
			    del : function(id) {
			    	$.messager.confirm('提示', '确认删除该题目?', function (r) {
			        	if (r) {
			            	$.ajax({
			                	url: '${webroot}/qsRepoTopic/f_json/delete.shtml',
			                    type: 'post',
			                    data: { id: id },
			                    dataType: 'json',
			                    success : function(json) {
									if(json.result==='success') {
								    	info.query();
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
			$(document).ready(function() {
				$('#'+info.panel).datagrid({
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                fit: true,
	                collapsible:true,
	                url:'${webroot}/qsRepoTopic/f_json/pageQuery.shtml',
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
		                    {field:'title',title:'题目标题',sortable:true,width:520},
		                    {field:'catName',title:'类别',sortable:true,width:100},
		                    {field:'ttypeName',title:'题目类型',sortable:true,width:80},
		                    {field:'_operate',title:'操作',width:70,
								formatter:function(value,r){
									var usernme = $('#id_username').val();
									if (usernme === r.createUser) {
										return ['<a href="javascript:info.edit(\'',r.tid,'\',\'修改\');" class="ico_editor" title="修改"></a>',
												'<a href="javascript:info.del(\'',r.tid,'\');" class="ico_del" title="删除"></a>'].join('');
									}
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