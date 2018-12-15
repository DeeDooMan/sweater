<#include "security.ftl">

<div class="card-columns">
    <#list cars as car>
    <div class="card my-3">
        <div class="m-2">
            <span>${car.nameCar}</span><br/>
        </div>
        <div class="card-footer text-muted">
            <a href="/car-list/${car.author.id}">${car.authorName}</a>
            <#if car.author.id == currentUserId>
              <td><a class="btn btn-danger" href="/deleteCar/${car.id}" onclick="return confirm('Вы уверены что хотите удалить эту машину?');">Удалить</a></td>

            </#if>

        </div>
    </div>
    <#else>
    Машин нет
    </#list>
</div>