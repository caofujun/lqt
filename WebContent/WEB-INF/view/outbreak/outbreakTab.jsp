<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>暴发预警分析</title>
<jsp:include page="/WEB-INF/view/core/report.jsp"></jsp:include>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout" style="border:0px">
	<div data-options="region:'west',border:false,title:''" style="width:365px;">
		<div id="001"></div>		
		<div id="tb1" class="m_search">	
			<input type="hidden" id="auditFlagStr" name="auditFlagStr" value="0"/>
			<div class="m_search_last">预警分类：<a class="cur" href="#" id="auditFlagStr_0" href="#" onclick="javascript:outbreak.setauditFlagStr('0')">待处理</a><a href="#" id="auditFlagStr_1" onclick="javascript:outbreak.setauditFlagStr('1,2')">已处理</a><a id="auditFlagStr_all" onclick="javascript:outbreak.setauditFlagStr('')">全部</a></div>
			<input type="hidden" id="grade" name="grade" value="3"/>
			<div class="m_search_last">预警级别：<a class="cur" href="#" id="grade_3" onclick="javascript:outbreak.setGrade('3')">重点关注</a><a href="#" id="grade_1" onclick="javascript:outbreak.setGrade('1')">一般关注</a><a href="#" id="grade_all" onclick="javascript:outbreak.setGrade('')">全部</a><a href="javascript:void(0)" onclick="outbreak.setGradeConfig('设置重点关注项目');" class="icon_r" title="设置重点关注项目"><i class="icon iconfont">&#xe601;</i></a></div>				
			<div class="m_search_last h_set">
				<span class="pro_text">监测日期：</span>
				<input type="text" id="queryStartDate" value="${startDate}" style="width:78px"  class="Wdate text" onclick="WdatePicker()"  /> ~
				<input type="text" id="queryEndDate" value="${endDate}" style="width:78px"  class="Wdate text" onclick="WdatePicker()"  />		
		 		<div class="n_btn_blue">
					<a href="javascript:;" onclick="outbreak.query('')"><i class="icon iconfont"></i><span>搜索</span></a>
				</div>
		 	</div>
		</div>
	</div>	
	<div data-options="region:'center',border:false" style="height:30;overflow:hidden;border-left-width: 1px;">
		<div id="002"></div>		
		<div id="tb2" class="m_search">	
		</div>	
	</div>

<script type="text/javascript">
	var outbreak = {
		panel : 'outbreakPanel',
		id : '',
		deptId : '',
		query : function () {
			autoTip.clear();
			$('#001').datagrid({
	            url: '${webroot}/by007Bfjl/f_json/findListByDept.shtml',
	            queryParams: {
	            	'auditFlagStr':$('#auditFlagStr').val(),
	            	'grade':$('#grade').val(),
	            	'queryStartDate':$('#queryStartDate').val(),
	            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59'
	            }
	        });
		},
		queryDetail : function () {
			$('#002').datagrid({
	            url: '${webroot}/by007Bfjl/f_json/findList.shtml?page=1&size=200',
	            page:1,
	            size:200,
	            queryParams: {
	            	'id':outbreak.id/*,
	            	'deptId':deptId,
	            	'auditFlagStr':$('#auditFlagStr').val(),
	            	'grade':$('#grade').val(),
	            	'queryStartDate':$('#queryStartDate').val(),
	            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59'*/
	            }
	        });
		},
		audit : function (divId,id,auditFlag) {
         	$("#"+divId).tooltip('hide');
        	$.ajax({
                url: '${webroot}/by007Bfjl/f_json/auditflag/set.shtml',
                type: 'post',
                data: { id: id, auditFlag: auditFlag },
                dataType: 'json',
                success : function(json) {
					if(json.result==='success') {
						outbreak.queryDetail();
						if(auditFlag == '1'){
                       		$.messager.show({ title: '提示', msg: '确认为暴发预警成功！' });
                        }else{
                       		$.messager.show({ title: '提示', msg: '排除成功！' });
                        }
			    	} else if(json.result === 'error') {
			    		$.messager.show({ title: '提示', msg: '系统异常！' });
			    	} else {
			    		$.messager.show({ title: '提示', msg: json.msg });
			    	}
				}
        	});
		},
	    //备注
	    setMemo:function(divId,id, title) {
	    	if(id===undefined || id == null ) id = '';
	    	Comm.dialogGlobal({
	        	url:"${webroot}/by007Bfjl/f_view/toMemo.shtml?id=" + id,
	        	title: title,
	            width:350,
	            height:250,
	            type:'iframe',
	            parent:this
	        });
	    },
	    //设置重点关注项
	    setGradeConfig:function(title) {
	    	Comm.dialogGlobal({
	        	url:"${webroot}/by007Config/f_view/toGrade.shtml",
	        	title: title,
	            width:400,
	            height:400,
	            type:'iframe',
	            parent:this
	        });
	    },
		setauditFlagStr : function (auditFlagStr) {
			$('#auditFlagStr').val(auditFlagStr);
			if(auditFlagStr == '0'){
				$('#auditFlagStr_0').attr("class","cur");
				$('#auditFlagStr_1').attr("class","");
				$('#auditFlagStr_all').attr("class","");
			}else if(auditFlagStr == '1,2'){
				$('#auditFlagStr_0').attr("class","");
				$('#auditFlagStr_1').attr("class","cur");
				$('#auditFlagStr_all').attr("class","");
			}else{
				$('#auditFlagStr_0').attr("class","");
				$('#auditFlagStr_1').attr("class","");
				$('#auditFlagStr_all').attr("class","cur");
			}
			outbreak.query();
		},
		setGrade : function (grade) {
			$('#grade').val(grade);
			if(grade == '3'){
				$('#grade_3').attr("class","cur");
				$('#grade_1').attr("class","");
				$('#grade_all').attr("class","");
			}else if(grade == '1'){
				$('#grade_3').attr("class","");
				$('#grade_1').attr("class","cur");
				$('#grade_all').attr("class","");
			}else{
				$('#grade_3').attr("class","");
				$('#grade_1').attr("class","");
				$('#grade_all').attr("class","cur");
			}
			outbreak.query();
		},
		//添加Tooltip
		addTooltip : function(divId, id,breakStartDate, breakEndDate, deptId, showId, breakType,absoluteDetailName) {
			var tmpDiv = '<ul class="down_li">';
			tmpDiv += '<li><a href="#" onclick="javascript:outbreak.patientList(\''+breakStartDate+'\',\''+breakEndDate+'\',\''+deptId+'\',\''+showId+'\',\''+breakType+'\',\''+absoluteDetailName+'\')">详情</a></li>';
			tmpDiv += '<li><a href="#" onclick="javascript:outbreak.audit(\''+divId+'\',\''+id+'\',1)">确认</a></li>';
			tmpDiv += '<li><a href="#" onclick="javascript:outbreak.audit(\''+divId+'\',\''+id+'\',2)">排除</a></li>';
			tmpDiv += '<li><a href="#" onclick="javascript:outbreak.setMemo(\''+divId+'\',\''+id+'\',\'备注\')">备注</a></li>';
			tmpDiv += '<li><a href="#" onclick="javascript:sendMessage(\''+divId+'\')">干预</a></li>';
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
		},
		//添加备注Tooltip
		addMemoTooltip : function(divId, memo) {
			var tmpDiv = '<div style="width:200px;height:100px;"><span><P><b>备注：</b></P></span>'+memo+'</div>';
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
		},
    	//病例列表
    	patientList : function(breakStartDate, breakEndDate, deptId, showId, breakType,absoluteDetailName) {
           	var url = '';
           	var title = '';
          	if ('0607003' == absoluteDetailName) {
           		title = '血培养人数';
           		url = webroot + '/by007Show/f_view/toBloodCultureList.shtml?dataType=trend&id=' + showId + '&deptId=' + deptId + '&queryStartDate=' + breakStartDate + '&queryEndDate=' + breakEndDate;
           	} else if ('0607005' == absoluteDetailName) {
           		title = '病原体人数';
           		url = webroot + '/by007Show/f_view/toPathogenPatientList.shtml?dataType=trend&id=' + showId + '&deptId=' + deptId + '&queryStartDate=' + breakStartDate + '&queryEndDate=' + breakEndDate;
           	} else if ('0607006' == absoluteDetailName) {
           		title = '感染部位';
           		url = webroot + '/by007Show/f_view/toInfectionSiteList.shtml?dataType=trend&id=' + showId + '&deptId=' + deptId + '&queryStartDate=' + breakStartDate + '&queryEndDate=' + breakEndDate;
           	} else if ('pr_stat_by0007_detail' == absoluteDetailName) {
           		title = '相同耐药谱';
           		url = webroot + '/by007Show/f_view/toDrugResistantList.shtml?dataType=trend&id=' + showId + '&deptId=' + deptId + '&queryStartDate=' + breakStartDate + '&queryEndDate=' + breakEndDate;
           	}else {
           		title = '病人列表';
           		url = webroot + '/by007Show/f_view/toPatientList.shtml?dataType=trend&id=' + showId + '&deptId=' + deptId + '&queryStartDate=' + breakStartDate + '&queryEndDate=' + breakEndDate;
           	}
          	Comm.dialog({
	        	url: url,
	            title: title,
	            width:980,
	            height:500,
	            type:"iframe",
	            parent:this
		   });
        }
	};
	

	$(document).ready(function () {
		<c:if test="${flag==0}">outbreak.setauditFlagStr('');</c:if>
		$('#001').datagrid({
	        fit: true,
	        nowrap: false,
	        autoRowHeight: false,
	        striped: true,
	        fitColumns: true,
	        collapsible:true,
	        url:'${webroot}/by007Bfjl/f_json/findListByDept.shtml',
	        queryParams: {
            	'auditFlagStr':$('#auditFlagStr').val(),
            	'grade':$('#grade').val(),
            	'queryStartDate':$('#queryStartDate').val(),
            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59'
	        },
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        columns:[[
	                {field:'deptName',title:'科室',sortable:true,width:150,
						formatter:function(value,rec,index){
							return ['<input type="hidden" id="id' + rec.id + '" name="id" value="' + (rec.id) + '"/>' + 
							        rec.deptName].join('');
					    }
	                },
	                {field:'deptCount',title:'事件数',sortable:true,width:45},
	                {field:'typeName',title:'概要',sortable:true,width:130}
	            ]],
	        rownumbers:true,
	        toolbar:'#tb1', 
	        showFooter: true,
	        onClickRow : function (index, row){
	        	outbreak.id = row.id;
	        	outbreak.deptId = row.deptId;
            	outbreak.queryDetail();
            },
            onLoadSuccess : function (data) {
            	if (data.rows.length > 0) {
                	$('#001').datagrid('selectRow', 0);
    	        	outbreak.id = data.rows[0].id;
    	        	outbreak.deptId = data.rows[0].deptId;
                	outbreak.queryDetail();
            	}
            }
	    });
		
		$('#002').datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: false,
	        striped: true,
	        fitColumns: true,
	        collapsible:true,
	        url:'${webroot}/by007Bfjl/f_json/findList.shtml?page=1&size=200',
	        queryParams: {
	        	'deptId':'0000'
	        },
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        columns:[[
	                {field:'breakStartDate',title:'开始日期',sortable:true,width:100},
	                {field:'breakEndDate',title:'结束日期',sortable:true,width:100},
	                {field:'deptName',title:'发生科室',sortable:true,width:140},
	                {field:'breakCount',title:'周期 | 数量 | 发生原因',sortable:true,width:200,
		        	  formatter:function(value,row,index){
		        		  var tmp = (''+row.observeDay+'天 | '+row.breakCount+('0607006' == row.absoluteDetailName?'例':'人')+' | '+row.name+'');
		        		  if(row.memo != null && row.memo != ''){
		        			  tmp += ('<span class="ico_note" title="'+ row.memo +'" class="easyui-tooltip" id="id_tooltip_memo_' + row.id + '"></span>');
		        		  }
					  	  return [tmp].join('');
					  }
		            },
	                {field:'moniDate',title:'监测日期',sortable:true,width:100},
	                {field:'auditName',title:'操作人员',sortable:true,width:80},
	                {field:'auditDate',title:'操作时间',sortable:true,width:140},
	                {field:'auditFlag',title:'状态',sortable:true,width:100,
						styler: function(value,row,index) {
							if(row.auditFlag == "1"){
								return 'color:#FF4200;';
							}else if(row.auditFlag == "2"){
								return 'color:#C4C4C4;';
							}
						},
						formatter:function(value,row){
							if(row.auditFlag == "1"){
								return '确认为暴发';
							}else if(row.auditFlag == "2"){
								return '已排除';
							}
						}
					},
                    {field:'_operate1',title:'操作',width:70,
						formatter:function(value,r){
							var resPropName = '';
							return resPropName += '<a href="#" title="cc111" class="easyui-tooltip" id="id_tooltip_' + r.id + '">详情<span class="arrow_down"></span></a>';
						}
					}/*,
                    {field:'_operate',title:'干预',width:140,
						formatter : function(value,r){
							return ['<a href="javascript:sendMessage();" class="ico_mail" title="消息"></a>'].join('');
						}
					}*/
	            ]],
            onLoadSuccess: function(data) {
        		for (var i = 0; i < data.rows.length; i++) {
        			var row = data.rows[i];
        			//alert(row.absoluteDetailName);
        			outbreak.addTooltip('id_tooltip_' + row.id,row.id,row.breakStartDate, row.breakEndDate, row.deptId, row.showId, row.breakType, row.absoluteDetailName);
        			outbreak.addMemoTooltip('id_tooltip_memo_' + row.id,row.memo);
        		}
            },
	        rownumbers: true
	    });
	});

	function sendMessage(divId){
     	$("#"+divId).tooltip('hide');
		Comm.dialogGlobal({
	    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?msgType=1",
	        title: "干预会话",
	        width:870,
	        height:555,
	        type:"iframe"
	    });
	}
</script>
</body>
</html>