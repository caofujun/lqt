<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>首页组件管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body style="padding:10px;overflow-x:hidden;">
	<div class="m_search" style="padding:0px;">
		<nis:select id="queryCodeBusiness" dictcode="business_scope" dictParentCode="0" headerKey="" cssCls="easyui-combobox" headerValue="请选择业务域"/>
		<input type="text" class="auto-tip" data-tip="组件名称/组件编码" id="searchString"/>		
		<div class="n_btn_blue">
			<a href="javascript:;" onclick="component.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>			
	</div>
	
	<div class="home_component">
		<ul id="componentPanel">
		</ul>
	</div>
		
		<script type="text/javascript">
			var component = {
				//查询
				query : function() {
					autoTip.clear();
					$.ajax({
                        url: '${webroot}/sysHpComponent/f_json/find.shtml',
                        type: 'post',
                        data: { 'searchString': $('#searchString').val(), codeBusiness: $('#queryCodeBusiness').combogrid('getValue') },
                        dataType: 'json',
                        success : function(json) {
							var _componentPanel = $('#componentPanel').empty();
							if(json.length > 0) {
								$.each(json, function(i, obj) {
									var _stsBtn = [];
									var _img = webroot + obj.imgUrl;
									if(obj.layoutStatus==='disable') {
										var _index = _img.indexOf('.');
										_img = _img.substring(0, _index) + '_disable' + _img.substring(_index, _img.length);
										_stsBtn.push('<input type="button" class="ico_enable" title="启用" onclick="component.status(\'',obj.id,'\', \'',obj.layoutStatus,'\')"/>');
									} else {
										_stsBtn.push('<input type="button" class="ico_disable" title="禁用" onclick="component.status(\'',obj.id,'\', \'',obj.layoutStatus,'\')"/>');
									}
									_componentPanel.append(['<li class="component_preview">',
									        				'<div class="cap"><span class="cap_w">',obj.componentName,'</span><div class="fr"><input type="button" class="ico_modify" title="修改" onclick="component.edit(\'',obj.id,'\',\'修改组件\')"/>',
									        				'<input type="button" class="ico_delete" title="删除" onclick="component.del(\'',obj.id,'\')"/>',
									        				_stsBtn.join(''),'</div></div>',
									        				'<div class="con"><img src="',_img,'" width="100%" /></div>',
									        			'</li>'].join(''));
								});
					    	} else {
					    		$.messager.show({ title: '提示', msg: '暂无记录' });
					    	}
							_componentPanel.append('<li class="component_preview"><div class="cap"><div class="fr"></div></div>'
									+'<div class="con"><a href="javascript:component.edit(undefined,\'新增组件\')"><img src="'+webroot+'/resources/images_org/home/img_add.jpg" width="100%" /></a></div>'
								+'</li><div class="clear"></div>');
						}
            		});
			    },
			    //编辑
			    edit:function(id, title) {
			    	if(id===undefined) id = '';
			        Comm.dialog({
			        	url:"${webroot}/sysHpComponent/f_view/toedit.shtml?id=" + id,
			            title: title,
			            width:600
			        });
			    },
			    //禁用
			    status:function(id, status) {
			    	status = (status==='disable'?'enable':'disable');
			    	var _typeName;
			    	if(status==='disable') {
			    		_typeName = '禁用';
			    	} else {
			    		_typeName = '启用';
			    	}
			    	$.messager.confirm('提示', '确认要 ['+_typeName+'] 该组件?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sysHpComponent/f_json/status.shtml',
			                        type: 'post',
			                        data: { id: id, status: status },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											component.query();
			                                $.messager.show({ title: '提示', msg: '操作成功！' });
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
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该组件?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/sysHpComponent/f_json/delete.shtml',
			                        type: 'post',
			                        data: { id: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											component.query();
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
			    }
			};
			$(document).ready(function () {
				component.query();
			});
		</script>
	</body>
</html>
