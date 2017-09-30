<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>审核信息管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<style>
	.select{
    	color: #FF5D25 !important;
		font-weight: bold;
    	text-decoration: underline !important;
	}
</style>
</head>
<body>
	<div id="AuditInfoPanel" style="width:100%;"></div>
	<div id="tb" class="m_search search_list">
		<input type="text" name="searchString" id="searchString" style="width:120px" class="auto-tip" data-tip="审核编号/生产、经营企业编号"/>
		<div class="n_btn_blue">
			<a href="javascript:;" id="searchButt" onclick="AI.query();" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>		
		<div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="AI.add();"><i class="icon iconfont">&#xe602;</i><span>新建</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="AI.edit();"><i class="icon iconfont">&#xe601;</i><span>修改</span></a>
			</div>
			<span style="border-left: 1px solid #dddddd;margin:5px; 2px;"></span>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="AI.setEnable();"><i class="icon iconfont">&#xe63b;</i><span>启用</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="AI.setDisabled();"><i class="icon iconfont">&#xe619;</i><span>停用</span></a>
			</div>
			<span style="border-left: 1px solid #dddddd;margin:5px; 2px;"></span>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="AI.exportXls();"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
			</div>
		</div>
	</div>

	<script>	
	
	var AI = {
			panel : 'AuditInfoPanel',
			//查询
			query : function() {
				autoTip.clear();
				$('#'+AI.panel).datagrid({
					 url:'${webroot}/aim/f_json/query.shtml',
			         queryParams: {
			            'searchString': $('#searchString').val()
			         },
				});
			},
			add : function(){
				Comm.dialog({
			    	url:"${webroot}/aim/f_view/toAdd.shtml",
			        title: "新增",
			        width:800,
			        height:400,
			        type:"iframe",
			        parent:this
			    });
			},
			edit : function(){
				var st = $('#'+AI.panel).datagrid("getSelected");
				if(st){
					Comm.dialog({
				    	url:"${webroot}/aim/f_view/toAdd.shtml?shNo="+st.shNo,
				        title: "新增",
				        width:800,
				        height:400,
				        type:"iframe",
				        parent:this
				    });
				}else{
					$.messager.show({title : '提示',msg : "请选中要修改的记录！"});
				}
			},
			setDisabled : function(){
				var st = $('#'+AI.panel).datagrid("getSelected");
				if(st){
					AI.setStatus(st.shNo, "0");
				}else{
					$.messager.show({title : '提示',msg : "请选中要停用的记录！"});
				}
			},
			setEnable : function(){
				var st = $('#'+AI.panel).datagrid("getSelected");
				if(st){
					AI.setStatus(st.shNo, "1");
				}else{
					$.messager.show({title : '提示',msg : "请选中要启用的记录！"});
				}
			},
			setStatus : function(shNo,useFlag){
				$.ajax({
					url:"${webroot}/aim/f_json/setStatus.shtml",
					data:{
						"shNo":shNo,
						"useFlag":useFlag
					},
					success:function(data){
						data = eval("("+data+")");
						if (data.result == 'success') {
							$.messager.show({title : '提示',msg : "修改成功！"});
							//刷新
							AI.query();
						} else {
							$.messager.show({title : '提示',msg : data.msg});
						}
					}
				})
			},
			exportXls : function(){
				autoTip.clear();
				var form=$("<form>");//定义一个form表单
				form.attr("style","display:none");
				form.attr("target","");
				form.attr("method","post");
				form.attr("action","${webroot}/aim/f_json/exportShdjList.shtml");
				var input1=$("<input>");
				input1.attr("type","hidden");
				input1.attr("name","searchString");
				input1.attr("value",$("#searchString").val());
				$("body").append(form);//将表单放置在web中
				form.append(input1);
				form.submit();//表单提交 
			}
	}
	
	$(document).ready(function () {
		autoTip.clear();
		$('#'+AI.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: false,
	        striped: true,
	        fitColumns: true,
	        collapsible:true,
	        border:true,
	        url:'${webroot}/aim/f_json/query.shtml',
	        queryParams: {
	            'searchString': $('#searchString').val()
	        },
	        remoteSort: false,
	        singleSelect: true,
	        rownumbers:true,
	        columns:[
	        	[
	        	 	{field:'useFlag',title:'使用状态',sortable:true,formatter:function(value,row){
	                	if(value==1){
	                		return "启用";
	                	}else{
	                		return "停用";
	                	}
	                }},
	                {field:'shNo',title:'审核编号',sortable:true},
	                {field:'shType',title:'审核类型',sortable:true},
	                {field:'shName',title:'审核者',sortable:true},
	                {field:'shDate',title:'审核日期',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}},
	                {field:'shJg',title:'审核结果',sortable:true},
	                {field:'depCg',title:'采购部门',sortable:true},
	                {field:'cgRy',title:'采购人员',sortable:true},
	                {field:'scqyNo',title:'生产企业编号',sortable:true},
	                {field:'scqyName',title:'生产企业名称',hidden:true},
	                {field:'jyqyNo',title:'经营企业编号',sortable:true},
	                {field:'jyqyName',title:'经营企业名称',hidden:true},
	                {field:'scjyDate',title:'生产对经营授权有效期',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}},
	                {field:'jygrDate',title:'经营对个人授权有效期',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}},
	                {field:'scNd',title:'生产企业年度',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}},
	                {field:'jyJydate',title:'经营企业检验时间',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}}
	            ]
	        ],
	        toolbar:'#tb',
	        onLoadSuccess:function(data){
	        }
	    });
	});
	</script>
</body>
</html>