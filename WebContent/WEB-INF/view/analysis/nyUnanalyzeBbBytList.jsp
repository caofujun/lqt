<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><%@ include file="/WEB-INF/view/core/include.jsp"%></head>
<body>
	<div id="nyUnanalyzeBbBytPanel"></div>
	<div id="tb" class="m_search">
		<span>搜索:</span>
		<div class="select_del">
			<input type="text" class="auto-tip" data-tip="请输入搜索内容" name="bbDict" id="id_bbDict" />
			<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_bbDict').combo('clear');"></a>
		</div>
 		<div class="n_btn_blue">
			<a href="javascript:void(0)" onclick="nyUnanalyzeBbByt.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>	
	    <div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:void(0)" onclick="nyUnanalyzeBbByt.edit('','新增')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>
	</div>
	
<script type="text/javascript">
var nyUnanalyzeBbByt = {
	panel : 'nyUnanalyzeBbBytPanel',
	//查询
	query : function() {
		autoTip.clear();
		$('#'+nyUnanalyzeBbByt.panel).datagrid({
            queryParams: {
                'searchString': $('input[name="bbDict"]').val()
            },
            pageNumber: 1
        });
    },
    //编辑
    edit : function(id,title) {
    	//获取选中行值
        //var curRow = $('#'+nyUnanalyzeBbByt.panel).datagrid('getSelected');
        //if (curRow) {
            //通过弹出窗口的方式打开页面
            Comm.dialog({
                url:"${webroot}/nyUnanalyzeBbByt/f_view/toedit.shtml?id=" + id,
                title: title,
                width:420,
                height:200
            });
      	//}
        //else $.messager.alert('提示', '请选择需要编辑的记录.');
    },
    //删除
	del : function(id) {
		$.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
			if (r) {
				$.ajax({
					url: '${webroot}/nyUnanalyzeBbByt/f_json/delete.shtml',
					type: 'post',
					data: { 'id': id },
					dataType: 'json',
					success : function(json) {
						if(json.result==='success') {
							$('#'+nyUnanalyzeBbByt.panel).datagrid("reload");
							$('#'+nyUnanalyzeBbByt.panel).datagrid('unselectAll');
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
	$('#'+nyUnanalyzeBbByt.panel).datagrid({
        fit: true,
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        fitColumns: false,
        collapsible:true,
		url:'${webroot}/nyUnanalyzeBbByt/f_json/pageQuery.shtml',   
        remoteSort: false,
        singleSelect: true,
        border:false,
		columns:[
			[
				{field:'bbid',title:'bbid',sortable:true,width:120},
				{field:'bbmc',title:'标本',sortable:true,width:150},
				{field:'bytName',title:'检出菌',sortable:true,width:180},
				{field:'_operate',title:'操作',width:100,
					formatter:function(value,r){
						return ['<a href="javascript:nyUnanalyzeBbByt.edit(\'',r.bytid,'\',\'修改\');" class="ico_editor" title="修改"></a>',
								'<a href="javascript:nyUnanalyzeBbByt.del(\'',r.bytid,'\');" class="ico_del" title="删除"></a>'].join('');
					}
				}
			]
		],
		pagination:true,
		rownumbers:true,
		toolbar:'#tb'
	});
	Csm.combogrid.bbDict({
		id: 'id_bbDict',
		//value:'${xn017Tsnyzd.drugId}'
	});
});
</script>
</body>
</html>
