create sequence hibernate_sequence start 1 increment 1;

create table request (
                         id serial not null,
                         filename varchar(255),
                         address_id int4 not null ,
                         text varchar(255) not null ,
                         user_id int4,
                         status_id int4,
                         service_id int4,
                         creation_date TIMESTAMP,
                         last_modified TIMESTAMP,
                         completion_date TIMESTAMP,
                         primary key (id));

create table status (
                        id serial not null,
                        name varchar(255),
                        primary key (id));

create table usr (
                     id serial not null,
                     activation_code varchar(255),
                     active boolean not null,
                     email varchar(255),
                     password varchar(255) not null ,
                     username varchar(255) not null ,
                     user_role varchar(255) not null,
                     creation_date TIMESTAMP,
                     last_modified TIMESTAMP,
                     primary key (id));

create table services (
                          id serial not null,
                          service_name varchar(255),
                          primary key (id));

alter table if exists request
    add constraint request_user_fk
        foreign key (user_id) references usr;

alter table if exists request
    add constraint request_name_service_fk
        foreign key (service_id) references services;

alter table if exists request
    add constraint request_status_fk
        foreign key (status_id) references status;

