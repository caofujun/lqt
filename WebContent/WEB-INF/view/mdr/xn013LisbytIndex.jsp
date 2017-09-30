<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>微生物匹配</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height:30;overflow:hidden;">
		<div class="m_search datagrid-toolbar">
			<input type="text" class="auto-tip" style="width:150px" data-tip="病原体编码/名称" id="searchString"/>		 
			<div class="n_btn_blue">
				<a href="javascript:;"  onclick="xn013Lisbyt.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>
			<div class="n_btn_blue" >
				<a href="javascript:;"  onclick="xn013Lisbyt.match()" id="messager"><span>自动匹配</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;"  onclick="xn013Lisbyt.add('新增')" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
	</div>
	<div data-options="region:'center',collapsed:false,border:false" style="border-right-width: 1px;">
		<div id="xn013LisbytPanel"></div>
	</div>
	<div data-options="region:'east',border:false,title:'未匹配病原体'" style="width:350px;">
		 <iframe id="xn013LisbytDetailIframe" src="${webroot}/xn013Lisbyt/f_view/list.shtml" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe> 
	</div>
	</div>
		<script type="text/javascript">
			var xn013Lisbyt = {
				panel : 'xn013LisbytPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+xn013Lisbyt.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			        $("#xn013LisbytDetailIframe").attr("src","${webroot}/xn013Lisbyt/f_view/list.shtml?searchString="+xn013Lisbyt.getSearch());
			    },
			    //自动匹配
			    match : function (){
			    	autoTip.clear();
			    	var msg="关联提醒将通过名称进行精准名称关联匹配";
			    	$.messager.confirm("提示", msg, function (r) {
			    		if(r){
			    		//var countQueryMatched;
			    		//var countQueryNotMatched;
			    		//自动匹配，查询匹配的个数
			    		$.ajax({
		                    url: '${webroot}/xn013Lisbyt/f_json/match.shtml',
		                    type: 'post',
		                    dataType: 'json',
		                    success : function(json) {
		                    var countQueryMatched=json.data.YPP;
		                    var countQueryNotMatched=json.data.WPP;
		                    var countQueryNotMatch = Number(countQueryNotMatched)-Number(countQueryMatched);
		                    $.messager.alert('提示','已完成'+countQueryMatched+'条项目匹配，余'+"<font style='color:red;font-size:15px;'>" +countQueryNotMatch+ "</font>"+'条未匹配');
			    	  		$("#xn013LisbytDetailIframe").attr("src","${webroot}/xn013Lisbyt/f_view/list.shtml?searchString="+xn013Lisbyt.getSearch());
			    	  		xn013Lisbyt.query();
		                    }
		        		});	
			    		}
			        });
			    },
			  //新增
			    add:function(title) {
				        Comm.dialog({
				        	url:"${webroot}/xn013Lisbyt/f_view/toedit.shtml?",
				            title: title,
				            width:450,
				            height:230
				        });
			    },			 
			    //编辑
			    edit:function(title) {
			    	//获取选中行值
			        var curRow = $('#'+xn013Lisbyt.panel).datagrid('getSelected');
			        var lisBytid = encodeURIComponent(encodeURIComponent(curRow.lisBytid));
			        if (curRow) {
			        	Comm.dialog({
				        	url:"${webroot}/xn013Lisbyt/f_view/toedit.shtml?lisBytid=" + lisBytid,
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
				$('#'+xn013Lisbyt.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/xn013Lisbyt/f_json/pageQuery.shtml',
	                queryParams: {
		            	'searchString': $('#searchString').val()
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
							{title:'检验系统',colspan:2},                   
		                    {field:'createrName',title:'匹配规则',sortable:true,width:50,rowspan:2},
		                    {field:'flowCreatetime',title:'拼音码',sortable:true,width:40,rowspan:2},
		                    {title:'标准',colspan:2},
		                    {field:'_operate',title:'操作',width:20,rowspan:2,
								formatter:function(value,r){
									if(r.status=='1'){
										return ['<a href="javascript:xn013Lisbyt.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');
									}else{
										return ['<a href="javascript:xn013Lisbyt.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');
									}
								}
							}
		                ],
		                [
		                 	{field:'lisBytid',title:'病原体编号',sortable:true,width:40},
		                    {field:'lisBytname',title:'病原体名称',sortable:true,width:40},
		                    {field:'counterBytid',title:'标本编号',sortable:true,width:40},
		                    {field:'pathogenName',title:'标本名称',sortable:true,width:40}
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