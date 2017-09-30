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
<div class="easyui-layout" data-options="fit:true">
     <div data-options="region:'north',split:true" style="height:200px;border-width: 0 1px 1px 1px;">
         <div id="st011SyjgbListPanel"></div>
     </div>
     <div data-options="region:'center'" style="border-bottom-width: 0;">
     	<div id="st010JcbytListPanel"></div>
     </div>
 </div>
</body>
<script type="text/javascript">

	
	var checkInfo = {
	 		rPanel1 : 'st011SyjgbListPanel',
	 		rPanel2 : 'st010JcbytListPanel',
	 		setFooterList : function (zyid,mzid, testOrderNo,pathogenSn){
				$('#' + checkInfo.rPanel2).datagrid({
		            url: '${webroot}/st011Syjgb/f_json/findSt011SyjgbList.shtml',
		            queryParams: {
		            	'zyid':zyid,
		            	'mzid':mzid,
		            	'pathogenSn':pathogenSn,
		            	'testOrderNo':testOrderNo
		            }
		        });
			}
	};
	
	$(document).ready(function () {
		checkInfo.resultHigh = '${resultHigh}'.split(',');
		checkInfo.resultLow = '${resultLow}'.split(',');
		checkInfo.mdr = '${strNyDict}'.split(',');
		$('#' + checkInfo.rPanel1).datagrid({
	            url: '${webroot}/st010Jcbyt/f_json/findSt010JcbytList.shtml',
	            queryParams: {
	            	'zyid':'${zyid}',
	            	'mzid':'${mzid}',
	            	'testOrderNo':'${testOrderNo}'
	            }
	           
		});
		$('#' + checkInfo.rPanel2).datagrid({
	            url: '${webroot}/st011Syjgb/f_json/findSt011SyjgbList.shtml',
	            queryParams: {
	            	'zyid':'${zyid}',
	            	'mzid':'${mzid}',
	            	'testOrderNo':'${testOrderNo}'
	            }
	    });
		$('#' + checkInfo.rPanel1).datagrid({
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
		            {field:'pathoName',title:'检出病原体',sortable:true,width:230},
		            {field:'bacteriaAmount',title:'菌量 ',sortable:true,width:50},
		            {field:'resPropName',title:'多耐结果',sortable:true,width:60},
		            {field:'specDescribes',title:'特殊耐药',sortable:true,width:160},
		            {field:'esbl',title:'ESBL'}
		        ]
	        ],
	        rownumbers:true,
	        onClickRow : function (index, row){
	        	checkInfo.setFooterList(row.zyid,row.mzid, row.testOrderNo,row.pathogenSn);
            },
	        onLoadSuccess : function (data) {
            	if (data.rows.length > 0) {
            		$("#st011Panel").css("height", "200px"); 
            		$('#st011Panel').panel('open');
                	$('#'+checkInfo.rPanel1).datagrid('selectRow', 0);
                	checkInfo.setFooterList(data.rows[0].zyid,data.rows[0].mzid, data.rows[0].testOrderNo,data.rows[0].pathogenSn);    	
            	}else{
            		$("#st011Panel" ).css("height", "0px"); 
            		$('#st011Panel').panel('close');
            	}
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
		           	{field:'yaominResult',title:'药敏结果',sortable:true,width:60},
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
	        			if(rowData.yaominResult == 'R' || rowData.yaominResult == '耐药'){
	        				return 'color:red;';
	        			}else if(rowData.yaominResult == 'I' || rowData.yaominResult == '中介'){
	        				return 'color:#3db836;';
	        			}
	        		} 
	        	} 
            },
	    });
		
	});
</script>
</html>
		    