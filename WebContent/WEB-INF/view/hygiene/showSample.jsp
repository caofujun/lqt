<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<body>
	<div class="easyui-panel" data-options="border:false" style="width: 100%;">
		<div>
			<table class="info_table">
				<tbody>
					<tr>
						<th style="width: 60px;">检验人员：</th>
						<td style="width: 90px;">
							<c:out value="${hw102Jcdmx.testByName}" />
						</td>
						<th style="width: 60px;">菌落数：</th>
						<td>
							<c:out value="${hw102Jcdmx.resultPathoNum}" />
						</td>
					</tr>
					<tr>
						<th>检出细菌：</th>
						<td colspan="3">
							<c:out value="${hw102Jcdmx.checkOutStr}" />
						</td>
					</tr>
					<tr>
						<th>备注说明：</th>
						<td colspan="3">
							<c:out value="${hw102Jcdmx.memo}" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="easyui-panel" data-options="border:false" style="width: 350px;max-height:195px;">
			<table class="list_table">
				<tr>
					<th width="70">样本编号</th>
					<th width="100">监测指标</th>
					<th width="70">结果</th>
					<th width="80">执行标准</th>
				</tr>
				<c:forEach items="${hw103List}" var="hw103">
					<tr>
						<td style="width: 70px;" align="center"><c:out value="${hw103.resultId}" /></td>
						<td style="width: 100px;"><span title="${hw103.itemName}" class="easyui-tooltip" data-options="position: 'top'"><c:out value="${hw103.itemName}" /></span></td>
						<td style="width: 70px;" align="center"><c:out value="${hw103.result}" /></td>
						<td style="width: 80px;"><span title="<c:out value="${hw103.resultCondition}" /><c:out value="${hw103.resultCriterion}" /><c:out value="${hw103.resultUnit}" />" class="easyui-tooltip" data-options="position: 'top'"><c:out value="${hw103.resultCondition}" /><c:out value="${hw103.resultCriterion}" /><c:out value="${hw103.resultUnit}" /></span></td>
					</tr>
				</c:forEach>
			</table>
			<div id="sampleListPanel_${param.id}"></div>
		</div>
	</div>

<script type="text/javascript">
/* $('#sampleListPanel_${param.id}').datagrid({
	fit: false,
	url: '${webroot}/hw103Jcdjg/f_json/findListByHw102Id.shtml',
	queryParams: { hw102Id: '${param.id}' },
    nowrap: true,
    autoRowHeight: true,
    striped: true,
    fitColumns: false,
    remoteSort: false,
    singleSelect: true,
    width : 350,
    height : 180,
    columns:[ 
       	[
			{field:'resultId',title:'样本编号',sortable:true,width:90},
            {field:'itemName',title:'监测指标',sortable:true,width:50},
            {field:'resultCriterion',title:'结果',sortable:true,width:90},
            {field:'condition',title:'执行标准',sortable:true,width:80}
        ]
    ]
}); */
</script>
</body>
</html>