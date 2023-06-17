create table performing_organisation (
    id serial not null,
    organisation_address varchar(255),
    organisation_email varchar(255),
    organisation_name varchar(255),
    organisation_phone_number varchar(255) not null,
    service_id int4,
    primary key (id));

alter table if exists performing_organisation
    add constraint performing_organisation_services_fk
        foreign key (service_id) references services