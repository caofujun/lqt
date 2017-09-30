<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">今日工作提示</div>
			<div class="con" id="followUpTodayTaskRemind">
				<ul class="li_float">
				 {{each rows as obj i}}
		    		<li><span class="mr10">{{obj.NAME}}</span><span class="b blue">{{obj.COUNT}}</span></li>
				 {{/each}}
				</ul>
				<div class="clear"></div>
			</div>
</div>
<script>Homepage.initfollowUpTodayTaskRemind('followUpTodayTaskRemind');</script>
