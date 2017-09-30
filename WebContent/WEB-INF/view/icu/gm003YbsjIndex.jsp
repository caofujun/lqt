<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>咳嗽腹泻监测</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
	<body>
		<div id="tb" class="m_search" style="display: none;">			
			<span>填报日期:</span>
			<input type="text" id="queryStartDate" value="${queryStartDate}" class="Wdate text" style="width:80px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'queryEndTime\')}',dateFmt:'yyyy-MM-dd'})" />~
    		<input type="text" id="queryEndTime" value="${queryEndDate}" class="Wdate text" style="width:80px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'queryStartDate\')}',dateFmt:'yyyy-MM-dd'})" />
			<span class="ml5">科室:</span>
			<div class="select_del">
				<input type="text" id="id_reportDeptId" style="width: 150px;" />
				<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_reportDeptId').combo('clear');"></a>
			</div>			
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="handHygiene.query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="handHygiene.add();"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<div id="handHygienePanel"></div>
<script type="text/javascript">
var handHygiene = {
	panel : 'handHygienePanel',
	query : function() {
		$('#'+handHygiene.panel).datagrid({
            url:'${webroot}/sw001Swsyp/f_json/pageQuery.shtml',
            queryParams: {
            	'queryStartDate':$('#queryStartDate').val(),
            	'queryEndDate':$('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59',
            	'reportDeptId':$('#id_reportDeptId').combogrid('getValue'),
            	'type':$('#id_type').combogrid('getValue')
            }
		});
	},
	//新增
	add : function() {
		Comm.dialogGlobal({
        	url:"${webroot}/sw001Swsyp/f_view/toadds.shtml",
            title: '手卫生用品数据填报',
            width:515,
            height:510,
            type:"iframe",
            parent:this
        });
	},
    //删除
    del:function(id) {
    	$.messager.confirm('提示', '确认删除?', function (r) {
        	if (r) {
            	$.ajax({
                    url: '${webroot}/sw001Swsyp/f_json/delete.shtml',
                    type: 'post',
                    data: { id: id },
                    dataType: 'json',
                    success : function(json) {
						if(json.result==='success') {
							handHygiene.query();
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
    //修改
    edit : function(id, typeName) {
    	Comm.dialogGlobal({
        	url:"${webroot}/sw001Swsyp/f_view/toedit.shtml?id=" + id,
            title: typeName,
            width:350,
            height:350,
            type:"iframe",
            parent:this
        });
    }
};
$(document).ready(function () {
	
	$('#' + handHygiene.panel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: true,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/sw001Swsyp/f_json/pageQuery.shtml',
        queryParams: {
        	'queryStartDate':$('#queryStartDate').val(),
        	'queryEndDate':$('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59'
        	//'reportDeptId':$('#id_reportDeptId').combogrid('getValue'),
        	//'type':$('#id_type').combogrid('getValue')
        },
        columns:[ 
	       	[
	            {field:'reportDate',title:'日期',sortable:true,align:'center',width:88},
	            {field:'reportDeptName',title:'科室名称',sortable:true,width:160},
	            {field:'typeName',title:'用品类型',sortable:true,width:90},
	            {field:'specification',title:'规格',sortable:true,width:95,
	           		formatter:function(value,rec){
	           			return rec.specification + rec.specificaUnit;
	           		}
	           	},
	           	{field:'lastMonthRemain',title:'上月剩余量',sortable:true,width:80,
	           		formatter:function(value,rec){
	           			return rec.lastMonthRemain + rec.inventoryUnit;
	           		}
	           	},
	            {field:'thisMonthGet',title:'本月领用',sortable:true,width:80,
	           		formatter:function(value,rec){
	           			return rec.thisMonthGet + rec.usedUnit;
	           		}
	           	},
	           	{field:'thisMonthInventory',title:'本月库存量',sortable:true,width:95,
	           		formatter:function(value,rec){
	           			return rec.thisMonthInventory + rec.inventoryUnit;
	           		}
	           	},
	           	{field:'thisMonthUsed',title:'本月使用量',sortable:true,width:95,
	           		formatter:function(value,rec){
	           			return rec.thisMonthUsed + rec.inventoryUnit;
	           		}
	           	},
	           	{field:'reportUserName',title:'填报人',sortable:true,width:80},
	           	{field:'_operate',title:'操作',width:60,align:'center',
	           		formatter:function(value,rec){
						return ['<a href="javascript:handHygiene.edit(\'' + rec.id + '\', \'' + rec.typeName + '\')" class="ico_editor" title="修改"></a>' + 
						        '<a href="javascript:handHygiene.del(\'' + rec.id + '\')" class="ico_del" title="删除"></a>'].join('');
					}
	           	}
	        ]
        ],
        pagination:true,
        rownumbers:true,
        toolbar:'#tb'
	});
	
	//科室
	Csm.combogrid.dep({
		id: 'id_reportDeptId',
		flag: '1',
		//【可选参数】下拉列表的默认value，不传则没有默认值；
		//value: '${doctor.deptId}',
		callback: '0'
	});
});
</script>
	</body>
</html>