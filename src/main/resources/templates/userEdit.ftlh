<#import "parts/common.ftlh" as c>

<@c.page>
    <section class="main-block-userEdit">
        <div class="border-block-userEdit">
            User editor

            <form action="/user" method="post">
                <input type="text" name="username" value="${user.username}">
                <#list users as user>
                    <div>

                    </div>
                </#list>
                <input type="hidden" value="${user.id}" name="userId">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button type="submit">Save</button>
            </form>
        </div>
    </section>
</@c.page>