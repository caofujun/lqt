<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>报告卡信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<!-- 以单独页面加载时才启用以下js -->
<c:if test="${param.isSeparatePage eq 1}">
<script type="text/javascript" src="${webroot}/resources/js/monitor/reportCards.js?${version}"></script>
<script type="text/javascript">
var queryType = '${param.isSeparatePage}';
if (!queryType || queryType.length > 0) {
	reportCards.queryType = queryType;
} else {
	reportCards.queryType = '1';
}
</script>
</c:if>
<body>
	<div class="h_title" style="margin-right:20px;">
		<span style="margin-left:15px;"><b>报告科室：</b><c:out value="${bk001Sbk.reportDeptName}" /></span>
		<span style="margin-left:30px;"><b>上报医生：</b><c:out value="${bk001Sbk.reportDrName}" />（<fmt:formatDate value="${bk001Sbk.reportAt}" pattern="yyyy-MM-dd HH:mm" />）</span>
		<c:if test="${bk002Grzd.authStatus ne 0}">
			<span style="margin-left:30px;"><b>审核人：</b><c:out value="${bk002Grzd.authUsername}" />（<fmt:formatDate value="${bk002Grzd.authAt}" pattern="yyyy-MM-dd HH:mm" />）</span>
		</c:if>
		<c:if test="${bk002Grzd.authStatus == 2}">
			<span style="margin-left:15px;"><b>退卡原因：</b><font color="red" style="font-weight: bolder;"><c:out value="${bk002Grzd.returnReason}" /></font></span>
		</c:if>
		<c:if test="${bk002Grzd.authStatus == 3}">
			<span style="margin-left:15px;"><b>删卡原因：</b><font color="red" style="font-weight: bolder;"><c:out value="${bk002Grzd.delReason}" /></font></span>
		</c:if>
		<c:if test="${bk002Grzd.isPrint eq '1'}">
			<span class="mark">已打印</span>
		</c:if>
		<span class="btn_r_c">
			<c:if test="${ysbcurl!='0'}">
			<a href="javascript:void(0)"  onclick="reportCards.ysbc('${ysbcurl}')" class="a_icon_c green" title="原始病程" ><i class="icon nisfont">&#xe624;</i></a>
           	</c:if>
           	<c:if test="${!canEdit && bk001Sbk.isDel != '1' && bk002Grzd.authStatus != '2' && clinical=='1'}">
			<a href="javascript:reportCards.toOutcome('${bk002Grzd.relid}');" class="a_icon_c orange" title="转归"><i class="icon nisfont">&#xe6c0;</i></a>
			</c:if>
			<c:if test="${canAudit}">
			<a href="javascript:reportCards.toAudit('${bk001Sbk.relid}');" class="a_icon_c" title="审核"><i class="icon iconfont">&#xe607;</i></a>
			</c:if>
			<c:if test="${bk002Grzd.authStatus == 2}">
			<a href="javascript:reportCards.toReturn('${bk001Sbk.relid}');" class="a_icon_c" onclick="" title="撤销退卡"><i class="icon nisfont">&#xe686;</i></a>
			</c:if>
			<c:if test="${canEdit}">
				<a href="javascript:reportCards.toEdit('${bk001Sbk.relid}');" class="a_icon_c green"  title="修改"><i class="icon iconfont fax">&#xe601;</i></a>
			</c:if>
			<c:if test="${canDelete}">
			<a href="javascript:reportCards.toDelete('${bk001Sbk.relid}', '${bk001Sbk.zyid}');" class="a_icon_c red"  title="删除"><i class="icon iconfont fax">&#xe62b;</i></a> 
			</c:if>
			<a href="javascript:reportCards.printReportInfo('${bk001Sbk.relid}', '${bk001Sbk.zyid}', '${bk001Sbk.patientName}','${bk002Grzd.relid}');" class="a_icon_c blue"  title="打印"><i class="icon iconfont">&#xe604;</i></a>
		</span>
	</div>
	<div class="patient_infor" style="min-width:760px;margin: 6px 20px 6px 10px;">
		<table class="info_table">
			<tbody>  
		        <tr>
		        	<th>姓名：</th>
		            <td nowrap="nowrap">
		            	<c:out value="${bk001Sbk.patientName}" />
		            </td>
		        	<th>${patientZyTitle}：</th>
		            <td nowrap="nowrap">
		            	<a href="javascript:void(0);" class="underline" onclick="reportCards.patientInfo('${bk001Sbk.zyid}');"><c:out value="${patientZyValue == 'patientId'?bk001Sbk.patientId:bk001Sbk.zyid}" /></a>
		            </td>
		            <th>入院日期：</th>
		            <td nowrap="nowrap">
		            	<fmt:formatDate value="${bk001Sbk.inHospAt}" pattern="yyyy-MM-dd HH:mm" />
		            </td>
		        	<th>入院诊断：</th>
		            <td style="min-width: 60px;">
		            	<c:out value="${bk001Sbk.ryzd}" />
		            </td>
		        </tr>
		        <tr>
		        	<th>年龄：</th>
		            <td>
		            	<c:out value="${bk001Sbk.age}" /><c:out value="${bk001Sbk.ageUnit}" />
		            </td>
		            <th>性别：</th>
		            <td>
		            	<c:out value="${bk001Sbk.sex}" />
		            </td>
		        	<th>主管医生：</th>
		            <td>
		            	<c:out value="${bk001Sbk.chargeDrName}" />
		            </td>
		            <th>疾病名称：</th>
		            <td colspan="3">
		            	<c:out value="${bk001Sbk.jbzd}" />
		            </td>
		        </tr>
		        <c:if test="${!empty bk001Sbk.outAt}">
		        <tr>
		        	<th>出院日期：</th>
		        	<td>
		        		<fmt:formatDate value="${bk001Sbk.outAt}" pattern="yyyy-MM-dd HH:mm" />
		        	</td>
		        </tr>
		        </c:if>
		    </tbody>
		</table>
	</div>
	<div id="id_grbw_${bk001Sbk.relid}" style="margin:6px 10px 0px 10px;">		           
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">诊断信息</span>							
			</div>	
			<table class="info_table">
				<tbody>  
			        <tr>
			            <th>感染类型：</th>
			            <td nowrap="nowrap">
			            	<c:out value="${bk002Grzd.infectTypeName}" />
			            </td>
			            <th>感染日期：</th>
			            <td nowrap="nowrap">
			            	<fmt:formatDate value="${bk002Grzd.infectDate}" pattern="yyyy-MM-dd" />
			            </td>
			            <th>感染诊断：</th>
			            <td>
			            	<c:out value="${bk002Grzd.infectDiagnName}" />
			            </td>
			            <th>审核人：</th>
			            <td nowrap="nowrap" style="min-width: 60px;">
			            	<c:out value="${bk002Grzd.authUsername}" />
			            </td>
			        </tr>
			        <tr>
			        	<th>上报类型：</th>
			            <td>
			            	<c:out value="${bk002Grzd.bkTypeName}" />
			            </td>
			            <th>确诊日期：</th>
			            <td>
			            	<fmt:formatDate value="${bk002Grzd.confirmDt}" pattern="yyyy-MM-dd" />
			            </td>
			            <th>感染科室：</th>
			            <td>
			            	<c:out value="${bk002Grzd.infectDeptName}" />
			            </td>
			            <th>相关插管：</th>
			            <td>
			            	<c:out value="${bk002Grzd.relation}" />
			            </td>
			        </tr>
			        <tr>
			            <th>感染转归：</th>
			            <td id="id_jbzg">
			            	<c:out value="${bk002Grzd.jbzg}" />
			            </td>
			            <th>转归日期：</th>
			            <td id="id_jbzgDate">
			            	<fmt:formatDate value="${bk002Grzd.jbzgDate}" pattern="yyyy-MM-dd" />
			            </td>
	            		<th>手术名称：</th>
			            <td>
			            	<c:out value="${bk002Grzd.opeName}" />
			            </td>
	            		<th>切口类型：</th>
			            <td>
			            	<c:out value="${bk002Grzd.memo}" />
			            </td>
			        </tr>
			    </tbody>
			</table>
		</div>
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">易感因素</span>				
			</div>										
			<div class="gr_label" id="id_ygys_${bk001Sbk.relid}">				
				<c:forEach items="${bk003List}" var="bk003Ygys">
					<span><c:out value="${bk003Ygys.factorName}" /></span>
				</c:forEach>						
			</div>
		</div>
		<div id="id_zbj_${bk001Sbk.relid}">
		
		</div>
		<div id="id_kjyw_${bk001Sbk.relid}">
		
		</div>
		<c:if test="${ISCGSY != 0}">
		<div id="id_cgsy_${bk001Sbk.relid}">
		</div>
		</c:if>
		<!-- 历史报卡明细 -->
		<div id="id_lsbkmx_${bk002Grzd.refid }">
		</div>
<!-- 		<div class="card_cont"> -->
<!-- 			<div class="card_cont_h"> -->
<!-- 				<span class="card_c_h_text">抗菌药物使用</span> -->
<!-- 			</div> -->
<!-- 			<div class="byt_table"> -->
<%-- 				<div id="id_kjywsy_${bk002Grzd.relid}"></div> --%>
<!-- 			</div> -->
<!-- 		</div> -->
	</div>
	<div id="returnDialog" class="easyui-dialog" title="撤销退卡" style="width:400px;height:200px;" data-options="closed: true,cache: false,modal:true">
		<div style="text-align: center;padding-top:30px;">取消退卡的报卡可以在未审核中查询，可进行审核处理。</div>
		<input type="hidden" id="relid_hid" />
		<div class=" dialog_footer" style="width: 100%;text-align: center;margin-top: 50px;">
			<div class="footer_btn" >
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="returnCard();" class="no_ico"><span>撤销退卡</span></a>
				</div>	
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="$('#returnDialog').dialog('close')" class="no_ico"><span>暂不撤销</span></a>
				</div>			
			</div>
		</div>
	</div>
<script type="text/javascript">
	var hygiene = {
		showMdr:function(testOrderNo,pathogenSn){
			 Comm.dialogGlobal({
		        	url:"${webroot}/xn011Dclymx/f_view/mdrDetail.shtml?testOrderNo="+testOrderNo+"&pathogenSn="+pathogenSn,
		            title: "多耐详情",
		            width:500,
		            height:360,
		            type:"iframe",
		            parent:this
		        });
	   },
	};
	$(document).ready(function () {
			$.ajax({
	            url: '${webroot}/bk001Sbk/f_json/findPathogenDetection.shtml',
	            type: 'post',
	            data: { refid: '${bk002Grzd.relid}',  infectPartId: '${bk002Grzd.infectDiagnId}'},
	            dataType: 'json',
	            success : function(json) {
	            	//改写成不显示药敏结果
	            	var td1col = 0,
	            		gr10Id = '',
	            		htmlStr = '',
	            		tempStr1 = '', //上一轮的标本
	            		tempStr2 = ''; //上一轮的病原体
	            	for (var i=0; i<json.length; i++) {
	            		var gr010 = json[i];
	            		if (i === 0) {
	            			gr10Id = gr010.st9Id;
	            		}
	           			if (gr10Id != gr010.st9Id && i != 0) {
	           				gr10Id = gr010.st9Id;
	           				htmlStr += tempStr1 + tempStr2.substring(4, tempStr2.length);
	           				tempStr2 = '';
	           				td1col = 0;
	           			}
	           			
	           			tempStr2 += '<tr><td>' + pathogens.getNotNullStr(gr010.pathoName) + '</td><td><a href="javascript:hygiene.showMdr(\''+pathogens.getNotNullStr(gr010.testNo)+'\',\''+pathogens.getNotNullStr(gr010.pathogenSn)+'\');">'+pathogens.getNotNullStr(gr010.resPropName) +'</a></td></tr>';
	           			td1col ++;
	       				tempStr1 = '<tr><td rowspan="' + td1col + '">' + gr010.sampleName+ '</td><td rowspan="' + td1col + '">' +  pathogens.gethMdDateStr(gr010.submiAtStr) + '</td><td rowspan="' + td1col + '">' + pathogens.gethMdDateStr(gr010.testDate) + '</td>';
						            		
	        			if (i == json.length - 1){
	            			htmlStr += tempStr1 + tempStr2.substring(4, tempStr2.length);
	            			htmlStr = '<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">致病菌</span></div><div class="byt_table"><table class="stand_table" style="width: 100%; table-layout: fixed;"><thead><tr><th>标本名称</th><th>送检时间</th><th>检出时间</th><th>病原体</th><th>耐药情况</th></tr></thead><tbody>' + htmlStr + '</tr></tbody></table></div></div>';
	            		}
	            	}
	            	if (htmlStr.length > 0) {
	            		$('#id_zbj_${bk001Sbk.relid}').append(htmlStr);
	            	} else {
	            		$('#id_zbj_${bk001Sbk.relid}').append('<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">致病菌</span></div><div class="byt_table"></div></div>');
	            	}
				}
			});
			$.ajax({
	            url: '${webroot}/gr016BkKjyw/f_json/queryGr016BkKjyw.shtml',
	            type: 'post',
	            data: { refid: '${bk002Grzd.refid}'},
	            dataType: 'json',
	            success : function(json) {
	            	//console.log(json);
	            	var td1col = 0,
	            		gr16Id = '',
	            		htmlStr = '',
	            		tempStr2 = '',
	            		rows = "";
	            	for (var i=0; i<json.length; i++) {
	            		var gr016 = json[i];
	            		if (i === 0) {
	            			gr16Id = gr016.relid;
	            		}
	           			if (gr16Id != gr016.relid && i != 0) {
	           				gr16Id = gr016.relid;
	           				/* htmlStr += tempStr1 + tempStr2.substring(4, tempStr2.length);
	           				tempStr2 = '';
	           				td1col = 0; */
	           			}
	           			tempStr2 += '<td>' + pathogens.getNotNullStr(gr016.sypc) + '</td><td>' +  pathogens.getNotNullStr(gr016.preYymd) + '</td>';
// 	           			td1col ++;
	       				tempStr1 = '<td>' + gr016.orderName+ '</td><td >' +  pathogens.gethMdDateStr(gr016.orderAt) + '</td><td >' + pathogens.gethMdDateStr(gr016.stopAt) + '</td><td>' + pathogens.getNotNullStr(gr016.days) + '</td><td >' + pathogens.getNotNullStr(gr016.adminRouteName) + '</td><td>' + pathogens.getNotNullStr(gr016.dose) + '</td>';
        				rows += "<tr>"+tempStr1 + tempStr2+"</tr>";
        				tempStr2="";
        				tempStr1="";
	            	}
           			htmlStr = '<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">抗菌药物使用</span></div><div class="byt_table"><table class="stand_table" style="width: 100%; table-layout: fixed;"><thead><tr><th>抗菌药物</th><th>开嘱时间</th><th>停嘱时间</th><th>用药天数</th><th>给药路径</th><th>剂量</th><th>频次</th><th>用药目的</th></tr></thead><tbody>' + rows + '</tbody></table></div></div>';
	            	if (htmlStr.length > 0) {
	            		$('#id_kjyw_${bk001Sbk.relid}').append(htmlStr);
	            	} else {
	            		$('#id_kjyw_${bk001Sbk.relid}').append('<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">抗菌药物使用</span></div><div class="byt_table"></div></div>');
	            	}
				}
			});
			//插管使用
			$.ajax({
	            url: '${webroot}/bk001Sbk/f_json/findCgsyInfo.shtml',
	            type: 'post',
	            data: { refid: '${bk002Grzd.refid}'},
	            dataType: 'json',
	            success : function(json) {
	            	var htmlStr = '',
	            		tempStr2 = '',
	            		rows = "";
	            	for (var i=0; i<json.length; i++) {
	            		var gr016 = json[i];
	       				tempStr1 = '<td>' + gr016.orderName+ '</td><td >' +  pathogens.gethMdDateStr(gr016.orderAt) + '</td><td >' + pathogens.gethMdDateStr(gr016.stopAt) + '</td>';
        				rows += "<tr>"+tempStr1 + tempStr2+"</tr>";
        				tempStr1="";
	            	}
           			htmlStr = '<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">侵入性操作</span></div><div class="byt_table"><table class="stand_table" style="width: 100%; table-layout: fixed;"><thead><tr><th>侵入性操作名称</th><th>开嘱时间</th><th>停嘱时间</th></tr></thead><tbody>' + rows + '</tbody></table></div></div>';
	            	if (htmlStr.length > 0) {
	            		$('#id_cgsy_${bk001Sbk.relid}').append(htmlStr);
	            	} else {
	            		$('#id_cgsy_${bk001Sbk.relid}').append('<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">侵入性操作</span></div><div class="byt_table"></div></div>');
	            	}
				}
			});
			// 历史报卡明细
			$.ajax({
				url : '${webroot}/bk001Sbk/f_json/getReportInfectListByZyid.shtml',
				type : 'post',
				data : { zyid: '${bk001Sbk.zyid}'},
				dataType : 'json',
				success : function(json){
					var htmlStr = '',
						rows = '',
						tempStr1 = '',
						tempStr2 = '',
						isHave = false;
					for(var i = 0; i < json.length; i++){
						isHave = true;
						var bk001Sbk = json[i];
						tempStr1 = '<td class="omit" title="' + bk001Sbk.infectTypeName + '">' + bk001Sbk.infectTypeName + '</td><td class="omit" title="' + (bk001Sbk.startAt == null? "":bk001Sbk.startAt.substring(0,10)) + '">' + (bk001Sbk.startAt == null? "":bk001Sbk.startAt.substring(0,10)) + '</td><td class="omit" title="' + bk001Sbk.infectDeptName + '">' + bk001Sbk.infectDeptName + '</td><td class="omit" title="'+ bk001Sbk.infectName + '">'+ bk001Sbk.infectName + '</td><td class="omit" title="'+ bk001Sbk.reportDrName + '">'+ bk001Sbk.reportDrName + '</td><td class="omit" title="'+ bk001Sbk.reportDeptName + '">'+ bk001Sbk.reportDeptName + '</td>';
						if(bk001Sbk.ygysCount == 0){
							tempStr2 += '<td></td>';
						}else{
							tempStr2 += '<td class="omit" title="' + bk001Sbk.ygysArr + '" >'+ bk001Sbk.ygysArr + '</td>';
						}
						if(bk001Sbk.opeName == ''){
							tempStr2 += '<td></td>';
						}else{
							tempStr2 += '<td class="omit" title="' + bk001Sbk.opeName + '" >'+ bk001Sbk.opeName +'</td>';
						}
						if(bk001Sbk.zbjCount == 0){
							tempStr2 += '<td></td>';
						}else{
							tempStr2 += '<td class="omit" title="' + bk001Sbk.zbjArr + '" >' + bk001Sbk.zbjArr + '</td>';
						}
						rows += '<tr>' + tempStr1 + tempStr2 +'</tr>';
						tempStr1 = '';
						tempStr2 = '';
					}
					htmlStr = '<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">历史报卡明细</span></div><div class="byt_table"><table class="stand_table" style="width: 100%; table-layout: fixed;"><thead><tr><th>感染类型</th><th>感染日期</th><th>感染科室</th><th>感染诊断</th><th>上报医生</th><th>上报科室</th><th>易感因素</th><th>感染手术名称</th><th>致病菌</th></tr></thead><tbody>' + rows + '</tbody></table></div></div>';
					if(isHave){
						$('#id_lsbkmx_${bk002Grzd.refid }').append(htmlStr);
					}else{
						$('#id_lsbkmx_${bk002Grzd.refid }').append('<div class="card_cont"><div class="card_cont_h"><span class="card_c_h_text">历史报卡明细</span></div><div class="byt_table">无</div></div>');
					}
				}
			});
	});
	function returnCard(){
		$.ajax({
			url:"${webroot}/bk001Sbk/f_json/toReturnCards.shtml",
			type:"POST",
			data:{'relid':$("#relid_hid").val()},
			success:function(json){
				json = eval("("+json+")");
				if(json.result=="success"){
					$.messager.show({title:'提示',msg:'退卡成功！'});
					$("#returnDialog").dialog("close");
					if(infections){
						infections.closeAllTabs();
						infections.query();
					}else{
						menuInfo.refreshMenu('报卡操作');
					}
				}else{
					$.messager.show({title:'提示',msg:'退卡失败！'+json.msg});
				}
			},error:function(json){
				$.messager.show({title:'提示',msg:'退卡操作失败！'});
			}
		});
	}
</script>
</body>
</html>