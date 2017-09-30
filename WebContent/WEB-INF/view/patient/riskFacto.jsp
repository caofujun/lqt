<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>重点风险因素</title>
<%@ include file="/WEB-INF/view/core/include.jsp"%>
</head>

<body>
<div class="prevalence">
 <div id="prevalence_edit">
	
	<div class="title mb10">抗菌药物使用<a name="1" class="lemma-anchor maodian"></a></div>
	<div>
		<table class="table_custom">
			<tr>
				<th style="width:30px;">序号</th>
				<th style="width:50px;">医嘱类型</th>
				<th style="width:70px;">开嘱时间</th>
				<th style="width:70px;">停嘱时间</th>
				<th style="width:120px;">医嘱名称</th>
				<th style="width:45px;">剂量</th>
				<th style="width:60px;">用药方法</th>
				<th>用药目的</th>
				<th>药品线别</th>
				<th>开嘱医生</th>
				<th>停嘱医生</th>
			</tr>
			<c:forEach items="${kjSt004List}" var="st004" varStatus="status">
			<tr>
				<td><c:out value="${status.index + 1}" /></td>
				<td><c:out value="${st004.orderTypeName}" /></td>
				<td><fmt:formatDate value="${st004.orderAt}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${st004.stopAt}" pattern="yyyy-MM-dd" /></td>
				<td><c:out value="${st004.orderName}" /></td>
				<td><c:out value="${st004.dosage}" /><c:out value="${st004.dosageUnit}" /></td>
				<td><c:out value="${st004.adminRouteName}" /></td>
				<td><c:out value="${st004.usePurpose}" /></td>
				<td><c:out value="${st004.drugLineName}" /></td>
				<td><c:out value="${st004.bdocName}" /></td>
				<td><c:out value="${st004.edocName}" /></td>
			</tr>
			</c:forEach>
		</table>	
	</div>
	<div class="title mb10">三管操作<a name="2" class="lemma-anchor maodian"></a></div>
	<div>
		<table class="table_custom">
			<tr>
				<th style="width:30px;">序号</th>
				<th style="width:50px;">医嘱类型</th>
				<th style="width:70px;">开嘱时间</th>
				<th style="width:70px;">停嘱时间</th>
				<th style="width:200px;">医嘱名称</th>
				<th style="width:80px;">管道评估情况</th>
				<th style="width:70px;">开嘱医生</th>
				<th style="width:70px;">停嘱医生</th>
				<th style="width:70px;">执行护士</th>	
			</tr>
			<c:forEach items="${qxSt004List}" var="st004" varStatus="status">
			<tr>
				<td><c:out value="${status.index + 1}" /></td>
				<td><c:out value="${st004.orderTypeName}" /></td>
				<td><fmt:formatDate value="${st004.orderAt}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${st004.stopAt}" pattern="yyyy-MM-dd" /></td>
				<td><c:out value="${st004.orderName}" /></td>	
				<td><c:out value="${st004.isCgException}" /></td>
				<td><c:out value="${st004.bdocName}" /></td>			
				<td><c:out value="${st004.edocName}" /></td>
				<td><c:out value="${st004.bnrsName}" /></td>
			</tr>
			</c:forEach>
		</table>	
	</div>
	<div class="title mb10">检出菌<a name="3"class="lemma-anchor maodian"></a></div>
	<div>
		<table class="table_custom">
			<tr>
				<th style="width:40px;">序号</th>
				<th style="width:100px;">送检科室</th>
				<th style="width:76px;">送检时间</th>
				<th style="width:76px;">检出时间</th>
				<th>标本</th>
				<th>检出菌</th>
				<th>多耐类型</th>
				<th>ESBL</th>
				<th>感染类型</th>
				<th>感染部位</th>
				<th>隔离情况</th>				
			</tr>
			<c:forEach items="${viewMdrList}" var="viewMdr" varStatus="status">
			<tr>
				<td><c:out value="${status.index + 1}" /></td>
				<td><c:out value="${viewMdr.surveyDeptName}" /></td>
				<td><fmt:formatDate value="${viewMdr.submiAt}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${viewMdr.dt}" pattern="yyyy-MM-dd" /></td>
				<td><c:out value="${viewMdr.lisitemName}" /></td>
				<td><c:out value="${viewMdr.lispathoName}" /></td>
				<td><c:out value="${viewMdr.resPropName}" /></td>
				<td><c:out value="${viewMdr.esbl}" /></td>
				<td><c:out value="${viewMdr.infectTypeName}" /></td>
				<td><c:out value="${viewMdr.grbw}" /></td>
				<td><c:out value="${viewMdr.gl == 2 ? '隔离中' : ''}" /></td>
			</tr>
			</c:forEach>
		</table>
	</div>	
	<!-- <div class="title mb10">常规异常<a name="4"class="lemma-anchor maodian"></a></div>
	<div>
		<table class="table_custom">
			<tr>
				<th style="width:40px;">序号</th>
				<th>送检科室</th>
				<th>送检时间</th>
				<th>检出时间</th>
				<th>送检项目</th>
				<th>检出项目</th>
				<th>多耐类型</th>
				<th>结果</th>
				<th>定性</th>
				<th>范围</th>			
			</tr>
			<tr>
				<td>1</td>
				<td>呼吸内科</td>
				<td>2016/07/05</td>
				<td>2016/07/05</td>
				<td>尿常规</td>
				<td>白细胞</td>
				<td>100</td>
				<td>↓</td>
				<td>定值</td>
				<td></td>
			</tr>
			<tr>
				<td>2</td>
				<td>呼吸内科</td>
				<td>2016/07/25</td>
				<td>2016/07/25</td>
				<td>尿常规</td>
				<td>细胞</td>
				<td>100</td>
				<td>↑</td>
				<td>定值</td>
				<td></td>
			</tr>
		</table>		
	</div>	 -->
	<div class="title mb10" >手术<a name="5"class="lemma-anchor maodian"></a></div>
	<div>
		<table class="table_custom">
			<tr>
				<th style="width:30px;">序号</th>
				<th style="width:140px;">手术科室</th>
				<th>手术医生</th>
				<th style="width:150px;">手术名称</th>
				<th style="width:70px;">切口类型</th>
				<th style="width:60px;">手术时长</th>
				<th style="width:60px;">连台手术</th>
				<th>麻醉类型</th>	
			</tr>
			<c:forEach items="${st005List}" var="st005" varStatus="status">
			<tr>
				<td><c:out value="${status.index + 1}" /></td>
				<td><c:out value="${st005.deptName}" /></td>
				<td><c:out value="${st005.opedocName}" /></td>
				<td><c:out value="${st005.operName}" /></td>
				<td><c:out value="${st005.incisionGrade}" /></td>
				<td><c:out value="${st005.operLengTime}" /><c:out value="${empty st005.operLengTime ? '' : '分钟'}" /></td>
				<td><c:out value="${st005.continuousOpe}"/></td>
				<td><c:out value="${st005.narcKind}" /></td>
			</tr>
			</c:forEach>
		</table>
	</div>	
	<div class="title mb10" >体温异常<a name="6"class="lemma-anchor maodian"></a></div>
	<div>
		<table class="table_custom">
			<tr>
				<th style="width:30px;">序号</th>
				<!-- <th>科室名称</th> -->
				<th>采集日期</th>
				<th>体温值</th>
				<!-- <th>说明</th> -->
			</tr>
			<c:forEach items="${st006List}" var="st006" varStatus="status">
			<tr>
				<td><c:out value="${status.index + 1}" /></td>
				<%-- <td><c:out value="${st006.deptName}" /></td> --%>
				<td><fmt:formatDate value="${st006.recordingAt}" pattern="yyyy-MM-dd HH:mm" /></td>
				<td><c:out value="${st006.twValues}" />℃</td>
				<%-- <td><c:out value="${st006.twValues}" /></td> --%>
			</tr>
			</c:forEach>
		</table>
	</div>	
	<!-- <div class="title mb10">大便异常<a name="7"class="lemma-anchor maodian"></a></div>
	<div class="mb60">
		<table class="table_custom">
			<tr>
				<th style="width:40px;">序号</th>
				<th>科室名称</th>
				<th>日期</th>
				<th>排便情况</th>
				<th>说明</th>
			</tr>
			<tr>
				<td>1</td>
				<td>呼吸内科</td>
				<td>2016/07/05</td>
				<td>5次</td>
				<td></td>
			</tr>
			<tr>
				<td>2</td>
				<td>呼吸内科</td>
				<td>2016/07/06</td>
				<td>2次</td>
				<td></td>
			</tr>
			<tr>
				<td>3</td>
				<td>呼吸内科</td>
				<td>2016/07/07</td>
				<td>3次</td>
				<td></td>
			</tr>
			<tr>
				<td>4</td>
				<td>呼吸内科</td>
				<td>2016/07/08</td>
				<td>2次</td>
				<td></td>
			</tr>
		</table>
	</div>	 -->
</div>

<div id="prevalence_note"> 
	<div class="side-catalog" style="visibility: visible; top: 10px;">
		<div class="side-bar">
			<em class="circle start"></em>
			<em class="circle end"></em>
		</div>
		<div class="catalog-scroller">
			<dl class="catalog-list">
				<dt class="catalog-title level1 complete">
					<em class="pointer"></em>
					<span class="text">
						<a href="#1" class="title-link" nslog-type="1026">抗菌药物使用</a>
					</span>
				</dt>
				<dt class="catalog-title level1 complete">
					<em class="pointer"></em>
					<span class="text">
						<a href="#2" class="title-link" nslog-type="1026">侵入性操作</a>
					</span>
				</dt>
				<dt class="catalog-title level1 complete">
					<em class="pointer"></em>
					<span class="text">
						<a href="#3" class="title-link" nslog-type="1026">检出菌</a>
					</span>
				</dt>				
				<!-- <dt class="catalog-title level1 complete">
					<em class="pointer"></em>
					<span class="text">
						<a href="#4" class="title-link" nslog-type="1026">常规异常</a>
					</span>
				</dt> -->
				<dt class="catalog-title level1 complete">
					<em class="pointer"></em>
					<span class="text">
						<a href="#5" class="title-link" nslog-type="1026">手术</a>
					</span>
				</dt>			
				<dt class="catalog-title level1">
					<em class="pointer"></em>
					<span class="text">
						<a href="#6" class="title-link" nslog-type="1026">体温异常</a>
					</span>
				</dt>
				<!-- <dt class="catalog-title level1">
					<em class="pointer"></em>
					<span class="text">
						<a href="#7" class="title-link" nslog-type="1026">大便异常</a>
					</span>
				</dt>	 -->					
				<a class="arrow" href="javascript:void(0);" style="top:5px;" ></a>
			</dl>
		</div>				
	</div>
</div> 
</div>
<script>

	$(function(){   
		//遍历锚点  
		var mds = $(".maodian")  
		var arrMd = [];  
		for(var i = 0, len = mds.length;i<len;i++){  
			arrMd.push($(mds[i]));  
		}  
		   
		function update(){  
		var scrollH = $(window).scrollTop();
		
		for(var i = 0;i<len;i++){  
				var mdHeight = arrMd[i].offset().top;  
				if(mdHeight < scrollH +50){var j = i; navon(j);}  
			}  
		}  
		   
		//高亮导航菜单  
		function navon(id){ 			
			$('.arrow').css({"top": id*46+5});	 
		}  
		
		//绑定滚动事件  
		$(window).bind('scroll',update); 
		
	}) 
</script>
</body>
</html>