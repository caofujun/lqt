/**
 * 
 * 水平时间轴 
 * @Date 2014-12-3
 * @version v1.0.0
 */
(function($,undefined){
	
	var defaultOpt = {
		timeId : 'timeX',
		number :0
	};
	$.timeX = function (options) { 
		//拷贝属性
		var opts = $.extend({},defaultOpt,options);
		
		//初始化属性
		var timeId = opts.timeId;		//绑定div 需要进行时间轴控制 必须
		var	dist = -120;		// 一次移动120px
		var	interval = 200;		// 动画完成时间
		var number = opts.number; 		//记录数 			必须
		var	width = -1; 		// 最小的宽度		
		var containerWidth = $("#container").innerWidth()-240;   // div内部的宽度、高度 
		
		
		 $(".timeX-option a").each(function(){
		      $(this).click(clickAction);  
		 });
		 
		 function clickAction(){
		        var curButton = $(this);
		        //获取前后移动
		        var type = curButton.attr("id");
		        //获取时间运动轴
		        var img = $("#" + timeId);
		        // 判断是否触壁
		        var moveDist = getMoveDist(img, type);
		        // 移动
		        move(img, type, moveDist);
		    }
			     
		    function getMoveDist(img, type){
		        var moveDist = dist;
		        var curDist = 0;
		        if (type == "Left") {
		            curDist = getMargin(img, type);
		            if (curDist - dist > 0) {
		                moveDist = curDist;
		            }			
		        }
		        else if(type == "LeftFirst"){
					curDist = getMargin(img, "Left");
					moveDist = curDist;            
				}
		        else if (type == "Right") {
		            curDist = getMargin(img, "Left")+ dist;
		            if (curDist + dist < getWidth( number )+containerWidth ) {
		                moveDist = getWidth( number ) + containerWidth - curDist;
		            }
					else{
						moveDist=dist;
					}			
		        }
		        else if (type == "RightLast") {
		            curDist = getMargin(img, "Left");
		            if (curDist + dist < getWidth( number )+containerWidth ) {
		                moveDist = 0;
		            }
					else{
						moveDist= getWidth( number )+containerWidth - curDist - dist;
					}			
		        }			
		       return moveDist;
		    }
		     
		    function move(img, type, dist) {
		        $('#'+type).unbind('click', clickAction);
		        if (type == "Left") {
		            img.animate({marginLeft:'-=' + dist + 'px'}, interval, function(){
		                $('#'+type).bind('click', clickAction);
		            });
		        }
		        else if (type == "LeftFirst") {
		            img.animate({marginLeft:'-=' + dist + 'px'}, interval, function(){
		                $('#'+type).bind('click', clickAction);
		            });
		        }
		        else if (type == "Right") {
		            img.animate({marginLeft:'+=' + dist + 'px'}, interval, function(){
		                $('#'+type).bind('click', clickAction);
		            });
		        }
				else if (type == "RightLast") {
		            img.animate({marginLeft:'+=' + dist + 'px'}, interval, function(){
		                $('#'+type).bind('click', clickAction);
		            });
		        }
		    }
			
			function getWidth( number ){
				if(width > number * dist){
					width = number * dist;
				}
				return width;
			}
		     
		    function getMargin(obj, type) {
		        var value = parseInt(obj.css("margin" + type));
		        if (!value) {
		            value = 0;
		        }
		        return value;
		    }
	};
})(jQuery);