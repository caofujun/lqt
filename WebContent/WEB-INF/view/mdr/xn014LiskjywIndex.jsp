<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>药敏药物匹配</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height:30;overflow:hidden;">
		<div class="m_search datagrid-toolbar">
			<input type="text" class="auto-tip" style="width:150px" data-tip="药敏药物编码/名称" id="searchString2"/>		    
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="xn014Liskjyw.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>	
			<div class="n_btn_blue" >
				<a href="javascript:;"  onclick="xn014Liskjyw.match()" id="messager"><span>自动匹配</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="xn014Liskjyw.add('新增')" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
	</div>
	<div data-options="region:'center',collapsed:false,border:false" style="border-right-width: 1px;">
		<div id="xn014LiskjywPanel"></div>
	</div>
	<div data-options="region:'east',border:false,title:'未匹配药敏药物'" style="width:350px;">
		 <iframe id="xn014LiskjywDetailIframe" src="${webroot}/xn014Liskjyw/f_view/list.shtml" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe> 
	</div>
	</div>
		<script type="text/javascript">
			var xn014Liskjyw = {
				panel : 'xn014LiskjywPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+xn014Liskjyw.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString2').val()
			            },
			            pageNumber: 1
			        });
			        $("#xn014LiskjywDetailIframe").attr("src","${webroot}/xn014Liskjyw/f_view/list.shtml?searchString="+xn014Liskjyw.getSearch());
			    },
			    match : function (){
			    	var msg="关联提醒将通过名称进行精准名称关联匹配";
			    	$.messager.confirm("提示", msg, function (r) {
			    		if(r){
			    		//var countQueryMatched;
			    		//var countQueryNotMatched;
			    		//自动匹配，查询匹配的个数
			    		$.ajax({
		                    url: '${webroot}/xn014Liskjyw/f_json/match.shtml',
		                    type: 'post',
		                    dataType: 'json',
		                    success : function(json) {
		                    //var dataObj=eval("("+json+")"); 
		                    //alert("122");
		                    var countQueryMatched=json.data.YPP;
		                    var countQueryNotMatched=json.data.WPP;
		                    var countQueryNotMatch = Number(countQueryNotMatched)-Number(countQueryMatched);
		                    $.messager.alert('提示','已完成'+countQueryMatched+'条项目匹配，余'+"<font style='color:red;font-size:15px;'>" +countQueryNotMatch+ "</font>"+'条未匹配');
		                    $("#xn014LiskjywDetailIframe").attr("src","${webroot}/xn014Liskjyw/f_view/list.shtml?searchString="+xn014Liskjyw.getSearch());
		                    xn014Liskjyw.query();
		                    }
		        		});	
			    		}
			        });
			    	
			    },
			  //新增
			    add:function(title) {
				        Comm.dialog({
				        	url:"${webroot}/xn014Liskjyw/f_view/toedit.shtml?",
				            title: title,
				            width:450,
				            height:230
				        });
			    },			 
			    //编辑
			    edit:function(title) {
			    	//获取选中行值
			        var curRow = $('#'+xn014Liskjyw.panel).datagrid('getSelected');
			        var drugid = encodeURIComponent(encodeURIComponent(curRow.drugid));
			        if (curRow) {
			        	Comm.dialog({
				        	url:"${webroot}/xn014Liskjyw/f_view/toedit.shtml?drugid=" + drugid,
				            title: title,
				            width:450,
				            height:230
				        });
			        }
			    },
			    getSearch:function(){
			    	var searchString=encodeURIComponent(encodeURIComponent($('#searchString2').val()));
			    	return searchString;
			    }			    
			};
			$(document).ready(function () {
				autoTip.clear();
				$('#'+xn014Liskjyw.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/xn014Liskjyw/f_json/pageQuery.shtml',
	                queryParams: {
		            	'searchString': $('#searchString2').val()
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
										return ['<a href="javascript:xn014Liskjyw.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');
									}else{
										return ['<a href="javascript:xn014Liskjyw.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');
									}
								}
							}
		                ],
		                [
		                 	{field:'drugid',title:'药敏药物编号',sortable:true,width:40},
		                    {field:'drugname',title:'药敏药物名称',sortable:true,width:40},
		                    {field:'counterDrugid',title:'药敏药物编号',sortable:true,width:40},
		                    {field:'counterDrugName',title:'药敏药物名称',sortable:true,width:40}
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