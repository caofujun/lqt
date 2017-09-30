<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>添加评估表</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
        <script type="text/javascript" src="${webroot}/resources/js/functions/wjdc.js${version}"></script>
        <style>
        .past_history_menu li a{display:inline-block; color:#333; line-height:20px; width:170px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;}
        .past_history_menu li a:hover{color: #386FB2; text-decoration: none;}
        </style>
	</head>
	<body class="easyui-layout">

	<div data-options="region:'west',title:'常用评估表',border:false" style="width:200px;">
		<ul class="past_history_menu">
			<c:forEach items="${list}" var="info" varStatus="stus">
				<li>
					<a href="javascript:void(0);" id="wjdcBtn${stus.index}" title="${info.title}" onclick="wqai.clickWj('${info.qid}','<c:out value="${info.title}" />','<c:out value="${info.note}" />')">
					<span class="tree-file"></span><c:out value="${info.title}" /></a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div data-options="region:'center',border:false" style=" border-left-width:1px;">
			
		<div style="padding: 10px;">			
			<div id="wjdcBasePanel"></div>
			<div id="wjdcContPanel"></div>			
		</div>
	</div>
	<div data-options="region:'south',border:false" style="height:46px;">
		<div class="footer">		
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" class="no_ico" onclick="wqai.add()" ><span>当前模板创建</span></a>
				</div>
				<div class="n_btn_blue">
					<a href="javascript:;" class="no_ico" onclick="javascript:location.href='${webroot}/qsQuestionnaire/f_view/toedit.shtml?isCache=cached'"><span>空模板创建</span></a>
				</div>
				<div class="n_btn_grey">
					<a href="javascript:;"  class="no_ico" onclick="javascript:location.href='${webroot}/qsQuestionnaire/f_view/index.shtml'" ><span>取消</span></a>
				</div>
			</div>	
		</div>
	</div>
	<script>
	var wqai = {
			//点击左侧的问卷
			clickWj : function(quesId, title, note) {
				wqai.quesId = quesId;
				$('#wjdcBasePanel').empty().append(['<h2 style="padding: 5px;font-size: 16px;font-weight: bold;text-align: center;">',title,'</h2>',
					'<p>',note,'</p>'].join(''));
				var _wjdcContPanel = $('#wjdcContPanel').empty().append('题目加载中...');
				wjdc.loadQues('wjdcContPanel', quesId, {
					resultFn : function(i, obj) {
						var _cont = ['<div  class="div_question" id="questionSel',obj.qId,'"><div class="div_title_question_all"><div class="div_title_question">',(i + 1),'. ',obj.title,'</div></div>'];
						_cont.push('<div  class="div_table_radio_question">',wjdc.createOpt(obj),'</div></div>');
						return _cont.join('');
					},
					dataNullFn : function() {
						_wjdcContPanel.append('问卷还没有题目选项!');
					}
				});
			},
			//根据模板创建问卷
			add : function() {
				if(wqai.quesId === undefined) {
					$.messager.show({ title: '提示', msg: '请先选择一个模板!' });
					return;
				}
				location = '${webroot}/qsQuestionnaire/f_view/toedit.shtml?isCache=cached&quesId=' + wqai.quesId;
			}
	};
	$(function() {
		$('#wjdcBtn0').click();
	});
	</script>
	</body>
</html>