package com.company.ProjectManager.Dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TaskInfoDto {
    private Long id;
    private String taskName;

    public TaskInfoDto(Long id, String taskName) {
        this.id = id;
        this.taskName = taskName;
    }

}