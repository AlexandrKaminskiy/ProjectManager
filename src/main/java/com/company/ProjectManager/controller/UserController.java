package com.company.ProjectManager.controller;

import com.company.ProjectManager.Dto.UserDto;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> adminPanel() {
        return userService.findAllUsers();
    }

    @GetMapping("/{username}")
    public User userInfo(@PathVariable String username) {
        return userService.findUser(username);
    }

    @PostMapping()
    public User registateUser(@RequestBody @Valid UserDto userDto) {
        return userService.registrateUser(userDto);
    }

    @PutMapping("/{username}")
    public User updateProject(@PathVariable String username,
                                 @RequestBody @Valid UserDto userDto) {
        return userService.updateUser(username, userDto);
    }
}
