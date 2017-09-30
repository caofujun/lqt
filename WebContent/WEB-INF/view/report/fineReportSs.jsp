<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>细菌统计</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<input type="hidden" id="reportFile" value="nis7/SSXGYYGRFSL"/>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
			 <div id="patient_frame_tabs" data-options="fit:true,plain:true">		  
		        <nis:auth menuNo="D050301">
			        <div title="手术患者感染发生率"  data-options="href:'${webroot}/report/f_view/fineReportSsFrame.shtml?reportFile=nis7/SSXGYYGRFSL&__bypagesize__=false&id=ss1'">	           
			        </div>
		        </nis:auth>
		        <nis:auth menuNo="D050302">
			       <div title="手术患者肺部感染发生率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSsFrame.shtml?reportFile=nis7/SSHZFBGRFSL&__bypagesize__=false&id=ss3&startDate=${param.startDate}&endDate=${param.endDate}'" ${'3' eq param.tab ? "selected" : ""} >	           
			       </div>
		       </nis:auth>
		       <nis:auth menuNo="D050303">
			       <div title="手术切口感染发生率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSsFrame.shtml?reportFile=nis7/SSBWGRFSL&__bypagesize__=false&id=ss2&startDate=${param.startDate}&endDate=${param.endDate}'" ${'2' eq param.tab ? "selected" : ""} >	           
			       </div>
		       </nis:auth>
		       <nis:auth menuNo="D050304">
			       <div title="科室NNIS分级手术感染率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSsFrame.shtml?reportFile=nis7/NISSFJSSBWGRL&__bypagesize__=false&id=ss8&startDate=${param.startDate}&endDate=${param.endDate}'" ${'8' eq param.tab ? "selected" : ""} ></div>
		       </nis:auth>
		       <nis:auth menuNo="D050308">
			       <div title="不同感染风险指数手术部位感染发病率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSsFrame.shtml?reportFile=nis7/SS_NNISGRL&__bypagesize__=false&id=ss12&startDate=${param.startDate}&endDate=${param.endDate}'" ${'12' eq param.tab ? "selected" : ""} ></div>
		       </nis:auth>
		       <nis:auth menuNo="D050305">
			       <div title="外科医师专率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSsFrame.shtml?reportFile=nis7/WKYSZL&__bypagesize__=false&id=ss9&startDate=${param.startDate}&endDate=${param.endDate}'" ${'9' eq param.tab ? "selected" : ""} >	           
			       </div>
		       </nis:auth>
		       <nis:auth menuNo="D050306">
			       <div title="手术切口愈合率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSsFrame.shtml?reportFile=nis7/SSQKYHL&__bypagesize__=false&id=ss10&startDate=${param.startDate}&endDate=${param.endDate}&incisionGrade=${param.incisionGrade}'" ${'10' eq param.tab ? "selected" : ""} >	           
			       </div>
		       </nis:auth>
		       <nis:auth menuNo="D050307">
			       <div title="失血量大于1500ml的手术" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportSsFrame.shtml?reportFile=nis7/SXLDYYQHSDSS&__bypagesize__=false&id=ss11&startDate=${param.startDate}&endDate=${param.endDate}'" ${'11' eq param.tab ? "selected" : ""} >	           
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