<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    currentUserId = user.getId()
        isAdmin = user.isAdmin()
    >
<#else>
    <#assign
    name = "unknown"
     currentUserId = -1
        isAdmin = false
    >
</#if>