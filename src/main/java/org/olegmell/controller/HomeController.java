package org.olegmell.controller;

import org.olegmell.domain.*;
import org.olegmell.repository.RequestRepository;
import org.olegmell.repository.ServicesRepository;
import org.olegmell.repository.StatusRepository;
import org.olegmell.service.PerformingOrganisationService;
import org.olegmell.service.RequestService;
import org.olegmell.service.ServicesService;
import org.olegmell.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class HomeController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private PerformingOrganisationService organisationService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ServicesService servicesService;

    @Value("${upload.path}")
    private String uploadPath;
    private User user;

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

    @GetMapping("/home")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Request> requests = requestService.getAllActiveRequests();
        Iterable<Status> requestStatus = statusService.getAllStatuses();
        Iterable<Services> requestServices = servicesService.getAllServices();


        if (filter != null && !filter.isEmpty()) {
            requests = requestService.searchByTag(filter);
        } else {
            requests = requestService.getAllActiveRequests();
        }
        model.addAttribute("services", requestServices);
        model.addAttribute("requests", requests);
        model.addAttribute("filter", filter);
        model.addAttribute("status", requestStatus);

        return "home";
    }

}
