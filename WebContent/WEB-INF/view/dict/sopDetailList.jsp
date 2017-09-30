<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>SOP字典管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body class="easyui-layout">
	<div data-options="region:'east',split:false,collapsed:false,border:false" style="width:350px;">
	<iframe id="sopDetailIframe" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
	</div>
	<div id="id_sop" data-options="region:'center',border:false" style="border-right-width: 1px;height: 100%;">
		<div id="sopPanel"></div>
		<div id="tb" class="m_search">			
			<input type="text" class="auto-tip" data-tip="SOP名称/SOP编码" id="searchString"/>		    
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="sop.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>
		</div>
	</div>
		<script type="text/javascript">
			function close(){
				parent.Comm.dialogClose('${param.dialogId}');
			}
			function seturl(id){
				var url="${webroot}/sop/f_view/toDetail.shtml?id="+id;
			    $("#sopDetailIframe").attr("src",url);
			}
			var sop = {
				panel : 'sopPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+sop.panel).datagrid({
			            queryParams: {
			                'pFileId': $('#pFileId').val(),
			                'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    }		  
			};
			$(document).ready(function () { 
				$('#'+sop.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: true,
	                striped: true,
	                fitColumns: false,
	                collapsible:true,
	                url:'${webroot}/sop/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[ 
		                    {field:'fileId',title:'编码',sortable:true,width:40},
		                    {field:'fileTitle',title:'名称',sortable:true,width:290}                  
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb',
	                onBeforeLoad: function() {
	                	var pager = $('#' + sop.panel).datagrid('getPager');
	    	        	pager.pagination({
	    	        		displayMsg:''
	            		});
	    	        	$('#tb').next().height(parseInt($('#id_sop').children(":first").children(":first").height()) - 73);
	                },
	                onLoadSuccess: function() {
	                	$('#'+sop.panel).datagrid('selectRow', 0);
	                	var curRow = $('#'+sop.panel).datagrid('getSelected');
	                	seturl(curRow.fileId);
	                },
	                onClickRow:function(rowIndex, rowData){	                
	                	seturl(rowData.fileId);
			    	}
	            });
			});
		</script>
	</body>
</html>
