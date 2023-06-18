package org.olegmell.service;

import org.olegmell.domain.PerformingOrganisation;
import org.olegmell.repository.PerformingOrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformingOrganisationService {

    @Autowired
    private PerformingOrganisationRepository organisationRepository;

    @Autowired
    private ServicesService servicesService;

    @Autowired PerformingOrganisationService(PerformingOrganisationRepository organisationRepository){
        this.organisationRepository = organisationRepository;
    }

    public List<PerformingOrganisation> getAllOrganisation(){
        return organisationRepository.findAll();
    }

//    public void createOrganisation(PerformingOrganisation performingOrganisation,
//                                   Integer serviceId){
//        performingOrganisation.setServices(servicesService.getServicesById(serviceId));
//        PerformingOrganisation organisation = organisationRepository.save(performingOrganisation);
//    }


}
