<a class="btn btn-success" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Добавить модель машины
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="name" placeholder="Введите модель машины" />
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if mod??>${mod.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-success">Добавить</button>
            </div>
        </form>
    </div>
</div>
