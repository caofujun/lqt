<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>药敏药物匹配</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body >
		<div id="xn014LiskjywListPanel"></div>
		<script type="text/javascript">
			var xn014LiskjywList = {
				panel : 'xn014LiskjywListPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+xn014LiskjywList.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(title) {
			    	//获取选中行值
			        var curRow = $('#'+xn014LiskjywList.panel).datagrid('getSelected');
			        var drugid = encodeURIComponent(encodeURIComponent(curRow.drugid));
			        if (curRow) {
			        	parent.Comm.dialog({
				        	url:"${webroot}/xn014Liskjyw/f_view/toedit.shtml?drugid=" + drugid,
				            title: title,
				            width:450,
				            height:230
				        });
			        }
			    },
			};
			$(document).ready(function () {
				autoTip.clear();
				$('#'+xn014LiskjywList.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/xn014Liskjyw/f_json/pageQueryList.shtml',
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
										return ['<a href="javascript:xn014LiskjywList.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');
									}else{
										return ['<a href="javascript:xn014LiskjywList.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');
									}
								}
							}
		                    
		                ],
		                [
		                 	{field:'drugid',title:'药敏药物编号',sortable:true,width:40},
		                    {field:'drugname',title:'药敏药物名称',sortable:true,width:40}	                
		                ]
	                ],
	                pagination:true,
	    	        rownumbers:true
	            });
			});
		</script>
	</body>
</html>