<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>上传资料</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<!--  文件上传空件 -->
	<link rel="stylesheet" type="text/css" href="${webroot}/resources/uploadify/uploadify.css">
    <link rel="stylesheet" type="text/css" href="${webroot}/resources/uploadify/jquery.Jcrop.css">
    <script type="text/javascript" src="${webroot}/resources/uploadify/jquery.uploadify.min.js"></script>
    <script type="text/javascript" src="${webroot}/resources/uploadify/jquery.Jcrop.js"></script>
</head>
<!-- 上传资料第一步 -->
<div id="step_upload_1" class="zlk_upload">				
	<div class="zlk_upload_speed">					
		<div class="speed_son active"><span class="number">1</span><span class="text">选择文档</span></div>
		<div class="speed_bar"></div>
		<div class="speed_son"><span class="number">2</span><span class="text">补充信息</span></div>
		<div class="speed_bar"></div>
		<div class="speed_son"><span class="number">3</span><span class="text">完成上传</span></div>
		<div class="clear"></div>
	</div>								
	<div class="zlk_upload_cont1">
		<input  type="file" id="file_upload_1" class="input_file" />
		<button type="button" class="btn_upload"  ><i class="icon iconfont">&#xe655;</i>上传资料</button>
		<div class="file_format"><span>文件格式：</span><i class="icon_format icon_word"></i><i class="icon_format icon_excel"></i><i class="icon_format icon_pdf"></i><i class="icon_format icon_txt"></i><i class="icon_format icon_ppt"></i></div>
	</div>
	<div class="zlk_upload_note">
		<div class="zlk_upload_note_t">温馨提示:</div>					
		<p>1、支持格式为：DOC, XLS, PDF, TXT, PPT的资料上传；</p>
		<p>2、单个文件不能超过50MB；</p>
	</div>
</div>
<!-- 上传资料第二步 -->
<div id="step_upload_2" class="zlk_upload" style="display:none;">				
	<div class="zlk_upload_speed">					
		<div class="speed_son active"><span class="number">1</span><span class="text">选择文档</span></div>
		<div class="speed_bar active"></div>
		<div class="speed_son active"><span class="number">2</span><span class="text">补充信息</span></div>
		<div class="speed_bar"></div>
		<div class="speed_son"><span class="number">3</span><span class="text">完成上传</span></div>
		<div class="clear"></div>
	</div>								
	<div class="zlk_upload_btn">
		<span class="zlk_upload_btn_text"></span>
		<!-- <button type="button" class="btn btn-default"><i class="icon iconfont">&#xe602;</i> 继续添加</button> -->
		<button type="button" class="btn btn-warning" onclick="submitFileInfo();"><i class="icon iconfont">&#xe607;</i> 确认上传</button>					
	</div>
	<!-- 待上传文件列表 -->	
	<div id="upload_list" class="zlk_upload_main">
		<%-- <div class="upload_list">
			<div class="item_index">1</div>
			<input id="upload_index" type="hidden" value="1">
			<div class="item_op">
				<a href="" class="item_op_delete" title="删除" hidefocus="true">删除</a>
			</div>				
			<div class="item_bd">					
				<table class="table_noboredr table_from">
					<tr>
						<td class="title_r" style="width:80px;"><span class="red">*</span> 标题：</td>
						<td>
							<div class="upload_list_title">
								<i name="docFormat" class="icon_format icon_txt"></i>
								<input type="text" name="docName_1" tabindex="15" class="upload_list_title_input" value="感控知识库大全">
							</div>
						</td>
					</tr>
					<tr>
						<td class="title_r" >&nbsp;</td>
						<td>
							<span class="item_msg" style="display:block;"><i class="icon iconfont green">&#xe62e;</i> 文档附件上传成功！</span>
							<span class="item_msg" style="display:;"><i class="icon iconfont blue">&#xe672;</i> 文档附件上传成功，但与已有文档重复，只能自己阅读！</span>
							<span class="item_msg" style="display:;"><i class="icon iconfont blue">&#xe672;</i> 建议您结合文档正文完善文档标题信息</span>
							<span class="item_msg" style="display:;"><i class="icon iconfont red">&#xe649;</i> 文档附件上传失败！</span>
						</td>
					</tr>
					<tr>
						<input type="hidden" id="docUrl" name="docUrl" >
						<input type="hidden" id="docFormat" name="docFormat" >
					    <input type="hidden" id="docSize" name="docSize" >
						<td class="title_r" ><span class="red">*</span> 分类：</td>
						<td>
							<nis:select id="docType" name="docType" value="" dictcode="docType" cssCls="easyui-combobox" exp="style=\"width: 152px;\" data-options=\"editable:false\""/>
						</td>
					</tr>
				</table>					
			</div>
		</div> --%>
	</div>
</div>
<!-- 资料上传第三步 -->
<div id="step_upload_3" class="zlk_upload" style="display:none;">				
	<div class="zlk_upload_speed">
		<div class="speed_son active"><span class="number">1</span><span class="text">选择文档</span></div>
		<div class="speed_bar active"></div>
		<div class="speed_son active"><span class="number">2</span><span class="text">补充信息</span></div>
		<div class="speed_bar active"></div>
		<div class="speed_son active"><span class="number">3</span><span class="text">完成上传</span></div>
		<div class="clear"></div>
	</div>								
	<div class="zlk_upload_cont1">
		<span class="success_text"><i class="icon iconfont">&#xe62e;</i>恭喜！资料上传成功</span>
		<span class="zlk_upload_cont1_text"></span>
		<button type="button" class="btn btn-warning" onclick="refreshCurrentPage();">继续上传</button>					
	</div>				
</div>
	
<body>
<script type="text/javascript">
$(document).ready(function(){
	var index =  1;
	$("#file_upload_1").uploadify({
		fileSizeLimit : '50MB',
		fileObjName   : 'pic',
		fileDataName  : 'pic',
		progressData  : 'speed', 
		fileTypeDesc  : '格式:文本文件',     //描述
		width         : '320',
		height        : '50',
		fileTypeExts  : '*.doc;*.ppt;*.xls;*.docx;*.pptx;*.xlsx;*.pdf;*.txt;',
		cancelImg     : '${webroot}/resources/uploadify/uploadify/cancel.png',
		buttonImg     : '${webroot}/resources/uploadify/uploadify/buttonImg.png',  
		buttonText    : '上传我的文档',
		multi         : false,
		swf           : '${webroot}/resources/uploadify/uploadify.swf',
		uploader      : '${webroot}/docsearch/json/saveUpLoadFile.shtml',
		onUploadSuccess : function(file, data, response) {
			var json=jQuery.parseJSON(data);
			if(json.result==='success') {
				step2Style();
				$.each(json.data, function(i, item) {
					var str = '';
					if(item.isRepeat == '1'){
						str += '<span class="item_msg" style="display:block;"><i class="icon iconfont red">&#xe649;</i> 存在同名文件，系统已重命名文件！</span></td></tr>';
					}else{
						str += '<span class="item_msg" style="display:block;"><i class="icon iconfont green">&#xe62e;</i> 文档附件上传成功！</span>';
					}
		            // 写入指定的位置
		            $('#upload_list').append([
		           		'<div class="upload_list"><div class="item_index">'+ index +'</div><div class="item_op">'+
		           		//'<a href="" class="item_op_delete" title="删除" hidefocus="true">删除</a>'+
		           		'</div><div class="item_bd">'+
		           		'<table class="table_noboredr table_from">'+
		           		'<tr><td class="title_r" style="width:80px;"><span class="red">*</span> 标题：</td>'+
		           		'<td><div class="upload_list_title"><i name="docFormat_' + index + '" class="icon_format icon_txt"></i>'+
		           		'<input type="text" name="docName_' + index + '" tabindex="15" class="upload_list_title_input" value="'+ item.docName +'">'+
		           		'</div></td></tr>'+
		           		'<tr><td class="title_r" >&nbsp;</td><td>'+ str +
		           		//'<span class="item_msg" style="display:block;"><i class="icon iconfont green">&#xe62e;</i> 文档附件上传成功！</span>'+
		           		'<span class="item_msg" style="display:;"><i class="icon iconfont blue">&#xe672;</i> 文档附件上传成功，但与已有文档重复，只能自己阅读！</span>'+
		           		'<span class="item_msg" style="display:;"><i class="icon iconfont blue">&#xe672;</i> 建议您结合文档正文完善文档标题信息</span>'+
		           		'<span class="item_msg" style="display:;"><i class="icon iconfont red">&#xe649;</i> 文档附件上传失败！</span></td></tr>'+
		           		
		           		'<tr><input type="hidden" id="docUrl_' + index + '" name="docUrl' + index + '" ><input type="hidden" id="docFormat_' + index + '" name="docFormat_' + index + '" >'+
		           		'<input type="hidden" id="docSize_' + index + '" name="docSize_' + index + '" ><td class="title_r" ><span class="red">*</span> 分类：</td>'+
		           		'<td><nis:select id="docType_' + index + '" name="docType_' + index + '" value="" dictcode="docType" cssCls="easyui-combobox" exp="style=\"width: 152px;\" data-options=\"editable:false\""/></td>'+
		           		'</tr></table></div></div>'
		            ].join(''));
		            //$("input[name='docName_" + index + "']").val(item.docName);
		            //$("#docName").val(item.docName);
		            if(item.docFormat=="doc" || item.docFormat=="docx" ){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_doc")
		            }else if(item.docFormat=="xls" || item.docFormat=="xlsx"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_xls")
		            }else if(item.docFormat=="ppt" || item.docFormat=="pptx"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_ppt")
		            }else if(item.docFormat=="vsd"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_vsd")
		            }else if(item.docFormat=="pot"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_pot")
		            }else if(item.docFormat=="pps"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_pps")
		            }else if(item.docFormat=="rtf"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_rtf")
		            }else if(item.docFormat=="wps"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_wps")
		            }else if(item.docFormat=="epub"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_epub")
		            }else if(item.docFormat=="et"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_et")
		            }else if(item.docFormat=="dps"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_dps")
		            }else if(item.docFormat=="txt"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_txt")
		            }else if(item.docFormat=="pdf"){
		          		$("i[name='docFormat_" + index + "']").removeClass("ic_txt").addClass("ic_pdf")
		            }else{
		          		$("i[name='docFormat'_" + index + "]").removeClass("ic_txt").addClass("ic_dir")
		            }
		            $("#docUrl_" + index + "").val(item.docUrl);
		            $("#docFormat_" + index + "").val(item.docFormat);
		            $("#docSize_" + index + "").val(file.size);
		            index++;
				});
			}else if(json.result==='error'){
				// 文件上传错误。
			}else if(json.result==='0'){
				// 文档格式不匹配
				Comm.warnMsg("暂不支持您的文档格式");
			}
		},
		onUploadStart: function (file) { 
			$("#file_upload_1").uploadify("settings", "formData", {'userId': '${acAccount.userId}'});  
		}
	});
});
function step1Style(){	      	
	$("#step1_div").css("display","block");
	$("#step2_div").css("display","none");
	$("#step3_div").css("display","none");
};
function step2Style(){
	$("#step_upload_1").css("display","none");
	$("#step_upload_2").css("display","block");
	$("#step_upload_3").css("display","none"); 
};
function step3Style(){
	$("#step_upload_1").css("display","none");
	$("#step_upload_2").css("display","none");
	$("#step_upload_3").css("display","block"); 
};
// 移除"文档类型全部的选项"选项
//$("#docType option[value='1']").remove();
// 如果用户不是某个手机号就移出院感书籍，院感大讲堂的选项。
/* if(${acAccount.username!='admin' && acAccount.username!='18670385399'}){
	$("#docType option[value='8']").remove();
	$("#docType option[value='9']").remove();
}; */
function submitFileInfo(){
	var dName = $("input[name='docName_1']").val();
	if(dName === null || typeof dName == "undefined" || dName == 0 || dName === '' ){
		$.messager.show({ title: '提示', msg: '文件名不能为空！' });
		return false;
	}
/* 		 alert($("#docName").val()+"文件名字");
		 alert($("#docDesc").val()+"文档描述");
		 alert($("#docFormat").val()+"文档格式");
		 alert($("#docUrl").val()+"文档所路径");
		 alert($("#docType option:selected").val()+"文档类型");		 
		 alert($('input[name="docScope"]:checked').val()+"文档权限");
		 alert($("#docScore option:selected").val()+"文档所需分数");
	 if($("#docDesc").val()==''){
		 Comm.warnMsg("文档描述不能空");
		 return ;
	 }
	 if($("#docDesc").val().length<50){
		 Comm.warnMsg("文档描述不能少于50个字符");
		 return ;
	 }
	 var hasChk = $('#docAnonyrnous').is(':checked');
	 if(hasChk){
		 docAnonyrnousValue="1";
	 }
	var docAnonyrnousValue="0";    */
	$.ajax({
		url: '${webroot}/docsearch/s_view/submitFileInfo.shtml',
		type: 'post',
		data: {
			docName : $("input[name='docName_1']").val(),
			docType : $("#docType_1 option:selected").val(),
			docFormat : $("#docFormat_1").val(),
			docUrl : $("#docUrl_1").val(),
			docSize : $("#docSize_1").val()
			//docDesc:$("#docDesc").val(),
			//docScore:$("#docScore option:selected").val(),
			//docScope:$('input[name="docScope"]:checked').val(),
			//docAnonyrnous:docAnonyrnousValue,
		},
		dataType: 'json',
		success : function(json) {
			if(json.result==='success') {
				step3Style();
			} else if(json.result === 'repeatname') {
				$.messager.show({ title: '提示', msg: json.msg });
			} else {
				$.messager.show({ title: '提示', msg: json.msg });
			}
		}
	}); 
};
function refreshCurrentPage(){
    window.location.reload(); 
};
</script>
</body></html>