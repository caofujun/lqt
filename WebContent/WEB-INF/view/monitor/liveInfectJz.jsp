<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
	<form id="liveInfectEdit" method="post">
		<div data-options="region:'north',border:false" style="overflow: hidden;height: 400px;">
			<span style="margin-left:180px;color:#C0C0C0;">提示:双击数值修改</span>
			<div id="yzxxDetailPanel"></div>
		</div>
		<div data-options="region:'center',border:false" style="border-top-width: 1px;">
			<div id="button_div">				
					<div class="footer_btn" style="text-align:center;margin-top:8px;">
						<div class="n_btn_blue" >
							<a href="javascript:;"id="changeFormSubmitBtn" onclick = "liveShezhi.save();" class="no_ico"><span>保存</span></a>
						</div>
						<div class="n_btn_grey" >
							<a href="javascript:;" onclick="parent.Comm.dialogClose('${param.dialogId}')"  class="no_ico"><span>关闭</span></a>
						</div>
					</div>	
			</div>
		</div>
		</form>
		<script type="text/javascript">
		
		var liveShezhi = {
			save : function () {
				var rows = $('#yzxxDetailPanel').datagrid('getRows');
				for (var i = 0; i < rows.length; i++) {  
								if(!$("#baseInfectEdit"+i).val()){
									parent.$.messager.show({title : '提示',msg : '基准利率不能为空！'});
									return false;
								}else{
									if(isNaN($("#baseInfectEdit"+i).val())){
										parent.$.messager.show({title : '提示',msg : '基准利率输入不合法！'});
										  return false;
									}
								}
				}  
				$('#liveInfectEdit').submit();
			}
		};
		function updateActions(index){
			$('#yzxxDetailPanel').datagrid('updateRow',{
				index: index,
				row:{}
			});
		};
		$(document).ready(function () {
			
			window.setTimeout(function() {
				Comm.form({
					id : 'liveInfectEdit',
					url : '${webroot}/gr002YsgrMx/f_json/saveLiveInfectJz.shtml',
					subbtn : 'changeFormSubmitBtn',
					success : function(json) {
						if (json != undefined && json.result === 'success') {
							var parentObject = parent.Comm.getObjectCache();
							parentObject.query();
							parent.$.messager.show({ title: '提示', msg: '操作成功！' });
			        		parent.Comm.dialogClose('${param.dialogId}');
						} else if (json != undefined && json.result === 'error') {
							parent.$.messager.show({title : '提示',msg : '操作失败！'});
						} else {
							parent.$.messager.show({title : '提示',msg : json});
							//刷新父页面列表数据
						}

					}
					
				});
			}, 100);
			$('#yzxxDetailPanel').datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: false,
                striped: true,
                fitColumns: true,
                collapsible:true,
                border:false,
                url:'${webroot}/gr002YsgrMx/f_json/findLiveInfect.shtml',	                
                remoteSort: false,
                singleSelect: true,
               
                queryParams: {
                	'startDate': '${startDate}',
                	'endDate': '${startDate}',
                },
                columns:[
                	[              
	                    {field:'DEPTNAME',title:'科室',sortable:true,align:'left',width:30,
	                    	formatter:function(value,r,index){
	                    		return ['<input type="hidden" name="deptList[' + index + '].deptId" value="' + r.DEPTID + '"/>' + 
	    						        '<input type="hidden" name="deptList[' + index + '].deptName" value="' + r.DEPTNAME + '"/>'+
	    						        r.DEPTNAME].join('');
	                    	}
	                    },
	                    {field:'BASEINFECT',title:'基准感染率',sortable:true,  align:'center',width:17,
	                    	formatter:function(value,r,index){
								return ['<input type="text" id="baseInfectEdit'+index+'" name="deptList[' + index + '].baseInfect" style="display:none" value="' + r.BASEINFECT + '"/>' + 
 						        r.BASEINFECT+"%"].join('');
	                    	}
	                    	
	                    }
	              	]],
    	        rownumbers:true,
                toolbar:'#tb',
                onDblClickCell: function (index, field, value) {
                	document.getElementById("baseInfectEdit"+index).style.display='block';	
                }, 
                onAfterEdit : function(index, row, changes){
        		    var updated = $(this).datagrid('getChanges', 'updated');  
        		    if (updated.length < 1) {  
        		        editRow = undefined;  
        		        $(this).datagrid('unselectAll');  
        		        return;  
        		    } else {  
        		        // 传值  
        		        submitForm(index, row, changes);  
        		    }  
                }
            });
			
		});
		
		</script>
	</body>
</html>
