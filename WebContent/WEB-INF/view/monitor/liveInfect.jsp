<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>现患率</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">	
	<div data-options="region:'west',collapsed:false,border:false" style="width:320px; border-right-width: 1px;">
		<div class="easyui-layout" data-options="fit:true">
	        <div data-options="region:'north'" style="height:50px;border-width: 0px 0px 1px 1px;">
        		<div class="m_search_tab" style="margin:9px 20px">
        			<div class="tab_l search_1_${param.type}" style="border:0px;" id="id_isInHosp_2_${param.type}" name="isInHosp_${param.type}" onclick="zg024ImpOpe.query([]);" >
	        			<span style="border-right:0px;"><b>日期</b></span>
	        			<input type="text" class="Wdate text" style="width: 85px;" id="queryDate_${param.type}" readonly="readonly"  onclick="WdatePicker({skin:'whyGreen','dateFmt':'yyyy-MM-dd',onpicked:function(dq){zg024ImpOpe.query([]);},oncleared:function(dq){zg024ImpOpe.query([]);}})" value="${day}">
        			</div> 
					<div class="n_btn_grey" style="margin-left:75px;">
						<a href="javascript:;" onclick="zg024ImpOpe.sheZhi();"><span><b>设置</b></span></a>
					</div>				
        		</div>       	
        	</div>
	        <div data-options="split:true,region:'center'" style="height:240px;border-width: 0px 0px 1px 1px;">
	           <div id="zg024ImpOpePanel"></div>
	        </div>
	    </div>

	</div>
	<div data-options="region:'center',border:false,title:'两周内现患率变化情况'" id="reportPanel" style="border-left-width: 1px;">
		<iframe width="100%" src="" height="99%" id="reportXhl" name="reportXhl" allowtransparency scrolling="no" frameborder="0"></iframe>
	</div>
	</div>
		<script type="text/javascript">
		
		function seturl(deptName,deptId){
			var url='${reportUrl}nis7/GRQS_SSXHL.cpt&__bypagesize__=false&startDate='+$('#queryDate_${param.type}').val()+'&deptId='+deptId;
			$("#reportXhl").attr("src",url);
		}
		var zg024ImpOpe = {
			panel : 'zg024ImpOpePanel',
			query : function () {
				$('#'+zg024ImpOpe.panel).datagrid({
					queryParams: {
	                	'startDate': $('#queryDate_${param.type}').val(),
	                	'endDate': $('#queryDate_${param.type}').val(),
	                },
				});
			},
			sheZhi : function () {
				Comm.dialogGlobal({
		        	url:"${webroot}/gr002YsgrMx/view/toLiveInfectJz.shtml?startDate="+$('#queryDate_${param.type}').val()+"&endDate="+$('#queryDate_${param.type}').val(),
		            title: "设定科室基础感染率",
		            width:300,
		            height:500,
		            type:"iframe",
		            parent:this
		        });
			}
		};
		$(document).ready(function () {
			autoTip.clear();
			$('#'+zg024ImpOpe.panel).datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,
                striped: true,
                fitColumns: true,
                collapsible:true,
                border:false,
                url:'${webroot}/gr002YsgrMx/f_json/findLiveInfect.shtml',	                
                remoteSort: false,
                singleSelect: true,
                queryParams: {
                	'startDate': '${day}',
                	'endDate': '${day}',
                },
                columns:[
                	[              
	                    {field:'DEPTNAME',title:'科室',sortable:true,align:'left',width:30},
	                    {field:'BASEINFECT',title:'基准感染率',sortable:true,align:'center',width:17,
	                    	formatter:function(value,r){
	                    		return r.BASEINFECT+"%";
	                    	}},
	                    {field:'FBL',title:'现患率',sortable:true,align:'center',width:15,
	                    	formatter:function(value,r){
		                    	return r.FBL+"%";
		                }},
	              	],
                ],
    	        rownumbers:true,
                toolbar:'#tb',
                onLoadSuccess: function() {
                	$('#'+zg024ImpOpe.panel).datagrid('selectRow', 0);
                 	var curRow = $('#'+zg024ImpOpe.panel).datagrid('getSelected');
                	seturl(curRow.DEPTNAME,curRow.DEPTID);
                	},
                onClickRow:function(rowIndex, rowData){	                
                	seturl(rowData.DEPTNAME,rowData.DEPTID);
		    	}
            });
			
		});
		</script>
	</body>
</html>