<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>预警关键词配置</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:false,border:false" style="border-bottom-width: 1px;overflow: hidden;" >
		<div class="m_search">
			<span>所属系统：</span>
			<select id="dictType" name="dictType">
				<option value="YG" selected="selected">院感</option>
				<option value="CDC">传染病</option>
			</select>
			<span>词型：</span>
			<select id="itemClass" name="itemClass">
				<option value="3" selected="selected">关键词</option>
				<option value="0">否定词</option>
			</select>
			<!-- <span>感染名称：</span>
			<div class="select_del">
				<input type="text" id="id_infectName" name="infectName" style="width:152px"/>
				<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_infectName').combo('clear');"></a>
			</div> -->
			<span>词关键字：</span>
			<div class="select_del">
				<input type="text" id="searchString" name="searchString" class="auto-tip" data-tip="词名称"/>
				<!-- <a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#searchString').combo('clear');"></a> -->
			</div>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="zg007Dict.query();" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="zg007Dict.addfd('新增否定词');" ><i class="icon iconfont">&#xe602;</i><span>新增否定词</span></a>
			</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,border:false" style="width:480px;border-right-width: 1px;" class="easyui-layout" id="layout">
		<div id="zg007DictPanel"></div>
	</div>
	<div id="id_zg007DictPanel2" data-options="region:'center',border:false" style="border-left-width: 1px;">
		<div id="zg007DictPanel2"></div>
		<div id="tb2" class="m_search">
			<span>同义词</span>
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="zg007Dict.edit('新增同义词','');"><i class="icon iconfont"></i><span>新增同义词</span></a>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function () {
	function seturl(id,itemClass){
		if(itemClass == '3'){
			$('#id_zg007DictPanel2').show();
			$('#'+zg007Dict.panel+'2').datagrid({
			    url:'${webroot}/zg007Dict/f_json/pageQuery.shtml',
				queryParams: {
					'elementId':id
				},
			});
		}else{
			$('#id_zg007DictPanel2').hide();
		}
	}
	$('#'+zg007Dict.panel).datagrid({
		fit: true,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		fitColumns: false,
		collapsible:true,
        url:'${webroot}/zg007Dict/f_json/pageQuery.shtml',
        queryParams: {
        	'dictType':$('#dictType option:selected').val(),
        	'itemClass':$('#itemClass option:selected').val()
        },
        remoteSort: false,
        singleSelect: true,
        columns:[
        	[
             {field:'dictType',title:'所属系统',align:'center',width:80,
            	 formatter:function(value,r){
	        		 var reStr = '';
                		if(r.dictType == 'YG'){
                			reStr = '院感';
                		}else if (r.dictType == 'CDC'){
                			reStr = '传染病';
                		}else{
                			reStr = r.dictType;
                		}
                		return reStr;
				 }	 
             },
             {field:'itemClass',title:'词型',align:'center',width:100,
            	 formatter:function(value,r){
            		 var str = '';
	            		 if(r.itemClass == '0'){
	            			 str = '否定词';
	            		 }else if (r.itemClass == '3'){
	            			 str = '关键词';
	            		 }else{
	            			 str = r.itemClass;
	            		 }
	              	 return str;
            	 }
             },
             {field:'itemName',title:'词名称',align:'center',sortable:true,width:180},
	         {field:'_operate',title:'操作',align:'center',width:60,
 	         	formatter:function(value,r){
		           		 if(r.itemClass == '0'){
		  	         		var str = '<a href="javascript:zg007Dict.delfd(\'' + r.keyid + '\');" class="ico_del" title="删除"></a>';
			         		return str;
		           		 }else{
		           			 return '';
		           		 }
 					}
 			  }
             /*{field:'infectName',title:'感染名称',align:'center',sortable:true,width:160},
             {field:'flag',title:'启用',align:'center',sortable:true,width:60},
	         {field:'_operate',title:'操作',align:'center',sortable:true,width:60,
             	formatter:function(value,r){
             		var str = '<a href="javascript:zg007Dict.edit(\'' + r.keyid + '\');" class="ico_editor" title="修改"></a>';
             			if(r.flag == '1'){
             				str += '<a href="javascript:zg007Dict.edit(\'' + r.keyid + '\');" class="ico_stop" title="停用"></a>';
             			}else if (r.flag == '0'){
             				str += '<a href="javascript:zg007Dict.edit(\'' + r.keyid + '\');" class="ico_select" title="启用"></a>';
             			}
             		return str;
					}
			  } */
         ]
        ],
        pagination:false,
        rownumbers:true,
        toolbar:'#tb',
        onClickRow:function(rowIndex, rowData){	                
        	seturl(rowData.elementId,rowData.itemClass);
    	},
		onLoadSuccess: function() {
			$('#'+zg007Dict.panel).datagrid('selectRow', 0);
			var curRow = $('#'+zg007Dict.panel).datagrid('getSelected');
			if(curRow){
				seturl(curRow.elementId,curRow.itemClass);				
			}
		},
    });
	$('#'+zg007Dict.panel+'2').datagrid({
		fit: true,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		fitColumns: false,
		collapsible:true,
	    remoteSort: false,
	    singleSelect: true,
	    columns:[
	    	[
	         {field:'itemName',title:'词名称',align:'center',sortable:true,width:180},
	         {field:'_operate',title:'操作',align:'center',sortable:true,width:80,
	         	formatter:function(value,r){
	         		var str = '<a href="javascript:zg007Dict.edit(\'修改\',\'' + r.keyid + '\');" class="ico_editor" title="修改"></a>';
	         			str += '<a href="javascript:zg007Dict.del(\'' + r.keyid + '\');" class="ico_del" title="删除"></a>';
/* 	         			if(r.flag == '1'){
	         				str += '<a href="javascript:zg007Dict.edit(\'' + r.keyid + '\');" class="ico_stop" title="停用"></a>';
	         			}else if (r.flag == '0'){
	         				str += '<a href="javascript:zg007Dict.edit(\'' + r.keyid + '\');" class="ico_select" title="启用"></a>';
	         			} */
	         		return str;
					}
			  }
	     ]
	    ],
	    pagination:false,
	    rownumbers:true,
	    toolbar:'#tb2'
	});
});

var zg007Dict = {
panel : 'zg007DictPanel',
//查询
query : function() {
		autoTip.clear();
		var itemClass = $('#itemClass option:selected').val();
		if(itemClass == '3'){
			$('#id_zg007DictPanel2').show();
		}else{
			$('#id_zg007DictPanel2').hide();
		}
		$('#'+zg007Dict.panel).datagrid({
		    url: '${webroot}/zg007Dict/f_json/pageQuery.shtml',
		    queryParams: {
		        'dictType':$('#dictType option:selected').val(),
		        'itemClass':$('#itemClass option:selected').val(),
		        /* 'infectName':$('#id_infectName').combotree("getText"), */
		        'searchString': $('#searchString').val(),
		        'isMain':'1'
		    },
		    pageNumber: 1
		});
},
delfd : function(id){	
	$.messager.confirm('提示', '确认删除该否定词?', function (r) {
    	if (r) {
        	$.ajax({
                   url: '${webroot}/zg007Dict/f_json/delete.shtml',
                   type: 'post',
                   data: { id: id },
                   dataType: 'json',
                   success : function(json) {
					if(json.result==='success') {
						zg007Dict.query();
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
addfd : function(title){	
	 //通过弹出窗口的方式打开页面
   Comm.dialog({
       url:"${webroot}/zg007Dict/f_view/toadd.shtml?dictType="+$('#dictType').val()+"&itemClass="+$('#itemClass').val()+"" ,
       title: title,
       width:360,
       height:190
   });
},
query2 : function(id,itemClass) {
	if(itemClass == '3'){
		$('#id_zg007DictPanel2').show();
		$('#'+zg007Dict.panel+'2').datagrid({
			queryParams: {
				'elementId':id
			},
		});
	}else{
		$('#id_zg007DictPanel2').hide();
	}
},
//编辑
edit : function(title,keyid) {
	//获取选中行值
    var curRow = $('#'+zg007Dict.panel).datagrid('getSelected');
    if (curRow) { 
        //通过弹出窗口的方式打开页面
        Comm.dialog({
            url:"${webroot}/zg007Dict/f_view/toedit.shtml?keyid=" + keyid + "&elementId=" + curRow.elementId,
            title: title,
            width:360,
            height:190
        });
     }
    else $.messager.alert('提示', '请选择需要编辑的记录.'); 
},
//删除
del : function() {
	var curRow = $('#'+zg007Dict.panel+'2').datagrid('getSelected');
    if (curRow) {
        $.messager.confirm('提示', '是否确认删除 ' + curRow.itemName + ' ?', function (r) {
            if (r) {
                $.ajax({
                    url: '${webroot}/zg007Dict/f_json/delete.shtml',
                    type: 'post',
                    data: { 'id': curRow.keyid },
                    dataType: 'json',
                    success : function(json) {
						if(json.result==='success') {
							zg007Dict.query2(curRow.elementId,curRow.itemClass);
                            $.messager.show({ title: '提示', msg: '删除成功！' });
				    	}else if(json.result === 'error'){
					    	$.messager.show({ title: '提示', msg: '删除失败！' });
				    	}else{
				    		$.messager.show({ title: '提示', msg: json.msg });
				    	}
					}
                });
            }
        })
    }
    else $.messager.alert('提示', '请选择需要删除的数据.');
}
};
</script>
</body>
</html>
