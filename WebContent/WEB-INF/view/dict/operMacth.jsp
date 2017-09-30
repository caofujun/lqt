<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>手术匹配</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">	
	<div data-options="region:'west',collapsed:false,border:false" style="width:400px; border-right-width: 1px;">
		<div class="easyui-layout" data-options="fit:true">
	        <div data-options="region:'north',split:true" style="height:240px;border-width: 0px 0px 1px 1px;">
	           <div id="zg024ImpOpePanel"></div>
	        </div>
	        <div data-options="region:'center',border:false,title:'已匹配手术'">
	        	<div id="zg011SsPanel2"></div>
	        </div>
	    </div>

	</div>
	<div data-options="region:'center',border:false,title:'未匹配手术'">
		 <div id="tb2" class="m_search">
			<input type="text" class="auto-tip" style="width:105px" data-tip="手术名称/编码" id="searchString"/>
			<div class="n_btn_blue">				
				<a href="javascript:;"  onclick="zg024ImpOpe.query2()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>			
		 </div>
		 <div id="zg011SsPanel"></div>
	</div>
	</div>
		<script type="text/javascript">
		function seturl(impOpeId){
			 $('#'+zg024ImpOpe.panel3).datagrid({
				 queryParams: {
		            	'isImp': 1,
		            	'impOpeId':impOpeId
		            },
			 });
		}
		var zg024ImpOpe = {
			panel : 'zg024ImpOpePanel',
			panel2 : 'zg011SsPanel',
			panel3 : 'zg011SsPanel2',
			//查询
			query1 : function() {
				autoTip.clear();
		        $('#'+zg024ImpOpe.panel).datagrid({});	       
			},
			//查询
			query2 : function() {
				autoTip.clear();	       
		        $('#'+zg024ImpOpe.panel2).datagrid({
		        	 queryParams: {
		            	'isImp': 0,
		            	'searchString': $('#searchString').val()
			         },
		        });	      
			},
			//配置
			peizhi : function() {
				Comm.dialogGlobal({
			        	url:"${webroot}/zg011Ss/f_view/zdssquery.shtml",
			            title: "重点手术",
			            width:800,
			            height:500,
			            type:"iframe",
			            parent:this
			        });
			},
			//查询
			query3 : function() {
				autoTip.clear();		    
		        $('#'+zg024ImpOpe.panel3).datagrid({});
			},
		    //新增
		    add:function(title) {
		        Comm.dialog({
		        	url:"${webroot}/zg011Ss/f_view/toedit.shtml?",
		            title: title,
		            width:450,
		            height:230
		        });
		    },			 
		    //编辑
		    edit:function(title) {
		    	//获取选中行值
		        var curRow = $('#'+zg024ImpOpe.panel2).datagrid('getSelected');
		        if (curRow) {
		        	Comm.dialog({
			        	url:"${webroot}/zg011Ss/f_view/toedit.shtml?operId=" + curRow.operId,
			            title: title,
			            width:450,
			            height:230
			        });
		        }
		    },
		    //删除
		    del:function() {
		    	//获取选中行值
		        var curRow = $('#'+zg024ImpOpe.panel3).datagrid('getSelected');
		        if (curRow) {
			    	$.messager.confirm('提示', '确认取消关联?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/zg011Ss/f_json/delete.shtml',
			                        type: 'post',
			                        data: { operId: curRow.operId },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											zg024ImpOpe.query3();
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
		    }
		};
		$(document).ready(function () {
			autoTip.clear();
			$('#'+zg024ImpOpe.panel).datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,
                striped: true,
                fitColumns: true,
                collapsible:true,
                border:false,
                url:'${webroot}/zg024ImpOpe/f_json/pageQuery.shtml',	                
                remoteSort: false,
                singleSelect: true,
                columns:[
                	[              
	                    {field:'dictCode',title:'编码',sortable:true,width:10,},
	                    {field:'dictName',title:'重点手术<div class="n_btn_grey" style="margin-left:150px;"><a href="javascript:;"  onclick="zg024ImpOpe.peizhi()""><span>配置</span></a></div>',width:40}
	              	],
                ],
    	        rownumbers:true,
                toolbar:'#tb',
                onLoadSuccess: function() {
                	$('#'+zg024ImpOpe.panel).datagrid('selectRow', 0);
                	var curRow = $('#'+zg024ImpOpe.panel).datagrid('getSelected');
                	seturl(curRow.impOpeId);
                },
                onClickRow:function(rowIndex, rowData){	                
                	seturl(rowData.impOpeId);
		    	}
            });
			
			$('#'+zg024ImpOpe.panel2).datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,
                striped: true,
                fitColumns: true,
                collapsible:true,
                border:false,
                url:'${webroot}/zg011Ss/f_json/pageQuery.shtml',
                queryParams: {
	            	'isImp': 0
	            },
                remoteSort: false,
                singleSelect: true,
                columns:[
                	[              
	                    {field:'operId',title:'编码',sortable:true,width:10,},
	                    {field:'operaCnname',title:'手术名称',sortable:true,width:40},
	                    {field:'_operate',title:'设置',width:20,
							formatter:function(value,r){
								return ['<a href="javascript:zg024ImpOpe.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');								
							}
						}
	              	],
                ],
    	        rownumbers:true,
    	        pagination:true,
                toolbar:'#tb2'
            });
			$('#'+zg024ImpOpe.panel3).datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,
                striped: true,
                fitColumns: true,
                collapsible:true,
                border:false,
                url:'${webroot}/zg011Ss/f_json/pageQuery.shtml',
                queryParams: {
	            	'isImp': 1,
	            	'impOpeId':1
	            },
                remoteSort: false,
                singleSelect: true,
                columns:[
                	[              
	                    {field:'operId',title:'编码',sortable:true,width:10,},
	                    {field:'operaCnname',title:'手术名称',sortable:true,width:40},
	                    {field:'_operate',title:'操作',width:20,
							formatter:function(value,r){
								return ['<a href="javascript:zg024ImpOpe.del();" class="ico_del" title="取消关联"></a>'].join('');								
							}
						}
	              	],
                ],
    	        rownumbers:true,
    	        pagination:true,
                toolbar:'#tb'
            });
		});
		</script>
	</body>
</html>