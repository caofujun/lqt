<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>暴发预警血培养数据</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="drug_rightMenu" class="easyui-menu" style="width: 100px;display: none;">
	    <div name="export_patient" data-options="iconCls:'icon-print'">导出表格</div>
	</div>
	<div id="drugResistantPanel"></div>
<script type="text/javascript">
	//parent.$('#${param.dialogId}').dialog('setTitle', '${title}');
	
	var drugResistant = {
		panel : 'drugResistantPanel',
		//患者档案
		patientInfo : function (zyid) {
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
		},
		//导出患者
		exportList : function() {
			window.location.href = '${webroot}/by007Show/f_json/exportDrugResistantExcel.shtml?id=${param.id}&deptId=${param.deptId}&startDate=${param.moniDate}';
		},
		
		queryList : function() {
			$.ajax({
	            url: '${webroot}/by007Show/f_json/findSameDrugResistant.shtml',
	            type: 'post',
	            data: { startDate: '${param.moniDate}', queryStartDate: '${param.queryStartDate}', queryEndDate: '${param.queryEndDate}', id : '${param.id}', deptId : '${param.deptId}' },
	            dataType: 'json',
	            success : function(json) {
	            	//动态生成表头开始
	            	var columns = new Array();
	    			var cols = new Array();
	    			var colData = new Object();
	    			cols.push({'field' : 'ZYID', 'title' : '${patientZyTitle}', 'width' : 100, 'formatter' : function(value,rec){ return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="drugResistant.patientInfo(\'' + rec.ZYID + '\')">' + rec.ZYID + '</a>'].join('');}});
	    			cols.push({'field' : 'PATIENT_NAME', 'title' : '姓名', 'width' : 100,
			        	  formatter:function(value,row,index){
						  	  return [(row.PATIENT_NAME+'('+row.SEX+','+row.AGE+'岁)')].join('');
						  }
			        });
	    			cols.push({'field' : 'SUBMIT_AT', 'title' : '送检日期', 'width' : 70});
	    			cols.push({'field' : 'PATHO_NAME', 'title' : '检出病原体', 'width' : 110});
	    			cols.push({'field' : 'SUBMIT_DEPT', 'title' : '送检科室', 'width' : 120});
	    			cols.push({'field' : 'ITEM_TYPE_NAME', 'title' : '送检项目', 'width' : 130});
	    			cols.push({'field' : 'RESULT_DATE', 'title' : '检出日期', 'width' : 70});
	    			if (json && json.length > 0) {
	    				var row = json[0];
	    				for (var key in row) {
	    					if (/.*[\u4e00-\u9fa5]+.*$/.test(key)) {
	    						colData = new Object();
	    						colData.field = key;
		        				colData.title = key;
		        				colData.align = 'center';
		        				cols.push(colData);
	    					}
	    				}
	    			}
	    			columns.push(cols);
	    			//动态生成表头结束
	            	$('#' + drugResistant.panel).datagrid({
	    	            columns: columns,
	    	            data : json
	    	        });
	            }
			});
		}
	};

	$(function(){
		$('#' + drugResistant.panel).datagrid({
		    fit: true,
		    nowrap: true,
		    autoRowHeight: true,
		    striped: true,		    
		    remoteSort: false,
		    singleSelect: true,
		    border:false,
	        rownumbers:true,
	        onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
               e.preventDefault(); //阻止浏览器捕获右键事件
               $('#drug_rightMenu').menu('show', {//显示右键菜单
                   left: e.pageX, //在鼠标点击处显示菜单
                   top: e.pageY
               });
            }
		});
		
		drugResistant.queryList();
		
		$('#drug_rightMenu').menu({
	        onClick : function (item) {
	            var clickType = item.name;
	            switch(clickType) {
	            	case 'export_patient':
	            		drugResistant.exportList();
	            		break;
	            }
	        }
	    });
	});
</script>
</body>
</html>
