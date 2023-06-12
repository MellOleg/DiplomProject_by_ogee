package org.olegmell.service;

import org.olegmell.domain.Request;
import org.olegmell.domain.Status;
import org.olegmell.domain.User;
import org.olegmell.repository.RequestRepository;
import org.olegmell.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RequestService{
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    StatusService statusService;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Integer createRequest (Request request, Integer statusId, Integer serviceId, User user){
        request.setAuthor(user);
        request.setStatus(statusService.getStatusById(statusId));
        request.setService(servicesService.getServicesById(serviceId));
        Request newRequest =  requestRepository.save(request);
        return newRequest.getId();
    }

    public Request getRequestById (Integer id){
        return requestRepository.getOne(id);
    }

    public Iterable<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Iterable<Request> getAllByTag(String filter) {
        return requestRepository.findByTag(filter);
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
