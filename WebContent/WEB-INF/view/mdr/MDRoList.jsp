<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="" class="m_search_first" style="padding:2px 5px;">
			<lable class="radioSpan"  style="float:left; padding-left:15px; padding-top:5px;padding-bottom:5px;">
<!--                 <input type="radio" id = "quan" name="key" value="q" onclick="viewMdr.query()">全部</input> -->
                
                <input type="checkbox" id = "zhong" name="key" value="z" onclick="viewMdr.query()">&nbsp;&nbsp;&nbsp;重点菌</input>
            </lable>
			<lable class="radioSpan"  style="float:left; padding-left:15px; padding-top:5px;padding-bottom:5px;">
            <span style="color:red; padding-left:100px; padding-top:15px;padding-bottom:5px;">此页面多耐菌列表比实际检出时间延后一天</span>
            </lable>
		</div>
		<div id="yzxxDetailPanel"></div>
		<script type="text/javascript">
			
	        function seturl(testOrderNo,pathogenSn){
				var url="${webroot}/xn011Dclymx/f_view/mdrDetail.shtml?testOrderNo="+testOrderNo+"&pathogenSn="+pathogenSn;
			    $("#viewMdrDetailIframe").attr("src",url);
			}
	      
			var viewMdr = {
				panel : 'yzxxDetailPanel',
				query : function(){
					$('#'+viewMdr.panel).datagrid({
		                fit: true,
		                nowrap: true,
		                autoRowHeight: false,
		                striped: false,
		                fitColumns: true,
		                collapsible:true,
		                url:'${webroot}/xn011Dclymx/f_json/pageQueryMDRo.shtml?zyid=${zyid}&page=1&size=10&key='+$('input[name="key"]:checked ').val(),   
		                remoteSort: false,
		                singleSelect: true,
		                border:false,
		                rownumbers:true,
		                view: emptyView,
		                emptyMsg: '暂无需要报告的多耐菌！',
		                columns:[
		                	[ 
								{field:'authStatus',title:'状态',sortable:true,width:80},
								{field:'submiAt',title:'送检日期',sortable:true,width:80},
								{field:'lisitemName',title:'标本',sortable:true,width:60},
								{field:'dt',title:'检出日期',sortable:true,width:80},
								{field:'lispathoName',title:'检出菌',sortable:true,width:100},
								{field:'resPropName',title:'多耐类型',sortable:true,width:60,
									formatter:function(value,r){
										var resPropName = '<a class="a_w" href="javascript:viewMdr.showMdr(\''+r.testOrderNo+'\',\''+r.pathogenSn+'\');">'+r.resPropName+'</a>';
										if('hospital'=='${acType}'){
											resPropName = resPropName + '<a href="#" title="cc111" class="easyui-tooltip" id="id_tooltip_' + r.zyid + '_' + r.testOrderNo + '_' + r.orderno + '_' + r.surveyDeptId + '_' + r.normItemId + '_' + r.normOrderno + '"><span class="arrow_down"></span></a>';
										}
										return resPropName;
									}		
								},
								{field:'specDescribes',title:'特殊耐药',sortable:true,align:'center',width:60},
								{field:'esbl',title:'ESBL',sortable:true,align:'center',width:50},	
								{field:'ifts',title:'感染类型 | 感染时间 | 感染部位 | 操作人',sortable:true,width:350,
									formatter:function(value,r,index){
										var x = (!r.infectTypeName?"":r.infectTypeName)+(!r.grsj?"":" | "+(r.grsj).substring(0,10))+(!r.grbw?"":" | "+r.grbw)+((r.authStatus=="未审核" )?"(<font color='red'>"+r.authStatus+"</font>)":"")+(!r.reportDrName?"":" | "+r.reportDrName) ;
										return x;
									}								
								 },
								{field:'opts',title:'操作',width:100,formatter:function(value,row){
									var dc = (!row.bedNo?"未知":row.bedNo)+" 床 ["+row.patientName+"]于 "+row.dt+" 检出 "+row.lispathoName+" ( "+(!row.resPropName?" ":row.resPropName)+(!row.specDescribes?" ":" , "+row.specDescribes)+") "+(!row.esbl?"":"ESBL("+row.esbl+")")+"，请注意采取隔离措施";
									return viewMdr.addOpts('id_tooltip_' + row.testOrderNo+'_'+row.lispathoCode,row.deptId,dc,row.testOrderNo,row.dt,row.zyid,row.surveyDeptId,row.orderno,(!row.infectTypeId?"":row.infectTypeId),(!row.normItemId?"":row.normItemId),(!row.normOrderno?"":row.normOrderno),(!row.resProp?"":row.resProp));
								}}
			                ]
		                ],	                 
			            pagination:true,
		                onLoadSuccess: function(data) {
		            		for (var i = 0; i < data.rows.length; i++) {
		            			var row = data.rows[i];
		            			viewMdr.addTooltip('id_tooltip_' + row.zyid + '_' + row.testOrderNo + '_' + row.orderno + '_' + row.surveyDeptId + '_' + row.normItemId + '_' + row.normOrderno, row.zyid, row.testOrderNo, row.orderno, row.surveyDeptId, row.normItemId, row.normOrderno);
		            			viewMdr.addOdTooltip('od_tooltip_'+ row.zyid + '_' + row.testOrderNo + '_' + row.orderno + '_' + row.surveyDeptId + '_' + row.normItemId + '_' + row.normOrderno,  row.dt,row.zyid,row.surveyDeptId,row.orderno,row.testOrderNo,row.pathogenSn);
		            		}
		                },
		                onClickRow:function(rowIndex, rowData){	                
		                	seturl(rowData.testOrderNo,rowData.pathogenSn);
				    	},
				    	toolbar:'#tb'
		            });
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
			  //添加Tooltip
				addTooltip : function(divId, zyid, testOrderNo, orderno, surveyDeptId, normItemId, normOrderno) {
					$('#' + divId).tooltip({
		                content: $('<div></div>'),
		                showEvent: 'click',
		                onUpdate: function(content){
		                	alert("2");
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
					var tmpDiv = '<select style="padding:0px;height:23px;width:70px" id="'+divId+'" onchange="opts(\''+divId+'\',\''+deptId+'\',\''+defContent+'\',\''+ testOrderNo+'\',\''+dt+'\',\''+zyid+'\',\''+surveyDeptId+'\',\''+orderno+'\',\''+infectTypeId+'\',\''+normItemId+'\',\''+normOrderno+'\',\''+resprop+'\')">';
					if( infectTypeId == null || infectTypeId==""){
						tmpDiv += '<option>请选择</option>';
					}
					if('${ownership}'!="clinical"){
					tmpDiv += '<option value="gy">干预</option>';
					}
					if(infectTypeId!=2){
						tmpDiv += '<option value="1" '+(infectTypeId==1?"selected=selected":"")+'>院感</a></option>';
					}
					if(infectTypeId!=1){
						tmpDiv += '<option value="2" '+(infectTypeId==2?"selected=selected":"")+'>社感</a></option>';
					}
					if(!infectTypeId || infectTypeId>2){
						tmpDiv += '<option value="3" '+(infectTypeId==3?"selected=selected":"")+'>定植</a></option>';
						tmpDiv += '<option value="4" '+(infectTypeId==4?"selected=selected":"")+'>污染</a></option>';
						if('${ownership}'!="clinical"){
						tmpDiv += '<option value="5" '+(infectTypeId==5?"selected=selected":"")+'>不确定</a></option>';
						}
					}
					if( infectTypeId != "null" && infectTypeId!="" && infectTypeId!=1){
						tmpDiv += '<option value="0">撤销</a></option>';
					}
					tmpDiv += '</select>';
					return tmpDiv;
				},
				intervOpts:function(testOrderNo,dt,zyid,surveyDeptId,orderno,infectTypeId,infectTypeName,normItemId,normOrderno,resprop,defContent){
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
			                        	alert( json.msg);
			                        	//$.messager.show({title : '提示',msg : json.msg});
			                        	viewMdr.query();
			    					}
			                    });
			                }
			    		});
			    	}else if(infectTypeId>1){
			    		//不是院感
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
			                        	//$.messager.show({title : '提示',msg : json.msg});
			                        	alert( json.msg);
			                        	viewMdr.query();
			    					}
			                    });
			                }  
			            });  
			    	}else if(infectTypeId==1){
			    		//院感
			    		bk(zyid,surveyDeptId,orderno,infectTypeId,normItemId,normOrderno,testOrderNo,resprop);
			    	}
			    }
			};
			function bk(zyid,surveyDeptId,orderno,infectTypeId,normItemId,normOrderno,testOrderNo,resprop){
				 if(typeof(parent.parent.menuInfo) != 'undefined'){
					parent.parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid+'&infectTypeId='+infectTypeId+'&normItemId='+normItemId+'&normOrderno='+normOrderno+'&testOrderNo='+testOrderNo+'&orderno='+orderno+'&surveyDeptId='+surveyDeptId+'&resprop='+resprop,true);
				}else{
					window.location.href = '${webroot}/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid+'&infectTypeId='+infectTypeId+'&normItemId='+normItemId+'&normOrderno='+normOrderno+'&testOrderNo='+testOrderNo+'&orderno='+orderno+'&surveyDeptId='+surveyDeptId+'&resprop='+resprop;
				}
	    	};
	    	function sendMessage(zyid,divId,deptId,defContent){
		     	$("#"+divId).tooltip('hide');
				//.replace(/\+/g,"%2B") 转换特殊字符 + 
		     	Comm.dialogGlobal({
			    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid="+zyid+"&deptId="+deptId+"&msgType=1&defContent="+encodeURI(encodeURI(defContent).replace(/\+/g,"%2B")),
			        title: "干预会话",
			        width:870,
			        height:555,
			        type:"iframe"
			    });
			}
			$(document).ready(function () {
				if('${zhong}'=="1"){
					$('#zhong').attr('checked', true);
				}
				window.setTimeout(function() {
					viewMdr.query();
				}, 500);
			});
			function opts(divId, deptId, defContent, testOrderNo,dt,zyid,surveyDeptId,orderno,infectTypeId,normItemId,normOrderno,resprop) {
				var v = $("#"+divId+" option:selected").val();
				var n = $("#"+divId+" option:selected").text();
				if(!v){
					return;
				}else if(v=="gy"){
					sendMessage(zyid,divId,deptId,defContent);
				}else{
					viewMdr.intervOpts(testOrderNo,dt,zyid,surveyDeptId,orderno,v,n,normItemId,normOrderno,resprop);
				}
			}
		</script>
	</body>
</html>
