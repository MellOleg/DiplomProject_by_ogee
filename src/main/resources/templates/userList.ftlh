<#import "parts/common.ftlh" as c>

<@c.page>
    Список пользователей

    <table class="table">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Электронная почта</th>
            <th>Статус пользователя</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#if user.email??>${user.email}</#if></td>
                <td>
                    <#if user.active == true>
                        Активирован
                    <#else>
                        Заблокирован
                    </#if>
                </td>
                <td><a href="/user/${user.id}">Редактировать</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>