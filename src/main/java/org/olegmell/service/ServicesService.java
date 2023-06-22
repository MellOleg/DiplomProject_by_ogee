package org.olegmell.service;

import org.olegmell.domain.AddressItem;
import org.olegmell.domain.PerformingOrganisation;
import org.olegmell.domain.ServiceItem;
import org.olegmell.domain.Services;
import org.olegmell.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    public  Services getServicesById(int Id){
        return servicesRepository.getOne(Id);
    }

    public void createService(String service_name){
        Services services = new Services(service_name);
        servicesRepository.save(services);
    }

    public List<ServiceItem> getServiceList () {
        return servicesRepository.findAll()
                    .stream().map(this::mapToServiceItem)
                    .collect(Collectors.toList());
        }

    private ServiceItem mapToServiceItem(Services service) {
        List<String> orgNames = service.getOrganisationsServices()
                .stream().
                map(PerformingOrganisation::getOrganisationName).
                collect(Collectors.toList());

        return ServiceItem.builder()
                .id(service.getId())
                .name(service.getService_name())
                .organisationName(orgNames)
                .count(orgNames.size())
                .build();
    }

}
