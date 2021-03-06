<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>手卫生统计</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<input type="hidden" id="reportFile" value="nis7/KJYWSYL"/>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
		 <div id="patient_frame_tabs" data-options="fit:true,plain:true">
	       <nis:auth menuNo="D080901">
		       <div title="手卫生依从性按科室汇总"  data-options="href:'${webroot}/report/f_view/fineReportSwFrame.shtml?reportFile=nis7/SW_KSTJ&__bypagesize__=false&id=sw7'" ${'7' eq param.tab ? "selected" : ""}>	           
		       </div>
	       </nis:auth>
	       <nis:auth menuNo="D080902">
		       <div title="手卫生依从性按月份汇总" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSwFrame.shtml?reportFile=nis7/SW_AYFTJ&__bypagesize__=false&id=sw4'" ${'4' eq param.tab ? "selected" : ""}>	           
		       </div>
	       </nis:auth>
	       <nis:auth menuNo="D080903">
		       <div title="手卫生依从性按岗位汇总" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSwFrame.shtml?reportFile=nis7/SW_RYLXTJ&__bypagesize__=false&id=sw8'" ${'8' eq param.tab ? "selected" : ""}>	           
		       </div>
	       </nis:auth>
	       <nis:auth menuNo="D080905">
		       <div title="手卫生依从性按指征汇总" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSwFrame.shtml?reportFile=nis7/SW_ZZTJ&__bypagesize__=false&id=sw9'">	           
		       </div>
	       </nis:auth>
	       <nis:auth menuNo="D080906">
		       <div title="手卫生用品与设施调查统计" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSwFrame.shtml?reportFile=nis7/SW_SWSYPYSSDCTJ&__bypagesize__=false&id=sw6'">	           
		       </div>
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