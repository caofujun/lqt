<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>首页组件管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body style="padding:15px 10px; overflow-x:hidden;">
	<div class="m_search" style="padding:0;">
		<nis:select id="queryCodeBusiness" dictcode="business_scope" dictParentCode="0" headerKey="" cssCls="easyui-combobox" headerValue="请选择业务域"/>
		<input type="text" class="auto-tip text" data-tip="组件名称/组件编码" id="searchString"/>		
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
                        data: { 'layoutStatus': '1', 'searchString': $('#searchString').val(), codeBusiness: $('#queryCodeBusiness').combogrid('getValue') },
                        dataType: 'json',
                        success : function(json) {
							var _componentPanel = $('#componentPanel').empty();
							if(json.length > 0) {
								$.each(json, function(i, obj) {
									if(obj.isSelected && obj.isSelected == 'enable' ){
										//已经选择的不显示
										return true;
									}
									_componentPanel.append(['<li class="component_preview">',
									        				'<div class="cap"><span class="cap_w_80">',obj.componentName,'</span><div class="fr">',
									        				'<input type="button" class="ico_select2ed" ',
									        				'title="选择" onclick="component.select(\'',obj.componentCode,'\')"/>',
									        				'</div></div>',
									        				'<div class="con"><img src="',webroot,obj.imgUrl,'" width="100%" /></div>',
									        			'</li>'].join(''));
								});
								_componentPanel.append('<div class="clear"></div>');
					    	} else {
					    		parent.$.messager.show({ title: '提示', msg: '暂无记录' });
					    	}
						}
            		});
			    },
			    //选择
			    select:function(id) {
			    	var _type = '${param.sourceType}';
			    	if(_type == '') _type = 'set';
			    	parent.shsl.addComponent(id);
			    	parent.Homepage.init('infoPanel', parent.$('#layoutCode').val(), parent.componentCodes.join(','), _type);
			    	component.query();
			    	//parent.Comm.dialogClose('${param.dialogId}');
			    }
			};
			$(document).ready(function () {
				component.query();
			});
		</script>
	</body>
</html>
