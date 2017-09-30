<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<%-- 接口调用报卡选择是否立即上报界面 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>上报询问页</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<style>
	.opts{
		font-size: 20px;
		padding: 10px;
		color: white;
		border-radius:5px; 
	}
	.textBubble{
		height:90px;
	    line-height:80px; 
	    margin-top:30px;
	    padding-left:2em;
	    background-color:#dddddd;
	    position:relative; 
	}
	.bubbleTrangle{
		height:30px;
	    line-height:0; 
	    font-size:60px; 
	    color:#dddddd; 
	    position:absolute; 
	    left:105px; 
	}
	.msgFrame{
		border: 5px solid #dddddd;
		text-align: center;
		vertical-align: middle;
		margin: 50px auto;
		width: 500px;
		height:300px;
		font-size: 18px;
		color: #6d6d6d;
	}
</style>
</head>
<body> 
<c:if test="${!empty errMsg}">
	<div class="errTip">${errMsg}</div>
</c:if>
<c:if test="${!empty tipMsg}">
	<div class="errTip">${tipMsg}</div>
</c:if>

<div class="background">
	<div id="bigDialog">
		<div class="optsPlace" style="margin-top:60px;text-align: center;">
			<a class="opts" onclick="location.href='${webroot}${url}'" onmouseover="showNote('now');" style="margin-right:70px;background-color: #78c578;cursor: pointer;">&nbsp;立即上报&nbsp;</a>
			<a class="opts" onclick="reportLater();" onmouseover="showNote('later');" style="margin-left:70px;background-color: #78c578;cursor: pointer;">&nbsp;稍后再报&nbsp;</a>
		</div>
		<div class="notePlace" style="margin-top: 45px;font-size: 20px;color: #6d6d6d;" >
			<div class="textBubble">
			    <span class="bubbleTrangle">◆</span>
			    <div class="tipPlace">点击跳转到报卡页面进行上报</div>
			</div>
		</div>
	</div>
</div>
<div id="msgTip" class="msgFrame" style="display: none;">
	<div style="margin-top: 50px;"><span id="mainInfo"></span><br/><br/><br/><span id="tip">在患者列表可以看到相关提示并进行上报！</span></div>
</div>
<script>
	$("#bigDialog").dialog({
		title:"请选择下一步的操作",
		width:500,
		height:270
	});
	function showNote(type){
		if(type=="now"){
			$(".bubbleTrangle").css("left","105px");
			$(".tipPlace").html("点击跳转到报卡页面进行上报");
		}else{
			$(".bubbleTrangle").css("left","360px");
			$(".tipPlace").html("将病人记录插入待报列表，方便以后上报");
		}
		$(".notePlace").show();
	}
	function hideNote(type){
		$(".notePlace").hide();
	}
	function reportLater(){
		$.ajax({
			url:"${webroot}/cdc/f_json/saveTodoList.shtml",
			data:{
				mzzyid : "${mzzyid}",
				patientType : "${patientType}"
			},
			type:"POST",
			success:function(data){
				data = eval("("+data+")");
				if(data.result=="success"){
					$("#bigDialog").dialog("close");
					$("#mainInfo").html("添加待报列表成功！");
					$("#msgTip").show();
				}else{
					$("#bigDialog").dialog("close");
					$("#mainInfo").html("添加待报列表失败！");
					$("#tip").html("错误信息："+data.msg);
					$("#msgTip").show();
				}
			},
			error:function(){
				alert("抱歉，添加待报出现错误！");
			}
		});
	}
</script>

</body>
</html>