<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>危险因素</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body class="easyui-layout">
	<div data-options="region:'west',border:false,split:false,title:'患者风险详情'" style="width:240px; class="easyui-layout" id="layout">
	<div id="fxPatientPanel">
	</div>
	</div>
	<div data-options="region:'center'" style="border-width: 0 1px;">
	<iframe id="fxPatientDetailIframe" src="" scrolling="auto" width="100%" height="99%" frameborder="0"></iframe>
	</div>
		<script type="text/javascript">
			function seturl(zyId){
				var url="${webroot}/fxPatient/f_view/detail.shtml?zyId="+zyId;
			    $("#fxPatientDetailIframe").attr("src",url);
			}
			var fxPatient = {
				panel : 'fxPatientPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+fxPatient.panel).datagrid({
			            queryParams: {
			            	'deptCode': '${fxPatient.deptCode}',
			                'startDate': '${fxPatient.startDate}',
			                'endDate': '${fxPatient.endDate}',
			                'dgsType':'${fxPatient.dgsType}',
			                'gyStatus':'${fxPatient.gyStatus}',
			                'pgStatus':'${fxPatient.pgStatus}',
			                'cxStatus':'${fxPatient.cxStatus}'
			            },
			            pageNumber: 1
			        });
			    }
			};
			$(document).ready(function () {
				autoTip.clear();
				$('#'+fxPatient.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/fxPatient/f_json/pageQuery.shtml',
	                queryParams: {
		            	'deptCode': '${fxPatient.deptCode}',
		                'startDate': '${fxPatient.startDate}',
		                'endDate': '${fxPatient.endDate}',
		                'dgsType':'${fxPatient.dgsType}',
		                'gyStatus':'${fxPatient.gyStatus}',
		                'pgStatus':'${fxPatient.pgStatus}',
		                'cxStatus':'${fxPatient.cxStatus}'
		            },
	                remoteSort: false,
	                singleSelect: true,
	                showFooter: true,
	                columns:[
	                    [
	                     	{field:'patientName',title:'患者',sortable:true,width:12,rowspan:2},  
		                    {field:'gyStatusName',title:'干预',sortable:true,width:10,rowspan:2,
	                     		formatter : function(value,row) {
									if (row.gyStatusName == '已干预') { return '<span class="can_intervene" title="已干预"></span>';}
									else if(row.gyStatusName == '未干预'){return '<span class="no_intervene" title="未干预"></span>'}
								}
				            },
		                    {field:'pdcaStatusName',title:'PDCA',sortable:true,width:10,rowspan:2,
				            	formatter : function(value,row) {
									if (row.pdcaStatusName == '引入') { return '<span class="introduction" title="引入"></span>';}
								}
				            },
				            {title:'感染',colspan:2}
	                     ],
	                	[
		                   
		                    {field:'yjgrCount',title:'预警',sortable:true,width:10,align:'center',
		                    	formatter : function(value,row) {
		                    		if(row.yjgrCount>0){
		                    			return '<span class="dotNumber_red">'+row.yjgrCount+'</span>';
		                    		}
		                    	}
		                    },
		                    {field:'qrgrCount',title:'确认',sortable:true,width:10,align:'center',
		                    	formatter : function(value,row) {
		                    		if(row.qrgrCount>0){
		                    			return '<span class="dotNumber_red">'+row.qrgrCount+'</span>';
		                    		}
		                    	}	
		                    }
		                ]
	                ],
	                toolbar:'#tb',
	                onLoadSuccess: function() {
	                	$('#'+fxPatient.panel).datagrid('selectRow', 0);
	                	var curRow = $('#'+fxPatient.panel).datagrid('getSelected');
	                	seturl(curRow.zyId);
	                },
	                onClickRow:function(rowIndex, rowData){	                
	                	seturl(rowData.zyId);
			    	}
	            });
			});
		</script>
	</body>
</html>