<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>模板管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body class="easyui-layout">
	<div data-options="region:'east',split:false,border:false" style="width:380px;border-left-width: 1px;">
	<iframe id="msgTemplateDetailIframe" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
	</div>
	<div id="id_msgTemplate" data-options="region:'center',border:false">
		<div id="msgTemplatePanel"></div>
		<div id="tb" class="m_search">			
			<input type="text" class="auto-tip" data-tip="模板名称" id="searchString"/>		    
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="msgTemplate.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>
		</div>
	</div>
		<script type="text/javascript">
			function close(){
				parent.Comm.dialogClose('${param.dialogId}');
			}
			function seturl(id){
				var url="${webroot}/msgTemplate/f_view/toDetail.shtml?id="+id;
			    $("#msgTemplateDetailIframe").attr("src",url);
			}
			var msgTemplate = {
				panel : 'msgTemplatePanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+msgTemplate.panel).datagrid({
			            queryParams: {
			                'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该模板?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/msgTemplate/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											msgTemplate.query();
			                                //$.messager.show({ title: '提示', msg: '删除成功！' });
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
				$('#'+msgTemplate.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                collapsible:true,
	                url:'${webroot}/msgTemplate/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[ 
		                    {field:'title',title:'名称',sortable:true,width:300},
		                    {field:'_operate',title:'操作',width:75,
								formatter:function(value,r){
									return ['<a href="javascript:msgTemplate.del(\'',$.trim(r.id),'\');" class="ico_del" title="删除"></a>'].join('');
								}
							}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb',
	                onBeforeLoad: function() {
	                	var pager = $('#' + msgTemplate.panel).datagrid('getPager');
	    	        	pager.pagination({
	    	        		displayMsg:''
	            		});
	    	        	$('#tb').next().height(parseInt($('#id_msgTemplate').children(":first").children(":first").height()) - 73);
	                },
	                onLoadSuccess: function() {
	                	$('#'+msgTemplate.panel).datagrid('selectRow', 0);
	                	var curRow = $('#'+msgTemplate.panel).datagrid('getSelected');
	                	seturl(curRow.id);
	                },
	                onClickRow:function(rowIndex, rowData){	                
	                	seturl(rowData.id);
			    	}
	            });
			});
		</script>
	</body>
</html>
