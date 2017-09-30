<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>计算预警日志</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',border:false" style="border-left-width: 1px;"></div>
		<div id="judgeLogPanel"></div>
	</div>
<script type="text/javascript">
	var yj = {
		panel : 'judgeLogPanel',
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
		query : function(){
			$('#'+yj.panel).datagrid({
	            fit: true,
	            nowrap: true,
	            autoRowHeight: false,
	            striped: true,
	            fitColumns: true,
	            collapsible:true,
	            url:'${webroot}/zg/f_json/judgeLogPage.shtml?judgeCode=${param.judgeCode}',   
	            remoteSort: false,
	            singleSelect: true,
	            border:false,
	            columns:[
	            	[
	                    {field:'judgeName',title:'业务名称',sortable:true,width:100},
	                    {field:'startTime',title:'开始时间',sortable:true,width:80},
	                    {field:'totalCount',title:'计算总数',sortable:true,width:80},
	                    {field:'successCount',title:'成功数',sortable:true,width:80},
	                    {field:'failCount',title:'成功数',sortable:true,width:80},
	                    {field:'endTime',title:'结束时间',sortable:true,width:80},
	                    {field:'statusName',title:'状态',sortable:true,width:50},
	                    {field:'_operate',title:'操作',sortable:true,width:50,
	                    	formatter:function(value,r){
								if(r.status==2){
									return ['<a href="javascript:yj.openLog(\'',$.trim(r.id),'\',\'查看日志\');" class="ico_run" title="计算"></a>'].join('');
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
	$(document).ready(function () {
		yj.query();
	});
</script>
</body>
</html>