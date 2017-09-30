<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="tb" class="m_search">
		<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;"  onclick="sysDict.edit('','新增字典')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
		</div>
		</div>
		<div id="yzxxDetailPanel" style="width:100%;"></div>
		<script type="text/javascript">
			/**
	        * EasyUI DataGrid根据字段动态合并单元格
	        * @param fldList 要合并table的id
	        * @param fldList 要合并的列,用逗号分隔(例如："name,department,office");
	        */
	        var sysDict = {
					panel : 'sysDictPanel',
					//查询
					query : function() {
						autoTip.clear();
						$('#'+mdrDetail.panel).datagrid({
				        	
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
				        	url:"${webroot}/sysDict/f_view/toedit.shtml?id=" + id+"&dictTypeCode=operation_point",
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
				    }
				};
	        function MergeCells(tableID, fldList) {
	            var Arr = fldList.split(",");
	            var dg = $('#' + tableID);
	            var fldName;
	            var RowCount = dg.datagrid("getRows").length;
	            var span;
	            var PerValue = "";
	            var CurValue = "";
	            var length = Arr.length - 1;
	            for (i = length; i >= 0; i--) {
	                fldName = Arr[i];
	                PerValue = "";
	                span = 1;
	                for (row = 0; row <= RowCount; row++) {
	                    if (row == RowCount) {
	                        CurValue = "";
	                    }
	                    else {
	                        CurValue = dg.datagrid("getRows")[row][fldName];
	                    }
	                    if (PerValue == CurValue) {
	                        span += 1;
	                    }
	                    else {
	                        var index = row - span;
	                        dg.datagrid('mergeCells', {
	                            index: index,
	                            field: fldName,
	                            rowspan: span,
	                            colspan: null
	                        });
	                        span = 1;
	                        PerValue = CurValue;
	                    }
	                }
	            }
	        }
			var mdrDetail = {
				panel : 'yzxxDetailPanel'
			};
			$(document).ready(function () { 
				$('#'+mdrDetail.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: false,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/zg011Ss/f_json/zdssDetail.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[ 
		                    {field:'dictName',title:'字典名称',sortable:true,width:63},  
		                    {field:'dictCode',title:'字典编码',sortable:true,width:63},
		                    {field:'dictStatusName',title:'状态',sortable:true,width:40},
		                    {field:'_operate',title:'操作',width:75,
								formatter:function(value,r){
									if(r.dictStatus=='1'){
										return ['<a href="javascript:sysDict.edit(\'',r.id,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										        '<a href="javascript:sysDict.stop(\'',r.id,'\');" class="ico_stop" title="停用"></a>',
										'<a href="javascript:sysDict.del(\'',r.id,'\');" class="ico_del" title="删除"></a>'].join('');
									}else{
										return ['<a href="javascript:sysDict.edit(\'',r.id,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										        '<a href="javascript:sysDict.start(\'',r.id,'\');" class="ico_select" title="启用"></a>',
										'<a href="javascript:sysDict.del(\'',r.id,'\');" class="ico_del" title="删除"></a>'].join('');
									}
									
								}
							}
		                ]
	                ],	                 
		            onLoadSuccess:function(data){
		            	 MergeCells(yzxxDetailPanel.panel,'');
		            }
	            });
			});
		</script>
	</body>
</html>
