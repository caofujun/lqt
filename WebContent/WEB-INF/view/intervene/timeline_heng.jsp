<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script>
/*以下JS代码可以直接使用项目中文件：csm-comm-web-resources\src\main\webapp\resources\js\timeX.js*/
$(function(){
    var imgId = "timeX";
    var dist = -120;// 一次移动140px
    var interval = 200;// 动画完成时间
    var number= ${fn:length(patientZbList)}; //记录数
    var width = -1; // 最小的宽度    
    var containerWidth = $("#container").innerWidth() - 140;   // div内部的宽度、高度    
    $("#option a").each(function(){
        $(this).click(clickAction);  
    });
    function clickAction(){
        var curButton = $(this);
        var type = curButton.attr("id");
         
        var img = $("#" + imgId);
     
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
        var sef = 0;     
        var value = parseInt(obj.css("margin" + type));
        if (!value) {
            value = 0;
        }         
        return value;
    }
});

var index;

$(document).ready(function () {	
	//第一个菜单默认展开
	 document.getElementById("_nav_item_0_0").click();
});
var pWarning={
	query:function(){
		//$('#tagNavIframe').attr('src', $('#tagNavIframe').attr('src'));
		//$('#tagNavIframe').location.reload();
	}
};
function importPdca(){
	  Comm.dialogGlobal({
      	url:"${webroot}/zlPdcaPlans/f_view/toedit.shtml?",
          title: "引入PDCA",
          width:780,
          height:500,
          type:"iframe",
          parent:this
      });
}

function SendMessage(){
	Comm.dialogGlobal({
    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid=${zyxx.zyid}",
        title: "干预会话",
        width:870,
        height:555,
        type:"iframe"
    });
}
//导航条点击事件
function nOnClickEvent(index,index2,pzId,pzStatus){
	//清空选中样式
	$('.dropMenu').find("span").removeClass('drop_hover');
	var url = "";
	if(pzStatus==0||pzStatus==2){//风险因素
		url="${webroot}/fxPatientZb/f_view/edit.shtml?pzId="+pzId;
	}else if(pzStatus==3){//已通过感染部位
		url="${webroot}/gr002YsgrMx/f_view/toWarningResults.shtml?gr2Relid="+pzId+"&zyid=${zyxx.zyid}&incTpye=1";
	}else if(pzStatus==4){//预警
		url="${webroot}/gr002YsgrMx/f_view/toWarningResults.shtml?regId="+pzId+"&zyid=${zyxx.zyid}&incTpye=1";
	}else if(pzStatus==5){//评估表
		url="${webroot}/fxPatientZb/f_view/getRid.shtml?rid="+pzId;
	}
	//当前点击被选中
	$('#_nav_item_'+index+'_'+index2).addClass('drop_hover');
	$('#tagNavIframe').attr("src",url);
}

function iFrameHeight() {
    var ifm= document.getElementById("tagNavIframe");
    var subWeb = document.frames ? document.frames["tagNavIframe"].document :
	ifm.contentDocument;
    if(ifm != null && subWeb != null) {
    	ifm.height = subWeb.body.scrollHeight;
    }
}

//异步提示
function lookzy(o,pzId,type){
	if(type=='pdca'){
		$.post('${webroot}/fxPatientZb/f_view/getPdcaBypzId.shtml?pzId='+pzId,function (data){
		     if (data.result == 'success' && data.msg) {
		    	index = layer.tips(data.msg, o, {
		    	        maxWidth:300,
		    	        time: 0,
		    	        tips: [1, '#3595CC'],
		    	        closeBtn:0			    	     
		    	 });
			 }
		 },'json');
	}else if(type=='qs'){
		$.post('${webroot}/fxPatientZb/f_view/getQsBypzId.shtml?pzId='+pzId,function (data){
		     if (data.result == 'success' && data.msg) {
		    	index = layer.tips(data.msg, o, {
		    	        maxWidth:300,
		    	        time: 0,
		    	        tips: [1, '#3595CC'],
		    	        closeBtn:0
		    	 });
			 }
		 },'json');
	}
}

function closeTip(){
	layer.close(index); 
}

</script>
<div class="fxzh_box_timeline">
<div class="timelineContent" id="option">				
   <a id="LeftFirst"  class="first browse" title="First"></a>
<a id="Left"  class="prev browse" title="Prev"></a>
<a id="RightLast" class="last browse" title="Last"></a>
<a id="Right" class="next browse" title="Next"></a>
<div class="timeline" id="container">
	<div class="scrollable">
		<div class="items"  id="timeX">
			<c:forEach var="patientZb"  items="${patientZbList}" varStatus="status">                         
			<div class="tl_li">
				<div class="timeline_no"></div>
				<div class="timeline_date"><a href="#">${patientZb.charDate}</a></div>
				<div class="timeline_title">
					<c:forEach var="pz" items="${patientZb.pzList}" varStatus="st">
						<div class="dropMenu">
							<span id="_nav_item_${status.index}_${st.index}" onclick="nOnClickEvent('${status.index}',${st.index},'${pz.pzId}','${pz.pzStatus}')">
							<c:if test="${pz.pzStatus==null}"><b class="round red">●</b></c:if>
							<c:if test="${pz.pzStatus==0}"><b class="round red">●</b></c:if>
							<c:if test="${pz.pzStatus==1}"><b class="round gray">●</b></c:if>
							<c:if test="${pz.pzStatus==2}"><b class="round green">●</b></c:if>
							<c:if test="${pz.pzStatus==3}"><b class="round orange">●</b></c:if>
							<c:if test="${pz.pzStatus==4}"><b class="round orange">●</b></c:if>
							<c:if test="${pz.pzStatus==5}"><b class="round orange">●</b></c:if>
							<a  href="#" title="${pz.zbName}">${pz.zbName}</a><c:if test="${pz.pdcaStatus>0}"><c onmouseover="lookzy(this,'${pz.pzId}','pdca')" onmouseout="closeTip()">P</c></c:if> <c:if test="${pz.qsStatus>0}"><c onmouseover="lookzy(this,'${pz.pzId}','qs')" onmouseout="closeTip()" style="background-color:${pz.fxColor}">D</c></c:if></span>
						</div>				
					</c:forEach>
				</div>
			</div>
			</c:forEach>  
			<div class="clear"></div>
		</div><!--items -->
	</div><!--scrollable -->   
</div><!--timeline -->
</div>
</div>
<iframe id="tagNavIframe" src="" scrolling="no" width="100%" height="280" marginheight="0" frameborder="0"></iframe> 