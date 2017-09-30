<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="footer_btn" style="display: none;">
	<div class="n_btn_grey">
		<a href="javascript:;" class="no_ico"><span>稍后再报</span></a>
	</div>
</div>
<div class="footer_btn">
	<c:if test="${acType eq 'hospital'}">
		<c:choose>
			<c:when test="${empty flag}">
				<div class="n_btn_blue" id="upload-main">
					<a href="javascript:report();" id="upload" class="no_ico"><span>保存</span></a>
				</div>
			</c:when>
			<c:when test="${flag==0}">
				<div class="n_btn_blue" id="upload-main">
					<a href="javascript:audit('${BKT}','${MSTID}');" id="auditit" class="no_ico"><span>审核</span></a>
				</div>
				<div class="n_btn_blue" id="upload-main">
					<a href="javascript:retreat('${BKT}','${MSTID}');" id="treatit" class="no_ico"><span>退卡</span></a>
				</div>
				<div class="n_btn_blue" id="upload-main">
					<a href="javascript:remove('${BKT}','${MSTID}');" id="removeit" class="no_ico"><span>删卡</span></a>
				</div>
				<div class="n_btn_blue" id="upload-main">
					<a href="javascript:report();" id="upload" class="no_ico"><span>保存</span></a>
				</div>
				<c:if test="${BKT eq 'sybk'}">
					<div class="n_btn_blue" id="upload-main">
						<a href="javascript:printCDC('${BKT}',$('#masterid').val());" id="printButt" class="no_ico"><span>打印</span></a>
					</div>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="${flag==1}">
					<div class="n_btn_blue" id="upload-main">
						<a href="javascript:printCDC('${BKT}',$('#masterid').val());" id="printButt" class="no_ico"><span>打印</span></a>
					</div>
				</c:if>
				<div class="n_btn_blue" id="upload-main">
					<a href="javascript:cancel('${BKT}','${MSTID}','${flag}');" id="cancelit" class="no_ico"><span>取消<c:if test="${flag==1}">审核</c:if><c:if test="${flag==2}">退卡</c:if><c:if test="${flag==3}">删卡</c:if></span></a>
				</div>
			</c:otherwise>
		</c:choose>

	</c:if>
	<c:if test="${acType eq 'doctor'}">
		<c:choose>
			<c:when test="${empty flag || flag==2}">
				<div class="n_btn_blue" id="upload-main">
					<a href="javascript:report();" id="upload" class="no_ico"><span>保存</span></a>
				</div>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${BKT eq 'sybk' && flag!=3}">
						<div class="n_btn_blue" id="upload-main">
							<a href="javascript:printCDC('${BKT}',$('#masterid').val());" id="printButt" class="no_ico"><span>打印</span></a>
						</div>
					</c:when>
					<c:when test="${flag==1}">
						<c:if test="${allowClinicPrint==1}">
							<div class="n_btn_blue" id="upload-main">
								<a href="javascript:printCDC('${BKT}',$('#masterid').val());" id="printButt" class="no_ico"><span>打印</span></a>
							</div>
						</c:if>
					</c:when>
				
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:if>
	<div class="n_btn_grey">
		<a href="javascript:;" onclick="back();" class="no_ico"><span>取消</span></a>
	</div>
</div>
<div id="printCardChooseDialog" style="text-align: center;vertical-align: middle;display: none;">
	<div style="margin-bottom: 5px;margin-top:5px;"><input id="normalCrbCard" type="button" value="打印普通传染病报卡" onclick="printSingle('crbbk',masterId,subId);$('#printCardChooseDialog').dialog('close');" style="padding: 3px 10px;"/></div>
	<div style="margin-bottom: 5px;"><input id="YGCrbCard" type="button" value="打印乙肝传染病报卡" title="非乙肝报卡，不能打印" onclick="printSingleHBV(masterId,subId);$('#printCardChooseDialog').dialog('close');" disabled="disabled" style="padding: 3px 10px;color:gray;"/></div>
	<div style="margin-bottom: 5px;"><input id="XBCrbCard" type="button" value="打印性病附卡" title="非性病报卡，不能打印" onclick="printSingleHIV(masterId,subId);$('#printCardChooseDialog').dialog('close');" disabled="disabled" style="padding: 3px 10px;color:gray;"/></div>
	<c:if test="${isShowFjhZzd=='0'}">
		<div style="margin-bottom: 5px;"><input id="FJHCrbCard" type="button" value="打印肺结核附卡" title="非肺结核报卡，不能打印" onclick="printSingleFJH(masterId,subId);$('#printCardChooseDialog').dialog('close');" disabled="disabled" style="padding: 3px 10px;color:gray;"/></div>
	</c:if>
</div>
<script>
$(function(){
	$("#printCardChooseDialog").show();
	
	$("#printCardChooseDialog").dialog({
		title: '请选择要打印报卡的类别',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        resizable: false,
        width: 400,
        height: 205,
        modal: true,
        closed:true,
        onClose:function(){
        	$("#YGCrbCard").attr("disabled","disabled").attr("title","非乙肝报卡，不能打印").css("color","gray");
        	$("#XBCrbCard").attr("disabled","disabled").attr("title","非性病报卡，不能打印").css("color","gray");
        	$("#FJHCrbCard").attr("disabled","disabled").attr("title","非肺结核报卡，不能打印").css("color","gray");
        }
	});
});

function retreat(bktype,msid){
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
							var tt = parent.menuInfo.getCurSelectTabTitle();
							setTimeout("parent.menuInfo.refreshMenu('"+tt+"')",1000);
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
function remove(bktype,msid){
	var url="${webroot}/cdc/f_json/removeCards.shtml";
	$.messager.prompt("提示", "确认删除该报卡？<br/>请输入删卡原因（必填）：", function (data) {
		if(typeof data=="string"){
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
							var tt = parent.menuInfo.getCurSelectTabTitle();
							setTimeout("parent.menuInfo.refreshMenu('"+tt+"')",1000);
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
		}
    });
}
function cancel(bktype,msid,cardStates){
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
						var tt = parent.menuInfo.getCurSelectTabTitle();
						setTimeout("parent.menuInfo.refreshMenu('"+tt+"')",1000);
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
/**
 * 报卡类型；主记录ID；
 */
var subId = "";
var masterId = "";
var cdcId="";
function printCDC(bktype,msid){
	masterId = msid;
	var sexcard = "${sexcards}";
	var fjhcard = "${fjhDiseasis}";
	if(bktype=="crbbk"){
		$("#chosenDiseasis tbody tr").each(function(){
			cdcId = $(this).attr("rowno");
			if(cdcId=="0302"){
				//解锁HBV
				$("#YGCrbCard").removeAttr("disabled").css("color","black").removeAttr("title");
			}
			if(sexcard.indexOf(cdcId)>=0){
				//解锁HIV
				$("#XBCrbCard").removeAttr("disabled").css("color","black").removeAttr("title");
			}
			if(fjhcard.indexOf(cdcId)>=0){
				//解锁FJH
				$("#FJHCrbCard").removeAttr("disabled").css("color","black").removeAttr("title");
			}
			subId += $(this).find(".diseaseSubid").eq(0).val()+"|";
			masterId += msid+"|";
		});
		//去掉后面的竖线
		masterId = masterId.substring(0, masterId.length-1);
		subId = subId.substring(0, subId.length-1);
		//传染病
		var top = document.body.scrollTop||document.documentElement.scrollTop;
		var mtop = parseInt(($(window).height()-155)/2);
		if(mtop>0){
			top += mtop;
		}
		$("#printCardChooseDialog").window('open').window('resize',{top: top});
	}else{
		printSingle(bktype,masterId,subId);
	}
}
function printSingleHIV(msid,subid){
	var purl = "${reportUrl}"+"cdc/cdc.print.crb.HIV.cpt";
	if(msid && subid){
		window.open(purl+"&subid="+subid+"&masterid="+msid);
	}else{
		$.messager.show({ title: '提示', msg: "未获取到需打印的数据记录！无法打印！" });
	}
}
function printSingleFJH(msid,subid){
	var purl = "${reportUrl}"+"cdc/cdc.print.crb.FJHZZD.cpt";
	if(msid && subid){
		window.open(purl+"&subid="+subid+"&masterid="+msid);
	}else{
		$.messager.show({ title: '提示', msg: "未获取到需打印的数据记录！无法打印！" });
	}
}
function printSingleHBV(msid,subid){
	var purl = "${reportUrl}"+"cdc/cdc.print.crb.HBV.cpt";
	if(msid && subid){
		window.open(purl+"&subid="+subid+"&masterid="+msid);
	}else{
		$.messager.show({ title: '提示', msg: "未获取到需打印的数据记录！无法打印！" });
	}
}
function printSingle(bktype,mid,sid){
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

function updatePrintFlag(bktype,msid){
	 $.ajax({
		 url:"${webroot}/cdc/f_json/updataPrintFlag.shtml",
		 data:{
			 bktype:bktype,
			 msid:msid
		 },
		 type:"POST",
		 success:function(msg){
		 },
		 error:function(){
			 $.messager.alert("抱歉！更新打印状态失败！");
		 }
	 });
}
</script>