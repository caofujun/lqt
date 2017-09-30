<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>采样场所</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
	<body>
		<div id="hw003CycsPanel"></div>
		<div id="tb" class="m_search">
			<input type="text" id="searchString" class="auto-tip" data-tip="场所名称"  style="width: 180px"/>
			<div class="n_btn_blue">
				<a href="javascript:;"  onclick="hw003Cycs.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>
			
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="hw003Cycs.edit('','新增采样场所');" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
<script type="text/javascript">
var hw003Cycs = {
	panel : 'hw003CycsPanel',
	query : function() {
		autoTip.clear();
		$('#'+hw003Cycs.panel).datagrid({
            url:'${webroot}/hw003Cycs/f_json/findList.shtml',
            queryParams: {
                'searchString': $('#searchString').val()
            }
		});
	},
    //编辑
    edit:function(placeId, title) {
        Comm.dialogGlobal({
        	url:"${webroot}/hw003Cycs/f_view/toedit.shtml?placeId=" + placeId,
            title: title,
            width:350,
            height:250,
            type:"iframe",
            parent:this
        });
    },
    del:function(placeId){
		$.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
			if(r){
				$.ajax({
					url: '${webroot}/hw003Cycs/f_json/delete.shtml',
					type: 'post',
					data: { 
						'placeId': placeId
					},
					dataType: 'json',
					success : function(json) {
						if(json.result==='success') {
							$.messager.show({ title: '提示', msg: '操作成功！' });
							hw003Cycs.query();
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
    updateRow : function(placeId) {
    	var row = $('#' + hw003Cycs.panel).datagrid('getSelected');
    	var index = $('#' + hw003Cycs.panel).datagrid('getRowIndex', row);
    	$.ajax({
            url: '${webroot}/hw003Cycs/f_json/get.shtml',
            type: 'post',
            data: { placeId: placeId },
            dataType: 'json',
            success : function(json) {
				$('#' + hw003Cycs.panel).datagrid('updateRow',{
					index: index,
					row: {
						placeName : json.placeName,
						placeId : json.placeId,
						spCode : json.spCode,
						flag : json.flag
					}
				});
			}
    	});
    },
    //启用/停用
    isEnable : function(placeId, placeName, flag) {
    	var operat = (flag == 1 ? '停用' : '启用');
    	$.messager.confirm('提示', '确认' + operat + '【' + placeName + '】?', function (r) {
        	if (r) {
            	$.ajax({
                    url: '${webroot}/hw003Cycs/f_json/updFlag.shtml',
                    type: 'post',
                    data: { placeId: placeId, flag : flag },
                    dataType: 'json',
                    success : function(json) {
						if(json.result==='success') {
							var row = $('#' + hw003Cycs.panel).datagrid('getSelected');
					    	var index = $('#' + hw003Cycs.panel).datagrid('getRowIndex', row);
							$('#' + hw003Cycs.panel).datagrid('updateRow',{
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
	$('#'+hw003Cycs.panel).datagrid({
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: true,
        fit: true,
        url:'${webroot}/hw003Cycs/f_json/findList.shtml',
        remoteSort: false,
        singleSelect: true,
        border:false,
        columns:[
            [
	        	{field:'placeName',title:'场所名称',sortable:true,width:200},
	           	{field:'placeId',title:'场所编号',sortable:true,width:80},
	           	{field:'spCode',title:'首拼码',sortable:true,width:60},
	           	{field:'flag',title:'启用标识',sortable:true,width:60,align:'center',
	           		formatter:function(value,rec){
	           			return (rec.flag == 1 ? '启用' : '停用');
	           		}
	           	},
	           	{field:'_operate',title:'操作',width:60,align:'center',
					formatter:function(value,rec){
						return ['<a href="javascript:hw003Cycs.edit(\'' + $.trim(rec.placeId) + '\', \'编辑采样场所\')" class="ico_editor" title="修改"></a>' + 
						        '<a href="javascript:hw003Cycs.isEnable(\'' + $.trim(rec.placeId) + '\', \'' + rec.placeName + '\', ' + rec.flag + ')" class="' + (rec.flag == 1 ? 'ico_no_select' : 'ico_select') + '" title="' + (rec.flag == 1 ? '停用' : '启用') + '"></a>'+
						        '<a href="javascript:hw003Cycs.del(\'' + $.trim(rec.placeId) + '\')" class="ico_del" title="删除"></a>'+
						        ''].join('');
					}
	           	}
           	]
        ],
        rownumbers:true,
        toolbar:'#tb'
    });
	
});
</script>
	</body>
</html>