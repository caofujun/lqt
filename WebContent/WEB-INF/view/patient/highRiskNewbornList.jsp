<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>出入科记录</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="newborn_rightMenu" class="easyui-menu" style="width: 100px;display: none;">
	    <div name="edit_weight" data-options="iconCls:'icon-edit'">修改体重类型</div>
	    <div name="view_patient" onclick="">查看病人信息</div>
	</div>
	<div id="tb" style="display: none;min-width: 720px;">
		<table class="search_table">
			<tr>
				<th>
					<select id="id_neonatebw" style="width: 87px;">
						<option value="">-全部-</option>
						<option value="not_null">有体重</option>
						<option value="null">无体重</option>
					</select>
				</th>
				<th>
					<span>入院时间：</span>
				</th>
				<td>
					<input type="text" id="queryStartDate" value="${queryStartDate}" class="Wdate text" style="width:76px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />~
		    		<input type="text" id="queryEndDate" value="${queryEndDate}" class="Wdate text" style="width:76px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				</td>
				<td>
					<input type="text" id="id_patient_info" class="auto-tip" data-tip="${patientZyTitle}/姓名" title="${patientZyTitle}/姓名" style="width: 100px;" />
				</td>
				<td>
					&nbsp;&nbsp;
					<input type="button" class="btn_search" onclick="highRiskNewborn.query();" value="查询" />
				</td>
			</tr>
		</table>
	</div>
	<div id="newbornPanel"></div>
	<div id="id_edit_weight" class="easyui-dialog" title="新生儿体重" style="width:330px;height:173px;top:10px;padding:5px 5px;"
			data-options="closed: true,modal: true,buttons:'#id_edit_weight_button'" >			
		<div class="m_search" id="id_weight_div" style="display: none;">
			<table cellpadding="0" cellspacing="0" style="border-collapse:separate;border-spacing:10px;">

				<tr>
					<td>
						<span class="pro_text">请输入新生儿体重，单位为克：</span>
					</td>
					<td>
						<input id="id_deptCode" type="hidden" />
						<input id="id_zyid" type="hidden" />
						<input id="id_weight" type="text" style="width:60px" class="easyui-validatebox text" required="true" validType="number" />
					</td>
				</tr>
			</table>
		</div>
    </div>
     <div id="id_edit_weight_button" style="text-align: center;display: none;">
		<input type="button" class="button" id="changeFormSubmitBtn" onclick="highRiskNewborn.editWeight();" value=" 保存 ">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" class="button" data-options="iconCls:'icon-cancel'" style="background-color: #E5E5E5;color: #333;" onclick="$('#id_edit_weight').dialog('close');" value=" 关闭 " />
	</div>
<script type="text/javascript">
	var highRiskNewborn = {
		panel : 'newbornPanel',
		query : function() {
			autoTip.clear();
			$('#' + highRiskNewborn.panel).datagrid({
	            url: '${webroot}/st003Cryxxb/f_json/findNewbornList.shtml',
	            queryParams: {
	            	'neonatebw':$('#id_neonatebw').val(),
	            	'queryStartDate':$('#queryStartDate').val(),
	            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
	            	'searchString':$("#id_patient_info").val()
	            },
	            pageNumber : 1
	        });
		},
		gethMdDateStr : function(str) {
    		if (str && str.length > 10) {
    			return str.substring(0,10);
    		} else {
    			return '';
    		}
    	},
    	//打开修改新生儿体重
    	openEditWeight : function(zyid, deptCode) {
    		$('#id_weight').val('');
    		$('#id_zyid').val(zyid);
    		$('#id_deptCode').val(deptCode);
    		$('#id_weight_div').css('display','');
			$('#id_edit_weight').dialog('open');
    	},
    	//修改新生儿体重
    	editWeight : function() {
    		if (! $('#id_weight').validatebox('isValid')) {
    			$('#id_weight').focus();
    			return false;
    		}
    		$.ajax({
                url: '${webroot}/gm005Xsrtz/f_json/updWeight.shtml',
                type: 'post',
                data: { zyid : $('#id_zyid').val() , weight : $('#id_weight').val(), deptid : $('#id_deptCode').val() },
                dataType: 'json',
                success : function(json) {
                	if(json.result==='success') {
                		highRiskNewborn.query();
                		$('#id_edit_weight').dialog('close');
						parent.$.messager.show({ title: '提示', msg: '修改成功！' });
			    	} else {
			    		parent.$.messager.show({ title: '提示', msg: json.msg });
			    	}
				}
    		});
    	},
    	//查看患者信息
    	viewPatient : function (zyid) {
    		parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
    	}
	};

	$(function(){
		$('#' + highRiskNewborn.panel).datagrid({
	       fit: true,
	       nowrap: true,
	       autoRowHeight: true,
	       striped: true,
	       fitColumns: false,
	       url:'${webroot}/st003Cryxxb/f_json/findNewbornList.shtml',
	       queryParams: {
	           'queryStartDate':$('#queryStartDate').val(),
	           'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59'
           },
	       remoteSort: false,
	       singleSelect: true,
	       fitColumns: false,   
	       border:false,
	       columns:[ 
		       	[
		            {field:'weight',title:'体重',sortable:true,width:50},
		            {field:'neonatebwName',title:'体重类型',sortable:true,width:120},
		            {field:'patientName',title:'病人姓名',sortable:true,width:70},
		            {field:'birthDate',title:'出生日期',sortable:true,width:80,align:'center',
    					formatter:function(value,row,index){
    						return highRiskNewborn.gethMdDateStr(value);
    					}
    				},
		            {field:'zyid',title:'${patientZyTitle}',sortable:true,width:100},
		            {field:'visitId',title:'住院次数',sortable:true,width:50,align:'center'},
		            {field:'bedNo',title:'床号',sortable:true,width:50},
		            {field:'age',title:'年龄',sortable:true,width:50},
		            {field:'sex',title:'性别',sortable:true,width:40,align:'center'},
		            {field:'inHospAt',title:'入院时间',sortable:true,width:80,align:'center',
    					formatter:function(value,row,index){
    						return highRiskNewborn.gethMdDateStr(value);
    					}
    				},
		            {field:'inDeptName',title:'入院科室',sortable:true,width:110},
		            {field:'deptName',title:'当前科室',sortable:true,width:110},
		            {field:'outAt',title:'出院日期',sortable:true,width:80,align:'center',
    					formatter:function(value,row,index){
    						return highRiskNewborn.gethMdDateStr(value);
    					}
    				},
		            {field:'outDeptName',title:'出院科室',sortable:true,width:110},
		            {field:'chargeDrName',title:'主管医生',sortable:true,width:60}
		        ]
	       ],
	       pagination:true,
	       rownumbers:true,
	       toolbar:'#tb',
	       onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
               e.preventDefault(); //阻止浏览器捕获右键事件
               if (rowData != null) {
            	   $(this).datagrid("clearSelections"); //取消所有选中项
                   $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
                   $('#newborn_rightMenu').menu('show', {//显示右键菜单
                       left: e.pageX, //在鼠标点击处显示菜单
                       top: e.pageY
                   }).data('zyid', rowData.zyid).data('deptCode', rowData.deptCode);
               }
           }
	   });
		
		$('#newborn_rightMenu').menu({
	        onClick : function (item) {
	            var clickType = item.name;
	        	var zyid = $(this).data('zyid');
	        	var deptCode = $(this).data('deptCode');
	            switch(clickType) {
	            	case 'edit_weight':
	            		highRiskNewborn.openEditWeight(zyid, deptCode);
	            		break;
	            	case 'view_patient':
	            		highRiskNewborn.viewPatient(zyid);
	        			break;
	            }
	        }
	    });
	});
</script>
</body>
</html>
