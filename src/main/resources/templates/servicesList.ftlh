<#import "parts/common.ftlh" as c>

<@c.page>
    <section class="main-block-services">
        <div class="border-block-services">
            <li class="nav-item">
                <a class="btn btn-primary" href="/admin/services/add">Добавить услугу</a>
            </li><br>
<#--            <li class="nav-item">-->
<#--                <a class="btn btn-primary" href="/organisation/add">Добавить организацию</a>-->
<#--            </li><br>-->
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
                        <td rowspan="${service.count}">${service.name}</td>
                        <td>${service.organisationName[0]}</td>
                    </tr>
                    <#if service.organisationName[1]??>
                         <#assign orgList = service.organisationName[1..]>
                            <#list orgList as organisationList>
                                <tr>
                                    <td>${organisationList}</td>
                                </tr>
                            </#list>
                    </#if>
                </#list>

                </tbody>
            </table>

            <br>
        </div>
    </section>

</@c.page>
