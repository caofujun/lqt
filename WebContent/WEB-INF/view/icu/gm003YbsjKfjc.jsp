<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>咳嗽、腹泻监控</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body>
		<div id="tb2" class="m_search">
			<span>登记时间</span>
			<input type="text" id="queryStartDate" value="${queryStartDate}" class="Wdate text" style="width:80px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />~
    		<input type="text" id="queryEndDate" value="${queryEndTime}" class="Wdate text" style="width:80px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			<span>科室：</span>
			<div class="select_del"><input id="deptId"  style="width:120px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a></div>	
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="hygieneQuery.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="hygieneQuery.edit('','${deptId}','登记')" ><i class="icon iconfont">&#xe602;</i><span>登记</span></a>
				</div>				
			</div>
		</div>
		<div id="hygieneQueryPanel"></div>
		<script type="text/javascript">
		var hygieneQuery = {
				panel : 'hygieneQueryPanel',
				query : function () {
					autoTip.clear();
					$('#' + hygieneQuery.panel).datagrid({
			            queryParams: {
			            	'deptId': $('#deptId').combogrid('getValue'),
			            	'queryStartDate':$('#queryStartDate').val(),
			            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
			            }
			        });
				},
			    //编辑
			    edit:function(creationdate,deptId,title) {
			        Comm.dialogGlobal({
			        	url:"${webroot}/gm003Ybsj/f_view/kfjcEdit.shtml?deptId="+deptId+"&creationdate="+creationdate.substring(0,10),
			            title: title,
			            width:400,
			            parent:this
			        });
			    },
			    //删除
			    del:function(creationdate,deptId) {
			    	$.messager.confirm('提示', '确认删除该抗菌药物?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/gm003Ybsj/f_json/kfjcDelete.shtml',
			                        type: 'post',
			                        data: { creationdate: creationdate,deptId:deptId},
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											hygieneQuery.query();
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
			};
			$(document).ready(function () { 
				Csm.combogrid.dep({
					//【必传】控件名称
					id: 'deptId',
					ifenvoffice: '1',
					value : '${deptId}'
				});
				$('#'+hygieneQuery.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                queryParams: {
	                	'deptId': $('#deptId').combogrid('getValue'),
	                	'queryStartDate':$('#queryStartDate').val(),
	                	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59'
	                },
	                collapsible:true,
	                border:false,
	                url:'${webroot}/gm003Ybsj/f_json/kfjcPageQuery.shtml',
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[    
							{field:'deptId',title:'科室ID',sortable:true,width:120,hidden:true},
							{field:'typeId',title:'类型',sortable:true,width:120,hidden:true},
		                    {field:'creationdate',title:'登记时间',sortable:true,width:120,
								formatter:function(value,row,index){
									if(value){
										return value.substring(0,10);
									}
								}	
		                    },
		                    {field:'deptName',title:'科室名称',sortable:true,width:120},
		                    {field:'ywryksCount',title:'医务人员咳嗽',sortable:true,width:200},
		                    {field:'ywryfxCount',title:'医务人员腹泻',sortable:true,width:200},
		                    {field:'ywryfrCount',title:'医务人员发热',sortable:true,width:200},
		                    {field:'bhksCount',title:'病患咳嗽',sortable:true,width:200},
		                    {field:'bhfxCount',title:'病患腹泻',sortable:true,width:200},
		    				{field:'_operate',title:'操作',width:80,
								formatter:function(value,r){
									return ['<a href="javascript:hygieneQuery.edit(\'',r.creationdate,'\',\'',r.deptId,'\',\'','修改','\');" class="ico_editor" title="修改"></a>',
									'<a href="javascript:hygieneQuery.del(\'',r.creationdate,'\',\'',r.deptId,'\');" class="ico_del" title="删除"></a>'].join('');
								}
							}
		              	],
	                ],
	    	        rownumbers:true,
	    	        pagination:true,
	                toolbar:'#tb2',
	            });
			});
		</script>
	</body>
</html>
