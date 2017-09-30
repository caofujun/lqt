<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,user-scalable=no;"/>
   	<title>${questInfo.title}</title>
    <%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>

<body  class="qs_wap_body" >
	<form id="wjdcSurvey" method="post">
	<div class="jqContent mb60">
	<input type="hidden" name="depNo"  id="depNo"  value="${depNo}" />
	<input type="hidden" id="pid" name="pid" value="${pid}" />
	<input type="hidden" id="isAnonymous" name="isAnonymous" value="${questInfo.isAnonymous}" />
	<input type="hidden" name="patientPhone" id ="patientPhone" value="" />
	<input type="hidden" name="qid" id="qid"value="${questInfo.qid}" />
	<input type="hidden" name="unitId" value="${questInfo.unitId}" />
	<input type="hidden" name="nowQsType" value="${nowQsType}" /><!-- 用于记录流程问卷的问卷类型 -->
	<input type="hidden" name="fid" value="${fid}" /><!-- 用于记录流程问卷ID -->
	<input type="hidden" name="nowStep" value="${nowStep}" /><!-- 用于记录流程问卷当前步骤 -->
	<!-- app录入 -->
	<input type="hidden" name="surveyType" value="3" />
	<div class="surveyhead" style="border: 0px;">
		<h1 style="position:relative;">
			<span>${questInfo.title}</span>			
		</h1>		
		<div class="clear"></div>		
    	<!-- border_red -->
		<div class="surveycontent" style="margin-bottom:60px;">
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
											   <c:if test="${qsTopic.allowNull == 1}">
											   class="_qs_validate_text" onClick="webSubmit.hideWarnInfo(this)" id="${qsTopic.tid}_qs_validate_text"
											   </c:if>
											  name="resultTkt${qsTopic.tid}">
								    </textarea>
								</c:when>
								<c:when test="${qsTopic.ttype == 1}">
									<c:if test="${fn:length(qsTopic.options) > 0 }">
										<ul class="ulradiocheck <c:if test="${qsTopic.allowNull == 1}"> _qs_validate_li </c:if>" >
										<c:forEach items="${qsTopic.options}" var="qsOption" varStatus="opStatus">
											<li style="width: 80%;" >
												<input type="radio" name="resultOptId${qsOption.tid}" id="${qsOption.optId}"
													   value="${qsOption.tid}#@#${qsOption.optId}" 
													   <c:if test="${qsTopic.allowNull == 1}"> onClick="webSubmit.hideWarnInfo(this,true)" </c:if>
													   /><label for="${qsOption.optId}">${qsOption.optName}</label>
											    <c:if test="${qsOption.allowInput == 1}">
											    	<input type="text" class="text" onfocus="webSubmit.checkRadio(${qsOption.optId});" id="_result${qsOption.optId}" name="resultOptIdInput${qsOption.optId}" name="resultOptId${qsOption.tid}" value="" style="width: 80px;margin-left:100px;"/>
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
											<li style="width:80%;">
												<input type="checkbox" name="resultOptId${qsOption.tid}" id="${qsOption.optId}"
													   value="${qsOption.tid}#@#${qsOption.optId}" 
													   <c:if test="${qsTopic.allowNull == 1}"> onClick="webSubmit.hideWarnInfo(this,true)" </c:if>
													   /><label for="${qsOption.optId}">${qsOption.optName}</label>
												<c:if test="${qsOption.allowInput == 1}">
											    	<input type="text" class="text" onfocus="webSubmit.checkRadio(${qsOption.optId});" id="_result${qsOption.optId}" name="resultOptIdInput${qsOption.optId}" name="resultOptId${qsOption.tid}" value="" style="width: 80px;margin-left:100px;"/>
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
	</div>
	</div>
	<div class="validat" style="display:none;" id="inputPhoneNum">
		<div class="user_login">
			<table>				
				<tr><td style="color: red">请输入您的手机号：</td></tr>
				<tr><td><input type="text" id="MobilePhone"/></td></tr>
				<tr><td height="30"><span id="errorMessage" class="red"></span></td></tr>
				<tr><td><a href="javascript:void()" onclick="isphone();" class="grade_grey">开始调查</a></td></tr>
			</table>		   
		</div>			
	</div>
	</form>
	<div class="Mobile_footer_button"><a href="javascript:webSubmit.beforeSubmit();" class="btn_submit" id="wjdcSurveySubmit" >提交答卷</a></div>

</body>
<script type="text/javascript">
var flag=0;
function isphone(){
	 var num = document.getElementById('MobilePhone').value;
	 var x = document.getElementById("errorMessage");
	 var partten = /^1[3,5,8]\d{9}$/;
	 if(partten.test(num)){
	   x.innerHTML = "";
	   document.getElementById('patientPhone').value =num;
	   $("#inputPhoneNum").css("display","none");
	 }else{
	   x.innerHTML = "您输入的手机号码有误！";
	 }
}	

function isAnonymous(){

	 if($("#isAnonymous").val()==0 && $("#pid").val().length<1){//不是匿名
		
		  $("#inputPhoneNum").css("display","block");
	 }
}

var webSubmit = {
	//校验
	submitValidate : function(){
		//校验合法
		var isLegal = true;
		var id ="";
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
		var pid = $('#pid').val();
		var qid = $('#qid').val();
		 $.ajax({
             url: '${webroot}/qsSurveyRecord/json/getByPidQid.shtml',
             type: 'post',
             data: { 'patientId':pid,'qid':qid},
             dataType: 'json',
             success : function(json) {
	             	if (json.result === 'success') {
	             		alert('您已经答过此问卷!');
					}else{
						$('#wjdcSurvey').submit();						
					}
				}
        });
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
	}
};

function getElements()//使textarea初值没有空格
{
var textarea=document.getElementsByTagName("textarea");
  for (var i = 0; i < textarea.length; i++) {
   textarea[i].value="";
}
}

$(document).ready(function () { 
	isAnonymous();//是否匿名
	getElements();
	window.setTimeout(function(){
		Comm.form({
			id: 'wjdcSurvey',
			url: '${webroot}/qsSurveyRecord/json/openSave.shtml',
			subbtn: 'wjdcSurveySubmit',
			success : function(json) {
				if (json.result === 'success') {
					if(json.expandValue!=null&&json.expandValue!=''){
					    var reg=new RegExp("amp;","g");   
						location.href="${webroot}"+json.expandValue.replace(reg,'');
						return;
					}
					location.href="${webroot}/qsSurveyRecord/view/endPage.shtml?qid=${questInfo.qid}"
				}
			}
		});
	},100);
});


</script>
</html>
