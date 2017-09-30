<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   	<title>ICU临床病情等级评定</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout">
	<form id="editFormDjpdd" method="post">
	<input type="hidden" name="dtYear" value="${dtYear}"/>
	<input type="hidden" name="dtMonth" value="${dtMonth}"/>
	<input type="hidden" id="weeknumber" name="weeknumber" value="${weeknumber}"/>
	<input type="hidden" name="deptId" value="${deptId}"/>
	<input type="hidden" name="startdt" value="${strDate}"/>
	<input type="hidden" name="enddt" value="${enddt}"/>
	<div data-options="region:'center',border:false">
	<div id="gm002DjpddPanel"></div>
	<div id="tb" class="m_search">
		<span class="pro_text">选择日期：</span>
		<input type="text"  id="strDate" value="${strDate}" style="width:80px" class="Wdate text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${strDate}',maxDate:'${enddt}',onpicked:function(){gm002Djpdd.query()}})"  />		
		<div class="n_btn_blue">
			<a href="javascript:;"  onclick="gm002Djpdd.saveList()"><i class="icon iconfont">&#xe63d;</i><span>保存</span></a>
		</div>
		<div class="n_btn_blue" id="gradeInf" style="margin-right:20px; float:right;">
			<a href="javascript:;"  onclick="gm002Djpdd.gradeInfo()"><span>病情等级说明</span></a>
		</div>
	</div>
</div>
		</form>
	<script type="text/javascript">
		var tips=0;
		var gm002Djpdd = {
			panel : 'gm002DjpddPanel',
			//查询
			query : function() {
		        $('#'+gm002Djpdd.panel).datagrid({
		            queryParams: {
		            	'deptId': '${deptId}',
		                'strDate': $('#strDate').val(),
		                'year':'${dtYear}',
		                'month':'${dtMonth}',
		                'week':'${weeknumber}'
		            },
		            pageNumber: 1
		        });
		    },
		    gradeInfo : function() {
		    	parent.$.messager.show({
					title:'病情等级说明',
					msg:'<table class="table_grjc" cellspacing="0" cellpadding="0"><tbody><tr><td width="60">分类级别</td><td width="30">分值</td><td>分类标准</td></tr><tr><td>A类</td><td>1分</td><td>需要常规观察，不需加强护理和治疗，（包括手术后只需观察的患者）。这类患者常在48h内从ICU中转出。</td></tr><tr><td>B类</td><td>2分</td><td>病情稳定，但需要预防性观察，不需要加强护理和治疗的患者，例如某些患者因需要排除心肌炎、心肌梗死以及因需要服药而在ICU过夜观察。</td></tr><tr><td>C类</td><td>3分</td><td>病情稳定，但需要加强护理和/或监护的患者，如昏迷患者或出现慢性肾衰的患者。</td></tr><tr><td>D类</td><td>4分</td><td>病情不稳定，需要加强护理和治疗，需要经常评价和调整治疗方案的患者。如心率不齐、糖尿病酮症酸中毒（但尚未出现昏迷、休克、DIC）。</td></tr><tr><td>E类</td><td>5分</td><td>病情不稳定，且处在昏迷或休克状态，需要心肺复苏或需要加强护理治疗并需要经常评价护理和治疗效果的患者。</td></tr></tbody></table>',
					width:600,
					height:'70%',
					showType:'show',
					timeout:10000,
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						
					}
				});
		    },
		    edit: function(zyid,dtYear,dtMonth,weeknumber,grade){
		    	$.ajax({
                    url: '${webroot}/gm002Djpdd/f_json/save.shtml',
                    type: 'post',
                    data: { zyid: zyid,dtYear:dtYear,dtMonth:dtMonth,weeknumber:weeknumber,grade:grade,deptId:'${deptId}',startdt:'${strDate}',enddt:'${enddt}'},
                    dataType: 'json',
                    success : function(json) {
						parent.gm001Djpdm.refresh(weeknumber-1);
					}
        		});
		    },
		    showDetail:function(zyid){
		    	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
		    },
		    saveList:function(){
		    	$('#editFormDjpdd').submit();
		    },
		    onClickTips:function(){
		    	tips=1;
		    	parent.parentTips=1;
		    }
		};
		$(document).ready(function () {
			window.setTimeout(function() {
				Comm.form({
					id : 'editFormDjpdd',
					url : '${webroot}/gm002Djpdd/f_json/saveList.shtml',
					onLoading : function () {
						$.messager.progress({
							text : '正在提交中....',
							interval : 200
						});
					},
					success : function(json) {
						$.messager.progress('close');
						if (json.result === 'success') {
							parent.$.messager.show({ title: '提示', msg: '操作成功！' });
							parent.parentTips=0;
							//刷新父页面列表数据
							parent.gm001Djpdm.refresh($('#weeknumber').val()-1);
							parent.Comm.dialogClose('${param.dialogId}');
						} else if (json.result === 'error') {
							parent.$.messager.show({title : '提示',msg : '操作失败！'});
						} else {
							parent.$.messager.show({title : '提示',msg : json.msg});
						}
				}});
			});
			parent.parentTips=0;
			$('#'+gm002Djpdd.panel).datagrid({
                fit: true,
                nowrap: true,
                autoRowHeight: true,
                striped: true,
                fitColumns: true,
                collapsible:true,
                url:'${webroot}/gm002Djpdd/f_json/pageQuery.shtml',
                queryParams: {
	            	'deptId': '${deptId}',
	                'strDate': $('#strDate').val(),
	                'year':'${dtYear}',
	                'month':'${dtMonth}',
	                'week':'${weeknumber}'
	            },
                remoteSort: false,
                singleSelect: true,
                border:false,
                columns:[
                	[
						{field:'bedNo',title:'床号',sortable:true,width:10},  
						{field:'zyid',title:'${patientZyTitle}',sortable:true,width:15,
							formatter:function(value,r){
								return ['<a href="javascript:gm002Djpdd.showDetail(\'',r.zyid,'\',\'\');">',r.zyid,'</a>'].join('');
							}
						},
						{field:'patientName',title:'姓名',sortable:true,width:15},
						{field:'sex',title:'性别',sortable:true,width:15},
						{field:'age',title:'年龄',sortable:true,width:15},
						{field:'gradea',title:'A',sortable:true,width:5,align:'center',
							formatter:function(value,r,index){
								var checkboxs='<input type="hidden" name="djpddList['+index+'].zyid" value="'+r.zyid+'"/><input type="radio" name="djpddList['+index+'].grade"';
								if(r.gradea==1){
									checkboxs = checkboxs + 'checked="checked"';
								}
								checkboxs = checkboxs + ' value="A" onClick="gm002Djpdd.onClickTips()"/>';
								//checkboxs = checkboxs + ' onClick="gm002Djpdd.edit(\''+r.zyid+'\',\''+r.dtYear+'\',\''+r.dtMonth+'\',\''+r.weeknumber+'\',\'A\')" />';
								return [checkboxs].join('');							
							}	
						},		           
	                    {field:'gradeb',title:'B',sortable:true,width:5,align:'center',
							formatter:function(value,r,index){
								var checkboxs='<input type="radio" name="djpddList['+index+'].grade"';
								if(r.gradeb==1){
									checkboxs = checkboxs + 'checked="checked"';
								}
								checkboxs = checkboxs + ' value="B" onClick="gm002Djpdd.onClickTips()"/>';
								//checkboxs = checkboxs + ' onClick="gm002Djpdd.edit(\''+r.zyid+'\',\''+r.dtYear+'\',\''+r.dtMonth+'\',\''+r.weeknumber+'\',\'B\')">';
								return [checkboxs].join('');							
							}		
	                    },
	                    {field:'gradec',title:'C',sortable:true,width:5,align:'center',
	                    	formatter:function(value,r,index){
	                    		var checkboxs='<input type="radio" name="djpddList['+index+'].grade"';
								if(r.gradec==1){
									checkboxs = checkboxs + 'checked="checked"';
								}
								checkboxs = checkboxs + ' value="C" onClick="gm002Djpdd.onClickTips()"/>';
								//checkboxs = checkboxs + ' onClick="gm002Djpdd.edit(\''+r.zyid+'\',\''+r.dtYear+'\',\''+r.dtMonth+'\',\''+r.weeknumber+'\',\'C\')">';
								return [checkboxs].join('');							
							}		
	                    },
	                    {field:'graded',title:'D',sortable:true,width:5,align:'center',
	                    	formatter:function(value,r,index){
	                    		var checkboxs='<input type="radio" name="djpddList['+index+'].grade"';
								if(r.graded==1){
									checkboxs = checkboxs + 'checked="checked"';
								}
								checkboxs = checkboxs + ' value="D" onClick="gm002Djpdd.onClickTips()"/>';
								//checkboxs = checkboxs + ' onClick="gm002Djpdd.edit(\''+r.zyid+'\',\''+r.dtYear+'\',\''+r.dtMonth+'\',\''+r.weeknumber+'\',\'D\')">';
								return [checkboxs].join('');							
							}		
	                    },
	                    {field:'gradee',title:'E',sortable:true,width:5,align:'center',
	                    	formatter:function(value,r,index){
	                    		var checkboxs='<input type="radio" name="djpddList['+index+'].grade"';
								if(r.gradee==1){
									checkboxs = checkboxs + 'checked="checked"';
								}
								checkboxs = checkboxs + ' value="E" onClick="gm002Djpdd.onClickTips()"/>';
								//checkboxs = checkboxs + ' onClick="gm002Djpdd.edit(\''+r.zyid+'\',\''+r.dtYear+'\',\''+r.dtMonth+'\',\''+r.weeknumber+'\',\'E\')">';
								return [checkboxs].join('');							
							}		
	                    },
						{field:'outAt',title:'出院日期',sortable:true,width:15}
	                ]
                ],
                toolbar:'#tb'
            });
		});
	</script>
</body>
</html>