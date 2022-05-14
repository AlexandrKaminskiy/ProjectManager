package com.company.ProjectManager.Dto;

import com.company.ProjectManager.model.Role;
import com.company.ProjectManager.model.User;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private String username;
    private String password;
    private Set<Role> roles;

    public UserDto(String username, Set<Role> roles) {
        this.username = username;
        this.roles = roles;
    }

    public UserDto(){
    }

    public static UserDto toUserInfoDto(User user) {
        return new UserDto(user.getUsername(), user.getRoles());
    }
}
