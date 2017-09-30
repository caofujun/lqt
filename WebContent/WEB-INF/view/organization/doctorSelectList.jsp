<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="doctorPanel"></div>
	<div id="tb" class="m_search" >
		<input type="hidden" id="unit" value="${user.unitId}"/>
		<input type="text" name="depNo" id="dep" class="text" style="width: 170px;" value="${doctor.depNo}"/>
		<span class="standard_select">
			<span class="select_shelter">	
				<nis:select dictcode="doc_position" headerKey="" headerValue="医生职称" id="position"/>
			</span>
		</span>
		<input type="text" id="searchString" class="auto-tip text" data-tip="请输入医生名称或工号" /> 
		<input type="button" class="btn_search" name="search" onclick="doctor.query()" value="查询" />
		<input type="button" class="btn_add" value="添加" onclick="doctor.setToDep()" />
	</div>

	<script type="text/javascript">
		var ids="";
		var doctor = {
			panel:'doctorPanel',
			//查询
			query : function() {
				autoTip.clear();
		        $('#'+doctor.panel).datagrid({
		            queryParams: {
		                'searchString': $('#searchString').val(),
		                'unitId': $('#unit').val(),
		                'depNo': $('#dep').val(),
		                'zcid': $('#position').val(),
		                'depId':'${depId}'
		            },
		            pageNumber: 1
		        });
		    },
		    setToDep:function(){
		    	var curRow = $('#'+doctor.panel).datagrid('getSelections');
		    	if (curRow.length>0) {
		        	getSelectIds(curRow);
		        	$.ajax({
                        url: '${webroot}/depDocLink/f_json/save.shtml',
                        type: 'post',
                        data: {unitId:'${user.unitId}',depId:'${depId}',doctorIds: ids},
                        dataType: 'json',
                        success : function(json) {
							if(json.result==='success') {
								parent.depDocLink.query();
								parent.Comm.dialogClose('${param.dialogId}');
								parent.$.messager.show({ title: '提示', msg: '添加成功！' });
					    	} else if(json.result === 'error') {
					    		parent.$.messager.show({ title: '提示', msg: '系统异常！' });
					    	} else {
					    		parent.$.messager.show({ title: '提示', msg: json.msg });
					    	}
						}
	            	});
		    	}else parent.$.messager.alert('提示', '请选择需要添加的医生.');
		    }
		};

		$(document).ready(function () { 
			Csm.select.dep({
				id: 'dep',
				unitId: 'unit'
			});
			
			$('#'+doctor.panel).datagrid({
				nowrap: true,  
                rownumbers: true,  
                animate:true,
                fitColumns:true,
                fit:true,  
                collapsible:true, 
                url:'${webroot}/doctor/f_json/nosetDepQuery.shtml?unitId=${user.unitId}&depId=${depId}',   
                singleSelect: false,
                idField:'doctorId',
                frozenColumns:[[  
  	                    {field:'doctorId',checkbox:true}
                  	]],  
                columns:[
                	[
	                    {field:'docNo',title:'工号',sortable:true,width:100},
	                    {field:'doctorName',title:'医生姓名',sortable:true,width:100},
	                    {field:'showZcName',title:'职称',sortable:true,width:100},
	                    {field:'headship',title:'职务',sortable:true,width:100},
	                    {field:'showIsExpert',title:'专家',sortable:true,width:100},
	                    {field:'docSpec',title:'专业',sortable:true,width:100},
	                    {field:'showDepName',title:'所属科室',sortable:true,width:100}
	                ]
                ],
                pagination:true,
                rownumbers:true,
                toolbar:'#tb'
            });
		});
		
		function getSelectIds(selectRow){
        	for(var i=0;i<selectRow.length;i++){
        		ids=ids+selectRow[i].doctorId+",";
        	}
        	return ids;
		}
		
	</script>
</body>
</html>