<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>监测项目维护</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body class="easyui-layout">
	<div data-options="region:'center',collapsed:false,border:false" style="border-left-width: 1px;" >
<!-- 		<iframe id="jkDicAllDetailIframe" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe> -->
		<div id="jkDicAllPanel2"></div>
		<div id="tb" class="m_search">		
		 	<input type="text" class="auto-tip" style="width:150px" data-tip="项目名" id="searchString"/>
		 	<!-- <input type="button" onclick="jkDicAll.query()" class="btn_search" iconCls="icon-search" plain="true" value="查询" />
		 	<input type="button" onclick="jkDicAll.edit('','新增')" class="btn" iconCls="icon-search" plain="true" value="新增" /> -->
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="jkDicAll2.query2()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="jkDicAll2.edit('','新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>

	</div>
	<div data-options="region:'west',border:false" style="width:150px;" class="easyui-layout">
		<div id="jkDicAllPanel"></div>
	</div>
		<script type="text/javascript">
			function seturl(classCode){
				var url="${webroot}/jkDicAll/f_view/list.shtml?classCode="+classCode;
			    $("#jkDicAllDetailIframe").attr("src",url);
			}
			var jkDicAll = {
				panel : 'jkDicAllPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+jkDicAll.panel).datagrid({	            
			            pageNumber: 1
			        });
			    }		 	
			};
			var jkDicAll2 = {
					classCode :'',
					panel : 'jkDicAllPanel2',
					//查询
					query2 : function() {
						autoTip.clear();
				        $('#'+jkDicAll2.panel).datagrid({
				        	url:'${webroot}/jkDicAll/f_json/pageQueryList.shtml?classCode='+jkDicAll2.classCode,
				            queryParams: {
				            	'searchString': $('#searchString').val()
				            },
				            pageNumber: 1
				        });
				    },
				    //编辑
				    edit:function(orderCode, title) {
				    	if(orderCode===undefined) orderCode = '';
				        Comm.dialog({
				        	url:"${webroot}/jkDicAll/f_view/toedit.shtml?classCode=" + jkDicAll2.classCode+"&orderCode=" + orderCode+"",
				            title: title,
				            width:430,
				            height:250,
				            //type:"iframe",
				            //parent:this
				        });
				    },
				 
				    //删除
				    del:function(orderCode,classCode) {
				    	$.messager.confirm('提示', '确认删除该指标?', function (r) {
				        	if (r) {
				            	$.ajax({
				                        url: '${webroot}/jkDicAll/f_json/delete.shtml',
				                        type: 'post',
				                        data: { orderCode: orderCode },
				                        dataType: 'json',
				                        success : function(json) {
											if(json.result==='success') {
												jkDicAll2.query2();
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
				$('#'+jkDicAll2.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/jkDicAll/f_json/pageQueryList.shtml',
	                queryParams: {
		            	'searchString': $('#searchString').val()
		            },
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'orderName',title:'监测项目名称',sortable:true,width:80},  
		                    {field:'pyCode',title:'拼音码',sortable:true,width:20},
		                    {field:'updateUserId',title:'修改人',sortable:true,width:15},
		                    {field:'updateTime',title:'修改时间',sortable:true,width:20},
		                    {field:'source',title:'来源',sortable:true,width:20},
		                    {field:'_operate',title:'操作',width:10,
								formatter:function(value,r){
										return ['<a href="javascript:jkDicAll2.edit(\'',r.orderCode,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										        '<a href="javascript:jkDicAll2.del(\'',r.orderCode,'\');" class="ico_del" title="删除"></a>'].join('');							
								}
							}							
		                ]
	                ],
	                toolbar:'#tb'
	            });
				$('#'+jkDicAll.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/jkDicAll/f_json/pageQuery.shtml',            
	                remoteSort: false,
	                singleSelect: true,
	                idField: 'classCode',
	                columns:[
	                	[                 
		                    {field:'className',title:'监测项目类别',sortable:true,width:149}		                  
		                ]
	                ],
	                onLoadSuccess: function() {
	                	if('${classCode}' == ''){
		                	$('#'+jkDicAll.panel).datagrid('selectRow', 0);
		                }else{
		                	$('#'+jkDicAll.panel).datagrid('selectRecord', '${classCode}');
		                }
	                	var curRow = $('#'+jkDicAll.panel).datagrid('getSelected');
	                	jkDicAll2.classCode=curRow.classCode;
	                	jkDicAll2.query2();
// 	                	seturl(curRow.classCode);
	                },
	                onClickRow:function(rowIndex, rowData){	 
	                	jkDicAll2.classCode=rowData.classCode;
	                	jkDicAll2.query2();
// 	                	seturl(rowData.classCode);
			    	}
	            });
			});
		</script>
	</body>
</html>