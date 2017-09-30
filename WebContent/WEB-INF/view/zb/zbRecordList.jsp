<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>网络直报</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
	<body>
		<div id="form1" style="display: none;">	
			<form id="zbform" method="post">
			<input type="hidden" id="channel" name="channel" value="${channel}"/>
			<input type="hidden" id="inputData" name="inputData" value=""/>
			</form>
		</div>
		<div id="tb" class="m_search" style="display: none;">			
			<span>上报期限:</span>	
			<select id="reportStartYear" class="easyui-combobox" style="width: 60px;" data-options="editable:false,onChange:function(newValue,oldValue){zbRecord.query();}">
				<c:forEach items="${years}" var="year">
					<option value="${year}">${year}</option>
				</c:forEach>
			</select>
			<select id="reportStartMonth" class="easyui-combobox" style="width: 40px;" data-options="editable:false,onChange:function(newValue,oldValue){zbRecord.query();}">
				<c:forEach items="${months}" var="month">
					<option value="${month}" <c:if test="${month == 1}">selected</c:if>>${month}</option>
				</c:forEach>
			</select>
			 ~ 
			<select id="reportEndYear" class="easyui-combobox" style="width: 60px;" data-options="editable:false,onChange:function(newValue,oldValue){zbRecord.query();}">
				<c:forEach items="${years}" var="year">
					<option value="${year}">${year}</option>
				</c:forEach>
			</select>
			<select id="reportEndMonth" class="easyui-combobox" style="width: 40px;" data-options="editable:false,onChange:function(newValue,oldValue){zbRecord.query();}">
				<c:forEach items="${months}" var="month">
					<option value="${month}" <c:if test="${month == curMonth-1}">selected</c:if>>${month}</option>
				</c:forEach>
			</select>
			<div class="btn_r">
				<span style="color:red">上报前请确保本机能连接互联网！</span>
				<div class="n_btn_grey">
					<a href="javascript:void(0);" onclick="zbRecord.report();"><i class="icon iconfont">&#xe602;</i><span>网络直报</span></a>
				</div>
			</div>
		</div>
		<div id="zbRecordPanel"></div>
		<div id="waiting"></div>
		
		</div>
<script type="text/javascript">
var zbRecord = {
	panel : 'zbRecordPanel',
	reportCount : 0,
	yibao : 0,
	itemName : '',
	isEnd : 0,
	listLength : 0,
	str : '',
	isContinue : false,
	itemCodesTemp : new Array(),
	itemCodesZbTemp : new Array(),
	idsDates : new Array(),
	count : 0,
	itemCodesZbTempValue : '',
	idsDatesValue : '', 
	inputDatas : new Array(),
	reportSizes : new Array(),
	unReports : new Array(),
	query : function() {
		zbRecord.unReports= [];
		$('#'+zbRecord.panel).datagrid({
            url:'${webroot}/zbRecord/f_json/pageQuery.shtml',
            queryParams: {
            	'reportStartYear':$('#reportStartYear').combobox('getValue'),
            	'reportStartMonth':$('#reportStartMonth').combobox('getValue'),
            	'reportEndYear':$('#reportEndYear').combobox('getValue'),
            	'reportEndMonth':$('#reportEndMonth').combobox('getValue'),
            }
		});
	},
	//网络直报日志
	reportLog : function(itemCode,itemName) {
    	var reportStartYear = $('#reportStartYear').combobox('getValue');
    	var reportStartMonth = $('#reportStartMonth').combobox('getValue');
    	var reportEndYear = $('#reportEndYear').combobox('getValue');
    	var reportEndMonth = $('#reportEndMonth').combobox('getValue');
		Comm.dialogGlobal({
        	url:"${webroot}/zbRecord/f_view/zbLogList.shtml?itemCode="+itemCode+"&reportStartYear="+reportStartYear+"&reportStartMonth="+reportStartMonth+"&reportEndYear="+reportEndYear+"&reportEndMonth="+reportEndMonth+"",
            title: itemName + '上报日志',
            width:550,
            height:500,
            type:"iframe",
            parent:this
        });
	},

    //待报详情
    unReportList : function(itemCode) {
    	var reportStartYear = $('#reportStartYear').combobox('getValue');
    	var reportStartMonth = $('#reportStartMonth').combobox('getValue');
    	var reportEndYear = $('#reportEndYear').combobox('getValue');
    	var reportEndMonth = $('#reportEndMonth').combobox('getValue');
		Comm.dialogGlobal({
        	url:"${webroot}/zbRecord/f_view/zbLogList.shtml?itemCode="+itemCode+"&reportStartYear="+reportStartYear+"&reportStartMonth="+reportStartMonth+"&reportEndYear="+reportEndYear+"&reportEndMonth="+reportEndMonth+"",
            title: '上报日志',
            width:550,
            height:500,
            type:"iframe",
            parent:this
        });
    },
    //已报详情
    reportedList : function(itemCode) {
    	var reportStartYear = $('#reportStartYear').combobox('getValue');
    	var reportStartMonth = $('#reportStartMonth').combobox('getValue');
    	var reportEndYear = $('#reportEndYear').combobox('getValue');
    	var reportEndMonth = $('#reportEndMonth').combobox('getValue');
		Comm.dialogGlobal({
        	url:"${webroot}/zbRecord/f_view/zbLogList.shtml?itemCode="+itemCode+"&reportStartYear="+reportStartYear+"&reportStartMonth="+reportStartMonth+"&reportEndYear="+reportEndYear+"&reportEndMonth="+reportEndMonth+"",
            title: '上报日志',
            width:550,
            height:500,
            type:"iframe",
            parent:this
        });
    },
    //进度条页面
    jindutiao : function() {
		Comm.dialog({
        	url:"${webroot}/zbRecord/f_view/jdtshow.shtml",
            title: '直报进度',
            width:400,
            height:250
        });
    },
    report:function() {
    	zbRecord.itemCodesTemp = [];
    	var itemCodes =[]; 
    	itemCodes.push('0');
    	$('input[name="itemCode"]:checked').each(function(){
    		itemCodes.push($(this).val()); 
    	}); 
    	if(itemCodes.length == 1){
    		alert('你还没有选择需要上报的类型！'); 
    		return;
    	}
    	zbRecord.listLength = itemCodes.length;
    	$.messager.confirm('提示', '确认要上报选择的记录吗?', function (r) {
        	if (!r) {
        		return ;
        	}
        	zbRecord.jindutiao();
        	//按类型依次处理上报业务
// 		        $.messager.progress({ // 显示进度条  
// 		       		title:"院感直报",  
// 	    	   		text:"正在处理,请耐心等待...",  
// 	    	    	interval:100  
// 		    	});
            	var reportStartYear = $('#reportStartYear').combobox('getValue');
            	var reportStartMonth = $('#reportStartMonth').combobox('getValue');
            	var reportEndYear = $('#reportEndYear').combobox('getValue');
            	var reportEndMonth = $('#reportEndMonth').combobox('getValue');
        	for(var i=0;i<itemCodes.length;i++){
            	zbRecord.reportData(itemCodes[i],reportStartYear,reportStartMonth,reportEndYear,reportEndMonth);
        	}
				
    	});
    },
    
    reportData:function(itemCode,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth) {
    	//上报条数小于100
    	if(zbRecord.isContinue == false){
	    	zbRecord.itemCodesTemp.push(itemCode);
	    	if(zbRecord.itemCodesTemp.length==zbRecord.listLength){
		    	for (var i = 0; i < zbRecord.listLength; i++) {
		    	//获取直报数据
					$.ajax({
				        url: '${webroot}/zbRecord/f_json/findReportDataByItem.shtml',
			            type: 'post',
			            async: false,
			            data: { itemCode: zbRecord.itemCodesTemp[i], reportStartYear: reportStartYear, reportStartMonth: reportStartMonth, reportEndYear: reportEndYear, reportEndMonth: reportEndMonth},
			            dataType: 'json',
			            success : function(json) {
			            	if(json[0] != '0'){
			            		//调用直报接口
			                	zbRecord.zb(zbRecord.itemCodesTemp[i],reportStartYear,reportStartMonth,reportEndYear,reportEndMonth,json[0],json[1],json[2]);
			            	}else{
			            		zbRecord.count++;
			            		zbRecord.isEnd++;
			            		zbRecord.zb(zbRecord.itemCodesTemp[i],reportStartYear,reportStartMonth,reportEndYear,reportEndMonth,json[0],json[1],json[2]);
			            	}
						},  
					    error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.messager.alert('提示','提取直报数据异常！！','error');  
					    }
					 });
				}
	    	}
	    //上报条数大于100
    	}else{
    		$.ajax({
		        url: '${webroot}/zbRecord/f_json/findReportDataByItem.shtml',
	            type: 'post',
	            async: false,
	            data: { itemCode: itemCode, reportStartYear: reportStartYear, reportStartMonth: reportStartMonth, reportEndYear: reportEndYear, reportEndMonth: reportEndMonth},
	            dataType: 'json',
	            success : function(json) {
	            	if(json[0] != '0'){
	            		//调用直报接口
	                	zbRecord.zb(itemCode,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth,json[0],json[1],json[2]);
	            	}else {
	            		zbRecord.yibao = 0;
	            		zbRecord.isEnd++;
	            	}
				},  
			    error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.alert('提示','提取直报数据异常！！','error');  
			    }
    		 });
    	}
    },
    
    
   tijiao : function(itemCodesZbTemp,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth,reportSizes,idsDates,inputDatas){
	   $("#inputData").val(inputDatas[0]);
    	 if(inputDatas.length > 0 && reportSizes.length > 0){
	    	$('#zbform').form({
			    url: '${zbURL}',
				success : function(data) {
		    	//直报结果回写
		    	zbRecord.yibao = 0;
	    		zbRecord.zbResult(itemCodesZbTemp[0],reportStartYear,reportStartMonth,reportEndYear,reportEndMonth,reportSizes[0],idsDates[0]);
				},  
				onLoadError:function(result){  
					zbRecord.itemCodesTemp = [];
					zbRecord.itemCodesZbTemp =[];
	             	$.messager.progress('close');
	             	rateProgrss.close();
	             	$.messager.alert('直报网络异常！');
				}
			});
			$('#zbform').submit();
    	 }else{
    		 rateProgrss.close();
    		 $.messager.show({ title: '提示', msg: '直报失败！请勾选待报数不为空的项！' });
// 	        	$.messager.progress('close');
	        	zbRecord.query();
	        	zbRecord.itemCodesTemp = [];
	        	zbRecord.itemCodesZbTemp =[];
	        	zbRecord.reportSizes = [];
	        	zbRecord.idsDates = [];
	        	zbRecord.inputDatas = [];
				zbRecord.isEnd=0;
				zbRecord.count=0;
    	 }
    },
    
    zb:function(itemCode,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth,reportSize,idData,inputData) {
    	if(zbRecord.isContinue == false){
			 if(reportSize != 0){
			 	zbRecord.itemCodesZbTemp.push(itemCode);
		 		zbRecord.idsDates.push(idData);
		 		zbRecord.inputDatas.push(inputData);
		 		zbRecord.reportSizes.push(reportSize);
			 }
			if(zbRecord.itemCodesZbTemp.length ==(zbRecord.listLength-zbRecord.count)){
				zbRecord.tijiao(zbRecord.itemCodesZbTemp,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth,zbRecord.reportSizes,zbRecord.idsDates,zbRecord.inputDatas);
			}
		 }else{
			$("#inputData").val(inputData);
		    	$('#zbform').form({
				    url: '${zbURL}',
					success : function(json) {
				    	//直报结果回写
			    			zbRecord.zbResult(itemCode,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth,reportSize,idData);
				    },  
					onLoadError:function(result){  
						zbRecord.itemCodesTemp = [];
						zbRecord.itemCodesZbTemp =[];
// 		             	$.messager.progress('close');
						rateProgrss.close();
		             	$.messager.alert('直报网络异常！');
					}
				});
				$('#zbform').submit();
		 }
    },
    
    zbResult:function(itemCode,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth,reportSize,reportResult) {
    	//会写直报结果
    	$.ajax({
	        url: '${webroot}/zbRecord/f_json/zbResult.shtml',
            type: 'post',
            data: { itemCode: itemCode,reportResult: reportResult,
            	reportStartYear : reportStartYear,
            	reportStartMonth : reportStartMonth,
            	reportEndYear : reportEndYear,
            	reportEndMonth : reportEndMonth,	
            },
            async: false, 
            success : function(json) {
            	//每次处理100条，只到此类业务处理完毕
            	if(reportSize == 100){
            		zbRecord.isContinue = true;
            		//更新进度条
             	    zbRecord.itemName = json;
                    zbRecord.yibao += parseInt(reportSize);
                	zbRecord.reportData(itemCode,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth);
            	}else{
            		//更新进度条
             	    zbRecord.itemName = json;
                    zbRecord.yibao += parseInt(reportSize);
					zbRecord.isEnd ++;
					zbRecord.isContinue = false;
					if(parseInt(zbRecord.isEnd) == parseInt(zbRecord.listLength)){
    		        	$.messager.show({ title: '提示', msg: '直报成功！' });
    		        	zbRecord.yibao = 0;
//     		        	$.messager.progress('close');
						rateProgrss.close();
    		        	zbRecord.query();
    		        	zbRecord.itemCodesTemp = [];
    		        	zbRecord.itemCodesZbTemp =[];
    		        	zbRecord.reportSizes = [];
    		        	zbRecord.idsDates = [];
    		        	zbRecord.inputDatas = [];
						zbRecord.isEnd=0;
						zbRecord.count=0;
    				}else{
    					zbRecord.reportSizes.splice(0,1);
    					zbRecord.itemCodesZbTemp.splice(0,1);
    					zbRecord.inputDatas.splice(0,1);
    		    		zbRecord.idsDates.splice(0,1);
	            		zbRecord.tijiao(zbRecord.itemCodesZbTemp,reportStartYear,reportStartMonth,reportEndYear,reportEndMonth,zbRecord.reportSizes,zbRecord.idsDates,zbRecord.inputDatas);
    				}
            	}
			},  
		    error : function(XMLHttpRequest, textStatus, errorThrown) {
		    	zbRecord.itemCodesTemp = [];
             	$.messager.progress('close');
             	rateProgrss.close();
				$.messager.alert('提示','回写上报结果异常！！','error');  
		    }  
		});
    }
};
$(document).ready(function () {
	
	$('#' + zbRecord.panel).datagrid({
		fit: true,
        autoRowHeight: true,
        striped: true,
        remoteSort: false,
        singleSelect: true,
        border:false,
        url: '${webroot}/zbRecord/f_json/pageQuery.shtml',
        columns:[ 
	       	[
				{field:'itemCode',title:'监测ID',sortable:true,width:200,hidden:'true'},
	            {field:'itemName',title:'监测项目',sortable:true,width:200,align:'center'},
	            {field:'unReportAmount',title:'待报',sortable:true,width:80,align:'center'},
	           	{field:'reportAmount',title:'已报',sortable:true,width:80,align:'center'},
	           	{field:'reportLog',title:'上报日志',sortable:true,width:80,align:'center',
	           		formatter:function(value,rec){
						return ['<a href="javascript:zbRecord.reportLog(\'' + rec.itemCode + '\', \''  + rec.itemName+ '\')" title="查看日志">日志</a>'].join('');
					}
	           	},
	            {field:'ck',title:'选择',width:50,align:'center',
					formatter:function(value,rec,index){
						return ['<input style="width:15px;height:18px;" name="itemCode" value="',rec.itemCode,'" type="checkbox" ',' />'].join('');
				    }
	            }
	        ]
        ],
        rowStyler: function (index, row) {
            if (row.itemCode == '0') {//条件  
                    return 'display:none';
             }
          },
       
        onLoadSuccess: function() {
	      	var rows = $('#' + zbRecord.panel).datagrid('getRows');//获取当前页的数据行  
		    for (var i = 0; i < rows.length; i++) {
		    	map.key1 = rows[i]['itemName']; //获取指定列  
		        map.key2 = rows[i]['unReportAmount']; //获取指定列  
		        zbRecord.unReports.push(JSON.stringify(map));
		    }  
        },
//           rownumbers:true,
          toolbar:'#tb'
	});
});
var map = {
	key1:'',
	key2:'',
}; 
</script>
	</body>
</html>