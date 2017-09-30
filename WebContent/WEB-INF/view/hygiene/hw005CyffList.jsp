<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>采样方法</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
	<body>
		<div id="hw005CyffPanel"></div>
		<div id="tb" class="m_search">		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="hw005Cyff.edit('','新增采样方法');"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
<script type="text/javascript">
var hw005Cyff = {
	panel : 'hw005CyffPanel',
	query : function() {
		$('#'+hw005Cyff.panel).datagrid({
            url:'${webroot}/hw005Cyff/f_json/findList.shtml',
		});
	},
    //编辑
    edit:function(takeModeId, title) {
        Comm.dialogGlobal({
        	url:"${webroot}/hw005Cyff/f_view/toedit.shtml?takeModeId=" + takeModeId,
            title: title,
            width:400,
            height:360,
            type:"iframe",
            parent:this
        });
    },
    del:function(takeModeId){
		$.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
			if(r){
				$.ajax({
					url: '${webroot}/hw005Cyff/f_json/delete.shtml',
					type: 'post',
					data: { 
						'takeModeId': takeModeId
					},
					dataType: 'json',
					success : function(json) {
						if(json.result==='success') {
							$.messager.show({ title: '提示', msg: '操作成功！' });
							hw005Cyff.query();
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
    isEnable : function(takeModeId, takeModeName, flag) {
    	var operat = (flag == 1 ? '停用' : '启用');
    	$.messager.confirm('提示', '确认' + operat + '【' + takeModeName + '】?', function (r) {
        	if (r) {
            	$.ajax({
                    url: '${webroot}/hw005Cyff/f_json/updFlag.shtml',
                    type: 'post',
                    data: { takeModeId: takeModeId, flag : flag },
                    dataType: 'json',
                    success : function(json) {
						if(json.result==='success') {
							var row = $('#' + hw005Cyff.panel).datagrid('getSelected');
					    	var index = $('#' + hw005Cyff.panel).datagrid('getRowIndex', row);
							$('#' + hw005Cyff.panel).datagrid('updateRow',{
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
	
	$('#' + hw005Cyff.panel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: true,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/hw005Cyff/f_json/findList.shtml',
        columns:[ 
	       	[
	            {field:'takeModeName',title:'方法名称',sortable:true,width:200},
	            {field:'takeModeId',title:'方法编码',sortable:true,width:100},
	            {field:'spCode',title:'首拼码',sortable:true,width:100},
	           	{field:'flag',title:'启用标识',sortable:true,width:60,align:'center',
	           		formatter:function(value,rec){
	           			return (rec.flag == 1 ? '启用' : '停用');
	           		}
	           	},
	           	{field:'_operate',title:'操作',width:60,align:'center',
	           		formatter:function(value,rec){
						return ['<a href="javascript:hw005Cyff.edit(\'' + $.trim(rec.takeModeId) + '\', \'编辑采样方法\')" class="ico_editor" title="修改"></a>' + 
						        '<a href="javascript:hw005Cyff.isEnable(\'' + $.trim(rec.takeModeId) + '\', \'' + rec.takeModeName + '\', ' + rec.flag + ')" class="' + (rec.flag == 1 ? 'ico_no_select' : 'ico_select') + '" title="' + (rec.flag == 1 ? '停用' : '启用') + '"></a>'+
						        '<a href="javascript:hw005Cyff.del(\'' + $.trim(rec.takeModeId) + '\')" class="ico_del" title="删除"></a>'+
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
            return  '<span>' + rows[0].className + '</span>';
        },
        groupStyler: function(value,rows){
    		return 'background-color:#efefef;height:28px;font-size:13px;padding-top:3px;font-weight:normal;'; 
    	}
	});
	
});
</script>
	</body>
</html>