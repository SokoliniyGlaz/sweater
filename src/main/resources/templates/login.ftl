<#import "common.ftl" as c>
<#import "login_registry.ftl" as l>
<@c.page>
    ${message!}
    <div class="mb-2"><h3>Login page</h3></div>
    <@l.login "/login" false/><br><br>
    <a href="/registration"  class="btn btn-primary" type="submit">Add new user</a>
</@c.page>
