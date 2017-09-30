<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>首页样式配置</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<input type="hidden" id="id"/>
		<input type="hidden" id="layoutCode"/>
		<div style="padding:10px 15px 0px 15px;">
		<table>
			<tr>
			<td>
				<nis:select id="scopeLevel" dictcode="data_scope" headerKey="" headerValue="应用级别" />
				<input type="text" id="unitId" class="text" name="unitId" style="width:150px;" value=""/>
				<input type="text" id="depNo" class="text" name="depNo"   style="width:150px;" value=""/>
			</td>
			<td width="300">
				<a class="img_search" onclick="shsl.load()">查询</a>
				<a class="img_disable" onclick="shsl.openLayout()">调整布局</a>
				<a class="img_save" onclick="shsl.save()">保存</a>			
			</td>
			</tr>
		</table>
		</div>
		<div id="infoPanel">
		</div>
	<script type="text/javascript">
		var componentCodes = [];
		var shsl = {
				addComponent : function(id) {
					var _num = $.inArray(id,componentCodes);
					if(_num >= 0) return;
					componentCodes.push(id);
				},
				delComponent : function(id) {
					var _num = $.inArray(id,componentCodes);
					if(_num >= 0) {
						componentCodes.remove(id);
						Homepage.init('infoPanel', $('#layoutCode').val(), componentCodes.join(','), 'set');
					}
				},
				//加载布局
				load : function() {
					shsl.isLoad = true;
		    		$('#infoPanel').empty();
					autoTip.clear();
					$.ajax({
                        url: '${webroot}/sysHpStyle/f_json/load.shtml',
                        type: 'post',
                        data: { unitId: $('#unitId').val(), depNo: $('#depNo').val(), scopeLevel: $('#scopeLevel').val() },
                        dataType: 'json',
                        success : function(json) {
                        	componentCodes = [];
                        	$('#id').val('');
                        	$('#layoutCode').val('');
							if(json) {
								$('#id').val(json.id);
								Homepage.init('infoPanel', json.layoutCode, json.componentCodes, 'set');
								$('#layoutCode').val(json.layoutCode);
								$.each(json.componentCodes.split(','), function(i, value) {
									if(value && value != '') componentCodes.push(value);
								});
					    	} else {
					    		$.messager.show({ title: '提示', msg: '暂无首页，快设置吧' });
					    	}
						}
            		});
				},
				//保存布局
				save : function() {
					if(!shsl.isLoad) {
						$.messager.show({ title: '提示', msg: '请先查询' });
						return;
					}
					var _layoutCode = $('#layoutCode').val();
					if(_layoutCode === '') {
						$.messager.show({ title: '提示', msg: '请选择布局' });
						return;
					}
					if(componentCodes.length === 0) {
						$.messager.show({ title: '提示', msg: '请添加组件' });
						return;
					}
					autoTip.clear();
					$.ajax({
                        url: '${webroot}/sysHpStyle/f_json/save.shtml',
                        type: 'post',
                        data: { id: $('#id').val(), unitId: $('#unitId').val(), depNo: $('#depNo').val(), scopeLevel: $('#scopeLevel').val(),
                        	componentCodes: componentCodes.join(','), layoutCode: _layoutCode },
                        dataType: 'json',
                        success : function(json) {
							if(json) {
								$('#id').val(json.data.id);
					    		$.messager.show({ title: '提示', msg: '保存成功' });
					    	} else {
					    		$.messager.show({ title: '提示', msg: '保存失败' });
					    	}
						}
            		});
				},
				openLayout : function() {
					if(!shsl.isLoad) {
						$.messager.show({ title: '提示', msg: '请先查询' });
						return;
					}
					Comm.dialog({
				        url:"${webroot}/sysHpLayout/f_view/select.shtml",
				        title: '选择布局',
				        type: 'iframe',
				        width: 700,
				        height: 250
					});
				},
				openComponent : function() {
					Comm.dialog({
				        url:"${webroot}/sysHpComponent/f_view/select.shtml",
				        title: '选择组件',
				        type: 'iframe',
				        width: 640,
				        height: 450
					});
				}
		};
		$(function() {
			Csm.select.unit({
				id: 'unitId',
				depId: 'depNo'
			});
		});
		
		$(document).ready(function () { 
			shsl.load();
		});
	</script>
	</body>
</html>