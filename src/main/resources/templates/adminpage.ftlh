<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <section class="main-block-adminpage">
        <div class="border-block-adminpage">
            <#list requests as request>
                <#if request.status.name == "На рассмотрении">
                    <div class="card1 mb-3" style="max-width: 740px;">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <#if request.filename??>
                                <img src="/img/${request.filename}" class="img-fluid rounded-start">
                            <#else>
                                <img src="/img/placeholder-16-9.jpg" class="img-fluid rounded-start">
                            </#if>
                        </div>
                        <div class="col-md-8">
                            <div class="card1-body">
                                <h5 class="card1-title">${request.service.service_name}</h5>
                                <span class="card1-text">${request.text}</span><br>
                                <span class="card1-text">${request.address.address}</span><br>
                                <#if request.performingOrganisation??> <span>Организация - ${request.performingOrganisation.organisationName}</span><br></#if>
                                <p class="card1-text"><small class="text-muted">${request.status.name}</small>
                                </p>
                            </div>

                            <div class="card1-footer">
                                <a class="btn btn-primary" href="/request/edit/${request.id}">
                                    Изменить
                                </a>
                                <a class="btn btn-warning" href="/request/delete/${request.id}">
                                    Удалить
                                </a>
                            </div>
                        </div>
                    </div>
                </#if>
                </div>
            </#list>
        </div>
    </section>
</@c.page>