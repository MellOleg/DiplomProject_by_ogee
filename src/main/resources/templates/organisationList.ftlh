<#import "parts/common.ftlh" as c>

<@c.page>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">Name</th>
            <th scope="col">Address</th>
            <th scope="col">Email</th>
        </tr>
        </thead>
        <tbody>
        <#if organisationIterable??>
            <#list organisationIterable as organisation>
        <tr>
            <td>${organisation.id}</td>
            <td>${organisation.organisationAddress}</td>
            <td>${organisation.organisationEmail}</td>
            <td>${organisation.organisationName}</td>
        </tr>
            </#list>
        </#if>

        </tbody>
    </table>
    <li class="nav-item">
        <a class="nav-link" href="/organisation/add">Добавить организацию</a>
    </li>


</@c.page>
