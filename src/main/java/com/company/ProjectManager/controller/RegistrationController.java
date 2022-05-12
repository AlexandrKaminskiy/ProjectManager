package com.company.ProjectManager.controller;

import com.company.ProjectManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("registration")
    public String addUser(@RequestParam String username,
                          @RequestParam String password) {
        return userService.registrateUser(username, password);
    }
}
