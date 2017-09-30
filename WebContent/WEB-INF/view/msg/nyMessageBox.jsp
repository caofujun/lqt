<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- <div class="easyui-window" title="消息" data-options="width:700,height:510,left:10,top:10,inline:true"> -->
		<div class="w-content message_box">
			<div class="window_r_b"><a  href="javascript:void(0);" onclick="mark.markAllAsRead();messageBox.getMessageCount();">此页标为已读</a></div>
			<div id="msg-tabs" style="width:100%;height:450px;">
				<ul id="tt" class="tabs" style="height: 26px;padding-top: 3px;">
					<li class="tabs-first "><a href="javascript:void(0)" onclick="$('#tt li').removeClass('tabs-selected');$(this).closest('li').addClass('tabs-selected');MsgPagination.queryMsg();" class="tabs-inner" style="height: 25px; line-height: 25px;"><span class="tabs-title">全部消息</span></a></li>
					<li class="tabs-last tabs-selected"><a href="javascript:void(0)" onclick="$('#tt li').removeClass('tabs-selected');$(this).closest('li').addClass('tabs-selected');MsgPagination.queryMsg();" class="tabs-inner" style="height: 25px; line-height: 25px;"><span class="tabs-title">未读消息</span><span class="msgCount2"></span></a></li>
				</ul>
				<div id="MsgPlace" style="height: 355px;overflow: auto;"></div>
				<div id="PagingPart" class="pager-his" style="display: none;">
					<input type="hidden" id="size" class="size" value="8"/>
					<input type="hidden" id="page" class="page" value=""/>
					<input type="hidden" id="totalPage" class="totalPage" value=""/>
					<div>页数:<font id="curPage"></font>/<font id="Pages"></font>&nbsp;
						<a href="#" onclick="MsgPagination.pageTurning('first');">首页</a>
						<a href="#" onclick="MsgPagination.pageTurning('prev');">上一页</a>							
						<a href="#" onclick="MsgPagination.pageTurning('next');">下一页</a>
						<a href="#" onclick="MsgPagination.pageTurning('last');">末页</a>
					</div>
				</div>
			</div>
		
		</div>
	<!-- </div> -->
	<!-- <div class="batch_btn"><i class="icon iconfont">&#xe63e;</i><span>95</span></div> -->
	    <script type="text/javascript">
	    	/* $("#msg-tabs").tabs({
	    		onSelect:function(title){
	    			if(title=="未读消息"){
	    				$("#MsgPlace").load("${webroot}/nyMessageBox/f_json/getUnreadMsgs.shtml",function(){pagination();});
	    			}
	    		}
	    	}); */
	    	
	    /* $('.easyui-window').window({
	            collapsible: false,
	            minimizable: false,
	            maximizable: false,
	            closable: true
	        }); */
	       
	        var MsgPagination = {
	        	queryMsg : function(){
	        		var title = $("#msg-tabs").find(".tabs-selected").find(".tabs-title").eq(0).html();
	        		//clear container before load msg
	        		$("#MsgPlace").html('<span style="text-align: center;display: block;margin-top: 80px;color: #c5c2c2;font-size: 20px;">加载中...</span>');
	        		if(title=="未读消息"){
	    				$("#MsgPlace").load("${webroot}/nyMessageBox/f_json/getUnreadMsgs.shtml",function(){pagination();$("#PagingPart").show();});
	    			}else if(title=="全部消息"){
	    				$("#MsgPlace").load("${webroot}/nyMessageBox/f_json/getAllMsgs.shtml",{"isRead":2},function(){pagination();$("#PagingPart").show();});
	    			}
	        	},
	       		pageTurning : function(updown){
	       			var title = $("#msg-tabs").find(".tabs-selected").find(".tabs-title").eq(0).html();
	       			var URMCount = $("#msg-tabs").find(".tabs-selected").find(".msgCount2").eq(0).html();
	       			var isRead = "";
	       			if(title=="未读消息"){
	       				isRead = "0";
	       			}else if(title=="全部消息"){
	       				isRead = "2";
	    			}
	       			
	       			var curPage = parseInt($("#page").val());
	       			var totalPage = parseInt($("#totalPage").val());
	       			var size = 8;
	       			var orclBegNum = 1;
	       			var orclEndNum = size;
	       			var page = 1;
	       			
	       			//防止消息在别处被阅读后导致数据不对称，加个验证
	       			if(isRead==0 && totalPage!=URMCount/size){
	       				updown=="first";
	       			}
	       			
					if(updown=="next"){
	       				//下一页
	       				if(curPage<totalPage){
	       					orclBegNum = curPage*size+1;
	       					orclEndNum = (curPage+1)*size;
	       					page=curPage+1;
	       				}else{
	       					return;
	       				}
	       			}else if(updown=="prev"){
	       				//上一页
						if(curPage>1){
							orclBegNum = (curPage-2)*size+1;
	       					orclEndNum = (curPage-1)*size;
	       					page=curPage-1;
	       				}else{
	       					return;
	       				}
	       			}else if(updown=="first"){
	       				if(curPage!=1){
		       				//首页
		       				orclBegNum = 1;
	       					orclEndNum = 1*size;
	       				}else{
	       					return;
	       				}
	       			}else if(updown=="last"){
	       				if(curPage!=totalPage){
		       				//末页
		       				orclBegNum = (totalPage-1)*size+1;
		       				orclEndNum = totalPage*size;
		       				page=totalPage;
	       				}else{
	       					return;
	       				}
	       			}else{
	       				return;
	       			}
	       			
	       			$("#MsgPlace").load("${webroot}/nyMessageBox/f_json/getAllMsgs.shtml",
	       				{"orclBegNum": orclBegNum,"orclEndNum": orclEndNum,"page":page,"size":size,"isRead":isRead},
	       				function(){
	       					pagination();
	       				}
	       			);
	       		}
	        };
	        
	        var mark = {
	       		markAsRead : function(mid,themeId){
	       			$.ajax({
	       				url:"${webroot}/nyMessageBox/f_json/markAsRead.shtml",
	       				data:{
	       					mids:mid,themeIds:themeId
	       				},
	       				success:function(data){
	       					data = eval("("+data+")");
	       					if(data.result=="error"){
	       						$.messager.show({ title: '提示', msg: data.msg });
	       					}else{
	       						//$.messager.show({ title: '提示', msg: data.msg });
		       					MsgPagination.queryMsg();
	       					}
	       				},error:function(){
	       					alert("抱歉，更新已读信息失败！");
	       				}
	       			});
	       		},
	       		markAllAsRead : function(){
	       			var mids = "";
	       			var themeIds = "";
	       			$("#MsgPlace").find(".message_li").each(function(index){
	       				mids += $(this).find(".mid").val()+"|";
	       				themeIds += $(this).find(".themeId").val()+"|";
	       			});
	       			//
	       			if(mids.length>10){
	       				mids = mids.substring(0, mids.length-1);
	       			}
	       			if(themeIds.length>10){
	       				themeIds = themeIds.substring(0, themeIds.length-1);
	       			}
	       			//
	       			if(mids.length>10){
		       			mark.markAsRead(mids, themeIds);
	       			}else{
	       				$.messager.show({ title: '提示', msg: '消息盒子空了！' });
	       			}
	       		}
	       	}
	       
		</script>

