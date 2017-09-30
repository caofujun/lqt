<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>每日检出菌</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script type="text/javascript" src="${webroot}/resources/easyui/upgrade/datagrid-detailview.js${version}"></script>
	<style>
		.messager-button .l-btn{pading:3px 10px;width:auto;min-width: 80px;_min-width: 80px;}
		.window-header .panel-tool .panel-tool-collapse{display: none;}
		.window-header .panel-tool .panel-tool-min{display: none;}
		.window-header .panel-tool .panel-tool-max{display: none;}
	</style>
	</head>
	<body>
	<div id="tb" class="m_search">
		<div class="m_search_first">
			<span>检出日期:</span>
			<input type="text" id="date" value="${date}" style="width:80px"  class="Wdate text" onclick="WdatePicker({readOnly:true,onpicked:function(dp){viewMdr.chooseMdr('z','0')}})"  />
			<!-- <div class="n_btn_blue">
				<a href="javascript:;" onclick="viewMdr.chooseMdr('z','1')"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div> -->
		</div>
		<div class="m_search_first">
			<a class="cur" id="zda" href="javascript:;" onclick="viewMdr.chooseMdr('z','0',this)">重点菌(<span id="zdCount"></span>株)</a>
			<a id="fzda" href="javascript:;" onclick="viewMdr.chooseMdr('f','0',this)">非重点菌(<span id="fzdCount"></span>株)</a>
			<a id="qba" href="javascript:;" onclick="viewMdr.chooseMdr('q','0',this)">全部(<span id="qbCount"></span>株)</a>
		</div>
		<div class="m_search_m">
			<a id="yga" href="javascript:;"onclick="viewMdr.chooseMdr('','1',this)">院感(<span id="ygCount"></span>株)</a>
			<a href="javascript:;" onclick="viewMdr.chooseMdr('','2',this)">社感(<span id="sgCount"></span>株)</a>
			<a href="javascript:;" onclick="viewMdr.chooseMdr('','3',this)">定植(<span id="dzCount"></span>株)</a>
			<a href="javascript:;" onclick="viewMdr.chooseMdr('','4',this)">污染(<span id="wrCount"></span>株)</a>
			<a href="javascript:;" onclick="viewMdr.chooseMdr('','5',this)">不确定(<span id="bqdCount"></span>株)</a>
			<a id="wsha" href="javascript:;" onclick="viewMdr.chooseMdr('','0',this)">未处理(<span id="wshCount"></span>株)</a>
			<a href="javascript:;" onclick="viewMdr.chooseMdr('','',this)">全部(<span id="qbcCount"></span>株)</a>
		</div>
		<div class="m_search_last">
			<input type="radio" name="deptType" value="gx" onclick="viewMdr.chooseDeptType(this.value)" id="deptType_1"/><label class="input_text" for="deptType_1">管辖科室</label>
			<input type="radio" name="deptType" value="qy" onclick="viewMdr.chooseDeptType(this.value)" id="deptType_2"/><label class="input_text" for="deptType_2">全部科室</label>			
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="viewMdr.exportExcelMdrDay()"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
				</div>				
			</div>		
		</div>
		<!-- <input type="button" onclick="viewMdr.query('z','1')" class="btn_search" iconCls="icon-search" plain="true" value="查询" />
	 	<input type="button" onclick="viewMdr.exportExcelMdr()" class="btn" iconCls="icon-search" plain="true" value="导出" /> -->
	 </div>
		<div id="viewMdrPanel"></div>
		<script type="text/javascript">
		var spec='z';
		var typeId;
		var deptTypeId='qy';
		var viewMdr = {
			panel : 'viewMdrPanel',
			dataSections : eval('${dataSections}'),
			getYzxx:function(zyid){
		    	 Comm.dialogGlobal({
			        	url:"${webroot}/st004Yzxxb/f_view/getYzxx.shtml?zyId="+zyid,
			            title: "医嘱信息",
			            width:600,
			            height:400,
			            type:"iframe",
			            parent:this
			        });
		    },
			//查询
			query : function() {
				autoTip.clear();
		        $('#'+viewMdr.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: false,
	                collapsible:true,
	                url:'${webroot}/xn011Dclymx/f_json/pageQueryDay.shtml',
	                queryParams: {	                
		                'date': $('#date').val(),		            
		                'specType':spec,
		                'infectTypeId':typeId,
		                'deptType':deptTypeId
		            },
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
/* 	                frozenColumns:[[
							
	                            ]], */
	                columns:[
	                	[
						<c:if test="${isSh == 1}">
						{field:'authStatusMdr',title:'审核状态',sortable:true,width:70,
							 formatter:function(value,r,index){
									if(r.authStatusMdr){
										var auth;
										if(r.authStatusMdr==1){
											auth='已审核';
											return ['<span  title="'+r.authUser+'于'+r.authDate.substring(0,10)+'已审核">',auth,'</span>'].join('');
										}else if(r.authStatusMdr==0){
											auth='未审核';
											return auth;
										}
									}	
								}
							 },
							 </c:if>
							{field:'gl',title:'隔离状态',sortable:true,width:70,
							 formatter:function(value,r,index){
									if(r.gl){
										var agl;
										if(r.gl==2){
											agl='正在隔离';
										}else if(r.gl==1){
											agl='隔离结束';
										}
										return ['<a href="javascript:viewMdr.getYzxx(\'',r.zyid,'\',\'\');">',agl,'</a>'].join('');
									}	
								}
							 },
	         				{field:'submiAt',title:'送检日期',sortable:true,width:80},
	         				{field:'surveyDeptName',title:'送检科室',sortable:true,width:130},
	         				{field:'zyid',title:'${patientZyTitle}',sortable:true,width:100,
								formatter:function(value,r){
									return ['<a href="javascript:viewMdr.showDetail(\'',r.zyid,'\',\'\');">',r.${patientZyValue},'</a>'].join('');
								}	
							},
							{field:'patientName',title:'患者',sortable:true,width:80,
								formatter:function(value,row,index){
								  	  return [(row.patientName+'('+row.sex+','+row.age+row.ageUnit+')')].join('');}	
							},
							{field:'bedNo',title:'床号',sortable:true,width:50},
							{field:'deptName',title:'所在科室',sortable:true,width:100},
							{field:'testOrderNo',title:'单号',sortable:true,width:120},
							{field:'lisitemName',title:'标本',sortable:true,width:70},
							{field:'dt',title:'检出日期',sortable:true,width:80},
							{field:'lispathoName',title:'检出菌',sortable:true,width:130},
							{field:'resPropName',title:'多耐类型',sortable:true,width:100,
								formatter:function(value,r){
									var resPropName = '<a class="a_w" href="javascript:viewMdr.showMdr(\''+r.testOrderNo+'\',\''+r.pathogenSn+'\');">'+(!r.resPropName?"":r.resPropName)+'</a>';
									if('hospital'=='${acType}'){
										resPropName = resPropName + '<a href="#" title="cc111" class="easyui-tooltip" id="id_tooltip_' + r.zyid + '_' + r.testOrderNo.replace(/\./g,"") + '_' + r.orderno + '_' + r.surveyDeptId + '_' + r.normItemId + '_' + r.normOrderno + '"><span class="arrow_down"></span></a>';
									}
									return resPropName;
								}		
							},
							{field:'specDescribes',title:'特殊耐药',sortable:true,align:'center',width:60,
								formatter:function(value,r){
									return ['<a href="javascript:viewMdr.showTsny(\'',r.specDescribes,'\');">',r.specDescribes,'</a>'].join('');
								}		
							},
							{field:'esbl',title:'ESBL',sortable:true,align:'center',width:50},
							{field:'authStatus',title:'authStatus',hidden:true},
							{field:'ifts',title:'感染类型 | 感染时间 | 感染部位',sortable:true,width:280,
								formatter:function(value,r,index){
									var x = (!r.infectTypeName?"":r.infectTypeName)+(!r.grsj?"":" | "+(r.grsj).substring(0,10))+(!r.grbw?"":" | "+r.grbw)+((r.authStatus=="未审核" )?"(<font color='red'>"+r.authStatus+"</font>)":"") ;
									return [('<a href="javascript:void(0);" style="" id="od_tooltip_'+ r.zyid + '_' + r.testOrderNo + '_' + r.orderno + '_' + r.surveyDeptId + '_' + r.normItemId + '_' + r.normOrderno+'" class="easyui-tooltip">' + x + '</a>')].join('');
								}								
							 },
							{field:'opts',title:'操作',width:80,formatter:function(value,r){
								var resPropName = '';
								if('${isSh}'== 1){
									resPropName += '<a href="javascript:viewMdr.check(\''+ r.resPropName + '\',\'' + r.orderno + '\',\'' + r.surveyDeptId + '\',\'' + r.dt +'\');" class="ico_check" title="审核"></a>';
								}
								return resPropName += '<a href="#" class="easyui-tooltip" id="id_tooltip_' + r.testOrderNo.replace(/\./g,"") + '_'+ r.pathoCode+'">干预 &nbsp;&nbsp;<span class="arrow_down"></span></a>';
							}} 
		                ]
	                ],	              
			    	toolbar:'#tb',
			    	onLoadSuccess: function(data) {
			    		for (var i = 0; i < data.rows.length; i++) {
	            			var row = data.rows[i];
	            			var divId = 'id_tooltip_' + row.zyid + '_' + row.testOrderNo.replace(/\./g,"") + '_' + row.orderno + '_' + row.surveyDeptId + '_' + row.normItemId + '_' + row.normOrderno;
	            			//console.log(divId);
	            			viewMdr.addTooltip(divId, row.zyid, row.testOrderNo, row.orderno, row.surveyDeptId, row.normItemId, row.normOrderno);
	            			var t = (row.dt).replace(/-/g,"/");
	            			var dateM = new Date(t);
	            			var dc = (!row.bedNo?"未知":row.bedNo)+" 床 ["+row.patientName+"]于 "+(dateM.getMonth()+1)+"月"+dateM.getDate()+"日"+" 检出 "+row.lispathoName+" ( "+(!row.resPropName?" ":row.resPropName)+(!row.specDescribes?" ":" / "+row.specDescribes)+") "+(!row.esbl?"":"ESBL("+row.esbl+")")+"，请注意开立隔离医嘱，采取隔离措施";
	            			viewMdr.addOpts('id_tooltip_' + row.testOrderNo.replace(/\./g,"") +'_'+row.pathoCode,row.deptId,dc,row.testOrderNo,row.dt,row.zyid,row.surveyDeptId,row.orderno,(!row.infectTypeId?"":row.infectTypeId),(!row.normItemId?"":row.normItemId),(!row.normOrderno?"":row.normOrderno));
	            			viewMdr.addOdTooltip('od_tooltip_'+ row.zyid + '_' + row.testOrderNo.replace(/\./g,"") + '_' + row.orderno + '_' + row.surveyDeptId + '_' + row.normItemId + '_' + row.normOrderno,  row.dt,row.zyid,row.surveyDeptId,row.orderno,row.testOrderNo,row.pathogenSn);
			    		}
			    		$.ajax({
				            url: '${webroot}/xn011Dclymx/f_json/dayCount.shtml',
				            type: 'post',
				            data: {'specType':spec,'date': $('#date').val(),'deptType':deptTypeId},
				            dataType: 'json',			         
				            success : function(data) {
				            	$('#zdCount').html(data.ZDCOUNT);
				            	$('#fzdCount').html(data.FZDCOUNT);
				            	$('#qbCount').html(data.QBCOUNT);
				            	$('#ygCount').html(data.YGCOUNT);
				            	$('#sgCount').html(data.SGCOUNT);
				            	$('#dzCount').html(data.DZCOUNT);
				            	$('#wrCount').html(data.WRCOUNT);
				            	$('#bqdCount').html(data.BQDCOUNT);
				            	$('#wshCount').html(data.WSHCOUNT);
				            	$('#qbcCount').html(data.YGCOUNT+data.SGCOUNT+data.DZCOUNT+data.WRCOUNT+data.BQDCOUNT+data.WSHCOUNT);
							}
				        });
	                }
		        });
		    },
		    check :  function (resPropName,orderno,surveyDeptId,dt){
		    	 Comm.dialogGlobal({
			        	url:"${webroot}/xn011Dclymx/f_view/mdrCheck.shtml?orderno=" + orderno + "&surveyDeptId=" + surveyDeptId + "&dt="+dt.substring(0,10)+ "&resPropName=" + encodeURI(encodeURI(resPropName)),
			            title: "多耐类型审核确认",
			            width:400,
			            hight:500,
			            parent:this
			     });
		    	
		    },
		    chooseMdr:function (specType,infectTypeId,_this){
		    	$('.m_search_first').find("a").attr("class","");
		    	$('.m_search_m').find("a").attr("class","");
		    	if(specType==''){
					specType=spec;
				}else{
					spec=specType;
					$("#wsha").attr("class","cur");
				}
		    	typeId = infectTypeId;
		    	if(spec=='z'){
		    		$("#zda").attr("class","cur");
		    	}else if(spec=='f'){
		    		$("#fzda").attr("class","cur");
		    	}else if(spec=='q'){
		    		$("#qba").attr("class","cur");
		    	}
		    	$(_this).attr("class","cur");
		    	viewMdr.query();
		    },
		    //添加Tooltip
			addTooltip : function(divId, zyid, testOrderNo, orderno, surveyDeptId, normItemId, normOrderno) {
				$('#' + divId).tooltip({
	                content: $('<div class="ttDiv"></div>'),
	                showEvent: 'click',
	                onUpdate: function(content){
	                    content.panel({
	                    	width: 250,
	                    	border: false,
	                        href: '${webroot}/xn011Dclymx/f_view/mdr/type/list.shtml?zyid=' + zyid+'&testOrderNo=' + testOrderNo+'&orderno=' + orderno+'&surveyDeptId=' + surveyDeptId+'&normItemId=' + normItemId+'&normOrderno=' + normOrderno+'&divId=' + divId+''
	                    });
	                },
	                onShow: function(){
	                    var t = $(this);
	                    t.tooltip('tip').unbind().bind('mouseenter', function(){
	                        t.tooltip('show');
	                    }).bind('mouseleave', function(){
	                        t.tooltip('hide');
	                    });
	                }
	            });
			},
			addOdTooltip:function(divId, dt,zyid,surveyDeptId,orderno, testOrderNo,pathoCode){
		 		$('#' + divId).tooltip({
	                content: $('<div></div>'),
	                showEvent: 'click',
	                onUpdate: function(content){
	                    content.panel({
	                    	width: 300,
	                    	border: false,
	                        href: '${webroot}/xn011Dclymx/f_view/mdr/type/optsDetail.shtml?zyid=' + zyid+'&dt=' + dt+'&orderno=' + orderno+'&surveyDeptId=' + surveyDeptId+'&pathoCode='+pathoCode+'&testOrderNo='+testOrderNo
	                    });
	                },
	                onShow: function(){
	                    var t = $(this);
	                    t.tooltip('tip').unbind().bind('mouseenter', function(){
	                        t.tooltip('show');
	                    }).bind('mouseleave', function(){
	                        t.tooltip('hide');
	                    });
	                }
	            });
		    },
			//添加Tooltip
			addOpts: function(divId, deptId, defContent, testOrderNo,dt,zyid,surveyDeptId,orderno,infectTypeId,normItemId,normOrderno,resprop) {
				var tmpDiv = '<ul class="down_li">';
				tmpDiv += '<li><a href="#" onclick="javascript:sendMessage(\''+zyid+'\',\''+divId+'\',\''+deptId+'\',\''+defContent+'\',\''+testOrderNo+'\')">干预</a></li>';
				if(infectTypeId!=2){
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'1\',\'院感\',\''+normItemId+'\',\''+normOrderno+'\',\''+resprop+'\',\''+defContent+'\');">院感</a></li>';
				}
				if(infectTypeId!=1){
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'2\',\'社感\',\''+normItemId+'\',\''+normOrderno+'\',\''+resprop+'\',\''+defContent+'\');">社感</a></li>';
				}
				if(!infectTypeId || infectTypeId>2){
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'3\',\'定植\',\''+normItemId+'\',\''+normOrderno+'\',\''+resprop+'\');">定植</a></li>';
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'4\',\'污染\',\''+normItemId+'\',\''+normOrderno+'\',\''+resprop+'\');">污染</a></li>';
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'5\',\'不确定\',\''+normItemId+'\',\''+normOrderno+'\');">不确定</a></li>';
				}
				if(infectTypeId){
					var title = (infectTypeId==1?"院感":(infectTypeId==2?"社感":(infectTypeId==3?"定植":(infectTypeId==4?"污染":(infectTypeId==5?"不确定":""))))) ;
					tmpDiv += '<li><a href="#" onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\'0\',\'取消\',\''+normItemId+'\',\''+normOrderno+'\');">取消'+title+'</a></li>';
				}
				tmpDiv += '</ul>';
				
				//tmpDiv += '<li><a href="#" onclick="javascript:sendMessage(\''+divId+'\',\''+deptId+'\',\''+defContent+'\')">干预</a></li>';
				//tmpDiv += '<li><a href="#" '+(infectTypeId==2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',1,\'院感\');">院感</a></li>';
				//tmpDiv += '<li><a href="#" '+(infectTypeId==1?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',2,\'社感\');">社感</a></li>';
				//tmpDiv += '<li><a href="#" '+(infectTypeId<=2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',3,\'定值\');">定值</a></li>';
				//tmpDiv += '<li><a href="#" '+(infectTypeId<=2?"disabled=disabled":"") +' onclick="javascript:viewMdr.intervOpts(\''+testOrderNo+'\',4,\'污染\');">污染</a></li>';
				
				$('#' + divId).tooltip({
	                content: $(tmpDiv),
	                showEvent: 'click',
	                onShow: function(){
	                    var t = $(this);
	                    t.tooltip('tip').unbind().bind('mouseenter', function(){
	                        t.tooltip('show');
	                    }).bind('mouseleave', function(){
	                        t.tooltip('hide');
	                    });
	                }
	            });
			},
		    chooseDeptType:function(deptType){
		    	deptTypeId = deptType;
		    	viewMdr.query();
		    },
		    //导出
		    exportExcelMdrDay:function (){
				autoTip.clear();
                var date= $('#date').val();        
				var url = "${webroot}/xn011Dclymx/f_json/exportExcelMdrDay.shtml?date="+date+"&specType="+spec+"&infectTypeId="+typeId+"&deptType="+deptTypeId;			
		    	window.location.href = url;
			},
			showMdr:function(testOrderNo,pathogenSn){
				 Comm.dialogGlobal({
			        	url:"${webroot}/xn011Dclymx/f_view/mdrDetail.shtml?testOrderNo="+testOrderNo+"&pathogenSn="+pathogenSn,
			            title: "多耐详情",
			            width:500,
			            height:360,
			            type:"iframe",
			            parent:this
			        });
		    },
		    showTsny:function(specDescribes){
		    	Comm.dialogGlobal({
		    		url:"${webroot}/xn011Dclymx/f_view/mdrTsny.shtml?specDescribes=" + specDescribes ,
		    		title:"特殊耐药",
		    		width:600,
		    		height:320,
		    		type:"iframe",
		    		parent:this
		    	});
		    },
			showDetail:function(zyid){
		    	parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid='+zyid+"&tab=2",true);
		    },
		    //选择感染类型
		    chooseInfectTypeId:function(infectTypeId,zyid,testOrderNo,orderno,surveyDeptId,normItemId,normOrderno){
		    	if(infectTypeId=='1'||infectTypeId=='2'){
		    		parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid+"&infectTypeId="+infectTypeId+"&testOrderNo="+testOrderNo+"&normItemId="+normItemId+"&normOrderno="+normOrderno+"&surveyDeptId="+surveyDeptId+"&orderno="+orderno,true);
		    		
		    	}else{
		    		$.ajax({
	                    url: '${webroot}/xn011Dclymx/f_json/update.shtml',
	                    type: 'post',
	                    data: { zyid: zyid,normItemId:normItemId,normOrderno:normOrderno,testOrderNo:testOrderNo,orderno:orderno,surveyDeptId:surveyDeptId,infectTypeId:infectTypeId,audited:1},
	                    dataType: 'json',
	                    success : function(json) {
	                    	$.messager.show({title : '提示',msg : '操作成功！'});         
						}
	        		}); 
		    	}
		    },
		    //编辑
		    save: function(index, zyid,testOrderNo,orderno,surveyDeptId,normItemId,normOrderno) {
		    	var infectTypeId = $('input[name="infectTypeId'+index+'"]:checked ').val();
		    	var resProp = $('input[name="resProp'+index+'"]:checked ').val();
		    	var inteMode = $('input[name="inteMode'+index+'"]:checked ').val();
		    	 $.ajax({
                    url: '${webroot}/xn011Dclymx/f_json/update.shtml',
                    type: 'post',
                    data: { zyid: zyid,normItemId:normItemId,normOrderno:normOrderno,testOrderNo:testOrderNo,orderno:orderno,surveyDeptId:surveyDeptId,infectTypeId:infectTypeId,inteMode:inteMode,resProp:resProp,audited:1},
                    dataType: 'json',
                    success : function(json) {
                    	$.messager.show({title : '提示',msg : '操作成功！'});
                    	//$('#' + viewMdr.panel).datagrid('collapseRow', index);
                    	viewMdr.query(index);                 
					}
        		}); 
		    },
		    intervOpts:function(testOrderNo,dt,zyid,surveyDeptId,orderno,infectTypeId,infectTypeName,normItemId,normOrderno,resprop,defContent){
		    	if ('1' === infectTypeId) {
		    		defContent = defContent + "，已判断为院感，请及时上报（若上报请忽略）。";
		    	} else {
		    		defContent = defContent + "。";
		    	}
		    	if(!infectTypeId || !dt || !zyid || !surveyDeptId || !orderno){
		    		$.messager.show({title : '提示',msg : '必要参数获取失败！'});
		    	}else if(infectTypeId==0){
		    		$.messager.defaults = { ok: "确定", cancel: "取消" ,width:300};
		    		$.messager.confirm("操作提示", "确认撤销感染类型 ？", function (data) {  
		                if (data) {
		                	$.ajax({
		                    	url: '${webroot}/xn011Dclymx/f_json/updateGrlx.shtml',
		                        type: 'POST',
		                        data: { 
		                        	dt: dt,
		                        	zyid:zyid,
		                        	surveyDeptId:surveyDeptId,
		                        	orderno:orderno,
		                        	infectTypeId:infectTypeId,
		                        	infectTypeName:infectTypeName 
								},
		                        dataType: 'json',
		                        success : function(json) {
		                        	//json = eval("("+json+")");
		                        	$.messager.show({title : '提示',msg : json.msg});
		                        	viewMdr.query();
		    					}
		                    });
		                }
		    		});
		    	}else if(infectTypeId>2){
		    		//不是院感、社感
		    		$.messager.defaults = { ok: "确定", cancel: "取消" ,width:300};
		    		$.messager.confirm("操作提示", "确认此细菌为 "+infectTypeName+"？", function (data) {  
		                if (data) {  
		                    $.ajax({
		                    	url: '${webroot}/xn011Dclymx/f_json/updateGrlx.shtml',
		                        type: 'POST',
		                        data: { 
		                        	dt: dt,
		                        	zyid:zyid,
		                        	surveyDeptId:surveyDeptId,
		                        	orderno:orderno,
		                        	infectTypeId:infectTypeId,
		                        	infectTypeName:infectTypeName
								},
		                        dataType: 'json',
		                        success : function(json) {
		                        	//json = eval("("+json+")");
		                        	$.messager.show({title : '提示',msg : json.msg});
		                        	viewMdr.query();
		    					}
		                    });
		                }  
		            });  
		    	}else if(infectTypeId<=2){
		    		//院感、社感
		    		$.messager.defaults = { ok: "标记为"+infectTypeName+"致病菌,并提醒临床", cancel: "代报致病菌"+infectTypeName+"报卡" ,width:400};
		    		$.messager.confirm("操作提示", "确认此细菌为 "+infectTypeName+"致病菌？", function (data) {  
		    			if (data) {
		    				$.ajax({
		                    	url: '${webroot}/xn011Dclymx/f_json/updateGrlx.shtml',
		                        type: 'POST',
		                        data: { 
		                        	dt: dt,
		                        	zyid:zyid,
		                        	surveyDeptId:surveyDeptId,
		                        	orderno:orderno,
		                        	infectTypeId:infectTypeId,
		                        	infectTypeName:infectTypeName,
		                        	testOrderNo:testOrderNo,
		                        	remindClinic:"YES",
		                        	messageToClinic:defContent
								},
		                        dataType: 'json',
		                        success : function(json) {
		                        	//json = eval("("+json+")");
		                        	$.messager.show({title : '提示',msg : json.msg});
		                        	viewMdr.query();
		    					}
		                    });
		                }else{
		                	bk(zyid,surveyDeptId,orderno,infectTypeId,normItemId,normOrderno,testOrderNo);
		                }
		            });  
		    	}
		    }
		};
		$(document).ready(function () {
			window.setTimeout(function() {
				viewMdr.chooseMdr('${specType}','${infectTypeId}');
				$('.m_search_m').find("a").eq(5).click();
			}, 500);
		});
		function sendMessage(zyid,divId,deptId,defContent,testOrderNo){
	     	$("#"+divId).tooltip('hide');
			//.replace(/\+/g,"%2B") 转换特殊字符 + 
	     	Comm.dialogGlobal({
		    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid="+zyid+"&deptId="+deptId+"&msgType=36&testOrderNo="+testOrderNo+"&defContent="+encodeURI(encodeURI(defContent).replace(/\+/g,"%2B")),
		        title: "干预会话",
		        width:870,
		        height:555,
		        type:"iframe"
		    });
		}
		function bk(zyid,surveyDeptId,orderno,infectTypeId,normItemId,normOrderno,testOrderNo){
			parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid+'&infectTypeId='+infectTypeId+'&normItemId='+normItemId+'&normOrderno='+normOrderno+'&testOrderNo='+testOrderNo+'&orderno='+orderno+'&surveyDeptId='+surveyDeptId,true);
		}
		</script>
	</body>
</html>
