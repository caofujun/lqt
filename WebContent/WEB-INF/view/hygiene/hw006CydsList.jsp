<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>采样点数</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
	<body>
		<div id="hw006CydsPanel"></div>
		<div id="tb" class="m_search">			
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="hw006Cyds.edit('','新增采样点数');" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
<script type="text/javascript">
var hw006Cyds = {
	panel : 'hw006CydsPanel',
	query : function() {
		$('#'+hw006Cyds.panel).datagrid({
            url:'${webroot}/hw006Cyds/f_json/findList.shtml',
		});
	},
	getNotNullStr : function(str) {
		if (str == null) {
			return '';
		} else {
			return str;
		}
	},
    //编辑
    edit:function(posId, title) {
        Comm.dialogGlobal({
        	url:"${webroot}/hw006Cyds/f_view/toedit.shtml?posId=" + posId,
            title: title,
            width:400,
            height:360,
            type:"iframe",
            parent:this
        });
    },
    del:function(posId){
		$.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
			if(r){
				$.ajax({
					url: '${webroot}/hw006Cyds/f_json/delete.shtml',
					type: 'post',
					data: { 
						'posId': posId
					},
					dataType: 'json',
					success : function(json) {
						if(json.result==='success') {
							$.messager.show({ title: '提示', msg: '操作成功！' });
							hw006Cyds.query();
						} else if(json.result === 'error') {
							$.messager.show({ title : '提示', msg : '操作失败！' });
						} else {
							$.messager.show({ title : '提示', msg : json.msg });
						}
					}
				});
			}
		});
    },
    //启用/停用
    isEnable : function(posId, posName, flag) {
    	var operat = (flag == 1 ? '停用' : '启用');
    	$.messager.confirm('提示', '确认' + operat + '【' + posName + '】?', function (r) {
        	if (r) {
            	$.ajax({
                    url: '${webroot}/hw006Cyds/f_json/updFlag.shtml',
                    type: 'post',
                    data: { posId: posId, flag : flag },
                    dataType: 'json',
                    success : function(json) {
						if(json.result==='success') {
							var row = $('#' + hw006Cyds.panel).datagrid('getSelected');
					    	var index = $('#' + hw006Cyds.panel).datagrid('getRowIndex', row);
							$('#' + hw006Cyds.panel).datagrid('updateRow',{
								index: index,
								row: {
									flag : (flag == 1 ? 0 : 1)
								}
							});
	                        $.messager.show({ title: '提示', msg: operat + '成功！' });
		    			} else if(json.result === 'error') {
		    				$.messager.show({ title: '提示', msg: json.msg });
		    			}
					}
            	});
        	}
    	});
    }
};
$(document).ready(function () {
	
	$('#' + hw006Cyds.panel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: true,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/hw006Cyds/f_json/findList.shtml',
        columns:[ 
	       	[
	            {field:'posName',title:'点数名称',sortable:true,width:200},
	            {field:'posId',title:'点数编码',sortable:true,width:100},
	            {field:'posValue',title:'点位值',sortable:true,width:80},
	            {field:'spCode',title:'首拼码',sortable:true,width:100},
	           	{field:'flag',title:'启用标识',sortable:true,width:60,align:'center',
	           		formatter:function(value,rec){
	           			return (rec.flag == 1 ? '启用' : '停用');
	           		}
	           	},
	           	{field:'_operate',title:'操作',width:60,align:'center',
	           		formatter:function(value,rec){
						return ['<a href="javascript:hw006Cyds.edit(\'' + $.trim(rec.posId) + '\', \'编辑采样点数\')" class="ico_editor" title="修改"></a>' + 
						        '<a href="javascript:hw006Cyds.isEnable(\'' + $.trim(rec.posId) + '\', \'' + rec.posName + '\', ' + rec.flag + ')" class="' + (rec.flag == 1 ? 'ico_no_select' : 'ico_select') + '" title="' + (rec.flag == 1 ? '停用' : '启用') + '"></a>'+
						        '<a href="javascript:hw006Cyds.del(\'' + $.trim(rec.posId) + '\')" class="ico_del" title="删除"></a>'+
						        ''].join('');
					}
	           	}
	        ]
        ],
        rownumbers:true,
        groupField:'classId',
        view: groupview,
        toolbar:'#tb',
        groupFormatter:function(value, rows) {
            return  '<span>' + hw006Cyds.getNotNullStr(rows[0].className) + '</span>';
        },
        groupStyler: function(value,rows){
    		return 'background-color:#efefef;height:28px;font-size:13px;padding-top:3px;font-weight:normal;'; 
    	}
	});
	
});
</script>
	</body>
</html>