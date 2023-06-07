<#import "parts/common.ftlh" as c>

<@c.page>
    <section class="main-block-home">
        <div class="border-block-home">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <form method="get" action="/home" class="form-inline">
                        <input type="text" name="filter" class="form-control" value="${filter?ifExists}"
                               placeholder="Поиск по тегу">
                        <button type="submit" class="btn btn-primary ml-2">Найти</button>
                    </form>
                </div>
            </div>

            <a type="button" class="btn btn-primary" href="/request/create">Добавить заявку</a>

            <#include "parts/requestList.ftlh">
        </div>
    </section>
</@c.page>