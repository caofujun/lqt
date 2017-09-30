<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
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
<form id="editFormSysmoduleExplain" method="post">
			<table class="table mb60">
				<tbody>
					<tr>
						<td class="t_title" style="width: 80px;">
							模块名称：
						</td>
						<td class="t_cont">
						<!-- 填充菜单树 -->
						<input type="text" id="mid"  name="mid" style="width:200px" class="easyui-combotree" required="true" value='${moduleExplain.mid}'/>
		            	<input type="hidden" id="mkName" name="mkName"  value='${moduleExplain.mkName}' /> 
						</td>
					</tr>			
					<tr>
						<td class="t_title">
							模块说明：
						</td>
						<td class="t_cont">
							<textarea name="mkExplain" id="content" cols="100" rows="10" style="width:95%;height: 350px;" >
							 ${moduleExplain.mkExplain} 
							</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSysmoduleExplainSubmitBtn" onclick="$('#editFormSysmoduleExplain').submit()" class="no_ico"><span>发&nbsp;布</span></a>	
			</div>			
		</div>				
	</div>
</form>
	<script>
	var editor=null;
	/* var reportTypeSelect = '${moduleExplain.mkExplain}'; */
	
	$(document).ready(function () {
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
		window.setTimeout(function(){
			Comm.form({
				id: 'editFormSysmoduleExplain',
				url: '${webroot}/moduleExplain/f_json/save.shtml',
				subbtn: 'changeFormSysmoduleExplainSubmitBtn',
				success : function(json) {
					if(json.result=='success') {
						var parentObject = parent.Comm.getObjectCache();
						parentObject.query();
						parent.$.messager.show({ title: '提示', msg: '操作成功！' });
						parent.Comm.dialogClose('${param.dialogId}');
			    	}
			    	else $.messager.show({ title: '提示', msg: '操作失败！' });
				}
			});
		},100);
		
	<!--填充菜单树-->
	var mid = '${moduleExplain.mid}';
	$("#mid").combotree({
	//加载一个combotree,并展开所有节点，因为展开后才能显示选中的值  
    url:'${webroot}/acMenu/f_json/findTreeForEasyUI.shtml?mid='+mid,    
    editable:false, 
    // 选中节点值
    value:'${moduleExplain.mid}',
    onLoadSuccess:function(node,data){
    	
    } ,
    onSelect:function(node) {
    	if(node.attributes.destUrl=='disselect'){
    		$.messager.show({ title: '提示', msg: '标题不能被选择！'});
            //清除选中  
            $('#mid').combotree('clear');  
    		return;
    	}else{
      	  // 赋值 	  
      	  $("#mid").val(node.id);
      	  $("#mkName").val(node.text);
    	}
    }
 });  
	
//在设置一下combotree的值即可，value为你想选中的那个值，一般从后台取出来在设置的

});
	
	
/* 	function  submit(){
		var mid = $("#mid").combotree("getValue");// 取得选中的编码，单个的。
		var mkName= $("#mid").combotree("getText");// 取所有选中的文本，是一个String 。
		alert(mid);
		alert(mkName);
	//	$('#editFormSysmoduleExplain').submit();
	} */
	
	
  </script>
</body>
</html>