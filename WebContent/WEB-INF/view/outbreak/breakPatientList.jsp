<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>暴发预警患者住院信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="break_rightMenu" class="easyui-menu" style="width: 100px;display: none;">
	    <div name="export_patient" data-options="iconCls:'icon-print'">导出表格</div>
	</div>
	<div id="breakPatientPanel"></div>
<script type="text/javascript">
	//parent.$('#${param.dialogId}').dialog('setTitle', '${title}');
	
	var breakPatient = {
		panel : 'breakPatientPanel',
		//患者档案
		patientInfo : function (zyid) {
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
		},
		//导出患者
		exportList : function() {
			window.location.href = '${webroot}/by007Show/f_json/exportPatientExcel.shtml?dataType=${param.dataType}&id=${param.id}&deptId=${param.deptId}&moniDate=${param.moniDate}&queryStartDate=${param.queryStartDate}&queryEndDate=${param.queryEndDate}';
		}
	};

	$(function(){
		$('#' + breakPatient.panel).datagrid({
		    fit: true,
		    nowrap: true,
		    autoRowHeight: true,
		    striped: true,		    
		    url:'${webroot}/by007Show/f_json/findPatientInfo.shtml?dataType=${param.dataType}&id=${param.id}&deptId=${param.deptId}&moniDate=${param.moniDate}&queryStartDate=${param.queryStartDate}&queryEndDate=${param.queryEndDate}',   
		    remoteSort: false,
		    singleSelect: true,
		    fitColumns: true,
		    border:false,
		    columns:[ 
		     	[
		          {field:'zyid',title:'${patientZyTitle}',sortable:true,width: 130,
		        	  formatter:function(value,rec){
			        		var visitId = '';
		        		  	if(rec.visitId != null && rec.visitId != ''){
		        		  		visitId = '('+ rec.visitId +')';
		        		  	}
							return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="breakPatient.patientInfo(\'' + rec.zyid + '\')">' + rec.${patientZyValue}+visitId + '</a>'].join('');
					    }
		          },
		          {field:'bedNo',title:'床号',sortable:true,width:40},
		          {field:'patientName',title:'患者',sortable:true,width:110,
		        	  formatter:function(value,row,index){
					  	  return [(row.patientName+'('+row.sex+','+row.age+row.ageUnit+')')].join('');}
		          },		         
				  {field:'deptName',title:'当前科室',sortable:true,width:130},
				  {field:'inHospAt',title:'入院日期',sortable:true,width:120,align:'center'},
		          {field:'inDeptName',title:'入院科室',sortable:true,width:130},
		          {field:'outAt',title:'出院日期',sortable:true,width:120,align:'center'},
		          {field:'outDeptName',title:'出院科室',sortable:true,width:130},
		          {field:'chargeDrName',title:'主管医生',sortable:true,width:80},
		          {field:'D',title:'原因',sortable:true,formatter:function(value,row,index){
		        	  if(!row.itemTypeName){
		        		  if(!row.tw){
		        			  $('#' + breakPatient.panel).datagrid('hideColumn', 'D');
		        			  return "";
		        		  }else{
		        			  return row.tw+"&#8451;("+row.recording_at+")";
		        		  }
		        	  }else{
			        	 return row.itemTypeName+"("+row.submiAt+")";
		        	  }
		        	  
		          }}
		      ]
		    ],
	        rownumbers:true,
	        onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
               e.preventDefault(); //阻止浏览器捕获右键事件
          	   //$(this).datagrid("clearSelections"); //取消所有选中项
               //$(this).datagrid("selectRow", rowIndex); //根据索引选中该行
               $('#break_rightMenu').menu('show', {//显示右键菜单
                   left: e.pageX, //在鼠标点击处显示菜单
                   top: e.pageY
               }).data('zyid', rowData.zyid);
            }
		});
		
		$('#break_rightMenu').menu({
	        onClick : function (item) {
	            var clickType = item.name;
	        	//var zyid = $(this).data('zyid');
	            switch(clickType) {
	            	case 'export_patient':
	            		breakPatient.exportList();
	            		break;
	            }
	        }
	    });
	});
</script>
</body>
</html>
