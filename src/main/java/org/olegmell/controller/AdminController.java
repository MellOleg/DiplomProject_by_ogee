package org.olegmell.controller;

import org.olegmell.domain.Request;
import org.olegmell.domain.Services;
import org.olegmell.domain.Status;
import org.olegmell.domain.User;
import org.olegmell.service.RequestService;
import org.olegmell.service.ServicesService;
import org.olegmell.service.StatusService;
import org.olegmell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private UserService userService;


    @GetMapping("/activerequests")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Request> requests = requestService.getAllActiveRequests();
        Iterable<Status> requestStatus = statusService.getAllStatuses();
        Iterable<Services> requestServices = servicesService.getAllServices();

        requests = requestService.getAllActiveRequests();

        model.addAttribute("services", requestServices);
        model.addAttribute("requests", requests);
        model.addAttribute("filter", filter);
        model.addAttribute("status", requestStatus);

        return "home";
    }

    @GetMapping("/pendingrequests")
    public String newAdminPage(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Request> requests = requestService.getAllActiveRequests();
        Iterable<Status> requestStatus = statusService.getAllStatuses();
        Iterable<Services> requestServices = servicesService.getAllServices();

        requests = requestService.getAllPendingRequests();

        model.addAttribute("services", requestServices);
        model.addAttribute("requests", requests);
        model.addAttribute("filter", filter);
        model.addAttribute("status", requestStatus);

        return "adminpage";
    }

    @GetMapping("/userslist")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());

        return "userList";
    }

    @GetMapping("/user/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.findAll());

        return "userEdit";
    }

    @PostMapping("/user/{user}")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, form);

        return "redirect:/admin/userlist";
    }

    @GetMapping("/user/delete/{user}")
    public String deleteUser(
            @PathVariable Integer user
    ) throws IOException {

        userService.deleteUserById(user);

        return "redirect:/home";
    }
}
