<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>医院管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="unitPanel"></div>
	<div id="tb" class="m_search" >		
		<nis:select id="flag" dictcode="enable_status" headerKey="" headerValue="医院状态" cssCls="easyui-combobox"/>		
		<input type="text"  id="searchString" class="auto-tip" data-tip="请输入医院名称或ID" /> 
		<div class="n_btn_blue">
			<a href="javascript:;" onclick="unit.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>		
		<div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="unit.edit(undefined,'新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>		
	</div>

	<script type="text/javascript">
			var unit = {
				panel:'unitPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+unit.panel).datagrid({
			            queryParams: {
			                'searchString': $('#searchString').val(),
			                'flag':$('#flag').combogrid('getValue')
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			    	Comm.dialogGlobal({
			        	url:"${webroot}/unit/f_view/toedit.shtml?id=" + id,
			            title: title,
			            width:650
			        });
			    },
			  //停用
			    stop:function(id) {
			    	$.messager.confirm('提示', '确认停用该医院?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/unit/f_json/stop.shtml',
			                        type: 'post',
			                        data: { unitId: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											unit.query();
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
			    	$.messager.confirm('提示', '确认启用用该医院?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/unit/f_json/start.shtml',
			                        type: 'post',
			                        data: { unitId: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											unit.query();
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
			    	$.messager.confirm('提示', '确认删除该医院?', function (r) {
			        	if (r) {
			            	$.ajax({
		                        url: '${webroot}/unit/f_json/delete.shtml',
		                        type: 'post',
		                        data: { id: id },
		                        dataType: 'json',
		                        success : function(json) {
									if(json.result==='success') {
							    		unit.query();
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
			    setDep:function(hospId){
					parent.menuInfo.clickMenu('科室信息','/dep/f_view/index.shtml?hospId=' + hospId,true,'A0102');
			    }
			};

			$(document).ready(function () { 
				$('#'+unit.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/unit/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'unitId',title:'医院ID',sortable:true,width:120},  
		                    {field:'hospName',title:'医院名称',sortable:true,width:170},
		                    {field:'showHospLevel',title:'医院等级',sortable:true,width:70},
		                    {field:'showHospType',title:'医院类型',sortable:true,width:70},
		                    {field:'showHospNature',title:'医院类型',sortable:true,width:70},
		                    {field:'showIfbranch',title:'是否分院',sortable:true,width:60},
		                    {field:'showFlag',title:'状态',sortable:true,width:40},
		                    {field:'showCreateAt',title:'录入时间',sortable:true,width:80},
		                    {field:'_operate',title:'操作',width:100,
								formatter:function(value,r){
									var into = '';
									if(r.flag == '1'){
										return ['<!--a href="javascript:unit.edit(\'',r.unitId,'\',\'修改\');" class="ico_editor" title="修改"></a-->',
												'<a href="javascript:unit.setDep(\'',r.unitId,'\');" class="ico_setup" title="设置科室"></a>',
										        '<a href="javascript:unit.stop(\'',r.unitId,'\');" class="ico_stop" title="停用"></a>',
												'<a href="javascript:unit.del(\'',r.unitId,'\');" class="ico_del" title="删除"></a>',
										        into].join('');
									}else{
										return ['<!--a href="javascript:unit.edit(\'',r.unitId,'\',\'修改\');" class="ico_editor" title="修改"></a-->',
												'<a href="javascript:unit.setDep(\'',r.unitId,'\');" class="ico_setup" title="设置科室"></a>',
										        '<a href="javascript:unit.start(\'',r.unitId,'\');" class="ico_select" title="启用"></a>',
												'<a href="javascript:unit.del(\'',r.unitId,'\');" class="ico_del" title="删除"></a>',
										        into].join('');
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