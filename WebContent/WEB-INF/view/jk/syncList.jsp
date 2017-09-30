<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>中间库业务同步</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/zeroclipboard-2.2.0/ZeroClipboard.js"></script>
	<style>
		.easyui-combobox{width:75px;}
		.table_cx .easyui-combobox{width:150px;}
	</style>
</head>
<body class="easyui-layout">
<div data-options="region:'west',border:false,title:'中间库同步表范围'" style="width:230px;">
	<div class="easyui-layout" data-options="fit:true">              
		<div data-options="region:'center',border:false">
			<table class="table_cx" cellpadding="0" cellspacing="0">
				<tbody>	
					<tr>
						<td colspan="2" height="30">选择需同步的表(<a href="javascript:;" onclick="jkSync.setSyncTask()">设置同步定时任务</a>)</td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_DIC_OFFICE" name="tables" value="JK_DIC_OFFICE"/></td>
						<td><label for="JK_DIC_OFFICE">科室信息表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_DIC_DOCTOR" name="tables" value="JK_DIC_DOCTOR"/></td>
						<td><label for="JK_DIC_DOCTOR">医生信息表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_DIC_DISEASE" name="tables" value="JK_DIC_DISEASE"/></td>
						<td><label for="JK_DIC_DISEASE">诊断字典表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_DIC_DRUG" name="tables" value="JK_DIC_DRUG"/></td>
						<td><label for="JK_DIC_DRUG">抗菌药物字典表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_CLINIC" name="tables" value="JK_PATIENT_CLINIC"/></td>
						<td><label for="JK_PATIENT_CLINIC">患者门诊信息表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_VISIT" name="tables" value="JK_PATIENT_VISIT"/></td>
						<td><label for="JK_PATIENT_VISIT">患者入出院信息表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_ZKMX" name="tables" value="JK_PATIENT_ZKMX"/></td>
						<td><label for="JK_PATIENT_ZKMX">患者转科表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_DIAGNOSE" name="tables" value="JK_PATIENT_DIAGNOSE"/></td>
						<td><label for="JK_PATIENT_DIAGNOSE">患者诊断信息表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_TEMPRATURE" name="tables" value="JK_PATIENT_TEMPRATURE"/></td>
						<td><label for="JK_PATIENT_TEMPRATURE">患者体温信息表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_ROUTINE" name="tables" value="JK_PATIENT_ROUTINE"/></td>
						<td><label for="JK_PATIENT_ROUTINE">患者大便记录表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_YX" name="tables" value="JK_PATIENT_YX"/></td>
						<td><label for="JK_PATIENT_YX">患者影像信息表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_OPERATION" name="tables" value="JK_PATIENT_OPERATION"/></td>
						<td><label for="JK_PATIENT_OPERATION">患者手术信息表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_BC" name="tables" value="JK_PATIENT_BC"/></td>
						<td><label for="JK_PATIENT_BC">患者病程信息表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_YZ" name="tables" value="JK_PATIENT_YZ"/></td>
						<td><label for="JK_PATIENT_YZ">患者医嘱信息表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_LAB_ITEMS" name="tables" value="JK_PATIENT_LAB_ITEMS"/></td>
						<td><label for="JK_PATIENT_LAB_ITEMS">患者检验记录表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_LAB_PATHO" name="tables" value="JK_PATIENT_LAB_ANTI"/></td>
						<td><label for="JK_PATIENT_LAB_PATHO">患者检验结果表</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="JK_PATIENT_LAB_ANTI" name="tables" value="JK_PATIENT_LAB_PATHO"/></td>
						<td><label for="JK_PATIENT_LAB_ANTI">患者检验病原体表</label></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div data-options="region:'south',border:false" style="height:50px; border-top-width:1px;">	         		
			<div class="btn_center">
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="jkSync.syncData()" ><span>立即同步</span></a>
				</div>		
			</div>				
		</div>
	</div>
</div>
<div data-options="region:'center',border:false" style="border-left-width: 1px;">
	<div id="syncPanel"></div>
</div>	
<script type="text/javascript">
	var jkSync = {
		panel : 'syncPanel',
		//查询
		query : function() {
			autoTip.clear();
	        $('#'+jkSync.panel).datagrid({
	            queryParams: {
	            },
	            pageNumber: 1
	        });
	    },
		//设置同步任务
		setSyncTask : function() {
			parent.menuInfo.clickMenu('定时任务管理','/taskProject/f_view/manager.shtml',true,'A9905');
	    },
		//查看日志
		log : function(syncTime, title) {
	        Comm.dialogGlobal({
	        	url:"${webroot}/jk/f_view/syncLogList.shtml?syncTime=" + syncTime,
	            title: title,
	            width:800,
	            height:600,
	            type:'iframe',
	            parent:this
	        });
	    },
		//手工执行同步
	    syncData : function(){
			var tables = '';
    		$("input:checkbox[name='tables']:checked").each(function(){ 
    			tables += $(this).val() + ',';
   			});
    		if(tables === ''){
    			alert("请选择需要同步的中间库表!");
    			return ;
    		}
	        Comm.dialogGlobal({
	        	url:"${webroot}/jk/f_view/sync.shtml?tables="+tables,
	            title: "同步中间库数据",
	            width:600,
	            height:600,
	            type:'iframe',
	            parent:this
	        });
	    	/*$.messager.confirm('提示', '数据同步需要较长时间且不能终止，确实要同步吗?', function (r) {
	        	if (r) {
			    	$.messager.progress({ // 显示进度条  
			    		title:"中间库数据同步",  
			 	   		text:"正在处理...",  
			 	    	interval:100  
			 	    });
					$.ajax({
						url : '${webroot}/jk/f_json/syncJkData.shtml?tables='+tables,
						type:"POST",
			            timeout: 18000000, 
						success:function(data){
			            	$.messager.progress('close');
		                    $.messager.show({ title: '提示', msg: '中间库数据同步操作完成！' });
		                    jkSync.query();
						},
						error:function(){
			            	$.messager.progress('close');
		                    $.messager.show({ title: '提示', msg: '中间库数据同步操作失败！' });
		                    jkSync.query();
						}
					});
	        	}
	    	});*/
	    }
	};

	$(document).ready(function () { 
		$('#'+jkSync.panel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            fitColumns: true,
            collapsible:true,
            url:'${webroot}/jk/f_json/pageQuery.shtml',   
            remoteSort: false,
            singleSelect: true,
            border:false,
            columns:[
            	[
                    {field:'syncTime',title:'日期',sortable:true,width:120},
                    {field:'bizType',title:'业务类型',sortable:true,width:600},
                    {field:'_operate',title:'错误日志',width:50,
						formatter:function(value,r){
							if(r.syncStatus == '0'){
								return ['正常'].join('');
							}else{
								return ['<a href="javascript:jkSync.log(\'',$.trim(r.syncTime),'\',\'查看日志\');" class="ico_editor" title="查看"></a>'].join('');
							}
						}
					}
                ]
            ],
            pagination:true,
            rownumbers:true,
            toolbar:'#tb'
        });
	});
</script>
</body>
</html>
