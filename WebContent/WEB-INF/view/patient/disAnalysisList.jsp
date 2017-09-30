<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>病程分析</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
        <div data-options="region:'west',split:false" style="width:380px;">
        	<div id="disAnalysisPanel"></div>
			<div id="tb_disAnalysis" class="m_search h_set">				
				<!-- <span class="pro_text" style="font-size:12px">病程日期:</span> -->
				<input type="text" name="queryStartDate" id="analysis_queryStartDate" value="<fmt:formatDate value="${queryStartDate}" pattern="yyyy-MM-dd" />" class="Wdate text" style="width:95px;" onclick="WdatePicker()" />~
		    	<input type="text" name="queryEndTime" id="analysis_queryEndTime" value="<fmt:formatDate value="${queryEndTime}" pattern="yyyy-MM-dd" />" class="Wdate text" style="width:95px;" onclick="WdatePicker()" />
				<input type="hidden" id="id_analysis_zyid" value="${param.zyid}"/>	
				<input type="hidden" id="id_analysis_mzid" value="${param.mzid}"/>							
				<div class="n_btn_blue">
					<a href="javascript:;" onclick="disAnalysis.query()"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
				</div>
				<c:if test="${ysbcurl!='0'}">
					<div class="n_btn_blue">
						<a href="javascript:void(0)"   onclick="disAnalysis.ysbc()" ><span>原始病程</span></a>
					</div>
				</c:if>
			</div>
        </div>
        <div data-options="region:'center',border:false" style="border-left-width: 1px; padding-bottom:40px;" >
        	<div class="easyui-tabs" data-options="fit:true,plain:true,border:false">
                <div title="预警分析病程内容" style="padding:10px 20px;" id="id_anal_result"></div>
                <div title="原始病程内容" style="padding:10px 20px;" id="id_course_content"></div>
            </div>
	        <div class="footer dialog_footer" >	
		        <div style="float:left;margin-left:50px;">
		        	<span><font color="#FF0000"><b>红色  : 感染元素</b></font></span>
		        </div>
	        </div>
        </div>
    </div>
<script type="text/javascript">
	var disAnalysis = {
		panel : 'disAnalysisPanel',
		id : '${param.id}',
		query : function () {
			autoTip.clear();
			$('#' + disAnalysis.panel).datagrid({
	            url: '${webroot}/st008Bcjl/f_json/findDisAnalysisList.shtml',
	            queryParams: {
	            	'queryStartDate':$('#analysis_queryStartDate').val(),
	            	'queryEndDate':$('#analysis_queryEndTime').val() == ''?'':$('#analysis_queryEndTime').val()+' 23:59:59',
	            	'zyid':$('#id_analysis_zyid').val() == null ? '' : $('#id_analysis_zyid').val(),
	            	'mzid':$('#id_analysis_mzid').val() == null ? '' : $('#id_analysis_mzid').val()
	            },
	            pageNumber : 1
	        });
		},
		 //原始病程
	 	ysbc :function (){
	 		parent.menuInfo.clickMenu('原始病程','${ysbcurl}',true,'pt_note_info');
		}, 
		//设置右侧病程内容
		setDisCourseContent : function (id) {
			$.ajax({
                url: '${webroot}/st008Bcjl/f_json/getDisCourseContent.shtml',
                type: 'post',
                data: { id: id },
                dataType: 'json',
                success : function(json) {
                	$('#id_anal_result').html(json.showAnalResult == null ? '' : '<font style="font-size:16px; color:#000;">' + json.showAnalResult + '</font>');
                	$('#id_course_content').html(json.courseContent == null ? '' : '<font style="font-size:16px; color:#000;">' + json.courseContent + '</font>');
				}
    		});
		}
	}
	
	$(document).ready(function () {
		$('#' + disAnalysis.panel).datagrid({
	        fit: true,
	        nowrap: true,
	        autoRowHeight: true,
	        striped: true,
	        fitColumns: false,
	        url:'${webroot}/st008Bcjl/f_json/findDisAnalysisList.shtml',   
	        remoteSort: false,
	        singleSelect: true,
	        border:false,
	        idField:'id',
	        queryParams: {
            	'queryStartDate':$('#analysis_queryStartDate').val(),
            	'queryEndDate':$('#analysis_queryEndTime').val() == ''?'':$('#analysis_queryEndTime').val()+' 23:59:59',
            	'zyid':'${param.zyid}',
            	'mzid':'${param.mzid}'
            },
	        columns:[
		       	[
		            {field:'enterDate',title:'病程日期',sortable:true,width:110},
		            {field:'bcName',title:'病程类型 ',sortable:true,width:150},
		            {field:'keyWords',title:'感染因素',sortable:true,width:80,
						styler: function(value,row,index) {
							if (row.keyWords != '') { return 'color:red;';}
						},
						formatter:function(value,r){
							return ['<span title="',r.keyWords,'">',r.keyWords,'</span>'].join('');
						}
		            }
		        ]
	        ],
	        rownumbers:true,
	        toolbar:'#tb_disAnalysis',
	        onClickRow : function (index, row){
	        	disAnalysis.setDisCourseContent(row.id);
            },
            onLoadSuccess : function (data) {
            	if (data.rows.length > 0) {
		        	if (disAnalysis.id.length > 0) {
		        		$('#' + disAnalysis.panel).datagrid('selectRecord', disAnalysis.id);
		        		disAnalysis.setDisCourseContent(disAnalysis.id);
		        		disAnalysis.id = '';
		        	} else {
		        		$('#'+disAnalysis.panel).datagrid('selectRow', 0);
			        	disAnalysis.setDisCourseContent(data.rows[0].id);
		        	}
            	}
            }
	    });
	});
</script>
</body>
</html>
