<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">临床细菌培养检出情况&nbsp;<span id="wswjcqkDate"></span>
			<input type="hidden" id="wswjcqkDateStr"/>
			<div class="btn-group">					
					<button type="button" id="dayWswjcqkButton" class="btn btn-default cur" onClick="chooseWswjcqkDate('day')">当日</button>
					<button type="button" id="weekWswjcqkButton" class="btn btn-default" onClick="chooseWswjcqkDate('week')">一周</button>
					<button type="button" id="monthWswjcqkButton" class="btn btn-default" onClick="chooseWswjcqkDate('month')">本月</button>
			</div>
			</div>
			<div class="con con_arrow">
				<span class="arrow_left" onClick="orderWswjcqk('back')"></span><span class="arrow_right" onClick="orderWswjcqk('next')"></span>
				<div id="wswjcqk" style="height:220px;"></div>
			</div>
			<input type="hidden" id="wswjcqkDateType" value="day"/>
			<div class="home_box_select">
				<span class="mr20"><input type="checkbox" onClick="chooseWswjcqkIcu()" name="wswjcqkIficu" checked="checked" value="1">ICU科室</span>
				<span><input type="checkbox" onClick="chooseWswjcqkIcu()" name="wswjcqkIficu" checked="checked" value="0">普通科室</span>		
			</div>
</div>
<script>
Homepage.initwswjcqk('wswjcqk','day','1,0,');
function chooseWswjcqkDate(value){
	var wswjcqkIficu = '';
	$("input:checkbox[name='wswjcqkIficu']:checked").each(function(){ 
		wswjcqkIficu += $(this).val() + ',';
	});
	$('#dayWswjcqkButton').attr("class","btn btn-default");
	$('#weekWswjcqkButton').attr("class","btn btn-default");
	$('#monthWswjcqkButton').attr("class","btn btn-default");
	$("#"+value+"WswjcqkButton").attr("class","btn btn-default cur");
	Homepage.initwswjcqk('wswjcqk',value,wswjcqkIficu);
	$('#wswjcqkDateType').val(value);
}
function chooseWswjcqkIcu(){
	var date = $("#wswjcqkDate").html().substring(1,11);
	var wswjcqkIficu = '';
	$("input:checkbox[name='wswjcqkIficu']:checked").each(function(){ 
		wswjcqkIficu += $(this).val() + ',';
	});
	Homepage.initwswjcqk('wswjcqk',$('#wswjcqkDateType').val(),wswjcqkIficu,date);
}
function orderWswjcqk(value){
	var date = $("#wswjcqkDate").html().substring(1,11);
	var wswjcqkIficu = '';
	$("input:checkbox[name='wswjcqkIficu']:checked").each(function(){ 
		wswjcqkIficu += $(this).val() + ',';
	});
	Homepage.initwswjcqk('wswjcqk',$('#wswjcqkDateType').val(),wswjcqkIficu,date,value);
}
</script>