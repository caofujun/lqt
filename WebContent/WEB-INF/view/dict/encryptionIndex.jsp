<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<title>加密、解密工具</title>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css_org/comm.css" />
<link rel="stylesheet" type="text/css" href="${webroot}/resources/easyui-1.4.3/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/DatePicker/skin/WdatePicker.css"/>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/customer/jquery.mCustomScrollbar.css" />
<link href="${webroot}/resources/css_org/layout.css" rel="stylesheet"  type="text/css" />
<link  href="${webroot}/resources/css_org/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${webroot}/resources/js/jquery.min.js"></script>
</head>

<body style="height:100%; margin:0 auto;">
	<div id="main" style="margin-left:30px;padding-top:20px">
		<div id="tb" class="m_search" >
		<input type="text"  id="employeeId" name="employeeId"  style="width:200px"  class="auto-tip input_tip" data-tip="请输入需要加密或者解密的内容" />
		
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="dataWarning.jiami(1)" ><i class="icon iconfont">&#xe688;</i><span>加密</span></a>
			</div>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="dataWarning.jiami(2)" ><i class="icon iconfont">&#xe688;</i><span>解密</span></a>
			</div>
		</div>
		<div class="footer_btn">
			<textarea style="width:360px;height:100px;" id="employeeIdResult"></textarea>
<!-- 			<input class="easyui-textbox" data-options="multiline:true" id="employeeIdResult" style="width:372px;height:100px;" /> -->
		</div>
	</div>	
	

	<script type="text/javascript">
	var dataWarning = {
			jiami : function(type){
				$.ajax({     
		            url: webroot + '/dataInit/f_json/findEncryptionIndex.shtml',
		            type: 'post',
		            data: { str: $('#employeeId').val(), type: type },
		            dataType: 'json',
		            success : function(json) {
		            	if(json.result=="success"){
		            		$('#employeeIdResult').html(json.data);
		            	}else{
		            		$('#employeeIdResult').html(json.msg);
		            	}
					}
				});
			}
	};
	</script>
</body>

</html>