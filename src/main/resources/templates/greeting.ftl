<#import "common.ftl" as c>
<#include "security.ftl">
<@c.page>
<div>Hello, <#if user??>${name}<#else>User</#if></div>
<a href="/main">Main page</a>
</@c.page>