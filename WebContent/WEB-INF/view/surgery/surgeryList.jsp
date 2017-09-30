<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>外科手术感染监测</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<style>
		.easyui-combobox{width:75px;}
		.table_cx .easyui-combobox{width:150px;}
	</style>
</head>
<body class="easyui-layout">

<div data-options="region:'west',border:false,title:'查询条件'" style="width:230px;">
	<div class="easyui-layout" data-options="fit:true">              
		<div data-options="region:'center',border:false">
			<table class="table_cx" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="t_title">时间选择：</td>
						<td>
							<select style="width:120px" id="dateType" name="dateType" class="easyui-combobox">
								<option value="2">手术时间</option>
								<option value="1">出院时间</option>
								<option value="3">感染时间</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="t_title">开始时间：</td>
						<td>
							<input type="text" id="queryStartDate" value="${queryStartDate}" class="Wdate text easyui-validatebox" style="width:108px;" onclick="WdatePicker()" data-options="required:true,validateOnBlur:true" />
						</td>
					</tr>
					<tr>
						<td class="t_title">结束时间：</td>
						<td>
							<input type="text" id="queryEndTime" value="${queryEndDate}" class="Wdate text easyui-validatebox" style="width:108px;" onclick="WdatePicker()" data-options="required:true,validateOnBlur:true" />
						</td>
					</tr>
					<tr><td height="5"></td><td></td></tr>
					<tr>
						<td class="t_title">患者检索：</td>
						<td>
							<input type="text" id="id_patient_info" class="auto-tip" data-tip="${patientZyTitle}/姓名" title="${patientZyTitle}/姓名" style="width: 108px;" data-options="required:true,validateOnBlur:true" />
						</td>
					</tr>
					<tr><td height="5"></td><td></td></tr>
					<tr>
						<td class="t_title">科室类型：</td>
						<td>
							<select style="width:120px" class="easyui-combobox" id="deptType"><option value="s">手术科室</option><option value="z">所在科室</option></select>
						</td>
					</tr>
					<tr>
						<td class="t_title">科室名称：</td>
						<td>
						<div class="select_del"><input type="text" id="id_deptId" style="width: 120px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_deptId').combo('clear');"></a></div>
						</td>
					</tr>
					<tr><td height="5"></td><td></td></tr>
					<tr>
						<td class="t_title">重点手术：</td>
						<td>
						<div class="select_del"><input type="text" id="id_impOpeId" style="width: 120px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_impOpeId').combo('clear');"></a></div>
						</td>
					</tr>
					<tr>
						<td class="t_title">操作类别：</td>
						<td>
						<div class="select_del"><input type="text" id="id_opepartkindid" style="width: 120px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_opepartkindid').combo('clear');"></a></div>
						</td>
					</tr>
					<tr>
						<td class="t_title">手术部位：</td>
						<td>
						<nis:select id="id_operaBw"  cssCls="easyui-combobox" dictcode="opera_bw" headerKey="" headerValue="-部位分类-" exp="style=\"width: 120px;\"" />
						</td>
					</tr>
					<tr><td height="5"></td><td></td></tr>
					<tr>
						<td class="t_title">切口等级：</td>
						<td>
						<nis:select id="id_incisionGrade"  cssCls="easyui-combobox" dictcode="incision_type" value="${st005Ssxxb.incisionGrade}" headerKey="" headerValue="-切口类型-" exp="style=\"width: 120px;\"" />
						</td>
					</tr>
					<tr>
						<td class="t_title">手术名称：</td>
						<td>
							<input type="text" id="id_operName" class="auto-tip" data-tip="手术名称" title="手术名称" style="width: 108px;" />		
						</td>
					</tr>
					<tr>
						<td class="t_title">手术医生：</td>
						<td>
						<div class="select_del"><input type="text" id="id_opedocId" style="width: 120px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_opedocId').combo('clear');"></a></div>
						</td>
					</tr>
					<tr>
						<td class="t_title">手术间：</td>
						<td>
							<input type="text" id="id_operRoom" class="auto-tip" data-tip="手术房间"  style="width: 108px;" />		
						</td>
					</tr>
					<tr>
						<td class="t_title">手术时长：</td>
						<td>
							<select style="width:55px" id="lengTime" name="lengTime" class="easyui-combobox">
								<option value="">请选择</option>
								<option value="1">>=</option>
								<option value="2"><=</option>
							</select>
							<input type="text" id="id_operLengTime" class="auto-tip" data-tip="时长（分钟）"  style="width: 48px;" />		
						</td>
					</tr>
					<tr id="grTyperow" style="display: none;">
						<td class="t_title">感染类型：</td>
						<td>
							<select id="grType" name="grType" style="width:120px" >
								<option value="">----不限----</option>
								<option value="1">院感</option>
								<option value="2">社感</option>
							</select>
						</td>
					</tr>
					<tr><td height="5"></td><td></td></tr>			
					<tr>
						<td class="t_title"><input type="checkbox" id="gx" name="gx" value="1"/></td>
						<td><label for="gx">显示管辖科室</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="gr" name="gr" value="1"/></td>
						<td><label for="gr">显示手术感染</label></td>
					</tr>
					<tr>
						<td class="t_title"><input type="checkbox" id="gd" name="gd" value="4"/></td>
						<td><label for="gd">显示归档手术</label></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div data-options="region:'south',border:false" style="height:50px; border-top-width:1px;">	         		
			<div class="btn_center">
				<div class="n_btn_blue">
					<a href="javascript:;" id="toTrigger" onclick="surgery.query()" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
				</div>		
			</div>				
		</div>
	</div>
</div>
<div data-options="region:'center',border:false" style="border-left-width: 1px;">
	<div id="tb" class="m_search">
		<div class="m_search_last">					
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="surgery.add()"><i class="icon iconfont">&#xe602;</i><span>新增</span></a>
				</div>
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="surgery.print()"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
				</div>				
			</div>		
		</div>
	</div>	
	<div id="surgeryListPanel"></div>
</div>	
<script type="text/javascript">
	var surgery = {
		panel : 'surgeryListPanel',
		dataSections : eval('${dataSections}'),
		query : function () {
			var gx = '';
    		$("input:checkbox[name='gx']:checked").each(function(){ 
    			gx += $(this).val() + ',';
   			});
			if (this.validateDate()) {
				autoTip.clear();
				//alert($('#deptType').combogrid('getValue'));
				//alert($('#id_deptId').combogrid('getValue'));
				var grType = null;
				if($('#dateType').combogrid('getValue') == '3'){
					grType = $('#grType').val();
				}
				$('#' + surgery.panel).datagrid({
		            url: '${webroot}/st005Ssxxb/f_json/findSurgeryList.shtml',
		            queryParams: {
		            	'riskRateType':'${riskRateType}',
		            	'dateType':$('#dateType').combogrid('getValue'),
		            	'queryStartDate':$('#queryStartDate').val(),
		            	'queryEndDate':$('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59',
		            	'searchString':$('#id_patient_info').val(),
		            	'deptType':$('#deptType').combogrid('getValue'),
		            	'deptId':$('#id_deptId').combogrid('getValue'),
		            	'opepartkindid':$('#id_opepartkindid').combogrid('getValue'),
		            	'incisionGrade':$('#id_incisionGrade').combogrid('getValue'),
		            	'impOpeId':$('#id_impOpeId').combogrid('getValue'),
		            	'opedocId':$('#id_opedocId').combogrid('getValue'),
		            	'operName':$('#id_operName').val(),
		            	'operRoom' :$('#id_operRoom').val(),
		            	'operLengTime':$('#id_operLengTime').val(),
		            	'operBw':$('#id_operaBw').combogrid('getValue'),
		            	'gx':gx,
		            	'status':$(':checkbox[name="gd"]:checked').val(),
		            	'gr':$(':checkbox[name="gr"]:checked').val(),
		            	'grType' :grType,
		            	'lengTime':$('#lengTime').combogrid('getValue')
		            }
		        });
			}
		},
		print:function(){
			autoTip.clear();
        	var queryStartDate = $('#queryStartDate').val();
        	var queryEndDate = $('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59';
        	var searchString = encodeURIComponent(encodeURIComponent($('#id_patient_info').val())) ;
        	var deptType = $('#deptType').combogrid('getValue');
        	var deptId = $('#id_deptId').combogrid('getValue');
        	var opepartkindid  = $('#id_opepartkindid').combogrid('getValue');
        	var incisionGrade = encodeURIComponent(encodeURIComponent($('#id_incisionGrade').combogrid('getValue')));
        	var impOpeId = $('#id_impOpeId').combogrid('getValue');
        	var opedocId = $('#id_opedocId').combogrid('getValue');
        	var operName  = encodeURIComponent(encodeURIComponent($('#id_operName').val()));
        	var status =  $(':checkbox[name="gd"]:checked').val();
        	if(typeof(status) == 'undefined'){
        		status='';
        	}
        	var gr = $(':checkbox[name="gr"]:checked').val();
        	if(typeof(gr) == 'undefined'){
        		gr='';
        	}
    		var gx = '';
    		$("input:checkbox[name='gx']:checked").each(function(){ 
    			gx += $(this).val() + ',';
   			});
    		var dateType = $('#dateType').val();
			var url = '${webroot}/st005Ssxxb/f_json/exportExcelSsxx.shtml?&queryStartDate='+queryStartDate+'&queryEndDate='+queryEndDate+'&searchString=' + searchString+"&deptId="+deptId
			+'&opepartkindid='+opepartkindid+'&deptType='+deptType+'&incisionGrade='+incisionGrade+'&impOpeId='+impOpeId+'&opedocId='+opedocId+'&operName='+operName+'&gx='+gx+'&status='+status+'&gr='+gr+'&dateType='+dateType;
    		window.location.href = url;
		},
		//编辑
		edit : function(id,nnis) {
			parent.menuInfo.clickMenu('手术信息','/st005Ssxxb/f_view/toSurgeryInfoEdit.shtml?id='+id+'&nnis='+nnis, true, null, null, '${param.tabBodyId}');
    	},
    	//
    	add : function(){
    		parent.menuInfo.clickMenu('手术信息','/st005Ssxxb/f_view/toSurgeryInfoAdd.shtml', true, null, null, '${param.tabBodyId}');
    	},
    	 //选择感染类型
	    chooseInfectTypeId:function(infectTypeId,zyid,operId,relid,operName,incisionGrade){
	    	if(infectTypeId!=''){
	    		parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid+"&infectTypeId="+infectTypeId+"&operId="+operId+"&relid="+relid+"&operName="+operName+"&incisionGrade="+incisionGrade,true);    			   
	    	}
	    },
		//日期必填验证
		validateDate : function() {
			var re = true;
			if ($.trim($('#queryStartDate').val()).length == 0) {
				//$('#queryStartDate').addClass("validatebox-invalid");
				$('#queryStartDate').focus();
				re = false;
			}
			if ($.trim($('#queryEndTime').val()).length == 0) {
				//$('#queryEndTime').addClass("validatebox-invalid");
				if (re) {
					$('#queryEndTime').focus();
				}
				re = false;
			}
			return re;
		},
		//患者档案
		patientInfo : function (zyid) {
			parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
		},
		//归档
		archive : function (id,status,index) {
        	$.ajax({
                url: '${webroot}/st005Ssxxb/f_json/archive/status/update.shtml',
                type: 'post',
                data: { id: id,status: status },
                dataType: 'json',
                success : function(json) {
					if(json.result==='success') {
						//surgery.query();
						var statusName = '已归档';
						if(status=='0'){
							statusName='未归档';
						}
					 	
			           	$('#surgeryListPanel').datagrid('updateRow',{
							index: index,
							row: {
								statusName: statusName,
								status : status
							}
						}); 
                        $.messager.show({ title: '提示', msg: '操作成功！' });
			    	} else if(json.result === 'error') {
			    		$.messager.show({ title: '提示', msg: '系统异常！' });
			    	} else {
			    		$.messager.show({ title: '提示', msg: json.msg });
			    	}
				}
        	});
		},
		//查看状态点击处理
		setViewFlag : function(id, obj) {
			var viewFlag = 0;
			if ($(obj).attr("checked")) {
				viewFlag = 1;
			}
			$.ajax({
	            url: webroot + '/st005Ssxxb/f_json/updViewFlag.shtml',
	            type: 'post',
	            data: { id: id, viewFlag: viewFlag },
	            dataType: 'json',
	            success : function(json) {
	            	if (json.result === 'success') {
	            		$.messager.show({title : '提示',msg : '操作成功！'});
					} else {
						$.messager.show({title : '提示',msg : json.msg});
					}
				}
			});
		},
		//打印报卡信息
		printReportInfo : function(id) {
       		var url = reportUrl+'nis7/SS_SSGRXQ.cpt&__bypagesize__=false&id=' + id;
       		window.open(url);
       		//parent.menuInfo.clickMenu(decodeURI(decodeURI('报卡打印')), url, true, '', null);
		}
	};

	$(document).ready(function () {
		
		$("#dateType").combobox({
			onChange: function (n,o) {
				if(n==3){
					$("#gr").attr("checked","checked");
				}else{
					$("#gr").removeAttr("checked");
				}
			}
		});
		
		autoTip.clear();
		$('#' + surgery.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        //fitColumns: true,
	        url:'${webroot}/st005Ssxxb/f_json/findSurgeryList.shtml',
	        queryParams: {
	        	'riskRateType':'${riskRateType}',
            	'dateType':$('#dateType').val(),
            	'deptType':'s',
            	'queryStartDate':$('#queryStartDate').val(),
            	'queryEndDate':$('#queryEndTime').val() == ''?'':$('#queryEndTime').val()+' 23:59:59',
            	'searchString':$('#id_patient_info').val(),
            	'operName':$('#id_operName').val(),
            	'operRoom' :$('#id_operRoom').val(),
            	'operBw':$('#id_operaBw').combogrid('getValue'),
            	'operLengTime':$('#id_operLengTime').val(),
            	'lengTime':$('#lengTime').combogrid('getValue')
            },
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        columns:[ 
		       	[
					/* {field:'patientId',title:'档案号',sortable:true,width:125}, */
					{field:'deptName',title:'科室',sortable:true,width:125},
		            {field:'zyid',title:'${patientZyTitle}',sortable:true,width:90,
		            	formatter:function(value,rec){
							return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="surgery.patientInfo(\'' + rec.zyid + '\')">' + rec.${patientZyValue} + '</a>'].join('');
					    }
		            },
		            {field:'patientNameDesc',title:'患者',sortable:true,width:100,
    					formatter:function(value,row,index){
    						return [(row.patientName+'('+row.sex+','+row.age + ((row.ageUnit == null || row.ageUnit == 'null') ?'岁':row.ageUnit) +')')].join('');
    					}
    				},
    				{field:'bedNo',title:'床号',sortable:true,width:40},
		            {field:'operName',title:'手术名称',sortable:true,width:200,
    					formatter:function(value,rec){
							return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="surgery.edit(\'' + rec.id + '\',\'' + rec.nnis + '\')">' + value + '</a>'].join('');
					    }
		            },
		            {field:'operAt',title:'手术日期',sortable:true,align:'center',width:90},
		            {field:'outAt',title:'出院日期',sortable:true,align:'center',width:90},
		            {field:'incisionGrade',title:'切口类型',sortable:true,align:'center',width:50},
		            {field:'operLengTime',title:'持续时间',sortable:true,align:'center',width:50},
		            {field:'asa',title:'ASA',sortable:true,align:'center',width:40},
		            {field:'nnis',title:'NNIS',sortable:true,align:'center',width:40},
					/* {field:'ck',title:'查看状态',width:55,align:'center',
						formatter:function(value,rec){
							return ['<input style="width:15px;height:18px;" type="checkbox" ' + (rec.viewFlag == 1 ? 'checked="checked"' : '') + 'onclick="surgery.setViewFlag(' + rec.id + ', this);" />'].join('');
					    }
					}, */
					{field:'infectTypeName',title:'感染类型',sortable:true,width:90,
		            	formatter:function(value,r,index){
		            		var operId='';
		            		if(r.operId!=null){
		            			operId = r.operId;
		            		}
							var exp="onChange='surgery.chooseInfectTypeId(this.value,\""+r.zyid+"\",\""+operId+"\",\""+r.relid+"\",\""+encodeURI(encodeURI(r.operName))+"\",\""+encodeURI(encodeURI(r.incisionGrade))+"\")'";
							var selectStr = '<select name="infectTypeId'+index+'" '+exp+'><option value="">请选择</option>';
							for (var i=0; i < surgery.dataSections.length; i++) {
								var dataSection = surgery.dataSections[i];
								selectStr += '<option value="' + dataSection.value + '" ' + (dataSection.value == r.infectTypeId ? 'selected="selected"' : '') + '>' + dataSection.text + '</option>';
							}
							selectStr += '</select>';
							return selectStr;
						}								
					},
					{field:'infectDiagnName',title:'感染部位',sortable:true,width:150},
					{field:'statusName',title:'状态',sortable:true,align:'center',width:60},
			        {field:'_operate',title:'操作',align:'center',sortable:true,width:90,
		 	         	formatter:function(value,r,index){
				           	var returnString ='';
				           	if( r.status == '4'){
				           		returnString = '<a href="javascript:surgery.archive(\'' + r.id + '\',\'0\',\''+index+'\');" class="ico_stop" title="取消归档"></a>';
				           	}else{
				           		returnString = '<a href="javascript:surgery.archive(\'' + r.id + '\',\'4\',\''+index+'\');" class="ico_select" title="归档"></a>';
				           	}
				           	returnString+='<a href="javascript:surgery.printReportInfo(\'' + r.id + '\');" class="ico_print" title="打印"></a>';
				           	return returnString;
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
			value: '',
			ifcaseoffice: '1',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0'
		});
		
		//手术医生
		Csm.combogrid.doctor({
			id: 'id_opedocId',
			value: '',
			//【可选参数】1：回调，0：不回调，不传默认回调
			callback: '0'
		});
		
		//手术操作分类
		Csm.combogrid.sysDic({
			id: 'id_opepartkindid',
			value: '',
			dictTypeCode : 'icd9_opekind'
		});
		
		//重点监测手术
		Csm.combogrid.sysDic({
			id: 'id_impOpeId',
			value: '',
			mode:'',
			dictTypeCode : 'operation_point'
		});
		$("#dateType").combobox({
			onChange: function (n,o) {
				if($("#dateType").combogrid('getValue')=='3'){
					//$("#grTyperow").show();
					//$("#grType").val("1");
				}else{
					//$("#grTyperow").hide();
					//$("#grType").val("");
				}
			}
		})
	});
</script>
</body>
</html>
