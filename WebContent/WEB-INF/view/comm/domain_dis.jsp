<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<script type="text/javascript">
	window.onload = function() {
		parent.parent.parent.menuInfo.clickMenu(decodeURI(decodeURI('${param.title}')),'${param.url}', true,'','${urlPrefix}');
	};
</script>
</head>
<body>
</body>
</html>