<#include "parts/security.ftl">
<#if isAdmin>
<#import "parts/common.ftl" as c>
<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/mainMod" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск по модели машины">
            <button type="submit" class="btn btn-primary ml-2">Поиск</button>
        </form>
    </div>
</div>

<#include "parts/modelCarAdd.ftl" />
<#include "parts/modelCarList.ftl" />

</@c.page>
</#if>