<#include "security.ftlh">
<section class="main-block-requestList">
    <div class="border-block-requestList">
        <div class="card-columns" xmlns="http://www.w3.org/1999/html">
            <#list requests as request>
                <div class="card my-3">
                    <#if request.filename??>
                        <img src="/img/${request.filename}" class="card-img-top">
                    <#else>
                        <img src="/img/placeholder-16-9.jpg" class="card-img-top">
                    </#if>
                    <div class="m-2">
                        <span>${request.text}</span><br>
                        <i>#${request.tag}</i><br>
                        <span>Статус - ${request.status.name}</span><br>
                        <span>Услуга - ${request.service.service_name}</span><br>
                    </div>
                    <div class="card-footer text-muted">
                        <#if isAdmin>
                            <a href="/user/list/${request.author.id}">${request.authorName}</a><!-- authorName -->

                            <a class="btn btn-primary" href="/request/${request.id}/edit">
                                Изменить
                            </a>
                            <a class="btn btn-warning" href="/request/${request.id}/delete">
                                Удалить
                            </a>
                        </#if>
                    </div>
                </div>

            <#else>
                Объявлений нет
            </#list>
        </div>
    </div>
</section>