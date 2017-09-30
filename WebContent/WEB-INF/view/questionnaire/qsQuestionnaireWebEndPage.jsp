<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   <title>问卷调查</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
        <script type="text/javascript" src="${webroot}/resources/js/functions/wjdc.js${version}"></script>
	</head>
<body  class="qs_web_body">
<div class="jqContent">
	<div class="surveyhead" style="border: 0px;">
		
    
		<div class="jqContent_Submit"> 								
			<div class="jqContent_Submit_text">您的问卷已提交，感谢您的参与！</div>
			<img src="${webroot}/resources/images_org/OK.jpg"/>
		</div>		  
	</div>
	
</div>

<script type="text/javascript">
	/*根据屏幕高度自动调整页面的显示高度*/
	var winWidth = 0; 
	var winHeight = 0; 
	var Height_survey=0;
	
	/*根据屏幕宽度自动调整问卷的显示样式*/
	var width_all=0; 
	var width_body = 0;
	
	function findDimensions() // 函数：获取尺寸 
		{ 
			// 获取窗口宽度 
			if (window.innerWidth) 
				winWidth = window.innerWidth;
			else if ((document.body) && (document.body.clientWidth)) 
				winWidth = document.body.clientWidth; 
			// 获取窗口高度 
			if (window.innerHeight) 
				winHeight = window.innerHeight;
			else if ((document.body) && (document.body.clientHeight)) 
				winHeight = document.body.clientHeight; 
			// 通过深入 Document 内部对 body 进行检测，获取窗口大小 
			if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) 
				{ 
					winHeight = document.documentElement.clientHeight; 
					winWidth = document.documentElement.clientWidth; 
				} 
			// 给body赋高度值
			Height_survey = winHeight - 140 ;
			$(".qs_web_body").css({"height": Height_survey+"px"});
			//alert(winHeight);
			
			width_all = document.body.offsetWidth;
			width_body = width_all - 140;
			//alert(width_all + "," + width_body);
		
			if(width_all < 930)
				{
					$(".qs_web_body").css({"padding": "0"});
					$(".qs_web_body").css({"background": "#fff"});
					$(".jqContent").css({"width": width_all+"px"});
					$(".surveyhead").css({"width": width_body+"px"});
				}
				else{
					$(".qs_web_body").css({"padding": "70px 0"});
					$(".qs_web_body").css({"background": "#a8dcf9 url('${webroot}/resources/images_org/Commonbg.jpg') no-repeat fixed top center"});
					$(".jqContent").css({"width": "920px"});
					$(".surveyhead").css({"width": "780px"});
				}		
		}
	findDimensions(); 
	// 调用函数，获取数值 
	window.onresize=findDimensions;		
		
</script>

</body>
</html>