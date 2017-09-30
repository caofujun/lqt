<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<title>感染统计</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<input type="hidden" id="reportFile" value="nis7/YYGRL_DEP"/>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
			 <div id="patient_frame_tabs" class="easyui-tabs" data-options="fit:true,plain:true">
			   	<nis:auth menuNo="E0301">
			   		<div title="科室感染发生率"  data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/YYGRL_DEP&__bypagesize__=false&id=gr3&startDate=${param.startDate}&endDate=${param.endDate}&grType=1'" ${'3' eq param.tab ? "selected" : ""}></div>
			   	</nis:auth>
		       <nis:auth menuNo="E0302">
		       		<div title="科室医院感染日发生率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/YYGRRGRL_DEP&__bypagesize__=false&id=gr5'" ${'5' eq param.tab ? "selected" : ""}></div>		  
		       </nis:auth>
			  <nis:auth menuNo="E0401">
				  <div title="医院感染率趋势"  data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/GRQS_YYGRFBL_MONTH&__bypagesize__=false&id=gr23&startDate=${param.startDate}&endDate=${param.endDate}'" ${'23' eq param.tab ? "selected" : ""}>	           
			       </div>
			  </nis:auth>
		       <nis:auth menuNo="E0303">
		       		<div title="科室感染部位分布" style="overflow:hidden"  data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/YYGRBWFB&__bypagesize__=false&id=gr1&code=${code}&startDate=${param.startDate}&endDate=${param.endDate}&grType=1'" ${'4' eq param.tab ? "selected" : ""}></div>
		       </nis:auth>
		       <!-- div title="社区感染部位分布"  style="overflow:hidden"  data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/SQGRBWFB&__bypagesize__=false&id=gr6'"></div -->
		       <nis:auth menuNo="E0304">
		       		<div title="感染部位病原体分布"  style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/GRBWBYTFB&__bypagesize__=false&id=gr2'" ${'2' eq param.tab ? "selected" : ""}></div>
		       </nis:auth>
		       <!-- div title="社区感染部位病原体分布"  style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/SQGRBWBYTFB&__bypagesize__=false&id=gr7'"></div -->
		       <nis:auth menuNo="E0305">
		       		<div title="易感因素分布"  style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/YGYS&__bypagesize__=false&id=gr11'" ${'11' eq param.tab ? "selected" : ""}></div>
		       </nis:auth>
		       <nis:auth menuNo="E0306">
		       		<div title="CLABSI"  style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/CLABSI_DEPT&__bypagesize__=false&id=gr8'" ${'8' eq param.tab ? "selected" : ""}></div>	
		       </nis:auth>
		       <nis:auth menuNo="E0307">
		       		<div title="VAP"  style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/VAP_DEPT&__bypagesize__=false&id=gr9'" ${'9' eq param.tab ? "selected" : ""}></div>	
		       </nis:auth>
		       <nis:auth menuNo="E0308">
		       		<div title="CAUTI"   style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportGrFrame.shtml?reportFile=nis7/CAUTI_DEPT&__bypagesize__=false&id=gr10'" ${'10' eq param.tab ? "selected" : ""}></div>		    
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