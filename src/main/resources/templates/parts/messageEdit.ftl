<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Обновить предложение
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="text" placeholder="Введите новые контактные данные" />
            </div>
            <div class="form-group">
                            <label for="exampleFormControlSelect1">Выберите новое название машины</label>
                            <select class="form-control" id="exampleFormControlSelect1" name="tag">
                             <#list cars as car>
                              <option type="text" value="${car.nameCar}">${car.nameCar}</option>
                              </#list>
                            </select>
                          </div>

                          <div class="form-group">
                                                      <label for="exampleFormControlSelect1">Выберите новую модель машины</label>
                                                      <select class="form-control" id="exampleFormControlSelect1" name="mod">
                                                       <#list mods as mod>
                                                        <option type="text" value="${mod.name}">${mod.name}</option>
                                                        </#list>
                                                      </select>
                                                    </div>

            <div class="form-group">
                            <input type="text" class="form-control" name="price" placeholder="Введите новую цену машины">
                        </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Выберите новое изображение машины</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Обновить</button>
            </div>
        </form>
    </div>
</div>
