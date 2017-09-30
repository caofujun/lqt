<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">多重耐药分布图&nbsp;<span id="dcnyfbtDate"></span>
			<input type="hidden" id="dcnyfbtDateStr"/>	
			<div class="btn-group">					
					<button type="button" id="dayDcnyfbtButton" class="btn btn-default cur" onClick="chooseDcnyfbtDate('day')">当日</button>
					<button type="button" id="weekDcnyfbtButton" class="btn btn-default" onClick="chooseDcnyfbtDate('week')">一周</button>
					<button type="button" id="monthDcnyfbtButton" class="btn btn-default" onClick="chooseDcnyfbtDate('month')">本月</button>
			</div>
			</div>
			<div class="con con_arrow">
				<span class="arrow_left" onClick="orderDcnyfbt('back')"></span><span class="arrow_right" onClick="orderDcnyfbt('next')"></span>
				<div id="dcnyfbt" style="height:220px;"></div>
			</div>
			<input type="hidden" id="dcnyfbtDateType" value="day"/>
			<div class="home_box_select">
				<span class="mr20"><input type="checkbox" onClick="chooseDcnyfbtIcu()" name="dcnyfbtIficu" checked="checked" value="1">ICU科室</span>
				<span class="mr20"><input type="checkbox" name="dcnyfbtIficu" onClick="chooseDcnyfbtIcu()" checked="checked" value="0">普通科室</span>
				<span><input type="checkbox" name="dcnyfbtIficu" onClick="chooseDcnyfbtIcu()" checked="checked" value="2">管辖科室</span>			
			</div>
</div>
<script>
Homepage.initDcnyfbt('dcnyfbt','day','1,0,');
function chooseDcnyfbtDate(value){
	var dcnyfbtIficu = '';
	$("input:checkbox[name='dcnyfbtIficu']:checked").each(function(){ 
		dcnyfbtIficu += $(this).val() + ',';
	});
	$('#dayDcnyfbtButton').attr("class","btn btn-default");
	$('#weekDcnyfbtButton').attr("class","btn btn-default");
	$('#monthDcnyfbtButton').attr("class","btn btn-default");
	$("#"+value+"DcnyfbtButton").attr("class","btn btn-default cur");
	Homepage.initDcnyfbt('dcnyfbt',value,dcnyfbtIficu);
	$('#dcnyfbtDateType').val(value);
}
function chooseDcnyfbtIcu(){
	var date = $("#dcnyfbtDate").html().substring(1,11);
	var dcnyfbtIficu = '';
	$("input:checkbox[name='dcnyfbtIficu']:checked").each(function(){ 
		dcnyfbtIficu += $(this).val() + ',';
	});
	Homepage.initDcnyfbt('dcnyfbt',$('#dcnyfbtDateType').val(),dcnyfbtIficu,date);
}
function orderDcnyfbt(value){
	var date = $("#dcnyfbtDate").html().substring(1,11);
	var dcnyfbtIficu = '';
	$("input:checkbox[name='dcnyfbtIficu']:checked").each(function(){ 
		dcnyfbtIficu += $(this).val() + ',';
	});
	Homepage.initDcnyfbt('dcnyfbt',$('#dcnyfbtDateType').val(),dcnyfbtIficu,date,value);
}
</script>