<#include "security.ftl">

<div class="card-columns">
    <#list messages as message>
    <div class="card my-3">
        <#if message.filename??>
        <img src="/img/${message.filename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <span>${message.text}</span><br/>
            <i>#${message.tag}</i>
            <i>#${message.price}</i>
        </div>
        <div class="card-footer text-muted">
            <a href="/user-messages/${message.author.id}">${message.authorName}</a>
            <#if message.author.id == currentUserId>
              <td><a class="btn btn-danger" href="/deleteMessage/${message.id}" onclick="return confirm('Вы уверены что хотите удалить это предложение?');">Удалить</a></td>

             <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">Изменить</a>
             <a class="btn btn-primary">${message.aviable}</a>
            </#if>

        </div>
    </div>
    <#else>
    Предложений нет
    </#list>
</div>