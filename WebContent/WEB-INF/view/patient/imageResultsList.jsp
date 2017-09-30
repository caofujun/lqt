<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>影像结果</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
        <div data-options="region:'north',split:false,border:false" style="height:232px;border-bottom-width: 1px;">
        	<div id="imageResultsPanel"></div>
        </div>
        <div data-options="region:'center',border:false" >
        	<div  id="id_south_panel" style="padding:10px 20px;"></div>
        </div>
    </div>
<script type="text/javascript">
	var imageResults = {
		panel : 'imageResultsPanel',
		id: '${param.id}',
		
		//设置下侧检查描述和结论
		setSouthList : function (id) {
			$.ajax({
                url: '${webroot}/st014Pacs/f_json/getResultsContent.shtml',
                type: 'post',
                data: { id: id },
                dataType: 'json',
                success : function(json) {
        			$('#id_south_panel').html(json.showAnalResult == null ? '' : '<font style="font-size:13px">' + json.showAnalResult + '</font>');
        		}
    		});

		}
	}
	
	$(document).ready(function (){
		$('#' + imageResults.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url:'${webroot}/st014Pacs/f_json/findImageResultsList.shtml?zyid=' + '${param.zyid}'+'&mzid='+'${param.mzid}',   
	        remoteSort: false,
	        singleSelect: true,	       
	        border:false,
	        idField:'id',
	        columns:[
		       	[
		            {field:'checkNo',title:'检查号',sortable:true,width:75,align:'left'},
		            {field:'checkAt',title:'检查日期 ',sortable:true,width:80,align:'center'},
		            {field:'itemName',title:'检查类型',sortable:true,width:80,align:'left'},
		            {field:'checkDr',title:'检查医生',sortable:true,width:60,align:'left'},
		            {field:'reportDr',title:'报告人',sortable:true,width:60,align:'left'},
		            {field:'reportAt',title:'报告日期',sortable:true,width:80,align:'center'},
		            {field:'deptName',title:'申请科室',sortable:true,width:100,align:'left'},
		            {field:'elementName',title:'感染因素',sortable:true,width:150,align:'left',
						styler: function(value,row,index) {
							if (row.elementName != '') { return 'color:red;';}
						}
		            },
		            {field:'checkDesc',title:'检查描述',sortable:true,width:180,align:'left'},
		            {field:'checkImpr',title:'检查结论',sortable:true,width:150,align:'left'}
		        ]
	        ],
	        rownumbers:true,
	        onClickRow : function (index, row){
	        	imageResults.setSouthList(row.id);
            },
            onLoadSuccess : function (data) {
            	if (data.rows.length > 0) {
            		if (imageResults.id.length > 0) {
		        		$('#' + imageResults.panel).datagrid('selectRecord', imageResults.id);
		        		imageResults.setSouthList(imageResults.id);
		        		imageResults.id = '';
		        	} else {
		        		$('#' + imageResults.panel).datagrid('selectRow',0);
	            		imageResults.setSouthList(data.rows[0].id);
		        	}
            	}
            }
	    });
	});
</script>
</body>
</html>
