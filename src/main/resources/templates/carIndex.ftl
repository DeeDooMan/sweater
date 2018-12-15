<#include "parts/security.ftl">

<#if isAdmin>
<#import "parts/common.ftl" as c>
<@c.page>
<#include "parts/carList.ftl" />
</#if>
</@c.page>
