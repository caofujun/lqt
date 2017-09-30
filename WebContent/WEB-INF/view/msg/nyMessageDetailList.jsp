<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>干预会话</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="nyMessageDetailPanel"></div>
		<div id="tb" class="m_search">		 	
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="nyMessageDetail.edit('会话');"><i class="icon iconfont">&#xe632;</i><span>干预</span></a>
			</div>
		</div>
		<script type="text/javascript">
			var nyMessageDetail = {
				panel : 'nyMessageDetailPanel',
				//编辑
			    edit:function( title) {
			        Comm.dialogGlobal({
			        	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid=${param.zyid}&mzid=${param.mzid}&msgType=1",
			            title: title,
			            width:870,
			            height:565,
			            type:"iframe",
			            parent:this
			        });
			    }
			};
			$(document).ready(function () { 
				$('#'+nyMessageDetail.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                url:'${webroot}/nyMessageDetail/f_json/list.shtml?zyid=${param.zyid}',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'sendUserName',title:'发送人',sortable:true,width:20},
		                    {field:'sendTime',title:'发送时间',sortable:true,width:15},
		                    {field:'content',title:'消息内容',sortable:true,width:80},	           
		                    {field:'_operate',title:'操作',width:10,
								formatter:function(value,r){
										return ['<a href="javascript:nyMessageDetail.edit(\'会话\');">会话</a>'].join('');							
								}
							}
		                ]
	                ],
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>
