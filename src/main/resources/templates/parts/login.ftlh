<#macro login path isRegisterForm>

        <section class="view main-block intro-2">
                <div class="main-content">
                    <div class="main-form">
                    <form action="${path}"  method="post" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
            <div class="mask rgba-indigo-light h-100 d-flex justify-content-center align-items-center">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-5 col-lg-6 col-md-10 col-sm-12 mx-auto mt-lg-5">

                            <!-- Form with header -->
                            <div class="card wow fadeIn" data-wow-delay="0.3s">
                                <div class="card-body">

                                    <!-- Header -->
                                    <div class="form-header pink-gradient">
                                        <h3>
                                            <i class="fas fa-user mt-2 mb-2"></i> Log in:</h3>
                                    </div>

                                    <!-- Body -->
                                    <div class="md-form mb-0">
                                        <i class="fas fa-user prefix white-text"></i>
                                        <input type="text" name="username" value="<#if user??>${user.username}</#if>" class="form-control ${(usernameError??)?string('is-invalid', '')}" placeholder="Введите имя пользователя"/>
                                        <#if usernameError??>
                                            <div class="invalid-feedback">
                                                ${usernameError}
                                            </div>
                                        </#if>
                                        <label for="orangeForm-name">Имя пользователя</label>
                                    </div>
                                    <div class="md-form mb-0">
                                        <i class="fas fa-lock prefix white-text"></i>
                                        <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="Введите пароль"/>
                                        <#if passwordError??>
                                            <div class="invalid-feedback">
                                                ${passwordError}
                                            </div>
                                        </#if>
                                        <label for="orangeForm-pass">Пароль</label>
                                    </div>

                                    <hr>
                                    <#if isRegisterForm>
                                        <div class="md-form mb-0">
                                            <i class="fas fa-lock prefix white-text"></i>
                                            <input type="email" name="email" value="<#if user??>${user.email}</#if>" class="form-control ${(emailError??)?string('is-invalid', '')}" placeholder="example@example.com"/>
                                            <#if emailError??>
                                                <div class="invalid-feedback">
                                                    ${emailError}
                                                </div>
                                            </#if>
                                            <label for="orangeForm-pass">Почта</label>
                                        </div>
                                    </#if>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <#if !isRegisterForm>
                                        <a href="/registration">Зарегистрироваться</a></br>
                                    </#if>
                                    <div class="text-center">
                                        <button class="btn btn-primary mt-2" type="submit"/><#if isRegisterForm>Зарегистрироваться<#else>Войти</#if></button></br>
                                    </div>
                                </div>
                            </div>
                            <!-- Form with header -->

                        </div>
                    </div>
                </div>
            </div>
                    </form>
                </div>
            </div>

            <div class="main_block-image _ibg ">
                <img src="https://wallpapercrafter.com/desktop4/1213846-bridge-Belarus-Grodno-brewery-2K.jpg" alt="">
            </div>
        </section>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit"/>Выйти</button>
    </form>
</#macro>





