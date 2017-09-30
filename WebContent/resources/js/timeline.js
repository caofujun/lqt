/**
 * 
 * 患者时间线插件
 * 健康档案(01)、门诊记录(02)、住院记录(03)、随访记录(04)、预约记录(05)、短信记录(06)
 * @Date 2014-11-15
 * @version v1.0.0
 */
(function($,undefined){
	/**
	 * @param url	string
	 * @param param {}
	 * @returns tab string
	 */
	$.fn.timeLinetab = function (options){
		var tab = '';
		var obj = $(this);
		$.post(options.url,options.params,function(data){
			var cur = true;
			$.each(data,function (i,d) {
				if(cur){
					tab += '<a href="javascript:void(0);" class="cur" data-code = '+d.dictCode+'>'+d.dictName+'</a>';
					cur = false;
				}else{
					tab += '<a href="javascript:void(0);" data-code = '+d.dictCode+'>'+d.dictName+'</a>';
				}
				options.initBaseData(d.dictCode,d.dictName);
			});
			if(options.addMethod != undefined && options.addMethod != ''){
				tab += '<button onclick='+options.addMethod+' style=\"float: right;margin-right: 20px;\">新增记录</button>';
			}
			obj.empty();
			obj.append(tab);
			options.initBind();
		},'json');
	};
	
	$.extend({
		/**
		 * @decribe 获取各时间线类型数据
		 * @param data 数据 如{name：'小李', age：'3'}
		 * @param model 数据模型对应后台数据熟悉名称['name','age']
		 * @param type 门诊记录(02)、住院记录(03)、随访记录(04)、预约记录(05)、短信记录(06)
		 * @param baseData 02=门诊,03=住院记录...
		 * @param methods 显示修改和查询详情函数名称['modify()','detail()']
		 * @return string
		 */
		getTimeLineRender: function (data,model,type,baseData,methods){
			var renderText  = '<li class=\"sf_timeline_content\"><div class=\"sf_timeline_icon\"></div><div class="arrow_left"></div><div class=\"sf_timeline_cont\">';
			if(methods && methods.length > 0){
					renderText += '<div class="cont_title"><span class="cont_title_text">'+eval('data.'+model[0])+'&nbsp;&nbsp;&nbsp;'+baseData[type]+'</span><div style=\"float: right;\"><a href=\'javascript:void(0);\' onclick=\"'+methods[0]+'\">修改</a>&nbsp;</div></div>';
				}else{
					renderText += '<div class="cont_title">'+eval('data.'+model[0])+'&nbsp;&nbsp;&nbsp;'+baseData[type]+'</div>';
				}
				renderText += '<table class="sf_infor sf_infor_xz" cellspacing="0" cellpadding="0">';
			if (type === '02'){
				renderText += '<tr><th width="70">就诊科室：</th><td width="360">'+eval('data.'+model[1])+'</td><th width="70">就诊医生：</th><td>'+eval('data.'+model[2])+(Comm.util.isNotEmpty(eval('data.'+model[3]))?'（'+eval('data.'+model[3])+'）':'')+'</td></tr>';
				renderText += '<tr><th>门诊诊断：</th><td>'+eval('data.'+model[4])+'<span class="blue">'+eval('data.'+model[5])+'</span></td><th></th><td></td></tr>';
				//renderText += '<tr ><th>门诊情况：</th><td colspan="3">'+eval('data.'+model[6])+'</td></tr>';
				//renderText += '<tr><th>治疗计划：</th><td colspan="3">'+eval('data.'+model[7])+'</td></tr>';
				renderText += '<tr><th>检验检查：</th><td colspan="3"><ol class="level_column">'+eval('data.'+model[8])+'</ol></td></tr>';
			}else if (type === '03'){
				renderText += '<tr><th width="70">入院科室：</th><td width="360">'+eval('data.'+model[1])+'</td><th width="70">出院科室：</th><td>'+eval('data.'+model[2])+'</td></tr>';
				renderText += '<tr><th>入院时间：</th><td>'+eval('data.'+model[3])+'</td><th>住院天数：</th><td>';
				if (eval('data.'+model[3])) {
					var da = eval('data.'+model[4]);
					if(da){
						renderText += da+'天（已出院 '+eval('data.'+model[5])+'天）</td></tr>';
					}else{
						renderText += '未出院</td></tr>';
					}
				} else {
					renderText += '（已出院 '+eval('data.'+model[5])+'天）</td></tr>';
				}
				renderText += '<tr><th>出院诊断：</th><td>'+eval('data.'+model[6])+'</td><th>出院转归：</th><td>'+eval('data.'+model[7])+'</td></tr>';
				renderText += '<tr><th>检验检查：</th><td colspan="3"><ol class="level_column">'+eval('data.'+model[8])+'</ol></td></tr>';
				renderText += '<tr><th>住院小结：</th><td colspan="3">'+eval('data.'+model[9])+'</td></tr>';
				renderText += '<tr><th>出院医嘱：</th><td colspan="3">'+eval('data.'+model[10])+'</td></tr>';
				renderText += '<tr><th>出院方式：</th><td colspan="3">'+eval('data.'+model[11])+'</td></tr>';
			}else if (type === '04'){
				renderText += '<tr><th width="70">随访日期：</th><td width="360">'+eval('data.'+model[1])+'</td><th width="70">随访人：</th><td>'+eval('data.'+model[2])+'</td></tr>';
				renderText += '<tr><th>随访结果：</th><td><span class="green" style="margin-left:0px;">'+eval('data.'+model[3])+'</span></td><th></th><td></td></tr>';
				renderText += '<tr><th>随访小结：</th><td colspan="3">'+eval('data.'+model[5])+'</td></tr>';
				renderText += '<tr><th>随访工作：</th><td colspan="3">'+eval('data.'+model[6])+'</td></tr>';
			}else if (type === '05'){
				renderText += '<tr><th width="70">预约科室：</th><td width="360">'+eval('data.'+model[1])+'</td><th width="70">预约医生：</th><td>'+eval('data.'+model[2])+(Comm.util.isNotEmpty(eval('data.'+model[3]))?'（'+eval('data.'+model[3])+'）':'')+'</td></tr>';
				renderText += '<tr><th>预约时段：</th><td>'+eval('data.'+model[4])+'</td><th>预约费用：</th><td>'+eval('data.'+model[5])+'元</td></tr>';
				renderText += '<tr><th>职　　务：</th><td>'+eval('data.'+model[6])+'</td><th>预约级别：</th><td>'+eval('data.'+model[7])+'</td></tr>';
			}else if (type === '06'){
				renderText += '<tr><th width="70">接收号码：</th><td width="360">'+eval('data.'+model[1])+'（本人）</td><th width="70">发送时间：</th><td>'+eval('data.'+model[2])+'</td></tr>';
				renderText += '<tr><th>短信内容：</th><td colspan="3">'+eval('data.'+model[3])+'</td></tr>';
			}
			renderText +='</table></div><div class=\"clear\"></div></li>';
			return renderText;
		},
		/**
		 * @decribe 获取时间线标题
		 * @param year 年 case: 2014
		 * @return string
		 */
		getTimeLineTitle: function (year){
			return '<li class=\"sf_timeline_title\"><div class=\"title_icon\"></div><div class=\"sf_timeline_cont\">'+year+'年</div><div class=\"clear\"></div></li>';
		}
		
	}); 
})(jQuery);