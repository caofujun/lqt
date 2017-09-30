<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>医嘱信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="tb_docAdvice">
		<div class="m_search h_set">
			<div class="m_search_last">
				<input type="hidden" id="id_advice_zyid" value="${param.zyid}"/>		
				<input id="id_standing" type="checkbox" name="orderTypeIn" value="1" style="margin-top:-4px;"/>  
				<label for="id_standing">长嘱</label>
				<input id="id_stat" type="checkbox" name="orderTypeIn" value="0" style="margin-top:-4px;"/>  
				<label for="id_stat" class="mr10">临嘱</label>
				<span class="pro_text">开嘱日期:</span>
				<input type="text" name="queryStartDate" id="advice_queryStartDate"  class="Wdate text" style="width:85px;" onclick="WdatePicker()" />~
		    	<input type="text" name="queryEndTime" id="advice_queryEndTime"  class="Wdate text" style="width:85px;" onclick="WdatePicker()" />
				<nis:select id="id_monitoType" name="classCode" cssCls="easyui-combobox" dictcode="monitor_type" headerKey="" headerValue="--监测类型--"  />
				<input type="text" id="orderName" name="orderName" class="auto-tip" data-tip="医嘱内容" title="医嘱内容" style="width: 100px;" />
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="docAdvice.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
				</div>
				<div class="btn_r ico_zhts">
					<i class="ico_fxj"><a href="#" style="color:blue" onclick="javascript:docAdvice.openjkDicAll('003')">使用呼吸机</a></i>				
					<i class="ico_zxjmcg"><a href="#" style="color:blue" onclick="javascript:docAdvice.openjkDicAll('002')">中心静脉插管</a></i>
					<i class="ico_bndcg"><a href="#" style="color:blue" onclick="javascript:docAdvice.openjkDicAll('001')">留置导尿管</a></i>					
					<div class="mr10"><span class="fx_orange"></span><span>抗菌药物</span><!-- <a href="javascript:;" class="btn_icon" title="抗菌药物维护"><i class="icon iconfont fax">&#xe601;</i></a> --></div>
					<div class="mr10"><span class="fx_blue"></span><span><a href="#" style="color:blue" onclick="javascript:docAdvice.openjkDicAll('004')">侵袭性操作</a></span><!-- <a href="javascript:;" class="btn_icon" title="侵袭性操作"><i class="icon iconfont fax">&#xe601;</i></a> --></div>
				</div>
			</div>
		</div>		
	</div>
	<div id="docAdvicePanel"></div>
<script type="text/javascript">
	var docAdvice = {
		panel : 'docAdvicePanel',
		id : '${param.id}',
		query : function () {
			autoTip.clear();
			$('#' + docAdvice.panel).datagrid({
	            url: '${webroot}/st004Yzxxb/f_json/findDocAdviceList.shtml',
	            queryParams: {
	            	'orderTypeIns':this.getOrderTypeIns(),
	            	'orderName':$('#orderName').val(),
	            	'queryStartDate':$('#advice_queryStartDate').val(),
	            	'queryEndDate':$('#advice_queryEndTime').val() == ''?'':$('#advice_queryEndTime').val()+' 23:59:59',
	            	'classCode':$('#id_monitoType').combogrid('getValue'),
	            	'zyid':$('#id_advice_zyid').val() == null ? '' : $('#id_advice_zyid').val()
	            },
	            pageNumber : 1
	        });
		},
		//设置为监测项目
		setMonitorProject : function(orderId, orderName, id) {
			var orderIdStr = '';
			if (orderId.length > 0) {
				orderIdStr = "orderId=" + orderId;
			}
	        Comm.dialogGlobal({
	        	url:"${webroot}/st004Yzxxb/f_view/toMonitorProject.shtml?" + orderIdStr + "&id=" + id + "&orderName=" + encodeURI(encodeURI(orderName)),
	            title: "设置为监测项目",
	            width:530,
	            height:370,
	            type:"iframe",
	            parent:this
	        });
    	},
    	//设置为抗菌药物
		setAntimicrobial : function(orderId, orderName, id) {
			var orderIdStr = '';
			if (orderId.length > 0) {
				orderIdStr = "orderId=" + orderId;
			}
	        Comm.dialogGlobal({
	        	url:"${webroot}/st004Yzxxb/f_view/toAntimicrobial.shtml?" + orderIdStr + "&id=" + id + "&orderName=" + encodeURI(encodeURI(orderName)),
	            title: "设置为抗菌药物",
	            width:500,
	            height:370,
	            type:"iframe",
	            parent:this
	        });
    	},
    	getOrderTypeIns:function() {
    		var orderTypeIn = '';
    		$("input:checkbox[name='orderTypeIn']:checked").each(function(){ 
    			orderTypeIn = orderTypeIn + $(this).val() + ',';
   			});
    		return orderTypeIn;
    	},
    	openjkDicAll:function(classCode){
    		parent.parent.menuInfo.clickMenu('监测项目维护','/jkDicAll/f_view/index.shtml?classCode=' + classCode,true,'A0601');
	    }
	}

	$(document).ready(function () {
		$('#' + docAdvice.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url:'${webroot}/st004Yzxxb/f_json/findDocAdviceList.shtml?zyid=' + '${param.zyid}',   
	        remoteSort: false,
	        singleSelect: true,
	        idField:'id',
	        border:false,
	        columns:[ 
		       	[
					{field:'isKjyw',title:'抗菌药物',sortable:true,align:'center',width:35,
						formatter:function(value,rec){
							return ['<input style="width:15px;height:18px;" type="checkbox" ' + (rec.isKjyw == '1' ? 'checked="checked"' : '') + 'onclick="return false;" />'].join('');
					    }
					},
					{field:'flagJr',title:'侵袭性操作',sortable:true,align:'center',width:40,
						formatter:function(value,rec){
							return ['<input style="width:15px;height:18px;" type="checkbox" ' + (rec.flagJr == '1' ? 'checked="checked"' : '') + 'onclick="return false;" />'].join('');
					    }
					},
					{field:'orderTypeName',title:'长/临',sortable:true,align:'center',width:35},
		            {field:'orderAt',title:'开嘱时间',sortable:true,width:120},
		            {field:'stopAt',title:'停嘱时间 ',sortable:true,width:120},
		            {field:'orderName',title:'医嘱名称',sortable:true,width:160,formatter:function(value,row,index){
		            	var logo = ""
		            	if (row.classCode=='001') {
		            		logo = '<i class="ico_bndcg"></i>';
		                } else if (row.classCode=='002') {
		                	logo = '<i class="ico_zxjmcg"></i>';
		                } else if (row.classCode=='003') {
		                	logo = '<i class="ico_fxj"></i>';
		                } 
		            	return logo + value;
		            }},
		            {field:'dosage_dosageUnit',title:'单次剂量',sortable:true,align:'right',width:50,
    					formatter:function(value,row,index){
    						if(row.dosage != null){
    							return [(row.dosage+row.dosageUnit)].join('');
    						}else{
    							return [''].join('');
    						}
    					}
    				},
		            {field:'useCount',title:'数量',sortable:true,align:'center',width:30},
		            {field:'sypc',title:'使用频次',sortable:true,width:60},
		            {field:'adminRouteName',title:'给药途经',sortable:true,width:60},
		            {field:'bdocName',title:'开嘱医生',sortable:true,width:50},
		            {field:'edocName',title:'停嘱医生',sortable:true,width:50},
		            {field:'memo',title:'医嘱说明',sortable:true,width:75},
		            {field:'_operate',title:'操作',width:55,
    					formatter:function(value,row,index){
    						return [((row.flagJr == '1' ||  row.isKjyw == '1') ? '' : '<a href="javascript:void(0);" class="ico_setup" title="设置监控项目" onclick ="docAdvice.setMonitorProject(\'' + row.orderId + '\',\'' + row.orderName + '\',\'' + row.id + '\')"></a>') + 
    								((row.flagJr == '1' ||  row.isKjyw == '1') ? '' : '<a href="javascript:void(0);" class="ico_editor" title="设置抗菌药物" onclick ="docAdvice.setAntimicrobial(\'' + row.orderId + '\',\'' + row.orderName + '\',\'' + row.id + '\')"></a>')].join('');
    					}
    				}
		        ]
	        ],
	        pagination:true,
	        rownumbers:true,
	        toolbar:'#tb_docAdvice',
	        pageSize:200,
	        pageList:[100,200,300,400,500],
	        rowStyler:function(rowIndex,rowData){
                if (rowData && rowData.isKjyw && (rowData.isKjyw=='1')) {  
                    return 'color:#FF4200;';
                } else if (rowData && rowData.flagJr && (rowData.flagJr=='1')) {
                	return 'color:#cb0374;';
                } 
            },
	        onLoadSuccess : function (data) {
                if (docAdvice.id.length > 0) {
	        		$('#' + docAdvice.panel).datagrid('selectRecord', docAdvice.id);
	        		docAdvice.id = '';
	        	}
            }
	    });
	});
</script>
</body>
</html>
