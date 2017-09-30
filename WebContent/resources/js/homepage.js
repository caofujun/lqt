/**
 * 首页样式
 */

var Homepage = {
		//引入模板
		template :  window.template,
		//是否加载了模板
		isLoadTemplate : function () { return (typeof window.template == 'undefined') ? false : true },
		//渲染
		render : function (html,data) { var render = Homepage.template.compile(html);return render(data); },
		//装载已经载入的图表
		loadedChart : [],
		loadedMap: {},
		//是否是数组
		isArray : function (obj) {   
			  return Object.prototype.toString.call(obj) === '[object Array]';    
		},
		//初始化面板
		init : function(panelId, layoutCode, componentCodes, sourceType) {
			this.loadedChart.length = 0;
			$.ajax({
                url: webroot+'/sysHpStyle/f_json/layout.shtml',
                type: 'post',
                data: { layoutCode: layoutCode, componentCodes: componentCodes, sourceType: sourceType },
                dataType: 'json',
                success : function(html) {
					$('#'+panelId).empty().html(html);
					var _updNum = 0;
					//初始化组件拖动效果
					$( ".home_column" ).sortable({
						connectWith: ".home_column",
						handle: ".cap",
						cancel: ".portlet-toggle",
						placeholder: "portlet-placeholder ui-corner-all",
						update: function(data) {
							if(_updNum === 0) {
								shsl.updComponent();
								_updNum ++;
							}
						}
					});
					$( ".portlet-toggle" ).click(function() {
						var icon = $( this );
						icon.toggleClass( "ui-icon-minusthick ui-icon-plusthick" );
						icon.closest( ".portlet" ).find( ".portlet-content" ).toggle();
					});
				}
    		});
		},
		//查询数据渲染数据 options {panelId,url 是必须 data 不是必须,dataIndex 不是必须渲染数据时可能有用} 非图表类
		loadByUrlAndQueryParams : function (options) {
			if (!this.isLoadTemplate()) return;
			var html = options.html;
			$('#'+options.panelId).empty();
			$('#'+options.panelId).mask('加载中..');
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
            			$('#'+options.panelId+'Date').html("("+json.msg+")");
            			$('#'+options.panelId+'DateStr').val("("+json.expandValue+")");
                		$('#'+options.panelId).append(Homepage.render(html,_data));
                	}else{
                		$('#'+options.panelId).append('<ul><li><span class="mr10">暂无数据</span></li></ul>');
                	}
                	$('#'+options.panelId).unmask();
				}
    		});
		},
		//查询数据渲染数据 options {panelId,url 是必须 data 不是必须} 图表类
		loadByUrlAndQueryParamsToChart : function (options,callback) {
			if (!this.isLoadTemplate()) return;
			var myChart = Homepage.loadedMap[options.panelId];
			if (myChart && myChart.dispose) {
				myChart.dispose(); 
				Homepage.loadedMap[options.panelId] = null;
			}
			$('#'+options.panelId).empty();
			$('#'+options.panelId).css('filter', '');
			$('#'+options.panelId).css('background-color', '');
			$('#'+options.panelId).mask('加载中..');
			$.ajax({
                url: options.url,
                data : (typeof options.data != 'undefined') ? options.data : {},
                type: 'post',
                dataType: 'json',
                success : function(json) {
                	if (json.result == 'success') {
                		$('#'+options.panelId+'Date').html("("+json.msg+")");
                		if (Homepage.isArray(json.data) && json.data.length == 0) {
                			$('#'+options.panelId).append('暂无数据');
                		}else{
                			//集合数据,附加数据(可选)
                    		callback(json.data,json.msg);
                		}
                	}else{
                		alert("fff");
                		$('#'+options.panelId).append('暂无数据');
                	}
                	$('#'+options.panelId).unmask();
				}
    		});
		},
		
		//医生登录干预消息
		initInterveneDoctorMsg : function (panelId,userId) {
			var options = {
					panelId : panelId,
					url : webroot+'/nyMessageTheme/f_json/pageQueryHP.shtml',
					data : {userId : userId, page : 1, rows:5, isRead: 0}
			};
			this.loadByUrlAndQueryParams(options);
		},
		
		//侵入性操作相关性感染
		initqrxczxgxgr : function (panelId,dateType,ificu,date,orderType) {
			var options = {
					panelId : panelId,
					url : webroot+'/gm004Jcmx/f_json/qrxczxgxgr.shtml',
					data:{dateType : dateType,ificu:ificu,date:date,orderType:orderType}
			};
			this.loadByUrlAndQueryParamsToChart(options, function (data) {
				var xAxisData = [],seriesData = [],seriesData2 = [],seriesData3 = [];
				for (var i = 0, len = data.length ; i < len; i++) {
					xAxisData[i] = data[i].CREATIONDATE;
					seriesData[i] = data[i].VAP;
					seriesData2[i] = data[i].CAUTI; 
					seriesData3[i] = data[i].CRBSI; 
				}
				var myChart = echarts.init(document.getElementById(panelId));
				var option  = {
					    tooltip : { trigger: 'axis' },
					    calculable : false,
					    xAxis : [{data : xAxisData }],
					    yAxis : [{name:"",type : 'value'}],
					    legend: {
					        left: 'left',
					        data: ['CAUTI', 'VAP','CRBSI']
					    },
					    grid : {x:50,y:30,x2:5,y2:30},
					    series : [ {name:'CRBSI',type:'line',data : seriesData3},
					               {name:'VAP',type:'line',data : seriesData},
					               {name:'CAUTI',type:'line',data : seriesData2}]

				};
				myChart.setTheme(echartsTheme);
				myChart.setOption(option);
				Homepage.loadedChart.push(myChart);		
				Homepage.loadedMap[panelId] = myChart;
			});
		},
		
		//职业暴露人员科室分布
		initqrxczxgxgrlcs : function (panelId,dateType,ificu,date,orderType) {
			var options = {
					panelId : panelId,
					url : webroot+'/bk002Grzd/f_json/qrxczxgxgrlcs.shtml',
					data:{dateType : dateType,ificu:ificu,date:date,orderType:orderType}
			};
			this.loadByUrlAndQueryParamsToChart(options, function (data) {
				var xAxisData = [],seriesData = [],seriesData2 = [],seriesData3 = [];
				for (var i = 0, len = data.length ; i < len; i++) {
					xAxisData[i] = data[i].DEPT_NAME;
					seriesData[i] = data[i].DNGLCS;
					seriesData2[i] = data[i].ZXJMLCS;
					seriesData3[i] = data[i].HXJLCS;
				}
				var myChart = echarts.init(document.getElementById(panelId));
				var option  = {
					    tooltip : { trigger: 'axis' },
					    calculable : false,
					    xAxis : [{data : xAxisData ,axisLabel:{interval:0 ,rotate: 30, textStyle:{fontWeight:500}},splitLine:{show: false}}],
					    yAxis : [{name:"",type : 'value'}],
					    grid : {x:50,y:30,x2:5,y2:70},
					    series : [{name:'泌尿道',type:'bar',data : seriesData},{name:'中心静脉',type:'bar',data : seriesData2},{name:'呼吸机',type:'bar',data : seriesData3}]

				};
				myChart.setTheme(echartsTheme);
				myChart.setOption(option);
				Homepage.loadedChart.push(myChart);		
				Homepage.loadedMap[panelId] = myChart;
			});
		},
		
		//待处理工作提醒
		initDcl : function (panelId,dateType,html) {
			var options = {
					panelId : panelId,
					html : html,
					url : webroot+'/gm004Jcmx/f_json/dcl.shtml',
					data:{dateType : dateType}
			};
			this.loadByUrlAndQueryParams(options);
		},
		
		
		//职业暴露人员科室分布
		initzyblryksfb : function (panelId,dateType,ificu,date,orderType) {
			var options = {
					panelId : panelId,
					url : webroot+'/bl002Sjdj/f_json/findzyblryksfb.shtml',
					data:{dateType : dateType,ificu:ificu,date:date,orderType:orderType}
			};
			this.loadByUrlAndQueryParamsToChart(options, function (data) {
				var xAxisData = [],seriesData = [];
				for (var i = 0, len = data.length ; i < len; i++) {
					xAxisData[i] = data[i].DEPT_NAME;
					seriesData[i] = data[i].COUNT;
				}
				var myChart = echarts.init(document.getElementById(panelId));
				var option  = {
					    tooltip : { trigger: 'axis' },
					    calculable : false,
					    xAxis : [{data : xAxisData ,axisLabel:{interval:0 ,rotate: 30,textStyle:{fontWeight:500}},splitLine:{show: false}}],
					    yAxis : [{name:"",type : 'value'}],
					    grid : {x:50,y:30,x2:5,y2:70},
					    series : [{type:'bar',data : seriesData}]

				}
				myChart.setTheme(echartsTheme);
				myChart.setOption(option);
				Homepage.loadedChart.push(myChart);		
				Homepage.loadedMap[panelId] = myChart;	
			});
		},
		
		//职业暴露发生岗位统计
		initzyblfsgwtj : function (panelId,dateType,ificu,date,orderType) {
			var options = {
					panelId : panelId,
					url : webroot+'/bl002Sjdj/f_json/findzyblfsgwtj.shtml',
					data:{dateType : dateType,ificu:ificu,date:date,orderType:orderType}
			};
			this.loadByUrlAndQueryParamsToChart(options, function (data) {
				var xAxisData = [],seriesData = [];
				for (var i = 0, len = data.length ; i < len; i++) {
					xAxisData[i] = data[i].TJTYPE;
					seriesData[i] = data[i].COUNT;
				}
				var myChart = echarts.init(document.getElementById(panelId));
				var option  = {
					    tooltip : { trigger: 'axis' },
					    calculable : false,
					    xAxis : [{data : xAxisData,axisLabel:{interval:0 ,rotate: 30,textStyle:{fontWeight:500}}}],
					    yAxis : [{name:"",type : 'value'}],
					    grid : {x:50,y:30,x2:5,y2:70},
					    series : [{type:'bar',data : seriesData}]

				}
				myChart.setTheme(echartsTheme);
				myChart.setOption(option);
				Homepage.loadedChart.push(myChart);		
				Homepage.loadedMap[panelId] = myChart;	
			});
		},
		
		//微生物标本分布
		initwswbbfb : function (panelId,dateType,ificu,date,orderType) {
			var options = {
					panelId : panelId,
					url : webroot+'/xn011Dclymx/f_json/findwswbbfb.shtml',
					data:{dateType : dateType,ificu:ificu,date:date,orderType:orderType}
			};
			this.loadByUrlAndQueryParamsToChart(options, function (data) {
				var xAxisData = [],seriesData = [];
				for (var i = 0, len = data.length ; i < len; i++) {
					xAxisData[i] = data[i].ITEMNAME;
					seriesData[i] = data[i].COUNT;
				}
				var myChart = echarts.init(document.getElementById(panelId));
				var option  = {
					    tooltip : { trigger: 'axis' },
					    xAxis : [{data : xAxisData ,axisLabel:{interval:0 , textStyle:{fontWeight:500}}}],
					    yAxis : [{name:"",type : 'value'}],
					    grid : {x:50,y:30,x2:5,y2:30},
					    series : [{type:'bar',data : seriesData}]

				}
				myChart.setTheme(echartsTheme);
				myChart.setOption(option);
				Homepage.loadedChart.push(myChart);		
				Homepage.loadedMap[panelId] = myChart;		
			});
		},
		
		//微生物检出情况
		initwswjcqk : function (panelId,dateType,ificu,date,orderType) {
			var options = {
					panelId : panelId,
					url : webroot+'/xn011Dclymx/f_json/findwswjcqk.shtml',
					data:{dateType : dateType,ificu:ificu,date:date,orderType:orderType}
			};
			this.loadByUrlAndQueryParamsToChart(options, function (data) {
				var xAxisData = [],seriesData = [];
				for (var i = 0, len = data.length ; i < len; i++) {
					xAxisData[i] = data[i].PATHONAME;
					seriesData[i] = data[i].COUNT;
				}
				var myChart = echarts.init(document.getElementById(panelId));
				var option  = {
					    tooltip : { trigger: 'axis' },
					    xAxis : [{data : xAxisData ,axisLabel:{interval:0 ,rotate: 30,textStyle:{fontWeight:500}}}],
					    yAxis : [{name:"",type : 'value',splitLine:{show: false}}],
					    grid : {x:50,y:30,x2:5,y2:70},
					    series : [{type:'bar',data : seriesData}]

				}
				myChart.setTheme(echartsTheme);
				myChart.setOption(option);
				Homepage.loadedChart.push(myChart);		
				Homepage.loadedMap[panelId] = myChart;		
			});
		},
		
		//多重耐药分布图
		initDcnyfbt : function (panelId,dateType,ificu,date,orderType) {
			var options = {
					panelId : panelId,
					url : webroot+'/xn011Dclymx/f_json/dcnyfbt.shtml',
					data:{dateType : dateType,ificu:ificu,date:date,orderType:orderType}
			};
			this.loadByUrlAndQueryParamsToChart(options, function (data) {
				var xAxisData = [],seriesData = [];
				for (var i = 0, len = data.length ; i < len; i++) {
					xAxisData[i] = data[i].DEPTNAME;
					seriesData[i] = data[i].COUNT;
				}
				var myChart = echarts.init(document.getElementById(panelId));
				var option  = {
					    tooltip : { trigger: 'axis' },
					    xAxis : [{data : xAxisData ,axisLabel:{interval:0 ,rotate: 30,textStyle:{fontWeight:500}}}],
					    yAxis : [{name:"",type : 'value'}],
					    grid : {x:50,y:30,x2:5,y2:70},
					    series : [{type:'bar',data : seriesData}]

				}
				myChart.setTheme(echartsTheme);
				myChart.setOption(option);
				Homepage.loadedChart.push(myChart);		
				Homepage.loadedMap[panelId] = myChart;		
			});
		},
		
		//感染病例分布图
		initGrblfbt : function (panelId,dateType,ificu,date,orderType) {
			var options = {
					panelId : panelId,
					url : webroot+'/xn011Dclymx/f_json/grblfbt.shtml',
					data:{dateType : dateType,ificu:ificu,date:date,orderType:orderType}
			};
			this.loadByUrlAndQueryParamsToChart(options, function (data) {
				var xAxisData = [],seriesData = [];
				for (var i = 0, len = data.length ; i < len; i++) {
					xAxisData[i] = data[i].DEPTNAME;
					seriesData[i] = data[i].COUNT;
				}
				var myChart = echarts.init(document.getElementById(panelId));
				var option  = {
					    tooltip : { trigger: 'axis' },
					    xAxis : [{data : xAxisData ,axisLabel:{interval:0 ,rotate: 30, textStyle:{fontWeight:500}}}],
					    yAxis : [{name:"",type : 'value'}],
					    grid : {x:50,y:30,x2:5,y2:70},
					    series : [{type:'bar',data : seriesData}]

				}
				myChart.setTheme(echartsTheme);
				myChart.setOption(option);
				Homepage.loadedChart.push(myChart);		
				Homepage.loadedMap[panelId] = myChart;	
			});
		},
		
		//加载核心菜单
		initQuickMenu : function(panel) {
			var _panel = $('#'+panel);
			$.ajax({
				url: webroot + '/menu/f_json/quickMenu.shtml',
				dataType: 'json',
				cache: false,
				success: function(text) {
					if(text == '') {
						_panel.append('<li><a>暂无菜单</a></li>');
						return;
					}
					_panel.append(text);
					$('#'+panel).find('li>a').each(function(i, obj) {
						var _url = $(obj).attr('href');
						var _menuNo = $(obj).attr('data-menuNo');
						var _name = $(obj).text();
						$(obj).attr('href', '#');
						$(obj).click(function() {
							parent.menuInfo.clickMenu(_name, _url, undefined, _menuNo);
						});
					});
				}
			});
		},
		//加载系统公告
		initPtNoteList : function(panel) {
			var _panel = $('#'+panel);
			/*$.ajax({
				url: webroot + '/ptNote/adapter_json/list.shtml',
				dataType: 'json',
				error:function(){
					_panel.append('<li>暂无公告</li>');
				},
				success: function(json) {
					if(json.length > 0) {
						$.each(json, function(i, obj) {
							_panel.append(['<li><a href="javascript:Homepage.openPtNote(\'',obj.id,'\')">',obj.title,'</a></li>'].join(''));
						});
					} else {
						_panel.append('<li>暂无公告</li>');
					}
				}
			});*/
		},
		//打开公告详情
		openPtNote : function(id) {
			Comm.dialog({
		    	/*content: ['<table class="table mb60" cellpadding="0" cellspacing="0" style="margin-bottom: 10px;"><tbody>',
		    				'<tr><td class="t_title" style="width:85px;">标题：</td>',
		    				'	<td class="t_cont"><div id="ptNoteTitlePanel">加载中...</div></td>',
		    				'</tr><tr><td class="t_title">内容：</td>',
		    				'	<td class="t_cont"><div id="ptNoteContentPanel">加载中...</div></td>',
		    				'</tr>'].join(''),*/
		    	content: ['<div class="notice_title"><div id="ptNoteTitlePanel">加载中...</div></div>',	    				
	    				'<div class="notice_cont"><div id="ptNoteContentPanel">加载中...</div></div>'].join(''),
		    	title:'公告详情',
		    	width: 700,
		    	height: 450
		    });
			$.ajax({
				url: webroot + '/ptNote/adapter_json/get.shtml',
				data: {id: id},
				dataType: 'json',
				success: function(json) {
					$('#ptNoteTitlePanel').empty().append(json.title);
					$('#ptNoteContentPanel').empty().append(json.content);
				}
			});
		}
};