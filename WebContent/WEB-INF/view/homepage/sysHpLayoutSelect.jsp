<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>首页布局管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div class="home_component">
		<ul id="layoutPanel" style="border:0px;">		
		</ul>		
	</div>
		
		<script type="text/javascript">
			var hpLayout = {
				//查询
				query : function() {
					autoTip.clear();
					$.ajax({
                        url: '${webroot}/sysHpLayout/f_json/find.shtml',
                        type: 'post',
                        data: { layoutStatus: 'enable', 'searchString': $('#searchString').val() },
                        dataType: 'json',
                        success : function(json) {
							if(json.length > 0) {
								var _panel = $('#layoutPanel').empty();
								$.each(json, function(i, obj) {
									_panel.append(['<li class="component_preview">',
									        				'<div class="cap"><span class="cap_w_80">',obj.layoutName,'</span><div class="fr">',
									        				'<input type="button" class="ico_select2" title="选择" onclick="hpLayout.select(\'',obj.layoutCode,'\')"/>',
									        				'</div></div>',
									        				'<div class="con"><img src="',webroot,obj.imgUrl,'" width="100%" /></div>',
									        			'</li>'].join(''));
								});
					    	} else {
					    		$.messager.show({ title: '提示', msg: '暂无记录' });
					    	}
						}
            		});
			    },
			    //选择
			    select:function(id) {
			    	var _type = '${param.sourceType}';
			    	if(_type == '') _type = 'set';
			    	parent.$('#layoutCode').val(id);
			    	parent.Homepage.init('infoPanel', id, parent.componentCodes.join(','), _type);
			    	if(_type == 'my') {
			    		parent.shsl.save();
			    	}
			    	parent.Comm.dialogClose('${param.dialogId}');
			    }
			};
			$(document).ready(function () {
				hpLayout.query();
			});
		</script>
	</body>
</html>
