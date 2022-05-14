package com.company.ProjectManager.Dto;

import com.company.ProjectManager.model.Role;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserDto {
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    private String password;
    @NotNull
    @NotEmpty
    @ElementCollection
    private Set<Role> roles;

    public UserDto(String username, Set<Role> roles) {
        this.username = username;
        this.roles = roles;
    }

}
