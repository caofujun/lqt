<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>感染病例监测</title>
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
	<ul class="grjc_main">
	<c:forEach items="${gr002YsgrMxList}" var="gr002YsgrMx" varStatus="status">
		<li class="prjc_1ist">
			<div class="grjc_title">
				<div  class="grjc_title_cont" >
					<strong><c:out value="${gr002YsgrMx.infectName}" /></strong>
					<span><fmt:formatDate value="${gr002YsgrMx.startAt}" pattern="yyyy-MM-dd" /></span>
	
					<span class="grjc_sg"><c:choose>
							<c:when test="${gr002YsgrMx.reportType eq 1}">自报</c:when>
							<c:when test="${gr002YsgrMx.reportType eq 2}">代报</c:when>
							<c:otherwise>预警</c:otherwise>
						</c:choose> | <c:out value="${gr002YsgrMx.infectTypeId eq 1 ? '院感' : '社感'}" /></span>
					<c:choose>
						<c:when test="${gr002YsgrMx.state eq 1}">
							<span><a title="${gr002YsgrMx.pcName}">(<c:out value="${gr002YsgrMx.itemName}" />)</a></span>
						</c:when>
						<c:otherwise>
							<span class="orange">
								<span title="${confInfo}"><a title="${gr002YsgrMx.pcName}">(<c:out value="${gr002YsgrMx.itemName}" />)</a></span>
							<c:if test="${gr002YsgrMx.state eq 2}">
								<span>${gr002YsgrMx.au}</span>
								<span><fmt:formatDate value="${gr002YsgrMx.aa}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></span>
							</c:if>
							<c:if test="${gr002YsgrMx.state eq 3}">
								<span>${gr002YsgrMx.pcr}</span>
								<span><fmt:formatDate value="${gr002YsgrMx.lod}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></span>
							</c:if>
							</span>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div class="grjc_title_r">
					<a href="javascript:void(0);" onclick="intervent.intervention('${gr002YsgrMx.zyid}', '${gr002YsgrMx.patientName}');">干预</a>
					<c:choose>
					<c:when test="${clinical eq 1}">
					<c:if test="${gr002YsgrMx.state eq 1}">
					<a href="javascript:void(0);" onclick="warningPatient.confirm(warningPatient.getNotNullStr('${gr002YsgrMx.gr2Relid}'), warningPatient.getNotNullStr('${gr002YsgrMx.state}'), warningPatient.getNotNullStr('${gr002YsgrMx.reportType}'), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'));">上报</a>
					<div class="dropMenu">
						<span class="drop_hover" title="排除">排除</span>
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
					<c:if test="${gr002YsgrMx.state eq 1}">
					<a href="javascript:void(0);" onclick="warningPatient.confirm(warningPatient.getNotNullStr('${gr002YsgrMx.gr2Relid}'), warningPatient.getNotNullStr('${gr002YsgrMx.state}'), warningPatient.getNotNullStr('${gr002YsgrMx.reportType}'), warningPatient.getNotNullStr('${gr002YsgrMx.regId}'));">代报</a>
					<div class="dropMenu">
						<span class="drop_hover" title="排除">排除</span>
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
					</c:otherwise>
					</c:choose>
					<div class="SetOpen"></div>
				</div>
				
			</div>
			<div class="grjc_cont">
				<table class="table">
					<c:set var="count" value="0"></c:set>
					<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
						<c:if test="${item.dataForm eq '病程'}">
							<c:set var="count" value="${count+1}"></c:set>
						</c:if>
					</c:forEach>
					<c:choose>
					<c:when test="${count == 0}"></c:when>
					<c:otherwise>
					<tr>
						<td width="130"><div class="grjc_cont_header"><span class="yj_ico_bl">病历</span></div></td>
						<td class="b_b_d">
							<ul class="grjc_cont_list">
								<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
								<c:if test="${item.dataForm eq '病程'}">
								<li>
									<span class="grjc_tag_a"><a title="查看预警来源" href="javascript:warningPatient.courseDisease('${item.zyid}', '${item.sjId}', '<fmt:formatDate value="${item.dataDate}" pattern="yyyy-MM-dd" />');"><c:out value="${item.elementName}" /></a></span>
									<span>（<c:out value="${item.dateRange}" />）</span>
									<p><c:out value="${item.originalContent}" /></p>
								</li>
								</c:if>
								</c:forEach>
								
							</ul>
						</td>
					</tr>
					</c:otherwise>
					</c:choose>
					<c:set var="count" value="0"></c:set>
					<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
						<c:if test="${item.dataForm eq '检验'}">
							<c:set var="count" value="${count+1}"></c:set>
						</c:if>
					</c:forEach>
					<c:choose>
					<c:when test="${count == 0}"></c:when>
					<c:otherwise>					
					<tr>
						<td width="130"><div class="grjc_cont_header"><span class="yj_ico_jy">检验</span></div></td>
						<td class="b_b_d">
							<ul class="grjc_cont_list">
								<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
								<c:if test="${item.dataForm eq '检验'}">
								 <li>
								 	<input type="hidden" class="sjId_hide" value="${item.sjId}"/>
									<span class="grjc_tag_a"><a title="查看预警来源" href="javascript:warningPatient.inspectionInfo('${item.zyid}', '${item.sjId}');"><c:out value="${item.elementName}" /></a></span>
									<span>（<c:out value="${item.dateRange}" />）</span>
									<p><c:out value="${item.originalContent}" /></p>
								</li>
								</c:if>
								</c:forEach>
														
							</ul>
						</td>
					</tr>
					</c:otherwise>
					</c:choose>
					<c:set var="count" value="0"></c:set>
					<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
						<c:if test="${item.dataForm eq '体温'}">
							<c:set var="count" value="${count+1}"></c:set>
						</c:if>
					</c:forEach>
					<c:choose>
					<c:when test="${count == 0}"></c:when>
					<c:otherwise>
					<tr>
						<td width="130"><div class="grjc_cont_header"><span class="yj_ico_tw">体温</span></div></td>
						<td class="b_b_d">
							<ul class="grjc_cont_list">
							<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
							<c:if test="${item.dataForm eq '体温'}">
								<li>
									<span class="grjc_tag_a"><a title="查看预警来源" href="javascript:warningPatient.temperature('${item.zyid}', '<fmt:formatDate value="${item.dataDate}" pattern="yyyy-MM-dd" />');"><c:out value="${item.elementName}" /></a></span>
									<span>（<c:out value="${item.dateRange}" />）</span>								
									<p><c:out value="${item.originalContent}" /></p>
								</li>
							</c:if>
							</c:forEach>								
							</ul>
						</td>
					</tr>
					</c:otherwise>
					</c:choose>
					<c:set var="count" value="0"></c:set>
					<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
						<c:if test="${item.dataForm eq '影像'}">
							<c:set var="count" value="${count+1}"></c:set>
						</c:if>
					</c:forEach>
					<c:choose>
					<c:when test="${count == 0}"></c:when>
					<c:otherwise>
					<tr>
						<td width="130"><div class="grjc_cont_header"><span class="yj_ico_yx">影像</span></div></td>					
						<td class="b_b_d">
							<ul class="grjc_cont_list">
							<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
							<c:if test="${item.dataForm eq '影像'}">
								<li>
									<span class="grjc_tag_a"><a title="查看预警来源" href="javascript:warningPatient.imageResults('${item.zyid}', '${item.sjId}');"><c:out value="${item.elementName}" /></a></span>
									<span>（<c:out value="${item.dateRange}" />）</span>				
									<p><c:out value="${item.originalContent}" /></p>
								</li>
							</c:if>
							</c:forEach>								
							</ul>
						</td>
					</tr>
					</c:otherwise>
					</c:choose>
					<c:set var="count" value="0"></c:set>
					<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
						<c:if test="${item.dataForm eq '医嘱'}">
							<c:set var="count" value="${count+1}"></c:set>
						</c:if>
					</c:forEach>
					<c:choose>
					<c:when test="${count == 0}"></c:when>
					<c:otherwise>
					<tr>
						<td width="130"><div class="grjc_cont_header"><span class="yj_ico_kjyw">抗菌药物</span></div></td>					
						<td class="b_b_d">
							<ul class="grjc_cont_list">
							<c:forEach items="${gr002YsgrMx.gr002YsgrMxList}" var="item">
							<c:if test="${item.dataForm eq '医嘱'}">
								<li>
									<span class="grjc_tag_a"><a title="查看预警来源" href="javascript:warningPatient.kjywResults('${item.zyid}', '${item.sjId}');"><c:out value="${item.elementName}" /></a></span>
									<span>（<c:out value="${item.dateRange}" />）</span>
									<p><c:out value="${item.originalContent}" /></p>
								</li>
							</c:if>
							</c:forEach>															
							</ul>
						</td>
					</tr>
					</c:otherwise>
					</c:choose>
				</table>
			</div>
			<c:if test="${gr002YsgrMx.state eq 3}">
			<div class="grjc_cont_note"><span>排除原因：</span><p>${gr002YsgrMx.excludeName}，${gr002YsgrMx.remark}</p></div>
			</c:if> 			
		</li>
		</c:forEach>
		
		
		<li class="prjc_1ist">
			<div class="grjc_title">
				<div  class="grjc_title_cont" >
					<strong>重要关注因素</strong>					
				</div>
				<div class="grjc_title_r">					
					&nbsp;<div class="SetOpen"></div>
				</div>	
			</div>
			<div class="grjc_cont">
				<table class="table_grjc" cellspacing="0" cellpadding="0">					
					<tr>
						<th>血常规</th>
						<th>血培养</th>
						<th>尿常规</th>
						<th>PCT</th><th>呼吸机</th>
						<th>泌尿道插管</th>
						<th>住院天数</th>
						<th>总发热</th>
						<th>中心静脉插管</th>
						<th>C反应蛋白</th>
						<th>超敏C反应蛋白</th>
					</tr>
					<tr>	
						<td><span class="${fn:split(focusMap.xcg, '/')[1] ne '0' ? '' : 'grey'}${fn:split(focusMap.xcg, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.xcg}" /></span></td>						
						<td><span class="${fn:split(focusMap.xpy, '/')[1] ne '0' ? '' : 'grey'}${fn:split(focusMap.xpy, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.xpy}" /></span></td>						
						<td><span class="${fn:split(focusMap.ncg, '/')[1] ne '0' ? '' : 'grey'}${fn:split(focusMap.ncg, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.ncg}" /></span></td>						
						<td><span class="${fn:split(focusMap.jgsy, '/')[1] ne '0' ? '' : 'grey'}${fn:split(focusMap.jgsy, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.jgsy}" /></span></td>						
						<td><span class="${focusMap.hxj eq '0天' ? 'grey' : ''}"><c:out value="${focusMap.hxj}" /></span></td>						
						<td><span class=""${focusMap.mndcg eq '0天' ? 'grey' : ''}"><c:out value="${focusMap.mndcg}" /></span></td>						
						<td><span class=""><c:out value="${focusMap.zyts}" /></span></td>						
						<td><span class="${focusMap.fr eq '0天' ? 'grey' : ''}"><c:out value="${focusMap.fr}" /></span></td>						
						<td><span class="${focusMap.jmcg eq '0天' ? 'grey' : ''}"><c:out value="${focusMap.jmcg}" /></span></td>
						<td><span class="${fn:split(focusMap.cfydb, '/')[1] ne '0' ? '' : 'grey'}${fn:split(focusMap.cfydb, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.cfydb}" /></span></td>		
						<td><span class="${fn:split(focusMap.cmcfydb, '/')[1] ne '0' ? '' : 'grey'}${fn:split(focusMap.cmcfydb, '/')[0] ne '0' ? 'red' : ''}"><c:out value="${focusMap.cmcfydb}" /></span></td>				
					</tr>						
				</table>
				<table class="table">
				<c:if test="${fn:length(inZdxx) > 0}">
					<tr>
						<td width="130"><div class="grjc_cont_header"><span>入院诊断</span></div></td>
						<td>
						<c:forEach items="${inZdxx}" var="zdxx" varStatus="status">
							<span><c:out value="${zdxx.diagnosisName}" /><c:out value="${zdxx.diagnosisTypeMain eq '1' ? '（主要诊断）' : ''}" /><c:out value="${zdxx.outcome}" /></span>
						</c:forEach>
						</td>
					</tr>
				</c:if>
				<c:if test="${fn:length(outZdxx) > 0}">
					<tr>
						<td><div class="grjc_cont_header"><span>出院诊断</span></div></td>
						<td>
						<c:forEach items="${outZdxx}" var="zdxx" varStatus="status">
							<span><c:out value="${zdxx.diagnosisName}" /><c:out value="${zdxx.diagnosisTypeMain eq '1' ? '（主要诊断）' : ''}" /><c:out value="${zdxx.outcome}" />，</span>
						</c:forEach>
						</td>
					</tr>
				</c:if>					
				</table>
			</div>			
		</li>
	
	
	</ul>	
	
	<script  type="text/javascript">
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
				parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=7&zyid=' + zyid + '&sjId=' + warningPatient.getFirstSplit(sjId),true);
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
		    dingzhi : function(zyid,regId,sjId){
		    	Comm.dialogGlobal({
		        	url:"${webroot}/gr002YsgrMx/f_view/toSsbbList.shtml?zyid=" + zyid +"&regId="+regId+ "&sjId="+sjId,
		            title: '请选择需要审核的菌',
		            width:800,
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
		    			var e = document.all ? window.event : arguments[0] ? arguments[0] : event;  
		    			var sjId = "";
		    			$(e.target).eq(0).parents(".prjc_1ist").find(".sjId_hide").each(function(){
		    				sjId+=$(this).val()+",";
		    			});
		    			warningPatient.dingzhi(zyid,regId,sjId);
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
		//显示隐藏效果
	$(function (){		
		$(".SetOpen").bind('click',function(event){
			if($(this).parent(".grjc_title_r").parent(".grjc_title").next(".grjc_cont").is(":visible")){
				$(this).parent(".grjc_title_r").parent(".grjc_title").next(".grjc_cont").slideUp("slow");
				$(this).css({"border-color":"#aaa rgba(0, 0, 0, 0) rgba(0, 0, 0, 0) rgba(0, 0, 0, 0)"});
				$(this).css({"border-bottom-width":"0"});
				$(this).css({"border-top-width":"8px"});
				return false;
			}else{
				$(this).parent(".grjc_title_r").parent(".grjc_title").next(".grjc_cont").slideDown("slow");
				$(this).css({"border-color":"rgba(0, 0, 0, 0) rgba(0, 0, 0, 0) #aaa rgba(0, 0, 0, 0)"});
				$(this).css({"border-bottom-width":"8px"});
				$(this).css({"border-top-width":"0"});
				return false;
			}
		});
	});
	</script>
</body>
</html>
