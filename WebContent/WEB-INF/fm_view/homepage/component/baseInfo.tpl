<div class="userInfor menuSortInfo" data-id="${component.componentCode}" style="height: 38px;">
	<div style="line-height: 38px;">
		<span class="name" style="color: #0056ab;font-size: 16px;font-weight: bold; margin:0px 20px;">${loginUser.realname}</span>
		<span class="mr10">欢迎使用,</span>
		<span class="nowdate orange" id="dateMsgPanel">平平安安</span>
		<div class="middle">
			<div class="edit_pwd" style="width:300px;">
				<a class="img_audit" href="javascript:shsl.openLayout()" >修改布局 </a>
				<a class="img_add" href="javascript:shsl.openComponent()" >添加组件 </a>
				<!-- <a class="img_disable" href="javascript:openUpdatePasswd()" >修改密码 </a> -->
			</div>
		</div>
	</div>
</div>

<script>
//显示时间和欢迎语
var dateTip = {
		//提示语
		tipConts : ['','','','','','',''],
		weeks : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		init : function() {
			$.ajax({
	            url: '${webroot}/sysHpComponent/f_json/date.shtml',
	            type: 'post',
	            dataType: 'json',
	            success : function(json) {
					if(json.result==='success') {
						var date = json.data.split("-");
						var myDate = new Date(Number(date[0]), Number(date[1]) - 1, Number(date[2]));
						var _num = myDate.getDay();
						$('#dateMsgPanel').html([Comm.date.formatStr(myDate, 'yyyy-MM-dd'),' ',dateTip.weeks[_num],'',
			                         dateTip.tipConts[_num]].join(''));      
			    	}
				}
			});
			
		},
};
$(function() {
	dateTip.init();
	Homepage.initPtNoteList('ptNotePanel');
});
function openUpdateBase() {
	if(loginUser.dataScope == 0){
		return;
	}
	Comm.dialog({
		//url: '${webroot}/user/f_view/toUpdatePwd.shtml',
		url: '${webroot}/acAccount/f_view/toedit.shtml?sourceType=my&id=' + user.userId,
		title:'修改资料',
		width:550
	});
}

function openUpdatePasswd() {
	Comm.dialog({
		url: '${webroot}/acAccount/f_view/toUpdPasswd.shtml?sourceType=my',
		title:'修改密码',
		width:450
	});
}

//修改用户信息提示内容
function mouseOver() {
	document.getElementById('prompt').style.display ="block";
}
function mouseOut() {
	document.getElementById('prompt').style.display ="none";
}
</script>