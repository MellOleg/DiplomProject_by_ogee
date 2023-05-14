<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXEPTION.request}
        </div>
    </#if>

    <@l.login "/login" false/>
</@c.page>