package org.olegmell.controller;

import org.olegmell.domain.Statuses;
import org.olegmell.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/statuses")
public class StatusController {
    @Autowired
    private StatusRepository statusRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String statusList(Model model){
        Iterable<Statuses> statusesIterable = statusRepository.findAll();
        model.addAttribute("statusesIterable", statusesIterable);
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
        Statuses statuses = new Statuses(statusName);
        statusRepository.save(statuses);
        return "redirect:/statuses";
    }
}
