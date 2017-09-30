<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>干预会话</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="nyMessageThemePanel"></div>
		<div id="tb" class="m_search">
			<span class="pro_text">发送时间：</span>
			<input type="hidden" id="uid" value="${uid}"/>
			<input type="text"  id="startDate" value="${startDate}" style="width:80px"  class="Wdate text" onclick="WdatePicker()"  />~
			<input type="text"  id="endDate" value="${endDate}" style="width:80px" class="Wdate text" onclick="WdatePicker()" />
		 	<input type="text" class="auto-tip" style="width:180px" data-tip="患者/最后发送人/会话内容" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="nyMessageTheme.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>
		</div>
		<script type="text/javascript">
			var nyMessageTheme = {
				panel : 'nyMessageThemePanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+nyMessageTheme.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val(),
			                'startDate': $('#startDate').val(),
			                'endDate': $('#endDate').val(),
			                'uid' : $('#uid').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit:function(zyid,mzid,themeId, title,lastSendUserId) {
			    	if(zyid===undefined) zyid = '';
			        Comm.dialogGlobal({
			        	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?themeId="+themeId+"&userId="+lastSendUserId,
			            title: title,
			            width:870,
			            height:565,
			            type:"iframe",
			            parent:this
			        });
			    },
			 
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该字典?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/nyMessageTheme/f_json/delete.shtml',
			                        type: 'post',
			                        data: { pdSubid: id },
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											nyMessageTheme.query();
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
			    }
			};
			$(document).ready(function () { 
				$('#'+nyMessageTheme.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                border:false,
	                url:'${webroot}/nyMessageTheme/f_json/pageQuery.shtml',
	                queryParams: {
		                'startDate': $('#startDate').val(),
		                'endDate': $('#endDate').val(),
		                'uid' : $('#uid').val()
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
							{field:'patientName',title:'病人姓名',sortable:true,width:15},
		                    {field:'patientId',title:'${patientNoTitle}',sortable:true,width:20},
		                    {field:'deptName',title:'所在科',sortable:true,width:20},		           
		                    {field:'lastContent',title:'会话概要',sortable:true,width:80},
		                    {field:'_operate',title:'操作',width:10,
								formatter:function(value,r){
										return ['<a href="javascript:nyMessageTheme.edit(\'',r.zyid,'\',\'',r.mzid,'\',\'',r.themeId,'\',\'会话\',\'',r.lastSendUserId,'\');" class="btn_icon" title="会话"><i class="icon iconfont">&#xe625;</i></a>'].join('');							
								}
							},
							{field:'puid',title:'风险因素',sortable:true,width:20},
							{field:'lastSendUser',title:'最后发送消息人',sortable:true,width:15},
							{field:'lastSendTime',title:'最后发送时间',sortable:true,width:25},
		                ]
	                ],
	                pagination:true,
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>
