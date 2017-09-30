<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>监测科室授权</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
	<body>
		<div id="id_edit"></div>
		<div id="tb" class="m_search" style="display: none;">			
			<!-- <span>监测科室:</span>					
			<div class="select_del"><input type="text" id="id_deptId" style="width: 120px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_deptId').combo('clear');"></a></div> -->					
			<span class="ml5">业务科室:</span>					
			<div class="select_del"><input type="text" id="id_operateDeptId" style="width: 120px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_operateDeptId').combo('clear');"></a></div>
			<span class="ml5">监测人员:</span>
			<input type="text" id="id_searchString" style="width: 120px;" />					
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="hw010Zzry.query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="hw010Zzry.edit('', '', '新增专职人员');" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="hw010Zzry.roleManage();"><i class="icon iconfont">&#xe636;</i><span>角色管理</span></a>
				</div>
			</div>
		</div>
		<div id="hw010ZzryPanel"></div>
<script type="text/javascript">
var hw010Zzry = {
	panel : 'hw010ZzryPanel',
	query : function() {
		$('#'+hw010Zzry.panel).datagrid({
            url:'${webroot}/hw010Zzry/f_json/findAccreditList.shtml',
            queryParams: {
            	//'deptId':$('#id_deptId').combogrid('getValue'),
            	'operateDeptId':$('#id_operateDeptId').combogrid('getValue'),
            	'searchString':$('#id_searchString').val()
            }
		});
	},
    //角色管理
    roleManage : function() {
        Comm.dialogGlobal({
        	url:"${webroot}/hw016Role/f_view/toRoleManage.shtml",
            title: '角色管理',
            width:860,
            height:500,
            type:"iframe",
            parent:this
        });
    },
    //删除
    del:function(employeeId, deptId, employeeName) {
    	$.messager.confirm('提示', '确认删除【' + employeeName + '：' + employeeId + '】?', function (r) {
        	if (r) {
            	$.ajax({
                    url: '${webroot}/hw010Zzry/f_json/delete.shtml',
                    type: 'post',
                    data: { employeeId: employeeId, deptId: deptId },
                    dataType: 'json',
                    success : function(json) {
						if(json.result==='success') {
							hw010Zzry.query();
                            $.messager.show({ title: '提示', msg: '删除成功！' });
				    	} else if(json.result === 'error') {
				    		$.messager.show({ title: '提示', msg: '删除异常！' });
				    	} else {
				    		$.messager.show({ title: '提示', msg: json.msg });
				    	}
					}
            	});
        	}
    	});
    },
    //授权
    accredit :function(employeeId, deptId) {
    	Comm.dialogGlobal({
        	url:"${webroot}/hw010Zzry/f_view/toAccreditSelect.shtml?employeeId=" + employeeId + "&deptId=" + deptId,
            title: '用户授权',
            width:600,
            height:450,
            type:"iframe",
            parent:this
        });
    },
    //修改
    edit : function(employeeId, deptId, title) {
    	/* Comm.dialog({
        	url:"${webroot}/hw010Zzry/f_view/toEdit.shtml?employeeId=" + employeeId + '&deptId=' + deptId,
            title: title,
            width:460,
            height:360,
            parent:this
        }); */
        
    	$('#id_edit').dialog({
    	    title: title,
    	    width: 450,
    	    height: 220,
    	    closed: false,
    	    cache: false,
    	    href: '${webroot}/hw010Zzry/f_view/toEdit.shtml?employeeId=' + employeeId + '&deptId=' + deptId,
    	    modal: true
    	});
    },
    //刷新行
    updateRow : function(employeeId) {
    	var row = $('#' + hw010Zzry.panel).datagrid('getSelected');
    	var index = $('#' + hw010Zzry.panel).datagrid('getRowIndex', row);
    	$.ajax({
            url: '${webroot}/hw010Zzry/f_json/get.shtml',
            type: 'post',
            data: { employeeId: employeeId },
            dataType: 'json',
            success : function(json) {
				$('#' + hw010Zzry.panel).datagrid('updateRow',{
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
    }
};
$(document).ready(function () {
	
	$('#' + hw010Zzry.panel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: true,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/hw010Zzry/f_json/findAccreditList.shtml',
        columns:[ 
	       	[
	            {field:'employeeId',title:'人员编号',sortable:true,width:100},
	            {field:'employeeName',title:'人员姓名',sortable:true,width:100},
	            {field:'sourceName',title:'来源',sortable:true,width:60},
	            {field:'roleName',title:'所属角色',sortable:true,width:180},
	           	{field:'operateDeptName',title:'业务科室',sortable:true,width:300},
	           	{field:'_operate',title:'操作',width:60,align:'center',
	           		formatter:function(value,rec){
						return ['<a href="javascript:hw010Zzry.del(\'' + rec.employeeId + '\', \'' + rec.deptId + '\', \'' + rec.employeeName + '\')" class="ico_del" title="删除"></a>' + 
						        '<a href="javascript:hw010Zzry.accredit(\'' + rec.employeeId + '\', \'' + rec.deptId + '\')" class="ico_setup" title="授权"></a>'].join('');
					}
	           	}
	        ]
        ],
        //pageSize:20,
       // pageList:[20,40,60,80,100],
        pagination:true,
        rownumbers:true,
        toolbar:'#tb'
	});
	/* 
	//监测科室
	$('#id_deptId').combogrid({
		delay: 1000,    
	    mode: 'remote',
	    loadMsg : '正在查询中...',
        idField:'deptId',
        textField:'deptName',
        panelWidth: 260,
        panelHeight: 300,
		url: '${webroot}/hw009Kssq/f_json/queryList.shtml',
		queryParams: {
			page: 1,
			size: 200
		},
        columns:[
        	[
             {field:'deptId',title:'科室编号',sortable:true,width:80},  
             {field:'deptName',title:'科室名称',sortable:true,width:160},
            ]
        ]
	}); */
	Csm.combogrid.dep({
		//【必传】控件名称
		id: 'id_operateDeptId',
		hospId:'${unitId}',
		ifenvoffice: '1'
	});
	//业务科室
/* 	$('#id_operateDeptId').combogrid({
		delay: 1000,    
	    mode: 'remote',
	    loadMsg : '正在查询中...',
        idField:'deptId',
        textField:'deptName',
        panelWidth: 260,
        panelHeight: 300,
		url: '${webroot}/hw009Kssq/f_json/queryList.shtml',
		queryParams: {
			page: 1,
			size: 200
		},
        columns:[
        	[
             {field:'deptId',title:'科室编号',sortable:true,width:80},  
             {field:'deptName',title:'科室名称',sortable:true,width:160},
            ]
        ]
	}); */
});
</script>
	</body>
</html>