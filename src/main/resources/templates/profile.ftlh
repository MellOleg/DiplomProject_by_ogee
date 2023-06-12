<#import "parts/common.ftlh" as c>

<@c.page>
    <section class="main-block-profile">
        <div class="border-block-profile">
            <h5>Вы вошли как: "${username}"</h5>
            <a>На этой странице вы можете изменить свой пароль</a></br>
            ${request?ifExists}
            <form method="post" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
                <div class="form-group row mt-2">
                    <label class="col-sm-1 col-form-label"> Пароль: </label>
                    <div class="col-sm-5">
                        <input type="password" name="password" class="form-control" placeholder="Введите новый пароль"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-1 col-form-label"> Почта: </label>
                    <div class="col-sm-5">
                        <input type="email" name="email" class="form-control" placeholder="example@example.com"
                               aria-describedby="emailHelp" value="${email!''}"/>
                        <div id="emailHelp" class="form-text">После изменения пароля, проверьте свою почту</div>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary" type="submit"/>
                Сохранить изменеиня</button></br>
            </form>
        </div>
    </section>
</@c.page>