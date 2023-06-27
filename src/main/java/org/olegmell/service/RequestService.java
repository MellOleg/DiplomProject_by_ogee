package org.olegmell.service;

import org.olegmell.domain.Request;
import org.olegmell.domain.User;
import org.olegmell.repository.PerformingOrganisationRepository;
import org.olegmell.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;

@Service
public class RequestService{
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ServicesService servicesService;
    private final PerformingOrganisationRepository performingOrganisationRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository,
                          PerformingOrganisationRepository performingOrganisationRepository) {
        this.requestRepository = requestRepository;
        this.performingOrganisationRepository = performingOrganisationRepository;
    }

    public Integer createRequest (Request request, Integer statusId, Integer serviceId, User user){
        request.setAuthor(user);
        request.setStatus(statusService.getStatusById(statusId));
        request.setService(servicesService.getServicesById(serviceId));
        Request newRequest =  requestRepository.save(request);
        return newRequest.getId();
    }

    public void updateRequest (Request request, Integer addressId, Integer statusId, Integer serviceId, Integer orgId){
        Request existingRequest = requestRepository.getOne(request.getId());
        existingRequest.setAddress(addressService.getAddress(addressId));
        existingRequest.setStatus(statusService.getStatusById(statusId));
        existingRequest.setService(servicesService.getServicesById(serviceId));
        existingRequest.setPerformingOrganisation(performingOrganisationRepository.getOne(orgId));
        existingRequest.setText(request.getText());
        existingRequest.setFilename(request.getFilename());
        if (statusId == 3){ existingRequest.setCompletedTime(Calendar.getInstance().getTime()); }

        requestRepository.saveAndFlush(existingRequest);
    }
    public void updateRequest (Request request, Integer addressId, Integer statusId, Integer serviceId, Integer orgId, String filename){
        Request existingRequest = requestRepository.getOne(request.getId());
        existingRequest.setFilename(filename);
        existingRequest.setAddress(addressService.getAddress(addressId));
        existingRequest.setStatus(statusService.getStatusById(statusId));
        existingRequest.setService(servicesService.getServicesById(serviceId));
        existingRequest.setPerformingOrganisation(performingOrganisationRepository.getOne(orgId));
        existingRequest.setText(request.getText());
        existingRequest.setFilename(request.getFilename());
        if (statusId == 3){ existingRequest.setCompletedTime(Calendar.getInstance().getTime()); }

        requestRepository.saveAndFlush(existingRequest);
    }

    public Request getRequestById (Integer id){
        return requestRepository.getOne(id);
    }

    public Iterable<Request> getAllActiveRequests() {
        return requestRepository.findAllActiveRequests();
    }

    public Iterable<Request> getAllPendingRequests() {
        return requestRepository.findAllPendingRequests();
    }
    public Iterable<Request> getAllRequests(){
        return requestRepository.findAll(Sort.by(Sort.Direction.ASC, "createdTime"));
    }

    public void deleteById(Integer requestId) {
        if (exists(requestId)){
            requestRepository.deleteById(requestId);
        }
    }

    public boolean exists(int id){
        try{
            requestRepository.getOne(id); // will throw if not exists
            return true;           // entity does exist
        } catch(EntityNotFoundException e){
            return false;          // entity doesn't exist
        }
    }



}
