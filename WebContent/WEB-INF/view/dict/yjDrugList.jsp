<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>预警抗菌药物</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	</head>
	<body>
		<div id="yjDrugPanel"></div>
		<div id="tb2" class="m_search">
<!-- 			抗生素分类：<input name="drugTypeid" id="id_drugTypeid2"> -->
			<input type="text" class="auto-tip" data-tip="药物名称/编码" id="searchString1"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="st012Kjyw.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="st012Kjyw.edit('','新增抗菌药物')" ><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<script type="text/javascript">
			var st012Kjyw = {
				panel : 'yjDrugPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+st012Kjyw.panel).datagrid({
			            queryParams: {
// 			                'drugTypeid': $('#id_drugTypeid2').combogrid('getValue'),
			                'searchString': $('#searchString1').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/st012Kjyw/f_view/toeditSt012Kjyw.shtml?id=" + id,
			            title: title,
			            width:650,
			            parent:this
			        });
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该抗菌药物?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/st012Kjyw/f_json/deleteSt012Kjyw.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											st012Kjyw.query();
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
			  //更新药物线别
			    updDrugLine : function(obj) {
			    	var drugLine = $(obj).combobox('getValue');
			    	var id = $(obj).attr('id');
			    	$.ajax({
			            url: '${webroot}/st012Kjyw/f_json/updDrugLine.shtml',
			            type: 'post',
			            data: { drugLine : drugLine, id: id },
			            dataType: 'json',
			            success : function(json) {
							if(json.result==='success') {
			                    $.messager.show({ title: '提示', msg: '修改成功！' });
					    	} else if(json.result === 'error') {
					    		$.messager.show({ title: '提示', msg: '修改异常！' });
					    	} else {
					    		$.messager.show({ title: '提示', msg: json.msg });
					    	}
						}
			    	});
			    }
			};
			$(document).ready(function () { 
				$('#'+st012Kjyw.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                border:false,
	                url:'${webroot}/st012Kjyw/f_json/pageQuery.shtml',
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[    
		                    {field:'drugId',title:'抗菌药物编号',sortable:true,width:120,},
		                    {field:'drugName',title:'抗菌药物名称',sortable:true,width:120},
		                    {field:'drugLine',title:'级别',sortable:true,width:200,
		    					formatter:function(value,row,index) {
		    						return '<nis:select dictcode="drug_line" headerKey="" headerValue="-请选择-" exp="drugid=\"' + row.id + '\" defval=\"' + row.drugLine + '\"" />';
		    					}
		    				},
		    				{field:'_operate',title:'操作',width:100,
								formatter:function(value,r){
									return ['<a href="javascript:st012Kjyw.edit(\'',$.trim(r.id),'\',\'修改\');" class="ico_editor" title="修改"></a>',
									'<a href="javascript:st012Kjyw.del(\'',$.trim(r.id),'\');" class="ico_del" title="删除"></a>'].join('');
								}
							}
		              	],
	                ],
	    	        rownumbers:true,
	    	        pagination:true,
	                toolbar:'#tb2',
	      		  	onLoadSuccess : function(data){
	      			  for (var i = 0; i < data.rows.length; i++) {
	      				  var row = data.rows[i];
	      				  var obj = $("select[drugid='" + row.id + "']");
	      				  if (obj) {
	      					  obj.attr('id', row.id);
	      					  obj.combobox({
	      						  value : obj.attr('defval'),
	      						  editable : false,
	      						  onSelect : function(record) {
	      							st012Kjyw.updDrugLine(this);
	      						  }
	      					  });
	      				  }
	      			  }
	      		  }
	            });
// 				//抗菌药物分类
// 				Csm.combogrid.kjywfl({
// 					id: 'id_drugTypeid2',
// 					value:'${antibiosisDrug.drugTypeid}'
// 				});
			});
		</script>
	</body>
</html>
