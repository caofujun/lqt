<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<form id="bl002SjdjNote" method="post">
<div id="infect_monitor_tab" class="easyui-tabs" style="width:850px;height:480px" data-options="fit:true,plain:true">
	<c:choose>
	<c:when test="${lc eq 1}">
	<div title="应急处理步骤" style="padding:10px" align="center">
		<img src="/download/zyblImages/bl_yjclbz.png?tz=<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss"/>" />
	</div>
	</c:when>
	<c:when test="${lc eq 2}">
	<div title="职业暴露处理流程" style="padding:10px" align="center">
		<img src="/download/zyblImages/bl_czlc.png?tz=<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss"/>" />
	</div>
	</c:when>
	<c:otherwise>
	<div title="应急处理步骤" style="padding:10px" align="center">
		<img src="/download/zyblImages/bl_yjclbz.png?tz=<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss"/>" />
	</div>
	<div title="职业暴露处理流程" style="padding:10px" align="center">
		<img src="/download/zyblImages/bl_czlc.png?tz=<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss"/>" />
	</div>
	</c:otherwise>
	</c:choose>

	
	<nis:auth menuNo="D030101">
		<a class="ico_setting" title="进行注意事项配置" onclick="uploadPage();" style="position: fixed;right: 20px;top: 6px;z-index: 1;cursor: pointer;"><i class="icon nisfont">&#xe67d;</i></a>
	</nis:auth>
</div>
<div class="footer dialog_footer"><input type="button" class="btn_return3" id="changeFormSubmitBtn"
				onclick="edit()" value="我已经知道了(5秒)"></div>
</form>
<script>
	function edit() {
		var parentObject = parent.Comm.getObjectCache();
		parentObject.edit('','职业暴露登记');
		parent.Comm.dialogClose('${param.dialogId}');
	}
	
	var n = 0;
	var intervalID;
	var x = document.getElementById("changeFormSubmitBtn");
	var classVal = document.getElementById("changeFormSubmitBtn").getAttribute("class");	
	function setEnable(){
	    n++;
	    if(n>5){
	    	x.disabled = false;
	    	x.value = "我已经知道了";
	    	classVal = classVal.replace("btn_return3","btn_save3");
	    	document.getElementById("changeFormSubmitBtn").setAttribute("class",classVal );
	  		// 有时稍后
	 		clearInterval(intervalID);
	    }else{
	    	x.disabled = true;
	    	x.value = "我已经知道了("+(5-n)+ "秒)";
	    }
	}
	intervalID = window.setInterval("setEnable()",1000);
	
	function uploadPage(){
		Comm.dialogGlobal({
	    	url:"${webroot}/bl002Sjdj/f_view/uploadImage.shtml",
	        title: "注意事项配置",
	        width:850,
	        height:555,
	        type:"iframe"
	    });
		parent.Comm.dialogClose('${param.dialogId}');
	}
</script>