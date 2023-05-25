package org.olegmell.controller;

import org.olegmell.domain.Request;
import org.olegmell.domain.Status;
import org.olegmell.domain.User;
import org.olegmell.repository.RequestRepository;
import org.olegmell.repository.StatusRepository;
import org.olegmell.service.RequestService;
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
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class RequestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    StatusService statusService;

    @Value("${upload.path}")
    private String uploadPath;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/requestEditAdmin")
    public String requestAdminPage(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Request> requests = requestService.getAllRequests();

        if (filter != null && !filter.isEmpty()) {
            requests = requestService.getAllByTag(filter);
        } else {
            requests = requestService.getAllRequests();
        }

        model.addAttribute("requests", requests);
        model.addAttribute("filter", filter);

        return "requestEditAdmin";
    }

    @GetMapping("/user-requests/{user}")
    @ResponseBody
    public String userRequests (
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,  //надо подправить на RequestBody
            Model model,
            @RequestParam(required = false) Request request//Request request
    ) {
        Iterable<Status> requestStatus = statusService.getAllStatuses();
        Set<Request> requests = user.getRequests();
        model.addAttribute("status", requestStatus);
        model.addAttribute("requests", requests);
        model.addAttribute("request", request);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "userRequests";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/del-user-requests/{user}")
    public String deleteRequest(
            @PathVariable Long user,
            @RequestParam("request") Integer requestId
    ) throws IOException {

        requestService.deleteById(requestId);

        return "redirect:/main";
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

    @GetMapping("/createrequest")
    public String createRequest(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Request> requests = requestService.getAllRequests();
        Iterable<Status> requestStatus = statusService.getAllStatuses();

        if (filter != null && !filter.isEmpty()) {
            requests = requestService.getAllByTag(filter);
        } else {
            requests = requestService.getAllRequests();
        }

        model.addAttribute("requests", requests);
        model.addAttribute("filter", filter);
        model.addAttribute("status", requestStatus);

        return "createRequest";
    }

    @PostMapping(path ="/createrequest", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String createrequest(
            @AuthenticationPrincipal User user,
            @Valid Request request,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file")MultipartFile file,
            @RequestParam("requestStatus")Integer statusId)
            throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("request", request);
        } else {
            saveFile(request, file);
            model.addAttribute("request", null);
            Integer newRequestId = requestService.createRequest(request, statusId, user);
        }

        Iterable<Request> requests = requestService.getAllRequests();
        Iterable<Status> requestStatus = statusService.getAllStatuses();

        model.addAttribute("requests", requests);
        model.addAttribute("status", requestStatus);

        return "/user-requests/" + user.getId();
    }
}
