<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<div id="tableTitle" class="tableTitle" style="position:absolute;z-index:30;margin-top:-30px;">
	<!-- <span style="position: absolute;padding: 5px;color: #c16b6b;"></span> -->
	<table class="infos" style="background-color: #efefef; color:#333; width:100%;"  border="0" cellpadding="0" cellspacing="0">
		<tr style="line-height: 28px;">
			<th style="width:9%;text-align: left;padding-left: 10px;"><input type="checkbox" id="checkall" onclick="checkAll(this);" /></th>
			<th style="width:1%;"></th>
			<th style="width:40%;">报卡信息</th>
			<th style="width:1%;"></th>
			<th style="width:9%;">状态</th>
			<th style="width:1%"></th>
			<th style="width:13%">上报信息</th>
			<th style="width:1%"></th>
			<th style="width:9%">操作</th>
			<th style="width:1%"></th>
			<th style="">操作信息</th>
		</tr>		
	</table>
</div>
<div style="margin-top:30px;">
<c:forEach items="${Cards}" var="cs">
	<div class="tables">
		<table class="infos" cellpadding="0" cellspacing="0" style="width: 100%;">
			<tr>
				<td colspan="11" style="background-color: #fff;border-top: 1px solid #ddd;background-color: #eee;">
					<span class="ml10"><input type="checkbox" name="batchOpt" /></span>
					<span>卡片编号：${cs.cardid }</span>
					<input type="hidden" class="cardStates" value="${cs.flag}" />
					<input type="hidden" class="mid" value="${cs.masterid}" />
					<span class="toolBar">
						<c:if test="${cs.isprint==1 && isShowPrintTag==1}"><span class="mark">已打印</span></c:if>
						<a class="btn_icon" href="javascript:;" onclick="sendMessage('${cs.zyid}');" title="干预消息"><i class="icon iconfont">&#xe63e;</i></a>			
						<a class="btn_icon" title="备注" onclick="writeNote('${cs.masterid}','${cs.notes}');"><i class="icon iconfont">&#xe620;</i></a>
					<c:if test="${cs.flag<=1}">
						<c:choose>
							<c:when test="${cs.isprint==1}">
								<a class="btn_icon" title="已打印" onclick="printSingle('${cs.masterid}');"><i class="icon nisfont">&#xe6f5;</i></a>
							</c:when>
							<c:otherwise>
								<a class="btn_icon" title="打印" onclick="printSingle('${cs.masterid}')"><i class="icon iconfont">&#xe604;</i></a>
							</c:otherwise>
						</c:choose>
					</c:if>
					</span>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;width:9%;">
					<div>
						<c:choose>
							<c:when test="${cs.sexid==2}">
								<span class="img_woman"></span>
							</c:when>
							<c:when test="${cs.sexid==1}">
								<span class="img_man"></span>
							</c:when>
						</c:choose>
					</div>
					<div class="PInfor">
						<span class="pname" style="break-words:break-all;">${cs.patientName}</span>
						<span class="pAge">(${cs.age} ${cs.ageunit})</span>
					</div>
				</td>
				<td class="BothSidesSpace topBottomSpace" style="width:1%;"><div class="borderStyle"></div></td>
				<td style="width:40%;">
					<table class="table_childer" style="width:100%;">
						<tr>
							<td class="rightTextAlign vat" style="width:90px;">直接死因：</td>
							<td>${cs.acause}</td>
						</tr>
						<tr>
							<td class="rightTextAlign vat">根本死因：</td>
							<td>${cs.basecause}</td>
						</tr>
						<tr>
							<td style="width:auto;" class="rightTextAlign vat" >死亡地点：</td>
							<td>${cs.deadzonevalue}</td>
						</tr>
						<tr>
							<td class="rightTextAlign vat" style="width: auto;">死亡日期：</td>
							<td><fmt:formatDate value="${cs.deaddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<td class="rightTextAlign vat">最高诊断单位：</td>
							<td style="width:120px;" >${cs.dorglevelvalue}</td>
						</tr>
						<tr>
							<td class="rightTextAlign vat">最高诊断依据：</td>
							<td>${cs.diagnosebyvalue}</td>
						</tr>
						<tr>
							<td class="rightTextAlign vat">备注：</td>
							<td>${cs.notes}</td>
						</tr>
					</table>
				</td>
				<td class="BothSidesSpace topBottomSpace" style="width:1%;"><div class="borderStyle"></div></td>
				<td style="text-align: center;width: 9%;">
					<c:choose>
						<c:when test="${cs.flag==0}">
							<div class="yellow">未审核</div>
						</c:when>
						<c:when test="${cs.flag==1}">
							<div class="red">已审核</div>
						</c:when>
						<c:when test="${cs.flag==2}">
							<div class="blue">已退卡</div>
						</c:when>
						<c:when test="${cs.flag==3}">
							<div class="gray">已删卡</div>
						</c:when>
					</c:choose>
					<div class="table_a">
						<a href="javascript:void(0);" 
						<c:choose>
							<c:when test="${cs.isemptycard==1}"> title="空卡上报记录，无法查看患者详情！" onclick="showTip('空卡上报记录，无法查看患者详情！')" </c:when>
							<c:otherwise> onclick="showDetail('${cs.zyid}','${cs.mzid}');" </c:otherwise>
						</c:choose> >患者详情</a>
					</div>
					<div class="table_a"><a href="javascript:bkDetail('${cs.zyid}','${cs.mzid}','${cs.masterid}');">报卡详情</a></div>
				</td>
				<td class="BothSidesSpace topBottomSpace" style="width:1%;"><div class="borderStyle"></div></td>
				<td style="text-align: center;width: 13%;">
					<div>${cs.reportdeptname}</div>
					<div>${cs.reportdoctorname}</div>
					<div><fmt:formatDate value="${cs.filldate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				</td>
				<td class="BothSidesSpace topBottomSpace" style="width:1%;"><div class="borderStyle"></div></td>
				<td style="text-align: center;width: 9%;">
					<c:choose>
						<c:when test="${cs.flag==0}">
							<div class="table_btn_blue"><input type="button" class="optsButt" onclick="audit('${cs.masterid}');" value="确认审核"/></div>
							<div class="table_a"><a href="javascript:retreat('${cs.masterid}')">退卡操作</a></div>
							<div class="table_a"><a href="javascript:remove('${cs.masterid}')">删卡操作</a></div>
						</c:when>
						<c:when test="${cs.flag==1}">
							<div class="table_btn_gray"><input type="button" class="optsButt" onclick="cancel('${cs.masterid}','1')" value="取消审核"/></div>
						</c:when>
						<c:when test="${cs.flag==2}">
							<div class="table_btn_gray"><input type="button" class="optsButt" onclick="cancel('${cs.masterid}','2')" value="取消退卡"/></div>
						</c:when>
						<c:when test="${cs.flag==3}">
							<div class="table_btn_gray"><input type="button" class="optsButt" onclick="cancel('${cs.masterid}','3')" value="取消删卡"/></div>
						</c:when>
					</c:choose>
				</td>
				<td class="BothSidesSpace topBottomSpace" style="width:1%;"><div class="borderStyle"></div></td>
				<td style="text-align: center;">
					<div class="topSpace" style="word-break:break-all;">${cs.auditorname}</div>
					<div class="topSpace" style="word-break:break-all;"><fmt:formatDate value="${cs.auditdate}" type="both"/></div>
				</td>				
			</tr>
			
		</table>
	</div>
</c:forEach>
</div>
<script>
 $(function(){
	 var dc = "${fn:length(Cards)}";
	 if(dc>=0){
		 $("#dataCount").html(dc);
	 }
 });
 function bkDetail(zyid,mzid,msid){
	 if(zyid){
		 parent.menuInfo.clickMenu('死因报卡详情','/cdc/f_view/deathReport.shtml?zyid='+zyid+'&masterid='+msid,true);
	 }else if(mzid){
		 parent.menuInfo.clickMenu('死因报卡详情','/cdc/f_view/deathReport.shtml?mzid='+mzid+'&masterid='+msid,true);
	 }
 }
</script>