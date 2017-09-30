<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>字典管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
</head>
	<body>
		<div id="sysDictPanel"></div>
		<div id="tb" class="m_search">
			<input type="text" class="auto-tip" data-tip="字典名称/字典编码" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;"  onclick="sysDict.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;"  onclick="sysDict.edit('','新增字典')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var sysDict = {
				panel : 'sysDictPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+sysDict.panel).datagrid({
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
			        	url:"${webroot}/sysDict/f_view/toedit.shtml?id=" + id+"&dictTypeCode=${code}",
			            title: title,
			            width:650,
			            parent:this
			        });
			    },
			  //停用
			    stop:function(id) {
			    	$.messager.confirm('提示', '确认停用该字典?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sysDict/f_json/stop.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											sysDict.query();
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
			                        url: '${webroot}/sysDict/f_json/start.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											sysDict.query();
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
			    	$.messager.confirm('提示', '确认删除该字典?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sysDict/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											sysDict.query();
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
			    // 上下移动
				moveSort : function (obj,id,num,type){
					obj = $(obj).parent().parent().parent();
					var _params = '';
					var objEx;
					if (type=='up') {
						obj = obj.prev('tr');
						objEx = obj.find('.ico_top');
					} else if(type == 'down'){
						obj = obj.next('tr');
						objEx = obj.find('.ico_down');
					} else if(type == 'totop'){
						obj = obj.prev('tr');
						objEx = obj.find('.ico_top_disable');
					} else if(type == 'todown'){
						obj = obj.next('tr');
						objEx = obj.find('.ico_down_disable');
					}
					_params += id + ',' + objEx.attr('seqnum') + '#' + objEx.attr('id') + ',' + num;
					$.ajax({
						url: '${webroot}/sysDict/f_json/updateSort.shtml',
						type: 'post',
						data: { 'params': _params },
						dataType: 'json',
						success : function(json) {
							if (json.result === 'success') {
								$.messager.show({ title : '提示', msg : '操作成功！' });
								sysDict.query();
							} else if(json.result === 'error') {
								$.messager.show({ title : '提示', msg : '操作失败！' });
							} else {
								$.messager.show({ title : '提示', msg : json.msg });
							}
						}
			        });
				}
			};
			$(document).ready(function () {
				$('#'+sysDict.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/sysDict/f_json/pageQuery.shtml?dictTypeCode=${code}',
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'dictName',title:'字典名称',sortable:true,width:63},
		                    {field:'dictCode',title:'字典编码',sortable:true,width:63},
		                    {field:'sequenceNumber',title:'排序号',sortable:true,width:50},
		                    {field:'dictStatusName',title:'状态',sortable:true,width:40},
		                    {field:'scopelevelName',title:'应用类型',sortable:true,width:63},
		                    {field:'extParam1',title:'扩展参数1',sortable:true,width:70},
		                    {field:'extParam2',title:'扩展参数2',sortable:true,width:70},
		                    {field:'_operate',title:'操作',width:75,
								formatter:function(value,r,index){
									var data = $('#sysDictPanel').datagrid('getData');
									var str = '';
									if(index == 0){
										str += '<input type="button" class="ico_top_disable" title="" id="'+ r.id +'" seqnum="' + r.sequenceNumber + '" />';
									}else if(index == 1){
										str += '<input type="button" class="ico_top" title="上移" onclick="sysDict.moveSort(this,\'' + r.id + '\',\'' + r.sequenceNumber + '\',\'totop\');" id="'+ r.id +'" seqnum="' + r.sequenceNumber + '" />';
									}else{
										str += '<input type="button" class="ico_top" title="上移" onclick="sysDict.moveSort(this,\'' + r.id + '\',\'' + r.sequenceNumber + '\',\'up\');" id="'+ r.id +'" seqnum="' + r.sequenceNumber + '" />';
									}
									if(index == (data.rows.length)-1){
										str += '<input type="button"  class="ico_down_disable" title=""id="'+ r.id +'" seqnum="' + r.sequenceNumber + '" />';
									}else if(index == (data.rows.length)-2){
										str += '<input type="button" class="ico_down" title="下移" onclick="sysDict.moveSort(this,\'' + r.id + '\',\'' + r.sequenceNumber + '\',\'todown\');" id="'+ r.id +'" seqnum="' + r.sequenceNumber + '" />';
									}else{
										str += '<input type="button" class="ico_down" title="下移" onclick="sysDict.moveSort(this,\'' + r.id + '\',\'' + r.sequenceNumber + '\',\'down\');" id="'+ r.id +'" seqnum="' + r.sequenceNumber + '" />';
									}
									if(r.dictStatus=='1'){
										return ['<a href="javascript:sysDict.edit(\'',r.id,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										        '<a href="javascript:sysDict.stop(\'',r.id,'\');" class="ico_stop" title="停用"></a>',
												'<a href="javascript:sysDict.del(\'',r.id,'\');" class="ico_del" title="删除"></a>',
												str,
												''].join('');
									}else{
										return ['<a href="javascript:sysDict.edit(\'',r.id,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										        '<a href="javascript:sysDict.start(\'',r.id,'\');" class="ico_select" title="启用"></a>',
												'<a href="javascript:sysDict.del(\'',r.id,'\');" class="ico_del" title="删除"></a>',
												str,
												''].join('');
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