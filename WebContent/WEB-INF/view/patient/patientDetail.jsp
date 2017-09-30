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
			            <th>${patientZyTitle}：</th>
			            <td>
			            	<c:out value="${patientZyValue == 'patientId' ? st003Cryxxb.patientId:st003Cryxxb.zyid}" />
			            </td>
			            <th>姓名：</th>
			            <td>
			            	<c:out value="${st003Cryxxb.patientName}" />
			            </td>
			            <th>性别：</th>
			            <td>
			            	<c:out value="${st003Cryxxb.sex}" />
			            </td>
			            <th>年龄：</th>
			            <td>
			            	<c:out value="${st003Cryxxb.age}" />
			            </td>
			        </tr>
			        <tr>
			        	<th>住院次数：</th>
			            <td>
			            	<c:out value="${st003Cryxxb.visitId}" />
			            </td>
			            <th>体重：</th>
			            <td>
			            	<c:out value="${st003Cryxxb.weight}" />
			            </td>
			            <th>住院天数：</th>
			            <td>
			            	<c:out value="${st003Cryxxb.inDays}" />
			            </td>
			            <th>出生日期：</th>
			            <td>
			            	<fmt:formatDate value="${st003Cryxxb.birthDate}" pattern="yyyy-MM-dd" />
			            </td>
			        </tr>
			        <tr>
			            <th>入院日期：</th>
			            <td>
			            	<fmt:formatDate value="${st003Cryxxb.inHospAt}" pattern="yyyy-MM-dd HH:mm" />
			            </td>
			            <th>出院日期：</th>
			            <td>
			            	<fmt:formatDate value="${st003Cryxxb.outAt}" pattern="yyyy-MM-dd HH:mm" />
			            </td>
			            <th>当前科室：</th>
			            <td>
			            	<c:out value="${st003Cryxxb.deptName}" />
			            </td>
			            <th>管床医生：</th>
			            <td>
			            	<c:out value="${st003Cryxxb.chargeDrName}" />
			            </td>
			        </tr>
			        <tr>
			            <th>入院诊断：</th>
			            <td colspan="7">
			            	<c:out value="${st003Cryxxb.diagnosisName}" />
			            </td>
			        </tr>
			    </tbody>                
			</table>
		    </div>
		</div>

		<div class="card_cont">
			<div class="card_cont_h">
				<span class="card_c_h_text">诊疗记录</span>
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
              url:'${webroot}/st003Cryxxb/f_json/findCryxxList.shtml?patientId=' + '${st003Cryxxb.patientId}',   
              remoteSort: false,
              singleSelect: true,            
              columns:[
              	  [
	                   /*{field:'patientId',title:'${patientNoTitle}',sortable:true,width:90},  */
	                   {field:'zyid',title:'${patientZyTitle}',sortable:true,width:90,
			            	formatter:function(value,rec){
								return ['<a href="javascript:void(0)" style="text-decoration: underline;" >' + rec.${patientZyValue} + '</a>'].join('');
						    }
			            },
	                   {field:'inDeptName',title:'入院科室',sortable:true,width:140},
	                   {field:'inHospAt',title:'入院时间',sortable:true,width:120},
	                   {field:'outDeptName',title:'出院科室',sortable:true,width:140},
	                   {field:'outAt',title:'出院时间',sortable:true,width:120},
	                   {field:'diagnosisName',title:'入院诊断',sortable:true,width:160},
	                   {field:'visitId',title:'住院次数',sortable:true,width:60}
                  ]
              ],
              rownumbers:true,
              onClickRow : function (index, row){
            	  var currTab = parent.$('#mainTabs').tabs('getSelected');
	      	      var url = '${webroot}/st003Cryxxb/f_view/toPatientRecords.shtml?zyid=' + row.zyid;
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
