<a class="btn btn-success" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Добавить предложение
</a>

<div class="collapse" id="collapseExample">

    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="text" placeholder="Введите контактные данные" />
            </div>

<div class="form-group">
                <label for="exampleFormControlSelect1">Выберите название машины</label>
                <select class="form-control" id="exampleFormControlSelect1" name="tag">
                 <#list cars as car>
                  <option type="text" value="${car.nameCar}">${car.nameCar}</option>
                  </#list>
                </select>
              </div>

              <div class="form-group">
                              <label for="exampleFormControlSelect1">Выберите модель машины</label>
                              <select class="form-control" id="exampleFormControlSelect1" name="mod">
                               <#list mods as mod>
                                <option type="text" value="${mod.name}">${mod.name}</option>
                                </#list>
                              </select>
                            </div>

            <div class="form-group">
                            <input type="text" class="form-control" name="price" placeholder="Введите цену машины">
                        </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Выберите изображение машины</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-success">Добавить</button>
            </div>
        </form>
    </div>
</div>
