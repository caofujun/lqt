<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>文档管理</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>

	<div id="docsearchPanel"></div>
	
	<div id="tb" class="m_search" >
		<span class="pro_text">感控共享资料：</span>
		<input type="text" id="searchString" class="auto-tip input_tip" style="width:300px" data-tip="请输入感控资料关键字">
		<div class="n_btn_blue">
			<a href="javascript:;" onclick="docsearch.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
		</div>
		<div class="btn_r">
			<div class="n_btn_grey">
				<c:if test="${acType == 'hospital'}">
					<a href="javascript:void(0);" onclick="upLoadFile();"><i class="icon iconfont">&#xe63f;</i><span>上传资料</span></a>				
				</c:if>
			</div>				
			<div class="n_btn_grey">
				<!-- <a href="javascript:void(0);" onclick=""><i class="icon iconfont">&#xe65f;</i><span>管理资料库</span></a> -->
			</div>
		</div>
    	<!--  资料总数：100，本月新增 20 -->
		<div class="zlk_upload_head" style="display:none;">
			<span>资料总数:&nbsp;<font style="color: black"><span id="totalCount" name="totalCount" >1000</span></font>&nbsp;&nbsp;&nbsp;&nbsp;本月新增:&nbsp;<font style="color:#FF0000"><span id="currentMouthTotalCount" name="currentMouthTotalCount">10</span></font></span>
		</div>
		<div class="select_con">
			<h2 class="select_con_title">感控资料共享</h2>
			<ul id="id_labels" class="select_list">
				<c:forEach items="${labelDict}" var="label">
					<c:choose>
						<c:when test="${label.checked == true}">
							<li dictCode="${label.dictCode}" class="cur"><a href="javascript:void(0);" onclick="chooseLabel(this, '${label.dictCode}')" >${label.dictName}</a></li>
						</c:when>	
						<c:otherwise>
							<li dictCode="${label.dictCode}" ><a href="javascript:void(0);" onclick="chooseLabel(this, '${label.dictCode}')" >${label.dictName}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
			<input type="hidden" id="id_docType" name="docType" /> 
<%-- 			<dl class="format_dl">
				<dt>文件格式：</dt>
				<dd id="id_labels2">
					<a href="javascript:void(0);" class="cur" onclick="chooseLabel2(this, '1')" >全部</a>
					<c:forEach items="${formatList}" var="f" varStatus="i" >
						<a href="javascript:void(0);" onclick="chooseLabel2(this, '${i.index+2}')" >${f}</a>
					</c:forEach>
				</dd>
			</dl> --%>
			<dl class="format_dl">
				<dt>文件格式：</dt>
				<dd id="id_labels2">
					<span dictCode="1" class="cur2"><a href="javascript:void(0);" onclick="chooseLabel2(this, '1')" >全部</a></span>
					<c:forEach items="${formatList}" var="f" varStatus="i" >
						<span dictCode="${f}"><a href="javascript:void(0);" onclick="chooseLabel2(this, '${i.index+2}')" >${f}</a></span>
					</c:forEach>
				</dd>
			</dl>
			<input type="hidden" id="id_docFormat" name="docFormat" />
		</div>
	</div>
	
<script type="text/javascript">
var docsearch = {
	panel:'docsearchPanel',
	//查询
	query : function() {
		autoTip.clear();
        $('#'+docsearch.panel).datagrid({
            queryParams: {
                'searchString' : $('#searchString').val(),
                'docType' : $('#id_docType').val(),
                'docFormat' : $('#id_docFormat').val()
            },
            pageNumber: 1
        });
    },
    //新增
    add:function() {
    	
    },
    //编辑
    edit:function(id,deptId,title) {
       //parent.menuInfo.clickMenu('编辑上传文件','/docsearch/f_view/toeditDoc.shtml?id=' + id,true,'A0602');
    },
    //删除
    del:function(id) {
    	$.messager.confirm('提示', '确认删除该条数据? 删除后无法恢复！', function (r) {
        	if (r) {
            	$.ajax({
                       url: '${webroot}/docsearch/f_json/delDoc.shtml',
                       type: 'post',
                       data: {id:id},
                       dataType: 'json',
                       success : function(json) {
						if(json.result==='success') {
				    		docsearch.query();
                            $.messager.show({ title: '提示', msg: '删除成功！' });
				    	} else if(json.result === 'error') {
				    		$.messager.show({ title: '提示', msg: json.msg });
				    	} else {
				    		$.messager.show({ title: '提示', msg: json.msg });
				    	}
					}
            	});
        	}
    	});
    },
   //  下载文档
   download : function(id){
	   window.location.href="${webroot}/docsearch/s_view/downLoadFile.shtml?fileId=" + id;
   }
};
function upLoadFile() {
	parent.menuInfo.clickMenu('上传资料','/docsearch/f_view/upLoadFileStep1.shtml',true);
	//window.location.href = "${webroot}/docsearch/f_view/upLoadFileStep1.shtml";
};
//文档分类取值
function getQaTagCodes() {
	var qaTagCode = '';
	$("#id_labels > .cur").each(function(){ 
		if ($(this).attr('dictCode')) {
			qaTagCode += $(this).attr('dictCode') + ',';
		}
	});
	qaTagCode = qaTagCode.substring(0, qaTagCode.lastIndexOf(','));
	return qaTagCode;
};
//文档分类点击
function chooseLabel(obj, dictCode) {
	var pObj = $(obj).parent();
	if ('1' === dictCode) {
		$(".cur").removeClass("cur");
		pObj.addClass('cur');   				
	} else {    			
		// var all = $("#id_labels li").eq(0);
		var all=$("#id_labels").find("li[dictcode=1]");
		if (all.hasClass('cur')) {
			all.removeClass('cur');
		}
		if (pObj.hasClass('cur')) {
			pObj.removeClass('cur');
		} else {
			pObj.addClass('cur');
		}
	}
	// 默认选中全部
	if ($("#id_labels > .cur").length == 0) {
	//	var all = $("#id_labels li").eq(0);
		var all=$("#id_labels").find("li[dictcode=1]");
		all.addClass('cur');  
	};
	$("#id_docType").val(getQaTagCodes());
	//$("#id_docFormat").val(getQaTagCodes());
	//　提交查询请求。
	docsearch.query();
};
//-------------------------------------------------------------------
//文件类型取值
function getQaTagCodes2() {
	var qaTagCode = '';
	$("#id_labels2 > .cur2").each(function(){ 
		if ($(this).attr('dictCode')) {
			qaTagCode += $(this).attr('dictCode') + ',';
		}
	});
	qaTagCode = qaTagCode.substring(0, qaTagCode.lastIndexOf(','));
	return qaTagCode;
};
//文件类型点击
function chooseLabel2(obj, dictCode) {
	var pObj = $(obj).parent();
	if ('1' === dictCode) {
		$(".cur2").removeClass("cur2");
		pObj.addClass('cur2');   				
	} else {    			
		var all=$("#id_labels2").find("li[dictcode=1]");
		if (all.hasClass('cur2')) {
			all.removeClass('cur2');
		}
		if (pObj.hasClass('cur2')) {
			pObj.removeClass('cur2');
		} else {
			pObj.addClass('cur2');
		}
	}
	// 默认选中全部
	if ($("#id_labels2 > .cur2").length == 0) {
		var all=$("#id_labels2").find("li[dictcode=1]");
		all.addClass('cur2');  
	};
	$("#id_docFormat").val(getQaTagCodes2());
	//　提交查询请求。
	docsearch.query();
}
$(document).ready(function () {
	// 获取当前总文档数，当前文档数；
	$.ajax({
	    url: '${webroot}/docsearch/f_json/getTotalDocAndCurrentYearTDC.shtml',
	    type: 'post',
	    dataType: 'json',
	    success : function(json) {
			if(json.result==='success') {
		   		// 设置数据在对应的标签上面。
				$("#totalCount").html(json.extraValue);
				$("#currentMouthTotalCount").html(json.expandValue);
		   	}
		}
	});
	$('#'+docsearch.panel).datagrid({
		fit: true,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		fitColumns: true,
		collapsible:true,
		url:'${webroot}/docsearch/f_json/pageQuery.shtml',
		queryParams: {
            'searchString' : $('#searchString').val(),
            'docType' : $('#id_docType').val(),
            'docFormat' : $('#id_docFormat').val()
		},
		remoteSort: false,
		singleSelect: true,
		border:false,
		columns:[
			[
				{field:'docName',title:'资料名称',sortable:true,width:100,
					formatter:function(value,r){
						return '<a href="javascript:docsearch.download(\'' + r.id + '\');">'+ r.docName +'</a>';
					}
				},
				{field:'docTypeName',title:'分类',sortable:true,width:40},
				{field:'docFormat',title:'格式类型',sortable:true,width:40}, 
				{field:'createTime',title:'添加时间',sortable:true,width:80 },
				{field:'createUsername',title:'贡献者',sortable:true,width:60},
				{field:'docDownload',title:'下载',sortable:true,width:20},
				//{field:'docScore',title:'所需积分',sortable:true,width:40},
				//{field:'分享次数',title:'分享次数',sortable:true,width:30},
				{field:'docFlag',title:'文档状态',sortable:true,width:30, 
				    formatter: function(value,row,index){  
				        if(value=='0'){ //icon-bullet_tick  
				            return "<span style='color:#FF0000; '>已删除</span>";  		                               
				        }else{  
				        	 return "<span style='color:blue; '>有效</span>";  
				        }  
				   }},
				{field:'_operate',title:'操作',width:80,
					formatter:function(value,r){
						var str = '<a href="javascript:docsearch.download(\'' + r.id + '\');" class="ico_download" title="下载"></a>';
						if('${acType}' == 'hospital'){
							str += '<a href="javascript:docsearch.del(\'' + r.id + '\');" class="ico_del" title="删除"></a>';
						}
						/* return ['<a href="javascript:docsearch.edit(\'',r.id,'\',\'',r.deptId,'\',\'修改\');" class="ico_editor" title="修改"></a>',
						        '<a href="javascript:docsearch.download(\'',r.id,'\');" class="ico_export" title="下载"></a>',
						        '<a href="javascript:docsearch.del(\'',r.id,'\');" class="ico_del" title="删除"></a>'].join(''); */
						return str;
					}
				}
			]
	            ],
		pagination:true,
		rownumbers:true,
		toolbar:'#tb'
	});
});
</script>
</body>
</html>