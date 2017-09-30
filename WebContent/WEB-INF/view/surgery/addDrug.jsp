<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>新增患者手术信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout" >
	<form method="post" id="id_form">
	<div data-options="region:'center',border:false"  class="byt_table">
		<input type="hidden" name="operTypeId" value="${st004Yzxxb.operTypeId}" />
		<input type="hidden" name="zyid" value="${st004Yzxxb.zyid}" />
		<input type="hidden" name="refid" value="${st004Yzxxb.refid}" />
		<div id="" class="m_search" style="padding:2px 5px;">
			<lable class="radioSpan"  style="float:left; padding-left:15px; padding-top:5px;padding-bottom:5px;">
				<input type="checkbox" id = "isKjyw" name="isKjyw" value="1" checked="true" onclick="addDrug.query()">抗菌药物</input>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" id = "isMemo" name="isMemo" value="1" onclick="addDrug.query()">备注</input>
	       </lable>
		</div>
		<div id="id-add-operation"></div>
	</div>
	<div  data-options="region:'south',border:false,split:true" style="height:47px;">
		<div class="footer">	
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="$('#id_form').submit();"  class="no_ico"><span>确定</span></a>
				</div>
			</div>	
		</div>
	</div>
	</form>
<script type="text/javascript">
	var addDrug = {
		//查询
		query : function() {
			$('#id-add-operation').datagrid({
	            queryParams: {
	            	'operTypeId':'${st004Yzxxb.operTypeId}',
	            	'isKjyw':$('input[name="isKjyw"]:checked ').val(),
	            	'isMemo':$('input[name="isMemo"]:checked ').val(),
	            	'zyid':'${st004Yzxxb.zyid}'
	            }
	        });
	    }
	};
	
	$(document).ready(function () {
		//术前用药情况
		$('#id-add-operation').datagrid({
	        fit: false,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url:'${webroot}/st004Yzxxb/f_json/findDrugSituation.shtml',
	        queryParams: {
	        	//不做已选择后不查询的限制（0914唐鑫）
            	//'operTypeId':'${st004Yzxxb.operTypeId}',
            	'isKjyw':$('input[name="isKjyw"]:checked ').val(),
            	'isMemo':$('input[name="isMemo"]:checked ').val(),
            	'zyid':'${st004Yzxxb.zyid}'
            },
	        remoteSort: false,
	        singleSelect: true,
	        border:true,
	        columns:[ 
		       	[
					{field:'ck',title:'选择',width:35,align:'center',
						formatter:function(value,rec,index){
							return ['<input style="width:15px;height:18px;" name="yzid" value="',rec.id,'" type="checkbox" />'].join('');
					    }
					},
					{field:'orderTypeName',title:'医嘱类型',sortable:true,align:'center',width:40},
		            {field:'orderName',title:'医嘱名称',sortable:true,width:160},
		            {field:'orderAt',title:'开嘱时间',sortable:true,align:'center',width:110},
		            {field:'stopAt',title:'停嘱时间',sortable:true,align:'center',width:110},
		            {field:'executeTime',title:'执行时间',sortable:true,align:'center',width:110},
		            {field:'dosage',title:'剂量',sortable:true,align:'center',width:40},
		            {field:'sypc',title:'频次',sortable:true,align:'center',width:40},
		            {field:'adminRouteName',title:'给药路径',sortable:true,align:'center',width:60},
		            {field:'memo',title:'备注',sortable:true,width:120}
		        ]
	        ],
	        rownumbers:true
	    });
		
		Comm.form({
			id : 'id_form',
			url : '${webroot}/st004Yzxxb/f_json/saveDrugSituation.shtml',
			subbtn : 'changeFormSubmitBtn',
			success : function(json) {
				if (json.result === 'success') {
					parent.$.messager.show({title : '提示',msg : '操作成功！'});
					var parentObject = parent.Comm.getObjectCache();
					parentObject.query('${st004Yzxxb.operTypeId}');
					parent.Comm.dialogClose('${param.dialogId}');
				} else if(json.result === 'error') {
					parent.$.messager.show({ title : '提示', msg : '操作失败！' });
				} else {
					parent.$.messager.show({ title : '提示', msg : json.msg });
				}
			}
		});
	});
</script>
</body>
</html>