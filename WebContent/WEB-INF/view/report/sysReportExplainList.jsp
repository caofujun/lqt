<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>报表说明维护</title>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
	</head>
	<body>
		<div id="sysReportExplainPanel"></div>
		<div id="tb" class="m_search">
		<select id="reportType" style="width:120px" class="easyui-combobox"></select> 
		<div class="n_btn_blue">
			<a href="javascript:;" onclick="sysReportExplain.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>		
		<div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="sysReportExplain.edit(undefined,'新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>			
		</div>
		
		<script type="text/javascript">
			$(document).ready(function () {
				$('#reportType').combobox({
					url:'${webroot}/sysDict/f_json/findTop.shtml?dictTypeCode=report_type',
				    valueField:'dictCode',
				    textField:'dictName',
					headerKey: '',
					headerValue: '请选择'
				})
				$('#'+sysReportExplain.panel).datagrid({
	                nowrap: true,
	                fit:true,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/reportExplain/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'reportShowName',title:'报表名称',sortable:true,width:120},
		                    {field:'formulaTitle',title:'公式名称',sortable:true,width:120},
		                    {field:'reportFormula',title:'报表公式',sortable:true,width:120},
		                    {field:'reportRule',title:'统计规则',sortable:true,width:120},
		                    {field:'reportDesc',title:'说明',sortable:true,width:120},		              
		                    {field:'_operate',title:'操作',width:60,
								formatter:function(value,r){
									return ['<a href="javascript:sysReportExplain.edit(\'',r.reportId,'\',\'修改\');" class="ico_editor" title="修改"></a>',
									'<a href="javascript:sysReportExplain.del(\'',r.reportId,'\');" class="ico_del" title="删除"></a>'].join('');
								}
							}
		                    
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb'
	            });
			});
			var sysReportExplain = {
				panel : 'sysReportExplainPanel',
				//查询
				query : function() {
			        $('#'+sysReportExplain.panel).datagrid({
			            url: '${webroot}/reportExplain/f_json/pageQuery.shtml',
			            queryParams: {
			                'reportType': $('#reportType').combogrid('getValue')
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit : function(reportId,title) {
		            //通过弹出窗口的方式打开页面
		            Comm.dialogGlobal({
		                url:"${webroot}/reportExplain/f_view/toedit.shtml?id=" + reportId,
		                title: title,
		                width:700,
		                height:485,
		                type:'iframe',
		                parent:this
		            });
			    },
			    //删除
			    del : function(reportId) {
			            $.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
			                if (r) {
			                    $.ajax({
			                        url: '${webroot}/reportExplain/f_json/delete.shtml',
			                        type: 'post',
			                        data: { 'id': reportId },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result=='success') {
								    		$('#'+sysReportExplain.panel).datagrid("reload");
			                                $('#'+sysReportExplain.panel).datagrid('unselectAll');
			                                $.messager.show({ title: '提示', msg: '删除成功！' });
								    	}
								    	else $.messager.show({ title: '提示', msg: '删除失败！' });
									}
			                    });
			                }
			            })
			    }
			};
		</script>
	</body>
</html>
