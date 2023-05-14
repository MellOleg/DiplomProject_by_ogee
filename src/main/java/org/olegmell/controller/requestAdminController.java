package org.olegmell.controller;

import org.olegmell.domain.Request;
import org.olegmell.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class requestAdminController {
    @Autowired
    private RequestRepository requestRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/requestEditAdmin")
    public String requestAdminPage(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Request> requests = requestRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            requests = requestRepository.findByTag(filter);
        } else {
            requests = requestRepository.findAll();
        }

        model.addAttribute("requests", requests);
        model.addAttribute("filter", filter);

        return "requestEditAdmin";
    }
}
