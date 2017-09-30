<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>职业暴露项目管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body>
		
		<div id="bl004CsDetailinfoPanel"></div>
		<div id="tb" class="m_search">
			<nis:select id="csmId" dictcode="bl_item_type" cssCls="easyui-combobox" headerKey="" headerValue="职业暴露项目分类" />			
			<input type="text" class="auto-tip" data-tip="职业暴露项目名称/编号" style="width:200px" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="bl004CsDetailinfo.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="bl004CsDetailinfo.edit('','','新增职业暴露项目')" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var bl004CsDetailinfo = {
				panel : 'bl004CsDetailinfoPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+bl004CsDetailinfo.panel).datagrid({
			            queryParams: {
			                'csmId': $('#csmId').combogrid('getValue'),
			                'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(csmId, csdId, title) {
			    	if(csmId===undefined) csmId = '';
			    	if(csdId===undefined) csdId = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/bl004CsDetailinfo/f_view/toedit.shtml?csmId=" + csmId+"&csdId=" + csdId+"",
			            title: title,
			            width:720,
			            parent:this
			        });
			    },
			    //删除
			    del:function(csmId, csdId) {
			    	$.messager.confirm('提示', '确认删除该职业暴露项目?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/bl004CsDetailinfo/f_json/delete.shtml',
			                        type: 'post',
			                        data: { csmId: csmId, csdId: csdId },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											bl004CsDetailinfo.query();
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
			  	//停用
			    stop:function(id) {
			    	$.messager.confirm('提示', '确认停用该职业暴露项目?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/bl004CsDetailinfo/f_json/stop.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											bl004CsDetailinfo.query();
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
			    start:function(id) {
			    	$.messager.confirm('提示', '确认启用用该职业暴露项目?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/bl004CsDetailinfo/f_json/start.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											bl004CsDetailinfo.query();
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
			    }
			};
			$(document).ready(function () { 
				$('#'+bl004CsDetailinfo.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/bl004CsDetailinfo/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'csmId',title:'项目分类编号',sortable:true,width:100},  
		                    {field:'csmName',title:'项目分类名称',sortable:true,width:100},  
		                    {field:'csdId',title:'职业暴露项目编号',sortable:true,width:120},
		                    {field:'csdName',title:'职业暴露项目名称',sortable:true,width:200},  
		                    {field:'_flag',title:'状态',sortable:true,width:60,formatter:function(value, r){
								if(r.flag===1) {
									return '已启用';
								} else {
									return '未启用';
								}
							}},
		                    {field:'_operate',title:'操作',width:75,
								formatter:function(value,r){
									return ['<a href="javascript:bl004CsDetailinfo.edit(\'',$.trim(r.csmId),'\',\'',$.trim(r.csdId),'\',\'修改\');" class="ico_editor" title="修改"></a>',
									'<a href="javascript:bl004CsDetailinfo.del(\'',$.trim(r.csmId),'\',\'',$.trim(r.csdId),'\');" class="ico_del" title="删除"></a>'].join('');
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
