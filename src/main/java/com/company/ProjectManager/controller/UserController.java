package com.company.ProjectManager.controller;

import com.company.ProjectManager.model.User;
import com.company.ProjectManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public String adminPanel(Model model) {
        model.addAttribute("userList",userService.findAllUsers());
        return "adminpanel";
    }

    @GetMapping("{user}")
    public String editUserPanel(@PathVariable User user) {

        return "edituserpanel";
    }

    @PostMapping("editUser")
    public String editUser(@RequestParam User user,
                           @RequestParam String role) {
        userService.changeUserInfo(role,user);
        return "redirect:/users";
    }


}
