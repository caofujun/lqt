<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>ICU日志</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="tb" class="m_search" style="display: none;" >
			<span class="pro_text">选择月份:</span>
			<input type="text"  id="startMonthDate" value="${startMonthDate}" style="width:80px"  class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  />		
		 	<input type="text"  id="endMonthDate" value="${endMonthDate}" style="width:80px"  class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  />	
		 	<c:if test="${unitFlag=='1'}">
			<tr>
				<td class="t_title">院区：</td>
				<td>
					<div class="select_del">
					<input id="unitId" name="unitId" style="width:150px;"/>
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#unitId').combo('clear');"></a>
					</div>
				</td>
			</tr>
			</c:if>
			<div class="select_del"><input id="deptId"  style="width:180px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a></div>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="gm003Ybsj.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">				
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="gm003Ybsj.exportExcelIcu()" ><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
				</div>
			</div>
		</div>
		<div id="gm003YbsjPanel"></div>				
		
		<script type="text/javascript">
			var gm003Ybsj = {
				panel : 'gm003YbsjPanel',
				//查询
				query : function() {
					//if (this.validates()) {
						autoTip.clear();
				        $('#'+gm003Ybsj.panel).datagrid({
				            queryParams: {
								<c:if test="${unitFlag=='1'}">
				            	'unitId': $('#unitId').combobox('getValue'),
								</c:if>
				            	'deptId': $('#deptId').combogrid('getValue'),
				                'startMonthDate': $('#startMonthDate').val(),
				                'endMonthDate': $('#endMonthDate').val()
				            },
				            pageNumber: 1
				        });
					//}
			    },
			    //excel导出
				exportExcelIcu:function (){
					autoTip.clear();
					var url = "${webroot}/gm003Ybsj/f_json/exportExcelIcuByMonth.shtml?deptId="+$('#deptId').combogrid('getValue')+"&unitId="+$('#unitId').combobox('getValue')+"&startMonthDate="+$('#startMonthDate').val()+"&endMonthDate="+$('#endMonthDate').val();
			    	window.location.href = url;
				},
			    //编辑
			    edit:function(startDate,endDate,typeid) {
			        Comm.dialog({
			        	url:"${webroot}/gm004Jcmx/f_view/index.shtml?bizType=1&startDate=" + startDate+"&endDate="+ endDate+"&typeid="+typeid+"&deptid="+$('#deptId').combogrid('getValue')+"&unitId="+$('#unitId').combobox('getValue'),
			            title: "患者列表",
			            width:870,
			            height:550,
			            type:"iframe",
			            parent:this
			        });
			    },
			    //编辑
			    icuList:function(monthDate) {
	            	var unitId ='';
					<c:if test="${unitFlag=='1'}">
	            		unitId = $('#unitId').combobox('getValue');
					</c:if>
	            	var deptId = $('#deptId').combogrid('getValue');
			    	var url='${webroot}/gm003Ybsj/f_view/icuList.shtml?unitId='+unitId+'&deptId='+deptId+'&monthDate='+monthDate;
			    	var current_tab = parent.$('#patient_frame_tabs').tabs('getTab', "监测月报");
			    	parent.$('#patient_frame_tabs').tabs('select', "监测月报");
			    	parent.$('#patient_frame_tabs').tabs('update',{
					     tab: current_tab,
					     options : {
					    	 content : '<iframe  scrolling="yes" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>' 
					    	 //content: '<iframe name="'+_tabId+'"  id="'+_tabId+'" width="100%" height="100%" frameborder="0" src="'+url+'" scrolling="auto"></iframe>'
					     }
					});
			    },
				//必填验证
				validates : function() {
					var re = true;
					if (!$('#deptId').combogrid('isValid')) {
						//$('#deptId').next().find(".textbox-text").focus();
						re = false;
						$.messager.alert('提示','请选择科室!','warning');
					}
					return re;
				}	 	   
			};
			$(document).ready(function () {
				<c:if test="${unitFlag=='1'}">
					Csm.comboBox.unit({
						//【必传】控件名称
						id: 'unitId',
						flag: '1',
						onSelect : function(record) {
							Csm.combogrid.dep({
								//【必传】控件名称
								id: 'deptId',
								ifcaseoffice: '1',
								hospId : record.unitId
							});
						}
					});
				</c:if>
				
				Csm.combogrid.dep({
					//【必传】控件名称
					id: 'deptId',
					value: '${deptId}',
					//required:true,
					ifcaseoffice: '1',
			        onHidePanel : function() {
			        	Csm.valueValite.combogrid('deptId');
					}
				});
				$('#'+gm003Ybsj.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: true,
	                striped: true,
	                fitColumns: false,
	                url:'${webroot}/gm003Ybsj/f_json/pageQuerybyMonth.shtml',
	                queryParams: {
						<c:if test="${unitFlag=='1'}">
		            	'unitId': $('#unitId').combobox('getValue'),
						</c:if>
		            	'deptId': $('#deptId').combogrid('getValue'),
		                'startMonthDate': $('#startMonthDate').val(),
		                'endMonthDate': $('#endMonthDate').val()
		            },
	                remoteSort: false,
	                showFooter: true,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
							{field:'strDate',title:'日期',sortable:true,width:125,align:'center',
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return '合计(已剔除重复)';
									}else{
										return ['<a href="javascript:gm003Ybsj.icuList(\'',r.strDate,'\');">',r.strDate,'</a>'].join('');							
									}
								}
							},
		                    {field:'newCount',title:'新住进患者数',sortable:true,width:125,align:'center',
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return r.newCount;
									}else{
										return ['<a href="javascript:gm003Ybsj.edit(\'',r.startDate,'\',\'',r.endDate,'\',\'01\');">',r.newCount,'</a>'].join('');							
									}
								}
							},
		                    {field:'inCount',title:'在院患者数',sortable:true,width:125,align:'center',
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return r.inCount;
									}else{
										return ['<a href="javascript:gm003Ybsj.edit(\'',r.startDate,'\',\'',r.endDate,'\',\'02\');">',r.inCount,'</a>'].join('');							
									}
								}
		                    },
		                    {field:'frCount',title:'发热人数',sortable:true,width:125,align:'center',
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.frCount;
									}else{
										return ['<a href="javascript:gm003Ybsj.edit(\'',r.startDate,'\',\'',r.endDate,'\',\'07\');">',r.frCount,'</a>'].join('');							
									}
		                    	}
		                    },
		                    {field:'hxjCount',title:'呼吸机插管患者数',sortable:true,width:125,align:'center',                    	
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.hxjCount;
									}else{
										return ['<a href="javascript:gm003Ybsj.edit(\'',r.startDate,'\',\'',r.endDate,'\',\'06\');">',r.hxjCount,'</a>'].join('');							
									}
		                    	}
		                    },
		                    {field:'mndCount',title:'泌尿道插管患者数',sortable:true,width:125,align:'center',
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.mndCount;
									}else{
										return ['<a href="javascript:gm003Ybsj.edit(\'',r.startDate,'\',\'',r.endDate,'\',\'04\');">',r.mndCount,'</a>'].join('');							
									}
		                    	}
		                    },		           
		                    {field:'zxjmCount',title:'中心静脉插管患者数',sortable:true,width:125,align:'center',
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.zxjmCount;
									}else{
										return ['<a href="javascript:gm003Ybsj.edit(\'',r.startDate,'\',\'',r.endDate,'\',\'05\');">',r.zxjmCount,'</a>'].join('');							
									}
		                    	}
		                    }
		                ]
	                ],
	                rownumbers:true,
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>
