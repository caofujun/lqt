<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>

<div class="message_main">
	<c:if test="${MsgPage.totalPage<=0}">
		<span style="text-align: center;display: block;margin-top: 80px;color: #c5c2c2;font-size: 20px;">没有消息记录</span>
	</c:if>
	<c:forEach items="${MsgPage.rows}" var="mp">
		<div class="message_li <c:if test="${mp.isRead==1}">read</c:if>" >
			<input class="mid" type="hidden" value="${mp.mid}" />
			<input class="themeId" type="hidden" value="${mp.themeId}" />
			<input class="mzid" type="hidden" value="${mp.mzid}">
			<input class="zyid" type="hidden" value="${mp.zyid}">
			<input class="isRead" type="hidden" value="${mp.isRead}">
			<c:choose>
				<c:when test="${mp.msgType==0}">
					<span class="message_icon_xtxx"></span>
					<span class="message_text" title="">【普通消息】 ${mp.content}</span>
					<span class="message_btn"><a href="javascript:void(0);" onclick="operation.reply('${mp.zyid}','${mp.mzid}','${mp.themeId}','${mp.theme}','${mp.sendUserId}');">回复</a></span>
					<span class="message_date"><fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
				</c:when>
				<c:when test="${mp.msgType==1}">
					<span class="message_icon_jsxx"></span>
					<span class="message_text" title="">【干预消息】  ${mp.content}</span>
					<span class="message_btn"><a href="javascript:void(0);" onclick="operation.reply('${mp.zyid}','${mp.mzid}','${mp.themeId}','${mp.theme}','${mp.sendUserId}');">回复</a></span>
					<span class="message_date"><fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
				</c:when>
				<c:when test="${mp.msgType==2}">
					<span class="message_icon_sbxx"></span>
					<%-- ${fn:replace(fn:replace(mp.content,'<font  color=\'red\'>',''),'</font>','')} --%>
					<span class="message_text" title="${mp.content}">【上报消息】  ${mp.content}</span>
					<span class="message_btn">
						<c:choose>
							<c:when test="${!empty mp.caseId}">
								<a href="javascript:void(0);" onclick="operation.checkBL('${mp.zyid}','${mp.mzid}','${mp.caseId}','${mp.mid}','${mp.themeId}','${mp.isRead}');">查看</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" onclick="mark.markAsRead('${mp.mid}','${mp.themeId}','${mp.isRead}');">知道了</a>
							</c:otherwise>
						</c:choose>
					</span>
					<span class="message_date"><fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
				</c:when>
				<c:when test="${mp.msgType==3}">
					<span class="message_icon_jcjg"></span>
					<span class="message_text" title="">【职业暴露检测】  ${mp.content}</span>
					<span class="message_btn"><a href="javascript:void(0);" title="${mp.content}" onclick="operation.check('zybl','${mp.caseId}','${mp.mid}','${mp.themeId}','${mp.isRead}');">查看</a></span>
					<span class="message_date"><fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
				</c:when>
				<c:when test="${mp.msgType==4}">
					<span class="message_icon_jcjg"></span>
					<span class="message_text" title="">【环境监测结果】  ${mp.content}</span>
					<span class="message_btn"><a href="javascript:void(0);" title="${mp.content}" onclick="operation.check('hjjc','${mp.caseId}','${mp.mid}','${mp.themeId}','${mp.isRead}');">查看</a></span>
					<span class="message_date"><fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
				</c:when>
				<%-- <c:when test="${mp.msgType==5}">
					<span class="message_icon_grxx"></span>
					<span class="message_text" title="">【感染预警】  ${mp.content}</span>
					<span class="message_btn"><a href="javascript:void(0);" onclick="operation.check();">查看</a></span>
					<span class="message_date"><fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
				</c:when> --%>
				<c:when test="${mp.msgType==6}">
					<span class="message_icon_fctx"></span>
					<span class="message_text" title="">【复查提醒】  ${mp.content}</span>
					<span class="message_btn"><a href="javascript:void(0);" onclick="operation.remind();">提醒</a></span>
					<span class="message_date"><fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
				</c:when>
				<c:when test="${mp.msgType==7}">
					<div style="width:100%;text-align: center;padding-bottom: 5px;">
						<!-- <span class="message_icon_fctx"></span> -->
						<span class="message_date" style="float: right;width:150px;line-height: 25px;margin-top: 10px;">发现时间：<fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
						<div class="message_text" style="width:100%;height:auto;white-space: inherit;line-height: 30px;text-align: center;" title="">
							<span style="text-align: left;">
							 ${mp.content}
							</span>
						</div>
						<span class="n_btn_grey" style="padding:3px 25px;margin-top: 10px;margin-bottom: 5px;"><a href="javascript:void(0);" onclick="operation.jkRemind('${mp.mid}','${mp.themeId}','${mp.isRead}');">已读</a></span>
					</div>
				</c:when>
				<c:when test="${(mp.msgType>=8 && mp.msgType<=35) || (mp.msgType>=100 && mp.msgType<210)}">
					<c:if test="${ (mp.msgType>=8 && mp.msgType<=14) || (mp.msgType>=29 && mp.msgType<=35) || (mp.msgType==100) || (mp.msgType==190)}">
						<span class="message_icon_sbxx"></span>
						<span class="message_text" title='${mp.content}'>【上报消息】  ${mp.content}</span>
					</c:if>
					<c:if test="${(mp.msgType>=15 && mp.msgType<=21)  || (mp.msgType==130)}">
						<span class="message_icon_tkxx"></span>
						<span class="message_text" title='${mp.content}'>【退卡消息】  ${mp.content}</span>
					</c:if>
					<c:if test="${(mp.msgType>=22 && mp.msgType<=28) || (mp.msgType==160)}">
						<span class="message_icon_skxx"></span>
						<span class="message_text" title='${mp.content}'>【删卡消息】  ${mp.content}</span>
					</c:if>
					<c:choose>
						<c:when test="${mp.msgType==8 || mp.msgType==15 || mp.msgType==22 || mp.msgType==29}"><c:set var="type" value="crbbk"></c:set><c:set var="title" value="传染病报卡详情"></c:set></c:when>
						<c:when test="${mp.msgType==9 || mp.msgType==16 || mp.msgType==23 || mp.msgType==30}"><c:set var="type" value="/cdc/f_view/deathReport.shtml"></c:set><c:set var="title" value="死因报卡详情"></c:set></c:when>
						<c:when test="${mp.msgType==10 || mp.msgType==17 || mp.msgType==24 || mp.msgType==31}"><c:set var="type" value="/cdc/f_view/fsaReport.shtml"></c:set><c:set var="title" value="食源异常报卡详情"></c:set></c:when> 
						<c:when test="${mp.msgType==11 || mp.msgType==18 || mp.msgType==25 || mp.msgType==32}"><c:set var="type" value="/cdc/f_view/fsmReport.shtml"></c:set><c:set var="title" value="食源监测报卡详情"></c:set></c:when>
						<c:when test="${mp.msgType==12 || mp.msgType==19 || mp.msgType==26 || mp.msgType==33}"><c:set var="type" value="/cdc/f_view/tumourReport.shtml"></c:set><c:set var="title" value="肿瘤病例报卡详情"></c:set></c:when>
						<c:when test="${mp.msgType==13 || mp.msgType==20 || mp.msgType==27 || mp.msgType==34}"><c:set var="type" value="/cdc/f_view/hcvReport.shtml"></c:set><c:set var="title" value="心脑血管报卡详情"></c:set></c:when>
						<c:when test="${mp.msgType==14 || mp.msgType==21 || mp.msgType==28 || mp.msgType==35}"><c:set var="type" value="/cdc/f_view/sunstrokeReport.shtml"></c:set><c:set var="title" value="高温中暑报卡详情"></c:set></c:when>
						<c:when test="${mp.msgType==100 || mp.msgType==130 || mp.msgType==160 || mp.msgType==190}"><c:set var="type" value="/cdc/f_view/nyzdReport.shtml"></c:set><c:set var="title" value="农药中毒报卡详情"></c:set></c:when>
					</c:choose>
					<span class="message_btn"><a href="javascript:void(0);" onclick="operation.checkCDC('${type}','${title}','${mp.zyid}','${mp.mzid}','${mp.caseId}','${mp.mid}','${mp.themeId}','${mp.isRead}');">查看</a></span>
					<span class="message_date"><fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
				</c:when>
				<c:when test="${mp.msgType==36}">
					<span class="message_icon_jsxx"></span>
					<span class="message_text" title="">【干预消息】  ${mp.content}</span>
					<span class="message_btn"><a href="javascript:void(0);" onclick="operation.dnCheck('${mp.mid}','${mp.themeId}','${mp.caseId}','${isRead}');">查看</a></span>
					<span class="message_date"><fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
				</c:when>
				<c:otherwise>
					<span class="message_icon_qtxx"></span>
					<span class="message_text" title="">【其他消息】  ${mp.content}</span>
					<span class="message_btn"><a href="javascript:viod(0);" onclick="operation.showAll('${mp.mid}','${mp.themeId}',this,'${isRead}');">知道了</a></span>
					<span class="message_date"><fmt:formatDate value="${mp.sendTime}" pattern="MM-dd HH:mm"/></span>
				</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
</div>			
<%-- <div class="pager-his">
	<input type="hidden" class="row" value="8"/>
	<input type="hidden" class="page" value="${MsgPage.page}"/>
	<input type="hidden" class="totalPage" value="${MsgPage.totalPage}"/>
	<div>页数:<font class="curPage">${MsgPage.page}</font>/<font class="totalPage">${MsgPage.totalPage}</font>&nbsp;
		<a href="#" onclick="pageTurning('f');">首页</a>
		<a href="#" onclick="pageTurning(-1);">上一页</a>							
		<a href="#" onclick="pageTurning(1);">下一页</a>
		<a href="#" onclick="pageTurning('');">末页</a>
	</div>
</div> --%>
<script>
	function pagination(){
		$("#size").val(8);
		$("#page").val("${MsgPage.page}");
		$("#totalPage").val("${MsgPage.totalPage}");
		$("#curPage").html("${MsgPage.page}");
		$("#Pages").html("${MsgPage.totalPage}");
	}
	var operation = {
			reply : function(zyid,mzid,themeId,title,lastSendUserId){
				//回复
		    	if(zyid===undefined) zyid = '';
		        Comm.dialogGlobal({
		        	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?themeId="+themeId+"&userId="+lastSendUserId+"&msgType=1",
		            title: title,
		            width:870,
		            height:565,
		            type:"iframe",
		            parent:this
		        });
			},
			check : function(type,caseId,mid,themeid,isRead){
				//已读
				if(isRead!=1){
					mark.markAsRead(mid,themeid);
				}
				//查看
				if(type=="zybl"){
					menuInfo.clickMenu("职业暴露登记表","/bl002Sjdj/f_view/toedit.shtml?id="+caseId,true,"","","","#3");
					messageBox.hideMessageBox();
				}else if(type=="hjjc"){
					$.ajax({
						url:"${webroot}/hw102Jcdmx/f_json/getDjIdById.shtml",
						data:{"id":caseId},
						success:function(data){
							if(data){
								Comm.dialogGlobal({
						        	url:"${webroot}/hw101Jcdj/f_view/toHygieneEdit.shtml?type=1&djId=" + data.replace(/\"/g,""),
						            title: "修改监测报告单",
						            width:970,
						            height: 600,
						            type:"iframe",
						            parent:this
						        });
							}
						},error:function(){
							
						}
					});
				}
			},
			checkBL : function(zyid,mzid,caseId,mid,themeid,isRead){
				//已读
				if(isRead!=1){
					mark.markAsRead(mid,themeid);
				}
				//查看
				if(caseId){
					//
					menuInfo.clickMenu("报卡详情","/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&relid="+caseId,true);
					messageBox.hideMessageBox();
				}
			},
			checkCDC : function(type,title,zyid,mzid,caseId,mid,themeid,isRead){
				//已读
				if(isRead!=1){
					mark.markAsRead(mid,themeid);
				}
				//查看
				if(caseId){
					if(type=="crbbk"){
						if(zyid){
							menuInfo.clickMenu(title,"/cdc/f_view/reportCardZY.shtml?zyid="+zyid+"&masterid="+caseId,true);
						}else{
							menuInfo.clickMenu(title,"/cdc/f_view/reportCardMZ.shtml?mzid="+mzid+"&masterid="+caseId,true);
						}
					}else{
						menuInfo.clickMenu(title,type+"?zyid="+zyid+"&mzid="+mzid+"&masterid="+caseId,true);
					}
					messageBox.hideMessageBox();
				}
			},
			dnCheck : function(mid,themeid,caseId,isRead){
				//已读
				if(isRead!=1){
					mark.markAsRead(mid,themeid);
				}
				if(caseId){
					menuInfo.clickMenu("检出菌搜索","/xn011Dclymx/f_view/index.shtml?testOrderNo="+caseId,true);
				}
			},
			remind : function(){
				
			},
			jkRemind : function(mid,themeid,isRead){
				//已读
				if(isRead!=1){
					mark.markAsRead(mid,themeid);
				}
				MsgPagination.queryMsg();
			},
			showAll :function(mid,themeid,ele,isRead){
				//已读
				if(isRead!=1){
					mark.markAsRead(mid,themeid);
				}
				Comm.dialog({
					title:"消息详情",
					content:content
				});
			}
	}
</script>