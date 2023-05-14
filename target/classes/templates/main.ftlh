<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск по тегу">
                <button type="submit" class="btn btn-primary ml-2">Найти</button>
            </form>
        </div>
    </div>

    <#include "parts/requestEdit.ftlh">
    <#include "parts/requestList.ftlh">
</@c.page>