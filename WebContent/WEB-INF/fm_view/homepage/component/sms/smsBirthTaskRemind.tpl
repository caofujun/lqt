<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
			<div class="cap">生日短信关怀提醒</div>
			<div class="con" id="smsBirthTaskRemind">
				<ul class="li_float">
				 {{each rows as obj i}}
		    		<li><span class="mr10">{{obj.NAME}}</span><span class="b blue">{{obj.COUNT}}</span></li>
				 {{/each}}
				</ul>
				<div class="clear"></div>
			</div>
</div>
<script>Homepage.initSmsBirthTaskRemind('smsBirthTaskRemind');</script>
