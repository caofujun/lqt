<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<%@ include file="/WEB-INF/view/core/editor.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<style type="text/css">
	.tabs-header {
		border-width: 0 0 0 1px;
	}
	.tabs-panels {
		border-right-width: 0;
	}
</style>
</head>
<body style="overflow: hidden;">
<form id="editFormnyMessageTheme" method="post">
<input type="hidden" id="testOrderNo" value="${testOrderNo}" />
<input type="hidden" id="msgType" value="${msgType}" />
<input type="hidden" id="pzId" value="${pzId}" />
<div class="session-box-left">
	<div class="session-list-box" id="nyMessageThemeDiv" style="height: 252px;">				
		<ul class="session-list" id="nyMessageThemeTable">
			<c:forEach var="messageDetail" items="${messageDetailList}" varStatus="status">
			<c:choose>
			<c:when test="${realname eq messageDetail.sendUserName}"><li class="session-right"></c:when>
			<c:otherwise>
			<li class="session-left">
			</c:otherwise>
			</c:choose>
				<p class="client-tip"><b>[${messageDetail.sendUserName}]</b><fmt:formatDate value="${messageDetail.sendTime}" pattern="yyyy-MM-dd HH:mm"/></p>
				<div class="cont">${messageDetail.content}。<span class="yes-mark">(未读数${messageDetail.wdCount}/总条数${messageDetail.qbCount})</span></div>
				<div class="clear"></div>					
			</li>
			</c:forEach>							
		</ul>
	</div>
	
	<div class="Reply">
		<div class="Reply-select">
			<!-- <input type="button" class="btn_save" value="干预模板" >
			<a href="#" class="ico_search" onclick="saveMsgTemplate()">保存正文为干预模板</a>
			<a href="#" class="ico_search" onclick="nyMessageTheme.showTemplate()">干预模板</a>
			<a href="#" class="ico_search" onclick="nyMessageTheme.showSop()" style="margin-left:10px">查看SOP字典</a> -->
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="saveMsgTemplate()"><i class="icon iconfont">&#xe63d;</i><span>保存正文为干预模板</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="nyMessageTheme.showTemplate()"><i class="icon iconfont">&#xe632;</i><span>干预模板</span></a>
			</div>
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="nyMessageTheme.showSop()" ><i class="icon iconfont">&#xe688;</i><span>查看SOP字典</span></a>
			</div>
		</div>
		<textarea name="content" id="content" cols="60" rows="5" style="width:99%;height: 60px;" >
			<c:choose>
				<c:when test="${!empty model}"> ${model.showFileData}</c:when>
				<c:when test="${!empty defContent}"> ${defContent}</c:when>
			</c:choose>		
		</textarea>
	</div>
	
	<div class="footer">
<!-- 		<input type="button" class="btn_save" title="保存正文为干预模板" value="另存为模板" onclick="saveMsgTemplate()">
		<input type="button" class="btn_save" id="changeFormSubmitBtn" onclick="sendMessage()" value="发送"> -->
		<div class="footer_btn">
			<div class="n_btn_blue">
					<a href="javascript:;" onclick="sendMessage()" class="no_ico"><span>发送</span></a>
			</div>
		</div>
	</div>
</div>
<div class="session-box-right" style="overflow: hidden;">
	<div class="easyui-tabs" style="width:250px;">
		<div title="科室" style="padding:5px 10px">
			<input id="dep" name="depId" style="width:130px" />
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="addDept()"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>
		<div title="医生"  style="padding:5px 10px;">
			<input id="doctor" name="doctorId" style="width:130px" />
			<div class="n_btn_grey">
				<a href="javascript:;" onclick="addDoctor()"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>
	</div>
	<div class="easyui-panel" data-options="border:false" style="width:250px;overflow: hidden;border-left-width: 1px;">
		<div id="nyMessageUserPanel" style="height:440px;"></div>
	</div>
</div>
</form>
<script>
var loadCount=0;
var nyMessageUser = {
		panel : 'nyMessageUserPanel',
		//查询
		query : function(idValue) {
			$('#'+nyMessageUser.panel).datagrid({
				onLoadSuccess:function(rowData){
					$('#'+nyMessageUser.panel).datagrid('selectRecord',idValue);
				}
			});
			
	    },
	    //删除
	    del:function(msgUserId) {
	    	$.messager.confirm('提示', '确认删除该对象?', function (r) {
	        	if (r) {
	            	$.ajax({
	                        url: '${webroot}/nyMessageUser/f_json/delete.shtml',
	                        type: 'post',
	                        data: { msgUserId: msgUserId },
	                        dataType: 'json',
	                        success : function(json) {
								if(json.result==='success') {
									nyMessageUser.query();									
	                                parent.$.messager.show({ title: '提示', msg: '删除成功！' });
						    	} else if(json.result === 'error') {
						    		parent.$.messager.show({ title: '提示', msg: '系统异常！' });
						    	} else {
						    		parent.$.messager.show({ title: '提示', msg: json.msg });
						    	}
							}
	            	});
	        	}
	    	});
	    }
	};
	
var editor=null;
var nyMessageTheme = {
	showSop:function(){
		Comm.dialogGlobal({
	    	url:"${webroot}/sop/f_view/sopDetailList.shtml",
	        title: "SOP字典",
	        width:750,
	        height:400,
	        type:"iframe",
	        parent:this
	    });
	},
	showTemplate:function(){
		Comm.dialogGlobal({
	    	url:"${webroot}/msgTemplate/f_view/index.shtml",
	        title: "干预模板",
	        width:750,
	        height:400,
	        type:"iframe",
	        parent:this
	    });
	},
	showContent:function(content){
		editor.html(content);
		$("#content").val(content);
	}	
};

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}


function addDept(){
	var deptId = $("input[name='depId']").val();
	if(deptId!=''){
		$.ajax({
	        url: '${webroot}/nyMessageUser/f_json/save.shtml',
	        type: 'post',
	        data: { deptId: deptId,themeId:'${nyMessageTheme.themeId}'},
	        dataType: 'json',
	        success : function(json) {
				if(json.result==='success') {
					nyMessageUser.query(json.data);			
	               parent.$.messager.show({ title: '提示', msg: '添加成功！' });
		    	} else if(json.result === 'error') {
		    		parent.$.messager.show({ title: '提示', msg: '系统异常！' });
		    	} else {
		    		parent.$.messager.show({ title: '提示', msg: json.msg });
		    	}
			}
		});	
	}
}

function addDoctor(){
	var userId = $("input[name='doctorId']").val();
	if(userId!=''){
		$.ajax({
	        url: '${webroot}/nyMessageUser/f_json/save.shtml',
	        type: 'post',
	        data: { userId: userId,themeId:'${nyMessageTheme.themeId}'},
	        dataType: 'json',
	        success : function(json) {
				if(json.result==='success') {
					nyMessageUser.query(json.data);			
	                parent.$.messager.show({ title: '提示', msg: '添加成功！' });
		    	} else if(json.result === 'error') {
		    		parent.$.messager.show({ title: '提示', msg: '系统异常！' });
		    	} else {
		    		parent.$.messager.show({ title: '提示', msg: json.msg });
		    	}
			}
		});
	}	
}

function saveMsgTemplate(){
	var content = $("#content").val();
	var title = content.substring(0,content.length>5?5:content.length);
	if(content!=''){
		Comm.dialogGlobal({
        	url:"${webroot}/msgTemplate/f_view/toedit.shtml?content="+encodeURIComponent(encodeURIComponent(content))+"&title="+encodeURIComponent(encodeURIComponent(title)),
            title: "保存内容为干预模板",
            width:750,
            type:"iframe",
            height:350
        });
	}else{
		parent.$.messager.show({title : '提示',msg : '请输入内容！'});
	}
}

//发送消息
function sendMessage(){
	var curRow = $('#'+nyMessageUser.panel).datagrid('getSelections');
	var content = $("#content").val();
	var msgType = $("#msgType").val();
	var ton = $("#testOrderNo").val();
	var pzId = $("#pzId").val();
	if (curRow.length>0&&content!='') {
		var msgUserIds="";
		for(var i=0;i<curRow.length;i++){
			msgUserIds=msgUserIds+curRow[i].msgUserId+",";
    	}
		$.ajax({
	        url: '${webroot}/nyMessageDetail/f_json/save.shtml',
	        type: 'post',
	        data: { content: content,themeId:'${nyMessageTheme.themeId}',msgUserIds:msgUserIds,msgType: msgType,pzId: pzId,caseId: ton},
	        dataType: 'json',
	        success : function(json) {
				if(json.result==='success') {
					ajaxMessage();
					editor.html('');
					$("#content").val('');
					parent.$.messager.show({ title: '提示', msg: '发送成功！' });
					var parentObject = parent.Comm.getObjectCache();
					if(typeof(parentObject)!="undefined"){ 
						parentObject.query();
					}
		    	} else if(json.result === 'error') {
		    		parent.$.messager.show({ title: '提示', msg: '系统异常！' });
		    	} else {
		    		parent.$.messager.show({ title: '提示', msg: json.msg });
		    	}
			}
		});
	}else{
		parent.$.messager.show({ title: '提示', msg: '请输入消息内容或者选择接收对象！' });
	}
}

function ajaxMessage(){
	$.ajax({
        url: '${webroot}/nyMessageTheme/f_json/getByThemeId.shtml',
        type: 'post',
        data: {themeId:'${nyMessageTheme.themeId}'},
        dataType: 'json',
        success : function(json) {
        	var html="";
        	for(var i=0;i<json.length;i++){
        		if('${realname}'==json[i].sendUserName){
        			html=html+'<li class="session-right">';
        		}else{
        			html=html+'<li class="session-left">';
        		}
            	html =html+ '<p class="client-tip"><b>['+json[i].sendUserName+']</b>'+json[i].sendTime+'</p><div class="cont">'+json[i].content+'。<span class="yes-mark">(未读数'+json[i].wdCount+'/总条数'+json[i].qbCount+')</span></div><div class="clear"></div></li>';
        	}
        	if(html!=''){
            	$('#nyMessageThemeTable').html(html);
        	}
        	guendong();
        }
	})
}

//自动滚动到最后
function guendong(){
	var nyMessageThemeDiv = document.getElementById('nyMessageThemeDiv');
	nyMessageThemeDiv.scrollTop = nyMessageThemeDiv.scrollHeight;
}

$(document).ready(function() {
		window.editor = KindEditor.create('#content', {
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
		ajaxMessage();
		guendong();	 
		setInterval(function() {
			ajaxMessage();
		}, ${refresh});
		$('#'+nyMessageUser.panel).datagrid({
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            fitColumns: true,
            border:false,
            url:'${webroot}/nyMessageUser/f_json/getByThemeId.shtml',
            queryParams: {
            	'themeId': '${nyMessageTheme.themeId}'
            },
            remoteSort: false,
            idField:"msgUserId",
            singleSelect: false,
            frozenColumns:[[  
      	        	         {field:'msgUserId',checkbox:true}
      	        ]],	  
            columns:[
            	[
                    {field:'name',title:'发送对象',sortable:true,width:20},
                    {field:'_operate',title:'操作',width:10,
						formatter:function(value,r){
								return ['<a href="javascript:nyMessageUser.del(\'',r.msgUserId,'\');">删除</a>'].join('');							
						}
					}
                ]
            ],    
           onLoadSuccess:function(rowData){
        	   /* if('${param.zyid}'!=''||'${param.mzid}'!=''){
            	   if(loadCount==0){
            		   $('#'+nyMessageUser.panel).datagrid('selectAll');
            	   }
            	   loadCount++; 
        	   }else{ */
        		   $('#'+nyMessageUser.panel).datagrid('selectRecord','${nyMessageUser.msgUserId}');
        	   //}

           }

        });
		Csm.combogrid.dep({
			//【必传】控件名称
			id: 'dep',
			hospId:'${unitId}',
			callback: '0'
		});
		//主管医生
		Csm.combogrid.doctor({
			id: 'doctor',
			callback: '0'
		});
	});
</script>
</body>
</html>