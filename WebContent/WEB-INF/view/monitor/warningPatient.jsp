<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>预警信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<c:if test="${!empty param.type}">
	<div id="tb_warningPatient" class="h_title">
		<c:if test="${param.type eq 'wait'}">
			<c:set var="waitNum" value="0"></c:set>
			<c:forEach items="${gr002YsgrMxList}" var="gr002YsgrMx" varStatus="status">
				<c:if test="${gr002YsgrMx.state eq 1}"><c:set var="waitNum" value="${waitNum+1}"></c:set></c:if>
			</c:forEach>
			<span><font color="red" style="font-weight: 800; vertical-align: bottom; margin-right:3px; font-size: 18px;"><c:out value="${waitNum}" /></font>例未处理</span>&nbsp;&nbsp;&nbsp;
		</c:if>
		<h2 style="display:inline-block; margin-right:10px;"><c:out value="${st003Cryxxb.patientName}" /></h2>
		<span><a href="javascript:void(0);" onclick="warningPatient.patientInfo('${st003Cryxxb.zyid}');"><c:out value="${patientZyValue == 'patientId'?st003Cryxxb.patientId:st003Cryxxb.zyid}" /></a></span>
		<span style="margin-left:15px;"><c:out value="${st003Cryxxb.sex}" /></span>
		<span style="margin-left:15px;"><c:out value="${st003Cryxxb.age}${st003Cryxxb.ageUnit}" /></span>
		<c:if test="${!empty st003Cryxxb.bedNo}">
			<span style="margin-left:15px;"><c:out value="${st003Cryxxb.bedNo}" />床</span>
		</c:if>
		<span style="margin-left:15px;"><fmt:formatDate value="${st003Cryxxb.inHospAt}" pattern="yyyy-MM-dd HH:mm" />&nbsp;入院（<c:out value="${st003Cryxxb.inDeptName}" />）</span>
		<c:if test="${!empty st003Cryxxb.outAt}">
			<span style="margin-left:15px;"><fmt:formatDate value="${st003Cryxxb.outAt}" pattern="yyyy-MM-dd HH:mm" />&nbsp;出院（<c:out value="${st003Cryxxb.outDeptName}" />）</span>
		</c:if>
		<c:if test="${!empty st003Cryxxb.chargeDrName}">
			<span style="margin-left:15px;"><c:out value="${st003Cryxxb.chargeDrName}" /></span>
		</c:if>
		<span style="margin-left:15px;">所在科室：<c:out value="${st003Cryxxb.deptName}" /></span>
		<span style="margin-left:15px;"><c:out value="${!empty st003Cryxxb.outAt ? '已出院' : '在院'}" /></span>
		<c:if test="${clinical!='1'}">
			<c:if test="${follow=='1'}">
			<span id="guanzhuPatient" class="guanzhu" title="取消关注" onclick="foPatient()"></span>
			</c:if>
			<c:if test="${follow!='1'}">
			<span id="guanzhuPatient" class="guanzhu_gray" title="关注" onclick="foPatient()"></span>
			</c:if>
		</c:if>	
	</div>
		</c:if>
	<!-- <div id="warningPatientPanel"></div> -->
	<c:forEach items="${gr002YsgrMxList}" var="gr002YsgrMx" varStatus="status">
		<ul class="grjc_main">
			<li id="prjc_${status.index}">
				<div class="grjc_title">				
					<div class="grjc_lable">
						<span class="first">
						<c:choose>
							<c:when test="${gr002YsgrMx.reportType eq 1}">自报</c:when>
							<c:when test="${gr002YsgrMx.reportType eq 2}">代报</c:when>
							<c:otherwise>预警</c:otherwise>
						</c:choose>
						</span>
						<span><c:out value="${gr002YsgrMx.infectTypeId eq 1 ? '院感' : '社感'}" /></span>
					</div>
					<div  class="grjc_title_cont" >
						<strong><c:out value="${gr002YsgrMx.infectName}" /></strong>
						<span><fmt:formatDate value="${gr002YsgrMx.startAt}" pattern="yyyy-MM-dd" /></span>
						<%-- <span>xxxx: ${gr002YsgrMx.reportDrName}${!empty gr002YsgrMx.reportDrName ? '；' : ''}${gr002YsgrMx.auditDrName}</span> --%>
						<c:if test="${gr002YsgrMx.state ne 1}">
							<c:set var="confInfo" value="${gr002YsgrMx.reportDrName}${!empty gr002YsgrMx.reportDrName ? '；' : ''}${gr002YsgrMx.auditDrName}"></c:set>  
						</c:if>
						<c:choose>
							<c:when test="${gr002YsgrMx.state eq 1}">
								<span title="${confInfo}"><a title="${gr002YsgrMx.pcName}">(<c:out value="${gr002YsgrMx.itemName}" />)</a></span>
							</c:when>
							<c:otherwise>
								<div style="margin-top:15px;padding-top:15px;border-top: 1px dashed #ccc;margin-bottom:15px;" >
									<strong>操作信息</strong>
									<span title="${confInfo}"><a title="${gr002YsgrMx.pcName}">(<c:out value="${gr002YsgrMx.itemName}" />)</a></span>
								<c:if test="${gr002YsgrMx.state eq 2}">
									<span>${gr002YsgrMx.au}</span>
									<span><fmt:formatDate value="${gr002YsgrMx.aa}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></span>
								</c:if>
								<c:if test="${gr002YsgrMx.state eq 3}">
									<span>${gr002YsgrMx.pcr}</span>
									<span><fmt:formatDate value="${gr002YsgrMx.lod}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></span>
								</c:if>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="grjc_cont">	
					<div class="grjc_cont_header">病历
					<c:set var="count" value="0"></c:set>
					<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
						<c:if test="${item.dataForm eq '病程'}">
							<c:set var="count" value="${count+1}"></c:set>
						</c:if>
					</c:forEach>（${count}）
					</div>
					<c:if test="${count == 0}"><div class="no_data">无</div></c:if>
					<ul class="grjc_cont_list">
						<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
							<c:if test="${item.dataForm eq '病程'}">
								<li>
									<span><a title="查看预警来源" href="javascript:warningPatient.courseDisease('${item.zyid}', '${item.sjId}', '<fmt:formatDate value="${item.dataDate}" pattern="yyyy-MM-dd" />');"><c:out value="${item.elementName}" /></a></span>
									<span>（<c:out value="${item.dateRange}" />）</span>
									<span><c:out value="${item.tiemsCount}" />次</span>
									<p><c:out value="${item.originalContent}" /></p>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				<div class="grjc_cont">
					<div class="grjc_cont_header">检验
					<c:set var="count" value="0"></c:set>
					<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
						<c:if test="${item.dataForm eq '检验'}">
							<c:set var="count" value="${count+1}"></c:set>
						</c:if>
					</c:forEach>（${count}）
					</div>
					<c:if test="${count == 0}"><div class="no_data">无</div></c:if>
					<ul class="grjc_cont_list">
						 <c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
							<c:if test="${item.dataForm eq '检验'}">
								<li>
									<span><a title="查看预警来源" href="javascript:warningPatient.inspectionInfo('${item.zyid}', '${item.sjId}');"><c:out value="${item.elementName}" /></a></span>
									<span>（<c:out value="${item.dateRange}" />）</span>
									<span><c:out value="${item.tiemsCount}" />次</span>
									<p><c:out value="${item.originalContent}" /></p>
								</li>
							</c:if>
						</c:forEach>					
					</ul>
				</div>
				<div class="grjc_cont">
					<div class="grjc_cont_header">体温
					<c:set var="count" value="0"></c:set>
					<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
						<c:if test="${item.dataForm eq '体温'}">
							<c:set var="count" value="${count+1}"></c:set>
						</c:if>
					</c:forEach>（${count}）
					</div>
					<c:if test="${count == 0}"><div class="no_data">无</div></c:if>
					<ul class="grjc_cont_list">
						<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
							<c:if test="${item.dataForm eq '体温'}">
								<li>
									<span><a title="查看预警来源" href="javascript:warningPatient.temperature('${item.zyid}', '<fmt:formatDate value="${item.dataDate}" pattern="yyyy-MM-dd" />');"><c:out value="${item.elementName}" /></a></span>
									<span>（<c:out value="${item.dateRange}" />）</span>
									<span><c:out value="${item.tiemsCount}" />次</span>
									<p><c:out value="${item.originalContent}" /></p>
								</li>
							</c:if>
						</c:forEach>							
					</ul>
				</div>
				<div class="grjc_cont">
					<div class="grjc_cont_header">影像
					<c:set var="count" value="0"></c:set>
					<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
						<c:if test="${item.dataForm eq '影像'}">
							<c:set var="count" value="${count+1}"></c:set>
						</c:if>
					</c:forEach>（${count}）
					</div>
					<c:if test="${count == 0}"><div class="no_data">无</div></c:if>
					<ul class="grjc_cont_list">
						<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
							<c:if test="${item.dataForm eq '影像'}">
								<li>
									<span><a title="查看预警来源" href="javascript:warningPatient.imageResults('${item.zyid}', '${item.sjId}');"><c:out value="${item.elementName}" /></a></span>
									<span>（<c:out value="${item.dateRange}" />）</span>
									<span><c:out value="${item.tiemsCount}" />次</span>
									<p><c:out value="${item.originalContent}" /></p>
								</li>
							</c:if>
						</c:forEach>							
					</ul>
				</div>
				<div class="grjc_btn">
					<div class="grjc_btn_cont">
						<c:choose>
						<c:when test="${clinical eq 1}">
						<c:if test="${gr002YsgrMx.state eq 1}">
						<a href="javascript:void(0);" onclick="warningPatient.confirm(warningPatient.getNotNullStr('${gr002YsgrMx.gr2Relid}'), warningPatient.getNotNullStr('${gr002YsgrMx.state}'), warningPatient.getNotNullStr('${gr002YsgrMx.reportType}'), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'));">上报</a>
						<div class="dropMenu">
							<span class="drop_hover" title="排除"><a href="javascript:void(0);">排除</a></span>
							<div class="dropMenuli">
								<ul class="drpMenuli_wj">
									<li><a href="javascript:void(0);" onclick="warningPatient.exclude(warningPatient.getNotNullStr(''), warningPatient.getNotNullStr(''), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'),warningPatient.getNotNullStr('${st003Cryxxb.zyid}'),1);">是定植菌</a></li>
									<li><a href="javascript:void(0);" onclick="warningPatient.exclude(warningPatient.getNotNullStr(''), warningPatient.getNotNullStr(''), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'),warningPatient.getNotNullStr('${st003Cryxxb.zyid}'),2);">列为社感</a></li>
									<li><a href="javascript:void(0);" onclick="warningPatient.exclude(warningPatient.getNotNullStr(''), warningPatient.getNotNullStr(''), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'),warningPatient.getNotNullStr('${st003Cryxxb.zyid}'),3);">直接排除</a></li>
								</ul>
								<div class="clear"></div>
							</div>
						</div>
						</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:void(0);" onclick="intervent.intervention('${gr002YsgrMx.zyid}', '${gr002YsgrMx.patientName}');">干预</a>
						<c:if test="${gr002YsgrMx.state eq 1}">
						<a href="javascript:void(0);" onclick="warningPatient.confirm(warningPatient.getNotNullStr('${gr002YsgrMx.gr2Relid}'), warningPatient.getNotNullStr('${gr002YsgrMx.state}'), warningPatient.getNotNullStr('${gr002YsgrMx.reportType}'), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'));">代报</a>
						<%-- <a href="javascript:void(0);" onclick="warningPatient.exclude(warningPatient.getNotNullStr('${gr002YsgrMx.reportType}'), warningPatient.getNotNullStr('${gr002YsgrMx.gr2Relid}'), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'));">排除</a>--%>						
						<div class="dropMenu">
							<span class="drop_hover" title="排除"><a href="javascript:void(0);">排除</a></span>
							<div class="dropMenuli">
								<ul class="drpMenuli_wj">
									<li><a href="javascript:void(0);" onclick="warningPatient.exclude(warningPatient.getNotNullStr('${gr002YsgrMx.reportType}'), warningPatient.getNotNullStr('${gr002YsgrMx.gr2Relid}'), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'),warningPatient.getNotNullStr('${st003Cryxxb.zyid}'),1);">是定植菌</a></li>
									<li><a href="javascript:void(0);" onclick="warningPatient.exclude(warningPatient.getNotNullStr('${gr002YsgrMx.reportType}'), warningPatient.getNotNullStr('${gr002YsgrMx.gr2Relid}'), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'),warningPatient.getNotNullStr('${st003Cryxxb.zyid}'),2);">列为社感</a></li>
									<li><a href="javascript:void(0);" onclick="warningPatient.exclude(warningPatient.getNotNullStr('${gr002YsgrMx.reportType}'), warningPatient.getNotNullStr('${gr002YsgrMx.gr2Relid}'), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'),warningPatient.getNotNullStr('${st003Cryxxb.zyid}'),3);">直接排除</a></li>
								</ul>
								<div class="clear"></div>
							</div>
						</div>
						</c:if>
						</c:otherwise>
						</c:choose>

					</div>
				</div>
				<div class="clear"></div>
				<c:if test="${gr002YsgrMx.state eq 3}">
				<div class="Exclude">
					<div><span class="Exclude_title red">排除原因：</span><span class="Exclude_text">${gr002YsgrMx.excludeName}</span></div>
					<div><span class="Exclude_title">备注：</span><span class="Exclude_text">${gr002YsgrMx.remark}</span></div>
				</div>
				</c:if>
			</li>
		</ul>
	</c:forEach>
	<%-- <table class="stand_table" style="width: 700px;">
		<c:forEach items="${gr002YsgrMxList}" var="gr002YsgrMx" varStatus="status">
		<tr>
			<td>
				<c:choose>
					<c:when test="${gr002YsgrMx.reportType eq 1}">自报</c:when>
					<c:when test="${gr002YsgrMx.reportType eq 2}">代报</c:when>
					<c:otherwise>预警</c:otherwise>
				</c:choose>
				<c:out value="${gr002YsgrMx.infectTypeId eq 1 ? '院感' : '社感'}" />
				<c:out value="${gr002YsgrMx.infectName}" /><fmt:formatDate value="${gr002YsgrMx.startAt}" pattern="yyyy-MM-dd HH:mm" />
			</td>
		</tr>
		</c:forEach>
	</table> --%>
	<table class="table_grjc" cellspacing="0" cellpadding="0">	
		<tbody>
			<tr class="table_header">
				<td colspan="6">重要关注因素</td>
			</tr>
			<tr>
				<th width="80">血常规</th><td width="80"><font color="${fn:split(focusMap.xcg, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.xcg}" /></font></td>
				<th width="80">血培养</th><td width="80"><font color="${fn:split(focusMap.xpy, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.xpy}" /></font></td>
				<th width="80">尿常规</th><td width="80"><font color="${fn:split(focusMap.ncg, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.ncg}" /></font></td>
			</tr>
			<tr>
				<th>PCT</th><td><font color="${fn:split(focusMap.jgsy, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.jgsy}" /></font></td>
				<th>呼吸机</th><td><c:out value="${focusMap.hxj}" /></td>
				<th>泌尿道插管</th><td><c:out value="${focusMap.mndcg}" /></td>
			</tr>
			<tr>
				<th>住院天数</th><td><c:out value="${focusMap.zyts}" /></td>
				<th>总发热</th><td><c:out value="${focusMap.fr}" /></td>
				<th>中心静脉插管</th><td><c:out value="${focusMap.jmcg}" /></td>
			</tr>
			<tr>
				<th width="80">C反应蛋白</th><td width="80"><font color="${fn:split(focusMap.cfydb, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.cfydb}" /></font></td>
				<th></th><td></td>
				<th></th><td></td>
			</tr>
			
			<c:if test="${fn:length(inZdxx) > 0}">
				<c:forEach items="${inZdxx}" var="zdxx" varStatus="status">
					<tr>
						<c:if test="${status.index == 0}">
							<th rowspan="${fn:length(inZdxx)}">入院诊断</th>
						</c:if>
						<td colspan="5"><c:out value="${zdxx.diagnosisName}" /><c:out value="${zdxx.diagnosisTypeMain eq '1' ? '（主要诊断）' : ''}" /><c:out value="${zdxx.outcome}" /></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(outZdxx) > 0}">
				<c:forEach items="${outZdxx}" var="zdxx" varStatus="status">
					<tr>
						<c:if test="${status.index == 0}">
							<th rowspan="${fn:length(outZdxx)}">出院诊断</th>
						</c:if>
						<td colspan="5"><c:out value="${zdxx.diagnosisName}" /><c:out value="${zdxx.diagnosisTypeMain eq '1' ? '（主要诊断）' : ''}" /><c:out value="${zdxx.outcome}" /></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
<script type="text/javascript">
//关注患者
function foPatient(){
	if($("#guanzhuPatient").attr("title")=='关注'){
		$.ajax({
            url: '${webroot}/foPatient/f_json/save.shtml',
            type: 'post',
            data: { patientId:'${st003Cryxxb.patientId}'},
            dataType: 'json',
            success : function(json) {
            	if(json.result=='success'){
            		$("#guanzhuPatient").attr("title","取消关注");
            	/* $("#followPatient").attr("class","img_satr"); */
            		$("#guanzhuPatient").attr("class","guanzhu");
            	}
            }
		});
	}else{
		$.ajax({
            url: '${webroot}/foPatient/f_json/delete.shtml',
            type: 'post',
            data: { patientId:'${st003Cryxxb.patientId}'},
            dataType: 'json',
            success : function(json) {
            	if(json.result=='success'){
            		$("#guanzhuPatient").attr("title","关注");
            	/*	$("#followPatient").attr("class","img_satr_gray"); */
            		$("#guanzhuPatient").attr("class","guanzhu_gray");
            	}
            }
		});
		
	}
};
	var warningPatient = {
		panel : 'warningPatientPanel',
		
		index : '${fn:length(gr002YsgrMxList)}',
		
		diagType : '${param.diagType}',
		
		//刷新页面用
		query : function() {
			<c:choose>
				<c:when test="${clinical eq 1}">
					parent.bk001Sbk.query();
					parent.Comm.dialogClose('${param.dialogId}');
				</c:when>
				<c:otherwise>
					$(".tree-node-selected").trigger("click");
				</c:otherwise>
			</c:choose>
		},
		patientInfo : function(zyId){
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyId+"&tab=2",true);
		},
		
		//按逗号分割截取第一条
		getFirstSplit : function(str) {
			return str.split(",")[0];
		},
		
		//病程穿透
		courseDisease : function (zyid, sjId, dataDate) {
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=7&zyid=' + zyid + '&sjId=' + warningPatient.getFirstSplit(sjId) + '&dataDate=' + dataDate,true);
		},
		
		//检验穿透
		inspectionInfo : function (zyid, sjId) {
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=4&zyid=' + zyid + '&sjId=' + warningPatient.getFirstSplit(sjId),true);
		},
		
		//体温穿透
		temperature : function (zyid, dataDate) {
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=6&zyid=' + zyid + '&dataDate=' + dataDate,true);
		},
		
		//影像结果穿透
		imageResults : function (zyid, sjId) {
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=8&zyid=' + zyid + '&sjId=' + warningPatient.getFirstSplit(sjId),true);
		},
		
		//抗菌药物穿透
		kjywResults : function (zyid, sjId) {
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=3&zyid=' + zyid + '&sjId=' + warningPatient.getFirstSplit(sjId),true);
		},
		
	    //确认
	    confirm : function(gr2Relid, state, reportType, regId) {
	    	if (state != '1') {
	    		if (!gr2Relid || gr2Relid == '') {
	    			$.messager.show({ title: '提示', msg: '关联关系不存在！' });
	    			return false;
	    		}
	    		if(warningPatient.diagType=='1'){
	    			parent.parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&isAuth=${param.isAuth}&bk2Relid=' + gr2Relid,true);
	    		}else{
	    			parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&isAuth=${param.isAuth}&bk2Relid=' + gr2Relid,true);

	    		}
	    	} else {
	    		if (!reportType && reportType != '1' && reportType != '2') { //系统预警 - 上报
	    			if(warningPatient.diagType=='1'){
	    				parent.parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&isAuth=${param.isAuth}&regId=' + regId,true);
	    			}else{
	    				parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&isAuth=${param.isAuth}&regId=' + regId,true);
	    			}
	    		} else { //修改
	    			if (!gr2Relid || gr2Relid == '') {
	        			$.messager.show({ title: '提示', msg: '关联关系不存在！' });
	        			return false;
	        		}
	    			if(warningPatient.diagType=='1'){
	    				parent.parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&isAuth=${param.isAuth}&action=edit&bk2Relid=' + gr2Relid,true);
	    			}else{
	    				parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&isAuth=${param.isAuth}&action=edit&bk2Relid=' + gr2Relid,true);	
	    			}		
	    		}
	    	}
	    	parent.Comm.dialogClose('${param.dialogId}');
	    },
	    //定植
	    dingzhi : function(zyid,regId){
	    	Comm.dialogGlobal({
	        	url:"${webroot}/gr002YsgrMx/f_view/toSsbbList.shtml?zyid=" + zyid +"&regId="+regId,
	            title: '检出菌',
	            width:760,
	            height:360,
	            type:"iframe",
	            parent:this
	        });
	    },
	    shequ : function(regId,bk2Relid){
	    	Comm.dialogGlobal({
	        	url:"${webroot}/bk001Sbk/f_view/sqgrCard.shtml?regId="+regId+"&bk2Relid="+bk2Relid,
	            title: '社区感染',
	            width:460,
	            height:300,
	            type:"iframe",
	            parent:this
	        });
	    },
	    //排除
	    exclude : function(reportType, gr2Relid, regId,zyid,excludeType) {
	    	if (!reportType && reportType != '1' && reportType != '2' && (!gr2Relid || gr2Relid == '')) { //系统预警走排除
	    		if(excludeType=='1'){//定植
	    			warningPatient.dingzhi(zyid,regId);
	    		}else if(excludeType=='2'){
	    			warningPatient.shequ(regId,gr2Relid);
	    		}else if(excludeType=='3'){
	    			Comm.dialogGlobal({
			        	url:"${webroot}/gr002YsgrMx/f_view/toRuleOutInfect.shtml?regId=" + regId,
			            title: '排除',
			            width:360,
			            height:320,
			            type:"iframe",
						parent:this
			        });
	    		}else{
	    			Comm.dialogGlobal({
			        	url:"${webroot}/gr002YsgrMx/f_view/toRuleOutInfect.shtml?regId=" + regId,
			            title: '排除',
			            width:360,
			            height:320,
			            type:"iframe",
			            parent:this
			        });
	    		}    		
	    	} else { //走审核流程
	    		if (!gr2Relid || gr2Relid == '') {
	    			$.messager.show({ title: '提示', msg: '关联关系不存在！' });
	    			return false;
	    		}
	    		parent.menuInfo.clickMenu('感染上报','/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&bk2Relid=' + gr2Relid,true);
	    	}
	    	
	    },
	    getNotNullStr : function(str) {
			if (str == null) {
				return '';
			} else {
				return str;
			}
		},
		gethMdDateStr : function(str) {
			if (str && str.length > 10) {
				return str.substring(0,10);
			} else {
				return '';
			}
		},
		//列表加载合并处理
		mergeCells : function(rows) {
			var merges = new Array();
			var index = 0;
			var rowspan = 0;
			var regId = '';
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				if (i == 0) {
					regId = row.regId;
				} else if (((regId != row.regId || i == rows.length - 1) && rowspan > 1) || (i == rows.length - 1 && rowspan == 1)) {
					if (i == rows.length - 1 && regId == row.regId) {
						rowspan ++;
					}
					var merge = {};
					merge['index'] = index;
					merge['rowspan'] = rowspan;
					merges.push(merge);
					rowspan = 0;
					regId = row.regId;
					index = i;
				} else if (regId != row.regId) {
					rowspan = 0;
					regId = row.regId;
					index = i;
				}
				rowspan ++;
			}
			for (var i = 0; i < merges.length; i++){
                $('#' + warningPatient.panel).datagrid('mergeCells',{
                    index: merges[i].index,
                    field: 'infectName',
                    rowspan: merges[i].rowspan
                });
                $('#' + warningPatient.panel).datagrid('mergeCells',{
                    index: merges[i].index,
                    field: '_operate',
                    rowspan: merges[i].rowspan
                });
            }
		}
	};
	
	var intervent = {
			//刷新页面用
			query : function() {
				
			},
			//干预
			intervention : function(zyid, name) {
		        Comm.dialogGlobal({
		        	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid=" + zyid + '&msgType=1',
		            title: '患者【' + name + '】的干预内容',
		            width:870,
		            height:565,
		            type:"iframe",
		            parent:this
		        });
		    },
		};
	
	$(document).ready(function () {
		/*数据自适应高度*/
		for (var i = 0; i < parseInt(warningPatient.index); i ++) {
			var id="prjc_" + i;
			var height = document.getElementById(id).offsetHeight - 20;
			var li_id = document.getElementById(id);document.getElementById(id);;
			$(li_id).find(".grjc_cont").css({"height": height+"px"});
			$(li_id).find(".grjc_title").css({"height": height+"px"});
			$(li_id).find(".grjc_btn").css({"height": height+"px"});		
		}
		
		/* $('#' + warningPatient.panel).datagrid({
			fit: true,
	        nowrap: true,
	        autoRowHeight: true,
            remoteSort:false,
            striped: false,
            fitColumns: false,
            singleSelect: true,
            border:false,
            url:'${webroot}/gr002YsgrMx/f_json/findWarningResults.shtml?zyid=${param.zyid}&regId=${param.regId}&gr2Relid=${param.gr2Relid}',
            columns:[[
				{field:'infectName',title:'感染部位',width:130},
                {field:'dataForm',title:'来源',width:50,align:'center'},
                {field:'elementName',title:'原因',width:80},
                {field:'tiemsCount',title:'出现次数',width:60,align:'center'},
                {field:'dateRange',title:'出现时间',width:100,align:'center'},
                {field:'originalContent',title:'说明',width:160},
                {field:'feverDays',title:'发热',width:50,align:'center'},
                {field:'bloodTestCount',title:'血常规异常率',width:80,
                	formatter:function(value,row,index){
                		return (row.bloodTestCount ?  row.bloodTestUnusualCount + '/' + row.bloodTestCount : '');
                	}
                },
                {field:'remark',title:'尿常规异常率',width:80},
                {field:'remark',title:'插管情况',width:80},
                {field:'remark',title:'术后天数',width:60},
                {field:'_operate',title:'操作',width:55,align:'center',
					formatter:function(value,row,index){
						return ['<a href="javascript:void(0);" class="ico_editor" title="确认" onclick ="warningPatient.confirm(\'' + casesWarning.getNotNullStr(row.gr2Relid) + '\', \'' + casesWarning.getNotNullStr(row.state) + '\', \'' + casesWarning.getNotNullStr(row.reportType) + '\', \'' + casesWarning.getNotNullStr(row.regId) + '\')"></a>' + 
								'<a href="javascript:void(0);" class="ico_del" title="排除" onclick ="warningPatient.exclude(\'' + casesWarning.getNotNullStr(row.reportType) + '\', \'' + casesWarning.getNotNullStr(row.gr2Relid) + '\', \'' + casesWarning.getNotNullStr(row.regId) + '\')"></a>'].join('');
					}
				}
            ]],
            rownumbers:true,
            toolbar:'#tb_warningPatient',
        	onLoadSuccess:function(data){
        		$(this).prev().find('div.datagrid-body').unbind('mouseover');
        		if (data.rows) {
        			warningPatient.mergeCells(data.rows);
        		}
        	}
        }); */
	});
</script>
</body>
</html>
