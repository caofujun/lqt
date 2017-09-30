<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<style>
	body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;overflow: hidden;}
</style>
<head>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<input type="hidden" id="reportFile" value="${reportFile}_DEPT"/>
<body class="easyui-layout">	
	<div data-options="region:'north',split:false,border:false" style="border-bottom-width: 1px;overflow: hidden;" >
	<div id="tb" class="datagrid-toolbar" >	
		<ul id="generic" class="tabs_a">	
			<nis:auth menuNo="E0101">
			<li><a <c:if test="${reportFile=='nis7/YYGRFBL'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/YYGRFBL&__bypagesize__=false">医院感染发病（例次）率</a></li>
			</nis:auth>
			<nis:auth menuNo="E0102">
			<li><a <c:if test="${reportFile=='nis7/YYGRXHLSS'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/YYGRXHLSS&__bypagesize__=false">医院感染现患（例次）率-实时</a></li>
			</nis:auth>
			<nis:auth menuNo="E0103">
			<li><a <c:if test="${reportFile=='nis7/YYGRXHLDC'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/YYGRXHLDC&__bypagesize__=false">医院感染现患（例次）率-调查</a></li>
			</nis:auth>
			<nis:auth menuNo="E0104">
			<li><a <c:if test="${reportFile=='nis7/CBLB'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/CBLB&__bypagesize__=false">医院感染病例漏报率</a></li>
			</nis:auth>
			<nis:auth menuNo="E0105">
			<li><a <c:if test="${reportFile=='nis7/TSLYJJCL'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/TSLYJJCL&__bypagesize__=false">多重耐药菌检出率</a></li>
			</nis:auth>
			<nis:auth menuNo="E0106">
			<li><a <c:if test="${reportFile=='nis7/TSLYJGRFBL'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/TSLYJGRFBL&__bypagesize__=false">多重耐药菌感染发生率</a></li>
			</nis:auth>
			<nis:auth menuNo="E0114">
			<li><a <c:if test="${reportFile=='nis7/TSLYJJCQK'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/TSLYJJCQK&__bypagesize__=false">特殊耐药检出情况</a></li>
			</nis:auth>
			<nis:auth menuNo="E0107">
			<li><a <c:if test="${reportFile=='nis7/KJYWSYL'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/KJYWSYL&__bypagesize__=false">住院患者抗菌药物使用率</a></li>
			</nis:auth>
			<nis:auth menuNo="E0108">
			<li><a <c:if test="${reportFile=='nis7/KJYWZLQSJL'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/KJYWZLQSJL&__bypagesize__=false">抗菌药物治疗前病原学送检率</a></li>
			</nis:auth>
			<nis:auth menuNo="E0109">
			<li><a <c:if test="${reportFile=='nis7/ILQKSSKJYWBWGR'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/ILQKSSKJYWBWGR&__bypagesize__=false">手术部位感染率</a></li>
			</nis:auth>
			<nis:auth menuNo="E0110">
			<li><a <c:if test="${reportFile=='nis7/ILQKSSKJYWYFSY'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/ILQKSSKJYWYFSY&__bypagesize__=false">手术抗菌药物预防使用率</a></li>
			</nis:auth>
			<nis:auth menuNo="E0111">
			<li><a <c:if test="${reportFile=='nis7/MLDCGGR'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/MLDCGGR&__bypagesize__=false">泌尿道插管相关泌尿系统感染发病率</a></li>
			</nis:auth>
			<nis:auth menuNo="E0112">
			<li><a <c:if test="${reportFile=='nis7/ZXJMCG'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/ZXJMCG&__bypagesize__=false">血管内导管相关血流感染发病率</a></li>
			</nis:auth>
			<nis:auth menuNo="E0113">
			<li><a <c:if test="${reportFile=='nis7/HXJFY'}">class="cur"</c:if> href="${webroot}/report/f_view/fineReportAdvancedFrame.shtml?reportFile=nis7/HXJFY&__bypagesize__=false">呼吸机相关肺炎发病率</a></li>			
			</nis:auth>
		</ul>
		<div class="clear"></div>		
	</div>
	</div>
	<div data-options="region:'west',border:false,title:''" style="width:250px;">
	<form id="searchform" name="searchform" method="post" action="${reportUrl}${reportFile}&__bypagesize__=${__bypagesize__}" target="reportFrame">
	<input type="hidden" id="userType" name="userType" value="${account.userType}" />
	<input type="hidden" id="doctorId" name="doctorId" value="${account.doctorId}" />
	<input type="hidden" id="scopeInfo" name="scopeInfo" value="${account.scopeInfo}" />
	<input type="hidden" id="dataScope" name="dataScope" value="${account.dataScope}" />
	<input type="hidden" id="urlPrefix" name="urlPrefix" value="${nisUrl}" />
	<table class="table_cx h_set" cellpadding="0" cellspacing="0">
		<tbody>
			<c:if test="${reportFile=='nis7/TSLYJJCL'}">
				<tr>
					<td class="t_title">时间选择：</td>
					<td>
						<select style="width:150px" id="dateType" name="dateType" class="easyui-combobox">
							<option value="2">检出时间</option>
							<option value="1">送检时间</option>
						</select>
					</td>
				</tr>
			</c:if>
			<c:if test="${reportFile=='nis7/ILQKSSKJYWBWGR'}">
				<tr>
					<td class="t_title">时间选择：</td>
					<td>
						<select style="width:150px" id="dateType" name="dateType" class="easyui-combobox">
							<option value="1">手术时间</option>
							<option value="2">出院时间</option>
							<option value="3">感染时间</option>
						</select>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="t_title">开始时间：</td>
				<td>
					<input type="text" id="startDate" name="startDate" value="${startDate}" style="width:150px"  class="Wdate text" onclick="WdatePicker()"  />
				</td>
			</tr>
			<tr>
				<td class="t_title">结束时间：</td>
				<td>
					<input type="text" id="endDate" name="endDate" value="${endDate}" style="width:150px" class="Wdate text" onclick="WdatePicker()" />
				</td>
			</tr>
			<c:choose>
			<c:when test="${empty statType}">
			<tr>
				<td class="t_title">统计方式：</td>
				<td>
					<nis:select id="statType" name="statType" exp="style='width: 150px;'" dictcode="stat_type"/>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<input type="hidden" id="statType" name="statType" value="${statType}" />
			</c:otherwise>
			</c:choose>
			<c:if test="${reportFile=='nis7/ILQKSSKJYWBWGR' or reportFile=='nis7/ILQKSSKJYWYFSY'}">
			<tr>
				<td class="t_title">切口等级：</td>
				<td>
					<select id="incisionGrade" name="incisionGrade" style="width:150px" class="easyui-combobox">
						<option value="">---不限---</option>
						<option value="0">零类</option>
						<option value="1">I类</option>
						<option value="2">II类</option>
						<option value="3">III类</option>
						<option value="4">IV类</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_title">归档状态：</td>
				<td>
					<label><input type="checkbox" id="status" checked name="status" value="4"/>已归档</label>
				</td>
			</tr>
			</c:if>
			<c:if test="${reportFile=='nis7/TSLYJGRFBL'}">
				<tr>
					<td class="t_title">检出菌类：</td>
					<td>
					<select style="width:152px" id="pathoType" name="pathoType" class="easyui-combobox">
						<option value="">全部</option>
						<option value="z">重点菌</option>
						<option value="f">非重点菌</option>
					</select>
					</td>
				</tr>
			</c:if>
			<c:if test="${reportFile=='nis7/TSLYJJCL'}">
				<tr>
					<td class="t_title">剔除重复：</td>
					<td>
						<label><input type="checkbox" id="sn" name="sn" value="1"/>是</label>
						<span class="ico_help"></span>
					</td>
				</tr>
			</c:if>
			<c:if test="${unitFlag=='1'}">
			<tr>
				<td class="t_title">院区：</td>
				<td>
					<div class="select_del">
					<input id="unitId" name="unitId" style="width:150px;"/>
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#unitId').combo('clear');"></a>
					</div>
				</td>
			</tr>
			</c:if>
			<tr>
				<td class="t_title">科室类型：</td>
				<td>
					<nis:select id="deptType" name="deptType" exp="style='width: 150px;'" dictcode="stat_dept_type"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-panel" style="padding:5px;width:220px;height:200px;<c:if test="${deptFlag=='0' and roleType=='clinical'}">display:none;</c:if>">
						<span>感染科室：</span>
						<ul id="ksTree"></ul>
					</div>
					<input type="hidden" id="statDept" name="statDept" />
					<input type="hidden" id="statDeptName" name="deptName" />
					<input type="hidden" id="statDeptClass" name="statDeptClass" />
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">				
				<a href="javascript:search.query();"><i class="icon iconfont">&#xe61e;</i><span>统计</span></a>
			</div>
		</div>
	</div>	
	</form>
	</div>
	<div data-options="region:'center',border:false" id="reportPanel" style="border-left-width: 1px;">
		<iframe width="100%" height="98%" id="reportFrame" name="reportFrame" allowtransparency scrolling="no" 
			<%-- <c:if test="${!empty reportFile}">
				 src="${reportUrl}${reportFile}_DEPT.cpt&__bypagesize__=${__bypagesize__}&unitId=-1&urlPrefix=${nisUrl}" 
			</c:if> --%>
		 frameborder="0"></iframe>
	</div>
	
	<script type="text/javascript">
		var reportType = '2';
		/**剔除重复规则显示**/
		$(function(){
            $('.ico_help').tooltip({
                position: 'right',
                content: '<p style="color:#333; width:300px; padding:10px;"><b>送检次数：</b>统计时间内，同一科室、同一患者、送检相同标本，只算一次送检；<br/><b>检出次数：</b>统计时间内，同一科室、同一患者、同一送检标本、检出相同细菌，只算一次检出；<br/><b>多耐次数：</b>统计时间内、同一科室、同一患者、同一送检标本、检出相同细菌、且均为多耐，只算最早检出的那一株。</p>',
                onShow: function(){
                    $(this).tooltip('tip').css({
                        backgroundColor: '#fafafa',
                        borderColor: '#ccc'
                    });
                }
            });
		 });
		
		var search = {
			//查询
			query : function() {
				var rf = "${reportFile}";
				if(rf){
					//报表名存在才查询
					if(!search.saveDate()){
						return;
					}
					var statType =  $('#statType').val();
					if(statType == 1){
						document.searchform.action = "${reportUrl}${reportFile}_DEPT.cpt&__bypagesize__=${__bypagesize__}";
						$('#reportFile').val('${reportFile}_DEPT');
					}
					if(statType == 2){
						document.searchform.action = "${reportUrl}${reportFile}_MONTH.cpt&__bypagesize__=${__bypagesize__}";
						$('#reportFile').val('${reportFile}_MONTH');
					}
					if(statType == 3){
						document.searchform.action = "${reportUrl}${reportFile}_TYPE.cpt&__bypagesize__=${__bypagesize__}";
						$('#reportFile').val('${reportFile}_TYPE');
					}
					
					<c:if test="${roleType=='clinical'}">
						if($('#statDept').val() == ''){
							alert("请选择统计科室！");
							return;
						}
					</c:if>
					document.searchform.submit();
				}
		    },
		    
			saveDate:function(){
				var startDate =  $('#startDate').val();
				var endDate = $('#endDate').val();
				if(startDate == ''){
					alert("请选择统计开始时间！");
					return false;
				}
				if(endDate == ''){
					alert("请选择统计结束时间！");
					return false;
				}
				 var d1 = new Date(startDate.replace(/\-/g, "\/"));  
				 var d2 = new Date(endDate.replace(/\-/g, "\/"));  
				 if(startDate!=""&&endDate!=""&&d2<d1){  
					  alert("结束时间不能小于开始时间！");  
					  return false;  
				 }
				
				if(startDate != "" && endDate != ""){
					$.ajax({
				     	type: "post",
				          url: "${webroot}/acAccountConfig/f_json/save.shtml",
				          data: {configKey:"reportStartDate",configValue:startDate}
				     });
					$.ajax({
				     	type: "post",
				          url: "${webroot}/acAccountConfig/f_json/save.shtml",
				          data: {configKey:"reportEndDate",configValue:endDate}
				     });
				}
				return true;
			}
		};
	
		
		
		$(document).ready(function () { 
			<c:if test="${unitFlag=='1'}">
				Csm.comboBox.unit({
					//【必传】控件名称
					id: 'unitId',
					value: '',
					flag: '1',
					callback: '0'
				});
			</c:if>
			
		    <c:if test="${empty reportFile}">
				//获取已授权的第一个报表
				var hf = $("#generic li a").eq(0).attr("href");
				if(hf){
					window.open(hf,"_self");
				}
		    </c:if>
		    
		    <c:if test="${not empty param.incisionGrade}">
				$("#incisionGrade").combobox("setValue","${param.incisionGrade}");
		    </c:if>
		    
		    <c:if test="${reportFile=='nis7/TSLYJGRFBL' or reportFile=='nis7/TSLYJJCL'}">
		    	$("#statType").append( "<option value=\"3\">按菌</option>" );
		    </c:if>
			
		    $("#ksTree").tree({
				url:'${webroot}/report/json/getTreeRoot.shtml',
				animate:true,
				loadFilter : function(data) {   
					var c = new Array();
					c.push(data);
					return c;
				},
				onSelect:function(rec){
					if($('#ksTree').tree('isLeaf',rec.target)){
						$("#statDept").val(rec.id);
						$("#statDeptName").val(encodeURI(rec.text));
						$("#statDeptClass").val("");
					}else if(rec.id == 0){
						$("#statDept").val("");
						$("#statDeptName").val("");
						$("#statDeptClass").val("");
					}else{
						$("#statDept").val("");
						$("#statDeptName").val(encodeURI(rec.text));
						$("#statDeptClass").val(rec.id);
					}
				},
				onLoadSuccess:function(rec){
					//临床端默认选择本科室
					<c:if test="${roleType=='clinical'}">
						try{
						var node = $('#ksTree').tree('find', '${curDeptId}');      //找到id为”tt“这个树的节点id为”1“的对象
						$('#ksTree').tree('select', node.target);     //设置选中该节点
						}catch(e){}						
					</c:if>
					
					window.setTimeout(function() {
						search.query();
					}, 200);
				}
			});
		});
	</script>
</body>
</html>