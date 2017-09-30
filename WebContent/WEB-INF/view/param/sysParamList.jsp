<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>参数管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
	</head>
	<body>
		<div id="sysParamPanel"></div>
		<div id="tb" class="m_search">
			<input type="text" class="auto-tip" data-tip="参数名称/参数编码" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="sysParam.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="sysParam.edit(undefined,'新增参数')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			var sysParam = {
				panel : 'sysParamPanel',
				length : 0,
				//查询
				query : function() {
					if("" != $('#searchString').val() && "参数名称/参数编码" != $('#searchString').val()){
						sysParam.length=1;
					}
					autoTip.clear();
			        $('#'+sysParam.panel).datagrid({
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
			        	url:"${webroot}/sysParam/f_view/toedit.shtml?id=" + id,
			            title: title,
			            width:600
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该参数?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sysParam/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											sysParam.query();
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
				$('#'+sysParam.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/sysParam/f_json/pageQuery.shtml?source=${param.source}',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'paramName',title:'参数名称',sortable:true,width:150},  
		                    {field:'paramCode',title:'参数编码',sortable:true,width:160},
		                    {field:'paramValue',title:'参数值',sortable:true,width:100},
		                    {field:'paramType',title:'参数类型',sortable:true,width:80},
// 		                    {field:'scopeLevelName',title:'应用类型',sortable:true,width:80},
		                    {field:'unitName',title:'适应医院',sortable:true,width:80},
		                    {field:'depName',title:'适应科室',sortable:true,width:80},
		                    {field:'realName',title:'适应用户',sortable:true,width:80},
		                    {field:'_operate',title:'操作',width:60,
								formatter:function(value,r){
									return ['<a href="javascript:sysParam.edit(\'',r.id,'\',\'修改\');" class="ico_editor" title="修改"></a>',
									'<a href="javascript:sysParam.del(\'',r.id,'\');" class="ico_del" title="删除"></a>'].join('');
								}
							}
		                ]
	                ],
// 	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb',
	                groupField:'paramType',
	        		view: groupview,
	        		groupFormatter : function(value, rows){
	        			return value;
	        		},
	        		onLoadSuccess : function(data){
		        		if (sysParam.length == 1) {
							$('#' + sysParam.panel).datagrid('collapseGroup');
		                	$('#' + sysParam.panel).datagrid('expandGroup', 0);
		            	}else{
		                	$('#' + sysParam.panel).datagrid('collapseGroup');
		            	}
		        		sysParam.length=0;
	        		}
	            });
			});
		</script>
	</body>
</html>
