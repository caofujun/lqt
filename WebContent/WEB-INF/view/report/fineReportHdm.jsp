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
	<input type="hidden" id="reportFile" value="nis7/HDM_KSXHL"/>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
			 <div id="patient_frame_tabs" data-options="fit:true,plain:true">		  
		        <nis:auth menuNo="D010601">
			        <div title="科室现患率统计"  data-options="href:'${webroot}/report/f_view/fineReportHdmFrame.shtml?reportFile=nis7/HDM_KSXHL&__bypagesize__=false&id=hdm1'"></div>
		        </nis:auth>
		        <nis:auth menuNo="D010602">
			       <div title="科室抗菌药物汇总" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportHdmFrame.shtml?reportFile=nis7/HDM_KSKJYWHZ&__bypagesize__=false&id=hdm2&startDate=${param.startDate}&endDate=${param.endDate}'" ${'2' eq param.tab ? "selected" : ""} ></div>
		       </nis:auth>
		       <nis:auth menuNo="D010603">
			       <div title="科室抗菌药物种类分布" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportHdmFrame.shtml?reportFile=nis7/HDM_KSKJYWFB&__bypagesize__=false&id=hdm3&startDate=${param.startDate}&endDate=${param.endDate}'" ${'3' eq param.tab ? "selected" : ""} ></div>
		       </nis:auth>
		       <nis:auth menuNo="D010604">
			       <div title="病原体抗菌药物分布" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportHdmFrame.shtml?reportFile=nis7/HDM_BYTKJYWFB&__bypagesize__=false&id=hdm4&startDate=${param.startDate}&endDate=${param.endDate}'" ${'4' eq param.tab ? "selected" : ""} ></div>
		       </nis:auth>
		       <nis:auth menuNo="D010605">
			       <div title="病原体感染部位分布" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportHdmFrame.shtml?reportFile=nis7/HDM_BYTGRBWFB&__bypagesize__=false&id=hdm5&startDate=${param.startDate}&endDate=${param.endDate}'" ${'5' eq param.tab ? "selected" : ""} ></div>
		       </nis:auth>
		       <nis:auth menuNo="D010606">
			       <div title="抗菌药物用药目的分布" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportHdmFrame.shtml?reportFile=nis7/HDM_KJYWYYMD&__bypagesize__=false&id=hdm6&startDate=${param.startDate}&endDate=${param.endDate}'" ${'6' eq param.tab ? "selected" : ""} ></div>
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