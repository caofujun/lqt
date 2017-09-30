<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<style>
	body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;overflow: hidden;}
</style>
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<head>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
</head>
<body>
<div class="easyui-layout" style="width: 100%;height: 100%;">
	<div data-options="region:'west',border:false,title:''" style="width:270px; border-right-width: 1px;">
	<form id="searchform${id}" method="post" action="${reportUrl}${reportFile}.cpt&__bypagesize__=${__bypagesize__}" target="reportFrame${id}">
	<input type="hidden" id="userType" name="userType" value="${account.userType}" />
	<input type="hidden" id="doctorId" name="doctorId" value="${account.doctorId}" />
	<input type="hidden" id="scopeInfo" name="scopeInfo" value="${account.scopeInfo}" />
	<input type="hidden" id="dataScope" name="dataScope" value="${account.dataScope}" />
	<input type="hidden" id="urlPrefix" name="urlPrefix" value="${nisUrl}" />
	<table class="table_cx h_set" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">时间选择：</td>
				<td>
					<select style="width:150px" id="dateType" name="dateType" class="easyui-combobox">
						<option value="1">手术时间</option>
						<option value="2">出院时间</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_title">开始时间：</td>
				<td>
					<input type="text" id="startDate${id}" name="startDate" value="${startDate}" style="width:138px"  class="Wdate text" onclick="WdatePicker()"  />
				</td>
			</tr>
			<tr>
				<td class="t_title">结束时间：</td>
				<td>
					<input type="text" id="endDate${id}" name="endDate" value="${endDate}" style="width:138px" class="Wdate text" onclick="WdatePicker()" />
				</td>
			</tr>
			<tr>
				<td class="t_title">切口等级：</td>
				<td>
					<select id="incisionGrade${id}" name="incisionGrade" style="width:150px" class="easyui-combobox">
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
				<td class="t_title">用药目的：</td>
				<td>
					<select id="usePurpose${id}" name="usePurpose" class="easyui-combobox" style="width:150px" >
						<option value="">----不限----</option>
						<option value="1">治疗</option>
						<option value="2">预防</option>
						<option value="3">预防+治疗</option>
					</select>
				</td>
			</tr>
			<c:if test="${unitFlag=='1'}">
			<tr>
				<td class="t_title">院区：</td>
				<td>
					<div class="select_del">
					<input id="unitId${id}" name="unitId" style="width:150px;"/>
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#unitId${id}').combo('clear');"></a>
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
						<span>手术科室：</span>
						<ul id="ksTree${id}"></ul>
					</div>
					<input type="hidden" id="statDept${id}" name="statDept" />
					<input type="hidden" id="statDeptName${id}" name="deptName" />
					<input type="hidden" id="statDeptClass${id}" name="statDeptClass" />
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
			<a href="javascript:query${id}();"><i class="icon iconfont">&#xe61e;</i><span>统计</span></a>
			</div>
		</div>
	</div>	
	</form>
	</div>
	<div data-options="region:'center',border:false" id="reportPanel">		
		<iframe width="100%" height="99%" id="reportFrame${id}" name="reportFrame${id}" allowtransparency scrolling="no" frameborder="0"></iframe>
	</div>
	
	<script type="text/javascript">	
		function query${id}() {
			if(!saveDate${id}()){
				return;
			}
			<c:if test="${roleType=='clinical'}">
				if($('#statDept${id}').val() == ''){
					alert("请选择统计科室！");
					return;
				}
			</c:if>
			$('#searchform${id}').attr("action","${reportUrl}${reportFile}.cpt&__bypagesize__=${__bypagesize__}");
			$('#searchform${id}').submit();
	    }
	    
		function saveDate${id}(){
			var startDate =  $('#startDate${id}').val();
			var endDate = $('#endDate${id}').val();
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
			
			
		$(document).ready(function () {
			<c:if test="${unitFlag=='1'}">
				Csm.comboBox.unit({
					//【必传】控件名称
					id: 'unitId${id}',
					value: '',
					flag: '1',
					callback: '0'
				});
			</c:if>
			
			Csm.combogrid.bbDict({
				//【必传】控件名称
				id: 'bbsx${id}'
			});
		    
		    <c:if test="${not empty param.incisionGrade}">
			window.setTimeout(function() {
		    	$("#incisionGrade${id}").combobox("setValue","${param.incisionGrade}");
		    }, 100);
		    </c:if>
		    
		    <c:if test="${not empty param.usePurpose}">
			window.setTimeout(function() {
		    	$("#usePurpose${id}").combobox("setValue","${param.usePurpose}");
		    }, 100);
		    </c:if>
		    
		    $("#ksTree${id}").tree({
				url:'${webroot}/report/json/getTreeRoot.shtml',
				animate:true,
				loadFilter : function(data) {   
					var c = new Array();
					c.push(data);
					return c;
				},
				onSelect:function(rec){
					if($('#ksTree${id}').tree('isLeaf',rec.target)){
						$("#statDept${id}").val(rec.id);
						$("#statDeptName${id}").val(encodeURI(rec.text));
						$("#statDeptClass${id}").val("");
					}else if(rec.id == 0){
						$("#statDept${id}").val("");
						$("#statDeptName${id}").val("");
						$("#statDeptClass${id}").val("");
					}else{
						$("#statDept${id}").val("");
						$("#statDeptName${id}").val(encodeURI(rec.text));
						$("#statDeptClass${id}").val(rec.id);
					}
				},
				onLoadSuccess:function(rec){
					//临床端默认选择本科室
					<c:if test="${roleType=='clinical'}">
						try{
						var node = $('#ksTree${id}').tree('find', '${curDeptId}');      //找到id为”tt“这个树的节点id为”1“的对象
						$('#ksTree${id}').tree('select', node.target);     //设置选中该节点
						}catch(e){}						
					</c:if>
					
					window.setTimeout(function() {
						$('#searchform${id}').submit();
					}, 200);
				}
			});
		});
	</script>
</div>
</body>
</html>