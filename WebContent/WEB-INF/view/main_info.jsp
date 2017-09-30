<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蓝蜻蜓医院感染实时监控平台</title>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
<script type="text/javascript" src="${webroot}/resources/echarts/theme/macarons.js"></script>
<script type="text/javascript" src="${webroot}/resources/js/hp-template.js"></script>
<script type="text/javascript" src="${webroot}/resources/js/homepage.js${version}"></script>
<script type="text/javascript" src="${webroot}/resources/load/jquery.loadmask.min.js"></script>
<script type="text/javascript" src="${webroot}/resources/js/jquery-ui.js"></script>
<style type="text/css">
	/*首页table居中显示*/
	.hp_table th td {text-align:center;}
	.portlet {
		margin: 0 1em 1em 0;
		padding: 0.3em;
	}
	.portlet-header {
		padding: 0.2em 0.3em;
		margin-bottom: 0.5em;
		position: relative;
	}
	.portlet-toggle {
		position: absolute;
		top: 50%;
		right: 0;
		margin-top: -8px;
	}
	.portlet-content {
		padding: 0.4em;
	}
	.portlet-placeholder {
		border: 1px dotted #ccc;		
		margin: 15px 0 0 15px;
		height: 150px;
	}
</style>

</head>
<body>
<input type="hidden" id="id" value="${sysHpStyle.id}"/>
<input type="hidden" id="layoutCode" value="${sysHpStyle.layoutCode}"/>
<input type="hidden" id="unitId" value=""/>
<input type="hidden" id="depNo" value=""/>
<div id="infoPanel" style="margin: 0 5px; height: 100%;">
</div>
<script type="text/javascript">
var curMonthFirst = '${curMonthFirst}', curMonthLast = '${curMonthLast}';
var componentCodes = '${sysHpStyle.componentCodes}'.split(',');
var shsl = {
		addComponent : function(id) {
			var _num = $.inArray(id,componentCodes);
			if(_num >= 0) return;
			componentCodes.push(id);
			shsl.save();
		},
		updComponent : function() {
			componentCodes = [];
			$('.menuSortInfo').each(function(i, obj) {
				componentCodes.push($(obj).attr('data-id'));
			});
			shsl.save(true);
		},
		delComponent : function(id) {
			var _num = $.inArray(id,componentCodes);
			if(_num >= 0) {
				if(confirm('您确认要删除该组件吗?')) {
					componentCodes.remove(id);
					shsl.save(true);
				}
			}
		},
		//保存布局
		save : function(isRefresh) {
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
                data: { id: $('#id').val(), unitId: $('#unitId').val(), depNo: $('#depNo').val(),
                	userName: '${user.username}',
                	componentCodes: componentCodes.join(','), layoutCode: _layoutCode },
                dataType: 'json',
                success : function(json) {
					if(json) {
						$('#id').val(json.data.id);
			    		//$.messager.show({ title: '提示', msg: '编辑成功' });
			    		if(isRefresh == true) {
			    			Homepage.init('infoPanel', $('#layoutCode').val(), componentCodes.join(','), 'my');
			    		}
			    		/* window.setTimeout(function() {
			    			location = webroot + '/user/f_view/main_info.shtml';
			    		}, 800); */
			    	} else {
			    		$.messager.show({ title: '提示', msg: '保存失败' });
			    	}
				}
    		});
		},
		openLayout : function() {
			Comm.dialog({
		        url:"${webroot}/sysHpLayout/f_view/select.shtml?sourceType=my",
		        title: '选择布局',
		        type: 'iframe',
		        width: 700,
		        height: 450
			});
		},
		openComponent : function() {
			Comm.dialog({
		        url:"${webroot}/sysHpComponent/f_view/select.shtml?sourceType=my",
		        title: '选择组件',
		        type: 'iframe',
		        width: 700,
		        height: 450
			});
		}
};
$(function() {
	Homepage.init('infoPanel', '${sysHpStyle.layoutCode}', '${sysHpStyle.componentCodes}', 'my');
	//图表跨度自适应问题
	$(window).resize(function () {
		$.each(Homepage.loadedChart,function () {
			this.resize();
		});
	});
});
</script>
<script type="text/javascript" src="${webroot}/resources/echarts/echarts-all.js"></script>
</body>
</html>