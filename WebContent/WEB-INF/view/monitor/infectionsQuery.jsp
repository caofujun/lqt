<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>感染病例查询</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/js/monitor/reportCards.js?${version}"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:30;overflow:hidden;">
		<div class="m_search datagrid-toolbar">
			<div class="m_search_last">
				<select id="id_dateType" class="easyui-combobox" data-options="editable:false" style="width: 87px;">
					<option value="1">报告日期</option>
					<option value="2">感染日期</option>
					<option value="3">入院日期</option>
					<option value="4">出院日期</option>
					<option value="5">审核日期</option>
				</select>
				<input type="text" id="queryStartDate" value="${queryStartDate}" class="Wdate text" style="width:80px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />~
		    	<input type="text" id="queryEndDate" value="${queryEndDate}" class="Wdate text" style="width:80px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			 	<c:if test="${unitFlag=='1'}">
				<tr>
					<td class="t_title">院区：</td>
					<td>
						<div class="select_del">
						<input id="unitId" name="unitId" style="width:120px;"/>
						<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#unitId').combo('clear');"></a>
						</div>
					</td>
				</tr>
				</c:if>
		    	<!-- <span class="pro_text">感染类型：</span> -->
				<select id="id_infectTypeId" class="easyui-combobox" data-options="editable:false" style="width: 80px;">
					<option value="" ${infectTypeId eq 0 ? 'selected="selected"' : ''}>-感染类型-</option>
					<option value="1" ${infectTypeId eq 1 ? 'selected="selected"' : ''} >医院感染</option>
					<option value="2"${infectTypeId eq 2 ? 'selected="selected"' : ''}>社区感染</option>
				</select>
				<!-- <span class="pro_text">报卡状态：</span> -->
				<select id="id_reportStates" class="easyui-combobox" data-options="editable:false" style="width: 80px;">
					<option value="">-报卡状态-</option>				
					<option value="0" <c:if test="${bkState==0}">selected="selected"</c:if>>未确认</option>
					<option value="1" <c:if test="${bkState==null}">selected="selected"</c:if>>已确认</option>
					<option value="2">退卡</option>
					<option value="3">删卡</option>
				</select>			
				<input type="text" id="id_patient_info" class="auto-tip" data-tip="${patientNoTitle}/姓名/住院号" title="${patientNoTitle}/姓名/住院号" style="width: 100px;" />
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="infections.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
				</div>
				<div class="n_btn_blue">
					<a  href="javascript:void(0)" onclick="infections.openAdvancedQuery();"><i class="icon iconfont">&#xe688;</i><span>高级搜索</span></a>	
				</div>
				<div class="btn_r">				
					<div class="n_btn_grey">
						<a href="javascript:;" onclick="infections.exportExcelReport();"><i class="icon iconfont">&#xe628;</i><span>导出报卡</span></a>
					</div>				
				</div>
			</div>
		</div>
	</div>
    <div data-options="region:'west',split:false" style="width:280px;border-width: 0 1px 0px 0;">
		<div id="infectionsQueryPanel"></div>
		<div id="id_advanced_search" class="easyui-dialog" title="高级查询" style="width:400px;height:320px;top:30px;padding:5px 5px;"
			data-options="closed: true,modal: true,buttons:'#id_advanced_button'" >			
			<div class="m_search" id="id_advanced_div" style="display: none;">
				<table cellpadding="0" cellspacing="0" style="border-collapse:separate;border-spacing:10px;">
					<tr>
						<td>
							<span class="pro_text">相关性：</span>
							<input id="id_central" type="checkbox" name="relation" value="中心静脉插管"/>  
							<label for="id_central">中心静脉插管</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="id_catheter" type="checkbox" name="relation" value="导尿管插管"/>  
							<label for="id_catheter">导尿管插管</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="id_breath" type="checkbox" name="relation" value="呼吸机"/>  
							<label for="id_breath">呼吸机</label>
						</td>
					</tr>
					<tr>
						<td>
							<span class="pro_text">上报科室：</span>
							<div class="select_del">
								<input id="id_reportDept" style="width:250px" />
								<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_reportDept').combo('clear');"></a>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<span class="pro_text">感染科室：</span>
							<div class="select_del">
								<input id="id_infectDept" style="width:250px" />
								<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_infectDept').combo('clear');"></a>
							</div>
							<!-- <input type="text" id="id_infectDept" class="auto-tip text" data-tip="名字/首拼/编号" title="名字/首拼/编号"  /> -->
						</td>
					</tr>
					<tr>
						<td>
							<span class="pro_text">感染部位：</span>
							<div class="select_del"><input class="easyui-combotree" id="id_infectDiagnosis" data-options="panelHeight:300,url:'${webroot}/yygrzd/f_json/findYygrzdList.shtml?flag=1',method:'get',value:'${param.infectDiagnId}'" style="width:250px;" ><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_infectDiagnosis').combotree('clear');"></a></div>
						</td>
					</tr>
					<tr>
						<td>
							<span class="pro_text">重点菌名：</span>
							<nis:select name="specDescribes" id="id_specDescribes" value="${param.specDescribes}" cssCls="easyui-combobox" headerKey="" headerValue="-请选择-" dictcode="spec_describes"  exp="style=\"width: 120px;\" data-options=\"editable:false\""/>
						</td>
					</tr>
				</table>
			</div>			
	    </div>
	    <div id="id_advanced_button" style="text-align: center;display: none;">			
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="infections.advancedQuery();" class="no_ico"><span>查询</span></a>
				</div>			
			</div>
		</div>
    </div>
    <div data-options="region:'center'" style="border-width: 0 0px 0 1px;">
    	<div id="infect_monitor_tab" class="easyui-tabs card_tab" data-options="fit:true,plain:true,border:false" ></div>
    </div>
<script type="text/javascript">

	var infections = {
		panel : 'infectionsQueryPanel',
		tabId : 'infect_monitor_tab',
		queryType : '0',
		query : function() {
			this.queryType = '0';
			autoTip.clear();
			//this.clearAdvanced();
			$('#' + infections.panel).datagrid({
	            url: '${webroot}/bk001Sbk/f_json/findInfectionsCards.shtml',
	            queryParams: {
					<c:if test="${unitFlag=='1'}">
	            	'unitId': $('#unitId').combobox('getValue'),
					</c:if>
	            	'dateField':$('#id_dateType').combobox('getValue'),
	            	'queryStartDate':$('#queryStartDate').val(),
	            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
	            	'infectTypeId':$('#id_infectTypeId').combobox('getValue'),
	            	'isOk':$('#id_reportStates').combobox('getValue'),
	            	'searchString':$("#id_patient_info").val()
	            },
	            pageNumber : 1
	        });
		},
		//清除高级查询数据
		clearAdvanced : function() {
			$('#id_specDescribes').combobox('clear');
			$("#id_infectDiagnosis").combotree('clear');
			$('#id_infectDept').combogrid('clear');
			$('#id_reportDept').combogrid('clear');
			$("input:checkbox[name='relation']:checked").each(function(){ 
    			$(this).attr("checked",false);
   			});
		},
		//打开高级查询
		openAdvancedQuery : function () {
			$('#id_advanced_div').css('display','');
			$('#id_advanced_search').dialog('open');
		},
		//高级查询
		advancedQuery : function() {
			$('#id_advanced_search').dialog('close');
			this.queryType = '1';
			autoTip.clear();
			$('#' + infections.panel).datagrid({
	            url: '${webroot}/bk001Sbk/f_json/findInfectionsCards.shtml',
	            queryParams: {
					<c:if test="${unitFlag=='1'}">
	            	'unitId': $('#unitId').combobox('getValue'),
					</c:if>
	            	'dateField':$('#id_dateType').combobox('getValue'),
	            	'queryStartDate':$('#queryStartDate').val(),
	            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
	            	'infectTypeId':$('#id_infectTypeId').combobox('getValue'),
	            	'isOk':$('#id_reportStates').combobox('getValue'),
	            	'searchString':$("#id_patient_info").val(),
	            	'relation':this.getRelations(),
	            	'reportDeptId':$('#id_reportDept').combogrid('getValue'),
	            	'infectDeptId':$('#id_infectDept').combogrid('getValue'),
	            	'infectDiagnId':$("#id_infectDiagnosis").combotree('getValue'),
	            	'specDescribes':$('#id_specDescribes').combobox('getValue')
	            },
	            pageNumber : 1
	        });
		},
		closeAllTabs : function() {
			 var tabs = $('#' + infections.tabId).tabs('tabs');
	         var all = [];
	         all = $.map(tabs,function(n,i){
	             return $(n).panel('options');
	         });
			 $.each(all,function(i,n){
			 	$('#' + infections.tabId).tabs('close', n.title);
			 });
		},
		//病例卡片点击事件
		casesCrads : function (relid,zyid,patientId) {
			this.closeAllTabs();
			autoTip.clear();
			var dateField = $('#id_dateType').combobox('getValue'),
// 				queryStartDate = $('#queryStartDate').val(),
// 				queryEndDate = $('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
				//infectTypeId = $('#id_infectTypeId').combobox('getValue'),
				//isOk = $('#id_reportStates').combobox('getValue'),
				relation = this.queryType == '1' ? this.getRelations() : '',
				reportDeptId = this.queryType == '1' ? $('#id_reportDept').combogrid('getValue') : '',
				infectDeptId = this.queryType == '1' ? $('#id_infectDept').combogrid('getValue') : '',
				infectDiagnId = this.queryType == '1' ? $("#id_infectDiagnosis").combotree('getValue') : '',
				specDescribes = this.queryType == '1' ? $('#id_specDescribes').combobox('getValue') : '';
				
			$.ajax({
                url: '${webroot}/bk001Sbk/f_json/findReportCards.shtml',
                type: 'post',
                data: { relid: relid,  zyid: zyid,  patientId: patientId, dateField: dateField,  relation: relation, reportDeptId: reportDeptId, infectDeptId: infectDeptId, infectDiagnId: infectDiagnId, specDescribes : specDescribes },
                dataType: 'json',
                success : function(json) {
                	for (var i=0; i<json.length; i++) {
                		var bk001 = json[i];
                		if(relid == bk001.refid){
	                		$('#' + infections.tabId).tabs('add',{
	            			    title: bk001.infectDiagnName + '（' + bk001.isOkName + '）',
// 	            			    selected: relid == bk001.refid ? true : false,
	            			    index: i,
	            			    href:'${webroot}/bk001Sbk/f_view/toReportCards.shtml?relid=' + bk001.relid
	            			});
                		}
                	}
				}
    		});
// 			parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
// 			$.ajax({
//                 url: '${webroot}/bk001Sbk/f_view/toReportCards.shtml',
//                 type: 'post',
//                 data: { relid: bk001.relid},
//                 dataType: 'json',
//                 success : function(json) {
//                 	for (var i=0; i<json.length; i++) {
//                 		var bk001 = json[i];
//                 		$('#' + infections.tabId).tabs('add',{
//             			    title: bk001.infectDiagnName + '（' + bk001.isOkName + '）',
//             			    selected: relid == bk001.refid ? true : false,
//             			    index: i,
//             			    href:'${webroot}/bk001Sbk/f_view/toReportCards.shtml?relid=' + bk001.relid
//             			});
//                 	}
// 				}
//     		});
			
		},
    	getRelations : function() {
    		var relation = '';
    		$("input:checkbox[name='relation']:checked").each(function(){ 
    			relation += $(this).val() + ',';
   			});
    		relation = relation.substring(0, relation.lastIndexOf(','))
    		return relation;
    	},
    	//excel导出报卡明细
		exportExcelReport : function (){
			autoTip.clear();
			var dateField = $('#id_dateType').combobox('getValue');
			if (dateField) {
				if (dateField == '2') {
					dateField = 'infect_date';
				} else if (dateField == '3') {
					dateField = 'st3.in_hosp_at';
				} else if (dateField == '4') {
					dateField = 'st3.out_at';
				} else if (dateField == '5') {
					dateField = 'bk2.auth_at';
				} else {
					dateField = 'bk1.report_at';
				}
			} else {
				dateField = 'bk1.report_at';
			}
			var relationIn = this.getRelations();
			if (relationIn && relationIn.length > 0) {
				var relations = relationIn.split(',');
				for (var i = 0; i < relations.length; i++) {
					if (i == 0) {
						relationIn = "(";
					}
					relationIn += "'" + relations[i] + "',";
					if (i == (relations.length - 1)) {
						relationIn = relationIn.substring(0, relationIn.lastIndexOf(','));
						relationIn += ")";
					}
				}
			}
			var url = "/report/f_view/fineReportFrame.shtml?reportFile=nis7/CARDS_LIST&__filename__=" + encodeURI(encodeURI(encodeURI('感染上报明细'))) + "&dateField=" + dateField + "&queryStartDate=" + $('#queryStartDate').val() + "&queryEndDate=" + ($('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59') + 
				"&infectTypeId=" + $('#id_infectTypeId').combobox('getValue') + "&isOk=" + $('#id_reportStates').combobox('getValue') + "&searchString=" + encodeURI(encodeURI(encodeURI($('#id_patient_info').val()))) + "&relation=" + encodeURI(encodeURI(encodeURI(relationIn))) + "&reportDeptId=" + $('#id_reportDept').combogrid('getValue') + 
				"&infectDeptId=" + $('#id_infectDept').combogrid('getValue') + "&infectDiagnId=" + $('#id_infectDiagnosis').combotree('getValue') + "&specDescribes=" + $('#id_specDescribes').combobox('getValue');
			parent.menuInfo.clickMenu(decodeURI(decodeURI('感染上报明细导出')), url, true, '', null);
		}
	};
	
	var cardSpecial = {
		//刷新方法
		query : function() {
			infections.query();
		},
		//编辑模式
		toEdit : function(relid) {
			var currTab = $('#infect_monitor_tab').tabs('getSelected');
			var href = '${webroot}/bk001Sbk/f_view/toReportCardsEdit.shtml?relid=' + relid;
			currTab.panel('refresh', href);
		},
		//退出编辑
		exitEdit : function(relid) {
			var currTab = $('#infect_monitor_tab').tabs('getSelected');
			var href = '${webroot}/bk001Sbk/f_view/toReportCards.shtml?relid=' + relid;
			currTab.panel('refresh', href);
		}
	};
	
	$(document).ready(function () {
		<c:if test="${unitFlag=='1'}">
			Csm.comboBox.unit({
				//【必传】控件名称
				id: 'unitId',
				value: '${unitId}',
				flag: '1'
			});
		</c:if>
	
		$('#' + infections.panel).datagrid({
			fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: false,
	        remoteSort: false,
	        singleSelect: true,
	        fitColumns: false,
			showFooter: true,
			border:false,
	        url: '${webroot}/bk001Sbk/f_json/findInfectionsCards.shtml',
	        queryParams: {
	        	'dateField':$('#id_dateType').combobox('getValue'),
            	'queryStartDate':$('#queryStartDate').val(),
            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
      			'infectTypeId':$('#id_infectTypeId').combobox('getValue'),
           		'isOk':$('#id_reportStates').combobox('getValue'),
           		'infectDiagnId':$("#id_infectDiagnosis").combotree('getValue'),
            	'specDescribes':$('#id_specDescribes').combobox('getValue')
            },
	        columns:[
		       	[
					{field:'reportAt',title:'报告日期 ',sortable:true,width:76},
					{field:'reportDeptName',title:'报告科室',sortable:true,width:80},
		            {field:'patientName',title:'姓名',sortable:true,width:60},
		            {field:'relid',title:'bk2id',hidden:true},
		            {field:'${patientZyValue}',title:'${patientZyTitle}',sortable:true,width:105},
		            {field:'infectDeptName',title:'感染科室',sortable:true,width:80}/* ,
		            {field:'reportNum',title:'卡片数',sortable:true,align:'center',width:45} */
		        ]
	        ],
	        showFooter: true,
	        rownumbers:true,
	        onLoadSuccess : function (data) {
            	if (data.rows && data.rows.length > 0) {
            		$('#' + infections.panel).datagrid('selectRow',0);
            		infections.casesCrads(data.rows[0].relid,data.rows[0].zyid,data.rows[0].patientId);
            	}
            },
            onClickRow : function (index, row){
            	infections.casesCrads(row.relid,row.zyid,row.patientId);
            }
	    });
		
		//上报科室
		Csm.combogrid.dep({
			id: 'id_reportDept',
			ifcaseoffice: '1',
			//【可选参数】下拉列表的默认value，不传则没有默认值；
			value: '${reportDeptId}',
			readonly : ${clinical == '1' ? true : false}
		});
		
		//感染科室
		Csm.combogrid.dep({
			id: 'id_infectDept',
			ifcaseoffice: '1',
			callback: '0',
		});
	});
</script>
</body>
</html>
