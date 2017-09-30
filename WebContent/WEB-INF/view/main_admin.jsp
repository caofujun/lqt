<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${webroot}/favicon.ico" type="image/x-icon" />
<title>蓝蜻蜓医院感染实时监控平台-临床端</title>
<jsp:include page="/WEB-INF/view/core/include.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${webroot}/resources/css_org/layout.css${version}">
<script type="text/javascript" src="${webroot}/resources/JavaScript/Tdrag.js"></script>
<style type="text/css">
.batch-container .batch-msg-container {
    width: 670px;
    height: 465px;
    position: fixed;
    _position: absolute;
    bottom: 0;
    right: 20px;
    background: #fff;
    z-index: 200;
    box-shadow: 0 2px 5px rgba(0,0,0,0.3);
    -moz-box-shadow: 0 2px 5px rgba(0,0,0,0.3);
    -webkit-box-shadow: 0 2px 5px rgba(0,0,0,0.3);
    -ms-box-shadow: 0 2px 5px rgba(0,0,0,0.3);
    transition: transform .25s ease-in,opacity .25s ease-in;
    -webkit-transition: transform .25s ease-in,opacity .25s ease-in;
    -mos-transition: transform .25s ease-in,opacity .25s ease-in;
    -ms-transition: transform .25s ease-in,opacity .25s ease-in;
    transform: translateY(480px);
    -webkit-transform: translateY(480px);
    -ms-transform: translateY(480px);
    -mos-transform: translateY(480px);
    display: none \9;
}
.batch-container .batch-msg-container .batch-msg-header .batch-msg-close {
    float: right;
    font-size: 12px;
    cursor: pointer;
}
.batch-container .batch-msg-container .batch-msg-list {
	height: 430px;
    overflow: hidden;
}
</style>
</head>
<!-- 全局对话框 -->
<div id="_glod_dialog_" style="display:none" >_glod_dialog</div>
<body class="easyui-layout">
	<%@ include file="comm/header_admin.jsp"%>
	<%@ include file="comm/menu_admin.jsp"%> 
    <div data-options="region:'center',iframe:true,title:''" iconCls="icon-home">
    	 <div id="mainTabs" class="easyui-tabs" data-options="region:'center',border:false,fit:true,tools:'#id_main_tab_tools'">
			<c:if test="${isPatientList eq 0}">
				<div title="患者列表">
					<iframe width="100%" height="100%" name="donDeleteThisAttr" frameborder="0" src="${webroot}/bk001Sbk/f_view/index.shtml?ownership=clinical" scrolling="auto"></iframe>
				</div>
			</c:if>	
		</div> 
        <!-- <iframe id="show_win" scrolling="auto" width="100%" height="99%" frameborder="0" src=""></iframe> -->
    </div>
    <div id="id_main_tab_tools">   
        <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-stop'" onclick="menuInfo.stopHttp();" title="停止" href="javascript:;"></a>
    </div>
    <div id="rightTabMenu" class="easyui-menu" style="width: 100px;">
	    <div name="close" data-options="iconCls:'icon-cancel'">关闭</div>
	    <div name="refresh" data-options="iconCls:'icon-reload'">刷新</div>
	    <div name="closeAll">关闭全部</div>
	    <div name="closeOthor">关闭其他</div>
	</div>

<c:if test="${SHOWMSGBOX==1}">	
	<div id="mc" class="batch_btn "><i class="icon iconfont">&#xe63e;</i></div>
	<div class="batch-container" style="width:100%;height:100%;position: absolute; top:0px;display: none;z-index:10;">
		<div class="batch-msg-container">
			<div class="batch-msg-header" style="height: 32px;line-height: 32px;background: #293038;padding: 0 15px 0 20px;color: #fff;font-size: 14px;cursor: move;">消息盒子<i class="iconfont icon-close batch-msg-close"></i></div>
			<div class="batch-msg-list"></div>
		</div>
		<div style="clear:both;"></div>
	</div>
</c:if>

	<script type="text/javascript">
	$(function(){
		//监听右键事件，创建右键菜单
        $('#mainTabs').tabs({
            onContextMenu:function(e, title, index){
                e.preventDefault();
                if(index > 0){
                    $('#rightTabMenu').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    }).data('tabTitle', title);
                }
            },
            onSelect:function(title){
        		if("患者列表".indexOf(title)>=0){
        			//触发查询
        			var ct = $("#mainTabs").tabs('getTab',title);
        			if(ct.find("iframe").length>0){
        				var ifn = ct.find("iframe").eq(0).attr("name");
        				$(window.frames[ifn].document).find(".toTrigger").trigger("click");
        			}
        		}
            }
        });
	    //关闭当前标签页
	    $("#closecur").bind("click",function(){
	        var curTabTitle = $(menu).data('tabTitle');
            $("#mainTabs").tabs("close", curTabTitle);
	    });
	  //右键菜单click
        $("#rightTabMenu").menu({
            onClick : function (item) {
                var _type = item.name;
            	var _title = $(this).data('tabTitle');
    	        var _index = 0;
        		var _tabList = $('#mainTabs').tabs('tabs');
    	        for(var i = _tabList.length - 1; i >= 0; i--){
    	        	if(_tabList[i].panel('options').title == _title) {
    	        		_index = i;
    	        	}
    	        }
                switch(_type) {
                	case 'close':
	                    $('#mainTabs').tabs('close', _title);
	                	break;
                	case 'refresh':
                		//menuInfo.clickMenu(_title, menuInfo.clickMenus[_title].url, true, menuInfo.clickMenus[_title].menuNo);
                		menuInfo.refreshMenu(_title);
                		break;
                	case 'closeAll':
            	        for(var i = _tabList.length - 1; i >= 0; i--){
            	        	if(i > 0) {
            	            	$('#mainTabs').tabs('close', i);
            	        	}
            	        }
                		break;
                	case 'closeOthor':
                		var _tabList = $('#mainTabs').tabs('tabs');
            	        for(var i = _tabList.length - 1; i > _index; i--){
            	            $('#mainTabs').tabs('close', i);
            	        }
            	        var _num = _index - 1;
            	        for(var i = _num; i>=0; i--){
            	        	if(i > 0) {
            	            	$('#mainTabs').tabs('close', i);
            	        	}
            	        }
                		break;
                }
            }
        });
	  
<c:if test="${SHOWMSGBOX==1}">	  
        messageBox.getMessageCount();
		$(document).on("click", ".batch-msg-header .batch-msg-close",
		   function() {
				messageBox.hideMessageBox();
		   }
		);
		$("body").on("click",function(e){
			if($(e.target).parents(".batch-container").length<=0 && $(".ie8hack").length>0){
				window.parent.messageBox.hideMessageBox();
			}
		});
		//拖拽
		$(".batch-msg-header").on({
		    mousedown: function(e){
	           var el=$(".batch-msg-container");
	           var os = el.offset(); dx = e.pageX-os.left, dy = e.pageY-os.top;
	           $(document).on('mousemove.drag', function(e){
	        	   el.offset({top: e.pageY-dy, left: e.pageX-dx});
	           });
		   },
		   mouseup: function(e){ 
			   $(document).off('mousemove.drag'); 
		   }
		});
		
		var t = setInterval("messageBox.getMessageCount()",60000);
</c:if>		
	});
	
	
	var messageBox = {
			
			showMessageBox : function (){
				 $(".batch-container").show();
				/* $(document).on("click",function(){
					//var isClosed = $("#mydialog").dialog("options").closed;
					var isClosed = $("#message-container").is(":hidden");
					if(!isClosed){
						$("#message-container").dialog("close");
					}	
				}); */
				$(".batch-msg-container").show().css({filter:"alpha(opacity=1)","-moz-opacity":"1",opacity:"1",transform:"translateY(0)","-webkit-transform":"translateY(0)","-ms-transform":"translateY(0)","-mos-transform":"translateY(0)"}).addClass("ie8hack");
				/* Comm.dialog({
					dialogid:'message-container',
		        	url:'${webroot}/nyMessageBox/f_view/index.shtml',
		    		type: 'iframe',
		            title: '消息盒子',
		    		width: 760,
		    		height: 520,
		    		modal: false,
		    		closeFn: function(){
		    			$(".batch_btn").show();
		    			messageBox.getMessageCount();
		    		}
		        }); */
				$(".batch_btn").hide();
		       
				messageBox.getMessageList();
			},
			hideMessageBox : function (){
		        var B = $(".batch-msg-container");
		        if (B.offset().top + B.height() >= $(window).height() + $(window).scrollTop()) {
		            B.css({
		                transform: "translateY(550px)",
		                "-webkit-transform": "translateY(550px)",
		                "-ms-transform": "translateY(550px)",
		                "-mos-transform": "translateY(550px)"
		            }).removeClass("ie8hack");
		            setTimeout(function(){
		            	$(".batch-container").hide();
		            },300);
		        } else {
		            B.css({
		                filter: "alpha(opacity=0)",
		                "-moz-opacity": "0",
		                opacity: "0"
		            }).removeClass("ie8hack");
		            setTimeout(function() {
		                B.hide();
		                setTimeout(function(){
			            	$(".batch-container").hide();
			            },300);
		            },
		            300);
		        }
		        $(".batch_btn").show();
		        n = false;
		        //刷新未读信息
		        messageBox.getMessageCount();
			},
			getMessageList:function(){
				var url="${webroot}/nyMessageBox/f_view/index.shtml";
				var data={};
				$(".batch-msg-list").load(url,function(){
				    MsgPagination.queryMsg();
				    
				});
			},
			getMessageCount : function(){
				$("#mc").removeClass("ms");
				$.ajax({
					url:"${webroot}/nyMessageBox/f_json/unreadMsgCount.shtml",
					type:"POST",
					success:function(data){
						if(data>0){
							if($(".msgCount").length>0){
								$(".msgCount").html(data);
							}else{
								$(".batch_btn").append("<span class='msgCount'>"+data+"</span>");
							}
							$(".msgCount2").show();
							$(".msgCount2").html(" ("+data+")");
							$("#mc").addClass("ms");
						}else if(data==0){
							$(".msgCount").remove();
							$(".msgCount2").hide();
						}
					}
				});
			}
	};

	function stopPropagation(e){
	    e= e || window.event;
	    if(document.all){  //只有ie识别
	        e.cancelBubble=true;
	    }else{
	        e.stopPropagation();
	    }
	}
	
	//消息图标的拖拽
	$(document).ready(function(){
		var move = 0;
		$("#mc").Tdrag({
			scope:"。easyui-layout",
			cbMove:function(){
			move = move + 1;			
			}
						
		});	
	  $("#mc").click(function(e){
		if(move == 0){
			stopPropagation(e);
			messageBox.showMessageBox();			
		}
		else{
			move = 0;
			return false;
		}		
	  });
	});
	</script>
</body>
</html>