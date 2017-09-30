<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<script type="text/javascript" src="${webroot}/resources/js/pinyin.js"></script>
<form id="editFormAntibiosisDrug" method="post">
	<table class="table mb60" cellpadding="0" cellspacing="0" >
		<tbody>
			<tr>
				<td class="t_title" ><span class="red">*</span>抗菌药物编号：</td>
				<td><input type="text"  id="drugId" name="drugId" style="width: 138px;" <c:if test="${antibiosisDrug.drugId!=null and antibiosisDrug.drugId!=''}"> readonly="true"</c:if>
					value="<c:out value="${antibiosisDrug.drugId}" />" class="easyui-validatebox text" required="true"></td>
				
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>抗菌药物名称：</td>
				<td><input type="text"  id="drugName" name="drugName" style="width: 138px;"
					value="<c:out value="${antibiosisDrug.drugName}" />" class="easyui-validatebox text" required="true"></td>
			</tr>
			<tr>
				<td class="t_title"><span class="red">*</span>抗菌药物英文名：</td>
				<td>
					<input type="text"  id="drugEnname" name="drugEnname" style="width: 138px;" value="<c:out value="${antibiosisDrug.drugEnname}" />" class="easyui-validatebox text" required="true">
				</td>
			</tr>
			<tr>
				<td class="t_title">抗菌药物分类：</td>
				<td>
					<input name="drugTypeid" id="id_drugTypeid" style="width: 150px;">				
				</td>
			</tr>
			<tr>
				<td class="t_title">抗菌药物亚类：</td>
				<td>
					<input type="text" name="subClassid" id="id_subClassid" >				
				</td>
			</tr>
			<tr>
				<td class="t_title">拼音码：</td>
				<td colspan="3">
					<input type="text"  id="spCode" name="spCode" style="width: 138px;" value="<c:out value="${antibiosisDrug.spCode}" />" class="easyui-validatebox text" required="true">
				</td>
			</tr>
			<tr>
				<td class="t_title">属性设置：</td>
				<td colspan="3">
				    <label><input type="checkbox" <c:if test="${antibiosisDrug.ifCommon=='1'}">checked</c:if> id="ifCommon" name="ifCommon" value="1"/>固定统计</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <label><input type="checkbox" <c:if test="${antibiosisDrug.ifReport=='1'}">checked</c:if> id="ifReport" name="ifReport" value="1"/>常用过敏药物</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</tbody>
	</table>
	<div class="footer dialog_footer">
		<div class="footer_btn">
			<div class="n_btn_blue">
				<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#editFormAntibiosisDrug').submit()"  class="no_ico"><span>确&nbsp;认</span></a>
			</div>			
		</div>		
	</div>
</form>
<script>
	var antibiosisDrug = {
		kjywyl : function(drugTypeId){
			$('#id_subClassid').combogrid({
				delay: 1000,
// 			    required:true,
			    mode: 'remote',
		        idField:'SUBCLASSID',
		        textField:'SUBCLASSNAME',
		        panelWidth: 300,
		        panelHeight:260,
		        fitColumn : true,
 		       // value : '${antibiosisDrug.subClassid}',
		        url:'${webroot}/antibiosisDrug/f_json/findKjywyl.shtml?drugTypeid='+drugTypeId, 
		        columns:[
		        	[
			            {field:'SUBCLASSID',title:'亚类编号',sortable:true,align:'center',width:70},
			            {field:'SUBCLASSNAME',title:'亚类名称',sortable:true,align:'center',width:70},
			            {field:'PYCODE',title:'拼音码',sortable:true,align:'center',width:70},
			            {field:'WBCODE',title:'五笔码',sortable:true,align:'center',width:70}
		            ]
		        ],
		        onLoadSuccess :  function(data){
		        	if(data.total>0){
		        		if($('#id_drugTypeid').combobox('getValue')=='${antibiosisDrug.drugTypeid}'){
				        	$('#id_subClassid').combogrid('setValue','${antibiosisDrug.subClassid}');  
		        		}
		        	}
		        }
			});
		}
	};
	
	
	
	$(document).ready(function() {
		window.setTimeout(function() {
			$('#drugName').live('blur',function() {
				var drugName = $(this).pinyinFirstLower().toUpperCase();
				if(drugName.length > 10){
					drugName = drugName.substring(0,10);
				}
				$("#spCode").val(drugName);
			});
			//抗菌药物分类
			Csm.combogrid.kjywfl({
				id: 'id_drugTypeid',
				value:'${antibiosisDrug.drugTypeid}',
				required:true,
				onClickRow : function(index,row){
// 					$('#id_subClassid').combobox('clear');
					antibiosisDrug.kjywyl(row.drugTypeId);
				},
				onLoadSuccess : function(data){
					antibiosisDrug.kjywyl('${antibiosisDrug.drugTypeid}');
				}
			});
			Comm.form({
				id : 'editFormAntibiosisDrug',
				url : '${webroot}/antibiosisDrug/f_json/save.shtml',
				subbtn : 'changeFormSubmitBtn',
				success : function(json) {
					if (json.result === 'success') {
						$.messager.show({ title : '提示', msg : '操作成功！' });
						var parentObject = parent.Comm.getObjectCache();
						parentObject.query();
						Comm.dialogClose('${param.dialogId}');
					} else if(json.result === 'error') {
						$.messager.show({ title : '提示', msg : json.msg });
					} else {
						$.messager.show({ title : '提示', msg : json.msg });
					}
				}
			});
		}, 100);
	});
</script>