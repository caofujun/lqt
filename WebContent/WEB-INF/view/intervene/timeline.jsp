<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<div class="sf_record_box">
		<div class="sf_record_menu">
		<!-- 	<div class="sf_record_menu_header">
				<span class="standard_select">
					<span class="select_shelter">
						<select id="" name="" style="width:100px;" >
							<option value="">全部记录</option>
							<option value="">门诊</option>
							<option value="">出院</option>
							<option value="">随访</option>
						</select>
					</span>
				</span>
		
			</div> -->
			<div id="_nav_ul" style="overflow: auto;">
				<c:forEach var="patientZb"  items="${patientZbList}" varStatus="status"> 
				<dl>
					<dt class="sf_record_menu_year">${patientZb.charDate}</dt>
					<c:forEach var="pz" items="${patientZb.pzList}" varStatus="st">
					<c:if test="${pz.pzStatus==null}"><dd class="red"></c:if>
					<c:if test="${pz.pzStatus==0}"><dd class="red"></c:if>
					<c:if test="${pz.pzStatus==1}"><dd class="gray"></c:if>
					<c:if test="${pz.pzStatus==2}"><dd class="green"></c:if>
					<c:if test="${pz.pzStatus==3}"><dd class="orange"></c:if>
					<c:if test="${pz.pzStatus==4}"><dd class="orange"></c:if>
					<c:if test="${pz.pzStatus==5}"><dd class="orange"></c:if>
					<a id="_nav_item_${status.index}_${st.index}" class="_nav_item" href="#" onclick="nOnClickEvent('${status.index}',${st.index},'${pz.pzId}','${pz.pzStatus}')" >${pz.zbName}</a><c:if test="${pz.pdcaStatus>0}"><span onmouseover="lookzy(this,'${pz.pzId}','pdca')" onmouseout="closeTip()">P</span></c:if> <c:if test="${pz.qsStatus>0}"><span onmouseover="lookzy(this,'${pz.pzId}','qs')" onmouseout="closeTip()" style="background-color:${pz.fxColor}">D</span></c:if></dd>
					</c:forEach>
				</dl>
				</c:forEach>
			</div>
		</div>
		<div class="sf_record_cont" id="sf_record_cont">
			<iframe id="tagNavIframe" src=""  scrolling="no" width="100%" marginheight="0" frameborder="0"  onload="AutoIframe();getHeight()"></iframe>			
		</div>		
	</div>	

	<script type="text/javascript">
	var index;
	$(document).ready(function () {	
		//第一个菜单默认展开
		 document.getElementById("_nav_item_0_0").click();
		
		function shrinkage(num){		
			if(num == "0")
			{
				$(".infor_short").hide();
				$(".infor_long").show();
			}else{
				$(".infor_long").hide();
				$(".infor_short").show();
			}		
		}
		shrinkage("0");	
	});
	//异步提示
	function lookzy(o,pzId,type){
		if(type=='pdca'){
			$.post('${webroot}/fxPatientZb/f_view/getPdcaBypzId.shtml?pzId='+pzId,function (data){
			     if (data.result == 'success' && data.msg) {
			    	index = layer.tips(data.msg, o, {
			    	        maxWidth:300,
			    	        time: 0,
			    	        tips: [1, '#3595CC'],
			    	        closeBtn:0			    	     
			    	 });
				 }
			 },'json');
		}else if(type=='qs'){
			$.post('${webroot}/fxPatientZb/f_view/getQsBypzId.shtml?pzId='+pzId,function (data){
			     if (data.result == 'success' && data.msg) {
			    	index = layer.tips(data.msg, o, {
			    	        maxWidth:300,
			    	        time: 0,
			    	        tips: [1, '#3595CC'],
			    	        closeBtn:0
			    	 });
				 }
			 },'json');
		}
	}
	
	function closeTip(){
		layer.close(index); 
	}
	//导航条点击事件
	function nOnClickEvent(index,index2,pzId,pzStatus){
		//清空选中样式
		$('._nav_item').removeClass('cur');
		var url = "";
		if(pzStatus==0||pzStatus==2){//风险因素
			url="${webroot}/fxPatientZb/f_view/edit.shtml?pzId="+pzId;
		}else if(pzStatus==3){//已通过感染部位
			url="${webroot}/gr002YsgrMx/f_view/toWarningResults.shtml?gr2Relid="+pzId+"&zyid=${zyxx.zyid}";
		}else if(pzStatus==4){//预警
			url="${webroot}/gr002YsgrMx/f_view/toWarningResults.shtml?regId="+pzId+"&zyid=${zyxx.zyid}";
		}else if(pzStatus==5){//评估表
			url="${webroot}/fxPatientZb/f_view/getRid.shtml?rid="+pzId;
		}
		//当前点击被选中
		
		$('#_nav_item_'+index+'_'+index2).addClass('cur');
		$('#tagNavIframe').attr("src",url);
	}
	
	//获取档案详情部分的高度
	function getHeight() {
		var h1;
		h1= $(".sf_record_box").height();
		$("#_nav_ul").css("height",h1+"px");
	}

	
	function AutoIframe()
	{
	    if(document.readyState!='complete')
	    {
	        setTimeout( function(){AutoIframe();},25 );
	        return;
	    }else {
	      var ifobj=document.getElementById("tagNavIframe");
	      ifobj.height= ifobj.contentWindow.document.body.scrollHeight;
	    };
	}
	</script>