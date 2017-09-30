<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>送检项目</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body >
		<div id="yj003SynonymListPanel"></div>
		<script type="text/javascript">
			var yj003SynonymList = {
				panel : 'yj003SynonymListPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+yj003SynonymList.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(title) {
			    	//获取选中行值
			        var curRow = $('#'+yj003SynonymList.panel).datagrid('getSelected');
			        if (curRow) {
			        	parent.Comm.dialog({
				        	url:"${webroot}/yj003Synonym/f_view/toedit.shtml?lisBytid=" + curRow.lisBytid,
				            title: title,
				            width:450,
				            height:230
				        });
			        }
			    },
			};
			$(document).ready(function () {
				autoTip.clear();
				$('#'+yj003SynonymList.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/yj003Synonym/f_json/pageQueryList.shtml',
	                queryParams: {
		            	'searchString': '${searchString}'
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
		                [
		                    {field:'synonyms',title:'项目名称',sortable:true,width:40}	,
		                    {field:'_operate',title:'操作',width:20,
								formatter:function(value,r){
										return ['<a href="javascript:yj003SynonymList.edit(\'修改\');" class="ico_editor" title="修改"></a>',									 
										'<a href="javascript:yj003SynonymList.del();" class="ico_del" title="删除"></a>'].join('');
								}
							}
		                ]
	                ],
	                pagination:true,
	    	        rownumbers:true
	            });
			});
		</script>
	</body>
</html>