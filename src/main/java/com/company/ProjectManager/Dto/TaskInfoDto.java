package com.company.ProjectManager.Dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TaskInfoDto {

    private Long projectId;
    private String taskName;

    public TaskInfoDto(Long projectId, String taskName) {
        this.projectId = projectId;
        this.taskName = taskName;
    }

}