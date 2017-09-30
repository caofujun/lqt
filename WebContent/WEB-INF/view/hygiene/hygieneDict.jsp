<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>数据/字典维护</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
			 <div id="patient_frame_tabs" data-options="fit:true,plain:true">		  
		        <div title="监测项目管理"  >	           
		       		<iframe scrolling="yes" frameborder="0"  src="${webroot}/hw001Jcxm/f_view/toList.shtml" style="width:100%;height:100%;"></iframe>
		       </div>
		       <div title="监测计算标准" style="overflow:hidden">
		       		<iframe scrolling="yes" frameborder="0"  src="${webroot}/hw002Jsbz/f_view/toList.shtml" style="width:100%;height:100%;"></iframe>	           
		       </div>
		        <div title="采样场所" style="overflow:hidden">
		        	<iframe scrolling="yes" frameborder="0"  src="${webroot}/hw003Cycs/f_view/toList.shtml" style="width:100%;height:100%;"></iframe>	           
		       </div>
		       <div title="采样标本" style="overflow:hidden">
		        	<iframe scrolling="yes" frameborder="0"  src="${webroot}/hw004Cybb/f_view/toList.shtml" style="width:100%;height:100%;"></iframe>	           
		       </div>
		       <div title="采样方法" style="overflow:hidden">
		        	<iframe scrolling="yes" frameborder="0"  src="${webroot}/hw005Cyff/f_view/toList.shtml" style="width:100%;height:100%;"></iframe>	           
		       </div>
		       <div title="采样点数" style="overflow:hidden">
		        	<iframe scrolling="yes" frameborder="0"  src="${webroot}/hw006Cyds/f_view/toList.shtml" style="width:100%;height:100%;"></iframe>	           
		       </div>	    
		    </div>            
	 </div>
    </div>
<script type="text/javascript">
$(document).ready(function () {
	function refresh(index) {
		$('#patient_frame_tabs').tabs('select', index);
		var currTab = $('#patient_frame_tabs').tabs('getTab', index);
		var url = currTab.attr('href');
		currTab.panel('refresh', url);
	}
	
	//监听右键事件，创建右键菜单
    $('#patient_frame_tabs').tabs({
        onContextMenu:function(e, title, index){
   		    e.preventDefault();   
        }
    });
	
 
});
</script>
</body>
</html>