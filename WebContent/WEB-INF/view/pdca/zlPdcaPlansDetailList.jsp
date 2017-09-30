<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<form id="editFormZlPdcaPlans" method="post">
	<input type="hidden" name="puid" value="${zlPdcaPlans.puid}" />
	<input type="hidden" name="planId" value="${zlPdcaPlans.planId}" /> 
	<input type="hidden" name="planName" value="${zlPdcaPlans.planName}" /> 
	<input type="hidden" name="deptName" value="${zlPdcaPlans.deptName}" /> 
	<input type="hidden" name="deptId" value="${zlPdcaPlans.deptId}" />
	<input type="hidden" id="saveOrAs" name="saveOrAs" value="0" />    
	<table class="table mb60" id="zlPdcaPlansDetailList" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td class="t_title"><span class="red">*</span>1、监测项目：</td>
			<td colspan="3" style="text-align:left;"><input type="text" id="jcxm" name="jcxm" style="width:250px" value="<c:out value="${zlPdcaPlans.jcxm}" />" class="easyui-validatebox text" required="true"></td>
		</tr>
		<tr>
			<td class="t_title">2、预期目标：</td>
			<td colspan="3" style="text-align:left;"><textarea name="yqmb" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlans.yqmb}" /></textarea></td>
		</tr>
		<tr>
			<td class="t_title">3、资料来源：</td>
			<td colspan="3" style="text-align:left;"><textarea name="zlly" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlans.zlly}" /></textarea></td>
		</tr>
		<tr>
			<td class="t_title">4、监测结果：</td>
			<td colspan="3" style="text-align:left;"><textarea name="jcjg" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlans.jcjg}" /></textarea></td>
		</tr>
		<tr>
			<td class="t_title">5、问题叙述：</td>
			<td colspan="3" style="text-align:left;"><textarea name="wtxs" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlans.wtxs}" /></textarea></td>
		</tr>
		<tr>
			<td class="t_title">6、原因分析：</td>
			<td colspan="3" style="text-align:left;"><textarea name="yyfx" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlans.yyfx}" /></textarea></td>
		</tr>
		<tr>
			<td class="t_title">7、项目负责人：</td>
			<td style="text-align:left;">
			<input type="text" id="xmfzr" name="xmfzr" style="width:150px" value="<c:out value="${zlPdcaPlans.xmfzr}" />" class="easyui-validatebox text">
			<td class="t_title">8、参加人员：</td>
			<td style="text-align:left;">
			<input type="text" id="cjry" name="cjry" style="width:150px" value="<c:out value="${zlPdcaPlans.cjry}" />" class="easyui-validatebox text">
			</td>
		</tr>
		<tr>
			<td class="t_title"  colspan="4" style="text-align:left;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				9、是否展开调查与改进：
				<input type="radio" name="isPdca" value="1" <c:if test="${zlPdcaPlans.isPdca=='1'}">checked</c:if>/>展开PDCA调查&nbsp;&nbsp;
				<input type="radio" name="isPdca" value="0" <c:if test="${zlPdcaPlans.isPdca=='0'}">checked</c:if>/>偶发现一次，不需调查&nbsp;&nbsp;&nbsp;&nbsp;
				<select id="fuid" name="fuid"  style="width:140px"></select>
			</td>
		</tr>
		<tr>
			<td class="t_title" colspan="2">10、PDCA调查与改进步骤</td>
			<td class="t_title" colspan="2"></td>
		</tr>
	<c:forEach  var="zlPdcaPlansDetail"  items="${zlPdcaPlansDetailList}" varStatus="status">
	<input type="hidden" name="zlPdcaPlansDetailList[${status.index}].stepName" value="${zlPdcaPlansDetail.stepName}"/>
	<input type="hidden" name="zlPdcaPlansDetailList[${status.index}].stepNo" value="${zlPdcaPlansDetail.stepNo}"/>
	<tr>
		<td class="t_title" width="200">${zlPdcaPlansDetail.stepName}：</td>
		<td colspan="3">
		是否完成：<input type="checkbox" name="zlPdcaPlansDetailList[${status.index}].status"<c:if test="${zlPdcaPlansDetail.status==1}">checked="checked"</c:if> value="1"/>&nbsp;
		完成时间：<input type="text" name="zlPdcaPlansDetailList[${status.index}].wcDate" value="<fmt:formatDate value='${zlPdcaPlansDetail.wcDate}' pattern='yyyy-MM-dd' />" class="Wdate text" onclick="WdatePicker()"/>
		<%-- <nis:select name="zlPdcaPlansDetailList[${status.index}].status" dictcode="pdca_plans_status" value="${zlPdcaPlansDetail.status}" cssCls="easyui-validatebox" exp="required=\"true\""/>--%>		
		</td>
	</tr>
	<tr>
		<td colspan="4"><textarea name="zlPdcaPlansDetailList[${status.index}].stepContent" id="stepContent${status.index}"
				style="width: 95%; height: 60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlansDetail.stepContent}" /></textarea>
		</td>
	</tr>
	<script>
	KindEditor.ready(function(K) {
		//加载模板
		$('#fuid').combogrid({
			delay: 1000,    
		    mode: 'remote',
		    value:'${zlPdcaPlans.fuid}',
            idField:'fuid',
            panelWidth: 450,
            textField:'flowName', 	       
            url:'${webroot}/zlPdcaFlow/f_json/getAll.shtml',
            columns:[
            	[          
                 {field:'flowName',title:'模板名称',sortable:true,width:120},
                 {field:'flowSteps',title:'模板步骤',sortable:true,width:225}
                ]
            ],
    		onClickRow:function(rowIndex, rowData){	                
        		setFlow(rowData.fuid);
    		}   		
        });	
   	 	window.editor = K.create('#stepContent${status.index}', {
   			uploadJson   : '${webroot}/nyMessageTheme/f_json/upload.shtml',
   			allowFileManager : false,
   	   	    allowImageManager : true,
   	        allowImageUpload : true, 
			items:[
	       	'preview','justifyleft','justifycenter', 'justifyright',
	      		'justifyfull','clearhtml', 'quickformat', 'selectall', '|', 'fullscreen',
	      		'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'image',
	     			'italic', 'underline', 'strikethrough'
			],
			autoHeightMode : true,
			afterBlur: function(){this.sync();}
		});
	});
	</script>
	</c:forEach>
	</tbody>
	</table>
	<div class="footer">
		<input type="button" class="btn_save" id="changeFormSubmitBtn"
			onclick="$('#editFormZlPdcaPlans').submit()" value="保存">
	</div>
</form>
<script>


$(document).ready(
	function() {
		window.setTimeout(function() {
			Comm.form({
				id : 'editFormZlPdcaPlans',
				url : '${webroot}/zlPdcaPlans/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({title : '提示',msg : '操作成功！'});
					} else if (json.result === 'error') {
						parent.$.messager.show({title : '提示',msg : '操作失败！'});
					} else {
						parent.$.messager.show({title : '提示',msg : json.msg});
					}
				}});
		}, 100);
	});
</script>