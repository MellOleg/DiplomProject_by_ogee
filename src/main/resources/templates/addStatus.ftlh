<#import "parts/common.ftlh" as c>
<@c.page>

    <div class="container mt-5">
        <h1>Добавление статуса</h1>
        <form action="/statuses/add" method="post">
            <input type="text" name="statusName" placeholder="Название статуса" class="form-control"><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}" /></br>
            <button type="submit" class="btn btn-success ">Сохранить</button>
        </form>
    </div>

</@c.page>