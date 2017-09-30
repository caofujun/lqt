<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>检验预警计算配置</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">	
		<div class="easyui-layout" data-options="fit:true">
	        <div data-options="region:'north',split:true" style="height:300px;border-width: 0px 0px 1px 1px;">
				<div id="tb" class="m_search">
					<b>送检项目匹配</b>
					<div class="btn_r">
						<div class="n_btn_grey">
							<a href="javascript:void(0);" onclick="jyyj.add('新增');"><i class="icon iconfont"></i><span>新增</span></a>
						</div>
					</div>
				</div>
	           <div id="jyyjPanel"></div>
	        </div>
	        <div data-options="region:'center',border:false">
	        	<div id="tb2" class="m_search">
	        		<b>检出项目匹配</b>
					<div class="btn_r">
						<div class="n_btn_grey">
							<a href="javascript:;" onclick="jyyj.add2('新增');"><i class="icon iconfont"></i><span>新增</span></a>
						</div>
					</div>
				</div>
	        	<div id="jyyjPanel2"></div>
	        </div>
	    </div>
	</div>
		<script type="text/javascript">
		function seturl(id){
			 $('#'+jyyj.panel2).datagrid({
				 queryParams: {
		            	'sjId':id
		            },
			 });
		}
		var jyyj = {
			panel : 'jyyjPanel',
			panel2 : 'jyyjPanel2',
			//查询
			query : function() {
				autoTip.clear();
		        $('#'+jyyj.panel).datagrid({});	       
			},
		    //新增
		    add:function(title) {
		        Comm.dialog({
		        	url:"${webroot}/zg032Sjxmpp/f_view/toedit.shtml",
		            title: title,
		            width:450,
		            height:330
		        });
		    },
		    add2:function(title) {
		    	var curRow = $('#'+jyyj.panel).datagrid('getSelected');
		    	if(curRow){
			        Comm.dialog({
			        	url:"${webroot}/zg033Jcxxpp/f_view/toedit.shtml?sjId=" + curRow.id,
			            title: title,
			            width:450,
			            height:430
			        });
		    	}
		    },	
		    //编辑
		    edit:function(id,title) {
	        	Comm.dialog({
		        	url:"${webroot}/zg032Sjxmpp/f_view/toedit.shtml?id=" + id,
		            title: title,
		            width:450,
		            height:300
		        });
		    },
		    edit2:function(id,title) {
		    	var curRow = $('#'+jyyj.panel).datagrid('getSelected');
		    	if(curRow){
		        	Comm.dialog({
			        	url:"${webroot}/zg033Jcxxpp/f_view/toedit.shtml?id=" + id + "&sjId=" + curRow.id,
			            title: title,
			            width:500,
			            height:380
		    	    });
		    	}
		    },
		    //删除
		    del:function(id) {
		    	$.messager.confirm('提示', '确认删除?', function (r) {
		        	if (r) {
		            	$.ajax({
		                        url: '${webroot}/zg032Sjxmpp/f_json/delete.shtml',
		                        type: 'post',
		                        data: { id: id },
		                        dataType: 'json',
		                        success : function(json) {
									if(json.result==='success') {
										jyyj.query();
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
		    del2:function(id) {
		    	$.messager.confirm('提示', '确认删除?', function (r) {
		        	if (r) {
		            	$.ajax({
		                        url: '${webroot}/zg033Jcxxpp/f_json/delete.shtml',
		                        type: 'post',
		                        data: { id: id },
		                        dataType: 'json',
		                        success : function(json) {
									if(json.result==='success') {
										jyyj.query();
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
		};
		$(document).ready(function () {
			autoTip.clear();
			$('#'+jyyj.panel).datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,
                striped: true,
                fitColumns: true,
                collapsible:true,
                border:false,
                url:'${webroot}/zg032Sjxmpp/f_json/pageQuery.shtml',	                
                remoteSort: false,
                singleSelect: true,
                columns:[
                	[              
	                    {field:'matchField',title:'检验项目匹配字段',sortable:true,align:'center',width:50,
	                    	formatter:function(value,r){
	                    		var str = '';
	                    		if(r.matchField == 'ITEM_TYPE_NAME'){
	                    			str = '送检项目';
	                    		}else if (r.matchField == 'ITEM_NAME'){
	                    			str = '标本';
	                    		}else{
	                    			str = r.matchField;
	                    		}
	                    		return str;
							}
	                    },
	                    {field:'match',title:'匹配符',sortable:true,align:'center',width:30,
	                    	formatter:function(value,r){
	                    		var str = '';
	                    		if(r.match == 'like'){
	                    			str = '包含';
	                    		}else if (r.match == '='){
	                    			str = '等于';
	                    		}else if (r.match == '>'){
	                    			str = '大于';
	                    		}else if (r.match == '<'){
	                    			str = '小于';
	                    		}else{
	                    			str = r.match;
	                    		}
	                    		return str;
	                    	}
	                    },
	                    {field:'matchValue',title:'匹配值',sortable:true,align:'center',width:30},
	                    {field:'elementName',title:'感染因素',sortable:true,align:'center',width:40},
	                    {field:'_operate',title:'操作',sortable:true,align:'center',width:20,
	                    	formatter:function(value,r){
	                    		var str = '<a href="javascript:jyyj.edit(\'' + r.id + '\',\'修改\');" class="ico_editor" title="修改"></a>'+
	                    				'<a href="javascript:jyyj.del(\''+ r.id +'\');" class="ico_del" title="删除"></a>';
								return str;
							}	
	                    }
	              	],
                ],
    	        rownumbers:true,
    	        pagination:true,
                toolbar:'#tb',
                onLoadSuccess: function() {
                	$('#'+jyyj.panel).datagrid('selectRow', 0);
                	var curRow = $('#'+jyyj.panel).datagrid('getSelected');
                	seturl(curRow.id);
                },
                onClickRow:function(rowIndex, rowData){	                
                	seturl(rowData.id);
		    	}
            });
			$('#'+jyyj.panel2).datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,
                striped: true,
                fitColumns: true,
                collapsible:true,
                border:false,
                url:'${webroot}/zg033Jcxxpp/f_json/pageQuery.shtml',
                remoteSort: false,
                singleSelect: true,
                columns:[
                	[
	                    {title:'检出项目',colspan:3},
						{title:'检出值',colspan:3},
	                    {field:'_operate',title:'操作',rowspan:2,width:30,align:'center',
							formatter:function(value,r){
								var str = '<a href="javascript:jyyj.edit2(\'' + r.id + '\',\'修改\');" class="ico_editor" title="修改"></a>'+
                						'<a href="javascript:jyyj.del2(\''+ r.id +'\');" class="ico_del" title="删除"></a>';
								return str;								
							}
						}
					],
					[
	                    {field:'itemField',title:'检出项目字段',sortable:true,align:'center',width:40,
	                    	formatter:function(value,r){
	                    		var str = '';
	                    		if(r.itemField == 'ANTI_NAME'){
	                    			str = '检出项目';
	                    		}else{
	                    			str = r.itemField;
	                    		}
	                    		return str;
							}
	                    },
	                    {field:'itemMatch',title:'匹配符',sortable:true,align:'center',width:40,
	                    	formatter:function(value,r){
	                    		var str = '';
	                    		if(r.itemMatch == 'like'){
	                    			str = '包含';
	                    		}else if (r.itemMatch == '='){
	                    			str = '等于';
	                    		}else if (r.itemMatch == '>'){
	                    			str = '大于';
	                    		}else if (r.itemMatch == '<'){
	                    			str = '小于';
	                    		}else{
	                    			str = r.itemMatch;
	                    		}
	                    		return str;
	                    	}
	                    },
	                    {field:'itemMatchValue',title:'匹配值',sortable:true,align:'center',width:40},
	                    {field:'valueField',title:'检出值字段',sortable:true,align:'center',width:40,
	                    	formatter:function(value,r){
	                    		var str = '';
	                    		if(r.valueField == 'TEST_RESULT'){
	                    			str = '定量';
	                    		}else if(r.valueField == 'REMARK'){
	                    			str = '定性';
	                    		}else{
	                    			str = r.valueField;
	                    		}
	                    		return str;
							}
	                    },
	                    {field:'valueMatch',title:'匹配符',sortable:true,align:'center',width:40,
	                    	formatter:function(value,r){
	                    		var str = '';
	                    		if(r.valueMatch == 'like'){
	                    			str = '包含';
	                    		}else if (r.valueMatch == '='){
	                    			str = '等于';
	                    		}else if (r.valueMatch == '>'){
	                    			str = '大于';
	                    		}else if (r.valueMatch == '<'){
	                    			str = '小于';
	                    		}else{
	                    			str = r.valueMatch;
	                    		}
	                    		return str;
	                    	}	
	                    },
	                    {field:'valueMatchValue',title:'匹配值',sortable:true,align:'center',width:40},
	              	],	
                ],
    	        rownumbers:true,
    	        pagination:true,
                toolbar:'#tb2'
            });
		});
		</script>
	</body>
</html>