<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>科室信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="depPanel"></div>
	<div id="tb" class="m_search" >
		<c:if test="${unitFlag=='1'}">
		<tr>
			<td class="t_title">院区：</td>
			<td>
				<div class="select_del">
				<input id="hospId" name="hospId" style="width:150px;"/>
				<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#hospId').combo('clear');"></a>
				</div>
			</td>
		</tr>
		</c:if>
		<nis:select id="deptTypeId" dictcode="dept_type" headerKey="" headerValue="科室类型"  cssCls="easyui-combobox"/>
		<select id="depPorp" name="depPorp" class="easyui-combobox" style="width:150px" >
			<option value="">--科室属性--</option>
			<option value="ifcaseoffice">病例监测科室</option>
			<option value="ifenvoffice">环境监测科室</option>
			<option value="ificu">ICU科室</option>
			<option value="ifchildoffice">新生儿科室</option>
			<option value="ifoperoffice">手术科室</option>
			<option value="ifbedicu">ICU床位检测科室</option>
			<option value="ifmzoffice">手卫生监测科室</option>
			<option value="flag">是否有效</option>
		</select>
		<input type="text" id="searchString" class="auto-tip" data-tip="请输入科室名称或编号" /> 
		<div class="n_btn_blue">
			<a href="javascript:;" onclick="dep.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>		
		<div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="dep.edit(undefined,'新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>	
	</div>

	<script type="text/javascript">
			var dep = {
				panel:'depPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+dep.panel).datagrid({
			            queryParams: {
			        		<c:if test="${unitFlag=='1'}">
			                'hospId': $('#hospId').combobox('getValue'),
			        		</c:if>
			                'deptTypeId': $('#deptTypeId').combogrid('getValue'),
			                'depPorp': $('#depPorp').combogrid('getValue'),
			                'searchString': $('#searchString').val()
			            }
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			    	Comm.dialogGlobal({
			        	url:"${webroot}/dep/f_view/toedit.shtml?id=" + id,
			            title: title,
			            parent:this,
			            width:500
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该科室?', function (r) {
			        	if (r) {
			            	$.ajax({
		                        url: '${webroot}/dep/f_json/delete.shtml',
		                        type: 'post',
		                        data: { depNo: id },
		                        dataType: 'json',
		                        success : function(json) {
									if(json.result==='success') {
							    		dep.query();
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
			    setDoctor:function(id){
					parent.menuInfo.clickMenu('医生信息','/doctor/f_view/index.shtml?hospId=${dep.hospId}&deptId=' + id,true,'A0103');
			    }
			};

			$(document).ready(function () {
				<c:if test="${unitFlag=='1'}">
					Csm.comboBox.unit({
						//【必传】控件名称
						id: 'hospId',
						flag: '1',
						callback: '0'
					});
				</c:if>
				
				$('#'+dep.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/dep/f_json/pageQuery.shtml',
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
	     					<c:if test="${unitFlag=='1'}">
		                    {field:'hospName',title:'院区',sortable:true,width:90},  
							</c:if>
		                    {field:'deptId',title:'科室编号',sortable:true,width:70},  
		                    {field:'deptName',title:'科室名称',sortable:true,width:140},
		                    {field:'deptClassifyName',title:'科室分类',sortable:true,width:50,align:'center'},
		                    {field:'deptTypeName',title:'科别',sortable:true,width:40,align:'center'},
		                    {field:'showStandDeptId',title:'对应标准科室',sortable:true,width:80,align:'center'},
		                    {field:'baseInfect',title:'现患率基准数',sortable:true,width:60,align:'center'},
		                    {field:'showOrder',title:'排序号',sortable:true,width:40,align:'center'},
		                    {field:'_flag',title:'是否有效',sortable:true,width:50,align:'center',
								formatter:function(value,r){
									var showDesc = "";
									if("1" == r.flag){
										showDesc += '有效';
									}else{
										showDesc += '无效';
									}
									return [showDesc].join('');
								}
		                    },
		                    {field:'_showDesc',title:'标签',sortable:true,width:350,
								formatter:function(value,r){
									var showDesc = "";
									if("1" == r.ifcaseoffice){
										showDesc += '病例监测科室&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.ifenvoffice){
										showDesc += '环境监测科室&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.ificu){
										showDesc += 'ICU科室&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.ifchildoffice){
										showDesc += '新生儿科室&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.ifoperoffice){
										showDesc += '手术科室&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.ifhospdept){
										showDesc += '住院科室&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.ifmzoffice){
										showDesc += '门诊科室&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.ifbedicu){
										showDesc += 'ICU床位监测&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.ifmzoffice){
										showDesc += '手卫生调查科室&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.hosinfectBaseper){
										showDesc += '医院感染基准科室&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									if("1" == r.outhosinfectBaseper){
										showDesc += '社区感染基准科室&nbsp;&nbsp;&nbsp;&nbsp;';
									}
									return [showDesc].join('');
								}
		                    },
		                    {field:'_operate',title:'操作',width:60,
								formatter:function(value,r){
									return ['<a href="javascript:dep.edit(\'',r.deptId,'\',\'修改\');" class="ico_editor" title="修改"></a>',
									'<a href="javascript:dep.setDoctor(\'',r.deptId,'\');" class="ico_setup" title="设置医生"></a>'].join('');
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