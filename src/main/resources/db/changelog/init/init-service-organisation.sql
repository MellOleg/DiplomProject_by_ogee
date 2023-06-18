create table service_organisation (
    service_id int4 not null,
    organisation_id int4 not null,
    primary key (service_id, organisation_id));

alter table if exists service_organisation
    add constraint service_organisation_services_fk
        foreign key (service_id) references services (id),
    add constraint service_organisation_performing_organisation_fk
        foreign key (organisation_id) references performing_organisation (id);