<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="mdrDetailPanel"></div>
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
				panel : 'mdrDetailPanel'
			};
			$(document).ready(function () { 
				$('#'+mdrDetail.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: false,
	                fitColumns: true,
	                collapsible:true,
	                url:'${webroot}/xn011Dclymx/f_json/pageQueryMdrDetail.shtml?testOrderNo=${testOrderNo}&pathogenSn=${pathogenSn}',   
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[ 
		                    {field:'drugTypeName',title:'抗菌药物分类',sortable:true,width:40},
		                    {field:'drugName',title:'抗菌药物名称',sortable:true,width:40,
		                    	styler:function(value, rowData, index){
		   	                	 if (rowData && rowData.yaominResult) {
		   				        		if (rowData.yaominResult == 'R'|| rowData.yaominResult == '耐药') {
		   				        			return 'background-color:#ffe5e5;';
		   				        		}
		   				        		else if(rowData.yaominResult == 'I'|| rowData.yaominResult == '中介') {
		   				        			return 'background-color:#e1f3e4;';
		   				        		} 
		   				         }  
		   	                 	}	
		                    },
		                    {field:'testResult',title:'定量',sortable:true,width:20,
		                    	styler:function(value, rowData, index){
			   	                	 if (rowData && rowData.yaominResult) {
			   				        		if (rowData.yaominResult == 'R'|| rowData.yaominResult == '耐药') {
			   				        			return 'background-color:#ffe5e5;';
			   				        		} 
			   				        		else if(rowData.yaominResult == 'I'|| rowData.yaominResult == '中介') {
			   				        			return 'background-color:#e1f3e4;';
			   				        		} 
			   				         }  
			   	                 	}	
		                    },
		                    {field:'yaominResult',title:'定性',sortable:true,width:20,
		                    	styler:function(value, rowData, index){
			   	                	 if (rowData && rowData.yaominResult) {
			   				        		if (rowData.yaominResult == 'R') {
			   				        			return 'background-color:#ffe5e5;';
			   				        		} 
			   				        		else if(rowData.yaominResult == 'I') {
			   				        			return 'background-color:#e1f3e4;';
			   				        		} 
			   				         }  
			   	                 	}	
		                    },
		                    {field:'gn',title:'固耐',sortable:true,width:20,
		                    	styler:function(value, rowData, index){
			   	                	 if (rowData && rowData.yaominResult) {
			   				        		if (rowData.yaominResult == 'R') {
			   				        			return 'background-color:#ffe5e5;';
			   				        		} 
			   				        		else if(rowData.yaominResult == 'I') {
			   				        			return 'background-color:#e1f3e4;';
			   				        		} 
			   				         }  
			   	                 },formatter:function(value,row,index){
			   	                	 if(value>0){
			   	                		 return "是";
			   	                	 }else{
			   	                		 return "";
			   	                	 }
			   	                 }
		                    }
		                ]
	                ],	                 
	                 /* rowStyler:function(rowIndex,rowData){
			        	if (rowData && rowData.yaominResult) {
			        		if (rowData.yaominResult == 'R') {
			        			return 'background-color:#ffe5e5;';
			        		} 
			        	}  
		            },  */
		            onLoadSuccess:function(data){
		            	 MergeCells(mdrDetail.panel,'drugTypeName');
		            }
	            });
			});
		</script>
	</body>
</html>
