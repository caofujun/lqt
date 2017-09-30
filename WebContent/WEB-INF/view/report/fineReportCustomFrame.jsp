<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<style>
	body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;overflow: hidden;}
</style>
<head>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
</head>
<input type="hidden" id="reportFile" value="${reportFile}"/>
<body class="easyui-layout">	
	<div data-options="region:'west',border:false,title:''" style="width:250px;">
	<form id="searchform" name="searchform" method="post" action="${reportUrl}${reportFile}&__bypagesize__=${__bypagesize__}" target="reportFrame">
	<input type="hidden" id="userType" name="userType" value="${account.userType}" />
	<input type="hidden" id="doctorId" name="doctorId" value="${account.doctorId}" />
	<input type="hidden" id="scopeInfo" name="scopeInfo" value="${account.scopeInfo}" />
	<input type="hidden" id="dataScope" name="dataScope" value="${account.dataScope}" />
	<input type="hidden" id="urlPrefix" name="urlPrefix" value="${nisUrl}" />
	<table class="table_cx h_set" cellpadding="0" cellspacing="0">
		<tbody>
		    <c:if test="${fn:contains(searchTerms,'dateType1')}">
				<tr>
					<td class="t_title">时间选择：</td>
					<td>
						<select style="width:150px" id="dateType" name="dateType" class="easyui-combobox">
							<option value="1">在院时间</option>
						</select>
					</td>
				</tr>
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'dateType2')}">
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
		    <c:if test="${fn:contains(searchTerms,'dateType3')}">
				<tr>
					<td class="t_title">时间选择：</td>
					<td>
						<select style="width:150px" id="dateType" name="dateType" class="easyui-combobox">
							<option value="2">手术时间</option>
							<option value="1">出院时间</option>
						</select>
					</td>
				</tr>
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'dateType4')}">
				<tr>
					<td class="t_title">时间选择：</td>
					<td>
						<select style="width:150px" id="dateType" name="dateType" class="easyui-combobox">
							<option value="1">感染时间</option>
						</select>
					</td>
				</tr>
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'dateType')}">
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
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'urgentOpe')}">
			<tr>
				<td class="t_title">是否择期：</td>
				<td>
					<select style="width:150px" id="urgentOpe" name="urgentOpe">
						<option value="">---不限---</option>
						<option value="0">择期手术</option>
						<option value="1">急诊手术</option>
					</select>
				</td>
			</tr>
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'incisionGrade')}">
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
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'usePerpose')}">
			<tr>
				<td class="t_title">用药目的：</td>
				<td>
					<select id="usePerpose" name="usePerpose" class="easyui-combobox" style="width:150px" >
						<option value="">----不限----</option>
						<option value="1">治疗</option>
						<option value="2">预防</option>
						<option value="3">治疗+预防</option>
					</select>
				</td>
			</tr>
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'drugLine')}">
			<tr>
				<td class="t_title">药品类别：</td>
				<td>
					<div class="select_del">
						<select id="drugLine" name="drugLine" class="easyui-combobox" style="width:150px" >
							<option value="">----不限----</option>
							<option value="1">非限制类</option>
							<option value="2">限制类</option>
							<option value="3">特殊类</option>
						</select>
						<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#drugLine').combo('clear');"></a>
					</div>
				</td>
			</tr>
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'pathogenId')}">
				<tr>
					<td class="t_title">检出菌：</td>
					<td>
					<div class="select_del"><input id="pathogenId" name="pathogenId" style="width:150px"/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#pathogenId').combo('clear');"></a></div>
					</td>
				</tr>
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'bbsx')}">
				<tr>
					<td class="t_title">标本筛选：</td>
					<td>
						<select id="bbsx" name="bbsx"  class="easyui-combobox" style='width: 150px;'></select>
					</td>
				</tr>
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'sn')}">
				<tr>
					<td class="t_title">剔除重复：</td>
					<td>
						<label><input type="checkbox" id="sn" name="sn" value="1"/>是</label>
						<span class="ico_help"></span>
					</td>
				</tr>
			</c:if>
			<c:if test="${unitFlag=='1' and fn:contains(searchTerms,'unitId')}">
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
		    <c:if test="${fn:contains(searchTerms,'deptType')}">
			<tr>
				<td class="t_title">科室类型：</td>
				<td>
					<nis:select id="deptType" name="deptType" exp="style='width: 150px;'" dictcode="stat_dept_type"/>
				</td>
			</tr>
			</c:if>
		    <c:if test="${fn:contains(searchTerms,'statDept')}">
				<tr>
					<td colspan="2">
						<div class="easyui-panel" style="padding:5px;height:300px;">
							<span>科室：</span>
							<ul id="ksTree"></ul>
						</div>
						<input type="hidden" id="statDept" name="statDept" />
						<input type="hidden" id="statDeptName" name="deptName" />
						<input type="hidden" id="statDeptClass" name="statDeptClass" />
					</td>
				</tr>
			</c:if>
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
			<c:if test="${!empty reportFile}">
				 src="${reportUrl}${reportFile}.cpt&__bypagesize__=${__bypagesize__}&unitId=-1&urlPrefix=${nisUrl}" 
			</c:if>
		 frameborder="0"></iframe>
	</div>
	
	<script type="text/javascript">
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
	
		function updateJcj(){
			$('#pathogenId').combogrid({
				url: '${webroot}/xn002Byt/f_view/queryCount.shtml?startDate='+$('#startDate').val()+'&endDate='+$('#endDate').val()+'&page=1&size=200'
			});
		};
		
		var search = {
			//查询
			query : function() {
				var rf = "${reportFile}";
				if(rf){
					//报表名存在才查询
					if(!search.saveDate()){
						return;
					}
					document.searchform.action = "${reportUrl}${reportFile}.cpt&__bypagesize__=${__bypagesize__}";
					$('#reportFile').val('${reportFile}');
					document.searchform.submit();
				}
		    },
		    
			saveDate:function(){
				var startDate =  $('#startDate').val();
				var endDate = $('#endDate').val();
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
			<c:if test="${unitFlag=='1' and fn:contains(searchTerms,'unitId')}">
				Csm.comboBox.unit({
					//【必传】控件名称
					id: 'unitId',
					value: '',
					flag: '1',
					callback: '0'
				});
			</c:if>

		    <c:if test="${fn:contains(searchTerms,'statDept')}">
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
				}
			});
			</c:if>
			

		    <c:if test="${fn:contains(searchTerms,'pathogenId')}">
			$('#pathogenId').combogrid({
				delay: 1000,    
			    mode: 'remote',
			    loadMsg : '正在查询中...',
		        idField:'pathogenId',
		        panelWidth: 150,
		        panelHeight: 230,
		        value : "${pathogenId}",
		        textField:'pathogenName',
				url: '${webroot}/xn002Byt/f_view/queryCount.shtml?startDate='+$('#startDate').val()+'&endDate='+$('#endDate').val()+'&page=1&size=200',
		        columns:[
		        	[
		             {field:'pathogenName',title:'名称',sortable:true,width:150},
		            ]
		        ]
			});
			$('#pathogenId').combo('clear');
			</c:if>
			

		    <c:if test="${fn:contains(searchTerms,'bbsx')}">
			$('#bbsx').combobox({
			    url:'${webroot}/nyBbDict/f_json/queryList.shtml',
			    valueField:'bbid',
			    textField:'bbmc',
			    value:'${code}',
			    multiple:true
			});			
			$("#bbsx").combobox("setValue","${code}");
			</c:if>

			window.setTimeout(function() {
				search.query();
			}, 200);
		});
	</script>
</body>
</html>