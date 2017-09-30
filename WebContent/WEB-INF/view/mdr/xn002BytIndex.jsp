<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>标准病原体字典</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
	</head>
	<body>
		
		<div id="bytPanel"></div>
		<div id="byttb" class="m_search">
			病原体：
			<input type="text" class="auto-tip" data-tip="病原体名称/编号" id="searchString"/>
			革兰氏分类：
			<select style="width:120px" id="rsId" class="easyui-combobox"><option value="">请选择</option><option value="G+">G+</option><option value="G-">G-</option></select>
			菌属分类：
			<div class="select_del"><input id="jszd"  style="width:120px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#jszd').combo('clear');"></a></div>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="xn002Byt.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="xn002Byt.edit('','新增病原体')" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<script type="text/javascript">
			var xn002Byt = {
				panel : 'bytPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+xn002Byt.panel).datagrid({
			            queryParams: {
			                'rsId': $('#rsId').combogrid('getValue'),
			                'jszd': $('#jszd').combogrid('getValue'),
			                'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/xn002Byt/f_view/edit.shtml?id=" + id,
			            title: title,
			            width:400,
			            parent:this
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该病原体?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/xn002Byt/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											xn002Byt.query();
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
				Csm.combogrid.jszd({
					//【必传】控件名称
					id: 'jszd'
				});
				$('#'+xn002Byt.panel).datagrid({
	                url:'${webroot}/xn002Byt/f_json/pageQuery.shtml',   
	                singleSelect: false,
	                checkOnSelect:false,
	                selectOnCheck:false,
	                checkbox:true,
	                fit: true,
	                fitColumns: true,
// 	                idField:'itemName',
// 	                treeField:'itemName',
	                columns:[
	                	[	                     
		                    {field:'pathogenId',title:'病原体编号',sortable:true,width:120},
		                    {field:'pathogenName',title:'病原体名称',sortable:true,width:200},  
		                    {field:'pathogenEnName',title:'英文名',sortable:true,width:150},
		                    {field:'spCode',title:'首拼码',sortable:true,width:150},
		                    {field:'itemName',title:'革兰氏类',sortable:true,width:150},
		                    {field:'jsName',title:'菌属',sortable:true,width:150},
		                    {field:'_operate',title:'操作',width:75,
								formatter:function(value,r){
									return ['<a href="javascript:xn002Byt.edit(\'',$.trim(r.pathogenId),'\',\'修改\');" class="ico_editor" title="修改"></a>',
									'<a href="javascript:xn002Byt.del(\'',$.trim(r.pathogenId),'\');" class="ico_del" title="删除"></a>'].join('');
								}
							}
		                ]
	                ],
// 	                groupField:'itemName',
// 	        		view: groupview,
// 	        		groupFormatter : function(value, rows){
// 	        			return value;
// 	        		},
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#byttb'
	            });
			});
		</script>
	</body>
</html>
