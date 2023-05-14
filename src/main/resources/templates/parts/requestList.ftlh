<#include "security.ftlh">
<div class="card-columns" xmlns="http://www.w3.org/1999/html">
    <#list requests as request>
        <div class="card my-3">
            <#if request.filename??>
                <img src="/img/${request.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${request.text}</span><br>
                <i>#${request.tag}</i><br>
                <#if request.text?? >
                    <span name="status">Статус - ${request.status}</span>
                </#if>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-requests/${request.author.id}">${request.authorName}</a><!-- authorName -->
                <#if isAdmin>
                    <a class="btn btn-primary" href="/user-requests/${request.author.id}?request=${request.id}">
                        Изменить
                    </a>
                    <a class="btn btn-warning" href="/del-user-requests/${request.author.id}?request=${request.id}">
                        Удалить
                    </a>
                </#if>
            </div>
        </div>

    <#else>
        Объявлений нет
    </#list>
</div>