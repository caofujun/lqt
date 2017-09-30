<#if sourceType == "set">
<div class="SingleRow">
	<div  class="default">
		<a href="javascript:shsl.openComponent()" class="default_img" ></a>
	</div>
</div>
</#if>
<#if (components?size > 0) >
<div class="SingleRow">
	<#if components[0].componentCode != "baseInfo" || sourceType == "set">
	<div class="edit_pwd"><a class="img_del" href="javascript:shsl.delComponent('${components[0].componentCode}')" title="删除组件"></a></div>
	</#if>
	<#if sourceType == "set">
		<div class="home_box">
			<div class="cap">${components[0].componentName}</div>
			<div class="con"><img src="${webroot}${components[0].imgUrl!}" width="100%" height="150"/></div>
		</div>
	<#else>
	${components[0].html}
	</#if>
</div>
</#if>
<#if (components?size > 1) >
<div class="SingleRow">
	<#if components[1].componentCode != "baseInfo" || sourceType == "set">
	<div class="edit_pwd"><a class="img_del" href="javascript:shsl.delComponent('${components[1].componentCode}')" title="删除组件"></a></div>
	</#if>
	<#if sourceType == "set">
		<div class="home_box">
			<div class="cap">${components[1].componentName}</div>
			<div class="con"><img src="${webroot}${components[1].imgUrl!}" width="100%" height="150"/></div>
		</div>
	<#else>
	${components[1].html}
	</#if>
</div>
</#if>
<div class="home_column home_left">
<#list components as comp>
	<#if (comp_index > 1) && (comp_index <= (components?size)/2) >
	<div class="DoubleRow" title="${comp.componentName!}">
		<#if comp.componentCode != "baseInfo">
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
	<#if (comp_index > 1) && (comp_index > (components?size)/2) >
	<div class="DoubleRow">
		<#if comp.componentCode != "baseInfo">
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