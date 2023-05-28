package org.olegmell.controller;

import org.olegmell.domain.PerformingOrganisation;
import org.olegmell.domain.Services;
import org.olegmell.service.PerformingOrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class PerformingOrganisationController {
    @Autowired
    private PerformingOrganisationService organisationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String OrganisationList(Model model) {
        Iterable<PerformingOrganisation> organisationIterable = organisationService.getAllOrganisation();
        model.addAttribute("organisationIterable", organisationIterable);
        return "organisationList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String addOrganisation(Model model){
        return "addPerformingOrganisation";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String addOrganisationPost(@RequestParam String service_name) {
        organisationService.createOrganisation(service_name);
        return "redirect:/services";
    }
}
