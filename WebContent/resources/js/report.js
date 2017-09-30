/**
 * 报表js
 */
;(function (window) {
	if (window._report) return;
	var _report = {
		//引入模板
		template :  window.template,
		//是否加载了模板
		isLoadTemplate : function () { return (typeof window.template == 'undefined') ? false : true },
		//渲染
		render : function (html,data) {
			var render = _report.template.compile(html);
			return render(data); 
		},
		//装载已经载入的图表
		loadedChart : [],
		//是否是数组
		isArray : function (obj) {   
			  return Object.prototype.toString.call(obj) === '[object Array]';    
		}, 
		//查询数据渲染数据 options {panelId,url 是必须 data 不是必须,dataIndex 不是必须渲染数据时可能有用} 非图表类
		loadByUrlAndQueryParams : function (options) {
			if (!this.isLoadTemplate()) return;
			var html = $('#'+options.panelId).html();
			$('#'+options.panelId).empty();
			$.ajax({
                url: options.url,
                data : (typeof options.data != 'undefined') ? options.data : {},
                type: 'post',
                dataType: 'json',
                success : function(json) {
                	if (json.result == 'success') {
            			var _data = null;
            			if (typeof options.dataIndex != 'undefined') {
            				var _l = {} ; _l[options.dataIndex] = json.data;
            				_data = _l;
            			}else{
            				_data = json.data;
            			} 
                		$('#'+options.panelId).append(_report.render(html,_data));
                	}else{
                		$('#'+options.panelId).append('<ul><li><span class="mr10">暂无数据</span></li></ul>');
                	}
				}
    		});
		},
		//查询数据渲染数据 options {panelId,url 是必须 data 不是必须} 图表类
		loadByUrlAndQueryParamsToChart : function (options,callback,clearCallback) {
			$('#'+options.panelId).empty();
			$.ajax({
                url: options.url,
                data : (typeof options.data != 'undefined') ? options.data : {},
                type: 'post',
                dataType: 'json',
                success : function(json) {
                	if (json.result == 'success') {
                		if (_report.isArray(json.data) && json.data.length == 0) {
                			clearCallback();
                			$('#'+options.panelId).append('暂无数据');
                		}else{
                			//集合数据,附加数据(可选)
                    		callback(json.data,json.msg);
                		}
                	}else{
                		clearCallback();
                		$('#'+options.panelId).append('暂无数据');
                	}
				}
    		});
		},
		//获取chart
		getChart : function (panelId) {
			for (var i = 0, len = _report.loadedChart.length; i<len; i++) {
				if (_report.loadedChart[i].id==panelId) return _report.loadedChart[i].c;
			}
			return null;
		},
		//填充chart
		addChart : function (panelId,_chart) {
			_report.removeChart(panelId);
			var _d = {id:panelId,c:_chart};
			_report.loadedChart.push(_d);
		},
		//删除chart
		removeChart : function (panelId) {
			for (var i = 0, len = _report.loadedChart.length; i<len; i++) {
				if (_report.loadedChart[i].id==panelId){
					_report.loadedChart.splice(i, 1);
					return;
				}
			}
		}
	};
	//解决echarts自适应问题
	window.onresize = function () {
		for (var i = 0, len = _report.loadedChart.length; i<len; i++) {
			_report.loadedChart[i].c.resize();
		}
	}
	window._report = _report
})(window);