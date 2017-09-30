<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<title>传染病报卡统计分析</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<input type="hidden" id="reportFile" value="cdc/stats.cdc.dept.class"/>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
	<div data-options="iframe:true,region:'center'" style="border-width: 0px;">
			 <div id="patient_frame_tabs" data-options="fit:true,plain:true">
			  	<nis:auth menuNo="K0601">
				  	<div title="传染病报告科室统计表"  data-options="href:'${webroot}/report/f_view/fineReportCdcFrame.shtml?reportFile=cdc/stats.cdc.dept.class&__bypagesize__=false&id=cdc1&startDate=${param.startDate}&endDate=${param.endDate}'">	            </div>
			  	</nis:auth>
			  	<nis:auth menuNo="K0602">
		        	<div title="传染病疾病分类统计表" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportCdcFrame.shtml?reportFile=cdc/stats.cdc.diseases.class&__bypagesize__=false&id=cdc2'" ></div>
		        </nis:auth>
		        <nis:auth menuNo="K0603">
		        	<div title="传染病报告医生统计表" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportCdcFrame.shtml?reportFile=cdc/stats.cdc.doctor.class&__bypagesize__=false&id=cdc3'" >	           </div>
		       </nis:auth>
		       <nis:auth menuNo="K0604">
		        	<div title="传染病上报疾病分类环比" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportCdcFrame.shtml?reportFile=cdc/stats.cdc.link.ratio&__bypagesize__=false&id=cdc4'" >	           </div>	
		       </nis:auth>
		       <nis:auth menuNo="K0605">
		       		<div title="HIV送检统计表" style="overflow:hidden" data-options="href:'${webroot}/report/f_view/fineReportHivFrame.shtml?reportFile=cdc/stats.cdc.hiv.ratio&__bypagesize__=false&id=cdc5'" >	            </div>		  
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