<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormSop" method="post">
	<div class="easyui-panel" data-options="border:false,footer:'#button_div'" style="width: 100%;height: 375px;">
		<div style="margin: 10px;height: 287px;">
			<input type="hidden" id="showFileData" value='${sop.showFileData}'/>
			${sop.showFileData}
		</div>
	</div>
	<div id="button_div" class="footer">		
		<div class="n_btn_blue">
			<a href="javascript:;" id="changeFormSubmitBtn" onclick="showSop()"  class="no_ico"><span>确认</span></a>
		</div>
	</div>
</form>
<script>
	function showSop(){
		var parentObject = parent.parent.Comm.getObjectCache();
		parentObject.showContent($('#showFileData').val());
		parent.close();
	}
	
</script>