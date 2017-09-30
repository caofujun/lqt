<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>消毒灭菌录入</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
			 <div id="patient_frame_tabs" data-options="fit:true,plain:true">		  
		        <div title="环境卫生学监测"  >	           
		       		<iframe scrolling="yes" frameborder="0"  src="${webroot}/hw101Jcdj/f_view/toHygieneList.shtml?type=1" style="width:100%;height:100%;"></iframe>
		       </div>
		       <div title="一次性用品监测" style="overflow:hidden">
		       		<iframe scrolling="yes" frameborder="0"  src="${webroot}/hw101Jcdj/f_view/toHygieneList.shtml?type=2" style="width:100%;height:100%;"></iframe>	           
		       </div>
		        <div title="污水监测" style="overflow:hidden">
		        	<iframe scrolling="yes" frameborder="0"  src="${webroot}/hw101Jcdj/f_view/toHygieneList.shtml?type=3" style="width:100%;height:100%;"></iframe>	           
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