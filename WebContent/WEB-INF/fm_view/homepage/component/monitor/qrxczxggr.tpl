<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">三管相关性感染&nbsp;<span id="qrxczxgxgrDate"></span>
				<input type="hidden" id="qrxczxgxgrDateStr"/>
				<div class="btn-group">					
					<button type="button" id="weekQrxButton" class="btn btn-default cur" onClick="chooseQrxDate('week')">一周</button>
					<button type="button" id="monthQrxButton" class="btn btn-default" onClick="chooseQrxDate('month')">本月</button>
				</div>
			</div>
			<div class="con con_arrow">
				<span class="arrow_left" onClick="orderQrxczxgxgr('back')"></span><span class="arrow_right" onClick="orderQrxczxgxgr('next')"></span>
				<div id="qrxczxgxgr" style="height:220px;"></div>
			</div>
			<input type="hidden" id="qrxczxgxgrDateType" value="week"/>
			<div class="home_box_select">
				<span class="mr20"><input type="checkbox" onClick="chooseQrxczxgxgrIcu()" name="qrxczxgxgrIficu" checked="checked" value="1">ICU科室</span>
				<span><input type="checkbox" name="qrxczxgxgrIficu" onClick="chooseQrxczxgxgrIcu()" checked="checked" value="0">普通科室</span>			
			</div>
</div>
<script>
Homepage.initqrxczxgxgr('qrxczxgxgr','week','1,0,');
function chooseQrxDate(value){
	var qrxczxgxgrIficu = '';
	$("input:checkbox[name='qrxczxgxgrIficu']:checked").each(function(){ 
		qrxczxgxgrIficu += $(this).val() + ',';
	});
	$('#weekQrxButton').attr("class","btn btn-default");
	$('#monthQrxButton').attr("class","btn btn-default");
	$("#"+value+"QrxButton").attr("class","btn btn-default cur");
	Homepage.initqrxczxgxgr('qrxczxgxgr',value,qrxczxgxgrIficu);
	$('#qrxczxgxgrDateType').val(value);
}
function chooseQrxczxgxgrIcu(){
	var date = $("#qrxczxgxgrDate").html().substring(1,11);
	var qrxczxgxgrIficu = '';
	$("input:checkbox[name='qrxczxgxgrIficu']:checked").each(function(){ 
		qrxczxgxgrIficu += $(this).val() + ',';
	});
	Homepage.initqrxczxgxgr('qrxczxgxgr',$('#qrxczxgxgrDateType').val(),qrxczxgxgrIficu,date);
}
function orderQrxczxgxgr(value){
	var date = $("#qrxczxgxgrDate").html().substring(1,11);
	var qrxczxgxgrIficu = '';
	$("input:checkbox[name='qrxczxgxgrIficu']:checked").each(function(){ 
		qrxczxgxgrIficu += $(this).val() + ',';
	});
	Homepage.initqrxczxgxgr('qrxczxgxgr',$('#qrxczxgxgrDateType').val(),qrxczxgxgrIficu,date,value);
}
</script>