create table address (
    id serial not null,
    address varchar(255),
    is_deleted boolean,
    primary key (id));

alter table if exists request
    add constraint request_address_fk
        foreign key (address_id) references address;