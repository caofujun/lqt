<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>ICD10字典管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body>
		
		<div id="icd10Panel"></div>
		<div id="tb" class="m_search">
			<input type="text" class="auto-tip" data-tip="ICD10名称/编号" id="searchString"/>
		    <div class="n_btn_blue">
				<a href="javascript:;" onclick="icd10.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="icd10.edit('','新增ICD10')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<script type="text/javascript">
			var icd10 = {
				panel : 'icd10Panel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+icd10.panel).datagrid({
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
			        	url:"${webroot}/icd10/f_view/toedit.shtml?id=" + id,
			            title: title,
			            width:550,
			            parent:this
			        });
			    },
			  //停用
			    stop:function(id) {
			    	$.messager.confirm('提示', '确认停用该ICD10?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/icd10/f_json/stop.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											icd10.query();
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
			    	$.messager.confirm('提示', '确认启用用该ICD10?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/icd10/f_json/start.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											icd10.query();
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
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该ICD10?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/icd10/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											icd10.query();
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
				$('#'+icd10.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/icd10/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'icdCode',title:'ICD10编码',sortable:true,width:120},  
		                    {field:'icdName',title:'ICD10名称',sortable:true,width:300},
		                    {field:'_operate',title:'操作',width:75,
								formatter:function(value,r){
									if(r.dictStatus=='1'){
										return ['<a href="javascript:icd10.edit(\'',$.trim(r.icdId),'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:icd10.del(\'',$.trim(r.icdId),'\');" class="ico_del" title="删除"></a>'].join('');
									}else{
										return ['<a href="javascript:icd10.edit(\'',$.trim(r.icdId),'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:icd10.del(\'',$.trim(r.icdId),'\');" class="ico_del" title="删除"></a>'].join('');
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
