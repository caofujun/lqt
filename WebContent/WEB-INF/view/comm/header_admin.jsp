<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<div id="new_header_d" data-options="region:'north',border:false">
 	 	<%-- <%@ include file="/WEB-INF/view/core/lqtMessage.jsp"%>	 --%>
		<div id="logo" onclick="menuInfo.refreshMain('患者列表','${webroot}/bk001Sbk/f_view/index.shtml','')"></div><div class="header_text"><c:choose><c:when test="${user.roleScope eq 'crb' || systemScope=='cdc7'}">蓝蜻蜓传染病监测系统</c:when><c:otherwise>蓝蜻蜓医院感染实时监控平台</c:otherwise></c:choose><span>（${hospName}）</span></div>		
		<div class="user_infor">
			<%-- <span class="Customer" onclick="showhidediv('CustomerImg');" onmouseleave="hidediv('CustomerImg');"><i class="icon iconfont">&#xe61c;</i>客服</span>
			<span class="user_name" onclick="showhidediv('drp_user');" onmouseleave="hidediv('drp_user');"><i class="icon iconfont">&#xe670;</i>${user.realname}<i class="icon iconfont">&#xe65b;</i></span>						 --%>
			<span style="margin-right: 10px;">服务电话：400-178-9899</span>
			<span><a class="flashPlayer" href="javascript:flashDownload()" title="下载FlashPlayer"></a></span>
			<span class="Customer easyui-tooltip" data-options="
                    showEvent: 'click',
                    content: function(){
                        return $('#CustomerImg');
                    },
                    onShow: function(){
                        var t = $(this);
	                    t.tooltip('tip').unbind().bind('mouseenter', function(){
	                        t.tooltip('show');
	                    }).bind('mouseleave', function(){
	                        t.tooltip('hide');
	                    });
                    }
                "></span>
                     <span class="czsc"><a title="点击下载“临床端操作手册“" href="${nisCzscCUrl}" target="_blank"><i class="icon nisfont">&#xe6c1;</i>操作手册</a></span>
<!--             <span class="items_dealt" onclick="menuInfo.clickMenu('本科检出菌','/xn011Dclymx/f_view/index.shtml','')">本科检出菌<span class="badge">3</span></span> -->
            <span class="menu_combobox">
            	<select id="deptList" class="easyui-combobox" data-options="editable:false">
				<c:forEach var="dep" items="${deptList}">
					<option value="${dep.deptId}" <c:if test="${dep.deptId==zg_dept}">selected="selected"</c:if>>${dep.deptName}</option>
				</c:forEach>
				</select>			
			</span>
			<span class="user_name" onclick="showhidediv('drp_user');"><i class="icon iconfont">&#xe670;</i>${user.realname}<i class="icon iconfont">&#xe65b;</i></span>						
			<span class="close" onclick="logout();"><i class="icon iconfont">&#xe60d;</i></span>
			<div class="clear"></div>			
		</div>
	
	
<!--    	<input type="button" value="ceshisave" onClick=" nyTellEvent.OnCallInFunc('13975168547')">  -->
<!--	<input type="button" value="ceshizhuyuan" onClick=" Comm.dialog({url:'${webroot}/callIn/index.action?telPhone=13975168547',type:'iframe',title:'来电',height:500,width:1000})">-->
	<%--	<div class="quickmenu" id="parentMenuPanel">
        	 <ul>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_02.gif">随访管理</a></li>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_03.gif">患者管理</a></li>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_04.gif">评价反馈</a></li>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_05.gif">满意度调查</a></li>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_07.gif">统计分析</a></li>
				<li><a href="#"><img src="${webroot}/resources/images/admin/menu_ico_08.gif">短信平台</a></li>
				<li><a href="javascript:openTelDialog()"><img src="${webroot}/resources/images/admin/menu_ico_06.gif">系统设置</a></li>
				<li><a class="last" href="${webroot}/account/logout.action" target="_top"><img src="${webroot}/resources/images/admin/menu_ico_exit.gif">退出系统</a></li>
			</ul> 
        </div>--%>
    
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
<ul id="drp_user" class="dropdown_menu drp_user" onmouseleave="hidediv('drp_user');">
	<li><span onclick="openUpdatePasswd(); hidediv('drp_user');"><i class="icon iconfont">&#xe680;</i>修改密码</span></li>
	<!-- <li><span><i class="icon iconfont">&#xe674;</i>用户信息</span></li>
	<li><span><i class="icon iconfont">&#xe685;</i>系统设置</span></li>	 -->						
</ul>
<div id="CustomerImg">
	<img src="${webroot}/resources/images_org/nis/Customer.png">	
	<span>全国感控老师交流群</span>
	<span>扫一扫，加群主微信</span>
</div>
<script type="text/javascript">
$("#deptList").combobox({
	onChange: function (n,o) {
		$.ajax({
	        url: '${webroot}/user/f_json/chooseDep.shtml',
	        type: 'post',
	        data: { deptId: n },
	        dataType: 'json',
	        success : function(json) {
				if(json.result==='success') {
					location="${webroot}/user/f_view/main_admin.shtml";
		    	} else if(json.result === 'error') {
		    		$.messager.show({ title: '提示', msg: '切换异常！' });
		    	} else {
		    		$.messager.show({ title: '提示', msg: json.msg });
		    	}
			}
		});
	}
});
function flashDownload() {
	Comm.dialog({
		url: '${webroot}/zg/f_view/flashDown.shtml',
		title:'flash下载',
		type:"iframe",
		height:300,
		width:450
	});
}
function hidediv(id){
	var sbtitle=document.getElementById(id);
	sbtitle.style.display='none';
}
//显示隐藏下拉菜单
function showhidediv(id){			
	var sbtitle=document.getElementById(id);		
	if(sbtitle){
	   if(sbtitle.style.display=='none'){
			sbtitle.style.display='block';
	   }else{			
			sbtitle.style.display='block';			
		}
	}
}
 
function openUpdatePasswd() {
	Comm.dialog({
		url: '${webroot}/acAccount/f_view/toUpdPasswd.shtml?sourceType=my',
		title:'修改密码',
		type:"iframe",
		height:250,
		width:450
	});
}

function logout() {
	$.messager.confirm('提示', '确认退出系统?', function (r) {
    	if (r) {
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
	});
}
//$(function() {
//	menuInfo.init();
//});

function closeMe() { 
    window.opener = null;  
    window.open("", "_self");  
    window.close(); 
}

</script>