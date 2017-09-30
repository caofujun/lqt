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
	<form id="mdmmForm">
		<table class="mainTable">
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign" ><span class="red">*</span>企业编号：</td>
				<td>
					<input type="hidden" id="orgQyNo" name="orgQyNo" value="${XK001.qyNo}"></input>
					
					<input type="text" id="qyNo" name="qyNo" value="${XK001.qyNo}" onblur="EIP.isNoExist(this.value);"></input>
				</td>
				<td class="rightTextAlign">企业类型：</td>
				<td>
					<select id="qyType" name="qyType">
						<c:forEach items="${ET}" var="et">
							<option value="${et.dictName}" <c:if test="${et.dictName eq XK001.qyType}">selected="selected"</c:if> >${et.dictName}</option>
						</c:forEach>
					</select>	
				</td>
			</tr>
			<tr style="    line-height: 30px;">	
				<td class="rightTextAlign">企业名称：</td>
				<td><input type="text" id="qyName" name="qyName" value="${XK001.qyName}"></input></td>
				<td class="rightTextAlign">地址：</td>
				<td><input type="text" id="address" name="address" value="${XK001.address}"></input></td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">法人：</td>
				<td><input type="text" id="qyFr" name="qyFr" value="${XK001.qyFr}"></input></td>
				<td class="rightTextAlign">电话（传真）：</td>
				<td><input type="text" id="tel" name="tel" value="${XK001.tel}"></input></td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">CDC(CMA)检验报告：</td>
				<td><input type="text" id="cdcBg" name="cdcBg" value="${XK001.cdcBg}"></input></td>
				<td class="rightTextAlign">使用状态：</td>
				<td>
					<select id="useFlag" name="useFlag">
						<c:forEach items="${ES}" var="es">
							<option value="${es.dictCode}" <c:if test="${es.dictCode eq XK001.useFlag}">selected="selected"</c:if> >${es.dictName}</option>
						</c:forEach>
					</select>	
				</td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">营业执照：</td>
				<td><input type="text" id="yyzz" name="yyzz" value="${XK001.yyzz}"></input></td>
				<td class="rightTextAlign">营业执照有效期：</td>
				<td><input type="text" id="yyzzDate" name="yyzzDate" class="Wdate" style="width: 140px;"  onclick='WdatePicker({dateFmt:"yyyy-MM-dd"});'  value="<fmt:formatDate value="${XK001.yyzzDate}" pattern="yyyy-MM-dd" /> "></input></td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">医疗器械生产企业许可证：</td>
				<td><input type="text" id="ylscXk" name="ylscXk" value="${XK001.ylscXk}"></input></td>
				<td class="rightTextAlign">器械生产许证可有效期：</td>
				<td><input type="text" id="ylscDate" name="ylscDate" class="Wdate" style="width: 140px;" onclick='WdatePicker({dateFmt:"yyyy-MM-dd"});'  value="<fmt:formatDate value="${XK001.ylscDate}" pattern="yyyy-MM-dd" />"></input></td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">医疗器械注册证：</td>
				<td><input type="text" id="ylZc" name="ylZc" value="${XK001.ylZc}"></input></td>
				<td class="rightTextAlign">医疗器械注册证有效期：</td>
				<td><input type="text" id="ylzcDate" name="ylzcDate" class="Wdate" style="width: 140px;" onclick='WdatePicker({dateFmt:"yyyy-MM-dd"});'  value="<fmt:formatDate value="${XK001.ylzcDate}" pattern="yyyy-MM-dd" />"></input></td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">医疗器械经营企业许可证：</td>
				<td><input type="text" id="yljyXkz" name="yljyXkz" value="${XK001.yljyXkz}"></input></td>
				<td class="rightTextAlign">医疗器械经营许可证有效期：</td>
				<td><input type="text" id="jyxkDate" name="jyxkDate" class="Wdate" style="width: 140px;" onclick='WdatePicker({dateFmt:"yyyy-MM-dd"});'  value="<fmt:formatDate value="${XK001.jyxkDate}" pattern="yyyy-MM-dd" />"></input></td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">卫生许可证：</td>
				<td><input type="text" id="wsXk" name="wsXk" value="${XK001.wsXk}"></input></td>
				<td class="rightTextAlign">卫生许可证有效期：</td>
				<td><input type="text" id="wsxkDate" name="wsxkDate" class="Wdate" style="width: 140px;" onclick='WdatePicker({dateFmt:"yyyy-MM-dd"});'  value="<fmt:formatDate value="${XK001.wsxkDate}" pattern="yyyy-MM-dd" />"></input></td>
			</tr>
			<tr style="    line-height: 30px;">
				<td class="rightTextAlign">卫生许可批件：</td>
				<td><input type="text" id="wsXkpj" name="wsXkpj" value="${XK001.wsXkpj}"></input></td>
				<td class="rightTextAlign">卫生许可批件有效期：</td>
				<td><input type="text"  id="wspjDate" name="wspjDate" class="Wdate" style="width: 140px;" onclick='WdatePicker({dateFmt:"yyyy-MM-dd"});' value="<fmt:formatDate value="${XK001.wspjDate}" pattern="yyyy-MM-dd" />"></input></td>
			</tr>
		</table>
		<div class="footer_btn" style="text-align: center;margin-top: 20px;margin-bottom: 20px;">
			<div class="n_btn_blue">
				<a href="javascript:void(0);"  id="id_submit" onclick="EIP.save();" class="no_ico"><span>保存</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:void(0);" onclick="EIP.cancel();" class="no_ico"><span>取消</span></a>
			</div>
		</div>	
	</form>
	

	<script>	
	
		$(function () {
			
		});
	var EIP = {
			
			isNoExist : function(qyNo){
				var orgQyNo = "${XK001.qyNo}";
				var newQyNo = $("#qyNo").val();
				if(orgQyNo == newQyNo){
					return;
				}
				$.ajax({
					url:"${webroot}/mdmm/f_json/isNoExist.shtml",
					data:{'qyNo':qyNo},
					type:"POST",
					success:function(data){
						data = eval("("+data+")");
						if (data.result == 'success') {
							parent.$.messager.show({title : '提示',msg : "该企业编号已存在！请更换企业编号！"});
							$('#id_submit').parent().hide();
						}else{
							$('#id_submit').parent().show();
						}
					}
				})
			},
			save : function(){
				if(EIP.check()){
					<c:choose>
						<c:when test="${empty XK001.qyNo}">
							var url = "${webroot}/mdmm/f_json/save.shtml";
						</c:when>
						<c:otherwise>
							var url = "${webroot}/mdmm/f_json/update.shtml";
						</c:otherwise>
					</c:choose>
					$.ajax({
						url: url,
						data:$("#mdmmForm").serialize(),
						type:"POST",
						success : function(json) {
							json = eval("("+json+")");
							if (json.result == 'success') {
								parent.$.messager.show({title : '提示',msg : "保存成功！"});
								//刷新
								parent.EI.query();
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
				if(!$("#qyNo").val()){
					parent.$.messager.show({title : '提示',msg : "请填写企业编号！"});
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