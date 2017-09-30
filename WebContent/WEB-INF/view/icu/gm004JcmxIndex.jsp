<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>插管评估</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body style="padding:5px;">
	
	<div class="easyui-layout" data-options="fit:true">
       <div data-options="region:'west',split:false,border:true" style="width:215px;border-right-width: 1px;">
        	<div class="m_search"  style="display:;border-bottom:1px solid #ccc;width:198px;">
					<div class="m_search_tab" style="width:193px;">
	        			<div class="tab_l search_1_ cur"  name="isInHosp" style="padding: 1px 1px;"  onclick="fxPatientIndex.query('1','');" value="1">在院</div>
	        			<div class="tab_l search_1_ " id="id_isInHosp_2"  name="isInHosp" style="padding: 1px 1px;width:130px;" onclick="fxPatientIndex.query('2','date');" value="2">出院
						<input type="text" style="width:80px;" class="Wdate text" id="queryDate" readonly="readonly"  onclick="WdatePicker({skin:'whyGreen','dateFmt':'yyyy-MM-dd',onpicked:function(dq){fxPatientIndex.query('2','date');},oncleared:function(dq){fxPatientIndex.query('2','date');}})" value="${queryDate}"></div>
        			</div> 
        	</div>
        	<div class="menu_list" >
	        	<div id="warningTreePanel" ></div>
        	</div>
        	<div class="footer dialog_footer">	
		        <div id="patientNumbers" style="padding-right:10px;float:right;">
		        </div>
	        </div>
        </div>
        <div data-options="region:'center',border:true" style="padding-left:10px;">
        	<div id="patientTest" ></div>
        </div>
        <div data-options="region:'east',split:false,border:false"  style="width:740px;">
        	<iframe id="cgPg" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
        </div>
    </div>
	
		<script type="text/javascript">
				var isInHos = "";
		var fxPatientIndex = {
				panel : '"warningTreePanel"',
				//查询
				query : function(isInHosp,cls) {
					if (cls == 'date') {
						$('.search_1_').removeClass('cur');
						$('#id_isInHosp_2').addClass('cur');
					} else if (cls == '') {
						$('.search_1_').addClass('cur');
						$('#id_isInHosp_2').removeClass('cur');
					}
					isInHos = isInHosp;
					$('#warningTreePanel').tree({
				        fit: true,
				        nowrap: true,
				        autoRowHeight: true,
				        lines: false,
				        animate:true,
				        url:'${webroot}/gm004Jcmx/f_json/gm004JcmxIndexMenu.shtml',
			            queryParams: {
			            	'time': $('#time').val(),
			            	'isInHosp': isInHosp,
			            	'queryDate':$('#queryDate').val(),
			            	'deptId':'${deptId}',
			            },
			            onLoadSuccess : function(){
			            	$.ajax({
			                    url: '${webroot}/gm004Jcmx/f_json/gm004JcmxCountPatient.shtml?isInHosp='+isInHosp+'&queryDate='+$('#queryDate').val()+'&deptId=${deptId}',
			                    type: 'post',
			                    dataType: 'json',
			                    success : function(json) {
			                    	$("#patientNumbers").html("共计<b>"+json.data+"</b>人");
								}
			        		});	
                       }
				    });
			    },
			    seturl:function (zyid,creationdate){
			    	var showName = '1';
					var url='${webroot}/gm004Jcmx/f_view/cgPgList.shtml?zyid='+zyid+'&dateMonth='+creationdate+'&showName='+showName;
				    $("#cgPg").attr("src",url);
				}
		};
		$(document).ready(function () {
			$('#warningTreePanel').tree({
		        fit: true,
		        nowrap: true,
		        autoRowHeight: true,
		        lines: false,
		        animate:true,
		        url:'${webroot}/gm004Jcmx/f_json/gm004JcmxIndexMenu.shtml',
	            queryParams: {
	            	'time': $('#time').val(),
	            	'isInHosp': $("div[name='isInHosp'].cur").attr('value'),
	            	'queryDate':$('#queryDate').val(),
	            	'deptId':'${deptId}',
	            },
	            onLoadSuccess: function (node, data) {
	                if (data.length > 0) {
	                	//找到第一个元素
	                    var n = $('#warningTreePanel').tree('find', data[0].id);
	                    n = $("#warningTreePanel").tree("expand",n.target);  
	                    //调用选中事件
	                    var children = $("#warningTreePanel").tree('getChildren',n.target); 
	                    /* 选中第一个子节点 */  
	                    $("#warningTreePanel").tree("select",children[1].target);  
	                  
	                }
	            }, 
	            onSelect : function(node){
	            	if ($(this).tree('isLeaf', node.target)){
		            	panel : '"patientTest"',
		            	$.ajax({
		                    url: '${webroot}/gm004Jcmx/f_json/gm004JcmxGetpatientList.shtml',
		                    type: 'post',
		                    data: { id: node.id },
		                    dataType: 'json',
		                    success : function(json) {
		                    	$('#patientTest').datagrid({
		                      	  title:'',
		                    	  method:'post',
		                    	  url:'${webroot}/gm004Jcmx/f_json/gm004JcmxGetpatientList.shtml?id='+node.id,
		                          iconCls:'icon-ok',
		                    	  striped:true,
		                    	  singleSelect: true,
		                    	  nowrap:true,
		                          rownumbers:true,
		                          fitColumns: true, 
		                          autoRowHeight: true,
		                          showFooter: true,
		                          fit: true,
		                          columns:[[    
		                          {field:'creationdate',title:'插管日',width:100,align:'center'},    
		                          {field:'typeid',title:'插管类型',width:100,align:'center',
		                        	  formatter:function(value,r){
											if('04' == r.typeid){
												typeid = '导尿管插管';
												return typeid;
											}else if('05' == r.typeid){
												typeid = '中心静脉插管';
												return typeid;
											}else if('06' == r.typeid){
												typeid = '呼吸机';
												return typeid;
											}
											
										}	 
		                          },    
		                          {field:'isTest',title:'插管状态',width:100,align:'center'}                        
		                         ]],
		                         onLoadSuccess : function(){
		                        	 $('#patientTest').datagrid('selectRow', 0);
		                          	var curRow = $('#patientTest').datagrid('getSelected');
		          	            	var creationdate = curRow.creationdate.substring(0,7).replace("-","/");
		          	            	fxPatientIndex.seturl(curRow.zyid,creationdate);
		                          },
		                         onClickRow:function(rowIndex, rowData){
		                        	 var creationdate = rowData.creationdate.substring(0,7).replace("-","/");
		                        	 fxPatientIndex.seturl(rowData.zyid,creationdate);
		      			    	}
		                        });
		    				}
		        		});
	            	}
	            },
	            onClick : function(node){
	            	if (!$(this).tree('isLeaf', node.target)) {
	            		$(this).tree('toggle',node.target);  
	            	} else {
	            	panel : '"patientTest"',
	            	$.ajax({
	                    url: '${webroot}/gm004Jcmx/f_json/gm004JcmxGetpatientList.shtml',
	                    type: 'post',
	                    data: { id: node.id },
	                    dataType: 'json',
	                    success : function(json) {
	                    	$('#patientTest').datagrid({
	                      	  title:'',
	                    	  method:'post',
	                    	  url:'${webroot}/gm004Jcmx/f_json/gm004JcmxGetpatientList.shtml?id='+node.id,
	                          iconCls:'icon-ok',
	                    	  striped:true,
	                    	  singleSelect: true,
	                    	  nowrap:true,
	                          rownumbers:true,
	                          columns:[[    
	                          {field:'creationdate',title:'插管日',width:100,align:'center'},    
	                          {field:'typeid',title:'插管类型',width:100,align:'center',
	                        	  formatter:function(value,r){
										if('04' == r.typeid){
											typeid = '导尿管插管';
											return typeid;
										}else if('05' == r.typeid){
											typeid = '中心静脉插管';
											return typeid;
										}else if('06' == r.typeid){
											typeid = '呼吸机';
											return typeid;
										}
										
									}	 
	                          },    
	                          {field:'isTest',title:'插管状态',width:100,align:'center'}	                         
	                         ]],
	                         onLoadSuccess : function(){
	                        	 $('#patientTest').datagrid('selectRow', 0);
	                          	var curRow = $('#patientTest').datagrid('getSelected');
	          	            	var creationdate = curRow.creationdate.substring(0,7).replace("-","/");
	          	            	fxPatientIndex.seturl(curRow.zyid,creationdate);
	                          },
	                         onClickRow:function(rowIndex, rowData){
	                        	 var creationdate = rowData.creationdate.substring(0,7).replace("-","/");
	                        	 fxPatientIndex.seturl(rowData.zyid,creationdate);
	      			    	}
	                        });
	    				}
	        		});}
	            },
	            onBeforeExpand:function(node,param){
	            	$('#warningTreePanel').tree('options').url='${webroot}/gm004Jcmx/f_json/gm004JcmxIndexMenuNode.shtml?deptCode='+node.id;
	            }
		    });
			$.ajax({
                url: '${webroot}/gm004Jcmx/f_json/gm004JcmxCountPatient.shtml?isInHosp='+$("div[name='isInHosp'].cur").attr('value')+'&queryDate='+$('#queryDate').val()+'&deptId=${deptId}',
                type: 'post',
                dataType: 'json',
                success : function(json) {
                	$("#patientNumbers").html("共计<b>"+json.data+"</b>人");
				}
    		});	

		});


		</script>
	</body>
</html>
