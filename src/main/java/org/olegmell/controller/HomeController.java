package org.olegmell.controller;

import org.olegmell.domain.*;
import org.olegmell.service.PerformingOrganisationService;
import org.olegmell.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private PerformingOrganisationService organisationService;
    @Autowired
    private ServicesService servicesService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/priceList")
    private String priceServiceList(Model model){
        Iterable<Services> servicesIterable = servicesService.getAllServices();
        Iterable<PerformingOrganisation> organisationIterable = organisationService.getAllOrganisation();

        model.addAttribute("organisationIterable", organisationIterable);
        model.addAttribute("servicesIterable", servicesIterable);
        return "priceList";
    }
}
