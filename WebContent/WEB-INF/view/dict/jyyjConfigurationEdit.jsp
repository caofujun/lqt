<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<form id="editJyyj" method="post">
	<input type="hidden" name="id" value="${zg032Sjxmpp.id }" />
	<table class="table" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td class="t_title"><span class="red">*</span>送检项目匹配字段：</td>
				<td>
					<nis:select id="matchField" name="matchField" value="${zg032Sjxmpp.matchField }" dictcode="match_field" cssCls="easyui-combobox" exp="style=\"width: 152px;\" data-options=\"editable:false\""/>
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>匹配符：</td>
				<td>
					<nis:select id="match" name="match" value="${zg032Sjxmpp.match }" dictcode="match_character" cssCls="easyui-combobox" exp="style=\"width: 152px;\" data-options=\"editable:false\""/>
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>匹配值：</td>
				<td>
					<input type="text" class="easyui-validatebox" required="true" id="matchValue" name="matchValue" value="${zg032Sjxmpp.matchValue}" style="width:140px;">
				</td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>感染因素：</td>
				<td>
				<div class="select_del">
					<input type="text" id="id_infectFactor" name="infectFactor" required="true" />
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_infectFactor').combo('clear');"></a>
				</div>
				</td>
			</tr>
		</tbody>
	</table>
</form>
	<div class="footer dialog_footer">		
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="editJyyjBtn" onclick="$('#editJyyj').submit()" class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>	
	</div>
<script type="text/javascript">
$(document).ready(function() {
	window.setTimeout(function() {
		Comm.form({
			id : 'editJyyj',
			url : '${webroot}/zg032Sjxmpp/f_json/save.shtml',
			subbtn : 'editJyyjBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					jyyj.query();
					Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					$.messager.show({ title : '提示', msg : json.msg });
				} else {
					$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
		$('#id_infectFactor').combogrid({
			url:'${webroot}/zg032Sjxmpp/f_json/findByIdOrName.shtml',
			method:'get',
			value:'${param.elementId}',
			panelHeight:300,
			panelWidth:300,
			idField:'elementId',
	        textField:'elementName',
	        columns:[
	        	[
		            {field:'elementId',title:'诊断依据序号',sortable:true,width:100},
		            {field:'elementName',title:'名称',sortable:true,width:180}
	            ]
	        ],
	        onHidePanel : function() {
	        	Csm.valueValite.combogrid('id_infectFactor');
			},
			onLoadSuccess:function(r){
                $("#id_infectFactor").combogrid("setValue", '${zg032Sjxmpp.infectFactor}');
            }
		});
		
	}, 100);
	
});

</script>