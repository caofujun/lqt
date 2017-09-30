<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form id="saveAccredit" method="post">
	<input type="hidden" name="userId" value="${param.employeeId}">
	<input type="hidden" name="deptId" value="${param.deptId}">
		<div style="width:100%;height:350px;overflow: hidden;">
			<div class="easyui-tabs" data-options="fit:true,plain:true,border:false">
				<div title="角色权限" style="overflow:hidden" style="padding:10px;">
					<div id="roleAccreditPanel"></div>
		        </div>
		        <div title="业务科室权限" style="overflow:hidden" style="padding:10px;">
		        	<div id="tb_deptAccredit"><span>&nbsp;&nbsp;业务科室：</span><input type="text" id="id_deptId" style="width: 133px;" /></div>
		        	<div id="deptAccreditPanel"></div>
		        </div>
		        <div title="监测项目权限" style="overflow:hidden" style="padding:10px" >
		        	<div id="jcxmAccreditPanel"></div>
		        </div>
			</div>
		</div>
</form>
<div class="footer dialog_footer">
	<div class="footer_btn">
		<div class="n_btn_blue">
			<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#saveAccredit').submit();"  class="no_ico"><span>确认</span></a>
		</div>
		<div class="n_btn_grey">
			<a href="javascript:;" onclick="parent.Comm.dialogClose('${param.dialogId}')" class="no_ico"><span>取消</span></a>
		</div>
	</div>	
</div>
<script type="text/javascript">
	var accredit = {
		rolePanel : 'roleAccreditPanel',
		deptPanel : 'deptAccreditPanel',
		jcxmPanel : 'jcxmAccreditPanel',
		//全选/全不选
		checkAll : function(className) {
			if ($('#id_' + className).val() == 0) {
				$("." + className).prop("checked", true);
				$('#id_' + className).val(1);
			} else {
				$("." + className).prop("checked", false);
				$('#id_' + className).val(0);
			}
		},
		check_all : function(className) {
			if ($('#id_' + className).is(':checked')) {
				$('#id_' + className).prop("checked", true);
				$("." + className).prop("checked", true);
			} else {
				alert('OK2');
				$("." + className).prop("checked", false);
			}
			//$("." + className).prop("checked", $('#id_' + className).prop("checked"));
		}
	};
	$(document).ready(function () { 
		window.setTimeout(function() {
			Comm.form({
				id : 'saveAccredit',
				url : '${webroot}/hw008Xmsq/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				onLoading : function () {
					$.messager.progress({
						text : '正在提交中....',
						interval : 200
					});
					//$('#changeFormSubmitBtn').hide();
				},
				success : function(json) {
					$.messager.progress('close');
					//$('#changeFormSubmitBtn').show();
					if (json.result === 'success') {
						parent.$.messager.show({ title: '提示', msg: '操作成功！' });
						//刷新父页面列表数据
						var parentObject = parent.Comm.getObjectCache();
						parentObject.query(json.data);
						parent.Comm.dialogClose('${param.dialogId}');
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
		}, 100);
		
		//角色权限
		$('#'+accredit.rolePanel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: true,
            striped: true,
            fitColumns: true,
            collapsible:true,
            url:'${webroot}/hw016Role/f_json/findListJoinHw018.shtml?userId=${param.employeeId}',
            remoteSort: false,
            singleSelect: true,
            border:false,
            columns:[
            	[
	              	{field:'userId',width:30,align:'center',
						formatter:function(value,row,index){
							return ['<input name="roleIdList" type="checkbox" class="checkbox_list" ' + (row.userId != null ? 'checked="checked"' : '') + ' value="' + row.roleId + '" />'].join('');
					    }
					},
                 	{field:'roleId',title:'角色编号',sortable:true,width:80},
                    {field:'roleName',title:'角色名称',sortable:true,width:170}
                ]
            ],
            pagination:false,
            rownumbers:true
        });
		
		//业务科室权限
		$('#'+accredit.deptPanel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: true,
            striped: true,
            fitColumns: true,
            collapsible:true,
            url:'${webroot}/hw009Kssq/f_json/findAccreditDept.shtml?userId=${param.employeeId}',
            remoteSort: false,
            singleSelect: true,
            idField: 'deptId',
            border:false,
            columns:[
            	[
	              	{title:'<span title="全选" style="cursor:pointer;" onclick="accredit.checkAll(\'dept_id_list\')">全选<input type="hidden" id="id_dept_id_list" value="0" /></span>',field:'userId',width:30,align:'center',
						formatter:function(value,row,index){
							return ['<input name="deptIdList" type="checkbox" class="checkbox_list dept_id_list" ' + (row.userId != null ? 'checked="checked"' : '') + ' value="' + row.deptId + '" />'].join('');
					    }
					},
					{field:'deptId',title:'科室编号',sortable:true,width:80},
                    {field:'deptName',title:'科室名称',sortable:true,width:170}
                ]
            ],
            rownumbers:true,
            toolbar:'#tb_deptAccredit'
        });
		
		//业务科室
		Csm.combogrid.dep({
			id: 'id_deptId',
			ifenvoffice: '1',
	        onClickRow : function(index,row) {
	        	$('#'+accredit.deptPanel).datagrid('selectRecord', row.deptId);
	        }
		});
		
		/* $('#id_deptId').combogrid({
			delay: 1000, 
		    mode: 'remote',
		    loadMsg : '正在查询中...',
	        idField:'deptId',
	        textField:'deptName',
	        panelWidth: 260,
	        panelHeight: 300,
	        border:false,
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
	        ],
	        onClickRow : function(index,row) {
	        	$('#'+accredit.deptPanel).datagrid('selectRecord', row.deptId);
	        }
		}); */
		
		//项目权限
		$('#'+accredit.jcxmPanel).datagrid({
            fit: true,
            nowrap: true,
            autoRowHeight: true,
            striped: true,
            fitColumns: true,
            collapsible:true,
            url:'${webroot}/hw008Xmsq/f_json/findXmsqList.shtml?userId=${param.employeeId}&deptId=${param.deptId}',
            remoteSort: false,
            singleSelect: true,
            border:false,
            columns:[ 
    	       	[
    				{field:'classId',title:'监测项目编号',sortable:true,width:80,rowspan:2,
    					formatter:function(value,row,index){
    						return ['<input name="hw008List[' + index + '].classId" value="' + row.classId + '" type="hidden" />' + 
    						        '<span>' + row.classId + '</span>'].join('');
    				    }
    				},
    				{field:'className',title:'监测项目名称',sortable:true,width:120,rowspan:2},
    				{title:'本科室',sortable:true,colspan:3},
    				{title:'其它科室',sortable:true,colspan:3}
    	        ],[  
    	            {title:'<span title="全选" style="cursor:pointer;" onclick="accredit.checkAll(\'selfView\')">查看<input type="hidden" id="id_selfView" value="0" /></span>',field:'selfView',width:50,align:'center',
    					formatter:function(value,row,index){
    						return ['<input class="checkbox_list selfView" name="hw008List[' + index + '].selfView" value="1" type="checkbox" ' + (row.selfView == 1 ? 'checked="checked"' : '') + ' />'].join('');
    				    }
    	            },
    	            {title:'<span title="全选" style="cursor:pointer;" onclick="accredit.checkAll(\'selfAdd\')">上报<input type="hidden" id="id_selfAdd" value="0" /></span>',field:'selfAdd',width:50,align:'center',
    					formatter:function(value,row,index){
    						return ['<input class="checkbox_list selfAdd" name="hw008List[' + index + '].selfAdd" value="1" type="checkbox" ' + (row.selfAdd == 1 ? 'checked="checked"' : '') + ' />'].join('');
    				    }
    	            },
    	            {title:'<span title="全选" style="cursor:pointer;" onclick="accredit.checkAll(\'selfResult\')">结果录入<input type="hidden" id="id_selfResult" value="0" /></span>',field:'selfResult',width:50,align:'center',
    					formatter:function(value,row,index){
    						return ['<input class="checkbox_list selfResult" name="hw008List[' + index + '].selfResult" value="1" type="checkbox" ' + (row.selfResult == 1 ? 'checked="checked"' : '') + ' />'].join('');
    				    }
    	            },
    	            {title:'<span title="全选" style="cursor:pointer;" onclick="accredit.checkAll(\'otherView\')">查看<input type="hidden" id="id_otherView" value="0" /></span>',field:'otherView',width:50,align:'center',
    					formatter:function(value,row,index){
    						return ['<input class="checkbox_list otherView" name="hw008List[' + index + '].otherView" value="1" type="checkbox" ' + (row.otherView == 1 ? 'checked="checked"' : '') + ' />'].join('');
    				    }
    	            },
    	            {title:'<span title="全选" style="cursor:pointer;" onclick="accredit.checkAll(\'otherAdd\')">上报<input type="hidden" id="id_otherAdd" value="0" /></span>',field:'otherAdd',width:50,align:'center',
    					formatter:function(value,row,index){
    						return ['<input class="checkbox_list otherAdd" name="hw008List[' + index + '].otherAdd" value="1" type="checkbox" ' + (row.otherAdd == 1 ? 'checked="checked"' : '') + ' />'].join('');
    				    }
    	            },
    	            {title:'<span title="全选" style="cursor:pointer;" onclick="accredit.checkAll(\'otherResult\')">结果录入<input type="hidden" id="id_otherResult" value="0" /></span>',field:'otherResult',width:50,align:'center',
    					formatter:function(value,row,index){
    						return ['<input class="checkbox_list otherResult" name="hw008List[' + index + '].otherResult" value="1" type="checkbox" ' + (row.otherResult == 1 ? 'checked="checked"' : '') + ' />'].join('');
    				    }
    	            }
    		    ]
            ],
            rownumbers:true
        });
	});
</script>