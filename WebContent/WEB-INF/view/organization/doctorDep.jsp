<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form method="post" id="doctorDepForm">
	<div id="dataModelPanel" class="easyui-layout" style="width: 580px; height: 380px;">
		<div data-options="region:'center',border:false,footer:'#button_div'" style="overflow: hidden;">
			<input type="hidden" name="id" value="${param.id}" />
			<input type="hidden" id="id_delDepIds" name="delDepIds"/>
			<input type="hidden" id="id_havegrants" name="havegrants"/>
			<div id="tb" class="m_search h_set" >
				<nis:select id="deptTypeId" dictcode="dept_type" headerKey="" headerValue="科室类型" cssCls="easyui-combobox"/>				
				<input type="text" id="searchString" class="auto-tip" data-tip="科室名称/编号"  style="width: 180px"/>				
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="doctorDep.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
				</div>
			</div>
			<div id="docDepPanel"></div>
		</div>
		<div id="button_div" class="footer" border="false" >
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="doctorDep.formSubmit();"  class="no_ico"><span>确&nbsp;认</span></a>
				</div>			
			</div>			
		</div>
	</div>
</form>
<script type="text/javascript">
var doctorDep = {
	//查询
	panel:'docDepPanel',
	//删除授权的科室
	delDepIds : new Array(),
	//添加授权的科室
	havegrants : new Array(),
	query : function() {
		autoTip.clear();
        $('#'+doctorDep.panel).datagrid({
            queryParams: {
                'deptTypeId': $('#deptTypeId').combogrid('getValue'),
                'searchString': $('#searchString').val()
            },
            pageNumber: 1
        });
    },
    //checkbox事件处理、
    checkDep : function(obj, depId) {
    	if (!$(obj).is(':checked')) {
    		if ($.inArray(depId, doctorDep.delDepIds) == -1) {
    			doctorDep.delDepIds.push(depId);
    		}
    		var index = $.inArray(depId, doctorDep.havegrants);
    		if (index >= 0) {
    			doctorDep.havegrants.splice(index,1);
    		}
    	} else {
    		if ($.inArray(depId, doctorDep.havegrants) == -1) {
    			doctorDep.havegrants.push(depId);
    		}
    		var index = $.inArray(depId, doctorDep.delDepIds);
    		if (index >= 0) {
    			doctorDep.delDepIds.splice(index,1);
    		}
    	}
    },
    formSubmit : function() {
    	$('#id_delDepIds').val(doctorDep.delDepIds.join(','));
    	$('#id_havegrants').val(doctorDep.havegrants.join(','));
    	$('#doctorDepForm').submit();
    }
};
$(document).ready(function() {

	$('#'+doctorDep.panel).datagrid({
		fit: true,
        nowrap: true,
        autoRowHeight: true,
        striped: true,
        fitColumns: true,
        collapsible:true,
        url:'${webroot}/doctor/f_json/findMyDep.shtml?id=${param.id}',
        remoteSort: false,
        singleSelect: false,
        checkOnSelect:false,
        selectOnCheck:false,
        border:false,
        columns:[
        	[
             {field:'deptTypeName',title:'科室类型',sortable:true,width:80,align:'center'},
             {field:'deptId',title:'科室编号',sortable:true,width:150},
             {field:'deptName',title:'科室名称',sortable:true,width:380},
          	 {field:'havegrant',title:'<input id="cackb" type="checkbox" title="全选" style="width:15px;height:20px;" onclick="ca(this);" />授权',sortable:true,width:70,align:'center',formatter:function(value,rec){
             	var _cont = [];
             	_cont.push('<input type="checkbox" class="checkbox_list" name="ckb_dept" onclick="unccackb(this);doctorDep.checkDep(this, \'',rec.deptId,'\');" ',(rec.isHavegrant==='1'?'checked':''),' value="',rec.deptId,'"/>');
              	return _cont.join('');
             }}
            ]
        ],
        pagination:false,
        rownumbers:true,
        toolbar:'#tb',
        onLoadSuccess:function(){
        	//$("#detailcheckbox").unbind("click");
        	$("#detailcheckbox").click(function(){
        		//总记录数
                var totolrows = $(":checkbox[name='ckb_dept']").length;
                //选中的记录数
                var checkrows = $(":checkbox[name='ckb_dept']:checked").length;

                if (checkrows == totolrows) {
                    $("#detailcheckbox").attr("checked", 'checked');
                }else {
                    $("#detailcheckbox").removeAttr("checked");
                }
        	});
        	$(":checkbox[name='ckb_dept']").bind("click", function () {
                //总记录数
                var totolrows = $(":checkbox[name='ckb_dept']").length;
                //选中的记录数
                var checkrows = $(":checkbox[name='ckb_dept']:checked").length;

                if (checkrows == totolrows) {
                    $("#detailcheckbox").attr("checked", 'checked');
                }else {
                    $("#detailcheckbox").removeAttr("checked");
                }
        	});
        }
    });
	
	Comm.form({
		id : 'doctorDepForm',
		url : '${webroot}/doctor/f_json/saveDep.shtml',
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
	doctorDep.delDepIds.splice(0,doctorDep.delDepIds.length);//清空数组;
	doctorDep.havegrants.splice(0,doctorDep.havegrants.length);//清空数组;
	if($(ele).is(":checked")){ 
		//全选
		$(":checkbox[name='ckb_dept']").each(function(){
			$(this).attr("checked","checked");
			//加入所有id
			doctorDep.havegrants.push($(this).val());
		});
	}else{
		//全不选
		$(":checkbox[name='ckb_dept']").each(function(){
			$(this).removeAttr("checked");
			doctorDep.delDepIds.push($(this).val());
		});
	}
}
function unccackb(ele){
	if($(ele).is(":checked")){}else{
		$("#cackb").removeAttr("checked");
	}
}
</script>
