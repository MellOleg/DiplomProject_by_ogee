<#import "parts/common.ftlh" as c>

<@c.page>
    <section class="main-block-services">
        <div class="border-block-services">
            <li class="nav-item">
                <a class="btn btn-primary" href="/admin/services/add">Добавить услугу</a>
            </li><br>
            <li class="nav-item">
                <a class="btn btn-primary" href="/organisation/add">Добавить организацию</a>
            </li>

            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Услуга</th>
                    <th scope="col">Исполняющая организация</th>
                </tr>
                </thead>
                <tbody>
                <#list servicesIterable as service>
                <tr>
                    <td>${service.service_name}</td>
                    <td><#list organisationIterable as organisation>
                            <#if service.id == organisation.id>${organisation.organisationName} |</#if>
                        </#list>
                    </td>
                    </#list>
                </tbody>
            </table>

            <#list servicesIterable as service>
                <br>
                <tr>
                    <td>id: ${service.id}</td>
                    <td>| ${service.service_name}</td>
                </tr>
                <input type="text" class="form-control " value="${service.service_name}" name="text" placeholder=""/>

            </#list>
            <br>

            <form>
                <div class="form-group">
                    <label for="exampleFormControlSelect1">Выберите услугу</label>
                    <select class="form-control" id="exampleFormControlSelect1">
                        <#list servicesIterable as service>
                            <option type="text" value="${service.service_name}">
                                <#if service.id == service.id>${service.service_name}</#if>
                            </option>
                        </#list>
                    </select>
                </div>
            </form>


            <select class="form-select" aria-label="Default select example"
                    value="" name="tag">
                <option value="">Выберите тип услуги</option>
                <#list servicesIterable as service>
                    <option type="text" value="${service.service_name}">
                        ${service.service_name}
                    </option>
                </#list>
            </select>

            <div class="form-group mt-3">
                <button type="submit" class="btn btn-primary ">Сохранить</button>
            </div>
        </div>
    </section>

</@c.page>
