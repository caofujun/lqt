<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   	<title>${questInfo.title}</title>
    <%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>

<body class="qs_web_body" >
	<form id="wjdcSurvey" method="post">
	<div class="jqContent">
	<input type="hidden" name="qid" value="${questInfo.qid}" />
	<input type="hidden" name="depNo" value="${depNo}" />
	<input type="hidden" name="pid" value="${pid}" />
	<input type="hidden" name="unitId" value="${questInfo.unitId}" />
	<!-- 现场录入 -->
	<input type="hidden" name="surveyType" value="1" />
	<div class="surveyhead" style="border: 0px;">
		<h1 style="position:relative;">
			<span>${questInfo.title}<c:if test="${not empty isView and isView == 1 }"><span style="color:red;">(查看)</span></c:if></span>	
		   
		</h1>	
		<h3 style="float: right;">已填写${questInfo.answerCount}份</h3>	
		<div class="clear"></div>
		<div  class="surveydescription">
			<span  style="vertical-align: middle;">
				尊敬的女士/先生：<br/>
				您好！感谢您在百忙之中填写问卷，请您根据自己的实际感受和看法如实填写，本问卷采用匿名形式，所有数据仅供学术研究分析使用。<br/>
				敬祝身体健康，万事如意！
			</span>
		</div>
		<div class="clear"></div>
    	<!-- border_red -->
		<div class="surveycontent">
			<c:forEach items="${questInfo.qsTopicList}" var="qsTopic" varStatus="status">
					<div class="div_question"  myFocusAttrType="${qsTopic.ttype}" > 
						<div class="div_title_question_all">
							<input type="hidden" name="tIdList" value="${qsTopic.tid}"/>
							<div  class="div_title_question">${status.count}、${qsTopic.title}
								<c:if test="${qsTopic.allowNull == 1}"><span style="color:red;">&nbsp;*</span></c:if>
							</div>
							<div class="clear"></div>
						</div>
						<div class="div_table_radio_question" >
							<div class="div_table_clear_top"></div>
							<c:choose>
								<c:when test="${qsTopic.ttype == 3}">
									<textarea title="" style="overflow: auto;width:90%;height:88px;" 
											   value="" 
											  <c:if test="${qsTopic.allowNull == 1}"> 
											  class="_qs_validate_text" onClick="webSubmit.hideWarnInfo(this)" id="${qsTopic.tid}_qs_validate_text"
											  </c:if> 
											  name="resultTkt${qsTopic.tid}"></textarea>
								</c:when>
								<c:when test="${qsTopic.ttype == 1}">
									<c:if test="${fn:length(qsTopic.options) > 0 }">
										<ul class="ulradiocheck <c:if test="${qsTopic.allowNull == 1}"> _qs_validate_li </c:if>" >
										<c:forEach items="${qsTopic.options}" var="qsOption" varStatus="opStatus">
											<li style="width: 90%;" >
											<c:choose>
												<c:when test="${qsOption.isSelect == 1}">
												<input class="_trueInput" type="radio" checked="checked" name="resultOptId${qsOption.tid}" id="${qsOption.optId}"
													   value="${qsOption.tid}#@#${qsOption.optId}" 
													   <c:if test="${qsTopic.allowNull == 1}"> onClick="webSubmit.hideWarnInfo(this,true)" </c:if>
													   /><label for="${qsOption.optId}">${qsOption.optName}</label>
												</c:when>
												<c:otherwise>
													<input class="_trueInput" type="radio" name="resultOptId${qsOption.tid}" id="${qsOption.optId}"
													   value="${qsOption.tid}#@#${qsOption.optId}" 
													   <c:if test="${qsTopic.allowNull == 1}"> onClick="webSubmit.hideWarnInfo(this,true)" </c:if>
													   /><label for="${qsOption.optId}">${qsOption.optName}</label>
												</c:otherwise>
											</c:choose>
											    <c:if test="${qsOption.allowInput == 1}">
											    	<input type="text" class="text" onfocus="webSubmit.checkRadio(${qsOption.optId});" id="_result${qsOption.optId}" name="resultOptIdInput${qsOption.optId}" value="" style="width: 80px; margin-left:100px;"/>
											    </c:if>
											</li>
										</c:forEach>
										<div class="clear"></div>
										</ul>
									</c:if>
								</c:when>
								<c:when test="${qsTopic.ttype == 2}">
									<c:if test="${fn:length(qsTopic.options) > 0 }">
										<ul class="ulradiocheck <c:if test="${qsTopic.allowNull == 1}"> _qs_validate_li </c:if>" >
										<c:forEach items="${qsTopic.options}" var="qsOption" varStatus="opStatus">
											<li style="width: 90%;" >
											<c:choose>
												<c:when test="${qsOption.isSelect == 1}">
												<input class="_trueInput" type="checkbox" checked="checked" name="resultOptId${qsOption.tid}"  id="${qsOption.optId}"
													   value="${qsOption.tid}#@#${qsOption.optId}" 
													   <c:if test="${qsTopic.allowNull == 1}"> onClick="webSubmit.hideWarnInfo(this,true)" </c:if>
													   /><label for="${qsOption.optId}">${qsOption.optName}</label>
												</c:when>
												<c:otherwise>
													<input class="_trueInput" type="checkbox" name="resultOptId${qsOption.tid}"  id="${qsOption.optId}"
													   value="${qsOption.tid}#@#${qsOption.optId}" 
													   <c:if test="${qsTopic.allowNull == 1}"> onClick="webSubmit.hideWarnInfo(this,true)" </c:if>
													   /><label for="${qsOption.optId}">${qsOption.optName}</label>
												</c:otherwise>
												</c:choose>										 
												<c:if test="${qsOption.allowInput == 1}">
											    	<input type="text" class="text" onfocus="webSubmit.checkRadio(${qsOption.optId});" id="_result${qsOption.optId}" name="resultOptIdInput${qsOption.optId}" value="" style="width: 80px;margin-left:100px;"/>
											    </c:if>

											</li>
										</c:forEach>
										<div class="clear"></div>
										</ul>
									</c:if>
								</c:when>
							</c:choose>
							<ul class="ulradiocheck"><div class="clear"></div></ul>
							<div class="clear"></div><div class="div_table_clear_bottom"></div>
						</div>
						<div class="errorMessage" style="display: none;">此题为必答题，请填写答案！</div>
					</div>
			</c:forEach>							
		</div>
		<c:if test="${empty isView}">
			<div class="qs_footer"><input type="button" class="btn_save" id="wjdcSurveySubmit" onclick="webSubmit.beforeSubmit();" value="提交答卷" /></div>
		</c:if>    
	</div>
	</div>	
	</form>
</body>
<script type="text/javascript">
var flag=0;
var webSubmit = {
	//校验
	submitValidate : function(){
		//校验合法
		var isLegal = true;
		var id = "";
		var idLegal = true;
		var divObject = $('.div_question');
		if(divObject.length > 0){
			//遍历整个问题数据
			divObject.each(function(){
				var type = $(this).attr('myFocusAttrType');
				if(type == 3){
					var texterea = $(this).children('.div_table_radio_question').children('._qs_validate_text');
					//遍历文本域输入数据
					texterea.each(function(){
						var value = $(this).val();
						if(value == '' ){
							var tempParent = $(this).parent().parent();
							//加警告框
							tempParent.addClass('border_red');
							//加提示errorMessage
							tempParent.children('.errorMessage').show();
							isLegal = false;
							if(idLegal){
								id = $(this).attr('id');
								idLegal = false;
							};
						};
					});
				}else{
					var select = $(this).children('.div_table_radio_question').children('.ulradiocheck._qs_validate_li');
					//遍历选择题数据
					select.each(function(){
						var li = $(this).children();
						if(li.length > 0){
							//是否输入值
							var isWriter = false;
							li.each(function(){
								var input = $(this).children('input:first');
								if(input.attr("checked")){
									isWriter = true;
								};
							});
							//么有输入值
							if(!isWriter){
								var tempParent = $(this).parent().parent();
								//加警告框
								tempParent.addClass('border_red');
								//加提示errorMessage
								tempParent.children('.errorMessage').show();
								isLegal = false;
								li.each(function(){
									if(idLegal){
										id = $(this).children('input:first').attr('id');
										idLegal = false;
									}
								});
							};
						};
					});
				};
			});
		}
		$("#"+id+"").focus();
		return isLegal;
	},
	//提交前
	beforeSubmit : function(){
		var result = webSubmit.submitValidate();
		//校验数据是否合法
		if(!result){
			return;
		}
		if(flag==0){
		flag=1;
		webSubmit.submit();
		}
	},
	//提交数据
	submit : function(){
		$('#wjdcSurvey').submit();
	},
	//隐藏警告信息
	hideWarnInfo : function(object,isCheck){
		var tempParent = null;
		if(isCheck){
			//非填空题目
			tempParent = $(object).parent().parent().parent().parent();
		}else{
			tempParent = $(object).parent().parent();
		}
		//加警告框
		tempParent.removeClass('border_red');
		//加提示errorMessage
		tempParent.children('.errorMessage').hide();
	},
	//选中
	checkRadio : function(optId){
		var selectOb = $('#_result'+optId);
		var pre = selectOb.siblings("._trueInput");
		pre.attr("checked","checked");
	},
};

/*根据屏幕宽度自动调整问卷的显示样式*/
var width_all=0; 
var width_body = 0;
function editWidth(){	
	width_all = document.body.offsetWidth;
	width_body = width_all - 140;
	//alert(width_all + "," + width_body);

	if(width_all < 930)
		{
			$(".qs_web_body").css({"padding": "0"});
			$(".qs_web_body").css({"background": "#fff"});
			$(".jqContent").css({"width": width_all+"px"});
			$(".surveyhead").css({"width": width_body+"px"});
		}
		else{
			$(".qs_web_body").css({"padding": "70px 0"});
			$(".qs_web_body").css({"background": "#a8dcf9 url('${webroot}/resources/images_org/Commonbg.jpg') no-repeat fixed top center"});
			$(".jqContent").css({"width": "920px"});
			$(".surveyhead").css({"width": "780px"});
		}		
}

$(document).ready(function () { 
	window.setTimeout(function(){
		Comm.form({
			id: 'wjdcSurvey',
			url: '${webroot}/qsSurveyRecord/json/openSave.shtml',
			subbtn: 'wjdcSurveySubmit',
			success : function(json) {
				if (json.result === 'success') {
					javascript:scroll(0,0)
					$.messager.show({ title: '提示', msg: '问卷提交成功!' });
					window.location.href='${webroot}/qsSurveyRecord/view/webEndPage.shtml';
					document.getElementById("wjdcSurvey").reset(); 
				}else{
					$.messager.show({ title: '提示', msg: '问卷提交失败!' });
				}	
			}
		});
	},100);	
	
	window.onresize = editWidth();
});
</script>
</html>
