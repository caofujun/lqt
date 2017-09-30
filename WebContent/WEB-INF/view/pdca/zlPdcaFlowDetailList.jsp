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
		<div id="zlPdcaFlowDetailPanel"></div>
		<div id="tb" class="m_search">		     
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="zlPdcaFlowDetail.edit('','新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var zlPdcaFlowDetail = {
				panel : 'zlPdcaFlowDetailPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+zlPdcaFlowDetail.panel).datagrid({
			            queryParams: {
			                'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(fdSubid, title) {
			    	if(fdSubid===undefined) fdSubid = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/zlPdcaFlowDetail/f_view/toedit.shtml?fdSubid=" + fdSubid+"&fuid=${fuid}",
			            title: title,
			            width:600,
			            height:260,
			            type:"iframe",
			            parent:this
			        });
			    },
			    //上移
			    move:function(fdSubid) {
			    	$.messager.confirm('提示', '确认上移?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/zlPdcaFlowDetail/f_json/move.shtml',
			                        type: 'post',
			                        data: { fdSubid: fdSubid },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											zlPdcaFlowDetail.query();
			                                $.messager.show({ title: '提示', msg: '操作成功！' });
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
			    //删除
			    del:function(fdSubid) {
			    	$.messager.confirm('提示', '确认删除该模板?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/zlPdcaFlowDetail/f_json/delete.shtml',
			                        type: 'post',
			                        data: { fdSubid: fdSubid },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											zlPdcaFlowDetail.query();
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
				$('#'+zlPdcaFlowDetail.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/zlPdcaFlowDetail/f_json/list.shtml?fuid=${fuid}',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[ 
		                    {field:'processName',title:'过程名称',sortable:true,width:40},
		                    {field:'_operate',title:'操作',width:20,
								formatter:function(value,r){
										if(r.xh==1){
											return ['<a href="javascript:zlPdcaFlowDetail.edit(\'',r.fdSubid,'\',\'修改\');" class="ico_editor" title="修改"></a>',
													'<a href="javascript:zlPdcaFlowDetail.del(\'',r.fdSubid,'\');" class="ico_del" title="删除"></a>'].join('');							
										}else{
											return ['<a href="javascript:zlPdcaFlowDetail.edit(\'',r.fdSubid,'\',\'修改\');" class="ico_editor" title="修改"></a>',
													'<a href="javascript:zlPdcaFlowDetail.del(\'',r.fdSubid,'\');" class="ico_del" title="删除"></a>','<input type="button" style="margin-top:6px" class="ico_up" onclick="zlPdcaFlowDetail.move(\'',r.fdSubid,'\')" title="上移"/>'].join('');							
										}
								}
							}
		                ]
	                ],             
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>
