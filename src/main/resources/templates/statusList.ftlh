<#import "parts/common.ftlh" as c>

<@c.page>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">StatusName</th>
        </tr>
        </thead>
        <tbody>
        <#list statusesIterable as statuses>
        <tr>
            <td>${statuses.id}</td>
            <td>${statuses.statusName}</td>
            </#list>
        </tbody>
    </table>
    <li class="nav-item">
        <a class="nav-link" href="/statuses/add">Добавить статус</a>
    </li>
    <#list statusesIterable as statuses>
        <br><tr>
        <td>id: ${statuses.id}</td>
        <td>| ${statuses.statusName}</td>
    </tr>
        <input type="text" class="form-control " value="${statuses.statusName}" name="text" placeholder="" />

    </#list>
    <br>

    <form>
        <div class="form-group">
            <label for="exampleFormControlSelect1">Выберите статус</label>
            <select class="form-control" id="exampleFormControlSelect1">
                <#list statusesIterable as statuses>
                    <option type="text" value="${statuses.statusName}">
                        <#if statuses.id == statuses.id>${statuses.statusName}</#if>
                    </option>
                </#list>
            </select>
        </div>
    </form>


    <select class="form-select"  aria-label="Default select example"
            value="" name="tag">
        <option value="">Выберите статус</option>
        <#list statusesIterable as statuses>
            <option type="text" value="${statuses.statusName}">
                <#if statuses.id == statuses.id>${statuses.statusName}</#if>
            </option>
        </#list>
    </select>

    <div class="form-group mt-3">
        <button type="submit" class="btn btn-primary ">Сохранить</button>
    </div>

</@c.page>
