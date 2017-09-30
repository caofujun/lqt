<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>ICU日志</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="gm004JcmxPanel"></div>
		<script type="text/javascript">
			var gm004Jcmx = {
				panel : 'gm004JcmxPanel',
				//编辑
			    edit:function(zyid,time) {
			    	parent.parent.parent.parent.menuInfo.clickMenu('插管评估','/gm004Jcmx/f_view/cgPgList.shtml?zyid='+zyid+'&dateMonth='+time,true);
			    }
			};
			$(document).ready(function () { 
				$('#'+gm004Jcmx.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/gm004Jcmx/f_json/findByZyid.shtml',
	                queryParams: {
		            	'zyid': '${zyid}',
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
							{field:'outbreakTypeName',title:'项目名称',sortable:true,width:20},  
		                    {field:'startAt',title:'开始日期',sortable:true,width:20},  
		                    {field:'d',title:'最近医嘱开始日期',sortable:true,width:20,formatter:function(value, row, index){
		                    	if(row.typeid=="04"){
		                    		return row.dngLastDt;
		                    	}else if(row.typeid=="05"){
		                    		return row.zxjmLastDt;
		                    	}else if(row.typeid=="06"){
		                    		return row.hxjLastDt;
		                    	}
		                    }},  
		                    {field:'stopAt',title:'截止日期',sortable:true,width:20},
		                    {field:'days',title:'使用天数',sortable:true,width:15}
		                    <c:if test="${cgpg != 1}">,
		                    {field:'_days',title:'管道评估情况',sortable:true,width:15,
		                    	formatter:function(value,r){
		                    		if(r.typeid == '04'){
			                    			if('${cgpg}'=='0'&& r.isTest == '未评估'){
			                    				return "异常";
			                    			}else{
			                    				return "正常";
			                    			}
			                    		
		                    		}else if(r.typeid == '05'){
		                    			
			                    			if('${cgpg}'=='0'&& r.isTest == '未评估'){
			                    				return "异常";
			                    			}else{
			                    				return "正常";
			                    			}
		                    		
		                    		}else if(r.typeid == '06'){
		                    			
			                    			if('${cgpg}'=='0'&& r.isTest == '未评估'){
			                    				return "异常";
			                    			}else{
			                    				return "正常";
			                    			}
		                    			
		                    		}
		                    	}
		                    },
		                    {field:'_operate',title:'操作',width:10,
								formatter:function(value,r){
									var ctime = r.startAt.substring(0,7).replace("-","/");
									return ['<a href="javascript:gm004Jcmx.edit(\'',r.zyid,'\',\''+ctime+'\');">查看插管情况</a>'].join('');							
								}
							} </c:if>
		                ]
	                ],
	                rowStyler:function(rowIndex,r){
	                	
		        		if(r.typeid == '04'){
		        			
	                   			if('${cgpg}'=='0'&& r.isTest == '未评估'){
	                   				return 'background-color:#7FFF00;color:red;';
	                   			}
		        			
                   		}else if(r.typeid == '05'){
	                   			if('${cgpg}'=='0'&& r.isTest == '未评估'){
	                   				return 'background-color:#7FFF00;color:red';
	                   			}
                   		}else if(r.typeid == '06'){
	                   			if('${cgpg}'=='0'&& r.isTest == '未评估'){
	                   				return 'background-color:#7FFF00;color:red';
	                   			}
                   		}		        			
		            }
	            });
			});
		</script>
	</body>
</html>
