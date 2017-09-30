<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<title>ICU感染统计</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<input type="hidden" id="reportFile" value="nis7/ICUGRTJ_DEPT"/>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
		 <div id="patient_frame_tabs" data-options="fit:true,plain:true">
		  <nis:auth menuNo="D020301">
			  <div title="ICU调整感染率"  data-options="href:'${webroot}/report/f_view/fineReportGrtjFrame.shtml?reportFile=nis7/ICUGRTJ_DEPT&__bypagesize__=false&id=grtj1&startDate=${param.startDate}&endDate=${param.endDate}'" ${'1' eq param.tab ? "selected" : ""}></div>
		   </nis:auth>
		   <nis:auth menuNo="D020302">
		       <div title="新生儿调整感染率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportGrtjFrame.shtml?reportFile=nis7/NICUGRL&__bypagesize__=false&id=grtj2&startDate=${param.startDate}&endDate=${param.endDate}'" ${'2' eq param.tab ? "selected" : ""}></div> 
		   </nis:auth>
	    </div>            
	 </div>
    </div>
<script type="text/javascript">
var reportType = '1';
var report = {
	stopHttp : function() {
		$('#patient_frame_tabs').tabs('getSelected').find('iframe:first-child').attr('src', '');
	}
};
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
        },
        onSelect:function(title,index){
        	var pp = $('#patient_frame_tabs').tabs('getSelected');
        	var url = pp.panel('options').href;
			$('#reportFile').val(url.substring(url.indexOf('=')+1,url.indexOf('&')));
        }
    });
	
 
});
</script>
</body>
</html>