package com.company.ProjectManager.service;

import com.company.ProjectManager.Dto.UserDto;
import com.company.ProjectManager.exceptions.UserNotFoundException;
import com.company.ProjectManager.model.Role;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> findAllUsers() {
        var users = userRepo.findAll();
        if (users == null) throw new UserNotFoundException();
        return users;
    }

    public User findUser(String username) {
        var user = userRepo.findByUsername(username);
        if (user == null) throw new UserNotFoundException();
        return user;
    }

    public User updateUser(String username, UserDto userDto) {
        User dbUser = userRepo.findByUsername(username);
        if (dbUser == null) throw new UserNotFoundException();
        User user = new User();
        user.setUsername(userDto.getUsername());
        var newRoles = userDto.getRoles();
        newRoles.remove(Role.ADMIN);
        if (newRoles.isEmpty()) newRoles.add(Role.USER);
        user.setRoles(newRoles);
        BeanUtils.copyProperties(user,dbUser,"active","id","password");
        userRepo.save(dbUser);
        return dbUser;
    }

    public User registrateUser(UserDto userDto) {
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

        return user;
    }

}
