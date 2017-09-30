<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>监测项目</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
	<body>
		<div id="hw001JcxmPanel"></div>
		<div id="tb" class="m_search">			
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="hw001Jcxm.edit('','新增环境监测项目');"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
<script type="text/javascript">
var hw001Jcxm = {
	panel : 'hw001JcxmPanel',
	query : function() {
		$('#'+hw001Jcxm.panel).treegrid({
            url:'${webroot}/hw001Jcxm/f_json/findList.shtml',
		});
	},
    //编辑
    edit:function(classId, title) {
        Comm.dialogGlobal({
        	url:"${webroot}/hw001Jcxm/f_view/toedit.shtml?classId=" + classId,
            title: title,
            width:600,
            height:470,
            type:"iframe",
            parent:this
        });
    },
  	//删除
	del:function(classId) {
		// 点击删除时先查询该id下有无子类 有就提示 没有就显示确认删除
		$.ajax({
			url:'${webroot}/hw001Jcxm/f_view/findSubclass.shtml',
			type:'post',
			data:{
				'classId' : classId 
			},
			dataType:'json',
			success:function(json){
				if(json.result==='success'){
					$.messager.confirm('提示','该类下存在子类，是否删除？',function (r){
						if(r){
							$.ajax({
								url: '${webroot}/hw001Jcxm/f_view/delClass.shtml',
								type: 'post',
								data: { 
									'classId': classId
								},
								dataType: 'json',
								success : function(json) {
									if(json.result==='success') {
										$.messager.show({ title: '提示', msg: '操作成功！' });
										hw001Jcxm.query();
									} else if(json.result === 'error') {
										$.messager.show({ title : '提示', msg : '操作失败！' });
									} else {
										$.messager.show({ title : '提示', msg : json.msg });
									}
								}
							});
						}
					});
				}else{
					$.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
						if(r){
							$.ajax({
								url: '${webroot}/hw001Jcxm/f_view/del.shtml',
								type: 'post',
								data: { 
									'classId': classId
								},
								dataType: 'json',
								success : function(json) {
									if(json.result==='success') {
										$.messager.show({ title: '提示', msg: '操作成功！' });
										hw001Jcxm.query();
									} else if(json.result === 'error') {
										$.messager.show({ title : '提示', msg : '操作失败！' });
									} else {
										$.messager.show({ title : '提示', msg : json.msg });
									}
								}
							});
						}
					});
				}
			}
		});
	},
    //刷新行
    updateRow : function(classId) {
    	$.ajax({
            url: '${webroot}/hw001Jcxm/f_json/get.shtml',
            type: 'post',
            data: { classId: classId },
            dataType: 'json',
            success : function(json) {
				$('#' + hw001Jcxm.panel).treegrid('update',{
					id: classId,
					row: {
						className : json.className,
						material : json.material,
						takeArea : json.takeArea,
						takeMode : json.takeMode,
						testMode : json.testMode,
						spCode : json.spCode,
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
							$('#' + hw001Jcxm.panel).treegrid('update',{
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
	$('#'+hw001Jcxm.panel).treegrid({
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: true,
        fit: true,
        collapsible:true,
        url:'${webroot}/hw001Jcxm/f_json/findList.shtml',
        remoteSort: false,
        singleSelect: true,
        idField:'classId',
        treeField:'className',
        lines: true,
        border:false,
        columns:[
            [
	        	{field:'className',title:'项目名称',sortable:true,width:200},
	           	{field:'classId',title:'项目编号',sortable:true,width:80},
	           	{field:'material',title:'材料',sortable:true,width:100},
	           	{field:'takeArea',title:'采样面积',sortable:true,width:70},
	           	{field:'takeMode',title:'采样方法',sortable:true,width:80},
	           	{field:'testMode',title:'检测方法',sortable:true,width:80},
	           	{field:'spCode',title:'首拼码',sortable:true,width:60},
	           	{field:'memo',title:'备注',sortable:true,width:180},
	           	{field:'flag',title:'启用标识',sortable:true,width:60,align:'center',
	           		formatter:function(value,rec){
	           			return (rec.flag == 1 ? '启用' : '停用');
	           		}
	           	},
	           	{field:'_operate',title:'操作',width:60,align:'center',
					formatter:function(value,rec){
						return ['<a href="javascript:hw001Jcxm.edit(\'' + $.trim(rec.classId) + '\', \'编辑环境监测项目\')" class="ico_editor" title="修改"></a>' + 
						        '<a href="javascript:hw001Jcxm.isEnable(\'' + $.trim(rec.classId) + '\', \'' + rec.className + '\', ' + rec.flag + ')" class="' + (rec.flag == 1 ? 'ico_no_select' : 'ico_select') + '" title="' + (rec.flag == 1 ? '停用' : '启用') + '"></a>'+
						        '<a href="javascript:hw001Jcxm.del(\'' + $.trim(rec.classId) + '\')" class="ico_del" title="删除"></a>'+
						        ''].join('');
					}
	           	}
           	]
        ],
        onDblClickRow:function(row) {
        	$('#'+hw001Jcxm.panel).treegrid('toggle', row.classId);
        },
        toolbar:'#tb',
        onLoadSuccess: function (row, data) {
        	$('#'+hw001Jcxm.panel).treegrid('collapseAll');
        }
    });
	
});
</script>
	</body>
</html>