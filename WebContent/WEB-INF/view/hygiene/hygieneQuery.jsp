<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>环境卫生学监测查询</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body class="easyui-layout">
	<div id="tb" class="m_search" style="display: none;">		
		<div class="m_search_last">
			<select id="id_dateType" class="easyui-combobox" data-options="editable:false" style="width:80px;">
				<option value="1">填报日期</option>
				<option value="2">采样日期</option>
			</select>
			<input type="text" id="queryStartDate" value="${queryStartDate}" class="Wdate text" style="width:80px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />~
    		<input type="text" id="queryEndDate" value="${queryEndTime}" class="Wdate text" style="width:80px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			<span class="ml5">采样场所：</span>
			<div class="select_del"><input type="text" id="id_placeId" style="width: 100px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_placeId').combo('clear');"></a></div>
			<span>监控项目：</span>
			<div class="select_del"><input class="easyui-combotree" id="id_classId" data-options="url:'${webroot}/hw001Jcxm/f_json/queryTree.shtml?flag=1',method:'get',panelHeight:300,panelWidth: 230" style="width:133px;" ><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_classId').combotree('clear');"></a></div>
			<span>科室：</span>
			<input type="hidden" id="unit" value="${unitId}"/>
			<div class="select_del" <c:if test="${clinical==1}">style="display:none;"</c:if>><input id="deptId"  style="width:120px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a></div>	
			<select id="id_resultFlag"  class="easyui-combobox" data-options="editable:false" style="width:80px;">
				<option value="">-结果-</option>
				<option value="-1">待处理</option>
				<option value="1">不合格</option>
				<option value="0">合格</option>
			</select>
			<label><input type="checkbox" id="id_recheck" value="True" />&nbsp;复查</label>				
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="hygieneQuery.query();" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>	
			<div class="btn_r">
				<a href="javascript:;" onclick="hygieneQuery.printHz();">打印汇总单</a>
			</div>		
		</div>
	</div>
	<div id="hygieneQueryPanel"></div>
<script type="text/javascript">
	var hygieneQuery = {
		panel : 'hygieneQueryPanel',
		query : function () {
			autoTip.clear();
			$('#' + hygieneQuery.panel).datagrid({
	            url: '${webroot}/hw101Jcdj/f_json/findHygieneList.shtml',
	            queryParams: {
	            	'deptId': $('#deptId').combogrid('getValue'),
	            	'dateType':$('#id_dateType').combogrid('getValue'),
	            	'queryStartDate':$('#queryStartDate').val(),
	            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
	            	'placeId':$('#id_placeId').combogrid('getValue'),
	            	'classId':$('#id_classId').combotree('getValue'),
	            	'resultFlag':$('#id_resultFlag').combogrid('getValue'),
	            	'recheck':$('#id_recheck:checked').val()
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
		//日期必填验证
		validateDate : function() {
			var re = true;
			if ($.trim($('#queryStartDate').val()).length == 0) {
				$('#queryStartDate').focus();
				re = false;
			}
			if ($.trim($('#queryEndDate').val()).length == 0) {
				if (re) {
					$('#queryEndDate').focus();
				}
				re = false;
			}
			return re;
		},
		 //打印汇总单
		printHz : function(djId,deptId) {
			var recheck = $('#id_recheck:checked').val();
			if(typeof(recheck)=='undefined'){
				recheck='';
			}
    		var url = '${reportUrl}nis7/HJWSX_HZ.cpt&__filename__=' + encodeURI(encodeURI(encodeURI('监测报告单打印'))) + '&__bypagesize__=false&dateType='+$('#id_dateType').combogrid('getValue')+'&userId=${userId}&startDate='+$('#queryStartDate').val()+'&endDate='+$('#queryEndDate').val()+"&deptId="+$('#deptId').combogrid('getValue')
    		+'&placeId='+$('#id_placeId').combogrid('getValue')+'&classId='+$('#id_classId').combotree('getValue')+'&resultFlag='+$('#id_resultFlag').combogrid('getValue')+'&recheck='+recheck;
    		window.open(url);
    		//parent.menuInfo.clickMenu(decodeURI(decodeURI('环境卫生学监测（汇总）')), url, true, '', null);
		},
		 //打印
		print : function(djId,deptId) {
    		var url = '${reportUrl}nis7/HJWSX_CARD.cpt&__filename__=' + encodeURI(encodeURI(encodeURI('监测报告单打印'))) + '&type=${type}&deptId=${deptId}&userId=\'${userId}\'&djId=' + djId+"&djDeptId="+deptId;
    		window.open(url);
    		//parent.menuInfo.clickMenu(decodeURI(decodeURI('监测报告单打印')), url, true, '', null);
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
		}
	};

	$(document).ready(function () {
		Csm.combogrid.dep({
			//【必传】控件名称
			id: 'deptId',
			hospId:'${unitId}',
			ifenvoffice: '1'
		});
		$('#' + hygieneQuery.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: false,
	        url:'${webroot}/hw101Jcdj/f_json/findHygieneList.shtml',
	        queryParams: {
            	'dateType':$('#id_dateType').combogrid('getValue'),
            	'queryStartDate':$('#queryStartDate').val(),
            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59'
            },
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        columns:[ 
		       	[
					{field:'className',title:'监测项目',sortable:true,width:140,
						formatter:function(value,row,index){
    						return [('<a href="javascript:void(0);" style="text-decoration: underline;" id="id_tooltip_' + row.id + '" class="easyui-tooltip">' + hygieneQuery.getNotNullStr(value) + '</a>')].join('');
    					}
					},
		            {field:'resultFlagName',title:'结果',sortable:true,width:50},
		            {field:'takeTypeName',title:'监测类型',sortable:true,width:90},
		            {field:'placeName',title:'采样场所',sortable:true,width:80},
		            {field:'sampleName',title:'采样标本',sortable:true,width:120},
		            {field:'posName',title:'采样点数',sortable:true,width:100},
		            {field:'takeModeName',title:'采样方法',sortable:true,width:90},
		            {field:'takeByName',title:'采样人',sortable:true,width:70},
		            {field:'takeAt',title:'采样日期',sortable:true,align:'center',width:80},
		            {field:'testAt',title:'检验日期',sortable:true,align:'center',width:80},
		            {field:'testByName',title:'检验人',sortable:true,width:70},
		            {field:'recheck',title:'是否复查',sortable:true,align:'center',width:55,
		            	formatter:function(value,row,index){
		            		return value == 'True' ? '是' : '否';
		            	}
		            },
		            {field:'memo',title:'备注',sortable:true,width:120}
		        ]
	        ],
	        pagination:true,
	        rownumbers:true,
	        toolbar:'#tb',
        	groupField:'djId',
            view: groupview,
            groupFormatter:function(value, rows) {
            	var reStr = '<span>申请单号：</span><span style="width:140px;">' + rows[0].djId + '</span><span>科室：</span><span class="omit" title="' + rows[0].deptName + '" style="width:110px;margin-right:10px;">' + rows[0].deptName + '</span>' + 
            		'<span>填报人：</span><span class="omit" style="width:80px; margin-right:10px;">' + hygieneQuery.getNotNullStr(rows[0].reportByName) + '</span><span>填报日期：</span><span style="width:85px;">' + rows[0].reportAt + '</span>' + 
	        		'<span  class="mr_btn"><a class="ico_no" href="javascript:void(0)" onclick="hygieneQuery.print(\'' + rows[0].djId + '\',\''+rows[0].deptId+'\')">打印</a></span>';
                return  reStr;
            },
            groupStyler: function(value,rows){
        		return 'background-color:#efefef;height:28px;font-size:13px;padding-top:3px;font-weight:normal;'; 
        	},
        	onLoadSuccess : function(data){
        		for (var i = 0; i < data.rows.length; i++) {
        			var row = data.rows[i];
        			hygieneQuery.addTooltip('id_tooltip_' + row.id, row.id);
        		}
        		if (data.rows.length > 0) {
                	$('#' + hygieneQuery.panel).datagrid('collapseGroup');
                	$('#' + hygieneQuery.panel).datagrid('expandGroup', 0);
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
