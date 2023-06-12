package org.olegmell.service;

import org.olegmell.domain.Status;
import org.olegmell.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Status getStatusById(int Id) {
        return statusRepository.getOne(Id);
    }

    public String getStatusName (int Id){
        return getStatusById(Id).getName();
    }

    public void createStatus (Status newStatus){
        statusRepository.save(newStatus);
    }


}
