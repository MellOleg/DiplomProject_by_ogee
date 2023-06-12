<#import "parts/common.ftlh" as c>
<@c.page>

<div class="container mt-5">
    <h1>Добавление организации</h1>
    <form action="/organisation/add" method="post">
        <input type="text" name="organisationName" placeholder="Название" class="form-control"><br>
        <input type="text" name="organisationEmail" placeholder="почта" class="form-control"><br>
        <input type="text" name="organisationPhoneNumber" placeholder="номер телефона" class="form-control"><br>
        <input type="text" name="organisationAddress" placeholder="адрес" class="form-control"><br>

        <div class="form-group">
            <label for="requestStatus">Выберите сервис</label>
            <select class="form-control" id="serviceOrganisation" name="serviceId">
                <#list servicesIterable as service>
                    <option value="${service.id}">
                        ${service.service_name}
                    </option>
                </#list>
            </select>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" /></br>
        <div class="form-group mt-3">
            <button type="submit" class="btn btn-primary ">Сохранить</button>
        </div>
    </form>
</div>

</@c.page>
<#include "parts/footer.ftlh">