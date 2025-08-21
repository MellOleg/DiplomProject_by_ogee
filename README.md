## Дипломный проект: Система бронирования/обслуживания для жильцов

Кратко: серверное приложение на Spring Boot для управления заявками жильцов (создание, просмотр, обновление, удаление), с REST API, безопасностью, миграциями БД и уведомлениями по email.

### Основные возможности
- **CRUD-заявки**: создание, чтение, обновление и удаление заявок жильцов.
- **REST API**: взаимодействие с клиентом через JSON-эндпоинты.
- **Валидации и связи**: модель данных со связями и проверками (Hibernate Validator).
- **Безопасность**: интеграция Spring Security (аутентификация/авторизация).
- **Email-уведомления**: отправка писем (SMTP) при событийных действиях.
- **Миграции БД**: управление схемой через Liquibase.
- **Шаблоны**: Freemarker/Thymeleaf для серверных представлений (где требуется).
- **Загрузка файлов**: поддержка загрузок (см. `upload.path`).

### Технологический стек
- **Java**: 16
- **Spring Boot**: 2.4.3 (Web, Data JPA, Security, Mail, DevTools)
- **БД**: PostgreSQL + Hibernate (JPA)
- **Миграции**: Liquibase
- **Сборка**: Maven
- **Тесты**: JUnit 5, Mockito, Maven Surefire

### Архитектура (высокоуровнево)
- **`controller`**: REST-контроллеры
- **`service`**: бизнес-логика
- **`domain`**: DTO/модели
- **`repository`**: доступ к данным (JPA)
- **`resources/db/changelog`**: скрипты Liquibase

### Быстрый старт
1) Установите зависимости:
- JDK 16+
- Maven 3.8+
- PostgreSQL 12+

2) Создайте БД PostgreSQL (пример):
```sql
CREATE DATABASE comapplicationrw;
CREATE USER postgres WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE comapplicationrw TO postgres;
```

3) Настройте приложение:
- Файл `src/main/resources/application.properties` содержит дефолтные параметры:
  - `spring.datasource.url=jdbc:postgresql://localhost/comapplicationrw`
  - `spring.datasource.username=postgres`
  - `spring.datasource.password=...`
  - `spring.jpa.hibernate.ddl-auto=validate`
  - `spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml`
  - `spring.mail.*` (SMTP для отправки писем)
  - `upload.path=/C:/uploads`
- Рекомендуется переопределять чувствительные значения переменными окружения или профилями (например, `application-local.properties`).

4) Сборка и запуск:
```bash
mvn clean install
mvn spring-boot:run
# или запуск собранного JAR:
java -jar target/comApplicationRW-1.0-SNAPSHOT.jar
```

### Тесты
- Запуск всех тестов:
```bash
mvn clean test
```
- В проекте используются JUnit 5 и Mockito. Конфигурация плагина Surefire и зависимостей уже добавлена в `pom.xml`.

### Конфигурация почты (SMTP)
- Свойства `spring.mail.*` должны соответствовать вашему провайдеру SMTP.
- Для продакшн-окружений используйте секреты/переменные окружения вместо хранения паролей в репозитории.

### Настройка миграций БД
- Liquibase автоматически применяет изменения при старте приложения согласно `spring.liquibase.change-log`.
- Для локальной разработки миграции находятся в `src/main/resources/db/changelog`.

### Полезные свойства
- **Логи SQL**: `spring.jpa.show-sql=true`
- **Путь загрузок**: `upload.path=/C:/uploads` (убедитесь, что путь существует и доступен на запись)
- **Отладка безопасности**: `logging.level.org.springframework.security=DEBUG`

### Развитие
- Добавление новых эндпоинтов: создавайте контроллеры в `controller`, бизнес-логику в `service`, сущности/DTO в `domain`, и репозитории в `repository`.
- При изменении схемы обновляйте миграции в `db/changelog`.

### Лицензия
- Проект учебный (дипломный). Лицензирование — по договоренности с автором.
