function showContent(url, showDiv, data, method) {
	var defaultMethod = "get";
	if(data) defaultMethod = "post";
	if(method) defaultMethod = method;
    var showObj = $("#" + showDiv);
    $.ajax({
        url: url,
        type: defaultMethod,
        data: data,
        dataType: 'html',
        cache:false,
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            showObj.html("<br/>加载失败！");
        },
        success: function(result) {
            showObj.html(result);
        },
        beforeSend:function() {
            showObj.html("<br/>载入中...");
        }
    });
}

function iconUpOrDown(opt){
	var obj = $(opt).find("i");
	var css = $(obj).attr("class");
	$(obj).removeClass(css);
	if(css=="icon-slideup"){ 
		$(obj).addClass("icon-slidedown");
	}else{
		$(obj).addClass("icon-slideup");
	}
}


function selectThisPage(pageIndex){
	var allPage = $(".pageNumber");
		allPage.removeClass("cur");
		allPage.eq(pageIndex-1).addClass("cur");
	if(allPage.length>9){
		allPage.hide();
		var move = pageIndex-5;
		if(move<0){
			move=0;
		}else if(move+9>allPage.length){
			move=allPage.length-9;
		}
		for ( var int = 0; int < 9; int++) {
			allPage.eq(move).show();
			move++;
		}
	}
}
$(function(){
	$(".pageNumber").click(function(){
		if($(this).hasClass("cur")==true){
			return false;
		}
		var page = $(this).text();
		selectThisPage(page);
		toAjaxLoad(page,uids,size);
	});

	$("#fPage").click(function(){
		var page = $(".pageNumber:first").text();
		selectThisPage(page);
		toAjaxLoad(page,uids,size);
	});
	$("#backPage").click(function(){
		var page = $(".pageNumber.cur").text();
		page=Number(page)-1;
		if(page<=0){
			page = 1;
			return false;
		}
		selectThisPage(page);
		toAjaxLoad(page,uids,size);
	});
	$("#nextPage").click(function(){
		var page = $(".pageNumber.cur").text();
		page=Number(page)+1;
		if(page>$(".pageNumber").length){
			page=$(".pageNumber").length;
			return false;
		}
		selectThisPage(page);
		toAjaxLoad(page,uids,size);
	});
	$("#lastPage").click(function(){
		var page = $(".pageNumber:last").text();
		selectThisPage(page);
		toAjaxLoad(page,uids,size);
	});
});

function alertInfo(title,info,icon,fun){
	art.dialog({
		title: title,
		content: info,
		icon: icon,
		follow: document.getElementById('btn2'),
		ok:fun,fixed:true,resize:true
	});
}

