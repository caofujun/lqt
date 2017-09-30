<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>感染因素</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div class="easyui-panel" data-options="fit: true,border:false">
		<div id="tb_hasRuleOut" class="m_search">
	   		<span class="pro_text" style="padding-left: 10px;">感染科室：</span>
	   		<div class="select_del"><input id="id_infectDept_r" name="infectDeptId"/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_infectDept_r').combogrid('clear');"></a></div>
	   		<!-- <input type="text" id="id_infectDeptName" class="auto-tip" data-tip="科室" />--> 
	   		<span class="pro_text" style="padding-left: 10px;">操作日期：</span>
	   		<input type="text" id="id_operate_start" value="${queryStartDate}" onclick="WdatePicker()" class="Wdate text" style="width:80px;">~
			<input type="text" id="id_operate_end" value="${queryEndTime}" onclick="WdatePicker()" class="Wdate text" style="width:80px;">
			<input type="text" id="id_patient_info_r" class="auto-tip" data-tip="${patientZyTitle}/姓名" title="${patientZyTitle}/姓名" style="width: 100px;" />
	   		<div class="n_btn_blue">
				<a href="javascript:;" onclick="hasRuleOut.query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>	   		
	   	</div>
	    <div id="hasRuleOutPanel"></div>
	</div>
<script type="text/javascript">
	var hasRuleOut = {
		panel : 'hasRuleOutPanel',
		query : function() {
			autoTip.clear();
			$('#' + hasRuleOut.panel).datagrid({
	            url: '${webroot}/gr002YsgrMx/f_json/findHasRuleOut.shtml',
	            queryParams: {
	            	'queryStartDate':$('#id_operate_start').val(),
	            	'queryEndDate':$('#id_operate_end').val() == ''?'':$('#id_operate_end').val()+' 23:59:59',
	            	'deptId':$('#id_infectDept_r').combogrid('getValue'),
	            	'searchString':$("#id_patient_info_r").val()
	            },
	            pageNumber : 1
	        });
		},
		gethMdDateStr : function(val,row) {
			if (val && val.length > 10) {
				return val.substring(0,10);
			} else {
				return '';
			}
		},
	    //确认
	    confirm : function(regId) {
	    	parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&regId=' + regId,true);
	    },
	    showDetail:function(zyid){
	    	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
	    }
	};

	$(function(){
		//感染科室
		Csm.combogrid.dep({
			id: 'id_infectDept_r',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
			ifcaseoffice: '1'
		});
		
		$('#' + hasRuleOut.panel).datagrid({
			fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url: '${webroot}/gr002YsgrMx/f_json/findHasRuleOut.shtml',
	        queryParams: {
            	'queryStartDate':$('#id_operate_start').val(),
            	'queryEndDate':$('#id_operate_end').val() == ''?'':$('#id_operate_end').val()+' 23:59:59'
            },
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        columns:[
		       	[
		            {field:'zyid',title:'${patientZyTitle}',sortable:true,width:80,
						formatter:function(value,r){
							return ['<a href="javascript:hasRuleOut.showDetail(\'',r.zyid,'\',\'\');">',r.zyid,'</a>'].join('');
						}
					}
		            {field:'patientName',title:'姓名',sortable:true,width:50},
		            {field:'age',title:'年龄',sortable:true,align:'center',width:35},
		            {field:'sex',title:'性别',sortable:true,align:'center',width:30},
		            {field:'infectName',title:'感染诊断',sortable:true,width:100},
		            {field:'reportTypeName',title:'来源',sortable:true,align:'center',width:60},
		            {field:'startAt',title:'感染日期',sortable:true,align:'center',width:80,formatter:hasRuleOut.gethMdDateStr},
		            {field:'infectTypeName',title:'感染类型',sortable:true,align:'center',width:60},
		            {field:'deptName',title:'感染科室',sortable:true,width:100},
		            {field:'reportDrName',title:'上报人',sortable:true,width:50},
		            {field:'reportAt',title:'上报日期',sortable:true,align:'center',width:80,formatter:hasRuleOut.gethMdDateStr},
		            {field:'operator',title:'操作人',sortable:true,width:50},
		            {field:'lastoperDate',title:'操作时间',sortable:true,align:'center',width:120},
		            {field:'remark',title:'排除原因',sortable:true,width:90},
		            {field:'_operate',title:'操作',sortable:true,width:35,
    					formatter:function(value,row,index){
    						return ['<a href="javascript:void(0);" onclick="hasRuleOut.confirm(\'' + row.regId + '\');" class="ico_editor" title="重新确认"></a>'].join('');
    					}
    				}
		        ]
	        ],
	        pagination:true,
	        rownumbers:true,
	        toolbar:'#tb_hasRuleOut'
	   });
	});
</script>
</body>
</html>
