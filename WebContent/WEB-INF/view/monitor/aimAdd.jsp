<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>增加或修改</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css${version}" />
<style>
	.select{
    	color: #FF5D25 !important;
		font-weight: bold;
    	text-decoration: underline !important;
	}
</style>
</head>
<body>
	<form id="aimForm">
		<table class="mainTable">
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign" ><span class="red">*</span>审核编号：</td>
				<td>
					<input type="hidden" id="orgShNo" name="orgShNo" value="${XK002.shNo}"></input>
					
					<input type="text" id="shNo" name="shNo" value="${XK002.shNo}" onblur="AIP.isNoExist(this.value);"></input>
				</td>
				<td class="rightTextAlign"><span class="red">*</span>审核类型：</td>
				<td>
					<select id="shType" name="shType">
						<c:forEach items="${AAT}" var="aat">
							<option value="${aat.dictName}" <c:if test="${aat.dictName eq XK002.shType}">selected="selected"</c:if> >${aat.dictName}</option>
						</c:forEach>
					</select>	
				</td>
			</tr>
			<tr style="    line-height: 30px;">	
				<td class="rightTextAlign"><span class="red">*</span>审核者：</td>
				<td><input type="text" id="shName" name="shName" value="${XK002.shName}"></input></td>
				<td class="rightTextAlign">使用状态：</td>
				<td>
					<select id="useFlag" name="useFlag">
						<c:forEach items="${ES}" var="es">
							<option value="${es.dictCode}" <c:if test="${es.dictCode eq XK002.useFlag}">selected="selected"</c:if> >${es.dictName}</option>
						</c:forEach>
					</select>	
				</td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign"><span class="red">*</span>采购部门：</td>
				<td><input type="text" id="depCg" name="depCg" value="${XK002.depCg}"></input></td>
				<td class="rightTextAlign"><span class="red">*</span>采购部门签收人：</td>
				<td><input type="text" id="cgRy" name="cgRy" value="${XK002.cgRy}"></input></td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign"><span class="red">*</span>生产企业编号：</td>
				<td>
					<input type="text" id="scqyNo" name="scqyNo" value="${XK002.scqyNo}"></input>
					<input type="hidden" id="scqyName" name="scqyName" value="${XK002.scqyName}"></input>
				</td>
				<td class="rightTextAlign"><span class="red">*</span>经营企业编号：</td>
				<td>
					<input type="text" id="jyqyNo" name="jyqyNo" value="${XK002.jyqyNo}"></input>
					<input type="hidden" id="jyqyName" name="jyqyName" value="${XK002.jyqyName}"></input>
				</td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">生产对经营授权有效期：</td>
				<td><input type="text" id="scjyDate" name="scjyDate" class="Wdate" style="width: 140px;"  onclick='WdatePicker({dateFmt:"yyyy-MM-dd"});'  value="<fmt:formatDate value="${XK002.scjyDate}" pattern="yyyy-MM-dd" /> "></input></td>
				<td class="rightTextAlign">经营对个人授权有效期：</td>
				<td>
					<input type="text" id="jygrDate" name="jygrDate" class="Wdate" style="width: 140px;"  onclick='WdatePicker({dateFmt:"yyyy-MM-dd"});'  value="<fmt:formatDate value="${XK002.jygrDate}" pattern="yyyy-MM-dd" /> "></input>
				</td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">生产企业年度：</td>
				<td><input type="text" id="scNd" name="scNd" class="Wdate" style="width: 140px;"  onclick='WdatePicker({dateFmt:"yyyy-MM-dd"});'  value="<fmt:formatDate value="${XK002.scNd}" pattern="yyyy-MM-dd" /> "></input></td>
				<td class="rightTextAlign">经营企业检验时间：</td>
				<td><input type="text" id="jyJydate" name="jyJydate" class="Wdate" style="width: 140px;"  onclick='WdatePicker({dateFmt:"yyyy-MM-dd"});'  value="<fmt:formatDate value="${XK002.jyJydate}" pattern="yyyy-MM-dd" /> "></input></td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">审核结果：</td>
				<td colspan="3"><input type="text" id="shJg" name="shJg" value="${XK002.shJg}" style="width:550px;"></input></td>
			</tr>
		</table>
		<div class="footer_btn" style="text-align: center;margin-top: 20px;margin-bottom: 20px;">
			<div class="n_btn_blue">
				<a href="javascript:void(0);"  id="id_submit" onclick="AIP.save();" class="no_ico"><span>审核</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:void(0);" onclick="AIP.cancel();" class="no_ico"><span>取消</span></a>
			</div>
		</div>	
	</form>
	<script>	
	
		$(function () {
			$("#scqyNo").combogrid({
				panelWidth:260,
				url: '${webroot}/aim/f_json/queryQyList.shtml',
				queryParams : {
					"qyType" : "生产企业"
				},
				method:"POST",
			    idField: 'qyNo',
			    textField: 'qyNo',
			    value:"${XK002.scqyNo}",
			    columns: [[
					{field:'qyNo',title:'企业编号',width:100,sortable:true},
					{field:'qyName',title:'企业名称',width:150,sortable:true}
			    ]],
			    onClickRow:function(index,row){
					$("#scqyName").val(row['qyName']);
				}
			});
			
			$("#jyqyNo").combogrid({
				panelWidth:260,
				url: '${webroot}/aim/f_json/queryQyList.shtml',
				queryParams : {
					"qyType" : "经营企业"
				},
				method:"POST",
			    idField: 'qyNo',
			    textField: 'qyNo',
			    value:"${XK002.jyqyNo}",
			    columns: [[
					{field:'qyNo',title:'企业编号',width:100,sortable:true},
					{field:'qyName',title:'企业名称',width:150,sortable:true}
			    ]],
			    onClickRow:function(index,row){
					$("#jyqyName").val(row['qyName']);
				}
			});
		});

	var AIP = {
			
			isNoExist : function(shNo){
				var orgShNo = "${XK002.shNo}";
				var newShNo = $("#shNo").val();
				if(orgShNo == newShNo){
					return;
				}
				$.ajax({
					url:"${webroot}/aim/f_json/isNoExist.shtml",
					data:{'shNo':shNo},
					type:"POST",
					success:function(data){
						data = eval("("+data+")");
						if (data.result == 'success') {
							parent.$.messager.show({title : '提示',msg : "该审核编号已存在！请更换审核编号！"});
							$('#id_submit').parent().hide();
						}else{
							$('#id_submit').parent().show();
						}
					}
				})
			},
			save : function(){
				if(AIP.check()){
					<c:choose>
						<c:when test="${empty XK002.shNo}">
							var url = "${webroot}/aim/f_json/save.shtml";
						</c:when>
						<c:otherwise>
							var url = "${webroot}/aim/f_json/update.shtml";
						</c:otherwise>
					</c:choose>
					$.ajax({
						url: url,
						data:$("#aimForm").serialize(),
						type:"POST",
						success : function(json) {
							json = eval("("+json+")");
							if (json.result == 'success') {
								parent.$.messager.show({title : '提示',msg : "保存成功！"});
								//刷新
								parent.AI.query();
								//关闭
								parent.Comm.dialogClose('${param.dialogId}');
							} else {
								parent.$.messager.show({title : '提示',msg : json.msg});
							}
						}
					});
				}
			},
			check : function(){
				if(!$("#shNo").val()){
					parent.$.messager.show({title : '提示',msg : "请填写审核编号！"});
					return false;	
				}
				if(!$("#shType option:selected").val()){
					parent.$.messager.show({title : '提示',msg : "请填写审核类型！"});
					return false;	
				}
				if(!$("#shName").val()){
					parent.$.messager.show({title : '提示',msg : "请填写审核者！"});
					return false;	
				}
				if(!$("#depCg").val()){
					parent.$.messager.show({title : '提示',msg : "请填写采购部门！"});
					return false;	
				}
				if(!$("#cgRy").val()){
					parent.$.messager.show({title : '提示',msg : "请填写采购部门签收人！"});
					return false;	
				}
				
				return true;
			},
			cancel : function(){
				parent.Comm.dialogClose('${param.dialogId}');
			}
	}
	</script>
</body>
</html>