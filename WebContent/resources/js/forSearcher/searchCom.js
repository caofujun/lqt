/**
* 筛选器公共对象
*/
SEARCH = {
		// 组装通用列
		assemColumn:function(data){
			var items= [];
			var checkBoxs = {field:'_id',title:'全选',checkbox:true};
			items.push(checkBoxs);
			$.each(data,function(index,itemValue){
				var item = {};
				if(itemValue.name == "患者姓名"){
					item = {field:itemValue.value,title:itemValue.name,sortable:true,width:itemValue.width,formatter:function(value,r){
	                	var html = "";
	                	if(r.sex == '0'){
	                		html = value+"(男)";
	                	}else if(r.sex == '1'){
	                		html = value+"(女)";
	                	}else{
	                		html = value;
	                	}
	                	return html;
					}};
					items.push(item);
					return true;
				}
				// 需要翻译字段
				if(itemValue.translate != null && itemValue.translate != ""){
					item = {field:itemValue.translate,title:itemValue.name,sortable:true,width:itemValue.width};
					items.push(item);
					return true;
				}
				item = {field:itemValue.value,title:itemValue.name,sortable:true,width:itemValue.width};
				items.push(item);
			});
			return items;
		},
		// 加载表格数据
		loadGridData:function(panel){
			var startTime = $("input[timeTag='timeTypeStart']").val();
			var endTime = $("input[timeTag='timeTypeEnd']").val();
			if(startTime != "" && endTime != null){
				var dates = new Date(startTime);
				var datee = new Date(endTime);
				if(dates > datee){
					$.messager.alert('提示', '查询条件起始时间大于结束时间.');
					return;
				}
			}
	        $('#'+panel).datagrid({
	        	queryParams:SEARCH.getParams(),
	            pageNumber: 1 
	        });
		},
		//取得查询参数
		getParams:function(){
			autoTip.clear();
			var json = {"vdateStart":$("#_vdate_start").val() == '' ? "" : $("#_vdate_start").val(),
					"vdateEnd":$("#_vdate_end").val() == '' ? "" : $("#_vdate_end").val(),
					"depNo":""+($("#depNos").val() == null ? "" : $("#depNos").val()),
					"diseaseCode":""+($("#diseas_type").val() == null ? "" : $("#diseas_type").val()),
					"icdCode":""+($("#diseasesSearchValue").val() == null ? "" : $("#diseasesSearchValue").val()),
					"doctorName":$("#doctorName").val() == null ? "" : $("#doctorName").val(),
					"age":$("#age").val() == null ? null : $("#age").val(),
					"patientName":$("#patientName").val() == null ? "" : $("#patientName").val(),
					"searchId":$("#searchId").val(),
					"followStatus":$("#_followStatus").val() == '' ? "" : $("#_followStatus").val(),
					"visitTimeStart":$("#_visitTime_start").val() == '' ? "" : $("#_visitTime_start").val(),
					"visitTimeEnd":$("#_visitTime").val() == '' ? "" : $("#_visitTime").val(),
					"backDateStart" :$("#_backDate_start").val() == '' ? "" : $("#_backDate_start").val(),
					"backDateEnd" :$("#_backDate_end").val() == '' ? "" : $("#_backDate_end").val(),
					"dgsType" : $("#dgsType").val() == '' ? '' : $("#dgsType").val(),
					"smsSqlTemp" : $("#smsSqlTemp").val() == '' ? '' : $("#smsSqlTemp").val()
			};
			return json;
		},
		getParamsUrl : function(){
			var params = "";
			params += "smsSqlTemp="+($("#smsSqlTemp").val() == '' ? '' : $("#smsSqlTemp").val());
			params += "&vdateStart="+($("#_vdate_start").val() == '' ? "" : $("#_vdate_start").val());
			params += "&vdateEnd="+($("#_vdate_end").val() == '' ? "" : $("#_vdate_end").val());
			params += "&visitTimeStart="+($("#_visitTime_start").val() == '' ? "" : $("#_visitTime_start").val());
			params += "&visitTimeEnd="+($("#_visitTime").val() == '' ? "" : $("#_visitTime").val());
			params += "&backDateStart="+($("#_backDate_start").val() == '' ? "" : $("#_backDate_start").val());
			params += "&backDateEnd="+($("#_backDate_end").val() == '' ? "" : $("#_backDate_end").val());
			params += "&depNo="+($("#depNos").val() == null ? '' : $("#depNos").val());
			params += "&diseaseCode="+($("#diseas_type").val() == null ? "" : $("#diseas_type").val());
			params += "&icdCode="+($("#diseasesSearchValue").val() == null ? "" : $("#diseasesSearchValue").val());
			params += "&patientName="+($("#patientName").val() == null ? "" : $("#patientName").val());
			params += "&searchId="+$("#searchId").val();
			params += "&dgsType="+($("#dgsType").val() == '' ? '' : $("#dgsType").val());
			return params;
		},
		// 刷新页面
		refreshData:function(panel){
			$('#'+panel).datagrid('reload');
		},
		//公共查询下拉框
		comCombobox:function(id,id2,urlp,loaclDataArray,dataArray,checkAllMethodName){
			$('#'+id).combobox({url:webroot+urlp,
					valueField:'key', 
					textField:'value',
					editable:false,
					multiple:true,
					formatter:function(row){
						    var vtemp = 'all'+id;
							if(row.key != vtemp){
								loaclDataArray.push(row.key);
								loaclDataArray = loaclDataArray.uniqueData();
								return '<input type="checkbox" flag="'+id+'" id="'+row.key+'"/>'+row.value;
							}else{
								var checked = "";
								try {
									if(row.selected){
										checked = " checked='checked' ";
									}
								} catch (e) {
								}
								return '<input type="checkbox" '+checked+' id="'+id+'All" onclick="SEARCH.'+checkAllMethodName+'" />'+row.value;
							}
					},
					onSelect:function(record){
						var all = $("#"+id+"All");
						var vtemp = 'all'+id;
						if(record.key == vtemp){
							//去掉所有选择项
							$("input[flag='"+id+"']").attr('checked',false);
							$('#'+id).combobox('setValues',['all'+id]);
							all.attr('checked',true);
							$('#'+id2).val("");
							return;
						}
						if(all != undefined && all.attr('checked') == 'checked'){
							$('#'+id).combobox('setValues',['all'+id]);
							$('#'+id2).val("");
							return;
						}
						$('#'+record.key).attr('checked',true);
						dataArray.push(record.key);
						dataArray = dataArray.uniqueData();
						$('#'+id2).val(dataArray.join(','));
					},
					onUnselect:function(record){
						var all = $("#"+id+"All");
						var vtemp = 'all'+id;
						if(record.key == vtemp){
							$('#'+id).combobox('setValues',[]);
							all.attr('checked',false);
							$('#'+id2).val("");
							return;
						}
						$('#'+record.key).attr('checked',false);
						$('#all').attr('checked',false);
						dataArray.remove(record.key);
						dataArray = dataArray.uniqueData();
						$('#'+id2).val(dataArray.join(','));							
					}
			});
		},
		depdata:[],
		deplocalData:[],
		orgCombobox : function(){
			//科室
			var depName = encodeURIComponent(encodeURIComponent("默认科室"));
			SEARCH.comCombobox('dep_nos','depNos','/dep/json/queryByDepTypeEasyUi.shtml?allDepName='+depName+'',SEARCH.deplocalData,SEARCH.depdata,'depcheckAll()');
		},
		depcheckAll:function(){
			//去掉所有选择项
			$("input[flag='dep_nos']").attr('checked',false);
			$('#depNos').val("");
			if($("#dep_nosAll").attr('checked')){
				$('#dep_nos').combobox('setValues',['alldep_nos']);
				$("input[flag='dep_nos']").attr('disabled','disabled');
			}else{
				$('#dep_nos').combobox('setValues',['']);
				$("input[flag='dep_nos']").removeAttr('disabled');
			}
		},
		diseasTypedata:[],
		diseasTypelocalData:[],
		diseasTypeCombobox : function(){
			//病种
			var sysDiseasetype = encodeURIComponent(encodeURIComponent("默认病种"));
			SEARCH.comCombobox('diseaseCodes','diseas_type','/sysDiseasetype/f_json/getAllSysDiseasetypeEasyUi.shtml?allDiseaseName='+sysDiseasetype+'',SEARCH.diseasTypelocalData,SEARCH.diseasTypedata,'diseasTypecheckAll()');
		},
		diseasTypecheckAll:function(){
			//去掉所有选择项
			$("input[flag='diseaseCodes']").attr('checked',false);
			$('#diseas_type').val("");
			if($("#diseaseCodesAll").attr('checked')){
				$('#diseaseCodes').combobox('setValues',['alldiseaseCodes']);
				$("input[flag='diseaseCodes']").attr('disabled','disabled');
			}else{
				$('#diseaseCodes').combobox('setValues',['']);
				$("input[flag='diseaseCodes']").removeAttr('disabled');
			}
		}
};

//查询元素索引位置
Array.prototype.index = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
//删除数组元素
Array.prototype.remove = function(val) {
    var index = this.index(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

//数组唯一
Array.prototype.uniqueData = function(){  
   var res = [],hash={};
   for(var i=0,elem; i<this.length; i++){
   		elem=this[i];
   		if(!elem) continue;
  		var s = hash[elem];
	   	if(!hash[elem]){
	   		res.push(elem);
	   		hash[elem]=1;
	   	}
   }
		return res;
};

//查找数组是否包含某个元素
Array.prototype.contain = function(c) {
	var i=0;
	for(;i<this.length && this[i]!=c;i++);
	return (i==this.length) ? false : true;
};