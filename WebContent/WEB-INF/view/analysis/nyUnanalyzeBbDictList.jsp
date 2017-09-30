<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><%@ include file="/WEB-INF/view/core/include.jsp"%></head>
<body>
	<div id="nyUnanalyzeBbDictPanel"></div>
	<div id="tb2" class="m_search">
		<span>搜索:</span>
		<div class="select_del">
			<input type="text" class="auto-tip" data-tip="请输入搜索内容" name="noDictName" id="id_noDictName" />
			<!-- <a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_noDictName').combo('clear');"></a> -->
		</div>
 		<div class="n_btn_blue">
			<a href="javascript:void(0)" onclick="nyUnanalyzeBbDict.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>	
	    <div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:void(0)" onclick="nyUnanalyzeBbDict.edit('','新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>
	</div>
	
<script type="text/javascript">
var nyUnanalyzeBbDict = {
	panel : 'nyUnanalyzeBbDictPanel',
	//查询
	query : function() {
		autoTip.clear();
        $('#'+nyUnanalyzeBbDict.panel).datagrid({
            url: '${webroot}/nyUnanalyzeBbDict/f_json/pageQuery.shtml',
            queryParams: {
            	'searchString': $('input[name="noDictName"]').val()
            },
            pageNumber: 1
        });
    },
    //编辑
    edit : function(id,title) {
		//通过弹出窗口的方式打开页面
		Comm.dialog({
		    url:"${webroot}/nyUnanalyzeBbDict/f_view/toedit.shtml?id=" + id,
		    title: title,
            width: 420,
            height: 180
		});
    },
    //删除
	del : function(id) {
		$.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
			if (r) {
				$.ajax({
				url: '${webroot}/nyUnanalyzeBbDict/f_json/delete.shtml?id=' + encodeURIComponent(encodeURIComponent(id)),
				type: 'post',
				dataType: 'json',
				success : function(json) {
					if(json.result==='success') {
	   					$('#'+nyUnanalyzeBbDict.panel).datagrid("reload");
	                    $('#'+nyUnanalyzeBbDict.panel).datagrid('unselectAll');
	                    $.messager.show({ title: '提示', msg: '删除成功！' });
	 			  	}
	 				else $.messager.show({ title: '提示', msg: '删除失败！' });
				}
              });
			}
		})
	}
};
$(document).ready(function () {
	$('#'+nyUnanalyzeBbDict.panel).datagrid({
        fit: true,
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        fitColumns: false,
        collapsible:true,
		url:'${webroot}/nyUnanalyzeBbDict/f_json/pageQuery.shtml',   
        remoteSort: false,
        singleSelect: true,
        border:false,
		columns:[
			[
			{field:'noDictName',title:'检出结果',sortable:true,width:200},
			{field:'_operate',title:'操作',width:100,
				formatter:function(value,r){
					return ['<a href="javascript:nyUnanalyzeBbDict.edit(\'',r.noDictName,'\',\'修改\');" class="ico_editor" title="修改"></a>',
							'<a href="javascript:nyUnanalyzeBbDict.del(\'',r.noDictName,'\');" class="ico_del" title="删除"></a>'].join('');
				}
			}
			]
				],
		pagination:true,
		rownumbers:true,
		toolbar:'#tb2'
	});
});
</script>
</body>
</html>
