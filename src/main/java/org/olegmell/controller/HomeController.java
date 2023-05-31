package org.olegmell.controller;

import org.olegmell.domain.Request;
import org.olegmell.domain.Services;
import org.olegmell.domain.Status;
import org.olegmell.domain.User;
import org.olegmell.repository.RequestRepository;
import org.olegmell.repository.ServicesRepository;
import org.olegmell.repository.StatusRepository;
import org.olegmell.service.ServicesService;
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
    private RequestRepository requestRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ServicesService servicesService;

    @Value("${upload.path}")
    private String uploadPath;
    private User user;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/home")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Request> requests = requestRepository.findAll();
        Iterable<Status> requestStatus = statusRepository.findAll();
        Iterable<Services> requestServices = servicesService.getAllServices();


        if (filter != null && !filter.isEmpty()) {
            requests = requestRepository.findByTag(filter);
        } else {
            requests = requestRepository.findAll();
        }
        model.addAttribute("services", requestServices);
        model.addAttribute("requests", requests);
        model.addAttribute("filter", filter);
        model.addAttribute("status", requestStatus);

        return "home";
    }

    @PostMapping(path ="/home", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Request request,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file")MultipartFile file,
            @RequestParam("requestStatus")Integer statusId)
            throws IOException {
        request.setAuthor(user);
        request.setStatus(statusRepository.getOne(statusId));
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("request", request);
        } else {
            saveFile(request, file);
            model.addAttribute("request", null);
            requestRepository.save(request);
        }

        Iterable<Request> requests = requestRepository.findAll();
        Iterable<Status> requestStatus = statusRepository.findAll();

        model.addAttribute("requests", requests);
        model.addAttribute("status", requestStatus);

        return "requestEditAdmin"; //in second version: return main
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

}
