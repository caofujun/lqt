<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>PDCA管理平台</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
		<div id="tb" class="m_search">
			<span class="pro_text">创建时间：</span><input type="text"  style="width:85px" id="startDate" value="${startDate}"  class="Wdate text" onclick="WdatePicker()"  />~
			<input type="text"  id="endDate" value="${endDate}" style="width:85px" class="Wdate text" onclick="WdatePicker()" />			
			<input type="hidden" id="unit" value="${unitId}"/>
			科室：<div class="select_del"><input id="dep" name="depId" />	<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#dep').combo('clear');"></a></div>	    
			<input type="text" class="auto-tip" style="width:150px" data-tip="计划名称/编号" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="zlPdcaPlans.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="zlPdcaPlans.add('新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>		
		</div>
	<div id="zlPdcaPlansPanel"></div>
	<div id="pop">
		<iframe style="display: none;" id="download"></iframe>
		<div id="loading" style="margin-top: 35px;margin-left: 80px; display: none;">
			<img src="${webroot}/resources/images/loading.gif"/>
			<span>下载数据加载中...</span>
		</div>
	</div>
	<!-- div data-options="region:'center',split:true,collapsed:false">
	<iframe id="zlPdcaPlansDetailIframe" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
	</div-->
		<script type="text/javascript">
			function seturl(puid){
				var url="${webroot}/zlPdcaPlansDetail/f_view/index.shtml?puid="+puid;
			    $("#zlPdcaPlansDetailIframe").attr("src",url);
			}
			var zlPdcaPlans = {
				panel : 'zlPdcaPlansPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+zlPdcaPlans.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val(),
			                'startDate': $('#startDate').val(),
			                'endDate': $('#endDate').val(),
			                'deptId' : $('#dep').combogrid('getValue')
			            },
			            pageNumber: 1
			        });
			    },
			  //新增
			    add:function(title) {
				        Comm.dialogGlobal({
				        	url:"${webroot}/zlPdcaPlans/f_view/toedit.shtml?",
				            title: title,
				            width:650,
				            height:450,
				            type:"iframe",
				            parent:this
				        });
			    },
			    exportFile:function(){
			    	//获取选中行值
			        var curRow = $('#'+zlPdcaPlans.panel).datagrid('getSelected');
			        if (curRow) {
				    	$('#pop').window({
				    		  title:'导出',   
						      width:250,   
						      height:150,   
						      modal:true  
					 	 });
					 	 
					 	$('#loading').show();
					 	$('#download').attr('src','${webroot}/zlPdcaPlans/f_json/exportWord.shtml?puid='+curRow.puid);
					 	 window.setTimeout(function(){
					 	 	$('#loading').hide();
					 	 	$('#pop').window('close');
					 	 },5000);
			        }
			    },
			    //编辑
			    edit:function(title) {
			    	//获取选中行值
			        var curRow = $('#'+zlPdcaPlans.panel).datagrid('getSelected');
			        if (curRow) {
			        	Comm.dialogGlobal({
				        	url:"${webroot}/zlPdcaPlans/f_view/toedit.shtml?puid=" + curRow.puid,
				            title: title,
				            type:"iframe",
				            width:650,
				            height:500,
				            parent:this
				        });
			        }
			    },
			    //删除
			    del:function() {
			    	//获取选中行值
			        var curRow = $('#'+zlPdcaPlans.panel).datagrid('getSelected');
			        if (curRow) {
				    	$.messager.confirm('提示', '确认删除该流程?', function (r) {
				        	if (r) {
				            	$.ajax({
				                        url: '${webroot}/zlPdcaPlans/f_json/delete.shtml',
				                        type: 'post',
				                        data: { puid: curRow.puid },
				                        dataType: 'json',
				                        success : function(json) {
											if(json.result==='success') {
												zlPdcaPlans.query();
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
			        }else{
			        	 $.messager.show({ title: '提示', msg: '请选择需要删除的字典类型！' });
			        }
			    }
			};
			$(document).ready(function () {
				autoTip.clear();
				Csm.combogrid.dep({
					//【必传】控件名称
					id: 'dep',
					hospId:'${unitId}',
					isCaseOrEnvo: '1'
				});
				$('#'+zlPdcaPlans.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/zlPdcaPlans/f_json/pageQuery.shtml',
	                queryParams: {
		            	'searchString': $('#searchString').val(),         	
		                'startDate': $('#startDate').val(),
		                'endDate': $('#endDate').val(),
		                'deptId' : $('#dep').combogrid('getValue')
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
		                    {field:'planName',title:'PDCA名称',sortable:true,width:300},
		                    {field:'wcd',title:'完成度',sortable:true,width:45},
		                    {field:'deptName',title:'科室',sortable:true,width:110},
		                    {field:'createrName',title:'创建人',sortable:true,width:60},
		                    {field:'createDate',title:'创建时间',sortable:true,width:80},
		                    {field:'_operate',title:'操作',width:80,
								formatter:function(value,r){
										return ['<a href="javascript:zlPdcaPlans.edit(\'修改\');" class="ico_editor" title="修改"></a>',										     
										'<a href="javascript:zlPdcaPlans.del();" class="ico_del" title="删除"></a>','<a href="javascript:zlPdcaPlans.exportFile();" class="ico_output" title="导出word"></a>'].join('');
								}
							}
		                ]
	                ],
	    	        rownumbers:true,
	                pagination:true,
	                toolbar:'#tb',
	                onLoadSuccess: function() {
	                	$('#'+zlPdcaPlans.panel).datagrid('selectRow', 0);
	                	var curRow = $('#'+zlPdcaPlans.panel).datagrid('getSelected');
	                	seturl(curRow.puid);
	                },
	                onClickRow:function(rowIndex, rowData){	                
	                	seturl(rowData.puid);
			    	}
	            });
			});
		</script>
	</body>
</html>