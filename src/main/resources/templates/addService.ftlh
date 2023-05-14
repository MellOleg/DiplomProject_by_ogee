<#import "parts/common.ftlh" as c>
<@c.page>

    <div class="container mt-5">
        <h1>Добавление услуги</h1>
        <form action="/services/add" method="post">
            <input type="text" name="service_name" placeholder="Название услуги" class="form-control"><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}" /></br>
            <button type="submit" class="btn btn-success ">Сохранить</button>
        </form>
    </div>

</@c.page>