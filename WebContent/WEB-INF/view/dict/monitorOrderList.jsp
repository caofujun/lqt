<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>监测项目管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body>
		
		<div id="monitorOrderPanel"></div>
		<div id="tb" class="m_search">
			<nis:select id="classCode" dictcode="monitor_type" headerKey="" cssCls="easyui-combobox" headerValue="监测项目类型" />						
			<input type="text" class="auto-tip" data-tip="监测项目名称" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="monitorOrder.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="monitorOrder.edit('','新增监测项目')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>		
		</div>
		<script type="text/javascript">
			var monitorOrder = {
				panel : 'monitorOrderPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+monitorOrder.panel).datagrid({
			            queryParams: {
			                'classCode': $('#classCode').combogrid('getValue'),
			                'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/monitorOrder/f_view/toedit.shtml?id=" + id,
			            title: title,
			            width:550,
			            parent:this
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该监测项目?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/monitorOrder/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											monitorOrder.query();
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
				$('#'+monitorOrder.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/monitorOrder/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'className',title:'监测项目类型',sortable:true,width:200},  
		                    {field:'orderName',title:'监测项目',sortable:true,width:400},
		                    {field:'spCode',title:'SOP拼音码',sortable:true,width:80},
		                    {field:'_operate',title:'操作',width:75,
								formatter:function(value,r){
									if(r.dictStatus=='1'){
										return ['<a href="javascript:monitorOrder.edit(\'',r.orderCode,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:monitorOrder.del(\'',r.orderCode,'\');" class="ico_del" title="删除"></a>'].join('');
									}else{
										return ['<a href="javascript:monitorOrder.edit(\'',r.orderCode,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:monitorOrder.del(\'',r.orderCode,'\');" class="ico_del" title="删除"></a>'].join('');
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
