<#include "security.ftlh">
<section class="main-block-requestList">
    <div class="border-block-requestList">
        <div class="card-columns" xmlns="http://www.w3.org/1999/html">
            <#list requests as request>
                <div class="card card-blog">
                    <div class="card-image">
                    <#if request.filename??>
                        <img src="/img/${request.filename}" class="card-img-top">
                    <#else>
                        <img src="/img/placeholder-16-9.jpg" class="card-img-top">
                    </#if>
                    </div>
                    <div class="table">
                        <span>${request.text}</span><br>
                        <i>${request.address.address}</i><br>
                        <span>Статус - ${request.status.name}</span><br>
                        <span>Услуга - ${request.service.service_name}</span><br>
                    </div>
                    <div class="chmo">
                        <#if isAdmin>
                            <a href="myrequests/${request.author.id}">${request.authorName}</a><!-- authorName -->

                            <a class="btn btn-primary" href="/request/edit/${request.id}">
                                Изменить
                            </a>
                            <a class="btn btn-warning" href="/request/delete/${request.id}">
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
<#include "footer.ftlh">