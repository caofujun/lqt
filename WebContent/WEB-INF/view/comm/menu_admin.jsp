<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<div data-options="region:'west',split:false,collapsed:false" title="" style="width: 76px;">
	<nav class="main_menu">
		<ul id="nav">
			<c:forEach items="${user_menus}" var="menu" varStatus="st">
			<c:if test="${(menu.parentMenuNo==null || menu.parentMenuNo=='') && menu.ownership=='clinical' && menu.menuType=='0'}">
			<c:choose>
			<c:when test="${menu.destUrl!=null}">
			<li id="menu_${st.index+1}"  >
				<a href="javascript:clickThirdMenu('${menu.menuName}','${menu.destUrl}',true,'${menu.menuNo}');" onmouseover="showmenu('menu_${st.index+1}','${menu.menuNo}');">
					 <i class="icon nisfont">${menu.image}</i> 
					<span class="nav_text">${menu.menuName}</span>
				</a>				
			</li>
			</c:when>
			<c:otherwise>
<%-- 				<c:if test="${deptType=='ICU'}"> --%>
					<li id="menu_${st.index+1}" >
						<a href="javascript:void(0);"  onmouseover="showmenu('menu_${st.index+1}','${menu.menuNo}');">
							 <i class="icon nisfont">${menu.image}</i>					
							<span class="nav_text">${menu.menuName}</span>
						</a>				
					</li>
<%-- 				</c:if> --%>
			</c:otherwise>
			</c:choose>

			</c:if>
			</c:forEach>		
		</ul>			
	</nav>
</div>
<div>
	<c:forEach items="${user_menus}" var="menu">
	<c:if test="${(menu.parentMenuNo==null || menu.parentMenuNo=='') && menu.ownership=='clinical' && menu.menuType=='0'}">
	<ul id="${menu.menuNo}"  onmouseleave="hidemenu('','${menu.menuNo}');" class="second_menu" >
	<c:forEach items="${user_menus}" var="cldMenu">
	<c:if test="${menu.menuNo=='H' && cldMenu.isreport=='1' && cldMenu.menuType=='0'}">
		<li>	
			<c:choose>
				<c:when test="${cldMenu.destUrl!=null}">
				<a href="javascript:clickThirdMenu('${cldMenu.menuName}','${cldMenu.destUrl}',true,'${cldMenu.menuNo}','${cldMenu.menuNo}');"  class="has_sub_second_nav" ><span class="subnav_text" >${cldMenu.menuName}</span></a>
				</c:when>
				<c:otherwise><a href="javascript:void(0);"  class="has_sub_second_nav"><span class="subnav_text">${cldMenu.menuName}</span><i class="icon iconfont">&#xe65b;</i></a></c:otherwise>
			</c:choose>			
			<ul class="third_menu">
			<c:forEach items="${user_menus}" var="thirdMenu">
			<c:if test="${cldMenu.menuNo==thirdMenu.parentMenuNo && thirdMenu.isreport=='1' && thirdMenu.menuType=='0'}">
				<li>
					<a class="subnav_text" href="javascript:clickThirdMenu('${thirdMenu.menuName}','${thirdMenu.destUrl}',true,'${menu.menuNo}','${thirdMenu.menuNo}')">${thirdMenu.menuName}</a>
				</li>
			</c:if>
			</c:forEach>
			</ul>
		</li>
	</c:if>
	<c:if test="${menu.menuNo==cldMenu.parentMenuNo && cldMenu.ownership=='clinical' && cldMenu.menuType=='0'}">	
		<c:if test="${deptType=='ICU' }"> 
		<li>			
			<c:choose>
				<c:when test="${cldMenu.destUrl!=null}">
				<a href="javascript:clickThirdMenu('${cldMenu.menuName}','${cldMenu.destUrl}',true,'${cldMenu.menuNo}','${cldMenu.menuNo}');"  class="has_sub_second_nav" ><span class="subnav_text" >${cldMenu.menuName}</span></a>
				</c:when>
				<c:otherwise><a href="javascript:void(0);"  class="has_sub_second_nav"><span class="subnav_text">${cldMenu.menuName}</span><i class="icon iconfont">&#xe65b;</i></a></c:otherwise>
			</c:choose>			
			<ul class="third_menu">
			<c:forEach items="${user_menus}" var="thirdMenu">
			<c:if test="${cldMenu.menuNo==thirdMenu.parentMenuNo && thirdMenu.ownership=='clinical' && thirdMenu.menuType=='0'}">
				<li>
					<a class="subnav_text" href="javascript:clickThirdMenu('${thirdMenu.menuName}','${thirdMenu.destUrl}',true,'${menu.menuNo}','${thirdMenu.menuNo}')">${thirdMenu.menuName}</a>
				</li>
			</c:if>
			</c:forEach>
			</ul>
		</li>
		</c:if>
		<c:if test="${deptType!='ICU' && cldMenu.menuName !='ICU月报' &&  cldMenu.menuName !='ICU病情等级评定'}"> 
		<li>			
			<c:choose>
				<c:when test="${cldMenu.destUrl!=null}">
				<a href="javascript:clickThirdMenu('${cldMenu.menuName}','${cldMenu.destUrl}',true,'${cldMenu.menuNo}','${cldMenu.menuNo}');"  class="has_sub_second_nav"><span class="subnav_text" >${cldMenu.menuName}</span></a>
				</c:when>
				<c:otherwise><a href="javascript:void(0);"  class="has_sub_second_nav"><span class="subnav_text">${cldMenu.menuName}</span><i class="icon iconfont">&#xe65b;</i></a></c:otherwise>
			</c:choose>			
			<ul class="third_menu">
			<c:forEach items="${user_menus}" var="thirdMenu">
			<c:if test="${cldMenu.menuNo==thirdMenu.parentMenuNo && thirdMenu.ownership=='clinical' && thirdMenu.menuType=='0'}">
				<li>
					<a class="subnav_text" href="javascript:clickThirdMenu('${thirdMenu.menuName}','${thirdMenu.destUrl}',true,'${menu.menuNo}','${thirdMenu.menuNo}');">${thirdMenu.menuName}</a>
				</li>
			</c:if>
			</c:forEach>
			</ul>
		</li>
		</c:if>
	</c:if>
	</c:forEach>
	</ul>
	</c:if>
	</c:forEach>
	
</div>
<script type="text/javascript">
	function clickThirdMenu(name, url, isReload,id,menuNo){
		menuInfo.clickMenu(name,url,isReload,menuNo);
		hidemenu('',id);
	}
	function hidemenu(id1,id2){
		var sbtitle=document.getElementById(id2);
		if(sbtitle!=null){
			sbtitle.style.display='none';
		}
	}
/***二级菜单显示***/
	function showmenu(id1,id2){		
		var menu=document.getElementById(id1);
		var sbtitle=document.getElementById(id2);
		var n=sbtitle.parentNode.firstChild;
		var m=menu.parentNode.firstChild;
		if(sbtitle){	
		   sbtitle.style.display='block';
		   menu.className = "aHover";
			   for ( ; n; n = n.nextSibling ) {
					if ( n.nodeType === 1 && n !== sbtitle ) {
					n.style.display="none";
					}
				}
			for ( ; m; m = m.nextSibling ) {
					if ( m.nodeType === 1 && m !== menu ) {					
					m.className = "noHover";
					}
				}
			}
	}
/***菜单导航***/
	function mainMenu() {	//三级菜单的隐藏与展开	
		$(".second_menu .has_sub_second_nav").click(function(){
			$(".second_menu li").each(function(i, obj) {
				$(obj).removeClass('cur');
			});
			$(this).parent("li").siblings("li").find(".third_menu").slideUp("normal");
			if(!$(this).next(".third_menu").is(":visible")){
				$(this).next(".third_menu").slideDown("slow");		    
				$(this).parent("li").addClass('cur');
				return false;
	        }else{
			   $(this).next(".third_menu").slideUp("normal");
			   return false;
		    }
		});	
		//$(".second_menu .has_sub_second_nav").eq(0).click();  
	}
	$(function() {
		//mainMenu();
	});
	
	var menuInfo = {
			//一级菜单
			parentMenus : [],
			//自己拥有的所有菜单
			menuJson : [],
			clickMenus : {},
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
					url += 'ownership=clinical&tabBodyId=' + _tabId+'&menuNo='+menuNo;
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
					//$('#mainTabs').tabs('select', name);
					if(isReload) {
						var current_tab = $('#mainTabs').tabs('getTab', name);
						$('#mainTabs').tabs('update',{
						     tab: current_tab,
						     options : {
						    	 content : '<iframe name="' + _tabId + '" id="' + _tabId + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>' 
						    	 //content: '<iframe name="'+_tabId+'"  id="'+_tabId+'" width="100%" height="100%" frameborder="0" src="'+url+'" scrolling="auto"></iframe>'
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
			//关闭当前选中的tab标题
			closeCurSelectTab : function () {
				var currTab =$('#mainTabs').tabs('getSelected').panel('options').title; 
				$('#mainTabs').tabs('close', currTab);
			},
			//获取当前选中的tab标题
			getCurSelectTabTitle : function () {
				var currTabTitle =$('#mainTabs').tabs('getSelected').panel('options').title; 
				return currTabTitle;
			},
			getCurSelectTabPanelOpts : function () {
				var currTabPanelOpts = $('#mainTabs').tabs('getSelected').panel('options'); 
				return currTabPanelOpts;
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
				if (menuNo && menuNo.length > 0) {
					menuInfo.clickMenus[name] = {menuNo:menuNo, url:url};
				}
				var mainTab = $('#mainTabs').tabs('getTab', 0);
				if (!name || name.length == 0) {
					name = mainTab.panel('options').title;
				}
				//获取tab的iframe对象  
		        var tbIframe = mainTab.find('iframe:first-child'); 
		        if (!url || url.length == 0) {
					url = tbIframe.attr('src');
				}
				var id = tbIframe.attr('id');
				$('#mainTabs').tabs('update',{     
					tab : mainTab,     
					options : {
						title : name,
						content : '<iframe name="' + id + '" id="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>'
					}
				});
				$('#mainTabs').tabs('select', 0);
			},
			//停止当前所有的http请求
			stopHttp : function() {
				//停止ajax请求  
	            var xmlhttp;
	            if (window.XMLHttpRequest) {  
	                xmlhttp = new XMLHttpRequest();  // code for IE7+, Firefox, Chrome, Opera, Safari  
	            } else {  
	                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");  // code for IE6, IE5  
	            }  
	            xmlhttp.abort();  
	            //停止继续加载页面，相当于点击stop按钮。
	            if (window.stop) {
	                window.stop(); //code for  Firefox, Chrome, Opera, Safari   
	            } else {
	            	/* var currTab = this.getCurSelectTab();
					var tbIframe = currTab.find('iframe:first-child').contents().find("body"); 
					tbIframe.find('iframe').each(function(){
						var id = $(this).attr('id');
						if (id && id.indexOf('reportFrame') >= 0) {
							$(this).attr('src', '');
						}
					}); */
					try {
		            	document.execCommand("stop");// code for IE
		            	var tbIframe = this.getCurSelectTab().find('iframe:first-child');
		            	var doc = $(tbIframe.contents()); 
		            	var win = tbIframe[0].window || tbIframe[0].contentWindow;
		            	if (win && win.reportType) {
		            		if (win.reportType == '1') { //easyui原生tab
		            			//只停止当前选中tab
		            			//doc.find('#report_frame_tabs').tabs('getSelected').find('iframe:first-child').attr('src', '');
		            			if (win.report && win.report.stopHttp) {
		            				win.report.stopHttp();
		            			}
		            		} else { //写的tab样式
		            			//停止第一个iframe
		            			//停止第一个iframe
		            			var fra = doc.find('iframe:first-child');
		            			if (fra) {
		            				fra.attr('src', '');
		            			}
		            		}
		            	}
					} catch (e) {
						console.error(e);
					}
	            }
			}
	}
</script>