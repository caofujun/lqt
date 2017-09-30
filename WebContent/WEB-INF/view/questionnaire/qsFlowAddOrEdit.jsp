<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>编辑问卷流程</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div class="easyui-layout" fit="true">
	    	<!-- <div region="south" border="true" style="height:50px;text-align: center;padding: 10px;overflow: hidden;">
	    		<input type="button" class="btn_save" id="editFormQSInfoBtn" onclick="beforeSubmitCheck()" value="保存"/>
				<input type="button" class="btn_return" onclick="parent.Comm.dialogClose('${param.dialogId}')" value="关闭"/>
	        </div> -->
	        <div region="center" border="false">
        		<form id="editFormQSInfo" method="post">
        			<input type="hidden" name="fid" value="${qsFlow.fid}"/>
        			<input type="hidden" id="ids" value="${ids}"/>
					<input type="hidden" id="unitId" name="unitId" value="${qsFlow.unitId}"/>
					<table class="table" cellpadding="0" cellspacing="0" style="width:570px;">
						<tbody>
							<tr>
								<td class="t_title" style="width:140px;"><span style="color:red">*</span>标题：</td>
								<td>
									<input type="text" name="fName" value="<c:out value="${qsFlow.fName}" />" style="width: 300px;" maxlength="50" class="easyui-validatebox text" required="true"/>
								
								</td>
							</tr>
							<tr>
								<td class="t_title" style="width:140px;"><span style="color:red">*</span>急诊满意度调查问卷：</td>
								<td>
								<select id="emergency"  name="emergency" style="width: 200px;" >
					                    <option value="-1">请选择问卷</option>
										<c:forEach var="list" items="${emergencyList}">
										<option value="${list.qid}">${list.title}</option>
										</c:forEach>
								</select>
								</td>
							</tr>		
							<tr>
								<td class="t_title"><span style="color:red">*</span>导诊满意度调查问卷：</td>
								<td>
								<select id="guide"  name="guide" style="width: 200px;" >
					                    <option value="-1">请选择问卷</option>
										<c:forEach var="list" items="${guideList}">
										<option value="${list.qid}">${list.title}</option>
										</c:forEach>
								</select>
								</td>
							</tr>		
							<tr>
								<td class="t_title"><span style="color:red">*</span>分诊满意度调查问卷：</td>
								<td>
								<select id="triage" name="triage" style="width: 200px;" >
					                    <option value="-1">请选择问卷</option>
										<c:forEach var="list" items="${triageList}">
										<option value="${list.qid}">${list.title}</option>
										</c:forEach>
								</select>
								</td>
							</tr>		
							<tr>
								<td class="t_title"><span style="color:red">*</span>综合满意度调查问卷：</td>
								<td>
								
								<select id="zh_satisfaction"  name="zh_satisfaction" style="width: 200px;" >
					                    <option value="-1">请选择问卷</option>
										<c:forEach var="list" items="${satisfactionList}">
										<option value="${list.qid}">${list.title}</option>
										</c:forEach>
								</select>
								
								</td>
							</tr>		
							<tr>
								<td class="t_title">截止日期：</td>
							<td >
							<input type="text" name="endDate" style="width:140px;" value="${endTime}" class="Wdate text" onclick="WdatePicker({minDate:'%y-%M-%d'})"  validType="date[{minDate:'%y-%M-%d'}]"/> <span style="color: gray;">为空表示长期有效</span>
						    </td>
							</tr>
							<tr>
								<td class="t_title">流程描述：</td>
								<td>
								  <textarea rows="3" cols="3" name="fInfo" style="width: 300px;" ><c:out value="${qsFlow.fInfo}"/></textarea>
							    </td>
							</tr>
						</tbody>
					</table>
				</form>
        	</div>
        	<div class="footer dialog_footer">
	    		<input type="button" class="btn_save" id="editFormQSInfoBtn" onclick="beforeSubmitCheck()" value="保存"/>
				<input type="button" class="btn_return" onclick="parent.Comm.dialogClose('${param.dialogId}')" value="关闭"/>
	        </div>
		</div>
	<script>

	
	$(document).ready(function () { 
		window.setTimeout(function(){
			Comm.form({
				id: 'editFormQSInfo',
				url: '${webroot}/qsFlow/f_json/save.shtml',
				subbtn: 'editFormQSInfoBtn',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({ title: '提示', msg: '操作成功！！！' });
						parent.wjdcQuestionnaire.query();
			    		parent.Comm.dialogClose('${param.dialogId}');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : '操作失败！' });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		},100);
		setQsForEdit();
	});
	//判空
	function beforeSubmitCheck(){
		if($("#emergency").val()=='-1'){alert("急诊满意度调查问卷未选择！"); return;}
		if($("#guide").val()=='-1'){alert("导诊满意度调查问卷未选择！"); return;}
		if($("#triage").val()=='-1'){alert("分诊满意度调查问卷未选择！"); return;}
		if($("#zh_satisfaction").val()=='-1'){alert("综合满意度调查问卷未选择！"); return;}
		$('#editFormQSInfo').submit();
	}
	
	//修改操作 给下拉列表设值
	function setQsForEdit(){
		var ids=$("#ids").val();
		if(ids==null||ids==''){
			return;
		}
		var qsIds=ids.split("#");
		if(ids!=null){
		    $("#emergency").find("option[value="+qsIds[0]+"]").attr("selected",true);
		    $("#guide").find("option[value="+qsIds[1]+"]").attr("selected",true);
		    $("#triage").find("option[value="+qsIds[2]+"]").attr("selected",true);
		    $("#zh_satisfaction").find("option[value="+qsIds[3]+"]").attr("selected",true);
		} 
	}
	
	</script>
	</body>
</html>