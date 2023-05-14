create sequence hibernate_sequence start 2 increment 1;

create table request (
                         id int8 not null,
                         filename varchar(255),
                         tag varchar(255) not null ,
                         text varchar(255) not null ,
                         status varchar(255),
                         user_id int8,
                         service_id int8,
                         primary key (id));

create table user_role (
                           user_id int8 not null,
                           roles varchar(255));

create table usr (
                     id int8 not null,
                     activation_code varchar(255),
                     active boolean not null,
                     email varchar(255),
                     password varchar(255) not null ,
                     username varchar(255) not null ,
                     primary key (id));

create table services (
                          id int8 not null,
                          service_name varchar(255),
                          primary key (id));

alter table if exists request
    add constraint request_user_fk
        foreign key (user_id) references usr;

alter table if exists request
    add constraint request_name_service_fk
        foreign key (service_id) references services;

alter table if exists user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr;