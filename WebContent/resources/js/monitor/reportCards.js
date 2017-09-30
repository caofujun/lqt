/**
 * 报卡信息页面Js
 */
var reportCards = {
	queryType : '', //1代表单独操作页，默认列表页
	relid : '',
	yzIds : '',
	ticket:'',
	//删除感染诊断
	toDelete : function (relid, zyid) {
		Comm.dialogGlobal({
        	url:webroot + "/gr002YsgrMx/f_view/toDelDiagnosis.shtml?zyid=" + zyid + '&relid=' + relid,
            title: '删除确认',
            width:600,
            height:350,
            type:"iframe",
            parent:this
        });
	},
	//添加药敏结果
	addymjgList : function (mzid, zyid , testOrderNo,pathoCode, panelId) {
		$('#id_result_'+panelId).datagrid({
			url:webroot+'/st011Syjgb/f_json/findSt011SyjgbList.shtml',
			queryParams: {
	         	'zyid':zyid,
	         	'mzid':mzid,
	         	'testOrderNo':testOrderNo,
	         	'pathoCode' : pathoCode,
	         }
        });
	},
	//添加抗菌药物
	addKjyw : function(relid, zyid,yzId) {
		if(reportCards.yzIds != null){
			yzId = reportCards.yzIds;
		}
		Comm.dialogGlobal({
        	url:webroot+"/st004Yzxxb/f_view/toAddKjyw.shtml?zyid="+zyid+"&refid="+relid+"&operTypeId=1" + "&yzId=" + yzId,
            title: "选择抗菌药物",
            width:850,
            height:500,
            type:'iframe',
            parent:this,
        });
		this.relid = relid;
	},
	//保存抗菌药物
	savekjyw : function(yzid,refid,yymd,panelId){
		$.ajax({
            url: webroot+"/st004Yzxxb/f_json/saveKjyw.shtml?yzId="+yzid+"&refid="+refid+"&yymd="+yymd,
            type: 'post',
            dataType: 'json',
            success : function(json) {
				if(json.result==='success') {
					reportCards.queryKjyw(panelId,refid);
		    	} 
			}
    	});
		},
	//添加抗菌药物
	addEditKjyw : function(relid, zyid,panelId) {
		var yzId='';
		if(reportCards.yzIds != null){
			yzId = reportCards.yzIds;
		}
		Comm.dialogGlobal({
        	url:webroot+"/st004Yzxxb/f_view/toAddEditKjyw.shtml?zyid="+zyid+"&refid="+relid+"&operTypeId=1" + "&yzId=" + yzId+"&panelId="+panelId,
            title: "选择抗菌药物",
            width:850,
            height:500,
            type:'iframe',
            parent:this,
        });
		this.relid = relid;
	},
	kjyw : function(yzid,refid,yymd){
		$('#id_kjywsy_'+refid).datagrid({
		    fit: false,
		    nowrap: true,
		    autoRowHeight: true,
		    striped: true,
		    fitColumns: true,
		    height:'200px',
		    url:webroot+"/st004Yzxxb/f_json/findKjywDrugSituation.shtml?yzid="+yzid,
		    remoteSort: false,
		    singleSelect: true,
		    border: true,
		    columns:[ 
			    [
			    {field:'id',title:'',sortable:true,width:120},
			    {field:'orderName',title:'抗菌药物',sortable:true,width:120,
			    	formatter:function(value,rec,index){
						return ['<input type="hidden" name="gr16List[' + index + '].yzId" value="' + rec.id + '"/>' + 
						        '<input type="hidden" name="gr16List[' + index + '].refid" value="' + refid + '"/>' + 
						        '<input type="hidden" id="id_drugSituate_2_' + rec.id + '" name="gr16List[' + index + '].isselect" value="' + (rec.isselect == null ? 0 : rec.isselect) + '"/>' + 
						        rec.orderName].join('');
				    }},
			    {field:'orderAt',title:'开嘱时间',sortable:true,align:'center',width:120},
			    {field:'stopAt',title:'停嘱时间',sortable:true,align:'center',width:120},
			    {field:'days',title:'用药天数',sortable:true,align:'center',width:120},
			    {field:'adminRouteName',title:'给药路径',sortable:true,align:'center',width:120},
			    {field:'dosage',title:'剂量',sortable:true,align:'center',width:120},
			    {field:'sypc',title:'频次',sortable:true,align:'center',width:120},
			    {field:'preYymd',title:'用药目的',width:120,align:'center',
			    	formatter:function(value,row,index){
                        return reportCards.setPreYymdList(value, index, 'gr16List',yymd);
			    	},
			    },
                 {field:'_operate',title:'操作',width:40,
						formatter : function(value,row){	
							return ['<a href="javascript:reportCards.delTemp(\'' + row.id + '\', \'' +refid + '\', \''+ yzid + '\');" class="ico_del" title="删除"></a>'].join('');
						}
                 }
			    ]
		    ],
		    onLoadSuccess : function (data) {
		    	reportCards.yzIds=yzid;
		    	$('#id_kjywsy_'+refid).datagrid('hideColumn', 'id');
	        },
		    rownumbers:true
		 
	    });
		
	},
	delTemp : function(id,refid,yzid) {
    	$.messager.confirm('提示', '确认删除该抗菌用药?', function (r) {
        	if (r) {
            	$.ajax({
                    url: webroot+"/st004Yzxxb/f_json/findKjywDrugSituationTemp.shtml?id="+id+"&yzId="+yzid+"&refid="+refid,
                    type: 'post',
                    dataType: 'json',
                    success : function(json) {
                    	reportCards.kjyw(json.yzId,json.refid,json.yymd);
					}
            	});
        	}
    	});
	},
	//处理用药情况
	del : function(panelId,refid, relid) {
    	$.messager.confirm('提示', '确认删除该抗菌用药?', function (r) {
        	if (r) {
            	$.ajax({
                    url: webroot + '/gr016BkKjyw/f_json/delete.shtml?relid='+relid,
                    type: 'post',
                    dataType: 'json',
                    success : function(json) {
						if(json.result==='success') {
							reportCards.queryKjyw(panelId,refid);
                            $.messager.show({ title: '提示', msg: '删除成功！' });
				    	} else if(json.result === 'error') {
				    		$.messager.show({ title: '提示', msg: '系统异常！' });
				    	} else {
				    		$.messager.show({ title: '提示', msg: json.msg });
				    	}
					}
            	});
        	}
    	});
	},
	//更新抗菌药物查询
	queryKjyw : function(panelId,refid) {
		$('#id_kjywsy_'+panelId).datagrid({
			queryParams: {
		    	refid:refid,
            }
        });
    },
	//用药目的选择
	setPreYymdList : function(value, index, nameList,yymd){
		var selectStr = '<select name="' + nameList + '[' + index +'].preYymd' + '" style="width: 110px;" > ';
		var yymdList=$.parseJSON(yymd);
		for (var i=0; i < yymdList.length; i++) {
			var preYymd = yymdList[i];
			selectStr += '<option value="' + preYymd.value + '" ' + (preYymd.value == value ? 'selected="selected"' : '') + '>' + preYymd.text + '</option>';
		}
		selectStr += '</select>';
		return selectStr;
	},
	//添加易感因素
	addEasyFactors : function(relid, index) {
		//获取已经存在的易感因素
		var existYgys = '';
		$('#id_ygys_' + relid).children('span').each(function(){ 
			existYgys += $(this).attr('code') + ',';
		});
		Comm.dialogGlobal({
        	url:webroot + "/sysDict/f_view/toselect.shtml?dictTypeCode=susceptible_factor&index=" + index + '&checkeds=' + existYgys,
            title: '选择易感因素',
            width:500,
            height: 450,
            type:"iframe",
            parent:this
        });
		this.relid = relid;
	},
	//删除易感因素
	delEasyFactors : function(relid, factorId) {
		//页面上面删除
		$('#id_ygys_' + relid + '_' + factorId).remove();
	},
	//选择的易感因素操作
	setDictList : function(sysDictSelections) {
		if (sysDictSelections && sysDictSelections.length > 0) {
			//获取已经存在的易感因素
			var existYgysArr = new Array();
			$('#id_ygys_' + this.relid).children('span').each(function(){ 
				existYgysArr.push($(this).attr('code'));
			});
			var factorId = '',
				factorName = '',
				relid = this.relid;
			for (var i = 0; i < sysDictSelections.length; i++) {
				factorId = sysDictSelections[i].dictCode;
				factorName = sysDictSelections[i].dictName;
				//如果选择的易感因素不存在则添加
				if ($.inArray(factorId, existYgysArr) == -1) {
					//页面上面加入
					$('#id_ygys_' + relid).append('<span id="id_ygys_' + relid + '_' + factorId + '" code="' + factorId + '"><input type="hidden" name="factorIds" value="' + factorId + '" />' + factorName + '<a href="javascript:void(0)" class="img_del" onclick="reportCards.delEasyFactors(\'' + relid + '\',\'' + factorId + '\');"></a></span>');
				}
			}
		}
	},

	//选择手术
	selOpration : function(relid, zyid, patientId) {
		var operAt = $('#id_infectDate_' + relid).val();
		Comm.dialogGlobal({
        	url:webroot + "/st005Ssxxb/f_view/toSelectList.shtml?patientId=" + patientId + "&zyid=" + zyid + "&operAt=" + operAt,
            title: '选择手术',
            width: 500,
            height: 450,
            type:"iframe",
            parent:this
        });
		this.relid = relid;
	},
	//设置手术信息
	setOprationList : function(oprationSelections) {
		if (oprationSelections && oprationSelections.length > 0) {
			$('#id_opration_id_' + this.relid).val(oprationSelections[0].operId);
			$('#id_opration_relid_' + this.relid).val(oprationSelections[0].relid);
			$('#id_opration_name_' + this.relid).val(oprationSelections[0].operName);
			if(oprationSelections[0].incisionGrade !=null){
				$('#id_memo_' + this.relid).combobox('setValues',oprationSelections[0].incisionGrade);
			}
		}
	},
	//设置手术信息
	setNewOprationList : function(operId,relid,operName,incisionGrade) {
		$('#id_opration_id_' + this.relid).val(operId);
		$('#id_opration_relid_' + this.relid).val(relid);
		$('#id_opration_name_' + this.relid).val(operName);
		$('#id_memo_' + this.relid).combobox('setValues',incisionGrade);
	},
	//设置标准手术信息
	setICD9List : function(icd9Selections) {
		if (icd9Selections && icd9Selections.length > 0) {
			$('#id_opration_id_' + this.relid).val(icd9Selections[0].icdId);
			$('#id_opration_name_' + this.relid).val(icd9Selections[0].operaCnname);
		}
	},
	//删除手术信息
	delOpration : function(relid) {
		$('#id_opration_id_' + relid).val('');
		$('#id_opration_relid_' + relid).val('');
		$('#id_opration_name_' + relid).val('');
		$('#id_memo_' + relid).combobox('setValues','')
	},
	//操作后刷新界面
	query : function() {
		if ('1' == reportCards.queryType) {
			var currTab = parent.$('#mainTabs').tabs('getSelected');
	        //获取tab的iframe对象  
	        var tbIframe = currTab.find('iframe:first-child'); 
	        var url = tbIframe.attr('src');
			parent.$('#mainTabs').tabs('update',{     
				tab : currTab,     
				options : {          
					content : '<iframe scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>' 
				}
			});
		} else if ('2' == reportCards.queryType) {
			parent.cardSpecial.query();
		} else {
			cardSpecial.query();
		}
	},
	//删除主管医生
	delChargeDr : function(obj) {
		relid = $(obj).attr("relid");
		$('#id_chargeDrId_' + relid).combogrid("clear");
	},
	//删除疾病 
	delJbzd : function(obj) {
		relid = $(obj).attr("relid");
		$('#id_jbzd_' + relid).combogrid("clear");
		$('#id_jbzdCode_' + relid).val('');
	},
	//编辑模式
	toEdit : function(relid) {
		if ('1' == reportCards.queryType) {
			if(reportCards.ticket=='nis'){
				window.location.href = webroot + '/bk001Sbk/f_view/toReportCardsEdit.shtml?isSeparatePage=1&relid=' + relid;
			}else{
				var currTab = parent.$('#mainTabs').tabs('getSelected');
				var url = webroot + '/bk001Sbk/f_view/toReportCardsEdit.shtml?isSeparatePage=1&relid=' + relid;
				parent.$('#mainTabs').tabs('update',{     
					tab : currTab,     
					options : {          
						content : '<iframe scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>' 
					}
				});	
			}
		} else if ('2' == reportCards.queryType) {
			parent.cardSpecial.toEdit(relid);
		} else {
			cardSpecial.toEdit(relid);
		}
	},
	//退出编辑
	exitEdit : function(relid) {
		$.messager.confirm('提示', '确认退出修改?', function (r) {
        	if (r) {
				if ('1' == reportCards.queryType) {
					var currTab = parent.$('#mainTabs').tabs('getSelected');
					var url = webroot + '/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&relid=' + relid;
					parent.$('#mainTabs').tabs('update',{     
						tab : currTab,     
						options : {          
							content : '<iframe scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>' 
						}
					});
				} else if ('2' == reportCards.queryType) {
					parent.cardSpecial.exitEdit(relid);
				} else {
					cardSpecial.exitEdit(relid);
				}
        	}
		});
	},
	//报卡审核
	toAudit : function(relid) {
		Comm.dialogGlobal({
        	url:webroot + "/bk001Sbk/f_view/toAuditCards.shtml?relid=" + relid,
            title: '报卡审核',
            width:500,
            height:350,
            type:"iframe",
            parent:this
        });
	},
	toReturn : function(relid){
		/*$("#returnDialog").dialog({
			title: '退卡',
		    width: 400,
		    height: 200,
		    closed: true,
		    cache: false,
		    modal: true
		});*/
		$("#relid_hid").val(relid);
		$('#returnDialog').dialog('open');
	},
	//患者转归
	toOutcome : function(relid) {
		Comm.dialogGlobal({
        	url:webroot + "/bk001Sbk/f_view/toOutcome.shtml?relid=" + relid,
            title: '患者转归情况',
            width:500,
            height:350,
            type:"iframe",
            parent:this
        });
	},
	//复写患者转归
	refreshOutcome : function(jbzg,jbzgDate) {
		$('#id_jbzg').html(jbzg);
		$('#id_jbzgDate').html(jbzgDate);
	},
	//患者档案
	patientInfo : function (zyid) {
		parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
	},
	 //原始病程
 	ysbc :function (ysbcurl){
 		parent.menuInfo.clickMenu('原始病程',ysbcurl,true,'pt_note_info');
	}, 
	//提交表单处理
	formSubmit : function(refid) {
		$('#id_form_' + refid).submit();
	},
	//添加感染选择感染界面
	addInfectSelect : function(relid) {
		$('#id_select_button_' + relid).css('display','');
		$('#id_infect_div_' + relid).css('display','');
		$('#id_select_infect_' + relid).dialog('open');
	},
	//添加感染
	addInfect : function(refid, zyid, patientId, action,infectTypeId,testOrderNo,operId,relid,operName,incisionGrade) {
		var url = '/bk001Sbk/f_view/toPathogensInfoEdit.shtml?refid=' + refid;
		if ('add' == action) {
			url = '/bk001Sbk/f_view/toPathogensInfoAdd.shtml?action=add&infectTypeId='+infectTypeId+'&testOrderNo='+testOrderNo+'&operId='+operId+'&relid='+relid+'&operName='+operName+'&incisionGrade='+incisionGrade+'&regId=' + refid;
		}
		var index = $('#id_index_' + refid).val();
		if ($('#id_infectId_' + refid).combotree('isValid')) {
			var infectDiagnName = $('#id_infectId_' + refid).combotree('getText');
			var infectDiagnId = $('#id_infectId_' + refid).combotree('getValue');
			if (!$('#id_tab_' + refid).tabs('exists', infectDiagnName)) {
				$('#id_tab_' + refid).tabs('add',{
				    title: infectDiagnName,
				    selected: true,
				    href: webroot + url + '&index=' + index + '&patientId=' + patientId + '&zyid=' + zyid + '&infectDiagnId=' + infectDiagnId + '&infectDiagnName=' + encodeURI(encodeURI(infectDiagnName))
				});
				$('#id_select_infect_' + refid).dialog('close');
			} else {
				parent.$.messager.show({title : '提示',msg : '该感染已存在！' });
			}
			$('#id_infectId_' + refid).combotree('clear');
		} else {
			parent.$.messager.show({title : '提示',msg : '请选择诊断！' });
		}
	},
	//删除感染
	delInfect : function(refid) {
		//判断剩余感染数量
		var tabs = $('#id_tab_' + refid).tabs('tabs');
		if (tabs.length == 1) {
			parent.$.messager.show({title : '提示',msg : '不能删除全部感染！' });
			return;
		} else if (tabs.length == 0) {
			parent.$.messager.show({title : '提示',msg : '没有可以删除的感染！' });
			return;
		}
		var tab = $('#id_tab_' + refid).tabs('getSelected');
		if (tab) {
			$.messager.confirm('提示', '删除诊断【' + tab.panel('options').title + '】?', function (r) {
	        	if (r) {
		        	var relid = tab.panel('options').id;
					var isDel = true;
					if ('edit' == $('#id_action_' + relid).val()) {
						$.ajax({
				            url: webroot + '/bk002Grzd/f_json/delete.shtml',
				            type: 'post',
				            data: { relid: relid },
				            dataType: 'json',
				            success : function(json) {
				            	if (json.result === 'success') {
				            		parent.$.messager.show({title : '提示',msg : '删除成功！'});
								} else {
									parent.$.messager.show({title : '提示',msg : json.msg});
									isDel = false;
									return;
								}
							}
						});
					}
					if (isDel) {
						var index = $('#id_tab_' + refid).tabs('getTabIndex', tab);
		                $('#id_tab_' + refid).tabs('close', index);
		                //验证用的数据中清除
		        		$('#id_relids_' + refid).val($('#id_relids_' + refid).val().replace(',' + relid, ''));
					}
	        	}
			});
		}
	},
	//表单验证
	formValidate : function(refid) {
		var relidStr = $('#id_relids_' + refid).val();
		var relids = relidStr.split(',');
		for (var i = 0; i < relids.length; i ++) {
			var relid = relids[i];
			if ($.trim(relid).length != 0) {
				if (!$('#id_infectDate_' + relid).validatebox('isValid')) {
					return this.validateFalseDis(refid, relid, $('#id_infectDate_' + relid));
				}/* else if (!$('#id_infectDiagnName_' + relid).validatebox('isValid')) {
					return this.validateFalseDis(refid, relid, $('#id_infectDiagnName_' + relid));
				}*/ else if (!$('#id_confDate_' + relid).validatebox('isValid')) {
					return this.validateFalseDis(refid, relid, $('#id_confDate_' + relid));
				} else if (!$('#id_infectDept_' + relid).combogrid('isValid')) {
					return this.validateFalseDis(refid, relid, $('#id_infectDept_' + relid).next().find(".textbox-text"));
				}
			}
		}
		return true;
	},
	//打印报卡信息
	printReportInfo : function(relid, zyid, patientName,bk2Relid) {
		$.ajax({
            url: webroot+'/bk002Grzd/f_json/upPrint.shtml',
            type: 'post',
            data: { relid: bk2Relid,  isPrint: 1},
            dataType: 'json',
            success : function(json) {
            	var reportname = "REPORT_CARDS.cpt";
            	if(json.data==1){
            		reportname = "REPORT_CARD.cpt";
            	}
        		var url = reportUrl+'nis7/'+reportname+'&__bypagesize__=false&__filename__=' + encodeURI(encodeURI(encodeURI('感染病例登记表(' + patientName + ')'))) + '&relid=' + relid + '&zyid=' + zyid;
        		window.open(url);
        		//parent.menuInfo.clickMenu(decodeURI(decodeURI('报卡打印')), url, true, '', null);
            }
		})
	}
};

var pathogens = {
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
	//勾选送检标本处理
	checkTestOrderNo : function(obj, relid, testOrderNo,pathoName) {
		var objs = $('#id_samples_' + relid).parent().find("input:checkbox[value='" + testOrderNo + "']");
		var title = '';
		if ($(obj).is(':checked')) {
			objs.each(function(){
				if (!$(this).is(':checked')) {
					title = title + $(this).attr("title");				
				}
			});
			if(title!=''){
				$('#tips').html('提示：'+pathoName+'的送检标本同时检出了'+title+'，请核实致病菌');
			}	
		} 
	},
	//勾选抗菌药物处理
	checkYzid : function(obj,relid,yzId) {
		alert(yzId);
		var objs = $('#id_kjywsy_' + relid).parent().find("input:checkbox[value='" + yzId + "']");
		if ($(obj).is(':checked')) {
			objs.attr("checked", true);
		} else {
			objs.attr("checked", false);
		}
	},
	//弹出多耐详情窗口
	showMDRDetail : function(){
	}
};