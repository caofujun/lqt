<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>计算预警日志</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/zeroclipboard-2.2.0/ZeroClipboard.js"></script>
	<style>
		.easyui-combobox{width:75px;}
		.table_cx .easyui-combobox{width:150px;}
	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="border-left-width: 1px;"></div>
		<div id="judgePanel"></div>
	</div>	
<script type="text/javascript">
	var yj = {
		panel : 'judgePanel',
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
	    stop:function(judgeCode) {
	    	$.messager.confirm('提示', '确认停止?', function (r) {
	        	if (r) {
	            	$.ajax({
	                        url: '${webroot}/taskJob/f_json/updateStatus.shtml',
	                        type: 'post',
	                        data: { judgeCode: judgeCode,status:50 },
	                        dataType: 'json',
	                        success : function(json) {
								if(json.result==='success') {
									//taskJob.query();
	                                parent.$.messager.show({ title: '提示', msg: '停止成功！' });
						    	} else if(json.result === 'error') {
						    		parent.$.messager.show({ title: '提示', msg: '系统异常！' });
						    	} else {
						    		parent.$.messager.show({ title: '提示', msg: json.msg });
						    	}
							}
	            	});
	        	}
	    	});
	    },
		//手工执行同步
	    currentSyncJkDataInfo : function(id){
			$('#'+yj.panel).datagrid({
	            fit: true,
	            nowrap: true,
	            autoRowHeight: false,
	            striped: true,
	            fitColumns: true,
	            collapsible:true,
	            url:'${webroot}/zg/f_json/judgeLogList.shtml?id='+id,   
	            remoteSort: false,
	            singleSelect: true,
	            border:false,
	            columns:[
	            	[
	                    {field:'judgeName',title:'业务名称',sortable:true,width:100},
	                    {field:'startTime',title:'开始时间',sortable:true,width:80},
	                    {field:'totalCount',title:'计算总数',sortable:true,width:80},
	                    {field:'successCount',title:'成功数',sortable:true,width:80},
	                    {field:'failCount',title:'失败数',sortable:true,width:80},
	                    {field:'statusName',title:'状态',sortable:true,width:50},
	                    {field:'endTime',title:'结束时间',sortable:true,width:80},
	                    {field:'_operate',title:'操作',width:50,
							formatter:function(value,r){
								if(r.status == '0'){
									return ['<a href="javascript:yj.stop(\'',r.judgeCode,'\');" class="ico_stop" title="停止"></a>'].join('');
								}else{
									return [''].join('');
								}
							}
						}
	                ]
	            ],
	            rownumbers:false
	        });
	    }
	};

	$(document).ready(function () {
		$.ajax({
			url : '${webroot}/zg/f_json/ajaxJudgeYj.shtml?tables=${param.judgeType}',
			type:"POST",
			dataType: 'json',
			success:function(json){
			    //要执行的代码
				yj.currentSyncJkDataInfo(json.data);
				 setInterval(function(){
				    //要执行的代码
					yj.currentSyncJkDataInfo(json.data);
				},5000); 
			}
		});
	});
</script>
</body>
</html>
