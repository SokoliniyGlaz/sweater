<#import "common.ftl" as c>
<#import "login_registry.ftl" as l>
<@c.page>
    <#if Session ?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>
    <div class="mb-2"><h3>Login page</h3></div>
    <div><@l.login "/login" false/></div>

</@c.page>
