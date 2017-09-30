<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">职业暴露发生岗位统计&nbsp;<span id="zyblfsgwtjDate"></span>
			<input type="hidden" id="zyblfsgwtjDateStr"/>
			<div class="btn-group">					
					<button type="button" id="dayZyblfsgwtjButton" class="btn btn-default cur" onClick="chooseZyblfsgwtjDate('day')">当日</button>
					<button type="button" id="weekZyblfsgwtjButton" class="btn btn-default" onClick="chooseZyblfsgwtjDate('week')">一周</button>
					<button type="button" id="monthZyblfsgwtjButton" class="btn btn-default" onClick="chooseZyblfsgwtjDate('month')">本月</button>
			</div>
			</div>
			<div class="con con_arrow">
				<span class="arrow_left" onClick="orderZyblfsgwtj('back')"></span><span class="arrow_right" onClick="orderZyblfsgwtj('next')"></span>
				<div id="zyblfsgwtj" style="height:220px;"></div>
			</div>
			<input type="hidden" id="zyblfsgwtjDateType" value="day"/>
			<div class="home_box_select">
				<span class="mr20"><input type="checkbox" onClick="chooseZyblfsgwtjIcu()" name="zyblfsgwtjIficu" checked="checked" value="1">ICU科室</span>
				<span class="mr20"><input type="checkbox" name="zyblfsgwtjIficu" onClick="chooseZyblfsgwtjIcu()" checked="checked" value="0">普通科室</span>		
			</div>
</div>
<script>
Homepage.initzyblfsgwtj('zyblfsgwtj','day','1,0,');
function chooseZyblfsgwtjDate(value){
	var zyblfsgwtjIficu = '';
	$("input:checkbox[name='zyblfsgwtjIficu']:checked").each(function(){ 
		zyblfsgwtjIficu += $(this).val() + ',';
	});
	$('#dayZyblfsgwtjButton').attr("class","btn btn-default");
	$('#weekZyblfsgwtjButton').attr("class","btn btn-default");
	$('#monthZyblfsgwtjButton').attr("class","btn btn-default");
	$("#"+value+"ZyblfsgwtjButton").attr("class","btn btn-default cur");
	Homepage.initzyblfsgwtj('zyblfsgwtj',value,zyblfsgwtjIficu);
	$('#zyblfsgwtjDateType').val(value);
}
function chooseZyblfsgwtjIcu(){
	var date = $("#zyblfsgwtjDate").html().substring(1,11);
	var zyblfsgwtjIficu = '';
	$("input:checkbox[name='zyblfsgwtjIficu']:checked").each(function(){ 
		zyblfsgwtjIficu += $(this).val() + ',';
	});
	Homepage.initzyblfsgwtj('zyblfsgwtj',$('#zyblfsgwtjDateType').val(),zyblfsgwtjIficu,date);
}
function orderZyblfsgwtj(value){
	var date = $("#zyblfsgwtjDate").html().substring(1,11);
	var zyblfsgwtjIficu = '';
	$("input:checkbox[name='zyblfsgwtjIficu']:checked").each(function(){ 
		zyblfsgwtjIficu += $(this).val() + ',';
	});
	Homepage.initzyblfsgwtj('zyblfsgwtj',$('#zyblfsgwtjDateType').val(),zyblfsgwtjIficu,date,value);
}
</script>