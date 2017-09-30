<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>感染病例预警</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="id_casesWarning_tab" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:false,border:false" style="width:240px;border-right-width: 1px;">
			<div id="infect_monitor_tab" class="easyui-tabs" data-options="fit:true,plain:true,pill:true,justified:true,border:false">
			    <div title="待处理" style="overflow:hidden;" data-options="href:'${webroot}/gr002YsgrMx/f_view/toWarningTree.shtml?type=wait&isAll=${param.isAll}'">
			        
			    </div>
			    <div title="关注患者" style="overflow:hidden;" data-options="href:'${webroot}/gr002YsgrMx/f_view/toWarningTree.shtml?type=favorit'">
			    	
			    </div>
			    <div title="全部" style="overflow:hidden" data-options="href:'${webroot}/gr002YsgrMx/f_view/toWarningTree.shtml?type=all'">
			    	
			    </div>
			</div>
		</div>
		<div data-options="region:'center',border:false">
        </div>
	</div>
    
<script type="text/javascript">
	var casesWarning = {
		query : function(obj, type, method, cls) {
			if (cls == 'date') {
				$('.search_1_' + type).removeClass('cur');
				$('#id_isInHosp_2_' + type).addClass('cur');
			} else if (cls) {
				$('.' + cls).removeClass('cur');
				$(obj).addClass('cur');
			}
			var isInHosp = $("div[name='isInHosp_" + type + "'].cur").attr('value');
			var dataRange = $("a[name='dataRange_" + type + "'].cur").attr('value');
			var queryDate = $('#queryDate_' + type).val() + '-01';
			var infectTypeId = $('#id_infectType_' + type).attr('value');
			/* if (isInHosp == 2) {
				$('#queryDate_' + type).show();
			} else {
				$('#queryDate_' + type).hide();
			} */
			//更新统计数
			$.ajax({
                url:webroot + '/gr002YsgrMx/f_json/findInAndOutCount.shtml',
                type: 'post',
                data: { type: type, queryDate: queryDate, isInHosp : isInHosp, infectTypeId: infectTypeId},
                dataType: 'json',
                success : function(json) {
               		$('#id_dataRange_1_' + type).html('管辖(' + json.inNum + ')'); 
               		$('#id_dataRange_0_' + type).html('全部(' + json.outNum + ')'); 
				}
    		});
			$('#warningTreePanel_' + type).tree({
	            url: '${webroot}/gr002YsgrMx/f_json/' + method + '.shtml',
	            queryParams: {
	            	'dataRange':dataRange,
	            	'isInHosp': isInHosp,
	            	'queryDate': queryDate,
	            	'infectTypeId' : infectTypeId
	            }
	        });
		},
		audit : function (zyid) {
			parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=10&zyid=' + zyid,true);
		},
		patientInfo : function (zyid) {
			parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
		},
    	getNotNullStr : function(str) {
    		if (str == null) {
    			return '';
    		} else {
    			return str;
    		}
    	},
    	gethMdDateStr : function(str) {
    		if (str && str.length > 10) {
    			return str.substring(0,10);
    		} else {
    			return '';
    		}
    	},
    	//风险因素
    	riskFactors : function(zyid) {
    		parent.menuInfo.clickMenu('风险详情','/fxPatient/f_view/detail.shtml?zyId='+zyid+"&tab=2",true);
    	},
    	//设置右边病例预警详情
    	setWarningPatient : function(zyid, type) {
    		var infectTypeId = $('#id_infectType_' + type).attr('value');
    		$('#id_casesWarning_tab').layout('panel','center').panel({  
                href : '${webroot}/gr002YsgrMx/f_view/toWarningPatient.shtml?zyid=' + zyid + '&type=' + type + '&infectTypeId=' + infectTypeId+'&isAuth=1'
            });
    	},
    	//院感社感切换
    	setInfectType : function(obj, type, method) {
    		var value = $(obj).attr('value');
    		if (value == '1') {
    			$(obj).html('社');
    			$(obj).attr('value', 2);
    		} else {
    			$(obj).html('院');
    			$(obj).attr('value', 1);
    		}
    		this.query(obj, type, method);
    	}
	};
	
	$(document).ready(function () {
		

	});
</script>
</body>
</html>
