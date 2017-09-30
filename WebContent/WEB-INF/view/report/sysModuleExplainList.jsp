<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>模块说明维护</title>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
	</head>
	<body>
		<div id="sysModuleExplainPanel"></div>
		<div id="tb" class="m_search">
		<!-- <select id="reportType" style="width:120px" class="easyui-combobox"></select> -->
        <input type="text" id="mkName"  name="mkName" style="width:200px" class="easyui-combotree"  />
		<div class="n_btn_blue">
			<a href="javascript:;" onclick="sysModuleExplain.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>		
		<div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="sysModuleExplain.edit(undefined,'新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>			
		</div>
		
		<script type="text/javascript">
			$(document).ready(function () {
/* 				$('#reportType').combobox({
					url:'${webroot}/sysDict/f_json/findTop.shtml?dictTypeCode=report_type',
				    valueField:'dictCode',
				    textField:'dictName',
					headerKey: '',
					headerValue: '请选择'
				}) */
				
				<!--填充菜单树-->
				$("#mkName").combotree({
				//加载一个combotree,并展开所有节点，因为展开后才能显示选中的值  
			    url:'${webroot}/acMenu/f_json/findTreeForEasyUI.shtml?mid='+"",     
			    editable:true, 
			    onLoadSuccess:function(node,data){
			       // var t = $("#mkName").combotree('tree');//获取tree  
			        var t = $(this).combotree('tree');
			        var node = t.tree('getSelected');
			        if (node){
			           t.tree('expandTo', node.target);
			           }
			        
			    //for (var i=0;i<data.length ;i++ ){
			    	//	 node=t.tree('find',data[i].id);
			    	
			        //if(data[i].id=='${moduleExplain.mid}'){
			       	//     node=t.tree('find',data[i].id);
			       	//     t.tree('check',node.target);
			       	//     t.tree('expandAll', node.target); 
			       	//     t.tree('select', node.target);
			        // } 	
			    //}

			    // for (var i=0;i<data.length ;i++ ){  
			    //    node= t.tree("find",data[i].id);  
			    //    t.tree('expandAll',node.target);//展开所有节点       
			    // }  
	
			    } ,
			    onSelect:function(node) {
			    	
			    	if(node.attributes.destUrl=='disselect'){
			    		$.messager.show({ title: '提示', msg: '标题不能被选择！'});
			            //清除选中  
			            $('#mkName').combotree('clear');  
			    		return;
			    	}else{
			      	  // 赋值 	  
			    		 $("#mkName").val(node.text);
			    	}
	 
			    }
			 });  
				
				$('#'+sysModuleExplain.panel).datagrid({
	                nowrap: true,
	                fit:true,
	                striped: true,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/moduleExplain/f_json/pageQuery.shtml',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'mkName',title:'模块名称',sortable:true,width:120},
		                    {field:'mkExplain',title:'模块说明',sortable:true,width:120},
		                    {field:'updateTime',title:'更新时间',sortable:true,width:120},
		                    {field:'createName',title:'创建人',sortable:true,width:120},		              
		                    {field:'_operate',title:'操作',width:60,
								formatter:function(value,r){
									return ['<a href="javascript:sysModuleExplain.edit(\'',r.mid,'\',\'修改\');" class="ico_editor" title="修改"></a>',
									'<a href="javascript:sysModuleExplain.del(\'',r.mid,'\');" class="ico_del" title="删除"></a>'].join('');
								}
							}
		                    
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb'
	            });
			});
			var sysModuleExplain = {
				panel : 'sysModuleExplainPanel',
				//查询
				query : function() {
					/* alert($('#mkName').val()); */
					
			        $('#'+sysModuleExplain.panel).datagrid({
			            url: '${webroot}/moduleExplain/f_json/pageQuery.shtml',
			            queryParams: {
			                'mkName': $('#mkName').val()
			            },
			            pageNumber: 1
			        });
			        $('#mkName').val('');
			    },
			    //编辑
			    edit : function(mid,title) {
		            //通过弹出窗口的方式打开页面
		            Comm.dialogGlobal({
		                url:"${webroot}/moduleExplain/f_view/toedit.shtml?id=" + mid,
		                title: title,
		                width:800,
		                height:550,
		                type:'iframe',
		                parent:this
		            });
			    },
			    //删除
			    del : function(mid) {
			            $.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
			                if (r) {
			                    $.ajax({
			                        url: '${webroot}/moduleExplain/f_json/delete.shtml',
			                        type: 'post',
			                        data: { 'id': mid },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result=='success') {
								    		$('#'+sysModuleExplain.panel).datagrid("reload");
			                                $('#'+sysModuleExplain.panel).datagrid('unselectAll');
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
