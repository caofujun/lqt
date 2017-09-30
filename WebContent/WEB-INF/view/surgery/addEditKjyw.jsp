<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>新增抗菌药物信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body class="easyui-layout" >
	<form method="post" id="id_form">
	<div data-options="region:'center',border:false"  class="byt_table">
		<input type="hidden" name="operTypeId" value="${st004Yzxxb.operTypeId}" />
		<input type="hidden" name="zyid" value="${st004Yzxxb.zyid}" />
		<input type="hidden" name="refid" value="${st004Yzxxb.refid}" />
		<input type="hidden" name="relid" value="${st004Yzxxb.relid}" />
		<div id="id-add-operation"></div>
	</div>
	<div   data-options="region:'south',border:false,split:true" style="height:47px;">
		<div class="footer">	
			<div class="footer_btn">
				<div class="n_btn_blue">
					<a href="javascript:;" id="changeFormSubmitBtn" onclick="kjyw.addKjyw();"  class="no_ico"><span>确定</span></a>
				</div>
			</div>	
		</div>
	</div>	
	</form>
<script type="text/javascript">
	
	var kjyw={
		yymd : '${yymd}',
		addKjyw : function (){
			var refid='${st004Yzxxb.refid}';
			var chk_value =[]; 
			var sysDictSelections = $("#id-add-operation").datagrid("getSelections");
			var parentObject = parent.Comm.getObjectCache();
			for (var i = 0; i < sysDictSelections.length; i++) {
			var	yzid = sysDictSelections[i].id;
				chk_value.push(yzid); 
			}
			parent.Comm.dialogClose('${param.dialogId}');
			parentObject.savekjyw(chk_value,refid,kjyw.yymd,'${st004Yzxxb.panelId}');
		}
	};
	$(document).ready(function () {
		$('#id-add-operation').datagrid({
	        fit: false,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: true,
	        url:'${webroot}/st004Yzxxb/f_json/findDrugSituationTemp.shtml',
	        queryParams: {
            	'zyid':'${st004Yzxxb.zyid}',
            	'yzIdStr':'${yzIdStr}',
            	'refid':'${st004Yzxxb.refid}',
            },
	        remoteSort: false,
	        singleSelect: false,
	        border:true,
	        columns:[ 
		       	[
					{field:'ck',title:'选择',width:35,align:'center',checkbox:true,
						formatter:function(value,rec,index){
							if(rec.isselect==1){
								return ['<input style="width:15px;height:18px;" name="yzid"  checked="checked" value="',rec.id,'"'+' type="checkbox" />'].join('');
							}else{
								return ['<input style="width:15px;height:18px;" name="yzid" value="',rec.id,'"'+' type="checkbox" />'].join('');
							}
					    }
					},
					{field:'id',title:'id',sortable:true,align:'center',width:70},
					{field:'orderTypeName',title:'医嘱类型',sortable:true,align:'center',width:70},
		            {field:'orderName',title:'医嘱名称',sortable:true,width:140},
		            {field:'orderAt',title:'开嘱时间',sortable:true,align:'center',width:125},
		            {field:'stopAt',title:'停嘱时间',sortable:true,align:'center',width:125},
		            {field:'dosage',title:'剂量',sortable:true,align:'center',width:50},
		            {field:'sypc',title:'频次',sortable:true,align:'center',width:50},
		            {field:'adminRouteName',title:'给药路径',sortable:true,align:'center',width:80}
		        ]
	        ],
	        onLoadSuccess : function (data) {
		    	$('#id-add-operation').datagrid('hideColumn', 'id');
	        },
	        rownumbers:true
	    });
	});
</script>
</body>
</html>