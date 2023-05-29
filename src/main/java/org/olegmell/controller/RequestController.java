package org.olegmell.controller;

import org.olegmell.domain.Request;
import org.olegmell.domain.Services;
import org.olegmell.domain.Status;
import org.olegmell.domain.User;
import org.olegmell.repository.RequestRepository;
import org.olegmell.repository.StatusRepository;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    StatusService statusService;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/request")
    public String requestAdminPage(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Request> requests = requestService.getAllRequests();

        if (filter != null && !filter.isEmpty()) {
            requests = requestService.getAllByTag(filter);
        } else {
            requests = requestService.getAllRequests();
        }

        model.addAttribute("requests", requests);
        model.addAttribute("filter", filter);

        return "home";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{user}")
    public String deleteRequest(
            @PathVariable Long user,
            @RequestParam("request") Integer requestId
    ) throws IOException {

        requestService.deleteById(requestId);

        return "redirect:/home";
    }


    private void saveFile(Request request, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            request.setFilename(resultFilename);
        }
    }

    @GetMapping("/create")
    public String createRequest(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Request> requests = requestService.getAllRequests();
        Iterable<Services> requestServices = servicesService.getAllServices();
        Iterable<Status> requestStatus = statusService.getAllStatuses();

        if (filter != null && !filter.isEmpty()) {
            requests = requestService.getAllByTag(filter);
        } else {
            requests = requestService.getAllRequests();
        }

        model.addAttribute("services", requestServices);
        model.addAttribute("requests", requests);
        model.addAttribute("filter", filter);
        model.addAttribute("status", requestStatus);

        return "createOrEditRequest";
    }

    @PostMapping(path ="/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String createrequest(
            @AuthenticationPrincipal User user,
            @Valid Request request,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file")MultipartFile file,
            @RequestParam("requestServices") Integer serviceId,
            @RequestParam("requestStatus")Integer statusId)
            throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("request", request);
        } else {
            saveFile(request, file);
            model.addAttribute("request", null);
            Integer newRequestId = requestService.createRequest(request, statusId, serviceId, user);
        }

        Iterable<Services> requestServices = servicesService.getAllServices();
        Iterable<Request> requests = requestService.getAllRequests();
        Iterable<Status> requestStatus = statusService.getAllStatuses();

        model.addAttribute("services", requestServices);
        model.addAttribute("requests", requests);
        model.addAttribute("status", requestStatus);

        return "userRequests" ;
    }

    @GetMapping("/{requestId}/edit")
    public String updateRequest(@PathVariable Integer requestId, Model model) {
        Iterable<Status> requestStatus = statusService.getAllStatuses();
        Iterable<Services> requestServices = servicesService.getAllServices();

        Request request = requestService.getRequestById(requestId);

        model.addAttribute("services", requestServices);
        model.addAttribute("request", request);
        model.addAttribute("status", requestStatus);

        return "createOrEditRequest";
    }

    @PostMapping(path="/{requestId}/edit", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String updateRequest(
            @AuthenticationPrincipal User user,
            @Valid Request request,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file")MultipartFile file,
            @RequestParam("requestService")Integer serviceId,
            @RequestParam("requestStatus")Integer statusId)
            throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("request", request);
        } else {
            saveFile(request, file);
            model.addAttribute("request", null);
            Integer newRequestId = requestService.createRequest(request, statusId, serviceId, user);
        }

        Iterable<Services> requestServices = servicesService.getAllServices();
        Iterable<Request> requests = requestService.getAllRequests();
        Iterable<Status> requestStatus = statusService.getAllStatuses();

        model.addAttribute("services", requestServices);
        model.addAttribute("requests", requests);
        model.addAttribute("status", requestStatus);
        return "userRequests" ;
    }
}
