<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>检查数据匹配</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
		    <div id="patient_frame_tabs" data-options="fit:true,plain:true">			  
				       <div title="微生物" style="overflow:hidden" data-options="href:'${webroot}/xn013Lisbyt/f_view/index.shtml'" ${'xj' eq param.tab ? "selected" : ""}>	           
				       </div>
				       <div title="药敏药物" style="overflow:hidden" data-options="href:'${webroot}/xn014Liskjyw/f_view/index.shtml'" ${'yw' eq param.tab ? "selected" : ""}>	           
				       </div>
				        <div title="标本匹配" style="overflow:hidden" data-options="href:'${webroot}/zg027LisbbPp/f_view/index.shtml'">	           
				       </div>
				    </div>    
		        <!-- div title="常规检验匹配" style="overflow:hidden">
		        	<div class="easyui-tabs" data-options="fit:true,plain:true">		  
				       <div title="送检项目" style="overflow:hidden" data-options="href:'${webroot}/yj003Synonym/f_view/index.shtml'">	           
				       </div>
				       <div title="检验结果(定性)" style="overflow:hidden">	           
				       </div>
				        <div title="检验结果(定量)" style="overflow:hidden">	           
				       </div>
				    </div>
		        </div>
		        <div title="其他匹配" style="overflow:hidden" data-options="" ${'bb' eq param.tab ? "selected" : ""}>	           
		        	  <div class="easyui-tabs" data-options="fit:true,plain:true">		  
				        <div title="标本匹配" style="overflow:hidden" data-options="href:'${webroot}/zg027LisbbPp/f_view/index.shtml'">	           
				       </div>
				    </div>
		        </div -->
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