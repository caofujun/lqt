<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>医院感染诊断</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>

	<body>
		<div id="yygrzdPanel"></div>
		<div class="footer" region="south" split="false" border="false">		
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="yygrzd.getSelected()"  class="no_ico"><span>选&nbsp;择</span></a>
				</div>			
			</div>
		</div>

		<script type="text/javascript">
			var yygrzd = {
				panel : 'yygrzdPanel',
			    //选择
			    getSelected : function() {
			    	var curRow = $('#'+yygrzd.panel).datagrid('getSelected');
					var parentObject = parent.Comm.getObjectCache();
					parent.Comm.dialogClose('${param.dialogId}');
					parentObject.getYygrzd(curRow.infectCode,curRow.infectName);
			    }
			};
			$(document).ready(function () {
				var _columns = [];
				_columns.push({field:'infectName',title:'名称',sortable:true,width:180});
				_columns.push({field:'infectCode',title:'编码',sortable:true,width:100});
				$('#'+yygrzd.panel).treegrid({
	                nowrap: true,
	                autoRowHeight: true,
	                striped: true,
	                fitColumns: true,
	                fit: true,
	                collapsible:true,
	                url:'${webroot}/yygrzd/f_json/findYygrzdList.shtml?flag=1',
	                remoteSort: false,
	                singleSelect: true,
	                idField:'infectCode',
	                treeField:'infectName',
	                columns:[
	                	_columns
	                ]
			    	,onDblClickRow:function(row){
			    		yygrzd.getSelected();
			        },
	                toolbar:'#tb',
	                onLoadSuccess: function (row, data) {
	                	$.each(data, function (i, val) {
	                		$('#'+yygrzd.panel).treegrid('collapseAll', data[i].id);
	                	});
	                }
	            });
				
			});
	
		</script>
	</body>
</html>