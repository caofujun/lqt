<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">临床细菌培养标本分布&nbsp;<span id="wswbbfbDate"></span>
			<input type="hidden" id="wswbbfbDateStr"/>
			<div class="btn-group">					
					<button type="button" id="dayWswbbfbButton" class="btn btn-default cur" onClick="chooseWswbbfbDate('day')">当日</button>
					<button type="button" id="weekWswbbfbButton" class="btn btn-default" onClick="chooseWswbbfbDate('week')">一周</button>
					<button type="button" id="monthWswbbfbButton" class="btn btn-default" onClick="chooseWswbbfbDate('month')">本月</button>
			</div>
			</div>
			<div class="con con_arrow">
				<span class="arrow_left" onClick="orderWswbbfb('back')"></span><span class="arrow_right" onClick="orderWswbbfb('next')"></span>
				<div id="wswbbfb" style="height:220px;"></div>
			</div>
			<input type="hidden" id="wswbbfbDateType" value="day"/>
			<div class="home_box_select">
				<span class="mr20"><input type="checkbox" onClick="chooseWswbbfbIcu()" name="wswbbfbIficu" checked="checked" value="1">ICU科室</span>
				<span><input type="checkbox" name="wswbbfbIficu" onClick="chooseWswbbfbIcu()" checked="checked" value="0">普通科室</span>			
			</div>
</div>
<script>
Homepage.initwswbbfb('wswbbfb','day','1,0,');
function chooseWswbbfbDate(value){
	var wswbbfbIficu = '';
	$("input:checkbox[name='wswbbfbIficu']:checked").each(function(){ 
		wswbbfbIficu += $(this).val() + ',';
	});
	$('#dayWswbbfbButton').attr("class","btn btn-default");
	$('#weekWswbbfbButton').attr("class","btn btn-default");
	$('#monthWswbbfbButton').attr("class","btn btn-default");
	$("#"+value+"WswbbfbButton").attr("class","btn btn-default cur");
	Homepage.initwswbbfb('wswbbfb',value,wswbbfbIficu);
	$('#wswbbfbDateType').val(value);
}
function chooseWswbbfbIcu(){
	var date = $("#wswbbfbDate").html().substring(1,11);
	var wswbbfbIficu = '';
	$("input:checkbox[name='wswbbfbIficu']:checked").each(function(){ 
		wswbbfbIficu += $(this).val() + ',';
	});
	Homepage.initwswbbfb('wswbbfb',$('#wswbbfbDateType').val(),wswbbfbIficu,date);
}
function orderWswbbfb(value){
	var date = $("#wswbbfbDate").html().substring(1,11);
	var wswbbfbIficu = '';
	$("input:checkbox[name='wswbbfbIficu']:checked").each(function(){ 
		wswbbfbIficu += $(this).val() + ',';
	});
	Homepage.initwswbbfb('wswbbfb',$('#wswbbfbDateType').val(),wswbbfbIficu,date,value);
}
</script>