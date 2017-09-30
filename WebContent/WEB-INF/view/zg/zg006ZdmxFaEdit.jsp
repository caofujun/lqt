<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>预警调节</title>
		<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
	<c:forEach items="${zdmxFaList}" var="zg006ZdmxFa">
		<div class="FA_config">
			<div class="FA_config_title"><c:out value="${zg006ZdmxFa.faName}" /> </div>
			<div class="FA_config_head">
				<div class="FA_config_head_l"><span>方案说明：</span><input type="text" value="${zg006ZdmxFa.faDescribe}" onblur="zdmxFx.saveFaDescribe(this, '${zg006ZdmxFa.id}');" /></div>
				<div class="FA_config_head_r">																
					<input style="display: none;" id-data="${zg006ZdmxFa.id}" class="easyui-switchbutton" ${zg006ZdmxFa.state eq '1' ? 'checked' : ''} data-options="${zg006ZdmxFa.state eq '1' ? 'readonly:true,' : ''} onChange:function(checked) {if (checked) {zdmxFx.start(this, '${zg006ZdmxFa.faName}');}}"/>						
				</div>
			</div>
			<div class="FA_config_main">
				<div class="FA_config_main_title">
					<div class="title_h">权值度</div>
					<div class="title_v">90%</div>
					<div class="title_v">60%</div>
					<div class="title_v">30%</div>
					<div class="title_v">0%</div>
				</div>
				<div class="FA_config_main_li">
					<div class="FA_config_main_slider">
						<input type="hidden" class="easyui-slider" value="${zg006ZdmxFa.bcyxWeight}" style="height:200px" data-options="mode:'v',step:30,showTip:true,onChange:function(newValue) {zdmxFx.adjust(newValue, '${zg006ZdmxFa.id}', 'bcyx');}">
					</div>
					<div class="FA_config_main_slider_text">病例影像记录</div>
				</div>
				<div class="FA_config_main_li">
					<div class="FA_config_main_slider">
						<input type="hidden" class="easyui-slider" value="${zg006ZdmxFa.jyxxWeight}" style="height:200px" data-options="mode:'v',step:30,showTip:true,onChange:function(newValue) {zdmxFx.adjust(newValue, '${zg006ZdmxFa.id}', 'jyxx');}">
					</div>
					<div class="FA_config_main_slider_text">检验信息</div>
				</div>
				<div class="FA_config_main_li">
					<div class="FA_config_main_slider">
						<input type="hidden" class="easyui-slider" value="${zg006ZdmxFa.xjppWeight}" style="height:200px" data-options="mode:'v',step:30,showTip:true,onChange:function(newValue) {zdmxFx.adjust(newValue, '${zg006ZdmxFa.id}', 'xjpp');}">
					</div>
					<div class="FA_config_main_slider_text">细菌培养</div>
				</div>
				<div class="FA_config_main_li">
					<div class="FA_config_main_slider">
						<input type="hidden" class="easyui-slider" value="${zg006ZdmxFa.kjyyWeight}" style="height:200px" data-options="mode:'v',step:30,showTip:true,onChange:function(newValue) {zdmxFx.adjust(newValue, '${zg006ZdmxFa.id}', 'kjyy');}">
					</div>
					<div class="FA_config_main_slider_text">抗菌药物</div>
				</div>
				<div class="FA_config_main_li">
					<div class="FA_config_main_slider">
						<input type="hidden" class="easyui-slider" value="${zg006ZdmxFa.tyxzbWeight}" style="height:200px" data-options="mode:'v',step:30,showTip:true,onChange:function(newValue) {zdmxFx.adjust(newValue, '${zg006ZdmxFa.id}', 'tyxzb');}">
					</div>
					<div class="FA_config_main_slider_text">特异性指标</div>
				</div>
			</div>
		</div>
	</c:forEach>
		</div>
<script>
var zdmxFx = {
	//启用
	start : function(obj, faName) {
		var id = $(obj).attr('id-data');
		$.ajax({
            url: '${webroot}/zg006ZdmxFa/f_json/start.shtml',
            type: 'post',
            data: { id: id },
            dataType: 'json',
            success : function(json) {
				if(json.result==='success') {
					$('.easyui-switchbutton').each(function() {
						if ($(this).attr('id-data') !== id) {
							$(this).switchbutton('uncheck');
							$(this).switchbutton('readonly', false);
						}
					});
					$(obj).switchbutton('readonly', true);
					parent.$.messager.show({ title: '提示', msg: '【' + faName + '】启用成功！' });
		    	} else if(json.result === 'error') {
		    		$(obj).switchbutton('uncheck');
		    		parent.$.messager.show({ title: '提示', msg: '【' + faName + '】启用异常！' });
		    	} else {
		    		$(obj).switchbutton('uncheck');
		    		parent.$.messager.show({ title: '提示', msg: json.msg });
		    	}
			}
    	});
	},
	//调节权重
	adjust : function(newValue, id, type) {
		$.ajax({
            url: '${webroot}/zg006ZdmxFa/f_json/adjust.shtml',
            type: 'post',
            data: { id: id, weight : newValue, type : type },
            dataType: 'json',
            success : function(json) {
				if(json.result==='success') {
					parent.$.messager.show({ title: '提示', msg: '调节成功！' });
		    	} else if(json.result === 'error') {
		    		parent.$.messager.show({ title: '提示', msg: '调节异常！' });
		    	} else {
		    		parent.$.messager.show({ title: '提示', msg: json.msg });
		    	}
			}
    	});
	},
	//保存方案描述
	saveFaDescribe : function(obj, id) {
		var describe = $(obj).val();
		if ($.trim(describe).length == 0) {
			$.messager.show({ title: '提示', msg: '方案说明不能为空！' });
		} else {
			$.ajax({
	            url: '${webroot}/zg006ZdmxFa/f_json/saveDescribe.shtml',
	            type: 'post',
	            data: { id: id, faDescribe : describe },
	            dataType: 'json',
	            success : function(json) {
					if (json.result==='success') {
						parent.$.messager.show({ title: '提示', msg: '保存成功！' });
			    	} else if(json.result === 'error') {
			    		parent.$.messager.show({ title: '提示', msg: '保存异常！' });
			    	} else {
			    		parent.$.messager.show({ title: '提示', msg: json.msg });
			    	}
				}
	    	});
		}
	}
};
$(document).ready(function() {
});
</script>
	</body>
</html>