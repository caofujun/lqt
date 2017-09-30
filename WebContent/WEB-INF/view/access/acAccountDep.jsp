<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form method="post" id="acAccountDepForm">

		<div style="width:100%;height:350px;overflow: hidden;">
			<input type="hidden" name="userId" value="${acAccount.userId}" />
			<input type="hidden" id="id_delDepIds" name="delDepIds"/>
			<input type="hidden" id="id_havegrants" name="havegrants"/>
			<div id="tb" class="m_search h_set" >
				<nis:select id="deptTypeId" dictcode="dept_type" headerKey="" cssCls="easyui-combobox" headerValue="科室类型" />				
				<input type="text" id="searchString" class="auto-tip" data-tip="科室名称/编号"  style="width: 180px"/>				
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="acAccountDep.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
				</div>
			</div>
			<div id="usDepPanel"></div>
		</div>
		<div id="button_div" class="footer" border="false">			
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="acAccountDep.formSubmit();"  class="no_ico"><span>确&nbsp;认</span></a>
				</div>
			</div>
		</div>
</form>
<script type="text/javascript">
	var acAccountDep = {
		depNos : {},
		//删除授权的科室
		delDepIds : new Array(),
		//添加授权的科室
		havegrants : new Array(),
		//所有菜单编码
		getDep : function() {
			$('input[name=havegrants]').each(function(i, obj) {
				acAccountDep.depNos[$(obj).attr('data-no')] = $(obj).attr('data-pNo');
			});
		},
		//查询
		panel:'usDepPanel',
		query : function() {
			autoTip.clear();
	        $('#'+acAccountDep.panel).datagrid({
	            queryParams: {
	                'deptTypeId': $('#deptTypeId').combogrid('getValue'),
	                'searchString': $('#searchString').val()
	             /*    'showDepName' : $('#showDepName').val(),
	                'showRoleName' : $('#showRoleName').val() */
	            },
	            pageNumber: 1
	        });
	    },
	    //checkbox事件处理、
	    checkDep : function(obj, depId) {
	    	if (!$(obj).is(':checked')) {
	    		if ($.inArray(depId, acAccountDep.delDepIds) == -1) {
	    			acAccountDep.delDepIds.push(depId);
	    		}
	    		var index = $.inArray(depId, acAccountDep.havegrants);
	    		if (index >= 0) {
	    			acAccountDep.havegrants.splice(index,1);
	    		}
	    	} else {
	    		if ($.inArray(depId, acAccountDep.havegrants) == -1) {
	    			acAccountDep.havegrants.push(depId);
	    		}
	    		var index = $.inArray(depId, acAccountDep.delDepIds);
	    		if (index >= 0) {
	    			acAccountDep.delDepIds.splice(index,1);
	    		}
	    	}
	    },
	    formSubmit : function() {
	    	$('#id_delDepIds').val(acAccountDep.delDepIds.join(','));
	    	$('#id_havegrants').val(acAccountDep.havegrants.join(','));
	    	$('#acAccountDepForm').submit();
	    },
		//复选框点击事件
		sel : function(_this, name) {
			var _depNos = {};
			name = 'input[name='+name+']'
			$(name).each(function(i, obj) {
				_depNos[$(obj).attr('data-no')] = $(obj).attr('data-pno');
			});
			var _bool = ($(_this).attr('checked')?true:false);
			_this = $(_this);
			if(_bool) {
				//选中下面的节点，自动选中它的上级节点
				$(name).each(function(i, first) {
					if(_this.attr('data-pno') == $(first).attr('data-no')) {
						$(first).attr('checked', _bool);
						$(name).each(function(j, second) {
							if($(first).attr('data-pno') == $(second).attr('data-no')) {
								$(second).attr('checked', _bool);
								$(name).each(function(k, third) {
									if($(second).attr('data-pno') == $(third).attr('data-no')) {
										$(third).attr('checked', _bool);
									}
								});
							}
						});
					}
				});
			}
			//取消和选中
			$(name).each(function(i, first) {
				if(_this.attr('data-no') == $(first).attr('data-pno')) {
					$(first).attr('checked', _bool);
					$(name).each(function(j, second) {
						if($(first).attr('data-no') == $(second).attr('data-pno')) {
							$(second).attr('checked', _bool);
							$(name).each(function(k, third) {
								if($(second).attr('data-no') == $(third).attr('data-pno')) {
									$(third).attr('checked', _bool);
								}
							});
						}
					});
				}
			});
		}
};
$(document).ready(function() {
	$('#usDepPanel').datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: true,
        collapsible:true,
        url:'${webroot}/acAccount/f_json/findMyDep.shtml?ifcaseoffice=1&ownership=${param.ownership}&userId=${acAccount.userId}',
        remoteSort: false,
        singleSelect: false,
        checkOnSelect:false,
        selectOnCheck:false,
        border:false,
        columns:[
        	[
             {field:'deptTypeName',title:'科室类型',sortable:true,width:50,align:'center'},
             {field:'deptId',title:'科室编号',sortable:true,width:50},
             {field:'deptName',title:'科室名称',sortable:true,width:70},
          {field:'havegrant',title:'<div style="margin-top:8px;margin-left:12px;float:left;">授权</div><input id="cackb" type="checkbox" title="全选" style="width:15px;height:20px;margin:10px 2px;"  onclick="ca(this);" />',sortable:true,width:30,align:'center',formatter:function(value,rec){
            	var _cont = [];
           		_cont.push('<input type="checkbox" class="checkbox_list" name="ckb_dept" onclick="unccackb(this);acAccountDep.checkDep(this, \'',rec.deptId,'\');" ',(rec.isHavegrant==='1'?'checked':''),' data-pno="',rec.deptId,'" data-no="',rec.deptId,'" value="',rec.deptId,'"/>');
            	return _cont.join('');
            	} 
             }
            ]
        ],
        pagination:false,
        rownumbers:true,
        toolbar:'#tb'
    });
	
	Comm.form({
		id : 'acAccountDepForm',
		url : '${webroot}/acAccount/f_json/saveDep.shtml',
		subbtn : 'changeFormSubmitBtn',
		success : function(json) {
			if (json.result === 'success') {
				parent.$.messager.show({ title : '提示', msg : '操作成功！' });
				parent.Comm.dialogClose('${param.dialogId}');
			} else if(json.result === 'error') {
				parent.$.messager.show({ title : '提示', msg : '操作失败！' });
			} else {
				parent.$.messager.show({ title : '提示', msg : json.msg });
			}
		}
	});
});

function ca(ele){
	acAccountDep.delDepIds.splice(0,acAccountDep.delDepIds.length);//清空数组;
	acAccountDep.havegrants.splice(0,acAccountDep.havegrants.length);//清空数组;
	if($(ele).is(":checked")){ 
		$(":checkbox[name='ckb_dept']").each(function(){
			$(this).attr("checked","checked");
			//加入所有id
			acAccountDep.havegrants.push($(this).val());
		});
	}else{
		$(":checkbox[name='ckb_dept']").each(function(){
			$(this).removeAttr("checked");
			acAccountDep.delDepIds.push($(this).val());
		});
	}
}
function unccackb(ele){
	if($(ele).is(":checked")){}else{
		$("#cackb").removeAttr("checked");
	}
}
</script>
