package org.olegmell.controller;

import org.olegmell.domain.Status;
import org.olegmell.repository.StatusRepository;
import org.olegmell.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private static StatusRepository statusRepository;

    @Autowired
    private StatusService statusService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String statusList(Model model){
        Iterable<Status> statusesIterable = statusRepository.findAll();
        model.addAttribute("statusIterable", statusesIterable);
        return "statusList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String addStatus(Model model){
        return "addStatus";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String addStatusPost(@RequestParam String statusName,
                                 Model model) {
        Status status = new Status(statusName);
        statusRepository.save(status);
        return "redirect:/status";
    }

    public String getStatusName (int Id){
        Status status = statusRepository.getOne(Id);
        return status.getName();
    }

    public static Status getStatus(int Id){
        return statusRepository.getOne(Id);
    }
}
