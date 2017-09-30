<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><%@ include file="/WEB-INF/view/core/include.jsp"%></head>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,border:false" style="width:520px;border-right-width: 1px;" class="easyui-layout" id="layout">
		<div id="sysDictPanel"></div>
		<div id="tb" class="m_search">
			<b>重点菌维护</b>
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="sysDict.add('新增');"><i class="icon iconfont"></i><span>新增</span></a>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="border-left-width: 1px;">
		<div id="xn017TsnyzdPanel">
		<div id="tb2" class="m_search">
			<span><b id="id_dict_name" ></b>&nbsp;<b>当前已有规则</b></span>
			<span class="Prompt_infor" style="text-align: center;">提示：只要满足下面任意一条件即为重点监测菌</span>
			<div class="btn_r">
				<div class="n_btn_grey" id="id_edit1" style="display: ;">
					<a href="javascript:void(0);" onclick="xn017Tsnyzd.edit('','','','新增对菌','1');"><i class="icon iconfont"></i><span>新增规则-对菌</span></a>
				</div>
				<div class="n_btn_grey" id="id_edit2" style="display: ;" >
					<a href="javascript:void(0);" onclick="xn017Tsnyzd.edit('','','','新增对药物','2');"><i class="icon iconfont"></i><span>新增规则-对药物</span></a>
				</div>
				<div class="n_btn_grey" id="id_edit3" style="display: none;">
					<a href="javascript:void(0);" onclick="xn017Tsnyzd.edit('','','','新增对ESBL','3');"><i class="icon iconfont"></i><span>新增规则-对ESBL</span></a>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
<script type="text/javascript">
$(document).ready(function () {
	function seturl(code,name){
		if(code == 'ESBLS'){
			$('#id_edit1').hide();
			$('#id_edit2').hide();
			$('#id_edit3').show();
		}else{
			$('#id_edit1').show();
			$('#id_edit2').show();
			$('#id_edit3').hide();
		};
		$('#'+xn017Tsnyzd.panel).datagrid({
			queryParams: {
				'specDescribe':code
			},
		});
		$('#id_dict_name').text(name);
	}
	$('#'+sysDict.panel).datagrid({
        fit: true,
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        fitColumns: false,
        collapsible:true,
        url:'${webroot}/sysDict/f_json/getAllSpecDescribes.shtml?dictTypeCode=spec_describes',   
        remoteSort: false,
        singleSelect: true,
        border:false,
        columns:[
        	[
                {field:'dictName',title:'重点菌名称',sortable:true,width:120},
                {field:'remark',title:'描述',sortable:true,width:300,
                	formatter: function (value,r) {
                		if(value!=null){
	                		return '<span title="' + value + '">' + value + '</span>'
                		}else{
							return '';
                		}
                	}
                },
                {field:'_operate',title:'操作',sortable:true,width:60,
                	formatter:function(value,r){
                		var str = '<a href="javascript:sysDict.edit(\'' + r.id + '\',\'修改\');" class="ico_editor" title="修改"></a>';
                		if(r.dictStatus=='1'){
                			str += '<a href="javascript:sysDict.stop(\'' + r.id + '\');" class="ico_stop" title="停用"></a>';                			
                		}else{
                			str += '<a href="javascript:sysDict.start(\'' + r.id + '\');" class="ico_select" title="启用"></a>';
                		}
                		//str += '<a href="javascript:sysDict.del(\'' + r.id + '\');" class="ico_del" title="删除"></a>';
                		return str;
					}	
                }
            ]
        ],
        rownumbers:true,
        toolbar:'#tb',
        onClickRow:function(rowIndex, rowData){	                
        	seturl(rowData.dictCode,rowData.dictName);
    	},
		onLoadSuccess: function() {
			$('#'+sysDict.panel).datagrid('selectRow', 0);
			var curRow = $('#'+sysDict.panel).datagrid('getSelected');
			seturl(curRow.dictCode,curRow.dictName);
		},
    });
	$('#'+xn017Tsnyzd.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: false,
	        striped: true,
	        fitColumns: true,
	        collapsible:true,
		    url:'${webroot}/xn017Tsnyzd/f_json/pageQuery.shtml',   
            remoteSort: false,
            singleSelect: true,
            border:false,
		    columns:[
		    	[
		         {field:'pathogenName',title:'菌名',sortable:true,width:120},
		         {field:'drugName',title:'药物名',sortable:true,width:120},
		         {field:'showTestresult',title:'药敏结果定性',sortable:true,width:120},
		         {field:'_operate',title:'操作',sortable:true,width:80,
	                	formatter:function(value,r){
	                		var str = '';
	                		if (r.drugId == '无'){
	                			str+= '<a href="javascript:xn017Tsnyzd.edit(\'' + r.pathogenId + '\',\'' + r.drugId + '\',\'' + r.testresult + '\',\'修改\',\'1\');" class="ico_editor" title="修改"></a>';
	                		}else{
	                			str+= '<a href="javascript:xn017Tsnyzd.edit(\'' + r.pathogenId + '\',\'' + r.drugId + '\',\'' + r.testresult + '\',\'修改\',\'2\');" class="ico_editor" title="修改"></a>';
	                		}
	                		if(r.specDescribe == 'ESBLS'){
	                			str = '<a href="javascript:xn017Tsnyzd.edit(\'' + r.pathogenId + '\',\'' + r.drugId + '\',\'' + r.testresult + '\',\'修改\',\'3\');" class="ico_editor" title="修改"></a>';
	                		}
	                		str += '<a href="javascript:xn017Tsnyzd.del(\'' + r.pathogenId + '\',\'' + r.drugId + '\',\'' + r.testresult + '\');" class="ico_del" title="删除"></a>';
	                		return str;
						}
	              }
		     	]
		    ],
            pagination:true,
            rownumbers:true,
		    toolbar:'#tb2'
	});
});
var sysDict = {
		panel : 'sysDictPanel',
		//查询
		query : function() {
			autoTip.clear();
	        $('#'+sysDict.panel).datagrid({
	            queryParams: {
	            	'searchString': $('#searchString').val()
	            },
	            pageNumber: 1
	        });
	    },
	 	//新增
	    add:function(title) {
		        Comm.dialogGlobal({
		        	url:"${webroot}/sysDict/f_view/toeditSpecDescribes.shtml?dictTypeCode=spec_describes",
		            title: title,
		            width:580,
		            parent:this
		        });
	    },
	    //编辑
	    edit:function(id,title) {
	    	//获取选中行值
	        var curRow = $('#'+sysDict.panel).datagrid('getSelected');
	        if (curRow) {
	        	Comm.dialogGlobal({
		        	url:"${webroot}/sysDict/f_view/toeditSpecDescribes.shtml?id=" + curRow.id,
		            title: title,
		            width:580,
		            parent:this
		        });
	        }
	    },
	    //删除
	    del:function() {
	    	//获取选中行值
	        var curRow = $('#'+sysDict.panel).datagrid('getSelected');
	        if (curRow) {
		    	$.messager.confirm('提示', '确认删除该重点菌?', function (r) {
		        	if (r) {
		            	$.ajax({
		                        url: '${webroot}/sysDict/f_json/deleteSpecDescribes.shtml',
		                        type: 'post',
		                        data: { id: curRow.id , specDescribe : curRow.dictCode},
		                        dataType: 'json',
		                        success : function(json) {
									if(json.result==='success') {
										sysDict.query();
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
	        }else{
	        	 $.messager.show({ title: '提示', msg: '请选择需要删除的字典类型！' });
	        }
	    },
		//停用
	    stop:function(id) {
	    	$.messager.confirm('提示', '确认停用该字典?', function (r) {
	        	if (r) {
	            	$.ajax({
	                        url: '${webroot}/sysDict/f_json/stop.shtml',
	                        type: 'post',
	                        data: { id: id },
	                        dataType: 'json',
	                        success : function(json) {
								if(json.result==='success') {
									sysDict.query();
	                                $.messager.show({ title: '提示', msg: '停用成功！' });
						    	} else if(json.result === 'error') {
						    		$.messager.show({ title: '提示', msg: '停用异常！' });
						    	} else {
						    		$.messager.show({ title: '提示', msg: json.msg });
						    	}
							}
	            	});
	        	}
	    	});
	    },
	  	//启用
	    start:function(id) {
	    	$.messager.confirm('提示', '确认启用用该字典?', function (r) {
	        	if (r) {
	            	$.ajax({
	                        url: '${webroot}/sysDict/f_json/start.shtml',
	                        type: 'post',
	                        data: { id: id },
	                        dataType: 'json',
	                        success : function(json) {
								if(json.result==='success') {
									sysDict.query();
	                                $.messager.show({ title: '提示', msg: '启用成功！' });
						    	} else if(json.result === 'error') {
						    		$.messager.show({ title: '提示', msg: '启用异常！' });
						    	} else {
						    		$.messager.show({ title: '提示', msg: json.msg });
						    	}
							}
	            	});
	        	}
	    	});
	    }
	};
var xn017Tsnyzd = {
	panel : 'xn017TsnyzdPanel',
	//查询
	query : function() {
		autoTip.clear();
        $('#'+xn017Tsnyzd.panel).datagrid({});
    },
    //编辑
    edit : function(pathogenId,drugId,testresult,title,num) {
    	var height = 210;
    	if(num == '2'){
    		height = 290;
    	}
    	var curRow = $('#'+sysDict.panel).datagrid('getSelected');
        Comm.dialog({
            url:"${webroot}/xn017Tsnyzd/f_view/toedit.shtml?pathogenId=" + pathogenId + "&drugId=" + drugId + "&testresult=" + encodeURIComponent(encodeURIComponent(testresult)) + "&specDescribe=" + curRow.dictCode + "&dictName=" +curRow.dictName + "&num=" + num,
            title: title,
            width:420,
            height:height
        });
    },
    //删除
    del : function(pathogenId,drugId,testresult) {
      $.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
    	  var curRow = $('#'+sysDict.panel).datagrid('getSelected');
          if (r) {
              $.ajax({
                  url: '${webroot}/xn017Tsnyzd/f_json/delete.shtml',
                  type: 'post',
                  data: { 
					'pathogenId': pathogenId,
					'drugId' : drugId,
					'specDescribe' : curRow.dictCode,
					'testresult': testresult
                  },
                  dataType: 'json',
                  success : function(json) {
      				if(json.result==='success') {
      		    		$.messager.show({ title: '提示', msg: '操作成功！' });
      		    		xn017Tsnyzd.query();
      		    	} else if(json.result === 'error') {
      					$.messager.show({ title : '提示', msg : json.msg });
      				} else {
      					$.messager.show({ title : '提示', msg : json.msg });
      				}
					}
              });
          }
      })
    }
};
</script>
</body>
</html>
