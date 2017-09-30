<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上报历史记录查询界面</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css/cdcCustom.css?x=${version}" />
<style type="text/css">
	.panel-body{border-width:0px;}
</style>
</head>
<body class="easyui-layout" style="border:0px;">

	<div data-options="region:'west',border:false,title:'查询条件'" style="width:240px;">
		<div class="easyui-layout" data-options="fit:true">              
			<div data-options="region:'center',border:false">
				<div id="mainTB">
					<form id="suspectForm"> 
					<table class="table_cx" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="t_title">报卡类型：</td>
								<td>
									<%--  
										<nis:select id="cardType" name="cardType" dictcode="cdc_card_type" cssCls="easyui-combobox" exp="style=\"width: 120px;\" "/>
									 --%>
									 <select name="cardType" id="cardType" onchange="query();" style="width:120px;" class="easyui-combobox">
										<c:forEach items="${allowCards}" var="ac">
											<c:if test="${fn:contains(cdcScope,ac.dictCode) or fn:contains(cdcScope,'all')}">
												<option value="${ac.dictCode}" ${ac.dictCode eq cardType ? 'selected="selected"' : ''}>${ac.dictName}</option>
											</c:if>
											<%-- <option value="${ac.dictCode}">${ac.dictName}</option> --%>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td class="t_title">报卡状态：</td>
								<td>
									<select name="cardStates" id="cardStates" style="width:120px;" class="easyui-combobox">
										<option value="-1">全部</option>
										<option value="0">未审核</option>
										<option value="1">已审核</option>
										<option value="2">已退卡</option>
										<option value="3">已删卡</option>
									</select>
								</td>
							</tr>
							<tr><td height="5"></td><td></td></tr>
							<tr>
								<td class="t_title">日期类型：</td>
								<td>
									<select id="dateType" style="width:120px;" class="easyui-combobox">
										<option value="1">上报日期</option>
										<option value="2">审核日期</option>
									</select>
								</td>
							</tr>				
							<tr>
								<td class="t_title">开始日期：</td>
								<td>
									<input type="text" id="queryStartDate" name="queryStartDate" class="Wdate text" style="width:108px;" value="${queryStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
								</td>
							</tr>					
							<tr>
								<td class="t_title">结束日期：</td>
								<td>
									<input type="text" id="queryEndDate" name="queryEndDate" class="Wdate text" style="width:108px;"  value="${queryEndDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr><td height="5"></td><td></td></tr>					
							<tr>
								<td class="t_title">上报科室：</td>
								<td>
									<div class="select_del">
										<input class="easyui-combobox" name="reportdeptid" id="deptId" style="width: 120px;"/>
										<c:if test="${acType eq 'hospital'}">
										<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#deptId').combo('clear');"></a>
										</c:if>
									</div>
								</td>
							</tr>					
							<tr>
								<td class="t_title">上报医生：</td>
								<td>
									<div class="select_del">
										<input class="easyui-combobox" name="reportdoctorid" id="doctorId" style="width: 120px;" >
										<c:if test="${acType eq 'hospital'}">
										<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#doctorId').combo('clear');"></a>
										</c:if>
									</div>
								</td>
							</tr>
							<tr>
								<td class="t_title">患者关键字：</td>
								<td>
									<input type="text" style="width: 108px;" id="searchString" name="searchString" class="auto-tip input_tip" data-tip="姓名/门诊号/${patientZyTitle}"/>
								</td>
							</tr>
							<tr style="display: none;">
								<td class="t_title"><input type="checkbox" id="unReadMsg" name="unReadMsg" value="1"/></td>
								<td>
								<label for="unReadMsg">有未读干预消息</label>
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
						<a href="javascript:;" id="toTrigger" class="toTrigger" onclick="query();"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
					</div>		
				</div>				
			</div>
		</div>
		
	</div>	
	<div id="tbdiv" data-options="region:'center',border:false" style="border-left-width:1px; overflow: hidden;display: none;">		
		<div id="toolBar" class="m_search"> 
			<div class="m_search_last">					
				<div class="btn_r">						
					<div class="n_btn_grey">
						<a href="javascript:void(0);" onclick="ExportXls();"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
					</div>														
				</div>		
			</div>
		</div>
		<div id="cardDatagrid"></div>
	</div>
	<div id="printCardChooseDialog" style="text-align: center;vertical-align: middle;display: none;">
		<input type="hidden" id="mstId" />
		<input type="hidden" id="sbId" />
		<div style="margin-bottom: 5px;margin-top:5px;"><input id="normalCrbCard" type="button" value="打印普通传染病报卡" onclick="var x=$('#mstId').val();var y=$('#sbId').val();printSingle('crbbk',x,y);$('#printCardChooseDialog').dialog('close');" style="padding: 3px 10px;"/></div>
		<div style="margin-bottom: 5px;"><input id="YGCrbCard" type="button" value="打印乙肝传染病报卡" title="非乙肝报卡，不能打印" onclick="var x=$('#mstId').val();var y=$('#sbId').val();printSingleHBV(x,y);$('#printCardChooseDialog').dialog('close');" disabled="disabled" style="padding: 3px 10px;color:gray;"/></div>
		<div style="margin-bottom: 5px;"><input id="XBCrbCard" type="button" value="打印性病附卡" title="非性病报卡，不能打印" onclick="var x=$('#mstId').val();var y=$('#sbId').val();printSingleHIV(x,y);$('#printCardChooseDialog').dialog('close');" disabled="disabled" style="padding: 3px 10px;color:gray;"/></div>
		<c:if test="${isShowFjhZzd=='0'}">
		<div style="margin-bottom: 5px;"><input id="FJHCrbCard" type="button" value="打印肺结核附卡"  title="非肺结核附卡，不能打印" onclick="var x=$('#mstId').val();var y=$('#sbId').val();printSingleFJH(x,y);$('#printCardChooseDialog').dialog('close');" disabled="disabled" style="padding: 3px 10px;color:gray;"/></div>
		</c:if>
	</div>
	<script type="text/javascript">
		$(function(){
			//防止在页面加载好之前出现在奇怪的位置
			$("#tbdiv").show();
			$("#printCardChooseDialog").show();
			$("#cardType").combobox({
				onChange: function (n,o) {
					query();
				}
			});
			<c:if test="${!empty cardType}">
 			$("#cardType").combobox("setValue","${cardType}");
 			</c:if> 
			Csm.combogrid.dep({
<%--			<c:if test="${acType eq 'doctor'}">
				//找到所有授权的科室
				authDep:true,
				readonly:false,
				onLoadSuccess:function(data){
					var defaultv = "${user.depNo}";
					if(defaultv){
						$('#deptId').combogrid('grid').datagrid('selectRecord',defaultv);
					}
				},
			</c:if>	 --%>
				//【必传】控件名称
				id: 'deptId',
				//【可选参数】不传默认区session的医院ID
				hospId: '${user.unitId}'
				//【可选参数】不传默认区所有监控科室
				//dataType: 'mz'
			});
	 		Csm.combogrid.doctor({
<%-- 			<c:if test="${acType eq 'doctor'}">
				readonly:true,
				onBeforeLoad:function(param){
					if(!param.q){
						//如果q为空，则用默认的去查找
						var defaultv = "${user.doctorId}";
						param.q=defaultv;
					}
				},
				onLoadSuccess:function(data){
					var defaultv = "${user.userId}";
					if(defaultv){
						$('#doctorId').combogrid('grid').datagrid('selectRecord',defaultv);
					}
				},
			</c:if> --%>
				//【必传】控件名称
				id: 'doctorId',
				//【可选参数】不传默认区session的医院ID
				hospId: '${user.unitId}'
				//【可选参数】不传默认区所有监控科室
				//dataType: 'mz',
			});
			$("#cardDatagrid").datagrid({
	            url: "",
	            toolbar:'#toolBar',
	            fit: true, 
	            type:"POST",
	            fitColumns: true, 
	            striped: true,
	            singleSelect: true,
	            rownumbers: true, 
	            pagination: true,
	            checkOnSelect: false, 
	            selectOnCheck: false,
	            columns: [[
	                {field: 'masterid',title: 'mid',hidden:true},
					{field: 'mzid',title: '门诊号',hidden:true},
					{field: 'zyid',title: '${patientZyTitle}',hidden:true},
					{field: 'unReadMsg',title: '未读消息',hidden:true},
					{field: 'cardType',width:100, title: '报卡类别ID',hidden:true},
					{field: 'cardUrl',width:100, title: '报卡url',hidden:true},
					{field: 'cardName',width:100, title: '报卡类别'},
					{field: 'subid', hidden:true,title: 'sid'},
					{field: 'diseaseId',hidden:true, title:'did'},
					{field: 'diseaseName', width:80,title: '上报疾病'},
					{field: 'patientType',width:60, title: '患者类别',formatter:function(value,row,index){
						if(row['mzid']){
							return "门诊";
						}else if(row['zyid']){
							return "住院";
						}
					}},
					{field: 'id',width:120, title: '门诊/${patientZyTitle}',formatter:function(value,row,index){
						if(row['mzid']){
							if(row['isemptycard']==1){
								return row['mzid'];
							}else{
								return "<a href='javascript:void(0)' onclick=\"showDetail('','"+row['mzid']+"');\">"+row['mzid']+"</a>";
							}
						}else if(row['zyid']){
							return "<a href='javascript:void(0)' onclick=\"showDetail('"+row['zyid']+"','');\">"+row['zyid']+"</a>";
						}
					}},
					{field: 'patientName', title: '患者',width:120,
						formatter:function(value,row,index){
    						return [(row.patientName+'('+row.sexname+','+row.ageunit+')')].join('');
    					}
					},					
					{field: 'telp', title: '联系电话',width:100},
					{field: 'filldate', title: '上报日期',width:130},
					{field: 'reportdoctorname', title: '上报医生',width:60},
					{field: 'reportdeptname', title: '上报科室',width:100},
					{field: 'cardStates', title: '报卡状态',formatter:function(value,row,index){
						if(value=="0"){
							return "<a class='yellow'>未审核</a>";
						}else if(value=="1"){
							return "<a class='red'>已审核</a>";
						}else if(value=="2"){
							return "<a class='blue'>已退卡</a>";
						}else if(value=="3"){
							return "<a class='gray'>已删卡</a>";
						}
					}},
					{field: 'delreason', title: '删卡/退卡原因',width:100},
					{field: 'isprint', title: '打印标记',hidden:true},
					{field: 'op', title: '操作',width:100,formatter:opts},
	            ]]
			});
			
			//
			setTimeout("query()",500);
			
			
			$("#printCardChooseDialog").dialog({
	 			title: '请选择要打印报卡的类别',
                collapsible: false,
                minimizable: false,
                maximizable: false,
                resizable: false,
                width: 400,
                height: 220,
                modal: true,
                closed:true,
                onClose:function(){
                	$("#YGCrbCard").attr("disabled","disabled").attr("title","非乙肝报卡，不能打印").css("color","gray");
                	$("#XBCrbCard").attr("disabled","disabled").attr("title","非性病报卡，不能打印").css("color","gray");
                	if('${isShowFjhZzd}'=='0'){
                		$("#FJHCrbCard").attr("disabled","disabled").attr("title","非肺结核报卡，不能打印").css("color","gray");
                	}
                	
                }
	 		});
		});
		function opts(value,row,index){
			//获取未读消息记录
			var str = "<a title='报卡详情' class='ico_check' href=\"javascript:newDetail('"+(!row.cardName?'':row.cardName)+"','"+(!row.cardUrl?'':row.cardUrl)+"','"+(!row.zyid?'':row.zyid)+"','"+(!row.mzid?'':row.mzid)+"','"+(!row.masterid?'':row.masterid)+"');\"></a>";
			//var str = "<a title='报卡详情' class='ico_check' href=\"javascript:detail('"+row['cardType']+"','"+row['cardName']+"','"+row['zyid']+"','"+row['mzid']+"','"+row['masterid']+"');\"></a>";
			str += "<a title='干预消息' class='btn_icon' onclick=\"sendMessage('"+row['zyid']+"','"+row['mzid']+"')\"><i class='icon iconfont'>&#xe63e;</i></a>";
			if(row['unReadMsg']>0){
				//str += "<span id='' class='gyTip'>"+row['unReadMsg']+"</span>";
			}
			<c:if test="${acType eq'doctor'}">
				<c:choose>
					<c:when test="${empty allowClinicPrint || allowClinicPrint==0}">
							if(row['cardType']=="sybk" && row['cardStates']<=1){
								str+="<a title='打印' class='btn_icon' onclick=\"printSingle('"+row['cardType']+"','"+row['masterid']+"')\"><i class='icon nisfont'>"+(row['isprint']==1?'&#xe6f5;':'&#xe6f9;')+"</i></a>";
							}	
					</c:when>
					<c:when test="${allowClinicPrint==1}">
						if(row['cardType']=="sybk" && row['cardStates']<=1){
							str+="<a title='"+(row['isprint']==1?'已打印':'打印')+"' class='btn_icon' onclick=\"printSingle('"+row['cardType']+"','"+row['masterid']+"')\"><i class='icon nisfont'>"+(row['isprint']==1?'&#xe6f5;':'&#xe6f9;')+"</i></a>";
							//str+="<a title='打印' class='ico_print' onclick=\"printSingle('"+row['cardType']+"','"+row['masterid']+"')\"></a>";
						}else if(row['cardType']=="crbbk" && row['cardStates']==1){
							str+="<a title='"+(row['isprint']==1?'已打印':'打印')+"' class='btn_icon' onclick=\"printCDC('"+row['masterid']+"','"+row['subid']+"','"+row['diseaseId']+"')\"><i class='icon nisfont'>"+(row['isprint']==1?'&#xe6f5;':'&#xe6f9;')+"</i></a>";
							//str+="<a title='打印' class='ico_print' onclick=\"printCDC('"+row['masterid']+"','"+row['subid']+"','"+row['diseaseId']+"')\"></a>";
						}else if(row['cardStates']==1){
							str+="<a title='"+(row['isprint']==1?'已打印':'打印')+"' class='btn_icon' onclick=\"printSingle('"+row['cardType']+"','"+row['masterid']+"')\"><i class='icon nisfont'>"+(row['isprint']==1?'&#xe6f5;':'&#xe6f9;')+"</i></a>";
						}
					</c:when>
				</c:choose>
			</c:if>
			<c:if test="${acType eq 'hospital'}">
				if(row['cardType']=="sybk" && row['cardStates']<=1){
					str+="<a title='"+(row['isprint']==1?'已打印':'打印')+"' class='btn_icon' onclick=\"printSingle('"+row['cardType']+"','"+row['masterid']+"')\"><i class='icon nisfont'>"+(row['isprint']==1?'&#xe6f5;':'&#xe6f9;')+"</i></a>";
					//str+="<a title='打印' class='ico_print' onclick=\"printSingle('"+row['cardType']+"','"+row['masterid']+"')\"></a>";
				}else if(row['cardType']=="crbbk" && row['cardStates']==1){
					str+="<a title='"+(row['isprint']==1?'已打印':'打印')+"' class='btn_icon' onclick=\"printCDC('"+row['masterid']+"','"+row['subid']+"','"+row['diseaseId']+"')\"><i class='icon nisfont'>"+(row['isprint']==1?'&#xe6f5;':'&#xe6f9;')+"</i></a>";
					//str+="<a title='打印' class='ico_print' onclick=\"printCDC('"+row['masterid']+"','"+row['subid']+"','"+row['diseaseId']+"')\"></a>";
				}else if(row['cardStates']==1){
					str+="<a title='"+(row['isprint']==1?'已打印':'打印')+"' class='btn_icon' onclick=\"printSingle('"+row['cardType']+"','"+row['masterid']+"')\"><i class='icon nisfont'>"+(row['isprint']==1?'&#xe6f5;':'&#xe6f9;')+"</i></a>";
				}
			</c:if>
			return str;
		}
		function query(){
			var ss = $("#searchString").val();
			$("#cardDatagrid").datagrid({
				url:"${webroot}/cdc/f_view/reportHistoryQuery.shtml",
				queryParams: {
						"cardType":$("#cardType").combobox("getValue"),
						"dateType":$("#dateType").combobox("getValue"),
						"queryStartDate":$("#queryStartDate").val(),
						"queryEndDate":$("#queryEndDate").val(),
						"searchString":(ss=="姓名/门诊号/${patientZyTitle}"?"":ss),
						"reportdeptid":$("#deptId").combogrid("getValue"),
						"reportdoctorid":$("#doctorId").combogrid("getValue"),
						"cardStates":$("#cardStates").combobox("getValue"),
						"unReadMsg":$("input[name='unReadMsg']:checked").val()
		          },
		          pageNumber: 1,
		          onLoadSuccess: function (data) {
		        	  if($("#cardType").combobox("getValue")=="crbbk"){    
		        		  $("#cardDatagrid").datagrid("showColumn", "diseaseName"); // 设置显示列 
		              }else{
		            	  $("#cardDatagrid").datagrid("hideColumn", "diseaseName"); // 设置隐藏列    
		              }
		          }
			});
		}
		function newDetail(cardName,url,zyid,mzid,masterid){
			if(url){
				parent.menuInfo.clickMenu(cardName+"详情",url+"?zyid="+(zyid)+"&mzid="+(mzid)+"&masterid="+(masterid)+"&justLook=Y",true);
			}else{
				$.messager.show({ title: '提示', msg: "报卡路径错误，请重新设置！" });
			}
		}
		
/* 		function detail(cardType,zyid,mzid,masterid){
			parent.menuInfo.closeMenu("传染病报卡详情");
			if(cardType=="crbbk"){
				if(zyid && zyid!="null"){
					parent.menuInfo.clickMenu("传染病报卡详情","/cdc/f_view/reportCardZY.shtml?zyid="+zyid+"&masterid="+masterid+"&justLook=Y");
				}else if(mzid && mzid!="null"){
					parent.menuInfo.clickMenu("传染病报卡详情","/cdc/f_view/reportCardMZ.shtml?mzid="+mzid+"&masterid="+masterid+"&justLook=Y");
				}
			}else if(cardType=="sybk"){
				parent.menuInfo.clickMenu("死因报卡详情","/cdc/f_view/deathReport.shtml?zyid="+zyid+"&mzid="+mzid+"&masterid="+masterid+"&justLook=Y");
			}else if(cardType=="syjcbk"){
				parent.menuInfo.clickMenu("食源监测报卡详情","/cdc/f_view/fsmReport.shtml?zyid="+zyid+"&mzid="+mzid+"&masterid="+masterid+"&justLook=Y");
			}else if(cardType=="zlbk"){
				parent.menuInfo.clickMenu("肿瘤病例报卡详情","/cdc/f_view/tumourReport.shtml?zyid="+zyid+"&mzid="+mzid+"&masterid="+masterid+"&justLook=Y");
			}else if(cardType=="syycbk"){
				parent.menuInfo.clickMenu("食源异常报卡详情","/cdc/f_view/fsaReport.shtml?zyid="+zyid+"&mzid="+mzid+"&masterid="+masterid+"&justLook=Y");
			}
		} */
		
		function printSingle(bktype,mid,sid){
			var purl = "${reportUrl}";
			$.ajax({
				url : '${webroot}/cdc/f_json/report/file/get.shtml',
				dataType: 'json',
				data: { bktype : bktype},
				type: 'post',
				cache: false,
	            success : function(json){
	            	if(json.data != null && json.data != ''){
	    				purl += json.data;
	            	}else{
	            		$.messager.show({ title: '提示', msg: "报表文件名丢失，请核对相关参数！" });
	            		return;
	            	}
	    			if(sid){
	    				purl+="&subid="+sid;
	    			}
	    			if(mid){
	    				updatePrintFlag(bktype,mid);
	    				window.open(purl+"&masterid="+mid);
	    			}else{
	    				$.messager.show({ title: '提示', msg: "未获取到可以被打印的数据记录！无法打印！" });
	    			}
	            }
			});
		}
		function printCDC(msid,subid,cdcId){
			var bktype = $("#cardType").combobox("getValue");
			//fz
			$("#mstId").val(msid);
			$("#sbId").val(subid);
			
			var sexcard = "${sexcards}";
			var fjhcard = "${fjhDiseasis}";
			if(bktype=="crbbk"){
				//传染病
				var top = document.body.scrollTop||document.documentElement.scrollTop;
				var mtop = parseInt(($(window).height()-155)/2);
				if(mtop>0){
					top += mtop;
				}
				$("#printCardChooseDialog").window('open').window('resize',{top: top});
				if(cdcId=="0302"){
					//解锁HBV
					$("#YGCrbCard").removeAttr("disabled").css("color","black").removeAttr("title");
				}
				if(sexcard.indexOf(cdcId)>=0){
					//解锁HIV
					$("#XBCrbCard").removeAttr("disabled").css("color","black").removeAttr("title");
				}
				if(fjhcard.indexOf(cdcId)>=0){
					//解锁FJH
					$("#FJHCrbCard").removeAttr("disabled").css("color","black").removeAttr("title");
				}
			}else{
				printSingle("crbbk",msid,subid);
			}
		}
		function printSingleHIV(msid,subid){
			var purl = "${reportUrl}"+"cdc/cdc.print.crb.HIV.cpt";
			if(msid && subid){
				window.open(purl+"&subid="+subid+"&masterid="+msid);
			}else{
				$.messager.show({ title: '提示', msg: "未获取到需打印的数据记录！无法打印！" });
			}
		}
		function printSingleFJH(msid,subid){
			var purl = "${reportUrl}"+"cdc/cdc.print.crb.FJHZZD.cpt";
			if(msid && subid){
				window.open(purl+"&subid="+subid+"&masterid="+msid);
			}else{
				$.messager.show({ title: '提示', msg: "未获取到需打印的数据记录！无法打印！" });
			}
		}
		function printSingleHBV(msid,subid){
			var purl = "${reportUrl}"+"cdc/cdc.print.crb.HBV.cpt";
			if(msid && subid){
				window.open(purl+"&subid="+subid+"&masterid="+msid);
			}else{
				$.messager.show({ title: '提示', msg: "未获取到需打印的数据记录！无法打印！" });
			}
		}
		function sendMessage(zyid,mzid){
			Comm.dialogGlobal({
		    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid="+zyid+"&mzid="+mzid+"&msgType=1",
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
		function ExportXls(){
			var cd = $("#cardDatagrid").datagrid('getRows');
			if(cd.length<=0){
				$.messager.show({ title: '提示', msg: "无数据被导出！" });
				return;
			}else{
				var bktype = $("#cardType").combobox("getValue");
				var purl = "${reportUrl}";
				$.ajax({
					url:"${webroot}/cdc/f_json/export/file/get.shtml",
					data: { bktype : bktype},
					dataType: 'json',
					type: 'post',
					cache: false,
					success:function(json){
						if(json.data){
							purl+=json.data+"&flag="+$("#cardStates option:selected").val()+"&DTType="+$("#dateType option:selected").val()+"&ReportDoctorID="+$("#doctorId").combobox('getValue')+"&endDate="+$("#queryEndDate").val()+"&startDate="+$("#queryStartDate").val()+"&ReportDeptID="+$("#deptId").combobox('getValue')+"&PatientKey="+($("#searchString").val()=="姓名/门诊号/${patientZyTitle}"?"":$("#searchString").val());
							window.open(purl);
		            	}else{
		            		$.messager.show({ title: '提示', msg: "导出报表路径获取失败！无法导出！" });
		            	}
					}
				});
			}
		}
		
		function updatePrintFlag(bktype,msid){
			 $.ajax({
				 url:"${webroot}/cdc/f_json/updataPrintFlag.shtml",
				 data:{
					 bktype:bktype,
					 msid:msid
				 },
				 type:"POST",
				 success:function(msg){
					 //console.log(msg);
					 //刷新
					 query();
				 },
				 error:function(){
					 $.messager.alert("抱歉！更新打印状态失败！");
				 }
			 });
		 }
	</script>
</body>
</html>