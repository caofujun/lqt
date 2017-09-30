<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>指标配置</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="fxZhibiaoPanel"></div>
		<div id="tb" class="m_search">		
		 	<input type="text" class="auto-tip" style="width:150px" data-tip="指标名" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="fxZhibiao.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;"  onclick="fxZhibiao.edit('','新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<script type="text/javascript">
			var fxZhibiao = {
				panel : 'fxZhibiaoPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+fxZhibiao.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(zbId, title) {
			    	if(zbId===undefined) zbId = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/fxZhibiao/f_view/toedit.shtml?zbId=" + zbId,
			            title: title,
			            width:650,
			            height:310,
			            type:"iframe",
			            parent:this
			        });
			    },
			 
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该指标?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/fxZhibiao/f_json/delete.shtml',
			                        type: 'post',
			                        data: { pdSubid: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											fxZhibiao.query();
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
				autoTip.clear();
				$('#'+fxZhibiao.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/fxZhibiao/f_json/pageQuery.shtml',
	                queryParams: {
		            	'searchString': $('#searchString').val()
		            },
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'zbName',title:'指标名',sortable:true,width:20},  
		                    {field:'zbValue',title:'指标值',sortable:true,width:80},
		                    {field:'zbScore',title:'指标得分',sortable:true,width:15},
		                    {field:'isGyName',title:'是否可干预',sortable:true,width:20},
		                    {field:'zbTypeName',title:'指标来源',sortable:true,width:20},
		                    {field:'_operate',title:'操作',width:10,
								formatter:function(value,r){
										return ['<a href="javascript:fxZhibiao.edit(\'',r.zbId,'\',\'修改\');" class="ico_editor" title="修改"></a>'].join('');							
								}
							}							
		                ]
	                ],
	                rownumbers:true,
	                pagination:true,
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>
