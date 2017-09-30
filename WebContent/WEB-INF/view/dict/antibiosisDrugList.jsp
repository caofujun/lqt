<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>药敏抗菌药物</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body>
		
		<div id="antibiosisDrugPanel"></div>
		<div id="tb" class="m_search">
			抗菌药物分类：<input name="drugTypeid" id="id_drugTypeid">
			<input type="text" class="auto-tip" data-tip="抗菌药物名称/编号" id="searchString2"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="antibiosisDrug.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="antibiosisDrug.edit('','新增抗菌药物')" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<script type="text/javascript">
			var antibiosisDrug = {
				panel : 'antibiosisDrugPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+antibiosisDrug.panel).datagrid({
			            queryParams: {
			                'drugTypeid': $('#id_drugTypeid').combogrid('getValue'),
			                'searchString': $('#searchString2').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/antibiosisDrug/f_view/toedit.shtml?id=" + id,
			            title: title,
			            width:400,
			            parent:this
			        });
			    },
			  //停用
			    stop:function(id) {
			    	$.messager.confirm('提示', '确认停用该抗菌药物?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/antibiosisDrug/f_json/stop.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											antibiosisDrug.query();
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
			    	$.messager.confirm('提示', '确认启用用该抗菌药物?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/antibiosisDrug/f_json/start.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											antibiosisDrug.query();
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
			    	$.messager.confirm('提示', '确认删除该抗菌药物?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/antibiosisDrug/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											antibiosisDrug.query();
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
				$('#'+antibiosisDrug.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/antibiosisDrug/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[	                     
		                    {field:'drugId',title:'抗菌药物编号',sortable:true,width:120},
		                    {field:'drugName',title:'抗菌药物名称',sortable:true,width:200},  
		                    {field:'drugEnname',title:'抗菌药物英文名',sortable:true,width:200},
		                    {field:'drugTypeName',title:'抗菌药物分类',sortable:true,width:150},
		                    {field:'subclassName',title:'抗菌药物亚类',sortable:true,width:150}, 
		                    {field:'_operate',title:'操作',width:75,
								formatter:function(value,r){
									if(r.dictStatus=='1'){
										return ['<a href="javascript:antibiosisDrug.edit(\'',$.trim(r.drugId),'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:antibiosisDrug.del(\'',$.trim(r.drugId),'\');" class="ico_del" title="删除"></a>'].join('');
									}else{
										return ['<a href="javascript:antibiosisDrug.edit(\'',$.trim(r.drugId),'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:antibiosisDrug.del(\'',$.trim(r.drugId),'\');" class="ico_del" title="删除"></a>'].join('');
									}
									
								}
							}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb'
	            });
				//抗菌药物分类
				Csm.combogrid.kjywfl({
					id: 'id_drugTypeid',
					value:'${antibiosisDrug.drugTypeid}'
				});
			});
		</script>
	</body>
</html>
