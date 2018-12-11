<#import "parts/common.ftl" as c>

<@c.page>
Список пользователей

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
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a class="btn btn-primary" href="/user/${user.id}">Изменить</a></td>
            <td><a class="btn btn-primary" href="/deleteUser/${user.id}">Удалить</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>