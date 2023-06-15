<#include "security.ftlh">
<section class="main-block-requestEdit">
    <div class="border-block-requestEdit">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if request??>${request.text}</#if>" name="text" placeholder=" Подробно опишите проблему" />
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>


            <div class="form-group">
			    <label for="address">Aдрес</label>
                    <select class="form-control select2-single" id="address" name="address">
                            <option value="">Укажите адрес, требующий исполнения услуги</option>
                    </select>
            </div>



            <div class="form-group">
                <label for="requestServices">Выберите услугу</label>
                <select class="form-control" id="requestServices" name="requestServices">
                    <#list services as requestServices>
                        <option value="${requestServices.id}">
                            ${requestServices.service_name}
                        </option>
                    </#list>
                </select>
            </div>


            <div class="form-group">
                <#if isAdmin>
                    <label for="requestStatus">Выберите статус</label>
                        <select class="form-control" id="requestStatus" name="requestStatus">
                            <#list status as requestStatus>
                                <option value="${requestStatus.id}">
                                    ${requestStatus.name}
                                </option>
                            </#list>
                        </select>
                </#if>
                <#if !isAdmin>
                    <select hidden class="form-control" id="requestStatus" name="requestStatus">
                        <#list status as requestStatus>
                            <option value="${requestStatus.id}">
                                ${requestStatus.name}
                            </option>
                        </#list>
                    </select>
                 </#if>
            </div>
			<div class="preview">
				<#if !request??>
                    <img id="imagePreview" src="" class="img-fluid">
                <#elseif request.filename??>
                    <img id="imagePreview" src="/img/${request.filename}" class="img-fluid">
				<#elseif request.id??>
				    <img id="imagePreview" src="" class="img-fluid">
				<#else>
					<img id="imagePreview" src="/img/placeholder-16-9.jpg" class="img-fluid">
                </#if>
			</div>
            <div class="image-file">
				<label class="image-file-label" for="imageInput">Выбрать файл</label>
                <input class="form-control" type="file" name="file" accept="image/*" id="imageInput">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" /></br>
            <input type="hidden" name="id" value="<#if request??>${request.id}<#else>-1</#if> " /></br>
            <div class="form-group mt-3">
                <button type="submit" class="btn btn-primary ">Сохранить</button>
            </div>
        </form>
        <script type="text/javascript">
        imageInput.onchange = evt => {
              const [file] = imageInput.files
              if (file) {
                imagePreview.src = URL.createObjectURL(file)
              }
            }
        </script>

        <script>
            $("#address").select2({
                theme: "bootstrap4",
                ajax: {
                    url: '/address',
                    dataType: 'json',
                    delay: 250,
                    processResults: function (response) {
                        return {
                            results: response
                        };
                    },
                    cache: true
                }
            });
        </script>
    </div>
    </div>
</section>