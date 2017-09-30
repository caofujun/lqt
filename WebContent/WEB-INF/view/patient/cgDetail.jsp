<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>检验信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
    	<div id="st010JcbytListPanel"></div>
</body>
<script type="text/javascript">
	var checkInfo = {
			rPanel2 : 'st010JcbytListPanel',	
	};
	$(document).ready(function () {
		checkInfo.resultHigh = '${resultHigh}'.split(',');
		checkInfo.resultLow = '${resultLow}'.split(',');
		checkInfo.mdr = 'R,耐药'.split(',');
		$('#' + checkInfo.rPanel2).datagrid({
            url: '${webroot}/st011Syjgb/f_json/findSt011SyjgbList.shtml',
            queryParams: {
            	'zyid':'${zyid}',
            	'mzid':'${mzid}',
            	'testOrderNo':'${testOrderNo}'
            }
   		 });
		
		$('#'+checkInfo.rPanel2).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        remoteSort: false,
	        singleSelect: true,	        
	        border:false,
	        columns:[
		       	[
		            {field:'antiName',title:'项目名称',sortable:true,width:180},
		           	{field:'yaominResult',title:'药敏结果 ',sortable:true,width:60},
		            {field:'testResult',title:'试验结果',align:'right',sortable:true,width:60},
		            {field:'unit',title:'单位',align:'left',sortable:true,width:50},
		            {field:'remark',title:'结果',sortable:true,width:40,
						formatter:function(value,rec){
							var remarkStr = '';
							if (rec.remark) {
								if ($.inArray(rec.remark, checkInfo.resultHigh) != -1) { remarkStr = '↑';} else if ($.inArray(rec.remark, checkInfo.resultLow) != -1) {remarkStr = '↓';}
							}
							return [remarkStr].join('');
					    }
					},
		            {field:'referRange',title:'参考范围',sortable:true,width:80}
		        ]
	        ],
	        
	        rownumbers:true,
	        rowStyler:function(rowIndex,rowData){
	        	if (rowData && rowData.remark) {
	        		if ($.inArray(rowData.remark, checkInfo.resultHigh) != -1) {
	        			return 'color:red;';
	        		} else if ($.inArray(rowData.remark, checkInfo.resultLow) != -1) {
	        			return 'color:blue;';
	        		}
	        	} 
	        	if (rowData && rowData.yaominResult) {
	        		if ($.inArray(rowData.yaominResult, checkInfo.mdr) != -1) {
	        			return 'color:red;';
	        		} 
	        	}  
            },
			onLoadSuccess:function(data){ 
		        $('#' + checkInfo.rPanel2).datagrid("hideColumn", "yaominResult"); // 设置隐藏列    
		          
	       },
	    });
		
	});
</script>
</html>
		    