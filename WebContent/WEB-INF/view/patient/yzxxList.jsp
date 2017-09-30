<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="yzxxDetailPanel"></div>
		<script type="text/javascript">
			/**
	        * EasyUI DataGrid根据字段动态合并单元格
	        * @param fldList 要合并table的id
	        * @param fldList 要合并的列,用逗号分隔(例如："name,department,office");
	        */
	        function MergeCells(tableID, fldList) {
	            var Arr = fldList.split(",");
	            var dg = $('#' + tableID);
	            var fldName;
	            var RowCount = dg.datagrid("getRows").length;
	            var span;
	            var PerValue = "";
	            var CurValue = "";
	            var length = Arr.length - 1;
	            for (i = length; i >= 0; i--) {
	                fldName = Arr[i];
	                PerValue = "";
	                span = 1;
	                for (row = 0; row <= RowCount; row++) {
	                    if (row == RowCount) {
	                        CurValue = "";
	                    }
	                    else {
	                        CurValue = dg.datagrid("getRows")[row][fldName];
	                    }
	                    if (PerValue == CurValue) {
	                        span += 1;
	                    }
	                    else {
	                        var index = row - span;
	                        dg.datagrid('mergeCells', {
	                            index: index,
	                            field: fldName,
	                            rowspan: span,
	                            colspan: null
	                        });
	                        span = 1;
	                        PerValue = CurValue;
	                    }
	                }
	            }
	        }
			var mdrDetail = {
				panel : 'yzxxDetailPanel'
			};
			$(document).ready(function () { 
				$('#'+mdrDetail.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: false,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/st004Yzxxb/f_json/findYzxxDetail.shtml?zyId=${zyId}',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[ 
		                    {field:'orderAt',title:'开嘱时间',sortable:true,width:30},
		                    {field:'stopAt',title:'停嘱时间',sortable:true,width:30,},
		                    {field:'days',title:'天数',sortable:true,width:10,},
		                    {field:'orderName',title:'医嘱名称',sortable:true,width:30,},
		                    {field:'bdocName',title:'开嘱医生',sortable:true,width:20,},
		                    {field:'edocName',title:'停嘱医生',sortable:true,width:20,}
		                ]
	                ],	                 
		            onLoadSuccess:function(data){
		            	 MergeCells(yzxxDetailPanel.panel,'');
		            }
	            });
			});
		</script>
	</body>
</html>
