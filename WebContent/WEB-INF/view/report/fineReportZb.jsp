<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<title>感染统计</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
<div>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/YYGRFBL&__bypagesize__=false">医院感染发病（例次）率</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/YYGRXHLSS&__bypagesize__=false">医院感染现患（例次）率-实时</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/YYGRXHLDC&__bypagesize__=false">医院感染现患（例次）率-调查</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/CBLB&__bypagesize__=false">医院感染病例漏报率</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/TSLYJJCL&__bypagesize__=false">多重耐药菌检出率</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/TSLYJGRFBL&__bypagesize__=false">多重耐药菌感染发生率</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/KJYWSYL&__bypagesize__=false">住院患者抗菌药物使用率</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/KJYWZLQSJL&__bypagesize__=false">抗菌药物治疗前病原学送检率</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/ILQKSSKJYWBWGR&__bypagesize__=false">Ⅰ类切口手术部位感染率</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/ILQKSSKJYWYFSY&__bypagesize__=false">Ⅰ类切口手术抗菌药物预防使用率</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/MLDCGGR&__bypagesize__=false">泌尿道插管相关泌尿系统感染发病率</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/ZXJMCG&__bypagesize__=false">血管内导管相关血流感染发病率</a>
<a href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/HXJFY&__bypagesize__=false">呼吸机相关肺炎发病率</a>
</div>
</body>
</html>