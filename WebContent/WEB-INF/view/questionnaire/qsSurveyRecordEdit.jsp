<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>评估表</title>
</head>
<body>
<script type="text/javascript" src="${webroot}/resources/js/functions/wjdc.js${version}"></script>
	<form id="editFormWjdcSurvey" method="post">
		<input type="hidden" id="rid" name="rid" value="${qsSurveyRecord.rid}"/>
		<input type="hidden" id="qid" name="qid" value="${qsSurveyRecord.qid}"/>
		<input type="hidden" id="visitId" name="visitId" value="${qsSurveyRecord.visitId}"/>
		<input type="hidden" id="zyid" name="zyid" value="${qsSurveyRecord.zyid}"/>
		<input type="hidden" id="unitId" name="unitId" value="${user.unitId}"/>
		<input type="hidden" id="depType" name="depType" value="${qsSurveyRecord.depType}">
		<div style="padding: 5px 10px 50px 10px;">
			<c:choose>
			<c:when test="${param.type == 'phone' || qsSurveyRecord.surveyType=='2'}">
			<input type="hidden" name="surveyType" value="2"/>
				<div class="m_search h_set" style="border-bottom:1px solid #ccc;">
					调查时间：
					<input type="hidden" id="changeType" value="1"/>
					<input type="text" name="surveyTime" value="<fmt:formatDate value="${qsSurveyRecord.surveyTime}" pattern="yyyy-MM-dd" />" class="easyui-validatebox Wdate text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  required="true" style="width: 120px;"/>
					<input type="hidden" id="unit" value="${unitId}"/>
					<input id="dep" name="depNo" style="width:140px" />				
					<input type="text" id="wjdcSurveyAttendUser" name="patientName" value="<c:out value="${qsSurveyRecord.patientName}" />" style="width: 60px;" class="easyui-validatebox auto-tip text" data-tip="姓名"/>
					<input type="text" id="wjdcSurveyAttendPhone" name="patientPhone" value="<c:out value="${qsSurveyRecord.patientPhone}" />" style="width: 100px;" class="easyui-validatebox auto-tip text" data-tip="电话" required="true"/>
					<input type="button" class="btn" onclick="wse.call(this)" value="呼叫"/>
				</div>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="surveyType" value="1"/>
				<div class="m_search h_set" style="border-bottom:1px solid #ccc;">
					<div class="m_search_first">
						<div <c:if test="${qsSurveyRecord.patientId!=null}">class="hidden"</c:if>>			
							请填写调查基本信息：							
							<select id="changeType" onchange="wse.changeType(this)" style="width: 90px;">
								<option value="0">匿名用户</option>
								<option value="1">实名用户</option>
							</select>
							<input type="hidden" id="unit" value="${unitId}"/>
							<span class="ml10">科室：</span>
							<div class="select_del"><input id="dep" name="depNo" style="width:140px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#dep').combo('clear');"></a></div>			
						</div>
					</div>
					<div class="m_search_first">
						调查时间：<input type="text" name="surveyTime" value="<fmt:formatDate value="${qsSurveyRecord.surveyTime}" pattern="yyyy-MM-dd" />" class="easyui-validatebox Wdate text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  required="true" style="width: 120px;"/>		
					</div>
					<div class="clear"></div>
					
					<div <c:if test="${qsSurveyRecord.patientId!=null}">class="hidden"</c:if> >
						<div id="userBasePanel" style="display: none; border-top:1px dashed #ccc; padding-top:5px;">
							<div  class="m_search_first">						
								<input type="hidden" name="patientId" value="${qsSurveyRecord.patientId}"/>
								姓名：<input type="text" id="patientName" class="text" name="patientName" value="<c:out value="${qsSurveyRecord.patientName}" />" style="width: 60px; margin-right:10px;"/>
								 电话：<input type="text" id="patientPhone" name="patientPhone" class="easyui-validatebox text" validType="phoneOrMobile" value="${qsSurveyRecord.patientPhone}" style="width: 90px; margin-right:10px;"/>
								<span style="color:#1b60ad;">实名用户请需填写调查者的基本信息！</span>
							</div>
						</div>					
					</div>
					
				</div>
			</c:otherwise>
			</c:choose>
			
			<div id="wjdcContPanel"></div>
		</div>

		
		<div class="footer">	
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="wse.save()" class="no_ico"><span>确认</span></a>
				</div>				
			</div>	
		</div>
	</form>
	<script>
	var wse = {
			onInit : true,
			init : function() {
				if('${qsSurveyRecord.patientId}'!='' || '<c:out value="${qsSurveyRecord.patientName}" />'!='' || '${qsSurveyRecord.patientPhone}'!='') {
					$('#changeType').val('1');
					wse.changeType($('#changeType')[0]);
				}
				wse.loadWj();
			},
			save : function() {
				if($('#changeType').val() === '0') {
					$('#patientName').val('');
					$('#patientPhone').val('');
				}
				$('#editFormWjdcSurvey').submit();
			},
			
			loadWj : function() {
				var _wjdcContPanel = $('#wjdcContPanel');
				wjdc.loadQues('wjdcContPanel', $('#qid').val(), {
					rid : $('#rid').val(),
					resultFn : function(i, obj) {
						var _cont = ['<div class="div_question" id="questionSel',obj.tid,'"><div class="div_title_question_all"><div class="div_title_question">',(i + 1),'. ',obj.title,'<a class="ml10" href="javascript:wjdc.clearCont(\'questionSel',obj.tid,'\');">取消</a></div></div>'];
						_cont.push('<div class="div_table_radio_question">',
						wjdc.createOpt(obj),'</div></div>');
						return _cont.join('');
					},
					successFn : function() {
						$.parser.parse('#wjdcContPanel');
						try{
							parent.iFrameHeight();
						}catch(e){
							
						}
					},
					dataNullFn : function() {
						_wjdcContPanel.append('问卷还没有题目选项!');
					}
				});
			},
			changeType : function(_this) {
				if(_this.value == 1) $('#userBasePanel').css('display', 'block');
				else $('#userBasePanel').css('display', 'none');
			},
			//呼叫
			call : function(_this) {
				var _mobile = $('#wjdcSurveyAttendPhone').val();
				if(_mobile==$('#wjdcSurveyAttendPhone').attr('data-tip')){
					_mobile='';
				}else{
					_mobile = $('#wjdcSurveyAttendPhone').val();
				}
				if(_mobile==='') {
					$.messager.alert('友情提示', '请输入呼叫号码!');
					return;
				}
				var _patientName = $('#wjdcSurveyAttendUser');
				var _pVal = '';
				if(_patientName.val() != _patientName.attr('data-tip')) {
					_pVal = _patientName.val();
				}
				parent.parent.Comm.phone.call({
					_this:_this,
					phone:_mobile,
					patientName: _pVal,
					uploadRecord: 0,
					callback: function() {
						//var _sfxjBtn = $('#suifangXiaojieBtn');
					}
				});
			}
	};
	
/* 	// 计算页面的实际高度，iframe自适应会用到
	function calcPageHeight(doc) {
	    var cHeight = Math.max(doc.body.clientHeight, doc.documentElement.clientHeight);
	    var sHeight = Math.max(doc.body.scrollHeight, doc.documentElement.scrollHeight);
	    var height  = Math.max(cHeight, sHeight);
	    return height;
	}
	window.onload = function() {
		var height = calcPageHeight(document);
		//parent.$("#ifr").attr("style","height='"+height+"px'");
		parent.document.getElementById('ifr').style.height = height + 'px' ;		
	}
	 */
	$(document).ready(function () { 
		window.setTimeout(function(){
			Csm.combogrid.dep({
				//【必传】控件名称
				id: 'dep',
				//【可选参数】下拉列表的默认value，不传则没有默认值；
				value: '${qsSurveyRecord.depNo}',
				//【可选参数】不传默认区session的医院ID
				hospId: '"${unitId}',
				//【可选参数】1：回调,回调方法setDep()，0：不回调，不传默认回调
				//callback: '0',
				
			});
			wse.init();
			Comm.form({
				id: 'editFormWjdcSurvey',
				url: '${webroot}/qsSurveyRecord/f_json/save.shtml',
				subbtn: 'changeFormWjdcSurveySubmitBtn',
				onSubmit: function() {
					autoTip.clear();
				},
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({ title: '提示', msg: '操作成功！' });
						var parentObject = parent.Comm.getObjectCache();
						try{
							parentObject.query();
						}catch(e){
							
						}						
						parent.Comm.dialogClose('${param.dialogId}');
						//parent.location.reload();
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : '操作失败！' });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
			
			
		},100);
	});
	</script>
</body>
</html>