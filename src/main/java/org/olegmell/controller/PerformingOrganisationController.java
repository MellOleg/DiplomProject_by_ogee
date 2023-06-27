package org.olegmell.controller;

import org.olegmell.domain.AddressItem;
import org.olegmell.domain.PerformingOrganisation;
import org.olegmell.domain.Services;
import org.olegmell.service.PerformingOrganisationService;
import org.olegmell.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/organisation")
public class PerformingOrganisationController {
    @Autowired
    private PerformingOrganisationService organisationService;

    @Autowired
    private ServicesService servicesService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String organisationList(Model model) {
        Iterable<PerformingOrganisation> organisationIterable = organisationService.getAllOrganisation();

        model.addAttribute("organisationIterable", organisationIterable);
        return "organisationList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String addOrganisation(Model model){
        Iterable<Services> servicesIterable = servicesService.getAllServices();

        model.addAttribute("servicesIterable", servicesIterable);
        return "addOrganisation";
    }

    @GetMapping("/{orgId}")
    @ResponseBody
    public AddressItem getOrg(@PathVariable Integer orgId){
        return organisationService.getOrganisationItem(orgId);
    }
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping(path="/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public String addOrganisationPost( PerformingOrganisation performingOrganisation, Integer serviceId) {
//        organisationService.createOrganisation(performingOrganisation, serviceId);
//        return "redirect:/services";
//    }
}
