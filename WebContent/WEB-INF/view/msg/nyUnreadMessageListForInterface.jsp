<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	   	<title>未读消息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
	</head>
	<body>
		<div id="nyUnreadMessagePanel"></div>
		<div id="tb" class="m_search">
			<span class="pro_text">发送时间：</span>
			<input type="hidden" id="employeeId" value="${employeeId}"/>
			<input type="hidden" id="deptId" value="${deptId}"/>
			<input type="text"  id="startDate" value="${startDate}" style="width:80px"  class="Wdate text" onclick="WdatePicker()"  />~
			<input type="text"  id="endDate" value="${endDate}" style="width:80px" class="Wdate text" onclick="WdatePicker()" />
		 	<input type="text" class="auto-tip" style="width:180px" data-tip="发送人/接收人/会话内容" id="searchString"/>
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="nyMessageTheme.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>
		</div>
		<script type="text/javascript">
			var nyMessageTheme = {
				panel : 'nyUnreadMessagePanel',
				//查询
				query : function() {
					autoTip.clear();
			        $('#'+nyMessageTheme.panel).datagrid({
			            queryParams: {
			            	'searchString': $('#searchString').val(),
			                'startDate': $('#startDate').val(),
			                'endDate': $('#endDate').val(),
			                'userId' : $('#employeeId').val(),
			                'sendDeptId':$('#deptId').val()
			            },
			            pageNumber: 1
			        });
			    }			    
			};
			$(document).ready(function () { 
				$('#'+nyMessageTheme.panel).datagrid({
	                fit: true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                border:false,
	                url:'${webroot}/msg/f_view/pageQueryForInterface.shtml',
	                queryParams: {
		                'startDate': $('#startDate').val(),
		                'endDate': $('#endDate').val(),
		                'userId' : $('#employeeId').val(),
		                'sendDeptId':$('#deptId').val()
		            },
	                remoteSort: false,
	                singleSelect: true,
	                columns:[
	                	[
	                	 	//来源科室 ，来源人，接收人 接收科室  接收内容，接收时间
	                	 	{field:'sendDeptName',title:'发送科室',width:10},
	                	 	{field:'sendUserId',title:'sendUserId',hidden:true},
	                	 	{field:'sendUserName',title:'发送人',width:10},
		                    {field:'acceptDeptName',title:'接收科室',width:10},	
		                    {field:'acceptUserName',title:'接收人',width:10},	
		                    {field:'content',title:'会话概要',width:10},
							{field:'sendTime',title:'发送时间',width:10},
							{field:'msgType',title:'msgType',hidden:true},
							{field:'caseId',title:'caseid',hidden:true},
							{field:'zyid',title:'zyid',hidden:true},
							{field:'mzid',title:'mzid',hidden:true},
							{field:'isRead',title:'isRead',hidden:true},
							{field:'themeId',title:'themeId',hidden:true},
							{field:'theme',title:'theme',hidden:true},
							{field:'mid',title:'mid',hidden:true},
		                    {field:'_operate',title:'操作',
								formatter:function(value,r){
									if(r.msgType==0 || r.msgType==1){
										return ['<a href="javascript:operation.reply(\''+r.zyid+'\',\''+r.mzid+'\',\''+r.themeId+'\',\'会话\',\''+r.sendUserId+'\');" class="btn_icon" title="会话"><i class="icon iconfont">&#xe625;</i></a>'].join('');	
									}else if(r.msgType==2){
										if(r.caseId){
											return ['<a href="javascript:operation.checkBL(\''+r.zyid+'\',\''+r.mzid+'\',\''+r.caseId+'\',\''+r.mid+'\',\''+r.themeId+'\',\''+r.isRead+'\');" class="btn_icon" title="查看"><i class="icon iconfont">&#xe625;</i></a>'].join('');	
										}else{
											return ['<a href="javascript:mark.markAsRead(\''+r.mid+'\',\''+r.themeId+'\',\''+r.isRead+'\');" class="btn_icon" title="知道了"><i class="icon iconfont">&#xe625;</i></a>'].join('');	
										}
									}else if(r.msgType==3){
										return ['<a href="javascript:operation.check(\'zybl\',\''+r.caseId+'\',\''+r.mid+'\',\''+r.themeId+'\',\''+r.isRead+'\');" class="btn_icon" title="查看"><i class="icon iconfont">&#xe625;</i></a>'].join('');	
									}else if(r.msgType==4){
										return ['<a href="javascript:operation.check(\'hjjc\',\''+r.caseId+'\',\''+r.mid+'\',\''+r.themeId+'\',\''+r.isRead+'\');" class="btn_icon" title="查看"><i class="icon iconfont">&#xe625;</i></a>'].join('');	
									}else if(r.msgType==6){
										//operation.remind();
										return "";
									}else if(r.msgType==7){
										//
										return ['<a href="javascript:operation.jkRemind(\''+r.mid+'\',\''+r.themeId+'\',\''+r.isRead+'\');" class="btn_icon" title="查看"><i class="icon iconfont">&#xe625;</i></a>'].join('');	
									}else if((r.msgType>=8 && r.msgType<=35) || (r.msgType>=100 && r.msgType<210)){
										//crbbks
										var type = "",title="";
										
										if(r.msgType==8 || r.msgType==15 || r.msgType==22 || r.msgType==29){
											type = "crbbk"; title = "传染病报卡详情";
										}else if(r.msgType==9 || r.msgType==16 || r.msgType==23 || r.msgType==30){
											type = "/cdc/f_view/deathReport.shtml"; title = "死因报卡详情";
										}else if(r.msgType==10 || r.msgType==17 || r.msgType==24 || r.msgType==31){
											type = "/cdc/f_view/fsaReport.shtml"; title = "食源异常报卡详情";
										}else if(r.msgType==11 || r.msgType==18 || r.msgType==25 || r.msgType==32){
											type = "/cdc/f_view/fsmReport.shtml"; title = "食源监测报卡详情";
										}else if(r.msgType==12 || r.msgType==19 || r.msgType==26 || r.msgType==33){
											type = "/cdc/f_view/tumourReport.shtml"; title = "肿瘤病例报卡详情";
										}else if(r.msgType==13 || r.msgType==20 || r.msgType==27 || r.msgType==34){
											type = "/cdc/f_view/hcvReport.shtml"; title = "心脑血管报卡详情";
										}else if(r.msgType==14 || r.msgType==21 || r.msgType==28 || r.msgType==35){
											type = "/cdc/f_view/sunstrokeReport.shtml"; title = "高温中暑报卡详情";
										}else if(r.msgType==100 || r.msgType==130 || r.msgType==160 || r.msgType==190){
											type = "/cdc/f_view/nyzdReport.shtml"; title = "农药中毒报卡详情";
										}
										return ['<a href="javascript:operation.checkCDC(\''+type+'\',\''+title+'\',\''+r.zyid+'\',\''+r.mzid+'\',\''+r.caseId+'\',\''+r.mid+'\',\''+r.themeId+'\',\''+r.isRead+'\');" class="btn_icon" title="查看"><i class="icon iconfont">&#xe625;</i></a>'].join('');	
									}else if(r.msgType==36){
										return ['<a href="javascript:operation.dnCheck(\''+r.mid+'\',\''+r.themeId+'\',\''+r.caseId+'\',\''+r.isRead+'\');" class="btn_icon" title="查看"><i class="icon iconfont">&#xe625;</i></a>'].join('');	
									}else{
										return ['<a href="javascript:operation.showAll(\''+r.mid+'\',\''+r.themeId+'\',this,\''+r.isRead+'\');" class="btn_icon" title="知道了"><i class="icon iconfont">&#xe625;</i></a>'].join('');	
									}
										//return ['<a href="javascript:nyMessageTheme.edit(\'',r.zyid,'\',\'',r.mzid,'\',\'',r.themeId,'\',\'会话\',\'',r.lastSendUserId,'\');" class="btn_icon" title="会话"><i class="icon iconfont">&#xe625;</i></a>'].join('');							
								}
							}
		                ]
	                ],
	                pagination:true,
	                toolbar:'#tb'
	            });
			});
			
			
			var operation = {
					reply : function(zyid,mzid,themeId,title,lastSendUserId){
						//回复
				    	if(zyid===undefined) zyid = '';
				        Comm.dialogGlobal({
				        	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?themeId="+themeId+"&userId="+lastSendUserId,
				            title: title,
				            width:870,
				            height:565,
				            type:"iframe",
				            parent:this
				        });
					},
					check : function(type,caseId,mid,themeid,isRead){
						//已读
						if(isRead!=1){
							mark.markAsRead(mid,themeid);
						}
						//查看
						if(type=="zybl"){
							location.href="${webroot}/bl002Sjdj/f_view/toedit.shtml?id="+caseId+"&ownership=clinical&tabBodyId=tab_body_undefined&menuNo=undefined"+"#3";
							//menuInfo.clickMenu("职业暴露登记表","/bl002Sjdj/f_view/toedit.shtml?id="+caseId,true,"","","","#3");
							//messageBox.hideMessageBox();
						}else if(type=="hjjc"){
							$.ajax({
								url:"${webroot}/hw102Jcdmx/f_json/getDjIdById.shtml",
								data:{"id":caseId},
								success:function(data){
									if(data){
										Comm.dialogGlobal({
								        	url:"${webroot}/hw101Jcdj/f_view/toHygieneEdit.shtml?type=1&djId=" + data.replace(/\"/g,""),
								            title: "修改监测报告单",
								            width:970,
								            height: 600,
								            type:"iframe",
								            parent:this
								        });
									}
								},error:function(){
									
								}
							});
						}
					},
					checkBL : function(zyid,mzid,caseId,mid,themeid,isRead){
						//已读
						if(isRead!=1){
							mark.markAsRead(mid,themeid);
						}
						//查看
						if(caseId){
							//
							location.href ="${webroot}/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&relid="+caseId+"&ownership=clinical&tabBodyId=tab_body_undefined&menuNo=undefined";
							//menuInfo.clickMenu("报卡详情","/bk001Sbk/f_view/toReportCards.shtml?isSeparatePage=1&relid="+caseId,true);
							//messageBox.hideMessageBox();
						}
					},
					checkCDC : function(type,title,zyid,mzid,caseId,mid,themeid,isRead){
						//已读
						if(isRead!=1){
							mark.markAsRead(mid,themeid);
						}
						//查看
						if(caseId){
							if(type=="crbbk"){
								if(zyid){
									location.href ="${webroot}/cdc/f_view/reportCardZY.shtml?zyid="+zyid+"&masterid="+caseId+"&ownership=clinical&tabBodyId=tab_body_undefined&menuNo=undefined";
									//menuInfo.clickMenu(title,"/cdc/f_view/reportCardZY.shtml?zyid="+zyid+"&masterid="+caseId,true);
								}else{
									location.href ="${webroot}/cdc/f_view/reportCardMZ.shtml?mzid="+mzid+"&masterid="+caseId+"&ownership=clinical&tabBodyId=tab_body_undefined&menuNo=undefined";
									//menuInfo.clickMenu(title,"/cdc/f_view/reportCardMZ.shtml?mzid="+mzid+"&masterid="+caseId,true);
								}
							}else{
								location.href ="${webroot}"+type+"?zyid="+zyid+"&mzid="+mzid+"&masterid="+caseId+"&ownership=clinical&tabBodyId=tab_body_undefined&menuNo=undefined";
								//menuInfo.clickMenu(title,type+"?zyid="+zyid+"&mzid="+mzid+"&masterid="+caseId,true);
							}
							//messageBox.hideMessageBox();
						}
					},
					dnCheck : function(mid,themeid,caseId,isRead){
						//已读
						if(isRead!=1){
							mark.markAsRead(mid,themeid);
						}
						if(caseId){
							location.href ="${webroot}/xn011Dclymx/f_view/index.shtml?testOrderNo="+caseId+"&ownership=clinical&tabBodyId=tab_body_undefined&menuNo=undefined";
							//menuInfo.clickMenu("检出菌搜索","/xn011Dclymx/f_view/index.shtml?testOrderNo="+caseId,true);
						} 
					},
					remind : function(){
						
					},
					jkRemind : function(mid,themeid,isRead){
						//已读
						if(isRead!=1){
							mark.markAsRead(mid,themeid);
						}
						//MsgPagination.queryMsg();
						nyMessageTheme.query();
					},
					showAll :function(mid,themeid,ele,isRead){
						//已读
						if(isRead!=1){
							mark.markAsRead(mid,themeid);
						}
						Comm.dialog({
							title:"消息详情",
							content:content
						});
					}
			}
			 var mark = {
		       		markAsRead : function(mid,themeId){
		       			$.ajax({
		       				url:"${webroot}/nyMessageBox/f_json/markAsRead.shtml",
		       				data:{
		       					mids:mid,themeIds:themeId
		       				},
		       				success:function(data){
		       					data = eval("("+data+")");
		       					if(data.result=="error"){
		       						$.messager.show({ title: '提示', msg: data.msg });
		       					}else{
		       						//$.messager.show({ title: '提示', msg: data.msg });
			       					//MsgPagination.queryMsg();
		       						nyMessageTheme.query();
		       					}
		       				},error:function(){
		       					alert("抱歉，更新已读信息失败！");
		       				}
		       			});
		       		},
		       		markAllAsRead : function(){
		       			//标记本页
		       			var mids = "";
		       			var themeIds = "";
		       			$("#MsgPlace").find(".message_li").each(function(index){
		       				mids += $(this).find(".mid").val()+"|";
		       				themeIds += $(this).find(".themeId").val()+"|";
		       			});
		       			//
		       			if(mids.length>10){
		       				mids = mids.substring(0, mids.length-1);
		       			}
		       			if(themeIds.length>10){
		       				themeIds = themeIds.substring(0, themeIds.length-1);
		       			}
		       			//
		       			if(mids.length>10){
			       			mark.markAsRead(mids, themeIds);
		       			}else{
		       				$.messager.show({ title: '提示', msg: '消息盒子空了！' });
		       			}
		       		},
		       		markAllAsRead2 : function(){
		       			
		       		}
		       	}
		</script>
	</body>
</html>
