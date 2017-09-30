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
	<div id="blood_rightMenu" class="easyui-menu" style="width: 100px;display: none;">
	    <div name="export_patient" data-options="iconCls:'icon-print'">导出表格</div>
	</div>
	<div id="bloodCulturePanel"></div>
<script type="text/javascript">
	//parent.$('#${param.dialogId}').dialog('setTitle', '${title}');

	var bloodCulture = {
		panel : 'bloodCulturePanel',
		//患者档案
		patientInfo : function (zyid) {
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
		},
		//导出患者
		exportList : function() {
			window.location.href = '${webroot}/by007Show/f_json/exportBloodCultureExcel.shtml?dataType=${param.dataType}&id=${param.id}&deptId=${param.deptId}&moniDate=${param.moniDate}&queryStartDate=${param.queryStartDate}&queryEndDate=${param.queryEndDate}';
		},
		getNotNullStr : function(str) {
    		if (str == null) {
    			return '';
    		} else {
    			return str;
    		}
    	}
	};

	$(function(){
		$('#' + bloodCulture.panel).datagrid({
		    fit: true,
		    nowrap: true,
		    autoRowHeight: true,
		    striped: true,		    
		    url:'${webroot}/by007Show/f_json/findBloodCulturePatient.shtml?dataType=${param.dataType}&id=${param.id}&deptId=${param.deptId}&queryStartDate=${param.queryStartDate}&queryEndDate=${param.queryEndDate}',   
		    remoteSort: false,
		    singleSelect: true,
		    fitColumns: true,
		    border:false,
		    columns:[ 
		     	[
		          {field:'zyid',title:'${patientZyTitle}',sortable:true,width: 120,
		        	  formatter:function(value,rec){
		        		var visitId = '';
	        		  	if(rec.visitId != null && rec.visitId != ''){
	        		  		visitId = '('+ rec.visitId +')';
	        		  	}
						return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="bloodCulture.patientInfo(\'' + rec.zyid + '\')">' + value+visitId + '</a>'].join('');
					  }
		          },
		          {field:'patientName',title:'患者',sortable:true,width:120,
		        	  formatter:function(value,row,index){
					  	  return [(bloodCulture.getNotNullStr(row.patientName) + '(' + bloodCulture.getNotNullStr(row.sex) + ',' + bloodCulture.getNotNullStr(row.age+row.ageUnit) + ')')].join('');}
		          },		         
				  {field:'deptName',title:'当前科室',sortable:true,width:130},
				  {field:'submiAt',title:'送检日期',sortable:true,width:120,align:'center'},
		          {field:'submiDeptName',title:'送检科室',sortable:true,width:130},
		          {field:'testDate',title:'检出日期',sortable:true,width:120},
		          {field:'itemTypeName',title:'检验项目',sortable:true,width:150,
					styler: function(value,row,index) {
						return 'color:#FF6600;';
					}
		          },
		          {field:'pathoName',title:'检出结果',sortable:true,width:180,
					styler: function(value,row,index) {
						return 'color:#FF6600;';
					}
		          }
		      ]
		    ],
	        rownumbers:true,
	        onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
               e.preventDefault(); //阻止浏览器捕获右键事件
               $('#blood_rightMenu').menu('show', {//显示右键菜单
                   left: e.pageX, //在鼠标点击处显示菜单
                   top: e.pageY
               }).data('zyid', rowData.zyid);
            }
		});
		
		$('#blood_rightMenu').menu({
	        onClick : function (item) {
	            var clickType = item.name;
	        	//var zyid = $(this).data('zyid');
	            switch(clickType) {
	            	case 'export_patient':
	            		bloodCulture.exportList();
	            		break;
	            }
	        }
	    });
	});
</script>
</body>
</html>
