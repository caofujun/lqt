<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>手卫生调查新增</title>
		<%@ include file="/WEB-INF/view/core/include.jsp"%>
		<style>
			.forHHCTable tr td,th{padding: 5px;border-bottom: 1px dotted #dddddd;border-right: 1px dotted #dddddd;}
			.forProblems{border: 1px solid #dddddd;background-color:#dddddf;padding: 5px 10px;margin: 5px;}
			.custom_tab{margin:0px;padding: 0px;}
			.custom_tab li{float:left;padding:3px 6px;background-color: #f3f3f3;margin-right: 10px;cursor:pointer; margin-bottom: 6px;font-size:14px;}
			.custom_tab li a{color:#b3b3b3}
			.forCAR{margin-right: 5px;line-height: 25px;}
			.checkLi{background-color: #faebd7 !important;}
			.checkA{color: #3d93d0 !important; }
			.ib{display:inline-block; _zoom:1;_display:inline; min-width:70px;}
			
			.frozenStyle{
				margin-top: -1px;position: absolute; z-index: 100;border-top:1px solid #dddddd; 
			}
			.frozenSearchBar{
				background-color:#fff;position: fixed; z-index: 101;margin-top: -37px;width:956px;border-bottom: 1px solid #ddd;
			}
		</style>
	</head>
<body>
<c:if test="${!empty msg}">
	<div>${msg}</div>
	<script>
		$(function(){
			$("#handHygieneAdd").hide();
		});
	</script>
</c:if>
<form id="handHygieneAdd" >
<div style="width: 100%;height: 100%">
			<div class="m_search frozenSearchBar">				
				<input type="hidden" id="dcId" name="dcId" value="${sw002.dcId}"/>
				<input type="hidden" id="gcUsername" name="gcUsername" value="${sw002.gcUsername}"/>
				<input type="hidden" id="gcUserId" name="gcUserId" value="${sw002.gcUserId}"/>
				<input type="hidden" id="djDate" name="djDate" value='<fmt:formatDate value="${sw002.djDate}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
				<span>
				<span>科室:</span>
				<div class="select_del">
					<input type="text" id="dcWardId" name="dcWardId"  class="easyui-combobox"  style="height:25px;" />
					<input type="hidden" id="dcWard" name="dcWard" value="${sw002.dcWard}"/>
					<%-- <input type="hidden" id="dcWardId4Form" name="dcWardId" value="${sw002.dcWardId}"/> --%>
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#dcWardId').combo('clear');"></a>
				</div>
				</span>
				
				<span>&nbsp;&nbsp;&nbsp;&nbsp;调查时期:</span>
				<input type="text" id="dcDate" name="dcDate" style="height:25px;"  class="Wdate easyui-validatebox" required="true" onclick="WdatePicker()"
					<c:choose>
						<c:when test="${!empty sw002}">
							value="<fmt:formatDate value="${sw002.dcDate}" pattern="yyyy-MM-dd"/>"
						</c:when>
						<c:otherwise>
							value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"
						</c:otherwise>
					</c:choose> />
				
				<span>&nbsp;&nbsp;&nbsp;&nbsp;观察时间(分):</span>
				<input  id="gcTime" name="gcTime" type="text" style="height:25px;" title="只能填写数字！" value="${sw002.gcTime}" onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="this.value=this.value.replace(/\D/g,'')" />
				<div class="btn_r">
					<div class="n_btn_grey">
						<a href="javascript:;" id="" onclick="HHCT.newRow();" class="no_ico"><span><i class="icon iconfont">&#xe602;</i>新增时机</span></a>
					</div>
				</div>				
		</div>
	<div id="tablePlace"  style="border-width: 0px;border-bottom: 1px solid #dddddd;height:400px;overflow: scroll;overflow-x: hidden;margin-top: 37px;">
		<div class="datagrid-htable" style="border-top: 1px solid #dddddd;">
			<div class="frozenHeadPart" style="width:955px;"></div>
			<table id="hhct" style="width: 100%;font-size: 12px;overflow: hidden;" class="forHHCTable">
				<thead class="datagrid-header frozenTr">
					<tr class="datagrid-header-row">
						<th style="width:5%;">时机</th>
						<th style="width:18%;">人员类型</th>
						<th style="width:24%;">指征</th>
						<th style="">手卫生行为</th>
						<th style="width:81px;">是否正确</th>
						<c:if test="${INIER==1}"><th  style="width:17%;">不正确原因</th></c:if>
						<th style="width:4%;">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${!empty AllWatchList}">
					<c:forEach items="${AllWatchList}" var="awl" varStatus="vs">
					<tr  rno="${vs.index+1}">
						<td style="text-align: center;">${vs.index+1} 
							<input type="hidden" name="watchList2[${vs.index}].orderBy" value="${vs.index+1}"/>
						</td>
						<td style="text-align: left;">
							<input class="ryName" name="watchList2[${vs.index}].ryName" type="hidden" value="${awl.ryName}"/>
							<c:forEach items="${personalType}" var="pt" varStatus="pti" >
								<div class="ib">
									<label class="forCAR"><input type="radio" <c:if test="${awl.ryType eq pt.dictCode }">checked="checked"</c:if>  name="watchList2[${vs.index}].ryType" value="${pt.dictCode}" class="rylx" tvalue="${pt.dictName}" />${pt.dictName}</label>
								</div>
							</c:forEach>	
						</td>
						<td>
							<c:forEach items="${indication }" var="idct" >
								<div class="ib"><label class="forCAR"><input type="checkbox" name="watchList2[${vs.index}].sjList" value="${idct.dictCode}" class="zhizheng"  />&nbsp;${idct.dictName}</label></div>
							</c:forEach>
							<script type="text/javascript">
								var zzs = "${awl.sjList}";
								if(zzs){
									var zzss = zzs.split(",");
									for(var zz in zzss){
										$(":checkbox[name='watchList2[${vs.index}].sjList'][value='"+zzss[zz]+"']").attr("checked","checked");
									}
								}
							</script>
						</td>
						<td>
							<c:forEach items="${behavior}" var="xw">
								<div class="ib"><label class="forCAR"><input type="radio"  <c:if test="${awl.swsList eq xw.dictCode }">checked="checked"</c:if> name="watchList2[${vs.index}].swsList" value="${xw.dictCode}" svalue="${xw.extParam1}" class="wl${vs.index}st swsxw" tvalue="${xw.dictName}" />&nbsp;${xw.dictName}</label></div>
							</c:forEach>
						</td>
						<td style="text-align: center;">
							<c:forEach items="${isright}" var="ir" >
								<div class="ib"><label class="forCAR"><input type="radio" <c:if test="${awl.isRight==ir.dictCode}">checked="checked"</c:if> class="watchList${vs.index}isRight watchListIsRight" name="watchList2[${vs.index}].isRight" value="${ir.dictCode}"/>&nbsp;${ir.dictName}</label></div>
							</c:forEach>
						</td>
					<c:if test="${INIER==1}">
						<td>
							<div  style="<c:choose><c:when test="${empty awl.isRight}">display:none;</c:when><c:when test="${awl.isRight!=1}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>" class="errReasonDiv"   >
								<select id="watchList2_${vs.index}errReason" class="easyui-combobox errReason"  name="watchList2[${vs.index}].errReason" data-options="
									multiple:true,
									editable:false,
									onLoadSuccess:function(){
										$(this).combobox('clear');
										<c:if test="${!empty awl.errReason}">
										$(this).combobox('setValues','${awl.errReason}'.split(','));
										</c:if>
									}" style="width:150px;">
									<c:forEach items="${errreason}" var="er">
										<option value="${er.dictCode}">${er.dictName}</option>
									</c:forEach>
								</select>
							</div>
						</c:if>
						<td style="text-align: center;padding-left: 10px;">
							<a class="ico_del" title="删除" onclick="HHCT.delRow(this);"></a>
						</td>
					</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<tr  rno="1">
						<td style="text-align: center;">1
							<input type="hidden" name="watchList2[0].orderBy" value="1"/>
						</td>
						<td style="text-align: left;">
							<input class="ryName" name="watchList2[0].ryName" type="hidden"/>
							<c:forEach items="${personalType}" var="pt" varStatus="pti">
								<div class="ib">
									<label class="forCAR"><input type="radio" name="watchList2[0].ryType" value="${pt.dictCode}" class="rylx"  tvalue="${pt.dictName}" />&nbsp;${pt.dictName}</label>
								</div>
							</c:forEach>	
						</td>
						<td >
							<c:forEach items="${indication }" var="idct" >
								<div class="ib"><label class="forCAR"><input type="checkbox"  name="watchList2[0].sjList" value="${idct.dictCode}" class="zhizheng"  />&nbsp;${idct.dictName}</label></div>
							</c:forEach>
						</td>
						<td >
							<c:forEach items="${behavior}" var="xw">
								<div class="ib"><label class="forCAR"><input type="radio"  name="watchList2[0].swsList" value="${xw.dictCode}" svalue="${xw.extParam1}" class="watchList0swsxw_y swsxw" tvalue="${xw.dictName}"/>&nbsp;${xw.dictName}</label></div>
							</c:forEach>
						</td>
						<td style="text-align: center;">
							<c:forEach items="${isright}" var="ir" >
								<div class="ib"><label class="forCAR"><input type="radio" name="watchList2[0].isRight" value="${ir.dictCode}" class="watchList0isRight watchListIsRight" />&nbsp;${ir.dictName}</label></div>
							</c:forEach>
						</td>
					<c:if test="${INIER==1}">
						<td>
							<div style="display:none;" class="errReasonDiv" >
								<select id="watchList2_0errReason" class="easyui-combobox errReason"  name="watchList2[0].errReason" data-options="multiple:true,editable:false,value:''" style="width:150px;">
									<option></option>
									<c:forEach items="${errreason}" var="er">
										<option value="${er.dictCode}">${er.dictName}</option>
									</c:forEach>
								</select>
							</div>
						</td>
					</c:if>
						<td style="text-align: center;padding-left: 10px;">
							<a class="ico_del" title="删除" onclick="HHCT.delRow(this);"></a>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
				</tbody>
			</table>
		</div>
	</div>
		<div style="height: 150px;margin-bottom: 53px;overflow:scroll;overflow-x: hidden;">
			<div style="padding: 5px;">选择手卫生用品与设施调查：</div>
			<div id="problems" style="padding-left: 10px;">
				<ul id="title1" class="custom_tab">
					<c:forEach items="${handsDeficiency}" var="hd">
						<li><a onclick="select(this);" name="key" tvalue="${hd.dictCode}" style="vertical-align: middle;">${hd.dictName}</a></li>
					</c:forEach>
				</ul>
				<input type="hidden" id="swsdc" name="ypss" value="${sw002.ypss}"/>
				<div class="clear"></div>
			</div>
		</div>
		<div class="footer">			
			<div class="footer_btn">				
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="HHCT.saveTable();" class="no_ico"><span>完成调查</span></a>
				</div>
				<div class="n_btn_grey" <c:if test="${!(empty sw002.dcId)}"> style="display:none"</c:if>>
					<a href="javascript:;" onclick="HHCT.cancel();" class="no_ico"><span>取消</span></a>
				</div>				
			</div>	
		</div>
</div>
</form>
<script type="text/javascript">
$(document).ready(function () {
	//上报科室
	Csm.combogrid.dep({
		//【必传】控件名称
		id: 'dcWardId',
		//【可选参数】不传默认区session的医院ID
		hospId: '',
		flag:'1',
		ifmzoffice:'${handDepFlag}',
		required:true,
		//【可选参数】不传默认区所有监控科室
		//dataType: 'zy',
		onClickRow:function(index,row){
			/* $('#dcWardId4Form').val(row['deptId']); */
			$("#dcWard").val(row['deptName']);
		},
		onLoadSuccess:function(data){
			if(data.total>0){
				if(""=="${sw002.dcWardId}"){
					if(!("${user.acType}"=="hospital")){
						//默认选中第一行
						$('#dcWardId').combogrid('grid').datagrid('selectRecord',"${user.depNo}");
					}
				}else{
					$('#dcWardId').combogrid('grid').datagrid('selectRecord',"${sw002.dcWardId}");
				}
				//有选中的就设置值
				var r = $('#dcWardId').combogrid('grid').datagrid('getSelected');
				if(r){
					/* $('#dcWardId4Form').val(r['deptId']); */
					$("#dcWard").val(r['deptName']);
				}
			}else{
				$("#dcWard").val("");
				/* $("#dcWardId4Form").val(""); */
			}
		}
	});
	
	$(".rylx").click(function(){
		$(this).parent().parent().parent().find(".ryName").val($(this).attr("tvalue"));
	});
	
	//页面展示初始化
	if($("#swsdc").val()){
		var dcs = $("#swsdc").val().split("|");
		for(var i=0;i<dcs.length;i++){
			$("a[name='key'][tvalue='"+dcs[i]+"']").trigger("click");
		}
	}
	//初始化禁用相关
	$(".swsxw").each(function(){
		var xwname= $(this).attr("name");
		var xwsv = $(":radio[name='"+xwname+"']:checked").attr("svalue");
		if(xwsv=="0"){
			//选择无后清空是否正确
			var ftr = $(this).closest("tr");   //返回父级tr
			if(ftr){
				//移除正确选中
				$(":radio[name='"+ftr.find(".watchListIsRight").attr("name")+"']").attr("disabled","disabled");
			}
		}
	});
	
	$(".swsxw").click(function(){
		var xwname= $(this).attr("name");
		var xwtv = $(":radio[name='"+xwname+"']:checked").attr("tvalue");
		var xwsv = $(":radio[name='"+xwname+"']:checked").attr("svalue");
		//但选中不用填写正确的项时，清除正确和不正确原因项
		if(xwsv=="0"){
			//选择无后清空是否正确
			var ftr = $(this).closest("tr");   //返回父级tr
			if(ftr){
				//移除正确选中
				$(":radio[name='"+ftr.find(".watchListIsRight").attr("name")+"']").removeAttr('checked').attr("disabled","disabled");
				//直接将错误原因父级隐藏，提交时会重置
				$(ftr.find(".errReason").parent()).hide();
			}
		}else{
			var ftr = $(this).closest("tr");   //返回父级tr
			if(ftr){
				$(":radio[name='"+ftr.find(".watchListIsRight").attr("name")+"']").removeAttr("disabled");
			}
		}
	});
	
	$(".watchListIsRight").click(function(){
		if($(this).val()=="0"){
			$(this).parent().parent().parent().parent().find(".errReason").combobox('clear');
			
			$(this).parent().parent().parent().parent().find(".errReasonDiv").show();
			$(this).parent().parent().parent().parent().find(".errReason").next(".combo").show();
		}else{
			$(this).parent().parent().parent().parent().find(".errReasonDiv").hide();
			//清空选择
			$(this).parent().parent().parent().parent().find(".errReason").combobox('clear');
		}
	});
	
/* 	$("#hhct tbody tr").each(function(index){
		//获取当前行号
		var rno = $(this).attr("rno");
		isRight(rno-1);
	}); */
	
	//ie9新增一次后，再次打开新增窗口，观察时间无法focus... 手动focus
	$("#gcTime").focus();
	
	//
	setTimeout("frozenTr()",1000);
	
});

var HHCT = {
	newRow : function(){
		if(!HHCT.checkForm()){
			return;
		}

		HHCT.autoSaveTable();
		
		var rowCount = parseInt($("#hhct tbody tr").last().find("td").eq(0).html())|0;
		if($("#hhct tbody tr").length==0){
			rowCount=0;
		}
		var curRowNum = parseInt($("#hhct tbody tr").last().attr("rno"));
		if($("#hhct tbody tr").length==0){
			curRowNum=0;
		}
		var row = '<tr rno="'+(curRowNum+1)+'">'+
			'<td style="text-align: center;">'+(rowCount+1)+'<input type="hidden" name="watchList2['+(curRowNum+1)+'].orderBy" value="'+(curRowNum+1)+'"/></td>'+
			'<td style="text-align: left;"><input class="ryName" name="watchList2['+(curRowNum+1)+'].ryName" type="hidden"/>';
			<c:forEach items="${personalType}" var="pt" varStatus="pti">
				row += '<div class="ib"><label class="forCAR"><input type="radio" name="watchList2['+(curRowNum+1)+'].ryType" value="${pt.dictCode}" class="rylx"  tvalue="${pt.dictName}" />&nbsp;${pt.dictName}</label></div>';
			</c:forEach>	
			row += '</td>'+
			/* '<td>'+
				'<div style="text-align: center;">'+
					'<label class="forCAR"><input type="checkbox" name="watchList['+(curRowNum+1)+'].zzBrq" value="1" class="zhizheng" />&nbsp;病人前</label>'+
					'<label class="forCAR"><input type="checkbox" name="watchList['+(curRowNum+1)+'].zzCzq" value="1" class="zhizheng" />&nbsp;操作前</label>'+
					'<label class="forCAR"><input type="checkbox" name="watchList['+(curRowNum+1)+'].zzTyh" value="1" class="zhizheng" />&nbsp;体液后</label>'+
				'</div>'+
				'<div style="text-align: center;">'+
					'<label class="forCAR"><input type="checkbox" name="watchList['+(curRowNum+1)+'].zzBrh" value="1" class="zhizheng" />&nbsp;病人后</label>'+
					'<label class="forCAR"><input type="checkbox" name="watchList['+(curRowNum+1)+'].zzHjh" value="1" class="zhizheng" />&nbsp;环境后</label>'+
				'</div>'+
			'</td>'+ */
			'<td>'+
			<c:forEach items="${indication }" var="idct" >
				'<div class="ib"><label class="forCAR"><input type="checkbox"  name="watchList2['+(curRowNum+1)+'].sjList" value="${idct.dictCode}" class="zhizheng"  />&nbsp;${idct.dictName}</label></div>'+
			</c:forEach>
			'</td>'+
			/* '<td>'+
				'<div style="text-align: center;">'+
					'<label class="forCAR"><input type="checkbox" style="line-height: 25px;" name="watchList['+(curRowNum+1)+'].xwSx" value="1" class="watchList'+(curRowNum+1)+'swsxw_y swsxw" onclick="$(\'.watchList'+(curRowNum+1)+'swsxw_w\').removeAttr(\'checked\');isRight('+(curRowNum+1)+');" />&nbsp;手消</label>'+
					'<label class="forCAR"><input type="checkbox" style="line-height: 25px;" name="watchList['+(curRowNum+1)+'].xwFzs" value="1" class="watchList'+(curRowNum+1)+'swsxw_y swsxw" onclick="$(\'.watchList'+(curRowNum+1)+'swsxw_w\').removeAttr(\'checked\');isRight('+(curRowNum+1)+');"/>&nbsp;肥皂和水</label>'+
					'<label class="forCAR"><input type="checkbox" style="line-height: 25px;" name="watchList['+(curRowNum+1)+'].xwW" value="1" class="watchList'+(curRowNum+1)+'swsxw_w swsxw" onclick="$(\'.watchList'+(curRowNum+1)+'swsxw_y\').removeAttr(\'checked\');isRight('+(curRowNum+1)+');" />&nbsp;无</label>'+
				'</div>'+
				'<div style="text-align: center;">'+
					'<label class="forCAR"><input type="checkbox" style="line-height: 25px;" name="watchList['+(curRowNum+1)+'].xwDst" value="1" class="wl'+(curRowNum+1)+'st swsxw" onclick="isRight('+(curRowNum+1)+');"/>&nbsp;戴手套</label>'+
				'</div>'+
			'</td>'+ */
			'<td >'+
			<c:forEach items="${behavior}" var="xw">
				'<div class="ib"><label class="forCAR"><input type="radio"  name="watchList2['+(curRowNum+1)+'].swsList" value="${xw.dictCode}" svalue="${xw.extParam1}" class="watchList'+(curRowNum+1)+'swsxw_y swsxw" tvalue="${xw.dictName}" />&nbsp;${xw.dictName}</label></div>'+
			</c:forEach>
			'</td>'+
			'<td style="text-align: center;">';
			<c:forEach items="${isright}" var="ir" >
				row += '<div class="ib"><label class="forCAR"><input type="radio" name="watchList2['+(curRowNum+1)+'].isRight" class="watchList'+(curRowNum+1)+'isRight watchListIsRight" value="${ir.dictCode}" />&nbsp;${ir.dictName}</label></div>';
			</c:forEach>
			row += '</td>'+
			<c:if test="${INIER==1}">
				'<td>'+
				'<div  style="display:none;" class="errReasonDiv" >'+
					'<select id="watchList2_'+(curRowNum+1)+'errReason" class="easyui-combobox errReason"  name="watchList2['+(curRowNum+1)+'].errReason" data-options="multiple:true" style="width:150px;">'+
						'<option></option>'+
					<c:forEach items="${errreason}" var="er">
						'<option value="${er.dictCode}" >${er.dictName}</option>'+
					</c:forEach>
					'</select>'+
				'</div>'+
			</c:if>
			'<td style="text-align: center;padding-left: 10px;">'+
				'<a class="ico_del" title="删除" onclick="HHCT.delRow(this);"></a>'+
			'</td></tr>';
		$("#hhct tbody").append(row);
		//定位到最后新加的一行
		$('#tablePlace').scrollTop( $('#hhct')[0].scrollHeight );
		signName();
	},
	delRow : function(ele){
		$(ele).parent().parent().remove();
		HHCT.updateRowNumber();
	},
	updateRowNumber : function(){
		var maxrno = 0;
		$("#hhct tbody tr").each(function(index){
			//获取当前行号
			var rno = $(this).attr("rno");
			if(rno>maxrno){maxrno=rno;}  //获取最大的行号
			//更新行号
			$(this).find("td").eq(0).html(index+1);
		});
	},
	cancel : function(){
		if('${sw002.dcId}' == '' && $("#dcId").val() != ''){
			$.messager.confirm('提示', "数据未保存，是否关闭？", function (r) {
	        	if (r) {
		        	$.ajax({
		                url: '${webroot}/sw002Ycxdc/f_json/delData.shtml',
		                type: 'post',
		                data: { 'dcId': $("#dcId").val() },
		                dataType: 'json',
		                success : function(json) {
		    	    		parent.Comm.dialogClose('${param.dialogId}');
						}
		        	});
	        	}
			});
		}else{
    		parent.Comm.dialogClose('${param.dialogId}');
		}
	},
	saveTable : function(){
		if(HHCT.checkForm()){
			//清掉
			$(".errReasonDiv").each(function(){
				if($(this).is(":hidden")){
					//清空值
					if($(this).find(".easyui-combobox").length>0){
						//先初始化，不然会报错。。。
						$(this).find(".easyui-combobox").combobox();
						//以防万一，还是清除
						$(this).find(".easyui-combobox").eq(0).combobox("clear");
					}
				}
			})
			$.messager.confirm('提示', "确定保存信息？", function (r) {
				//console.log($("#handHygieneAdd").serialize());
				if(r){
					$.ajax({
						url:"${webroot}/sw002Ycxdc/f_json/saveData.shtml",
						data:$("#handHygieneAdd").serialize(),
						type:"POST",
						success:function(data){
							data = eval("("+data+")");
							if(data.result=="success"){
								parent.Comm.getObjectCache().message(data.msg);
								//刷新之前的datagrid
								parent.Comm.getObjectCache().query();
								parent.Comm.dialogClose('${param.dialogId}');
							}else{
								$.messager.show({ title: '提示', msg: data.msg+"("+data.extraValue+")"});
							}
						},
						error:function(){
							alert("抱歉操作出错！");
						}
					});
				}
			});
		}
	},
	autoSaveTable : function(){
		//清掉
		$(".errReasonDiv").each(function(){
			if($(this).is(":hidden")){
				//清空值
				if($(this).find(".easyui-combobox").length>0){
					//先初始化，不然会报错。。。
					$(this).find(".easyui-combobox").combobox();
					//以防万一，还是清除
					$(this).find(".easyui-combobox").eq(0).combobox("clear");
				}
			}
		});
		$.ajax({
			url:"${webroot}/sw002Ycxdc/f_json/saveData.shtml",
			data:$("#handHygieneAdd").serialize(),
			type:"POST",
			success:function(json){
				json = eval("("+json+")");
				var data = json.data;
				$("#dcId").val(data.dcId);
				$("#gcUsername").val(data.gcUsername);
				$("#gcUserId").val(data.gcUserId);
				$("#djDate").val(data.djDate);
			},
			error:function(){
				alert("抱歉操作出错！");
			}
		});
	},
	checkForm : function(){
		if($("#handHygieneAdd").form('validate')){
			if(!$("#dcWard").val()){
				$.messager.show({ title: '提示', msg: '请选择病房！' });
				return false;
			}
			if(!$("#dcDate").val()){
				$.messager.show({ title: '提示', msg: '请选择调查时期！' });
				return false;
			}
			if(!$("#gcTime").val()){
				$("#gcTime").focus();
				$.messager.show({ title: '提示', msg: '请选择观察时间！' });
				return false;
			}
			if($("#hhct tbody tr").length<1){
				$.messager.show({ title: '提示', msg: '请添加时机！' });
				return false;
			}
			try{
				$("#hhct tbody tr").each(function(index){
					//人员类型
					var srylx = $(this).find(".rylx").eq(0);
					var rylxname = $(srylx).attr("name");
					if(!$(":radio[name='"+rylxname+"']:checked").val()){
						$.messager.show({ title: '提示', msg: '请选择第'+(index+1)+'条记录的人员类型！' });
						throw "x";
					}
					//判断指征
					var zzcount = 0;
					var zzlen = $(this).find(".zhizheng").length;
					$(this).find(".zhizheng").each(function(){
						if($(this).is(":checked")){
							//有被选中的就跳出each
							return false;
						}else{
							zzcount++;
						}
					});
					if(zzcount==zzlen){
						$.messager.show({ title: '提示', msg: '请选择第'+(index+1)+'条记录的指征信息！' });
						throw "x";
					}
					
					//判断手卫生行为
					/* var swscount = 0;
					var swslen = $(this).find(".swsxw").length;
					$(this).find(".swsxw").each(function(){
						if($(this).is(":checked")){
							//有被选中的就跳出each
							return false;
						}else{
							swscount++;
						}
					});
					if(swscount==swslen){
						$.messager.show({ title: '提示', msg: '请选择第'+(index+1)+'条记录的手卫生行为！' });
						throw "x";
					} */
					var swname = $(this).find(".swsxw").eq(0).attr("name");
					var swval = $(":radio[name='"+swname+"']:checked").attr("tvalue");
					//是否验证正确性
					var swsval = $(":radio[name='"+swname+"']:checked").attr("svalue");
					if(!swval){
						$.messager.show({ title: '提示', msg: '请选择第'+(index+1)+'条记录的手卫生行为！' });
						throw "x";
					}
				if(!swsval || swsval!=0){
					//判断是否填写 是否正确
					var sir = $(this).find(".watchListIsRight").eq(0);
					var irname = $(sir).attr("name");
					if($(sir).is(":disabled")){
						
					}else if(!$(":radio[name='"+irname+"']:checked").val()){
						//没有被禁用就判断值是否为空
						$.messager.show({ title: '提示', msg: '请选择第'+(index+1)+'条记录是否正确项！' });
						throw "x";
					}
				
					<c:if test="${INIER==1}">
					//是否启用ER
					<c:if test="${IERN==1}">
						//判断是否填写 错误原因
						var er = $(this).find(".errReason").eq(0);
						if($(er).parent().is(":hidden")){
							//是否隐藏，不管
						}else if($(er).combobox("getValues").length<=0){
							//没有被隐藏就判断值是否为空
							$.messager.show({ title: '提示', msg: '请选择第'+(index+1)+'条记录不正确原因项！' });
							throw "x";
						}
					</c:if>
					</c:if>		
					}	
				});
			}catch(error){
				return false;
			}
			return true;
		}else{
			return false;
		}
	}
}
	function select(ele){
		//更改样式
		if($(ele).parent().hasClass("checkLi")){
			//取消选中
			$(ele).removeClass("checkA");
			//$(ele).parent().find(".ico_select").remove();
			$(ele).parent().removeClass("checkLi");
		}else{	
			$(ele).addClass("checkA");
			//$(ele).before("<a class='ico_select'></a>");
			$(ele).parent().addClass("checkLi");
		}
		var cvs = "";
		//获取选中编号保存
		var len = $("#title1").find("li.checkLi").length;
		$("#title1").find("li.checkLi").each(function(index){
			cvs+=$(this).children("a[name='key']").attr("tvalue");
			if(index<len-1){
				cvs+="|";
			}
		});
		$("#swsdc").val(cvs);
	}
	function getCheck(ele,ckbname,id){
		//获取选中的checkbox的值
		var cvs = "";
		var len = $(ele).parent().parent().find("input[name='"+ckbname+"']:checked").length;
		$(ele).parent().parent().find("input[name='"+ckbname+"']:checked").each(function(index){
			cvs+=$(this).val();
			if(index<len-1){
				cvs+="|";
			}
		});
		$("#"+id).val(cvs);
	}
	function isRight(rowNum){
		//找到行
		//var row = $("#hhct tbody tr").find("[rno='"+rowNum+"']").eq(0);
		//判断是否勾选无  或 戴手套
		if($(".wl"+rowNum+"st").is(":checked") || $(".watchList"+rowNum+"swsxw_w").is(":checked")){
			$(".watchList"+rowNum+"isRight").attr("disabled","disabled").removeAttr("checked");
		}else{
			$(".watchList"+rowNum+"isRight").removeAttr("disabled");
		}
	}
	function signName(){
		//给动态新增的相关控件绑定事件
		$(".rylx").click(function(){
			$(this).parent().parent().parent().find(".ryName").val($(this).attr("tvalue"));
		});
		$(".watchListIsRight").click(function(){
			if($(this).val()=="0"){
				//单个初始化，防止其他的被重置
				$(this).parent().parent().parent().parent().find(".errReason").combobox();
				//清空选择，防止控件默认选中第一个
				$(this).parent().parent().parent().parent().find(".errReason").combobox('clear');
				//显示
				$(this).parent().parent().parent().parent().find(".errReasonDiv").show();
				$(this).parent().parent().parent().parent().find(".errReason").next(".combo").show();
			}else{
				//单个初始化，防止其他的被重置
				$(this).parent().parent().parent().parent().find(".errReason").combobox();
				//隐藏
				$(this).parent().parent().parent().parent().find(".errReasonDiv").hide();
				//清空选择，防止错误数据提交
				$(this).parent().parent().parent().parent().find(".errReason").combobox('clear');
			}
		});
		$(".swsxw").click(function(){
			var xwname= $(this).attr("name");
			var xwtv = $(":radio[name='"+xwname+"']:checked").attr("tvalue");
			var xwsv = $(":radio[name='"+xwname+"']:checked").attr("svalue");
			//但选中不用填写正确的项时，清除正确和不正确原因项
			if(xwsv=="0"){
				//选择无后清空是否正确
				var ftr = $(this).closest("tr");   //返回父级tr
				if(ftr){
					//移除正确选中
					$(":radio[name='"+ftr.find(".watchListIsRight").attr("name")+"']").removeAttr('checked').attr("disabled","disabled");
					//直接将错误原因父级隐藏，提交时会重置
					$(ftr.find(".errReason").parent()).hide();
				}
			}else{
				var ftr = $(this).closest("tr");   //返回父级tr
				if(ftr){
					$(":radio[name='"+ftr.find(".watchListIsRight").attr("name")+"']").removeAttr("disabled");
				}
			}
		});
	}
	function frozenTr(){
		$(".frozenHeadPart").append($("<table>").append($(".frozenTr").clone()).css("width",$(".frozenTr").width()).addClass("forHHCTable")).addClass("frozenStyle").css("width",$("#hhct").width());
	}
</script>
</body>
</html>