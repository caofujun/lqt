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
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
        <div data-options="region:'center',border:false">
        	<div id="st009SjbbListPanel"></div>
        </div>
        <div data-options="region:'east',split:false,border:false" style="width:550px;">
        	<iframe id="jyDetailIframe" src="" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
<!--         	<div class="easyui-layout" data-options="fit:true"> -->
<!-- 		        <div id="st011Panel" class="easyui-panel" data-options="region:'north',split:true"  style="height:200px;width:550px;border-width: 0 1px 1px 1px;"> -->
<!-- 		            <div id="st011SyjgbListPanel"></div> -->
<!-- 		        </div> -->
<!-- 		        <div data-options="region:'center'" style="border-bottom-width: 0;"> -->
<!-- 		        	<div id="st010JcbytListPanel"></div> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
        </div>
    </div>
<script type="text/javascript">
	var checkInfo = {
		panel : 'st009SjbbListPanel',
		rPanel1 : 'st011SyjgbListPanel',
		rPanel2 : 'st010JcbytListPanel',
		resultHigh : new Array(),
		resultLow : new Array(),
		id : '${param.id}',
		setUrl : function (zyid,mzid, testOrderNo,itemType){
			var url="${webroot}/st009Sjbb/f_view/jyDetail.shtml?zyid="+zyid+"&mzid="+mzid+"&testOrderNo="+testOrderNo+"&itemType="+itemType;
		    $("#jyDetailIframe").attr("src",url);
		},
		
		//设置右侧病程内容
// 		setRigthList : function (zyid,mzid, testOrderNo) {
// 			$('#' + checkInfo.rPanel1).datagrid({
// 	            url: '${webroot}/st010Jcbyt/f_json/findSt010JcbytList.shtml',
// 	            queryParams: {
// 	            	'zyid':zyid,
// 	            	'mzid':mzid,
// 	            	'testOrderNo':testOrderNo
// 	            },
// 	            onLoadSuccess : function (data) {
// 	            	if (data.rows.length > 0) {
// 	         			//document.getElementById("st011Panel").style.height="200px";
// 	            		//$("#st011Panel").css("display", "block");
// 	            		$("#st011Panel").css("height", "200px"); 
// 	            		//$("#st011Panel").show();  
// 	            		$('#st011Panel').panel('open');
	            	   
// 	            		// document.getElementById("st011Panel").style.display="block";
// 	            	}else{
// 	            		$("#st011Panel" ).css("height", "0px"); 
// 	            		$('#st011Panel').panel('close');
// 	            		//$("#st011Panel").hide();  
// // 	            		$("#st011Panel" ).css("display", "none"); 
// 	            		//document.getElementById("st011Panel").style.display="none";
// 	         			//document.getElementById("st011Panel").style.height="0px";
// 	            	}
// 	            }
// 	        });
// 			$('#' + checkInfo.rPanel2).datagrid({
// 	            url: '${webroot}/st011Syjgb/f_json/findSt011SyjgbList.shtml',
// 	            queryParams: {
// 	            	'zyid':zyid,
// 	            	'mzid':mzid,
// 	            	'testOrderNo':testOrderNo
// 	            }
// 	        });
//		},
// 		setFooterList : function (zyid,mzid, testOrderNo,pathogenSn){
		
// 			$('#' + checkInfo.rPanel2).datagrid({
// 	            url: '${webroot}/st011Syjgb/f_json/findSt011SyjgbList.shtml',
// 	            queryParams: {
// 	            	'zyid':zyid,
// 	            	'mzid':mzid,
// 	            	'pathogenSn':pathogenSn,
// 	            	'testOrderNo':testOrderNo
// 	            }
// 	        });
// 		}
	}
	
	$(document).ready(function () {
// 		var iscg = false;
		checkInfo.resultHigh = '${resultHigh}'.split(',');
		checkInfo.resultLow = '${resultLow}'.split(',');
		checkInfo.mdr = 'R,耐药,阳性'.split(',');
		$('#' + checkInfo.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url:'${webroot}/st009Sjbb/f_json/findSt009SjbbList.shtml?zyid=' + '${param.zyid}' + '&mzid=${param.mzid}&id=' + checkInfo.id,   
	        remoteSort: false,
	        singleSelect: true,	       
	        border:false,
	        idField:'id',
	        columns:[
		       	[
		            {field:'submiAt',title:'送检日期',sortable:true,width:80},
		            {field:'itemName',title:'标本 ',sortable:true,width:50},
		            {field:'itemTypeNameDesc',title:'检验项目',sortable:true,width:240,
    					formatter:function(value,row,index){
    						if(row.isexception > 0 || row.isyang > 0){
    							return [(row.itemTypeName+'<span class="r_red"></span>')].join('');
    						}else{
        						return [(row.itemTypeName)].join('');
    						}
    					}
    				},
		            {field:'checkOutAt',title:'检出日期',sortable:true,width:80},
		            {field:'testOrderNo',title:'检验单号',sortable:true,width:140}
		        ]
	        ],
	       
	        pagination:true,
	        rownumbers:true,
	        pageSize:100,
	        pageList:[100,200,300,400,500], 
	        onClickRow : function (index, row){
	        	checkInfo.setUrl(row.zyid,row.mzid, row.testOrderNo,  row.itemType);
            },
            onLoadSuccess : function (data) {
            	if (data.rows.length > 0) {
                	$('#'+checkInfo.panel).datagrid('selectRow', 0);
            		checkInfo.setUrl(data.rows[0].zyid,data.rows[0].mzid, data.rows[0].testOrderNo ,data.rows[0].itemType);
            		if (checkInfo.id.length > 0) {
		        		$('#' + checkInfo.panel).datagrid('selectRecord', checkInfo.id);
		        		checkInfo.id = '';
		        	}
            	}
            }
	    });
		
		
	});
</script>
</body>
</html>
