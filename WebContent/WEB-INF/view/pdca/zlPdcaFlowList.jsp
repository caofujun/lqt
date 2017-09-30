<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>PDCA模板管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:30;overflow:hidden;">
		<div class="m_search datagrid-toolbar">
			<span class="pro_text">创建时间：</span><input type="text" style="width:85px" id="startDate" value="${startDate}"  class="Wdate text" onclick="WdatePicker()"  />~
			<input type="text"  id="endDate" value="${endDate}" style="width:85px" class="Wdate text" onclick="WdatePicker()" />
			<input type="text" class="auto-tip" style="width:150px" data-tip="模板编号/名称" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="zlPdcaFlow.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;"  onclick="zlPdcaFlow.add('新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
	</div>
	<div data-options="region:'center',collapsed:false,border:false" style="border-left-width: 1px;">
		<iframe id="zlPdcaFlowDetailIframe" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
	</div>
	<div data-options="region:'west',border:false" style="width:450px;">
		<div id="zlPdcaFlowPanel"></div>
	</div>
		<script type="text/javascript">
			function seturl(fuid){
				var url="${webroot}/zlPdcaFlowDetail/f_view/index.shtml?fuid="+fuid;
			    $("#zlPdcaFlowDetailIframe").attr("src",url);
			}
			var zlPdcaFlow = {
				panel : 'zlPdcaFlowPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+zlPdcaFlow.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val(),
			                'startDate': $('#startDate').val(),
			                'endDate': $('#endDate').val()
			            },
			            pageNumber: 1
			        });
			    },
			  //新增
			    add:function(title) {
				        Comm.dialogGlobal({
				        	url:"${webroot}/zlPdcaFlow/f_view/toedit.shtml?",
				            title: title,
				            width:400,
				            type:"iframe",
				            parent:this
				        });
			    },
			  //停用
			    stop:function(fuid) {
			    	$.messager.confirm('提示', '确认停用该模板?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/zlPdcaFlow/f_json/stop.shtml',
			                        type: 'post',
			                        data: { fuid: fuid },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											zlPdcaFlow.query();
			                                $.messager.show({ title: '提示', msg: '停用成功！' });
								    	} else if(json.result === 'error') {
								    		$.messager.show({ title: '提示', msg: '停用异常！' });
								    	} else {
								    		$.messager.show({ title: '提示', msg: json.msg });
								    	}
									}
			            	});
			        	}
			    	});
			    },
			  //启用
			    start:function(fuid) {
			    	$.messager.confirm('提示', '确认启用用该模板?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/zlPdcaFlow/f_json/start.shtml',
			                        type: 'post',
			                        data: { fuid: fuid },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											zlPdcaFlow.query();
			                                $.messager.show({ title: '提示', msg: '启用成功！' });
								    	} else if(json.result === 'error') {
								    		$.messager.show({ title: '提示', msg: '启用异常！' });
								    	} else {
								    		$.messager.show({ title: '提示', msg: json.msg });
								    	}
									}
			            	});
			        	}
			    	});
			    },
			    //编辑
			    edit:function(title) {
			    	//获取选中行值
			        var curRow = $('#'+zlPdcaFlow.panel).datagrid('getSelected');
			        if (curRow) {
			        	Comm.dialogGlobal({
				        	url:"${webroot}/zlPdcaFlow/f_view/toedit.shtml?fuid=" + curRow.fuid,
				            title: title,
				            width:400,
				            type:"iframe",
				            parent:this
				        });
			        }
			    },
			    //删除
			    del:function() {
			    	//获取选中行值
			        var curRow = $('#'+zlPdcaFlow.panel).datagrid('getSelected');
			        if (curRow) {
				    	$.messager.confirm('提示', '确认删除该模板?', function (r) {
				        	if (r) {
				            	$.ajax({
				                        url: '${webroot}/zlPdcaFlow/f_json/delete.shtml',
				                        type: 'post',
				                        data: { fuid: curRow.fuid },
				                        dataType: 'json',
				                        success : function(json) {
											if(json.result==='success') {
												zlPdcaFlow.query();
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
			        }else{
			        	 $.messager.show({ title: '提示', msg: '请选择需要删除的字典类型！' });
			        }
			    }
			};
			$(document).ready(function () {
				autoTip.clear();
				$('#'+zlPdcaFlow.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/zlPdcaFlow/f_json/list.shtml',
	                queryParams: {
		            	'searchString': $('#searchString').val(),         	
		                'startDate': $('#startDate').val(),
		                'endDate': $('#endDate').val()
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
		                    /* {field:'flowNo',title:'编号',sortable:true,width:80}, */  
		                    {field:'flowName',title:'模板名称',sortable:true,width:140},
		                    {field:'statusName',title:'状态',sortable:true,width:40},
		                    {field:'createrName',title:'创建人',sortable:true,width:50},
		                    {field:'flowCreatetime',title:'创建时间',sortable:true,width:80},
		                    {field:'_operate',title:'操作',width:120,
								formatter:function(value,r){
									if(r.status=='1'){
										return ['<a href="javascript:zlPdcaFlow.edit(\'修改\');" class="ico_editor" title="修改"></a>',
										        '<a href="javascript:zlPdcaFlow.stop(\'',r.fuid,'\');" class="ico_stop" title="停用"></a>',
										        /* '<a href="" class="ico_add" title="新增过程"></a>', */
										'<a href="javascript:zlPdcaFlow.del();" class="ico_del" title="删除"></a>'].join('');
									}else{
										return ['<a href="javascript:zlPdcaFlow.edit(\'修改\');" class="ico_editor" title="修改"></a>',
										        '<a href="javascript:zlPdcaFlow.start(\'',r.fuid,'\');" class="ico_select" title="启用"></a>',
										       /*  '<a href="" class="ico_add" title="新增过程"></a>', */
										'<a href="javascript:zlPdcaFlow.del();" class="ico_del" title="删除"></a>'].join('');
									}
								}
							}
		                ]
	                ],
	    	        rownumbers:true,
	                toolbar:'#tb',
	                onLoadSuccess: function() {
	                	$('#'+zlPdcaFlow.panel).datagrid('selectRow', 0);
	                	var curRow = $('#'+zlPdcaFlow.panel).datagrid('getSelected');
	                	seturl(curRow.fuid);
	                },
	                onClickRow:function(rowIndex, rowData){	                
	                	seturl(rowData.fuid);
			    	}
	            });
			});
		</script>
	</body>
</html>