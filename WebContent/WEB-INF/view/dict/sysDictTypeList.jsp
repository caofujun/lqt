<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>字典类型</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body id="layout" class="easyui-layout">
	<div data-options="region:'west',split:true,border:false,title:'字典类型'" style="width:300px;border-right-width: 1px;" class="easyui-layout" id="layout">
		<!-- <div data-options="region:'center',border:false,fit:true" style="padding: 10px 0px;">
			<ul id="tagGroupItem" class="easyui-tree" data-options="animate:false"></ul>
		</div> -->
		<div id="sysDictTypePanel"></div>
		<div id="tb" class="m_search">
			<span class="fl">
			<input type="text" class="auto-tip" style="width:105px" data-tip="类型名称/编码" id="searchString"/>		    
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="sysDictType.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>	
			</span>
			<div class="fr" style="line-height:28px;">
				<input type="button" class="ico_img_add" onclick="sysDictType.add('新增')" id="addDmtBtn" value="" title="新增字典类型">
	        	<input type="button" class="ico_img_edit tagOption" onclick="sysDictType.edit('修复')" id="updDmtBtn" value="" title="修改字典类型">
	        	<input type="button" class="ico_img_del tagOption" onclick="sysDictType.del()" id="delDmtBtn" value="" title="删除字典类型">
			</div>
			<div class="clear"></div>
		</div>
		
	</div>
	<div data-options="region:'center',border:false" style="border-left-width: 1px;">
		<iframe id="sysDictIframe" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
	</div>
		
		<script type="text/javascript">
			function seturl(code){
				var url="${webroot}/sysDict/f_view/index.shtml?code="+code;
			    $("#sysDictIframe").attr("src",url);
			}
			var sysDictType = {
				panel : 'sysDictTypePanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+sysDictType.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			  //新增
			    add:function(title) {
				        Comm.dialogGlobal({
				        	url:"${webroot}/sysDictType/f_view/toedit.shtml?",
				            title: title,
				            width:550,
				            parent:this
				        });
			    },
			    //编辑
			    edit:function(title) {
			    	//获取选中行值
			        var curRow = $('#'+sysDictType.panel).datagrid('getSelected');
			        if (curRow) {
			        	Comm.dialogGlobal({
				        	url:"${webroot}/sysDictType/f_view/toedit.shtml?id=" + curRow.id,
				            title: title,
				            width:550
				        });
			        }
			    },
			    //删除
			    del:function() {
			    	//获取选中行值
			        var curRow = $('#'+sysDictType.panel).datagrid('getSelected');
			        if (curRow) {
				    	$.messager.confirm('提示', '确认删除该字典类型?', function (r) {
				        	if (r) {
				            	$.ajax({
				                        url: '${webroot}/sysDictType/f_json/delete.shtml',
				                        type: 'post',
				                        data: { id: curRow.id },
				                        dataType: 'json',
				                        success : function(json) {
											if(json.result==='success') {
												sysDictType.query();
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
				$('#'+sysDictType.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                collapsible:true,
	                url:'${webroot}/sysDictType/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'dictTypeName',title:'字典类型名称',sortable:true,width:110},  
		                    {field:'dictTypeCode',title:'字典类型编码',sortable:true,width:135}		                    
		                ]
	                ],
	                rownumbers:true,
	                toolbar:'#tb',
	                onLoadSuccess: function() {
	                	$('#'+sysDictType.panel).datagrid('selectRow', 0);
	                	var curRow = $('#'+sysDictType.panel).datagrid('getSelected');
	                	seturl(curRow.dictTypeCode);
	                },
	                onClickRow:function(rowIndex, rowData){	                
	                	seturl(rowData.dictTypeCode);
			    	}
	            });
			});
		</script>
	</body>
</html>
