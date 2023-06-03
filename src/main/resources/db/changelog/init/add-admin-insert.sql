insert into usr (username, password, active, user_role, creation_date, last_modified)
values ('admin', '123', true, 'ADMIN', current_timestamp, current_timestamp);

insert into services(id, service_name)
values (1, 'sueta');