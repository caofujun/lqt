<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>选择题目</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
        <script type="text/javascript" src="${webroot}/resources/js/functions/wjdc.js${version}"></script>
	</head>
	<body>
		<div id="wjdcRepoQuestionPanel"></div>
		<div id="tb" class="m_search">			
			<input type="text" name="wjdcRepoQuestion.title"  id="wjdcRepoQuestionTitle" class="auto-tip" data-tip="题目标题"/>			
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="wjdcRepoQuestion.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">				
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="wjdcRepoQuestion.add()"><i class="icon iconfont">&#xe607;</i><span>确认添加</span></a>
				</div>				
			</div>
		</div>
		
		<script type="text/javascript">
			var wjdcRepoQuestion = {
				panel : 'wjdcRepoQuestionPanel',
				//查询
				query : function() {
			        $('#'+wjdcRepoQuestion.panel).datagrid({
			            queryParams: {
			                'title': $('#wjdcRepoQuestionTitle').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //添加选择项
			    add : function() {
			    	var curRow = $('#'+wjdcRepoQuestion.panel).datagrid('getSelections');
			    	if(curRow.length == 0) {
			    		$.messager.alert('提示', '请至少选择一项题目！');
			    	} else {
			    		parent.wqe.addQuestion(curRow);
			    		parent.Comm.dialogClose('${param.dialogId}');
			    	}
			    }
			};
			$(document).ready(function () {
				$('#'+wjdcRepoQuestion.panel).datagrid({
	                nowrap: true,
	                autoRowHeight: true,
	                striped: true,
	                fitColumns: false,
	                fit: true,
	                collapsible:true,
	                url:'${webroot}/qsRepoTopic/f_json/selectList.shtml',
	                remoteSort: false,
	                singleSelect: false,
	                frozenColumns:[[
	                    {field:'repoQId',checkbox:true},
	                ]],
	                columns:[
	                	[
							{field:'title',title:'题目列表',sortable:true,width:580,formatter:function(value, r){
								var _cont = ['<p style="margin:5px;"><b>',r.title,'</b></p>'];
								_cont.push('<div style="margin:5px;">',wjdc.createOpt(r),'</div>');
								return _cont.join('');
							}},
							{field:'status',title:'使用状态',sortable:true,width:80,formatter:function(value, r){
								if($.inArray(r.title, parent.wqe.selectQuestions)===-1) {
									return '未使用';
								} else {
									return '已使用';
								}
							}}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb'
	            });
			});
		</script>
	</body>
</html>