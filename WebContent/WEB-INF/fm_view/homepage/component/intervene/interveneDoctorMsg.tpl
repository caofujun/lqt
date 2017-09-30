<div class="home_box textLi menuSortInfo" data-id="${component.componentCode}">
	<div class="cap">干预消息提醒</div>
	<div class="con" id="interveneDoctorMsg" style="height:250px;">
		<ul>
		 {{each rows as obj i}}
    		<li><span class="blue"><a zyid='{{obj.zyid}}'>{{obj.theme}} &gt;&gt;</a></span></li>
		 {{/each}}
		</ul>
		<div class="summary"><a zyid='all'>共<span class="b orange">{{total}}</span>条记录 查看全部&gt;&gt;</a></div>
	</div>
</div>
<script>
	Homepage.initInterveneDoctorMsg('interveneDoctorMsg','${loginUser.userId}');
	$(function () {
		$('#interveneDoctorMsg a').live('click',function() {
			var zyid = $(this).attr('zyid');
			if(zyid==="all"){
				parent.menuInfo.clickMenu('干预会话','/nyMessageTheme/f_view/index.shtml?userId=${loginUser.userId}');
				return;
			}
	        Comm.dialogGlobal({
	        	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid=" + zyid,
	            title: "干预会话",
	            width:870,
	            height:550,
	            type:"iframe",
	            parent:this
	        });
		});
	});
</script>