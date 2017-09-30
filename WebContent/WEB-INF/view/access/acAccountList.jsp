<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="acUserPanel"></div>
	<div id="tb" class="m_search" >
		<input type="text" id="searchString" class="auto-tip" data-tip="帐号/姓名/角色"  style="width: 180px"/>
		<!-- <input type="text" id="showDepName" class="auto-tip" data-tip="所属科室" /> -->
		<!-- <input type="text" id="showRoleName" class="auto-tip" data-tip="角色" /> -->
		<div class="n_btn_blue">
			<a href="javascript:;"  onclick="acAccount.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>		
		<div class="btn_r">
			<div class="n_btn_grey">
				<a href="javascript:;"  onclick="acAccount.edit(null,'新增用户')"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
			</div>
		</div>
	</div>

<script type="text/javascript">
$(document).ready(function () { 
	$('#acUserPanel').datagrid({
              fit: true,
              nowrap: true,
              autoRowHeight: false,
              striped: true,
              fitColumns: true,
              collapsible:true,
              url:'${webroot}/acAccount/f_json/pageQuery.shtml?ownership=${param.ownership}',   
              remoteSort: false,
              singleSelect: true,
              border:false,
              columns:[
              	[
                   {field:'username',title:'帐号',sortable:true,width:60},  
                   {field:'realname',title:'姓名',sortable:true,width:60},
                   {field:'mobilenum',title:'手机号码',sortable:true,width:90},
                   {field:'email',title:'邮箱',sortable:true,width:130},
                   {field:'showDepName',title:'所属科室',sortable:true,width:110},
                   {field:'showRoleName',title:'角色',sortable:true,width:80},
                   {field:'docNo',title:'医生工号',sortable:true,width:70},
                   {field:'stringDate',title:'有效日期',sortable:true,width:80},
                   {field:'_operate',title:'操作',width:100,
					formatter : function(value,r){
						return ['<a href="javascript:acAccount.edit(\'',r.userId,'\',\'修改用户信息\');" class="ico_editor" title="修改"></a>',
								/* '<a href="javascript:acAccount.grantAuth(\'',r.userId,'\');" id="grantAuth" class="ico_setup" title="用户菜单资源授权"></a>', */
								'<a href="javascript:acAccount.grantDep(\'',r.userId,'\');" id="grantDep" class="ico_view" title="用户业务科室授权"></a>',
								'<a href="javascript:acAccount.del(\'',r.userId,'\');" class="ico_del" title="删除"></a>',
								'<a href="javascript:acAccount.resetPwd(\'',r.userId,'\');" class="ico_key" title="重置密码"></a>'].join('');
					}
				}
               ]
              ],
              pagination:true,
              rownumbers:true,
              toolbar:'#tb'
          });
});

var acAccount = {
		panel:'acUserPanel',
		//查询
		query : function() {
			autoTip.clear();
	        $('#'+acAccount.panel).datagrid({
	            queryParams: {
	                'searchString': $('#searchString').val(),
	             /*    'showDepName' : $('#showDepName').val(),
	                'showRoleName' : $('#showRoleName').val() */
	            },
	            pageNumber: 1
	        });
	    },
	    //编辑
	    edit:function(id, title) {
	    	if(id===undefined || id == null ) id = '';
	    	Comm.dialogGlobal({
	        	url:"${webroot}/acAccount/f_view/toedit.shtml?ownership=${param.ownership}&id=" + id,
	        	title: title,
	            width:650,
	            height:400
	        });
	    },
	    //删除
	    del:function(id) {
	    	$.messager.confirm('提示', '确认删除该角色?', function (r) {
	        	if (r) {
	            	$.ajax({
                        url: '${webroot}/acAccount/f_json/delete.shtml',
                        type: 'post',
                        data: { id: id },
                        dataType: 'json',
                        success : function(json) {
							if(json.result==='success') {
					    		acAccount.query();
                                $.messager.show({ title: '提示', msg: '删除成功！' });
					    	} else if(json.result === 'error') {
					    		$.messager.show({ title: '提示', msg: '系统异常！' });
					    	} else {
					    		$.messager.show({ title: '提示', msg: json.msg });
					    	}
						}
	            	});
	        	}
	    	});
	    },
	    //分配菜单权限
	    grantAuth : function(id) {
	    	Comm.dialogGlobal({
	        	url:"${webroot}/acAccount/f_view/toauth.shtml?ownership=${param.ownership}&id=" + id,
	            title: '用户菜单资料授权',
	            width:600,
	            height: 450,
	            type: 'iframe'
	        });
	    },
	    //分配科室权限
	    grantDep : function(id) {
	    	Comm.dialogGlobal({
	        	url:"${webroot}/acAccount/f_view/todep.shtml?ownership=${param.ownership}&id=" + id,
	            title: '用户科室权限设置',
	            width:600,
	            height: 450,
	            type: 'iframe'
	        });
	    },
	    //重置密码
	    resetPwd : function(id) {
	    	$.messager.confirm('提示', '确认给该用户重置密码为：${defaultPwd} ?', function (r) {
	    		if (r) {
	    			$.ajax({
                        url: '${webroot}/acAccount/f_json/reSetPwd.shtml',
                        type: 'post',
                        data: { id: id },
                        dataType: 'json',
                        success : function(json) {
							if(json.result==='success') {
					    		acAccount.query();
                                $.messager.show({ title: '提示', msg: '重置成功！' });
					    	} else if(json.result === 'error') {
					    		$.messager.show({ title: '提示', msg: '系统异常！' });
					    	} else {
					    		$.messager.show({ title: '提示', msg: json.msg });
					    	}
						}
            		});
	    		}
	    	});
	    }
};
</script>	
</body>
</html>