<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>监测计算标准</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
	<body>
		<div id="hw002JsbzPanel"></div>
		<div id="tb" class="m_search">			
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="hw002Jsbz.edit('','新增监测计算标准');"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
<script type="text/javascript">
var hw002Jsbz = {
	panel : 'hw002JsbzPanel',
	query : function() {
		$('#'+hw002Jsbz.panel).datagrid({
            url:'${webroot}/hw002Jsbz/f_json/findList.shtml',
		});
	},
    //编辑
    edit:function(itemId, title) {
        Comm.dialogGlobal({
        	url:"${webroot}/hw002Jsbz/f_view/toedit.shtml?itemId=" + itemId,
            title: title,
            width:600,
            height:350,
            type:"iframe",
            parent:this
        });
    },
    del:function(itemId){
    	$.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
    		if(r){
				$.ajax({
					url: '${webroot}/hw002Jsbz/f_json/delete.shtml',
					type: 'post',
					data: { 
						'itemId': itemId
					},
					dataType: 'json',
					success : function(json) {
						if(json.result==='success') {
							$.messager.show({ title: '提示', msg: '操作成功！' });
							hw002Jsbz.query();
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
    //刷新行
    updateRow : function(itemId) {
    	var row = $('#' + hw002Jsbz.panel).datagrid('getSelected');
    	var index = $('#' + hw002Jsbz.panel).datagrid('getRowIndex', row);
    	$.ajax({
            url: '${webroot}/hw002Jsbz/f_json/get.shtml',
            type: 'post',
            data: { itemId: itemId },
            dataType: 'json',
            success : function(json) {
				$('#' + hw002Jsbz.panel).datagrid('updateRow',{
					index: index,
					row: {
						itemName : json.itemName,
						itemId : json.itemId,
						className : json.className,
						condition : json.condition,
						criterion : json.criterion,
						unit : json.unit,
						memo : json.memo
					}
				});
			}
    	});
    },
    //启用/停用
    isEnable : function(classId, className, flag) {
    	var operat = (flag == 1 ? '停用' : '启用');
    	$.messager.confirm('提示', '确认' + operat + '【' + className + '】?', function (r) {
        	if (r) {
            	$.ajax({
                    url: '${webroot}/hw001Jclb/f_json/updFlag.shtml',
                    type: 'post',
                    data: { classId: classId, flag : flag },
                    dataType: 'json',
                    success : function(json) {
						if(json.result==='success') {
							$('#' + hw002Jsbz.panel).datagrid('update',{
								id: classId,
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
	
	$('#' + hw002Jsbz.panel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: true,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/hw002Jsbz/f_json/findList.shtml',
        columns:[ 
	       	[
	            {field:'itemName',title:'监测项目',sortable:true,width:160},
	            {field:'itemId',title:'类别ID',sortable:true,width:60},
	            {field:'className',title:'类别名称',sortable:true,width:160},
	            {field:'condition',title:'条件',sortable:true,align:'center',width:50},
	            {field:'criterion',title:'执行标准',sortable:true,width:80},
	            {field:'unit',title:'单位',sortable:true,width:180},
	            {field:'memo',title:'备注',sortable:true,width:180},
	           	{field:'_operate',title:'操作',width:40,align:'center',
					formatter:function(value,rec){
						return ['<a href="javascript:hw002Jsbz.edit(\'' + $.trim(rec.itemId) + '\', \'编辑监测计算标准\')" class="ico_editor" title="修改"></a>'+
						        '<a href="javascript:hw002Jsbz.del(\'' + $.trim(rec.itemId) + '\')" class="ico_del" title="删除"></a>'+
						        ''].join('');
					}
	           	}
	        ]
        ],
        rownumbers:true,
        groupField:'pclassId',
        view: groupview,
        toolbar:'#tb',
        groupFormatter:function(value, rows) {
            return  '<span>' + rows[0].pclassName + '</span>';
        },
        groupStyler: function(value,rows){
    		return 'background-color:#efefef;height:28px;font-size:13px;padding-top:3px;font-weight:normal;'; 
    	}
	});
	
});
</script>
	</body>
</html>