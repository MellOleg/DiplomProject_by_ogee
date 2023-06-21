package org.olegmell.controller;

import org.olegmell.domain.*;
import org.olegmell.service.AddressService;
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
    private StatusService statusService;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private AddressService addressService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/create")
    public String createRequestForm(Model model) {
        Iterable<Services> services = servicesService.getAllServices();
        Iterable<Status> requestStatus = statusService.getAllStatuses();
        Iterable<Address> addresses = addressService.getAllAddresses();

        model.addAttribute("services", services);
        model.addAttribute("status", requestStatus);
        model.addAttribute("addresses", addresses);

        return "createRequest";
    }

    @PostMapping(path ="/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String createRequest(
            @AuthenticationPrincipal User user,
            @Valid Request request,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file")MultipartFile file,
            @RequestParam("requestServices") Integer serviceId,
            @RequestParam("requestStatus")Integer statusId)
            throws IOException {
        Iterable<Services> requestServices = servicesService.getAllServices();
        Iterable<Request> requests = requestService.getAllActiveRequests();
        Iterable<Status> requestStatus = statusService.getAllStatuses();

        model.addAttribute("services", requestServices);
        model.addAttribute("requests", requests);
        model.addAttribute("status", requestStatus);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("request", request);
            return "createRequest" ;
        } else {
            saveFile(request, file);
            model.addAttribute("request", null);
            Integer newRequestId = requestService.createRequest(request, statusId, serviceId, user);
            return "redirect:/user/myrequests" ;
        }

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{requestId}")
    public String deleteRequest(
            @PathVariable Integer requestId, Model model){
        Iterable<Services> requestServices = servicesService.getAllServices();
        Iterable<Request> requests = requestService.getAllActiveRequests();
        Iterable<Status> requestStatus = statusService.getAllStatuses();

        model.addAttribute("services", requestServices);
        model.addAttribute("requests", requests);
        model.addAttribute("status", requestStatus);

        requestService.deleteById(requestId);
        return "redirect:/user/myrequests";
    }


    @GetMapping("/edit/{requestId}")
    public String updateRequest(@PathVariable Integer requestId, Model model) {
        Iterable<Status> requestStatus = statusService.getAllStatuses();
        Iterable<Services> requestServices = servicesService.getAllServices();
        Iterable<Address> addresses = addressService.getAllAddresses();

        Request request = requestService.getRequestById(requestId);

        model.addAttribute("addresses", addresses);
        model.addAttribute("services", requestServices);
        model.addAttribute("request", request);
        model.addAttribute("status", requestStatus);

        return "editRequest";
    }

    @PostMapping(path="/edit/{requestId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String updateRequest(
            @AuthenticationPrincipal User user,
            @Valid Request request,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file")MultipartFile file,
            @RequestParam("address")Integer addressId,
            @RequestParam("requestServices")Integer serviceId,
            @RequestParam("requestStatus")Integer statusId,
            @RequestParam("filename")String filename)
            throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("request", request);
            return "editRequest" ;
        } else if (file.isEmpty()){
            requestService.updateRequest(request, addressId, statusId, serviceId, filename);
        } else {
            saveFile(request, file);
            model.addAttribute("request", null);
            requestService.updateRequest(request, addressId, statusId, serviceId);
        }

        Iterable<Services> requestServices = servicesService.getAllServices();
        Iterable<Request> requests = requestService.getAllActiveRequests();
        Iterable<Status> requestStatus = statusService.getAllStatuses();
        Iterable<Address> addresses = addressService.getAllAddresses();

        model.addAttribute("addresses", addresses);
        model.addAttribute("services", requestServices );
        model.addAttribute("requests", requests);
        model.addAttribute("status", requestStatus);
        return "userRequests" ;
    }

    private void saveFile(Request request, MultipartFile file)
            throws IOException {
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
}
