<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">感染病例分布图&nbsp;<span id="grblfbtDate"></span>
				<input type="hidden" id="grblfbtDateStr"/>	
				<div class="btn-group">						
						<button type="button" id="dayGrblfbtButton" class="btn btn-default cur" onClick="chooseGrblfbtDate('day')">当日</button>
						<button type="button" id="weekGrblfbtButton" class="btn btn-default" onClick="chooseGrblfbtDate('week')">一周</button>
						<button type="button" id="monthGrblfbtButton" class="btn btn-default" onClick="chooseGrblfbtDate('month')">本月</button>
				</div>
			</div>
			<div class="con con_arrow">
				<span class="arrow_left" onClick="orderGrblfbt('back')"></span><span class="arrow_right" onClick="orderGrblfbt('next')"></span>
				<div id="grblfbt" style="height:220px;"></div>
			</div>
			<input type="hidden" id="grblfbtDateType" value="day"/>
			<div class="home_box_select">
				<span class="mr20"><input type="checkbox" onClick="chooseGrblfbtIcu()" name="grblfbtIficu" checked="checked" value="1">ICU科室</span>
				<span><input type="checkbox" name="grblfbtIficu" onClick="chooseGrblfbtIcu()" checked="checked" value="0">普通科室</span>			
			</div>
</div>
<script>
Homepage.initGrblfbt('grblfbt','day','1,0,');
function chooseGrblfbtDate(value){
	var grblfbtIficu = '';
	$("input:checkbox[name='grblfbtIficu']:checked").each(function(){ 
		grblfbtIficu += $(this).val() + ',';
	});
	$('#dayGrblfbtButton').attr("class","btn btn-default");
	$('#weekGrblfbtButton').attr("class","btn btn-default");
	$('#monthGrblfbtButton').attr("class","btn btn-default");
	$("#"+value+"GrblfbtButton").attr("class","btn btn-default cur");
	Homepage.initGrblfbt('grblfbt',value,grblfbtIficu);
	$('#grblfbtDateType').val(value);
}
function chooseGrblfbtIcu(){
	var date = $("#grblfbtDate").html().substring(1,11);
	var grblfbtIficu = '';
	$("input:checkbox[name='grblfbtIficu']:checked").each(function(){ 
		grblfbtIficu += $(this).val() + ',';
	});
	Homepage.initGrblfbt('grblfbt',$('#grblfbtDateType').val(),grblfbtIficu,date);
}
function orderGrblfbt(value){
	var date = $("#grblfbtDate").html().substring(1,11);
	var grblfbtIficu = '';
	$("input:checkbox[name='grblfbtIficu']:checked").each(function(){ 
		grblfbtIficu += $(this).val() + ',';
	});
	Homepage.initGrblfbt('grblfbt',$('#grblfbtDateType').val(),grblfbtIficu,date,value);
}
</script>