<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
	<div class="cap">投诉待审核提醒</div>
	<div class="con" id="complainUnaudit">
		<ul>
		 {{each rows as obj i}}
    		<li><span class="mr10">{{obj.tsTime}}</span><span class="blue"><a fid='{{obj.fId}}'>{{obj.content}}&gt;&gt;</span></a></li>
		 {{/each}}
		</ul>
		<div class="summary"><a fid='all'>共<span class="b orange">{{total}}</span>条记录 查看全部&gt;&gt;</a></div>
	</div>
</div>
<script>
	Homepage.initComplainRemindByType('complainUnaudit','1');
	$(function () {
		$('#complainUnaudit a').live('click',function() {
			var fid = $(this).attr('fid');
			if (fid == 'all') {
				parent.menuInfo.clickMenu('投诉审核','/fbFeedback/f_view/indexMain.shtml?theOption=index&feedBackUIState=un_check_state',true);
			}else{
				parent.menuInfo.clickMenu('投诉审核','/fbFeedback/f_view/indexMain.shtml?theOption=index&fId='+fid,true);
			}
		});
	});
</script>