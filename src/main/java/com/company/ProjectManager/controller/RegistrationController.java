package com.company.ProjectManager.controller;

import com.company.ProjectManager.model.Role;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.HashSet;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("registration")
    public String addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null) {
            return "registration";
        }
        user.setActive(true);
        user.setRoles(new HashSet<>(){{add(Role.ADMIN);add(Role.USER);}});
        userRepo.save(user);
        return "redirect:/login";
    }




}
