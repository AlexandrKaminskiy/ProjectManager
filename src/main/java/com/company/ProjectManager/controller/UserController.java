package com.company.ProjectManager.controller;

import com.company.ProjectManager.Dto.UserDto;
import com.company.ProjectManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public List<UserDto> adminPanel() {
        return userService.findAllUsers();
    }

    @GetMapping("{username}")
    public UserDto userInfo(@PathVariable String username) {
        return userService.findUser(username);
    }

    @PostMapping()
    public UserDto registateUser(@RequestBody UserDto userDto) {
        return userService.registrateUser(userDto);
    }

    @PutMapping("{username}")
    public UserDto updateProject(@PathVariable String username,
                                 @RequestBody UserDto userDto) {
        return userService.updateUser(username, userDto);
    }
}
