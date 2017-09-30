<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>病例预警选择树</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	 <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="overflow: hidden; height: 70px; border-bottom:1px solid #ccc;">
        	<div class="m_search"  style="display:;">
        		<div class="m_search_tab">
        			<div class="tab_l search_1_${param.type} cur" name="isInHosp_${param.type}" onclick="casesWarning.query(this, '${param.type}', '${method}', 'search_1_${param.type}');" value="1">在院</div>
        			<div class="tab_l search_1_${param.type}" id="id_isInHosp_2_${param.type}" name="isInHosp_${param.type}" onclick="casesWarning.query(this, '${param.type}', '${method}', 'search_1_${param.type}');" value="2"><span>出院</span><input type="text" class="Wdate wdate_no" id="queryDate_${param.type}" readonly="readonly"  onclick="WdatePicker({skin:'whyGreen','dateFmt':'yyyy-MM',onpicked:function(dq){casesWarning.query(this, '${param.type}', '${method}', 'date');},oncleared:function(dq){casesWarning.query(this, '${param.type}', '${method}', 'date');}})" value="${queryDate}"></div> 
        		</div>       	
	        	<div class="m_search_a">
					<a href="javascript:void(0);" id="id_dataRange_1_${param.type}" name="dataRange_${param.type}" value="1" class="search_2_${param.type} ${'1' ne isAll ? 'cur' : '' }" onclick="casesWarning.query(this, '${param.type}', '${method}', 'search_2_${param.type}');">管辖(${inNum})</a>
					<a href="javascript:void(0);" id="id_dataRange_0_${param.type}" name="dataRange_${param.type}" value="0" class="search_2_${param.type} ${'1' eq isAll ? 'cur' : '' }" onclick="casesWarning.query(this, '${param.type}', '${method}', 'search_2_${param.type}');">全部(${outNum})</a>
				</div>
				<div class="m_search_r_icon"><a href="javascript:void(0);" id="id_infectType_${param.type}" onclick="casesWarning.setInfectType(this, '${param.type}', '${method}');" value="1" title="设置社院或院感">院</a></div>
        	</div>
        	
        	<%-- <div class="m_search" style="text-align: center; display:none;">
        		<div>
        			<input type="radio" onchange="casesWarning.query(this, '${param.type}', '${method}');" id="id_isInHosp_1_${param.type}" name="isInHosp_${param.type}"  value="1" checked="checked"/>
        			<label id="lb_isInHosp_1_${param.type}" for="id_isInHosp_1_${param.type}">在院（${inNum}位）</label>&nbsp;&nbsp;&nbsp;
       				<input type="radio" onchange="casesWarning.query(this, '${param.type}', '${method}');" id="id_isInHosp_2_${param.type}" name="isInHosp_${param.type}"  value="2" />
       				<label id="lb_isInHosp_2_${param.type}" for="id_isInHosp_2_${param.type}">出院（${outNum}位）</label>
        		</div>
        		<div>
        			<label><input type="radio" onchange="casesWarning.query(this, '${param.type}', '${method}');" name="dataRange_${param.type}"  value="1" ${'1' ne isAll ? 'checked="checked"' : '' }/>管辖科室</label>&nbsp;&nbsp;&nbsp;
        			<label><input type="radio" onchange="casesWarning.query(this, '${param.type}', '${method}');" name="dataRange_${param.type}"  value="0" ${'1' eq isAll ? 'checked="checked"' : '' }/>全部科室</label>	
        		</div>
        		<div><input type="text" id="queryDate_${param.type}" readonly="readonly"  onclick="WdatePicker({'dateFmt':'yyyy-MM',onpicked:function(dq){casesWarning.query(this, '${param.type}', '${method}');},oncleared:function(dq){casesWarning.query(this, '${param.type}', '${method}');}})" style="width:68px;display: none;" value="${queryDate}"></div>
        	</div> --%>
        </div>
        <div data-options="region:'center',border:false" style="padding-left:10px;">
        	<div id="warningTreePanel_${param.type}"></div>
        </div>
    </div>
    
<script type="text/javascript">
	$(document).ready(function () {
		$('#warningTreePanel_${param.type}').tree({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        lines: false,
	        animate:true,
	        url: '${webroot}/gr002YsgrMx/f_json/${method}.shtml',
            queryParams: {
            	'dataRange': $("a[name='dataRange_${param.type}'].cur").attr('value'),
            	'isInHosp': $("div[name='isInHosp_${param.type}'].cur").attr('value'),
            	'infectTypeId': $('#id_infectType_${param.type}').attr('value')
            },
            onLoadSuccess : function (node, data) {
            	var dataRange = $("a[name='dataRange_${param.type}'].cur").attr('value');
            	if (data[0]) {
            		if (data[0].state == 'closed') {
	                	var count = 0;
	                	//console.log(data);
	                	if (data && data.length > 0) {
	                		for (var i = 0; i < data.length; i ++) {
	                			count += data[i].cldCount;
	                    	}
	                	}
	                	$('#id_dataRange_' + dataRange + '_${param.type}').html((dataRange == 1 ? ('管辖') : '全部') + '(' + count + ')'); 
            		}
            	} else {
            		$('#id_dataRange_' + dataRange + '_${param.type}').html((dataRange == 1 ? ('管辖') : '全部') + '(0)'); 
            	}
            	if (data.length > 0) {
                	//找到第一个元素
                    var n = $('#warningTreePanel_${param.type}').tree('find', data[0].id);
                    n = $('#warningTreePanel_${param.type}').tree("expand",n.target);  
                    //调用选中事件
                    var children = $('#warningTreePanel_${param.type}').tree('getChildren',n.target); 
                    /* 选中第一个子节点 */  
                    $('#warningTreePanel_${param.type}').tree("select",children[1].target);  
                  
                }
            },  
            onSelect : function(node){
            	if ($(this).tree('isLeaf', node.target)){
            		casesWarning.setWarningPatient(node.id, '${param.type}');
            	}
            },
            onClick : function(node){
            	if (!$(this).tree('isLeaf', node.target)) {
            		$(this).tree('toggle',node.target);  
            	} else {
            		casesWarning.setWarningPatient(node.id, '${param.type}');
            	}
            }
	    });

	});
</script>
</body>
</html>
