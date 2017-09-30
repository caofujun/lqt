<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>送检项目</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height:30;overflow:hidden;">
		<div class="m_search datagrid-toolbar">
			<input type="text" class="auto-tip" style="width:150px" data-tip="项目名称" id="searchString"/>		   
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="yj003Synonym.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="yj003Synonym.match()"><i class="icon iconfont">&#xe688;</i><span>自动匹配</span></a>
			</div>
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="yj003Synonym.add('新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>

		</div>
	</div>
	<div data-options="region:'center',collapsed:false,border:false" style="border-right-width: 1px;">
		<div id="yj003SynonymPanel"></div>
	</div>
	<div data-options="region:'east',border:false" style="width:350px;">
		 <iframe id="yj003SynonymDetailIframe" src="${webroot}/yj003Synonym/f_view/list.shtml" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe> 
	</div>
	</div>
		<script type="text/javascript">
			var yj003Synonym = {
				panel : 'yj003SynonymPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+yj003Synonym.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			        $("#yj003SynonymDetailIframe").attr("src","${webroot}/yj003Synonym/f_view/list.shtml?searchString="+yj003Synonym.getSearch());
			    },
			  //新增
			    add:function(title) {
				        Comm.dialog({
				        	url:"${webroot}/yj003Synonym/f_view/toedit.shtml?",
				            title: title,
				            width:450,
				            height:230
				        });
			    },	
			  //自动匹配
			    match : function (){
			    	var msg="关联提醒将通过名称进行精准名称关联匹配";
			    	$.messager.confirm("提示", msg, function (r) {
			    		if(r){
			    		//var countQueryMatched;
			    		//var countQueryNotMatched;
			    		//自动匹配，查询匹配的个数
			    		$.ajax({
		                    url: '${webroot}/yj003Synonym/f_json/match.shtml',
		                    type: 'post',
		                    dataType: 'json',
		                    success : function(json) {
		                    //var dataObj=eval("("+json+")"); 
		                    var countQueryMatched=json.data.YPP;
		                    var countQueryNotMatched=json.data.WPP;
		                    var countQueryNotMatch = Number(countQueryNotMatched)-Number(countQueryMatched);
		                    $.messager.alert('提示','已完成'+countQueryMatched+'条项目匹配，余'+countQueryNotMatch+'条未匹配'); 
							}
		        		});	
			    		}
			        });
			    	
			    },
			    //编辑
			    edit:function(title) {
			    	//获取选中行值
			        var curRow = $('#'+yj003Synonym.panel).datagrid('getSelected');
			        if (curRow) {
			        	Comm.dialog({
				        	url:"${webroot}/yj003Synonym/f_view/toedit.shtml?id=" + curRow.id,
				            title: title,
				            width:450,
				            height:230
				        });
			        }
			    },
			    getSearch:function(){
			    	var searchString=encodeURIComponent(encodeURIComponent($('#searchString').val()));
			    	return searchString;
			    }
			};
			$(document).ready(function () {
				autoTip.clear();
				$('#'+yj003Synonym.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/yj003Synonym/f_json/pageQuery.shtml',
	                queryParams: {
		            	'searchString': $('#searchString').val()
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[                	
		                [
			             	{field:'synonyms',title:'项目名称',sortable:true,width:40},
		                    {field:'compareType',title:'匹配规则',sortable:true,width:40},
		                    {field:'standardId',title:'标准',sortable:true,width:40},
		                    {field:'compareUserId',title:'匹配者',sortable:true,width:40},
		                    {field:'compareTime',title:'匹配时间',sortable:true,width:40},
		                    {field:'_operate',title:'操作',width:20,
								formatter:function(value,r){
										return ['<a href="javascript:yj003Synonym.edit(\'修改\');" class="ico_editor" title="修改"></a>',									 
										'<a href="javascript:yj003Synonym.del();" class="ico_del" title="删除"></a>'].join('');								
								}
							}
		                ]
	                ],
	                pagination:true,
	    	        rownumbers:true,
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>