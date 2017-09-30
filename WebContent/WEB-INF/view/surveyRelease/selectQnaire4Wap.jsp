<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,user-scalable=no;"/>
   	<title>选择问卷</title>
    <%@ include file="/WEB-INF/view/core/include.jsp"%>
    <script type="text/javascript" src="${webroot}/resources/zclip/jquery.zclip.js${version}"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery-migrate-1.1.0.js"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery.jqprint-0.3.js"></script>
</head>
<body  class="qs_wap_body" >
	<div class="user_login">
		<table>			
			<tr><td style="padding:30px 10px;">
				<select style="width:95%; height:35px;" id="qsQuestionnaireList">
				<c:forEach var="list" items="${qsQuestionnaireList}" >
					<option value="${list.qid }">${list.title}</option>
				</c:forEach>
				</select>
			</td></tr>
			<tr><td><a href="#" class="grade_grey" onclick='confrim()'>确&nbsp;&nbsp;定</a></td></tr>
		</table>		   
	</div>
	
	<script type="text/javascript">
	function confrim(){
		var qid=$("#qsQuestionnaireList").val();
		location = '${webroot}/surveyRelease/f_view/selectPatient4Wap.shtml?qid='+qid;	
	}
	</script>
</body>
</html>
