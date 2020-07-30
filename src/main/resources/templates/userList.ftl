<#import "common.ftl" as c>
<#include "security.ftl">
<@c.page>
    List of users
    <table>
        <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
        </thead>
        <tbody>
    <#list users as user>
<tr>
    <td><#if user.username??>${user.username}<#else>none</#if></td>
    <td><#list user.getRoles() as role>${role}<#sep>, </#list></td>
    <td><a href="/user/${user.id}">edit</a> </td>
</tr>
    </#list>
        </tbody>
    </table>
</@c.page>