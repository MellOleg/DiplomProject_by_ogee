<#import "parts/common.ftlh" as c>

<@c.page>

    <script src="/static/openweather.js"></script>
    <div class="wrapper">
        <main class="page">
            <section class="main-block">
                <div class="main-info  main-content">
                    <div class="main_block_body">
                        <div class="page-wrapper">
                            <div class="left-panel-wrapper">
                                <div class="left-panel">

                                        <div class="">

                                            <div id="wrapper-bg" class="text-white bg-image shadow-4-strong"
                                                 style="background-image: url('img/clouds.gif')">
                                                <!-- Main current data -->
                                                <div class="card-header p-4 border-0">
                                                    <div class="text-center mb-3">
                                                        <p class="h2 mb-1" id="wrapper-name"></p>
                                                        <p class="mb-1" id="wrapper-description"></p>
                                                        <p class="display-1 mb-1" id="wrapper-temp"></p>
                                                        <span class="">Давление: <span id="wrapper-pressure"></span></span>
                                                        <span class="mx-2">|</span>
                                                        <span class="">Влажность: <span id="wrapper-humidity"></span></span>
                                                    </div>
                                                </div>

                                                <!-- Hourly forecast -->
                                                <div class="card-body p-4 border-top border-bottom mb-2">
                                                    <div class="row text-center">
                                                        <div class="col-2">
                                                            <strong class="d-block mb-2">Сейчас</strong>
                                                            <img id="wrapper-icon-hour-now" src="" class="" alt="" />
                                                            <strong class="d-block" id="wrapper-hour-now"></strong>
                                                        </div>

                                                        <div class="col-2">
                                                            <strong class="d-block mb-2" id="wrapper-time1"></strong>
                                                            <img id="wrapper-icon-hour1" src="" class="" alt="" />
                                                            <strong class="d-block" id="wrapper-hour1"></strong>
                                                        </div>

                                                        <div class="col-2">
                                                            <strong class="d-block mb-2" id="wrapper-time2"></strong>
                                                            <img id="wrapper-icon-hour2" src="" class="" alt="" />
                                                            <strong class="d-block" id="wrapper-hour2"></strong>
                                                        </div>

                                                        <div class="col-2">
                                                            <strong class="d-block mb-2" id="wrapper-time3"></strong>
                                                            <img id="wrapper-icon-hour3" src="" class="" alt="" />
                                                            <strong class="d-block" id="wrapper-hour3"></strong>
                                                        </div>

                                                        <div class="col-2">
                                                            <strong class="d-block mb-2" id="wrapper-time4"></strong>
                                                            <img id="wrapper-icon-hour4" src="" class="" alt="" />
                                                            <strong class="d-block" id="wrapper-hour4"></strong>
                                                        </div>

                                                        <div class="col-2">
                                                            <strong class="d-block mb-2" id="wrapper-time5"></strong>
                                                            <img id="wrapper-icon-hour5" src="" class="" alt="" />
                                                            <strong class="d-block" id="wrapper-hour5"></strong>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- Daily forecast -->
                                                <div class="card-body px-5">
                                                    <div class="row align-items-center">
                                                        <div class="col-lg-6">
                                                            <strong>Сегодня</strong>
                                                        </div>

                                                        <div class="col-lg-2 text-center">
                                                            <img id="wrapper-icon-today" src="" class="w-100" alt="" />
                                                        </div>

                                                        <div class="col-lg-4 text-end">
                                                            <span id="wrapper-forecast-temp-today"></span>
                                                        </div>
                                                    </div>

                                                    <div class="row align-items-center">
                                                        <div class="col-lg-6">
                                                            <strong>Завтра</strong>
                                                        </div>

                                                        <div class="col-lg-2 text-center">
                                                            <img id="wrapper-icon-tomorrow" src="" class="w-100" alt="" />
                                                        </div>

                                                        <div class="col-lg-4 text-end">
                                                            <span id="wrapper-forecast-temp-tomorrow">28</span>
                                                        </div>
                                                    </div>

                                                    <div class="row align-items-center">
                                                        <div class="col-lg-6">
                                                            <strong>Послезавтра</strong>
                                                        </div>

                                                        <div class="col-lg-2 text-center">
                                                            <img id="wrapper-icon-dAT" src="" class="w-100" alt="" />
                                                        </div>

                                                        <div class="col-lg-4 text-end">
                                                            <span id="wrapper-forecast-temp-dAT">28</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>

                                </div>
                            </div>

                            <div class="content-wrapper">
                                <div class="content">
                                    <h1>Вас приветствует сервис по резервированию услуг жильцов многоквартирных домов</h1>
                                    <#--
                                    <#--                     <p>Администрация сервиса оставляет за собой право на окончательную интрепритацию правил.</p>-->
                                    <p><span>Сервис предназначен для резервирования и исполнения услуг, связанных с работой желищнно-комунального хозяйства. Завку следует формуровать в соответствии с правилами создания заяки, это позволит ускорить ответ на вашу заявку и/или ее исполнение. </span></p>
                                    <div class="button-block">
                                        <a href="/request/create" class="button-info">Оставить заявку</a>
                                    </div>
                                </div>
                            </div>

                            <div class="right-panel-wrapper">
                                <div class="right-panel">
                                    <p> </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="main_block-image _ibg ">
                    <img src="https://wallpapercrafter.com/desktop4/1213846-bridge-Belarus-Grodno-brewery-2K.jpg" alt="">
                </div>




            </section>
            <section id="about" class=" mb-5 py-3 wow fadeIn" data-wow-delay="0.2s">
                <div class="container">
                    <!-- Grid row -->
                    <div class="row pt-2 mt-5">

                        <!-- Grid column -->
                        <div class="col-lg-5 col-md-12 mb-3 wow fadeIn" data-wow-delay="0.4s">
                            <!-- Image -->
                            <img src="https://img.freepik.com/free-vector/online-document-form-digital-agreement-electronic-contract-internet-questionnaire-to-do-list-note-voting-ballot-poll-flat-design-element-concept-illustration_335657-2013.jpg?w=826&t=st=1685973630~exp=1685974230~hmac=9cb8673749191d2c460ac33e2bb7118fc8139ff1078019d9b2d08f24409f6073"
                                 class="img-fluid z-depth-1 rounded"
                                 alt="My photo">
                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-lg-6 ml-auto col-md-12 wow fadeIn" data-wow-delay="0.4s">

                            <!-- Secion heading -->
                            <h3 class="mb-5 dark-grey-text title font-weight-bold wow fadeIn" data-wow-delay="0.2s">
                                <strong>Для комфортного пользования сервисом, необходимо ознакомиться с
                                    правилами</strong>
                            </h3>

                            <!-- Description -->
                            <p>1.1 Текст заявок, подлежащих публикации, должен быть изложен на русском языке.</p>
                            <p>1.2 Заявка содержит вопрос в отношении одного объекта</p>
                            <p>1.3 Для публикации принимаются фотографии в формате *jpg, *png и иных аналогичных
                                форматов размером не более 2 Мб</p>
                            <p>1.4 Не допускается публикация фотографий, содержащих изображения людей, имеющих
                                разрешение и качество, недостаточное
                                для различения объектов и выполненных на них работ, фотографий, требующих
                                редактирования, в частности переворачивания, кадрирования или приближения.</p>
                            <p>1.5 В одной заявке пользователь может направить не более одной фотографии.</p>
                        </div>

                        <div class="line"></div>
                        <!-- Grid column -->
                        <div class="col-lg-6  ml-auto col-md-12 wow fadeIn" data-wow-delay="0.4s">

                            <!-- Secion heading -->
                            <h3 class="mb-5 dark-grey-text title font-weight-bold wow fadeIn" data-wow-delay="0.2s">
                                <strong>Заявка так же может быть удалена по седующим причинам</strong>
                            </h3>

                            <!-- Description -->
                            <p>2.1 Тематика заявок не относится к сфере жилищно-коммунального хозяйства и городского
                                хозяйства Республики Беларусь</p>
                            <p>2.2 Заявка содержит вопрос в отношении одного и того же объекта, ответ на который уже был
                                дан ранее.</p>
                            <p>2.3 Заявка не содержит конкретных фактов, на основании которых возможно провести
                                проверку/установить факт невыполнения/некачественного выполнения заявки.</p>
                            <p>2.4 Заявка содержит нецензурную лексику, либо ее производные.</p>
                            <p>2.5 Фотография не отвечает требованиям пунктов 1.3 и 1.4 настоящих Правил</p>
                            <p>2.6 Заявка содержит описание нескольких проблем по разным темам.</p>
                        </div>
                        <div class="col-lg-5 col-md-12 mb-3  wow fadeIn" data-wow-delay="0.4s">
                            <!-- Image -->
                            <img src="https://img.freepik.com/free-vector/removing-goods-from-basket-refusing-to-purchase-changing-decision-item-deletion-emptying-trash-online-shopping-app-laptop-user-cartoon-character_335657-2566.jpg?w=826&t=st=1685974072~exp=1685974672~hmac=48fcf6bd982fc0d8616ce6c1369e0cbb5b46565123a3546ff715048adeace98f"
                                 class="img-fluid z-depth-1 rounded"
                                 alt="My photo">
                        </div>

                        <div class="line"></div>

                        <div class="col-lg-5 col-md-12 mb-3 wow fadeIn" data-wow-delay="0.4s">
                            <!-- Image -->
                            <img src="https://cdn-icons-png.flaticon.com/512/1154/1154176.png?w=826&t=st=1686513963~exp=1686514563~hmac=5f67e16b6cf8fc1bcea0dfd8cec21dd94ad1f8ff717847f50667fcf2c55addf6"
                                 class="img-fluid z-depth-1 rounded"
                                 alt="My photo">
                        </div>
                        <!-- Grid column -->


                        <!-- Grid column -->
                        <div class="col-lg-6 ml-auto col-md-12 wow fadeIn" data-wow-delay="0.4s">

                            <!-- Secion heading -->
                            <h3 class="mb-5 dark-grey-text title font-weight-bold wow fadeIn" data-wow-delay="0.2s">
                                <strong>Прайслист за предоставляемые услуги</strong>
                            </h3>

                            <!-- Description -->
                            <p>За предоставляемые услуги взымается плата, указанная в отдельном документе по ссылке <a href="/priceList">Ссылка</a></p>
                            <p>Все выставленные цены соответствуют действующим ценам оргранизаций и их услуг</p><br>
                            <p>Администрация сервиса оставляет за собой право на окончательную интрепритацию размещнного текста.<p>

                        </div>
                    </div>
                    <!-- Grid row -->
                </div>
            </section>
        </main>
    </div>

</@c.page>
<#include "parts/footer.ftlh">