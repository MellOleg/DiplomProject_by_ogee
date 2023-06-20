<#import "parts/common.ftlh" as c>

<@c.page>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Услуга</th>
            <th scope="col">Исполняющая организация</th>
            <th scope="col">Стоимость услуги</th>
        </tr>
        </thead>
        <tbody>
        <#list servicesIterable as service>
        <tr>
            <td>${service.service_name}</td>
            <td><#list organisationIterable as organisation>
<#--                    <#if service.id == organisation> |</#if>-->${organisation.organisationName} |
                    <>
                </#list>
            </td>
            <td>55p</td>
        </tr>
        </#list>
        </tbody>
    </table>
</@c.page>