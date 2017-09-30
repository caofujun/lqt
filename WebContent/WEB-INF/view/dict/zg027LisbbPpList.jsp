<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>标本匹配</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body >
		<div id="zg027LisbbPpListPanel"></div>
		<script type="text/javascript">
			var zg027LisbbPpList = {
				panel : 'zg027LisbbPpListPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+zg027LisbbPpList.panel).datagrid({
			            queryParams: {
			            	'searchString': '${searchString}'
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(title) {
			    	//获取选中行值
			        var curRow = $('#'+zg027LisbbPpList.panel).datagrid('getSelected');
			        if (curRow) {
			        	parent.Comm.dialog({
				        	url:"${webroot}/zg027LisbbPp/f_view/toedit.shtml?bbppId=" + encodeURIComponent(encodeURIComponent( curRow.bbppId)),
				            title: title,
				            width:450,
				            height:230
				        });
			        }
			    },
			};
			$(document).ready(function () {
				autoTip.clear();
				$('#'+zg027LisbbPpList.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/zg027LisbbPp/f_json/pageQueryList.shtml',
	                queryParams: {
		            	'searchString': '${searchString}'
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
							{title:'检验系统',colspan:2},
							 {field:'_operate',title:'操作',width:20,rowspan:2,
								formatter:function(value,r){
									if(r.status=='1'){
										return ['<a href="javascript:zg027LisbbPpList.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');
									}else{
										return ['<a href="javascript:zg027LisbbPpList.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');
									}
								}
							}
		                    
		                ],
		                [
		                 	{field:'itemCode',title:'标本编号',sortable:true,width:40},
		                    {field:'itemName',title:'标本名称',sortable:true,width:40}	                
		                ]
	                ],
	                pagination:true,
	    	        rownumbers:true
	            });
			});
		</script>
	</body>
</html>