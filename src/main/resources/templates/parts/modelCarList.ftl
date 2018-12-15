<#include "security.ftl">

<div class="card-columns">
    <#list mods as mod>
    <div class="card my-3">
        <div class="m-2">
            <span>${mod.name}</span><br/>
        </div>
        <div class="card-footer text-muted">
            <a href="/mod-list/${mod.author.id}">${mod.authorName}</a>
            <#if mod.author.id == currentUserId>
              <td><a class="btn btn-danger" href="/deleteMod/${mod.id}" onclick="return confirm('Вы уверены что хотите удалить эту модель?');">Удалить</a></td>
            </#if>
        </div>
    </div>
    <#else>
    Моделей машин нет
    </#list>
</div>