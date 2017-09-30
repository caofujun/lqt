<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>ICU日志</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="tb">
			<div class="m_search">
				<div class="m_search_first">
					<span class="pro_text">选择月份：</span>
					<input type="text"  id="monthDate" value="${monthDate}" style="width:80px"  class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  />		
			 		<div class="n_btn_blue">
						<a href="javascript:;" onclick="gm004Jcmx.query('')"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
					</div>
			 	</div>
			 	<div class="m_search_first">
					<a id="icu" href="javascript:gm004Jcmx.chooseHref('${webroot}/gm003Ybsj/f_view/index.shtml?monthDate=')">ICU科室</a>
					<a class="cur" id="nicu" href="javascript:gm004Jcmx.chooseHref('${webroot}/gm004Jcmx/f_view/nicuCount.shtml?monthDate=')">NICU科室</a>
				</div>
				<div class="m_search_m">
					<c:forEach items="${nicuList}" var="nicu" varStatus="st">		 			
			 			<a  <c:if test="${st.index==0}">class="cur"</c:if> href="javascript:;" onClick="gm004Jcmx.chooseDept('${nicu.deptId}',this)">${nicu.deptName}</a>
			 		</c:forEach>
				</div>			 	
			</div>
			<div class="Rep_header">
				<div class="Rep_h_t">NICU医院感染监测月报</div>
				<div class="Rep_h_note">
					<span>监测月：<span id="month"></span></span>
					<span>监测科室：<span id="deptName"></span></span>
					<div class="Rep_h_n_btn">
						<a class="ico_no"  href="javascript:;" onclick="gm004Jcmx.exportExcelIcu()" title="导出"><i class="icon iconfont">&#xe628;</i></a>
						<a class="ico_no" href="javascript:;" title="打印"><i class="icon iconfont">&#xe604;</i></a>
						<a class="ico_no"  href="javascript:;" onclick="gm004Jcmx.editNewbornWeight();" title="维护新生儿体重"><i class="icon iconfont fax">&#xe601;</i></a>
					</div>
				</div>
			</div>
		</div>
		<div id="gm004JcmxPanel"></div>	
		<script type="text/javascript">
			var allDeptId = '${deptId}';
			var gm004Jcmx = {
				panel : 'gm004JcmxPanel',
				//查询
				query : function(deptId) {
					if(deptId==''){
						deptId = allDeptId;
					}else{
						allDeptId = deptId;
					}
					$('#month').html($('#monthDate').val());
					$('#deptName').html($('.m_search_m').find(".cur").html());
					autoTip.clear();
			        $('#'+gm004Jcmx.panel).datagrid({
			            queryParams: {
			            	'deptId': deptId,
			                'monthDate': $('#monthDate').val()
			            },
			            pageNumber: 1
			        });
			    },
			  //excel导出
				 exportExcelIcu:function (){
					autoTip.clear();
					var url = "${webroot}/gm004Jcmx/f_json/exportExcelNicu.shtml?deptId="+allDeptId+"&monthDate="+$('#monthDate').val();
			    	window.location.href = url;
				},
			    //编辑
			    edit:function(strDate,typeid,neonatebw) {
			        Comm.dialog({
			        	url:"${webroot}/gm004Jcmx/f_view/index.shtml?bizType=0&startDate=" + strDate+"&endDate="+ strDate +"&neonatebw="+neonatebw+"&typeid="+typeid+"&deptid="+allDeptId,
			            title: "患者列表",
			            width:870,
			            height:500,
			            type:"iframe",
			            parent:this
			        });
			    },
			    //维护新生儿体重
			    editNewbornWeight : function() {
			    	Comm.dialogGlobal({
			    		url:"${webroot}/st003Cryxxb/f_view/toHighRiskNewbornList.shtml",
			            title: "高危新生儿体重维护",
			            width:970,
			            height:500,
			            type:"iframe",
			            parent:this,
			            modal: false
			        });
			    },
			    chooseHref:function(href){
			    	location.href = href+$('#monthDate').val();
			    },
			    //选择科室
			    chooseDept:function(deptId,_this){
			    	$('.m_search_m').find("a").attr("class","");
			    	$(_this).attr("class","cur");
			    	gm004Jcmx.query(deptId);
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
				/* Csm.combogrid.dep({
					//【必传】控件名称
					id: 'deptId',
					value: '${deptId}',
					hospId:'${unitId}',
					required:true,
					ifchildoffice: '1',
			        onHidePanel : function() {
			        	Csm.valueValite.combogrid('deptId');
					}
				}); */
				$('#month').html($('#monthDate').val());
				$('#deptName').html($('.m_search_m').find(".cur").html());
				$('#'+gm004Jcmx.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                collapsible:true,
	                url:'${webroot}/gm004Jcmx/f_json/findNicuCount.shtml',
	                queryParams: {
		            	'deptId': '${deptId}',
		                'monthDate': $('#monthDate').val()
		            },
	                remoteSort: false,
	                showFooter: true,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
							{field:'strDate',title:'日期',sortable:true,width:80,rowspan:2},
							{title:'BW<=1000g',colspan:4},
							{title:'BW1001g~1500g',colspan:4},
							{title:'BW1501g~2500g',colspan:4},
							{title:'BW>2500g',colspan:4}],
						[	
		                    {field:'newCount1',title:'新入新生儿数',sortable:true,width:80,
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return r.newCount1;
									}else{
										if(r.newCount1>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'11\',\'01\');">',r.newCount1,'</a>'].join('');							
										}else{
											return r.newCount1;
										}
									}
								}
							},  
		                    {field:'inCount1',title:'已住新生儿数',sortable:true,width:80,
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return r.inCount1;
									}else{
										if(r.inCount1>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'02\',\'01\');">',r.inCount1,'</a>'].join('');							
										}else{
											return r.inCount1;
										}
									}
								}
		                    },
		                    {field:'zxjmCount1',title:'脐/中心静脉',sortable:true,width:80,
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.zxjmCount1;
									}else{
										if(r.zxjmCount1>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'05\',\'01\');">',r.zxjmCount1,'</a>'].join('');							
										}else{
											return r.zxjmCount1;
										}
									}
		                    	}
		                    },
		                    {field:'hxjCount1',title:'使用呼吸机数',sortable:true,width:80,	                    	
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.hxjCount1;
									}else{
										if(r.hxjCount1>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'06\',\'01\');">',r.hxjCount1,'</a>'].join('');							
										}else{
											return r.hxjCount1;
										}
									}
		                    	}
		                    },
		                    	                    
		                    {field:'newCount2',title:'新入新生儿数',sortable:true,width:80,
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return r.newCount2;
									}else{
										if(r.newCount2>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'11\',\'02\');">',r.newCount2,'</a>'].join('');							
										}else{
											return r.newCount2;
										}
									}
								}
							},  
		                    {field:'inCount2',title:'已住新生儿数',sortable:true,width:80,
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return r.inCount2;
									}else{
										if(r.inCount2>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'02\',\'02\');">',r.inCount2,'</a>'].join('');							
										}else{
											return r.inCount2;
										}
									}
								}
		                    },
		                    {field:'zxjmCount2',title:'脐/中心静脉',sortable:true,width:80,
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.zxjmCount2;
									}else{
										if(r.zxjmCount2>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'05\',\'02\');">',r.zxjmCount2,'</a>'].join('');							
										}else{
											return r.zxjmCount2;
										}
									}
		                    	}
		                    },
		                    {field:'hxjCount2',title:'使用呼吸机数',sortable:true,width:80,	                    	
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.hxjCount2;
									}else{
										if(r.hxjCount2>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'06\',\'02\');">',r.hxjCount2,'</a>'].join('');							
										}else{
											return r.hxjCount2;
										}
									}
		                    	}
		                    },
		                    {field:'newCount3',title:'新入新生儿数',sortable:true,width:80,
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return r.newCount3;
									}else{
										if(r.newCount3>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'11\',\'03\');">',r.newCount3,'</a>'].join('');							
										}else{
											return r.newCount3;
										}
									}
								}
							},  
		                    {field:'inCount3',title:'已住新生儿数',sortable:true,width:80,
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return r.inCount3;
									}else{
										if(r.inCount3>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'02\',\'03\');">',r.inCount3,'</a>'].join('');							
										}else{
											
											return r.inCount3;
										}
									}
								}
		                    },
		                    {field:'zxjmCount3',title:'脐/中心静脉',sortable:true,width:80,
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.zxjmCount3;
									}else{
										if(r.zxjmCount3>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'05\',\'03\');">',r.zxjmCount3,'</a>'].join('');							
										}else{
											return r.zxjmCount3;
										}
									}
		                    	}
		                    },
		                    {field:'hxjCount3',title:'使用呼吸机数',sortable:true,width:80,	                    	
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.hxjCount3;
									}else{
										if(r.hxjCount3>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'06\',\'03\');">',r.hxjCount3,'</a>'].join('');							
										}else{
											return r.hxjCount3;
										}
									}
		                    	}
		                    },
		                    
		                    {field:'newCount4',title:'新入新生儿数',sortable:true,width:80,
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return r.newCount4;
									}else{
										if(r.newCount4>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'11\',\'04\');">',r.newCount4,'</a>'].join('');							
										}else{
											return r.newCount4;
										}
									}
								}
							},  
		                    {field:'inCount4',title:'已住新生儿数',sortable:true,width:80,
								formatter:function(value,r){
									if(r.strDate=='合计'){
										return r.inCount4;
									}else{
										if(r.inCount4>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'02\',\'04\');">',r.inCount4,'</a>'].join('');							
										}else{
											return r.inCount4;
										}
									}
								}
		                    },
		                    {field:'zxjmCount4',title:'脐/中心静脉',sortable:true,width:80,
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.zxjmCount4;
									}else{
										if(r.zxjmCount4>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'05\',\'04\');">',r.zxjmCount4,'</a>'].join('');							
										}else{
											return r.zxjmCount4;
										}
									}
		                    	}
		                    },
		                    {field:'hxjCount4',title:'使用呼吸机数',sortable:true,width:80,	                    	
		                    	formatter:function(value,r){
		                    		if(r.strDate=='合计'){
										return r.hxjCount4;
									}else{
										if(r.hxjCount4>0){
											return ['<a href="javascript:gm004Jcmx.edit(\'',r.strDate,'\',\'06\',\'04\');">',r.hxjCount4,'</a>'].join('');							
										}else{
											return r.hxjCount4;
										}
									}
		                    	}
		                    }, 
		                ]
	                ],
	                toolbar:'#tb',
	            });
			});
		</script>
	</body>
</html>
