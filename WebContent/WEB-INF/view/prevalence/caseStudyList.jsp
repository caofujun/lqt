<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>横断面个案调查</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id=id_batch_generate class="easyui-dialog" title="批量生成" style="width:350px;height:350px;padding:20px 25px;"
		data-options="closed: true,modal: true,buttons:'#id_batch_button'" >
		<div id="id_batch_div" style="display: none;">
			<div id="id_choose_date">
				<div style="border-top: 1px solid #ddd; ">
					<span style="padding:5px 10px 5px 0px;margin-top:-15px;background-color: #fff;position: absolute;">调查日期</span>
				</div>
				<div class="mb10" style="margin-top:20px;">选择调查日期，将生成全院的当天在院以及当天出院患者，自然剔除当天入院患者。</div>
				<div align="left">
					<span><b>调查日期：</b></span>
					<input type="text" id="id_survey_date" class="Wdate text" value="${currDate}" style="width:95px;" onclick="WdatePicker()" />
				</div>
				<div align="left" style="border-top: 1px solid #ddd;margin-top:30px; ">
					<span style="padding:5px 10px 5px 0px;margin-top:-15px;background-color: #fff;position: absolute;">自动生成数据</span>
					<div style="margin-top:20px; ">
						<label><input type="checkbox" checked="checked" disabled="disabled"/> 自动获取抗菌药物是否使用标识</label>
						<span style="display: block;">调查日期当天未停医嘱和新开医嘱</span>
					</div>
					<!-- <div style="margin-top:30px; ">
						<label>自动获取送培养时机为抗菌药物使用前标识</label>
						<span style="display: block;">抗菌药物开嘱日期之前的送检项目</span>
					</div> -->
				</div>
			</div>
			<div id="id_batch_loading" style="display: none;">
				<div align="center">生成数据需要3~5分钟时间。</div>
				<div align="center" style="margin-top: 15px;">
					<img src="${webroot}/resources/images/loading.gif"/>
					<div style="margin-top: 5px;">处理中，请稍候...</div>
				</div>
			</div>
		</div>
    </div>
    <div id="id_batch_button" align="center" style="text-align: center;display: none;">		
		<div class="n_btn_blue">
			<a href="javascript:;" onclick="caseStudy.generate();" class="no_ico"><span>生成</span></a>
		</div>
	</div>
	<div id="tb" style="display: none;">
		<div class="m_search">
			<div class="m_search_first">
				<span>调查日期:</span>
				<select id="surveyDate">
					<c:forEach items="${xl001BrxxList}" var="xl001Brxx">
						<option value="<fmt:formatDate value="${xl001Brxx.votedate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />"><fmt:formatDate value="${xl001Brxx.votedate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></option>
					</c:forEach>
				</select>
				<span>科室:</span>
				<div class="select_del">
					<input type="text" id="id_deptId" style="width: 120px;" />
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_deptId').combo('clear');"></a>
				</div>
				<select id="id_state" class="easyui-combobox"  data-options="editable:false" style="width:80px;" >
					<option value="">-登记状态-</option>
					<option value="1">已登记</option>
					<option value="0">未登记</option>
				</select>
				<select id="id_infectType" class="easyui-combobox" data-options="editable:false" style="width: 80px;">
					<option value="">-感染类型-</option>
					<option value="1">医院感染</option>
					<option value="2">社区感染</option>
				</select>			
				<input type="text" id="id_patient_info" class="auto-tip" data-tip="${patientNoTitle}/${patientZyTitle}/姓名" title="${patientNoTitle}/${patientZyTitle}/姓名" style="width: 140px;" />
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="caseStudy.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
				</div>
			</div>		
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="caseStudy.add();"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
				<c:if test="${clinical!=1}">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="caseStudy.toBatchGenerate();"><i class="icon iconfont">&#xe607;</i><span>生成</span></a>
				</div>
				</c:if>
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="caseStudy.batchDel();"><i class="icon iconfont fax">&#xe62b;</i><span>删除</span></a>
				</div>
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="caseStudy.exportMx();"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
				</div>
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="caseStudy.printBedInvesti();"><i class="icon iconfont">&#xe604;</i><span>打印</span></a>
				</div>
				<c:if test="${clinical!=1}">
				<!-- >div class="n_btn_grey">
					<a href="javascript:;" onclick="registryForm.addPathogens(this, 'unit');" ><i class="icon iconfont">&#xe655;</i><span>直报</span></a>
				</div -->
				</c:if>
			</div>
		</div>		
	</div>
	<div id="caseStudyPanel"></div>
<script type="text/javascript">
	var caseStudy = {
		panel : 'caseStudyPanel',
		query : function () {
			autoTip.clear();
			$('#' + caseStudy.panel).datagrid({
	            url: '${webroot}/xl001Brxx/f_json/findcaseStudyList.shtml',
	            queryParams: {
	            	'state':$('#id_state').combogrid('getValue'),
	            	'queryStartDate':$('#surveyDate').val(),
	            	'queryEndDate':$('#surveyDate').val()+' 23:59:59',
	            	'searchString':$('#id_patient_info').val(),
	            	'isCa':$('#id_infectType').combogrid('getValue') == '1' ? '1' : '',
	            	'isHa':$('#id_infectType').combogrid('getValue') == '2' ? '1' : '',
	            	'deptId':$('#id_deptId').combogrid('getValue')
	            }
	        });
		},
		//添加
		add : function() {
			if($('#surveyDate').val() == ''){
				alert("请先选择调查日期！");
				return ;
			}
			parent.menuInfo.clickMenu('个案登记表','/xl001Brxx/f_view/toRegistryFormEdit.shtml?surveyDate=' + $('#surveyDate').val(),true, null, null, '${param.tabBodyId}');
    	},
		//编辑
		edit : function(brid) {
			parent.menuInfo.clickMenu('个案登记表','/xl001Brxx/f_view/toRegistryFormEdit.shtml?brid=' + brid, true, null, null, '${param.tabBodyId}');
    	},
    	//删除
		del : function(brid, title) {
			$.messager.confirm('提示', (title ? title : '确认删除') + '?', function (r) {
	        	if (r) {
	            	$.ajax({
                        url: '${webroot}/xl001Brxx/f_json/delete.shtml',
                        type: 'post',
                        data: { brids: brid },
                        dataType: 'json',
                        success : function(json) {
							if(json.result==='success') {
								caseStudy.query();
                                $.messager.show({ title: '提示', msg: '删除成功！' });
					    	} else if(json.result === 'error') {
					    		$.messager.show({ title: '提示', msg: '删除异常！' });
					    	} else {
					    		$.messager.show({ title: '提示', msg: json.msg });
					    	}
						}
	            	});
	        	}
	    	});
    	},
    	//批量删除
    	batchDel : function() {
    		var selections = $("#"+caseStudy.panel).datagrid("getChecked");
    		var brids = '';
    		if (selections && selections.length > 0) {
    			for (var i = 0; i < selections.length; i++) {
        			brids += selections[i].brid + ',';
        		}
    			this.del(brids, '确认批量删除选中项');
    		} else {
    			$.messager.show({ title: '提示', msg: '请选择要删除的记录！' });
    		}
    	},
    	//批量生成
    	toBatchGenerate : function() {
    		$('#id_batch_div').css('display','');
    		$('#id_choose_date').css('display','');
    		$('#id_batch_button').css('display','');
    		$('#id_batch_loading').css('display','none');
			$('#id_batch_generate').dialog('open');
    	},
    	//执行批量生成
    	generate : function() {
    		var surveyDate = $('#id_survey_date').val();
    		$('#id_choose_date').css('display','none');
    		$('#id_batch_button').css('display','none');
    		$('#id_batch_loading').css('display','');
    		$.ajax({
                url: '${webroot}/xl001Brxx/f_json/callPNis6TaskXhl.shtml',
                type: 'post',
                data: { surveyDate: surveyDate },
                dataType: 'json',
                success : function(json) {
                	$('#id_batch_generate').dialog('close');
					if(json.result==='success') {
// 						caseStudy.query();
						window.location.reload();
                        $.messager.show({ title: '提示', msg: '生成成功！' });
			    	} else if(json.result === 'error') {
			    		$.messager.show({ title: '提示', msg: '生成异常！' });
			    	} else {
			    		$.messager.show({ title: '提示', msg: json.msg });
			    	}
				}
        	});
    	},
    	showDetail : function(zyid){
	    	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
	    },
    	//导出明细
    	exportMx : function() {
    		autoTip.clear();
    		var url = '${reportUrl}nis7/CASE_STUDY.cpt&__filename__=' + encodeURI(encodeURI(encodeURI('横断面个案调查明细'))) + '&state=' + $('#id_state').combogrid('getValue') + '&queryStartDate=' + $('#surveyDate').val() + 
    				'&queryEndDate=' + ($('#surveyDate').val() == ''?'':$('#surveyDate').val()+' 23:59:59') + '&searchString=' + encodeURI(encodeURI(encodeURI($('#id_patient_info').val()))) + '&deptId=' + $('#id_deptId').combogrid('getValue') +
    				'&isCa=' + ($('#id_infectType').combogrid('getValue') == '1' ? '1' : '') + '&isHa=' + ($('#id_infectType').combogrid('getValue') == '2' ? '1' : '');
    		window.open(url);
    		//parent.menuInfo.clickMenu(decodeURI(decodeURI('横断面个案调查导出')), url, true, '', null);
    	},
    	//打印床头调查表
    	printBedInvesti : function() {
    		if ($.trim($('#id_deptId').combogrid('getValue')).length == 0) {
    			parent.$.messager.alert('提示','请选择科室！','warning');
    			return false;
    		} else {
    			autoTip.clear();
        		var url = '${reportUrl}nis7/BED_QUESTION.cpt&__filename__=' + encodeURI(encodeURI(encodeURI('横断面调查床旁调查表'))) + '&state=' + $('#id_state').combogrid('getValue') + '&queryStartDate=' + $('#surveyDate').val() + 
        				'&queryEndDate=' + ($('#surveyDate').val() == ''?'':$('#surveyDate').val()+' 23:59:59') + '&searchString=' + encodeURI(encodeURI(encodeURI($('#id_patient_info').val()))) + '&deptId=' + $('#id_deptId').combogrid('getValue') +
        				'&deptName=' + encodeURI(encodeURI(encodeURI($('#id_deptId').combogrid('getText')))) + '&isCa=' + ($('#id_infectType').combogrid('getValue') == '1' ? '1' : '') + '&isHa=' + ($('#id_infectType').combogrid('getValue') == '2' ? '1' : '');
        		window.open(url);
        		//parent.menuInfo.clickMenu(decodeURI(decodeURI('床旁调查表导出')), url, true, '', null);
    		}
    	},
    	//刷新列表当条记录
		refreshRecord : function(brid) {
			$.ajax({
                url: '${webroot}/xl001Brxx/f_json/findOne.shtml',
                type: 'post',
                data: { brid: brid },
                dataType: 'json',
                success : function(json) {
                	if (json) {
                		var index = $('#' + caseStudy.panel).datagrid('getRowIndex', brid);
                    	$('#' + caseStudy.panel).datagrid('updateRow',{
    						index: index,
    						row: {
    							deptName : json.deptName,
    							zyid : json.zyid,
    							patientName : json.patientName,
    							bedNo : json.bedNo,
    							isCa : json.isCa,
    							isHa : json.isHa,
    							infectName : json.infectName,
    							isOperName : json.isOperName,
    							incisionType : json.incisionType,
    							drugResults : json.drugResults,
    							stateName : json.stateName,
    							votedate : json.votedate
    						}
    					}); 
                	}
                }
        	});
		}
	};

	$(document).ready(function () {
		$('#' + caseStudy.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url:'${webroot}/xl001Brxx/f_json/findcaseStudyList.shtml?queryStartDate=' + $('#surveyDate').val()+'&queryEndDate=' + $('#surveyDate').val()+' 23:59:59',   
	        remoteSort: false,
	        singleSelect: true,
	        checkOnSelect: false,
	        selectOnCheck: false,
	        border:false,
	        idField: 'brid',
	        columns:[ 
		       	[
					{field:'ck',checkbox:true,width:20},
		            {field:'deptName',title:'科室',sortable:true,width:120},
		            {field:'zyid',title:'${patientZyTitle}',sortable:true,width:100,
		            	formatter:function(value,row,index){
    						return ['<a href="javascript:caseStudy.showDetail(\'',row.zyid,'\',\'\');">',row.${patientZyValue},'</a>'].join('');
    					}
		            },
		            {field:'patientName',title:'患者',sortable:true,width:100,
		            	formatter:function(value,row,index){
    						return [(row.patientName+'('+row.sex+','+row.age+row.ageUnit+')')].join('');
    					}
		            },
		            {field:'bedNo',title:'床号',sortable:true,width:40},
		            {field:'infectType',title:'感染类型',sortable:true,width:80,
		            	formatter:function(value,row,index){
		            		if(row.isCa==1){
		            			return "医院感染";
		            		}
		            		if(row.isHa==1){
		            			return "社区感染";
		            		}
    						
    					}
		            },
		            {field:'infectName',title:'感染名称',sortable:true,width:150},
		            {field:'isOperName',title:'是否手术',sortable:true,align:'center',width:55},
		            {field:'incisionType',title:'切口类型',sortable:true,align:'center',width:55},
		            {field:'drugResults',title:'多耐情况',sortable:true,width:220},
					{field:'stateName',title:'状态',sortable:true,align:'center',width:50},
		            {field:'votedate',title:'调查日期',sortable:true,align:'center',width:80},
		            {field:'lastAt',title:'修改日期',sortable:true,align:'center',width:80},
		            {field:'votename',title:'调查者',sortable:true,width:60},
		            {field:'_operate',title:'操作',width:70,
    					formatter:function(value,row,index){
    						return ['<a href="javascript:void(0);" class="ico_editor" title="编辑" onclick ="caseStudy.edit(\'' + row.brid + '\', null)"></a>' + 
    								'<a href="javascript:void(0);" class="ico_del" title="删除" onclick ="caseStudy.del(\'' + row.brid + '\', null)"></a>'].join('');
    					}
    				}
		        ]
	        ],
	        pagination:true,
	        rownumbers:true,
	        toolbar:'#tb'
	    });
		
		//科室
		Csm.combogrid.dep({
			id: 'id_deptId',
			ifcaseoffice: '1',
			value: '${reportDeptId}',
			readonly : ${clinical == '1' ? true : false}
		});
	});
</script>
</body>
</html>
