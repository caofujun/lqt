<div class="home_box home_table2 menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">随访工作提醒</div>
			<div class="con" id="followUpExpireTaskRemind">
				<table cellSpacing="0" cellPadding="0">
					<tr>
						<th width="100" colspan="2" style="text-align:left;" ><a class="home_a" title="处理随访工作" ><span class="icon_clock"></span>今日还需随访<span style="color:red;">{{NEED_HAND}}</span>人,请完成随访工作!</a></th>				
					</tr>
					<tr>
						<th width="100">已过期：</th>
						<td><span class="b">{{COUNT}}</span></td>					
					</tr>
					<tr>
						<th>今日到期：</th>
						<td><span class="b">{{NCOUNT}}</span></td>
					</tr>
					<tr>
						<th>最近3天到期：</th>					
						<td><span class="b">{{LTCOUNT}}</span></td>
					</tr>
				</table>	
			</div>
</div>
<script>
Homepage.initFollowUpExpireTaskRemind('followUpExpireTaskRemind');
$(function() {
	$('#followUpExpireTaskRemind a').live('click',function () {
		parent.menuInfo.clickMenu('处理随访工作','/foPatientGroup/f_view/needHandIndex.shtml',true);
	});
})
</script>
