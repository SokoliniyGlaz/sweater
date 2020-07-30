<#import "common.ftl" as c>
<#import "login_registry.ftl" as l>

<@c.page>
    ${message!}
    <@l.login "/registration" true/>
</@c.page>