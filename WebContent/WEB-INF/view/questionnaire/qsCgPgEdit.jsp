<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>评估表</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
<script type="text/javascript" src="${webroot}/resources/js/functions/pgb.js${version}"></script>
	<form id="editFormWjdcSurvey" method="post">
	<div class="Assess mb60">
		<div class="Assess_title">${qsQuestionnaire.title}</div>
		<div class="n_btn_blue">
			<a href="javascript:wse.addWjdc();"><i class="icon iconfont">&#xe602;</i><span>新增评估</span></a>
		</div>
		<table class="table_noborder_b" id="pgbList">		
			<c:forEach items="${surveyRecordList}" var="surveyRecord" varStatus="status">	
				<tr id="tr${status.index}">
					<td width="80"><fmt:formatDate value="${surveyRecord.surveyTime}" pattern="MM月dd日" /></td>
					<td style="text-align:left">
						<input type="hidden" name="surveyRecordList[${status.index}].rid" id="rid${status.index}" value="${surveyRecord.rid}"/>
						<input type="hidden" name="surveyRecordList[${status.index}].qid" id="qid${status.index}" value="${surveyRecord.qid}"/>
						<input type="hidden" name="surveyRecordList[${status.index}].unitId" value="${user.unitId}"/>
						<input type="hidden" name="surveyRecordList[${status.index}].patientId" value="${surveyRecord.patientId}"/>
						<input type="hidden" name="surveyRecordList[${status.index}].zyid" value="${surveyRecord.zyid}"/>
						<input type="hidden" name="surveyRecordList[${status.index}].depNo" value="${surveyRecord.depNo}" />
						<input type="hidden" name="surveyRecordList[${status.index}].patientName" value="<c:out value="${surveyRecord.patientName}" />"/>			
						<input type="hidden" name="surveyRecordList[${status.index}].surveyTime" value="<fmt:formatDate value="${surveyRecord.surveyTime}" pattern="yyyy-MM-dd" />"/>
						<div id="wjdcContPanel${status.index}"></div>				
					
						<div style="text-align:right;">
							<span>评估人：</span>
							<input type="hidden" id="doctorId${status.index}" value="${surveyRecord.username}"/>
							<!-- 新增验证 -->
							<input id="docid${status.index}" type="hidden" class="pgrId" value="${surveyRecord.username}"/>
							<input id="doctor${status.index}" name="surveyRecordList[${status.index}].username" style="width:130px" />
						</div>
					</td>
					<td width="20">					
						<a href="javascript:wse.delWjdc('${surveyRecord.rid}','${status.index}');" class="table_del" title="删除"><i class="icon iconfont">&#xe615;</i></a>
					</td>
				</tr>	
			</c:forEach>
		</table>	
	</div>		
	<div class="footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:wse.save();" class="no_ico"><span>确认</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:parent.Comm.dialogClose('${param.dialogId}');"  class="no_ico"><span>取消</span></a>
			</div>
		</div>	
	</div>
</form>
	<script>
	var wjdcSize = '${fn:length(surveyRecordList)}';
	var wse = {
			onInit : true,
			init : function() {		
				//wse.loadWj();
				wse.loadWjList();
			},
			save : function() {
				if($('#changeType').val() === '0') {
					$('#patientName').val('');
					$('#patientPhone').val('');
				}
				//验证评估人是否正确
				try{
					$(".pgrId").each(function(index){
						if(! $(this).val()){
							parent.$.messager.show({ title: '提示', msg: '第'+(index+1)+'条记录请从下拉列表中选择评估人！' });	
							throw "x";
						}
					});
				}catch(e){
					return;
				}
				$('#editFormWjdcSurvey').submit();
			},
			delWjdc : function(rid,id){
				 $.ajax({
	                url: webroot + '/qsSurveyRecord/f_json/delete.shtml',
	                type: 'post',
	                data: { 'id':rid },
	                dataType: 'json',
	                success : function(data) {
	                	parent.$.messager.show({ title: '提示', msg: '操作成功！' });
	                	$('#tr'+id).remove();
					}
				}) 
			},
			addWjdc : function(){
				var nowDate = parent.dateMonth;
				var month = parseInt(nowDate.getMonth())+1;
				var surveyTime = nowDate.getFullYear()+"-"+month+"-"+nowDate.getDate();
				var trHtml='<tr id="tr'+wjdcSize+'"><td width="80"><input type="text" name="surveyRecordList['+wjdcSize+'].surveyTime" value="'+surveyTime+'" class="Wdate text" onclick="WdatePicker()"></td><td style="text-align:left"><input type="hidden" name="surveyRecordList['+wjdcSize+'].rid" id="rid'+wjdcSize+'" value=""/><input type="hidden" name="surveyRecordList['+wjdcSize+'].qid" id="qid'+wjdcSize+'" value="${qsSurveyRecord.qid}"/><input type="hidden" name="surveyRecordList['+wjdcSize+'].unitId" value="${user.unitId}"/><input type="hidden" name="surveyRecordList['+wjdcSize+'].patientId" value="${qsSurveyRecord.patientId}"/><input type="hidden" name="surveyRecordList['+wjdcSize+'].zyid" value="${qsSurveyRecord.zyid}"/><input type="hidden" name="surveyRecordList['+wjdcSize+'].depNo" value="${qsSurveyRecord.depNo}" /><input type="hidden" name="surveyRecordList['+wjdcSize+'].patientName" value="<c:out value="${qsSurveyRecord.patientName}" />"/><div id="wjdcContPanel'+wjdcSize+'"></div><div style="text-align:right;"><span>评估人：</span><input type="hidden" id="doctorId'+wjdcSize+'" value=""/><input id="docid'+wjdcSize+'" type="hidden" class="pgrId" /><input id="doctor'+wjdcSize+'" name="surveyRecordList['+wjdcSize+'].username" style="width:130px" class="pgr" /></div></td><td width="20"><a href="javascript:wse.delWjdc(\'\','+wjdcSize+');" class="table_del" title="删除"><i class="icon iconfont">&#xe615;</i></a></td></tr>';
				$('#pgbList').prepend(trHtml);
				wse.loadWj();
				var zzz = 'docid'+wjdcSize;
				var yyy = 'doctor'+wjdcSize;
				Csm.combogrid.doctor({
					id: 'doctor'+wjdcSize,
					callback: '0',
					value:'${username}',
					onClickRow:function(index,row){
						$("#"+zzz).val(row['employeeId']);
					},
					onLoadSuccess:function(data){
						if(data.total>0){
							//新增，方便验证
							var sr = $("#"+yyy).combogrid('grid').datagrid("getSelected");
							if(sr.employeeId){
								$("#"+zzz).val(sr.employeeId);
							}
						}else{
							$("#"+zzz).val("");
						}
					}
				});
				wjdcSize = parseInt(wjdcSize) +1;
			},
			loadWjList : function() {
				for(var num=0;num<wjdcSize;num++){
					var _wjdcContPanel = $('#wjdcContPanel'+num);
					wjdc.loadQues('wjdcContPanel'+num, $('#qid'+num).val(), num, {
						rid : $('#rid'+num).val(),
						resultFn : function(i, obj, data,num1) {
							var _cont = ['<div class="Assess_item" id="questionSel',obj.tid,'"><span class="Assess_item_t">',obj.title,':</span>'];
							_cont.push('<span class="Assess_item_c">',wjdc.createOpt(obj,num1),'</span></div>');
							return _cont.join('');
						},
						successFn : function(num1) {
							$.parser.parse('#wjdcContPanel'+num1);				
						},
						dataNullFn : function() {
							_wjdcContPanel.append('评估表还没有题目选项!');
						}
					});
				}				
			},		
			loadWj : function() {
				var _wjdcContPanel = $('#wjdcContPanel'+wjdcSize);
				wjdc.loadQues('wjdcContPanel'+wjdcSize, $('#qid'+wjdcSize).val(),wjdcSize, {
					rid : $('#rid'+wjdcSize).val(),
					resultFn : function(i, obj,data,num1) {
						var _cont = ['<div class="Assess_item" id="questionSel',obj.tid,'"><span class="Assess_item_t">',obj.title,':</span>'];
						_cont.push('<span class="Assess_item_c">',wjdc.createOpt(obj,num1),'</span></div>');
						return _cont.join('');
					},
					successFn : function(num1) {
						$.parser.parse('#wjdcContPanel'+num1);				
					},
					dataNullFn : function() {
						_wjdcContPanel.append('评估表还没有题目选项!');
					}
				});
			}		
	};
	
	
	$(document).ready(function () { 
		window.setTimeout(function(){
			//主管医生
			for(var num=0;num<wjdcSize;num++){
				Csm.combogrid.doctor({
					id: 'doctor'+num,
					callback: '0',
					value:$('#doctorId'+num).val()
				});
			}
			wse.init();
			Comm.form({
				id: 'editFormWjdcSurvey',
				url: '${webroot}/qsSurveyRecord/f_json/saveList.shtml',
				subbtn: 'changeFormWjdcSurveySubmitBtn',
				onSubmit: function() {
					autoTip.clear();
				},
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({ title: '提示', msg: '操作成功！' });									
						setTimeout(parent.location.reload(),1000);
						parent.Comm.dialogClose('${param.dialogId}');
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
