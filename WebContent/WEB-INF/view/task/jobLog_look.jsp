<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<style type="text/css">
	.tabs-header {
		border-width: 0 0 0 1px;
	}
	.tabs-panels {
		border-right-width: 0;
	}
</style>
</head>
<body style="overflow: hidden;">
<table class="table mb60">
	<tbody>
	<tr>
		<td class="t_title" style="width: 80px;">
			执行状态：
		</td>
		<td class="t_cont">
			${taskJobLog.statusname}
		</td>
	</tr>
	<tr>
		<td class="t_title" style="width: 80px;">
			调度时间：
		</td>
		<td class="t_cont">
			<fmt:formatDate value="${taskJobLog.addtime}" pattern="yyyy-MM-dd HH:mm:ss" />	
		</td>
	</tr>
	<tr>
		<td class="t_title" style="width: 80px;">
			调用链接：
		</td>
		<td class="t_cont">
			${taskJobLog.link}
		</td>
	</tr>
	<tr>
		<td class="t_title" style="width: 80px;">
			响应内容：
		</td>
		<td class="t_cont">
			${taskJobLog.result}
		</td>
	</tr>		
	</tbody>
</table>
</body>
</html>	