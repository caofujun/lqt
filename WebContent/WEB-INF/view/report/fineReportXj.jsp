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
	<input type="hidden" id="reportFile" value="nis7/SJYXL_DEP"/>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
	 <div id="patient_frame_tabs" data-options="fit:true,plain:true">		  
       <!--div title="送检检出率（科室）"  data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/SJYXL_DEP&__bypagesize__=false&id=xj1'">	           
       </div-->
       <nis:auth menuNo="D040301">
       <div title="科室检出菌多耐率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/SJDNL_DEP&__bypagesize__=false&id=xj2&startDate=${param.startDate}&endDate=${param.endDate}'" ${'2' eq param.tab ? "selected" : ""} >	           
       </div>
       </nis:auth>
       <nis:auth menuNo="D040309">
       <div title="科室检出菌分布" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/DCNYJZDJKSFB&__bypagesize__=false&id=xj10'" ${'10' eq param.tab ? "selected" : ""} >	           
       </div>
       </nis:auth>
       <nis:auth menuNo="D040312">
       <div title="科室标本分布" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/JCJ_KSBBFB&__bypagesize__=false&id=xj13'" ${'13' eq param.tab ? "selected" : ""} >	           
       </div>
	   </nis:auth>
       <nis:auth menuNo="D040304">
       <div title="科室感染类型分布" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/GRLXKSFB&__bypagesize__=false&id=xj5&startDate=${param.startDate}&endDate=${param.endDate}'" ${'5' eq param.tab ? "selected" : ""} >	           
       </div>
	   </nis:auth>
       <nis:auth menuNo="D040305">
       <div title="科室多耐菌感染率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/DCNYJGRFSL&__bypagesize__=false&id=xj6&grType=${param.grType}'" ${'6' eq param.tab ? "selected" : ""} >	           
       </div>
	   </nis:auth>
       <nis:auth menuNo="D040306">
       <div title="科室重点菌感染率" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/GRXJJCGCB&__bypagesize__=false&id=xj7'" ${'7' eq param.tab ? "selected" : ""} >	           
       </div>
	   </nis:auth>
       <nis:auth menuNo="D040302">
       <div title="检出菌药敏分布"  style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/JCJNYL&__bypagesize__=false&id=xj3&grByt=${param.grByt}'" ${'3' eq param.tab ? "selected" : ""} >	           
       </div>
       </nis:auth>
       <nis:auth menuNo="D040315">
       <div title="检出菌标本分布"  style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/JCJ_JCJBBFB&__bypagesize__=false&id=xj15'" ${'15' eq param.tab ? "selected" : ""} >	           
       </div>
       </nis:auth>
       <nis:auth menuNo="D040314">
       <div title="检出菌时间分布" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/JCJ_JCJSJFB&__bypagesize__=false&id=xj14'" ${'14' eq param.tab ? "selected" : ""} >	           
       </div>
	   </nis:auth>
       <nis:auth menuNo="D040310">
       <div title="检出菌检出数趋势" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/DCNYJJCSQS&__bypagesize__=false&id=xj11&statType=1'" ${'11' eq param.tab ? "selected" : ""} >	           
       </div>
	   </nis:auth>
       <nis:auth menuNo="D040311">
       <div title="检出菌耐药率趋势" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/DCNYJNYQS&__bypagesize__=false&id=xj12'" ${'12' eq param.tab ? "selected" : ""} >	           
       </div>
	   </nis:auth>
       <nis:auth menuNo="D040303">
       <div title="标本检出菌分布" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/JCJBBFB&__bypagesize__=false&id=xj4&startDate=${param.startDate}&endDate=${param.endDate}&code=${code}&dateType=${param.dateType}'" ${'4' eq param.tab ? "selected" : ""} >	           
       </div>	
	   </nis:auth>
       <nis:auth menuNo="D040307">
       <div title="标本检出菌情况" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/JCJ_BBJCJ&__bypagesize__=false&id=xj8'" ${'8' eq param.tab ? "selected" : ""} >	           
       </div>
	   </nis:auth>
       <nis:auth menuNo="D040308">
       <div title="体温异常血培养送检率"   style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportXjFrame.shtml?reportFile=nis7/ZYHZTWYCXPYSJL&__bypagesize__=false&id=xj9'" ${'9' eq param.tab ? "selected" : ""} >
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