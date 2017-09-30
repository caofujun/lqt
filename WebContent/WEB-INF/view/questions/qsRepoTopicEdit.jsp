<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>编辑题目</title>
        <%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div class="easyui-layout" fit="true">
	    	
	        <div region="center" border="false">
        		<form id="editFormInfo" method="post">
        			<input type="hidden" name="tid" value="${qsRepoTopic.tid}"/>
					<input type="hidden" id="unitId" value="${user.unitId}"/>
					<table class="table" cellpadding="0" cellspacing="0" style="margin-bottom:60px;">
						<tbody>
							<tr>
								<td class="t_title" style="width:80px;"><span style="color:red">*</span>标题：</td>
								<td>
									<input type="text" id="title" name="title" value="<c:out value="${qsRepoTopic.title}" />" style="width: 300px;" maxlength="240" class="easyui-validatebox text" required="true"/>
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
									<nis:radio name="allowNull" dictcode="boolean" value="${qsRepoTopic.allowNull}" defvalue="0"/>
								</td>
							</tr>
							<tr>
								<td class="t_title">题型选择：</td>
								<td>
									<nis:radio name="ttype" dictcode="topic_type" value="${qsRepoTopic.ttype}" defvalue="1" exp="onclick=\"wrqe.dtSelect(this.value)\""/>
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
											</tr>
										</table>
										<p style="margin-left:10px;">
											<a class="ico_add mr5" title="新增选项" id="addSelectBtn" data-id="0" href="javascript:wrqe.addSelect();"></a>
											<a title="插入模板" class="ico_template" href="javascript:wrqe.addTemp();"></a>
										</p>
									</div>
									<div id="qtypeGapPanel" class="hidden">
										<p><textarea cols="20" rows="1" disabled="disabled" style="width:400px; height:60px;"></textarea></p>
									</div>
									<!-- <div id="qtypeSingle" class="hidden">
										<p><input type="text" class="text" disabled="disabled"/></p>
									</div>&nbsp; -->
								</td>
							</tr>
							<%-- <tr<c:if test="${user.depNo!=null}"> class="hidden"</c:if>>
								<td class="t_title">所属科室：</td>
								<td class="t_cont">
									<input type="text" class="text" id="depNo" name="depNo" value="<c:if test="${qsRepoTopic==null}">${user.depNo}</c:if>${qsRepoTopic.depNo}" style="width: 150px;"/>
								</td>
							</tr> --%>
							<tr>
								<td class="t_title">答题描述：</td>
								<td>
									<input type="text" class="text" name="note" style="width:90%;" value="<c:out value="${qsRepoTopic.note}" />"/>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
        	</div>
        	<div class="footer" >
	    		<input type="button" class="btn_save" id="editFormInfoBtn" onclick="wrqe.beforeSubmit()" value="保存"/>
				<input type="button" class="btn_return" onclick="parent.Comm.dialogClose('${param.dialogId}')" value="关闭"/>
	        </div>
		</div>
	<script>
	var flag="0";
	var wrqe = {
			/* toType : function() {
				location = '${webroot}/type/index.action?ptype=${user.unitId}003&back='+location.href;
			}, */
			//答题选项出发事件
			dtSelect : function(value) {
				if(value==='1' || value==='2') {
					$('#qtypeGapPanel').addClass('hidden');
					//$('#qtypeSingle').addClass('hidden');
					$('#qtypeSelectPanel').removeClass('hidden');
					if(value==='2') {
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
			//保存
			beforeSubmit : function(){
				$('#editFormInfo').submit();
			},
			//点击是否可填
			aiClick : function(_this) {
				if($(_this).prop('checked')) {
					$(_this).parent().find('input[name=allowInput]').val('1');
				} else {
					$(_this).parent().find('input[name=allowInput]').val('0');
				}
			},
			//添加选项
			addSelect : function(optName, optValue, allowInput) {
				var _addSelectBtn = $('#addSelectBtn');
				var value = _addSelectBtn.attr('data-id');
				var count1 = Number(value)+Number(1);
				var _qtype = $('input:radio[name="ttype"]:checked').val();
				var _hidden = '';
				if(_qtype==2) {
					_hidden = 'style="display: none;"';
				}
				$('#selectTable').append([
		    	                           '<tr id="options',value,'"><td><input type="text" class="text" name="optName" value="',optName,'" style="width: 100px;"></td>',
		    	                           '<td ',_hidden,'><input type="text" class="text" name="optValue" ',(_qtype==2?'readonly':''),' value="',optValue,'" style="width: 30px;"></td>',
		    	                           '<td><input type="hidden" name="allowInput" value="',(allowInput==1?1:0),'"/><input type="checkbox" onclick="wrqe.aiClick(this)" ',(allowInput==1?' checked':''),'></td>',
		    	                           '<td><a class="ico_del" title="删除" href="javascript:wrqe.delSelect(\'options',value,'\');"></a></td></tr>'
		    	                           ].join(''));
				_addSelectBtn.attr('data-id', count1);
			},
			//添加模板
			addTemp : function() {
				wrqe.addSelect('执行', '');
				wrqe.addSelect('未执行', '');
				wrqe.addSelect('执行有缺陷', '');
			},
			//删除选项
		    delSelect : function(value) {
				$('#' + value).remove();
			}
	};
	$(document).ready(function () { 
		window.setTimeout(function(){
			$('#title').focus();
			
			Csm.select.load({
				id: 'catId',
				url: webroot + '/sysDict/f_json/getValue.shtml',
				data: {unitId: $('#unitId').val(), dictTypeCode: 'topic_classify'},
				headerKey: '',
				headerValue: '请选择题目分类',
				//是否可以多选
				//multiple: true,
				value: '${qsRepoTopic.catId}',
				kcode: 'dictCode',
				kvalue: 'dictName',
				//如果为空则表示没有等级关系
				pid: 'parentCode'
			});
			
			Csm.select.dep({
				id: 'depNo',
				unitId: 'unitId'
			});
			
			//处理选项
			<c:forEach items="${options}" var="info">wrqe.addSelect('${info.optName}','${info.optValue}','${info.allowInput}');</c:forEach>
			
			var _qtype = $('input:radio[name="ttype"]:checked').val();
			if(_qtype != undefined) {
				wrqe.dtSelect(_qtype);
			}
			Comm.form({
				id: 'editFormInfo',
				url: '${webroot}/qsRepoTopic/f_json/save.shtml',
				subbtn: 'editFormInfoBtn',
				success : function(json) {
					if (json.result === 'success') {
						parent.$.messager.show({ title: '提示', msg: '操作成功！' });
						parent.info.query();
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