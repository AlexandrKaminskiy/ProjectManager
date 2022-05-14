package com.company.ProjectManager.service;

import com.company.ProjectManager.Dto.UserDto;
import com.company.ProjectManager.exceptions.UserNotFoundException;
import com.company.ProjectManager.model.Role;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepo userRepo;

    @Test
    void registrateUser() {
        UserDto dto = new UserDto();
        dto.setPassword("1");
        dto.setUsername("testusr");
        dto.setRoles(Collections.singleton(Role.USER));
        var userDto = userService.registrateUser(dto);
        assertEquals(userDto.getUsername(),dto.getUsername());
    }

    @Test
    void updateUser() {
        UserDto dto = new UserDto();
        String name = "notfounded";
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(name,dto));
    }

    @Test
    void createExisted() {
        Mockito.doReturn(new User())
                .when(userRepo)
                .findByUsername("111");
        UserDto userDto = new UserDto();
        userDto.setUsername("111");
        assertNull(userService.registrateUser(userDto));
    }
}