<#import "common.ftl" as c>

<@c.page>
<div>
    <form action="/logout" method="post">
        <input type="submit" value="Sign Out"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</div>
<div>
    <form method="post">
        <input type="text" name="text" placeholder="Введите сообщение">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="text" name="tag" placeholder="Тэг">
        <button type="submit">Добавить</button>

    </form>

</div>
<div>Список сообщений</div>
<form method="post" action="filter">
    <input type="text" name="filter">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Найти</button>
</form>
<#list messages as message>
<div>
    <ul>
    <b>${message.id}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${message.authorName}</strong>
    </ul>
</div>
<#else>
No messages
</#list>
</@c.page>