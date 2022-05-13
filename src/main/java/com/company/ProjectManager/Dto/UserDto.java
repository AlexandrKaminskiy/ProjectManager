package com.company.ProjectManager.Dto;

import com.company.ProjectManager.model.Role;
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

}
