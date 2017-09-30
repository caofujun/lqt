<div class="home_box home_table2 menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">待处理 &nbsp;<span id="dclDate" onClick="dateDcl()"></span>
			<input type="hidden" id="dclDateStr"/>
			<div class="btn-group">							
					<button type="button" id="dayDclButton" class="btn btn-default cur" onClick="chooseDclDate('day')">当日</button>
					<button type="button" id="weekDclButton" class="btn btn-default" onClick="chooseDclDate('week')">一周</button>
					<button type="button" id="monthDclButton" class="btn btn-default" onClick="chooseDclDate('month')">本月</button>
			</div>
			</div>
			<div class="con" id="dcl" style="height:100px;">
				<table cellSpacing="0" cellPadding="0">
					<tr>
						<th width="100">病例预警：</th>
						<td><span class="b"><a href="#" onClick="parent.menuInfo.clickMenu('感染预警','/gr002YsgrMx/f_view/toCasesWarning.shtml?isAll=1&startDate=' + getStartDate() + '&endDate=' + getEndDate(),true)">{{YJCOUNT}}</a></span></td>
						<th>报卡：</th>
						<td><span class="b"><a href="#" onClick="parent.menuInfo.clickMenu('感染病例查询','/bk001Sbk/f_view/toInfectionsQuery.shtml?bkState=0&queryStartDate='+ getStartDate() + '&queryEndDate='+ getEndDate() ,true)">{{BKCOUNT}}</a></span></td>
						<th>暴发预警：</th>					
						<td><span class="b">{{BFYJCOUNT}}</span></td>					
					</tr>
					<tr>
						<th>暴露待复查：</th>					
						<td><span class="b"><a href="#" onClick="parent.menuInfo.clickMenu('职业暴露','/bl002Sjdj/f_view/index.shtml?startDateFc='+ getStartDate() + '&endDateFc=' + getEndDate(),true)">{{FCCOUNT}}</a></span></td>
						<th>风险因素：</th>					
						<td><span class="b"><a href="#" onClick="parent.menuInfo.clickMenu('风险分析','/fxPatient/f_view/index.shtml?startDate='+ getStartDate() + '&endDate=' + getEndDate(),true)">{{FXCOUNT}}</a></span></td>
						<th>多重耐药菌：</th>
						<td><span class="b"><a href="#" onClick="parent.menuInfo.clickMenu('多重耐药菌检出','/xn011Dclymx/f_view/index.shtml?startDate='+ getStartDate() + '&endDate='+ getEndDate() ,true)">{{XNCOUNT}}</a></span></td>
					</tr>
					<tr id="qrxDiv">
						<th>呼吸机：</th>					
						<td><span class="b"><a href="#" onClick="parent.menuInfo.clickMenu('监测日报','/gm003Ybsj/f_view/dayIndex.shtml?dayDate='+ getStartDate(),true)">{{HXJCOUNT}}</a></span></td>
						<th>中心静脉插管：</th>					
						<td><span class="b"><a href="#" onClick="parent.menuInfo.clickMenu('监测日报','/gm003Ybsj/f_view/dayIndex.shtml?dayDate='+ getStartDate(),true)">{{ZXJMCOUNT}}</a></span></td>
						<th>泌尿道插管：</th>					
						<td><span class="b"><a href="#" onClick="parent.menuInfo.clickMenu('监测日报','/gm003Ybsj/f_view/dayIndex.shtml?dayDate='+ getStartDate(),true)">{{MNDCOUNT}}</a></span></td>
					</tr>
				</table>	
			</div>
</div>
<script>
var dclhtml = $('#dcl').html(); 
Homepage.initDcl('dcl','day',dclhtml);
function chooseDclDate(value){
	if(value=='day'){
		$('#qrxDiv').show();
	}else{
		$('#qrxDiv').hide();
	}
	$('#dayDclButton').attr("class","btn btn-default");
	$('#weekDclButton').attr("class","btn btn-default");
	$('#monthDclButton').attr("class","btn btn-default");
	$("#"+value+"DclButton").attr("class","btn btn-default cur");
	Homepage.initDcl('dcl',value,dclhtml);
}
function getStartDate() {
	return $("#dclDateStr").val().substring(1,11);
}
function getEndDate() {
	return $("#dclDateStr").val().substring(12,22);
}
</script>
