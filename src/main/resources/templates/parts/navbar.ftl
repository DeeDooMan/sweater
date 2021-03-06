<#include "security.ftl">
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Rent a car</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Домой</a>
            </li>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/main">Предложения</a>
            </li>
            <li class="nav-item">
             <a class="nav-link" href="/user-messages/${currentUserId}">Мои предложения</a>
            </li>
            </#if>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">Список пользователей</a>
            </li>
            <li class="nav-item">
                            <a class="nav-link" href="/mainCar">Список машин</a>
                        </li>
                        <li class="nav-item">
                                                    <a class="nav-link" href="/mainMod">Список моделей</a>
                                                </li>
            </#if>

        </ul>

        <div class="navbar-text mr-3">${name}</div>
        <#if user??>
        <@l.logout />
        </#if>
        <#if !user??><@l.login1 /></#if>
    </div>
</nav>