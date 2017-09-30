<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">投诉工作提醒</div>
			<div class="con" id="complainRemind">
				<ul class="li_float">
				<li>
					<span class="mr10">总投诉</span><span class="b blue"><a option='index' d='check_all_state' title="查看总投诉">{{COUNT}}13</a></span>
				</li>
				<li>
					<span class="mr10">待审核</span><span class="b blue"><a option='index' d='un_check_state' title="查看待审核">{{UNAUDIT}}12</a></span>
				</li>
				<li>
					<span class="mr10">待处理</span><span class="b blue"><a option='dealList' d='un_deal_state' title="查看待处理">{{UNHANDLE}}12</a></span>
				</li>
				<li>
					<span class="mr10">待归档</span><span class="b blue"><a option='closeList' d='un_guidang_state' title="查看待归档">{{UNFILE}}13</a></span>
				</li>
				</ul>
				<div class="clear"></div>
			</div>
</div>
<script>Homepage.initComplainRemind('complainRemind');
	$(function() {
		$('#complainRemind a').live('click',function () {
			var state =$(this).attr('d');
			var option = $(this).attr('option');
			parent.menuInfo.clickMenu('投诉审核','/fbFeedback/f_view/indexMain.shtml?theOption='+option+'&feedBackUIState='+state+'&starDate='+curMonthFirst+'&endDate='+curMonthLast,true);
		});
	})
</script>
