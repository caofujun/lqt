<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>已确认感染</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<div class="easyui-panel" data-options="fit: true,border:false">
		<div class="m_search" id="tb_confirm_info">
	   		<span class="pro_text" style="padding-left: 10px;">感染科室：</span>
	   		<div class="select_del"><input id="id_infectDept_c" name="infectDeptId"/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_infectDept_c').combogrid('clear');"></a></div>
	   		<!-- <input type="text" id="id_infectDeptName" class="auto-tip" data-tip="科室"  />--> 
			<span class="pro_text" style="padding-left: 10px;">感染日期：</span>
			<input type="text" id="id_queryStartDate" onclick="WdatePicker()" class="Wdate text" style="width:80px;" value="${queryStartDate}">~
			<input type="text" id="id_queryEndDate"  onclick="WdatePicker()" class="Wdate text" style="width:80px;" value="${queryEndTime}">
			<input type="text" id="id_patient_info_c" class="auto-tip" data-tip="${patientZyTitle}/姓名" title="${patientZyTitle}/姓名" style="width: 100px;" />
	   		<div class="n_btn_blue">
				<a href="javascript:;" onclick="confirmInfect.query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>	   		
	   	</div>
		<div id="confirmInfoPanel"></div>
	</div>
<script type="text/javascript">
	var confirmInfect = {
		panel : 'confirmInfoPanel',
		query : function () {
			autoTip.clear();
			$('#' + confirmInfect.panel).datagrid({
	            url: '${webroot}/gr002YsgrMx/f_json/findConfirmInfect.shtml',
	            queryParams: {
	            	'queryStartDate':$('#id_queryStartDate').val(),
	            	'queryEndDate':$('#id_queryEndDate').val() == ''?'':$('#id_queryEndDate').val()+' 23:59:59',
	            	'infectDeptId':$('#id_infectDept_c').combogrid('getValue'),
	            	'searchString':$("#id_patient_info_c").val()
	            },
	            pageNumber : 1
	        });
		},
		getNotNullStr : function(str, prefix, suffix) {
			if (str == null) {
				return '';
			} else {
				if (prefix) {
					str = prefix + str;
				}
				if (suffix) {
					str += suffix;
				}
				return str;
			}
		},
		gethMdDateStr : function(str) {
			if (str && str.length > 10) {
				return str.substring(0,10);
			} else {
				return '';
			}
		},
		toDelete : function (relid, zyid) {
			Comm.dialogGlobal({
	        	url:"${webroot}/gr002YsgrMx/f_view/toDelDiagnosis.shtml?zyid=" + zyid + '&relid=' + relid,
	            title: '删除确认',
	            width:600,
	            height:350,
	            type:"iframe",
	            parent:this
	        });
		},
		del : function () {
			/* var dataStr = '',
				relid = $('#id_delete_relid').val(),
				zyid = $('#id_delete_zyid').val(),
				delReason = $('#id_delReason').val();
			$("input:checkbox[relid='" + relid + "']:checked").each(function(){
    			dataStr += $(this).attr('data'); + '##';
   			});
    		if (dataStr == '') { //删除整个报卡
    			$.ajax({
                    url: '${webroot}/gr002YsgrMx/f_json/delReportCard.shtml',
                    type: 'post',
                    data: { relid: relid, zyid: zyid, delReason:delReason },
                    dataType: 'json',
                    success : function(json) {
                    	if (json.result === 'success') {
        					$.messager.show({ title: '提示', msg: '操作成功！' });
        					confirmInfect.query();
        					$('#id_delReason').val('');
        					$('#id_delete_report').dialog('close');
        				} else if (json.result === 'error') {
        					$.messager.show({title : '提示',msg : '操作失败！'});
        				} else {
        					$.messager.show({title : '提示',msg : json.msg});
        				}
                    }
        		});
    		} else { //删除单个/多个诊断
    			$.ajax({
                    url: '${webroot}/gr002YsgrMx/f_json/delDiagnosis.shtml',
                    type: 'post',
                    data: { relid: relid, zyid: zyid, data: dataStr, delReason:delReason },
                    dataType: 'json',
                    success : function(json) {
                    	if (json.result === 'success') {
        					$.messager.show({ title: '提示', msg: '操作成功！' });
        					confirmInfect.query();
        					$('#id_delReason').val('');
        					$('#id_delete_report').dialog('close');
        				} else if (json.result === 'error') {
        					$.messager.show({title : '提示',msg : '操作失败！'});
        				} else {
        					$.messager.show({title : '提示',msg : json.msg});
        				}
                    }
        		});
    		}
    		
    		return dataStr; */
		},
		//修改
		toEdit : function(relid) {
			parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toReportCardsEdit.shtml?isSeparatePage=1&relid=' + relid,true);
		},
		resizeGrid : function(subtract, minWidth, percent) {
			var remain = document.body.clientWidth - subtract - 60;
			remain = remain * percent / 100;
			if (remain < minWidth) {
				return minWidth;
			}
			return remain;
		}
	};

	$(document).ready(function () {
		//感染科室
		Csm.combogrid.dep({
			id: 'id_infectDept_c',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0',
			ifcaseoffice: '1'
		});
		
		$('#' + confirmInfect.panel).datagrid({
			fit: true,
	        nowrap: true,
	        autoRowHeight: true,
            remoteSort:false,
            striped: false,
            fitColumns: true,
            singleSelect: true,
	        border:false,
            url:'${webroot}/gr002YsgrMx/f_json/findConfirmInfect.shtml',
            queryParams: {
            	'queryStartDate':$('#id_queryStartDate').val(),
            	'queryEndDate':$('#id_queryEndDate').val() == ''?'':$('#id_queryEndDate').val()+' 23:59:59'
            },
            columns:[[
                {field:'reportTypeName',title:'来源',width:60,sortable:true,align:'center'},
                {field:'infectName',title:'感染诊断',width:218,sortable:true,align:'center'},
                {field:'startAt',title:'感染日期',width:80,sortable:true,formatter:confirmInfect.gethMdDateStr,align:'center'},
                {field:'infectTypeName',title:'感染类型',width:60,sortable:true,align:'center'},
                {field:'infectDeptName',title:'感染科室',width:218,align:'center'},
                {field:'relation',title:'相关性',width:70},
                {field:'opeName',title:'手术名称',width:100},
                {field:'memo',title:'切口类型',width:70,align:'center'},
                {field:'jbzg',title:'转归情况',width:70},
                {field:'infectendDt',title:'转归日期',width:80,sortable:true,align:'center'}
            ]],
            pagination:true,
            toolbar:'#tb_confirm_info',
            rownumbers:true,
            pageSize:50,
            groupField:'relid',
            view: groupview,
            groupFormatter:function(value, rows){
                return '<span style="width:100px;">' +rows[0].patientName + '(' + rows.length + ')' + '</span><span style="width:180px;"><b>${patientZyTitle}：</b>' + rows[0].zyid + '</span><span style="width:50px;"> ' + rows[0].age + '</span><span style="width:140px;"><b>入院：</b>' + confirmInfect.gethMdDateStr(rows[0].inHospAt) + '</span><span style="width:210px;"><span class="omit" style="max-width:110px;"><b>上报：</b>' + rows[0].reportDrName + '</span>(' + rows[0].reportAt + ')' + 
                		'</span><span style="width:200px;"><span class="omit" style="max-width:100px;"><b>审核：</b>' + confirmInfect.getNotNullStr(rows[0].auditDrName) + '</span>' + confirmInfect.getNotNullStr(rows[0].auditAt, '(', ')')  + '</span>' + 
                		'<span class="mr_btn"><a class="ico_no" href="javascript:void(0)" onclick="confirmInfect.toEdit(\'' + value + '\');" >修改</a>&nbsp;&nbsp;<a class="ico_no" href="javascript:void(0)" onclick="confirmInfect.toDelete(\'' + value + '\', \'' + rows[0].zyid + '\');">删除</a></span>';
            },
            onLoadSuccess : function (data) {
            	var tab = $('#infect_monitor_tab').tabs('getTab',1); // 取得第二个tab 
            	$('#infect_monitor_tab').tabs('setTabTitle',{tab:tab,title:'已确认(' + data.total + ')'});
            	/* $('#infect_monitor_tab').tabs('update', {
                	tab: tab, 
                	options: { 
                		title: '已确认(' + data.total + ')'
                	} 
            	}); */
            },
            groupStyler: function(value,rows){
        		return 'background-color:#efefef;height:28px;font-size:13px;padding-top:3px;font-weight:normal;'; 
        	}
        });
	});
</script>
</body>
</html>
