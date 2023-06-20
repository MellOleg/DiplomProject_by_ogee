<#import "parts/common.ftlh" as c>

<@c.page>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Услуга</th>
            <th scope="col">Исполняющая организация</th>
            <th scope="col">Стоимость услуги</th>
        </tr>
        </thead>
        <tbody>
        <#list servicesIterable as service>
        <tr>
            <td>${service.service_name}</td>
            <td><#list organisationIterable as organisation>
                </#list>

            </td>
            <#function randomNumber salt>
                <#local str= .now?long />
                <#assign str = (str * salt)/3 />
                <#return str[(str?string?length-5)..]/>
            </#function>


            <#assign rnd = randomNumber(-1)/>

            function getRandomInt(max) {
            return Math.floor(Math.random() * max);
            }
            <td>${rnd}</td>
        </tr>
        </#list>
        </tbody>
    </table>
</@c.page>