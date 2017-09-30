<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>感染病例监测</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/datagrid-groupview.js"></script>
</head>
<body>
	 <div id="infect_monitor_tab" class="easyui-tabs" data-options="fit:true,plain:true,border:false">
        <div title="待查">
            <div class="easyui-layout" data-options="fit:true">
		        <div data-options="region:'west',split:false,border:false" style="width:350px;border-right-width: 1px;">
					<div id="tb" class="m_search" style="display: none;">
						<!-- <span>感染日期：</span> --><input type="text" id="queryStartDate" onclick="WdatePicker()" style="width:68px;" value="${queryStartDate}">~
						<input type="text" id="queryEndDate"  onclick="WdatePicker()" style="width:68px;" value="${queryEndTime}">						
						<div class="n_btn_blue"><a href="javascript:;" onclick="infectionCounts.query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>	</div>
						<div class="btn_r_c"><nis:auth menuNo="C010101"><a href="javascript:void(0)" onclick="infectionCounts.openAdvancedQuery();" class="a_icon_c" title="高级查询"><i class="icon iconfont">&#xe686;</i></a></nis:auth></div>
					</div>
					<div id="infectionCountsPanel"></div>
					<div id="id_advanced_search" class="easyui-dialog" title="高级查询" style="width:400px;height:280px;top:30px;padding:5px 30px;"
						data-options="closed: true,modal: true,buttons:'#id_advanced_button'" >
						<div id="id_advanced_div" style="display: none;">
							<ul class="search_li">
								<li>
									<input id="id_floor" type="radio" name="dataRange" value="0" ${'1' eq isAll ? 'checked="checked"' : '' }/>  
									<label for="id_floor">全院</label>
								</li>
								<li>
									<input id="id_manage" type="radio" name="dataRange" value="1" ${'1' ne isAll ? 'checked="checked"' : '' }/>  
									<label for="id_manage">管辖科室</label>
								</li>
							</ul>
							<div class="clear"></div>
							<ul class="search_li">						
								<li>
									<input id="id_unit_infect" type="radio" name="infectTypeId" value="1" checked="checked"/>  
									<label for="id_unit_infect">医院感染</label>
								</li>
								<li>
									<input id="id_social_infect" type="radio" name="infectTypeId" value="2"/>  
									<label for="id_social_infect">社区感染</label>
								</li>
							</ul>
							<div class="clear"></div>
							<ul class="search_li">
								<li>
									<input id="id_all_hosp" type="radio" name="isInHosp" value="" checked="checked"/>  
									<label for="id_all_hosp">全部</label>
								</li>
								<li>
									<input id="id_in_hosp" type="radio" name="isInHosp" value="1"/>  
									<label for="id_in_hosp">在院</label>
								</li>
								<li>
									<input id="id_out_hosp" type="radio" name="isInHosp" value="2"/>  
									<label for="id_out_hosp">出院</label>
								</li>
							</ul>
							<div class="clear"></div>
							<div style="border-top:1px dotted #ccc; margin-top:10px; padding-top:10px;">
								<span>感染部位：</span>
								<div class="select_del"><input class="easyui-combotree" id="infection_parts_code" data-options="url:'${webroot}/yygrzd/f_json/findYygrzdList.shtml?flag=1',method:'get'" style="width:230px;" ><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#infection_parts_code').combotree('clear');"></a></div>
								<!-- <input type="text" id="infection_parts_name" class="auto-tip text" onclick="infectionCounts.grantDep();" readOnly="true" />
								<input type="hidden" id="infection_parts_code"/> -->
							</div>
						</div>
				    </div>
				    <div id="id_advanced_button" style="display: none;">
				    	<div class="n_btn_blue">
							<a href="javascript:;" onclick="infectionCounts.advancedQuery();"><i class="icon iconfont">&#xe688;</i><span>查询</span></a>
						</div>
						<!-- <input type="button" onclick="infectionCounts.advancedQuery();" class="btn_searchA" iconCls="icon-search" plain="true" value="高级查询" /> -->
					</div>
		        </div>
		        <div data-options="region:'center',border:false">
		        	<div class="gr_main_r">
						<div class="gr_main_r_dc" id="id_waitSearch"></div>
					</div>
		        </div>
		    </div>
        </div>
        <div title="已确认" style="overflow:hidden;" data-options="href:'${webroot}/gr002YsgrMx/f_view/toConfirmInfect.shtml'">
        	
        </div>
        <div title="已排除" style="overflow:hidden" data-options="href:'${webroot}/gr002YsgrMx/f_view/toHasRuleOutList.shtml'">
        	
        </div>
    </div>
    
<script type="text/javascript">
	var infectionCounts = {
		panel : 'infectionCountsPanel',
		query : function() {
			$('#' + infectionCounts.panel).datagrid({
	            url: '${webroot}/gr002YsgrMx/f_json/findInfectionCounts.shtml',
	            queryParams: {
	            	'queryStartDate':$('#queryStartDate').val(),
	            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
	            	'dataRange':$("input:radio[name='dataRange']:checked").val(),
	            	'infectTypeId':$("input:radio[name='infectTypeId']:checked").val(),
	            	'isInHosp':$("input:radio[name='isInHosp']:checked").val(),
	            	'infectCode':$('#infection_parts_code').combotree('getValue')
	            },
	            pageNumber : 1
	        });
		},
		//打开高级查询
		openAdvancedQuery : function () {
			$('#id_advanced_button').css('display','');
			$('#id_advanced_div').css('display','');
			$('#id_advanced_search').dialog('open');
		},
		advancedQuery : function() {
			$('#id_advanced_search').dialog('close');
			infectionCounts.query();
		},
		followSearch : function(deptId){
			var queryStartDate = $('#queryStartDate').val(),
			queryEndDate = $('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59';
			$.ajax({
                url: '${webroot}/foPatient/f_json/findByDeptIdDetail.shtml',
                type: 'post',
                data: { deptId: deptId, queryStartDate: queryStartDate, queryEndDate: queryEndDate},
                dataType: 'json',
                success : function(json) {
                	var htmlStr = '<ul>';
                	for (var i=0; i<json.length; i++) {
                		var st003 = json[i];
                		htmlStr += '<li><div class="gr_main_r_dc_t" onclick="infectionCounts.patientInfo(\'' + st003.zyid + '\')" style="background-color:#0190D4;font-color:#FFFFFF;font-weight:bold;cursor:pointer;"><span style="color:#FFFFFF">' + st003.patientName + '</span><span style="color:#FFFFFF">' + infectionCounts.getNotNullStr(st003.bedNo) + '床</span><span style="color:#FFFFFF">' + st003.sex + '</span><span style="color:#FFFFFF">' + st003.age + st003.ageUnit + '</span></div>';
                		htmlStr += '<div class="gr_main_r_dc_c"><table><tr><td>住&nbsp;院&nbsp;号：</td><td><a href="javascript:void(0)" class="underline" onclick="infectionCounts.patientInfo(\'' + st003.zyid + '\')">' + st003.zyid + '</a></td></tr><tr><td>入院日期：</td><td>' + st003.inHospAt + '</td></tr>';
                		htmlStr += '<tr><td>诊&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;断：</td><td title="'+ infectionCounts.getNotNullStr(st003.diagnosisName) +'"><span class="fixed_w">' + infectionCounts.getNotNullStr(st003.diagnosisName) + '</span></td></tr></table>';
						htmlStr += '</div>';
                		htmlStr += '<div class="gr_main_r_dc_b"><a href="javascript:void(0)" onclick="infectionCounts.audit(\'' + st003.zyid + '\')" class="underline">审核</a>';
                		htmlStr += '<a href="javascript:void(0)" onclick="intervent.intervention(\'' + st003.zyid + '\', \'' + st003.patientName + '\')" class="underline">干预</a>' + 
                				'<a href="javascript:void(0)" onclick="infectionCounts.riskFactors(\'' + st003.zyid + '\')" class="underline">风险因素</a></div></li>';
                	}
                	htmlStr += '</ul>';
                	$('#id_waitSearch').html(htmlStr);
                	var tab = $('#infect_monitor_tab').tabs('getTab',0); // 取得第一个tab 
                	$('#infect_monitor_tab').tabs('update', {
	                	tab: tab, 
	                	options: { 
	                		title: '待查(' + json.length + ')'
	                	} 
                	});
				}
    		});
		},
		waitSearch : function(deptId, dataType) {
			$('#id_waitSearch').html('');
			var queryStartDate = $('#queryStartDate').val(),
				queryEndDate = $('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
				infectTypeId = $("input:radio[name='infectTypeId']:checked").val(),
				isInHosp = $("input:radio[name='isInHosp']:checked").val(),
				suspectedDegreeGt = '',
				suspectedDegreeLt = '',
				reportType = '',
				infectCode = $('#infection_parts_code').combotree('getValue');
			if (dataType == 'conform') {
				suspectedDegreeGt = '100';
				suspectedDegreeLt = '';
			} else if (dataType == 'veryLikely') {
				suspectedDegreeGt = '80';
				suspectedDegreeLt = '100';
			} else {
				reportType = '1';
			}
			$.ajax({
                url: '${webroot}/gr002YsgrMx/f_json/findInfectionDetail.shtml',
                type: 'post',
                data: { deptId: deptId, queryStartDate: queryStartDate, queryEndDate: queryEndDate, infectTypeId: infectTypeId, isInHosp: isInHosp, suspectedDegreeGt: suspectedDegreeGt, suspectedDegreeLt: suspectedDegreeLt, reportType: reportType, infectCode: infectCode},
                dataType: 'json',
                success : function(json) {
                	var htmlStr = '<ul>';
                	for (var i=0; i<json.length; i++) {
                		var gr002 = json[i];
                		htmlStr += '<li><div class="gr_main_r_dc_t" onclick="infectionCounts.patientInfo(\'' + gr002.zyid + '\')" style="background-color:#0190D4;font-color:#FFFFFF;font-weight:bold;cursor:pointer;"><span style="color:#FFFFFF">' + gr002.patientName + '</span><span style="color:#FFFFFF">' + infectionCounts.getNotNullStr(gr002.bedNo) + '床</span><span style="color:#FFFFFF">' + gr002.sex + '</span><span style="color:#FFFFFF">' + gr002.age + gr002.ageUnit + '</span>' + ((gr002.reportType == 1 || gr002.reportType == 2) ? '<span title="' + gr002.reportTypeName + '" class="round red">●</span>' : '') + '</div>';
                		htmlStr += '<div class="gr_main_r_dc_c"><table><tr><td>住&nbsp;院&nbsp;号：</td><td><a href="javascript:void(0)" class="underline" onclick="infectionCounts.patientInfo(\'' + gr002.zyid + '\')">' + gr002.zyid + '</a></td></tr><tr><td>入院日期：</td><td>' + gr002.inHospAt + '</td></tr>';
                		htmlStr += '<tr><td>监测日期：</td><td>' + infectionCounts.getNotNullStr(gr002.moniAt) + '</td></tr><tr><td>感染日期：</td><td>' + infectionCounts.gethMdDateStr(gr002.startAt) + '</td></tr><tr><td>感染部位：</td><td title="' + gr002.infectName + '"><span class="fixed_w">' + gr002.infectName + '</span></td></tr></table>';
						htmlStr += '</div>';
                		htmlStr += '<div class="gr_main_r_dc_b"><a href="javascript:void(0)" onclick="infectionCounts.audit(\'' + gr002.zyid + '\')" class="underline">审核</a>';
                		htmlStr += '<a href="javascript:void(0)" onclick="intervent.intervention(\'' + gr002.zyid + '\', \'' + gr002.patientName + '\')" class="underline">干预</a>' + 
                				'<a href="javascript:void(0)" onclick="infectionCounts.riskFactors(\'' + gr002.zyid + '\')" class="underline">风险因素</a></div></li>';
                	}
                	htmlStr += '</ul>';
                	$('#id_waitSearch').html(htmlStr);
                	var tab = $('#infect_monitor_tab').tabs('getTab',0); // 取得第一个tab 
                	$('#infect_monitor_tab').tabs('update', {
	                	tab: tab, 
	                	options: { 
	                		title: '待查(' + json.length + ')'
	                	} 
                	});
				}
    		});
		},
		audit : function (zyid) {
			parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=10&zyid=' + zyid,true);
		},
		patientInfo : function (zyid) {
			parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=10&zyid=' + zyid,true);
		},
		grantDep : function() {
	    	Comm.dialogGlobal({
	        	url:"${webroot}/yygrzd/f_view/yygrzdSelect.shtml",
	            title: '感染部位',
	            width:350,
	            height: 450,
	            parent:this,
	            type: 'iframe'
	        });
	    },
	    getYygrzd : function (code,name){
	    	$('#infection_parts_name').val(name);
	    	$('#infection_parts_code').val(code);
	    },
    	getNotNullStr : function(str) {
    		if (str == null) {
    			return '';
    		} else {
    			return str;
    		}
    	},
    	gethMdDateStr : function(str) {
    		if (str && str.length > 10) {
    			return str.substring(0,10);
    		} else {
    			return '';
    		}
    	},
    	//风险因素
    	riskFactors : function(zyid) {
    		parent.menuInfo.clickMenu('风险详情','/fxPatient/f_view/detail.shtml?zyId='+zyid+"&tab=2",true);
    	}
	};
	
	var intervent = {
		//刷新页面用
		query : function() {
			
		},
		//干预
		intervention : function(zyid, name) {
	        Comm.dialogGlobal({
	        	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid=" + zyid + '&msgType=1',
	            title: '患者【' + name + '】的干预内容',
	            width:870,
	            height:565,
	            type:"iframe",
	            parent:this
	        });
	    },
	};
	
	$.extend($.fn.datagrid.methods,{
	    statistics: function (jq) {
	    	$('#table').datagrid('appendRow', {
	            Saler: '<span class="subtotal">合计</span>',
	            TotalOrderCount: '<span class="subtotal">' + compute("TotalOrderCount") + '</span>',
	            TotalOrderMoney: '<span class="subtotal">' + compute("TotalOrderMoney") + '</span>',
	            TotalOrderScore: '<span class="subtotal">' + compute("TotalOrderScore") + '</span>',
	            TotalTrailCount: '<span class="subtotal">' + compute("TotalTrailCount") + '</span>',
	            Rate: '<span class="subtotal">' + ((compute("TotalOrderScore") / compute("TotalTrailCount")) * 100).toFixed(2) + '</span>'
	        });
	    }
	});
	
	
	
	$(document).ready(function () {
		$('#' + infectionCounts.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        url: '${webroot}/gr002YsgrMx/f_json/findInfectionCounts.shtml',
            queryParams: {
            	'queryStartDate':$('#queryStartDate').val(),
            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
            	'dataRange':$("input:radio[name='dataRange']:checked").val(),
            	'infectTypeId':$("input:radio[name='infectTypeId']:checked").val(),
            	'isInHosp':$("input:radio[name='isInHosp']:checked").val(),
            	'infectCode':$('#infection_parts_code').combotree('getValue')
            },
	        columns:[
		       	[
		            {field:'deptName',title:'科室名称',sortable:true,width:140},
		            {field:'reportNum',title:'上报 ',sortable:true,align:'center',width:40,
						formatter:function(value,rec){
							return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="infectionCounts.waitSearch(\'' + rec.deptId + '\', \'report\')">' + value + '</a>'].join('');
					    }
					},
		            {field:'conformNum',title:'符合',sortable:true,align:'center',width:40,
						formatter:function(value,rec){
							return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="infectionCounts.waitSearch(\'' + rec.deptId + '\', \'conform\')">' + value + '</a>'].join('');
					    }
					},
		            {field:'veryLikelyNum',title:'很可能',sortable:true,align:'center',width:50,
						formatter:function(value,rec){
							return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="infectionCounts.waitSearch(\'' + rec.deptId + '\', \'veryLikely\')">' + value + '</a>'].join('');
					    }
					},
					{field:'followNum',title:'关注',sortable:true,align:'center',width:40,
						formatter:function(value,rec){
							return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="infectionCounts.followSearch(\'' + rec.deptId + '\')">' + value + '</a>'].join('');
					    }
					}
		        ]
	        ],
			showFooter: true,
	        rownumbers:true,
	        toolbar:'#tb',
            onLoadSuccess : function (data) {
            	if (data.rows.length > 0) {
            		$('#' + infectionCounts.panel).datagrid('selectRow',0);
            		infectionCounts.waitSearch(data.rows[0].deptId, 'report');
            	}
            }
	    });

	});
</script>
</body>
</html>
