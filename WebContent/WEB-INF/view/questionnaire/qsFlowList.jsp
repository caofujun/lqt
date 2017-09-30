<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>调查流程问卷</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
        <script type="text/javascript" src="${webroot}/resources/js/functions/wjdc.js${version}"></script>
	</head>
	<body>
	    <input type="hidden" id="id_username" value="${username}"/>
		<div id="infoPanel"></div>
		<div id="tb" class="m_search">
			<table>
			<tr>
			<td><input type="text" name="wjdcQuestionnaire.title" id="wjdcQuestionnaireTitle" class="auto-tip text" data-tip="请输入问卷流程标题"/>
		    </td><td><input type="button" class="btn_search" onclick="wjdcQuestionnaire.query()" value="查询"/>
			<input type="button" class="btn_add" value="新增" onclick="wjdcQuestionnaire.edit(undefined,'新增')"/>
			<!-- <input type="button" class="btn" value="预&nbsp;览" onclick="wjdcQuestionnaire.preview()"/> -->
 		    </td>
		    </tr>
		    </table>
		</div>
		
		<script type="text/javascript">
		
		var isHttps='';
		$(document).ready(function(){
			var path =getRootPath_web();
			if(path.indexOf("https")>-1){
				isHttps="yes";
			}else{
				isHttps="no";
			}
		});
		
			var wjdcQuestionnaire = {
				panel : 'infoPanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+wjdcQuestionnaire.panel).datagrid({
			            queryParams: {
			                'fName': $('#wjdcQuestionnaireTitle').val()
			            },
			            pageNumber: 1
			        });
			    },
			    //编辑
			    edit : function(id,title) {
			    	if(id === undefined) {
			    	    Comm.dialog({
				        	url:'${webroot}/qsFlow/f_view/toadd.shtml?fid=' + id,
				    		type: 'iframe',
				            title: title,
				    		width: 600,
				    		height: 420
				        });
			    	} else {
			    		 Comm.dialog({
					        	url:'${webroot}/qsFlow/f_view/toedit.shtml?fid=' + id,
					    		type: 'iframe',
					            title: "修改",
					    		width: 600,
					    		height: 420
					        });
			    	}
			    },
			    //预览
			    preview : function(quesId) {
			   		var curRow = $('#'+wjdcQuestionnaire.panel).datagrid('getSelected');
				    if (curRow || quesId!=undefined) {
				    	quesId = curRow.qid;
				        	Comm.dialog({
						    	content: ['<div id="wjdcPreViewPanel" style="padding: 10px;">加载中...</div>'].join(''),
						    	title:'录入调查',
						    	width: 800,
						    	height: 500
						    });
				        	setTimeout(function() {
				        		wjdc.loadQues('wjdcPreViewPanel', quesId, {
									resultFn : function(i, obj) {
										var _cont = ['<div class="div_question" id="questionSel',obj.qId,'"><div class="div_title_question_all"><div class="div_title_question">',(i + 1),'. ',obj.title,'</div></div>'];
										_cont.push('<div class="div_table_radio_question">',wjdc.createOpt(obj),'</div></div>');
										return _cont.join('');
									}
								});
				        	}, 500);
				    }
					else $.messager.alert('提示', '请选择需要预览的记录.');
			    },
			    //删除
			    del : function(id) {
			            $.messager.confirm('提示', '是否确认删除该行记录?', function (r) {
			                if (r) {
			                	$.ajax({
			                        url: '${webroot}/qsFlow/f_json/delete.shtml',
			                        type: 'post',
			                        data: { 'fid': id },
			                        dataType: 'json',
			                        success : function(json) {
			                        	if (json.result === 'success') {
			                        		wjdcQuestionnaire.query();
			                                $.messager.show({ title: '提示', msg: '删除成功！' });
			        					} else if(json.result === 'error') {
			        						$.messager.show({ title : '提示', msg : '操作失败！' });
			        					} else {
			        						$.messager.show({ title : '提示', msg : json.msg });
			        					}
									}
			                    });
			                }
			            });
			    },
			    //关闭或发布
			    issue : function(quesId, tmCount, state) {
			    	if(state == 0) {
				    	if(tmCount===0) {
				    		$.messager.alert('提示', '没有题目的问卷不能发布，快去添加题目吧！');
				    		return;
				    	}
			    		state = 1;
			    	} else state = 0;
			    	$.ajax({
                        url: '${webroot}/qsFlow/f_json/issue.shtml',
                        type: 'post',
                        data: { 'id': quesId, 'status': state },
                        dataType: 'json',
                        success : function(json) {
                        	if (json.result === 'success') {
                        		wjdcQuestionnaire.query();
                                $.messager.show({ title: '提示', msg: '操作成功！' });
        					} else if(json.result === 'error') {
        						$.messager.show({ title : '提示', msg : '操作失败！' });
        					} else {
        						$.messager.show({ title : '提示', msg : json.msg });
        					}
						}
                    });
			    },
			    //公开或取消公开
			    publish : function(quesId, publish) {
			    	if(publish==0) publish=1;
			    	else publish=0;
			    	$.ajax({
                        url: '${webroot}/qsFlow/f_json/publish.shtml',
                        type: 'post',
                        data: { 'fid': quesId, 'publish': publish },
                        dataType: 'json',
                        success : function(json) {
                        	if (json.result === 'success') {
                        		wjdcQuestionnaire.query();
                                $.messager.show({ title: '提示', msg: '操作成功！' });
        					} else if(json.result === 'error') {
        						$.messager.show({ title : '提示', msg : '操作失败！' });
        					} else {
        						$.messager.show({ title : '提示', msg : json.msg });
        					}
						}
                    });
			    },
			    //分享
			    share : function(quesId, publish){
			    	if(publish==0){
			    		$.messager.show({ title : '提示', msg : '请先公开问卷！' });
			    		return;
			    	}
			    	parent.menuInfo.clickMenu('流程问卷','/qsFlow/f_view/qsFlowShare.shtml?fId='+quesId+"&isHttps="+isHttps,true);
			    }
			};
			$(document).ready(function () {
				$('#'+wjdcQuestionnaire.panel).datagrid({
	                nowrap: true,
	                autoRowHeight: true,
	                striped: true,
	                fit: true,
	                collapsible:true,
	                url:'${webroot}/qsFlow/f_json/pageQuery.shtml',
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
							{field:'fName',title:'流程问卷标题',sortable:true,width:200,formatter:function(value, r){
								return '<span>'+r.fName+'</span>';
							}},
		                    {field:'endTime',title:'有效时长',sortable:true,width:80,formatter:function(value, r) {
		                    	return (r.endTime!=null&&r.endTime!=''? r.endTime :'长期有效');
		                    }},
		                    {field:'publish',title:'是否发布',sortable:true,width:65,formatter:function(value, r) {
		                    	return (r.publish=='1'? '已发布':'未发布');
		                    }},
		                   // {field:'catName',title:'分类',sortable:true,width:120},
		                    //{field:'depNoName',title:'所属科室',sortable:true,width:120},
		                    {field:'createUser',title:'创建人',sortable:true,width:100},
		                   // {field:'answerCount',title:'答卷人数',sortable:true,width:65},
		                    {field:'_operate',title:'操作',width:130,
								formatter:function(value,r){
									var _cont = [''];
									var usernme = $('#id_username').val();
									if (usernme === r.createUser) {
									//if('${user.dataScope}'==6) {
										_cont.push('<a  onclick="wjdcQuestionnaire.edit(',r.fid,')" class="ico_editor" title="修改"></a>');										
										var _etime = '';
										if(r.endTime!=null && r.endTime!='')
 											_etime = Comm.date.formatDate(r.endTime+' 23:59:59','yyyy-MM-dd HH:mm:ss');
 										if(_etime === '' || Comm.date.compareDate(_etime, new Date()) > 0) {
					                		_cont.push('<a href="javascript:wjdcQuestionnaire.publish(',r.fid,',',r.publish,');" ',(r.publish=='0'?'class="ico_pubopen" title="发布"':'class="ico_pubclose" title="取消发布"'),'></a> ');
 										}
 										 if(r.status == 1&& r.publish==1){
 											_cont.push('<a href="javascript:wjdcQuestionnaire.share(',r.fid,',',r.status,');" class="ico_tag" title="分享"></a>');
 										} 
/* 					                	_cont.push('<a href="javascript:wjdcQuestionnaire.publish(',r.qid,',',r.publish,');"',(r.publish==0?'class="ico_public" title="公开"':'class="ico_publicclose" title="取消公开"'),'></a>'); 
 */					                	if(r.status == 0) {
											_cont.push('<a href="javascript:wjdcQuestionnaire.del(',r.fid,');" class="ico_del" title="删除"></a>');
										}
					                //}
									}
					                return _cont.join('');
								}
							}
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
