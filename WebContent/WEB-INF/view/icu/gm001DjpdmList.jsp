<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   	<title>ICU临床病情等级评定</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:300px;border-bottom-width: 1px;">
	<div id="gm001DjpdmPanel"></div>
	<div id="tb">
		<div class="m_search">
			<div class="m_search_first">
				<span class="pro_text">选择月份：</span>
				<input type="text"  id="monthDate" value="${monthDate}" style="width:80px"  class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  />		
		 		<div class="n_btn_blue">
					<a href="javascript:;" onclick="gm001Djpdm.query('0','')" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
				</div>
		 	</div>
		 	<c:if test="${roleType=='hospital'}">
		 	<div class="m_search_first">
		 		
				<a <c:if test="${param.ifChildIcu==null}">class="cur"</c:if> id="icu" href="javascript:gm001Djpdm.chooseHref('${webroot}/gm001Djpdm/f_view/index.shtml?monthDate=')">ICU科室</a>
				<a <c:if test="${param.ifChildIcu=='1'}">class="cur"</c:if> id="nicu" href="javascript:gm001Djpdm.chooseHref('${webroot}/gm001Djpdm/f_view/index.shtml?ifChildIcu=1&monthDate=')">NICU科室</a>
			</div>
			<div class="m_search_m">
		 		<c:forEach items="${icuList}" var="icu" varStatus="st">		 			
		 			<a  <c:if test="${st.index==0}">class="cur"</c:if> href="javascript:;" onClick="gm001Djpdm.chooseDept('${icu.deptId}',this)">${icu.deptName}</a>
		 		</c:forEach>	 		
			 </div>
			</c:if>
			<c:if test="${roleType=='clinical'}">
			</c:if>			
		</div>
		<div class="Rep_header">			
			<div class="Rep_h_note" style="text-align: left; margin-left: 5px;">
				<span>监测月：<span id="month"></span></span>
				<span>监测科室：<span id="deptName"></span></span>
				<span>临床病情等级评估表</span>
				<div class="Rep_h_n_btn" style="top:3px;">
					<a class="ico_no"  href="javascript:;" onclick="gm001Djpdm.exportExcelDjpdm()"  title="导出"><i class="icon iconfont">&#xe628;</i></a>
					<a class="ico_no" href="javascript:;" title="打印"><i class="icon iconfont">&#xe604;</i></a>
				</div>
			</div>
		</div>
	</div>
</div>
<div data-options="region:'center',border:false">
		<iframe id="gm002DjpddIframe" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
	</div>
	<script type="text/javascript">
		function seturl(strDate,enddt,dtYear,dtMonth,weeknumber){
			var url="${webroot}/gm002Djpdd/f_view/index.shtml?deptId="+allDeptId+"&strDate="+strDate+"&enddt="+enddt+"&dtYear="+dtYear+"&dtMonth="+dtMonth+"&weeknumber="+weeknumber;
		    $("#gm002DjpddIframe").attr("src",url);
		}
		var parentTips= 0;
		var allDeptId = '${deptId}';
		var gm001Djpdm = {
			panel : 'gm001DjpdmPanel',
			
			//查询
			query : function(index,deptId) {
				if(deptId==''){
					deptId = allDeptId;
				}else{
					allDeptId = deptId;
				}
				$('#month').html($('#monthDate').val());
				$('#deptName').html($('.m_search_m').find(".cur").html());
		        $('#'+gm001Djpdm.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/gm001Djpdm/f_json/pageQuery.shtml',
	                queryParams: {
		            	'deptId': deptId,
		                'monthDate': $('#monthDate').val()
		            },
	                remoteSort: false,
	                showFooter: true,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
	                	 	{field:'weeknumber',title:'周数',sortable:true,width:10},  
		                    {field:'startdt',title:'开始时间',sortable:true,width:25},
		                    {field:'enddt',title:'结束时间',sortable:true,width:25},
		                    {field:'acount',title:'A',sortable:true,width:15},		           
							{field:'bcount',title:'B',sortable:true,width:15},
							{field:'ccount',title:'C',sortable:true,width:15},
							{field:'dcount',title:'D',sortable:true,width:15},
							{field:'ecount',title:'E',sortable:true,width:15},
							{field:'sumscore',title:'合计',sortable:true,width:15},
							{field:'avgscore',title:'平均等级',sortable:true,width:20},
							{field:'adjustername',title:'评定人',sortable:true,width:20},
							{field:'evaluatedt',title:'评定时间',sortable:true,width:20},
							{field:'noScore',title:'未评分人数',sortable:true,width:20}
		                ]
	                ],
	                toolbar:'#tb',
	                onLoadSuccess: function(data) {
	                	if (data && data.total > 0) {
	                		$('#'+gm001Djpdm.panel).datagrid('selectRow', index);
		                	var curRow = $('#'+gm001Djpdm.panel).datagrid('getSelected');
		                	seturl(curRow.startdt,curRow.enddt,curRow.dtYear,curRow.dtMonth,curRow.weeknumber);
	                	}
	                },
	                onClickRow:function(rowIndex, rowData){
	                	if(parentTips==0){
	                		
	                	}else{
	                		 var close = confirm("还未保存，确定保存？");  if (close) {
	                			 $("#gm002DjpddIframe")[0].contentWindow.gm002Djpdd.saveList();
	                		 }
	                	}
	                	seturl(rowData.startdt,rowData.enddt,rowData.dtYear,rowData.dtMonth,rowData.weeknumber);
			    	}
		        });
		    },
		    refresh : function(index){
		    	$('#'+gm001Djpdm.panel).datagrid({
		    		queryParams: {
		            	'deptId': allDeptId,
		                'monthDate': $('#monthDate').val()
		            },
		            onLoadSuccess: function() {
	                	$('#'+gm001Djpdm.panel).datagrid('selectRow', index);
	                }
		    	});
		    },
		    chooseHref:function(href,_this){
		    	location.href = href+$('#monthDate').val();
		    },
		    //选择科室
		    chooseDept:function(deptId,_this){
		    	$('.m_search_m').find("a").attr("class","");
		    	$(_this).attr("class","cur");
		    	gm001Djpdm.query('0',deptId);
		    },
		  //excel导出
			exportExcelDjpdm:function (){
				autoTip.clear();
				var url = "${webroot}/gm001Djpdm/f_json/exportExcelDjpdm.shtml?deptId="+allDeptId+"&monthDate="+$('#monthDate').val();
		    	window.location.href = url;
			},
			/* //必填验证
			validates : function() {
				var re = true;
				if (!$('#deptId').combogrid('isValid')) {
					//$('#deptId').next().find(".textbox-text").focus();
					re = false;
					$('#'+gm001Djpdm.panel).datagrid('loadData',{total:0,rows:[]});
					$.messager.alert('提示','请选择科室!','warning');
				}
				return re;
			} */
		};
		$(document).ready(function () {
			/* Csm.combogrid.dep({
				//【必传】控件名称
				id: 'deptId',
				value: '${deptId}',
				hospId:'${unitId}',
				required:true,
				ificu: '1',
		        onHidePanel : function() {
		        	Csm.valueValite.combogrid('deptId');
				}
			}); */
			gm001Djpdm.query('0','');
		});
	</script>
</body>
</html>