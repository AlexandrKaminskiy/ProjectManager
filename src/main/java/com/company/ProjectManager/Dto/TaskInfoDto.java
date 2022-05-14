package com.company.ProjectManager.Dto;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
public class TaskInfoDto {
    private Long id;
    @NotNull
    @NotEmpty
    private String task;

    public TaskInfoDto(Long id, String taskName) {
        this.id = id;
        this.task = taskName;
    }
}