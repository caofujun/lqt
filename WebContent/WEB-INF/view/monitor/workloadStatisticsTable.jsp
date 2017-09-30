<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<style>
	.statisticsTablePanel{margin:0px auto;padding:0px 0px;}
	.statisticsTable{border:1px solid #fff;text-align: center;}
	.statisticsTable thead tr{}
	.statisticsTable thead tr th{height:30px;color:rgb(255,255,255);font-family:宋体;padding:0px 20px;border:1px solid #dedede;background-color: #969696;font-weight: bold; }
	.statisticsTable tbody tr td{height:30px;color:rgb(68,68,68);border:1px solid #dedede;}
</style>

<div class="statisticsTablePanel">
	<input id="qsd" type="hidden" value='<fmt:formatDate value="${qsd}" pattern="yyyy-MM-dd"/>'/>
	<input id="qed" type="hidden" value='<fmt:formatDate value="${qed}" pattern="yyyy-MM-dd"/>'/>
	<table class="statisticsTable">
		<thead>
			<tr>
				<th rowspan="2" style="width:150px;">人员</th>
				<th rowspan="2">审卡例次数</th>
				<th rowspan="2">预警处理例次数</th>
				<th colspan="4">预警处理分类</th>
			</tr>
			<tr>
				<th>确认院感</th>
				<th>定植菌</th>
				<th>列为社感</th>
				<th>排除</th>
			</tr>
		</thead>
		<tbody>
			<c:set value="0" var="clzrs" />
			<c:set value="0" var="ygyqr" />
			<c:set value="0" var="dzj" />
			<c:set value="0" var="lwsg" />
			<c:set value="0" var="pc" />
			<c:set value="0" var="lcscount" />
			<c:forEach var="wsl" items="${wsl}">
				<tr>
					<c:set var="clzrs" value="${clzrs+wsl.clzrs}"></c:set>
					<c:set var="ygyqr" value="${ygyqr+wsl.ygyqr}"></c:set>
					<c:set var="dzj" value="${dzj+wsl.dzj}"></c:set>
					<c:set var="lwsg" value="${lwsg+wsl.lwsg}"></c:set>
					<c:set var="pc" value="${pc+wsl.pc}"></c:set>
					<c:set var="ycllcs" value="${wsl.ygyqr+wsl.dzj+wsl.lwsg+wsl.pc}"/>
					<c:set var="lcscount" value="${lcscount+ycllcs}"></c:set>
					<td>${wsl.pcr}<input type="hidden" name="clrid" value="${wsl.pcrid}"/></td>
					<td><a href="javascript:void(0);" onclick="dialogTitle='审卡例次数';workloadTable.showMe('${wsl.pcrid}',0);">${wsl.clzrs}</a></td>
					<td><a href="javascript:void(0);" onclick="dialogTitle='预警处理例次数';workloadTable.showMe('${wsl.pcrid}',-1);">${ycllcs}</a></td>
					<td><a href="javascript:void(0);" onclick="dialogTitle='已确认预警例次数';workloadTable.showMe('${wsl.pcrid}',1);">${wsl.ygyqr}</a></td>
					<td><a href="javascript:void(0);" onclick="dialogTitle='列为定植菌预警例次数';workloadTable.showMe('${wsl.pcrid}',2);">${wsl.dzj}</a></td>
					<td><a href="javascript:void(0);" onclick="dialogTitle='列为社感预警例次数';workloadTable.showMe('${wsl.pcrid}',3);">${wsl.lwsg}</a></td>
					<td><a href="javascript:void(0);" onclick="dialogTitle='已排除预警例次数';workloadTable.showMe('${wsl.pcrid}',4);">${wsl.pc}</a></td>
				</tr>
			</c:forEach>
			<tr style="height: 33px;color: #333333;font-weight: bold;background-color:#ccccff;">
				<td>全部</td>
				<td>${clzrs}</td>
				<td>${lcscount}</td>
				<td>${ygyqr}</td>
				<td>${dzj}</td>
				<td>${lwsg}</td>
				<td>${pc}</td>
			</tr>
		</tbody>
	</table>
</div>
<div id="workloadDetailPanel">
	<div id="workloadDetail"></div>
</div>
<script>
var dialogTitle = "";
	var workloadTable = {
		showMe : function(clrid,state){
			$("#workloadDetailPanel").dialog({
				title: dialogTitle,
				width: 900,
			    height: 500,
			    closed: true,
			    cache: false,
			    modal: true
			});
			$("#workloadDetail").datagrid({
				url:"",
				fit:true,
				singleSelect:true,
				height:300,
				columns:[[
					{field:'zyid',title:'${patientZyTitle}',width:90,formatter:function(value,row,index){
						return "<a href='javascript:void(0);' onclick='workloadTable.showDetail(\""+value+"\")'>"+value+"</a>";
					}},
					{field:'pinfo',title:'患者信息',width:95,formatter:function(value,row,index){
						return row.patientName+","+row.sex+","+row.age+row.ageUnit;	
					}},
					{field:'deptName',title:'当前科室',width:120},
					{field:'inHospAt',title:'入院日期',width:110},
					{field:'startAt',title:'预警日期',width:75,formatter:function(value,row,index){
						if(!value){
							return "";
						}else{
							return value.substring(0,10);
						}
					}},
					{field:'infectName',title:'感染诊断',width:150},
					{field:'clrname',title:'处理人员',width:80},
					{field:'dataDate',title:'处理时间',width:110,formatter:function(value,row,index){
						if(!value){
							return "";
						}else{
							return value.substring(0,16);
						}
					}}
				]],
			    rownumbers:true
			});
			$("#workloadDetailPanel").dialog("open");
			workloadTable.detailQuery(clrid,state);
		},
		detailQuery : function(clrid,state){
			$('#workloadDetail').datagrid({
				url:'${webroot}/gr002YsgrMx/f_view/workloadDetail.shtml',
				queryParams: {
					'queryStartDate':$("#qsd").val(),
					'queryEndDate':$("#qed").val(),
		     		'userId': clrid,
		     		'state':state
		        },
		        onLoadSuccess:function(data){
		        	//这种方式设置后不知为什么就不能拖动了
		        	//$('#workloadDetailPanel').panel({title: dialogTitle+data.total+"例"});
		        	$('#workloadDetailPanel').parent().find(".panel-title").eq(0).html(dialogTitle+data.total+"例");
		        }
			});
		},
		showDetail : function(zyid){
		    parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
	    }
	}
	$(function(){
			
	});
</script>