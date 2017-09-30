<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>ICU日志</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<style>
/*.Rep_header{text-align:left;}
.Rep_header .Rep_h_t {margin-left:385px;}
.Rep_header .Rep_h_note {margin-left:350px;}*/
</style>
	</head>
	<body>
		<div id="tb">
			<div class="m_search">
				<div class="m_search_first">
					<span class="pro_text">选择月份：</span>
					<input type="text"  id="monthDate" value="${monthDate}" style="width:80px"  class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  />		
			 		<div class="n_btn_blue">
						<a href="javascript:;" onclick="gm003Ybsj.query('')"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
					</div>
			 	</div>
			 	<div class="m_search_first">
					<a class="cur" id="icu" href="javascript:gm003Ybsj.chooseHref('${webroot}/gm003Ybsj/f_view/index.shtml?monthDate=')">ICU科室</a>
					<a id="nicu" href="javascript:gm003Ybsj.chooseHref('${webroot}/gm004Jcmx/f_view/nicuCount.shtml?monthDate=')">NICU科室</a>
				</div>
			 	<div class="m_search_m">
			 		<c:forEach items="${icuList}" var="icu" varStatus="st">		 			
			 			<a  <c:if test="${st.index==0}">class="cur"</c:if> href="javascript:;" onClick="gm003Ybsj.chooseDept('${icu.deptId}',this)">${icu.deptName}</a>
			 		</c:forEach>
			 	</div>
			 </div>			 	
			<div class="Rep_header">
				<div class="Rep_h_t">ICU医院感染监测月报</div>
				<div class="Rep_h_note">
					<span>监测月：<span id="month"></span></span>
					<span>监测科室：<span id="deptName"></span></span>
					<div class="Rep_h_n_btn">
						<a class="ico_no"  href="javascript:;" onclick="gm003Ybsj.exportExcelIcu()"  title="导出"><i class="icon iconfont">&#xe628;</i></a>
						<a class="ico_no" href="javascript:;" title="打印"><i class="icon iconfont">&#xe604;</i></a>
					</div>
				</div>
			</div>						
		</div>
		<div id="gm003YbsjPanel"></div>				
		
		<script type="text/javascript">
			var allDeptId = '${deptId}';
			var gm003Ybsj = {
				panel : 'gm003YbsjPanel',
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
			        $('#'+gm003Ybsj.panel).datagrid({
			            queryParams: {
			            	'deptId': deptId,
			            	'bedicu':'${bedicu}',
			                'monthDate': $('#monthDate').val()
			            },
			            pageNumber: 1
			        });
			    },
			     //excel导出
				 exportExcelIcu:function (){
					autoTip.clear();
					var url = "${webroot}/gm003Ybsj/f_json/exportExcelIcu.shtml?deptId="+allDeptId+"&monthDate="+$('#monthDate').val();
			    	window.location.href = url;
				},
			    //编辑
			    edit:function(strDate,typeid) {
			        Comm.dialog({
			        	url:"${webroot}/gm004Jcmx/f_view/index.shtml?bizType=0&startDate=" + strDate+"&endDate="+ strDate +"&typeid="+typeid+"&deptid="+allDeptId+"&bedicu=${bedicu}",
			            title: "患者列表",
			            width:870,
			            height:550,
			            type:"iframe",
			            parent:this
			        });
			    },
			    chooseHref:function(href){
			    	location.href = href+$('#monthDate').val();
			    },
			    //选择科室
			    chooseDept:function(deptId,_this){
			    	$('.m_search_m').find("a").attr("class","");
			    	$(_this).attr("class","cur");
			    	gm003Ybsj.query(deptId);
			    }
			};
			$(document).ready(function () {
				/* Csm.combogrid.dep({
					//【必传】控件名称
					id: 'deptId',
					value: '${deptId}',
					hospId:'${unitId}',
					required:true,
					${param.type == 'month' ? 'ifcaseoffice: "1"' : 'ificu: "1"'},
			        onHidePanel : function() {
			        	Csm.valueValite.combogrid('deptId');
					}
				}); */
				$('#month').html($('#monthDate').val());
				$('#deptName').html($('.m_search_m').find(".cur").html());
				if('${deptId}'!=''){
					$('#'+gm003Ybsj.panel).datagrid({
		                fit: true,
		                nowrap: true,
		                autoRowHeight: true,
		                striped: true,
		                fitColumns: true,
		                url:'${webroot}/gm003Ybsj/f_json/pageQuery.shtml',
		                queryParams: {
			            	'deptId': '${deptId}',
			            	'bedicu':'${bedicu}',
			                'monthDate': $('#monthDate').val()
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
											return r.strDate;
// 											return ['<a href="javascript:gm003Ybsj.dayList(\'',r.strDate,'\');">',r.strDate,'</a>'].join('');							
										}
									}	
								},  
			                    {field:'newCount',title:'新住进患者数',sortable:true,width:125,align:'center',
									formatter:function(value,r){
										if(r.strDate=='合计'){
											return r.newCount;
										}else{
											if(r.newCount>0){
												return ['<a href="javascript:gm003Ybsj.edit(\'',r.strDate,'\',\'01\');">',r.newCount,'</a>'].join('');							
											}else{
												return r.newCount;
											}
										}
									}
								},  
			                    {field:'inCount',title:'当前所在患者数',sortable:true,width:125,align:'center',
									formatter:function(value,r){
										if(r.strDate=='合计'){
											return r.inCount;
										}else{
											if(r.inCount>0){
												return ['<a href="javascript:gm003Ybsj.edit(\'',r.strDate,'\',\'02\');">',r.inCount,'</a>'].join('');							
											}else{
												return r.inCount;
											}
										}
									}
			                    },
			                    {field:'frCount',title:'发热人数',sortable:true,width:125,align:'center',
			                    	formatter:function(value,r){
			                    		if(r.strDate=='合计'){
											return r.frCount;
										}else{
											if(r.frCount>0){
												return ['<a href="javascript:gm003Ybsj.edit(\'',r.strDate,'\',\'07\');">',r.frCount,'</a>'].join('');							
											}else{
												return r.frCount;
											}
										}
			                    	}
			                    },
			                    {field:'hxjCount',title:'呼吸机插管患者数',sortable:true,width:125,align:'center',                    	
			                    	formatter:function(value,r){
			                    		if(r.strDate=='合计'){
											return r.hxjCount;
										}else{
											if(r.hxjCount>0){
												return ['<a href="javascript:gm003Ybsj.edit(\'',r.strDate,'\',\'06\');">',r.hxjCount,'</a>'].join('');							
											}else{
												return r.hxjCount;
											}
										}
			                    	}
			                    },
			                    {field:'mndCount',title:'泌尿道插管患者数',sortable:true,width:125,align:'center',
			                    	formatter:function(value,r){
			                    		if(r.strDate=='合计'){
											return r.mndCount;
										}else{
											if(r.mndCount>0){
												return ['<a href="javascript:gm003Ybsj.edit(\'',r.strDate,'\',\'04\');">',r.mndCount,'</a>'].join('');							
											}else{
												return r.mndCount;
											}
										}
			                    	}
			                    },		           
			                    {field:'zxjmCount',title:'中心静脉插管患者数',sortable:true,width:125,align:'center',
			                    	formatter:function(value,r){
			                    		if(r.strDate=='合计'){
											return r.zxjmCount;
										}else{
											if(r.zxjmCount>0){
												return ['<a href="javascript:gm003Ybsj.edit(\'',r.strDate,'\',\'05\');">',r.zxjmCount,'</a>'].join('');							
											}else{
												return r.zxjmCount;
											}
										}
			                    	}
			                    }
			                ]
		                ],
		                rownumbers:true,
		                toolbar:'#tb'
		            });
				}
				
			});
		</script>
	</body>
</html>
