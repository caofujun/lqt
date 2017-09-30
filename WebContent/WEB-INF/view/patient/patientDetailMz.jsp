<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>患者信息</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>
<body>
<div style="margin:10px;">	
		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">基本信息</span>
			</div>
			<div class="stand_info">    
			<table class="info_table">
			    <tbody>  
			        <tr>
			            <th>门诊号：</th>
			            <td>
			            	<c:out value="${st020ClinicPatients.mzid}" />
			            </td>
			            <th>姓名：</th>
			            <td>
			            	<c:out value="${st020ClinicPatients.patientName}" />
			            </td>
			            <th>性别：</th>
			            <td>
			            	<c:out value="${st020ClinicPatients.sex}" />
			            </td>
			            <th>年龄：</th>
			            <td>
			            	<c:out value="${st020ClinicPatients.age}" />
			            </td>
			        </tr>
			        <tr>
			        	<th>就诊次数：</th>
			            <td>
			            	<c:out value="${st020ClinicPatients.visitId}" />
			            </td>
			            <th>就诊科室：</th>
			            <td>
			            	<c:out value="${st020ClinicPatients.deptName}" />
			            </td>
			            <th>就诊日期：</th>
			            <td>
			            	<fmt:formatDate value="${st020ClinicPatients.diagnosisDt}" pattern="yyyy-MM-dd HH:mm" />
			            </td>
			            <th>就诊医生：</th>
			            <td>
			            	<c:out value="${st020ClinicPatients.doctorName}" />
			            </td>
			        </tr>
			        <tr>
			            <th>门诊诊断：</th>
			            <td colspan="7">
			            	<c:out value="${st020ClinicPatients.diagnosisName}" />
			            </td>
			        </tr>
			    </tbody>                
			</table>
		    </div>
		</div>

		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">就诊记录</span>
			</div>	
			<div class="byt_table">
				<div id="cryxxPanel" ></div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function () {
		$('#cryxxPanel').datagrid({
              fit: false,
              nowrap: true,
              autoRowHeight: false,
              striped: true,
              fitColumns: true,
              collapsible:true,
              url:'${webroot}/st003Cryxxb/f_json/findMzList.shtml?patientId=' + '${st020ClinicPatients.patientId}',   
              remoteSort: false,
              singleSelect: true,            
              columns:[
              	  [
	                   {field:'patientId',title:'${patientNoTitle}',sortable:true,width:90},  
	                   {field:'visitId',title:'就诊次数',sortable:true,width:90},  
	                   {field:'mzid',title:'门诊号',sortable:true,width:90,
			            	formatter:function(value,rec){
								return ['<a href="javascript:void(0)" style="text-decoration: underline;" >' + value + '</a>'].join('');
						    }
			            },
	                   {field:'diagnosisDt',title:'就诊日期',sortable:true,width:120},
	                   {field:'deptName',title:'就诊科室',sortable:true,width:140},
	                   {field:'doctorName',title:'就诊医生',sortable:true,width:120},
	                   {field:'diagnosisName',title:'门诊诊断',sortable:true,width:160}
                  ]
              ],
              rownumbers:true,
              onClickRow : function (index, row){
            	  var currTab = parent.$('#mainTabs').tabs('getSelected');
	      	      var url = '${webroot}/st003Cryxxb/f_view/toPatientRecordsMz.shtml?mzid=' + row.mzid;
	      	      parent.$('#mainTabs').tabs('update',{     
	     			  tab : currTab,     
	      			  options : {          
	      			  	  content : '<iframe scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>' 
	      			  }
	      		  });
              }
          });
	});
</script>
</body>
</html>
