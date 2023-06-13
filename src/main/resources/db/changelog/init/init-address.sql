create table address (
    id serial not null,
    address varchar(255),
    is_deleted boolean,
    primary key (id));