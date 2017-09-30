<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>疑似病例搜索</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css${version}" />
<style type="text/css">
	.panel-body{border-width:0px;}
	.fake-combobox{position: relative;position: relative;padding-top: 2px;padding-bottom: 5px;border: 1px solid #ddd;left: -20px;}
</style>
</head>
<body class="easyui-layout">

	<div data-options="region:'west',border:false,title:'查询条件'" style="width:240px;">
		<div class="easyui-layout" data-options="fit:true">              
			<div data-options="region:'center',border:false">
				<div id="mainTB">
					<form id="suspectForm"> 
					<table class="table_cx" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="t_title">患者类别：</td>
								<td>
									<select id="patientType" name="patientType" onchange="conditions();initDateGrid();" style="width:120px;">
										<option value="1">门诊</option>
										<option value="2">住院</option>
									</select><span class="fake-combobox"></span>
								</td>
							</tr>
							<tr><td height="5"></td><td></td></tr>
							<tr>
								<td class="t_title">日期类型：</td>
								<td>
									<select id="queryDateType" name="dateType" style="width:120px;" >
										<optgroup id="mzdt" label="门诊">
											<option value="1">就诊日期</option>
										</optgroup>
										<optgroup id="zydt" label="住院">
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
									<select id="deptType" name="deptType" style="width: 120px;" >
										<optgroup id="mzdept" label="门诊">
											<option value="1">就诊科室</option>
										</optgroup>
										<optgroup id="zydept" label="住院">
											<option value="2">入院科室</option>
											<option value="3">出院科室</option>
											<option value="4">所在科室</option>
										</optgroup>
									</select><span class="fake-combobox"></span>
								</td>
							</tr>
							<tr>
								<td class="t_title">科室：</td>
								<td>
									<div class="select_del"><input class="easyui-combobox" name="deptId" id="deptId" style="width: 120px;"><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a></div>
								</td>
							</tr>
							<tr><td height="5"></td><td></td></tr>
							<tr>
								<td class="t_title">检验指标：</td>
								<td>
									<div class="select_del"><input id="chooseJY" name="JYZB" style="width: 120px;"><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#chooseJY').combo('clear');"></a></div>					
								</td>
							</tr>
							<tr>
								<td class="t_title">患者关键字：</td>
								<td>
									<input type="text" style="width: 108px;" id="patientName" name="patientName" class="auto-tip input_tip" data-tip="姓名/门诊号/住院号"/>							
								</td>
							</tr>
							<tr>
								<td class="t_title">诊断关键字：</td>
								<td>
									<input type="text" style="width: 108px;" id="diagnosisName" name="diagnosisName" class="auto-tip input_tip" data-tip="请输入诊断关键字"/>
								</td>
							</tr>
							<tr>
								<td class="t_title">影像关键字：</td>
								<td>
									<input type="text" style="width: 108px;" id="imageQueryStr" name="imageQueryStr"  class="auto-tip input_tip" data-tip="请输入影像关键字" />
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
						</tbody>
					</table>					
					</form>	
				</div>
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
	<div data-options="region:'center',border:false" style="border-left-width: 1px;overflow: hidden;">		
		<div id="tb" class="m_search"> 
			<div class="m_search_last">					
				<div class="btn_r">					
					<div class="n_btn_grey">
						<a href="javascript:;" onclick="excludeCheckedRow(1);"><span><i class="icon iconfont">&#xe607;</i>批量确认</span></a>
					</div>
					<div class="n_btn_grey">
						<a href="javascript:;" onclick="excludeCheckedRow(-1);"><span><i class="icon iconfont">&#xe669;</i>批量排除</span></a>
					</div>	
					<div class="n_btn_grey" style="display: none;">
						<a href="javascript:;"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
					</div>														
				</div>		
			</div>
		</div>
		<div id="datagridPlace"></div>
	</div>

	<script type="text/javascript">
		$(function(){
			
			conditions();
			$("#chooseJY").combogrid({
			    idField:'orderno',
			    textField:'itemnamevalue',
			    url:'${webroot}/cdc/f_json/jydata.shtml',
			    multiple:true,
			    panelWidth:300,
			    fitColumns:true,
			    singleSelect:false,
			    checkOnSelect:true,
			    selectOnCheck:true,
			    toolbar:'#toolbar', 
			    columns:[[
				    {field:'ck',title:'选择',checkbox:true,width:60},
				    {field:'orderno',title:'检验指标编号',hidden:true},
				    {field:'itemnamevalue',title:'检验指标',width:150}
			    ]]
			});
			Csm.combogrid.dep({
				//【必传】控件名称
				id: 'deptId',
				//【可选参数】不传默认区session的医院ID
				hospId: '',
				//【可选参数】不传默认区所有监控科室
				dataType: 'mz'
			});
			
			initDateGrid();
			
		});
		function initDateGrid(){
			var code = $("#patientType option:selected").val();
			//var code = $("#patientType").combobox("getValue");
			if(code==2){
				query4zy();
			}else{
				query4mz();
			}
		}
		function query4mz(){
			$("#datagridPlace").datagrid({
	            url: "${webroot}/cdc/f_view/YSBLSearchDate.shtml",
	            toolbar:'#tb',
	            fit: true, 
	            type:"POST",
				queryParams:{
					"patientType":$("#patientType option:selected").val(),
					"dateType":$("#queryDateType option:selected").val(),
					"startTime":$("#queryStartDate").val(),
					"endTime":$("#queryEndDate").val(),
					"patientName":$("#patientName").val(),
					"unReadMsg":$("input[name='unReadMsg']:checked").val(),
					"hideExclude":$("input[name='hideExclude']:checked").val(),
					"deptType":$("#deptType option:selected").val(),
					"diagnosisName":$("#diagnosisName").val(),
					"deptId":$("#deptId").combogrid("getValue"),
					"JYZB":$("#chooseJY").combogrid("getValues").toString(),
					"imageQueryStr":$("#imageQueryStr").val()
				},
	            fitColumns: true, 
	            striped: true,
	            singleSelect: true,
	            rownumbers: true, 
	            nowrap: true, 
	            pagination: true,
	            checkOnSelect: false, 
	            selectOnCheck: false,
	            columns: [[
					{field:'ck',checkbox:true},
					{field: 'id', title: 'ID',hidden:true},
					{field: 'mzid', title: '门诊号',width:150,formatter:function(value,row,index){
						return "<a href='javascript:void(0)' onclick=\"showDetail('','"+value+"');\">"+value+"</a>";
					}},
					{field: 'patientName', title: '患者',width:150,
						formatter:function(value,row,index){
    						return [(row.patientName+'('+row.sex+','+row.age+row.ageUnit+')')].join('');
    					}
					},					
					{field: 'isreturnvisit', title: '复诊',align:'center',width:80,formatter:function(value,row,index){
						if(value==1){
							return "是";
						}else if(value==0){
							return "否";
						}else{
							return "否";
						}
					}},
					{field: 'startDt', title: '发病日期',align:'center',width:150},
					{field: 'diagnosisDt', title: '就诊日期',align:'center',width:180},
					{field: 'doctorName', title: '就诊医生',align:'center',width:80},
					{field: 'deptName', title: '就诊科室',width:150},
					{field: 'diagnosisName', title: '门诊诊断',width:200},
					{field: 'cardCount', title: '卡片数',align:'center',width:60,formatter:function(value,row,index){
						if(value==0){
							return "<a href='javascript:void(0)'>"+value+"</a>";
						}else{
							return "<a href='javascript:void(0)' onclick=\"crbbk('"+row.patientName+"','','"+row.mzid+"');\">"+value+"</a>";
						}
					}},
					{field: 'unReadMsg', title: '未读消息数',hidden:true},
					{field: 'g', title: '操作',formatter:opts,width:80},
					{field: 'xxxx', title: '状态',width:80,hidden:true,
						styler: function(value,row,index) {
							if(value==1){
								return 'color:#FF4200;';
							}else if(value==-1){
								return 'color:#C4C4C4;';
							}
						},
						formatter:function(value,row,index){
						if(value==1){
							return "已确认";
						}else if(value==-1){
							return "已排除";
						}
					}},
					{field: 'isMarked', title: '状态',width:80,
						styler: function(value,row,index) {
							if(value==1){
								return 'color:#FF4200;';
							}else if(value==2){
								return 'color:#C4C4C4;';
							}
						},
						formatter:function(value,row,index){
						if(value==1){
							return "<a href='#' style='color:#FF4200' class='easyui-tooltip' id='"+row.mzid+"' >已确认<span class='arrow_down'></span></a>";
						}else if(value==-1){
							return "<a href='#' style='color:#C4C4C4' class='easyui-tooltip' id='"+row.mzid+"' >已排除<span class='arrow_down'></span></a>";
						}else{
							return "<a href='#' class='easyui-tooltip' id='"+row.mzid+"' >请选择<span class='arrow_down'></span></a>";
						}
					}},
					{field: 'h', title:'标记',width:100,hidden:true,formatter:function(value,row,index){
						if(!row['isMarked']){
							return "<a class='ico_select' title='确认' href='javascript:excludeCheckedRow(1)'></a><a class='ico_stop' title='排除' href='javascript:excludeCheckedRow(-1)'></a>";
						}else if(row['isMarked']==1){
							return "<a class='ico_stop' title='排除' href='javascript:excludeCheckedRow(-1)'></a>";
						}else if(row['isMarked']==-1){
							return "<a class='ico_select' title='确认' href='javascript:excludeCheckedRow(1)'></a>";
						}
					}}
				]],
				onClickRow:function(){
					
				},
				onLoadSuccess:function(data){
					for (var i = 0; i < data.rows.length; i++) {
						var row = data.rows[i];
						var id = row.mzid;
						downMenu(id);
					}
				}
			});
		}
		
		function downMenu(mzzyid){
			var tmpDiv = '<ul class="down_li">';
			
			tmpDiv += '<li><a href="javascript:void(0);" onclick="excludeCheckedRow(1)">确定</a></li>';
			tmpDiv += '<li><a href="javascript:void(0);" onclick="excludeCheckedRow(-1)">排除</a></li>';
			
			tmpDiv += '</ul>';
			
			//tmpDiv += '<li><a href="#" onclick="javascript:sendMessage(\''+divId+'\',\''+deptId+'\',\''+defContent+'\')">干预</a></li>';
			//tmpDiv += '<li><a href="#" '+(infectTypeId==2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',1,\'院感\');">院感</a></li>';
			//tmpDiv += '<li><a href="#" '+(infectTypeId==1?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',2,\'社感\');">社感</a></li>';
			//tmpDiv += '<li><a href="#" '+(infectTypeId<=2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',3,\'定值\');">定值</a></li>';
			//tmpDiv += '<li><a href="#" '+(infectTypeId<=2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',4,\'污染\');">污染</a></li>';
			
			$('#' + mzzyid).tooltip({
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
		
		function query4zy(){
			$("#datagridPlace").datagrid({
				toolbar:'#tb',
	            url: "${webroot}/cdc/f_view/YSBLSearchDate.shtml",
	            queryParams:{
	            	"patientType":$("#patientType option:selected").val(),
					"dateType":$("#queryDateType option:selected").val(),
					"startTime":$("#queryStartDate").val(),
					"endTime":$("#queryEndDate").val(),
					"patientName":$("#patientName").val(),
					"unReadMsg":$("input[name='unReadMsg']:checked").val(),
					"hideExclude":$("input[name='hideExclude']:checked").val(),
					"deptType":$("#deptType option:selected").val(),
					"diagnosisName":$("#diagnosisName").val(),
					"deptId":$("#deptId").combogrid("getValue"),
					"JYZB":$("#chooseJY").combogrid("getValues").toString(),
					"imageQueryStr":$("#imageQueryStr").val()
	            },
	            fit: true, 
	            fitColumns: true, 
	            singleSelect: true,
	            rownumbers: true, 
	            nowrap: true, 
	            pagination: true,
	            checkOnSelect: false, 
	            selectOnCheck: false,
	            columns: [[
					{field:'ck',checkbox:true},
					{field: 'zyid', title: '住院号',width:150,formatter:function(value,row,index){
						return "<a href='javascript:void(0)' onclick=\"showDetail('"+value+"','');\">"+value+"</a>";
					}},
					{field: 'patientName', title: '姓名',width:150,
						formatter:function(value,row,index){
    						return [(row.patientName+'('+row.sex+','+row.age+row.ageUnit+')')].join('');
    					}
					},
					{field: 'sex', title: '性别', hidden:true},
					{field: 'age', title: '年龄', hidden:true},
					{field: 'ageUnit', title: '年龄单位',hidden:true},
					{field: 'inHospAt', title: '入院日期',width:170},
					{field: 'inDeptName', title: '入院科室',width:140},
					{field: 'iHD', title: '入院诊断',width:250},
					{field: 'outAt', title: '出院日期',width:170},
					{field: 'outDeptName', title: '出院科室',width:140},
					{field: 'oHD', title: '出院诊断',width:250},
					{field: 'cardCount', title: '卡片数',align:'center',width:70,formatter:function(value,row,index){
						if(value==0){
							return "<a href='javascript:void(0)'>"+value+"</a>";
						}else{
							return "<a href='javascript:void(0)' onclick=\"crbbk('"+row.patientName+"','"+row.zyid+"','');\">"+value+"</a>";
						}
					}},
					{field: 'g', title: '操作',formatter:opts,width:85},
					{field: 'ccccc', title: '状态', hidden:true,
						styler: function(value,row,index) {
							if(value==1){
								return 'color:#FF4200;';
							}else if(value==-1){
								return 'color:#C4C4C4;';
							}
						},
						formatter:function(value,row,index){
						if(value==1){
							return "已确认";
						}else if(value==-1){
							return "已排除";
						}
					}},
					{field: 'isMarked', title: '状态',width:90,
						styler: function(value,row,index) {
							if(value==1){
								return 'color:#FF4200;';
							}else if(value==2){
								return 'color:#C4C4C4;';
							}
						},
						formatter:function(value,row,index){
						if(value==1){
							return "<a href='#' style='color:#FF4200' class='easyui-tooltip' id='"+row.zyid+"' >已确认<span class='arrow_down'></span></a>";
						}else if(value==-1){
							return "<a href='#' style='color:#C4C4C4' class='easyui-tooltip' id='"+row.zyid+"' >已排除<span class='arrow_down'></span></a>";
						}else{
							return "<a href='#' class='easyui-tooltip' id='"+row.zyid+"' >请选择<span class='arrow_down'></span></a>";
						}
					}},
					{field: 'h', title:'标记',width:120,hidden:true,formatter:function(value,row,index){
						if(!row['isMarked']){
							return "<a class='ico_select' title='确认' href='javascript:excludeCheckedRow(1)'></a><a class='ico_stop' title='排除' href='javascript:excludeCheckedRow(-1)'></a>";
						}else if(row['isMarked']==1){
							return "<a class='ico_stop' title='排除' href='javascript:excludeCheckedRow(-1)'></a>";
						}else if(row['isMarked']==-1){
							return "<a class='ico_select' title='确认' href='javascript:excludeCheckedRow(1)'></a>";
						}
					}}
				]],
				onClickRow:function(){
					
				},
				onLoadSuccess:function(data){
					for (var i = 0; i < data.rows.length; i++) {
						var row = data.rows[i];
						var id = row.zyid;
						downMenu(id);
					}
				}
			});
		}
		function opts(value,row,index){
			//获取未读消息记录<a title='上报' class='ico_up'></a>
			var str = "";
			//str += "<a title='患者详情' class='ico_view'></a>";
			str += "<a title='干预消息' class='ico_mail' onclick=\"sendMessage('"+row.zyid+"','"+row.mzid+"');\"></a>";
			if(row['unReadMsg']>0){
				str += "<span id='' class='gyTip'>"+row['unReadMsg']+"</span>";
			}
			<c:if test="${OR eq '1'}">
				str += "<a href='javascript:crbbk(\""+row.patientName+"\",\""+row.zyid+"\",\""+row.mzid+"\");' class='ico_up_green' title='公卫卡上报'></a>";
			</c:if>
			return str;
		}
		function query(){
			var act_url = '${webroot}/cdc/f_view/YSBLSearchDate.shtml';
			$("#datagridPlace").datagrid({
		           url: act_url,
		           queryParams: {
		        	   "patientType":$("#patientType option:selected").val(),
						"dateType":$("#queryDateType option:selected").val(),
						"startTime":$("#queryStartDate").val(),
						"endTime":$("#queryEndDate").val(),
						"patientName":($("#patientName").val()=="姓名/门诊号/住院号"?"":$("#patientName").val()),
						"unReadMsg":$("input[name='unReadMsg']:checked").val(),
						"hideExclude":$("input[name='hideExclude']:checked").val(),
						"deptType":$("#deptType option:selected").val(),
						"diagnosisName":($("#diagnosisName").val()=="请输入诊断关键字"?"":$("#diagnosisName").val()),
						"deptId":$("#deptId").combogrid("getValue"),
						"JYZB":$("#chooseJY").combogrid("getValues").toString(),
						"imageQueryStr":($("#imageQueryStr").val()=="请输入影像关键字"?"":$("#imageQueryStr").val())
		           },
		           pageNumber: 1,
		           onLoadSuccess: function (data) {
		        	   for (var i = 0; i < data.rows.length; i++) {
							var row = data.rows[i];
							var id = "";
							var pt = $("#patientType option:selected").val();
							if(pt==1){
								id = row.mzid;
							}else{
								id = row.zyid;
							}
							downMenu(id);
						}
		           }
		       });
		}
		function conditions(){
			var pt = $("#patientType option:selected").val();
			//var pt = $("#patientType").combobox("getValue");
			if(pt==1){
				//门诊条件（日期类型）
				$("#zydt").hide();
				$("#mzdt").show();
				$("#queryDateType option:first").prop("selected", 'selected');
				//科室类型
				$("#zydept").hide();
				$("#mzdept").show();
				$("#deptType option:first").prop("selected", 'selected');
				Csm.combogrid.dep({
					//【必传】控件名称
					id: 'deptId',
					//【可选参数】不传默认区session的医院ID
					hospId: '',
					//【可选参数】不传默认区所有监控科室
					dataType: 'mz'
				});
			}else{
				$("#mzdt").hide();
				$("#zydt").show();
				$("#queryDateType option:nth-child(1)").prop("selected", 'selected');
				$("#mzdept").hide();
				$("#zydept").show();
				$("#deptType option:nth-child(1)").prop("selected", 'selected');
				Csm.combogrid.dep({
					//【必传】控件名称
					id: 'deptId',
					//【可选参数】不传默认区session的医院ID
					hospId: '',
					//【可选参数】不传默认区所有监控科室
					dataType: 'zy'
				});
			}
		}
		function excludeCheckedRow(operId){
			var ckdRows = $('#datagridPlace').datagrid('getChecked');
			var tip = "";
			if(operId==1){
				tip = "请确认是否确认当前已选中的记录？";
				if(ckdRows.length>0){
					tip ="请确认是否确认当前所有已勾选的记录？";
				}
			}else{
				tip = "请确认是否排除当前已选中的记录？";
				if(ckdRows.length>0 ){
					tip ="请确认是否排除当前所有已勾选的记录？";
				}
			}
			$.messager.confirm('提示', tip, function (r) {
				if(r==false){
					return;
				}
				var ids = "";
				var pt = $("#patientType option:selected").val();
				//var pt = $("#patientType").combobox("getValue");
				var dt = "";
				if(ckdRows.length>0){
					if(pt==1){
						//mz
						for(var x=0;x<ckdRows.length;x++){
							ids += ckdRows[x].mzid+",";
						}
						dt="2";
					}else{
						//zy
						for(var x=0;x<ckdRows.length;x++){
							ids += ckdRows[x].zyid+",";
						}
						dt="1";
					}
				}else{
					var slcRow = $('#datagridPlace').datagrid('getSelected');
					if(pt==1){
						ids = slcRow.mzid;
						dt="2";
					}else{
						ids = slcRow.zyid;
						dt="1";
					}
				}
				$.ajax({
					url:"${webroot}/cdc/f_json/excludeRows.shtml",
					data:{
						'ids':ids,
						'dataType':dt,
						'operId':operId
					},
					success:function(data){
						data = eval("("+eval("("+data+")")+")");
						var m = data.msg;
						$.messager.show({ title: '提示', msg: data.msg });
						//query();
						$(".pagination-load").trigger("click"); //只刷新当前分页
					},error:function(){
						$.messager.show({ title: '提示', msg: '操作失败！' });
					}
				});
				
			});
			
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
			if($("#patientType option:selected").val()=="1"){
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
	</script>
</body>
</html>