var wjdc = {
		//加载问卷{attr.bizFn成功后，简单的修改属性的业务/attr.resultFn处理拼装html的函数/surveyId问卷调查答案ID}
		loadQues : function(panel, qid, num, attr) {
			$.ajax({
                url: webroot + '/qsTopic/f_json/findByQid.shtml',
                type: 'post',
                data: { 'qid': qid, 'rid': attr.rid },
                dataType: 'json',
                success : function(data) {
        			if(attr===undefined) attr = {};
                	var _qPanel = $('#'+panel).empty();
                	if(attr.bizFn != undefined) eval(attr.bizFn);
					if(data.length > 0) {
						$.each(data, function(i, obj) {
							_qPanel.append(attr.resultFn(i, obj, data, num));
						});
						if(attr.successFn != undefined) attr.successFn(num);
					} else {
						if(attr.dataNullFn != undefined) attr.dataNullFn();
					}
				}
			});
		},
		//生成问卷题目选项样式[type: repo展示/survey做问卷调查题目]
		createOpt : function(wj,num) {
			index =wj.tid;
			var _cont = [];
			var _name = 'result';
			var _tktValid = '';
			var _rdoValid = '';
			
			//调查
			if(wj.allowNull==='1') {
				_tktValid = ' class="easyui-validatebox" required="true"';
				_rdoValid = ' class="easyui-validatebox" validType="radio[\'surveyRecordList['+num+'].'+_name+'OptId'+wj.tid+'\']"';
			}

			_cont.push('<input type="hidden" name="surveyRecordList['+num+'].'+_name+'Tid" value="',wj.tid,'">');
			if(wj.ttype==='3') {
				//填空题
				var _value = '';
				if(wj.answers && wj.answers.length>0) _value = wj.answers[0].inputValue;
				_cont.push('<textarea name="surveyRecordList['+num+'].',_name,'Tkt',wj.tid,'"',_tktValid,' style="width:80%;height:50px">',_value,'</textarea>');
				//设置选项ID[填空题没有选项]
				//if(type!='repo') _cont.push('<input type="hidden" name="'+_name+'.optId" value="',wj.optId,'">');
			} else {
				var _mod = 4;
				var _options = wj.options;
				$.each(_options, function(i, obj) {
					var _value = '';
					var _inputValue = '';
					if(wj.answers && wj.answers.length>0) {
						$.each(wj.answers, function(i, objVal) {
							if(obj.optId==objVal.optId) {
								_value = 'checked';
								_inputValue = objVal.inputValue;
							}
						});
					}
					/*if(i%_mod === 0) {
						if(i > 0) {
							_cont.push('<div class="clear"></div></ul>');
						}
						_cont.push('<ul class="ulradiocheck">');
					}
					_cont.push('<li>');*/
					
					if(wj.ttype==='1') {
						//单选 值为 题目ID,选项ID						
						if(obj.allowInput == 1) {
							_cont.push('<span class="Options"><input type="radio" name="surveyRecordList['+num+'].',_name,'OptId',wj.tid,'" ',_value,_rdoValid,' id="',obj.optId,'" value="',wj.tid,'#@#',obj.optId,'"><label for="',obj.optId,'" style="width:auto;">',obj.optName,'&nbsp;</label><input type="text" class="text" name="',_name,'OptIdInput',obj.optId,'" value="',_inputValue,'" style="margin-left:100px;"/></span>');
						}
						else{
							_cont.push('<span class="Options"><input type="radio" name="surveyRecordList['+num+'].',_name,'OptId',wj.tid,'" ',_value,_rdoValid,' id="',obj.optId,'" value="',wj.tid,'#@#',obj.optId,'"><label for="',obj.optId,'">',obj.optName,'&nbsp;</label></span>');
						}
					} else if(wj.ttype==='2') {
						//多选 值为 题目ID,选项ID						
						if(obj.allowInput == 1) {
							_cont.push('<span class="Options"><input type="checkbox" name="surveyRecordList['+num+'].',_name,'OptId',wj.tid,'" ',_value,_rdoValid,' id="',obj.optId,'" value="',wj.tid,'#@#',obj.optId,'"><label for="',obj.optId,'" style="width:auto;">',obj.optName,'&nbsp;</label><input type="text" class="text" name="',_name,'OptIdInput',obj.optId,'" value="',_inputValue,'" style="margin-left:100px;"/></span>');
						}
						else{
							_cont.push('<span class="Options"><input type="checkbox" name="surveyRecordList['+num+'].',_name,'OptId',wj.tid,'" ',_value,_rdoValid,' id="',obj.optId,'" value="',wj.tid,'#@#',obj.optId,'"><label for="',obj.optId,'">',obj.optName,'&nbsp;</label></span>');
						}
					}
					/*_cont.push('</li>');
					if( (i+1) === _options.length) {
						var _num = _options.length%_mod;
						for(i=0;i<(_mod-_num)&&_num!=0;i++) {
							_cont.push('');
						}
						_cont.push('<div class="clear"></div></ul>');
					}*/
					_bool = false;
				});
			}
			return _cont.join('');
		},
		//清空选择或填写的内容
		clearCont : function(pid) {
			$('#'+pid).find('div input').each(function(i, obj) {
				var _obj = $(obj);
				if(_obj.attr('type')==='radio' || _obj.attr('type')==='checkbox') {
					_obj.attr('checked', false);
				} else if(_obj.attr('type')==='text') {
					_obj.val('');
				}
			});
			$('#'+pid).find('div textarea').each(function(i, obj) {
				$(obj).val('');
			});
			
		}
};