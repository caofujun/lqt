<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%-- <%@ include file="/WEB-INF/view/core/phone.jsp"%> --%>
<div id="header" data-options="region:'north',border:false">
 	 	<%@ include file="/WEB-INF/view/core/lqtMessage.jsp"%>
	<div id="logo"><div class="header_v">V7.0</div><div class="hospital"></div></div>
<!--    	<input type="button" value="ceshisave" onClick=" nyTellEvent.OnCallInFunc('13975168547')">  -->
<!--	<input type="button" value="ceshizhuyuan" onClick=" Comm.dialog({url:'${webroot}/callIn/index.action?telPhone=13975168547',type:'iframe',title:'来电',height:500,width:1000})">-->
		<div class="quickmenu" id="parentMenuPanel">
        	<%-- <ul>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_02.gif">随访管理</a></li>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_03.gif">患者管理</a></li>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_04.gif">评价反馈</a></li>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_05.gif">满意度调查</a></li>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_07.gif">统计分析</a></li>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_08.gif">短信平台</a></li>
				<li><a href="javascript:openTelDialog()"><img src="${webroot}/resources/images/admin/menu_ico_06.gif">系统设置</a></li>
				<li><a class="last" href="${webroot}/account/logout.action" target="_top"><img src="${webroot}/resources/images/admin/menu_ico_exit.gif">退出系统</a></li>
			</ul> --%>
        </div>
    
  <!--  <div id="toolbar">
		<ul>
        	<li><span>${hp_account_key.cname}</span>，您好，欢迎登录！</li>
			<%-- <li><a href="#"class="icon_role">工具选项</a>
				<div class="popup_menu" style=" ">
                   <ul>
						<li><a href="">切换角色</a></li>
                        <li><a href="">信息设置</a></li>
                        <li><a href="javascript:Comm.dialog({url: '${webroot}/account/toaddchangPassword.action', title:'修改密码'})">修改密码</a></li>
                  </ul>
				</div>
			</li> --%>
			<li><a class="exit" href="${webroot}/account/logout.action" target="_top">退出</a></li>
        </ul>
	</div> -->
</div>
<script type="text/javascript">
var menuInfo = {
		//一级菜单
		parentMenus : [],
		//自己拥有的所有菜单
		menuJson : [],
		clickMenus : {},
		init : function() {
			/* menuInfo.parentMenus['fo'] = '<li><a href="javascript:;" onclick="menuInfo.parentSel(this,\'fo\')"><img src="${webroot}/resources/images_org/admin/menu_ico_02.gif">随访管理</a></li>';
			menuInfo.parentMenus[88] = '<li><a href="javascript:;" onclick="menuInfo.parentSel(this,88)"><img src="${webroot}/resources/images_org/admin/menu_ico_03.gif">患者管理</a></li>';
			menuInfo.parentMenus[397] = '<li><a href="javascript:;" onclick="menuInfo.parentSel(this,397)"><img src="${webroot}/resources/images_org/admin/menu_ico_04.gif">评价反馈</a></li>';
			menuInfo.parentMenus[365] = '<li><a href="javascript:;" onclick="menuInfo.parentSel(this,365)"><img src="${webroot}/resources/images_org/admin/menu_ico_05.gif">满意度调查</a></li>';
			menuInfo.parentMenus[11300000401] = '<li><a href="javascript:;" onclick="menuInfo.parentSel(this,11300000401)"><img src="${webroot}/resources/images_org/admin/menu_ico_08.gif">短信平台</a></li>';
			menuInfo.parentMenus['sys'] = '<li><a href="javascript:;" onclick="menuInfo.parentSel(this,\'sys\')"><img src="${webroot}/resources/images_org/admin/menu_ico_06.gif">系统设置</a></li>';
			 */
			<c:forEach items="${user_menus}" var="menu">
			<c:if test="${(menu.parentMenuNo==null || menu.parentMenuNo=='') && menu.ownership=='hospital' && menu.menuType=='0'}">
			menuInfo.parentMenus['${menu.menuNo}'] = '<li><a href="javascript:;" onclick="menuInfo.parentSel(this,\'${menu.menuNo}\')"><img src="${webroot}${menu.image}">${menu.menuName}</a></li>';
			
			menuInfo.menuJson.push({ menuId:'${menu.menuNo}',menuName:'${menu.menuName}',children:[
			<c:forEach items="${user_menus}" var="cldMenu">
			<c:if test="${menu.menuNo==cldMenu.parentMenuNo && cldMenu.ownership=='hospital' && cldMenu.menuType=='0'}">
			{ menuId:'${cldMenu.menuNo}',menuName:'${cldMenu.menuName}',destUrl:'${cldMenu.destUrl}',children:[
			
			<c:forEach items="${user_menus}" var="thirdMenu">
			<c:if test="${cldMenu.menuNo==thirdMenu.parentMenuNo && thirdMenu.ownership=='hospital' && thirdMenu.menuType=='0'}">
			{ menuId:'${thirdMenu.menuNo}',menuName:'${thirdMenu.menuName}',destUrl:'${thirdMenu.destUrl}' },
			</c:if>
			</c:forEach>
			null ]
			},
			</c:if>
			</c:forEach>
			null ] });
			
			</c:if>
			</c:forEach>
			
			var _pMenuArr = ['<ul>'];
			$.each(menuInfo.menuJson, function(i, obj) {
				_pMenuArr.push(menuInfo.parentMenus[obj.menuId]);
			});
			if('${isAdmin}'==='1') {
				_pMenuArr.push('<li><a class="last" href="javascript:void(0);" onclick="closeMe();" target="_top"><img src="${webroot}/resources/images_org/admin/menu_ico_exit.gif">关闭</a></li></ul>');
			} else {
				_pMenuArr.push('<li><a class="last" href="javascript:logout();" target="_top"><img src="${webroot}/resources/images_org/admin/menu_ico_exit.gif">退出系统</a></li></ul>');
			}
			$('#parentMenuPanel').append(_pMenuArr.join(''));
			//$("#parentMenuPanel ul li a").eq(0).click();
		},
		//选择一级菜单
		parentSel : function(_this, menuId, isInitMenu) {		
			/* var _display = $('.layout-panel-west').css('display');
			if(_display === 'none') {
				$("body.easyui-layout").layout('expand','west');
			}; */
			var menuName = $(_this).html().split(">")[1];
			if(menuId!='J'){				
				menuInfo.refreshMain(menuName,'${webroot}/acMenu/f_view/acMenuIndex.shtml?menuNo='+menuId,menuId);
			}else{
				menuInfo.refreshMain(menuName,'${webroot}/user/f_view/main_info.shtml',menuId);
			}
			$("#parentMenuPanel ul li a").each(function(i, obj) {
				$(obj).removeClass();
			});
			$(_this).addClass('cur');
			$.each(menuInfo.menuJson, function(i, obj) {
				if(menuId == obj.menuId) {
					var _html = [];//'<div class="leftmenus_box"><h3 onclick="menuInfo.clickMenu(\'首页\',\'/account/home.action\')">网页首页</h3></div>'];
					$.each(obj.children, function(j, objCld1) {
						if(objCld1 != null) {
							_html.push('<div class="leftmenus_box" menuId="'+objCld1.menuId+'" ><h3 title="',objCld1.menuName,'" id="leftmenus_box_',objCld1.menuId,'" onclick="menuInfo.clickMenu(\'',objCld1.menuName,'\',\'',objCld1.destUrl,'\',undefined,\'',objCld1.menuId,'\')">',objCld1.menuName);
							if(objCld1.children.length > 1) {
								_html.push('<span></span></h3><ul class="m-treeview">');
								$.each(objCld1.children, function(j, objCld2) {
									if(objCld2 != null) {
										_html.push('<li><dl><dt id="leftmenus_box_dt_',objCld2.menuId,'" onclick="menuInfo.clickMenu(\'',objCld2.menuName,'\',\'',objCld2.destUrl,'\',true,\'',objCld2.menuId,'\')"><a title="',objCld2.menuName,'" href="javascript:;">',objCld2.menuName,'</a></dt></dl></li>');
									}
								});
								_html.push('</ul>');
							} else {
								_html.push('</h3>');
							}
							_html.push('</div>');
						}
					});
					$('#leftmenus').html(_html.join(''));
					
					//初始化左侧菜单点击事件
					mainMenu(isInitMenu);
					return true;
				}
			});
		},
		closeMenu : function (name) {
			if($('#mainTabs').tabs('exists', name)){
				 $('#mainTabs').tabs('close', name);
			}
		},
		//点击菜单右边跳转
		clickMenu : function(name, url, isReload, menuNo, urlPrefix, fromTabBodyId, position) {
			if(url == '') {
				return;
			}
			if (urlPrefix) {
				url = urlPrefix + url;
			}
			if(!isReload) {
				name = '&nbsp;' + name + '&nbsp;';
			} 
			menuInfo.clickMenus[name] = {menuNo:menuNo, url:url};
			var _tabId = 'tab_body_' + menuNo;
			if(url.indexOf('http://')== -1 && url.indexOf('https://')== -1) {
				 if(url.lastIndexOf('?') != -1) {
					url += '&';
				} else {
					url += '?';
				} 
				url = webroot + url;
				url += 'ownership=hospital&tabBodyId=' + _tabId;
				if (fromTabBodyId && fromTabBodyId.length > 0) {
					url += '&fromTabBodyId=' + fromTabBodyId;
				}
				if (position && position.length > 0) {
					url += position;
				}
			} else if(menuNo!='pt_note_info') {
				//包含http链接
				var menuUrl = url;
				url = webroot + '/menu/f_view/to.shtml?menuNo=' + menuNo;
				if (!menuNo || menuNo.length == 0) {
					url += '&menuUrl=' + menuUrl;
				}
			}
			if(!$('#mainTabs').tabs('exists', name)){
			    $('#mainTabs').tabs('add', {
			    	title: name,
	                content: '<iframe name="'+_tabId+'"  id="'+_tabId+'" width="100%" height="100%" frameborder="0" src="'+url+'" scrolling="auto"></iframe>',
	                closable: true
			    });
			}else{
				$('#mainTabs').tabs('select', name);
				if(isReload) {
					var current_tab = $('#mainTabs').tabs('getSelected');
					$('#mainTabs').tabs('update',{
					     tab: current_tab,
					     options : {
					    	 content: '<iframe name="'+_tabId+'"  id="'+_tabId+'" width="100%" height="100%" frameborder="0" src="'+url+'" scrolling="auto"></iframe>'
					     }
					});
				}
			}
			/* if($('body').layout('panel', 'center') != ''){
				var _cont = ['<table width="100%" cellpadding="0" cellspacing="0" ><tr><td>',title,'</td>',
				'<td align="right"><div id="menuTipRightPanel"></div></td></tr></table>'].join('');
			    $('body').layout('panel', 'center').panel({
			    	title: _cont,
			    	iconCls: 'icon-home',
			    	headerCls: 'nav-title'
			    });
			}
			if(url.length > 0) {
				$('#show_win').attr('src', webroot + url);
			} */
		},
		getCurSelectTabTitle : function () {
			var currTabTitle =$('#mainTabs').tabs('getSelected').panel('options').title; 
			return currTabTitle;
		},
		getCurSelectTabPanelOpts : function () {
			var currTabPanelOpts = $('#mainTabs').tabs('getSelected').panel('options'); 
			return currTabPanelOpts;
		},
		//获取当前选中的tab标题
		closeCurSelectTab : function () {
			var currTab =$('#mainTabs').tabs('getSelected').panel('options').title; 
			$('#mainTabs').tabs('close', currTab);
		},
		//刷新tab
		refreshMenu : function (name) {
			if($('#mainTabs').tabs('exists', name)){
				var currTab = $('#mainTabs').tabs('getTab', name);
		        //获取tab的iframe对象  
		        var tbIframe = currTab.find('iframe:first-child'); 
		        var url = tbIframe.attr('src');
		        var id = tbIframe.attr('id');
				$('#mainTabs').tabs('update',{     
					tab : currTab,     
					options : {          
						content : '<iframe name="' + id + '" id="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>' 
					}
				});
			}
		},
		//刷新首页
		refreshMain : function(name, url, menuNo) {
			menuInfo.clickMenus[name] = {menuNo:menuNo, url:url};
			var mainTab = $('#mainTabs').tabs('getTab', 0);
			//获取tab的iframe对象  
	        var tbIframe = mainTab.find('iframe:first-child'); 
			var id = tbIframe.attr('id');
			$('#mainTabs').tabs('update',{     
				tab : mainTab,     
				options : {
					title : name,
					content : '<iframe name="' + id + '" id="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>'
				}
			});
			$('#mainTabs').tabs('select', 0);
		}/* ,
		//更新菜单提示栏右边的内容
		changeMtipRight : function(val) {
			$('#menuTipRightPanel').html(val);
		} */
};
function logout() {
	if(location.href.indexOf('https://') != -1) {
		location = '${webroot}/user/f_view/logout.shtml';
		return;
	}
	//不在91160平台退出
	location = '${webroot}/user/f_view/logout.shtml';
	/* $('body').append('<iframe id="logoutIframe" style="display:none;" src="http://h.91160.com/sz/index.php?c=User&a=logout"></iframe>');
	$("#logoutIframe").load(function(){
		location = '${webroot}/user/f_view/logout.shtml';
    }); */
	/* $.ajax({
		url: 'http://h.91160.com/sz/index.php?c=User&a=logout',
		dataType: 'text',
		success: function(json) {
			alert(json);
			return;
			location = '${webroot}/user/f_view/logout.shtml';
		}
	}); */
}
$(function() {
	menuInfo.init();
});

function closeMe() { 
    window.opener = null;  
    window.open("", "_self");  
    window.close(); 
} 


/*根据屏幕宽度自动调整医院名称的显示宽度*/
var width_hospital=0;
var menuNumber = 1;
$(function() {
	$(".quickmenu ul li").each(function(index, element) {
        menuNumber = index+1;        
		});	
	width_hospital = document.body.offsetWidth -menuNumber*80- 240;
	$(".hospital").css({"width": width_hospital+"px"});		
	window.onresize = function(){		
	width_hospital = document.body.offsetWidth -menuNumber*80- 240;	
	$(".hospital").css({"width": width_hospital+"px"});		
}     
});
</script>