<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<div id="header_d" data-options="region:'north',border:false"><!--框架头部 -->
	<div id="logo"><div class="header_v">V7.0</div></div>
	<div class="easyui-panel" style="padding:5px;">
		<c:forEach items="${user_menus}" var="menu">
		<c:if test="${(menu.parentMenuNo==null || menu.parentMenuNo=='') && menu.ownership=='clinical' && menu.menuType=='0'}">
		<c:set var="count" value="0"></c:set>
		<c:forEach items="${user_menus}" var="cldMenu">
		<c:if test="${menu.menuNo==cldMenu.parentMenuNo && cldMenu.ownership=='clinical' && cldMenu.menuType=='0'}"><c:set var="count" value="1"></c:set></c:if>
		</c:forEach>
		<a href="javascript:menuInfo.clickMenu('${menu.menuName}','${menu.destUrl}',true,'${menu.menuNo}')" <c:if test="${count==0}">class="easyui-linkbutton" data-options="plain:true"</c:if> <c:if test="${count==1}">class="easyui-menubutton" data-options="menu:'#${menu.menuNo}'"</c:if> >${menu.menuName}</a>
		</c:if>
		</c:forEach>
	</div>
	<c:forEach items="${user_menus}" var="menu">
		<c:if test="${(menu.parentMenuNo==null || menu.parentMenuNo=='') && menu.ownership=='clinical' && menu.menuType=='0'}">
		<c:set var="count" value="0"></c:set>
		<c:forEach items="${user_menus}" var="cldMenu">
		<c:if test="${menu.menuNo==cldMenu.parentMenuNo && cldMenu.ownership=='clinical' && cldMenu.menuType=='0'}"><c:set var="count" value="1"></c:set></c:if>
		</c:forEach>
		<c:if test="${count==1}"><div id="${menu.menuNo}" class="header_mm2"></c:if>
		</c:if>
		<c:forEach items="${user_menus}" var="cldMenu">
		<c:if test="${menu.menuNo==cldMenu.parentMenuNo && cldMenu.ownership=='clinical' && cldMenu.menuType=='0'}">
			<div onclick="menuInfo.clickMenu('${cldMenu.menuName}','${cldMenu.destUrl}',true,'${cldMenu.menuNo}')">${cldMenu.menuName}</div>
		</c:if>
		</c:forEach>
		</div>
	</c:forEach>
		
		<!-- <nav class="nav">
		  <ul class="nav__menu">			
			<li class="nav__menu-item cur"><a><span>病历书写管理</span><span class="bordCss"></span></a>
			  <ul class="nav__submenu">
				<li class="nav__submenu-item"> <a><span>病历书写</span></a></li>
				<li class="nav__submenu-item"> <a><span>病历编辑</span></a></li>
				<li class="nav__submenu-item"> <a><span>病历删除</span></a></li>
			  </ul>
			</li>
			<li class="nav__menu-item"><a><span>病历模板管理</span></a></li>
			<li class="nav__menu-item"><a><span>病历系统维护</span></a></li>
			<li class="nav__menu-item"><a><span>病历质控管理</span></a></li>
		  </ul>
		</nav> -->
		<div class="user_infor">			
			<select>
				<option>呼吸内科ICU病区</option>
				<option>神经外科ICU病区</option>
				<option>4病区(心脏外科)</option>
				<option>5病区(手显微外科)</option>
			</select>			
			<span>FCadmin</span>
			<a class="change_pw">修改密码</a>
			<a class="exit" href="javascript:logout();">退出系统</a>
		</div>
	</div><!--header_d结束 -->
<script type="text/javascript">
var menuInfo = {
		clickMenus : {},
		//点击菜单右边跳转
		clickMenu : function(name, url, isReload, menuNo) {
			if(url == '') {
				return;
			}
			if(!isReload) {
				name = '&nbsp;' + name + '&nbsp;';
			}
			menuInfo.clickMenus[name] = {menuNo:menuNo, url:url};
			if(url.lastIndexOf('?') != -1) {
				url += '&';
			} else {
				url += '?';
			}
			if(url.indexOf('http://')== -1 && url.indexOf('https://')== -1) {
				url = webroot + url;
				url += 'ownership=platform';
			} else if(menuNo!='pt_note_info') {
				//包含http链接
				url = webroot + '/menu/f_view/to.shtml?menuNo=' + menuNo;
			}
			if(!$('#mainTabs').tabs('exists', name)){
			    $('#mainTabs').tabs('add', {
			    	title: name,
	                content: '<iframe width="100%" height="100%" frameborder="0" src="'+url+'" scrolling="auto"></iframe>',
	                closable: true
			    });
			}else{
				$('#mainTabs').tabs('select', name);
				if(isReload) {
					var current_tab = $('#mainTabs').tabs('getSelected');
					$('#mainTabs').tabs('update',{
					     tab: current_tab,
					     options : {
					    	 content: '<iframe width="100%" height="100%" frameborder="0" src="'+url+'" scrolling="auto"></iframe>'
					     }
					});
				}
			}
		}	
};
function logout() {
	location = '${webroot}/user/f_view/logout.shtml';	
}
</script>