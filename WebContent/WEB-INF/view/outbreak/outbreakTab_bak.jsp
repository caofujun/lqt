<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<jsp:include page="/WEB-INF/view/core/report.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>暴发预警分析</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<style type="text/css">
.tabs-panels {
	border-width: 0;
}
</style>
</head>
<body>
    <div class="easyui-tabs" data-options="fit:true,plain:true,border:false">
        <div title="趋势分析" style="overflow:hidden;" data-options="href:'${webroot}/st003Cryxxb/f_view/viewframe.shtml?src=/by007Show/f_view/toOutbreakTrends.shtml'">
        	
        </div>
        <div title="对比分析" style="overflow:hidden" data-options="href:'${webroot}/st003Cryxxb/f_view/viewframe.shtml?src=/by007Show/f_view/toOutbreakContrast.shtml'">
        	
        </div>
    </div>
<script type="text/javascript">
$(document).ready(function () {
	
});
</script>
</body>
</html>