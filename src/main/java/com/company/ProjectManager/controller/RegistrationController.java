package com.company.ProjectManager.controller;

import com.company.ProjectManager.Dto.UserDto;
import com.company.ProjectManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public UserDto registateUser(@RequestBody UserDto userDto) {
        return userService.registrateUser(userDto);
    }
}
