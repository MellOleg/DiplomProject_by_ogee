<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>Вас приветствует сервис по резервированию услуг жильцов многоквартирных домов</h5></br>
    <a>Здесь вы можете оставить свои объявления касаемо данной тематики</a></br>
    <a>Обратная связь: example@example.com</a>

    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
            Dropdown
        </button>
        <div class="dropdown-menu">
            <button class="dropdown-item" type="button">Action</button>
            <button class="dropdown-item" type="button">Another action</button>
            <button class="dropdown-item" type="button">Something else here</button>
        </div>
    </div>
    <form>
        <div class="form-group">
            <label for="exampleFormControlSelect1">Example select</label>
            <select class="form-control" id="exampleFormControlSelect1">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleFormControlSelect2">Example multiple select</label>
            <select multiple class="form-control" id="exampleFormControlSelect2">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Example textarea</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
    </form>


      <div class="container">
        <h3>Form with Select Field</h3>
        <form action="/submit-form" method="POST">
          <div class="form-group">
            <label for="statusSelect">Status:</label>
            <select class="form-control" id="statusSelect" name="status">
              <option value="option1">Option 1</option>
              <option value="option2">Option 2</option>
              <option value="option3">Option 3</option>
            </select>
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>

</@c.page>