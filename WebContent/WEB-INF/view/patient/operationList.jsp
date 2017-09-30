<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>手术信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="operationPanel"></div>
	
<script type="text/javascript">
	var operation = {
		panel : 'operationPanel',
		operInfo : function(id) {
			parent.menuInfo.clickMenu('手术信息','/st005Ssxxb/f_view/toSurgeryInfoEdit.shtml?id=' + id, true, null, null, '${param.tabBodyId}');
    	},
	};

	$(function(){
		
		$('#' + operation.panel).datagrid({
	       fit: true,
	       nowrap: true,
	       autoRowHeight: true,
	       striped: true,
	       fitColumns: true,
	       url:'${webroot}/st005Ssxxb/f_json/findRecordList.shtml?zyid=' + '${param.zyid}',   
	       remoteSort: false,
	       singleSelect: true,
	       border:false,
	       columns:[ 
		       	[
		            {field:'relid',title:'手术单号',sortable:true,width:90},
		            {field:'operRoom',title:'手术房间',sortable:true,width:60},
		            {field:'operAt',title:'手术时间',sortable:true,align:'center',width:70},
		            {field:'operLengTime',title:'持续时间（分钟）',sortable:true,width:100},
		            {field:'operName',title:'手术名称',sortable:true,width:140,
		            	formatter:function(value,rec){
							return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="operation.operInfo(\'' + rec.id + '\')">' + value + '</a>'].join('');
					    }
		            },
		            {field:'incisionGrade',title:'切口等级',sortable:true,width:60},
		            {field:'heal',title:'愈合情况',sortable:true,width:80},
		            {field:'narcKind',title:'麻醉类型',sortable:true,width:60},
		            {field:'opedocName',title:'手术医生',sortable:true,width:80},
		            {field:'anesDrName',title:'麻醉医生',sortable:true,width:80},
		            {field:'asa',title:'ASA评分',sortable:true,align:'center',width:55},
		            {field:'nnis',title:'NNIS评分',sortable:true,align:'center',width:55},

		        ]
	       ],
	       rownumbers:true
	   });
	});
</script>
</body>
</html>
