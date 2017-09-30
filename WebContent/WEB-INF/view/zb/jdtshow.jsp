<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="nis" uri="/WEB-INF/tld/nis.tld" %>
<div id = "zbjdtshow" class="prog_box">
	<div class="prog_title">感染直报正在上报中，请您耐心等待</div>
	<div class="prog_text" ><span id = "progName">正在加载数据...</span></div>
	<div class="prog">
	  <div class="progbar" id="progbarValue" style="width: 0.5%;" ></div>
<!-- 	  <span id="progbarRate"></span> -->
	</div>
</div>
   <script type="text/javascript">
   $(document).ready(function () {
	  var tempa = setInterval(function(){
		    //要执行的代码
			rateProgrss.change(zbRecord.itemName,zbRecord.yibao);
		    if(parseInt(zbRecord.isEnd) == parseInt(zbRecord.listLength)){
 				clearInterval(tempa);
		    }
		},50); 
	});
   
	$('.easyui-window').window({
		collapsible: false,
		minimizable: false,
		maximizable: false,
		closable: true
	});
	
	var rateProgrss = {
		change : function(itemName,yibao){
			//找到对应的分母
			var mapList = zbRecord.unReports;
			var rate = 0; 
			for (var i = 0; i < mapList.length; i++) {
				var j = JSON.parse(mapList[i]);
				itemName=itemName.replace('"','').replace('"','');
				if(j.key1 == itemName){
					rate = (parseInt(yibao)/parseInt(j.key2))*100;
					rate = rate.toFixed(2);//
					document.getElementById('progbarValue').style.width = rate+"%";
					$('#progName').html(itemName+"上报中..."+rate+"%");
// 					$('#progbarRate').html(rate+"%");
				}
			}
			
		},
		close: function(){
			Comm.dialogClose('${param.dialogId}');
		}
	};
		
	</script>

