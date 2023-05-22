package org.olegmell.controller;

import org.olegmell.domain.Request;
import org.olegmell.domain.Status;
import org.olegmell.domain.User;
import org.olegmell.repository.RequestRepository;
import org.olegmell.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

@Controller
public class UserRequestController {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    StatusRepository statusRepository;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/user-requests/{user}")
    public String userRequests (
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,  //надо подправить на RequestBody
            Model model,
            @RequestParam(required = false) Request request//Request request
    ) {
        Iterable<Status> requestStatus = statusRepository.findAll();
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

        requestRepository.deleteById(requestId);

        return "redirect:/main";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/user-requests/{user}")
    public String updateRequest (

            @PathVariable Long user,
            Model model,
            @RequestParam("id") Request request,
            @RequestParam("text") String text,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (!StringUtils.isEmpty(request)){
            if (!StringUtils.isEmpty(text)){
                request.setText(text);
            }
            if(!StringUtils.isEmpty(tag)){
                request.setTag(tag);
            }
            if(!StringUtils.isEmpty(status)){
                request.setStatus(statusRepository.findFirstByName(status));
            }

            saveFile(request, file);
            requestRepository.save(request);
        }

        return "redirect:/user-requests/" + user;
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
