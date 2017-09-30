<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>职业暴露登记</title>
	<%@ include file="/WEB-INF/view/core/include.jsp"%>
	<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
	<style>
		.easyui-combobox{width:75px;}
		.table_cx .easyui-combobox{width:150px;}
	</style>
</head>
<body class="easyui-layout" style="border:0px">
	<div data-options="region:'west',border:false,title:'查询条件'" style="width:250px;">
		<div class="easyui-layout" data-options="fit:true">              
			<div data-options="region:'center',border:false">
				<table class="table_cx" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td class="t_title">时间选择：</td>
							<td>
								<select name="dateType" id="dateType"  class="easyui-combobox" data-options="editable:false,value:'${param.dateType}'" style="width:120px;">
									<option value="1">登记时间</option>
									<option value="2">暴露时间</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="t_title">开始时间：</td>
							<td>
								<input type="text" id="startDate" value="${param.startDate}" style="width:108px"  class="Wdate text" onclick="WdatePicker()"  />
							</td>
						</tr>
						<tr>
							<td class="t_title">结束时间：</td>
							<td>
								<input type="text" id="endDate" value="${param.endDate}" style="width:108px" class="Wdate text" onclick="WdatePicker()" />
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">当前进度：</td>
							<td>
		<%-- 						<nis:select id="sjState" dictcode="bl_status" value="${param.sjState}"   cssCls="easyui-combobox" exp="style='width: 120px;'" headerKey="" headerValue="--状态--" /> --%>
								<nis:select id="sjState" dictcode="bl_status" cssCls="easyui-combobox" exp="style='width: 120px;' data-options='multiple:true,value:\"${param.sjState}\"'" headerKey="" headerValue="--状态--" />
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">暴露者：</td>
							<td>
							<input type="text" id="searchString" class="auto-tip" data-tip="员工姓名或工号" style="width: 108px;"/>	
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">暴露科室：</td>
							<td>
							<input id="dep" name="djDept" style="width:120px" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#dep').combo('clear');"></a>
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">暴露方式：</td>
							<td>
							<nis:select id="wtjg34" dictcode="bl_mode"  cssCls="easyui-combobox" headerKey="" headerValue="--暴露方式--"  exp="style=\"width: 120px;\""/>
							</td>
						</tr>
						<tr>
							<td class="t_title">来源情况：</td>
							<td>
							<nis:select id="sjBlqk" dictcode="bl_source_type"  cssCls="easyui-combobox" headerKey="" headerValue="--暴露源情况--"  exp="style=\"width: 120px;\""/>
							</td>
						</tr>
						<tr>
							<td class="t_title">暴露级别：</td>
							<td>
								<nis:blselect id="blGrade" csmId="019"   cssCls="easyui-combobox" headerKey="" headerValue="--暴露级别--"  exp="style=\"width: 120px;\""/>	
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">发生地点：</td>
							<td>
								<nis:blselect id="enterAdd" csmId="002" cssCls="easyui-combobox" headerKey="" headerValue="--发生地点--"  exp="style=\"width: 120px;\""/>	
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">结论诊断：</td>
							<td>
								<nis:blselect id="blJl" csmId="033" cssCls="easyui-combobox" headerKey="" headerValue="--结论诊断--"  exp="style=\"width: 120px;\""/>	
							</td>
						</tr>
						<tr><td height="5"></td><td></td></tr>
						<tr>
							<td class="t_title">岗位：</td>
							<td>
								<nis:blselect id="djGw" csmId="001" name="djGw" value="${bl002Sjdj.djGw}" cssCls="easyui-combobox" headerKey="" headerValue="--岗位--" exp="style='width:120px;'" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div data-options="region:'south',border:false" style="height:50px; border-top-width:1px;">	         		
				<div class="btn_center">
					<div class="n_btn_blue">
						<a href="javascript:;" onclick="bl002Sjdj.query()"  ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
					</div>		
				</div>				
			</div>
		</div>			
	</div>

	<div data-options="region:'center',border:false" style="overflow:hidden;border-left-width: 1px;">
		<div class="easyui-layout" data-options="fit:true">   
			<c:if test="${roleType!='clinical'}">
			<div data-options="region:'south',border:false" style="height:240px; border-top-width:1px;" >
					<div id="fcrPanel"></div>	
			</div>
			</c:if>
			<div data-options="region:'center',border:false" style="border-top-width:1px;" >
				<div id="bl002SjdjPanel" ></div>	
				<div id="tb" class="m_search">
					<div class="m_search_last">					
						
						<div class="btn_r">
							<div class="n_btn_grey">
								<a href="javascript:;" onclick="bl002Sjdj.toReport()"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
							</div>				
						</div>
						<div class="btn_r">
							<div class="n_btn_blue">
								<a href="javascript:;" onclick="bl002Sjdj.add('','登记职业暴露')"><i class="icon iconfont">&#xe658;</i><span>登记</span></a>
							</div>	
						</div>		
					</div>
				</div>
			</div>
		</div>
	</div>
		<script type="text/javascript">
			var bl002Sjdj = {
				panel : 'bl002SjdjPanel',
				//查询
				query : function() {
					autoTip.clear();
					//去除空的选项
					$('#sjState').combobox('unselect','');
			        $('#'+bl002Sjdj.panel).datagrid({
			            queryParams: {
			                'dateType': $('#dateType').combogrid('getValue'),
			                'queryStartDate': $('#startDate').val(),
			                'queryEndDate': $('#endDate').val(),
			                'searchString': $('#searchString').val(),
			                'blGrade': $('#blGrade').combogrid('getValue'),
			                'blSgrade': $('#blSgrade').val(),
			                'sjBlqk': $('#sjBlqk').combogrid('getValue'),
			                'wtjg34':$('#wtjg34').combogrid('getValue'),
			                'enterAdd':$('#enterAdd').combogrid('getValue'),
			                'blJl':$('#blJl').combogrid('getValue'),
			                'sjState': $('#sjState').combobox('getValues').toString()  ,
			                'djDept' : $('#dep').combogrid('getValue'),
			                'djGw' : $("#djGw").combobox('getValue')
			            },
			            pageNumber: 1
			        });
			    },
			    exportExcel:function(){
			    	autoTip.clear();
			    	//去除空的选项
					$('#sjState').combobox('unselect','');
			    	var dateType= $('#dateType').combogrid('getValue');
			    	var startDate= $('#startDate').val();
			    	var endDate= $('#endDate').val();
			    	var searchString= $('#searchString').val();
			    	var blGrade= $('#blGrade').combogrid('getValue');
			    	var sjBlqk= $('#sjBlqk').combogrid('getValue');
			    	var wtjg34=$('#wtjg34').combogrid('getValue');
			    	var enterAdd=$('#enterAdd').combogrid('getValue');
			    	var blJl=$('#blJl').combogrid('getValue');
			    	var sjState= $('#sjState').combobox('getValues') ;
			    	var djDept= $('#dep').combogrid('getValue');
  					var url = "${webroot}/bl002Sjdj/f_json/exportExcel.shtml?dateType="+dateType+"&startDate="+startDate+"&endDate="+endDate+"&searchString="+searchString+"&blGrade="+blGrade+"&sjBlqk="+sjBlqk+"&wtjg34="+wtjg34+"&enterAdd="+enterAdd+"&blJl="+blJl+"&sjState="+sjState+"&djDept="+djDept;
  			    	window.location.href = url;
			    },
			    toReport:function(){
			    	autoTip.clear();
			    	//去除空的选项
					$('#sjState').combobox('unselect','');
			    	var dateType= $('#dateType').combogrid('getValue');
			    	var startDate= $('#startDate').val();
			    	var endDate= $('#endDate').val();
			    	var searchString= $('#searchString').val();
			    	var blGrade= $('#blGrade').combogrid('getValue');
			    	var sjBlqk= $('#sjBlqk').combogrid('getValue');
			    	var wtjg34=$('#wtjg34').combogrid('getValue');
			    	var enterAdd=$('#enterAdd').combogrid('getValue');
			    	var blJl=$('#blJl').combogrid('getValue');
			    	var sjState= $('#sjState').combobox('getValues') ;
			    	var djDept= $('#dep').combogrid('getValue');
  					var url = "${reportUrl}"+"nis7/ZYBL_HZ.cpt&dateType="+dateType+"&startDate="+startDate+"&endDate="+endDate+"&searchString="+searchString+"&blGrade="+blGrade+"&sjBlqk="+sjBlqk+"&wtjg34="+wtjg34+"&enterAdd="+enterAdd+"&blJl="+blJl+"&sjState="+sjState+"&djDept="+djDept+"&__bypagesize__=false";
  					window.open(url);
			    },
			    //编辑
			    add:function(title) {
			        Comm.dialogGlobal({
			        	url:"${webroot}/bl002Sjdj/f_view/tonote.shtml",
			            title: "注意事项",
			            width:850,
			            height:600,
			            type:"iframe",
			            parent:this
			        });
			    },
			    //编辑
			    edit:function(id, title,blStep) {
					parent.menuInfo.clickMenu('职业暴露登记表','/bl002Sjdj/f_view/toedit.shtml?id=' + id+'',true,'','','','#'+blStep);
			    },
			    //打印
			    print:function(id, title) {
			    	var url = '${reportUrl}nis7/ZYBL.cpt&__filename__=' + encodeURI(encodeURI(encodeURI('职业暴露打印'))) + '&__bypagesize__=false&bl_id=' + id;
			    	window.open(url);
			    },
			    //删除
			    del:function(id) {
			    	$.messager.confirm('提示', '确认删除该职业暴露项目?', function (r) {
			        	if (r) {
			            	$.ajax({
			                        url: '${webroot}/bl002Sjdj/f_json/delete.shtml',
			                        type: 'post',
			                        data: { blId: id},
			                        dataType: 'json',
			                        success : function(json) {
										if(json.result==='success') {
											bl002Sjdj.query();
			                                $.messager.show({ title: '提示', msg: '删除成功！' });
								    	} else if(json.result === 'error') {
								    		$.messager.show({ title: '提示', msg: '系统异常！' });
								    	} else {
								    		$.messager.show({ title: '提示', msg: json.msg });
								    	}
									}
			            	});
			        	}
			    	});
			    }			  
			};
			$(document).ready(function () { 
				Csm.combogrid.dep({
					//【必传】控件名称
					id: 'dep'
					//callback: '0',
				});
				
	            autoTip.clear();
	          	//去除空的选项
				$('#sjState').combobox('unselect','');
				$('#'+bl002Sjdj.panel).datagrid({
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                fitColumns: true,
	                <c:choose>
		                <c:when test="${roleType == 'clinical'}">
		                fit:true,
						</c:when>
		                <c:otherwise>
		                height:430,
		                </c:otherwise>
					</c:choose>
	                collapsible:true,
	                url:'${webroot}/bl002Sjdj/f_json/pageQuery.shtml',
	                queryParams: {
	                	'dateType': $('#dateType').combogrid('getValue'),
		                'queryStartDate': $('#startDate').val(),
		                'queryEndDate': $('#endDate').val(),
		                'searchString': $('#searchString').val(),
		                'blGrade': $('#blGrade').combogrid('getValue'),
		                'blSgrade': $('#blSgrade').val(),
		                'sjBlqk': $('#sjBlqk').combogrid('getValue'),
		                'wtjg34':$('#wtjg34').combogrid('getValue'),
		                'enterAdd':$('#enterAdd').combogrid('getValue'),
		                'blJl':$('#blJl').combogrid('getValue'),
		                'sjState': $('#sjState').combogrid('getValues').toString() ,
		                'djDept' : $('#dep').combogrid('getValue'),
		                'djGw' : $("#djGw").combobox('getValue')
		            },
	                remoteSort: false,
	                singleSelect: true,
	                border:false,
	                columns:[
	                	[
		                    {field:'djName',title:'暴露者姓名',sortable:true,width:80},
		                    {field:'showDjDept',title:'暴露科室',sortable:true,width:120},
		                    {field:'enterAddName',title:'发生地点',sortable:true,width:80},
		                    {field:'enterTime',title:'暴露时间',sortable:true,width:120},
		                    {field:'showBlSgrade',title:'危险程度',sortable:true,width:80},
		                    {field:'wtjg34',title:'暴露方式',sortable:true,width:100},
		                    {field:'wtjg185',title:'暴露级别',sortable:true,width:80},
		                    {field:'djMen',title:'登记人',sortable:true,width:80},
		                    {field:'djTime',title:'登记时间',sortable:true,width:100},
							{field:'showSjState',title:'状态',sortable:true,width:60},
 		                    {field:'_operate',title:'操作',width:115,
 								formatter:function(value,r){
 									<c:choose>
 									<c:when test="${roleType == 'clinical' }">
 									 if(r.sjState=='0'||r.sjState=='2'){ 
  										return ['<a href="javascript:bl002Sjdj.edit(\'',$.trim(r.blId),'\',\'修改\',\'',$.trim(r.blStep),'\');" class="ico_editor" title="修改"></a>',
  										        '<a href="javascript:bl002Sjdj.print(\'',$.trim(r.blId),'\',\'打印\');" class="ico_view" title="打印"></a>',
  			 									'<a href="javascript:bl002Sjdj.del(\'',$.trim(r.blId),'\');" class="ico_del" title="删除"></a>'].join('');
   									 }else{
  										return ['<a href="javascript:bl002Sjdj.edit(\'',$.trim(r.blId),'\',\'修改\',\'',$.trim(r.blStep),'\');" class="ico_editor" title="修改"></a>',
  										        '<a href="javascript:bl002Sjdj.print(\'',$.trim(r.blId),'\',\'打印\');" class="ico_view" title="打印"></a>'].join('');
  									} 
 									</c:when>
 									<c:otherwise>
 									return ['<a href="javascript:bl002Sjdj.edit(\'',$.trim(r.blId),'\',\'修改\',\'',$.trim(r.blStep),'\');" class="ico_editor" title="修改"></a>',
										        '<a href="javascript:bl002Sjdj.print(\'',$.trim(r.blId),'\',\'打印\');" class="ico_view" title="打印"></a>',
			 									'<a href="javascript:bl002Sjdj.del(\'',$.trim(r.blId),'\');" class="ico_del" title="删除"></a>'].join('');
 									</c:otherwise>
 									</c:choose>
 									
 								}
 							}
		                ]
	                ],
	                pagination:true,
	                rownumbers:true,
	                toolbar:'#tb'
	            });
				
				$("#fcrPanel").datagrid({
					title:"近${days}天需要复查人员",
					fit:true,
	                nowrap: true,
	                autoRowHeight: false,
	                striped: true,
	                collapsible:true,
	                singleSelect: true,
	                fitColumns: true,
	                url:'${webroot}/bl007Fcsj/f_view/list.shtml',
	                columns:[
	 	                [
							{field:'blId',title:'blId',hidden:true},
	 		               {field:'djName',title:'姓名',sortable:true,width:80},
	 		               {field:'djDept',title:'暴露科室',sortable:true,width:120},
	 		               {field:'enterTime',title:'暴露时间',sortable:true,width:140},
	 		               {field:'itemName',title:'检查项目',sortable:true,width:120},
	 		               {field:'fc',title:'复查时间',sortable:true,width:120},
	 		               {field:'beforFc',title:'上次复查时间',width:120},
	 		               {field:'fcZt',title:'操作',width:120,formatter:function(value,row,index){
	 		            	   if(value=="1"){
	 		            		   return "已复查";
	 		            	   }else{
	 		            		   return "";
	 		            	   }
	 		               }}
	 		            ]
	 	            ],
	                rownumbers:true	                
				});
			});
			
		</script>
	</body>
</html>
