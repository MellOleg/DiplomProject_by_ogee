<#include "security.ftlh">
<section class="main-block-requestList">
    <div class="border-block-requestList">
        <div class="card-columns" xmlns="http://www.w3.org/1999/html">
            <#list requests as request>
                <div class="card card-blog">
                    <div class="card-image">
                    <#if request.filename??>
                        <img src="/img/${request.filename}" class="card-img-top">
                    <#else>
                        <img src="/img/placeholder-16-9.jpg" class="card-img-top">
                    </#if>
                    </div>
                    <div class="table">
                        <span>${request.text}</span><br>
                        <i>#${request.tag}</i><br>
                        <span>Статус - ${request.status.name}</span><br>
                        <span>Услуга - ${request.service.service_name}</span><br>
                    </div>
                    <div class="chmo">
                        <#if isAdmin>
                            <a href="myrequests/${request.author.id}">${request.authorName}</a><!-- authorName -->

                            <a class="btn btn-primary" href="/request/${request.id}/edit">
                                Изменить
                            </a>
                            <a class="btn btn-warning" href="/request/${request.id}/delete">
                                Удалить
                            </a>
                        </#if>
                    </div>
                </div>

            <#else>
                Объявлений нет
            </#list>
<#--            <div class="card card-blog">-->
<#--                <div class="card-image">-->
<#--                    <a href="#"> <img class="img" src="http://adamthemes.com/demo/code/cards/images/blog01.jpeg">-->
<#--                        <div class="card-caption"> Quisque a bibendum magna </div>-->
<#--                    </a>-->
<#--                    <div class="ripple-cont"></div>-->
<#--                </div>-->
<#--                <div class="table">-->
<#--                    <h6 class="category text-info">Cinema</h6>-->
<#--                    <p class="card-description"> Lorem ipsum dolor sit amet, consectetur adipis cingelit. Etiam lacinia elit et placerat finibus. Praesent justo metus, pharetra vel nibh sit amet, tincidunt posuere nulla. Vivamus odio antement, feugiat eget nisi sit amet, scelerisque dignissim velit antement. </p>-->
<#--                </div>-->
<#--            </div>-->
        </div>
    </div>

</section>
<#include "footer.ftlh">