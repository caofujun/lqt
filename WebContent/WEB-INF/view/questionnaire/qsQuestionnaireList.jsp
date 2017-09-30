<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>	
	   	<title>调查问卷</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
		
        <script type="text/javascript" src="${webroot}/resources/js/functions/wjdc.js${version}"></script>
        
        <script type="text/javascript">
			var previousRow;			
			function OnMouseOut(obj) {
				obj.style.border = '2px solid #efefef';
				if (obj.titleA)
					obj.titleA.className = obj.titleA.prevClass;
				previousRow = null;
			}
			function OnMouseOver(obj) {
				if (obj == previousRow) return;
				c = obj.style.border;
				obj.style.border = '2px solid #38a8fd';
				if (!obj.titleA) {
					obj.titleA = obj.getElementsByTagName("a")[0];
					obj.titleA.prevClass = obj.titleA.className;
				}
				obj.titleA.className = "titlehover";
				previousRow = obj;
			}
	   </script>
	</head>
	
	<body>
		<input type="hidden" id="id_username" value="${username}"/>
		<!-- <div class="pj_title" style="margin:10px 10px 0px 10px;">		
			<select id="catId" name="catId" class="easyui-validatebox" style="width: 150px;">
			</select>
			<input type="text" name="wjdcQuestionnaire.title" id="wjdcQuestionnaireTitle" class="auto-tip" style="width:200px;" data-tip="请输入问卷标题"/>
			<input type="button" id="" name="" class="btn_search"  onclick="wjdcQuestionnaire.query()" value="查询">
			<input type="button" id="" name="" class="btn_add" onclick="wjdcQuestionnaire.edit()" value="新增1">	
		</div> -->
		<div class="m_search">
			<select id="catId" name="catId" class="easyui-validatebox " style="width: 150px;"></select>		
			<input type="text" name="wjdcQuestionnaire.title" id="wjdcQuestionnaireTitle" class="auto-tip" style="width:200px;" data-tip="请输入评估表标题"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="wjdcQuestionnaire.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="wjdcQuestionnaire.edit()"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>				
			</div>
		</div>
		<div id="infoPanel" ></div>
		<div id="infoPage" ></div>

					
<script type="text/javascript">
	var infoPage = undefined;
	var wjdcQuestionnaire = {
			panel : 'infoPanel',
			//查询
			query : function() {
				var title = $('#wjdcQuestionnaireTitle').val();
				if(title == "请输入评估表标题"){
					title = "";
				}
				wjdcQuestionnaire.loadInfo(1,title);
		    },
			 //编辑
		    edit : function(id) {
		    	if(id === undefined) {
		    		location = '${webroot}/qsQuestionnaire/f_view/toadd.shtml';
		    	} else {
		       		location = '${webroot}/qsQuestionnaire/f_view/toedit.shtml?id=' + id;
		    	}
		    },
			 //预览
		    preview : function(quesId) {
			    if (quesId ==null || quesId=='' || quesId!=undefined) {
		        	Comm.dialog({
				    	content: ['<div id="wjdcPreViewPanel" style="padding: 10px;">加载中...</div>'].join(''),
				    	title:'录入调查',
				    	width: 800,
				    	height: 500
				    });
		        	setTimeout(function() {
		        		wjdc.loadQues('wjdcPreViewPanel', quesId, {
							resultFn : function(i, obj) {
								var _cont = ['<div id="questionSel',obj.qId,'"><p><b>',(i + 1),'. ',obj.title,'</b></p>'];
								_cont.push('<div style="padding-bottom: 15px;border-bottom: 1px gray dotted;">',wjdc.createOpt(obj),'</div></div>');
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
		                        url: '${webroot}/qsQuestionnaire/f_json/delete.shtml',
		                        type: 'post',
		                        data: { 'id': id },
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
                    url: '${webroot}/qsQuestionnaire/f_json/issue.shtml',
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
		 	//分享
		    share : function(quesId, publish,isHttps){
		    	if(publish==0){
		    		$.messager.show({ title : '提示', msg : '请先公开问卷！' });
		    		return;
		    	}
			    parent.menuInfo.clickMenu('分享问卷','/qsQuestionnaire/f_view/questShare.shtml?qId='+quesId+"&isHttps=no",true);
		    },
		  	//问卷调查
		    openSurvey : function(type, qid, title) {
				Comm.dialog({
		        	url:'${webroot}/qsSurveyRecord/f_view/toedit.shtml?type='+type+'&qid='+qid,
		    		type: 'iframe',
		            title: title,
		    		width: 750,
		    		height: 450
		        });
			},
			//查看统计
			lookStat : function(qid) {
				parent.parent.menuInfo.clickMenu('查看问卷统计','/qsSurveyResult/f_view/qsReport.shtml?qid='+qid,true);
			},
			//获取用户信息
			loadInfo : function(page,title) {
				if(!infoPage) {
					infoPage = new Page('infoPage', wjdcQuestionnaire.loadInfo, 'infoPanel', 'infoPage','unitId');
					/* infoPage.beginString = ['<table class="table table-striped table-hover"><thead><tr class="info">',
					                         '<th>模板类型</th>',
					                         '<th>医院/科室</th>',
					                         '<th width="500">模板内容</th>',
					                         '<th>短信类别</th>',
					                         '<th>管理</th>',
					                         '</tr></thead><tbody>'].join('');
					infoPage.endString = '</tbody></table>'; */
				}
				if(page != undefined) {
					infoPage.page = page;
					infoPage.size = 4;
				}
				
				var catId = $('#catId').val();
				if(catId == '请选择评估表分类'){
					catId = "";
				}
	
				$.ajax({
					url : '${webroot}/qsQuestionnaire/f_json/pageQuery.shtml',
					type : 'post',
					dataType : 'json',
					data : { page:infoPage.page, size:infoPage.size , title:title, catId: catId},
					beforeSend: function(){ infoPage.beforeSend('加载信息中...'); },
					error : function(json){ infoPage.error('加载信息出错了!'); },
					success : function(json){
						if(json.result === 'success') {
							
							function getResult(obj) {
								var objStr = '<div class="qm" onmouseover="OnMouseOver(this);" onmouseout="OnMouseOut(this);">'+
												'<div class="qm_t">'+
													'<div class="qm_t_l" >'+
														'<a href="javascript:wjdcQuestionnaire.preview(\''+obj.qid+'\');" >'+obj.title+'</a><span>('+obj.catName+')</span>'+
													'</div>'+
													'<div  class="qm_t_r">';
													
								if(obj.status == 1){
									objStr += '<span titel="问卷已经发布">运行中</span>';
								}else{
									objStr += '<span titel="必须发布后，问卷才能被填写">草稿</span>';
								};		
								objStr += '<span><a href="${webroot}/qsSurveyRecord/f_view/index.shtml?qid='+obj.qid+'&back='+location.href+'">答卷：'+ obj.answerCount +'</a></span>';
								objStr += '<span title="'+ obj.createTime+'">'+ obj.createTime +'</span>';
								objStr += '<div class="clear"></div>';
								objStr += '</div>'+
									  '</div>'+
									  '<div class="qm_d">'+
									  	'<ul class="qm_d_l">'+
									  		'<li class="dropMenu">'+
									  			'<a class="spot" title="问卷录入" href="javascript:void(0);">'+
									  				'<span class="spanText">问卷录入</span>'+
													'<span class="bordCss"></span>'+
												'</a>'+
												'<div class="dropMenuli">'+
													'<ul class="drpMenuli_wj">';
												
												var _cont = [];
												var usernme = $('#id_username').val();
												if (usernme == obj.createUser) {
													var _etime = '';
													if(obj.endTime!=null && obj.endTime!='')
			 											_etime = Comm.date.formatDate(obj.endTime+' 23:59:59','yyyy-MM-dd HH:mm:ss');
			 										if(_etime == '' || Comm.date.compareDate(_etime, new Date()) > 0) {
								                		if(obj.status != 0) {
								                			objStr += /* '<li><a href="javascript:wjdcQuestionnaire.openSurvey(\'phone\','+obj.qid+',\''+obj.title+'\')" >电话调查</a></li>'+	 */
																	  '<li><a href="javascript:wjdcQuestionnaire.openSurvey(\'input\',\''+obj.qid+'\',\''+obj.title+'\')" >现场调查</a></li>';
								                			_cont.push('&nbsp;');
								                		}
			 										}
												}
												if(_cont.length == 0) {
													objStr += /* '<li><a href="javascript:void(0);" style="color:gray;" title="问卷未发布，不能进行电话调查">电话调查</a></li>'+ */	
													  		  '<li><a href="javascript:void(0);" style="color:gray;" title="问卷未发布，不能进行现场调查">现场调查</a></li>';
												}
														
														
										  objStr += '</ul>'+
													'<div class="clear"></div>'+
												'</div>'+
											'</li>'+
				/* 							'<li class="dropMenu">';
											
											if(obj.status == 1 ){
												objStr += '<a href="javascript:wjdcQuestionnaire.share(\''+obj.qid+'\',\''+obj.status+'\');" class="share"><span class="spanText">分享问卷</span></a>';
											} else {
												objStr += '<a href="javascript:void(0);" style="color:gray;" title="问卷未发布,不可分享！" class="share">分享问卷</a>';
											}
											
											objStr += '</li>'+ */
											'<li class="dropMenu">'+
												'<a href="javascript:wjdcQuestionnaire.lookStat(\''+obj.qid+'\');" class="vote" title="统计分析" >'+
													'<span class="spanText">统计分析</span>'+
													'<span class="bordCss"></span>'+
												'</a>'+
												
											'</li>'+
										'</ul>';	
										var usernme = $('#id_username').val();
										if(obj.createUser == usernme){
											objStr += '<ul class="qm_d_r">';
											
											var _etime = '';
											if(obj.endTime!=null && obj.endTime!=''){
	 											_etime = Comm.date.formatDate(obj.endTime+' 23:59:59','yyyy-MM-dd HH:mm:ss');
											}
											if(_etime === '' || Comm.date.compareDate(_etime, new Date()) > 0) {
												if(obj.status == 0){
													objStr += '<li><a title="此问卷状态是草稿，点击发布问卷" class="pubopen" href="javascript:wjdcQuestionnaire.issue(\''+obj.qid+'\',\''+obj.tmCount+'\',\''+obj.status+'\');" >发布</a></li>';
												}else{
													objStr += '<li><a title="此问卷已经发布，点击停用问卷" class="pubclose" href="javascript:wjdcQuestionnaire.issue(\''+obj.qid+'\',\''+obj.tmCount+'\',\''+obj.status+'\');" >停用</a></li>';
												}
	 										} else {
	 											objStr += '<li><a title="此问卷已过期！" class="pubopen_no" href="javascript:void(0);" >无效</a></li>';
	 										}
											
											objStr += '<li><a class="editor" href="javascript:wjdcQuestionnaire.edit(\''+obj.qid+'\');">修改</a></li>';
											
						                	if(obj.status==0) {
						                		objStr +=	'<li><a title="删除此问卷" class="delete" href="javascript:wjdcQuestionnaire.del(\''+obj.qid+'\');">删除</a></li>';		
											} else {
												objStr +=	'<li><a title="已发布问卷不能删除" class="delete_no">删除</a></li>';		
	 										}
						                	
										  	objStr += '</ul>';
										}
										
						  				objStr += '<div class="clear"></div>'+
									'</div>'+
							  '</div>';
								return [objStr].join('');
							}
							infoPage.operate(json, { resultFn:getResult, dataNull:'没有记录噢' });
						}
						else alert('error');
					}
				});
			}
	};
	$(function() {
		var title = "";
		wjdcQuestionnaire.loadInfo(1,title);
		
		
		Csm.select.load({
			id: 'catId',
			url: webroot + '/sysDict/f_json/getValue.shtml',
			data: {unitId: $('#unitId').val(), dictTypeCode: 'qs_type'},
			headerKey: '',
			headerValue: '请选择评估表分类',
			//是否可以多选
			//multiple: true,
			value: '${qsQuestionnaire.catId}',
			kcode: 'dictCode',
			kvalue: 'dictName',
			//如果为空则表示没有等级关系
			pid: 'parentCode'
		});
		
	});
</script>
	</body>
</html>
