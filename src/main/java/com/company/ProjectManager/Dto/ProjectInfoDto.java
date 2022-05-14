package com.company.ProjectManager.Dto;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.model.TaskInfo;
import com.company.ProjectManager.model.User;
import com.company.ProjectManager.service.ProjectServise;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectInfoDto {

    private Long id;
    private String name;
    private List<UserDto> author;
    private List<TaskInfoDto> tasks;
    private String companyName;
    private Boolean isReady;

    public ProjectInfoDto() {
    }

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
