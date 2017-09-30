<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报卡审核界面</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css?x=${version}" />
</head>
<body class="easyui-layout" style="border:0px;">
	<div data-options="region:'north',split:false,border:false" style="border-bottom-width: 1px;overflow: hidden;" >
		<form id="searchCDTs">
			<ul class="tabs" style="margin-top: 4px;margin-bottom:6px;padding-left:10px;width: 750px;border-bottom: none;">
				<li><a class="tab_a" style="background-color: inherit;cursor: pointer;padding-bottom: 2px;" onclick="changeSelect(this);query();hideExportXls2();" dvalue="-1" ><span class="tabs-title">全部报卡</span></a></li>
				<li><a class="tab_a cur" style="background-color: inherit;cursor: pointer;padding-bottom: 2px;" onclick="changeSelect(this);query();hideExportXls2();" dvalue="0" ><span class="tabs-title">未审核</span><span class='normalTip' id="countTip" style="display: none;"></span></a></li>
				<li><a class="tab_a" style="background-color: inherit;cursor: pointer;padding-bottom: 2px;" onclick="changeSelect(this);query();showExportXls2();" dvalue="1" ><span class="tabs-title">已审核</span></a></li>
				<li><a class="tab_a" style="background-color: inherit;cursor: pointer;padding-bottom: 2px;" onclick="changeSelect(this);query();hideExportXls2();" dvalue="2" ><span class="tabs-title">已退卡</span></a></li>
				<li><a class="tab_a" style="background-color: inherit;cursor: pointer;padding-bottom: 2px;" onclick="changeSelect(this);query();hideExportXls2();" dvalue="3" ><span class="tabs-title">已删卡</span></a></li>
			</ul>				
		</form>
	</div>

	<div data-options="region:'west',border:false,title:'查询条件'" style="width:240px;">
		<div class="easyui-layout" data-options="fit:true">              
			<div data-options="region:'center',border:false">
				<table class="table_cx" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td class="t_title">报卡类型：</td>
							<td>
								<%-- 
								<nis:select id="cardType" name="cardType" dictcode="cdc_card_type" cssCls="easyui-combobox" exp="style=\"width: 120px;\""/>
								 --%>
								<select name="cardType" id="cardType" onchange="query();" style="width:120px;" class="easyui-combobox">
									<c:forEach items="${allowCards}" var="ac">
										<c:if test="${fn:contains(cdcScope,ac.dictCode) or fn:contains(cdcScope,'all')}">
											<option value="${ac.dictCode}" ${ac.dictCode eq cardType ? 'selected="selected"' : ''}>${ac.dictName}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">日期类型：</td>
							<td>
								<select id="dateType" style="width:120px;" class="easyui-combobox">
									<option value="1">上报日期</option>
									<option value="2">审核日期</option>
								</select>	
							</td>
						</tr>				
						<tr>
							<td class="t_title">开始日期：</td>
							<td>
								<input type="text" id="queryStartDate" name="queryStartDate" class="Wdate text" style="width: 108px;" value="${queryStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">结束日期：</td>
							<td>
								<input type="text" id="queryEndDate" name="queryEndDate" class="Wdate text" style="width: 108px;"  value="${queryEndDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr id="sbjb" style="display:none;">
							<td class="t_title">上报疾病：</td>
							<td>
								<div class="select_del">
									<input class="easyui-combobox" name="reportdiseaseid" id="reportdiseaseid" style="width: 120px;" >
									<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#reportdiseaseid').combo('clear');"></a>
								</div>
							</td>
						</tr>
						<tr>
							<td class="t_title">上报科室：</td>
							<td>
								<div class="select_del">
									<input class="easyui-combobox" name="reportdeptid" id="deptId" style="width: 120px; " >
									<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a>
								</div>
							</td>
						</tr>
						<tr>
							<td class="t_title">上报医生：</td>
							<td>
								<div class="select_del">
									<input class="easyui-combobox" name="reportdoctorid" id="doctorId" style="width: 120px; ">
									<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#doctorId').combo('clear');"></a>
								</div>
							</td>
						</tr>
						<tr>
							<td class="t_title">患者关键字：</td>
							<td>
								<input type="text" id="searchString" name="searchString" class="auto-tip input_tip" style="width: 108px; " data-tip="姓名/门诊号/${patientZyTitle}"/>
							</td>
						</tr>
						<tr>
							<td class="t_title">打印状态：</td>
							<td>
								<div class="select_del">
									<select id="printFlag" class="easyui-combobox" style="width: 120px;">
										<option></option>
										<option value="1">已打印</option>
										<option value="2">未打印</option>
									</select>
									<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#printFlag').combo('clear');"></a>
								</div>
							</td>
						</tr>				
						<tr><td height="5"></td><td></td></tr>
						<tr style="display: none;">
							<td class="t_title"><input type="checkbox" id="unReadMsg" name="unReadMsg" value="1"/></td>
							<td>
							<label for="unReadMsg">有未读干预消息</label>
							</td>
						</tr>	
					</tbody>
				</table>
			</div>
			<div data-options="region:'south',border:false" style="height:50px; border-top-width:1px;">	         		
				<div class="btn_center">
					<div class="n_btn_blue">
						<a href="javascript:;" id="toTrigger" class="toTrigger" onclick="query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
					</div>		
				</div>				
			</div>
		</div>	
	</div>	
	<div data-options="region:'center',border:false" style="border-left-width: 1px;overflow: hidden;">
		<div class="easyui-layout" data-options="fit:true">			
			<div data-options="region:'north',split:false,border:false" style="border-bottom-width: 1px;overflow: hidden;" >
				<div id="tb" class="m_search"> 
					<div class="m_search_last">	
						<span style="color: #ff6600; font-weight:bold;">共<span id="dataCount">0</span>记录</span>				
						<div class="btn_r">
							<c:if test="${crbZb eq 0}">
							<div id="id_ExportXls2" class="n_btn_grey" >
								<a id="id_ExportXls2_a" style="opacity: 0.2;" href="javascript:void(0);" ><i class="icon iconfont">&#xe628;</i><span>导出直报文件</span></a>
							</div>
							</c:if>
							<div class="n_btn_grey">
								<a href="javascript:;" onclick="batchAudit();"><i class="icon iconfont">&#xe607;</i><span>批量审核</span></a>
							</div>
							<div class="n_btn_grey"  id="id_printAll">
								<a href="javascript:;" onclick="printAll2();"><i class="icon iconfont">&#xe604;</i><span>批量打印</span></a>
							</div>
							<div class="n_btn_grey">
								<a  href="javascript:void(0);" onclick="ExportXls();"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
							</div>																	
						</div>		
					</div>
				</div>
			</div>
			<div data-options="region:'center',border:false">
				<div id="cardsPlace" style="width: 100%;"></div>	
			</div>
		</div>
	</div>	

	<div id="noteDialog" style="text-align: center;vertical-align: middle;display: none;">
		<input id="newNotes" style="width:350px; height:26px; border:1px solid #ddd; margin:10px 10px;" />
		<input id="noteSubid" type="hidden"/>
	</div>
	<div id="printCardChooseDialog" style="text-align: center;vertical-align: middle;display: none;">
		<input type="hidden" id="mstId" />
		<input type="hidden" id="sbId" />
		<div style="margin-bottom: 5px;margin-top:5px;"><input id="normalCrbCard" type="button" value="打印普通传染病报卡" onclick="var x=$('#mstId').val();var y=$('#sbId').val();printSingle(x,y);$('#printCardChooseDialog').dialog('close');" style="padding: 3px 10px;"/></div>
		<div style="margin-bottom: 5px;"><input id="YGCrbCard" type="button" value="打印乙肝传染病报卡" title="非乙肝报卡，不能打印" onclick="var x=$('#mstId').val();var y=$('#sbId').val();printSingleHBV(x,y);$('#printCardChooseDialog').dialog('close');" disabled="disabled" style="padding: 3px 10px;color:gray;"/></div>
		<div style="margin-bottom: 5px;"><input id="XBCrbCard" type="button" value="打印性病附卡" title="非性病报卡，不能打印" onclick="var x=$('#mstId').val();var y=$('#sbId').val();printSingleHIV(x,y);$('#printCardChooseDialog').dialog('close');" disabled="disabled" style="padding: 3px 10px;color:gray;"/></div>
		<c:if test="${isShowFjhZzd=='0'}">
			<div style="margin-bottom: 5px;"><input id="FJHCrbCard" type="button" value="打印肺结核附卡"  title="非肺结核报卡，不能打印" onclick="var x=$('#mstId').val();var y=$('#sbId').val();printSingleFJH(x,y);$('#printCardChooseDialog').dialog('close');" disabled="disabled" style="padding: 3px 10px;color:gray;"/></div>
		</c:if>
	</div>
	<div id="multiPrintCardChooseDialog" style="text-align: center;vertical-align: middle;display: none;">
		<div style="margin-bottom: 5px;margin-top:5px;"><input id="normalCrbCards" type="button" value="打印普通传染病报卡" onclick="printAll();$('#multiPrintCardChooseDialog').dialog('close');" style="padding: 3px 10px;"/></div>
		<div style="margin-bottom: 5px;"><input id="YGCrbCard" type="button" value="打印所有乙肝传染病报卡" onclick="printHBVs();$('#multiPrintCardChooseDialog').dialog('close');" style="padding: 3px 10px;"/></div>
		<div style="margin-bottom: 5px;"><input id="XBCrbCard" type="button" value="打印所有HIV附卡" onclick="printHIVs();$('#multiPrintCardChooseDialog').dialog('close');" style="padding: 3px 10px;"/></div>
		<c:if test="${isShowFjhZzd=='0'}">
			<div style="margin-bottom: 5px;"><input id="FJHCrbCard" type="button" value="打印所有肺结核附卡" onclick="printFJHs();$('#multiPrintCardChooseDialog').dialog('close');" style="padding: 3px 10px;"/></div>
		</c:if>
	</div>
	<script type="text/javascript">
	 	$(function(){
	 		//
	 		$("#noteDialog").show();
	 		$("#printCardChooseDialog").show();
	 		$("#multiPrintCardChooseDialog").show();
	 		if($("#cardType").combobox("getValue")=="crbbk"){$("#sbjb").show();}
	 		
	 		$("#cardType").combobox({
				onChange: function (n,o) {
					if(n=="crbbk"){
						$("#sbjb").show();
						$('#id_ExportXls2').show();
					}else{
						$("#sbjb").hide();
						$('#id_ExportXls2').hide();
					}
					query();
				}
			});
			<c:if test="${!empty cardType}">
	 			$("#cardType").combobox("setValue","${cardType}");
	 		</c:if> 
	 		
	 		Csm.combogrid.crbDisease({
	 			id: 'reportdiseaseid',
	 			hospId: '',
	 			keyword:$("#reportdiseaseid").val()
	 		});
	 		
	 		Csm.combogrid.dep({
				//【必传】控件名称
				id: 'deptId',
				//【可选参数】不传默认区session的医院ID
				hospId: ''
				//【可选参数】不传默认区所有监控科室
				//dataType: 'mz'
			});
	 		Csm.combogrid.doctor({
				//【必传】控件名称
				id: 'doctorId',
				//【可选参数】不传默认区session的医院ID
				hospId: ''
				//【可选参数】不传默认区所有监控科室
				//dataType: 'mz'
			});
	 		
	 		query();
	 		
	 		$('#noteDialog').dialog({
                title: '请输入备注',
                collapsible: false,
                minimizable: false,
                maximizable: false,
                resizable: false,
                width: 400,
                height: 155,
                modal: true,
                closed:true,
                onClose:function(){
                	$("#newNotes").val("");
                },
                buttons: [{
                    text: '确定',
                    iconCls: 'icon-ok',
                    handler: function () {
                    	updateNote();
                    }
                }, {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        $('#noteDialog').dialog('close');
                    }
                }]
            });
	 		$("#printCardChooseDialog").dialog({
	 			title: '请选择要打印报卡的类别',
                collapsible: false,
                minimizable: false,
                maximizable: false,
                resizable: false,
                width: 400,
                height: 220,
                modal: true,
                closed:true,
                onClose:function(){
                	$("#YGCrbCard").attr("disabled","disabled").attr("title","非乙肝报卡，不能打印").css("color","gray");
                	$("#XBCrbCard").attr("disabled","disabled").attr("title","非性病报卡，不能打印").css("color","gray");
                	$("#FJHCrbCard").attr("disabled","disabled").attr("title","非肺结核报卡，不能打印").css("color","gray");
                }
	 		});
	 		$("#multiPrintCardChooseDialog").dialog({
	 			title: '请选择要打印报卡的类别',
	 			collapsible: false,
                minimizable: false,
                maximizable: false,
                resizable: false,
                width: 400,
                height: 220,
                modal: true,
                closed:true,
                onClose:function(){
                	
                }
	 		});
	 	});
		function changeSelect(ele){
			$(".cur").removeClass("cur");
			$(ele).addClass("cur");
		}
		function showExportXls2(){
			$('#id_ExportXls2_a').attr('style','');
			$('#id_ExportXls2_a').attr('onclick','ExportXls3();');
		}
		function hideExportXls2(){
			$('#id_ExportXls2_a').attr('style','opacity: 0.2');
			$('#id_ExportXls2_a').attr('onclick','');
		}
		function query(){
			var aturl = "${webroot}/cdc/f_view/getAllCards.shtml";
			var t=$("#searchString").val();
			$("#cardsPlace").load(aturl,{
				'cardStates':$(".cur").attr("dvalue"),
				'cardType':$("#cardType").combobox("getValue"),
				'dateType':$("#dateType").combobox("getValue"),
				'unReadMsg':$("input[name='unReadMsg']:checked").val(),
				'searchString':(t=="姓名/门诊号/${patientZyTitle}"?"":t),
				'queryStartDate':$("#queryStartDate").val(),
				'queryEndDate':$("#queryEndDate").val(),
				'reportdoctorid':$("#doctorId").combogrid("getValue"),
				'reportdeptid':$("#deptId").combogrid("getValue"),
				'diseaseId':$("#reportdiseaseid").combogrid("getValue"),
				'isprint':$("#printFlag").combobox("getValue")
			});
		}
		function audit(msid){
			var bktype = $("#cardType").combobox("getValue");
			var url="${webroot}/cdc/f_json/auditCards.shtml";
			$.messager.confirm("提示", "确认审核该报卡？", function (r) {
				if(r){
					$.ajax({
						url:url,
						data:{
							'bktype':bktype,
							'masterid':msid
						},
						type:'POST',
						success:function(data){
							data = eval("("+data+")");
							if(data.result=="success"){
								$.messager.show({ title: '提示', msg: data.msg });
								query();
							}else{
								$.messager.show({ title: '提示', msg: data.msg });
							}
						},error:function(){
							$.messager.alert("提示","抱歉，操作失败！");
						}
					});
				}
			});
		}
		function retreat(msid){
			var bktype = $("#cardType").combobox("getValue");
			var url="${webroot}/cdc/f_json/retreatCards.shtml";
			$.messager.prompt("提示", "确认退回该报卡？<br/>请输入退卡原因（必填）：", function (data) {
				if(typeof data=="string"){
					if(data){
						$.ajax({
							url:url,
							data:{
								'bktype':bktype,
								'masterid':msid,
								'delreason':data
							},
							type:'POST',
							success:function(data){
								data = eval("("+data+")");
								if(data.result=="success"){
									$.messager.show({ title: '提示', msg: data.msg });
									query();
								}else{
									$.messager.show({ title: '提示', msg: data.msg });
								}
							},error:function(){
								$.messager.alert("抱歉，操作失败！");
							}
						});
					}else{
		            	$.messager.show({ title: '提示', msg: "退卡原因为空！无法进行退卡操作"});
		            }
				}
			});
		}
		function remove(msid){
			var bktype = $("#cardType").combobox("getValue");
			var url="${webroot}/cdc/f_json/removeCards.shtml";
			$.messager.prompt("提示", "确认删除该报卡？<br/>请输入删卡原因（必填）：", function (data) {
	            if (data) {
	            	$.ajax({
						url:url,
						data:{
							'bktype':bktype,
							'masterid':msid,
							'delreason':data
						},
						type:'POST',
						success:function(data){
							data = eval("("+data+")");
							if(data.result=="success"){
								$.messager.show({ title: '提示', msg: data.msg });
								query();
							}else{
								$.messager.show({ title: '提示', msg: data.msg });
							}
						},error:function(){
							$.messager.show({ title: '提示', msg: "抱歉，操作失败！"});
						}
					});
	            }else{
	            	$.messager.show({ title: '提示', msg: "删卡原因为空！无法进行删卡操作"});
	            }
	        });
		}
		function cancel(msid,cardStates){
			var bktype = $("#cardType").combobox("getValue");
			var url="${webroot}/cdc/f_json/cancelCards.shtml";
			var tip = "";
			if(cardStates==1){
				tip = "确认取消对该报卡的审核操作？";
			}else if(cardStates==2){
				tip = "确认取消对该报卡的退回操作？";
			}else if(cardStates==3){
				tip = "确认取消对该报卡的删除操作？";
			}
			$.messager.confirm("提示", tip, function (r) {
				if(r){
					$.ajax({
						url:url,
						data:{
							'bktype':bktype,
							'masterid':msid
						},
						type:'POST',
						success:function(data){
							data = eval("("+data+")");
							if(data.result=="success"){
								$.messager.show({ title: '提示', msg: data.msg });
								query();
							}else{
								$.messager.show({ title: '提示', msg: data.msg });
							}
						},error:function(){
							$.messager.alert("抱歉，操作失败！");
						}
					});
				}
			});
		}
		function writeNote(sid,note){
			if(sid){
				//居中判断
				var top = document.body.scrollTop||document.documentElement.scrollTop;
				var mtop = parseInt(($(window).height()-155)/2);
				if(mtop>0){
					top += mtop;
				}
				$("#noteDialog").window('open').window('resize',{top: top});
				$("#newNotes").val(note);
				$("#noteSubid").val(sid);
			}else{
				$.messager.show({ title: '提示', msg: '必要参数为空，添加备注失败！'});
			}
		}
		function updateNote(){
			var bktype = $("#cardType").combobox("getValue");
			var nn = $("#newNotes").val();
			if(nn.length>40){
				$.messager.show({ title: '提示', msg: "备注信息太长，不能超过40个汉字！" });
				return;
			}
			$.ajax({
				url:"${webroot}/cdc/f_json/writeNote.shtml",
				type:"POST",
				data:{
					'bktype':bktype,
					'subid':$("#noteSubid").val(),
					'note':$("#newNotes").val()
				},
				success:function(data){
					data = eval("("+data+")");
					if(data.result=="success"){
						$.messager.show({ title: '提示', msg: data.msg });
						$('#noteDialog').dialog('close');
						query();
					}else{
						$.messager.show({ title: '提示', msg: data.msg });
					}
				},error:function(){
					$.messager.alert("抱歉，操作失败！");
				}
			});
		}
		function printAll2(){
			var bktype = $("#cardType").combobox("getValue");
			var purl = "${reportUrl}";
			if($("input[name='batchOpt']:checked").length>0){
				if(bktype=="crbbk"){
					//传染病
					var top = document.body.scrollTop||document.documentElement.scrollTop;
					var mtop = parseInt(($(window).height()-155)/2);
					if(mtop>0){
						top += mtop;
					}
					$("#multiPrintCardChooseDialog").window('open').window('resize',{top: top});
					
				}else{
					printAll();
				}
			}else{
				$.messager.show({ title: '提示', msg: "未获取到被勾选的数据记录！无法打印！" });
			}
		}
		function printFJHs(){
			var bktype = $("#cardType").combobox("getValue");
			var purl = "${reportUrl}"+"cdc/cdc.print.crb.FJHZZD.cpt";
 			var sexcard = "${fjhDiseasis}";
			if(purl && confirm("确认是否只打印肺结核附卡的记录？")){
				var mids = "";
				var sids = "";
				if($("input[name='batchOpt']:checked").length>0){
					//获取勾选的数据
					$("input[name='batchOpt']:checked").each(function(){
						if($(this).parent().parent().find(".cardStates").eq(0).val()=="1"){
							if(sexcard.indexOf($(this).parent().parent().find(".did").eq(0).val())>=0){
								var smid = $(this).parent().parent().find(".mid").eq(0).val();
    							if(smid){
    								mids+=smid+"|";
    							}
    							var ssid = $(this).parent().parent().find(".sid").eq(0).val();
    							if(ssid){
    								sids+=ssid+"|";
    							}
							}
						}
					});
					if(mids){
						mids=mids.substring(0, mids.length-1);
						sids=sids.substring(0, sids.length-1);
						if(mids.length>25){
							purl += "&masterid="+mids;
						}
						if(sids.length>25){
							purl += "&subid="+sids;
						}
						updatePrintFlag(bktype,mids);
						window.open(purl);
					}else{
						$.messager.show({ title: '提示', msg: "未获取到可以被打印的数据记录！无法打印！" });
					}
				}else{
					$.messager.show({ title: '提示', msg: "未获取到可以被打印的数据记录！无法打印！" });
				}
			}
		}
		function printHIVs(){
			var bktype = $("#cardType").combobox("getValue");
			var purl = "${reportUrl}"+"cdc/cdc.print.crb.HIV.cpt";
			var sexcard = "${sexcards}";
			if(purl && confirm("确认是否只打印性病附卡的记录？")){
				var mids = "";
				var sids = "";
				if($("input[name='batchOpt']:checked").length>0){
					//获取勾选的数据
					$("input[name='batchOpt']:checked").each(function(){
						if($(this).parent().parent().find(".cardStates").eq(0).val()=="1"){
							if(sexcard.indexOf($(this).parent().parent().find(".did").eq(0).val())>=0){
								var smid = $(this).parent().parent().find(".mid").eq(0).val();
    							if(smid){
    								mids+=smid+"|";
    							}
    							var ssid = $(this).parent().parent().find(".sid").eq(0).val();
    							if(ssid){
    								sids+=ssid+"|";
    							}
							}
						}
					});
					if(mids){
						mids=mids.substring(0, mids.length-1);
						sids=sids.substring(0, sids.length-1);
						if(mids.length>25){
							purl += "&masterid="+mids;
						}
						if(sids.length>25){
							purl += "&subid="+sids;
						}
						updatePrintFlag(bktype,mids);
						window.open(purl);
					}else{
						$.messager.show({ title: '提示', msg: "未获取到可以被打印的数据记录！无法打印！" });
					}
				}else{
					$.messager.show({ title: '提示', msg: "未获取到可以被打印的数据记录！无法打印！" });
				}
			}
		}
		function printHBVs(){
			var bktype = $("#cardType").combobox("getValue");
			var purl = "${reportUrl}"+"cdc/cdc.print.crb.HBV.cpt";
			if(purl && confirm("确认是否只打印乙肝附卡的记录？")){
				var mids = "";
				var sids = "";
				if($("input[name='batchOpt']:checked").length>0){
					//获取勾选的数据
					$("input[name='batchOpt']:checked").each(function(){
						if($(this).parent().parent().find(".cardStates").eq(0).val()=="1"){
							if($(this).parent().parent().find(".did").eq(0).val()=="0302"){
								var smid = $(this).parent().parent().find(".mid").eq(0).val();
    							if(smid){
    								mids+=smid+"|";
    							}
    							var ssid = $(this).parent().parent().find(".sid").eq(0).val();
    							if(ssid){
    								sids+=ssid+"|";
    							}
							}
						}
					});
					if(mids){
						mids=mids.substring(0, mids.length-1);
						sids=sids.substring(0, sids.length-1);
						if(mids.length>25){
							purl += "&masterid="+mids;
						}
						if(sids.length>25){
							purl += "&subid="+sids;
						}
						updatePrintFlag(bktype,mids);
						window.open(purl);
					}else{
						$.messager.show({ title: '提示', msg: "未获取到可以被打印的数据记录！无法打印！" });
					}
				}else{
					$.messager.show({ title: '提示', msg: "未获取到可以被打印的数据记录！无法打印！" });
				}
			}
		}
		function printAll(){
			var bktype = $("#cardType").combobox("getValue");
			var purl = "${reportUrl}";
			
			$.ajax({
				url : '${webroot}/cdc/f_json/report/file/get.shtml',
				dataType: 'json',
				data: { bktype : bktype},
				type: 'post',
				cache: false,
	            success : function(json){
	            	if(json.data != null && json.data != ''){
	    				purl += json.data;
	            	}else{
	            		$.messager.show({ title: '提示', msg: "报表文件名丢失，请核对相关参数！" });
	            		return;
	            	}
	    			if(purl){
	    				var mids = "";
	    				var sids = "";
	    				if($("input[name='batchOpt']:checked").length>0){
	    					//获取勾选的数据
	    					$("input[name='batchOpt']:checked").each(function(){
	    						if($(this).parent().parent().find(".cardStates").eq(0).val()=="1"){
	    							var smid = $(this).parent().parent().find(".mid").eq(0).val();
	    							if(smid){
	    								mids+=smid+"|";
	    							}
	    							var ssid = $(this).parent().parent().find(".sid").eq(0).val();
	    							if(ssid){
	    								sids+=ssid+"|";
	    							}
	    						}
	    					});
	    					if(mids){
		    					mids=mids.substring(0, mids.length-1);
		    					sids=sids.substring(0, sids.length-1);
		    					if(mids.length>25){
		    						purl += "&masterid="+mids;
		    					}
		    					if(sids.length>25){
		    						purl += "&subid="+sids;
		    					}
		    					updatePrintFlag(bktype,mids);
		    					window.open(purl);
	    					}else{
	    						$.messager.show({ title: '提示', msg: "未获取到可以被打印的数据记录！无法打印！" });
	    					}
	    				}else{
	    					$.messager.show({ title: '提示', msg: "未获取到可以被打印的数据记录！无法打印！" });
	    				}
	    			}
	            }
			});
		}
		function printCDC(msid,subid,cdcId){
			var bktype = $("#cardType").combobox("getValue");
			//fz
			$("#mstId").val(msid);
			$("#sbId").val(subid);
			$("#cdcId").val(cdcId);
			
			var sexcard = "${sexcards}";
			var fjhcard = "${fjhDiseasis}";
			
			if(bktype=="crbbk"){
				//传染病
				var top = document.body.scrollTop||document.documentElement.scrollTop;
				var mtop = parseInt(($(window).height()-155)/2);
				if(mtop>0){
					top += mtop;
				}
				$("#printCardChooseDialog").window('open').window('resize',{top: top});
				if(cdcId=="0302"){
					//解锁HBV
					$("#YGCrbCard").removeAttr("disabled").css("color","black").removeAttr("title");
				}
				if(sexcard.indexOf(cdcId)>=0){
					//解锁HIV
					$("#XBCrbCard").removeAttr("disabled").css("color","black").removeAttr("title");
				}
				if(fjhcard.indexOf(cdcId)>=0){
					//解锁肺结核
					$("#FJHCrbCard").removeAttr("disabled").css("color","black").removeAttr("title");
				}
			}else{
				printSingle(msid,subid);
			}
		}
		function printSingleHIV(msid,subid){
			var bktype = $("#cardType").combobox("getValue");
			var purl = "${reportUrl}"+"cdc/cdc.print.crb.HIV.cpt";
			if(msid && subid){
				updatePrintFlag(bktype,msid);
				window.open(purl+"&subid="+subid+"&masterid="+msid);
			}else{
				$.messager.show({ title: '提示', msg: "未获取到需打印的数据记录！无法打印！" });
			}
		}
		function printSingleFJH(msid,subid){
			var bktype = $("#cardType").combobox("getValue");
			var purl = "${reportUrl}"+"cdc/cdc.print.crb.FJHZZD.cpt";
			if(msid && subid){
				updatePrintFlag(bktype,msid);
				window.open(purl+"&subid="+subid+"&masterid="+msid);
			}else{
				$.messager.show({ title: '提示', msg: "未获取到需打印的数据记录！无法打印！" });
			}
		}
		function printSingleHBV(msid,subid){
			var bktype = $("#cardType").combobox("getValue");
			var purl = "${reportUrl}"+"cdc/cdc.print.crb.HBV.cpt";
			if(msid && subid){
				updatePrintFlag(bktype,msid);
				window.open(purl+"&subid="+subid+"&masterid="+msid);
			}else{
				$.messager.show({ title: '提示', msg: "未获取到需打印的数据记录！无法打印！" });
			}
		}
		function printSingle(mid,sid){
			var bktype = $("#cardType").combobox("getValue");
			var purl = "${reportUrl}";
			$.ajax({
				url : '${webroot}/cdc/f_json/report/file/get.shtml',
				dataType: 'json',
				data: { bktype : bktype},
				type: 'post',
				cache: false,
	            success : function(json){
	            	if(json.data != null && json.data != ''){
	    				purl += json.data;
	            	}else{
	            		$.messager.show({ title: '提示', msg: "报表文件名丢失，请核对相关参数！" });
	            		return;
	            	}
	    			if(bktype=="crbbk"){
	    				if(mid && sid){
	    					updatePrintFlag(bktype,mid);
	    					window.open(purl+"&subid="+sid+"&masterid="+mid);
	    				}else{
	    					$.messager.show({ title: '提示', msg: "未获取到可以被打印的数据记录！无法打印！" });
	    				}
	    			}else{
	    				//其他的都只用mid  所以合在一起
	    				if(mid){
	    					//更新打印标识
	    					updatePrintFlag(bktype,mid);
	    					window.open(purl+"&masterid="+mid);
	    				}
	    			}
	            }
			});
		}
		function batchAudit(){
			var bktype = $("#cardType").combobox("getValue");
			if($("input[name='batchOpt']:checked").length>0){
				$.messager.confirm("提示","确认批量审核已勾选的未审核的记录(其他状态的数据将被过滤)？",function(r){
					if(r){
						var mids = "";
						$("input[name='batchOpt']:checked").each(function(){
							var cs = $(this).parent().parent().find(".cardStates").val();
							if(cs==0){
								mids += "'"+$(this).parent().parent().find(".mid").val()+"',";
							}
						});
						mids = mids.substring(0, mids.length-1);
						$.ajax({
							url:"${webroot}/cdc/f_json/batchAudit.shtml",
							data:{
								'bktype':bktype,
								'masterids':mids
							},
							success:function(data){
								data = eval("("+data+")");
								if(data.result=="success"){
									$.messager.show({ title: '提示', msg: data.msg });
									query();
								}else{
									$.messager.show({ title: '提示', msg: data.msg });
								}
							},error:function(){
								$.messager.alert("抱歉，操作失败！");
							}
						});
					}
				});
			}else{
				$.messager.show({ title: '提示', msg: "未获取到已勾选的数据记录！无法批量审核！" });
			}
		}
		function checkAll(ele){
			if($(ele).is(":checked")){
				$("input[name='batchOpt']").each(function(){
					$(this).attr("checked","checked");
				});
			}else{
				$("input[name='batchOpt']").each(function(){
					$(this).removeAttr("checked");
				});
			}
		}
		function sendMessage(zyid,mzid){
			Comm.dialogGlobal({
		    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid="+zyid+"&mzid="+mzid+"&msgType=1",
		        title: "干预会话",
		        width:870,
		        height:555,
		        type:"iframe"
		    });
		}
		function showDetail(zyid,mzid){
			if(!zyid){
		    	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecordsMz.shtml?mzid='+mzid+"&tab=1",true);
			}else if(!mzid){
		    	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
			}
	    }

		function ExportXls(){
			var cd = $("#cardsPlace .tables").length;
			if(cd<=0){
				$.messager.show({ title: '提示', msg: "无数据被导出！" });
				return;
			}else{
				var bktype = $("#cardType").combobox("getValue");
				var purl = "${reportUrl}";
				$.ajax({
					url:"${webroot}/cdc/f_json/export/file/get.shtml",
					data: { bktype : bktype},
					dataType: 'json',
					type: 'post',
					cache: false,
					success:function(json){
						if(json.data){
							purl+=json.data+"&flag="+$("a.cur").attr("dvalue")+"&DTType="+$("#dateType option:selected").val()+"&ReportDoctorID="+$("#doctorId").combobox('getValue')+"&endDate="+$("#queryEndDate").val()+"&startDate="+$("#queryStartDate").val()+"&ReportDeptID="+$("#deptId").combobox('getValue')+"&PatientKey="+($("#searchString").val()=="姓名/门诊号/${patientZyTitle}"?"":$("#searchString").val())+"&isprint="+$("#printFlag").combobox("getValue");
							window.open(purl);
		            	}else{
		            		$.messager.show({ title: '提示', msg: "导出报表路径获取失败！无法导出！" });
		            	}
					}
				});
			}
		}
		
		function ExportXls2(){
			var t=$("#searchString").val();
			var cd = $("#cardsPlace .tables").length;
			if(cd<=0){
				$.messager.show({ title: '提示', msg: "无数据被导出！" });
				return;
			}else{
				var form=$("<form>");//定义一个form表单
				form.attr("style","display:none");
				form.attr("target","");
				form.attr("method","post");
				form.attr("action","${webroot}/cdc/f_json/export/file/get2.shtml");
				var input1=$("<input>");
				input1.attr("type","hidden");
				input1.attr("name","cardStates");
				input1.attr("value",$(".cur").attr("dvalue"));
				var input2=$("<input>");
				input2.attr("type","hidden");
				input2.attr("name","cardType");
				input2.attr("value",'crbbk');
				var input3=$("<input>");
				input3.attr("type","hidden");
				input3.attr("name","dateType");
				input3.attr("value",$("#dateType option:selected").val());
				var input4=$("<input>");
				input4.attr("type","hidden");
				input4.attr("name","unReadMsg");
				input4.attr("value",$("input[name='unReadMsg']:checked").val());
				var input5=$("<input>");
				input5.attr("type","hidden");
				input5.attr("name","searchString");
				input5.attr("value",(t=="姓名/门诊号/${patientZyTitle}"?"":t));
				var input6=$("<input>");
				input6.attr("type","hidden");
				input6.attr("name","queryStartDate");
				input6.attr("value",$("#queryStartDate").val());
				var input7=$("<input>");
				input7.attr("type","hidden");
				input7.attr("name","queryEndDate");
				input7.attr("value",$("#queryEndDate").val() + ' 23:59:59');
				var input8=$("<input>");
				input8.attr("type","hidden");
				input8.attr("name","reportdoctorid");
				input8.attr("value",$("#doctorId").combogrid("getValue"));
				var input9=$("<input>");
				input9.attr("type","hidden");
				input9.attr("name","reportdeptid");
				input9.attr("value",$("#deptId").combogrid("getValue"));
				$("body").append(form);//将表单放置在web中
				form.append(input1);
				form.append(input2);
				form.append(input3);
				form.append(input4);
				form.append(input5);
				form.append(input6);
				form.append(input7);
				form.append(input8);
				form.append(input9);
				form.submit();
			}
		}
		
		//只导出被勾选的记录
		function ExportXls3(){
			if($("input[name='batchOpt']:checked").length>0){
				$.messager.confirm("提示","确认导出已勾选的记录？",function(r){
					if(r){
						var sids = "";
						$("input[name='batchOpt']:checked").each(function(){
							//var cs = $(this).parent().parent().find(".cardStates").val();
							//if(cs==0){
							//	mids += "'"+$(this).parent().parent().find(".mid").val()+"',";
							//}
							sids += "'"+$(this).parent().parent().find(".sid").val()+"',";
						});
						sids = sids.substring(0, sids.length-1);
						
						var t=$("#searchString").val();
						var form=$("<form>");//定义一个form表单
						form.attr("style","display:none");
						form.attr("target","");
						form.attr("method","post");
						form.attr("action","${webroot}/cdc/f_json/export/file/get2.shtml");
						var input1=$("<input>");
						input1.attr("type","hidden");
						input1.attr("name","cardStates");
						input1.attr("value",$(".cur").attr("dvalue"));
						var input2=$("<input>");
						input2.attr("type","hidden");
						input2.attr("name","cardType");
						input2.attr("value",'crbbk');
						var input3=$("<input>");
						input3.attr("type","hidden");
						input3.attr("name","dateType");
						input3.attr("value",$("#dateType option:selected").val());
						var input4=$("<input>");
						input4.attr("type","hidden");
						input4.attr("name","unReadMsg");
						input4.attr("value",$("input[name='unReadMsg']:checked").val());
						var input5=$("<input>");
						input5.attr("type","hidden");
						input5.attr("name","searchString");
						input5.attr("value",(t=="姓名/门诊号/${patientZyTitle}"?"":t));
						var input6=$("<input>");
						input6.attr("type","hidden");
						input6.attr("name","queryStartDate");
						input6.attr("value",$("#queryStartDate").val());
						var input7=$("<input>");
						input7.attr("type","hidden");
						input7.attr("name","queryEndDate");
						input7.attr("value",$("#queryEndDate").val() + ' 23:59:59');
						var input8=$("<input>");
						input8.attr("type","hidden");
						input8.attr("name","reportdoctorid");
						input8.attr("value",$("#doctorId").combogrid("getValue"));
						var input9=$("<input>");
						input9.attr("type","hidden");
						input9.attr("name","reportdeptid");
						input9.attr("value",$("#deptId").combogrid("getValue"));
						var input10=$("<input>");
						input9.attr("type","hidden");
						input9.attr("name","subid");
						input9.attr("value",sids);
						$("body").append(form);//将表单放置在web中
						form.append(input1);
						form.append(input2);
						form.append(input3);
						form.append(input4);
						form.append(input5);
						form.append(input6);
						form.append(input7);
						form.append(input8);
						form.append(input9);
						form.append(input10);
						form.submit();
					}
				});
			}else{
				$.messager.confirm("提示","未获取到已勾选的记录，是否需要导出该条件下所有记录？",function(r){
					if(r){
						ExportXls2();
					}
				});
			}
			setTimeout("query();",3000);
		}
		
		
		 function showTip(msg){
			 $.messager.alert("提示",msg);
		 }
		 
		 function updatePrintFlag(bktype,msid){
			 $.ajax({
				 url:"${webroot}/cdc/f_json/updataPrintFlag.shtml",
				 data:{
					 bktype:bktype,
					 msid:msid
				 },
				 type:"POST",
				 success:function(msg){
					 //console.log(msg);
					 //刷新
					 query();
				 },
				 error:function(){
					 $.messager.alert("抱歉！更新打印状态失败！");
				 }
			 });
		 }
	</script>
</body>
</html>
