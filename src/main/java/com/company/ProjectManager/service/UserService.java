package com.company.ProjectManager.service;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.Role;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import com.company.ProjectManager.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProjectInfoRepo projectInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public void changeUserInfo(String role, User user) {
        user.getRoles().clear();
        user.getRoles().add(Role.valueOf(role));

        userRepo.save(user);
    }


}
