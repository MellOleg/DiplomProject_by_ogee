<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <#if isAdmin>

    </#if>
    <#include "parts/requestEdit.ftlh">
    <#include "parts/requestList.ftlh">

</@c.page>