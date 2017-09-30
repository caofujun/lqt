<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>SOP字典管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body>		
		<div id="sopPanel"></div>
		<div id="tb" class="m_search">
			<nis:select id="pFileId" dictcode="sop_type" exp="style='width:150px;' data-options=\"panelWidth:280,editable:false\"" cssCls="easyui-combobox" headerKey="" headerValue="-SOP分类-" />
			<input type="text" class="auto-tip" data-tip="SOP名称/SOP编码" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="sop.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="sop.edit('','新增SOP字典')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<script type="text/javascript">
			var sop = {
				panel : 'sopPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+sop.panel).datagrid({
			            queryParams: {
			                'pFileId': $('#pFileId').combogrid('getValue'),
			                'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/sop/f_view/toedit.shtml?id=" + id+"&pFileId=" + $('#pFileId').combogrid('getValue'),
			            title: title,
			            width:750,
			            type:"iframe",
			            height:360,
			            parent:this
			        });
			    },
			  //停用
			    stop:function(id) {
			    	$.messager.confirm('提示', '确认停用该字典?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sop/f_json/stop.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											sop.query();
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
			    	$.messager.confirm('提示', '确认启用用该字典?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sop/f_json/start.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											sop.query();
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
			    	$.messager.confirm('提示', '确认删除该SOP字典?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sop/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											sop.query();
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
				$('#'+sop.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/sop/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'pFileId',title:'SOP类型编码',sortable:true,width:100},  
		                    {field:'pFileName',title:'SOP类型',sortable:true,width:200},  
		                    {field:'fileId',title:'SOP编码',sortable:true,width:100},
		                    {field:'fileTitle',title:'SOP名称',sortable:true,width:300,
								formatter:function(value,r){
									if(r.showFileData == null || r.showFileData == ''){
										return [r.fileTitle].join('');
									}else{
										return ['<a href="#" title="修改">',r.fileTitle,'</a>'].join('');
									}
								}
		                    },
		                    {field:'_operate',title:'操作',width:60,
								formatter:function(value,r){
									if(r.dictStatus=='1'){
										return ['<a href="javascript:sop.edit(\'',r.fileId,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:sop.del(\'',r.fileId,'\');" class="ico_del" title="删除"></a>'].join('');
									}else{
										return ['<a href="javascript:sop.edit(\'',r.fileId,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:sop.del(\'',r.fileId,'\');" class="ico_del" title="删除"></a>'].join('');
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
