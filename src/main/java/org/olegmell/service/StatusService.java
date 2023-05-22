package org.olegmell.service;

import org.olegmell.domain.Status;
import org.olegmell.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Status> getStatusById(int Id) {
        return statusRepository.findById(Id);
    }

    public String getStatusName (int Id){
        Optional<Status> matchedStatus = getStatusById(Id);
        if(matchedStatus.ifPresent)(status -> {status.getName();});
    }
}
