package org.olegmell.service;

import org.olegmell.domain.Services;
import org.olegmell.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
