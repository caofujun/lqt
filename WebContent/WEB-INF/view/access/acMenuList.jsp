<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单列表</title>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
</head>
<body>
	<div id="acMenuPanel"></div>
	<div id="tb" >
		<div class="m_search">			
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="menu.edit(undefined,'新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function () { 
			$('#acMenuPanel').datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,  
                striped: true,
                fitColumns: true,
                collapsible:true,  
                url:'${webroot}/acMenu/f_json/pageQuery.shtml?ownership=${ownership}',
                remoteSort: false,
                singleSelect: true,
                border:false,
                columns:[
                	[
	                    {field:'menuName',title:'菜单名称',sortable:true,width:190},
	                    {field:'menuTypeName',title:'类型',sortable:true,width:60},
	                    {field:'menuNo',title:'菜单编号',sortable:true,width:140},	                    
	                    {field:'showOrder',title:'显示顺序',sortable:true,width:60},
	                    {field:'destUrl',title:'链接地址',sortable:true,width:250},
	                    {field:'_operate',title:'操作',width:80,
							formatter:function(value,r){
								return ['<a href="javascript:menu.edit(\'',r.menuId,'\',\'修改\');"  class="ico_editor" title="修改"></a>',
									//	'<a href="javascript:menu.join(\'',r.menuNo,'\');"  class="ico_setup" title="关联报表"></a>',
										'<a href="javascript:menu.del(\'',r.menuId,'\');"  class="ico_del" title="删除"></a>'].join('');
							}
						}
	                ]
                ],
                pagination:false,
                rownumbers:true,
                toolbar:'#tb'
            });
		});
		var menu = {
			panel:'acMenuPanel',
			//查询
			query : function() {
		        $('#'+menu.panel).datagrid({
		            queryParams: {},
		            pageNumber: 1
		        });
		    },
		  	//编辑
		    edit:function(id, title) {
		    	var parentMenuNo = "";
		    	if(id===undefined) {
		    		id = '';
		    		var curRow = $('#acMenuPanel').datagrid('getSelected');
		    		if(curRow){
		    			parentMenuNo=curRow.menuNo;
		    		}
		    	}
		    	var ownership = '${ownership}';
		    	Comm.dialogGlobal({
		        	url:"${webroot}/acMenu/f_view/toedit.shtml?id=" + id + "&ownership=" + ownership + "&parentMenuNo=" + parentMenuNo,
		            title: title,
		            parent:this,
		            width:650
		            
		        });
		    },
		    join:function(id){
		    	Comm.dialogGlobal({
		        	url:"${webroot}/acMenu/f_view/acReportAuth.shtml?menuNo=" + id ,
		            title: "关联报表",
		            width:600,
		            height: 400,
		            type: 'iframe'		       
		        });
		    },
		    //删除
		    del:function(id) {
	            $.messager.confirm('提示', '是否确认删除该记录?', function (r) {
	                if (r) {
	                    $.ajax({
	                        url: '${webroot}/acMenu/f_json/delete.shtml',
	                        type: 'post',
	                        data: { id:id },
	                        dataType: 'json',
	                        success : function(json) {
								if(json.result==='success') {
	                                $.messager.show({ title: '提示', msg: '删除成功！' });
	                                menu.query();
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
	</script>
</body>
</html>