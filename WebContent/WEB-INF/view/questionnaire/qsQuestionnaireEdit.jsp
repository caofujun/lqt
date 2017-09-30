<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>修改问卷</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
        <script type="text/javascript" src="${webroot}/resources/js/functions/wjdc.js${version}"></script>
        <style>
        .panel-header{background-color:#efefef;}
        </style>
	</head>
	<body class="easyui-layout">
	<form id="editFormWjdcQuestionnaire" method="post">
		<input type="hidden" id="wqQuesId" name="qid" value="${qsQuestionnaire.qid}"/>
		<input type="hidden" id="unitId" name="unitId" value="${user.unitId}"/>
		<div data-options="region:'north',title:'评估表基本信息',border:false" style="height: 140px;overflow: hidden; border-bottom-width:1px;">
			<table class="table_cx" cellpadding="0" cellspacing="0" style="width:100%; min-width:925px;">
				<tbody>
					<tr>
						<td class="t_title" style="width:100px;"><span style="color:red">*</span>评估表主题：</td>
						<td >
							<input type="text" name="title" value="<c:out value="${qsQuestionnaire.title}" />"  onblur="if(this.value=='')this.value='${qsQuestionnaire.title}'"  onfocus="if(this.value=='${qsQuestionnaire.title}')this.value='';" class="easyui-validatebox text" required="true"/>
						</td>
						<td class="t_title" style="width:100px;">评估表分类：</td>
						<td >
							<select id="catId" name="catId" class="easyui-validatebox" style="width: 150px;" required="true"></select>
						</td>					
						<td class="t_title" style="width:100px;">截至日期：</td>
						<td >
							<input type="text" name="endTime" style="width:140px;" value="${qsQuestionnaire.endTime}" class="Wdate text" onclick="WdatePicker({minDate:'%y-%M-%d'})"  validType="date[{minDate:'%y-%M-%d'}]"/> <span style="color: gray;">为空表示长期有效</span>
						</td>
					</tr>
					<tr>
						<td class="t_title">是否自定义模板：</td>
						<td>
							<nis:radio name="isMod" dictcode="boolean" value="${qsQuestionnaire.isMod}" defvalue="0"/>
						</td>
				        <td class="t_title">是否设置权重：</td>
						<td>
							<nis:radio name="isQz" dictcode="boolean" value="${qsQuestionnaire.isQz}" exp="onclick=\"isDisplay()\"" defvalue="0"/>
						</td>
						<td class="t_title" colspan="1" style="width:100px;"> 调查类型：</td>
				        <td colspan="1">
	 				 		<nis:radio name="isAnonymous" dictcode="nametrueornot" value="${qsQuestionnaire.isAnonymous}" defvalue="1"/> 
				        </td>
					</tr>
					<tr>
						<%-- <c:choose>
						<c:when test="${user.depNo!=null}">
							<td class="t_title" width="100">所属科室：</td>
							<td class="t_cont" style="vertical-align: text-top;">
								<input type="hidden" id="depNo" name="depNo" value="${user.depNo}" style="width: 150px;"/>
							</td>
						</c:when>
						<c:otherwise>
							<td class="t_title">所属科室：</td>
							<td class="t_cont">
								<input type="text" class="text" id="depNo" name="depNo" value="${qsQuestionnaire.depNo}" style="width: 150px;"/>
							</td>
						</c:otherwise>
						</c:choose> --%>
						
						<td class="t_title">评估表说明：</td>
						<td colspan="5">
						    <textarea rows="1" cols="30" name="note">${qsQuestionnaire.note }</textarea>							
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div data-options="region:'center',title:'评估表题目',border:false" >			
			<div style="padding:10px;">
				<div id="questionsPanel" style="margin-bottom:60px;"></div>
			</div>
		</div>
		<div class="footer">
			<span style="float:left; margin-left:15px;">
				<a class="img_add" onclick="wqe.openUpdateQuestion()">添加题目</a>
				<a class="img_add" onclick="wqe.openAddQuestion()">从题库中选</a>
			</span>			
			<div class="footer_btn" style="margin-right:80px;">
				<div class="n_btn_blue">
					<a href="javascript:;" class="no_ico" id="changeFormWjdcQuestionnaireSubmitBtn" onclick="caScore()"><span>确认</span></a>
				</div>
				<div class="n_btn_grey">
					<a href="javascript:;"  class="no_ico" onclick="toBack()"><span>取消</span></a>
				</div>
			</div>	
	    </div>
	</form>
	<script>
	function toBack() {
		location.href = '${webroot}/qsQuestionnaire/f_view/index.shtml';
	}
  	function isDisplay(){
		var _qtype = $('input:radio[name="isQz"]:checked').val();
		if (_qtype==1) {
			var scorelist=document.getElementsByName("tscore");
			for(var i=0;i<scorelist.length;i++){
				if(scorelist.length>1 && scorelist[i].value=="100"){
					scorelist[i].value="";
				}
			} 
			$("span[name='quanzhong']").show();
		} else {
			$("span[name='quanzhong']").hide();						
		}
	}  
 	function caScore(){
		var scorelist=document.getElementsByName("tscore");
		var _qtype = $('input:radio[name="isQz"]:checked').val();
		var sum=0;
		for(var i=0;i<scorelist.length;i++){
			sum+=parseInt(scorelist[i].value);
		}
		if(sum!=100 && _qtype==1){
			alert("权重总合应为100！");
			return;
		}else{
			$('#editFormWjdcQuestionnaire').submit();			
		}
	} 
	var wqe = {
			//记录已经选择的题目
			selectQuestions : [],
			//记录题目的排序
			quesOptionSort : [],
			//加载题目
			loadQuestion : function() {
				wjdc.loadQues('questionsPanel', $('#wqQuesId').val(), {
					bizFn : 'wqe.selectQuestions = [];',
					resultFn : function(i, obj, data) {
						wqe.quesOptionSort.push({tid:obj.tid,sortNo:i*2}); 
						wqe.selectQuestions.push(obj.title); 
						var _cont=null;
						if(obj.ttype=='1'){
						_cont = ['<div id="questionSel',obj.tid,'" class="div_question"><div class="div_title_question_all"><div class="div_title_question">',(i + 1),'. ',obj.title,'&nbsp;&nbsp;&nbsp;',
									 '<a href="javascript:wqe.openUpdateQuestion(\'',obj.tid,'\');">修改</a>&nbsp;',
									 '<a href="javascript:wqe.delQuestion(\'',obj.tid,'\');">删除</a>&nbsp;',
									 '<span name="quanzhong" id="tishi" style="display:none">题目权重 :<input type="text" name="tscore" style="width:20px;"  onblur="wqe.saveScore(this,\''+obj.tid+'\')"  value=',obj.weight,'><span>%</span></span></div></div>'];
						}else{
							_cont = ['<div id="questionSel',obj.tid,'" class="div_question"><div class="div_title_question_all"><div class="div_title_question">',(i + 1),'. ',obj.title,'&nbsp;&nbsp;&nbsp;',
									 '<a href="javascript:wqe.openUpdateQuestion(\'',obj.tid,'\');">修改</a>&nbsp;',
									 '<a href="javascript:wqe.delQuestion(\'',obj.tid,'\');">删除</a>&nbsp;',
									 '</div></div>'];
						}
						 _cont.push('<div  class="div_table_radio_question">',wjdc.createOpt(obj),'</div>',
						'<div class="sorting">');
						if(i == 0)
							_cont.push('<input type="button" class="ico_up_grey" title="向上移动"/>');
						else
							_cont.push('<input type="button" class="ico_up" onclick="wqe.updateSort(\'',obj.tid,'\',\'up\')" title="向上移动"/>');
					
						if(i == (data.length - 1))
							_cont.push('<input type="button" class="ico_down_grey" title="向下移动"/>');
						else
							_cont.push('<input type="button" class="ico_down" onclick="wqe.updateSort(\'',obj.tid,'\',\'down\')" title="向下移动"/>');
						
						_cont.push('</div><div class="clear"></div></div>');
						return _cont.join('');
					},
					successFn : function() {
						//初始化权重是否显示与隐藏
						isDisplay();
					}
				});
			},
			//修改排序
			updateSort : function(tid, type) {
				var _params = '';
				$.each(wqe.quesOptionSort, function(i, obj) {
					var _sortNo = obj.sortNo;
					if(obj.tid == tid) {
						if(type=='up') _sortNo = _sortNo - 3;
						else if(type=='down') _sortNo = _sortNo + 3;
					}
					_params += obj.tid + ',' + _sortNo + '#';
				});
				$.ajax({
                    url: '${webroot}/qsTopic/f_json/updateSort.shtml',
                    type: 'post',
                    data: { 'params': _params },
                    dataType: 'json',
                    success : function(json) {
                    	if (json.result === 'success') {
                            wqe.loadQuestion();
    					} else if(json.result === 'error') {
    						$.messager.show({ title : '提示', msg : '操作失败！' });
    					} else {
    						$.messager.show({ title : '提示', msg : json.msg });
    					}
					}
                });
			},
			//保存权重
			saveScore : function(obj,id){  
			           if (!parseInt(obj.value)){   
			           	  alert("错误,请输入正确的整数"); 
			           		obj.value="";
			           		return;
			           }
			           if(obj.value<1||obj.value>100){
			        	   alert("请输入1-100以内的整数"); 
			        	  	obj.value="";
			        	   return;
			           }
			           else{	     
			 	$.ajax({
                    url: '${webroot}/qsTopic/f_json/scoreUpdate.shtml',
                    type: 'post',
                    data: {'weight':obj.value,'tid':id},
                    dataType: 'json',
                    success : function(json) {
                    	if (json.result === 'success') {
           						
    					} else if(json.result === 'error') {
    						$.messager.show({ title : '提示', msg : '设置权重失败！' });
    					} else {
    						$.messager.show({ title : '提示', msg : json.msg });
    					}
					}
                }); 
			  }
			},
			
			//修改问卷的题目
			openUpdateQuestion : function(qId) {
				if(!qId) {
					qId = '';
				}
				Comm.dialog({
	                url:"${webroot}/qsTopic/f_view/toedit.shtml?qid=${qsQuestionnaire.qid}&id=" + qId,
		    		type: 'iframe',
	                title: '编辑题目',
	                width: 750,
		    		height: 420
	            });
			},
			//打开添加题目
			openAddQuestion : function() {
				Comm.dialog({
		    		url: '${webroot}/qsRepoTopic/f_view/select.shtml',
		    		type: 'iframe',
		    		title:'新增题目',
		    		width: 750,
		    		height: 420
		    	});
			},
			//添加题目
			addQuestion : function(questions) {
				if(questions!=undefined) {
					var _repoQids = [];
					$.each(questions, function(i, obj) {
						if($.inArray(obj.title, wqe.selectQuestions)===-1) {
							_repoQids.push(obj.tid);
						}
					});
					if(_repoQids.length > 0) {
						$.ajax({
		                    url: '${webroot}/qsTopic/f_json/save.shtml',
		                    type: 'post',
		                    data: { 'qid': $('#wqQuesId').val(),'repoTids': _repoQids.join(',') },
		                    dataType: 'json',
		                    success : function(json) {
		                    	if (json.result === 'success') {
		                            $.messager.show({ title: '提示', msg: '添加题目成功！' });
		                            wqe.loadQuestion();
		    					} else if(json.result === 'error') {
		    						$.messager.show({ title : '提示', msg : '操作失败！' });
		    					} else {
		    						$.messager.show({ title : '提示', msg : json.msg });
		    					}
							}
		                });
					}
				}
			},
			//删除选择的题目
			delQuestion : function(tid) {
				$.messager.confirm('提示', '确定要删除该题目吗?', function (r) {
					if (r) {
						$.ajax({
		                    url: '${webroot}/qsTopic/f_json/delete.shtml',
		                    type: 'post',
		                    data: { 'id': tid },
		                    dataType: 'json',
		                    success : function(json) {
		                    	if (json.result === 'success') {
		                            $.messager.show({ title: '提示', msg: '删除成功！' });
		                            wqe.loadQuestion();
		    					} else if(json.result === 'error') {
		    						$.messager.show({ title : '提示', msg : '操作失败！' });
		    					} else {
		    						$.messager.show({ title : '提示', msg : json.msg });
		    					}
							}
		                });
					}
				});
			}
	};
		
	$(document).ready(function () { 
		window.setTimeout(function(){
			<c:if test="${user.dataScope==6}">
			Csm.select.dep({
				id: 'depNo',
				unitId: 'unitId'
			});
			</c:if>
			
			wqe.loadQuestion();
			
					
			
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
			
			Comm.form({
				id: 'editFormWjdcQuestionnaire',
				url: '${webroot}/qsQuestionnaire/f_json/save.shtml',
				subbtn: 'changeFormWjdcQuestionnaireSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({ title: '提示', msg: '操作成功！' });
						location = '${webroot}/qsQuestionnaire/f_view/index.shtml';
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : '操作失败！' });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
			
		},100);
	});
	</script>
	</body>
</html>