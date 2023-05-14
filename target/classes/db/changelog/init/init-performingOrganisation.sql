create table performing_organisation (
    id int8 not null,
    performing_address varchar(255),
    performing_email varchar(255),
    performing_name varchar(255),
    performing_number int4 not null,
    service_id int8,
    primary key (id));

alter table if exists performing_organisation
    add constraint performing_organisation_services_fk
        foreign key (service_id) references services