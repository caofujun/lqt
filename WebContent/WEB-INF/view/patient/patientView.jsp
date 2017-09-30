<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>患者综合视图</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<style>
.stand_table tr td{height:26px;}
</style>
</head>

<body class="easyui-layout" style="overflow: auto;">
	<div id="id_patientView" class="comprehensive_img">
		<div class="comprehensive_img_head">
			<div class="comprehensive_img_switch">
				<input type="button" onclick='patientView.backMonth()' value="上一页" ${previous}/>
				<input type="button" value="下一页" onclick="patientView.nextMonth()" ${next} />
			</div>
			<div class="comprehensive_img_ico">
				<span class="ico_fr">发热<c:set var="count"
						value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.twValuesCount}"></c:set>
					</c:forEach>(${count})天</span><span class="ico_fxj">呼吸机<c:set var="count" value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.hxjOrderAtCount}"></c:set>
					</c:forEach>(${count})天</span><span class="ico_zxjmcg">中心静脉插管<c:set
						var="count" value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.jmcgOrderAtCount}"></c:set>
					</c:forEach>(${count})天</span><span class="ico_bndcg">泌尿道插管<c:set var="count"
						value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.mndcgOrderAtCount}"></c:set>
					</c:forEach>(${count})天</span><span class="ico_xcg">血常规<c:set var="count"
						value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.xcgSubmiAtCount}"></c:set>
					</c:forEach>(${count})次</span><span class="ico_ncg">尿常规<c:set var="count"
						value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.ncgSubmiAtCount}"></c:set>
					</c:forEach>(${count})次</span><span class="ico_qtcg">其他常规<c:set var="count"
						value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.qtcgSubmiAtCount}"></c:set>
					</c:forEach>(${count})次</span><span class="ico_glbs">隔离标识<c:set var="count"
						value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.glbsOrderAtCount}"></c:set>
					</c:forEach>(${count})次</span><span class="ico_yj">有菌<c:set var="count"
						value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.yjSubmiAtCount}"></c:set>
					</c:forEach>(${count})次</span><span class="ico_sx">手术<c:set var="count"
						value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.operAtCount}"></c:set>
					</c:forEach>(${count})例</span><span class="ico_jgsy">降钙素原<c:set var="count"
						value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.jgsySubmiAtCount}"></c:set>
					</c:forEach>(${count})次</span><span class="ico_cfydb">C反应蛋白<c:set var="count"
						value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.cfydbSubmiAtCount}"></c:set>
					</c:forEach>(${count})次</span><span class="ico_cmcfydb">超敏C反应蛋白<c:set
						var="count" value="0"></c:set>
					<c:forEach items="${patientViewAllList}" var="item">
						<c:set var="count" value="${count+item.cmcfydbSubmiAtCount}"></c:set>
					</c:forEach>(${count})次</span>   
			</div>
			<div class="clear"></div>
		</div>
		<table class="stand_table" style="min-width: 1100px;">
			<thead>
				<tr>
					<th width="95"></th>
					<c:forEach items="${patientViewList}" var="patientView" varStatus="status">
						<th class="ico_fxj" title="入院第<${patientView.day}>天，日期：<fmt:formatDate value="${patientView.dateTime}" pattern="yyyy-MM-dd" />">${patientView.day}</th>
					</c:forEach>

				</tr>
			</thead>
			<tbody>
				<tr>
					<th>发热</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.twValues!=null}">
								<td><a class="ico_fr" title="<fmt:formatDate value="${patientView.dateTime}" pattern="yyyy-MM-dd" />：${patientView.twValues}℃"></a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>呼吸机</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.hxjOrderAt!=null}">
								<td><a class="ico_fxj" title="<fmt:formatDate value="${patientView.hxjOrderAt}" pattern="yyyy-MM-dd" />：${patientView.hxjOrderName}"></a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>中心静脉插管</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.jmcgOrderAt!=null}">
								<td><a class="ico_zxjmcg"
									title="<fmt:formatDate value="${patientView.jmcgOrderAt}" pattern="yyyy-MM-dd" />：${patientView.jmcgOrderName}"></a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>泌尿道插管</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.mndcgOrderAt!=null}">
								<td><a class="ico_bndcg"
									title="<fmt:formatDate value="${patientView.mndcgOrderAt}" pattern="yyyy-MM-dd" />：${patientView.mndcgOrderName}"></a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>血常规</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.xcgSubmiAt!=null}">
								<td class="result_click" submitAt="${patientView.xcgSubmiAt}" classCode="101" testOrderNo="${patientView.xcgTestOrderNo}" day="${patientView.day}" type="xcg" >
									<div class="popup">
										<a class="ico_xcg point" id="ico_xcg_point_${patientView.day}" title="${patientView.xcgSubmiAt}：${patientView.xcgItemName}"></a>
										<div id="id_xcg_${patientView.day}" class="popup_w" style="display: none;">
											<div id="tb_div_xcg_${patientView.day}" class="popup_w_h"><span class="ml10">送检日期：</span><span class="ml10">检出日期：</span><span class="ml10">送检单号：</span></div>
											<div id="id_div_xcg_${patientView.day}"></div>
										</div>
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>尿常规</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.ncgSubmiAt!=null}">
								<td class="result_click" submitAt="${patientView.ncgSubmiAt}" classCode="102" testOrderNo="${patientView.ncgTestOrderNo}" day="${patientView.day}" type="ncg" >
									<div class="popup">
										<a class="ico_ncg point" id="ico_ncg_point_${patientView.day}" title="${patientView.ncgSubmiAt}：${patientView.ncgItemName}"></a>
										<div id="id_ncg_${patientView.day}" class="popup_w" style="display: none;">
											<div id="tb_div_ncg_${patientView.day}" class="popup_w_h"><span class="mr10">送检日期：</span><span class="mr10">检出日期：</span><span class="mr10">送检单号：</span></div>
											<div id="id_div_ncg_${patientView.day}"></div>
										</div>
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>其他常规</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.qtcgSubmiAt!=null}">
								<td class="result_click" submitAt="${patientView.qtcgSubmiAt}" classCode="103" testOrderNo="${patientView.qtcgTestOrderNo}" day="${patientView.day}" type="qtcg" >
									<div class="popup">
										<a class="ico_qtcg point" id="ico_qtcg_point_${patientView.day}" title="${patientView.qtcgSubmiAt}：${patientView.qtcgItemName}"></a>
										<div id="id_qtcg_${patientView.day}" class="popup_w" style="display: none;">
											<div id="tb_div_qtcg_${patientView.day}" class="popup_w_h"><span class="mr10">送检日期：</span><span class="mr10">检出日期：</span><span class="mr10">送检单号：</span></div>
											<div id="id_div_qtcg_${patientView.day}"></div>
										</div>
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>隔离标识</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.glbsOrderAt!=null}">
								<td classCode="108"><a class="ico_glbs" title="<fmt:formatDate value="${patientView.glbsOrderAt}" pattern="yyyy-MM-dd" />：${patientView.glbsOrderName}"></a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>有菌</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.yjSubmiAt!=null}">
								<td class="result_click" submitAt="${patientView.yjSubmiAt}" testOrderNo="${patientView.yjTestOrderNo}" day="${patientView.day}" type="yj" >
									<div class="popup">
										<a class="ico_yj point" id="ico_yj_point_${patientView.day}" title="${patientView.yjSubmiAt}：${patientView.yjItemName}"></a>
										<div id="id_yj_${patientView.day}" class="popup_w" style="display: none;">
											<div id="id_div_yj_${patientView.day}"></div>
										</div>
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>手术</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.operAt!=null}">
								<td><a class="ico_sx" title="<fmt:formatDate value="${patientView.operAt}" pattern="yyyy-MM-dd" />：${patientView.operName}"></a></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>降钙素原</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.jgsySubmiAt!=null}">
								<td class="result_click" submitAt="${patientView.jgsySubmiAt}" classCode="104" testOrderNo="${patientView.jgsyTestOrderNo}" day="${patientView.day}" type="jgsy" >
									<div class="popup">
										<a class="ico_jgsy point" id="ico_jgsy_point_${patientView.day}" title="${patientView.jgsySubmiAt}：${patientView.jgsyItemName}"></a>
										<div id="id_jgsy_${patientView.day}" class="popup_w" style="display: none;">
											<div id="tb_div_jgsy_${patientView.day}" class="popup_w_h"><span class="mr10">送检日期：</span><span class="mr10">检出日期：</span><span class="mr10">送检单号：</span></div>
											<div id="id_div_jgsy_${patientView.day}"></div>
										</div>
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>C反应蛋白</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.cfydbSubmiAt!=null}">
								<td class="result_click" submitAt="${patientView.cfydbSubmiAt}" classCode="107"  testOrderNo="${patientView.cfydbTestOrderNo}" day="${patientView.day}" type="cfydb" >
									<div class="popup">
										<a class="ico_cfydb point" id="ico_cfydb_point_${patientView.day}" title="${patientView.cfydbSubmiAt}：${patientView.cfydbItemName}"></a>
										<div id="id_cfydb_${patientView.day}" class="popup_w" style="display: none;">
											<div id="tb_div_cfydb_${patientView.day}" class="popup_w_h"><span class="mr10">送检日期：</span><span class="mr10">检出日期：</span><span class="mr10">送检单号：</span></div>
											<div id="id_div_cfydb_${patientView.day}"></div>
										</div>
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<tr>
					<th>超敏C反应蛋白</th>
					<c:forEach items="${patientViewList}" var="patientView">
						<c:choose>
							<c:when test="${patientView.cmcfydbSubmiAt!=null}">
								<td class="result_click" submitAt="${patientView.cmcfydbSubmiAt}" classCode="106" testOrderNo="${patientView.cmcfydbTestOrderNo}" day="${patientView.day}" type="cmcfydb" >
									<div class="popup">
										<a class="ico_cmcfydb point" id="ico_cmcfydb_point_${patientView.day}" title="${patientView.cmcfydbSubmiAt}：${patientView.cmcfydbItemName}"></a>
										<div id="id_cmcfydb_${patientView.day}" class="popup_w" style="display: none;">
											<div id="tb_div_cmcfydb_${patientView.day}" class="popup_w_h"><span class="mr10">送检日期：</span><span class="mr10">检出日期：</span><span class="mr10">送检单号：</span></div>
											<div id="id_div_cmcfydb_${patientView.day}"></div>
										</div>
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
			</tbody>
		</table>
	</div>
<script type="text/javascript">
	$(function () {    
	    //点击空白处隐藏弹出层，下面分别为滑动和淡出效果。    
	    $(document).click(function (event) {
	    	if (patientView.popupId != '') {
				$('#' + patientView.popupId).fadeOut();
				patientView.popupId = '';
			}
			if (patientView.pointId != '') {
				$('#' + patientView.pointId).removeClass("hover");
				patientView.pointId = '';
			}
	    });     
	    
	    $('.result_click').click(function (event) {   
	        //取消事件冒泡    
	        event.stopPropagation();
	        var submitAt = $(this).attr("submitAt");
	        var testOrderNo = $(this).attr("testOrderNo");
	        var day = $(this).attr("day");
	        var type = $(this).attr("type");
	        var code = $(this).attr("classCode");
	        patientView.setDivShowOffset(this, 500, 260, 31, 'id_' + type + '_' + day);
	        if (type == 'yj') {
	        	patientView.bacteriaResult(submitAt, testOrderNo, day, type);
	        } else {
	        	patientView.checkResult(submitAt, testOrderNo, day, type,code);
	        }
	    });
	});
	
	var previousRow;
	var c = "";
	function OnMouseOut(obj) {
		obj.style.border = c;
		if (obj.titleA)
			obj.titleA.className = obj.titleA.prevClass;
		previousRow = null;
	}
	function OnMouseOver(obj) {
		if (obj == previousRow) return;
		c = obj.style.display;
		//obj.find(".popup_w").css({"display":"block"});
		obj.style.display = 'solid 2px #38a8fd';
		if (!obj.titleA) {
			obj.titleA = obj.getElementsByTagName("a")[0];
			obj.titleA.prevClass = obj.titleA.className;
		}
		obj.titleA.className = "titlehover";
		previousRow = obj;
	}
	
	var patientView = {
		popupId : '',
		pointId : '',
		startDate : '${startDate}',
		endDate : '${endDate}',
		resultHigh : '${resultHigh}'.split(','),
		resultLow : '${resultLow}'.split(','),
		mdr : 'R,耐药'.split(','),
		checkResult : function(resultDate,testOrderNo,day,type,code) {
			var divObj = $('#id_' + type + '_' + day);
// 			if ("1" != divObj.attr("data")) {
				divObj.attr("data", "1");
				$.ajax({
	                url: '${webroot}/st011Syjgb/f_json/getCheckResultTitle.shtml',
	                type: 'post',
	                data: { zyid: '${param.zyid}', testOrderNo: testOrderNo },
	                dataType: 'json',
	                success : function(json) {
// 	                	$('#tb_div_' + type + '_' + day).html('<span class="ml10"><b>送检：</b>' + json.submiAt + '</span><span class="ml10"><b>检出：</b>' + json.checkOutAt + '</span><span class="ml10"><b>送检单号：</b>' + json.testOrderNo + '</span>');
					Comm.dialogGlobal({
				        	url:"${webroot}/st011Syjgb/f_view/toSt011SyjgbList.shtml?testOrderNo="+testOrderNo+"&zyid=${param.zyid}"+"&submiAt="+json.submiAt+"&checkOutAt="+json.checkOutAt+"&classCode="+code,
				            title: '检验结果变化趋势',
				            width:800,
				            height:500,
				            type:"iframe",
				            parent:this
				     });
					}
	    		});
				
// 				$('#id_div_' + type + '_' + day).load("${webroot}/st011Syjgb/f_view/toSt011SyjgbList.shtml?testOrderNo="+testOrderNo+"&zyid="+'${param.zyid}');
// 				$('#id_div_' + type + '_' + day).datagrid({
// 		            fit: true,
// 		            nowrap: true,
// 		            autoRowHeight: true,
// 		            striped: true,
// 		            fitColumns: false,
// 		            collapsible:true,
// 		            url:'${webroot}/st011Syjgb/f_view/toSt011SyjgbList.shtml',
// 		            queryParams: {
// 		            	'zyid': '${param.zyid}',
// 		            	'testOrderNo':testOrderNo
// 		            },
// 		            remoteSort: false,
// 		            singleSelect: true,
// 			        columns:[
// 				       	[
// 							{field:'antiCode',title:'编号',sortable:true,width:40},
// 				            {field:'antiName',title:'项目名称',sortable:true,width:120},
// 				            {field:'yaominResult',title:'药敏结果 ',sortable:true,width:56},
// 				            {field:'testResult',title:'试验结果',align:'right',sortable:true,width:56},
// 				            {field:'unit',title:'单位',align:'left',sortable:true,width:40},
// 				            {field:'referRange',title:'参考范围',sortable:true,width:60},
// 				            {field:'remark',title:'结果',sortable:true,width:40,
// 								formatter:function(value,rec){
// 									var remarkStr = '';
// 									if (rec.remark) {
// 										if ($.inArray(rec.remark, patientView.resultHigh) != -1) { remarkStr = '↑';} else if ($.inArray(rec.remark, patientView.resultLow) != -1) {remarkStr = '↓';}
// 									}
// 									return [remarkStr].join('');
// 							    }
// 							}
				            
// 				        ]
// 			        ],
// 			        rownumbers:true,
// 			        rowStyler:function(rowIndex,rowData){
// 			        	if (rowData && rowData.remark) {
// 			        		if ($.inArray(rowData.remark, patientView.resultHigh) != -1) {
// 			        			return 'color:red;';
// 			        		} else if ($.inArray(rowData.remark, patientView.resultLow) != -1) {
// 			        			return 'color:blue;';
// 			        		}
// 			        	} 
// 		            },
// 		            toolbar:'#tb_div_' + type + '_' + day
// 			    });
// 			}
// 			this.setDivHidden(divObj, day, type);
		},
		//设置div隐藏
		setDivHidden : function (divObj,day,type) {
			if (divObj.is(":hidden")) {
				if (this.popupId != '') {
					$('#' + this.popupId).hide();
				}
				if (this.pointId != '') {
					$('#' + this.pointId).removeClass("hover");
				}
				this.popupId = 'id_' + type + '_' + day;
				this.pointId = 'ico_' + type + '_point_' + day;
				divObj.fadeIn();
				$('#' + this.pointId).addClass("hover");
			}
		},
		//有菌
		bacteriaResult : function(resultDate,testOrderNo,day,type) {
			var divObj = $('#id_' + type + '_' + day);
			if ("1" != divObj.attr("data")) {
				divObj.attr("data", "1");
				$('#id_div_' + type + '_' + day).datagrid({
		            fit: true,
		            nowrap: true,
		            autoRowHeight: true,
		            striped: true,
		            fitColumns: false,
		            collapsible:true,
		            url:'${webroot}/st009Sjbb/f_json/findBacteriaList.shtml?zyid=${param.zyid}&submiAt=' + resultDate,   
		            remoteSort: false,
		            singleSelect: true,
			        columns:[
				       	[
				            {field:'submiAt',title:'送检日期',align:'center',sortable:true,width:76},
				            {field:'itemName',title:'标本 ',sortable:true,width:70},
				            {field:'checkOutAt',title:'检出日期',align:'center',sortable:true,width:76},
				            {field:'pathoName',title:'检出结果',sortable:true,width:70},
				            {field:'resPropName',title:'多耐结果',align:'center',sortable:true,width:60},
				            {field:'specDescribes',title:'特殊耐药',sortable:true,width:60}
				            
				        ]
			        ],
			        rownumbers:true
			    });
			}
			this.setDivHidden(divObj, day, type);
		},
		setDivShowOffset : function (ele, sDivWidth, sDivHeight, tdiv, showDivId) {
			var right = getElementMargin.right(ele);
			var bot = getElementMargin.bot(ele);
			if ((right - sDivWidth - tdiv) < 0) { //右边宽度不够，向左
				//设置left
				$('#' + showDivId).css({ left: - (sDivWidth + 6) });
			}
			if ((bot - sDivHeight) < 0) { //下边高度不够，向上
				//设置top
				$('#' + showDivId).css({ top: - (sDivHeight - 25) });
			}
		},
		addDate : function(dd, dadd) {
			var strs = dd.split('-');
			var date = new Date(Number(strs[0]), Number(strs[1]) - 1, Number(strs[2]));
			var time = date.getTime();
			time = time + (dadd * 24 * 60 * 60 * 1000);
			return this.format(new Date(time), "yyyy-MM-dd");
		},
		format : function(date, fmt) {
			var o = {
				"M+" : date.getMonth() + 1, //月份 
				"d+" : date.getDate(), //日 
				"h+" : date.getHours(), //小时 
				"m+" : date.getMinutes(), //分 
				"s+" : date.getSeconds(), //秒 
				"q+" : Math.floor((date.getMonth() + 3) / 3), //季度 
				"S" : date.getMilliseconds()
			//毫秒 
			};
			if (/(y+)/.test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "") .substr(4 - RegExp.$1.length));
			}
			for ( var k in o) {
				if (new RegExp("(" + k + ")").test(fmt)) {
					fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]) .substr(("" + o[k]).length)));
				}
			}
			return fmt;
		},
		nextMonth : function() {
			location.href = "${webroot}/st003Cryxxb/f_view/view.shtml?zyid=${param.zyid}&startDate=" + this.addDate(this.startDate, 30) + "&endDate=" + this.addDate(this.endDate, 30);
		},
		backMonth : function() {
			location.href = "${webroot}/st003Cryxxb/f_view/view.shtml?zyid=${param.zyid}&startDate=" + this.addDate(this.startDate, -30) + "&endDate=" + this.addDate(this.endDate, -30);
		}
	};
	
	var getElementMargin = {
		left : function(ele) {
			var actualLeft = ele.offsetLeft;
			var current = ele.offsetParent;
			while (current !== null) {
				actualLeft += current.offsetLeft;
				current = current.offsetParent;
			}
			//一个准确获取网页客户区的宽高、滚动条宽高、滚动条Left和Top的代码
			var elementScrollLeft = 0;
			if (document.compatMode == "BackCompat") {
				elementScrollLeft = document.body.scrollLeft;
			} else {
				elementScrollLeft = document.documentElement.scrollLeft;
			}
			return actualLeft - elementScrollLeft;
		},

		top : function(ele) {
			var actualTop = ele.offsetTop;
			var current = ele.offsetParent;
			while (current !== null) {
				actualTop += current.offsetTop;
				current = current.offsetParent;
			}
			var elementScrollTop = 0;
			if (document.compatMode == "BackCompat") {
				elementScrollTop = document.body.scrollTop;
			} else {
				elementScrollTop = document.documentElement.scrollTop;
			}
			return actualTop - elementScrollTop;
		},

		bot : function(ele) {
			var bodyHeight = document.body.scrollHeight;
			if ($('#id_patientView').height() > bodyHeight) {
				bodyHeight = $('#id_patientView').height();
			}
			var actualBottom = bodyHeight - this.top(ele);//浏览器当前的高度减去当前元素的窗口位置，注意是相对的位置，不包括滚动条里的高度
			return actualBottom;
		},
		
		right : function(ele) {
			var bodyWidth = document.body.scrollWidth;
			if ($('#id_patientView').width() > bodyWidth) {
				bodyWidth = $('#id_patientView').width();
			}
			var actualRight = bodyWidth - this.left(ele);
			return actualRight;
		}
	}
</script>
</body>
</html>
