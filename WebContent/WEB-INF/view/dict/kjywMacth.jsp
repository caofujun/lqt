<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>抗菌药物匹配</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">	
	<div data-options="region:'west',collapsed:false,border:false" style="width:500px; border-right-width: 1px;">
		<div class="easyui-layout" data-options="fit:true">
		        <div data-options="region:'north',border:false,split:true,title:'抗菌药物'" style="height:240px;border-width: 0 0px 1px 0px;">
					<div style="margin:5px 10px 5px 10px;">
						<input type="text" class="auto-tip" style="width:105px" data-tip="药物名称/编码" id="searchString"/>
						<div class="n_btn_blue">				
							<a href="javascript:;"  onclick="zg010Kjyw.query1()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
						</div>		
						<div class="n_btn_blue" >
							<a href="javascript:;"  onclick="zg010Kjyw.match()" id="messager"><span>自动匹配</span></a>
						</div>
					</div>
		        	<div id="zg010KjywPanel"></div>
		        </div>
		        <div data-options="region:'center',border:false,title:'已匹配药物'">
		        	<div id="st012KjywPanel2"></div>
		        </div>
		 </div>
	</div>
	<div data-options="region:'center',border:false,title:'未匹配药物'">
		<div id="tb2" class="m_search datagrid-toolbar">
			<input type="text" class="auto-tip" style="width:105px" data-tip="药物名称/编码" id="searchString1"/>
			<div class="n_btn_blue">				
				<a href="javascript:;"  onclick="zg010Kjyw.query2()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>			
		</div>
		<div id="st012KjywPanel"></div>
	</div>
	</div>
		<script type="text/javascript">
		function seturl(syDrugId){
			 $('#'+zg010Kjyw.panel3).datagrid({
				 queryParams: {
		            	'isSys': 1,
		            	'syDrugId':syDrugId
		            },
			 });
		}
		var zg010Kjyw = {
			panel : 'zg010KjywPanel',
			panel2 : 'st012KjywPanel',
			panel3 : 'st012KjywPanel2',
			 //自动匹配
		    match : function (){
		    	autoTip.clear();
		    	var msg="关联提醒将通过名称进行精准名称关联匹配";
		    	$.messager.confirm("提示", msg, function (r) {
		    		if(r){
		    		//自动匹配，查询匹配的个数
		    		$.ajax({
	                    url: '${webroot}/st012Kjyw/f_json/match.shtml',
	                    type: 'post',
	                    dataType: 'json',
	                    success : function(json) {
	                    var countQueryMatched=json.data.YPP;
	                    var countQueryNotMatched=json.data.WPP;
	                    var countQueryNotMatch = Number(countQueryNotMatched)-Number(countQueryMatched);
	                    $.messager.alert('提示','已完成'+countQueryMatched+'条项目匹配，余'+"<font style='color:red;font-size:15px;'>" +countQueryNotMatch+ "</font>"+'条未匹配');
	                    zg010Kjyw.query2();
		    	  		zg010Kjyw.query1();
	                    }
	        		});	
		    		}
		        });
		    },
			//查询
			query1 : function() {
				autoTip.clear();
		        $('#'+zg010Kjyw.panel).datagrid({
		        	queryParams: {
		            	'searchString': $('#searchString').val()
			         },
		        });	       
			},
			//查询
			query2 : function() {
				autoTip.clear();	       
		        $('#'+zg010Kjyw.panel2).datagrid({
		        	 queryParams: {
		            	'isSys': 0,
		            	'searchString': $('#searchString1').val()
			         },
		        });	      
			},
			//查询
			query3 : function() {
				autoTip.clear();		    
		        $('#'+zg010Kjyw.panel3).datagrid({});
			},
			
		    //新增
		    add:function(title) {
		        Comm.dialog({
		        	url:"${webroot}/st012Kjyw/f_view/toedit.shtml?",
		            title: title,
		            width:450,
		            height:230
		        });
		    },			 
		    //编辑
		    edit:function(title) {
		    	//获取选中行值
		        var curRow = $('#'+zg010Kjyw.panel2).datagrid('getSelected');
		        if (curRow) {
		        	Comm.dialog({
			        	url:"${webroot}/st012Kjyw/f_view/toedit.shtml?id=" + curRow.id,
			            title: title,
			            width:450,
			            height:230
			        });
		        }
		    },
		    //删除
		    del:function() {
		    	//获取选中行值
		        var curRow = $('#'+zg010Kjyw.panel3).datagrid('getSelected');
		        if (curRow) {
			    	$.messager.confirm('提示', '确认取消关联?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/st012Kjyw/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: curRow.id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											zg010Kjyw.query3();
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
		        }
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
			autoTip.clear();
			$('#'+zg010Kjyw.panel).datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,
                striped: true,
                fitColumns: true,
                collapsible:true,
                border:false,
                url:'${webroot}/zg010Kjyw/f_json/pageQuery.shtml',	                
                remoteSort: false,
                singleSelect: true,
                columns:[
                	[              
	                    {field:'drugId',title:'编码',sortable:true,width:20,},
	                    {field:'drugName',title:'名称',sortable:true,width:30},
	                    {field:'drugTypeName',title:'分类',sortable:true,width:20}
	              	],
                ],
    	        rownumbers:true,
    	        pagination:true,
                toolbar:'#tb',
                onLoadSuccess: function() {
                	$('#'+zg010Kjyw.panel).datagrid('selectRow', 0);
                	var curRow = $('#'+zg010Kjyw.panel).datagrid('getSelected');
                	seturl(curRow.drugId);
                },
                onClickRow:function(rowIndex, rowData){	                
                	seturl(rowData.drugId);
		    	}
            });
			
			$('#'+zg010Kjyw.panel2).datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,
                striped: true,
                fitColumns: true,
                collapsible:true,
                border:false,
                url:'${webroot}/st012Kjyw/f_json/pageQuery.shtml',
                queryParams: {
	            	'isSys': 0
	            },
                remoteSort: false,
                singleSelect: true,
                columns:[
                	[              
	                    {field:'drugId',title:'编码',sortable:true,width:20,},
	                    {field:'drugName',title:'名称',sortable:true,width:40},
	                    {field:'drugLine',title:'级别',sortable:true,width:20,
	    					formatter:function(value,row,index) {
	    						return '<nis:select dictcode="drug_line" headerKey="" headerValue="-请选择-" exp="drugid=\"' + row.id + '\" defval=\"' + row.drugLine + '\"" />';
	    					}
	    				},
	                    {field:'_operate',title:'操作',width:20,
							formatter:function(value,r){
								return ['<a href="javascript:zg010Kjyw.edit(\'修改\');" class="ico_editor" title="修改"></a>'].join('');								
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
      							  zg010Kjyw.updDrugLine(this);
      						  }
      					  });
      				  }
      			  }
      		  }
            });
			$('#'+zg010Kjyw.panel3).datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,
                striped: true,
                fitColumns: true,
                collapsible:true,
                border:false,
                url:'${webroot}/st012Kjyw/f_json/pageQuery.shtml',
                queryParams: {
	            	'isSys': 1
	            },
                remoteSort: false,
                singleSelect: true,
                columns:[
                	[              
	                    {field:'drugId',title:'编码',sortable:true,width:10,},
	                    {field:'drugName',title:'名称',sortable:true,width:40},
	                    {field:'drugLine',title:'级别',sortable:true,width:20,
	    					formatter:function(value,row,index) {
	    						return '<nis:select dictcode="drug_line" headerKey="" headerValue="-请选择-" exp="drugid=\"' + row.id + '\" defval=\"' + row.drugLine + '\"" />';
	    					}
	    				},
	                    {field:'_operate',title:'操作',width:20,
							formatter:function(value,r){
								return ['<a href="javascript:zg010Kjyw.del();" class="ico_del" title="取消关联"></a>'].join('');								
							}
						}
	              	],
                ],
    	        rownumbers:true,
    	        pagination:true,
                toolbar:'#tb',
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
    							  zg010Kjyw.updDrugLine(this);
    						  }
    					  });
    				  }
    			  }
    		  }
            });
		});
		</script>
	</body>
</html>