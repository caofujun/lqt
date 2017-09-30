<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>员工管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="doctorPanel"></div>
	<div id="tb" class="m_search" >
		<span>科室:</span>
		<input type="hidden" id="unit" value="${doctor.hospId}"/>
		<div class="select_del"><input id="dep" name="depId" style="width:120px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#dep').combo('clear');"></a></div>
		<nis:select dictcode="doctor_type" headerKey="" headerValue="员工类型" id="cclass" cssCls="easyui-combobox"/>
		<input type="text" id="searchString" class="auto-tip" data-tip="请输入员工姓名或工号" />
		<div class="n_btn_blue">
			<a href="javascript:;" onclick="doctor.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>		
		<div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="doctor.edit(undefined,undefined,'新增')" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>	
	</div>

	<script type="text/javascript">
			var doctor = {
				panel:'doctorPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+doctor.panel).datagrid({
			            queryParams: {
			                'deptId': $('#dep').combogrid('getValue'),
			                'cclass': $('#cclass').combogrid('getValue'),
			                'searchString': $('#searchString').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(id, deptId, title) {
			    	if(id===undefined) id = '';
			    	Comm.dialogGlobal({
			        	url:"${webroot}/doctor/f_view/toedit.shtml?hospId=${doctor.hospId}&deptId="+deptId+"&id=" + id,
			            title: title,
			            parent:this,
			            width:650
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该员工?', function (r) {
			        	if (r) {
			        		var curRow = $('#'+doctor.panel).datagrid('getSelected');
			        		var deptId = curRow.deptId;
			            	$.ajax({
		                        url: '${webroot}/doctor/f_json/delete.shtml',
		                        type: 'post',
		                        data: {hospId:'${doctor.hospId}',deptId:deptId,doctorId:curRow.employeeId },
		                        dataType: 'json',
		                        success : function(json) {
									if(json.result==='success') {
							    		doctor.query();
		                                $.messager.show({ title: '提示', msg: '删除成功！' });
							    	} else if(json.result === 'error') {
							    		$.messager.show({ title: '提示', msg: '系统异常！' });
							    	} else {
							    		$.messager.show({ title: '提示', msg: json.msg });
							    	}
								}
			            	});
			        	}
			    	});
			    },
			    //重置密码
			    resetPwd : function(id) {
			    	$.messager.confirm('提示', '确认给该员工重置密码为：${defaultPwd} ?', function (r) {
			        	if (r) {
			        		var curRow = $('#'+doctor.panel).datagrid('getSelected');
			        		var deptId = curRow.deptId;
			            	$.ajax({
		                        url: '${webroot}/doctor/f_json/resetPassword.shtml',
		                        type: 'post',
		                        data: {hospId:'${doctor.hospId}',deptId:deptId,doctorId:curRow.employeeId },
		                        dataType: 'json',
		                        success : function(json) {
									if(json.result==='success') {
							    		doctor.query();
		                                $.messager.show({ title: '提示', msg: '重置成功！' });
							    	} else if(json.result === 'error') {
							    		$.messager.show({ title: '提示', msg: '系统异常！' });
							    	} else {
							    		$.messager.show({ title: '提示', msg: json.msg });
							    	}
								}
			            	});
			        	}
			    	});
			    },
			    //分配科室权限
			    grantDep : function(id) {
			    	Comm.dialogGlobal({
			        	url:"${webroot}/doctor/f_view/toSeldep.shtml?id=" + id,
			            title: '员工科室权限设置',
			            width:600,
			            height: 450,
			            type: 'iframe'
			        });
			    }
			};

			$(document).ready(function () { 
				Csm.combogrid.dep({
					//【必传】控件名称
					id: 'dep',
					//【可选参数】下拉列表的默认value，不传则没有默认值；
					value: '${doctor.deptId}',
					//【可选参数】不传默认区session的医院ID
					hospId: '${doctor.hospId}',
					//【可选参数】1：回调,回调方法setDep()，0：不回调，不传默认回调
					//callback: '0',
				});

				$('#'+doctor.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/doctor/f_json/pageQuery.shtml',
		            queryParams: {
		                'deptId': '${doctor.deptId}'
		            },
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'employeeId',title:'员工工号',sortable:true,width:80},
		                    {field:'employeeName',title:'员工姓名',sortable:true,width:80},
		                    {field:'showDeptId',title:'所属科室',sortable:true,width:180},
		                    {field:'drLinetypeName',title:'医生线别',sortable:true,width:60,hidden:true},
		                    {field:'showCclass',title:'员工类型',sortable:true,width:60},
		                    {field:'_showDesc',title:'标签',sortable:true,width:300,
								formatter:function(value,r){
									var showDesc = "";
									if("1" == r.operDoc){
										showDesc += '手术医生&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.authMode){
										showDesc += '科室管理员&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									return [showDesc].join('');
								}
		                    },
		                    {field:'_operate',title:'操作',width:80,
								formatter:function(value,r){
									return ['<a href="javascript:doctor.edit(\'',r.employeeId,'\',\'',r.deptId,'\',\'修改\');" class="ico_editor" title="修改"></a>',
											'<a href="javascript:doctor.grantDep(\'',r.employeeId,'\');" class="ico_view" title="员工业务科室授权"></a>',
											'<a href="javascript:doctor.del(\'',r.employeeId,'\');" class="ico_del" title="删除"></a>',
											'<a href="javascript:doctor.resetPwd(\'',r.employeeId,'\');" class="ico_key" title="重置密码"></a>'].join('');
								}
							}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb'
	            });
			});
		</script>
</body>
</html>