<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>送检标本信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/monitor/reportCards.js?${version}"></script>
<style>
	.messager-button .l-btn{pading:3px 10px;width:auto;min-width: 80px;_min-width: 80px;}
</style>
</head>
<body>
<form method="post" id="sjbbForm">
	<input type="hidden" name="regId" value="${param.regId}"/>
		<div id="button_div" class="footer" border="false" style="display:none;">			
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#sjbbForm').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
				</div>			
			</div>	
		</div>
		<div style="width:100%;height:300px;overflow: hidden;">
			<div id="id_samples_0">
			</div>	
		</div>		
</form>
<script type="text/javascript">
	var emptyDGview = $.extend({},$.fn.datagrid.defaults.view,{
	    onAfterRender:function(target){
	        $.fn.datagrid.defaults.view.onAfterRender.call(this,target);
	        var opts = $(target).datagrid('options');
	        var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
	        vc.children('div.datagrid-empty').remove();
	        if (!$(target).datagrid('getRows').length){
	            var d = $('<div class="datagrid-empty"  style="color:#dddddd;"></div>').html(opts.emptyMsg || 'no records').appendTo(vc);
	            d.css({
	                position:'absolute',
	                left:0,
	                top:50,
	                width:'100%',
	                textAlign:'center'
	            });
	        }
	    }
	});
	$(document).ready(function () {
		$('#id_samples_0').datagrid({
            fit: false,
            nowrap: true,
            autoRowHeight: true,
            striped: true,
            url:'${webroot}/st009Sjbb/f_json/findSjbbForInfectList.shtml?zyid=${param.zyid}',
            remoteSort: false,
            singleSelect: true,
            height:305,
            columns:[
            	[
					{field:'testOrderNos',align:'center',width:25,
						formatter:function(value,rec){
							return ['<input class="checkbox_list" name="testOrderNo" data="' + rec.testOrderNo + '" type="checkbox" ' + (rec.refid != null ? 'checked="checked"' : '') + ' value="' + rec.testOrderNo + '" onclick="pathogens.checkTestOrderNo(this, \'0\',\'' + rec.testOrderNo + '\');" />'].join('');
					    }
					},
                 	{field:'submiAt',title:'送检时间',sortable:true,width:80},
                    {field:'itemName',title:'标本名称',sortable:true,width:100},  
                    {field:'testDate',title:'检出时间',sortable:true,width:80},
                    {field:'pathoCode',title:'检出细菌CODE',hidden:true},
                    {field:'pathoName',title:'检出细菌',sortable:true,width:120},
                    {field:'resPropName',title:'耐药情况',sortable:true,width:70},
                    {field:'specDescribes',title:'特殊耐药',sortable:true,width:80},
                    {field:'testOrderNo',title:'检验单号',sortable:true,width:170},
                    {field:'X',title:'操作',formatter:function(value,r,index){
                    	return '<a href="#" class="easyui-tooltip" id="id_tooltip_' + r.testOrderNo + '_'+ r.pathoCode+'">&nbsp;操作&nbsp;<span class="arrow_down"></span></a>';
                    }}
                ]
            ],
            rowStyler : function(index,row){
            	if ("${param.sjId}".indexOf(row.testOrderNo)>-1){   
                    return 'background-color:#a3ddf1';   
                } 
            },
            pagination:false,
            view: emptyDGview,
            emptyMsg:"无数据！",
            rownumbers:false,
            onLoadSuccess: function(data) {
            	for (var i = 0; i < data.rows.length; i++) {
        			var r = data.rows[i];
        			addToolTip('id_tooltip_' + r.testOrderNo + '_'+ r.pathoCode , r.testOrderNo, r.testDate , '${param.zyid}' , r.surveyDeptId ,  r.orderno);
            	}
            }
        });
		
		Comm.form({
			id : 'sjbbForm',
			url : '${webroot}/gr002YsgrMx/f_json/dingzhi.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({ title : '提示', msg : '操作成功！' });
					var parentObject = parent.Comm.getObjectCache();
					parentObject.query();
					parent.Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : '操作失败！' });
				} else {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	});
	
	function addToolTip(divId, testOrderNo,dt,zyid,surveyDeptId,orderno){
		var tmpDiv = '<ul class="down_li">';
		tmpDiv += '<li><a href="#" onclick="javascript:intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'2\',\'社感\');">社感</a></li>';
		tmpDiv += '<li><a href="#" onclick="javascript:intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'3\',\'定植\');">定植</a></li>';
		tmpDiv += '<li><a href="#" onclick="javascript:intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'4\',\'污染\');">污染</a></li>';
		tmpDiv += '</ul>';
		
		$('#' + divId).tooltip({
            content: $(tmpDiv),
            showEvent: 'click',
            onShow: function(){
                var t = $(this);
                t.tooltip('tip').unbind().bind('mouseenter', function(){
                    t.tooltip('show');
                }).bind('mouseleave', function(){
                    t.tooltip('hide');
                });
            }
        });
	}
	function intervOpts(testOrderNo,dt,zyid,surveyDeptId,orderno,infectTypeId,infectTypeName){
    	if(!infectTypeId || !dt || !zyid || !surveyDeptId || !orderno){
    		$.messager.show({title : '提示',msg : '必要参数获取失败！'});
    	}else if(infectTypeId==0){
    		$.messager.defaults = { ok: "确定", cancel: "取消" ,width:300};
    		$.messager.confirm("操作提示", "确认撤销感染类型 ？", function (data) {  
                if (data) {
                	$.ajax({
                    	url: '${webroot}/xn011Dclymx/f_json/updateGrlx.shtml',
                        type: 'POST',
                        data: { 
                        	dt: dt,
                        	zyid:zyid,
                        	surveyDeptId:surveyDeptId,
                        	orderno:orderno,
                        	infectTypeId:infectTypeId,
                        	infectTypeName:infectTypeName 
						},
                        dataType: 'json',
                        success : function(json) {
                        	//json = eval("("+json+")");
                        	$.messager.show({title : '提示',msg : json.msg});
                        	//viewMdr.query();
    					}
                    });
                }
    		});
    	}else if(infectTypeId>2){
    		//不是院感、社感
    		$.messager.defaults = { ok: "确定", cancel: "取消" ,width:300};
    		$.messager.confirm("操作提示", "确认此细菌为 "+infectTypeName+"？", function (data) {  
                if (data) {  
                    $.ajax({
                    	url: '${webroot}/xn011Dclymx/f_json/updateGrlx.shtml',
                        type: 'POST',
                        data: { 
                        	dt: dt,
                        	zyid:zyid,
                        	surveyDeptId:surveyDeptId,
                        	orderno:orderno,
                        	infectTypeId:infectTypeId,
                        	infectTypeName:infectTypeName
						},
                        dataType: 'json',
                        success : function(json) {
                        	//json = eval("("+json+")");
                        	$.messager.show({title : '提示',msg : json.msg});
                        	$('#sjbbForm').submit();
                        	//viewMdr.query();
    					}
                    });
                }  
            });  
    	}else if(infectTypeId<=2){
    		//院感、社感
    		$.messager.defaults = { ok: "标记为"+infectTypeName+"致病菌,并提醒临床", cancel: "代报致病菌"+infectTypeName+"报卡" ,width:400};
    		$.messager.confirm("操作提示", "确认此细菌为 "+infectTypeName+"致病菌？", function (data) {  
    			if (data) {
    				$.ajax({
                    	url: '${webroot}/xn011Dclymx/f_json/updateGrlx.shtml',
                        type: 'POST',
                        data: { 
                        	dt: dt,
                        	zyid:zyid,
                        	surveyDeptId:surveyDeptId,
                        	orderno:orderno,
                        	infectTypeId:infectTypeId,
                        	infectTypeName:infectTypeName,
                        	testOrderNo:testOrderNo
						},
                        dataType: 'json',
                        success : function(json) {
                        	//json = eval("("+json+")");
                        	$.messager.show({title : '提示',msg : json.msg});
                        	$('#sjbbForm').submit();
    					}
                    });
                }else{
                	bk(zyid,surveyDeptId,orderno,infectTypeId,testOrderNo);
                	//
                	$('#sjbbForm').submit();
                }
            });  
    	}
    }
	function bk(zyid,surveyDeptId,orderno,infectTypeId,testOrderNo){
		parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid+'&infectTypeId='+infectTypeId+'&testOrderNo='+testOrderNo+'&orderno='+orderno+'&surveyDeptId='+surveyDeptId,true);
	}
</script>
</body>
</html>