<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<style>
	body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;overflow: hidden;}
</style>
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<head>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
</head>
<body>
<div class="easyui-layout" style="width: 100%;height: 100%;">
	<div data-options="region:'west',border:false,title:''" style="width:270px; border-right-width: 1px;">
	<form id="searchform${id}" method="post" action="${reportUrl}${reportFile}.cpt&__bypagesize__=${__bypagesize__}" target="reportFrame${id}">
	<input type="hidden" id="userType" name="userType" value="${account.userType}" />
	<input type="hidden" id="doctorId" name="doctorId" value="${account.doctorId}" />
	<input type="hidden" id="scopeInfo" name="scopeInfo" value="${account.scopeInfo}" />
	<input type="hidden" id="dataScope" name="dataScope" value="${account.dataScope}" />
	<input type="hidden" id="urlPrefix" name="urlPrefix" value="${nisUrl}" />
	<table class="table_cx" cellpadding="0" cellspacing="0">
		<tbody>
			<c:if test="${id!='xj9'}">
				<tr>
					<td class="t_title">时间选择：</td>
					<td>
						<select style="width:150px" id="dateType" name="dateType" class="easyui-combobox">
							<option value="2" <c:if test="${param.dateType=='2'}"> selected</c:if>>检出时间</option>
							<option value="1" <c:if test="${param.dateType=='1'}"> selected</c:if>>送检时间</option>
						</select>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="t_title">开始时间：</td>
				<td>
					<input type="text" id="startDate${id}" name="startDate" value="${startDate}" style="width:138px"  class="Wdate text" onclick="WdatePicker({skin:'whyGreen','dateFmt':'yyyy-MM-dd',onpicked:function(dq){updateJcj();},oncleared:function(dq){updateJcj();}})"/>
				</td>
			</tr>
			<tr>
				<td class="t_title">结束时间：</td>
				<td>
					<input type="text" id="endDate${id}" name="endDate" value="${endDate}" style="width:138px" class="Wdate text" onclick="WdatePicker({skin:'whyGreen','dateFmt':'yyyy-MM-dd',onpicked:function(dq){updateJcj();},oncleared:function(dq){updateJcj();}})"/>
				</td>
			</tr>
			<c:if test="${id!='xj3' and id!='xj15' and id!='xj8' and id!='xj9' and id!='xj11' and id!='xj12' and id!='xj14'}">
				<tr>
					<td class="t_title">标本筛选：</td>
					<td>
						<select id="bbsx${id}"  class="easyui-combobox" style="width:152px"></select>
						<input type="hidden" id="bbsxName${id}" name="bbsx">
					</td>
				</tr>
			</c:if>
			<c:if test="${id!='xj3' and id!='xj15' and id!='xj4' and id!='xj5' and id!='xj8' and id!='xj9' and id!='xj11' and id!='xj12' and id!='xj14'}">
				<tr>
					<td class="t_title">感染类型：</td>
					<td>
						<select id="gr_type${id}"  class="easyui-combobox" style="width:152px"></select>
						<input type="hidden" id="grTypeName${id}" name="grType">
					</td>
				</tr>
			</c:if>
			<c:if test="${id!='xj3' and id!='xj15' and id!='xj7' and id!='xj8' and id!='xj9' and id!='xj11' and id!='xj14'}">
				<tr>
					<td class="t_title">检出菌类：</td>
					<td>
					<select style="width:152px" id="pathoType${id}" name="pathoType" class="easyui-combobox"><option value="">全部</option><option value="z">重点菌</option><option value="f">非重点菌</option></select>
					</td>
				</tr>
			</c:if>
			<c:if test="${id!='xj3' and id!='xj15' and id!='xj8' and id!='xj9' and id!='xj11' and id!='xj12' and id!='xj14'}">
				<tr>
					<td class="t_title">革兰氏类：</td>
					<td>
					<select style="width:152px" id="rsId${id}" name="rsId" class="easyui-combobox"><option value="">全部</option><option value="1">G+</option><option value="2">G-</option></select>
					</td>
				</tr>
			</c:if>
			<c:if test="${id=='xj3'}">
				<tr>
					<td colspan="2">医院感染致病菌：
						<label><input type="checkbox" id="grByt${id}" name="grByt" value="1"/>是</label>
					</td>
				</tr>
			</c:if>
			<c:if test="${id=='xj3' or id=='xj8' or id=='xj11' or id=='xj12' or id=='xj14'}">
				<tr>
					<td class="t_title">检出菌：</td>
					<td>
					<div class="select_del"><input id="byt${id}" name="pathogenId" style="width:150px"/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#byt${id}').combo('clear');"></a></div>
					</td>
				</tr>
			</c:if>
			<c:if test="${id=='xj12'}">
				<tr>
					<td class="t_title">抗菌药物：</td>
					<td>
					<div class="select_del"><input id="kjyw${id}" name="kjyw" style="width:150px"/><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#kjyw${id}').combo('clear');"></a></div>
					</td>
				</tr>
			</c:if>
			<c:if test="${id=='xj11' or id=='xj12' or id=='xj14'}">
				<tr>
					<td class="t_title">统计维度：</td>
					<td>
					<select style="width:152px" id="statType${id}" name="statType" class="easyui-combobox">
						<option value="1">月度</option>
						<option value="2">季度</option>
						<option value="3">年度</option>
					</select>
					</td>
				</tr>
			</c:if>
			<c:if test="${id=='xj12'}">
				<tr>
					<td class="t_title">包括中介：</td>
					<td>
					<label><input type="checkbox" id="zj${id}" name="zj" value="1"/>是</label>
					</td>
				</tr>
			</c:if>
			<c:if test="${id!='xj3' and id!='xj15' and id!='xj9' and id!='xj11' and id!='xj12' and id!='xj14'}">
				<tr>
					<td class="t_title">剔除重复：</td>
					<td>
						<label><input type="checkbox" id="sn${id}" name="sn" value="1"/>是</label>
						<span class="ico_help"></span>
					</td>
				</tr>
			</c:if>
			<c:if test="${id=='xj4'}">
				<tr>
					<td class="t_title" colspan='2'>显示标本检出数排前
					<label><input type="text" id="rn${id}" name="rn" style="width:50px;text-align:right" value="5"/>病原体</label>
					</td>
				</tr>
			</c:if>
			<c:if test="${id=='xj10' or id=='xj13'}">
				<tr>
					<td class="t_title" colspan='2'>显示科室检出数排前
					<label><input type="text" id="rn${id}" name="rn" style="width:50px;text-align:right" value="5"/>标本</label>
					</td>
				</tr>
			</c:if>
			<c:if test="${id=='xj3'}">
				<tr>
					<td class="t_title">耐药率：</td>
					<td>
					<label><input type="text" id="mdrMin${id}" name="mdrMin" style="width:50px;text-align:right"/></label>
					至
					<label><input type="text"  id="mdrMax${id}" name="mdrMax" style="width:50px;text-align:left"/></label>
					</td>
				</tr>
			</c:if>
			<c:if test="${unitFlag=='1'}">
			<tr>
				<td class="t_title">院区：</td>
				<td>
					<div class="select_del">
					<input id="unitId${id}" name="unitId" style="width:150px;"/>
					<a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#unitId${id}').combo('clear');"></a>
					</div>
				</td>
			</tr>
			</c:if>
			<tr>
				<td class="t_title">科室类型：</td>
				<td>
					<nis:select id="deptType" name="deptType" exp="style='width: 150px;'" dictcode="stat_dept_type"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="easyui-panel" style="padding:5px;width:220px;height:200px;<c:if test="${deptFlag=='0' and roleType=='clinical'}">display:none;</c:if>">
						<span>送检科室：</span>
						<ul id="ksTree${id}"></ul>
					</div>
					<input type="hidden" id="statDept${id}" name="statDept" />
					<input type="hidden" id="statDeptName${id}" name="deptName" />
					<input type="hidden" id="statDeptClass${id}" name="statDeptClass" />
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">	
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:query${id}();"><i class="icon iconfont">&#xe61e;</i><span>统计</span></a>
			</div>
		</div>
	</div>	
	</form>
	</div>
	<div data-options="region:'center',border:false" >
		<iframe width="100%" height="99%" id="reportFrame${id}" name="reportFrame${id}" allowtransparency scrolling="no" frameborder="0"></iframe>
	</div>
	</div>
		<script type="text/javascript">
		/**剔除重复规则显示**/
		$(function(){
            $('.ico_help').tooltip({
                position: 'right',
                content: '<p style="color:#333; width:300px; padding:10px;"><b>送检次数：</b>统计时间内，同一科室、同一患者、送检相同标本，只算一次送检；<br/><b>检出次数：</b>统计时间内，同一科室、同一患者、同一送检标本、检出相同细菌，只算一次检出；<br/><b>多耐次数：</b>统计时间内、同一科室、同一患者、同一送检标本、检出相同细菌、且均为多耐，只算最早检出的那一株。</p>',
                onShow: function(){
                    $(this).tooltip('tip').css({
                        backgroundColor: '#fafafa',
                        borderColor: '#ccc'
                    });
                }
            });
		 });
		
		function updateJcj(){
			$('#byt${id}').combogrid({
				url: '${webroot}/xn002Byt/f_view/queryCount.shtml?startDate='+$('#startDate${id}').val()+'&endDate='+$('#endDate${id}').val()+'&page=1&size=200'
			});
		};
		
		function query${id}() {
			if(!saveDate${id}()){
				return;
			}
			<c:if test="${id!='xj3' and id!='xj15' and id!='xj8' and id!='xj9' and id!='xj11' and id!='xj12' and id!='xj14'}">
				var bbsx = $('#bbsx${id}').combobox('getValues');
				if(bbsx!=''){
					bbsx ="'"+$('#bbsx${id}').combobox('getValues')+"'";
					bbsx = bbsx.replace(/,/g,"','");
				}
				$('#bbsxName${id}').val(bbsx);		
			</c:if>
			<c:if test="${id!='xj3' and id!='xj15' and id!='xj4' and id!='xj5' and id!='xj8' and id!='xj9' and id!='xj11' and id!='xj12' and id!='xj14'}">
				$('#grTypeName${id}').val($('#gr_type${id}').combobox('getValues'));
			</c:if>
			<c:if test="${id=='xj11' or id=='xj12'}">
				if($('#byt${id}').combobox('getValues') == ''){
					$.messager.show({ title : '提示', msg : '此统计必须选择一个检出菌！' });
					return ;
				}
			</c:if>
			<c:if test="${id=='xj12'}">
				if($('#kjyw${id}').combobox('getValues') == ''){
					$.messager.show({ title : '提示', msg : '此统计必须选择一个抗菌药物！' });
					return ;
				}
			</c:if>
			<c:if test="${roleType=='clinical'}">
				if($('#statDept${id}').val() == ''){
					alert("请选择统计科室！");
					return;
				}
			</c:if>
			$('#searchform${id}').attr("action","${reportUrl}${reportFile}.cpt&__bypagesize__=${__bypagesize__}");
			/*if($("input[name='grByt']:checked").val() && '${id}'=='xj3'){
				$('#searchform${id}').attr("action","${reportUrl}${reportFile}_GR.cpt&__bypagesize__=${__bypagesize__}");
			}*/
			$('#searchform${id}').submit();
	    };
	    
		function saveDate${id}(){
			var startDate =  $('#startDate${id}').val();
			var endDate = $('#endDate${id}').val();
			if(startDate == ''){
				alert("请选择统计开始时间！");
				return false;
			}
			if(endDate == ''){
				alert("请选择统计结束时间！");
				return false;
			}
			var d1 = new Date(startDate.replace(/\-/g, "\/"));  
			var d2 = new Date(endDate.replace(/\-/g, "\/"));  
			if(startDate!=""&&endDate!=""&&d2<d1){  
				  alert("结束时间不能小于开始时间！");  
				  return false;  
			}
			if(startDate != "" && endDate != ""){
				$.ajax({
			     	type: "post",
			          url: "${webroot}/acAccountConfig/f_json/save.shtml",
			          data: {configKey:"reportStartDate",configValue:startDate}
			     });
				$.ajax({
			     	type: "post",
			          url: "${webroot}/acAccountConfig/f_json/save.shtml",
			          data: {configKey:"reportEndDate",configValue:endDate}
			     });
			}
			return true;
		}
			
			
		$(document).ready(function () {
			<c:if test="${unitFlag=='1'}">
				Csm.comboBox.unit({
					//【必传】控件名称
					id: 'unitId${id}',
					value: '',
					flag: '1',
					callback: '0'
				});
			</c:if>
			
			$('#byt${id}').combogrid({
				delay: 1000,    
			    mode: 'remote',
			    loadMsg : '正在查询中...',
		        idField:'pathogenId',
		        panelWidth: 180,
		        panelHeight: 300,
		        value : "${pathogenId}",
		        textField:'pathogenName',
				url: '${webroot}/xn002Byt/f_view/queryCount.shtml?startDate='+$('#startDate${id}').val()+'&endDate='+$('#endDate${id}').val()+'&page=1&size=200',
		        columns:[
		        	[
		             {field:'pathogenName',title:'名称',sortable:true,width:150},
		            ]
		        ],
		        onSelect : function(index, record) {
					<c:if test="${id=='xj12'}">
					$('#kjyw${id}').combogrid({
						delay: 1000,    
					    mode: 'remote',
					    loadMsg : '正在查询中...',
					    required:true,
				        idField:'drugId',
				        panelWidth: 250,
				        panelHeight: 300,
				        textField:'drugName',
						url: webroot + '/xn003Kjyw/f_view/query/byt.shtml?page=1&size=2000&pathogenId='+record.pathogenId,
				        columns:[
				        	[
				             {field:'drugId',title:'编号',sortable:true,width:60},  
				             {field:'drugName',title:'名称',sortable:true,width:150}
				            ]
				        ],
						onLoadSuccess:function(){
							var grid = $('#kjyw${id}').combogrid('grid');
							grid.datagrid('selectRow', 0);
						}
					});
					</c:if>
				}
			});

			<c:if test="${id=='xj12'}">
			Csm.combogrid.kjyw({
				//【必传】控件名称
				id: 'kjyw${id}',
				onLoadSuccess:function(){
					var grid = $('#kjyw${id}').combogrid('grid');
					grid.datagrid('selectRow', 0);
				}
			});
			</c:if>

			$('#gr_type${id}').combobox({
			    url:'${webroot}/sysDict/f_json/getValue.shtml?dictTypeCode=gr_type',
			    valueField:'dictCode',
			    textField:'dictName',
				<c:if test="${id=='xj6'}">
			    value:'${param.grType == ""?"1":param.grType}',
				</c:if>
			    multiple:true
			});
			
			$('#bbsx${id}').combobox({
			    url:'${webroot}/nyBbDict/f_json/queryList.shtml',
			    valueField:'bbid',
			    textField:'bbmc',
// 			    inField:'${code}',
		    	mode: 'remote',
			    value:'${code}',
			    multiple:true
			});

			<c:if test="${id=='xj3' or id=='xj8' or id=='xj14'}">
				$('#byt${id}').combo('clear');
			</c:if>
			
			<c:if test="${id eq 'xj4'}">
			$("#bbsx${id}").combobox("setValue","${code}");
			</c:if>

		    $("#ksTree${id}").tree({
				url:'${webroot}/report/json/getTreeRoot.shtml',
				animate:true,
				loadFilter : function(data) {   
					var c = new Array();
					c.push(data);
					return c;
				},
				onSelect:function(rec){
					if($('#ksTree${id}').tree('isLeaf',rec.target)){
						$("#statDept${id}").val(rec.id);
						$("#statDeptName${id}").val(encodeURI(rec.text));
						$("#statDeptClass${id}").val("");
					}else if(rec.id == 0){
						$("#statDept${id}").val("");
						$("#statDeptName${id}").val("");
						$("#statDeptClass${id}").val("");
					}else{
						$("#statDept${id}").val("");
						$("#statDeptName${id}").val(encodeURI(rec.text));
						$("#statDeptClass${id}").val(rec.id);
					}
				},
				onLoadSuccess:function(rec){
					//临床端默认选择本科室
					<c:if test="${roleType=='clinical'}">
						try{
						var node = $('#ksTree${id}').tree('find', '${curDeptId}');      //找到id为”tt“这个树的节点id为”1“的对象
						$('#ksTree${id}').tree('select', node.target);     //设置选中该节点
						}catch(e){}						
					</c:if>
					
					window.setTimeout(function() {
						query${id}();
					}, 200);
				}
			});
		});	
		
</script>

</body>
</html>