<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">职业暴露人员科室分布&nbsp;<span id="zyblryksfbDate"></span>
			<input type="hidden" id="zyblryksfbDateStr"/>
			<div class="btn-group">					
					<button type="button" id="dayZyblryksfbButton" class="btn btn-default cur" onClick="chooseZyblryksfbDate('day')">当日</button>
					<button type="button" id="weekZyblryksfbButton" class="btn btn-default" onClick="chooseZyblryksfbDate('week')">一周</button>
					<button type="button" id="monthZyblryksfbButton" class="btn btn-default" onClick="chooseZyblryksfbDate('month')">本月</button>
			</div>
			</div>
			<div class="con con_arrow">
				<span class="arrow_left" onClick="orderZyblryksfb('back')"></span><span class="arrow_right" onClick="orderZyblryksfb('next')"></span>
				<div id="zyblryksfb" style="height:220px;"></div>
			</div>
			<input type="hidden" id="zyblryksfbDateType" value="day"/>
			<div class="home_box_select">
				<span class="mr20"><input type="checkbox" onClick="chooseZyblryksfbIcu()" name="zyblryksfbIficu" checked="checked" value="1">ICU科室</span>
				<span class="mr20"><input type="checkbox" name="zyblryksfbIficu" onClick="chooseZyblryksfbIcu()" checked="checked" value="0">普通科室</span>		
			</div>
</div>
<script>
Homepage.initzyblryksfb('zyblryksfb','day','1,0,');
function chooseZyblryksfbDate(value){
var zyblryksfbIficu = '';
	$("input:checkbox[name='zyblryksfbIficu']:checked").each(function(){ 
		zyblryksfbIficu += $(this).val() + ',';
	});
	$('#dayZyblryksfbButton').attr("class","btn btn-default");
	$('#weekZyblryksfbButton').attr("class","btn btn-default");
	$('#monthZyblryksfbButton').attr("class","btn btn-default");
	$("#"+value+"ZyblryksfbButton").attr("class","btn btn-default cur");
	Homepage.initzyblryksfb('zyblryksfb',value,zyblryksfbIficu);
	$('#zyblryksfbDateType').val(value);
}
function chooseZyblryksfbIcu(){
	var date = $("#zyblryksfbDate").html().substring(1,11);
	var zyblryksfbIficu = '';
	$("input:checkbox[name='zyblryksfbIficu']:checked").each(function(){ 
		zyblryksfbIficu += $(this).val() + ',';
	});
	Homepage.initzyblryksfb('zyblryksfb',$('#zyblryksfbDateType').val(),zyblryksfbIficu,date);
}
function orderZyblryksfb(value){
	var date = $("#zyblryksfbDate").html().substring(1,11);
	var zyblryksfbIficu = '';
	$("input:checkbox[name='zyblryksfbIficu']:checked").each(function(){ 
		zyblryksfbIficu += $(this).val() + ',';
	});
	Homepage.initzyblryksfb('zyblryksfb',$('#zyblryksfbDateType').val(),zyblryksfbIficu,date,value);
}
</script>