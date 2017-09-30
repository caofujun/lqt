<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>接口数据异常</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/zeroclipboard-2.2.0/ZeroClipboard.js"></script>
	<style>
		.easyui-combobox{width:75px;}
		.table_cx .easyui-combobox{width:150px;}
	</style>
</head>
<body class="easyui-layout">

<div data-options="region:'west',border:false,title:'查询条件'" style="width:230px;">
	<div class="easyui-layout" data-options="fit:true">              
		<div data-options="region:'center',border:false">
			<table class="table_cx" cellpadding="0" cellspacing="0">
				<tbody>	
					<tr>
						<td colspan="2" height="30">选择需检测的业务表---------------</td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="KETTLE" name="bizType" value="KETTLE"/></td>
						<td><label for="KETTLE">KETTLE异常数据</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="MONITOR" name="bizType" value="MONITOR"/></td>
						<td><label for="MONITOR">接口、预警监测</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="ST003_CRYXXB" name="bizType" value="ST003_CRYXXB"/></td>
						<td><label for="ST003_CRYXXB">患者出入院信息</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="ST002_ZDXXB" name="bizType" value="ST002_ZDXXB"/></td>
						<td><label for="ST002_ZDXXB">诊断信息</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="ST004_YZXXB" name="bizType" value="ST004_YZXXB"/></td>
						<td><label for="ST004_YZXXB">医嘱信息</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="ST005_SSXXB" name="bizType" value="ST005_SSXXB"/></td>
						<td><label for="ST005_SSXXB">手术信息</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="ST006_TWXX" name="bizType" value="ST006_TWXX"/></td>
						<td><label for="ST006_TWXX">体温信息</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="ST008_BCJL" name="bizType" value="ST008_BCJL"/></td>
						<td><label for="ST008_BCJL">病程记录</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="ST009_SJBB" name="bizType" value="ST009_SJBB"/></td>
						<td><label for="ST009_SJBB">送检标本</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="ST010_JCBYT" name="bizType" value="ST010_JCBYT"/></td>
						<td><label for="ST010_JCBYT">检出病原体</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="ST011_SYJGB" name="bizType" value="ST011_SYJGB"/></td>
						<td><label for="ST011_SYJGB">常规结果&药敏结果</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="ST020_CLINIC_PATIENTS" name="bizType" value="ST020_CLINIC_PATIENTS"/></td>
						<td><label for="ST020_CLINIC_PATIENTS">门诊患者信息</label></td>
					</tr>
					<tr>
						<td colspan="2" height="30"></td>
					</tr>
					<tr>
						<td colspan="2" height="30">选择分析时间-------------------</td>
					</tr>
					<tr>
						<td class="t_title">开始时间：</td>
						<td>
							<input type="text" id="queryStartDate" value="${queryStartDate}" onclick="WdatePicker()" class="Wdate text easyui-validatebox" style="width:108px;" data-options="required:true,validateOnBlur:true" />
						</td>
					</tr>
					<tr>
						<td class="t_title">结束时间：</td>
						<td>
							<input type="text" id="queryEndDate" value="${queryEndDate}" onclick="WdatePicker()" class="Wdate text easyui-validatebox" style="width:108px;" data-options="required:true,validateOnBlur:true" />
						</td>
					</tr>	
				</tbody>
			</table>
		</div>
		<div data-options="region:'south',border:false" style="height:50px; border-top-width:1px;">	         		
			<div class="btn_center">
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="dataWarning.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
				</div>		
			</div>				
		</div>
	</div>
</div>
<div data-options="region:'center',border:false" style="border-left-width: 1px;">
	<div id="dataWarningPanel"></div>
</div>	
<script type="text/javascript">
	var dataWarning = {
		panel : 'dataWarningPanel',
		query : function () {
			var bizType = '';
    		$("input:checkbox[name='bizType']:checked").each(function(){ 
    			bizType += $(this).val() + ',';
   			});
			if (this.validateDate()) {
				autoTip.clear();
				$('#' + dataWarning.panel).datagrid({
		            url: '${webroot}/data/f_json/findDataWarning.shtml',
		            queryParams: {
		            	'bizType':bizType,
		            	'queryStartDate':$('#queryStartDate').val(),
		            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59'
		            }
		        });
			}
		},
		copy :function (sql){
			$.messager.show({
				title:'复制SQL',
				msg:sql,
				width:400,
				height:250,
				showType:'show',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					
				}
			});
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
		}
	};

	$(document).ready(function () {
		autoTip.clear();
		$('#' + dataWarning.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        columns:[ 
		       	[
					{field:'warning',title:'预警消息',sortable:true,width:750},
					{field:'_operate',title:'复制查询语句',width:80,align:'center',
						formatter:function(value,row,index){
							return ['<a href="javascript:void(0);" ttt="' + row.sql + '" onclick="dataWarning.copy($(this).attr(\'ttt\'));"   class="ico_copy clip_button"  title="复制SQL"></a>'].join('');	
						}
					}
		        ]
	        ],
	        rownumbers:true,
	        pagination:false,
	        toolbar:'#tb',
// 	        onLoadSuccess : function() {
// 	        	  var client = new ZeroClipboard( $('.clip_button'));
// 				  client.on( "ready", function( readyEvent ) {
// 					  client.on( "aftercopy", function( event ) {
// 					  	  $.messager.show({ title : '提示', msg : '复制成功！' });
// 					  });
// 				  });
// 	        }
	    });
	});
</script>
</body>
</html>
