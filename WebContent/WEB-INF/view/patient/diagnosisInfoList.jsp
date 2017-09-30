<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>诊断问题</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="diagnosisPanel"></div>
<script type="text/javascript">
	$(function(){
		$('#diagnosisPanel').datagrid({
	       fit: true,
	       nowrap: true,
	       autoRowHeight: true,
	       striped: true,
	       fitColumns: true,
	       url:'${webroot}/st003Cryxxb/f_json/findDiagnosisList.shtml?zyid=' + '${param.zyid}',   
	       remoteSort: false,
	       singleSelect: true,
	       border:false,
	       columns:[ 
		       	[
		            {field:'showDiagnosisDate',title:'诊断日期',sortable:true,width:80,align:'center'},
		            {field:'showDiagnosisType',title:'诊断类型',sortable:true,align:'center',width:60,align:'center'},
		            {field:'diagnosisNo',title:'诊断编号',sortable:true,align:'center',width:60},
		            {field:'diagnosisName',title:'诊断名称',sortable:true,width:260},
		            {field:'treatresult',title:'结果',sortable:true,width:160},
		            {field:'docName',title:'诊断医生',sortable:true,width:80},
		            {field:'docId',title:'诊断医生编号',sortable:true,width:90}
		        ]
	       ],
	       rownumbers:true
	   });
	});
</script>
</body>
</html>
