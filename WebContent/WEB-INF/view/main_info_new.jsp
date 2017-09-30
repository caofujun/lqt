<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蓝蜻蜓医院感染实时监控平台</title>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/core/report.jsp"></jsp:include>
<script type="text/javascript"
	src="${webroot}/resources/echarts/theme/macarons.js"></script>
<script type="text/javascript"
	src="${webroot}/resources/load/jquery.loadmask.min.js"></script>
<script type="text/javascript"
	src="${webroot}/resources/js/jquery-ui.js"></script>
<link href="${webroot}/resources/css_org/layout.css" rel="stylesheet"
	type="text/css" />
</head>
<body style="height: 100%; margin: 0 auto;">
	<div class="home_main">
		<div class="mod_define datalist_li_all">
			<h4>
				全院概况<span class="ml10"><c:out value="${date}" /></span>
				<!-- <span class="mod_define_r_btn"><a href="#" title="上一天"><i class="icon iconfont">&#xe679;</i></a><a href="#" title="下一天"><i class="icon iconfont">&#xe67a;</i></a><a href="#" title="今天">今</a></span> -->
			</h4>
			<ul class="datalist_li">
				<li
					onclick="parent.menuInfo.clickMenu('监测日报','/gm003Ybsj/f_view/dayIndex.shtml?dayDate=${startDate}',true)">
					<h3>在院患者</h3>
					<h2>
						<c:out value="${floorData.zyhsCount}" />
					</h2>
				</li>
				<li title=">=${tw}℃点击查看"
					onclick="parent.menuInfo.clickMenu('监测日报','/gm003Ybsj/f_view/dayIndex.shtml?dayDate=${startDate}',true)">
					<h3>体温异常</h3>
					<h2>
						<c:out value="${floorData.twycCount}" />
					</h2>
				</li>
				<li
					onclick="parent.menuInfo.clickMenu('监测日报','/gm003Ybsj/f_view/dayIndex.shtml?dayDate=${startDate}',true)">
					<h3>中心静脉插管</h3>
					<h2>
						<c:out value="${floorData.zxjmCount}" />
					</h2>
				</li>
				<li
					onclick="parent.menuInfo.clickMenu('监测日报','/gm003Ybsj/f_view/dayIndex.shtml?dayDate=${startDate}',true)">
					<h3>留置导尿</h3>
					<h2>
						<c:out value="${floorData.mndCount}" />
					</h2>
				</li>
				<li
					onclick="parent.menuInfo.clickMenu('监测日报','/gm003Ybsj/f_view/dayIndex.shtml?dayDate=${startDate}',true)">
					<h3>呼吸机</h3>
					<h2>
						<c:out value="${floorData.hxjCount}" />
					</h2>
				</li>
				<li
					onclick="parent.menuInfo.clickMenu('每日检出菌','/xn011Dclymx/f_view/dayIndex.shtml?date=${startDate}',true)">
					<h3>重点菌检出</h3>
					<h2>
						<c:out value="${floorData.zdjCount}" />
					</h2>
				</li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="mod_define datalist_li_need">
			<h4>待办事宜</h4>
			<ul class="datalist_li">
				<c:if test="${isByShow==1}">	
				<li
					onclick="parent.menuInfo.clickMenu('暴发记录','/by007Show/f_view/toOutbreakTab.shtml?startDate=${toStartDate}&endDate=${toEndDate}',true);">
					<h3>暴发预警</h3>
					<h2 class="red">
						<c:out value="${toData.bfyjCount}" />
					</h2>
				</li>
				</c:if>
				<li
					onclick="parent.menuInfo.clickMenu('感染预警','/gr002YsgrMx/f_view/toCasesWarning.shtml?isAll=1&startDate=${toStartDate}&endDate=${toEndDate}',true);">
					<h3>感染预警</h3>
					<h2 class="orange">
						<c:out value="${toData.gryjCount}" />
					</h2>
				</li>
				<%-- <li
					onclick="parent.menuInfo.clickMenu('风险分析','/fxPatient/f_view/index.shtml?startDate=${toStartDate}&endDate=${toEndDate}',true);">
					<h3>风险预警</h3>
					<h2 class="yellow">
						<c:out value="${toData.fxyjCount}" />
					</h2>
				</li> --%>
				<li class="li_border"></li>
				<li
					onclick="parent.menuInfo.clickMenu('感染病例查询','/bk001Sbk/f_view/toInfectionsQuery.shtml?infectTypeId=0&bkState=0&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);">
					<h3>感染报卡</h3>
					<h2>
						<c:out value="${toData.bkCount}" />
					</h2>
				</li>
				<c:if test="${fn:contains(systemScope,'cdc') && allowCards!= null && fn:length(allowCards) > 0 && isShowGw==1}">
				<li class="dropDown">
					<h3>公卫报卡</h3>
					<h2>
						<c:set value="0" var="bkCount" />  
						<c:forEach items="${allowCards}" var="ac">
							<c:if test="${fn:contains(cdcScope,ac.dictCode) or fn:contains(cdcScope,'all')}">
								<c:if test="${'crbbk' eq ac.dictCode}">
									<c:set value="${bkCount + toData.crbbkCount}" var="bkCount" />  
								</c:if>
								<c:if test="${'syjcbk' eq ac.dictCode}">
									<c:set value="${bkCount + toData.syxbkCount}" var="bkCount" />  
								</c:if>
								<c:if test="${'sybk' eq ac.dictCode}">
									<c:set value="${bkCount + toData.sybkCount}" var="bkCount" />  
								</c:if>
								<c:if test="${'zlbk' eq ac.dictCode}">
									<c:set value="${bkCount + toData.zlbkCount}" var="bkCount" />  
								</c:if>
								<c:if test="${'syycbk' eq ac.dictCode}">
									<c:set value="${bkCount + toData.syycbkCount}" var="bkCount" />  
								</c:if>
								<c:if test="${'xnxgbk' eq ac.dictCode}">
									<c:set value="${bkCount + toData.xnxgbkCount}" var="bkCount" />  
								</c:if>
								<c:if test="${'gwzsbk' eq ac.dictCode}">
									<c:set value="${bkCount + toData.gwzsbkCount}" var="bkCount" />  
								</c:if>
							</c:if>
						</c:forEach>
						${bkCount}
						<%-- 
						 <c:out value="${toData.crbbkCount+toData.syxbkCount+toData.sybkCount+toData.zlbkCount+toData.syycbkCount}" />
						 --%>
					</h2>
					<div class="dropDown_box">
						<i class="icon_spike"></i>
						<table>
							<tr><td>名称</td><td>待处理数</td></tr>
							<c:forEach items="${allowCards}" var="ac">
								<c:if test="${fn:contains(cdcScope,ac.dictCode) or fn:contains(cdcScope,'all')}">
									<c:if test="${'crbbk' eq ac.dictCode}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=crbbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>传染病报卡</td><td><b><c:out value="${toData.crbbkCount}" /></b></td></tr>
									<%-- <c:if test="${fn:contains(cdcScope,'crbbk') or fn:contains(cdcScope,'all')}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=crbbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>传染病报卡</td><td><b><c:out value="${toData.crbbkCount}" /></b></td></tr>
										</c:if> --%>
									</c:if>
									<c:if test="${'syjcbk' eq ac.dictCode}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=syjcbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>食源报卡</td><td><b><c:out value="${toData.syxbkCount}" /></b></td></tr>
								    <%-- <c:if test="${fn:contains(cdcScope,'syjcbk') or fn:contains(cdcScope,'all')}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=crbbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>传染病报卡</td><td><b><c:out value="${toData.crbbkCount}" /></b></td></tr>
										</c:if> --%>
									</c:if>
									<c:if test="${'sybk' eq ac.dictCode}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=sybk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>死因报卡</td><td><b><c:out value="${toData.sybkCount}" /></b></td></tr>
										<%-- <c:if test="${fn:contains(cdcScope,'sybk') or fn:contains(cdcScope,'all')}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=sybk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>死因报卡</td><td><b><c:out value="${toData.sybkCount}" /></b></td></tr>
										</c:if> --%>
									</c:if>
									<c:if test="${'zlbk' eq ac.dictCode}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=zlbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>肿瘤报卡</td><td><b><c:out value="${toData.zlbkCount}" /></b></td></tr>
										<%-- <c:if test="${fn:contains(cdcScope,'zlbk') or fn:contains(cdcScope,'all')}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=zlbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>肿瘤报卡</td><td><b><c:out value="${toData.zlbkCount}" /></b></td></tr>
										</c:if> --%>
									</c:if>
									<c:if test="${'syycbk' eq ac.dictCode}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=syycbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>食源异常报卡</td><td><b><c:out value="${toData.syycbkCount}" /></b></td></tr>
										<%-- <c:if test="${fn:contains(cdcScope,'syycbk') or fn:contains(cdcScope,'all')}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=syycbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>食源异常报卡</td><td><b><c:out value="${toData.syycbkCount}" /></b></td></tr>
										</c:if> --%>
									</c:if>
									<c:if test="${'xnxgbk' eq ac.dictCode}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=xnxgbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>心脑血管报卡</td><td><b><c:out value="${toData.xnxgbkCount}" /></b></td></tr>
										<%-- <c:if test="${fn:contains(cdcScope,'xnxgbk') or fn:contains(cdcScope,'all')}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=xnxgbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>心脑血管报卡</td><td><b><c:out value="${toData.xnxgbkCount}" /></b></td></tr>
										</c:if> --%>
									</c:if>
									<c:if test="${'gwzsbk' eq ac.dictCode}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=gwzsbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>高温中暑报卡</td><td><b><c:out value="${toData.gwzsbkCount}" /></b></td></tr>
										<%-- <c:if test="${fn:contains(cdcScope,'xnxgbk') or fn:contains(cdcScope,'all')}">
											<tr onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=gwzsbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);"><td>高温中暑报卡</td><td><b><c:out value="${toData.gwzsbkCount}" /></b></td></tr>
										</c:if> --%>
									</c:if>
								</c:if>
							</c:forEach>
						</table>
						<div class="dropDown_note">备注：点击记录可对卡片进行审核</div>
					</div>
				</li>
				</c:if>
				<%-- <c:if
					test="${fn:contains(cdcScope,'crbbk') or fn:contains(cdcScope,'all')}">
					<li
						onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=crbbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);">
						<h3>传染病报卡</h3>
						<h2>
							<c:out value="${toData.crbbkCount}" />
						</h2>
					</li>
				</c:if>
				<c:if
					test="${fn:contains(cdcScope,'syjcbk') or fn:contains(cdcScope,'all')}">
					<li
						onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=syjcbk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);">
						<h3>食源报卡</h3>
						<h2>
							<c:out value="${toData.syxbkCount}" />
						</h2>
					</li>
				</c:if>
				<c:if
					test="${fn:contains(cdcScope,'sybk') or fn:contains(cdcScope,'all')}">
					<li
						onclick="parent.menuInfo.clickMenu('临床报卡审核','/cdc/f_view/cardsAdmin.shtml?cardType=sybk&queryStartDate=${toStartDate}&queryEndDate=${toEndDate}' ,true);">
						<h3>死因报卡</h3>
						<h2>
							<c:out value="${toData.sybkCount}" />
						</h2>
					</li>
				</c:if> --%>
				<li class="li_border"></li>
				<li
					onclick="parent.menuInfo.clickMenu('职业暴露登记','/bl002Sjdj/f_view/index.shtml?startDate=${toStartDate}&endDate=${toEndDate}&sjState=1',true);">
					<h3>暴露上报</h3>
					<h2>
						<c:out value="${toData.blsbCount}" />
					</h2>
				</li>
				<li onclick="parent.menuInfo.clickMenu('职业暴露登记','/bl002Sjdj/f_view/index.shtml',true);">
					<h3>暴露复查</h3>
					<h2>
						<c:out value="${toData.blfcCount}" />
					</h2>
				</li>
				<c:if test="${cgpg=='0'}">
					<li
						onclick="parent.menuInfo.clickMenu('插管未评估','/gm004Jcmx/f_view/gm004JcmxIndex.shtml?startDate=${toStartDate}&endDate=${toEndDate}&sjState=1',true);">
						<h3>插管未评估</h3>
						<h2>
							<c:out value="${toData.cgCount}" />
						</h2>
					</li>
				</c:if>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="mod_define">
			<h4>
				<a href="javascript:void(0);"
					onclick="parent.menuInfo.clickMenu('感染统计','/report/f_view/fineReportGr.shtml?tab=4&startDate=${startDateYear}&endDate=${endDateYear}',true);">感染部位构成</a><span
					class="ml10"><c:out value="${year}" />年</span>
			</h4>
			<div class="Annular_chart" id="id_infection_chart"></div>
		</div>
		<div class="mod_define">
			<h4>
				<a href="javascript:void(0);"
					onclick="parent.menuInfo.clickMenu('细菌监测统计','/report/f_view/fineReportXj.shtml?tab=4&startDate=${startDateYear}&endDate=${endDateYear}&dateType=1',true);">送检标本构成</a><span
					class="ml10"><c:out value="${year}" />年</span>
			</h4>
			<div class="Annular_chart" id="id_samples_chart"></div>
		</div>
		<div class="mod_define">
			<h4>
				<a href="javascript:void(0);"
					onclick="parent.menuInfo.clickMenu('检出菌搜索','/xn011Dclymx/f_view/index.shtml?startDate=${startDateYear}&endDate=${endDateYear}',true);">MDRO构成</a><span
					class="ml10"><c:out value="${year}" />年</span>
			</h4>
			<div class="Annular_chart" id="id_focusbacteria_chart"></div>
		</div>
		<div class="mod_define">
			<h4>
				职业暴露人员构成<span class="ml10"><c:out value="${year}" />年</span>
			</h4>
			<div class="Annular_chart" id="id_exposure_chart"></div>
		</div>
		<div class="mod_define mod_cont">
			<h4>
				<a href="javascript:void(0);"
					onclick="parent.menuInfo.clickMenu('全院三管监测','/gm003Ybsj/f_view/jcList.shtml?dayDate=${startDate}',true);">发热趋势</a><span
					class="ml10"><a href="#" onclick="brokenChart.report('id_fever_chart', '人','-6');">近7天</a>&nbsp;&nbsp;&nbsp;<a href="#" onclick="brokenChart.report('id_fever_chart', '人','-29');">近30天</a></span>
			</h4>
			<div class="Trend_chart" id="id_fever_chart"></div>
		</div>
		<div class="mod_define mod_cont">
			<h4>
				<a href="javascript:void(0);"
					onclick="parent.menuInfo.clickMenu('感染病例查询','/bk001Sbk/f_view/toInfectionsQuery.shtml?queryStartDate=${startDateWeek}&queryEndDate=${toEndDate}&specDescribes=MRSA&infectDiagnId=BSI',true);">MRSA血流感染</a><span
					class="ml10">近7天</span>
			</h4>
			<div class="Trend_chart" id="id_mrsa_chart"></div>
		</div>
		<div class="mod_define mod_cont">
			<h4>
				<a href="javascript:void(0);"
					onclick="parent.menuInfo.clickMenu('细菌监测统计','/report/f_view/fineReportXj.shtml?tab=2&startDate=${startDateWeek}&endDate=${toEndDate}',true);">多耐菌检出率趋势</a><span
					class="ml10">近7天</span>
			</h4>
			<div class="Trend_chart" id="id_more_bacteria_chart"></div>
		</div>
		<div class="mod_define mod_cont">
			<h4>
				<a href="javascript:void(0);"
					onclick="parent.menuInfo.clickMenu('感染统计','/report/f_view/fineReportGr.shtml?startDate=${startDateMonth}&endDate=${endDateMonth}',true);">院感发病率趋势</a><span
					class="ml10">近12个月</span>
			</h4>
			<div class="Trend_chart" id="id_incidence_chart"></div>
		</div>
		<c:if test="${kfjc eq 1}">
		<div class="mod_define mod_cont">
			<h4>
				<a href="javascript:void(0);"
					onclick="parent.menuInfo.clickMenu('全院三管监测','/gm003Ybsj/f_view/jcList.shtml?dayDate=${startDate}',true);">咳嗽趋势</a><span
					class="ml10">近7天</span>
			</h4>
			<div class="Trend_chart" id="id_more_ks_chart"></div>
		</div>
		<div class="mod_define mod_cont">
			<h4>
				<a href="javascript:void(0);"
					onclick="parent.menuInfo.clickMenu('全院三管监测','/gm003Ybsj/f_view/jcList.shtml?dayDate=${startDate}',true);">腹泻趋势</a><span
					class="ml10">近7个天</span>
			</h4>
			<div class="Trend_chart" id="id_fx_chart"></div>
		</div>
		</c:if>
	</div>
	<script type="text/javascript">
		var pieChart = {
			loadedMap : {},
			urlMap : {
				'id_samples_chart' : '/xn011Dclymx/f_json/findMainSamples.shtml',
				'id_exposure_chart' : '/bl002Sjdj/f_json/findMainExposure.shtml',
				'id_focusbacteria_chart' : '/xn011Dclymx/f_json/findMainFocusBacteria.shtml',
				'id_infection_chart' : '/bk002Grzd/f_json/findMainInfectionParts.shtml'
			},
			report : function(panelId, dataRange, unitName) {
				var myChart = pieChart.loadedMap[panelId];
				if (dataRange == "undefined") {
					dataRange = 'month';
				}
				var options = {
					panelId : panelId,
					url : webroot + pieChart.urlMap[panelId],
					data : {
						dataRange : dataRange
					}
				};

				_report
						.loadByUrlAndQueryParamsToChart(
								options,
								function(data) {
									/**环形图**/
									try {
										if (myChart && myChart.dispose) {
											myChart.dispose();
											myChart = null;
											_report
													.removeChart(options.panelId);
										}
									} catch (e) {
										console.error(e.name + ": "
														+ e.message);
									}
									$('#' + options.panelId).css('filter', '');
									$('#' + options.panelId).css(
											'background-color', '');
									$('#' + options.panelId).mask('加载中..');
									myChart = echarts.init(document
											.getElementById(options.panelId));
									var legendData = new Array();
									$.each(data, function(index, value) {
										if (index < 5) {
											legendData.push(value.name);
										}
									});
									if (panelId == 'id_infection_chart') {
										var code = '';
										myChart.on('click',function(param) {
											 for(var i=0;i<data.length;i++){
												if(param.name==data[i].name){
													code=data[i].code;
												}
											 }
															parent.menuInfo.clickMenu(
																			'感染统计',
																			'/report/f_view/fineReportGr.shtml?tab=4&startDate=${startDateYear}&endDate=${endDateYear}&code='
																					+ code+"&name="+param.name,
																			true);
														});
									}
									if (panelId == 'id_samples_chart') {
										var code = '';
										myChart.on('click',function(param) {
											 for(var i=0;i<data.length;i++){
												if(param.name==data[i].name){
													code=data[i].code;
												}
											 }
											 var url ='';
											 if(code){
												 url = '/report/f_view/fineReportXj.shtml?tab=4&startDate=${startDateYear}&endDate=${endDateYear}&dateType=1&ownership=hospital&code='
														+ code+'&name='+param.name;
											 }else{
												 url = '/report/f_view/fineReportXj.shtml?tab=4&startDate=${startDateYear}&endDate=${endDateYear}&dateType=1&ownership=hospital&name='+param.name;
											 }
											 parent.menuInfo.clickMenu('标本统计',url,
																			true);
										});
									}
									if (panelId == 'id_focusbacteria_chart') {
										var code = '';
										myChart.on('click',function(param) {
											 for(var i=0;i<data.length;i++){
												if(param.name==data[i].name){
													code=data[i].code;
												}
											 }
											 parent.menuInfo.clickMenu('检出菌统计','/xn011Dclymx/f_view/index.shtml?startDate=${startDateYear}&endDate=${endDateYear}&ownership=hospital&code='
																					+ code+"&name="+encodeURIComponent(encodeURIComponent(param.name)),
																			true);
														});
									}
									//职业暴露
									if (panelId == 'id_exposure_chart') {
										var code = '';
										myChart.on('click',function(param) {
											 for(var i=0;i<data.length;i++){
												if(param.name==data[i].name){
													code=data[i].code;
												}
											 }
											parent.menuInfo.clickMenu( '职业暴露登记','/bl002Sjdj/f_view/index.shtml?ownership=hospital&djGw='+code+'&dateType=2&sjState=1,2,3,4,5,6,7,8,9&startDate=${startDateYear}&endDate=${endDateYear}', true);
										});
									}
									
									
									var option = {
										title : {
											subtext : unitName==''?'':'单位：' + unitName,
											x : 'left',
											y : 'bottom'
										},
										tooltip : {
											trigger : 'item',
											formatter : "{b} : {c}</br>({d}%)"
										},
										legend : {
											orient : 'vertical',
											x : 'right',
											y : 'bottom',
											data : legendData
										},
										series : [ {
											type : 'pie',
											center : [ '35%', '50%' ],
											itemStyle : {
												normal : {
													label : {
														show : false
													},
													labelLine : {
														show : false
													}
												}
											},
											radius : [ '40%', '60%' ],
											data : data
										} ]
									};
									myChart.setOption(option);
									_report.addChart(options.panelId, myChart);
									pieChart.loadedMap[panelId] = myChart;
								}, function() {
									$('#' + options.panelId).unmask();
								});
			}
		};

		var brokenChart = {
			loadedMap : {},
			urlMap : {
				'id_fever_chart' : '/st006Twxx/f_json/findMainFever.shtml',
				'id_more_ks_chart' : '/gm003Ybsj/f_json/findMainKs.shtml',
				'id_fx_chart' : '/gm003Ybsj/f_json/findMainFx.shtml',
				'id_more_bacteria_chart' : '/xn011Dclymx/f_json/findMainMoreResistant.shtml',
				'id_incidence_chart' : '/bk002Grzd/f_json/findMainIncidence.shtml',
				'id_mrsa_chart' : '/bk002Grzd/f_json/findBloodInfections.shtml'
			},
			report : function(panelId, unitName,time) {
				var myChart = brokenChart.loadedMap[panelId];
				var options = {
					panelId : panelId,
					url : webroot + brokenChart.urlMap[panelId]+"?dataRange="+time
				};

				_report.loadByUrlAndQueryParamsToChart(options, function(data) {
					/**折线图**/
					try {
						if (myChart && myChart.dispose) {
							myChart.dispose();
							myChart = null;
							_report.removeChart(options.panelId);
						}
					} catch (e) {
						//console.error(e.name + ": " + e.message);
					}
					$('#' + options.panelId).css('filter', '');
					$('#' + options.panelId).css('background-color', '');
					$('#' + options.panelId).mask('加载中..');
					myChart = echarts.init(document
							.getElementById(options.panelId));
					var legendData = data.legendData;
					var xAxisData = data.xAxisData;
					var series = new Array();
					for (var i = 0; i < data.series.length; i++) {
						var serieData = data.series[i];
						var serie = new Object();
						serie.name = legendData[i];
						serie.type = 'line';
						serie.symbol = 'emptyCircle';
						serie.showAllSymbol = true;
						serie.data = serieData;
						series.push(serie);
					}
					var option = {
						title : {
							subtext : unitName==''?'':'单位：' + unitName,
							x : 'left',
							y : 'bottom'
						},
						legend : {
							data : legendData
						},
						tooltip : {},
						xAxis : [ {
							type : 'category',
							splitNumber : xAxisData.length,
							boundaryGap : false,
							data : xAxisData
						} ],
						yAxis : [ {
							type : 'value',
							splitNumber : 4,
							axisLabel : {
								formatter : data.formatter
							}
						} ],
						series : series
					};
					myChart.setOption(option);
					_report.addChart(options.panelId, myChart);
					brokenChart.loadedMap[panelId] = myChart;
				}, function() {
					$('#' + options.panelId).unmask();
				});
			}
		};

		$(document).ready(function() {
			//感染部位构成
			pieChart.report('id_infection_chart', 'year', '例');
			//送检标本构成
			pieChart.report('id_samples_chart', 'year', '份');
			//重点检出菌构成
			pieChart.report('id_focusbacteria_chart', 'year', '');
			//职业暴露人员构成
			pieChart.report('id_exposure_chart', 'year', '人');
			//发热趋势
			brokenChart.report('id_fever_chart', '人','-6');
			//咳嗽趋势
			brokenChart.report('id_more_ks_chart', '人','-6');
			//腹泻趋势
			brokenChart.report('id_fx_chart', '人','-6');
			//MRSA相关血流感染
			brokenChart.report('id_mrsa_chart', '例');
			//多耐菌检出率趋势
			brokenChart.report('id_more_bacteria_chart', '');
			//发病率趋势
			brokenChart.report('id_incidence_chart', '');
			
		});
	</script>
</body>
</html>
