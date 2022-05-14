package com.company.ProjectManager.Dto;

import com.company.ProjectManager.model.TaskInfo;
import com.company.ProjectManager.model.User;
import com.sun.istack.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProjectInfoDto {

    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    @ElementCollection
    private List<UserDto> author;
    @NotNull
    @NotEmpty
    @ElementCollection
    private List<TaskInfoDto> tasks;
    @NotNull
    private String companyName;
    @BooleanFlag
    @NotNull
    private Boolean isReady;

    public ProjectInfoDto(Long id, String name, List<User> author,List<TaskInfo> taskInfo,String companyName,Boolean isReady) {
        this.id = id;
        this.name = name;
        this.author = new ArrayList<>();
        author
                .stream()
                .forEach((p) -> this.author.add(new UserDto(p.getUsername(),p.getRoles())));
        this.tasks = new ArrayList<>();
        taskInfo
                .forEach((p) -> this.tasks.add(new TaskInfoDto(p.getId(), p.getTask())));
        this.companyName = companyName;
        this.isReady = isReady;
    }

}
