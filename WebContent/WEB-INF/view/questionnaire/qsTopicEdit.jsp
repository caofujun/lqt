<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>编辑问卷题目</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div class="easyui-layout" fit="true">
	    	<div region="south" border="true" style="height:50px;text-align: center;padding: 10px;overflow: hidden;">
	    		<div class="footer_btn">
					<div class="n_btn_blue">
						<a href="javascript:;" class="no_ico" id="editFormInfoBtn" onclick="$('#editFormInfo').submit()" ><span>确认</span></a>
					</div>
					<div class="n_btn_grey">
						<a href="javascript:;" class="no_ico" onclick="parent.Comm.dialogClose('${param.dialogId}')"><span>取消</span></a>
					</div>
				</div>
	        </div>
	        <div region="center" border="false">
        		<form id="editFormInfo" method="post">
        			<input type="hidden" name="tid" value="${qsTopic.tid}"/>
        			<input type="hidden" name="qid" value="${qsTopic.qid}"/>
					<input type="hidden" id="unitId" name="unitId" value="${user.unitId}"/>
					<table class="table" cellpadding="0" cellspacing="0" style="width:696px;">
						<tbody>
							<tr>
								<td class="t_title" style="width:100px;"><span style="color:red">*</span>标题：</td>
								<td>
									<input type="text" name="title" value="<c:out value="${qsTopic.title}" />" style="width: 300px;" maxlength="50" class="easyui-validatebox text" required="true"/>
								
								</td>
							</tr>
							<tr>
								<td class="t_title"><span style="color:red">*</span>题目分类：</td>
								<td>
									<span class="standard_select">
										<span class="select_shelter">
											<select id="catId" name="catId" class="easyui-validatebox"  required="true"></select>
										</span>
									</span>
								</td>
							</tr>
							<tr>
								<td class="t_title">是否必填：</td>
								<td>
									<nis:radio name="allowNull" dictcode="boolean" value="${qsTopic.allowNull}" defvalue="0"/>
								</td>
							</tr>
							<tr>
								<td class="t_title">题型选择：</td>
								<td>
									<nis:radio name="ttype" dictcode="topic_type" value="${qsTopic.ttype}" defvalue="1" exp="onclick=\"wrqe.dtSelect(this.value)\""/>     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="tips" style="color:blue;display:none;">温馨提示：填写的多选题将不计入不满意调查结果</span>
								</td>
							</tr>
							<tr>
								<td class="t_title">答题选项：</td>
								<td>
									<div id="qtypeSelectPanel" class="hidden">
										<table id="selectTable" class="table_blank" cellpadding="0" cellspacing="0" style="width:350px;">
											<tr>
												<td width="110">选项内容</td>
												<td width="50" id="scorePanel">分值</td>
												<td width="50">是否可填</td>
												<td width="40">操作</td>
												<td width="50">缺省值</td>
											</tr>
										</table>
										<p style="margin-left:10px;">
											<a class="ico_add" title="新增选项" id="addSelectBtn" data-id="0" href="javascript:wrqe.addSelect();"></a>
											<a class="ico_template" title="插入模板" href="javascript:wrqe.addTemp();"></a>
										</p>
									</div>
									<div id="qtypeGapPanel" class="hidden">
										<p><textarea cols="20" rows="1" disabled="disabled" style="width:400px; height:60px;"></textarea></p>
									</div>
									
									<input type="hidden" id="depNo" name="depNo" value="${user.depNo}${qsTopic.depNo}" style="width: 150px;"/>
								</td>
							</tr>
							<tr>
								<td class="t_title">答题描述：</td>
								<td>
									<input type="text" name="note" class="text" style="width: 400px;" value="<c:out value="${qsTopic.note}" />"/>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
        	</div>
		</div>
	<script>
	var wrqe = {
			/* toType : function() {
				location = '${webroot}/type/index.action?ptype=${user.unitId}003&back='+location.href;
			}, */
			//答题选项出发事件
			dtSelect : function(value) {
				$('#tips').hide();
				if(value==='1' || value==='2') {
					$('#qtypeGapPanel').addClass('hidden');
					//$('#qtypeSingle').addClass('hidden');
					$('#qtypeSelectPanel').removeClass('hidden');
					if(value==='2') {
						$('#tips').show();
						//隐藏分值
						$('#scorePanel').css('display', 'none');
						$('input[name=optValue]').parent().css('display', 'none');
					} else {
						$('#scorePanel').css('display', 'block');
						$('input[name=optValue]').parent().css('display', 'block');
					}
				} else if(value==='3') {
					$('#qtypeSelectPanel').addClass('hidden');
					//$('#qtypeSingle').addClass('hidden');
					$('#qtypeGapPanel').removeClass('hidden');
				}/*  else if(value==='5'){
					$('#qtypeSelectPanel').addClass('hidden');
					$('#qtypeGapPanel').addClass('hidden');
					//$('#qtypeSingle').removeClass('hidden');
				} */
			},
			//点击是否可填
			aiClick : function(_this) {
				if($(_this).prop('checked')) {
					$(_this).parent().find('input[name=allowInput]').val('1');
				} else {
					$(_this).parent().find('input[name=allowInput]').val('0');
				}
			},
			//点击是否设置缺省值
			selectClick :function(_this){
					var _qtype = $('input:radio[name="ttype"]:checked').val();
					if(_qtype==1){
						var checks = document.getElementsByName("quesheng");
						if(_this.checked){
							  for(var i=0;i<checks.length;i++){
						          checks[i].checked = false;
						        $(checks[i]).parent().find('input[name=isSelect]').val('0');
						}
							  _this.checked = true;  
							  $(_this).parent().find('input[name=isSelect]').val('1');
						}else{
							   for(var i=0;i<checks.length;i++){
						            checks[i].checked = false;
						         $(checks[i]).parent().find('input[name=isSelect]').val('0');
						}
				
					}
				}else{
				if($(_this).prop('checked')) {
						$(_this).parent().find('input[name=isSelect]').val('1');				
					} else {
						$(_this).parent().find('input[name=isSelect]').val('0');
					}
				}
			},
			//添加选项
			addSelect : function(optId, optName, optValue, allowInput,isSelect) {
				var _addSelectBtn = $('#addSelectBtn');
				var value = _addSelectBtn.attr('data-id');
				var count1 = Number(value)+Number(1);
				var _qtype = $('input:radio[name="ttype"]:checked').val();
				var _hidden = '';
				if(_qtype==2) {
					_hidden = 'style="display: none;"';
				}		
				$('#selectTable').append([
		    	                           '<tr id="options',value,'"><td><input type="hidden" name="optId" value="',optId,'">',
		    	                           '<input type="text" class="text" name="optName" value="',optName,'" style="width: 100px;"></td>',
		    	                           '<td ',_hidden,'><input type="text" class="text" name="optValue" value="',optValue,'" style="width: 30px;"></td>',
		    	                           '<td><input type="hidden" name="allowInput" value="',(allowInput==1?1:0),'"/><input type="checkbox" onclick="wrqe.aiClick(this)" ',(allowInput==1?' checked':''),'></td>',
		    	                           '<td><a class="ico_del" title="删除" href="javascript:wrqe.delSelect(\'options',value,'\');"></a></td>',
		    	                           '<td><input type="hidden" name="isSelect" value="',(isSelect==1?1:0),'"/><input type="checkbox"  name="quesheng" onclick="wrqe.selectClick(this)" ',(isSelect==1?' checked':''),'></td></tr>'
		    	                           ].join(''));	
				_addSelectBtn.attr('data-id', count1);
			},
			//添加模板
			addTemp : function() {
				wrqe.addSelect(undefined, '是', '');
				wrqe.addSelect(undefined, '否', '');
			},
			//删除选项
		    delSelect : function(value) {
			     $('#' + value).remove();
			}
	};
	$(document).ready(function () { 
		window.setTimeout(function(){
			
			Csm.select.load({
				id: 'catId',
				url: webroot + '/sysDict/f_json/getValue.shtml',
				data: {unitId: $('#unitId').val(), dictTypeCode: 'topic_classify'},
				headerKey: '',
				headerValue: '请选择题目分类',
				//是否可以多选
				//multiple: true,
				value: '${qsTopic.catId}',
				kcode: 'dictCode',
				kvalue: 'dictName',
				//如果为空则表示没有等级关系
				pid: 'parentCode'
			});
			//处理选项
			<c:forEach items="${options}" var="info">wrqe.addSelect('${info.optId}',"<c:out value="${info.optName}" />","<c:out value="${info.optValue}" />","<c:out value="${info.allowInput}" />","<c:out value="${info.isSelect}" />");</c:forEach>
			
			var _qtype = $('input:radio[name="ttype"]:checked').val();
			if(_qtype != undefined) {
				wrqe.dtSelect(_qtype);
			}
			
			Comm.form({
				id: 'editFormInfo',
				url: '${webroot}/qsTopic/f_json/save.shtml',
				subbtn: 'editFormInfoBtn',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({ title: '提示', msg: '操作成功！' });
						parent.wqe.loadQuestion();
			    		parent.Comm.dialogClose('${param.dialogId}');
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