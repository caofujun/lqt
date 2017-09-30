<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>ICU日志</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body class="easyui-layout">
	<div data-options="region:'center'">
		<div id="tb" class="m_search">
		<span class="pro_text">		
			时间:&nbsp;${startDate}&nbsp;&nbsp;
			科室:&nbsp;${deptName}&nbsp;&nbsp;
			类型:&nbsp;${typeName}&nbsp;&nbsp;
			<input type="button" onclick="gm004Jcmx.exportExcelPatient()"  class="btn" iconCls="icon-search" plain="true" value="导出" />
			
			<!-- <input type="button" onclick="gm004Jcmx.findFxPatient()"  class="btn" iconCls="icon-search" plain="true" value="风险干预" />
 -->		</span>
		</div>
		<div id="gm004JcmxPanel"></div>
	</div>
	<div data-options="region:'south'" style="height:200px">
		<iframe id="gm004JcmxDetailIframe" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
	</div>
		<script type="text/javascript">
			function seturl(zyid){
				var url="${webroot}/gm004Jcmx/f_view/findByZyidUrl.shtml?zyid="+zyid;
			    $("#gm004JcmxDetailIframe").attr("src",url);
			}
			var gm004Jcmx = {
				panel : 'gm004JcmxPanel',
				//excel导出
				 exportExcelPatient : function (){
					var url = "${webroot}/gm004Jcmx/f_json/exportExcelPatient.shtml?bizType=${bizType}&deptid=${deptid}&typeid=${typeid}&neonatebw=${neonatebw}&startDate=${startDate}&endDate=${endDate}";
			    	window.location.href = url;
				},
				findFxPatient : function (){
					parent.parent.menuInfo.clickMenu('风险干预','/fxPatient/f_view/fxPatientListIcu.shtml?deptid=${deptid}&typeid=${typeid}&neonatebw=${neonatebw}&startDate=${startDate}&endDate=${endDate}&tab=2',true);
				},
				showDetail : function(zyid){
			    	parent.parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
			    },
			    showFx : function(zyid){
			    	parent.parent.menuInfo.clickMenu('风险详情','/fxPatient/f_view/detail.shtml?zyId='+zyid+"&tab=2",true);
			    },
			    query : function(){
			    	autoTip.clear();
			    	$('#'+gm004Jcmx.panel).datagrid({
			            queryParams: {
			            	'deptid': '${deptid}',
			                'typeid': '${typeid}',
			                'neonatebw' : '${neonatebw}',
			                'startDate':'${startDate}',
			                'endDate':'${endDate}'
			            }
			        });
			    },
			    del : function(zyid,deptid) {
			    	$.messager.confirm('提示', '确认删除改患者插管信息?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/gm004Jcmx/f_json/deleteHxj.shtml',
			                        type: 'post',
			                        data: { typeid:'${typeid}',zyid:zyid,deptid:'${deptid}',creationdate:'${startDate}'},
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											gm004Jcmx.query();
											parent.gm003Ybsj.query('${deptid}');
			                                $.messager.show({ title: '提示', msg: '删除成功！' });
								    	} else if(json.result === 'error') {
								    		$.messager.show({ title: '提示', msg: '系统异常！' });
								    	} else {
								    		$.messager.show({ title: '提示', msg: json.msg });
								    	}
									}
			            	});
			        	}
			    	});
			    }
			    
			};
			$(document).ready(function () { 
				$('#'+gm004Jcmx.panel).datagrid({
					fitColumns: true, 
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                /* fitColumns: true, */
	                collapsible:true,
	                url:'${webroot}/gm004Jcmx/f_json/pageQuery.shtml',
	                queryParams: {
		            	'bizType': '${bizType}',
		            	'deptid': '${deptid}',
		            	'unitId': '${unitId}',
		            	'bedicu': '${bedicu}',
		                'typeid': '${typeid}',
		                'neonatebw' : '${neonatebw}',
		                'startDate':'${startDate}',
		                'endDate':'${endDate}'
		            },
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
							{field:'zyid',title:'${patientZyTitle}',sortable:true,width:110,
								formatter:function(value,r){
									return ['<a href="javascript:gm004Jcmx.showDetail(\'',r.zyid,'\',\'\');">',r.${patientZyValue},'(',r.visitId,')</a>'].join('');
								}	
							},  
		                    {field:'patientName',title:'姓名',sortable:true,width:90,
								formatter:function(value,row,index){
			    					return [(row.patientName+'('+row.age+row.ageUnit+','+(row.bedNo != null ? row.bedNo:'')+'床)')].join('');
		    					}
							},
		                    {field:'tw',title:'体温',sortable:true,width:40,
								styler: function(value,row,index) {
									if (row.tw != '') { return 'color:red;';}
								},
								formatter:function(value,r){
									return ['<span title="',r.tw,'">',r.tw,'</span>'].join('');
								}
							},		                    
		                    {field:'diagnosisName',title:'入院诊断',sortable:true,width:150},
		                    {field:'inDeptName',title:'入院科室',sortable:true,width:100},
		        			<c:if test="${bizType!='1'}">
		                    {field:'gm4DeptName',title:'当前科室',sortable:true,width:100},
		        			</c:if>
		                    {field:'inHospAt',title:'入院时间',sortable:true,width:75},		           
		                    {field:'outAt',title:'出院时间',sortable:true,width:75}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb',
	                onLoadSuccess: function() {
	                	
	                	$('#'+gm004Jcmx.panel).datagrid('selectRow', 0);
	                	var curRow = $('#'+gm004Jcmx.panel).datagrid('getSelected');
	                	seturl(curRow.zyid);
	                },
	                onClickRow:function(rowIndex, rowData){	                
	                	seturl(rowData.zyid);
			    	}
	            });
			});
		</script>
	</body>
</html>
