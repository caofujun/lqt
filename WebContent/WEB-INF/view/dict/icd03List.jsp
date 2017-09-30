<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>ICD-0-3字典管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body>
		
		<div id="icd03Panel"></div>
		<div id="tb" class="m_search">
			<input type="text" class="auto-tip" data-tip="ICD-0-3名称/编号" id="searchString"/>		  
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="icd03.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;"  onclick="icd03.edit('','新增ICD-0-3')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<script type="text/javascript">
			var icd03 = {
				panel : 'icd03Panel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+icd03.panel).datagrid({
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
			        	url:"${webroot}/icd03/f_view/toedit.shtml?pathologyno=" + id,
			            title: title,
			            width:500,
			            parent:this
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该ICD0?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/icd03/f_json/delete.shtml',
			                        type: 'post',
			                        data: { pathologyno: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											icd03.query();
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
				$('#'+icd03.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/cdc/f_json/chooseICD0.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'pathologyno',title:'ICD0编码',sortable:true,width:100},
		                    {field:'pathologyname',title:'ICD0名称',sortable:true,width:250},
		                    {field:'pycode',title:'拼音码',sortable:true,width:250},
		                    {field:'_operate',title:'操作',width:75,
								formatter:function(value,r){
									if(r.dictStatus=='1'){
										return ['<a href="javascript:icd03.edit(\'',$.trim(r.pathologyno),'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:icd03.del(\'',$.trim(r.pathologyno),'\');" class="ico_del" title="删除"></a>'].join('');
									}else{
										return ['<a href="javascript:icd03.edit(\'',$.trim(r.pathologyno),'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:icd03.del(\'',$.trim(r.pathologyno),'\');" class="ico_del" title="删除"></a>'].join('');
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
