<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   	<title>问卷分享</title>
    <%@ include file="/WEB-INF/view/core/include.jsp"%>
    <script type="text/javascript" src="${webroot}/resources/zclip/jquery.zclip.js${version}"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery-migrate-1.1.0.js"></script>
    <script language="javascript"  src="${webroot}/resources/js/print/jquery.jqprint-0.3.js"></script>
</head>

<body  style="margin:20px auto; width:95%" >
	<table  class="table_blank mt10" cellspacing="0" cellpadding="0" border="1" >
		<tr>
			<td width="100" height="40">当前流程问卷：</td>
			<td width="180">
				<span class="standard_select">
					<span class="select_shelter">
						<input id="_qsList" name="qsList" />
					</span>
				</span>
			</td>
			<td>
				<a  href="javascript:share.viewQs1();">查看急诊问卷</a>
			</td>
			<td>
				<a  href="javascript:share.viewQs2();">查看导诊问卷</a>
			</td>
			<td>
				<a  href="javascript:share.viewQs3();">查看分诊问卷</a>
			</td>
			<td>
				<a  href="javascript:share.viewQs4();">查看综合满意度问卷</a>
			</td>
		</tr>
	<!-- 	<tr>
			<td>选择问卷科室：</td>
			<td>
				<span class="standard_select">
					<span class="select_shelter">
						<input id="_dep" name="dept" />
					</span>
				</span>
			</td>
			<td></td>
		</tr>	 -->									
	</table>
	<div class="quest_Share">
		<div class="quest_Share_title">1、电脑填写</div>	
		<div class="quest_Share_cont"><span>复制问卷链接</span><input type="text" id="pcLink" style="width:300px;" value="${pcLink}" /><input type="button" class="btn" id="copyLink" value="复制"/></div>
	</div>
	
	<div class="quest_Share">
		<div class="quest_Share_title">2、手机填写</div>
		<div class="quest_Share_cont">
			<div class="quest_Share_cont_left"><img  id="_qcCode1" src="${webroot}/qsFlow/f_view/questQcCode.shtml?fId=${fId}&isHttps=${isHttps}" style="width:150px;height:150px;"/> </div>
			<div class="quest_Share_cont_right">
				<p>通过<span class="b red">微信扫一扫</span>将问卷分享到朋友圈或发送给朋友填写</p>
				<input type="button" class="btn" id='hover' onclick="share.print();" value="打印"/>
			</div>
			<div class="clear"></div>
			<div class="quest_Share_print_conter quest_Share_position" style="display:none;"  >	
			<div id="quickCode" align="center" >
			<div id="fill">
			</div>
			<div class="quest_share_logo"><span id='unitName'></span>&nbsp;&nbsp;<span class="grey" id='depName'></span></div>
			<div class="quest_Share_print_title"><span id='quickCodeTitle'></span></div>			    
				<p id='qsNote'></p>
				<img id="_qcCode2" class="qr_code" src="${webroot}/qsFlow/f_view/questQcCode.shtml?fId=${fId}&isHttps=${isHttps}" style="width: 200px;height: 200px;"/>
				<div class="quest_Share_print_text">微信<span class="b">扫一扫</span>，填写问卷</div>				
			</div>			
			</div>
		</div>		
	</div>
	
<!--  	<div class="quest_Share">
		<div class="quest_Share_title"><span class="weixin"></span><span class="green">发给微信好友，填写问卷</span></div>	
		<div class="quest_Share_cont">
			<div class="weixin_note">
				<p>1、用微信"扫一扫"功能扫描问卷二维码；</p>
				<p>2、打开问卷后，点击右上角的"分享"按钮，然后选择"发送给朋友"或"分享到朋友圈"</p>
			</div>
			<div class="quest_Share_cont_left"><img src="${webroot}/resources/images_org/weixin_img01.jpg"/></div>
			<div class="quest_Share_cont_left"><img src="${webroot}/resources/images_org/weixin_img02.jpg"/></div>
			<div class="clear"></div>			
		</div>
-->	
	<input type="hidden" id="fId" value="${fId}" />
	<input type="hidden" id="ext1" value="${ext1}" />
	<input type="hidden" id="isHttps" value="${isHttps}" />
<script type="text/javascript">

$(document).ready(function(){
	var pcLink = $("#pcLink").val();
	$("#pcLink").val(getRootPath_web()+pcLink);
});

var share = {
	qsfirst : true ,
	depfirst : true ,
	onInit : true,
	//下载微信图片
	downQcCode : function(){
		var fId = $('#fId').val();
		var link = '${webroot}/qsFlow/f_view/downQcCode.shtml?fId='+fId;
		window.open(link); 
	},
	print:function(){
		var _style = $('#_qcCode2').attr('style');
		$('#_qcCode2').attr('style', 'width: 500px;height: 500px;');
		$('#quickCode').jqprint();
		$('#_qcCode2').attr('style', _style);
		$('#fill').html("");
	},
	
	//问卷下拉框
	qsCombobox : function(selectItem){
		var defalultValue = encodeURIComponent(encodeURIComponent("--请选择问卷--"));
		$('#_qsList').combobox({
		    url:'${webroot}/qsFlow/f_json/queryQsFlowEasyUi.shtml?fid='+selectItem+'&defalultValue='+defalultValue,
		    valueField:'key',
		    textField:'value',
		    onSelect : function(record){
		    	//不是第一次请求
		    	share.changeQs(record.key);
		    	setTimeout('getWenJuanInfo()',1100);
		    },
		});
	},
/* 	//科室下拉框
	depCombobox : function(){
		var allDepName = encodeURIComponent(encodeURIComponent("--请选择科室--"));
		$('#_dep').combobox({
		    url:'${webroot}/dep/json/queryByDepTypeEasyUiExt.shtml?isScope=false&allDepName='+allDepName,
		    valueField:'key',
		    textField:'value',
		    onSelect : function(record){
		    	$('#depName').html(record.value);
		    	//不是第一次请求
		    	share.changeDep(record.key);
		    },
		    onLoadSuccess : function(none){
		    	if (share.onInit) {
		    		var obj = $('#_dep').next().find("input");
		    		obj.focus(function(){
						var name = obj.val();
						if (name == '--请选择科室--') {
							obj.val('');
						}
					});
					
		    		obj.blur(function(){
						var name = obj.val();
						if (name == '') {
							obj.val('--请选择科室--');
						}
					});
		    	}
		    	share.onInit = false;
		    	 if($('#acDepNo').val()){
			 $('#_dep').combobox('setValue','${acDepNo}');
		    	 }
		    }
		});
	}, */
	//查看急诊问卷
	viewQs1 : function(){
		var fId = $('#fId').val();
		if(fId == null || fId == '' ){
			$.messager.show({ title: '提示', msg: '请先选择问卷!' });
			return;
		}
		var qIds=$('#ext1').val();
		var qId=qIds.split("#");
		window.open("${webroot}/qsFlow/f_view/viewQs.shtml?qId="+qId[0],"_blank");
	},
	//查看导诊问卷
	viewQs2 : function(){
		var fId = $('#fId').val();
		if(fId == null || fId == '' ){
			$.messager.show({ title: '提示', msg: '请先选择问卷!' });
			return;
		}
		var qIds=$('#ext1').val();
		var qId=qIds.split("#");
		window.open("${webroot}/qsFlow/f_view/viewQs.shtml?qId="+qId[1],"_blank");
	},
	//查看分诊问卷
	viewQs3 : function(){
		var fId = $('#fId').val();
		if(fId == null || fId == '' ){
			$.messager.show({ title: '提示', msg: '请先选择问卷!' });
			return;
		}
		var qIds=$('#ext1').val();
		var qId=qIds.split("#");
		window.open("${webroot}/qsFlow/f_view/viewQs.shtml?qId="+qId[2],"_blank");
	},
	//查看综合问卷
	viewQs4 : function(){
		var fId = $('#fId').val();
		if(fId == null || fId == '' ){
			$.messager.show({ title: '提示', msg: '请先选择问卷!' });
			return;
		}
		var qIds=$('#ext1').val();
		var qId=qIds.split("#");
		window.open("${webroot}/qsFlow/f_view/viewQs.shtml?qId="+qId[3],"_blank");
	},
	//改变问卷
	changeQs : function(fId){
		$('#fId').val(fId);
	/* 	var dep = $("input[name='dept']").val();
	 	dep = dep == 'alldep_nos' ? '' : '&depNo='+dep; */
		//重新生成请求连接
		$.ajax({
             url: '${webroot}/qsFlow/f_view/getPcLink.shtml',
             type: 'post',
             data: { 'fId': fId},
             dataType: 'json',
             success : function(json) {
	             	if (json.result === 'success') {
	             		$('#pcLink').val(json.data);	
					}
			 }
        });
		//重新生成微信二维码
		$('#_qcCode1').attr('src' , '${webroot}/qsFlow/f_view/questQcCode.shtml?fId='+fId+''+'&isHttps='+$('#isHttps').val());
		$('#_qcCode2').attr('src' , '${webroot}/qsFlow/f_view/questQcCode.shtml?fId='+fId+''+'&isHttps='+$('#isHttps').val());
	},
 /* 	//改动科室
	changeDep : function(depId){
		var dep = '&depNo='+depId;
		if(depId == 'alldep_nos'){
			dep = '';
		}
		var fId = $('#fId').val();
		var url = $('#pcLink').val();
		if(url.indexOf('&depNo') > 0){
			url = url.substring(0, url.indexOf('&depNo'));
		}
		$('#pcLink').val(url+''+dep);	
		//重新生成微信二维码
		$('#_qcCode1').attr('src' , '${webroot}/qsQuestionnaire/f_view/questQcCode.shtml?fId='+fId+''+dep+'&isHttps='+$('#isHttps').val());
		$('#_qcCode2').attr('src' , '${webroot}/qsQuestionnaire/f_view/questQcCode.shtml?fId='+fId+''+dep+'&isHttps='+$('#isHttps').val());
	} */
}; 

$(document).ready(function(){
    $('#copyLink').zclip({
        path : '${webroot}/resources/zclip/ZeroClipboard.swf',
        copy : function(){
            return $('#pcLink').val();
        }
    });
    var fId = $('#fId').val();
    //问卷下拉框
   share.qsCombobox(fId);
 /*    //科室下拉框
    share.depCombobox(); */
    
    //打印预览
	$("#hover").hover(function(){
		$(this).parent(".quest_Share_cont_right").parent(".quest_Share_cont").find(".quest_Share_print_conter").css({"display":"block"});
	},function(){
		$(this).parent(".quest_Share_cont_right").parent(".quest_Share_cont").find(".quest_Share_print_conter").css({"display":"none"});
	});
    
	setTimeout('getWenJuanInfo()',1100);
	
}
);

function getWenJuanInfo(){
	
	var qsList=document.getElementsByName('qsList');
	if(qsList[0].value!=null){
	$.ajax({
        url: '${webroot}/qsFlow/view/getWenJuanInfo.shtml',
        data : {fId:qsList[0].value},
        type: 'post',
        dataType: 'json',
        success : function(info) {
        	var data=info.data;
        	$('#quickCodeTitle').html(data.fName);
        	$('#qsNote').html(data.note);
        	$('#unitName').html(info.expandValue);
		},
		error:function(){
		}
	});
	
	}
	
}


</script>
</body>
</html>
