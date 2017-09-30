<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<jsp:include page="/WEB-INF/view/core/report.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>pdca</title>
</head>
<body>
<form id="editFormZlPdcaPlans" method="post">
	<input type="hidden" name="puid" value="${zlPdcaPlans.puid}" />
	<input type="hidden" name="pzId" value="${pzId}" />
	<input type="hidden" id="saveOrAs" name="saveOrAs" value="0" />  
	<div class="mb60">
	<table class="table_cx h_set" cellpadding="0" cellspacing="0" style="width:95%;">
	<tbody>
			<tr>
				<td class="t_title" width="150px"><span class="red">*</span>科室名称1：</td>
				<td style="text-align:left;" colspan="3">
					<input type="hidden" name="deptName" id="deptName" value="${zlPdcaPlans.deptName}">
					<input type="hidden" id="unit" value="${unitId}"/>
					<div class="select_del"><input id="dep" name="deptId" style="width:250px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#dep').combo('clear');"></a></div>
				</td>		
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>监测项目：</td>
				<td><input type="text" id="jcxm" name="planName" style="width:250px" value="<c:out value="${zlPdcaPlans.planName}" />" class="easyui-validatebox" required="true"></td>
			</tr>
			<tr>
				<td class="t_title">预期目标：</td>
				<td><textarea name="yqmb" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlans.yqmb}" /></textarea></td>
			</tr>
			<tr>
				<td class="t_title">资料来源：</td>
				<td><textarea name="zlly" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlans.zlly}" /></textarea></td>
			</tr>
			<tr>
				<td class="t_title">监测结果：</td>
				<td><textarea name="jcjg" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlans.jcjg}" /></textarea></td>
			</tr>
			<tr>
				<td class="t_title">问题叙述：</td>
				<td><textarea name="wtxs" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlans.wtxs}" /></textarea></td>
			</tr>
			<tr>
				<td class="t_title">原因分析：</td>
				<td><textarea name="yyfx" style="width:95%; height:60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlans.yyfx}" /></textarea></td>
			</tr>
			<tr>
				<td class="t_title">项目负责人：</td>
				<td>
				<input type="text" id="xmfzr" name="xmfzr" style="width:250px" value="<c:out value="${zlPdcaPlans.xmfzr}" />" class="easyui-validatebox text">
			</tr>
			<tr>
				<td class="t_title">参加人员：</td>
				<td>
				<input type="text" id="cjry" name="cjry" style="width:250px" value="<c:out value="${zlPdcaPlans.cjry}" />" class="easyui-validatebox text">
				</td>
			</tr>
			<tr>
				<td class="t_title">是否展开调查与改进：</td>
				<td>
					<input type="radio" id="isPdca_1" name="isPdca" value="1" onclick="set_isPdcadiv(1)" checked="checked"/><label class="mr5" for="isPdca_1">展开PDCA调查</label>					
					<input type="radio" id="isPdca_2" name="isPdca" value="0" onclick="set_isPdcadiv(0)"/><label class="mr5" for="isPdca_2">偶发现一次，不需调查</label>
					<div id="fuiddiv" style="display:inline-block;"><select id="fuid" name="fuid" style="width:120px"></select></div>
				</td>
			</tr>		
			<tr>
				<td class="t_title" style="vertical-align:top; padding-top:5px;">PDCA调查与改进步骤：</td>
				<td>
					<table class="table_cx" id="zlPdcaPlansDetailList" cellpadding="0" cellspacing="0" style="width:100%; margin:0px;">
						<tbody id="fuidtbody">
							<c:forEach  var="zlPdcaPlansDetail"  items="${zlPdcaPlansDetailList}" varStatus="status">
							<input type="hidden" name="zlPdcaPlansDetailList[${status.index}].stepName" value="${zlPdcaPlansDetail.stepName}"/>
							<input type="hidden" name="zlPdcaPlansDetailList[${status.index}].stepNo" value="${zlPdcaPlansDetail.stepNo}"/>
							<tr>
								<td>${zlPdcaPlansDetail.stepName}：
								<input type="checkbox" name="zlPdcaPlansDetailList[${status.index}].status"<c:if test="${zlPdcaPlansDetail.status==1}">checked="checked"</c:if> value="1"/><label class="mr10">是否完成</label>
								完成时间：<input type="text" name="zlPdcaPlansDetailList[${status.index}].wcDate" value="<fmt:formatDate value='${zlPdcaPlansDetail.wcDate}' pattern='yyyy-MM-dd' />" class="Wdate text" onclick="WdatePicker()"/>
								<%-- <nis:select name="zlPdcaPlansDetailList[${status.index}].status" dictcode="pdca_plans_status" value="${zlPdcaPlansDetail.status}" cssCls="easyui-validatebox" exp="required=\"true\""/> --%>
								</td>
							</tr>
							<tr>			
								<td><textarea name="zlPdcaPlansDetailList[${status.index}].stepContent" id="stepContent${status.index}"
										style="width: 95%; height: 60px;" class="easyui-validatebox"><c:out value="${zlPdcaPlansDetail.stepContent}" /></textarea>
								</td>
							</tr>
							<script>
							/* KindEditor.ready(function(K) {
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
							}); */
							</script>
							</c:forEach>
						</tbody>
					</table>
					</td>
			</tr>
		</tbody>
	</table>
	</div>
	<div class="footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormZlPdcaPlans').submit()" class="no_ico"><span>保存</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="saveAs()" class="no_ico"><span>保存为模板</span></a>
			</div>
		</div>	
	</div>
</form>
<script>
function setDep(dep){
	$("#deptName").val(dep.deptName);
}

function saveAs(){
	$("#saveOrAs").val("1");
	$("#editFormZlPdcaPlans").submit();
}

function set_isPdcadiv(status){
	if(status == 1){
		$("#fuiddiv").css('display','inline-block');
		$("#fuidtbody").css('display','block');
	}else{
		$("#fuiddiv").css('display','none');
		$("#fuidtbody").css('display','none');
	}
}
/**
 * 选择模板后
 */
function setFlow(fuid){
	$.ajax({
        url: '${webroot}/zlPdcaFlow/f_json/get.shtml',
        type: 'post',
        data: { 'fuid': fuid },
        dataType: 'json',
        success : function(data) {
        	$('#planName').val(data.processName);
        	var arr= data.zlPdcaFlowDetailList ;
        	var html="";
        	$.each( arr, function(index, zlPdcaFlowDetail){
        		html=html+'<tr><td>'+ zlPdcaFlowDetail.processName +'：<input type="hidden" name="zlPdcaPlansDetailList['+index+'].stepNo" value="'+zlPdcaFlowDetail.processNo+'"/><input type="hidden" name="zlPdcaPlansDetailList['+index+'].stepName" value="'+zlPdcaFlowDetail.processName+'"/><input type="checkbox" name="zlPdcaPlansDetailList['+index+'].status" value="1"/><label class="mr10">是否完成</label>完成时间：<input type="text" name="zlPdcaPlansDetailList['+index+'].wcDate" value="" class="Wdate text" onclick="WdatePicker()"/></td></tr><tr><td><textarea name="zlPdcaPlansDetailList['+index+'].stepContent" id="stepContent'+index+'" style="width: 95%; height: 60px;" class="easyui-validatebox">'+zlPdcaFlowDetail.processContent+'</textarea></td></tr>';
        	});
        	$('#zlPdcaPlansDetailList').find("tbody").html("");
        	$('#zlPdcaPlansDetailList').append(html);
        	try{
    			parent.iFrameHeight();
    		}catch(e){
    			
    		}
        	/* $.each( arr, function(index, zlPdcaFlowDetail){
	        	window.editor = KindEditor.create('#stepContent'+index, {
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
        	}); */
        }
	});
}
$(document).ready(
	function() {
		try{
			parent.iFrameHeight();
		}catch(e){
			
		}
		Csm.combogrid.dep({	
			id: 'dep',
			value: '${zlPdcaPlans.deptId}',
			hospId: '${unitId}',
			onClickRow : function(index,row){
				$('#deptName').val(row.deptName);
			}
		});
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
		window.setTimeout(function() {
				Comm.form({
					id : 'editFormZlPdcaPlans',
					url : '${webroot}/zlPdcaPlans/f_json/save.shtml',
					subbtn : 'changeFormSubmitBtn',
					success : function(json) {
						if (json.result === 'success') {
							parent.$.messager.show({title : '提示',msg : '操作成功！'});
							var parentObject = parent.Comm.getObjectCache();
							parentObject.query();
							parent.Comm.dialogClose('${param.dialogId}');
						} else if (json.result === 'error') {
							parent.$.messager.show({title : '提示',msg : '操作失败！'});
						} else {
							parent.$.messager.show({title : '提示',msg : json.msg});
						}
					}});
			}, 100);
	});
</script>
</body>
</html>