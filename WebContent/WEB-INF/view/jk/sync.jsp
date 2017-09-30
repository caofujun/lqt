<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>数据同步错误日志</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/zeroclipboard-2.2.0/ZeroClipboard.js"></script>
	<style>
		.easyui-combobox{width:75px;}
		.table_cx .easyui-combobox{width:150px;}
	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="border-left-width: 1px;"></div>
		<div id="syncPanel"></div>
	</div>	
<script type="text/javascript">
	var sync = {
		panel : 'syncPanel',
		//手工执行同步
	    stopSyncJkDataByTable : function(table){
			$.ajax({
				url : '${webroot}/jk/f_json/stopSyncJkDataByTable.shtml?table='+table,
				type:"POST",
				success:function(data){
				    //要执行的代码
					sync.currentSyncJkDataInfo();
				}
			});
	    },
	    progressFormatter : function (value,rowData,rowIndex){  
	        var htmlstr = '<div class="progressbar-text" style="width:198px;">'+value+'</div><div class="progressbar-value" style="width:'+value+';"> </div>';  
	        return htmlstr;  
	    },
		//手工执行同步
	    currentSyncJkDataInfo : function(){
			$('#'+sync.panel).datagrid({
	            fit: true,
	            nowrap: true,
	            autoRowHeight: false,
	            striped: true,
	            fitColumns: true,
	            collapsible:true,
	            url:'${webroot}/jk/f_json/currentSyncJkDataInfo.shtml?tables=${param.tables}',   
	            remoteSort: false,
	            singleSelect: true,
	            border:false,
	            columns:[
	            	[
	                    {field:'tableName',title:'业务名称',sortable:true,width:200},
	                    {field:'total',title:'同步任务总数',sortable:true,width:80},
	                    {field:'completed',title:'成功同步任务数',sortable:true,width:80},
	                    //{field:'_syncStatus',title:'同步进度',formatter:progressFormatter},
	                    {field:'syncStatus',title:'状态',sortable:true,width:50,
							formatter:function(value,r){
								if(r.stop == '1'){
									return '已终止';
								}else if(r.syncStatus == '0'){
									return '同步中';
								}else {
									return '已完成';
								}
							}
						},
	                    {field:'_operate',title:'操作',width:50,
							formatter:function(value,r){
								if(r.syncStatus == '0' && r.stop != '1'){
									return ['<a href="javascript:sync.stopSyncJkDataByTable(\'',r.table,'\');" class="ico_stop" title="停止"></a>'].join('');
								}else{
									return [''].join('');
								}
							}
						}
	                ]
	            ],
	            pagination:false,
	            rownumbers:false,
	            toolbar:'#tb'
	        });
	    }
	};

	$(document).ready(function () {
		$.ajax({
			url : '${webroot}/jk/f_json/syncJkData.shtml?tables=${param.tables}',
			type:"POST",
			success:function(data){
			    //要执行的代码
				sync.currentSyncJkDataInfo();
				setInterval(function(){
				    //要执行的代码
					sync.currentSyncJkDataInfo();
				},5000);
			}
		});
	});
</script>
</body>
</html>
