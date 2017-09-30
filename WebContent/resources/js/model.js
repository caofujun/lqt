/**
 * 采集模型
 * v1.0.0
 */
var model = {
	//绑定表单	
	form : '#collectForm',	
	//是否预览
	preview : false,
	//绑定from提交 默认id=collectForm名称
	dmPanel : '#collectPanel',
	//初始化组件
	cols : [],
	//加载采集项 根据formId加载采集项
	/*
	 *@param formId 采集单
	 *@param recordId 记录时间 
	 *@param dgsId 诊断
	 */
	loadMore : function(formId,recordId,dgsId) {
		$.ajax({
	        url: webroot+'/cfFormItem/f_json/getCollect.shtml',
	        type: 'post',
	        data: {'id':formId,'recordId':recordId},
	        dataType: 'json',
	        error: function() { $.messager.show({ title: '提示', msg: '加载采集单出错!' }); },
	        success : function(data) {
	        	model.initModel(data,undefined,dgsId,recordId);
			}
	    });
	},
	initModel : function(data, msg, dgsId,recordId) {
		var _dmForm = $(model.dmPanel);
		if(data != null) {
			$.each(data, function(i, obj) {
				var _cont = ['<div id="KJ_',obj.formId,'" class="dg_panel">'];
				var _suffix = '_'+obj.formId;
				var _cldCont = [];
				_cldCont.push('<ul class="dgc_panel" id="KJ',_suffix,'">');
				_cldCont.push('<li class="dp_title"><span class="dpt_name">',obj.name,'：</span>');
				_cldCont.push('<input type="hidden" name="KJ',_suffix,'_dgsId" value="'+dgsId+'"/>');
				_cldCont.push('<input type="hidden" name="KJ',_suffix,'_formType" value="'+obj.formType+'"/>');
				_cldCont.push('<input type="hidden" name="KJ',_suffix,'_formName" value="'+obj.name+'"/></span>');
				if (!recordId && model.ndate) {
					obj.recordDate = model.ndate;
				}
				if (obj.formType == 'physical' || obj.formType == 'specialist') {
					//_cldCont.push('<span class="dpt_attr"> 检验编号','：<input type="text" class="text" name="KJ',_suffix,'_code" value=""/></span>');
					_cldCont.push('<span class="dpt_attr"> <span class="pro_text">检查日期：</span>','<input type="text" name="KJ',_suffix,'_time" value="'+Comm.Str.fomartStr(obj.recordDate)+'" class="Wdate easyui-validatebox text" onclick="WdatePicker()"/></span>');
				} 
				if (obj.formType == 'lab') {
					_cldCont.push('<span class="dpt_attr"> <span class="pro_text">检查日期：</span>','<input type="text" name="KJ',_suffix,'_time" value="'+Comm.Str.fomartStr(obj.recordDate)+'" class="Wdate easyui-validatebox text" onclick="WdatePicker()"/></span>');
				} 
				_cldCont.push('</li>');
				//判断是否存在模板
				if(obj.eTemplate!=null && obj.eTemplate.length>0) {
					_cldCont.push('<li class="dp_cont"></li>');
					var partUrl = '';
					if (obj.eTemplate.indexOf('.js') > -1) {
						partUrl = obj.eTemplate.substring(0,obj.eTemplate.indexOf('.js')+3);
					} else {
						partUrl = obj.eTemplate;
					}
					jsLoader(webroot+'/resources/js/tpl/'+partUrl, function() {
						var _template  = '';
						if (obj.eTemplate.indexOf('?') > -1){
							_template = getQueryString(obj.eTemplate,'m');
						}
						_template = eval(_template);
						$('.dp_cont').append(_template);
						//处理指标输入框
						$.each(obj.items, function(j, colsObj) {
							dealCols(_suffix, colsObj,recordId);
						});
						model.dealCols();
					});
				} else {
					_cldCont.push('<li class="dp_cont">',
	      				'<table class="dg_table" rules="rows">',
			      			'<tr>',
			      				'<th width="100">项目名</th>',
			      				(obj.formType == 'lab') ? '<th>检验结果</th>' : '<th>检查结果</th>',
			      				(obj.formType == 'lab') ? '<th width="50">单位</th><th width="60">参考范围</th>':'',
			      			'</tr>');
					$.each(obj.items, function(j, colsObj) {
						dealCols(_suffix, colsObj,recordId);

						var _id = 'KJ'+_suffix+'_'+colsObj.itemCode;
						_cldCont.push('<tr><td class="dgt_title">',colsObj.itemName,'</td>');
						_cldCont.push('<td><div id="',_id,'"></div></td>');
						if (obj.formType == 'lab') {
							 _cldCont.push('<td>',colsObj.unit,'</td>');
							 _cldCont.push('<td>',colsObj.minValue,'~',colsObj.maxValue,'</td></tr>');
						}
					});
					_cldCont.push('</table></li>');
				}
				_cldCont.push('</ul>');
				_cont.push(_cldCont.join(''));
				if (!model.preview) {
					_cont.push('<div class=\'dp_operate\'><input type="button" style="\margin-left: 20px;\"');
					_cont.push('class="btn_save" id=\"collectFormSubmitBtn\"');
					_cont.push('onclick="$(\''+model.form+'\').submit()" value="保存"></div>');
				}
				_cont.push('</div>');
				_dmForm.append(_cont.join(''));
			});
			model.dealCols();
			$.parser.parse(model.dmPanel);
		} else if(msg) {
			_dmForm.append(msg);
		}
		//处理指标
		function dealCols(_suffix, colsObj,recordId) {
			var _id = 'KJ'+_suffix+'_'+colsObj.itemCode;
			colsObj['panelid'] = _id;
			colsObj['suffix'] = _suffix;
			colsObj['isValue'] = 0;
			//设置值
			if(typeof recordId != 'undefined' && recordId) {
				colsObj['isValue'] = 1;
			}
			
			//添加到需要加载指标的集合中
			var _co = {};
			for(var key in colsObj) {
				_co[key] = colsObj[key];
			}
			model.cols.push(_co);
		}
	},
	//处理采集单控件组装
	dealCols : function() {
		$.each(model.cols, function(i, obj) {
			model.createCols(i, obj);
		});
		model.cols = [];
		//给控件绑定enter事件进行提交 onclick="edi.clickSaveKJ()"
		this.initDm();
	},
	/*
	 *渲染采集单
	 *@param obj 数据
	 *@param v 结果 
	 *@return 渲染数据
	 */
	createCols : function (i,obj) {
		//TODO 目前不需要校验
		obj.required = 0;
		
		//alert(obj.panelid + '||' + obj.suffix);
		var _panel = $('#'+obj.panelid);
		var _selname = 'KJ'+obj.suffix+'_sel_'+obj.itemCode+'_'+obj.id;
		var _inpname = 'KJ'+obj.suffix+'_inp_'+obj.itemCode+'_'+obj.id;
		var _value1 = '';
		var _value2 = '';
		//有值
		if(obj.isValue===1) {
			_value1 = obj.selectValue;
			_value2 = obj.inputValue;
			if (typeof (_value1) === 'undefined' || _value1 == null) _value1 = '';
			if (typeof (_value2) === 'undefined' || _value2 == null) _value2 = '';
		}
		//输入类型(inputtype)：1：选择，2：输入，3：选择+输入
		if(obj.inputType===1 || obj.inputType===3) {
			var _cont = [];
			var _item = [];
			if (Comm.util.isNotEmpty(obj.itemList) && obj.itemList.indexOf(',') > -1) {
				_item = obj.itemList.split(',');
			} else if (Comm.util.isNotEmpty(obj.itemList) && obj.itemList.indexOf(',') == -1) {
				_item.push(obj.itemList);
			}
			var _validate = (obj.required===1?' class="easyui-validatebox" required="true"':'');
        	//选择类型： 1：单选（下拉），2：单选（radio），4：多选（checkbox），
		    if(obj.selectType===1) {
    			_cont.push('<select name="',_selname,'"',_validate,'><option value="">--请选择--</option>');
				$.each(_item, function(i, valObj) {
					//如果值为空，选择默认值
					if(_value1 == '' && valObj != '' && obj.defaultSelect != null){
						_cont.push('<option value="',valObj,'" ',(obj.defaultSelect==valObj?'selected="selected"':''),'>',valObj,'</option>');
						return true;
					}
					if(valObj!=''){
						_cont.push('<option value="',valObj,'" ',(_value1==valObj?'selected="selected"':''),'>',valObj,'</option>');
					}
				});
    			_cont.push('</select> ');
		    } else if(obj.selectType===2) {
    			var _br = (obj.islinefeed==='1'?'<br/>':'');
    			$.each(_item, function(i, valObj) {
    				//如果值为空，选择默认值
					if(_value1 == '' && valObj != '' && obj.defaultSelect != null){
						_cont.push((i>0?_br:''),'<label><input type="radio" name="',_selname,'"',_validate,' ondblclick="Comm.cancelRadio(this); model.checkListner(\'',_selname,'\',this);" value="',valObj,'" ',(obj.defaultSelect==valObj?'checked="checked"':''),'/> ',valObj,'</label> ');
						return true;
					}
    				if(valObj!='') _cont.push((i>0?_br:''),'<label><input type="radio" name="',_selname,'"',_validate,' ondblclick="Comm.cancelRadio(this); model.checkListner(\'',_selname,'\',this);" value="',valObj,'" ',(_value1==valObj?'checked="checked"':''),'/> ',valObj,'</label> ');
        		});
    		} else if(obj.selectType===4) {
    			var _br = (obj.islinefeed==='1'?'<br/>':'');
    			$.each(_item, function(j, valObj) {
    				var _checked = '';
    				if(_value1 == '' && valObj != '' && obj.defaultSelect != null ){
    					$.each(obj.defaultSelect.split(','), function(k, val1Obj) {
        					if(val1Obj==valObj) {
        						_checked = 'checked="checked"';
        						return false;
        					}
        				});
    					_cont.push((i>0?_br:''),'<label><input type="checkbox" onclick="model.checkListner(\'',_selname,'\',this);" name="',_selname,'"',_validate,' value="',valObj,'" ',_checked,'/> ',valObj,'</label> ');
    					return true;
					}
    				$.each(_value1.split(','), function(k, val1Obj) {
    					if(val1Obj==valObj) {
    						_checked = 'checked="checked"';
    						return false;
    					}
    				});
    				if(valObj!='') _cont.push((i>0?_br:''),'<label><input type="checkbox" onclick="model.checkListner(\'',_selname,'\',this);" name="',_selname,'"',_validate,' value="',valObj,'" ',_checked,'/> ',valObj,'</label> ');
        		});
    		}
    		_panel.html(_cont.join('')+_panel.html());
			if(obj.index!=-1) $.parser.parse('#'+obj.panelid);
		}
		//为输入或选择+输入
		if(obj.inputType===2 || obj.inputType===3) {
			var _cont = [];
			var w = obj.inputWidth > 0 ? 'style=\'width:'+obj.inputWidth+'px;\'' :'';
			//字段数据类型：科学数字型
			if(obj.dataType==='dt_sd') {
				var _power = model.parsePower(_value2);
				var _typeValidate = (obj.required===1?' class="easyui-validatebox w_50" required="true"':'class="w_50"');
				_cont.push('<input type="text" validType="knumber" class="text" id="',_inpname,'_p1" onblur="model.power(\'',_inpname,'_p1\', \'',_inpname,'_p2\')"',_typeValidate,w,' value="',_power[0],'"/> 10^ <input type="text" class="text" id="',_inpname,'_p2" onblur="model.power(\'',_inpname,'_p1\', \'',_inpname,'_p2\')"',_typeValidate,w,' value="',_power[1],'"/>');
				_cont.push('<input type="hidden" id="',_inpname,'" name="',_inpname,'"',_typeValidate,' value="',_value2,'"/>');
			}
			else if(obj.dataType==='dt_number') {
				var _validate = (obj.required===1?' required="true"':'');
				_cont.push('<input type="text" class="easyui-validatebox text" name="',_inpname,'"',_validate,w,' value="',_value2,'" validType="knumber"/>');
			}
			//字段数据类型：日期
			else if(obj.dataType==='dt_date') {
				var _validate = (obj.required===1?' required="true"':'');
				_cont.push('<input type="text" class="Wdate easyui-validatebox text" name="',_inpname,'"',_validate,w,' value="',Comm.date.formatStr(_value2, 'yyyy-MM-dd'),'" onclick="WdatePicker()"/>');
			}
			//字段类型为：长文本
			else if(obj.dataType==='dt_textarea') {
				var _validate = (obj.required===1?' class="easyui-validatebox" required="true"':'');
				_cont.push('<textarea name="',_inpname,'"',_validate,' cols="30" rows="3">',_value2,'</textarea>');
			}
			//普通文本
			else {
				var _size = obj.maxlength&&obj.maxlength>0&&obj.maxlength!=0?'size="'+obj.maxlength+'"':'';
				var _validate = (obj.required===1?' class="easyui-validatebox" required="true"':'');
				_cont.push('<input type="text" class="text"  ',_size,' name="',_inpname,'"',_validate,w,' value="',_value2,'"/>');
			}
			_panel.append(_cont.join(''));
			if(obj.index!=-1) $.parser.parse('#'+obj.panelid);
		}
	},
	//科学数字型计算
	power : function(_id1, _id2) {
		var _num1 = $('#'+_id1).val();
		if(_num1==='') return;
		var _num2 = $('#'+_id2).val();
		if(_num2==='') return;
		var _num = model.accMul(_num1, Math.pow(10, _num2));
		$('#'+_id1.substring(0, _id1.length - 3)).val(_num);
	},
	//精确浮点乘法运算
	accMul : function (arg1,arg2){
	  var m=0,s1=arg1.toString(),s2=arg2.toString();
	  try{m+=s1.split(".")[1].length;}catch(e){}
	  try{m+=s2.split(".")[1].length;}catch(e){}
	  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
	},
	//解析科学数字型
	parsePower : function(num, id1, id2) {
		if(num===undefined || num==='') return ['', ''];
		if(!Comm.util.isNumber(num)) return ['', ''];
		if(num<=0) return ['', ''];
		var _index = 0;
		while(true) {
			if( (num/10+'').indexOf('.')===-1) {
				num = num / 10;
				_index ++;
				if(_index > 1000) break;
			} else {
				break;
			}
		}
		if(id1 && id2) {
			$('#'+id1).val(num);
			$('#'+id2).val(_index);
		} else {
			return [num, _index];
		}
	},
	
	//初始化样式、事件等
	initDm : function() {
		//初始化样式
		$('.data-fn').each(function(i, obj) {
			if($(obj).attr('init-mehod')){
				template.obj = obj;
				eval($(obj).attr('init-mehod'));
			} 
		});
		//绑定事件
		setTimeout(function() {
			$('.data-readonly').each(function(i, obj) {
				$(obj).find('input').attr('readonly', true).css({'border':'0px'});
			});
			//隐藏内容
			$('.data-hide').each(function(i, obj) {
				if($(obj).find('input').val()==''){
					$(obj).find('input').hide();
				}
			});
			$('.data-fn').each(function(i, obj) {
				if($(obj).attr('data-fn-type')==='change') {
					$(obj).find('input').change(function() {
						eval($(obj).attr('data-fn'));
					});
					$(obj).find('select').change(function() {
						eval($(obj).attr('data-fn'));
					});
				} else if($(obj).attr('data-fn-type')==='keyup') {
					$(obj).find('input').keyup(function(event) {
						template.obj = obj;
						eval($(obj).attr('data-fn'));
					});
				} else if($(obj).attr('data-fn-type')==='click') {
					$(obj).find('input').click(function() {
						template.obj = obj;
						eval($(obj).attr('data-fn'));
					});
				} else if($(obj).attr('data-fn-type')==='blur') {
					$(obj).find('input').blur(function() {
						template.obj = obj;
						eval($(obj).attr('data-fn'));
					});
				}
			});
		}, 800);
	},
	checkListner : function (name,_this) {
		var _node  = $(_this).parent().parent();
		var _checkedboxs = _node.find('input:checked');
		var id = 'id_'+name;
		if (_checkedboxs.length == 0 && _node.has('#'+id).length == 0) {
			_node.append('<input type=\'hidden\' id=\''+id+'\' name=\''+name+'\' value="" />');
		} else {
			$('#'+id).remove();
		}
	}
};