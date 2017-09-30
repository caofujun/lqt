<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>采样标本</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
	<body>
		<div id="hw004CybbPanel"></div>
		<div id="tb" class="m_search">			
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="hw004Cybb.edit('','新增采样标本');"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
<script type="text/javascript">
var hw004Cybb = {
	panel : 'hw004CybbPanel',
	query : function() {
		$('#'+hw004Cybb.panel).datagrid({
            url:'${webroot}/hw004Cybb/f_json/findList.shtml',
		});
	},
    //编辑
    edit:function(sampleId, title) {
        Comm.dialogGlobal({
        	url:"${webroot}/hw004Cybb/f_view/toedit.shtml?sampleId=" + sampleId,
            title: title,
            width:600,
            height:360,
            type:"iframe",
            parent:this
        });
    },
    del:function(sampleId){
		$.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
			if(r){
				$.ajax({
					url: '${webroot}/hw004Cybb/f_json/delete.shtml',
					type: 'post',
					data: { 
						'sampleId': sampleId
					},
					dataType: 'json',
					success : function(json) {
						if(json.result==='success') {
							$.messager.show({ title: '提示', msg: '操作成功！' });
							hw004Cybb.query();
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
    updateRow : function(sampleId) {
    	var row = $('#' + hw004Cybb.panel).datagrid('getSelected');
    	var index = $('#' + hw004Cybb.panel).datagrid('getRowIndex', row);
    	$.ajax({
            url: '${webroot}/hw004Cybb/f_json/get.shtml',
            type: 'post',
            data: { sampleId: sampleId },
            dataType: 'json',
            success : function(json) {
				$('#' + hw004Cybb.panel).datagrid('updateRow',{
					index: index,
					row: {
						sampleName : json.sampleName,
						sampleId : json.sampleId,
						spCode : json.spCode,
						flag : json.flag,
						className : json.className,
						classId : json.classId
					}
				});
			}
    	});
    },
    //启用/停用
    isEnable : function(sampleId, sampleName, flag) {
    	var operat = (flag == 1 ? '停用' : '启用');
    	$.messager.confirm('提示', '确认' + operat + '【' + sampleName + '】?', function (r) {
        	if (r) {
            	$.ajax({
                    url: '${webroot}/hw004Cybb/f_json/updFlag.shtml',
                    type: 'post',
                    data: { sampleId: sampleId, flag : flag },
                    dataType: 'json',
                    success : function(json) {
						if(json.result==='success') {
							var row = $('#' + hw004Cybb.panel).datagrid('getSelected');
					    	var index = $('#' + hw004Cybb.panel).datagrid('getRowIndex', row);
							$('#' + hw004Cybb.panel).datagrid('updateRow',{
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
	
	$('#' + hw004Cybb.panel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: true,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/hw004Cybb/f_json/findList.shtml',
        columns:[ 
	       	[
	            {field:'sampleName',title:'标本名称',sortable:true,width:200},
	            {field:'sampleId',title:'标本ID',sortable:true,width:100},
	            {field:'spCode',title:'首拼码',sortable:true,width:100},
	           	{field:'flag',title:'启用标识',sortable:true,width:60,align:'center',
	           		formatter:function(value,rec){
	           			return (rec.flag == 1 ? '启用' : '停用');
	           		}
	           	},
	           	{field:'_operate',title:'操作',width:60,align:'center',
	           		formatter:function(value,rec){
						return ['<a href="javascript:hw004Cybb.edit(\'' + $.trim(rec.sampleId) + '\', \'编辑采样标本\')" class="ico_editor" title="修改"></a>' + 
						        '<a href="javascript:hw004Cybb.isEnable(\'' + $.trim(rec.sampleId) + '\', \'' + rec.sampleName + '\', ' + rec.flag + ')" class="' + (rec.flag == 1 ? 'ico_no_select' : 'ico_select') + '" title="' + (rec.flag == 1 ? '停用' : '启用') + '"></a>'+
						        '<a href="javascript:hw004Cybb.del(\'' + $.trim(rec.sampleId) + '\')" class="ico_del" title="删除"></a>'+
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