<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>企业信息管理</title>
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
	<div id="EnterpriseInfoPanel" style="width:100%;"></div>
	<div id="tb" class="m_search search_list">
		<input type="text" name="searchString" id="searchString" style="width:120px" class="auto-tip" data-tip="企业编号/企业名称/法人/电话（传真）"/>
		<div class="n_btn_blue">
			<a href="javascript:;" id="searchButt" onclick="EI.query();" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>		
		<div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="EI.add();"><i class="icon iconfont">&#xe602;</i><span>新建</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="EI.edit();"><i class="icon iconfont">&#xe601;</i><span>修改</span></a>
			</div>
			<span style="border-left: 1px solid #dddddd;margin:5px; 2px;"></span>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="EI.setEnable();"><i class="icon iconfont">&#xe63b;</i><span>启用</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="EI.setDisabled();"><i class="icon iconfont">&#xe619;</i><span>停用</span></a>
			</div>
			<span style="border-left: 1px solid #dddddd;margin:5px; 2px;"></span>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="EI.exportXls();"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
			</div>
		</div>
	</div>

	<script>	
	
	var EI = {
			panel : 'EnterpriseInfoPanel',
			//查询
			query : function() {
				autoTip.clear();
				$('#'+EI.panel).datagrid({
					 url:'${webroot}/mdmm/f_json/query.shtml',
			         queryParams: {
			            'searchString': $('#searchString').val()
			         },
				});
			},
			add : function(){
				Comm.dialog({
			    	url:"${webroot}/mdmm/f_view/toAdd.shtml",
			        title: "新增",
			        width:800,
			        height:440,
			        type:"iframe",
			        parent:this
			    });
			},
			edit : function(){
				var st = $('#'+EI.panel).datagrid("getSelected");
				if(st){
					Comm.dialog({
				    	url:"${webroot}/mdmm/f_view/toAdd.shtml?qyNo="+st.qyNo,
				        title: "新增",
				        width:800,
				        height:440,
				        type:"iframe",
				        parent:this
				    });
				}else{
					$.messager.show({title : '提示',msg : "请选中要修改的记录！"});
				}
			},
			setDisabled : function(){
				var st = $('#'+EI.panel).datagrid("getSelected");
				if(st){
					EI.setStatus(st.qyNo, "0");
				}else{
					$.messager.show({title : '提示',msg : "请选中要停用的记录！"});
				}
			},
			setEnable : function(){
				var st = $('#'+EI.panel).datagrid("getSelected");
				if(st){
					EI.setStatus(st.qyNo, "1");
				}else{
					$.messager.show({title : '提示',msg : "请选中要启用的记录！"});
				}
			},
			setStatus : function(qyNo,status){
				$.ajax({
					url:"${webroot}/mdmm/f_json/setStatus.shtml",
					data:{
						"qyNo":qyNo,
						"useFlag":status
					},
					success:function(data){
						data = eval("("+data+")");
						if (data.result == 'success') {
							$.messager.show({title : '提示',msg : "修改成功！"});
							//刷新
							EI.query();
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
				form.attr("action","${webroot}/mdmm/f_json/exportScqyList.shtml");
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
		$('#'+EI.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: false,
	        striped: true,
	        fitColumns: false,
	        collapsible:true,
	        border:true,
	        url:'${webroot}/mdmm/f_json/query.shtml',
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
						}else if(value==0){
							return "停用";
						}
					}},
	                {field:'qyNo',title:'企业编号',sortable:true},
	                {field:'qyType',title:'企业类型',sortable:true},
	                {field:'qyName',title:'企业名称',sortable:true},
	                {field:'address',title:'地址',sortable:true},
	                {field:'qyFr',title:'法人',sortable:true},
	                {field:'tel',title:'电话（传真）',sortable:true},
	                {field:'cdcBg',title:'CDC检验报告',sortable:true},
	                {field:'yyzz',title:'营业执照',sortable:true},
	                {field:'yyzzDate',title:'营业执照有效期',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}},
	                {field:'ylscXk',title:'医疗器械生产许可证',sortable:true},
	                {field:'ylscDate',title:'器械生产许可有效期',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}},
	                {field:'ylZc',title:'医疗器械注册证',sortable:true},
	                {field:'ylzcDate',title:'器械注册有效期',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}},
	                {field:'wsXk',title:'卫生许可证',sortable:true},
	                {field:'wsxkDate',title:'卫生许可证有效期',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}},
	                {field:'wsXkpj',title:'卫生许可批件',sortable:true},
	                {field:'wspjDate',title:'许可批件有效期',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}},
	                {field:'yljyXkz',title:'医疗器械经营许可证',sortable:true},
	                {field:'jyxkDate',title:'医疗器械经营有效期',sortable:true,formatter:function(value,row){return value?value.substring(0,10):"";}}
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