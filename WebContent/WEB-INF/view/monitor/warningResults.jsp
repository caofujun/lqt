<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>预警结果</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<div id="warningPanel"></div>
<script type="text/javascript">
	var warning = {
		panel : 'warningPanel',
		//分组Index
		groupIndex : 0,
		//需要折叠的分组Index
		collGroupIndex : new Array(),
		
		incTpye : '${param.incTpye}',  
		
		//刷新页面用
		query : function() {
			if ('1' == this.incTpye) {
				parent.pWarning.query();
			} else {
				var currTab = $('#patient_frame_tabs').tabs('getSelected');
				var url = currTab.attr('href');
				currTab.panel('refresh', url);
			}
		},
	    //确认
	    confirm : function(gr2Relid, state, reportType, regId) {
	    	if (state != '1') {
	    		if (!gr2Relid || gr2Relid == '') {
	    			$.messager.show({ title: '提示', msg: '关联关系不存在！' });
	    			return false;
	    		}
	    		if ('1' == this.incTpye) {
	    			parent.parent.parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&bk2Relid=' + gr2Relid,true);
	    		} else {
	    			parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&bk2Relid=' + gr2Relid,true);
	    		}
	    	} else {
	    		if (!reportType && reportType != '1' && reportType != '2') { //系统预警 - 上报
	    			if ('1' == this.incTpye) {
	    				parent.parent.parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&regId=' + regId,true);
	    			} else {
	    				parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&regId=' + regId,true);
	    			}
	    		} else { //修改
	    			if (!gr2Relid || gr2Relid == '') {
	        			$.messager.show({ title: '提示', msg: '关联关系不存在！' });
	        			return false;
	        		}
	    			if ('1' == this.incTpye) {
	    				parent.parent.parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&action=edit&bk2Relid=' + gr2Relid,true);
	    			} else {
	    				parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&action=edit&bk2Relid=' + gr2Relid,true);
	    			}
	    		}
	    	}
	    },
	    //排除
	    exclude : function(reportType, gr2Relid, regId) {
	    	if (!reportType && reportType != '1' && reportType != '2' && (!gr2Relid || gr2Relid == '')) { //系统预警走排除
	    		Comm.dialogGlobal({
		        	url:"${webroot}/gr002YsgrMx/f_view/toRuleOutInfect.shtml?regId=" + regId,
		            title: '排除',
		            width:360,
		            height:320,
		            type:"iframe",
		            parent:this
		        });
	    	} else { //走审核流程
	    		if (!gr2Relid || gr2Relid == '') {
	    			$.messager.show({ title: '提示', msg: '关联关系不存在！' });
	    			return false;
	    		}
	    		if ('1' == this.incTpye) {
	    			parent.parent.parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&bk2Relid=' + gr2Relid,true);
	    		} else {
	    			parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&bk2Relid=' + gr2Relid,true);
	    		}
	    	}
	    },
	    getNotNullStr : function(str) {
			if (str == null) {
				return '';
			} else {
				return str;
			}
		},
		gethMdDateStr : function(str) {
			if (str && str.length > 10) {
				return str.substring(0,10);
			} else {
				return '';
			}
		}
	};
	
	var intervent = {
		//刷新页面用
		query : function() {
			
		},
		//干预
		intervention : function(zyid, name) {
	        Comm.dialogGlobal({
	        	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid=" + zyid + '&msgType=1',
	            title: '患者【' + name + '】的干预内容',
	            width:870,
	            height:565,
	            type:"iframe",
	            parent:this
	        });
	    }
	};
	
	$(document).ready(function () {
		$('#' + warning.panel).datagrid({
			fit: true,
	        nowrap: true,
	        autoRowHeight: true,
            remoteSort:false,
            striped: false,
            fitColumns: true,
            singleSelect: true,
            border:false,
            url:'${webroot}/gr002YsgrMx/f_json/findWarningResults.shtml?zyid=${param.zyid}&regId=${param.regId}&gr2Relid=${param.gr2Relid}',
            columns:[[
                {field:'dataForm',title:'来源',width:50,sortable:true,align:'center'},
                {field:'elementName',title:'原因',width:130,sortable:true},
                {field:'tiemsCount',title:'出现次数',width:60,sortable:true,align:'center'},
                {field:'dateRange',title:'出现时间',width:100,sortable:true,align:'center'},
                {field:'originalContent',title:'说明',width:286},
                {field:'remark',title:'操作原因',width:150},
                {field:'operator',title:'操作人',width:70},
                {field:'lastoperDate',title:'操作时间',width:120,align:'center'}
            ]],
            rownumbers:true,
            groupField:'regId',
            view: groupview,
            groupFormatter:function(value, rows){
            	if (rows[0].state == 2 || rows[0].state == 3) {
            		warning.collGroupIndex.push(warning.groupIndex);
            	}
            	warning.groupIndex ++;
                return  '<span style="width:100px;">' + rows[0].infectTypeName + '</span><span style="width:100px;">' + warning.gethMdDateStr(rows[0].startAt) + '</span><span style="width:100px;">' + rows[0].reportTypeName + '</span><span style="width:100px;">' + rows[0].itemName + '</span><span style="width:220px;">' + rows[0].infectName + 
                		'</span><span class="mr_btn"><a class="ico_no" href="javascript:void(0)" onclick="warning.confirm(\'' +  warning.getNotNullStr(rows[0].gr2Relid) + '\', \'' +  warning.getNotNullStr(rows[0].state) + '\', \'' +  warning.getNotNullStr(rows[0].reportType) + '\', \'' +  warning.getNotNullStr(rows[0].regId) + '\');" >确认</a>' + 
                		(rows[0].state == 1 ? '&nbsp;&nbsp;<a class="ico_no" href="javascript:void(0)" onclick="warning.exclude(\'' +  warning.getNotNullStr(rows[0].reportType) + '\', \'' +  warning.getNotNullStr(rows[0].gr2Relid) + '\', \'' +  warning.getNotNullStr(rows[0].regId) + '\');">排除</a>' : '') +
                		'&nbsp;&nbsp;<a class="ico_no" href="javascript:void(0)" onclick="intervent.intervention(\'' +  rows[0].zyid + '\', \'' +  rows[0].patientName + '\');">干预</a></span>';
            },
            groupStyler: function(value,rows){
        		return 'background-color:#efefef;height:28px;font-size:13px;padding-top:3px;font-weight:normal;'; 
        	},
        	onLoadSuccess:function(data){
        		for (var i = 0;i < warning.collGroupIndex.length; i++) {
        			$('#' + warning.panel).datagrid('collapseGroup', warning.collGroupIndex[i]);
        		}
        		warning.groupIndex = 0;
        		warning.collGroupIndex.length = 0;
        	}
        });
	});
</script>
</body>
</html>
