<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>传染病字典维护</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css${version}" />
<style type="text/css">
</style>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body class="easyui-layout">
	<div id="diseasePanel"></div>
	<div id="tb" class="m_search">
			<input type="text" class="auto-tip" data-tip="疾病名称/疾病编码" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="diseaseMt.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="diseaseMt.edit(undefined,'新增参数')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var diseaseMt = {
				panel : 'diseasePanel',
				length : 0,
				//查询
				query : function() {
					if("" != $('#searchString').val() && "疾病名称/疾病编码" != $('#searchString').val()){
						diseaseMt.length=1;
					}
					autoTip.clear();
			        $('#'+diseaseMt.panel).treegrid({
			            queryParams: {
			                'searchString': $('#searchString').val()
			            }			            
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			    	Comm.dialogGlobal({
			        	url:"${webroot}/cdc/f_view/diseaseEdit.shtml?diseaseid=" + id,
			            title: title,
			            parent: this,
			            width:600,
			            height:400
			        });
			    },
			    //删除
			    del:function(id,caninput) {
			    	var tip = "确认删除该疾病？";
			    	var c = "";
			    	if(caninput!=1){
			    		tip = "确认删除该疾病并移除所有子节点？";
			    		c="1";
			    	}
			    	$.messager.confirm('提示', tip, function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/cdc/f_json/deleteDisease.shtml',
			                        type: 'post',
			                        data: { 'diseaseid': id, 'delByParent': c },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											diseaseMt.query();
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
			    changeStatus : function(ele,diseaseid,type){
			    	$.ajax({
			    		url:"${webroot}/cdc/f_json/changeStatus.shtml",
			    		type:"POST",
			    		data:{
			    			"diseaseid":diseaseid,
			    			"type":type,
			    			"value":$(ele).is(":checked")?"1":"0"
			    		},success:function(json){
			    			if(json.result=="error"){
			    				$.messager.show({title : '提示',msg : '保存失败！'});
			    				diseaseMt.query();
			    			}else{
			    				$.messager.show({title : '提示',msg : '操作成功！'});
			    			}
			    		},error:function(){
			    			$.messager.show({title : '提示',msg : '操作失败！'});
			    		}
			    	});
			    }
			};
			$(document).ready(function () { 
				$('#'+diseaseMt.panel).treegrid({
	                fit: true,
	                nowrap: false,
	                autoRowHeight: false,
	                striped: true,
	                iconCls: 'icon icon-org',
	                idField: 'diseaseid', 
	                treeField: 'diseasename',
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/cdc/f_json/diseaseMaintainQuery4Tree.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                loadMsg: "加载中......",
	                columns:[
	                	[
		                    {field:'diseasename',title:'疾病名称',sortable:true,width:200},  
		                    {field:'diseaseid',title:'疾病编号',sortable:true,width:60,formatter:function(value,row,index){
		                    	if(row.kindid){
		                    		return value;
		                    	}
		                    }},
		                    {field:'zjf',title:'首拼码',sortable:true,width:80},
		                    {field:'isintestinal',title:'是否肠道传染病',sortable:true,width:80,hidden:true,formatter:function(value,row,index){
		                    	if(row.caninput==1){
			                    	return '<input type="checkbox" onclick="diseaseMt.changeStatus(this,\''+row.diseaseid+'\',\'1\')" '+(value==1?"checked=chekced":"")+' />';
		                    	}
		                    }},
		                    {field:'isrespiratory',title:'是否呼吸道传染病',sortable:true,width:80,hidden:true,formatter:function(value,row,index){
		                    	if(row.caninput==1){
		                    		return '<input type="checkbox" onclick="diseaseMt.changeStatus(this,\''+row.diseaseid+'\',\'2\')" '+(value==1?"checked=chekced":"")+' />';
		                    	}
	                    	}},
		                    {field:'isnatural',title:'是否自然疫源及虫媒传染病',sortable:true,hidden:true,width:80,formatter:function(value,row,index){
		                    	if(row.caninput==1){
		                    		return '<input type="checkbox" onclick="diseaseMt.changeStatus(this,\''+row.diseaseid+'\',\'3\')" '+(value==1?"checked=chekced":"")+' />';
		                    	}
		                    }},
		                    {field:'ishemic',title:'是否血源传播传染病',sortable:true,width:80,hidden:true,formatter:function(value,row,index){
		                    	if(row.caninput==1){
		                    		return '<input type="checkbox" onclick="diseaseMt.changeStatus(this,\''+row.diseaseid+'\',\'4\')" '+(value==1?"checked=chekced":"")+' />';
		                    	}
	                    	}},
		                    {field:'issexspread',title:'是否性传播传染病',sortable:true,width:80,hidden:true,formatter:function(value,row,index){
		                    	if(row.caninput==1){
		                    		return '<input type="checkbox" onclick="diseaseMt.changeStatus(this,\''+row.diseaseid+'\',\'5\')" '+(value==1?"checked=chekced":"")+' />';
		                    	}
	                    	}},
		                    {field:'sexcard',title:'是否需要填写性病附卡',sortable:true,width:80,formatter:function(value,row,index){
		                    	if(row.caninput==1){
		                    		return '<input type="checkbox" onclick="diseaseMt.changeStatus(this,\''+row.diseaseid+'\',\'6\')" '+(value==1?"checked=chekced":"")+' />';
		                    	}
		                    }},
		                    {field:'scopetime',title:'诊断到报告法定间隔（小时）',sortable:true,width:80,formatter:function(value,row,index){
		                    	if(row.caninput==1){
		                    		return value;
		                    	}
		                    }},
		                    {field:'repeatCycle',title:'重卡验证周期（月）',sortable:true,width:80,formatter:function(value,row,index){
		                    	if(row.caninput==1){
		                    		return value;
		                    	}
		                    }},
		                    {field:'_operate',title:'操作',width:60,
								formatter:function(value,r){
									if(r.kindid){
										return ['<a href="javascript:diseaseMt.edit(\'',r.diseaseid,'\',\'修改\');" class="ico_editor" title="修改"></a>',
										'<a href="javascript:diseaseMt.del(\'',r.diseaseid,'\',\'',r.caninput,'\');" class="ico_del" title="删除"></a>'].join('');
									}
								}
							}
		                ]
	                ],
	                toolbar:'#tb',
	        		onLoadSuccess : function(data){
		        		if (diseaseMt.length == 1) {
							$('#' + diseaseMt.panel).datagrid('collapseGroup');
		                	$('#' + diseaseMt.panel).datagrid('expandGroup', 0);
		            	}else{
		                	$('#' + diseaseMt.panel).datagrid('collapseGroup');
		            	}
		        		diseaseMt.length=0;
	        		}
	            });
	            
			});
		</script>
</body>
</html>