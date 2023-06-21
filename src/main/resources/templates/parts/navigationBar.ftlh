<#include "security.ftlh">

<div class="navigation">
    <nav class="navbar navbar-expand-lg navbar-light fixed-top scrolling-navbar white ">
        <div class="container">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
                    aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <ul class="list-unstyled navbar-nav mr-auto">
                    <li class="nav-item ml-4">
                        <a class="nav-link title" href="/" data-offset="90">Главная</a>
                    </li>

                    <#if user??>
                    <li class="nav-item ml-4">
                        <a class="nav-link title" href="/user/myrequests" data-offset="90">Мои заявки</a>
                    </li>
                    </#if>
                    <#if user??&&name!="Guest">
                    <li class="nav-item ml-4">
                        <a class="nav-link title" href="/user/profile" data-offset="90">Профиль</a>
                    </li>
                    </#if>
                    <#if isAdmin>
                    <li class="nav-item dropdown ml-4">
                        <a class="nav-link dropdown-toggle title" id="navbarDropdownMenuLink3" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">Панель администратора</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="/admin/services">Сервисы и услуги</a>
                            <a class="dropdown-item" href="/admin/userslist">Список пользователей</a>
                            <a class="dropdown-item" href="/admin/activerequests">Активные заявки</a>
                            <a class="dropdown-item" href="/admin/pendingrequests">Заявки "На рассмотрении"</a>
                        </div>
                    </li>
                    </#if>
            </div>
            <div class="navbar-text mr-2">${name}</div>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <button class="btn btn-primary" type="submit"/><#if currentUserId == -1>Войти<#else >Выйти</#if></button>
            </form>
        </div>
    </nav>
</div>
