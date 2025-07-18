package org.olegmell.controller;

import org.olegmell.domain.AddressItem;
import org.olegmell.domain.PerformingOrganisation;
import org.olegmell.domain.ServiceItem;
import org.olegmell.domain.Services;
import org.olegmell.service.PerformingOrganisationService;
import org.olegmell.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/services")
public class ServicesController {
    @Autowired
    private ServicesService servicesService;

    @Autowired
    PerformingOrganisationService organisationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String ServicesList(Model model) {
        Iterable<ServiceItem> servicesIterable = servicesService.getServiceList();
        Iterable<PerformingOrganisation> organisationIterable = organisationService.getAllOrganisation();

        model.addAttribute("organisationIterable", organisationIterable);
        model.addAttribute("servicesIterable", servicesIterable);
        return "servicesList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String addService(Model model){
        return "addService";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String addServicePost(@RequestParam String service_name) {
        servicesService.createService(service_name);
        return "redirect:/admin/services";
    }

    @GetMapping ("/all")
    @ResponseBody
    public List<ServiceItem> ServicesListAll(Model model) {
        return servicesService.getServiceList();
    }

    @GetMapping ("/allservices")
    @ResponseBody
    public List<Services> ServiceListObject(Model model){return servicesService.getAllServices();}

    @GetMapping ("/{serviceid}/orgs")
    @ResponseBody
    public List<AddressItem> ServiceOrgs(@PathVariable Integer serviceid, Model model){
        return servicesService.getAllServiceOrgs(serviceid);
    }
}