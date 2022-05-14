package com.company.ProjectManager.service;

import com.company.ProjectManager.Dto.UserDto;
import com.company.ProjectManager.exceptions.UserNotFoundException;
import com.company.ProjectManager.model.Role;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.repos.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<UserDto> findAllUsers() {
        ArrayList<UserDto> userDtos = new ArrayList<>();
        var users = userRepo.findAll();
        if (users == null) throw new UserNotFoundException();
        users.stream().forEach((p) -> userDtos.add(new UserDto(p.getUsername(),p.getRoles())));
        return userDtos;
    }

    public UserDto findUser(String username) {
        var user = userRepo.findByUsername(username);
        if (user == null) throw new UserNotFoundException();
        UserDto userDto = new UserDto(user.getUsername(),user.getRoles());
        return userDto;
    }

    public UserDto updateUser(String username, UserDto userDto) {
        User dbUser = userRepo.findByUsername(username);
        if (dbUser == null) throw new UserNotFoundException();
        User user = new User();
        user.setUsername(userDto.getUsername());
        var newRoles = userDto.getRoles();
        newRoles.remove(Role.ADMIN);
        if (newRoles.isEmpty()) newRoles.add(Role.USER);
        user.setRoles(newRoles);
        BeanUtils.copyProperties(dbUser,user,"id");
        userRepo.save(dbUser);
        userDto.setPassword(null);
        return userDto;
    }

    public UserDto registrateUser(UserDto userDto) {
        User userFromDb = userRepo.findByUsername(userDto.getUsername());
        if (userFromDb != null) {
            return null;
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);
        userDto.setPassword(null);
        return userDto;
    }

}
