<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>微生物匹配</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body >
		<div id="xn013LisbytListPanel"></div>
		<script type="text/javascript">
			var xn013LisbytList = {
				panel : 'xn013LisbytListPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+xn013LisbytList.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(title) {
			    	//获取选中行值
			        var curRow = $('#'+xn013LisbytList.panel).datagrid('getSelected');
			        var lisBytid = encodeURIComponent(encodeURIComponent(curRow.lisBytid));
			        if (curRow) {
			        	parent.Comm.dialog({
				        	url:"${webroot}/xn013Lisbyt/f_view/toedit.shtml?lisBytid=" + lisBytid,
				            title: title,
				            width:450,
				            height:230
				        });
			        }
			    },
			};
			$(document).ready(function () {
				autoTip.clear();
				$('#'+xn013LisbytList.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/xn013Lisbyt/f_json/pageQueryList.shtml',
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
										return ['<a href="javascript:xn013LisbytList.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');
									}else{
										return ['<a href="javascript:xn013LisbytList.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');
									}
								}
							}
		                    
		                ],
		                [
		                 	{field:'lisBytid',title:'病原体编号',sortable:true,width:40},
		                    {field:'lisBytname',title:'病原体名称',sortable:true,width:40}	                
		                ]
	                ],
	                pagination:true,
	    	        rownumbers:true
	            });
			});
		</script>
	</body>
</html>