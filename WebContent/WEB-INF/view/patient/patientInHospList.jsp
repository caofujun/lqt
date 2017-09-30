<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>患者出入院记录</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
<script src="${webroot}/resources/js/emptyCards.js"></script>
</head>
<body>
	<div id="tb" style="display: none;">
		<div class="m_search">	
			<span>患者类型:</span>
			<select id="id_patientType" class="easyui-combobox" data-options="editable:false,value:'ZY'" style="width: 85px;">
				<option value="ZY">住院</option>
				<option value="MZ">门诊</option>
			</select>
			<span>出院日期:</span>
			<input type="text" id="queryStartDate" value="${queryStartDate}" class="Wdate text" style="width:80px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />~
    		<input type="text" id="queryEndDate" value="${queryEndTime}" class="Wdate text" style="width:80px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />				
			<span>科室:</span>		
			<div class="select_del"><input id="id_deptId" style="width: 150px;" /><a href="javascript:void(0)" title="清除" class="select_img_del" onclick="$('#id_deptId').combo('clear');"></a></div>
			<select id="id_isInHosp" class="easyui-combobox" data-options="editable:false,value:'${param.isInHosp}'" style="width: 85px;">
				<option value="">-请选择-</option>
				<option value="0">在院</option>
				<option value="1">出院</option>
			</select>
			<input type="text" id="id_patient_info" class="auto-tip" value="${searchString}" data-tip="${patientNoTitle}/${patientZyTitle}/姓名" title="${patientNoTitle}/${patientZyTitle}/姓名" style="width: 133px;" />
			<!-- <span>医嘱名称:</span>	
			<input type="text" id="id_yz_info" class="auto-tip" data-tip="医嘱名称" title="医嘱名称" style="width: 133px;" /> -->
			<div class="n_btn_blue">
				<a href="javascript:;" onclick="inHosp.query();" ><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div>
			<c:if test="${!empty isShowGw && isShowGw==1}">
				<c:if test="${fn:contains(systemScope,'cdc')}">
				<div class="btn_r">
					<select id="emptyCard" onclick="EC.emptySelect();">
						<option value="">选择空卡上报...</option>
						<c:forEach items="${AllBK}" var="abk">
							<c:if test="${fn:contains(cdcScope,abk.dictCode) or fn:contains(cdcScope,'all')}">
								<option value="${abk.dictCode}_empty">${abk.dictName}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				</c:if> 
			</c:if>
		</div>
	</div>
	<div id="inHospPanel"></div>
	
	<iframe id='id_iframe' style='display:none;'></iframe>
<script type="text/javascript">
	var inHosp = {
		panel : 'inHospPanel',
		isQuery : '${param.query}',
		trim :function (str){ 
            return str.replace(/(^\s*)|(\s*$)/g, ""); 
    	},
		query : function () {
			autoTip.clear();
			$('#' + inHosp.panel).datagrid({
	            url: '${webroot}/st003Cryxxb/f_json/findPatientList.shtml',
	            queryParams: {
	            	//'hospId':$('#id_hospId').combogrid('getValue'),
	            	'queryStartDate':$('#queryStartDate').val(),
	            	'queryEndDate':$('#queryEndDate').val() == ''?'':$('#queryEndDate').val()+' 23:59:59',
	            	'deptCode':$('#id_deptId').combogrid('getValue'),
	            	'isInHosp' : $('#id_isInHosp').combobox('getValue'),
	            	'searchString':inHosp.trim($('#id_patient_info').val())
	            	//'orderName':$('#id_yz_info').val()
	            },
	            pageNumber : 1
	        });
		},
		//患者档案
		patientInfo : function (zyid) {
			var urlPrefix = '${param.urlPrefix}';
			if (urlPrefix && urlPrefix.length > 0) {
				document.getElementById('id_iframe').src= '${param.myPrefix}/domain/f_view/toDomainDis.shtml?title=' + encodeURI(encodeURI('患者档案')) + '&url=' + '/st003Cryxxb/f_view/toPatientRecords.shtml~-~tab=2_~_zyid=' + zyid + '&urlPrefix=' + urlPrefix;
			} else {
				parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true,'','${param.urlPrefix}');
			}
		}
	};

	$(document).ready(function () {
		//科室
		Csm.combogrid.dep({
			id: 'id_deptId',
			hospId:'${unitId}',
			ifcaseoffice: '1'
		});
		$('#' + inHosp.panel).datagrid({
		    fit: true,
		    nowrap: true,
		    autoRowHeight: true,
		    striped: true,		    
		    //url:'${webroot}/st003Cryxxb/f_json/findPatientList.shtml',   
		    remoteSort: false,
		    singleSelect: true,
			fitColumns: true,
		    border:false,
		    queryParams: {
		    	'isInHosp' : '${param.isInHosp}',
		    },
		    columns:[ 
		     	[
		          /* {field:'hospName',title:'医院名称',sortable:true,width:110},
		          {field:'patientId',title:'${patientNoTitle}',sortable:true,width:100,
		        	  formatter:function(value,row,index){
					  	  return [(row.patientId+'('+row.visitId+')')].join('');}
		          }, */
		          {field:'zyid',title:'${patientZyTitle}',sortable:true,width: 100,
		        	  formatter:function(value,rec){
							return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="inHosp.patientInfo(\'' + rec.zyid + '\')">' + rec.${patientZyValue} + '</a>'].join('');
					    }
		          },
		          {field:'patientName',title:'患者',sortable:true,width:110,
		        	  formatter:function(value,row,index){
					  	  return [(row.patientName+'('+row.sex+','+row.age+row.ageUnit+')')].join('');}
		          },		         
				  {field:'deptName',title:'当前科室',sortable:true,width:130},
				  {field:'bedNo',title:'床号',sortable:true,width:40},
				  {field:'inHospAt',title:'入院日期',sortable:true,width:110,align:'center'},
		          {field:'inDeptName',title:'入院科室',sortable:true,width:130},
		          {field:'diagnosisName',title:'入院诊断',sortable:true,width:150},
		          {field:'outAt',title:'出院日期',sortable:true,width:110,align:'center'},
		          {field:'outDeptName',title:'出院科室',sortable:true,width:130},
	              {field:'yqxCount',title:'操作',sortable:true,width:100,
	                	formatter:function(value,r){
	                		var tmp = '';
	                		if('${systemScope}'.indexOf('nis') >= 0){
	                			tmp += '<a href="javascript:bk(\''+r.zyid+'\');" class="ico_up_blue" title="院感上报"></a>';
							}
	                		if('${systemScope}'.indexOf('cdc') >= 0 && '${isShowGw}'=='1'){
	                			tmp += '<a href="javascript:crbbk(\''+r.patientName+'\',\''+r.zyid+'\',\'\');" class="ico_up_green" title="公卫卡上报"></a>';
							}
	                		if('${cgpg}'== '0'){
	                			tmp += '<a href="javascript:cgPg(\''+r.zyid+'\');" class="ico_check" title="评估插管"></a>';
	                		}
	                		tmp += '<a href="javascript:sendMessage(\''+r.zyid+'\');" class="ico_mail" title="消息"></a>';
							return [tmp].join('');
						}
	              }
		      ]
		    ],
		    pagination:true,
	        rownumbers:true,
	        toolbar:'#tb'
		});
		
		//显示报卡图标
		$("#cardsPlace").show();
		
		if (inHosp.isQuery === '1') {
			inHosp.query();
		}else{
			parent.$.messager.show(
					 
					{ title: '提示', msg: '请选择查询条件，点击查询！' ,
						style:{ 
		                    right:'', 
		                    top:document.body.scrollTop+document.documentElement.scrollTop, 
		                    top:'' 
		                }	
					});
		}
		
		/* //医院
		Csm.combogrid.unit({
			id: 'id_hospId',
			value: '',
			flag: '1',
			callback: '0'
		}); */
		
		$("#id_patientType").combobox({
			onChange: function (nv,ov) {
				if(nv=="MZ"){
					parent.parent.menuInfo.clickMenu('患者信息查询','/st003Cryxxb/f_view/patientMzList.shtml?patientType=MZ',true);
				}else{
					parent.parent.menuInfo.clickMenu('患者信息查询','/st003Cryxxb/f_view/toList.shtml?patientType=ZY',true);
				}
			}
		});
		
	});
	
	function cgPg(zyid){
		parent.parent.menuInfo.clickMenu('插管评估','/gm004Jcmx/f_view/cgPgList.shtml?zyid='+zyid,true);
	}

	function sendMessage(zyid){
		Comm.dialogGlobal({
	    	url:"${webroot}/nyMessageTheme/f_view/toedit.shtml?zyid="+zyid+"&msgType=1",
	        title: "干预会话",
	        width:870,
	        height:555,
	        type:"iframe"
	    });
	}

	function bk(zyid){
		parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid,true);
	}
	
/* 	function emptyCard(){
		var curCT = $("#emptyCard option:selected").val();
		if(curCT=='crbbk_empty'){
			parent.menuInfo.clickMenu('传染病报卡上报','/cdc/f_view/reportCardMZ.shtml?type=empty',true);
		}else if(curCT=='sybk_empty'){
			parent.menuInfo.clickMenu('死因报卡上报','/cdc/f_view/deathReport.shtml?type=empty',true);
		}else if(curCT=='syjcbk_empty'){
			parent.menuInfo.clickMenu('食源监测报卡上报','/cdc/f_view/fsmReport.shtml?type=empty',true);
		}else if(curCT=='zlbk_empty'){
			parent.menuInfo.clickMenu('肿瘤病例报卡上报','/cdc/f_view/tumourReport.shtml?type=empty',true);
		}else if(curCT=='syycbk_empty'){
			parent.menuInfo.clickMenu('食源异常报卡上报','/cdc/f_view/fsaReport.shtml?type=empty',true);
		}else if(curCT=="xnxgbk_empty"){
			parent.parent.menuInfo.clickMenu('心脑血管事件报卡上报','/cdc/f_view/hcvReport.shtml?type=empty',true);
		}
		$("#emptyCard option[value='']").attr("selected","selected");
	} */
	
	function crbbk(pname,zyid,mzid){
		Comm.dialogGlobal({
	    	url:"${webroot}/cdc/f_view/toChooseCard.shtml?zyid="+zyid+"&mzid="+mzid+"&ownership=${param.ownership}",
	    	title: "病例上报 - 病人:"+pname+"("+(!zyid?mzid:zyid)+")	 - 已报卡列表",
	        width:850,
	        type:"iframe",
	        height:430
	    });
	}
	
</script>
</body>
</html>
