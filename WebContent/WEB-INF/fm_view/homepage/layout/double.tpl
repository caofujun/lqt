<#if sourceType == "set">
<div class="SingleRow">
	<div  class="default">
		<a href="javascript:shsl.openComponent()" class="default_img" ></a>
	</div>
</div>
</#if>
<div class="home_column home_left">
<#list components as comp>
	<#if comp_index <= components?size/2>
	<div class="DoubleRow" title="${comp.componentName!}">
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
	</#if>
</#list>
</div>
<div class="home_column home_right">
<#list components as comp>
	<#if (comp_index > components?size/2)>
	<div class="DoubleRow">
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
	</#if>
</#list>
</div>