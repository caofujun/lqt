<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<jsp:useBean id="now2" class="java.util.Date" />
<style>
	.menu_title{border-bottom: 1px solid #f1f1f1;}
	.normal{padding: 6px 20px;margin:0px 5px;cursor: pointer;border-radius: 3px;}
	.selected{/* border: 1px solid #030af4; */background-color: #2196f3;color: #ffffff;}
</style>
<form id="fileForm">
	<div style="text-align: center;">
		<div class="menu_title" style="padding: 10px 1px;">
			<!-- <ul style="margin: 0;padding: 0;">
				<li style="float:left;border: 1px solid #000000;padding: 4px 20px;width:130px;margin:10px 5px; "><a>应急处理步骤</a></li>
				<li style="float:left;border: 1px solid #000000;padding: 4px 20px;width:130px;margin:10px 5px; "><a>职业暴露处置流程</a></li>
			</ul> -->
			<a class="image_menu normal selected" style="" tvalue="bl_yjclbz.png">&nbsp;应急处理步骤&nbsp;</a>
			<a class="image_menu normal" tvalue="bl_czlc.png">职业暴露处置流程</a>
		</div>
		<div style="clear: both"></div>
		<div class="image_view" style="height:370px;overflow: scroll;text-align: center;">
			<img id="preview" alt="暂无预览" src="" >
		</div>
		<div class="upload_part">
			<div style="margin:10px 5px;">
				<input type="file" name="upload" id="file" accept="image/jpeg,image/png" onchange="test();"/> 支持文件格式  *.jpg、*.png
			</div>
			<div>
				<input type="button" class="btn_save" id="uploadBtn" onclick="uploadEvent();" value="上传">
				<input type="button" class="btn_save" style="color: white;margin-right: 15px;background: none;background-color: #dddddd;" onclick="quitConfirm();" value="重置">
				<input type="reset" id="resetBtn"  value="reset" style="display: none;"/>
			</div>
		</div>
	</div>
</form>
<script>
var isupload = false;
$(function(){
	$("#preview").attr("src",'/download/zyblImages/'+$(".selected").attr("tvalue")+'?tz='+Math.random()).attr("alt",$(".selected").text()+"暂无预览");
	
	$(".image_menu").click(function(){
		var tv = $(".selected").attr("tvalue");
		var ttv = $(this).attr("tvalue");
		if(tv!= ttv && quitConfirm()){
			//改变当前选择的
			$(".selected").removeClass("selected");
			$(this).addClass("selected");
			/* //重置表单
			$("#reset").trigger("click"); */
			//更新图片路径
			$("#preview").attr("src",'/download/zyblImages/'+$(this).attr("tvalue")+'?tz='+Math.random());
		}
	})
});
function test(){
	 isupload=true; //
 	 var preview = document.querySelector('img');
	 var file  = document.querySelector('input[type=file]').files[0];
	 var reader = new FileReader();
	 reader.onloadend = function () {
	  preview.src = reader.result;
	 }
	 if (file) {
	  reader.readAsDataURL(file);
	 } else {
	  preview.src = "";
	 }
}
function uploadEvent(){
	if(!$("#file").val()){
		$.messager.alert("提示","请先选择要上传的图片！");
		return;
	}
	var ft = $("#file").val().substring($("#file").val().lastIndexOf(".")+1);
	if("jpg|jpeg|png".indexOf(ft)<0){
		$.messager.alert("提示","文件格式不支持，请选择图片进行上传！");
		return;
	}
/* 	var f = document.getElementById("file").files;
	var fs = f[0].size/1024;   //kb
	if(fs>10240){
		try{
			$.messager.confrim("提示","您选择的图片文件太大了！是否继续？",function(data){
				if(data){}else{throw "X";}
			});
		}catch(e){
			return;
		}
	}
	alert("os:"+f[0].size+"  kb:"+f[0].size/1024+"  mb:"+f[0].size/1024/1024); */
	$.messager.confirm("提醒","确认上传图片？",function(data){
		if(data){
			$.ajax({
				url:"${webroot}/bl002Sjdj/f_json/replaceImage.shtml",
				type:"POST",
				data:{
					fileName:$(".selected").attr("tvalue"),
					file:$("#preview").attr("src")
				},
				success:function(msg){
					isupload=false;
					$("#file").val("");
					msg = eval("("+msg+")");
					$.messager.alert("提示",msg.msg);
				},
				error:function(){
					$.messager.alert("提示","抱歉，上传出错了！");
				}
			});
		}
	});
}
function quitConfirm(){
	if(isupload){
		$.messager.confirm("提醒","此次上传操作还未完成，确认放弃？",function(data){
			if(data){
				isupload=false;
				$('#resetBtn').trigger("click");
				//还原图片路径
				$("#preview").attr("src",'/download/zyblImages/'+$(".selected").attr("tvalue")+'?tz=${now2}');
				return true;
			}else{
				return false;
			}
		});
	}else{
		return true;
	}
}
</script>