/**
 * 产品公用的js
 * 2014-10-23
 * yuejing
 */
var Csm = {};

Csm.url = {
		//查询医院列表地址，支持模糊查询
		unitQuery : webroot + '/unit/json/query.shtml',
		unitListQuery : webroot + '/unit/json/queryList.shtml',
		unitGet : webroot + '/unit/json/get.shtml',
		//查询科室相关地址
		depQuery : webroot + '/dep/json/query.shtml',
		depListQuery : webroot + '/dep/json/queryList.shtml',
		authDepListQuery : webroot + '/dep/f_json/pageQueryForAuthDept.shtml',
		depGet : webroot + '/dep/json/get.shtml',
		//查询医生相关地址
		docQuery : webroot + '/doctor/json/query.shtml',
		docListQuery : webroot + '/doctor/json/queryList.shtml',
		docGet : webroot + '/doctor/json/get.shtml',
		//area相关
		areaPid : webroot + '/area/json/findByParentId.shtml',
		areaId : webroot + '/area/json/get.shtml',
		
		//诊断
		dgsQuery: webroot + '/icd10/f_view/query.shtml',
		dgsGet : webroot +'/sysDisease/f_json/findSysDiseaseByCodes.shtml',
		//疾病
		diseaseQueryList: webroot + '/cdc/f_json/chooseDiseaseForControl.shtml',
		
		//手术
		opeQuery: webroot + '/icd9/f_view/query.shtml',
		
		//感染部位
		grPositionQuery: webroot + '/bk002Grzd/f_json/queryGrPosition.shtml',
		
		//药物
		drugQuery: webroot + '/antibiosisDrug/f_view/query.shtml',
		
		//菌属
		jszdQuery: webroot + '/xn005Jszd/f_view/query.shtml',
		
		//病原体
		bytQuery: webroot + '/xn002Byt/f_view/query.shtml',
		
		
		//抗菌药物
		kjywQuery: webroot + '/xn003Kjyw/f_view/query.shtml',
		
		//抗菌药物分类
		kjywflQuery: webroot + '/xn006Kjywfl/f_view/query.shtml',
		
		//基础抗菌药物
		zgKjywQuery: webroot + '/zg010Kjyw/f_view/query.shtml',
		
		//标本
		bbDictQuery: webroot + '/nyBbDict/f_json/query.shtml',
		
		//标本检验项目
		standardQuery:webroot  + '/yj003Standard/f_view/query.shtml',
		
		//字典
		sysDicQueryList: webroot + '/sysDict/f_json/queryList.shtml',
		
		//革兰氏分类
		glsflQuery: webroot + '/xn002Byt/f_json/glsfl.shtml',
};

Csm.text = {
		//id显示名称的ID/value：医院ID/type: val、html赋值的形式默认为val
		unit: function(attr) {
			Csm.text.comm({
				url: Csm.url.unitGet,
				data: {id: attr.value},
				id: attr.id,
				type: attr.type
			});
		},
		comm: function(attr) {
			$.ajax({
				url: attr.url,
				data: attr.data,
	 			dataType: 'json',
	 			type: 'post',
				error: function() { 
					if(attr.alert){
						alert('数据加载失败'); 
					}
				},
				success: function(json) {
					if(attr.type === 'val') $('#'+attr.id).val(json.value);
					else if(attr.type === 'html') $('#'+attr.id).html(json.value);
					else $('#'+attr.id).val(json.value);
				}
			});
		}
};

Csm.valueValite = {
	combogrid : function(id) {
		var select = $('#' + id).combogrid('grid').datagrid('getSelected');
		if (!select) {
			$('#' + id).combogrid('clear');
		}
	},
	combobox : function(id) {
		var value = $('#' + id).combobox('getValue');
		if ($.trim(value).length == 0) {
			return;
		}
		var opts = $('#' + id).combobox('options');
		var data = $('#' + id).combobox('getData');
		var flag = true;
		for (var i = 0; i < data.length; i++) {
			if (value == data[i][opts.idField]){
				flag = false;
				break;
			}
		}
		if (flag) {
			$('#' + id).combobox('clear');
		}
	}
};

Csm.comboBox = {
	unit : function(param) {
		$('#' + param.id).combobox({
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
		    valueField:'unitId',
            textField:'hospName',
            editable : false,
			url: Csm.url.unitListQuery+'?flag='+(typeof(param.flag) == "undefined"?"":param.flag)+ '&page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
			onSelect : (typeof(param.onSelect) == "undefined"? function(){}:param.onSelect)
		});
	},
	/**
	 * 可输入easyui下拉框
	 * @param attr
	 */
	inputBox : function(attr){
		$("#"+attr.id).combobox({
			delay: attr.delay == null ? 1000 : attr.delay,    
		    mode: 'remote',
		    valueField:attr.valueField == '' || attr.valueField == null ? 'key' : attr.valueField,
			textField:attr.textField == '' || attr.textField == null ? 'value' : attr.textField,
			url: attr.url,
			width:attr.width,
			height:attr.height,
			required: attr.required == null ? false : attr.required,
			editable:attr.editable == null? true : attr.editable,
			onLoadSuccess : function(){
				//清除输入值
				var emptyTextInput = $("#"+attr.id).siblings(".combo").children(".combo-text");
				var emptyText = emptyTextInput.val();
				if(attr.dataTip && (emptyText == '' || emptyText == null)){
					emptyTextInput.val(attr.dataTip);
				}
				var allowSetValue = Csm.comboBox.getUrlParam(attr.url, 'allowSetValue');
				var notClean = attr.notClean;
				//不清理数据
				if(notClean != null && notClean){
					return;
				}
				if(allowSetValue == null || allowSetValue == '0'){
					$("input[name='"+attr.id+"']").val('');
				}
			},
			formatter:attr.formatter,
			onSelect : attr.onSelect
		});
	},
	getUrlParam : function(url,paramName){
		if (url.indexOf("?") != -1) { 
			var str = url.substr(url.indexOf("?")+1); 
			if (str.indexOf("&") != -1) {
	            strs = str.split("&");
	            for (var i = 0; i < strs.length; i++) {
	                var tempName = strs[i].split("=")[0];
	                if(tempName.indexOf(paramName) != -1){
	                	return strs[i].split("=")[1];
	                }
	            }
	            return null;
	        } else {
	        	return str.split("=")[1];
	        }
		}
		return null; 
	}
};

Csm.select = {
		/*
		 * 加载select，有等级关系的
		 * attr : {
		 * 		url: 请求地址
		 * 		data: 请求参数
		 * 		id: 下拉框控件的ID
		 * 		value: 选中的值
		 * 		headerKey: 第一项显示的值
		 * 		headerValue: 第一项显示的Text
		 * 		multiple: 是否多选【true/false默认false】
		 * 		success: 成功后的回调函数
		 * 		kcode: code值的key
		 * 		kvalue: value值的key
		 * 		pid: pid值的key【为空则表示没有等级关系】
		 * }
		 */
		load : function(attr) {
			//if(!attr.kcode) attr.kcode = 'kcode';
			//if(!attr.kvalue) attr.kvalue = 'kvalue';
			//if(!attr.pid) attr.pid = 'pid';
			$.ajax({
				url: attr.url,
				data: attr.data,
	 			dataType: 'json',
	 			type: 'post',
				error: function() { 
					if(attr.alert){
						alert('数据加载失败'); 
					}
				},
				success: function(json) {
					if(json.result === 'error_not_login') {
						alert('您还没有登录，无法请求：' + attr.url);
						return;
					}
					var _obj = $('#'+attr.id).empty();
					if(attr.headerValue!=undefined)
						_obj.append(['<option value="',attr.headerKey,'">',attr.headerValue,'</option>'].join(''));
					if(json.length > 0) {
						/*var _obj = $('#'+attr.id).empty();
						if(attr.headerValue!=undefined)
							_obj.append(['<option value="',attr.headerKey,'">',attr.headerValue,'</option>'].join(''));*/
						var _new = new Array();

						if(attr.pid) {
							function getNew(data, _new, obj, empty) {
								_new.push('<option value="',obj[attr.kcode],'">',empty,'|-&nbsp;',obj[attr.kvalue],'</option>');
								$.each(data, function(j, cldObj) {
									if(obj[attr.kcode]===cldObj[attr.pid]) {
										getNew(data, _new, cldObj, empty+'&nbsp;&nbsp;&nbsp;&nbsp;');
									}
								});
							}
							$.each(json, function(i, obj) {
								if(obj[attr.pid]===undefined || obj[attr.pid]===null || obj[attr.pid]=='0') {
									getNew(json, _new, obj, '&nbsp;&nbsp;');
								}
							});
						} else {
							$.each(json, function(i, obj) {
								_new.push('<option value="',obj[attr.kcode],'">',obj[attr.kvalue],'</option>');
							});
						}
						_obj.append(_new.join(''));
						if(attr.multiple) _obj.attr('multiple', 'multiple');
						if(attr.placeholder) _obj.attr('placeholder', attr.placeholder);
						if(attr.value!=undefined) {
							var _valArr = attr.value.split(',');
							_obj.find('option').each(function(i, option) {
								$(option).attr('selected', false);
								$.each(_valArr, function(j, val) {
									/*alert(val);
									alert($(option).val());*/
									if(val != '' && val == $(option).val()) {
										$(option).attr('selected', true);
									}
								});
							});
							//_obj.val(attr.value);
						}
						if(attr.success) attr.success();
						if(attr.multiple) {
							_obj.select2();
						}
					}
				}
			});
		},
		/*
		 * 省市区联动
		 * param: {
		 * 		panelId 	: 放控件的ID
		 * 		valueId		: 放值的隐藏文本ID
		 * 		valueName 	: 放值的隐藏文本NAME,
		 * 		value 		: 选中的值
		 * }
		 */
		area : function(param) {
			if(!param.value) param.value = '';
			$('#' + param.panelId).empty().append('<input type="hidden" id="'+param.valueId+'" name="'+param.valueName+'" value="'+param.value+'"/>');
			if(param.value && param.value != '') {
				$.ajax({
		 			url : Csm.url.areaId,
		 			data: {id:param.value},
		 			dataType: 'json',
		 			type: 'post',
		 			error: function() { 
		 				if(attr.alert){
							alert('数据加载失败'); 
						}
		 			},
		 			success: function(json) {
		 				if(!json) {
		 					return;
		 				}
		 				var _paths = json.idPath.split(',');
		 				$.each(_paths, function(i, obj) {
		 					if(obj === '') {
		 						return false;
		 					}
		 					switch (i) {
							case 0:
								areaSelect({
		 							type: 'p',
		 							parentId: obj,
		 							panelId: param.panelId,
		 							valueId: param.valueId,
		 							value: _paths[parseInt(i + 1)]
		 						});
								break;
							case 1:
								areaSelect({
		 							type: 'c',
		 							parentId: obj,
		 							panelId: param.panelId,
		 							valueId: param.valueId,
		 							value: _paths[parseInt(i + 1)]
		 						});
								break;
							case 2:
								areaSelect({
		 							type: 'a',
		 							parentId: obj,
		 							panelId: param.panelId,
		 							valueId: param.valueId,
		 							value: _paths[parseInt(i + 1)]
		 						});
								break;
							default:
								break;
							}
		 				});
		 			}
				});
				return;
			}
			areaSelect({
				type: 'p',
				parentId: 1,
				panelId: param.panelId,
				valueId: param.valueId
			});
			
			function areaSelect(p) {
				if(!p.parentId || p.parentId == '') {
					return;
				}
				$('#' + p.panelId).append(' <select id="'+p.type+p.valueId+'" style="display:none;min-width: 140px;"></select>');
				$.ajax({
		 			url : Csm.url.areaPid,
		 			data: {parentId:p.parentId},
		 			dataType: 'json',
		 			type: 'post',
		 			error: function() { alert('数据加载失败'); },
		 			success: function(json) {
		 				var _select = $('#'+p.type+p.valueId);
		 				if(json.length == 0) {
		 					_select.select2('destroy');
		 					_select.remove();
		 					return;
		 				}
		 				_select.css('display', 'inline-block');
		 				_select.append('<option value="">-- 请选择 --</option>');
		 				$.each(json, function(i, obj) {
		 					var _selected = '';
		 					if(obj.areaId == p.value) {
		 						_selected = 'selected="selected"';
		 					}
		 					_select.append('<option value="'+obj.areaId+'" '+_selected+'>'+obj.areaName+'</option>');
		 				});
		 				_select.select2();
		 				
		 				if(p.type === 'p') {
		 					$('#' + p.type + p.valueId).change(function() {
		 						var _val = $('#' + p.type + p.valueId).val();
		 						$('#' + p.valueId).val(_val);
		 						$('#c' + p.valueId).select2('destroy');
		 						$('#a' + p.valueId).select2('destroy');
		 						$('#c' + p.valueId).remove();
		 						$('#a' + p.valueId).remove();
		 						areaSelect({
		 							type: 'c',
		 							parentId: _val,
		 							panelId: p.panelId,
		 							valueId: p.valueId
		 						});
		 					});
		 				} else if(p.type === 'c') {
		 					$('#' + p.type + p.valueId).change(function() {
		 						var _val = $('#' + p.type + p.valueId).val();
		 						if(_val === '') {
		 							_val = $('#p' + p.valueId).val();
		 						}
		 						$('#' + p.valueId).val(_val);
		 						$('#a' + p.valueId).select2('destroy');
		 						$('#a' + p.valueId).remove();
		 						areaSelect({
		 							type: 'a',
		 							parentId: _val,
		 							panelId: p.panelId,
		 							valueId: p.valueId
		 						});
		 					});
		 				} else if(p.type === 'a') {
		 					$('#' + p.type + p.valueId).change(function() {
		 						var _val = $('#' + p.type + p.valueId).val();
		 						if(_val === '') {
		 							_val = $('#c' + p.valueId).val();
		 						}
		 						$('#' + p.valueId).val(_val);
		 					});
		 				}
		 			}
		 		});
			}
		},
		
		/*
		 * 医院选择和搜索框
		 * 	param: {
		 * 		id 			: 为医院input的ID名称
		 * 		idAddInput	: 添加自己输入的字符串到选择列表中[0否、1是，默认为0]
		 * 		depId		: 科室input的ID名称[不为空，则会出现联动科室]
		 * 		docId		: 医生input的ID名称[不为空，则会出现联动科室和医生，前提条件是depId不为空]
		 * 	}
		 */
		unit : function(param) {
			$('#' + param.id).select2Remote({
				//这里填写空选项时显示的文字
				placeholder: '请输入医院名称',
				//远程加载的url
				url: Csm.url.unitQuery,
				//初始化url
				initUrl: Csm.url.unitGet,
				idAddInput: param.idAddInput
			});
			if(param.depId) {
				//加载科室信息
				Csm.select.dep({
					id : param.depId,
					unitId : param.id,
					docId : param.docId
				});
			}
		},
		/*
		 * 科室选择和搜索框
		 * 	param: {
		 * 		id 			: 为科室input的ID名称
		 * 		unitId		: 医院input的ID名称
		 * 		docId		: 医生控件的ID名称[不为空，则会联动医生]
		 * 	}
		 */
		dep : function(param) {
			$('#' + param.id).select2Remote({
				//这里填写空选项时显示的文字
				placeholder: '请输入科室名称',
				//远程加载的url
				url: Csm.url.depQuery,
				//初始化url
				initUrl: Csm.url.depGet,
				data: {unitId: param.unitId},
				initData: {unitId: param.unitId},
				dataType: param.dataType
			});
			if(param.docId) {
				//加载科室信息
				Csm.select.doc({
					id : param.docId,
					unitId : param.unitId,
					depId : param.id
				});
			}
		},
		/*
		 * 医生选择和搜索框
		 * 	param: {
		 * 		id 			: 为医生input的ID名称
		 * 		unitId		: 医院input的ID名称
		 * 		depId		: 科室input的ID名称
		 * 	}
		 */
		doc : function(param) {
			$('#' + param.id).select2Remote({
				//这里填写空选项时显示的文字
				placeholder: '请输入医生名称',
				//远程加载的url
				url: Csm.url.docQuery,
				//初始化url
				initUrl: Csm.url.docGet,
				data: {unitId: param.unitId, depNo: param.depId},
				initData: {unitId: param.unitId, depNo: param.depId}
			});
		},
		/*
		 * 根据医院ID选择多个科室
		 * param: {
		 * 		id		: 科室控件ID
		 * 		url		: 请求地址
		 * 		data	: 请求参数
		 * 		unitId	: 医院ID的值
		 * 		values	: [值1,值2,值3]
		 * }
		 */
		depMultiple : function(param) {
			if(!param.url) {
				param.url = Csm.url.depQuery;
			}
			if(!param.data) {
				param.data = {unitId: param.unitId};
			}
			var _dep = $('#'+param.id);
			$.ajax({
				url: param.url,
				data: param.data,
				type: 'post',
				dataType: 'json',
				success : function(data) {
					//设置可以多选
					_dep.attr('multiple', 'multiple');
					$.each(data, function(i, obj) {
						var _sel = '';
						if(param.values) {
							$.each(param.values, function(j, val) {
								if(val == obj.key) {
									_sel = 'selected="selected"';
									return false;
								}
							});
						}
						_dep.append(['<option value="',obj.key,'" ',_sel,'>',obj.value,'</option>'].join(''));
					});
					_dep.select2('destroy');
					_dep.select2({ placeholder: '请选择科室' });
				}
			});
		},
		
		//诊断
		dgs : function(param) {
			$('#' + param.id).select2Remote({
				//这里填写空选项时显示的文字
				placeholder: '请输入诊断名称',
				//远程加载的url
				url: Csm.url.dgsQuery,
				//初始化url
				initUrl: Csm.url.dgsGet
			});
		}
};


Csm.combogrid = {

	//【必传】控件名称
	//id: 'unit',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '${unit.unitId}',
	//【可选参数】不传默认区session的医院ID
	//hospId: '${unit.unitId}',
	//【可选参数】1：回调,回调方法setDep()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	unit : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
            idField:'unitId',
            panelWidth: 200,
	        panelHeight: 200,
            textField:'hospName',
			url: Csm.url.unitListQuery+'?flag='+(typeof(param.flag) == "undefined"?"":param.flag)+ '&page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
            columns:[
            	[
                 {field:'hospName',sortable:true,width:175},
                ]
            ],
    		onClickRow : (typeof(param.onClickRow) == "undefined"? function(){}:param.onClickRow)
		});
	},
	
	//【必传】控件名称
	//id: 'code',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '${unit.unitId}',
	//【可选参数】不传默认区session的医院ID
	//hospId: '${unit.unitId}',
	//【可选参数】1：回调,回调方法setDep()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	grPosition : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
            idField:'flInfectCode',
            panelWidth: 150,
	        panelHeight: 200,
            textField:'flInfectName',
			url: Csm.url.grPositionQuery+'?flag='+(typeof(param.flag) == "undefined"?"":param.flag),
            columns:[
            	[
                 {field:'flInfectCode',title:'编号',sortable:true,width:50},  
                 {field:'flInfectName',title:'感染部位',sortable:true,width:100},
                ]
            ],
            onSelect : (typeof(param.onSelect) == "undefined"? function(){}:param.onSelect),
    		onClickRow : (typeof(param.onClickRow) == "undefined"? function(){}:param.onClickRow),
    		onHidePanel : (typeof(param.onHidePanel) == "undefined"? function(){}:param.onHidePanel),
    		onLoadSuccess : (typeof(param.onLoadSuccess) == "undefined"? function(){}:param.onLoadSuccess)
		});
		
	},
		
	//【必传】控件名称
	//id: 'dep',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '${doctor.deptId}',
	//【可选参数】不传默认区session的医院ID
	//hospId: '${doctor.hospId}',
	//【可选参数】科室属性【1：监控科室、2：住院科室、3：门诊科室】
	//dataType: '1',
	//【可选参数】1：回调,回调方法setDep()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	dep : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
            idField:'deptId',
            panelWidth: 320,
            multiple:(typeof(param.multiple) == "undefined"? false:param.multiple),
	        panelHeight: (typeof(param.panelHeight) == "undefined"? 300:param.panelHeight),
	        readonly: (typeof(param.readonly) == "undefined"? false:param.readonly),
            textField:'deptName',
			url: (typeof(param.authDep) == 'undefined' ?Csm.url.depListQuery : Csm.url.authDepListQuery) +'?hospId='+(typeof(param.hospId) == "undefined"?"":param.hospId)+'&page=1&size='+(typeof(param.size) == "undefined"?"2000":param.size),
			queryParams: {
				ifmzoffice: (typeof(param.ifmzoffice) == 'undefined' ? '' : param.ifmzoffice),
				iffocus: (typeof(param.iffocus) == 'undefined' ? '' : param.iffocus),
				ifcaseoffice: (typeof(param.ifcaseoffice) == 'undefined' ? '' : param.ifcaseoffice),
				ifenvoffice: (typeof(param.ifenvoffice) == 'undefined' ? '' : param.ifenvoffice),
				ifchildoffice: (typeof(param.ifchildoffice) == 'undefined' ? '' : param.ifchildoffice),
				ificu: (typeof(param.ificu) == 'undefined' ? '' : param.ificu),
				flag: (typeof(param.flag) == 'undefined' ? 1 : param.flag),
				isCaseOrEnvo: (typeof(param.isCaseOrEnvo) == 'undefined' ? '' : param.isCaseOrEnvo),
				dataType: (typeof(param.dataType) == 'undefined' ? '' : param.dataType),
				chargeManId: (typeof(param.chargeManId) == 'undefined' ? '' : param.chargeManId)
			},
			columns:[
            	[
                 {field:'deptId',title:'科室编号',sortable:true,width:100},  
                 {field:'deptName',title:'科室名称',sortable:true,width:180}
                ]
            ],
    		/*onSelect: function(index, row){
    			var depObj = new Object();
    			depObj.key = $('#'+param.id).combobox('getValue');
    			depObj.value = $('#'+param.id).combobox('getText');
    			if(param.callback == 1){
        			setDep(depObj);
    			}
    		},*/
    		onSelect : (typeof(param.onSelect) == "undefined"? function(){}:param.onSelect),
    		onClickRow : (typeof(param.onClickRow) == "undefined"? function(){}:param.onClickRow),
    		onHidePanel : (typeof(param.onHidePanel) == "undefined"? function(){}:param.onHidePanel),
    		onLoadSuccess : (typeof(param.onLoadSuccess) == "undefined"? function(){}:param.onLoadSuccess),
    		onBeforeLoad : (typeof(param.onBeforeLoad) == "undefined"? function(){}:param.onBeforeLoad)
		});
	},
	crbDisease : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
            idField:'diseaseid',
            panelWidth: 320,
            type:'POST',
	        panelHeight: (typeof(param.panelHeight) == "undefined"? 300:param.panelHeight),
	        readonly: (typeof(param.readonly) == "undefined"? false:param.readonly),
            textField:'diseasename',
			url: Csm.url.diseaseQueryList +'?hospId='+(typeof(param.hospId) == "undefined"?"":param.hospId)+'&page=1&size='+(typeof(param.size) == "undefined"?"2000":param.size),
			queryParams: {
				'keyword': (typeof(param.keyword) == 'undefined' ? '' : param.keyword)
			},
			columns:[
            	[
                 {field:'diseaseid',title:'疾病编号',sortable:true,width:100},  
                 {field:'diseasename',title:'疾病名称',sortable:true,width:180}
                ]
            ],
    		onSelect : (typeof(param.onSelect) == "undefined"? function(){}:param.onSelect),
    		onClickRow : (typeof(param.onClickRow) == "undefined"? function(){}:param.onClickRow),
    		onHidePanel : (typeof(param.onHidePanel) == "undefined"? function(){}:param.onHidePanel),
    		onLoadSuccess : (typeof(param.onLoadSuccess) == "undefined"? function(){}:param.onLoadSuccess),
    		onBeforeLoad : (typeof(param.onBeforeLoad) == "undefined"? function(){}:param.onBeforeLoad)
		});
	},
	getDepByType : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
            idField:'deptId',
            panelWidth: 320,
	        panelHeight: (typeof(param.panelHeight) == "undefined"? 300:param.panelHeight),
	        readonly: (typeof(param.readonly) == "undefined"? false:param.readonly),
            textField:'deptName',
			url: Csm.url.depListQuery+'?hospId='+(typeof(param.hospId) == "undefined"?"":param.hospId)+'&page=1&size='+(typeof(param.size) == "undefined"?"2000":param.size),
			queryParams: {
				ifcaseoffice: (typeof(param.ifcaseoffice) == 'undefined' ? '' : param.ifcaseoffice),
				ifenvoffice: (typeof(param.ifenvoffice) == 'undefined' ? '' : param.ifenvoffice),
				ifchildoffice: (typeof(param.ifchildoffice) == 'undefined' ? '' : param.ifchildoffice),
				ificu: (typeof(param.ificu) == 'undefined' ? '' : param.ificu),
				isCaseOrEnvo: (typeof(param.isCaseOrEnvo) == 'undefined' ? '' : param.isCaseOrEnvo),
				dataType: (typeof(param.dataType) == 'undefined' ? '' : param.dataType)
			},
			columns:[
            	[
                 {field:'deptId',title:'科室编号',sortable:true,width:100},  
                 {field:'deptName',title:'科室名称',sortable:true,width:180},
                ]
            ],
    		/*onSelect: function(index, row){
    			var depObj = new Object();
    			depObj.key = $('#'+param.id).combobox('getValue');
    			depObj.value = $('#'+param.id).combobox('getText');
    			if(param.callback == 1){
        			setDep(depObj);
    			}
    		},*/
    		onSelect : (typeof(param.onSelect) == "undefined"? function(){}:param.onSelect),
    		onClickRow : (typeof(param.onClickRow) == "undefined"? function(){}:param.onClickRow),
    		onHidePanel : (typeof(param.onHidePanel) == "undefined"? function(){}:param.onHidePanel),
    		onLoadSuccess : (typeof(param.onLoadSuccess) == "undefined"? function(){}:param.onLoadSuccess)
		});
	},

	//【必传】控件名称
	//id: 'dep',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '${doctor.deptId}',
	//【可选参数】不传默认区session的医院ID
	//hospId: '${doctor.hospId}',
	//【可选参数】不传医院查所有医生
	//deptId: '${doctor.deptId}',
	//【可选参数】1：回调,回调方法setDep()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	doctor : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:(typeof(param.idField) == "undefined"?"employeeId":param.idField),
	        panelWidth: 300,
	        panelHeight: 300,
	        readonly: (typeof(param.readonly) == "undefined"? false:param.readonly),
	        textField:'employeeName',
			url: Csm.url.docListQuery+'?hospId='+(typeof(param.hospId) == "undefined"?"":param.hospId)+'&deptId='+(typeof(param.deptId) == "undefined"?"":param.deptId)+'&page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
			queryParams: {
				defValue: (typeof(param.value) == 'undefined' ? '' : param.value),
				cclass: (typeof(param.cclass) == 'undefined' ? '' : param.cclass)
			},
			columns:[
	        	[
	             {field:'employeeId',title:'医生工号',sortable:true,width:100},  
	             {field:'employeeName',title:'医生姓名',sortable:true,width:125},  
	             /*{field:'showDeptId',title:'科室',sortable:true,width:150},*/
	            ]
	        ],
			onSelect: (typeof(param.onSelect) != "undefined"?param.onSelect:function(){
				var doctorObj = new Object();
				doctorObj.key = $('#'+param.id).combobox('getValue');
				doctorObj.value = $('#'+param.id).combobox('getText');
				if(param.callback == 1){
	    			setDoctor(doctorObj);
				}
			}),
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow),
    		onHidePanel : (typeof(param.onHidePanel) == "undefined"? function(){}:param.onHidePanel),
    		onLoadSuccess : (typeof(param.onLoadSuccess) == "undefined"? function(){}:param.onLoadSuccess),
    		onBeforeLoad : (typeof(param.onBeforeLoad) == "undefined"? function(){}:param.onBeforeLoad)
		});
	},

	//【必传】控件名称
	//id: 'icd10',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//【可选参数】1：回调,回调方法setIcd10()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	icd10 : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    //text: (typeof(param.text) == "undefined"?"":param.text),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:'icdCode',
	        panelWidth: 400,
	        panelHeight: 300,
	        textField:'icdName',
			url: Csm.url.dgsQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
	        columns:[
	        	[
	             {field:'icdCode',title:'诊断编号',sortable:true,width:100},  
	             {field:'icdName',title:'诊断名称',sortable:true,width:275},
	            ]
	        ],
			onSelect: function(){
				var doctorObj = new Object();
				doctorObj.key = $('#'+param.id).combobox('getValue');
				doctorObj.value = $('#'+param.id).combobox('getText');
				if(param.callback == 1){
	    			setIcd10(doctorObj);
				}
			},
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow),
			onHidePanel : (typeof(param.onHidePanel) == "undefined"? function(){}:param.onHidePanel),
    		onLoadSuccess : (typeof(param.onLoadSuccess) == "undefined"? function(){}:param.onLoadSuccess)
		});
	},

	//【必传】控件名称
	//id: 'icd9',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '000000',
	//【可选参数】1：回调,回调方法setIcd9()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	icd9 : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    value: (typeof(param.value) == "undefined"?"":param.value),
	        idField:'icdId',
	        panelWidth: 400,
	        panelHeight: 300,
	        textField:'operaCnname',
			url: Csm.url.opeQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size) + (typeof(param.value) == 'undefined' ? '' : ('&defValue=' + param.value)),
	        columns:[
	        	[
	             {field:'icdId',title:'手术编号',sortable:true,width:100},  
	             {field:'operaCnname',title:'手术名称',sortable:true,width:275},
	            ]
	        ],
			onSelect: function(){
				var doctorObj = new Object();
				doctorObj.key = $('#'+param.id).combobox('getValue');
				doctorObj.value = $('#'+param.id).combobox('getText');
				if(param.callback == 1){
	    			setIcd9(doctorObj);
				}
			}
		});
	},

	//【必传】控件名称
	//id: 'drug',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//【可选参数】1：回调,回调方法setDrug()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	drug : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    value: (typeof(param.value) == "undefined"?"":param.value),
	        idField:'drugId',
	        panelWidth: 400,
	        panelHeight: 300,
	        textField:'drugName',
			url: Csm.url.drugQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
	        columns:[
	        	[
	             {field:'drugId',title:'药品编号',sortable:true,width:100},  
	             {field:'drugName',title:'药品名称',sortable:true,width:275},
	            ]
	        ],
			onSelect: function(){
				var doctorObj = new Object();
				doctorObj.key = $('#'+param.id).combobox('getValue');
				doctorObj.value = $('#'+param.id).combobox('getText');
				if(param.callback == 1){
	    			setDrug(doctorObj);
				}
			}
		});
	},
	

	//【必传】控件名称
	//id: 'jszd',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//【可选参数】1：回调,回调方法setDrug()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	jszd : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:'bactGenusId',
	        panelWidth: 300,
	        panelHeight: 300,
	        textField:'bactGenusIdName',
			url: Csm.url.jszdQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
	        columns:[
	        	[
	             {field:'bactGenusId',title:'菌属编号',sortable:true,width:60},  
	             {field:'bactGenusIdName',title:'菌属名称',sortable:true,width:225}
	            ]
	        ],
			onSelect: function(){
				var doctorObj = new Object();
				doctorObj.key = $('#'+param.id).combobox('getValue');
				doctorObj.value = $('#'+param.id).combobox('getText');
				if(param.callback == 1){
	    			setDoctor(doctorObj);
				}
			},
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow)
		});
	},
	
	//【必传】控件名称
	//id: 'byt',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//【可选参数】1：回调,回调方法setDrug()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	byt : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:'pathogenId',
	        panelWidth: 200,
	        panelHeight: 230,
	        textField:'pathogenName',
			url: Csm.url.bytQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size)+'&isByt='+(typeof(param.isByt) == "undefined"?"0":param.isByt),
	        columns:[
	        	[
	             {field:'pathogenId',title:'编号',sortable:true,width:60},  
	             {field:'pathogenName',title:'名称',sortable:true,width:100}
	            ]
	        ],
			onSelect: function(){
				var doctorObj = new Object();
				doctorObj.key = $('#'+param.id).combobox('getValue');
				doctorObj.value = $('#'+param.id).combobox('getText');
				if(param.callback == 1){
	    			setDoctor(doctorObj);
				}
			},
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow)
		});
	},
	
	
	//【必传】控件名称
	//id: 'kjyw',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//【可选参数】1：回调,回调方法setDrug()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	kjyw : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:'drugId',
	        panelWidth: 250,
	        panelHeight: 300,
	        textField:'drugName',
			url: Csm.url.kjywQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
	        columns:[
	        	[
	             {field:'drugId',title:'编号',sortable:true,width:60},  
	             {field:'drugName',title:'名称',sortable:true,width:150}
	            ]
	        ],
			onSelect: function(){
				var doctorObj = new Object();
				doctorObj.key = $('#'+param.id).combobox('getValue');
				doctorObj.value = $('#'+param.id).combobox('getText');
				if(param.callback == 1){
	    			setDoctor(doctorObj);
				}
			},
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow),
			onLoadSuccess : (typeof(param.onLoadSuccess) == "undefined"?function(){}:param.onLoadSuccess)
		});
	},
	//【必传】控件名称
	//id: 'glsfl',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//【可选参数】1：回调,回调方法setDrug()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	glsfl : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        panelWidth: 200,
	        panelHeight: 300,
	        fitColumns: true,
	        idField:'ITEMID',
	        textField:'ITEMNAME',
			url: Csm.url.glsflQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
	        columns:[
	        	[
	             {field:'ITEMID',title:'编号',sortable:true,width:60},  
	             {field:'ITEMNAME',title:'名称',sortable:true,width:100}
	            ]
	        ],
	        onSelect: function(){
				var doctorObj = new Object();
				doctorObj.key = $('#'+param.id).combobox('getValue');
				doctorObj.value = $('#'+param.id).combobox('getText');
				if(param.callback == 1){
	    			setDoctor(doctorObj);
				}
			},
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow)
		});
	},
	
	//【必传】控件名称
	//id: 'kjywfl',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//【可选参数】1：回调,回调方法setDrug()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	kjywfl : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:'drugTypeId',
	        panelWidth: 300,
	        panelHeight: 300,
	        textField:'drugTypeName',
			url: Csm.url.kjywflQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
	        columns:[
	        	[
	             {field:'drugTypeId',title:'编号',sortable:true,width:80},  
	             {field:'drugTypeName',title:'名称',sortable:true,width:200},
	            ]
	        ],
			onSelect: function(){
				var doctorObj = new Object();
				doctorObj.key = $('#'+param.id).combobox('getValue');
				doctorObj.value = $('#'+param.id).combobox('getText');
			},
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow),
			onLoadSuccess : (typeof(param.onLoadSuccess) == "undefined"?function(){}:param.onLoadSuccess)
		});
	},
	
	
	//【必传】控件名称
	//id: 'zgKjyw',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//【可选参数】1：回调,回调方法setDrug()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	zgKjyw : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:'drugId',
	        panelWidth: 300,
	        panelHeight: 300,
	        textField:'drugName',
			url: Csm.url.zgKjywQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
	        columns:[
	        	[
	             {field:'drugId',title:'编号',sortable:true,width:60},  
	             {field:'drugName',title:'名称',sortable:true,width:100},
	             {field:'drugEnname',title:'英文名称',sortable:true,width:100}
	            ]
	        ],
			onSelect: function(){
				var doctorObj = new Object();
				doctorObj.key = $('#'+param.id).combobox('getValue');
				doctorObj.value = $('#'+param.id).combobox('getText');
				if(param.callback == 1){
	    			setDoctor(doctorObj);
				}
			},
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow)
		});
	},
	
	//【必传】控件名称
	//id: 'bbDict',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//【可选参数】1：回调,回调方法setDrug()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	bbDict : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:'bbid',
	        panelWidth: 200,
	        panelHeight: 220,
	        textField:'bbmc',
			url: Csm.url.bbDictQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
	        columns:[
	        	[
	             {field:'bbid',title:'编号',sortable:true,width:60},  
	             {field:'bbmc',title:'名称',sortable:true,width:100}	      
	            ]
	        ],
	        onSelect : (typeof(param.onSelect) == "undefined"? function(){}:param.onSelect),
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow)
		});
	},
	
	//【必传】控件名称
	//id: 'standard',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//【可选参数】1：回调,回调方法setDrug()，0：不回调，不传默认回调
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	standard : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: 'remote',
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:'id',
	        panelWidth: 200,
	        panelHeight: 220,
	        textField:'name',
			url: Csm.url.standardQuery+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size),
	        columns:[
	        	[
	             {field:'id',title:'编号',sortable:true,width:60},  
	             {field:'name',title:'名称',sortable:true,width:100}	      
	            ]
	        ],
	        onSelect : (typeof(param.onSelect) == "undefined"? function(){}:param.onSelect),
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow)
		});
	},
	
	//【必传】控件名称
	//id: 'sysDic',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	sysDic : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: (typeof(param.mode) == "undefined"?"remote":param.mode),
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:'dictCode',
	        panelWidth: 260,
	        panelHeight: 300,
	        textField:'dictName',
			url: Csm.url.sysDicQueryList+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size)+'&dictTypeCode='+param.dictTypeCode,
	        columns:[
	        	[
	             {field:'dictCode',title:'编号',sortable:true,width:40},  
	             {field:'dictName',title:'名称',sortable:true,width:200}
	            ]
	        ],
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow)
		});
	},
	
	//【必传】控件名称
	//id: 'sysDicName',
	//【可选参数】下拉列表的默认value，不传则没有默认值；
	//value: '00000',
	//callback: '1',
	//【可选参数】显示下拉列表行数，不传默认200
	//size: '200'
	sysDicName : function(param) {
		$('#' + param.id).combogrid({
			delay: 1000,    
		    mode: (typeof(param.mode) == "undefined"?"remote":param.mode),
		    loadMsg : '正在查询中...',
		    value: (typeof(param.value) == "undefined"?"":param.value),
		    required:(typeof(param.required) == "undefined"?false:param.required),
	        idField:'dictName',
	        panelWidth: 250,
	        panelHeight: 150,
	        textField:'dictName',
			url: Csm.url.sysDicQueryList+'?page=1&size='+(typeof(param.size) == "undefined"?"200":param.size)+'&dictTypeCode='+param.dictTypeCode,
	        columns:[
	        	[
	             {field:'dictName',title:'名称',sortable:true,width:245}
	            ]
	        ],
			onClickRow : (typeof(param.onClickRow) == "undefined"?function(){}:param.onClickRow)
		});
	}
};

//简化的select2 使用必须先引入select2.js
$.fn.select2Remote = function(options) {
	var opts = $.extend({},$.fn.select2Remote.defaults, options);
	this.select2({
		allowClear: true,
		multiple: opts.multiple,
		separator: opts.separator,
		placeholder: opts.placeholder,
		minimumInputLength: opts.minLength,
		id: function(obj){return obj[opts.valueField]; },
		ajax: {
			url: opts.url,
			dataType: 'json',
			type: 'post',
			quietMillis: opts.delay,
			data: function (term, page) {
				var _data = {name: term};
				opts.inputVal = term;
				$.each(opts.data, function(key, value) {
					if(opts.dataType === 'value') {
						_data[key] = value;
					} else {
						_data[key] = $('#' + value).val();
					}
				});
				return _data;
			},
			results: function (data, page) {
				if(opts.idAddInput === 1) {
					var _inputData = {};
					_inputData[opts.valueField] = opts.inputVal;
					_inputData[opts.textField] = '('+opts.inputVal+')';
					data.unshift(_inputData);
				}
				return {results: data};
			},
			cache: true
		},
		initSelection: function(element, callback) {
			var id = $(element).val();
			if (id!='') {
				var _initData = {};
				$.each(opts.initData, function(key, value) {
					if(opts.dataType === 'value') {
						_initData[key] = value;
					} else {
						_initData[key] = $('#' + value).val();
					}
				});
				_initData['id'] = id;
				$.ajax(opts.initUrl, {
					data: _initData,
					dataType: 'json',
					type: 'post'
				}).done(function(data) { 
					callback(data);
				});
			}
		},
		formatResult: function(obj){return obj[opts.textField];},
		formatSelection:function(obj){return obj[opts.textField];},
		dropdownCssClass: "bigdrop",
		escapeMarkup: function (m) { return m; }
	});
};
$.fn.select2Remote.defaults = {
		placeholder: "请输入",
		minLength: 1,
		url:'',
		initUrl:'',
		//不支持多选
		multiple: false,
		//分隔符
		separator: ',',
		//为data的类型id代表控件ID名称value代表指定的值
		dataType: 'id',
		//加载数据的参数，这里放参数名和控件的ID[比如：{传入后台参数:传入后台参数的值的ID名称}]
		data: {},
		//初始化数据的参数，这里放参数名和控件的ID[比如：{传入后台参数:传入后台参数的值的ID名称}]
		initData: {},
		delay:1000,
		//value名
		valueField:'key',
		//显示的text
		textField:'value'
};

//Date的操作方法
Csm.date = {
		//获取当前时间
		getDate : function() { return new Date(); },
		//获取时间戳 [date: 代表传入的日期]
		getTime : function(date) { if(date === undefined) { return Csm.date.getDate().valueOf(); } return date.valueOf(); },
		//根据指定格式获取日期[默认格式为: yyyy-MM-dd HH:mm:ss] [date  : 日期 format: 日期格式]
		formatStr : function(date, format) {
			if(date===undefined || date===null || date==='') return '';
			if(typeof(date)==='number') date = new Date(date);
			else if(typeof(date)==='string') return date;
			else if(date.time!=undefined) date = new Date(date.time);
			if(format === undefined) { format = "yyyy-MM-dd HH:mm:ss"; }
			var z = { y:date.getFullYear(), M:date.getMonth() + 1, d:date.getDate(), H:date.getHours(), m:date.getMinutes(), s:date.getSeconds() };
			return format.replace(/(y+|M+|d+|H+|m+|s+)/g, function(v) {
				return ((v.length > 1 ? "0" : "") + eval('z.'+v.slice(-1))).slice(-(v.length>2?v.length:2));
			});
		},
		//将字符串时间转为Date [dateStr : 字符串时间 format  : 字符串格式[目前支持(yyyy-MM-dd HH:mm:ss)] ]
		formatDate : function(dateStr, format) {
			if(dateStr===undefined || dateStr===null || dateStr==='') return '';
			dateStr = Date.parse(dateStr.replace(/-/g, "/"));
			var _date = new Date(dateStr);
			return _date;
		},
		//比较时间大小[-1: date1 < date2 / 0: date1 = date2 / 1: date1 > date2]
		compareDate : function(date1, date2) {
			var _datetime1 = date1.getTime();
			var _datetime2 = date2.getTime();
			if(_datetime1 < _datetime2) { return -1; }
			else if(_datetime1 === _datetime2) { return 0; }
			else if(_datetime1 > _datetime2) { return 1; }
		},
		//获取指定时间加上指定的月数 [date:日期 month:加上的月数]
		getDateAddMonth : function(date, month) {
			if(date === undefined || date === '') { date = Csm.date.getDate(); }
			if(month === undefined || month === '') { month = 0; }
			date.setMonth(date.getMonth() + month);
			return date;
		},
		//获取指定时间加上指定的天数 [_date:日期 _day:加上的天]
		getDateAddDay : function(_date, _day) {
			if(_date === undefined || _date === '') { _date = Csm.date.getDate(); }
			if(_day === undefined || _day === '') { _day = 0; }
			_date.setDate(_date.getDate() + _day);
			return _date;
		},
		//获取指定时间加上指定的小时数 [_date:日期 _hour:加上的小时]
		getDateAddHour : function(_date, _hour) {
			if(_date === undefined || _date === '') { _date = Csm.date.getDate(); }
			if(_hour === undefined || _hour === '') { _hour = 0; }
			_date.setHours(_date.getHours() + _hour);
			return _date;
		},
		//获取指定时间加上指定的分钟数 [_date:日期 _min:加上的分钟]
		getDateAddMin : function(_date, _min) {
			if(_date === undefined || _date === '') { _date = Csm.date.getDate(); }
			if(_min === undefined || _min === '') { _min = 0; }
			_date.setMinutes(_date.getMinutes() + _min);
			return _date;
		}
};

//给Array添加remove方法
Array.prototype.remove=function(obj) {
	if(obj===undefined || obj===null) return false;
	for(var i = 0, n = 0; i < this.length; i++) {
		if(this[i]!=obj) {
			this[n++]=this[i];
		}
	}
	this.length-=1;
};