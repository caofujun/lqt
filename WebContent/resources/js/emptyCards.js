var EC = {
	emptySelect : function(){
		var curCT = $("#emptyCard option:selected").val();
		if(curCT=='crbbk_empty'){
			parent.menuInfo.clickMenu('传染病报卡上报','/cdc/f_view/reportCardMZ.shtml?type=empty',true);
		}else if(curCT=='sybk_empty'){
			parent.menuInfo.clickMenu('死因报卡上报','/cdc/f_view/deathReport.shtml?type=empty',true);
		}else if(curCT=='syjcbk_empty'){
			parent.menuInfo.clickMenu('食源监测报卡上报','/cdc/f_view/fsmReport.shtml?type=empty',true);
		}else if(curCT=='zlbk_empty'){
			parent.menuInfo.clickMenu('肿瘤病例报卡上报','/cdc/f_view/tumourReport.shtml?type=empty',true);
		}else if(curCT=='syycbk_empty'){
			parent.menuInfo.clickMenu('食源异常报卡上报','/cdc/f_view/fsaReport.shtml?type=empty',true);
		}else if(curCT=="xnxgbk_empty"){
			parent.menuInfo.clickMenu('心脑血管事件报卡上报','/cdc/f_view/hcvReport.shtml?type=empty',true);
		}else if(curCT=="gwzsbk_empty"){
			parent.menuInfo.clickMenu('高温中暑病例报卡上报','/cdc/f_view/sunstrokeReport.shtml?type=empty',true);
		}else if(curCT=="nyzdbk_empty"){
			parent.menuInfo.clickMenu('农药中毒报卡上报','/cdc/f_view/nyzdReport.shtml?type=empty',true);
		}
		$("#emptyCard option[value='']").attr("selected","selected");
	}
}