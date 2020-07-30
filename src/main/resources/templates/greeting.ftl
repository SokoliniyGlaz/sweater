<#import "common.ftl" as c>
<#include "security.ftl">
<@c.page>
<h5>Hello, <#if user??>${name}<#else>User</#if></h5>
    <a href="/login">Login page</a>
</@c.page>