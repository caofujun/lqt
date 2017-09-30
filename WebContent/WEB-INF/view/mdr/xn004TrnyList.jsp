<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><%@ include file="/WEB-INF/view/core/include.jsp"%></head>
<body>
	<div id="xn004TrnyPanel"></div>
	<div id="tb" class="m_search">
		<span>搜索:</span>
		<div class="select_del">
			<input type="text" class="auto-tip" data-tip="病原体/抗菌药物" name="searchString" id="id_searchString" />
		</div>
 		<div class="n_btn_blue">
			<a href="javascript:void(0)" onclick="xn004Trny.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>	
	    <div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:void(0)" onclick="xn004Trny.edit('','','新增','1')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>
	</div>
	
<script type="text/javascript">
$(document).ready(function () {
	$('#'+xn004Trny.panel).datagrid({
		fit: true,
        nowrap: false,
        autoRowHeight: false,
        striped: true,
        fitColumns: true,
        collapsible:true,
   	    url:'${webroot}/xn004Trny/f_json/pageQuery.shtml',   
        remoteSort: false,
        singleSelect: true,
        border:false,
        columns:[
			[
				{field:'pathogenId',title:'病原体编号',sortable:true,width:120},
				{field:'pathogenName',title:'病原体名称',sortable:true,width:140},
				{field:'drugId',title:'抗菌药物编号',sortable:true,width:120},
				{field:'drugName',title:'抗菌药物名称',sortable:true,width:140},
				{field:'_operate',title:'操作',sortable:true,width:60,
                	formatter:function(value,r){
                		var str = '<a href="javascript:xn004Trny.edit(\'' + r.pathogenId + '\',\'' + r.drugId + '\',\'修改\',\'0\');" class="ico_editor" title="修改"></a>';
                		str += '<a href="javascript:xn004Trny.del(\'' + r.pathogenId + '\',\'' + r.drugId + '\');" class="ico_del" title="删除"></a>';
                		return str;
					}
				}
			]
          ],
          pagination:true,
          rownumbers:true,
		  toolbar:'#tb'
	});
});
var xn004Trny = {
	panel : 'xn004TrnyPanel',
	//查询
	query : function() {
		autoTip.clear();
        $('#'+xn004Trny.panel).datagrid({
            queryParams: {
                'searchString': $('#id_searchString').val()
            },
            pageNumber: 1
        });
    },
    //编辑
    edit : function(pathogenId,drugId,title,isAdd) {
        //通过弹出窗口的方式打开页面
        Comm.dialog({
            url:"${webroot}/xn004Trny/f_view/toedit.shtml?pathogenId=" + pathogenId + "&drugId=" + drugId + "&isAdd=" + isAdd,
            title : title,
            width : 420,
            height : 200,
            parent : this
        });
    },
    //删除
    del : function(pathogenId,drugId) {
		$.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
			if (r) {
				$.ajax({
					url: "${webroot}/xn004Trny/f_json/delete.shtml",
					type: 'post',
					data: { 
						'pathogenId' : pathogenId,
						'drugId' : drugId
					},
					dataType: 'json',
					success : function(json) {
						if(json.result==='success') {
							$('#'+xn004Trny.panel).datagrid("reload");
							$('#'+xn004Trny.panel).datagrid('unselectAll');
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
