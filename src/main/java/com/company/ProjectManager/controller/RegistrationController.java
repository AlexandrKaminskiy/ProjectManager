package com.company.ProjectManager.controller;

import com.company.ProjectManager.Dto.UserDto;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User registateUser(@RequestBody @Valid UserDto userDto) {
        return userService.registrateUser(userDto);
    }
}
