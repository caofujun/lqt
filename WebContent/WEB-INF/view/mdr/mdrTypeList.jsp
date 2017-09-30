<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<body>
<form id="editMdr" method="post">
	<input type="hidden" id="_divId" name="divId" value="${param.divId}"/>
	<input type="hidden" id="zyid" name="zyid" value="${param.zyid}"/>
	<input type="hidden" id="testOrderNo" name="testOrderNo" value="${param.testOrderNo}"/>
	<input type="hidden" id="orderno" name="orderno" value="${param.orderno}"/>
	<input type="hidden" id="surveyDeptId" name="surveyDeptId" value="${param.surveyDeptId}"/>
	<input type="hidden" id="normItemId" name="normItemId" value="${param.normItemId}"/>
	<input type="hidden" id="normOrderno" name="normOrderno" value="${param.normOrderno}"/>
</form>
	<div class="easyui-panel" data-options="border:false" style="width: 100%;">
		<div>
			<table class="info_table">
				<tbody>
					<tr>
						<c:forEach items="${mdrTypeList}" var="resProp">
							<%-- 排除阴性的选项 --%>
							<c:if test="${resProp.dictCode!=-1}">
								<th style="width: 60px;"><a href="#" onclick="javascript:update_mdrType('${resProp.dictCode}')">${resProp.dictName}</th>
							</c:if>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
<script type="text/javascript">
	function update_mdrType(resProp){
		var zyid = "${param.zyid}";    //$("#zyid").val();
		var divId = "${param.divId}"     //$("#_divId").val();
		var normItemId = "${param.normItemId}"   //$("#normItemId").val();
		var normOrderno = "${param.normOrderno}" //$("#normOrderno").val();
		var testOrderNo = "${param.testOrderNo}"  //$("#testOrderNo").val();
		var orderno = "${param.orderno}"   //$("#orderno").val();
		var surveyDeptId = "${param.surveyDeptId}"   //$("#surveyDeptId").val();
		 $.ajax({
             url: '${webroot}/xn011Dclymx/f_view/mdr/type/update.shtml',
             type: 'post',
             data: { zyid: zyid,normItemId:normItemId,normOrderno:normOrderno,testOrderNo:testOrderNo,orderno:orderno,surveyDeptId:surveyDeptId,resProp:resProp},
             dataType: 'json',
             success : function(json) {
             	$.messager.show({title : '提示',msg : '操作成功！'});
             	//$('#' + viewMdr.panel).datagrid('collapseRow', index);
             	//console.log("..."+divId);
             	$("#"+divId).tooltip('hide');
             	//立即刷新会导致悬浮窗无法消失的问题，若有其他办法，replace this
             	setTimeout("viewMdr.query()",500); 
			}
 		}); 
	}
</script>
</body>
</html>