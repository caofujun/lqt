<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>危险因素</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'患者列表'" style="width:300px;" class="easyui-layout" id="layout">
	<div data-options="region:'center',border:false,fit:true" style="padding: 5px 0px;">
	</div>
	<div id="fxPatientPanel">
	</div>
	</div>
	<div data-options="region:'center'">
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
				            	'deptid': '${deptid}',
				                'typeid': '${typeid}',
				                'strDate':'${strDate}',
				                'neonatebw':'${neonatebw}'
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
	                url:'${webroot}/fxPatient/f_json/pageQueryIcu.shtml',
	                queryParams: {
		            	'bizType': '1',
		            	'deptid': '${deptid}',
		                'typeid': '${typeid}',
		                'strDate':'${strDate}',
		                'neonatebw':'${neonatebw}'
		            },
	                remoteSort: false,
	                singleSelect: true,
	                //showFooter: true,
	                columns:[
	                	[
		                    {field:'patientName',title:'患者',sortable:true,width:15},  
		                    {field:'gyStatusName',title:'干预',sortable:true,width:12,
								styler: function(value,row,index) {
									if (row.gyStatusName == '已干预') { return 'color:green;';}
								}
				            },
		                    {field:'pdcaStatusName',title:'PDCA',sortable:true,width:12,
								styler: function(value,row,index) {
									if (row.pdcaStatusName == '已引入') { return 'color:green;';}
								}
				            },
		                    {field:'yjgrCount',title:'预警感染',sortable:true,width:15,align:'center'},
		                    {field:'qrgrCount',title:'确认感染',sortable:true,width:15,align:'center'}
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