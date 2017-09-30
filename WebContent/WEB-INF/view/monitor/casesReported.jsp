<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>病例上报情况</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	<div id="casesReportedPanel"></div>
<script type="text/javascript">
	var casesReported = {
		panel : 'casesReportedPanel',
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
		//修改
		toEdit : function(relid) {
			parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toReportCardsEdit.shtml?isSeparatePage=1&relid=' + relid,true);
		}
	};

	$(document).ready(function () {
		
		$('#' + casesReported.panel).datagrid({
			fit: true,
	        nowrap: true,
	        autoRowHeight: true,
            remoteSort:false,
            striped: false,
            fitColumns: true,
            singleSelect: true,
            border:false,
            url:'${webroot}/gr002YsgrMx/f_json/findCasesReported.shtml?zyid=${param.zyid}',
            columns:[[
                {field:'reportTypeName',title:'来源',width:70,sortable:true,align:'center'},
                {field:'_isOk',title:'报卡状态',width:60,sortable:true,align:'center',
					formatter:function(value,r,index){
						if(r.isDel == 1){
							return [('<font color="red">已删卡</font>')].join('');
						}else if(r.isOk == 1){
							return [('<font color="green">已确认</font>')].join('');
						}else if(r.isOk == 2){
							return [('<font color="red">已退卡</font>')].join('');
						}else{
							return [('<font color="red">未确认</font>')].join('');
						}
					}								
				},
                {field:'infectName',title:'感染诊断',width:220,sortable:true,align:'center'},
                {field:'startAt',title:'感染日期',width:80,sortable:true,formatter:casesReported.gethMdDateStr,align:'center'},
                {field:'infectTypeName',title:'感染类型',width:70,sortable:true,align:'center'},
                {field:'infectDeptName',title:'感染科室',width:180,align:'center'},
                {field:'reportDrName',title:'报告人',width:60,align:'center'},
                {field:'reportDeptName',title:'报告科室',width:180,align:'center'},
                {field:'reportAt',title:'报告时间',width:80,align:'center'},
                {field:'authUserName',title:'审核人',width:60,align:'center'},
                {field:'auditAt',title:'审核时间',width:80,align:'center'},
                {field:'returnReason',title:'退卡/删卡原因',width:120,align:'center',
                	formatter:function(value,r,index){
                		if(r.returnReason != null){
                			return ['<span title="'+r.returnReason+'">',r.returnReason,'</span>'].join('');
						}else if(r.delReason != null){
							return ['<span title="'+r.delReason+'">',r.delReason,'</span>'].join('');
						}	
					}		
                },
                {field:'_operate',title:'操作',width:70,align:'center',
                	formatter:function(value,r,index){
						return '<a href="javascript:void(0)" onclick="casesReported.toEdit(\'' + r.relid + '\');" class="ico_editor" title="修改" ></a>'+
						       '<a href="javascript:void(0)" onclick="casesReported.toDelete(\'' + r.relid + '\', \'' + r.zyid + '\');" class="ico_del" title="删除"></a>';
                	}
                }
            ]],
            rownumbers:true,
            groupField:'relid'
        });
	});
</script>
</body>
</html>
