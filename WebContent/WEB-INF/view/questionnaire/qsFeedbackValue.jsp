<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<form id="editFormFoVisitNotes" method="post">
		<input type="hidden" name="qid" value="${param.qid}"/>
			<table class="table" cellpadding="0" cellspacing="0">
				<tbody>		
					<tr>
						<td class="t_title">
							审核触发条件：
						</td>
						<td>
							单题分数<=<input type="text" name="feedbackValue" value="${qsQuestionnaire.feedbackValue}"   class="easyui-validatebox" >
						</td>
					</tr>					
				</tbody>
			</table>		
		<div class="footer dialog_footer">				
			<input type="button" id="changeFormFoVisitNotesSubmitBtn" class="btn_save" data-options="iconCls:'icon-save'" onclick="$('#editFormFoVisitNotes').submit()" value="保存"/>
		</div>

</form>
<script>
	$(document).ready(function () {
		window.setTimeout(function(){
			Comm.form({
				id: 'editFormFoVisitNotes',
				url: '${webroot}/qsQuestionnaire/f_json/update.shtml',
				subbtn: 'changeFormFoVisitNotesSubmitBtn',
				success : function(data) {
					if (data.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });						
						Comm.dialogClose('${param.dialogId}');
					}  else {
						parent.$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		},100);
	});
	</script>