<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>环境卫生学监测</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body class="easyui-layout">
	<!-- <div data-options="region:'center'"> -->
		<div id="tb"  class="m_search" style="display: none;">		
			<div class="m_search_last">
				<select id="id_dateType" class="easyui-combobox" data-options="editable:false" style="width:80px;">
					<option value="1">填报日期</option>
					<option value="2">检验日期</option>
				</select>
				<input type="text" id="queryStartDate" value="${queryStartDate}" class="Wdate text" style="width:85px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'queryEndTime\')}',dateFmt:'yyyy-MM-dd'})" />~
			    <input type="text" id="queryEndTime" value="${queryEndDate}" class="Wdate text" style="width:85px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'queryStartDate\')}',dateFmt:'yyyy-MM-dd'})" />
				<span class="ml5">状态:</span>
				<div class="select_del">
					<select id="id_dateStatus" class="easyui-combobox" data-options="editable:false"  style="width:80px;">
						<option></option>
						<option value="1">已出报告</option>
						<option value="2">未出报告</option>
						<option value="3">已审核</option>
						<option value="4">未审核</option>
					</select>
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_dateStatus').combobox('clear');"></a>
				</div>
				<span class="ml5">采样场所:</span>
				<div class="select_del"><input type="text" id="id_placeId" style="width: 100px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_placeId').combo('clear');"></a></div>
				<span class="ml5">监控项目:</span>
				<div class="select_del"><input class="easyui-combotree" id="id_classId" data-options="url:'${webroot}/hw001Jcxm/f_json/queryTree.shtml?flag=1&type=${type}',method:'get',panelHeight:300,panelWidth: 230" style="width:100px;" ><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_classId').combotree('clear');"></a></div>
				<input type="hidden" id="unit" value="${unitId}"/>
				<span class="ml5">科室:</span>
				<div class="select_del"><input id="deptId"  style="width:120px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a></div>						
				<label><input type="checkbox" id="id_recheck" value="True" />&nbsp;复查</label>				
<!-- 				<span class="ml5">申请单号:</span> -->
				<input type="text" class="auto-tip" style="width:108px" data-tip="申请单号" id="djId" name="djId" />
				<input type="text" class="auto-tip" style="width:100px" data-tip="监测结果" id="result" name="result" />
				<label><input type="checkbox" id="id_notPass" value="True" />&nbsp;不合格</label>	
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="hygieneList.query(null);" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
				</div>		
				<div class="btn_r">
					<div class="n_btn_grey">
						<a href="javascript:;" onclick="hygieneList.add();"><i class="icon iconfont">&#xe602;</i><span>新建</span></a>
					</div>
					<div class="n_btn_grey">
						<a href="javascript:;" onclick="hygieneList.addFromTemplet();" ><i class="icon iconfont">&#xe602;</i><span>从模版新建</span></a>
					</div>					
				</div>
			</div>
		</div>
		<div id="hygieneListPanel"></div>
	<!-- </div> -->
	<!-- <div data-options="region:'east',split:true" style="width:360px;">
		<div class="easyui-panel" data-options="border:false">
			<table class="info_table">
				<tbody>
					<tr>
						<th style="width: 60px;">检验人员：</th>
						<td>
							王某
						</td>
						<th style="width: 60px;">检验日期：</th>
						<td>
							2016-05-01
						</td>
					</tr>
					<tr>
						<th>菌落数：</th>
						<td>
							1
						</td>
						<th>检出细菌：</th>
						<td>
							表皮葡萄球菌
						</td>
					</tr>
					<tr>
						<th>备注说明：</th>
						<td colspan="3">
							测试结果
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="easyui-panel" data-options="border:false">
			<div id="sampleListPanel"></div>
		</div>
	</div> -->

<script type="text/javascript">
	var hygieneList = {
		panel : 'hygieneListPanel',
		//分组Index
		groupIndex : 0,
		//需要展开的分组Index
		expandGroupIndex : 0,
		//需要展开的申请单
		djId : null,
		//选中行
		selectRowIndex : null,
		query : function (djId) {
			this.groupIndex = 0;
			this.expandGroupIndex = 0;
			this.selectRowIndex = null;
			this.djId = djId;
			autoTip.clear();
			$('#' + hygieneList.panel).datagrid({
	            url: '${webroot}/hw101Jcdj/f_json/findHygieneList.shtml',
	            queryParams: {
	            	'type':'${type}',
	            	'deptId': $('#deptId').combogrid('getValue'),
	            	'dateType':$('#id_dateType').combogrid('getValue'),
	            	'queryStartDate':$('#queryStartDate').val(),
	            	'queryEndDate':$('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59',
	            	'placeId':$('#id_placeId').combogrid('getValue'),
	            	'classId':$('#id_classId').combotree('getValue'),
	            	'recheck':$('#id_recheck:checked').val(),
	            	'notPass':$('#id_notPass:checked').val(),
	            	'dataStatus':$('#id_dateStatus').combogrid('getValue'),
	            	'djId':$('#djId').val(),
	            	'result':$('#result').val()
	            }
	        });
		},
		getNotNullStr : function(str) {
			if (str == null) {
				return '';
			} else {
				return str;
			}
		},
		//编辑
		edit : function(djId) {
			Comm.dialogGlobal({
	        	url:"${webroot}/hw101Jcdj/f_view/toHygieneEdit.shtml?type=${type}&djId=" + djId,
	            title: "修改监测报告单",
	            width:970,
	            height: 600,
	            type:"iframe",
	            parent:this
	        });
    	},
    	//打印
		print : function(djId,deptId) {
			hygieneList.updatePrintFlag(djId);
    		var url = '${reportUrl}nis7/HJWSX_CARD.cpt&__filename__=' + encodeURI(encodeURI(encodeURI('监测报告单打印'))) + '&__bypagesize__=false&type=${type}&deptId=${deptId}&userId=\'${userId}\'&djId=' + djId+"&djDeptId="+deptId;
    		window.open(url);
		},
		updatePrintFlag : function(djId){
			$.ajax({
				url:"${webroot}/hw101Jcdj/f_json/updatePrintFlag.shtml?djId="+djId,
				success:function(msg){
					hygieneList.query();
					msg = eval("("+msg+")");
					if(msg.result=="error"){
						$.messager.show({ title: '提示', msg: msg.msg });
					}
				},
				error:function(){
					$.messager.show({ title: '提示', msg: '更新打印标识失败！' });
				}
			})
		},
		//日期必填验证
		validateDate : function() {
			var re = true;
			if ($.trim($('#queryStartDate').val()).length == 0) {
				$('#queryStartDate').focus();
				re = false;
			}
			if ($.trim($('#queryEndTime').val()).length == 0) {
				if (re) {
					$('#queryEndTime').focus();
				}
				re = false;
			}
			return re;
		},
		//新建申请单
		add : function() {
			Comm.dialogGlobal({
	        	url:"${webroot}/hw101Jcdj/f_view/toHygieneEdit.shtml?type=${type}",
	            title: "新建监测报告单",
	            width:970,
	            height: 600,
	            type:"iframe",
	            parent:this
	        });
		},
		//删除
		del : function(deptName, djId,resultFlag) {
			if(resultFlag==-1){
				$.messager.confirm('提示', '确认删除【' + deptName + '：' + djId + '】的申请单?', function (r) {
		        	if (r) {
		            	$.ajax({
	                        url: '${webroot}/hw101Jcdj/f_json/delHygiene.shtml',
	                        type: 'post',
	                        data: { djId: djId },
	                        dataType: 'json',
	                        success : function(json) {
								if(json.result==='success') {
									hygieneList.query(null);
	                                $.messager.show({ title: '提示', msg: '删除成功！' });
						    	} else if(json.result === 'error') {
						    		$.messager.show({ title: '提示', msg: '删除异常！' });
						    	} else {
						    		$.messager.show({ title: '提示', msg: json.msg });
						    	}
							}
		            	});
		        	}
		    	});
			}else{
				$.messager.confirm('提示', '该项目<font color="red"><b>已出结果</b></font>，确认删除【' + deptName + '：' + djId + '】的申请单?', function (r) {
		        	if (r) {
		            	$.ajax({
	                        url: '${webroot}/hw101Jcdj/f_json/delHygiene.shtml',
	                        type: 'post',
	                        data: { djId: djId },
	                        dataType: 'json',
	                        success : function(json) {
								if(json.result==='success') {
									hygieneList.query(null);
	                                $.messager.show({ title: '提示', msg: '删除成功！' });
						    	} else if(json.result === 'error') {
						    		$.messager.show({ title: '提示', msg: '删除异常！' });
						    	} else {
						    		$.messager.show({ title: '提示', msg: json.msg });
						    	}
							}
		            	});
		        	}
		    	});
			}
			
		},
		//添加Tooltip
		addTooltip : function(divId, id) {
			$('#' + divId).tooltip({
                content: $('<div></div>'),
                showEvent: 'click',
                onUpdate: function(content){
                    content.panel({
                    	width: 350,
                    	border: false,
                        href: '${webroot}/hw101Jcdj/f_view/toShowSample.shtml?id=' +  id
                    });
                },
                onShow: function(){
                    var t = $(this);
                    t.tooltip('tip').unbind().bind('mouseenter', function(){
                        t.tooltip('show');
                    }).bind('mouseleave', function(){
                        t.tooltip('hide');
                    });
                }
            });
		},
		//从模版中新建申请单
		addFromTemplet : function() {
			Comm.dialogGlobal({
	        	url:"${webroot}/hw201Jcdmb/f_view/toAddByTemplet.shtml?type=${type}",
	            title: "从模版中新建",
	            width:900,
	            height:500,
	            type:"iframe",
	            parent:this
	        });
		},
		//保存为模版
		saveToTemplet : function(djId) {
			Comm.dialogGlobal({
	        	url:"${webroot}/hw201Jcdmb/f_view/toStoSaveToTemplet.shtml?type=${type}&djId=" + djId,
	            title: "保存为模版...",
	            width:860,
	            height:500,
	            type:"iframe",
	            parent:this
	        });
		},
		//审核
		audit : function(deptName, djId, status) {
			$.messager.confirm('提示', '确认审核【' + deptName + '：' + djId + '】的申请单?', function (r) {
	        	if (r) {
	            	$.ajax({
                        url: '${webroot}/hw101Jcdj/f_json/auditHygiene.shtml',
                        type: 'post',
                        data: { djId: djId, status: status },
                        dataType: 'json',
                        success : function(json) {
							if(json.result==='success') {
								hygieneList.query(djId);
                                $.messager.show({ title: '提示', msg: '审核成功！' });
					    	} else if(json.result === 'error') {
					    		$.messager.show({ title: '提示', msg: '审核异常！' });
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
		Csm.combogrid.dep({
			//【必传】控件名称
			id: 'deptId',
			hospId:'${unitId}',
			ifenvoffice: '1'
		});
		var nowDjid ="";
		var nowRn = 0;
		$('#' + hygieneList.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: false,
	        url:'${webroot}/hw101Jcdj/f_json/findHygieneList.shtml',
	        queryParams: {
            	'type':'${type}',
            	'dateType':$('#id_dateType').combogrid('getValue'),
            	'queryStartDate':$('#queryStartDate').val(),
            	'queryEndDate':$('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59',
            	'dataStatus':$('#id_dateStatus').combogrid('getValue')
	        },
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        columns:[ 
		       	[
		       	 	{field:'rn',title:'',width:30,formatter:function(value,row,index){
		       	 		if(!nowDjid){
		       	 			nowDjid = row.djId;
		       	 		}
		       	 		if(nowDjid == row.djId){
		       	 			return "<div style='text-align:center;'>"+(++nowRn)+"</div>";
		       	 		}else{
		       	 			nowDjid = row.djId;
			       	 		nowRn=0;
			       	 		return "<div style='text-align:center;'>"+(++nowRn)+"</div>";
		       	 		}
		       	 	}},
		       	 	{field:'djId',title:'申请单号',hidden:true},
					{field:'className',title:'监测项目',sortable:true,width:100,
						formatter:function(value,row,index){
    						return [('<a href="javascript:void(0);" style="text-decoration: underline;" id="id_tooltip_' + row.id + '" class="easyui-tooltip">' + hygieneList.getNotNullStr(value) + '</a>')].join('');
    					}
					},
		            {field:'resultFlagName',title:'结果',sortable:true,width:50},
		            {field:'takeTypeName',title:'监测类型',sortable:true,width:90},
		            {field:'placeName',title:'采样场所',sortable:true,width:100},
		            {field:'sampleName',title:'采样标本',sortable:true,width:120},
		            {field:'posName',title:'采样点数',sortable:true,width:100},
		            {field:'takeModeName',title:'采样方法',sortable:true,width:100},
		            {field:'takeByName',title:'采样人',sortable:true,width:60},
		            {field:'takeAt',title:'采样日期',sortable:true,align:'center',width:80},
		            {field:'testAt',title:'检验日期',sortable:true,align:'center',width:80},
		            {field:'testByName',title:'检验人',sortable:true,width:60},
		            {field:'resultCriterion',title:'监测结果',sortable:true,width:60},
		            {field:'condition',title:'执行标准',sortable:true,width:100},
		            {field:'recheck',title:'是否复查',sortable:true,align:'center',width:55,
		            	formatter:function(value,row,index){
		            		return value == 'True' ? '是' : '否';
		            	}
		            },
		            {field:'cyMeno',title:'采样备注',sortable:true,width:120},
		            {field:'memo',title:'结果备注',sortable:true,width:120}
		        ]
	        ],
	        pagination:true,
	        rownumbers:false,
	        toolbar:'#tb',
        	groupField:'djId',
            view: groupview,
            groupFormatter:function(value, rows) {
            	if (rows[0].djId == hygieneList.djId) {
            		hygieneList.expandGroupIndex = hygieneList.groupIndex;
            	}
            	var resultFlag=-1;
            	for(var i=0;i<rows.length;i++){
            		if(rows[i].resultFlag!=-1){
            			resultFlag = rows[i].resultFlag;
            		}
            	}
            	hygieneList.groupIndex ++;
            	var reStr = '<span>申请单号：</span><span style="width:140px;">' + rows[0].djId + '</span><span>科室：</span><span class="omit" title="' + rows[0].deptName + '" style="width:110px;margin-right:10px;">' + rows[0].deptName + '</span>' + 
            		'<span>填报人：</span><span class="omit" style="width:80px; margin-right:10px;">' + hygieneList.getNotNullStr(rows[0].reportByName) + '</span><span>填报日期：</span><span style="width:85px;">' + rows[0].reportAt + '</span>' + 
	        		'<span class="mr_btn" style="margin-right: 50px;"><a class="ico_no" href="javascript:void(0)" onclick="hygieneList.edit(\'' + rows[0].djId + '\');">修改</a>' + 
	        		'&nbsp;&nbsp;<a class="ico_no" href="javascript:void(0)" onclick="hygieneList.del(\'' + rows[0].deptName + '\',\'' + rows[0].djId + '\',\'' + resultFlag + '\');">删除</a>';
	        	<c:if test="${isAudit eq '1'}">
	        		if (rows[0].status == 1) {
	        			reStr += '&nbsp;&nbsp;<a class="ico_no" href="javascript:void(0)" onclick="hygieneList.audit(\'' + rows[0].deptName + '\',\'' + rows[0].djId + '\', ' + rows[0].status + ');">取消审核</a>';
	        		} else {
	        			reStr += '&nbsp;&nbsp;<a class="ico_no" href="javascript:void(0)" onclick="hygieneList.audit(\'' + rows[0].deptName + '\',\'' + rows[0].djId + '\', ' + rows[0].status + ');">审核</a>';
	        		}
	        	</c:if>
			        reStr += '&nbsp;&nbsp;<a class="ico_no" href="javascript:void(0)" onclick="hygieneList.print(\'' + rows[0].djId + '\',\''+rows[0].deptId+'\')">'+(rows[0].isprint == 1?'已打印':'打印')+'</a>' +
	        		'&nbsp;&nbsp;<a class="ico_no" href="javascript:void(0)" onclick="hygieneList.saveToTemplet(\'' + rows[0].djId + '\');">保存为模版</a></span>';
                return reStr;
            },
            groupStyler: function(value,rows){
        		return 'background-color:#efefef;height:28px;font-size:13px;padding-top:3px;font-weight:normal;'; 
        	},
        	onLoadSuccess : function(data){
        		for (var i = 0; i < data.rows.length; i++) {
        			var row = data.rows[i];
        			hygieneList.addTooltip('id_tooltip_' + row.id, row.id);
        			if (hygieneList.selectRowIndex == null && hygieneList.djId == row.djId) {
        				hygieneList.selectRowIndex = i;
        			}
        		}
        		if (data.rows.length > 0) {
                	$('#' + hygieneList.panel).datagrid('collapseGroup');
                	$('#' + hygieneList.panel).datagrid('expandGroup', hygieneList.expandGroupIndex);
                	if (hygieneList.selectRowIndex != null) {
                		$('#' + hygieneList.panel).datagrid('selectRow', hygieneList.selectRowIndex);
                	}
            	}
    		},
	        rowStyler:function(rowIndex,rowData){
                if (rowData && rowData.resultFlag && (rowData.resultFlag == 1)) {  
                    return 'color:red;';
                }
            }
	    });
		//采样场所
		$('#id_placeId').combogrid({
			delay: 1000,
		    value: '',
		    mode: 'remote',
		    loadMsg : '正在查询中...',
	        idField:'placeId',
	        textField:'placeName',
	        panelWidth: 370,
	        panelHeight: 300,
			url: '${webroot}/hw003Cycs/f_json/queryList.shtml?page=1&size=200',
	        columns:[
	        	[
	            	{field:'placeId',title:'场所编号',sortable:true,align:'center',width:80},  
	            	{field:'placeName',title:'场所名称',sortable:true,width:260}
	            ]
	        ]
		});
	});
</script>
</body>
</html>
