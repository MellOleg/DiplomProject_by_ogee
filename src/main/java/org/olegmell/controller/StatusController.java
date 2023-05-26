package org.olegmell.controller;

import org.apache.velocity.exception.ResourceNotFoundException;
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

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String statusList(Model model){
        Iterable<Status> statusesIterable = statusService.getAllStatuses();
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
        statusService.createStatus(status);
        return "redirect:/status";
    }

    public String getStatusName (int Id){
        return statusService.getStatusName(Id);
    }

    public Status getStatus(int Id){
        return statusService.getStatusById(Id);
    }
}
