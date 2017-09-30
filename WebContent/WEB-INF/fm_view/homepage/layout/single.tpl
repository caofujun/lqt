<#if sourceType == "set">
<div class="SingleRow">
	<div  class="default">
		<a href="javascript:shsl.openComponent()" class="default_img" ></a>
	</div>
</div>
</#if>
<#list components as comp>
	<div class="home_column SingleRow">
		<#if comp.componentCode != "baseInfo" || sourceType == "set">
		<div class="edit_pwd"><a class="img_del" href="javascript:shsl.delComponent('${comp.componentCode}')" title="删除组件"></a></div>
		</#if>
		<#if sourceType == "set">
			<div class="home_box">
				<div class="cap">${comp.componentName}</div>
				<div class="con"><img src="${webroot}${comp.imgUrl!}" width="100%" height="150"/></div>
			</div>
		<#else>
		${comp.html}
		</#if>
	</div>
</#list>