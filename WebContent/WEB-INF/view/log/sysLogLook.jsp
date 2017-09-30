<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
	<table class="table mb60" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title">日期：</td>
				<td><fmt:formatDate value="${sysLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<td class="t_title">错误方法：</td>
				<td>${sysLog.logFun}
				</td>
			</tr>
			<tr>
				<td class="t_title">详情：</td>
				<td><textarea name="remark" style="width: 95%; height: 100px;" class="easyui-validatebox">${sysLog.logContent}</textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer"><input type="button" class="btn_save"
					onclick="Comm.dialogClose('${param.dialogId}')" value="关闭"></div>
