<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>手卫生用品监测</title>
		<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="tb" class="m_search" style="display: none;">
			<span <c:if test="${!(userType eq 'hospital')}"> style="display:none"</c:if>>
			<span>科室:</span>
			<div class="select_del">
				<input type="text" id="deptId" style="width: 150px;" />
				<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a>
			</div>	
			</span>
			<span class="ml5">人员类型:</span>
			<div class="select_del">
				<select id="personal_type" class="easyui-combobox"  data-options="editable:false" style="width: 100px;">
					<option value=""> </option>
					<c:forEach items="${personalType}" var="pt" >
						<option value="${pt.dictName}">${pt.dictName}</option>
					</c:forEach>
				</select>
				<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#personal_type').combo('clear');"></a>
			</div>
			<span class="ml5">调查时期:</span>
			<input type="text" id="queryStartDate" value="${queryStartDate}" class="Wdate text" style="width:80px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'queryEndTime\')}',dateFmt:'yyyy-MM-dd'})" />~
    		<input type="text" id="queryEndTime" value="${queryEndDate}" class="Wdate text" style="width:80px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'queryStartDate\')}',dateFmt:'yyyy-MM-dd'})" />
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="HHC.query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="HHC.add();"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
				<!-- div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="javascript:void(0);"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
				</div -->
			</div>
			
			
			
		</div>
		<div id="handsHygieneCompliancePanel"></div>
		<script type="text/javascript">
			var HHC = {
					panel : 'handsHygieneCompliancePanel',
					query : function() {
						$('#'+HHC.panel).datagrid({
				            url:'${webroot}/sw002Ycxdc/f_json/pageQuery.shtml',
				            queryParams: {
				            	'queryStartDate':$('#queryStartDate').val(),
				            	'queryEndDate':$('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59',
				            	<c:if test="${isqx eq 1}">		
							        <c:if test="${!(userType eq 'hospital')}">
			            	    		'gcUserId':'${user.userId}',
									</c:if>
					            	<c:if test="${(userType eq 'hospital')}">
					            	    'dcWardId':$('#deptId').combogrid('getValue'),
									</c:if>
				            	</c:if>
				            	<c:if test="${isqx eq 0}">					    
				            	    'dcWardId':$('#deptId').combogrid('getValue'),
				            	</c:if>
				            	'personalType':$('#personal_type').combogrid('getValue')
				            }
						});
					},
					add : function(){
						Comm.dialogGlobal({
				        	url:"${webroot}/sw002Ycxdc/f_view/toadd.shtml",
				            title: '新增手卫生调查',
				            width:1000,
				            height:700,
				            type:"iframe",
				            parent:this,
				            closable: false
				        });
					},
					edit : function(dcId){
						Comm.dialogGlobal({
				        	url:"${webroot}/sw002Ycxdc/f_view/toedit.shtml?dcId="+dcId,
				            title: '修改手卫生调查',
				            width:1000,
				            height:700,
				            type:"iframe",
				            parent:this
				        });
					},
					del : function(dcId){
						if(dcId){
							$.messager.confirm('提示', '确认删除?', function (r) {
								if (r) {
					            	$.ajax({
					                    url: '${webroot}/sw002Ycxdc/f_json/delData.shtml',
					                    type: 'post',
					                    data: { 'dcId': dcId },
					                    dataType: 'json',
					                    success : function(json) {
											if(json.result==='success') {
					                            $.messager.show({ title: '提示', msg: json.msg });
												HHC.query();
									    	} else {
									    		$.messager.show({ title: '提示', msg: json.msg });
									    	}
										}
					            	});
					        	}
							});
						}
					},
					message:function(msg){
						//为了方便调用
						$.messager.show({ title: '提示', msg: msg });
					}
			}
			
			$(document).ready(function () {
				
				//科室
				Csm.combogrid.dep({
					id: 'deptId',
					flag: '1',
					ifmzoffice:'${handDepFlag}',
					//【可选参数】下拉列表的默认value，不传则没有默认值；
					<c:if test="${isqx eq 0}">
					<c:if test="${!(userType eq 'hospital')}">
					value: '${user.depNo}',
					</c:if>
					</c:if>
					callback: '0'
				});
				
				
				$('#' + HHC.panel).datagrid({
					fit: true,
			        nowrap: true,
			        autoRowHeight: true,
			        striped: true,
			        fitColumns: true,
			        remoteSort: false,
			        singleSelect: true,
			        border:false,
			        url: '${webroot}/sw002Ycxdc/f_json/pageQuery.shtml',
			        queryParams: {
		            	'queryStartDate':$('#queryStartDate').val(),
		            	'queryEndDate':$('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59',
		            	<c:if test="${isqx eq 1}">		
				        <c:if test="${!(userType eq 'hospital')}">
            	    		'gcUserId':'${user.userId}',
						</c:if>
		            	<c:if test="${(userType eq 'hospital')}">
		            	    'dcWardId':$('#deptId').combogrid('getValue'),
						</c:if>
		            	</c:if>
		            	<c:if test="${isqx eq 0}">					    
		            	    'dcWardId':$('#deptId').combogrid('getValue'),
		            	</c:if>
		            	'personalType':$('#personal_type').combogrid('getValue')
		            },
			        columns:[ 
				       	[
				       	 	{field:'dcId',title:'djid',hidden:true},
				            {field:'dcDate',title:'调查时期',sortable:true,align:'center',width:88,formatter:function(value,row,index){
				            	return row.dcDate.substring(0,10);
				            }},
				            {field:'dcWardId',title:'科室ID',hidden:true},
				            {field:'dcWard',title:'调查科室',sortable:true,align:'center',width:88},
				            {field:'personalType',title:'人员类型',sortable:true,align:'center',width:88},
				            {field:'gcTime',title:'观察时间（分）',sortable:true,align:'center',width:88},
				            {field:'sjs',title:'时机数',sortable:true,align:'center',width:88},
				            {field:'gcUserId',title:'观察者ID',hidden:true},
				            {field:'gcUsername',title:'观察者',sortable:true,align:'center',width:88},
				            {field:'o',title:'操作',align:'center',width:88,
				            	formatter:function(value,rec){
									return ['<a href="javascript:HHC.edit(\'' + rec.dcId + '\')" class="ico_editor" title="修改"></a>' + 
									        '<a href="javascript:HHC.del(\'' + rec.dcId + '\')" class="ico_del" title="删除"></a>'].join('');
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