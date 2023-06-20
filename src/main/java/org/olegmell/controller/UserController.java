package org.olegmell.controller;

import liquibase.pro.packaged.A;
import org.olegmell.domain.Request;
import org.olegmell.domain.Services;
import org.olegmell.domain.Status;
import org.olegmell.domain.User;
import org.olegmell.service.ServicesService;
import org.olegmell.service.StatusService;
import org.olegmell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/myrequests")
    public String userRequests (
            @AuthenticationPrincipal User currentUser,
            Model model
    ) {
        Iterable<Services> requestServices = servicesService.getAllServices();
        Iterable<Status> requestStatus = statusService.getAllStatuses();
        Iterable<Request> requests = userService.getActiveUserRequests(currentUser);
        model.addAttribute("services", requestServices);
        model.addAttribute("status", requestStatus);
        model.addAttribute("requests", requests);

        return "userRequests";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }
    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);
        user.setPassword(passwordEncoder.encode(password));

        return "redirect:/user/profile";
    }

}
