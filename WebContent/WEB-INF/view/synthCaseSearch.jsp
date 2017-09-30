<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预警病例查询</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css${version}" />
<style type="text/css">
	html,body{height:100%;}
	.panel{float:left;}
	.yjjb .select_img_del {right:8px;}
	.panel-body{border-top-width:0px; border-bottom-width:0px;}
	.fake-combobox{position: relative;position: relative;padding-top: 2px;padding-bottom: 5px;border: 1px solid #ddd;left: -20px;}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',border:false,title:'查询条件'" style="width:240px;">
		<div class="easyui-layout" data-options="fit:true">              
			<div data-options="region:'center',border:false">
				<form id="searchBar"> 
					<table class="table_cx" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="t_title">患者类别：</td>
								<td>
									<select id="patientType" name="patientType" onchange="conditions();" style="width: 120px;">
										<c:choose>
											<c:when test="${acType eq 'doctor'}">
												<c:if test="${chargeLevel==1 || chargeLevel==3}">
													<option value="ZY" <c:if test="${patientType eq 'ZY'}">selected=selected</c:if> >住院</option>
												</c:if>
												<c:if test="${chargeLevel>=2}">
													<option value="MZ" <c:if test="${patientType eq 'MZ'}">selected=selected</c:if> >门诊</option>
												</c:if>
											</c:when>
											<c:otherwise>
												<option value="ZY" <c:if test="${patientType eq 'ZY'}">selected=selected</c:if> >住院</option>
												<option value="MZ" <c:if test="${patientType eq 'MZ'}">selected=selected</c:if> >门诊</option>
											</c:otherwise>
										</c:choose>
									</select><span class="fake-combobox"></span>
								</td>
							</tr>
							<tr><td height="5"></td><td></td></tr>
							<tr>
								<td class="t_title">日期类型：</td>
								<td>
									<select id="queryDateType" name="dateType" style="width:120px;">
										<optgroup id="mzdt" label="门诊">
											<option value="4">预警日期</option>
											<option value="1">就诊日期</option>
										</optgroup>
										<optgroup id="zydt" label="住院">
											<option value="4">预警日期</option>
											<option value="2">入院日期</option>
											<option value="3">出院日期</option>
										</optgroup>
									</select><span class="fake-combobox"></span>
								</td>
							</tr>				
							<tr>
								<td class="t_title">开始日期：</td>
								<td>
									<input type="text" id="queryStartDate" name="queryStartDate" class="Wdate text" style="width: 108px;" value="${queryStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								</td>
							</tr>					
							<tr>
								<td class="t_title">结束日期：</td>
								<td>
									<input type="text" id="queryEndDate" name="queryEndDate" class="Wdate text" style="width: 108px;" value="${queryEndDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr><td height="5"></td><td></td></tr>
							<tr>
								<td class="t_title">科室类型：</td>
								<td>
									<select id="deptType" name="deptType" style="width: 120px;">
									<optgroup id="mzdept" label="门诊">
										<option value="1" <c:if test="${deptType==1}">selected=selected</c:if> >就诊科室</option>
									</optgroup>
									<optgroup id="zydept" label="住院">
										<option value="2" <c:if test="${deptType==2}">selected=selected</c:if> >入院科室</option>
										<option value="3" <c:if test="${deptType==3}">selected=selected</c:if> >出院科室</option>
										<option value="4" <c:if test="${deptType==4}">selected=selected</c:if> >所在科室</option>
									</optgroup>
								</select><span class="fake-combobox"></span>
								</td>
							</tr>
							<tr>
								<td class="t_title">科室：</td>
								<td>
									<div class="select_del">
										<input class="easyui-combobox" name="deptId" id="deptId" style="width: 120px;">
										<c:if test="${acType eq 'hosptal'}">
											<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a>
										</c:if>
									</div>
								</td>
							</tr>
							<tr><td height="5"></td><td></td></tr>
							<tr>
								<td class="t_title">预警疾病：</td>
								<td>
									<div class="select_del yjjb"><input type="text" name="diseasename" id="diseasename" style="width: 108px;" onclick="addDisease();" readonly="readonly"/><a href="javascript:void(0)" title="清除" class="select_img_del"  onclick="$('#diseaseid').val('');$('#diseasename').val('');"></a></div>						
									<input type="hidden" name="diseaseid" id="diseaseid" >
								</td>
							</tr>
							<tr>
								<td class="t_title">患者关键字：</td>
								<td>
									<input type="text" style="width: 108px;" id="patientName" name="patientName" class="auto-tip input_tip" data-tip="姓名/门诊号/住院号" value="${patientName}"/>	
								</td>
							</tr>								
							<tr><td height="5"></td><td></td></tr>
							<tr style="display: none;">
								<td class="t_title"><input type="checkbox" id="unReadMsg" name="unReadMsg" value="1"/></td>
								<td>
									<label for="unReadMsg">有未读干预消息</label>
								</td>
							</tr>
							<tr>
								<td class="t_title"><input type="checkbox" id="hideExclude" name="hideExclude" value="1"/></td>
								<td>
									<label for="hideExclude">隐藏“排除”标记患者</label>
								</td>
							</tr>
							<tr>
								<td class="t_title"><input type="checkbox" id="showURP" name="showURP" value="1"/></td>
								<td>
									<label for="showURP">只显示未报卡患者</label>
								</td>
							</tr>
							<!-- <tr>
								<td class="t_title"><input type="checkbox" id="showEWP" name="showEWP" value="1" checked="checked"/></td>
								<td>
									<label for="showEWP">只显示有预警疾病患者</label>	
								</td>
							</tr> -->				
						</tbody>
					</table>					
				</form>
			</div>
			<div data-options="region:'south',border:false" style="height:50px; border-top-width:1px;">	         		
				<div class="btn_center">
					<div class="n_btn_blue">
						<a href="javascript:;" onclick="query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
					</div>		
				</div>				
			</div>
		</div>
		
							
	</div>	
	<div data-options="region:'center',border:false" style="overflow: hidden;">		
		<div id="mainTB" class="m_search">
			<div class="m_search_last">					
				<div class="btn_r">					
					<div class="n_btn_grey"  style="display: none;">
						<a href="javascript:;"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
					</div>														
				</div>		
			</div>
		</div>
		<div id="datagridPlace" style="height:80%;width:100%;"></div>
		<div style="clear: both;"></div>
		<div id="s" style="height: 20%;border: 1px solid #dddddd;">
			<div class="datagrid-header" style="height: 32px;"><span style="line-height: 32px;padding-left: 5px;">预警依据</span></div>
			<div style="padding:5px;">
				<div style="padding-bottom: 5px;">
					<b>依据来源：</b>
					<span id="source">请先选择表格数据</span>
				</div>
				<div>
					<b>依据片断：</b><font id="content"></font>
					<!-- <div style="height: 100%;"  id="content"></div> -->
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>		
	</div>
	
	<div id="chooseDisease" >
		<div id="distool">
			<input id="diseaseKey" type="text" style="width: 100%;height:32px;" class="easyui-textbox" data-options="prompt:'输入疾病编号/疾病名称/助记码检索'" />
		</div>
		<table id="diseasisTable" style="height:396px;"></table>
	</div>

	<script type="text/javascript">
	$(function(){
		
		//疾病选择框
		$("#chooseDisease").dialog({
			title: "请选择疾病 <span style='font-size:12px;font-weight:normal;color:#e25050;'>(双击确定选择)</span>",
			width: 450,
		    height: 485,
			closed: true,
		    cache: false,
		    modal: true,
		    onClose:function(){
				$("#diseaseKey").textbox("setValue","");
			}
		});
		
		$("input",$("#diseaseKey").next("span")).keyup(function(){
	    	timedCount();
	    });
		 
		InitDsisGrid();
		
		conditions();
		
		//initDateGrid();
		
		//条件赋值
		setTimeout("initCondition()",1000);
		
	});
	function addDisease(){
		//新增前清空
		$('#diseaseid').val('');
		$('#diseasename').val('');
		//居中判断
		var top = document.body.scrollTop;
		var mtop = parseInt(($(window).height()-485)/2);
		if(mtop>0){
			top += mtop;
		}
		$('#chooseDisease').window('open').window('resize',{top: top});
		queryDisease();
	}
	function queryDisease(){
		var act_url="${webroot}/cdc/f_json/chooseDisease.shtml";
		var tv = $("input",$("#diseaseKey").next("span")).val();
		$('#diseasisTable').datagrid({
	        url: act_url,
	        queryParams: {
	     		'keyword': (tv=="输入疾病编号/疾病名称/助记码检索"?"":tv)
	        },
	        method:"post",
	        onLoadSuccess: function (data) {}
	    });
	}
	function InitDsisGrid(){
		$('#diseasisTable').datagrid({
			url:"${webroot}/cdc/f_json/chooseDisease.shtml",
			fitColumns:true,
			height:396,
			width:440,
			singleSelect:true,
			columns:[[
				{field:'diseaseid',width:80,title:'疾病编号'},
				{field:'diseasename',width:150,title:'疾病名称'},
				{field:'kindname',width:80,title:'疾病分类'},
				{field:'zjf',width:80,title:'首拼码'}
			]],
			onLoadSuccess: function (data) {
			},
			onDblClickRow:function(index,row){
				$("#diseaseid").val(row['diseaseid']);
				$("#diseasename").val(row['diseasename']);
				$("#chooseDisease").dialog("close");
			}
		});
	}
	function initDateGrid(){
		var code = $("#patientType :selected").val();
		if(code=="ZY"){
			initTableForZY();
		}else{
			initTableForMZ();
		}
	}
	function initTableForZY(){
		Csm.combogrid.dep({
			//【必传】控件名称
			id: 'deptId',
			//【可选参数】不传默认区session的医院ID
			hospId: '',
			<c:if test="${acType eq 'doctor'}">
				chargeManId: "${user.docNo}",
				onLoadSuccess:function(data){
					$('#deptId').combogrid('grid').datagrid('selectRecord',"${user.depNo}");
					var sed = $('#deptId').combogrid('grid').datagrid("getSelected");
					if(!sed){
						//没有选中当前科室，则选中第一行
						$('#deptId').combogrid('grid').datagrid('selectRow', 0);
					}
				},
			</c:if>
				
			//【可选参数】不传默认区所有监控科室
			dataType: 'zy'
			
		});
		$("#datagridPlace").datagrid({
            url: "",
            queryParams:{
            	"patientType":$("#patientType option:selected").val(),
				"dateType":$("#queryDateType option:selected").val(),
				"queryStartDate":$("#queryStartDate").val(),
				"queryEndDate":$("#queryEndDate").val(),
				"patientName":($("#patientName").val()=="姓名/门诊号/住院号"?"":$("#patientName").val()),
				"unReadMsg":$(":checkbox[name='unReadMsg']:checked").val(),
				"hideExclude":$(":checkbox[name='hideExclude']:checked").val(),
				"deptType":$("#deptType option:selected").val(),
				"yjdisease":$("#diseaseid").val(),
				"deptId":$("#deptId").combogrid("getValue"),
				"showEWP":1,
				"showURP":$(":checkbox[name='showURP']:checked").val()
            },
            type:"POST",
            toolbar:'#mainTB',
            fitColumns: false, 
            striped: true,
            singleSelect: true,
            rownumbers: true, 
            nowrap: true, 
            pagination: true,
            columns: [[
				{field: 'mzzyid', title: '${patientZyTitle}',width:100,formatter:function(value,row,index){
					return "<a href='javascript:void(0)' onclick=\"showDetail('"+value+"','');\">"+value+"</a>";
				}},
				{field: 'patientName', title: '患者',width:100,
					formatter:function(value,row,index){
						return [(row.patientName+'('+row.sex+','+row.age+row.ageUnit+')')].join('');
					}
				},
				{field: 'inhospat', title: '入院日期',width:100},
				{field: 'indeptname', title: '入院科室',width:100},
				{field: 'outAt', title: '出院日期',width:100},
				{field: 'outdeptname', title: '出院科室',width:100},
				{field: 'yjdisease', title: '预警疾病ID', hidden:true},
				{field: 'yjdiseaseName', title: '预警疾病'},
				{field: 'cardCount', title: '卡片数',align:'center',width:50,formatter:function(value,row,index){
						if(value==0){
							return "<a href='javascript:void(0)' >"+value+"</a>";
						}else{
							return "<a href='javascript:void(0)' onclick=\"crbbk('"+row.patientName+"','"+row.mzzyid+"','');\">"+value+"</a>";
						}
					}},
				{field: 'g', title: '疑似漏报迟报', formatter:function(value,row,index){
					if(row.iscb=="1"){
						return "<a style='background-color:#33ff66;padding:2px 3px;color:#fff;'>迟报</a>";
					}
					if(row.islb=="1"){
						return "<a style='background-color:red;padding:2px 3px;color:#fff;'>漏报</a>";
					}
				}},
				{field: 'iscb', title: '迟报', hidden:true},
				{field: 'islb', title: '漏报', hidden:true},
				{field: 'o', title: '操作',formatter:opts,width:60},
				{field: 'flag', title: '状态',width:50,
					styler: function(value,row,index) {
						if(value==1){
							return 'color:#FF4200;';
						}else if(value==2){
							return 'color:#C4C4C4;';
						}
					},
					formatter:function(value,row,index){
					if(value==1){
						return "已确认";
					}else if(value==2){
						return "已排除";
					}
				}},
				{field: 'flagbk', title: '状态',width:60,hidden:true,
					formatter:function(value,row,index){
					if(value==1){
						return "<a href='#' style='color:#FF4200' class='easyui-tooltip' id='"+row.mzzyid+"-"+row.yjdisease+"' >已确认<span class='arrow_down'></span></a>";
					}else if(value==2){
						return "<a href='#' style='color:#C4C4C4' class='easyui-tooltip' id='"+row.mzzyid+"-"+row.yjdisease+"' >已排除<span class='arrow_down'></span></a>";
					}else{
						return "<a href='#' class='easyui-tooltip' id='"+row.mzzyid+"-"+row.yjdisease+"' >请选择<span class='arrow_down'></span></a>";
					}
				}},
				{field: 'm', title:'标记',width:60,formatter:function(value,row,index){
					if(!row['flag']){
						return "<a class='ico_select' title='确认' href='javascript:C_R(\""+row.mzzyid+"\",\""+row.yjdisease+"\",\"1\")'></a><a class='ico_stop' title='排除' href='javascript:C_R(\""+row.mzzyid+"\",\""+row.yjdisease+"\",\"2\")'></a>";
					}else if(row['flag']==1){
						return "<a class='ico_stop' title='排除' href='javascript:C_R(\""+row.mzzyid+"\",\""+row.yjdisease+"\",\"2\")'></a>";
					}else if(row['flag']==2){
						return "<a class='ico_select' title='确认' href='javascript:C_R(\""+row.mzzyid+"\",\""+row.yjdisease+"\",\"1\")'></a>";
					}
				}},
				{field: 'optName', title:'操作人'},
				{field: 'optDate', title:'操作时间'}
			]],
			onSelect:function(index,row){
				getYJInfo(row);
			},
			onLoadSuccess:function(data){
				for (var i = 0; i < data.rows.length; i++) {
					var row = data.rows[i];
					var id = row.mzzyid;
					var disease = row.yjdisease;
					downMenu(id,disease);
				}
				if(data.rows.length>0){
					$(this).datagrid("selectRow",0);
				}
			}
		});
		setTimeout("query()",500);
	}
	function initTableForMZ(){
		Csm.combogrid.dep({
			//【必传】控件名称
			id: 'deptId',
			//【可选参数】不传默认区session的医院ID
			hospId: '',
			<c:if test="${acType eq 'doctor'}">
				chargeManId: "${user.docNo}",
				onLoadSuccess:function(data){
					$('#deptId').combogrid('grid').datagrid('selectRecord',"${user.depNo}");
					var sed = $('#deptId').combogrid('grid').datagrid("getSelected");
					if(!sed){
						//没有选中当前科室，则选中第一行
						$('#deptId').combogrid('grid').datagrid('selectRow', 0);
					}
				},
			</c:if>
			//【可选参数】不传默认区所有监控科室
			dataType: 'mz'
		});
		$("#datagridPlace").datagrid({
            url: "",
            type:"POST",
            queryParams:{
            	"patientType":$("#patientType option:selected").val(),
				"dateType":$("#queryDateType option:selected").val(),
				"queryStartDate":$("#queryStartDate").val(),
				"queryEndDate":$("#queryEndDate").val(),
				"patientName":($("#patientName").val()=="姓名/门诊号/住院号"?"":$("#patientName").val()),
				"unReadMsg":$(":checkbox[name='unReadMsg']:checked").val(),
				"hideExclude":$(":checkbox[name='hideExclude']:checked").val(),
				"deptType":$("#deptType option:selected").val(),
				"yjdisease":$("#diseaseid").val(),
				"deptId":$("#deptId").combogrid("getValue"),
				"showEWP":1,
				"showURP":$(":checkbox[name='showURP']:checked").val()
            },
            toolbar:'#mainTB',
            fitColumns: false, 
            striped: true,
            singleSelect: true,
            rownumbers: true, 
            nowrap: true, 
            pagination: true,
            columns: [[
  					{field: 'mzzyid', title: '门诊号',width:120,formatter:function(value,row,index){
  						return "<a href='javascript:void(0)' onclick=\"showDetail('','"+value+"');\">"+value+"</a>";
  					}},
  					{field: 'patientName', title: '姓名',width:100, formatter:function(value,row,index){
						return [(row.patientName+'('+row.sex+','+row.age+row.ageUnit+')')].join('');
					}},
  					{field: 'age', title: '年龄', hidden:true},
  					{field: 'ageUnit', title: '年龄单位', hidden:true},
  					{field: 'isreturnvisit', title: '复诊',align:'center',width:40,formatter:function(value,row,index){
						if(value==1){
							return "是";
						}else if(value==0){
							return "否";
						}else{
							return "否";
						}
					}},
  					{field: 'diagnosisDt', title: '就诊日期',width:100},
  					{field: 'doctorId', title: '就诊医生ID', hidden:true},
  					{field: 'doctorName', title: '就诊医生',width:60},
  					{field: 'deptId', title: '就诊科室ID', hidden:true},
  					{field: 'deptName', title: '就诊科室',width:100},
  					{field: 'yjdisease', title: '预警疾病ID', hidden:true},
  					{field: 'yjdiseaseName', title: '预警疾病',width:120},
  					{field: 'cardCount', title: '卡片数',align:'center',width:50,formatter:function(value,row,index){
  						if(value==0){
  							return "<a href='javascript:void(0)' >"+value+"</a>";
  						}else{
  							return "<a href='javascript:void(0)' onclick=\"crbbk('"+row.patientName+"','','"+row.mzzyid+"');\">"+value+"</a>";
  						}
  					}},
  					{field: 'g', title: '疑似漏报迟报',width:80, formatter:function(value,row,index){
  						if(row.iscb=="1"){
  							return "<a style='background-color:#33ff66;padding:2px 3px;color:#fff;'>迟报</a>";
  						}
  						if(row.islb=="1"){
  							return "<a style='background-color:red;padding:2px 3px;color:#fff;'>漏报</a>";
  						}
  					}},
  					{field: 'iscb', title: '迟报', hidden:true},
  					{field: 'islb', title: '漏报', hidden:true},
  					{field: 'h', title: '操作', formatter:opts,width:60},
  					{field: 'flag', title: '状态',width:50,
						styler: function(value,row,index) {
							if(value==1){
								return 'color:#FF4200;';
							}else if(value==2){
								return 'color:#C4C4C4;';
							}
						},
						formatter:function(value,row,index){
						if(value==1){
							return "已确认";
						}else if(value==2){
							return "已排除";
						}
					}},
					{field: 'fff', title: '状态',width:80,hidden:true,
						styler: function(value,row,index) {
							if(value==1){
								return 'color:#FF4200;';
							}else if(value==2){
								return 'color:#C4C4C4;';
							}
						},
						formatter:function(value,row,index){
							if(value==1){
								return "<a href='#' style='color:#FF4200' class='easyui-tooltip' id='"+row.mzzyid+"-"+row.yjdisease+"' >已确认<span class='arrow_down'></span></a>";
							}else if(value==2){
								return "<a href='#' style='color:#C4C4C4' class='easyui-tooltip' id='"+row.mzzyid+"-"+row.yjdisease+"' >已排除<span class='arrow_down'></span></a>";
							}else{
								return "<a href='#' class='easyui-tooltip' id='"+row.mzzyid+"-"+row.yjdisease+"' >请选择<span class='arrow_down'></span></a>";
							} 	
					}},
  					{field: 'm', title:'标记',formatter:function(value,row,index){
						if(!row['flag']){
							return "<a class='ico_select' title='确认' href='javascript:C_R(\""+row.mzzyid+"\",\""+row.yjdisease+"\",\"1\");'></a><a class='ico_stop' title='排除' href='javascript:C_R(\""+row.mzzyid+"\",\""+row.yjdisease+"\",\"2\");'></a>";
						}else if(row['flag']==1){
							return "<a class='ico_stop' title='排除' href='javascript:C_R(\""+row.mzzyid+"\",\""+row.yjdisease+"\",\"2\");'></a>";
						}else if(row['flag']==2){
							return "<a class='ico_select' title='确认' href='javascript:C_R(\""+row.mzzyid+"\",\""+row.yjdisease+"\",\"1\");'></a>";
						}
					}},
					{field: 'optName', title:'操作人'},
					{field: 'optDate', title:'操作时间'}
  				]],
			onSelect:function(index,row){
				getYJInfo(row);
			},
			onLoadSuccess:function(data){
				for (var i = 0; i < data.rows.length; i++) {
					var row = data.rows[i];
					var id = row.mzzyid;
					var disease = row.yjdisease;
					downMenu(id,disease);
				}
				if(data.rows.length>0){
					$(this).datagrid("selectRow",0);
				}
			}
		});
		setTimeout("query()",500);
	}
	function opts(value,row,index){
		//获取未读消息记录
		/* var str = "<a title='上报' class='ico_up'></a>";
		str += "<a title='患者详情' class='ico_view'></a>"; */
		var str = "";
		if($("#patientType option:selected").val()=="MZ"){
			str = "<a title='干预消息' class='ico_mail' onclick=\"sendMessage('','"+row.mzzyid+"');\"></a>";
		}else{
			str = "<a title='干预消息' class='ico_mail' onclick=\"sendMessage('"+row.mzzyid+"','');\"></a>";
		};
		if(row['unReadMsg']>0){
			str += "<span id='' class='gyTip'>"+row['unReadMsg']+"</span>";
		}
		<c:if test="${OR eq '1'}">
			str += "<a href='javascript:crbbk(\""+row.patientName+"\",\""+row.mzzyid+"\",\""+row.mzzyid+"\");' class='ico_up_green' title='公卫卡上报'></a>";
		</c:if>
		return str;
	}
	function sendMessage(zyid,mzid){
		var activeUrl = "";
		if(!zyid || zyid=="undefined"){
			activeUrl = "${webroot}/nyMessageTheme/f_view/toedit.shtml?mzid="+mzid+"&msgType=1";
		}else{
			activeUrl = "${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid="+zyid+"&msgType=1";
		}
		Comm.dialogGlobal({
	    	url:activeUrl,
	        title: "干预会话",
	        width:870,
	        height:555,
	        type:"iframe"
	    });
	}
	function query(){
		if(!$("#queryStartDate").val() || !$("#queryEndDate").val()){
			alert("请将日期填写正确！");
			return;
		}
		//查询时清空预警依据
		$("#source").html("请先选择表格数据");
		$("#content").html("");
		
		var act_url = '${webroot}/cdc/f_json/suspectedCaseData.shtml';
		$("#datagridPlace").datagrid({
	           url: act_url,
	           queryParams: {
	        	    "patientType":$("#patientType option:selected").val(),
					"dateType":$("#queryDateType option:selected").val(),
					"queryStartDate":$("#queryStartDate").val(),
					"queryEndDate":$("#queryEndDate").val(),
					"patientName":($("#patientName").val()=="姓名/门诊号/住院号"?"":$("#patientName").val()),
					"unReadMsg":$(":checkbox[name='unReadMsg']:checked").val(),
					"hideExclude":$(":checkbox[name='hideExclude']:checked").val(),
					"deptType":$("#deptType option:selected").val(),
					"yjdisease":$("#diseaseid").val(),
					"deptId":$("#deptId").combogrid("getValue"),
					"showEWP":1,
					"showURP":$(":checkbox[name='showURP']:checked").val()
	           },
	           pageNumber: 1
	       });
	}
	function getYJInfo(row){
		$("#source").html(row.yjsource);
		$("#content").html(row.yjcontent);
	}
	function conditions(){
		var pt = $("#patientType option:selected").val();
		if(pt=="MZ"){
			//门诊条件（日期类型）
			$("#zydt").hide();
			$("#mzdt").show();
			$("#queryDateType option:first").prop("selected", 'selected');
			//科室类型
			$("#zydept").hide();
			$("#mzdept").show();
			$("#deptType option:first").prop("selected", 'selected');
		}else{
			$("#mzdt").hide();
			$("#zydt").show();
			$("#queryDateType option:nth-child(1)").prop("selected", 'selected');
			$("#mzdept").hide();
			$("#zydept").show();
			$("#deptType option:nth-child(1)").prop("selected", 'selected');
		}
		/* if($('#deptId').combogrid()){
			$('#deptId').combogrid('grid').datagrid("unselectAll");
			//$('#deptId').combogrid('clear');
		} */
		initDateGrid();
		//setTimeout("initDateGrid()", 1000);
	}
	var t;
	function timedCount() {
		if (t) {
		clearTimeout(t);
		}
		t = setTimeout(submitEvent, 1000);
	}
	function submitEvent() {
		queryDisease();
	}
	function C_R(mzzyid,diseaseid,operId){
		var m = operId;
		$.ajax({
			url:"${webroot}/cdc/f_json/confirmANDexclude.shtml",
			data:{
				'mzzyid':mzzyid,
				'diseaseid':diseaseid,
				'status':m
			},
			success:function(data){
				data = eval("("+data+")");
				if(data.result=='success'){
					$.messager.show({ title: '提示', msg: data.msg });
					<c:if test="${ISDB==1}">
						if(m==1){
							reportLater(mzzyid);
						}else if(m==2){
							removeDbRecord(mzzyid);
						}
					</c:if>
				}else{
					$.messager.show({ title: '提示', msg: '操作失败！错误信息：'+data.extraValue });
				}
				query();
			},
			error:function(){
				alert("抱歉，操作失败！");
			}
		});
	}
	function showDetail(zyid,mzid){
		if(!zyid){
	    	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecordsMz.shtml?mzid='+mzid+"&tab=1",true);
		}else if(!mzid){
	    	parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
		}
    }
	
	function crbbk(pname,zyid,mzid){
		//zyid传过来会变成undefined，这里置空；
		var tid = "";
		var url = "${webroot}/cdc/f_view/toChooseCard.shtml?ownership=${param.ownership}&isShowCards=hide";
		if($("#patientType option:selected").val()=="MZ"){
			zyid="";
			tid = mzid;
			url += "&mzid="+tid;
		}else{
			mzid="";
			tid = zyid;
			url += "&zyid="+tid;
		}
		Comm.dialogGlobal({
	    	url:url,
	    	title: "病例上报 - 病人:"+pname+"("+tid+")	 - 已报卡列表",
	        width:850,
	        type:"iframe",
	        height:430
	    });
	}
	function downMenu(mzzyid,yjdisease){
		var tmpDiv = '<ul class="down_li">';
		
		tmpDiv += '<li><a href="javascript:void(0);" onclick="cb(\''+mzzyid+'\',\''+yjdisease+'\',\'1\')">确定</a></li>';
		tmpDiv += '<li><a href="javascript:void(0);" onclick="cb(\''+mzzyid+'\',\''+yjdisease+'\',\'2\')">排除</a></li>';
		
		tmpDiv += '</ul>';
		
		//tmpDiv += '<li><a href="#" onclick="javascript:sendMessage(\''+divId+'\',\''+deptId+'\',\''+defContent+'\')">干预</a></li>';
		//tmpDiv += '<li><a href="#" '+(infectTypeId==2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',1,\'院感\');">院感</a></li>';
		//tmpDiv += '<li><a href="#" '+(infectTypeId==1?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',2,\'社感\');">社感</a></li>';
		//tmpDiv += '<li><a href="#" '+(infectTypeId<=2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',3,\'定值\');">定值</a></li>';
		//tmpDiv += '<li><a href="#" '+(infectTypeId<=2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',4,\'污染\');">污染</a></li>';
		
		$('#' + mzzyid+"-"+yjdisease).tooltip({
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
	//
	function cb(mzzyid,yjdisease,type){
		$('#'+mzzyid+'-'+yjdisease).trigger('mouseleave');
		setTimeout("C_R('"+mzzyid+"','"+yjdisease+"','"+type+"')",200);
	}
	function initCondition(){
		<c:if test="${!empty patientType}">
			$("#patientType").val("${patientType}");
		</c:if>
		<c:if test="${!empty dateType}">
			$("#queryDateType").val("${dateType}");
		</c:if>
		<c:if test="${!empty deptType}">
			$("#deptType").val("${deptType}");
		</c:if>
	}
	function reportLater(mzzyid){
		var pt = $("#patientType option:selected").val();
		$.ajax({
			url:"${webroot}/cdc/f_json/saveTodoList.shtml",
			data:{
				"mzzyid":mzzyid,
				"patientType":pt
			},
			type:"POST",
			success:function(data){
				data = eval("("+data+")");
				if(data.result=="error"){
					$.messager.show({ title: '提示', msg: '保存待报记录失败！' });
				}
			},error:function(){
				alert("抱歉，添加待报出现错误！");
			}
		});
	}
	function removeDbRecord(mzzyid){
		var pt = $("#patientType option:selected").val();
		$.ajax({
			url:"${webroot}/cdc/f_json/removeTodoList.shtml",
			data:{
				"mzzyid":mzzyid,
				"patientType":pt
			},
			type:"POST",
			success:function(data){
				data = eval("("+data+")");
				if(data.result=="error"){
					$.messager.show({ title: '提示', msg: '移除待报记录失败！' });
				}
			},error:function(){
				alert("抱歉，移除待报出现错误！");
			}
		});
	}
	</script>
</body>
</html>