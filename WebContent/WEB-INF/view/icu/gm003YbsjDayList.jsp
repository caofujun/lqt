<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>ICU日志</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
	</head>
	<body>
		<div id="tb" class="m_search" style="display: none;">
			<span class="pro_text">选择日期:</span>
			<input type="text"  id="startDate" value="${dayDate}" style="width:80px"  class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />		
		 	<%-- ~ <input type="text"  id="endDate" value="${dayDate}" style="width:80px"  class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />
 --%>		 	
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
			<span class="ml5">科室:</span>
		 	<select id="deptType" class="easyui-combobox" style="width:80px"><option value="">全院</option><option value="icu">ICU</option><option value="nicu">NICU</option><option value="focus">重点科室</option></select>
		 	<input type="hidden" id="unit" value="${unitId}"/>
			<div class="select_del"><input id="deptId"  style="width:180px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a></div>		
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="gm003Ybsj.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
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
					autoTip.clear();
			        $('#'+gm003Ybsj.panel).datagrid({
			            queryParams: {
							<c:if test="${unitFlag=='1'}">
			            	'unitId': $('#unitId').combobox('getValue'),
							</c:if>
			            	'deptId': $('#deptId').combogrid('getValue'),
			                'startDate': $('#startDate').val(),
			                'endDate': $('#startDate').val(),
			                'deptType': $('#deptType').combogrid('getValue')
			            },
			            pageNumber: 1
			        });
			    },
			  //excel导出
				 exportExcelIcu:function (){
					autoTip.clear();
					var url = "${webroot}/gm003Ybsj/f_json/exportExcelDay.shtml?deptId="+$('#deptId').combogrid('getValue')+"&startDate="+$('#startDate').val()+"&endDate="+$('#startDate').val()+"&deptType="+$('#deptType').combogrid('getValue');
					//alert(url);
					window.location.href = url;
				},
			    //编辑
			    edit:function(deptId,typeid) {
			        Comm.dialog({
			        	url:"${webroot}/gm004Jcmx/f_view/index.shtml?bizType=0&startDate=" + $('#startDate').val()+"&endDate=" + $('#startDate').val()+"&typeid="+typeid+"&deptid="+deptId,
			            title: "患者列表",
			            width:870,
			            height:550,
			            type:"iframe",
			            parent:this
			        });
			    }	 	   
			};
			$(document).ready(function () {
				<c:if test="${unitFlag=='1'}">
					Csm.comboBox.unit({
						//【必传】控件名称
						id: 'unitId',
						value:'${unitId}',
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
					hospId:'${unitId}',
					ifcaseoffice: '1'
					
				});
				$('#'+gm003Ybsj.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: true,
	                striped: true,
	                fitColumns: true,
	                url:'${webroot}/gm003Ybsj/f_json/pageQueryDay.shtml',
	                queryParams: {
						<c:if test="${unitFlag=='1'}">
		            	'unitId': $('#unitId').combobox('getValue'),
						</c:if>
		            	'deptId': $('#deptId').combogrid('getValue'),
		                'startDate': $('#startDate').val(),
		                'endDate': $('#startDate').val(),
		                'deptType': $('#deptType').combogrid('getValue')
		            },
	                remoteSort: false,
	                showFooter: true,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
							{field:'deptName',title:'科室',sortable:true,width:125},  
		                    {field:'newCount',title:'新住进患者数',sortable:true,width:125,align:'center',
								formatter:function(value,r){
									if(r.deptName=='合计'){
										return r.newCount;
									}else{
										if(r.newCount>0){
											return ['<a href="javascript:gm003Ybsj.edit(\'',r.deptId,'\',\'01\');">',r.newCount,'</a>'].join('');							
										}else{
											return r.newCount;
										}
									}
								}
							},  
		                    {field:'inCount',title:'当前所在患者数',sortable:true,width:125,align:'center',
								formatter:function(value,r){
									if(r.deptName=='合计'){
										return r.inCount;
									}else{
										if(r.inCount>0){
											return ['<a href="javascript:gm003Ybsj.edit(\'',r.deptId,'\',\'02\');">',r.inCount,'</a>'].join('');							
										}else{
											return r.inCount;
										}
									}
								}
		                    },
		                    {field:'frCount',title:'发热人数',sortable:true,width:125,align:'center',
		                    	formatter:function(value,r){
		                    		if(r.deptName=='合计'){
										return r.frCount;
									}else{
										if(r.frCount>0){
											return ['<a href="javascript:gm003Ybsj.edit(\'',r.deptId,'\',\'07\');">',r.frCount,'</a>'].join('');							
										}else{
											return	r.frCount;
										}
									}
		                    	}
		                    },
		                    {field:'hxjCount',title:'呼吸机插管患者数',sortable:true,width:125,align:'center',                    	
		                    	formatter:function(value,r){
		                    		if(r.deptName=='合计'){
										return r.hxjCount;
									}else{
										if(r.hxjCount>0){
											return ['<a href="javascript:gm003Ybsj.edit(\'',r.deptId,'\',\'06\');">',r.hxjCount,'</a>'].join('');							
										}else{
											return r.hxjCount;
										}
									}
		                    	}
		                    },
		                    {field:'mndCount',title:'泌尿道插管患者数',sortable:true,width:125,align:'center',
		                    	formatter:function(value,r){
		                    		if(r.deptName=='合计'){
										return r.mndCount;
									}else{
										if(r.mndCount>0){
											return ['<a href="javascript:gm003Ybsj.edit(\'',r.deptId,'\',\'04\');">',r.mndCount,'</a>'].join('');							
										}else{
											return r.mndCount;
										}
									}
		                    	}
		                    },		           
		                    {field:'zxjmCount',title:'中心静脉插管患者数',sortable:true,width:125,align:'center',
		                    	formatter:function(value,r){
		                    		if(r.deptName=='合计'){
										return r.zxjmCount;
									}else{
										if(r.zxjmCount>0){
											return ['<a href="javascript:gm003Ybsj.edit(\'',r.deptId,'\',\'05\');">',r.zxjmCount,'</a>'].join('');							
										}else{
											return r.zxjmCount;
										}
									}
		                    	}
		                    },
		                    <c:if test="${ISTSY == 1}">		
			                    {field:'tsyCount',title:'退烧药医嘱患者数',sortable:true,width:125,align:'center',
			                    	formatter:function(value,r){
			                    		if(r.deptName=='合计'){
											return r.tsyCount;
										}else{
											if(r.tsyCount>0){
												return ['<a href="javascript:gm003Ybsj.edit(\'',r.deptId,'\',\'20\');">',r.tsyCount,'</a>'].join('');							
											}else{
												return r.tsyCount;
											}
										}
			                    	}
			                    },
                    		</c:if>
		                    <c:if test="${ISKFJC == 1}">		
			                    {field:'bhksCount',title:'病患咳嗽',sortable:true,width:125,align:'center'},
			                    {field:'bhfxCount',title:'病患腹泻',sortable:true,width:125,align:'center'},
			                    {field:'ywryksCount',title:'医务人员咳嗽',sortable:true,width:125,align:'center'},
			                    {field:'ywryfxCount',title:'医务人员腹泻',sortable:true,width:125,align:'center'},
			                    {field:'ywryfrCount',title:'医务人员发热',sortable:true,width:125,align:'center'}
		                    </c:if>
		                   
		                ]
	                ],
	                rownumbers:true,
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>
