<#import "parts/common.ftl" as c>

<@c.page>
<h5>Список пользователей</h5>
    <table class="table table-bordered table-dark">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Role</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td scope="row">${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a class="btn btn-primary" href="/user/${user.id}">Изменить</a></td>
            <td><a class="btn btn-danger" href="/deleteUser/${user.id}" onclick="return confirm('Вы уверены что хотите удалить этого пользователя?');">Удалить</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>