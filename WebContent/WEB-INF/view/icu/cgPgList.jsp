<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>插管事件</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>

<body>
<c:choose>  
	<c:when test="${showName == '1'}">  
		<div class="CalendarEvents" style="pxpadding: 0px;padding-top: 0px;">
	</c:when>  
	<c:otherwise> 
	   	<div class="CalendarEvents">
	</c:otherwise> 
</c:choose> 
	<c:if test="${showName != '1'}">
		<div class="CalendarEvents_header"><a href="javascript:patientInfo('${cryxxb.zyid}')">${cryxxb.zyid}</a><span>${cryxxb.patientName}</span>插管事件日历</div>
	</c:if>
	<c:choose>  
	   <c:when test="${showName == '1'}">  
			<div class="CalendarEvents_cont" style="margin-top: 0px;">
	   </c:when>  
	   <c:otherwise> 
	   		<div class="CalendarEvents_cont">
	   </c:otherwise>  
	</c:choose>  
		<div class="CalendarEvents_year" style="line-height: 32px;">
			<span>${dateMonth}</span>
			<div class="Operat_tool"><a class="" href="javascript:backMonth()" title="上个月">&lt;</a><a class="" href="javascript:goToday()">今日</a><a class="" href="javascript:nextMonth()" title="下个月">&gt;</a></div>
		</div>
		<ul class="CalendarEvents_cont_title">
			<li>日</li>
			<li>一</li>
			<li>二</li>
			<li>三</li>
			<li>四</li>
			<li>五</li>
			<li>六</li>
			<div class="clear"></div>
		</ul>
		<ul class="CalendarEvents_cont_main">
			<c:forEach items="${dayList}" var="cgPg">
			<li <c:if test="${cgPg.toDay==1}">class="today"</c:if>>
				<div class="CalendarEvents_data"><c:if test="${cgPg.day!=0}"><span>${cgPg.day}日</span></c:if></div>
				<div class="CalendarEvents_data_event">
				<c:if test="${cgPg.mnd>1}"><a href="javascript:openPg('${cryxxb.zyid}','2')">尿</a></c:if><c:if test="${cgPg.mnd==1}"><a href="javascript:openPg('${cryxxb.zyid}','2')" class="red">尿</a></c:if>
				<c:if test="${cgPg.zxjm>1}"><a href="javascript:openPg('${cryxxb.zyid}','1')">中</a></c:if><c:if test="${cgPg.zxjm==1}"><a href="javascript:openPg('${cryxxb.zyid}','1')" class="red">中</a></c:if>
				<c:if test="${cgPg.hxj>1}"><a href="javascript:openPg('${cryxxb.zyid}','3')">呼</a></c:if><c:if test="${cgPg.hxj==1}"><a href="javascript:openPg('${cryxxb.zyid}','3')" class="red">呼</a></c:if>
				<c:if test="${cgPg.day!=0}">
				<a href="" class="add">新建评估表</a>
					<div class="drpMenuli" style="display:none">
						<div class="drpMenuli_list"><a href="javascript:void(0);" onclick="openPg('${cryxxb.zyid}','2')">泌尿道插管</a></div>
						<div class="drpMenuli_list"><a href="javascript:void(0);" onclick="openPg('${cryxxb.zyid}','1')">中心静脉插管</a></div>
						<div class="drpMenuli_list"><a href="javascript:void(0);" onclick="openPg('${cryxxb.zyid}','3')">呼吸机插管</a></div>						
						<div class="clear"></div>
					</div>
				</c:if>
				</div>
			</li>
			</c:forEach>
			
			<div class="clear"></div>
		</ul>
		<div class="CalendarEvents_note">
			<span class="CalendarEvents_note_title">图释：</span>
			<div class="CalendarEvents_note_list"><span>尿</span>表示患者当日评估泌尿道插管</div>
			<div class="CalendarEvents_note_list"><span>中</span>表示患者当日评估中心静脉插管</div>
			<div class="CalendarEvents_note_list"><span>呼</span>表示患者当日评估呼吸机插管</div>
			<div class="CalendarEvents_note_list"><span class="red">尿</span>表示患者当日泌尿道插管未完成评估，需完成</div>
<!-- 			<div class="CalendarEvents_note_list"><span class="unusual">尿<span class="abnormal"></span></span>表示泌尿道插管评估结果有异常</div> -->
		</div>
	</div>
</div>
	
<script>
var dateMonth = new Date('${nowDate}');
//患者档案
function patientInfo(zyid) {
	parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
}
function openPg(zyid,catId){
	 Comm.dialog({
     	 url:"${webroot}/qsSurveyRecord/f_view/toPgEdit.shtml?zyid="+zyid+"&catId="+catId,
     	type: 'iframe',
        title: '评估表',
		width: 900,
		height: 450
     });
}
function nextMonth () {
	var nextMonth = dateMonth;
	nextMonth.setMonth(dateMonth.getMonth()+2);
	location.href = "${webroot}/gm004Jcmx/f_view/cgPgList.shtml?zyid=${param.zyid}&dateMonth=" + nextMonth.getFullYear()+"/"+nextMonth.getMonth()+""+"&showName=${showName}";
}
function goToday () {
	location.href = "${webroot}/gm004Jcmx/f_view/cgPgList.shtml?zyid=${param.zyid}&dateMonth=${today}"
}
function backMonth () {
	var backMonth = dateMonth;
	backMonth.setMonth(dateMonth.getMonth());
	location.href = "${webroot}/gm004Jcmx/f_view/cgPgList.shtml?zyid=${param.zyid}&dateMonth=" + backMonth.getFullYear()+"/"+backMonth.getMonth()+""+"&showName=${showName}";
}
function drpMenu() {	//三级菜单的隐藏与展开	
	$(".CalendarEvents_data_event .add").click(function(){
		$(".drpMenuli").slideUp("slow");
		if($(this).next(".drpMenuli").css('display')=='none'){
			$(this).next(".drpMenuli").slideDown("normal");		 		
		    return false;
        }else{
		   $(this).next(".drpMenuli").slideUp("slow");
		   return false;
	    }
	});
	$(".drpMenuli").mouseleave(function(){
		$(".drpMenuli").slideUp("slow");
	});
}
$(function() {
	drpMenu();
});
</script>

</body>
</html>
