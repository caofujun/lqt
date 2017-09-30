<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报表列表</title>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
</head>
<body>
	<input type="hidden" value="${roleId}"  name="roleId" id="roleId">
	<ul id="reportTree" style="margin-bottom: 60px;margin-left: 10px;margin-top: 10px;"></ul>
	
	<div id="button_div" class="footer" border="false">			
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="" onclick="save();"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
	<script type="text/javascript">
			$("#reportTree").tree({
				url:'${webroot}/acMenu/f_view/toReportAuthTree.shtml?roleId=${roleId}',
				checkbox:true,
				animate:true,
				loadFilter : function(data) {   
					var c = new Array();
					c.push(data);
					return c;
				},
				onLoadSuccess : function(node, data){
				}
			});
			/* $("#reportTree").treegrid({
				url:'${webroot}/acMenu/f_view/toReportAuthGridTree.shtml?roleId=${roleId}',
			    idField:'id',
			    width:600,
				height:400,
				fit:true, 
		        fitColumns:true,  
		        singleSelect : false,
		        checkOnSelect:false,
		        selectOnCheck:false,
		        checkbox: true,
			    treeField:'text',
			    columns:[[
			        {title:'id',field:'编号',hidden:true},
			        {field:'text',title:'功能名称',width:200}
			    ]]
			}); */
			function save(){
				var cked = $("#reportTree").tree('getChecked', 'checked');
				var checkList = new Array();
				for(var i=0;i<cked.length;i++){
					//如果有子节点就排除
					if(!cked[i].children){
						var sl = {};
						sl["accessId"] = cked[i].id;
						sl["accessType"] = "1";
						sl["roleId"] = "${roleId}";
						sl["havegrant"] = "0";
						sl["operation"] = "1";
						checkList.push(sl);
					}
				}
				
				$.ajax({
					url:"${webroot}/acMenu/f_json/saveReportAuth.shtml",
					type:"POST",
					data:{
						"aps":JSON.stringify(checkList),
						"roleId":$("#roleId").val()
					},
					success:function(data){
						data = eval("("+data+")");
						if(data.result=="success"){
							parent.$.messager.show({ title: '提示', msg: '操作成功！' });
							parent.Comm.dialogClose('${param.dialogId}');
						}else{
							parent.$.messager.show({ title: '提示', msg: data.extraValue });
						}
					},error:function(){
						$.messager.show({ title: '提示', msg: '抱歉，出错了！' });
					}
				})
			}
	</script>
	
</body>
</html>