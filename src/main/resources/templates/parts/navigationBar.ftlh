<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">OGU</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Заявки</a>
            </li>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user-requests/${currentUserId}">Мои заявки</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Список пользователей</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/requestEditAdmin">Заявки о ожидании</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/services">Услуги</a>
                </li>
            </#if>
            <#if user??&&name!="Guest">               <!--<if user??&&name!="Guest"> -->
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Профиль</a>
                </li>
            </#if>
        </ul>

        <div class="navbar-text mr-2">${name}</div>
        <@l.logout />
    </div>
</nav>