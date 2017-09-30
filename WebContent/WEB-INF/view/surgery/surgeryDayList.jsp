<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>外科手术感染监测</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
	<div id="tb" class="m_search">
		<div class="m_search_first">
			<span>手术日期:</span>
			<input type="text" id="date" value="${date}" style="width:80px"  class="Wdate text" onclick="WdatePicker({readOnly:true,onpicked:function(dp){surgery.chooseSurgery('zd','')}})"  />
<!-- 			<div class="n_btn_blue">
				<a href="javascript:;" onclick="surgery.chooseSurgery('zd','零类')"><i class="icon iconfont">&#xe688;</i><span>搜索</span></a>
			</div> -->
		</div>
		<div class="m_search_first">
			<a class="cur" href="javascript:;" id="zd" onclick="surgery.chooseSurgery('zd','',this)">重点监测手术(<span id="zdCount"></span>例)</a>
			<a id="fzd" href="javascript:;" onclick="surgery.chooseSurgery('fzd','',this)">非重点监测手术(<span id="fzdCount"></span>例)</a>
			<a id="qb" href="javascript:;" onclick="surgery.chooseSurgery('q','',this)">全部(<span id="qbCount"></span>例)</a>
		</div>
		<div class="m_search_m">
			<a id="la" href="javascript:;" onclick="surgery.chooseSurgery('','零类',this)">零类(<span id="lingcount"></span>例)</a>
			<a id="ya" href="javascript:;" onclick="surgery.chooseSurgery('','I类',this)">I类(<span id="yicount"></span>例)</a>
			<a href="javascript:;" onclick="surgery.chooseSurgery('','II类',this)">II类(<span id="ercount"></span>例)</a>
			<a href="javascript:;" onclick="surgery.chooseSurgery('','III类',this)">III类(<span id="sancount"></span>例)</a>
			<a href="javascript:;" onclick="surgery.chooseSurgery('','IV类',this)">IV类(<span id="sicount"></span>例)</a>
			<a href="javascript:;" onclick="surgery.chooseSurgery('','未维护',this)">切口未维护(<span id="wwhcount"></span>例)</a>
			<a id="qbb" href="javascript:;" onclick="surgery.chooseSurgery('','',this)">全部(<span id="qbccount"></span>例)</a>
		</div>
		<div class="m_search_last">
			<input type="radio" name="deptType" value="gx" onclick="surgery.chooseDeptType(this.value)" id="deptType_1"/><label class="input_text" for="deptType_1">管辖科室</label>
			<input type="radio" name="deptType" value="qy" onclick="surgery.chooseDeptType(this.value)" id="deptType_2" checked="checked"/><label class="input_text" for="deptType_2">全部科室</label>			
			<div class="btn_r">
				<div class="n_btn_grey">
					<a href="javascript:;" onclick="surgery.print()"><i class="icon iconfont">&#xe628;</i><span>导出</span></a>
				</div>				
			</div>		
	 </div>
	 </div>	
	<div id="surgeryListPanel"></div>
<script type="text/javascript">
	var isZdjc = 'zd';
	var incision;
	var deptTypeId='qy';
	var surgery = {
		panel : 'surgeryListPanel',
		nnisName : 'NNIS评分',
		riskRateType : '${riskRateType}',
		dataSections : eval('${dataSections}'),
		query : function () {
			autoTip.clear();
			$('#' + surgery.panel).datagrid({
	            fit: true,
	            nowrap: true,
	            autoRowHeight: false,
	            striped: true,
	            fitColumns: true,
	            collapsible:true,
		        url:'${webroot}/st005Ssxxb/f_json/findSurgeryDayList.shtml',
		        queryParams: {
		        	'riskRateType':'${riskRateType}',
	            	'date':$('#date').val(),
	            	'isZdjc':isZdjc,
	            	'incisionGrade':incision,
	                'deptType':deptTypeId
	            }, 
	            remoteSort: false,
	            singleSelect: true,
	            border:false,
		        columns:[ 
			       	[
						{field:'deptName',title:'科室',sortable:true,width:125},
			            {field:'zyid',title:'${patientZyTitle}',sortable:true,width:90,
			            	formatter:function(value,rec){
								return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="surgery.patientInfo(\'' + rec.zyid + '\')">' + rec.${patientZyValue} + '</a>'].join('');
						    }
			            },
			            {field:'patientNameDesc',title:'患者',sortable:true,width:120,
	    					formatter:function(value,row,index){
	    						return [(row.patientName+'('+row.sex+','+row.age+row.ageUnit+')')].join('');
	    					}
	    				},			            
	    				{field:'bedNo',title:'床号',sortable:true,width:50},
			            {field:'operName',title:'手术名称',sortable:true,width:260,
	    					formatter:function(value,rec){
								return ['<a href="javascript:void(0)" style="text-decoration: underline;" onclick="surgery.edit(\'' + rec.id + '\')">' + value + '</a>'].join('');
						    }
			            },
			            {field:'operAt',title:'手术日期',sortable:true,align:'center',width:80},
			            {field:'incisionGrade',title:'切口类型',sortable:true,align:'center',width:50},
			            {field:'operLengTime',title:'持续时间',sortable:true,align:'center',width:50},
			            {field:'asa',title:'ASA评分',sortable:true,align:'center',width:55},
			            {field:'nnis',title:surgery.nnisName,sortable:true,align:'center',width:55},
			            {field:'infectTypeName',title:'感染类型',sortable:true,width:100,
			            	formatter:function(value,r,index){
			            		var operId=''
			            		if(r.operId!=null){
			            			operId = r.operId;
			            		}
								var exp="onChange='surgery.chooseInfectTypeId(this.value,\""+r.zyid+"\",\""+operId+"\",\""+r.relid+"\",\""+encodeURI(encodeURI(r.operName))+"\",\""+encodeURI(encodeURI(r.incisionGrade))+"\")'";
								var selectStr = '<select name="infectTypeId'+index+'" '+exp+'><option value="">请选择</option>';
								for (var i=0; i < surgery.dataSections.length; i++) {
									var dataSection = surgery.dataSections[i];
									selectStr += '<option value="' + dataSection.value + '" ' + (dataSection.value == r.infectTypeId ? 'selected="selected"' : '') + '>' + dataSection.text + '</option>';
								}
								selectStr += '</select>';
								return selectStr;
							}								
						},
						{field:'infectDiagnName',title:'感染部位',sortable:true,width:60},
						{field:'statusName',title:'状态',sortable:true,align:'center',width:90}
			        ]
		        ],

		        rownumbers:true,
		        toolbar:'#tb',
		        onLoadSuccess: function() {
		        	$.ajax({
			            url: '${webroot}/st005Ssxxb/f_json/dayCount.shtml',
			            type: 'post',
			            data: {'isZdjc':isZdjc,'date': $('#date').val(),'deptType':deptTypeId},
			            dataType: 'json',			         
			            success : function(data) {
			            	$('#zdCount').html(data.ZDCOUNT);
			            	$('#fzdCount').html(data.FZDCOUNT);
			            	$('#qbCount').html(data.QBCOUNT);
			            	$('#lingcount').html(data.LINGCOUNT);
			            	$('#yicount').html(data.YICOUNT);
			            	$('#ercount').html(data.ERCOUNT);
			            	$('#sancount').html(data.SANCOUNT);
			            	$('#sicount').html(data.SICOUNT);
			            	$('#wwhcount').html(data.WWHCOUNT);
			            	$('#qbccount').html(data.LINGCOUNT+data.YICOUNT+data.ERCOUNT+data.SANCOUNT+data.SICOUNT+data.WWHCOUNT);
						}
			        });
	            }
	        });
		},
		//设置nnis列名称
		setNnisName : function() {
			if (this.riskRateType == '1') {
				surgery.nnisName = 'NNIS评分(75百分位)';
			} else if (this.riskRateType == '2') {
				surgery.nnisName = 'NNIS评分(180分钟)';
			}
		},
		chooseSurgery:function (zdjc,incisionGrade,_this){
	    	$('.m_search_first').find("a").attr("class","");
	    	$('.m_search_m').find("a").attr("class","");
	    	if(zdjc==''){
	    		zdjc=isZdjc;
			}else{
				isZdjc=zdjc;
				$("#qbb").attr("class","cur");
			}
	    	incision = incisionGrade;
	    	if(isZdjc=='zd'){
	    		$("#zd").attr("class","cur");
	    	}else if(isZdjc=='fzd'){
	    		$("#fzd").attr("class","cur");
	    	}else if(isZdjc=='q'){
	    		$("#qb").attr("class","cur");
	    	}
	    	$(_this).attr("class","cur");
	    	surgery.query();
	    },
	    chooseDeptType:function(deptType){
	    	deptTypeId = deptType;
	    	surgery.query();
	    },
		print:function(){
			autoTip.clear();
			var date = $('#date').val();
			var url = '${webroot}/st005Ssxxb/f_json/exportExcelSsxxDay.shtml?date='+date+'&isZdjc='+isZdjc+'&deptType='+ deptTypeId+'&incisionGrade=' + encodeURIComponent(encodeURIComponent(incision));
    		window.location.href = url;
		},
		//编辑
		edit : function(id) {
			parent.menuInfo.clickMenu('手术信息','/st005Ssxxb/f_view/toSurgeryInfoEdit.shtml?id=' + id, true, null, null, '${param.tabBodyId}');
    	},
    	 //选择感染类型
	    chooseInfectTypeId:function(infectTypeId,zyid,operId,relid,operName,incisionGrade){
	    	if(infectTypeId!=''){
	    		parent.menuInfo.clickMenu('报卡操作','/bk001Sbk/f_view/toAddReportCards.shtml?isSeparatePage=1&zyid='+zyid+"&infectTypeId="+infectTypeId+"&operId="+operId+"&relid="+relid+"&operName="+operName+"&incisionGrade="+incisionGrade,true);    			   
	    	}
	    },
		//患者档案
		patientInfo : function (zyid) {
			parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?tab=2&zyid=' + zyid,true);
		}
	};

	$(document).ready(function () {
		window.setTimeout(function() {
			surgery.chooseSurgery('zd','零类')
		}, 500);
	});
</script>
</body>
</html>
