<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script>
	var indexs;
	$(document).ready(function () {	
		 $.ajax({
	            url: '${webroot}/fxPatient/f_view/getzyId.shtml',
	            type: 'post',
	            data: { zyId: '${zyxx.zyid}' },
	            dataType: 'json',
	            success : function(json) {
					if(json.result==='success') {
						$(".fishBone").fishBone(json.data);
						//document.getElementById("li_0_0").click();
			    	}
				}
			});	
	});
	//异步提示
	function lookzy(o,pzId,type){
		if(type=='pdca'){
			$.post('${webroot}/fxPatientZb/f_view/getPdcaBypzId.shtml?pzId='+pzId,function (data){
			     if (data.result == 'success' && data.msg) {
			    	indexs = layer.tips(data.msg, o, {
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
			    	indexs = layer.tips(data.msg, o, {
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
		layer.close(indexs); 
	}
	
	//导航条点击事件
	function nOnClickEvent(index,index2,pzId,pzStatus){
		//清空选中样式
		$('.bd').find("li").css({'color':'','font-weight':''});
		var url = "";
		if(pzStatus==0||pzStatus==2){//风险因素
			url="${webroot}/fxPatientZb/f_view/edit.shtml?pzId="+pzId;
		}else if(pzStatus==3){//已通过感染部位
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid=${zyxx.zyid}&tab=10',true);
			//url="${webroot}/gr002YsgrMx/f_view/toWarningResults.shtml?gr2Relid="+pzId+"&zyid=${zyxx.zyid}&incTpye=1";
		}else if(pzStatus==4){//预警
			parent.parent.menuInfo.clickMenu('患者档案','/st003Cryxxb/f_view/toPatientRecords.shtml?zyid=${zyxx.zyid}&tab=10',true);
			//url="${webroot}/gr002YsgrMx/f_view/toWarningResults.shtml?regId="+pzId+"&zyid=${zyxx.zyid}&incTpye=1";
		}else if(pzStatus==5){//评估表
			parent.parent.menuInfo.clickMenu('评估表','/fxPatientZb/f_view/getRid.shtml?rid='+pzId,true);
			//url="${webroot}/fxPatientZb/f_view/getRid.shtml?rid="+pzId;
		}
		//当前点击被选中
		$('#li_'+index+'_'+index2).css({'color':'#1b60ad','font-weight':'bold'});
		$('#tagNavIframe').attr("src",url);
	}
</script>

<div>
	<div class="fishBone" />
</div>
<iframe id="tagNavIframe" src="" scrolling="no" width="100%" height="140" marginheight="0" frameborder="0"></iframe> 