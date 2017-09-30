<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>暴发预警记录</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="outbreak_rightMenu" class="easyui-menu" style="width: 100px;display: none;">
	    <div name="read" >已读</div>
	    <div name="un_read" >未读</div>
	    <div name="delete" data-options="iconCls:'icon-del'">删除</div>
	</div>
	<div id="tb" style="display: none;">
		<div class="m_search">
			<div class="m_search_first">
				<span>监测日期:</span>
				<input type="text" name="queryStartDate" id="queryStartDate" value="<fmt:formatDate value="${queryStartDate}" pattern="yyyy-MM-dd" />" class="Wdate text" style="width:80px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'queryEndTime\')}'})" />~
				<input type="text" name="queryEndTime" id="queryEndTime" value="<fmt:formatDate value="${queryEndDate}" pattern="yyyy-MM-dd" />" class="Wdate text" style="width:80px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'queryStartDate\')}'})" />
				<span>科室：</span>
				<div class="select_del">
					<input type="text" id="id_deptId" style="width: 100px;" />
					<a href="javascript:void(0);" title="清除" class="select_img_del" onclick="$('#id_deptId').combo('clear');"></a>
				</div>
				<select id="id_showId" class="easyui-combobox"  data-options="editable:false" style="width: 95px;" >
					<option value="">-项目-</option>
					<c:forEach items="${itemList}" var="item">
						<option value="${item.id}"><c:out value="${item.name}" /></option>
					</c:forEach>
				</select>
				<select id="id_readFlag" class="easyui-combobox" data-options="editable:false" style="width: 80px;">
					<option value="">-状态-</option>
					<option value="0" ${param.readFlag eq 0 ? 'selected="selected"' : ''}>未读</option>
					<option value="1" ${param.readFlag eq 1 ? 'selected="selected"' : ''}>已读</option>
					<option value="2" ${param.readFlag eq 2 ? 'selected="selected"' : ''}>已删除</option>
				</select>	
				<div class="n_btn_blue">
					<a href="javascript:void(0);" onclick="outbreak.query()"><i class="icon iconfont">&#xe688;</i><span>查询</span></a>
				</div>
			</div>
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="outbreak.exportExcel();"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
				</div>
			</div>
		</div>		
	</div>
	<div id="outbreakPanel"></div>
<script type="text/javascript">
	var outbreak = {
		panel : 'outbreakPanel',
		query : function () {
			autoTip.clear();
			$('#' + outbreak.panel).datagrid({
	            url: '${webroot}/by007Bfjl/f_json/findList.shtml',
	            queryParams: {
	            	'readFlag':$('#id_readFlag').combobox('getValue'),
	            	'showId':$('#id_showId').combobox('getValue'),
	            	'deptId':$('#id_deptId').combogrid('getValue'),
	            	'queryStartDate':$('#queryStartDate').val(),
	            	'queryEndDate':$('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59'
	            },
	            pageNumber : 1
	        });
		},
    	//删除
		del : function(id, index) {
           	$.ajax({
            	url: '${webroot}/by007Bfjl/f_json/updReadFlag.shtml',
                type: 'post',
                data: { id: id , readFlag : 2},
                dataType: 'json',
                success : function(json) {
					if (json.result==='success') {
                        $.messager.show({ title: '提示', msg: '删除成功！' });
                        $('#' + outbreak.panel).datagrid('updateRow',{
                        	index: parseInt(index),
        					row: {
        						readFlagName : json.data.readFlagName
        					}
        				});
			    	} else if(json.result === 'error') {
			    		$.messager.show({ title: '提示', msg: '删除异常！' });
			    	} else {
			    		$.messager.show({ title: '提示', msg: json.msg });
			    	}
				}
           	});
    	},
    	//病例列表
    	patientList : function(moniDate, deptId, id, breakType) {
    		$.ajax({
                url: '${webroot}/by007Show/f_json/getByShowId.shtml',
                type: 'post',
                data: { id : id },
                dataType: 'json',
                success : function(json) {
                	var url = '';
                	var title = '';
                	if (breakType == '1') {
	                	if ('0607003' == json.absoluteDetailName) {
	                		title = '血培养人数';
	                		url = webroot + '/by007Show/f_view/toBloodCultureList.shtml?dataType=trend&id=' + id + '&deptId=' + deptId + '&moniDate=' + moniDate;
	                	} else if ('pr_stat_by0007_detail' == json.absoluteDetailName) {
	                		title = '相同耐药谱';
	                		url = webroot + '/by007Show/f_view/toDrugResistantList.shtml?dataType=trend&id=' + id + '&deptId=' + deptId + '&moniDate=' + moniDate;
	                	} else if ('0607001' == json.absoluteDetailName) {
	                		title = '病人列表';
	                		url = webroot + '/by007Show/f_view/toPatientList.shtml?dataType=trend&id=' + id + '&deptId=' + deptId + '&moniDate=' + moniDate;
	                	}
                	} else {
                		if ('0607004' == json.relativeDetailName) {
	                		title = '血培养人数';
	                		url = webroot + '/by007Show/f_view/toBloodCultureList.shtml?dataType=contrast&id=' + id + '&deptId=' + deptId + '&moniDate=' + moniDate;
	                	} else if ('0607002' == json.relativeDetailName) {
	                		title = '病人列表';
	                		url = webroot + '/by007Show/f_view/toPatientList.shtml?dataType=contrast&id=' + id + '&deptId=' + deptId + '&moniDate=' + moniDate;
	                	}
                	}
                	if (url.length > 0) {
                		Comm.dialogGlobal({
     			        	url: url,
     			            title: title,
     			            width:960,
     			            height:500,
     			            type:"iframe",
     			            parent:this
     			        });
            	 	}
                }
    		});
    	},
    	//已读
		read : function(id, index) {
           	$.ajax({
            	url: '${webroot}/by007Bfjl/f_json/updReadFlag.shtml',
                type: 'post',
                data: { id: id , readFlag : 1},
                dataType: 'json',
                success : function(json) {
					if (json.result==='success') {
                        $.messager.show({ title: '提示', msg: '操作成功！' });
                        $('#' + outbreak.panel).datagrid('updateRow',{
                        	index: parseInt(index),
        					row: {
        						readFlagName : json.data.readFlagName,
        						auditName : json.data.auditName,
        						auditDate : json.data.auditDate
        					}
        				});
			    	} else if(json.result === 'error') {
			    		$.messager.show({ title: '提示', msg: '操作异常！' });
			    	} else {
			    		$.messager.show({ title: '提示', msg: json.msg });
			    	}
				}
           	});
    	},
    	//未读
		unRead : function(id, index) {
           	$.ajax({
            	url: '${webroot}/by007Bfjl/f_json/updReadFlag.shtml',
                type: 'post',
                data: { id: id , readFlag : 0},
                dataType: 'json',
                success : function(json) {
					if (json.result==='success') {
                        $.messager.show({ title: '提示', msg: '操作成功！' });
                        $('#' + outbreak.panel).datagrid('updateRow',{
                        	index: parseInt(index),
        					row: {
        						readFlagName : json.data.readFlagName,
        						auditDate : json.data.auditDate
        					}
        				});
			    	} else if(json.result === 'error') {
			    		$.messager.show({ title: '提示', msg: '操作异常！' });
			    	} else {
			    		$.messager.show({ title: '提示', msg: json.msg });
			    	}
				}
           	});
    	},
    	//导出
		exportExcel : function() {
			var readFlag = $('#id_readFlag').combobox('getValue'),
        		showId = $('#id_showId').combobox('getValue'),
        		deptId = $('#id_deptId').combogrid('getValue'),
        		queryStartDate = $('#queryStartDate').val(),
        		queryEndDate = $('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59';
			window.location.href = '${webroot}/by007Show/f_json/exportOutbreakExcel.shtml?readFlag=' + readFlag + '&showId=' + showId + '&deptId=' + deptId + '&queryStartDate=' + queryStartDate + '&queryEndDate=' + queryEndDate;
		}
	};

	$(document).ready(function () {
		$('#' + outbreak.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url:'${webroot}/by007Bfjl/f_json/findList.shtml',   
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        idField:'id',
	        queryParams: {
            	'queryStartDate':$('#queryStartDate').val(),
            	'queryEndDate':$('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59'
            },
	        columns:[ 
		       	[
					{field:'readFlagName',title:'状态',sortable:true,align:'center',width:45},
		            {field:'breakStartDate',title:'起始日期',sortable:true,align:'center',width:80},
		            {field:'breakEndDate',title:'截止日期',sortable:true,align:'center',width:80},
		            {field:'observeDay',title:'间隔天数',sortable:true,align:'center',width:50},		            
		            {field:'deptId',title:'科室编号',sortable:true,width:60},
		            {field:'deptName',title:'科室',sortable:true,width:120},
		            {field:'typeName',title:'出现疑似暴发',sortable:true,width:70},
		            {field:'breakCount',title:'出现例数',sortable:true,align:'center',width:55,
		            	formatter:function(value,row,index){
							return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="outbreak.patientList(\'' + row.moniDate + '\',\'' + row.deptId + '\',\'' + row.showId + '\',\'' + row.breakType + '\')">' + value + '</a>'].join('');
						}
		            },
		            {field:'observeLine',title:'统计P值',sortable:true,align:'center',width:60},
		            {field:'mulriple',title:'最少人数',sortable:true,align:'center',width:55},
		            {field:'moniDate',title:'监测日期',sortable:true,align:'center',width:80},
		            {field:'createDate',title:'计算时间',sortable:true,align:'center',width:110},
		            {field:'auditName',title:'审核人',sortable:true,width:70},
		            {field:'auditDate',title:'审核时间',sortable:true,align:'center',width:130},
		            {field:'breakTypeName',title:'暴发类型',sortable:true,align:'center',width:70}
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
                   $('#outbreak_rightMenu').menu('show', {//显示右键菜单
                       left: e.pageX, //在鼠标点击处显示菜单
                       top: e.pageY
                   }).data('id', rowData.id).data('index', rowIndex);
               }
           }
	    });
		
		$('#outbreak_rightMenu').menu({
	        onClick : function (item) {
	            var clickType = item.name;
	        	var id = $(this).data('id');
	        	var index = $(this).data('index');
	            switch(clickType) {
	            	case 'read':
	            		outbreak.read(id, index);
	            		break;
	            	case 'un_read':
	            		outbreak.unRead(id, index);
	        			break;
	            	case 'delete':
	            		outbreak.del(id, index);
	        			break;
	            }
	        }
	    });
		
		//科室
		Csm.combogrid.dep({
			id: 'id_deptId',
			ifcaseoffice: '1'
		});
	});
</script>
</body>
</html>
