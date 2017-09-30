<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormSop" method="post">
	<div class="easyui-panel" data-options="border:false,footer:'#button_div'" style="width: 100%;">
		<div style="margin:10px;height: 287px;">
			<input type="hidden" id="contentStr" value="${msgTemplate.contentStr}"/>
			${msgTemplate.contentStr}
		</div>
	</div>
	<div id="button_div" class="footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="showMsgTemplate()" class="no_ico"><span>确认</span></a>
			</div>			
		</div>	
	</div>
</form>
<script>
	function showMsgTemplate(){
		var parentObject = parent.parent.Comm.getObjectCache();
		parentObject.showContent($('#contentStr').val());
		parent.close();
	}
	
</script>