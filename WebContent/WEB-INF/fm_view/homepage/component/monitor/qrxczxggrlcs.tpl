<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">侵入性操作相关性感染例次数&nbsp;<span id="qrxczxgxgrlcsDate"></span>
				<input type="hidden" id="qrxczxgxgrlcsDateStr"/>
				<div class="btn-group">					
					<button type="button" id="weekQrxlcsButton" class="btn btn-default cur" onClick="chooseQrxlcsDate('week')">一周</button>
					<button type="button" id="monthQrxlcsButton" class="btn btn-default" onClick="chooseQrxlcsDate('month')">本月</button>
				</div>
			</div>
			<div class="con con_arrow">
				<span class="arrow_left" onClick="orderQrxczxgxgrlcs('back')"></span><span class="arrow_right" onClick="orderQrxczxgxgrlcs('next')"></span>
				<div id="qrxczxgxgrlcs" style="height:220px;"></div>
			</div>
			<input type="hidden" id="qrxczxgxgrlcsDateType" value="week"/>
			<div class="home_box_select">
				<span class="mr20"><input type="checkbox" onClick="chooseQrxczxgxgrlcsIcu()" name="qrxczxgxgrlcsIficu" checked="checked" value="1">ICU科室</span>
				<span><input type="checkbox" name="qrxczxgxgrlcsIficu" onClick="chooseQrxczxgxgrlcsIcu()" checked="checked" value="0">普通科室</span>			
			</div>
</div>
<script>
Homepage.initqrxczxgxgrlcs('qrxczxgxgrlcs','week','1,0,');
function chooseQrxlcsDate(value){
	var qrxczxgxgrlcsIficu = '';
	$("input:checkbox[name='qrxczxgxgrlcsIficu']:checked").each(function(){ 
		qrxczxgxgrlcsIficu += $(this).val() + ',';
	});
	$('#weekQrxlcsButton').attr("class","btn btn-default");
	$('#monthQrxlcsButton').attr("class","btn btn-default");
	$("#"+value+"QrxlcsButton").attr("class","btn btn-default cur");
	Homepage.initqrxczxgxgrlcs('qrxczxgxgrlcs',value,qrxczxgxgrlcsIficu);
	$('#qrxczxgxgrlcsDateType').val(value);
}
function chooseQrxczxgxgrlcsIcu(){
	var date = $("#qrxczxgxgrlcsDate").html().substring(1,11);
	var qrxczxgxgrlcsIficu = '';
	$("input:checkbox[name='qrxczxgxgrlcsIficu']:checked").each(function(){ 
		qrxczxgxgrlcsIficu += $(this).val() + ',';
	});
	Homepage.initqrxczxgxgrlcs('qrxczxgxgrlcs',$('#qrxczxgxgrlcsDateType').val(),qrxczxgxgrlcsIficu,date);
}
function orderQrxczxgxgrlcs(value){
	var date = $("#qrxczxgxgrlcsDate").html().substring(1,11);
	var qrxczxgxgrlcsIficu = '';
	$("input:checkbox[name='qrxczxgxgrlcsIficu']:checked").each(function(){ 
		qrxczxgxgrlcsIficu += $(this).val() + ',';
	});
	Homepage.initqrxczxgxgrlcs('qrxczxgxgrlcs',$('#qrxczxgxgrlcsDateType').val(),qrxczxgxgrlcsIficu,date,value);
}
</script>