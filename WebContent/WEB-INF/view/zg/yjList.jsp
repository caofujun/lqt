<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>中间库业务同步</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/zeroclipboard-2.2.0/ZeroClipboard.js"></script>
	<style>
		.easyui-combobox{width:75px;}
		.table_cx .easyui-combobox{width:150px;}
	</style>
</head>
<body id="layout" class="easyui-layout">
	<div data-options="region:'west',split:true,border:false,title:'选择需要计算的任务'" style="width:300px;border-right-width: 1px;" class="easyui-layout" id="layout">
	
		<div id="judgePanel"></div>
		
	</div>
	<div data-options="region:'center',border:false" style="border-left-width: 1px;">
		<div id="judgeLogPanel2"></div>
		
<!-- 		<iframe id="sysDictIframe" name="sysDictIframe" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe> -->
	</div>

<script type="text/javascript">
	function seturl(code){
		var url="${webroot}/zg/f_view/judgeLogIndex.shtml?judgeCode="+code;
	    $("#sysDictIframe").attr("src",url);
	}
	var yjContent = {
			panel : 'judgeLogPanel2',
			openLog : function(id){
				 Comm.dialogGlobal({
			        	url:"${webroot}/sysLog/f_view/index.shtml?id="+id,
			            title: "查看日志",
			            width:800,
			            height:600,
			            type:'iframe',
			            parent:this
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
										if(json.result=='success') {
//	 										console.log(parent.yj.timer1);
// 											var parentObject = parent.Comm.getObjectCache();
// 											console.log(parentObject.timer1);
											clearInterval(yj.timer1);
			                                parent.$.messager.show({ title: '提示', msg: '停止成功！' });
//	 		                                location.reload();
//	 		                                window.reload();
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
			query : function(code){
				$('#'+yjContent.panel).datagrid({
		            fit: true,
		            nowrap: true,
		            autoRowHeight: false,
		            striped: true,
		            fitColumns: true,
		            collapsible:true,
		            url:'${webroot}/zg/f_json/judgeLogPage.shtml?judgeCode='+code,   
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
		                    {field:'endTime',title:'结束时间',sortable:true,width:80},
		                    {field:'statusName',title:'状态',sortable:true,width:50},
		                    {field:'_operate',title:'操作',sortable:true,width:50,
		                    	formatter:function(value,r){
									if(r.status==2){
										return ['<a href="javascript:yjContent.openLog(\'',$.trim(r.id),'\',\'查看日志\');" class="ico_view" title="查看日志"></a>'].join('');
									}else if(r.status==0){
										return ['<a href="javascript:yjContent.stop(\'',r.judgeCode,'\');" class="ico_stop" title="停止"></a>'].join('');
									}
									
		                    	}
		                    }
		                ]
		            ],
		            pagination:true,
		            rownumbers:false
		        });
		    }
		};
	var yj = {
		panel : 'judgePanel',
		code : '',
		timer1 : '',
		//手工执行任务
	    judgeData : function(judgeType){
	    	$.messager.confirm('提示', '确认计算?', function (r) {
	        	if (r) {
// 			        Comm.dialogGlobal({
// 			        	url:"${webroot}/zg/f_view/judgeYj.shtml?judgeType="+judgeType,
// 			            title: "计算",
// 			            width:600,
// 			            height:600,
// 			            type:'iframe',
// 			            parent:this
// 			        });
	        		$.ajax({
	        			url : '${webroot}/zg/f_json/ajaxJudgeYj.shtml?tables='+judgeType,
	        			type:"POST",
	        			dataType: 'json',
	        			success:function(json){
	        			    //要执行的代码
// 	        			    seturl(yj.code);
	        			    yjContent.query(yj.code);
	        			    yj.timer1 = setInterval(function(){
	        				    //要执行的代码
// 	        				    seturl(yj.code);
	        				    yjContent.query(yj.code);
	        				},5000); 
	        			}
	        		});
	        	}
	    	});
	    },
	    judgeDataTime : function(){
	    	 Comm.dialogGlobal({
		        	url:"${webroot}/gm004Jcmx/f_view/judgeJcmxList.shtml",
		            title: "根据时间执行三管和在院人数",
		            width:480,
		            height:220,
		            type:"iframe",
		            parent:this
		        });
	    }
	};

	$(document).ready(function () { 
		$('#'+yj.panel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            fitColumns: true,
            collapsible:true,
            url:'${webroot}/zg/f_json/judgeType.shtml',   
            remoteSort: false,
            singleSelect: true,
            border:false,
            columns:[
            	[
                    {field:'value',title:'业务类型',sortable:true,width:100},
                    {field:'_operate',title:'计算',width:50,
						formatter:function(value,r){
							if(r.key==8){
								return ['<a href="javascript:yj.judgeData(\'',$.trim(r.key),'\',\'计算\');" class="ico_run" title="计算"></a><a href="javascript:yj.judgeDataTime(\'',$.trim(r.key),'\',\'根据时间计算\');" class="ico_RegularRun" title="根据时间计算"></a>'].join('');
							}else{
								return ['<a href="javascript:yj.judgeData(\'',$.trim(r.key),'\',\'计算\');" class="ico_run" title="计算"></a>'].join('');
							}					
						}
					}
                ]
            ],
            rownumbers:true,
            onLoadSuccess: function() {
            	$('#'+yj.panel).datagrid('selectRow', 0);
            	var curRow = $('#'+yj.panel).datagrid('getSelected');
            	yj.code=curRow.key;
            	yjContent.query(curRow.key);
//             	seturl(curRow.key);
            },
            onClickRow:function(rowIndex, rowData){	  
            	yj.code=rowData.key;
            	yjContent.query(rowData.key);
//             	seturl(rowData.key);
	    	}
        });
	});
</script>
</body>
</html>	