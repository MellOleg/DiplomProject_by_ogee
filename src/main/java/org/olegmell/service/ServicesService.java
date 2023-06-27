package org.olegmell.service;

import org.olegmell.domain.*;
import org.olegmell.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<AddressItem> getAllServiceOrgs(Integer serviceid){
         return servicesRepository.getOne(serviceid).getOrganisationsServices()
                        .stream().map(this::mapToItem)
                        .collect(Collectors.toList());
    }

    public List<ServiceItem> getServiceList () {
        return servicesRepository.findAll()
                    .stream().map(this::mapToServiceItem)
                    .collect(Collectors.toList());
        }

    private ServiceItem mapToServiceItem(Services service) {
        List<String> orgNames = service.getOrganisationsServices()
                .stream().
                map(PerformingOrganisation::getDetails).
                collect(Collectors.toList());

        return ServiceItem.builder()
                .id(service.getId())
                .name(service.getService_name())
                .organisationName(orgNames)
                .count(orgNames.size())
                .build();
    }

    private AddressItem mapToItem(PerformingOrganisation org) {
        return AddressItem.builder()
                .id(org.getId())
                .text(org.getOrganisationName())
                .build();
    }

}
