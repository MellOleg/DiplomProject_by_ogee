<#import "parts/common.ftlh" as c>

<@c.page>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">ServiceName</th>
        </tr>
        </thead>
        <tbody>
        <#list servicesIterable as service>
        <tr>
            <td>${service.id}</td>
            <td>${service.service_name}</td>
            </#list>
        </tbody>
    </table>
    <li class="nav-item">
        <a class="nav-link" href="/services/add">Добавить услугу</a>
    </li>
    <#list servicesIterable as service>
        <br><tr>
        <td>id: ${service.id}</td>
        <td>| ${service.service_name}</td>
    </tr>
        <input type="text" class="form-control " value="${service.service_name}" name="text" placeholder="" />

    </#list>
    <br>
    <select class="form-select"  aria-label="Default select example"
            value="" name="tag">
        <option value="">Выберите тип услуги</option>
        <#list servicesIterable as service>
            <option type="text" value="${service.service_name}">
                <#if service.id == service.id>${service.service_name}</#if>
            </option>
        </#list>
    </select>

    <div class="form-group mt-3">
        <button type="submit" class="btn btn-primary ">Сохранить</button>
    </div>

</@c.page>
