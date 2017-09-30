<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>报卡</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="bk001SbkPanel"></div>
		<div id="tb" class="m_search">
			
		</div>
		<script type="text/javascript">
			var bk001Sbk = {
				panel : 'bk001SbkPanel',
				openBk:function(bk2Relid){
					parent.parent.menuInfo.clickMenu('报卡详情','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&action=info&bk2Relid=' + bk2Relid ,true);
				}
			};
			$(document).ready(function () { 
				$('#'+bk001Sbk.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                border:false,
	                url:'${webroot}/bk001Sbk/f_json/pageQueryByZyid.shtml',
	                queryParams: {
		                'zyid' : '${param.zyid}'
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
							{field:'infectTypeId',title:'感染类型',sortable:true,width:15,
								formatter:function(value,r){
									if(r.infectTypeId==1){
										return ["院感"].join('');
									}else{
										return ["社感"].join('');
									}
								}	
							},
		                    {field:'infectDiagnName',title:'感染部位',sortable:true,width:20},
		                    {field:'infectDate',title:'感染日期',sortable:true,width:20},		           
		                    {field:'infectDeptName',title:'感染科室',sortable:true,width:20},
		                    {field:'reportDrName',title:'上报',sortable:true,width:20,
		                    	formatter:function(value,r){
		                    		return [r.reportDrName,'(',r.reportAt,')'].join('');
		                    	}
		                    },
		                    {field:'authUsername',title:'确认',sortable:true,width:20,
		                    	formatter:function(value,r){
		                    		return [r.authUsername,'(',r.authAt,')'].join('');
		                    	}	
		                    },
		                    {field:'lastContent',title:'相关性',sortable:true,width:20},
		                    {field:'_operate',title:'操作',width:10,
								formatter:function(value,r){
										return ['<a href="javascript:bk001Sbk.openBk(\'',r.bk2Relid,'\');" class="ico_view" title="查看详细"></a>'].join('');							
								}
							}
		                ]
	                ],
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>
