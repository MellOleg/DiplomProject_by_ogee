<#import "parts/common.ftlh" as c>

<@c.page>
    <section class="main-block-home">
        <div class="border-block-home">
            <div class="form-row">
                <div class="form-group col-md-6">

                </div>
            </div>

            <a type="button" class="btn btn-primary" href="/request/create">Добавить заявку</a>

            <#include "parts/requestList.ftlh">
        </div>
    </section>
</@c.page>
