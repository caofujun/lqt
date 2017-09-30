<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<form method="post" id="by007ConfigForm">
	<div style="width:100%;height:290px;overflow: hidden;">
		<div id="by007ConfigPanel"></div>
	</div>
	<div class="footer dialog_footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#by007ConfigForm').submit();"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	var by007Config = {
		//查询
		panel:'by007ConfigPanel'
	};
	$(document).ready(function() {
		$('#by007ConfigPanel').datagrid({
			fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        collapsible:true,
	        url:'${webroot}/by007Config/f_json/findList.shtml',
	        remoteSort: false,
	        border:false,
	        columns:[
	        	[
	             {field:'typeName',title:'监测类型',sortable:true,width:80,align:'center'},
	             {field:'ck',title:'重点关注',width:50,align:'center',
					formatter:function(value,rec,index){
						return ['<input style="width:15px;height:18px;" name="typeId" value="',rec.typeId,'" type="checkbox" ',rec.grade=='3'?'checked':'',' />'].join('');
				    }
	             }
	            ]
	        ],
	        pagination:false,
	        rownumbers:true,
	        toolbar:'#tb'
	    });
		
		Comm.form({
			id : 'by007ConfigForm',
			url : '${webroot}/by007Config/f_json/saveGrade.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					var parentObject = parent.Comm.getObjectCache();
					parentObject.query();
					parent.Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : '操作失败！' });
				} else {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	});
</script>