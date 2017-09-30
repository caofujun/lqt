<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div id="leftmenus">
	<!-- <div class="leftmenus_box"><h3><a href="#">网页首页</a></h3>
	</div>
	<div class="leftmenus_box"><h3>随访操作<span></span></h3>
		<ul class="m-treeview">
			<li><dl><dt><a href="#">住院随访</a></dt></dl></li>
			<li><dl><dt><a href="#">心内科专科随访查询</a></dt></dl></li>				
		</ul>
	</div>
	<div class="leftmenus_box"><h3>随访查询<span></span></h3>
		<ul class="m-treeview">
			<li><dl><dt><a href="#">随访记录查询</a></dt></dl></li>
			<li><dl><dt><a href="#">电话录音查询</a></dt></dl></li>				
		</ul>
	</div>
	<div class="leftmenus_box"><h3>随访统计分析<span></span></h3>
		<ul class="m-treeview">
			<li><dl><dt><a href="#">随访工作报告</a></dt></dl></li>
			<li><dl><dt><a href="#">随访工作统计</a></dt></dl></li>
			<li><dl><dt><a href="#">随访失败原因分析</a></dt></dl></li>
		</ul>
	</div> -->			
</div>
<script type="text/javascript">

/***菜单导航***/
function mainMenu(isInitMenu) {
	//二级菜单的隐藏与展开
	/* $(".m-treeview li dt").click(function(){
		if($(this).hasClass("m-expanded")){
			$(".m-treeview li dl dt").attr("class","m-collapsed").siblings("dd").hide();
			$(this).attr("class","m-collapsed");
			$(this).siblings("dd").hide();
		}else{
			$(".m-treeview li dl dt").attr("class","m-collapsed").siblings("dd").hide();
			$(this).attr("class","m-expanded");
			//$(this).parent().find('dd:first').addClass('fmenu');
			$(this).parent().find('dd:last').addClass('lmenu');
			$(this).siblings("dd").show();
		}
		return false;
	});
	*/
	//主菜单的隐藏与展开
	$("#leftmenus h3").click(function(){
		$("#leftmenus h3").each(function(i, obj) {
			$(obj).removeClass();
		});
		$(this).parent(".leftmenus_box").siblings(".leftmenus_box").find("ul").slideUp("normal");
		$(this).parent(".leftmenus_box").siblings(".leftmenus_box").find("h3 span").css({"background":"url(${webroot}/resources/images_org/admin/icon_down.gif) no-repeat left center"});
		if(!$(this).next("ul").is(":visible")) {
			$(this).next("ul").slideDown("slow");
		    $(this).find("span").css({"background":"url(${webroot}/resources/images_org/admin/icon_up.gif) no-repeat left center"});
			$(this).addClass('cur');
		    return false;
        } else {
		   	$(this).next("ul").slideUp("normal");
		  	$(this).find("span").css({"background":"url(${webroot}/resources/images_org/admin/icon_down.gif) no-repeat left center"});
		   	return false;
	    }
	});
	//增加选中 dt 元素的样式
	$("#leftmenus ul li dl dt").click(function(){
		$("#leftmenus ul li dl dt").each(function(i, obj) {
			$(this).removeClass();
		});
		$(this).addClass('cur');
	});
	//第2个主菜单默认展开
	if(isInitMenu!=false) $("#leftmenus h3").eq(0).click();

}
</script>